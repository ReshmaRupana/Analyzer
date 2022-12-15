/**
 * 
 */
package com.disys.analyzer.repository.custom.impl;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Session;
import org.hibernate.jdbc.Work;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.disys.analyzer.config.util.FacesUtils;
import com.disys.analyzer.model.dto.ChartDataMapping;
import com.disys.analyzer.repository.custom.AnalyserChartDataRepositoryCustom;

/**
 * @author Sajid
 * 
 *
 */
public class AnalyserChartDataRepositoryImpl implements
		AnalyserChartDataRepositoryCustom {

	@PersistenceContext
	EntityManager entityManager;

	public static final String LIST_COMMISSION_IN_EACH_MONTH = "getCommissionInEachMonthOnType";
	public static final String LIST_COMPARABLE_COMMISSION = "getComparablePayableCommissionOnPerMonthBasis";
	public static final String LIST_PAYABLE_COMMISSION = "getPayableCommissionOnPerMonthBasis";
	public static final String LIST_TOTAL_COMMISSION = "getTotalCommissionInYearOnCommissionType";

	private List<ChartDataMapping> callProcedure(String commissionPersonEmplIdOrOprId, String fiscalYear,String action){
		Session session = entityManager.unwrap(Session.class);
		AnalyserChartData work = new AnalyserChartData(
				commissionPersonEmplIdOrOprId, fiscalYear,
				action);
		session.doWork(work);
		List<ChartDataMapping> list = work.getList();
		return list;
	}
	
	/* (non-Javadoc)
	 * @see com.disys.analyzer.repository.custom.AnalyserChartDataRepositoryCustom#getTotalCommissionInYearOnCommissionType(java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public List<ChartDataMapping> getTotalCommissionInYearOnCommissionType(
			String commissionPersonEmplIdOrOprId, String fiscalYear) {
		return callProcedure(commissionPersonEmplIdOrOprId, fiscalYear, LIST_TOTAL_COMMISSION);
	}

	/* (non-Javadoc)
	 * @see com.disys.analyzer.repository.custom.AnalyserChartDataRepositoryCustom#getCommissionInEachMonthOnType(java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public List<ChartDataMapping> getCommissionInEachMonthOnType(
			String commissionPersonEmplIdOrOprId, String fiscalYear) {
		return callProcedure(commissionPersonEmplIdOrOprId, fiscalYear, LIST_COMMISSION_IN_EACH_MONTH);
	}

	/* (non-Javadoc)
	 * @see com.disys.analyzer.repository.custom.AnalyserChartDataRepositoryCustom#getComparablePayableCommissionOnPerMonthBasis(java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public List<ChartDataMapping> getComparablePayableCommissionOnPerMonthBasis(
			String commissionPersonEmplIdOrOprId, String fiscalYear) {
		return callProcedure(commissionPersonEmplIdOrOprId, fiscalYear, LIST_COMPARABLE_COMMISSION);
	}

	/* (non-Javadoc)
	 * @see com.disys.analyzer.repository.custom.AnalyserChartDataRepositoryCustom#getPayableCommissionOnPerMonthBasis(java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public List<ChartDataMapping> getPayableCommissionOnPerMonthBasis(
			String commissionPersonEmplIdOrOprId, String fiscalYear) {
		return callProcedure(commissionPersonEmplIdOrOprId, fiscalYear, LIST_PAYABLE_COMMISSION);
	}

	private static class AnalyserChartData implements Work {
		public Logger logger = LoggerFactory.getLogger(getClass());
		private List<ChartDataMapping> list;
		private ChartDataMapping data;
		String commissionPersonEmplIdOrOprId;
		String fiscalYear;
		String action;

		
		/**
		 * @param analyserAddressId
		 */
		public AnalyserChartData(String commissionPersonEmplIdOrOprId,
				String fiscalYear, String action) {
			super();
			this.commissionPersonEmplIdOrOprId = commissionPersonEmplIdOrOprId;
			this.fiscalYear = fiscalYear;
			this.action = action;
		}

		@Override
		public void execute(Connection connection) throws SQLException {
			CallableStatement cstmt = connection.prepareCall("{call "
					+ FacesUtils.SCHEMA_WIRELESS + "." + action + "(?, ?)}");
			cstmt.setString("varCommissionPersonEmplIdOrOprId",
					commissionPersonEmplIdOrOprId.toString());
			cstmt.setString("varFiscalYear", fiscalYear.toString());

			ResultSet rs = cstmt.executeQuery();

			try {
				list = getRSRowInfo(rs);
				if(list == null){
					list = new ArrayList<ChartDataMapping>();
				}
				logger.debug("List size for "+action+" is : "+list.size());
				System.out.println(list.size());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				if (rs != null)
					rs.close();
				if (cstmt != null)
					cstmt.close();
			}
		}

		private List<ChartDataMapping> getRSRowInfo(ResultSet rs)
				throws Exception {
			ResultSetMetaData rsMeta;
			try {
				rsMeta = rs.getMetaData();
				int iRowCount = rsMeta.getColumnCount();
				list = new ArrayList<ChartDataMapping>();
				while (rs.next()) {
					data = new ChartDataMapping();
					for (int i = 0; i < iRowCount; i++) {
						String columnName = rsMeta.getColumnLabel(i + 1);
						try {
							if (columnName.equalsIgnoreCase("FISCALYEAR")) {
								data.setFiscalYear((Integer) getRSColumnValue(
										rs, rsMeta, i + 1));
							} else if (columnName
									.equalsIgnoreCase("accountingperiod")) {
								data.setAccountingPeriod((Integer) getRSColumnValue(
										rs, rsMeta, i + 1));
							} else if (columnName
									.equalsIgnoreCase("payablecommissiontype")) {
								data.setPayableCommissionType(((String) getRSColumnValue(
										rs, rsMeta, i + 1)));

							} else if (columnName
									.equalsIgnoreCase("commissionPersonEmplIdOrOprId")) {
								data.setCommissionPersonEmplIdOrOprId((Integer) getRSColumnValue(
										rs, rsMeta, i + 1));
							} else if (columnName.equalsIgnoreCase("total")) {
								Double tot = ((Double) getRSColumnValue(rs,
										rsMeta, i + 1)).doubleValue();
								data.setTotal(tot);
							}
						} catch (Exception ex) {
							continue;
						}
					}
					list.add(data);
				}
				return list;
			} catch (SQLException e) {
				String sz = "An error occured while attempting to parse resultset column information from the database.  Error type :  "
						+ e.getSQLState()
						+ ".  Error Code : "
						+ e.getErrorCode();
				Exception err = new Exception(sz);
				throw err;
			}
		}

		private Object getRSColumnValue(ResultSet rs, ResultSetMetaData rsMeta,
				int pos) throws Exception {
			try {
				int iType = (int) rsMeta.getColumnType(pos);
				switch (iType) {
				case Types.BIGINT:
					return new Long(rs.getLong(pos));
				case Types.NUMERIC: {
					java.math.BigDecimal bigDecimal = rs.getBigDecimal(pos);
					if (bigDecimal == null)
						return null;
					else if (bigDecimal.scale() == 0)
						return new Long(bigDecimal.longValue());
					else
						return new Double(bigDecimal.doubleValue());
				}

				case Types.CHAR: {
					String s = rs.getString(pos);
					// if (rs.wasNull()) return "";
					return s;
				}
				case Types.TIMESTAMP: {
					java.sql.Timestamp d = rs.getTimestamp(pos);
					return d;
				}
				case Types.DATE: {
					java.sql.Date d = rs.getDate(pos);
					return d;
				}
				case Types.DOUBLE:
					return new Double(rs.getDouble(pos));
				case Types.FLOAT:
					return new Float(rs.getFloat(pos));
				case Types.INTEGER:
					return new Integer(rs.getInt(pos));
				case Types.LONGVARCHAR:
					return rs.getString(pos);
				case Types.VARBINARY:
					return rs.getBytes(pos);
				case Types.VARCHAR: {
					String s = rs.getString(pos);
					return s;
				}
				case Types.DECIMAL: {
					BigDecimal s = rs.getBigDecimal(pos);
					return s;
				}
				default:
					return rs.getObject(pos);
				}
			} catch (SQLException e) {
				String sz = "An error occured while attempting to parse resultset column information from the database.  Error type :  "
						+ e.getSQLState()
						+ ".  Error Code : "
						+ e.getErrorCode();
				Exception err = new Exception(sz);
				throw err;
			}

		}

		/**
		 * @return the list
		 */
		public List<ChartDataMapping> getList() {
			return list;
		}

		/**
		 * @param list
		 *            the list to set
		 */
		public void setList(List<ChartDataMapping> list) {
			this.list = list;
		}

		/**
		 * @return the commissionPersonEmplIdOrOprId
		 */
		public String getCommissionPersonEmplIdOrOprId() {
			return commissionPersonEmplIdOrOprId;
		}

		/**
		 * @param commissionPersonEmplIdOrOprId
		 *            the commissionPersonEmplIdOrOprId to set
		 */
		public void setCommissionPersonEmplIdOrOprId(
				String commissionPersonEmplIdOrOprId) {
			this.commissionPersonEmplIdOrOprId = commissionPersonEmplIdOrOprId;
		}

		/**
		 * @return the fiscalYear
		 */
		public String getFiscalYear() {
			return fiscalYear;
		}

		/**
		 * @param fiscalYear
		 *            the fiscalYear to set
		 */
		public void setFiscalYear(String fiscalYear) {
			this.fiscalYear = fiscalYear;
		}
	}

}
