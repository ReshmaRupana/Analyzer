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
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Session;
import org.hibernate.jdbc.Work;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.disys.analyzer.config.util.FacesUtils;
import com.disys.analyzer.model.Analyser;
import com.disys.analyzer.model.dto.AnalyserDTO;
import com.disys.analyzer.model.dto.AnalyserTerminateDTO;
import com.disys.analyzer.model.dto.PortfolioDownstreamRequestDTO;
import com.disys.analyzer.model.dto.PortfolioDownstreamResponseDTO;
import com.disys.analyzer.repository.custom.AnalyserRepositoryCustom;

/**
 * @author Sajid
 * @since Oct 21, 2020
 */
@Repository
@Transactional(readOnly = true)
public class AnalyserRepositoryImpl implements AnalyserRepositoryCustom
{

	public Logger logger = LoggerFactory.getLogger(getClass());

	public static final String VERTICALS_LIST = "verticalsList";

	@PersistenceContext EntityManager entityManager;

	@Override
	public String addUpdateAnalyser(AnalyserDTO analyser, String userId, int actionType)
	{
		// TODO Auto-generated method stub

		String response = "";
		Session session = entityManager.unwrap(Session.class);

		AnalyserDTOWork work = new AnalyserDTOWork(analyser, userId, actionType);
		session.doWork(work);
		response = work.getStatus();
		return response;
	}

	@Override
	public List<Analyser> spGetAnalyserList(String userId, String userList, String orderBy, String searchString,  String companyCode)
	{
		Session session = entityManager.unwrap(Session.class);
		AnalyserListWork work = new AnalyserListWork(userId, userList, orderBy, searchString, companyCode);
		session.doWork(work);
		List<Analyser> list = work.getList();
		return list;
	}

	private static class AnalyserListWork implements Work
	{

		private Logger loggerAnalyserListWork = LoggerFactory.getLogger(AnalyserListWork.class);

		private List<Analyser> list;
		private Analyser analyser;

		String userId;
		String userList;
		String orderBy;
		String searchString;
		String companyCode;
		/**
		 * @param userId
		 * @param userList
		 * @param orderBy
		 * @param searchString
		 */
		public AnalyserListWork(String userId, String userList, String orderBy, String searchString, String companyCode)
		{
			super();
			this.userId = userId;
			this.userList = userList;
			this.orderBy = orderBy;
			this.searchString = searchString;
			this.companyCode = companyCode;
		}

		@Override
		public void execute(Connection connection) throws SQLException
		{

			analyser = new Analyser();
			list = new ArrayList<Analyser>();
			ResultSet rs = null;
			CallableStatement cstmt = null;

			try
			{
				cstmt = connection.prepareCall("{call " + FacesUtils.SCHEMA_WIRELESS + ".spGetAnalyserList(?, ?, ?, ?, ?)}");
				cstmt.setString("varLoggedOnUserID", userId);
				cstmt.setString("varUserList", userList);
				cstmt.setString("varOrderBy", orderBy);
				cstmt.setString("varSearchString", searchString);
				cstmt.setString("varCompanyCode", companyCode);
				cstmt.setFetchSize(100);

				rs = cstmt.executeQuery();
				list = getRSRowInfo(rs);
				System.out.println(list.size());
			}
			catch (Exception e)
			{
				loggerAnalyserListWork.error(e.getMessage());
				loggerAnalyserListWork.debug(e.getMessage());
				e.printStackTrace();
			}
			finally
			{
				if (rs != null) rs.close();
				if (cstmt != null) cstmt.close();
			}
		}

		private List<Analyser> getRSRowInfo(ResultSet rs) throws Exception
		{
			ResultSetMetaData rsMeta;
			try
			{
				rsMeta = rs.getMetaData();
				int iRowCount = rsMeta.getColumnCount();
				while (rs.next())
				{
					analyser = new Analyser();
					for (int i = 0; i < iRowCount; i++)
					{
						String columnName = rsMeta.getColumnLabel(i + 1);
						// put values into the array list
						try
						{
							if (columnName.equalsIgnoreCase("PARENTID"))
							{
								analyser.setParentId((String) getRSColumnValue(rs, rsMeta, i + 1));
							}
							else if (columnName.equalsIgnoreCase("BullhornPlacementId"))
							{
								analyser.setBullhornPlacementId((Integer) getRSColumnValue(rs, rsMeta, i + 1));
							}
							else if (columnName.equalsIgnoreCase("ANALYSERID"))
							{
								long aId = ((BigDecimal) getRSColumnValue(rs, rsMeta, i + 1)).longValue();
								analyser.setAnalyserId(new Long(aId));
							}
							else if (columnName.equalsIgnoreCase("LNAME"))
							{
								analyser.setLastName((String) getRSColumnValue(rs, rsMeta, i + 1));
							}
							else if (columnName.equalsIgnoreCase("FNAME"))
							{
								analyser.setFirstName((String) getRSColumnValue(rs, rsMeta, i + 1));
							}
							else if (columnName.equalsIgnoreCase("RECORDSTATUS"))
							{
								String value = (String) getRSColumnValue(rs, rsMeta, i + 1);
								if (value.equals("1"))
								{
									value = "Pending Manager Approval";
								}
								else if (value.equals("2"))
								{
									value = "Pending Administrator Approval";
								}
								else if (value.equals("3"))
								{
									value = "Approved";
								}
								analyser.setRecordStatus(value);
							}
							else if (columnName.equalsIgnoreCase("SDATE"))
							{
								/*
								 * analyser.setSubmissionDate((Timestamp)
								 * getRSColumnValue( rs, rsMeta, i + 1));
								 */

								analyser.setTerminateDate((String) getRSColumnValue(rs, rsMeta, i + 1));
							}
							else if (columnName.equalsIgnoreCase("USERID"))
							{
								analyser.setUserId((String) getRSColumnValue(rs, rsMeta, i + 1));
							}
							else if (columnName.equalsIgnoreCase("TERMINATED"))
							{
								analyser.setTerminated((String) getRSColumnValue(rs, rsMeta, i + 1));
							}

							else if (columnName.equalsIgnoreCase("RejectionStatus"))
							{
								analyser.setRejectionStatus((Integer) getRSColumnValue(rs, rsMeta, i + 1));
							}
							else if (columnName.equalsIgnoreCase("RejectionReason"))
							{
								analyser.setRejectionReason((String) getRSColumnValue(rs, rsMeta, i + 1));
							}

							else if (columnName.equalsIgnoreCase("RejectionDate"))
							{
								analyser.setRejectionDate((Timestamp) getRSColumnValue(rs, rsMeta, i + 1));
							}

							else if (columnName.equalsIgnoreCase("RejectedBy"))
							{
								analyser.setRejectedBy((String) getRSColumnValue(rs, rsMeta, i + 1));
							}

							else if (columnName.equalsIgnoreCase("BRANCH"))
							{
								analyser.setBranch((String) getRSColumnValue(rs, rsMeta, i + 1));
							}

							else if (columnName.equalsIgnoreCase("VERTICAL"))
							{
								analyser.setVertical((String) getRSColumnValue(rs, rsMeta, i + 1));
							}

							else if (columnName.equalsIgnoreCase("COMPANY"))
							{
								analyser.setClientCompanyName((String) getRSColumnValue(rs, rsMeta, i + 1));
							}

							else if (columnName.equalsIgnoreCase("RecruitingClassification"))
							{
								analyser.setRecruitingClassification((String) getRSColumnValue(rs, rsMeta, i + 1));
							}

							else if (columnName.equalsIgnoreCase("IsRehired"))
							{
								analyser.setIsRehired((String) getRSColumnValue(rs, rsMeta, i + 1));
							}

							else if (columnName.equalsIgnoreCase("CompanyCode"))
							{
								analyser.setCompanyCode((String) getRSColumnValue(rs, rsMeta, i + 1));
							}

						}
						catch (Exception ex)
						{
							continue;
						}
					}
					list.add(analyser);
				}
				return list;
			}
			catch (SQLException e)
			{
				String sz = "An error occured while attempting to parse resultset column information from the database.  Error type :  "
						+ e.getSQLState() + ".  Error Code : " + e.getErrorCode();
				Exception err = new Exception(sz);
				throw err;
			}
		}

		private Object getRSColumnValue(ResultSet rs, ResultSetMetaData rsMeta, int pos) throws Exception
		{
			try
			{
				int iType = (int) rsMeta.getColumnType(pos);
				switch (iType)
				{
				case Types.BIGINT:
					return new Long(rs.getLong(pos));
				case Types.NUMERIC:
				{
					java.math.BigDecimal bigDecimal = rs.getBigDecimal(pos);
					if (bigDecimal == null) return null;
					else if (bigDecimal.scale() == 0) return new Long(bigDecimal.longValue());
					else return new Double(bigDecimal.doubleValue());
				}

				case Types.CHAR:
				{
					String s = rs.getString(pos);
					// if (rs.wasNull()) return "";
					return s;
				}
				case Types.TIMESTAMP:
				{
					java.sql.Timestamp d = rs.getTimestamp(pos);
					return d;
				}
				case Types.DATE:
				{
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
				case Types.VARCHAR:
				{
					String s = rs.getString(pos);
					return s;
				}
				case Types.DECIMAL:
				{
					BigDecimal s = rs.getBigDecimal(pos);
					return s;
				}
				default:
					return rs.getObject(pos);
				}
			}
			catch (SQLException e)
			{
				String sz = "An error occured while attempting to parse resultset column information from the database.  Error type :  "
						+ e.getSQLState() + ".  Error Code : " + e.getErrorCode();
				Exception err = new Exception(sz);
				throw err;
			}

		}

		/**
		 * @return the list
		 */
		public List<Analyser> getList()
		{
			return list;
		}

	}

	private static class AnalyserDTOWork implements Work
	{

		private Logger logger = LoggerFactory.getLogger(getClass());

		String status;

		AnalyserDTO analyser;
		String userId;
		int actionType;

		/**
		 * @param analyser
		 * @param userId
		 * @param actionType
		 */
		public AnalyserDTOWork(AnalyserDTO analyser, String userId, int actionType)
		{
			super();
			this.analyser = analyser;
			this.userId = userId;
			this.actionType = actionType;
		}

		@Override
		public void execute(Connection connection) throws SQLException
		{
			status = "Its done!";

			CallableStatement callStmt = null;
			ResultSet rs = null;
			try
			{
				String analyserId = analyser.getAnalyserId();
				if (analyserId == null || analyserId.trim().equals(""))
				{
					analyserId = "0";
				}

				Integer references;
				Integer h1;
				Integer execOrphan;
				Integer recruiterOrphan;
				Integer other1Orphan;
				Integer other2Orphan;
				Integer placement;
				Integer com;
				Integer execOrphanInter;
				Integer other1OrphanInter;
				Integer other2OrphanInter;
				Integer recruiterOrphanInter;

				analyser.setCustom1("--");
				analyser.setCustom2("--");
				analyser.setMrmrs("--");
				analyser.setJrsr("--");
				analyser.setPonumber("--");
				analyser.setPermGAAmount(0.00);
				analyser.setVerticalTimesheetType("--");
				analyser.setPlacement(true);

				if (analyser.getPlacement())
				{
					placement = 1;
				}
				else
				{
					placement = 0;
				}

				if (analyser.getReferences())
				{
					references = 1;
				}
				else
				{
					references = 0;
				}

				if (analyser.getH1())
				{
					h1 = 1;
				}
				else
				{
					h1 = 0;
				}

				if (analyser.getExecOrphan())
				{
					execOrphan = 1;
				}
				else
				{
					execOrphan = 0;
				}

				if (analyser.getRecruiterOrphan())
				{
					recruiterOrphan = 1;
				}
				else
				{
					recruiterOrphan = 0;
				}

				if (analyser.getOther1Orphan())
				{
					other1Orphan = 1;
				}
				else
				{
					other1Orphan = 0;
				}

				if (analyser.getOther2Orphan())
				{
					other2Orphan = 1;
				}
				else
				{
					other2Orphan = 0;
				}
				if (analyser.getCom())
				{
					com = 1;
				}
				else
				{
					com = 0;
				}

				if (analyser.getExecOrphanInter() != null && analyser.getExecOrphanInter())
				{
					execOrphanInter = 1;
				}
				else
				{
					execOrphanInter = 0;
				}

				if (analyser.getOther1OrphanInter() != null && analyser.getOther1OrphanInter())
				{
					other1OrphanInter = 1;
				}
				else
				{
					other1OrphanInter = 0;
				}
				if (analyser.getOther2OrphanInter() != null && analyser.getOther2OrphanInter())
				{
					other2OrphanInter = 1;
				}
				else
				{
					other2OrphanInter = 0;
				}
				if (analyser.getRecruiterOrphanInter() != null && analyser.getRecruiterOrphanInter())
				{
					recruiterOrphanInter = 1;
				}
				else
				{
					recruiterOrphanInter = 0;
				}

				if (analyser.getBenefitsAmount() == null || analyser.getBenefitsAmount().trim().equals("null"))
				{
					analyser.setBenefitsAmount("0");
				}
				if (analyser.getPercent401k() == null || analyser.getPercent401k().trim().equals("null"))
				{
					analyser.setPercent401k("0");
				}
				if (analyser.getK401() == null || analyser.getK401().trim().equals("null"))
				{
					analyser.setK401("0");
				}
				if (analyser.getTax() == null || analyser.getTax().trim().equals("null"))
				{
					analyser.setTax("0");
				}
				if (analyser.getRdoPTO() == null || analyser.getRdoPTO().trim().equals("null"))
				{
					analyser.setRdoPTO("0");
				}

				if (analyser.getSickLeaveCap() == null)
				{
					analyser.setSickLeaveCap("0");
				}

				if(analyser.getpTOLimitToOverrideSick() == null)
				{
					analyser.setpTOLimitToOverrideSick(0.00);
				}

				System.out.println("Profit 3 is " + analyser.getProfit());
				System.out.println("Currency is " + analyser.getCurrency());

				String insertStoreProc = "{call " + FacesUtils.SCHEMA_WIRELESS
						+ ".spAddUpdateAnalyserNew(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?"
						+ "   ,?,?,?,?,?,?,?,?,?,?)}";

				callStmt = connection.prepareCall(insertStoreProc);

				/*
				 * callStmt.setString(1, "Gregory.Armstrong@DISYS.COM");
				 * callStmt.setString(2, "A"); callStmt.setString(3, "Company");
				 * callStmt.setString(4, "ALL"); callStmt.setString(5, "99");
				 */

				/*
				 * 
				 * Query to save analyser.
				 * 
				 */

				String val = "";
				int intVal = 0;

				StringBuilder queryToAddAnalyser = new StringBuilder();
				queryToAddAnalyser.append("USE [Analyser]\n");
				queryToAddAnalyser.append("GO\nDECLARE	@return_value int \nEXEC	@return_value = [wireless].[spAddUpdateAnalyserNew]");
				// @intActionType
				callStmt.setInt(1, actionType);
				queryToAddAnalyser.append("\n@intActionType = " + actionType + ",");
				// @varLoggedUserID
				callStmt.setString(2, userId);
				queryToAddAnalyser.append("\n@varLoggedUserID = N'" + userId + "',");
				// @intAnalyserID
				intVal = analyserId == null ? 0 : Integer.valueOf(analyserId);
				callStmt.setInt(3, intVal);
				queryToAddAnalyser.append("\n@intAnalyserID = " + intVal + ",");
				// @varLName
				callStmt.setString(4, analyser.getlName());
				queryToAddAnalyser.append("\n@varLName = N'" + analyser.getlName() + "',");
				// @varFName
				callStmt.setString(5, analyser.getfName());
				queryToAddAnalyser.append("\n@varFName = N'" + analyser.getfName() + "',");
				// @varEmpType
				callStmt.setString(6, analyser.getEmpType());
				queryToAddAnalyser.append("\n@varEmpType = N'" + analyser.getEmpType() + "',");
				// @varAddress1
				callStmt.setString(7, analyser.getAddress1());
				queryToAddAnalyser.append("\n@varAddress1 = N'" + analyser.getAddress1() + "',");
				// @varAddress2
				callStmt.setString(8, analyser.getAddress2());
				queryToAddAnalyser.append("\n@varAddress2 = N'" + analyser.getAddress2() + "',");
				// @varCity
				callStmt.setString(9, analyser.getCity());
				queryToAddAnalyser.append("\n@varCity = N'" + analyser.getCity() + "',");
				// @varState
				callStmt.setString(10, analyser.getState());
				queryToAddAnalyser.append("\n@varState = N'" + analyser.getState() + "',");
				// @varZip
				callStmt.setString(11, analyser.getZip());
				queryToAddAnalyser.append("\n@varZip = N'" + analyser.getZip() + "',");
				// @varTel
				callStmt.setString(12, analyser.getTelephone());
				queryToAddAnalyser.append("\n@varTel = N'" + analyser.getTelephone() + "',");
				// @varWokphone
				callStmt.setString(13, analyser.getWorkPhone());
				queryToAddAnalyser.append("\n@varWokphone = N'" + analyser.getWorkPhone() + "',");
				// @varFax
				callStmt.setString(14, analyser.getFax());
				queryToAddAnalyser.append("\n@varFax = N'" + analyser.getFax() + "',");
				// @varEmail
				String email = ( analyser.getEmail() == null || analyser.getEmail().trim().length() == 0 ) ? "empty@empty.com" : analyser.getEmail();
				analyser.setEmail(email.trim());
				callStmt.setString(15, analyser.getEmail());
				queryToAddAnalyser.append("\n@varEmail = N'" + analyser.getEmail() + "',");
				// @varSsn
				callStmt.setString(16, analyser.getSsn());
				queryToAddAnalyser.append("\n@varSsn = N'" + analyser.getSsn() + "',");
				// @varDob
				callStmt.setString(17, analyser.getDob());
				queryToAddAnalyser.append("\n@varDob = N'" + analyser.getDob() + "',");
				// @varJobTitle
				callStmt.setString(18, analyser.getJobTitle());
				queryToAddAnalyser.append("\n@varJobTitle = N'" + analyser.getJobTitle() + "',");
				// @varStartDate
				callStmt.setString(19, analyser.getStartDate());
				queryToAddAnalyser.append("\n@varStartDate = N'" + analyser.getStartDate() + "',");
				// @varEndDate
				callStmt.setString(20, analyser.getEndDate());
				queryToAddAnalyser.append("\n@varEndDate = N'" + analyser.getEndDate() + "',");
				// @intClientId
				intVal = analyser.getClientId() == null ? null : Integer.valueOf(analyser.getClientId());
				callStmt.setInt(21, intVal);
				queryToAddAnalyser.append("\n@intClientId = " + intVal + ",");
				// @intClientAddressId
				intVal = analyser.getClientAddressId() == null ? null : Integer.valueOf(analyser.getClientAddressId());
				callStmt.setInt(22, intVal);
				queryToAddAnalyser.append("\n@intClientAddressId = " + intVal + ",");
				// @intClientSiteId
				callStmt.setInt(23, analyser.getClientSiteId());
				queryToAddAnalyser.append("\n@intClientSiteId = " + analyser.getClientSiteId() + ",");
				// @varContractorId
				callStmt.setString(24, analyser.getContractorId());
				queryToAddAnalyser.append("\n@varContractorId = N'" + analyser.getContractorId() + "',");
				// @varCommName1
				callStmt.setString(25, analyser.getCommName1());
				queryToAddAnalyser.append("\n@varCommName1 = N'" + analyser.getCommName1() + "',");
				// @varPer1
				val = analyser.getCommPer1() == null ? "0.00" : analyser.getCommPer1().toString();
				callStmt.setString(26, val);
				queryToAddAnalyser.append("\n@varPer1 = N'" + val + "',");
				// @varCommName2
				callStmt.setString(27, analyser.getCommName2());
				queryToAddAnalyser.append("\n@varCommName2 = N'" + analyser.getCommName2() + "',");
				// @varPer2
				val = analyser.getCommPer2() == null ? "0.00" : analyser.getCommPer2().toString();
				callStmt.setString(28, val);
				queryToAddAnalyser.append("\n@varPer2 = N'" + val + "',");
				// @varCommName3
				callStmt.setString(29, analyser.getCommName3());
				queryToAddAnalyser.append("\n@varCommName3 = N'" + analyser.getCommName3() + "',");
				// @varPer3
				val = analyser.getCommPer3() == null ? "0.00" : analyser.getCommPer3().toString();
				callStmt.setString(30, val);
				queryToAddAnalyser.append("\n@varPer3 = N'" + val + "',");
				// @varCommName4
				callStmt.setString(31, analyser.getCommName4());
				queryToAddAnalyser.append("\n@varCommName4 = N'" + analyser.getCommName4() + "',");
				// @varPer4
				val = analyser.getCommPer4() == null ? "0.00" : analyser.getCommPer4().toString();
				callStmt.setString(32, val);
				queryToAddAnalyser.append("\n@varPer4 = N'" + val + "',");
				// @varCommName5
				callStmt.setString(33, analyser.getCommissionPerson5());
				queryToAddAnalyser.append("\n@varCommName5 = N'" + analyser.getCommissionPerson5() + "',");
				// @varPer5
				val = analyser.getCommission5() == null ? "0.00" : analyser.getCommission5().toString();
				callStmt.setString(34, val);
				queryToAddAnalyser.append("\n@varPer5 = N'" + val + "',");
				// @varTHW
				val = analyser.getTotHoursWorked() == null ? "0.00" : analyser.getTotHoursWorked().toString();
				callStmt.setString(35, val);
				queryToAddAnalyser.append("\n@varTHW = N'" + val + "',");
				// @varBenefitsAmount
				callStmt.setString(36, analyser.getBenefitsAmount());
				queryToAddAnalyser.append("\n@varBenefitsAmount = N'" + analyser.getBenefitsAmount() + "',");
				// @varPercent401k
				callStmt.setString(37, analyser.getPercent401k());
				queryToAddAnalyser.append("\n@varPercent401k = N'" + analyser.getPercent401k() + "',");
				// @varEducation
				callStmt.setString(38, analyser.getEducation());
				queryToAddAnalyser.append("\n@varEducation = N'" + analyser.getEducation() + "',");
				// @varLeave
				val = analyser.getLeave() == null ? "0.00" : analyser.getLeave().toString();
				callStmt.setString(39, val);
				queryToAddAnalyser.append("\n@varLeave = N'" + val + "',");
				// @varOtherDollar
				callStmt.setString(40, analyser.getOd());
				queryToAddAnalyser.append("\n@varOtherDollar = N'" + analyser.getOd() + "',");
				// @varOtherAmounts
				callStmt.setString(41, analyser.getOtherAmounts());
				queryToAddAnalyser.append("\n@varOtherAmounts = N'" + analyser.getOtherAmounts() + "',");
				// @varDiscount
				callStmt.setString(42, analyser.getDiscount());
				queryToAddAnalyser.append("\n@varDiscount = N'" + analyser.getDiscount() + "',");
				// @varBillRate
				val = analyser.getBillRate() == null ? "0.00" : analyser.getBillRate().toString();
				callStmt.setString(43, val);
				queryToAddAnalyser.append("\n@varBillRate = N'" + val + "',");
				// @varHealth
				callStmt.setString(44, analyser.getHealth());
				queryToAddAnalyser.append("\n@varHealth = N'" + analyser.getHealth() + "',");
				// @vark401
				callStmt.setString(45, analyser.getK401());
				queryToAddAnalyser.append("\n@vark401 = N'" + analyser.getK401() + "',");
				// @varTax
				callStmt.setString(46, analyser.getTax());
				queryToAddAnalyser.append("\n@varTax = N'" + analyser.getTax() + "',");
				// @varPayRate
				val = analyser.getPayRate() == null ? "0.00" : analyser.getPayRate().toString();
				callStmt.setString(47, val);
				queryToAddAnalyser.append("\n@varPayRate = N'" + val + "',");
				// @varGA
				val = analyser.getGa() == null ? "0.00" : analyser.getGa().toString();
				callStmt.setString(48, val);
				queryToAddAnalyser.append("\n@varGA = N'" + val + "',");
				// @varCommission
				val = analyser.getCommission() == null ? "0.00" : analyser.getCommission().toString();
				callStmt.setString(49, val);
				queryToAddAnalyser.append("\n@varCommission = N'" + val + "',");
				// @varSpreadWeek
				callStmt.setString(50, analyser.getSpreadWeek());
				queryToAddAnalyser.append("\n@varSpreadWeek = N'" + analyser.getSpreadWeek() + "',");
				// @varOtb
				callStmt.setString(51, analyser.getOtb());
				queryToAddAnalyser.append("\n@varOtb = N'" + analyser.getOtb() + "',");
				// @varOneTimeAmount
				callStmt.setString(52, analyser.getOneTimeAmount());
				queryToAddAnalyser.append("\n@varOneTimeAmount = N'" + analyser.getOneTimeAmount() + "',");
				// @varComments
				callStmt.setString(53, analyser.getComments());
				queryToAddAnalyser.append("\n@varComments = N'" + analyser.getComments() + "',");
				// @varDiscountRate
				callStmt.setString(54, analyser.getDiscountRate());
				queryToAddAnalyser.append("\n@varDiscountRate = N'" + analyser.getDiscountRate() + "',");
				// @varEffectiveDate
				callStmt.setString(55, analyser.getEffectiveDate());
				queryToAddAnalyser.append("\n@varEffectiveDate = N'" + analyser.getEffectiveDate() + "',");
				// @varProfit
				val = analyser.getProfit() == null ? "0.00" : analyser.getProfit().toString();
				callStmt.setString(56, val);
				queryToAddAnalyser.append("\n@varProfit = N'" + val + "',");
				// @varTermDate
				callStmt.setString(57, analyser.getTermDate());
				queryToAddAnalyser.append("\n@varTermDate = N'" + analyser.getTermDate() + "',");
				// @varReason
				callStmt.setString(58, analyser.getReason());
				queryToAddAnalyser.append("\n@varReason = N'" + analyser.getReason() + "',");
				// @varBranch
				callStmt.setString(59, analyser.getBranch());
				queryToAddAnalyser.append("\n@varBranch = N'" + analyser.getBranch() + "',");
				// @varPto
				callStmt.setString(60, analyser.getRdoPTO());
				queryToAddAnalyser.append("\n@varPto = N'" + analyser.getRdoPTO() + "',");
				// @varCurrency
				callStmt.setString(61, analyser.getCurrency());
				queryToAddAnalyser.append("\n@varCurrency = N'" + analyser.getCurrency() + "',");
				// @varMob_Pager
				callStmt.setString(62, analyser.getMobilePager());
				queryToAddAnalyser.append("\n@varMob_Pager = N'" + analyser.getMobilePager() + "',");
				// @varInitial
				callStmt.setString(63, analyser.getInitial());
				queryToAddAnalyser.append("\n@varInitial = N'" + analyser.getInitial() + "',");
				// @varMrmrs
				callStmt.setString(64, analyser.getMrmrs());
				queryToAddAnalyser.append("\n@varMrmrs = N'" + analyser.getMrmrs() + "',");
				// @varJrsr
				callStmt.setString(65, analyser.getJrsr());
				queryToAddAnalyser.append("\n@varJrsr = N'" + analyser.getJrsr() + "',");
				// @varCommision1
				callStmt.setString(66, analyser.getCommision1());
				queryToAddAnalyser.append("\n@varCommision1 = N'" + analyser.getCommision1() + "',");
				// @varCommision2
				callStmt.setString(67, analyser.getCommision2());
				queryToAddAnalyser.append("\n@varCommision2 = N'" + analyser.getCommision2() + "',");
				// @varCommision3
				callStmt.setString(68, analyser.getCommision3());
				queryToAddAnalyser.append("\n@varCommision3 = N'" + analyser.getCommision3() + "',");
				// @varCommision4
				callStmt.setString(69, analyser.getCommision4());
				queryToAddAnalyser.append("\n@varCommision4 = N'" + analyser.getCommision4() + "',");
				// @varCommEffDate
				callStmt.setString(70, analyser.getCommEffDate());
				queryToAddAnalyser.append("\n@varCommEffDate = N'" + analyser.getCommEffDate() + "',");
				// @varTemps
				callStmt.setString(71, analyser.getTemps());
				queryToAddAnalyser.append("\n@varTemps = N'" + analyser.getTemps() + "',");
				// @varAnnualPay
				callStmt.setString(72, analyser.getAnnualPay());
				queryToAddAnalyser.append("\n@varAnnualPay = N'" + analyser.getAnnualPay() + "',");
				// @varContractVehicle
				callStmt.setString(73, analyser.getContractVehicle());
				queryToAddAnalyser.append("\n@varContractVehicle = N'" + analyser.getContractVehicle() + "',");
				if (analyser.getRecordStatus() == null || analyser.getRecordStatus().trim().equals(""))
				{
					analyser.setRecordStatus("1");
				}
				// @intRecordStatus
				callStmt.setInt(74, Integer.valueOf(analyser.getRecordStatus()));
				queryToAddAnalyser.append("\n@intRecordStatus = " + Integer.valueOf(analyser.getRecordStatus()) + ",");
				// @varLiaisonName
				callStmt.setString(75, analyser.getLiaisonName());
				queryToAddAnalyser.append("\n@varLiaisonName = N'" + analyser.getLiaisonName() + "',");
				// @intReferences
				callStmt.setInt(76, references);
				queryToAddAnalyser.append("\n@intReferences = " + references + ",");
				// @intH1
				callStmt.setInt(77, h1);
				queryToAddAnalyser.append("\n@intH1 = " + h1 + ",");
				// @varJobCategory
				callStmt.setString(78, analyser.getJobCategory());
				queryToAddAnalyser.append("\n@varJobCategory = N'" + analyser.getJobCategory() + "',");
				// @execOrphan
				callStmt.setString(79, execOrphan.toString());
				queryToAddAnalyser.append("\n@execOrphan = N'" + execOrphan.toString() + "',");
				// @recruiterOrphan
				callStmt.setString(80, recruiterOrphan.toString());
				queryToAddAnalyser.append("\n@recruiterOrphan = N'" + recruiterOrphan.toString() + "',");
				// @other1Orphan
				callStmt.setString(81, other1Orphan.toString());
				queryToAddAnalyser.append("\n@other1Orphan = N'" + other1Orphan.toString() + "',");
				// @other2Orphan
				callStmt.setString(82, other2Orphan.toString());
				queryToAddAnalyser.append("\n@other2Orphan = N'" + other2Orphan.toString() + "',");
				// @varJobPayRate
				callStmt.setString(83, analyser.getJobPayRate());
				queryToAddAnalyser.append("\n@varJobPayRate = N'" + analyser.getJobPayRate() + "',");
				// @varJobBillRate
				callStmt.setString(84, analyser.getJobBillRate());
				queryToAddAnalyser.append("\n@varJobBillRate = N'" + analyser.getJobBillRate() + "',");
				// @varPoNumber
				callStmt.setString(85, analyser.getPonumber());
				queryToAddAnalyser.append("\n@varPoNumber = N'" + analyser.getPonumber() + "',");
				// @varDoubleTime
				callStmt.setString(86, analyser.getDoubleTime());
				queryToAddAnalyser.append("\n@varDoubleTime = N'" + analyser.getDoubleTime() + "',");
				// @varDoubleTimeBill
				callStmt.setString(87, analyser.getDoubleTimeBill());
				queryToAddAnalyser.append("\n@varDoubleTimeBill = N'" + analyser.getDoubleTimeBill() + "',");
				// @varPerdiem
				callStmt.setString(88, analyser.getPerdiem());
				queryToAddAnalyser.append("\n@varPerdiem = N'" + analyser.getPerdiem() + "',");
				// @varWorkEmail
				callStmt.setString(89, analyser.getWorkEmail());
				queryToAddAnalyser.append("\n@varWorkEmail = N'" + analyser.getWorkEmail() + "',");
				// @varChkReferralFee
				callStmt.setString(90, analyser.getChkReferralFee());
				queryToAddAnalyser.append("\n@varChkReferralFee = N'" + analyser.getChkReferralFee() + "',");
				// @varReferralFeeAmount
				callStmt.setString(91, analyser.getReferralFeeAmount());
				queryToAddAnalyser.append("\n@varReferralFeeAmount = N'" + analyser.getReferralFeeAmount() + "',");
				// @varDentalInsurance
				callStmt.setString(92, analyser.getDentalInsurance());
				queryToAddAnalyser.append("\n@varDentalInsurance = N'" + analyser.getDentalInsurance() + "',");
				// @varStdBenefit
				callStmt.setString(93, analyser.getStdBenefit());
				queryToAddAnalyser.append("\n@varStdBenefit = N'" + analyser.getStdBenefit() + "',");
				// @varLtdBenefit
				callStmt.setString(94, analyser.getLtdBenefit());
				queryToAddAnalyser.append("\n@varLtdBenefit = N'" + analyser.getLtdBenefit() + "',");
				// @varConsultantJobBoard
				callStmt.setString(95, analyser.getConsultantJobBoard());
				queryToAddAnalyser.append("\n@varConsultantJobBoard = N'" + analyser.getConsultantJobBoard() + "',");
				// @varDentalInsuranceAmount
				val = analyser.getDentalInsuranceAmount() == null ? "0.00" : analyser.getDentalInsuranceAmount().toString();
				callStmt.setString(96, val);
				queryToAddAnalyser.append("\n@varDentalInsuranceAmount = N'" + val + "',");
				// @varUnEmployedForTwoMonths
				callStmt.setString(97, analyser.getUnEmployedForTwoMonths());
				queryToAddAnalyser.append("\n@varUnEmployedForTwoMonths = N'" + analyser.getUnEmployedForTwoMonths() + "',");
				// @varEmployeeVeteran
				callStmt.setString(98, analyser.getEmployeeVeteran());
				queryToAddAnalyser.append("\n@varEmployeeVeteran = N'" + analyser.getEmployeeVeteran() + "',");
				// @gender
				callStmt.setString(99, analyser.getGender());
				queryToAddAnalyser.append("\n@gender = N'" + analyser.getGender() + "',");
				// @WorkSiteState
				callStmt.setString(100, analyser.getWorkSiteState());
				queryToAddAnalyser.append("\n@WorkSiteState = N'" + analyser.getWorkSiteState() + "',");
				// @AnalyzerCategory1
				callStmt.setString(101, analyser.getAnalyzerCategory1());
				queryToAddAnalyser.append("\n@AnalyzerCategory1 = N'" + analyser.getAnalyzerCategory1() + "',");
				// @AnalyzerCategory2
				callStmt.setString(102, analyser.getAnalyzerCategory2());
				queryToAddAnalyser.append("\n@AnalyzerCategory2 = N'" + analyser.getAnalyzerCategory2() + "',");
				// @FLSAStatus
				callStmt.setString(103, analyser.getFlsaStatus());
				queryToAddAnalyser.append("\n@FLSAStatus = N'" + analyser.getFlsaStatus() + "',");
				// @FLSAReferance
				callStmt.setString(104, analyser.getFlsaReference());
				queryToAddAnalyser.append("\n@FLSAReferance = N'" + analyser.getFlsaReference() + "',");
				// @Custom1
				callStmt.setString(105, analyser.getCustom1());
				queryToAddAnalyser.append("\n@Custom1 = N'" + analyser.getCustom1() + "',");
				// @Com
				callStmt.setString(106, com.toString());
				queryToAddAnalyser.append("\n@Com = N'" + com.toString() + "',");
				// @Custom2
				callStmt.setString(107, analyser.getCustom2());
				queryToAddAnalyser.append("\n@Custom2 = N'" + analyser.getCustom2() + "',");
				// @Comm
				callStmt.setString(108, analyser.getComm());
				queryToAddAnalyser.append("\n@Comm = N'" + analyser.getComm() + "',");
				// @DealType
				callStmt.setString(109, analyser.getDealType());
				queryToAddAnalyser.append("\n@DealType = N'" + analyser.getDealType() + "',");
				// @IsBonusEligible
				callStmt.setString(110, analyser.getIsBonusEligible());
				queryToAddAnalyser.append("\n@IsBonusEligible = N'" + analyser.getIsBonusEligible() + "',");
				// @BonusAmount
				callStmt.setDouble(111, analyser.getBonusAmount());
				queryToAddAnalyser.append("\n@BonusAmount = " + analyser.getBonusAmount() + ",");
				// @BonusPercentage
				callStmt.setString(112, analyser.getBonusPercentage());
				queryToAddAnalyser.append("\n@BonusPercentage = N'" + analyser.getBonusPercentage() + "',");
				// @PermGAAmount
				callStmt.setDouble(113, analyser.getPermGAAmount());
				queryToAddAnalyser.append("\n@PermGAAmount = " + analyser.getPermGAAmount() + ",");
				// @splitCommissionPercentage1
				callStmt.setString(114, analyser.getSplitCommissionPercentage1());
				queryToAddAnalyser.append("\n@splitCommissionPercentage1 = N'" + analyser.getSplitCommissionPercentage1() + "',");
				// @splitCommissionPercentage2
				callStmt.setString(115, analyser.getSplitCommissionPercentage2());
				queryToAddAnalyser.append("\n@splitCommissionPercentage2 = N'" + analyser.getSplitCommissionPercentage2() + "',");
				// @splitCommissionPercentage3
				callStmt.setString(116, analyser.getSplitCommissionPercentage3());
				queryToAddAnalyser.append("\n@splitCommissionPercentage3 = N'" + analyser.getSplitCommissionPercentage3() + "',");
				// @splitCommissionPercentage4
				callStmt.setString(117, analyser.getSplitCommissionPercentage4());
				queryToAddAnalyser.append("\n@splitCommissionPercentage4 = N'" + analyser.getSplitCommissionPercentage4() + "',");
				// @skillCategory
				callStmt.setString(118, analyser.getSkillCategory());
				queryToAddAnalyser.append("\n@skillCategory = N'" + analyser.getSkillCategory() + "',");
				// @vertical
				callStmt.setString(119, analyser.getVertical());
				queryToAddAnalyser.append("\n@vertical = N'" + analyser.getVertical() + "',");
				// @employeeClass
				callStmt.setString(120, analyser.getEmployeeClass());
				queryToAddAnalyser.append("\n@employeeClass = N'" + analyser.getEmployeeClass() + "',");
				// @verticalTimesheetType
				callStmt.setString(121, analyser.getVerticalTimesheetType());
				queryToAddAnalyser.append("\n@verticalTimesheetType = N'" + analyser.getVerticalTimesheetType() + "',");
				// @ImmigrationCost
				callStmt.setDouble(122, analyser.getImmigrationCost());
				queryToAddAnalyser.append("\n@ImmigrationCost = " + analyser.getImmigrationCost() + ",");
				// @EquipmentCost
				callStmt.setDouble(123, analyser.getEquipmentCost());
				queryToAddAnalyser.append("\n@EquipmentCost = " + analyser.getEquipmentCost() + ",");
				// @Product
				callStmt.setString(124, analyser.getProduct());
				queryToAddAnalyser.append("\n@Product = N'" + analyser.getProduct() + "',");
				// @NonBillableCost
				callStmt.setDouble(125, analyser.getNonBillableCost());
				queryToAddAnalyser.append("\n@NonBillableCost = " + analyser.getNonBillableCost() + ",");
				// @TravelRequired
				callStmt.setString(126, analyser.getTravelRequired());
				queryToAddAnalyser.append("\n@TravelRequired = N'" + analyser.getTravelRequired() + "',");
				// @CommissionModel1
				callStmt.setString(127, analyser.getCommissionModel1());
				queryToAddAnalyser.append("\n@CommissionModel1 = N'" + analyser.getCommissionModel1() + "',");
				// @CommissionModel2
				callStmt.setString(128, analyser.getCommissionModel2());
				queryToAddAnalyser.append("\n@CommissionModel2 = N'" + analyser.getCommissionModel2() + "',");
				// @CommissionModel3
				callStmt.setString(129, analyser.getCommissionModel3());
				queryToAddAnalyser.append("\n@CommissionModel3 = N'" + analyser.getCommissionModel3() + "',");
				// @CommissionModel4
				callStmt.setString(130, analyser.getCommissionModel4());
				queryToAddAnalyser.append("\n@CommissionModel4 = N'" + analyser.getCommissionModel4() + "',");
				// @RecruitingClassification
				callStmt.setString(131, analyser.getRecruitingClassification());
				queryToAddAnalyser.append("\n@RecruitingClassification = N'" + analyser.getRecruitingClassification() + "',");
				// @SickLeaveCost
				callStmt.setDouble(132, analyser.getSickLeaveCost());
				queryToAddAnalyser.append("\n@SickLeaveCost = " + analyser.getSickLeaveCost() + ",");
				// @FalseTermination
				callStmt.setString(133, analyser.getFalseTermination());
				queryToAddAnalyser.append("\n@FalseTermination = N'" + analyser.getFalseTermination() + "',");
				// @CommissionPerson5
				callStmt.setString(134, analyser.getCommissionPerson5());
				queryToAddAnalyser.append("\n@CommissionPerson5 = N'" + analyser.getCommissionPerson5() + "',");
				// @CommissionPerson6
				callStmt.setString(135, analyser.getCommissionPerson6());
				queryToAddAnalyser.append("\n@CommissionPerson6 = N'" + analyser.getCommissionPerson6() + "',");
				// @CommissionPerson7
				callStmt.setString(136, analyser.getCommissionPerson7());
				queryToAddAnalyser.append("\n@CommissionPerson7 = N'" + analyser.getCommissionPerson7() + "',");
				// @CommissionPerson8
				callStmt.setString(137, analyser.getCommissionPerson8());
				queryToAddAnalyser.append("\n@CommissionPerson8 = N'" + analyser.getCommissionPerson8() + "',");
				// @CommissionPerson9
				callStmt.setString(138, analyser.getCommissionPerson9());
				queryToAddAnalyser.append("\n@CommissionPerson9 = N'" + analyser.getCommissionPerson9() + "',");
				// @CommissionPercentage5
				callStmt.setDouble(139, analyser.getCommissionPercentage5());
				queryToAddAnalyser.append("\n@CommissionPercentage5 = " + analyser.getCommissionPercentage5() + ",");
				// @CommissionPercentage6
				callStmt.setDouble(140, analyser.getCommissionPercentage6());
				queryToAddAnalyser.append("\n@CommissionPercentage6 = " + analyser.getCommissionPercentage6() + ",");
				// @CommissionPercentage7
				callStmt.setDouble(141, analyser.getCommissionPercentage7());
				queryToAddAnalyser.append("\n@CommissionPercentage7 = " + analyser.getCommissionPercentage7() + ",");
				// @CommissionPercentage8
				callStmt.setDouble(142, analyser.getCommissionPercentage8());
				queryToAddAnalyser.append("\n@CommissionPercentage8 = " + analyser.getCommissionPercentage8() + ",");
				// @CommissionPercentage9
				callStmt.setDouble(143, analyser.getCommissionPercentage9());
				queryToAddAnalyser.append("\n@CommissionPercentage9 = " + analyser.getCommissionPercentage9() + ",");
				// @Commission5
				callStmt.setDouble(144, analyser.getCommission5());
				queryToAddAnalyser.append("\n@Commission5 = " + analyser.getCommission5() + ",");
				// @Commission6
				callStmt.setDouble(145, analyser.getCommission6());
				queryToAddAnalyser.append("\n@Commission6 = " + analyser.getCommission6() + ",");
				// @Commission7
				callStmt.setDouble(146, analyser.getCommission7());
				queryToAddAnalyser.append("\n@Commission7 = " + analyser.getCommission7() + ",");
				// @Commission8
				callStmt.setDouble(147, analyser.getCommission8());
				queryToAddAnalyser.append("\n@Commission8 = " + analyser.getCommission8() + ",");
				// @Commission9
				callStmt.setDouble(148, analyser.getCommission9());
				queryToAddAnalyser.append("\n@Commission9 = " + analyser.getCommission9() + ",");
				// @ProjectCost
				callStmt.setDouble(149, analyser.getProjectCost());
				queryToAddAnalyser.append("\n@ProjectCost = " + analyser.getProjectCost() + ",");
				// @SickLeavePerHourRate
				callStmt.setDouble(150, analyser.getSickLeavePerHourRate());
				queryToAddAnalyser.append("\n@SickLeavePerHourRate = " + analyser.getSickLeavePerHourRate() + ",");
				// @SickLeaveCap
				callStmt.setString(151, analyser.getSickLeaveCap());
				queryToAddAnalyser.append("\n@SickLeaveCap = N'" + analyser.getSickLeaveCap() + "',");
				// @ManagingDirector
				callStmt.setString(152, analyser.getManagingDirector());
				queryToAddAnalyser.append("\n@ManagingDirector = N'" + analyser.getManagingDirector() + "',");
				// @CommissionPersonGrade1
				callStmt.setString(153, analyser.getCommissionPersonGrade1());
				queryToAddAnalyser.append("\n@CommissionPersonGrade1 = N'" + analyser.getCommissionPersonGrade1() + "',");
				// @CommissionPersonGrade2
				callStmt.setString(154, analyser.getCommissionPersonGrade2());
				queryToAddAnalyser.append("\n@CommissionPersonGrade2 = N'" + analyser.getCommissionPersonGrade2() + "',");
				// @CommissionPersonGrade3
				callStmt.setString(155, analyser.getCommissionPersonGrade3());
				queryToAddAnalyser.append("\n@CommissionPersonGrade3 = N'" + analyser.getCommissionPersonGrade3() + "',");
				// @CommissionPersonGrade4
				callStmt.setString(156, analyser.getCommissionPersonGrade4());
				queryToAddAnalyser.append("\n@CommissionPersonGrade4 = N'" + analyser.getCommissionPersonGrade4() + "',");
				// @IsAddressUSPSValidated
				callStmt.setString(157, analyser.getIsAddressUSPSValidated());
				queryToAddAnalyser.append("\n@IsAddressUSPSValidated = N'" + analyser.getIsAddressUSPSValidated() + "',");
				// @UsPSAddressValidationDate
				val = analyser.getuSPSAddressValidationDate() == null ? null : analyser.getuSPSAddressValidationDate().toString();
				callStmt.setString(158, val);
				queryToAddAnalyser.append("\n@UsPSAddressValidationDate = N'" + val + "',");
				// @billablePTO
				callStmt.setDouble(159, analyser.getBillablePTO());
				queryToAddAnalyser.append("\n@billablePTO = " + analyser.getBillablePTO() + ",");
				// @nonBillablePTO
				callStmt.setDouble(160, analyser.getNonBillablePTO());
				queryToAddAnalyser.append("\n@nonBillablePTO = " + analyser.getNonBillablePTO() + ",");
				// @billablePTOCost
				callStmt.setDouble(161, analyser.getBillablePTOCost());
				queryToAddAnalyser.append("\n@billablePTOCost = " + analyser.getBillablePTOCost() + ",");
				// @nonBillablePTOCost
				callStmt.setDouble(162, analyser.getNonBillablePTOCost());
				queryToAddAnalyser.append("\n@nonBillablePTOCost = " + analyser.getNonBillablePTOCost() + ",");
				// @totalHolidays
				callStmt.setDouble(163, analyser.getTotalHolidays());
				queryToAddAnalyser.append("\n@totalHolidays = " + analyser.getTotalHolidays() + ",");
				// @billableHolidays
				callStmt.setDouble(164, analyser.getBillableHolidays());
				queryToAddAnalyser.append("\n@billableHolidays = " + analyser.getBillableHolidays() + ",");
				// @nonBillableHolidays
				callStmt.setDouble(165, analyser.getNonBillableHolidays());
				queryToAddAnalyser.append("\n@nonBillableHolidays = " + analyser.getNonBillableHolidays() + ",");
				// @billableHolidaysCost
				callStmt.setDouble(166, analyser.getBillableHolidaysCost());
				queryToAddAnalyser.append("\n@billableHolidaysCost = " + analyser.getBillableHolidaysCost() + ",");
				// @nonBillableHolidaysCost
				callStmt.setDouble(167, analyser.getNonBillableHolidaysCost());
				queryToAddAnalyser.append("\n@nonBillableHolidaysCost = " + analyser.getNonBillableHolidaysCost() + ",");
				// @intPermanentPlacement
				callStmt.setString(168, placement.toString());
				queryToAddAnalyser.append("\n@intPermanentPlacement = N'" + placement.toString() + "',");
				// @varPlacementAmount
				callStmt.setString(169, analyser.getPlacementAmount());
				queryToAddAnalyser.append("\n@varPlacementAmount = N'" + analyser.getPlacementAmount() + "',");
				// @varSalaryAmount
				callStmt.setString(170, analyser.getSalaryAmount());
				queryToAddAnalyser.append("\n@varSalaryAmount = N'" + analyser.getSalaryAmount() + "',");
				// @varPlacementPercentage
				callStmt.setString(171, analyser.getPlacementPercentage());
				queryToAddAnalyser.append("\n@varPlacementPercentage = N'" + analyser.getPlacementPercentage() + "',");
				// @varPlacementDate
				callStmt.setString(172, analyser.getPlacementDate());
				queryToAddAnalyser.append("\n@varPlacementDate = N'" + analyser.getPlacementDate() + "',");
				// @intDummy
				callStmt.setString(173, null);
				queryToAddAnalyser.append("\n@intDummy = N'" + null + "',");
				// @intDummy1
				callStmt.setString(174, null);
				queryToAddAnalyser.append("\n@intDummy1 = N'" + null + "',");
				// @execOrphanInter
				callStmt.setString(175, execOrphanInter.toString());
				queryToAddAnalyser.append("\n@execOrphanInter = N'" + execOrphanInter.toString() + "',");
				// @varBranchInter
				callStmt.setString(176, analyser.getBranchInter());
				queryToAddAnalyser.append("\n@varBranchInter = N'" + analyser.getBranchInter() + "',");
				// @varCommentsInter
				callStmt.setString(177, analyser.getCommentsInter());
				queryToAddAnalyser.append("\n@varCommentsInter = N'" + analyser.getCommentsInter() + "',");
				// @varCommName1Inter
				callStmt.setString(178, analyser.getCommName1Inter());
				queryToAddAnalyser.append("\n@varCommName1Inter = N'" + analyser.getCommName1Inter() + "',");
				// @varPer1Inter
				callStmt.setString(179, analyser.getCommPer1Inter());
				queryToAddAnalyser.append("\n@varPer1Inter = N'" + analyser.getCommPer1Inter() + "',");
				// @varCommName2Inter
				callStmt.setString(180, analyser.getCommName2Inter());
				queryToAddAnalyser.append("\n@varCommName2Inter = N'" + analyser.getCommName2Inter() + "',");
				// @varPer2Inter
				callStmt.setString(181, analyser.getCommPer2Inter());
				queryToAddAnalyser.append("\n@varPer2Inter = N'" + analyser.getCommPer2Inter() + "',");
				// @varCommName3Inter
				callStmt.setString(182, analyser.getCommName3Inter());
				queryToAddAnalyser.append("\n@varCommName3Inter = N'" + analyser.getCommName3Inter() + "',");
				// @varPer3Inter
				callStmt.setString(183, analyser.getCommPer3Inter());
				queryToAddAnalyser.append("\n@varPer3Inter = N'" + analyser.getCommPer3Inter() + "',");
				// @varCommName4Inter
				callStmt.setString(184, analyser.getCommName4Inter());
				queryToAddAnalyser.append("\n@varCommName4Inter = N'" + analyser.getCommName4Inter() + "',");
				// @varPer4Inter
				callStmt.setString(185, analyser.getCommPer4Inter());
				queryToAddAnalyser.append("\n@varPer4Inter = N'" + analyser.getCommPer4Inter() + "',");
				// @varCommision1Inter
				callStmt.setString(186, analyser.getCommision1Inter());
				queryToAddAnalyser.append("\n@varCommision1Inter = N'" + analyser.getCommision1Inter() + "',");
				// @varCommision2Inter
				callStmt.setString(187, analyser.getCommision2Inter());
				queryToAddAnalyser.append("\n@varCommision2Inter = N'" + analyser.getCommision2Inter() + "',");
				// @varCommision3Inter
				callStmt.setString(188, analyser.getCommision3Inter());
				queryToAddAnalyser.append("\n@varCommision3Inter = N'" + analyser.getCommision3Inter() + "',");
				// @varCommision4Inter
				callStmt.setString(189, analyser.getCommision4Inter());
				queryToAddAnalyser.append("\n@varCommision4Inter = N'" + analyser.getCommision4Inter() + "',");
				if (analyser.getSplit() == null || analyser.getSplit().trim().equals(""))
				{
					analyser.setSplit("0");
				}
				// @intSplit
				callStmt.setInt(190, Integer.valueOf(analyser.getSplit()));
				queryToAddAnalyser.append("\n@intSplit = " + Integer.valueOf(analyser.getSplit()) + ",");
				// @varCountry
				callStmt.setString(191, analyser.getCountry());
				queryToAddAnalyser.append("\n@varCountry = N'" + analyser.getCountry() + "',");
				// @bitInternational
				callStmt.setString(192, null);
				queryToAddAnalyser.append("\n@bitInternational = N'" + null + "',");
				// @other1OrphanInter
				callStmt.setString(193, other1OrphanInter.toString());
				queryToAddAnalyser.append("\n@other1OrphanInter = N'" + other1OrphanInter.toString() + "',");
				// @other2OrphanInter
				callStmt.setString(194, other2OrphanInter.toString());
				queryToAddAnalyser.append("\n@other2OrphanInter = N'" + other2OrphanInter.toString() + "',");
				// @recruiterOrphanInter
				callStmt.setString(195, recruiterOrphanInter.toString());
				queryToAddAnalyser.append("\n@recruiterOrphanInter = N'" + recruiterOrphanInter.toString() + "',");

				// @grossProfitPercentage1
				callStmt.setDouble(196, analyser.getGrossProfitPercentage());
				queryToAddAnalyser.append("\n@grossProfitPercentage = " + analyser.getGrossProfitPercentage() + ",");

				// @grossProfit1
				callStmt.setDouble(197, analyser.getGrossProfit());
				queryToAddAnalyser.append("\n@grossProfit = " + analyser.getGrossProfit() + ",");

				// @commissionableProfit1
				callStmt.setDouble(198, analyser.getCommissionableProfit());
				queryToAddAnalyser.append("\n@commissionableProfit = " + analyser.getCommissionableProfit() + ",");


				// @geoOffice1
				callStmt.setString(199, analyser.getGeoOffice());
				queryToAddAnalyser.append("\n@geoOffice = N'" + analyser.getGeoOffice() + "',");

				callStmt.setString(200, analyser.getLongitude());
				queryToAddAnalyser.append("\n@longitude = N'" + analyser.getLongitude() + "',");

				callStmt.setString(201, analyser.getLatitude());
				queryToAddAnalyser.append("\n@latitude = N'" + analyser.getLatitude() + "',");

				// @pTOLimitToOverrideSick
				callStmt.setDouble(202, analyser.getpTOLimitToOverrideSick());
				queryToAddAnalyser.append("\n@pTOLimitToOverrideSick = " + analyser.getpTOLimitToOverrideSick() + ",");

				// @DistanceFromWorksite
				callStmt.setString(203, analyser.getDistanceFromWorksite());
				queryToAddAnalyser.append("\n@DistanceFromWorksite = '" + analyser.getDistanceFromWorksite() + "', ");

				// @varSickLeaveType
				callStmt.setString(204, analyser.getSickLeaveType());
				queryToAddAnalyser.append("\n@varSickLeaveType = '" + analyser.getSickLeaveType() + "', ");

				// @varPortfolio
				callStmt.setString(205, analyser.getPortfolio());
				queryToAddAnalyser.append("\n@varPortfolio = '" + analyser.getPortfolio() + "', ");

				// portfolioDescription
				callStmt.setString(206, analyser.getPortfolioDescription());
				queryToAddAnalyser.append("\n@varPortfolioDescription = '" + analyser.getPortfolioDescription() + "', ");

				// CompanyCode
				callStmt.setString(207, analyser.getCompanyCode());
				queryToAddAnalyser.append("\n@varCompanyCode = '" + analyser.getCompanyCode() + "' ,");

				// DealPortfolio1AE1
				callStmt.setString(208, analyser.getDealPortfolio1AE1());
				queryToAddAnalyser.append("\n@varDealPortfolio1_AE1 = '" + analyser.getDealPortfolio1AE1() + "', ");

				// DealPortfolio2REC1
				callStmt.setString(209, analyser.getDealPortfolio2REC1());
				queryToAddAnalyser.append("\n@varDealPortfolio2_REC1 = '" + analyser.getDealPortfolio2REC1() + "', ");

				// DealPortfolio3AE2
				callStmt.setString(210, analyser.getDealPortfolio3AE2());
				queryToAddAnalyser.append("\n@varDealPortfolio3_AE2 = '" + analyser.getDealPortfolio3AE2() + "', ");

				// DealPortfolio4REC2
				callStmt.setString(211, analyser.getDealPortfolio4REC2());
				queryToAddAnalyser.append("\n@varDealPortfolio4_REC2 = '" + analyser.getDealPortfolio4REC2() + "', ");

				// BullhornBatchDataProcessedId
				callStmt.setLong(212, analyser.getBullhornBatchDataProcessedId());
				queryToAddAnalyser.append("\n@varBullhornBatchDataProcessedId = '" + analyser.getBullhornBatchDataProcessedId() + "', ");

				// CoSellStatus
				callStmt.setString(213, analyser.getCoSellStatus());
				queryToAddAnalyser.append("\n@varCoSellStatus = '" + analyser.getCoSellStatus() + "' ,");

				// DataSource
				callStmt.setString(214, analyser.getDataSource());
				queryToAddAnalyser.append("\n@varDataSource = '" + analyser.getDataSource() + "', ");

				// BullhornBatchCode
				callStmt.setString(215, analyser.getBullhornBatchCode());
				queryToAddAnalyser.append("\n@varBullhornBatchCode = '" + analyser.getBullhornBatchCode() + "', ");

				// BullhornBatchAnalyzerStagingId
				callStmt.setLong(216, analyser.getBullhornBatchAnalyzerStagingId());
				queryToAddAnalyser.append("\n@varBullhornBatchAnalyzerStagingId = '" + analyser.getBullhornBatchAnalyzerStagingId() + "' ,");

				// PlacementType
				callStmt.setString(217, analyser.getPlacementType());
				queryToAddAnalyser.append("\n@varPlacementType = '" + analyser.getPlacementType() + "', ");
				/*
				 * Added as part of Tax and Sick Leave Project
				 */


				// BullhornPlacementId
				callStmt.setInt(218, analyser.getBullhornPlacementId());
				queryToAddAnalyser.append("\n@varBullhornPlacementId = '" + analyser.getBullhornPlacementId() + "', ");

				// BullhornBatchCode
				callStmt.setInt(219, analyser.getBullhornBatchInfoId());
				queryToAddAnalyser.append("\n@varBullhornBatchInfoId = '" + analyser.getBullhornBatchInfoId() + "' ,");

				// BullhornTerminationDataProcessedId
				callStmt.setLong(220, analyser.getBullhornTerminationDataProcessedId());
				queryToAddAnalyser.append("\n@varBullhornTerminationDataProcessedId = '" + analyser.getBullhornTerminationDataProcessedId() + "', ");

				// BullhornTerminationDataStagingId
				callStmt.setLong(221, analyser.getBullhornTerminationDataStagingId());
				queryToAddAnalyser.append("\n@varBullhornTerminationDataStagingId = '" + analyser.getBullhornTerminationDataStagingId() + "', ");

				// TerminationBullhornBatchInfoId
				callStmt.setInt(222, analyser.getTerminationBullhornBatchInfoId());
				queryToAddAnalyser.append("\n@varTerminationBullhornBatchInfoId = '" + analyser.getTerminationBullhornBatchInfoId() + "', ");

				// IsModificationRequired
				callStmt.setString(223, analyser.getIsModificationRequired());
				queryToAddAnalyser.append("\n@varIsModificationRequired = N'" + analyser.getIsModificationRequired() + "' ,");

				// OverrideTermination
				callStmt.setString(224, analyser.getOverrideTermination());
				queryToAddAnalyser.append("\n@varOverrideTermination = N'" + analyser.getOverrideTermination() + "', ");

				// WorkFromSource
				callStmt.setString(225, analyser.getWorkFromSource());
				queryToAddAnalyser.append("\n@varWorkFromSource = N'" + analyser.getWorkFromSource() + "',");

				// MajorityWorkPerformed
				callStmt.setString(226, analyser.getMajorityWorkPerformed());
				queryToAddAnalyser.append("\n@varMajorityWorkPerformed = N'" + analyser.getMajorityWorkPerformed() + "',");

				callStmt.setString(227, analyser.getSickLeaveSource());
				queryToAddAnalyser.append("\n@varSickLeaveSource = N'" + analyser.getSickLeaveSource() + "' ");

				queryToAddAnalyser.append("SELECT	'Return Value' = @return_value GO ");

				System.out.println(queryToAddAnalyser.toString());
				logger.debug("Query to add update analyser is : " + queryToAddAnalyser.toString());

				// execute insertDBUSER store procedure
				rs = callStmt.executeQuery();

				if (rs != null)
				{
					while (rs.next())
					{
						status = "" + rs.getInt(1);
					}
					// con.close();
					callStmt.close();
					rs.close();
					// con = null;
					callStmt = null;
					rs = null;
				}
				else
				{
					status = "-1";
					System.err.println("Analyser could not be inserted");
					logger.debug("Analyser could not be inserted");
					throw new Exception("Analyser could not be inserted");
				}
			}
			catch (Exception ex)
			{
				status = "1";
				System.err.println("Exception : " + ex.getMessage());
				System.out.println("Exception in addUpdateAnalyser method of AnalyserRepositoryImpl");
				logger.debug("Exception in addUpdateAnalyser method of AnalyserRepositoryImpl");
				logger.debug("Exception " + ex.getMessage());
				ex.printStackTrace();
			}
			finally
			{
				if (rs != null) rs.close();
				if (callStmt != null) callStmt.close();
			}
			// return result;
		}

		/**
		 * @return the status
		 */
		public String getStatus()
		{
			return status;
		}

	}

	@Override
	public List<AnalyserDTO> getAnalyserCommissionHistory(String userId, String analyserId, String searchString, String orderBy)
	{
		Session session = entityManager.unwrap(Session.class);
		String action = "CommissionHistory";
		AnalyserInfoWork work = new AnalyserInfoWork(userId, analyserId, orderBy, searchString, action);
		session.doWork(work);
		List<AnalyserDTO> list = work.getList();
		if (list != null)
		{
			/*
			 * System.out.println("Analyser History " +
			 * list.get(0).getAnalyserId()); AnalyserDTO analyser = list.get(0);
			 */
			return list;
		}
		return null;
	}

	@Override
	public List<AnalyserDTO> spGetAnalyserHistory(String userId, String analyserId, String orderBy, String searchString)
	{
		Session session = entityManager.unwrap(Session.class);
		String action = "History";
		AnalyserInfoWork work = new AnalyserInfoWork(userId, analyserId, orderBy, searchString, action);
		session.doWork(work);
		List<AnalyserDTO> list = work.getList();
		if (list != null)
		{
			/*
			 * System.out.println("Analyser History " +
			 * list.get(0).getAnalyserId()); AnalyserDTO analyser = list.get(0);
			 */
			return list;
		}
		return null;
	}

	@Override
	public List<AnalyserDTO> spGetAnalyserInfo(String analyserId)
	{
		Session session = entityManager.unwrap(Session.class);
		String action = "Information";
		AnalyserInfoWork work = new AnalyserInfoWork(analyserId, action);
		session.doWork(work);
		List<AnalyserDTO> list = work.getList();
		if (list != null)
		{
			System.out.println("Analyser Information " + list.get(0).getAnalyserId());
			/*
			 * AnalyserDTO analyser = list.get(0); return analyser;
			 */
			return list;
		}
		return null;
	}

	private static class AnalyserInfoWork implements Work
	{

		private List<AnalyserDTO> list;
		String analyserId;

		String userId;
		String orderBy;
		String searchString;

		String action;

		/**
		 * @param analyserId
		 */
		public AnalyserInfoWork(String analyserId, String action)
		{
			super();
			this.analyserId = analyserId;
			this.action = action;
		}

		public AnalyserInfoWork(String userId, String analyserId, String orderBy, String searchString, String action)
		{
			super();
			this.userId = userId;
			this.analyserId = analyserId;
			this.orderBy = orderBy;
			this.searchString = searchString;
			this.action = action;
		}

		@Override
		public void execute(Connection connection) throws SQLException
		{
			list = new ArrayList<AnalyserDTO>();
			CallableStatement callStmt = null;
			ResultSet rs = null;
			AnalyserDTO analyser = null;

			boolean references = false;
			boolean h1 = false;
			boolean execOrphan = false;
			boolean recruiterOrphan = false;
			boolean other1Orphan = false;
			boolean other2Orphan = false;

			boolean execOrphanInter = false;
			boolean recruiterOrphanInter = false;
			boolean other1OrphanInter = false;
			boolean other2OrphanInter = false;

			boolean placement = false;
			boolean com = false;

			try
			{
				if (connection != null)
				{
					String query = "";
					if (action.equals("Information"))
					{
						query = "{call " + FacesUtils.SCHEMA_WIRELESS + ".spGetAnalyserInfo(" + analyserId + ")}";
						System.out.println("query " + query);
					}
					else if (action.equals("History"))
					{
						query = "{call " + FacesUtils.SCHEMA_WIRELESS + ".spGetAnalyserHistory('" + userId + "'" + "," + analyserId + ",'" + orderBy
								+ "','" + searchString + "')}";
						System.out.println("query " + query);
					}
					else if (action.equals("CommissionHistory"))
					{
						query = "{call " + FacesUtils.SCHEMA_WIRELESS + ".spGetCommissionHistory('" + userId + "','" + searchString + "'" + ",'"
								+ analyserId + "')}";
						System.out.println("query " + query);
					}

					callStmt = connection.prepareCall(query);
					rs = callStmt.executeQuery();
					if (rs != null)
					{
						if (action.equalsIgnoreCase("Information"))	//Main Analyzer page
						{
							if (rs.next())
							{

								analyser = new AnalyserDTO();
								analyser.setAnalyserId(rs.getString("AnalyserId"));
								analyser.setRecordStatus(rs.getString("RecordStatus"));
								analyser.setfName(rs.getString("FName"));
								analyser.setlName(rs.getString("LName"));

								analyser.setAddress1(rs.getString("Address1"));
								analyser.setAddress2(rs.getString("Address2"));
								analyser.setCity(rs.getString("City"));
								analyser.setState(rs.getString("State"));
								analyser.setTelephone(rs.getString("Telephone"));
								analyser.setWorkPhone(rs.getString("Workphone"));
								analyser.setZip(rs.getString("Zipcode"));
								analyser.setEmail(rs.getString("Email"));
								analyser.setFax(rs.getString("Fax"));
								analyser.setEmpType(rs.getString("EmpType"));

								analyser.setTotHoursWorked(convertStringToDoubleValue(rs.getString("THW")));
								analyser.setBenefitsAmount(rs.getString("HealthBenefitRates"));
								analyser.setPercent401k(rs.getString("Percentage_401k"));
								analyser.setEducation(rs.getString("Education"));

								analyser.setLeave(convertStringToDoubleValue(rs.getString("Leave")));
								if (analyser.getLeave() == null)
								{
									analyser.setLeave(0.00);
								}

								analyser.setOd(rs.getString("Other_Dollar"));
								analyser.setOtherAmounts(rs.getString("OtherAmount_Hour"));
								analyser.setDiscount(rs.getString("Discount"));

								analyser.setBillRate(convertStringToDoubleValue(rs.getString("BillRate")));

								analyser.setHealth(rs.getString("Health"));
								if (analyser.getHealth() == null || analyser.getHealth().trim().equals("")
										|| analyser.getHealth().trim().equals("null"))
								{
									analyser.setHealth("0");
								}

								analyser.setK401(rs.getString("k401"));
								analyser.setTax(rs.getString("Taxes"));
								analyser.setPayRate(convertStringToDoubleValue(rs.getString("PayRate")));
								analyser.setGa(convertStringToDoubleValue(rs.getString("GA")));
								analyser.setCommission(convertStringToDoubleValue(rs.getString("Commission")));
								analyser.setSpreadWeek(rs.getString("Spread_w"));

								analyser.setOtb(rs.getString("OneTime_Bill"));
								analyser.setOneTimeAmount(rs.getString("OneTime_Pay"));

								analyser.setSsn(rs.getString("SSN"));
								analyser.setDob(rs.getString("DOB"));
								analyser.setJobTitle(rs.getString("Job_Title"));
								analyser.setStartDate(rs.getString("StartDate"));
								analyser.setEndDate(rs.getString("EndDate"));

								analyser.setClientId(rs.getString("ClientId"));
								analyser.setClientAddressId(Integer.valueOf(rs.getString("ClientAddressId")));
								analyser.setClientSiteId(Integer.valueOf(rs.getString("SiteId")));
								analyser.setContractorId(rs.getString("SubContractorId"));

								analyser.setClientCompany(rs.getString("ClientCompany"));
								analyser.setInvoiceAddress(rs.getString("ClientAddress"));
								analyser.setClientManagerName(rs.getString("ClientManagerName"));
								analyser.setManagerPhone(rs.getString("ClientManagerPhone"));
								analyser.setSiteLocation(rs.getString("ClientSiteLocation"));

								analyser.setContCompanyName(rs.getString("ContractorCompany"));
								analyser.setContFin(rs.getString("ContractorFin"));
								analyser.setContPocName(rs.getString("ContractorPOC"));
								analyser.setContPayTerm(rs.getString("ContractorPayTerm"));
								analyser.setContEmail(rs.getString("ContractorEmail"));
								analyser.setContPhone(rs.getString("ContractorPhone"));
								analyser.setContAddress(rs.getString("ContractorAddress"));

								analyser.setComments(rs.getString("Comments"));

								analyser.setRecordStatus(rs.getString("RecordStatus"));

								analyser.setCommName1(rs.getString("Commision_Name1"));
								analyser.setCommPer1(convertStringToDoubleValue(rs.getString("Commision_Percent1")));

								analyser.setCommName2(rs.getString("Commision_Name2"));

								analyser.setCommPer2(convertStringToDoubleValue(rs.getString("Commision_Percent2")));

								analyser.setCommName3(rs.getString("Commision_Name3"));
								analyser.setCommPer3(convertStringToDoubleValue(rs.getString("Commision_Percent3")));

								analyser.setCommName4(rs.getString("Commision_Name4"));
								analyser.setCommPer4(convertStringToDoubleValue(rs.getString("Commision_Percent4")));

								analyser.setProfit(convertStringToDoubleValue(rs.getString("Profit")));
								analyser.setDiscountRate(rs.getString("DiscountRate"));
								analyser.setEffectiveDate(rs.getString("EffectiveDate"));

								analyser.setTermDate(rs.getString("TerminateDate"));
								analyser.setReason(rs.getString("Reason"));

								analyser.setBranch(rs.getString("Branch"));
								analyser.setCurrency(rs.getString("Currency"));
								analyser.setRdoPTO(rs.getString("PTO"));

								analyser.setMobilePager(rs.getString("Mobile_Pager"));
								analyser.setInitial(rs.getString("Initial"));
								analyser.setMrmrs(rs.getString("Mrmrs"));
								analyser.setJrsr(rs.getString("Jrsr"));

								analyser.setCommision1(rs.getString("Commision1"));
								analyser.setCommision2(rs.getString("Commision2"));
								analyser.setCommision3(rs.getString("Commision3"));
								analyser.setCommision4(rs.getString("Commision4"));

								analyser.setInternalAccounting(rs.getString("Internal_Accounting"));

								analyser.setCommEffDate(rs.getString("CommEffDate"));

								analyser.setTemps(rs.getString("Temps"));
								analyser.setAnnualPay(rs.getString("AnnualPay"));
								analyser.setContractVehicle(rs.getString("ContractVehicle"));

								analyser.setJobPayRate(rs.getString("JobPayRate"));
								analyser.setJobBillRate(rs.getString("JobBillRate"));

								analyser.setPonumber(rs.getString("PoNumber"));

								analyser.setLiaisonName(rs.getString("LiaisonName"));

								analyser.setJobCategory(rs.getString("JobCategory"));
								analyser.setCountry(rs.getString("Country"));

								String val = rs.getString("CheckedReferences");
								if (val != null)
								{
									if (val.equals("1"))
									{
										references = true;
									}
									else
									{
										references = false;
									}
								}

								val = rs.getString("H1");
								if (val != null)
								{
									if (val.equals("1"))
									{
										h1 = true;
									}
									else
									{
										h1 = false;
									}
								}

								analyser.setReferences(references);
								analyser.setH1(h1);

								analyser.setBranchInter(rs.getString("BranchInter"));
								analyser.setCommentsInter(rs.getString("CommentsInter"));

								analyser.setCommName1Inter(rs.getString("Commision_Name1Inter"));
								analyser.setCommPer1Inter(rs.getString("Commision_Percent1Inter"));

								analyser.setCommName2Inter(rs.getString("Commision_Name2Inter"));
								analyser.setCommPer2Inter(rs.getString("Commision_Percent2Inter"));

								analyser.setCommName3Inter(rs.getString("Commision_Name3Inter"));
								analyser.setCommPer3Inter(rs.getString("Commision_Percent3Inter"));

								analyser.setCommName4Inter(rs.getString("Commision_Name4Inter"));
								analyser.setCommPer4Inter(rs.getString("Commision_Percent4Inter"));

								analyser.setCommision1Inter(rs.getString("Commision1Inter"));
								analyser.setCommision2Inter(rs.getString("Commision2Inter"));
								analyser.setCommision3Inter(rs.getString("Commision3Inter"));
								analyser.setCommision4Inter(rs.getString("Commision4Inter"));

								analyser.setSplit(rs.getString("Split"));

								val = rs.getString("ExecOrphan");
								if (val != null)
								{
									if (val.equals("1"))
									{
										execOrphan = true;
									}
									else
									{
										execOrphan = false;
									}
								}
								analyser.setExecOrphan(execOrphan);

								val = rs.getString("RecruiterOrphan");
								if (val != null)
								{
									if (val.equals("1"))
									{
										recruiterOrphan = true;
									}
									else
									{
										recruiterOrphan = false;
									}
								}
								analyser.setRecruiterOrphan(recruiterOrphan);

								val = rs.getString("Other1Orphan");
								if (val != null)
								{
									if (val.equals("1"))
									{
										other1Orphan = true;
									}
									else
									{
										other1Orphan = false;
									}
								}
								analyser.setOther1Orphan(other1Orphan);

								val = rs.getString("Other2Orphan");
								if (val != null)
								{
									if (val.equals("1"))
									{
										other2Orphan = true;
									}
									else
									{
										other2Orphan = false;
									}
								}
								analyser.setOther2Orphan(other2Orphan);

								val = rs.getString("ExecOrphanInter");
								if (val != null)
								{
									if (val.equals("1"))
									{
										execOrphanInter = true;
									}
									else
									{
										execOrphanInter = false;
									}
								}
								analyser.setExecOrphanInter(execOrphanInter);

								val = rs.getString("RecruiterOrphanInter");
								if (val != null)
								{
									if (val.equals("1"))
									{
										recruiterOrphanInter = true;
									}
									else
									{
										recruiterOrphanInter = false;
									}
								}
								analyser.setRecruiterOrphanInter(recruiterOrphanInter);

								val = rs.getString("Other1OrphanInter");
								if (val != null)
								{
									if (val.equals("1"))
									{
										other1OrphanInter = true;
									}
									else
									{
										other1OrphanInter = false;
									}
								}
								analyser.setOther1OrphanInter(other1OrphanInter);

								val = rs.getString("Other2OrphanInter");
								if (val != null)
								{
									if (val.equals("1"))
									{
										other2OrphanInter = true;
									}
									else
									{
										other2OrphanInter = false;
									}
								}
								analyser.setOther2OrphanInter(other2OrphanInter);

								val = rs.getString("PermanentPlacement");
								if (val != null)
								{
									if (val.equals("1"))
									{
										placement = true;
									}
									else
									{
										placement = false;
									}
								}
								analyser.setPlacement(placement);

								val = rs.getString("Com");
								if (val != null)
								{
									if (val.equals("1"))
									{
										com = true;
									}
									else
									{
										com = false;
									}
								}
								analyser.setCom(com);

								analyser.setDoubleTime(rs.getString("DoubleTime"));
								analyser.setDoubleTimeBill(rs.getString("DoubleTimeBill"));
								analyser.setPerdiem(rs.getString("PerDiem"));
								analyser.setPlacementAmount(rs.getString("PlacementAmount"));
								analyser.setPlacementDate(rs.getString("PlacementDate"));
								analyser.setSalaryAmount(rs.getString("PlacementSalaryAmount"));
								analyser.setPlacementPercentage(rs.getString("PlacementPercentage"));
								analyser.setWorkEmail(rs.getString("workEmail"));

								//10/25/2019 Tayyab
								val = rs.getString("chkReferralFee");
								analyser.setChkReferralFee(val == null ? "N" : val);
								//analyser.setChkReferralFee(rs.getString("chkReferralFee"));

								analyser.setReferralFeeAmount(rs.getString("referralFeeAmount"));
								analyser.setVolumeDiscount(convertStringToDoubleValue(rs.getString("volumeDiscount")));
								analyser.setPaymentDiscount(convertStringToDoubleValue(rs.getString("paymentDiscount")));
								analyser.setOtherDiscount(convertStringToDoubleValue(rs.getString("otherDiscount")));
								analyser.setBackgroundAmount(convertStringToDoubleValue(rs.getString("backgroundAmount")));
								analyser.setCreditCheckAmount(convertStringToDoubleValue(rs.getString("creditCheckAmount")));
								analyser.setDrugTestAmount(convertStringToDoubleValue(rs.getString("drugTestAmount")));
								analyser.setBackgroundCheckAmount(0.00);
								analyser.setClientEmail(rs.getString("clientEmail"));

								analyser.setManagerEmail(rs.getString("managerEmail"));

								analyser.setUnEmployedForTwoMonths(rs.getString("unEmployedForTwoMonths"));

								analyser.setDentalInsurance(rs.getString("dentalInsurance"));
								//10/25/2019 Tayyab
								val = rs.getString("stdBenefit");
								analyser.setStdBenefit(val == null ? "N" : val);
								//analyser.setStdBenefit(rs.getString("stdBenefit"));

								//10/25/2019 Tayyab
								val = rs.getString("ltdBenefit");
								analyser.setLtdBenefit(val == null ? "N" : val);
								//analyser.setLtdBenefit(rs.getString("ltdBenefit"));

								analyser.setConsultantJobBoard(rs.getString("consultantJobBoard"));

								analyser.setPaymentTerms(rs.getString("paymentTerms"));
								analyser.setAttention(rs.getString("attention"));
								analyser.setDistributionMethod(rs.getString("distributionMethod"));
								analyser.setSpecialNotes(rs.getString("SpecialNotes"));
								analyser.setInvoiceFrequency(rs.getString("invoiceFrequency"));

								analyser.setEmployeeVeteran(rs.getString("EmployeeVeteran"));
								analyser.setGender(rs.getString("Gender")); // add
								// gender
								analyser.setWorkSiteState(rs.getString("WorkSiteState")); // add
								// WorkSiteState
								analyser.setAnalyzerCategory1(rs.getString("AnalyzerCategory1"));
								analyser.setAnalyzerCategory2(rs.getString("AnalyzerCategory2"));
								analyser.setFlsaStatus(rs.getString("FlsaStatus"));
								analyser.setFlsaReference(rs.getString("FlsaReferance"));
								analyser.setCustom1(rs.getString("Custom1")); // EXTRA
								// COLUMN
								analyser.setBillingType(rs.getString("BillingType"));
								analyser.setDeliveryType(rs.getString("DeliveryType"));
								analyser.setPracticeArea(rs.getString("PracticeArea"));
								analyser.setParentId(rs.getString("Parentid"));
								analyser.setDealType(rs.getString("DealType")); // EXTRA
								// COLUMN
								analyser.setComm(rs.getString("Comm")); // EXTRA
								// COLUMN
								analyser.setCustom2(rs.getString("Custom2")); // EXTRA
								// COLUMN
								analyser.setIsBonusEligible(rs.getString("IsBonusEligible"));
								analyser.setBonusAmount(convertStringToDoubleValue(rs.getString("BonusAmount")));
								analyser.setBonusPercentage(rs.getString("BonusPercentage"));
								analyser.setPermGAAmount(convertStringToDoubleValue(rs.getString("PermGAAmount")));
								// ADDED EW FEILDS FOR COMMISSION
								analyser.setSplitCommissionPercentage1(rs.getString("SplitCommissionPercentage1"));
								analyser.setSplitCommissionPercentage2(rs.getString("SplitCommissionPercentage2"));
								analyser.setSplitCommissionPercentage3(rs.getString("SplitCommissionPercentage3"));
								analyser.setSplitCommissionPercentage4(rs.getString("SplitCommissionPercentage4"));
								analyser.setSkillCategory(rs.getString("SkillCategory"));
								analyser.setVertical(rs.getString("Vertical"));
								analyser.setEmployeeClass(rs.getString("EmployeeClass"));
								analyser.setVerticalTimesheetType(rs.getString("VerticalTimesheetType"));
								analyser.setImmigrationCost(convertStringToDoubleValue(rs.getString("ImmigrationCost"))); // 20160627
								analyser.setEquipmentCost(convertStringToDoubleValue(rs.getString("EquipmentCost")));
								analyser.setProduct(rs.getString("Product"));
								analyser.setNonBillableCost(convertStringToDoubleValue(rs.getString("NonBillableCost")));
								analyser.setTravelRequired(rs.getString("TravelRequired"));
								analyser.setCommissionModel1(rs.getString("CommissionModel1"));
								analyser.setCommissionModel2(rs.getString("CommissionModel2"));
								analyser.setCommissionModel3(rs.getString("CommissionModel3"));
								analyser.setCommissionModel4(rs.getString("CommissionModel4"));
								analyser.setRecruitingClassification(rs.getString("RecruitingClassification")); // 20170330

								analyser.setSickLeaveCost(convertStringToDoubleValue(rs.getString("SickLeaveCost"))); // 20171206
								analyser.setFalseTermination(rs.getString("FalseTermination"));
								analyser.setCommissionPerson5(rs.getString("CommissionPerson5"));
								analyser.setCommissionPerson6(rs.getString("CommissionPerson6"));
								analyser.setCommissionPerson7(rs.getString("CommissionPerson7"));
								analyser.setCommissionPerson8(rs.getString("CommissionPerson8"));
								analyser.setCommissionPerson9(rs.getString("CommissionPerson9"));
								analyser.setCommissionPercentage5(convertStringToDoubleValue(rs.getString("CommissionPercentage5")));
								analyser.setCommissionPercentage6(convertStringToDoubleValue(rs.getString("CommissionPercentage6")));
								analyser.setCommissionPercentage7(convertStringToDoubleValue(rs.getString("CommissionPercentage7")));
								analyser.setCommissionPercentage8(convertStringToDoubleValue(rs.getString("CommissionPercentage8")));
								analyser.setCommissionPercentage9(convertStringToDoubleValue(rs.getString("CommissionPercentage9")));
								analyser.setCommission5(convertStringToDoubleValue(rs.getString("Commission5")));
								analyser.setCommission6(convertStringToDoubleValue(rs.getString("Commission6")));
								analyser.setCommission7(convertStringToDoubleValue(rs.getString("Commission7")));
								analyser.setCommission8(convertStringToDoubleValue(rs.getString("Commission8")));
								analyser.setCommission9(convertStringToDoubleValue(rs.getString("Commission9")));
								analyser.setProjectCost(convertStringToDoubleValue(rs.getString("ProjectCost")));
								analyser.setSickLeavePerHourRate(convertStringToDoubleValue(rs.getString("SickLeavePerHourRate")));
								analyser.setSickLeaveCap(rs.getString("SickLeaveCap"));
								analyser.setManagingDirector(rs.getString("ManagingDirector"));

								// ---------- 16-08-2018 -------------------//
								analyser.setBillablePTO(convertStringToDoubleValue(rs.getString("BillablePTO")));
								analyser.setNonBillablePTO(convertStringToDoubleValue(rs.getString("NonBillablePTO")));
								analyser.setBillablePTOCost(convertStringToDoubleValue(rs.getString("BillablePTOCost")));
								analyser.setNonBillablePTOCost(convertStringToDoubleValue(rs.getString("NonBillablePTOCost")));
								analyser.setTotalHolidays(convertStringToDoubleValue(rs.getString("TotalHolidays")));
								analyser.setBillableHolidays(convertStringToDoubleValue(rs.getString("BillableHolidays")));
								analyser.setNonBillableHolidays(convertStringToDoubleValue(rs.getString("NonBillableHolidays")));
								analyser.setBillableHolidaysCost(convertStringToDoubleValue(rs.getString("BillableHolidaysCost")));
								analyser.setNonBillableHolidaysCost(convertStringToDoubleValue(rs.getString("NonBillableHolidaysCost")));

								analyser.setGrossProfitPercentage(convertStringToDoubleValue(rs.getString("GrossProfitPercentage")));
								analyser.setGrossProfit(convertStringToDoubleValue(rs.getString("GrossProfit")));
								analyser.setCommissionableProfit(convertStringToDoubleValue(rs.getString("CommissionableProfit")));
								analyser.setRejectionStatus(rs.getInt("RejectionStatus"));
								analyser.setRejectionReason(rs.getString("RejectionReason"));
								analyser.setRejectedBy(rs.getString("RejectedBy"));
								analyser.setRejectionDate(rs.getTimestamp("RejectionDate"));
								analyser.setGeoOffice(rs.getString("GeoOffice"));
								analyser.setpTOLimitToOverrideSick(convertStringToDoubleValue(rs.getString("PTOLimitToOverrideSick")));

								analyser.setCommissionPersonGrade1(rs.getString("CommissionPersonGrade1"));
								analyser.setCommissionPersonGrade2(rs.getString("CommissionPersonGrade2"));
								analyser.setCommissionPersonGrade3(rs.getString("CommissionPersonGrade3"));
								analyser.setCommissionPersonGrade4(rs.getString("CommissionPersonGrade4"));
								analyser.setSickLeaveType(rs.getString("SickLeaveType"));
								analyser.setDistanceFromWorksite(rs.getString("DistanceFromWorksite"));

								analyser.setPortfolio(rs.getString("Portfolio"));
								analyser.setPortfolioDescription(rs.getString("PortfolioDescription"));
								analyser.setCompanyCode(rs.getString("CompanyCode"));
								analyser.setDealPortfolio1AE1(rs.getString("DealPortfolio1_AE1"));
								analyser.setDealPortfolio2REC1(rs.getString("DealPortfolio2_REC1"));
								analyser.setDealPortfolio3AE2(rs.getString("DealPortfolio3_AE2"));
								analyser.setDealPortfolio4REC2(rs.getString("DealPortfolio4_REC2"));
								analyser.setBullhornBatchDataProcessedId(rs.getLong("BullhornBatchDataProcessedId"));
								analyser.setCoSellStatus(rs.getString("CoSellStatus"));
								analyser.setDataSource(rs.getString("DataSource"));
								analyser.setBullhornBatchCode(rs.getString("BullhornBatchCode"));
								analyser.setBullhornBatchAnalyzerStagingId(rs.getLong("BullhornBatchAnalyzerStagingId"));
								analyser.setPlacementType(rs.getString("PlacementType"));
								analyser.setClientCompanyCode(rs.getString("ClientCompanyCode"));
								analyser.setVendorCompanyCode(rs.getString("VendorCompanyCode"));

								/*
								 * Added as part of Tax and Sick Leave Project
								 */
								analyser.setBullhornPlacementId(rs.getInt("BullhornPlacementId"));
								analyser.setBullhornBatchInfoId(rs.getInt("BullhornBatchInfoId"));
								analyser.setBullhornTerminationDataProcessedId(rs.getLong("BullhornTerminationDataProcessedId"));
								analyser.setBullhornTerminationDataStagingId(rs.getLong("BullhornTerminationDataStagingId"));
								analyser.setTerminationBullhornBatchInfoId(rs.getInt("TerminationBullhornBatchInfoId"));
								analyser.setIsModificationRequired(rs.getString("IsModificationRequired"));
								analyser.setOverrideTermination(rs.getString("OverrideTermination"));
								analyser.setWorkFromSource(rs.getString("WorkFromSource"));
								analyser.setMajorityWorkPerformed(rs.getString("MajorityWorkPerformed"));
								analyser.setSickLeaveSource(rs.getString("SickLeaveSource"));
								list.add(analyser);
							}
							else
							{
								analyser = null;
							}
						}
						if (action.equalsIgnoreCase("History"))		//For View / Approve History
						{
							while (rs.next())
							{
								analyser = new AnalyserDTO();

								analyser.setAnalyserId(rs.getString("AnalyserId"));
								analyser.setRecordStatus(rs.getString("RecordStatus"));
								analyser.setfName(rs.getString("FirstName"));
								analyser.setlName(rs.getString("LastName"));

								analyser.setAddress1(rs.getString("Address1"));
								analyser.setAddress2(rs.getString("Address2"));
								analyser.setCity(rs.getString("City"));
								analyser.setState(rs.getString("State"));
								analyser.setTelephone(rs.getString("Telephone"));
								analyser.setWorkPhone(rs.getString("Workphone"));
								analyser.setZip(rs.getString("Zipcode"));
								analyser.setEmail(rs.getString("Email"));
								analyser.setFax(rs.getString("Fax"));
								analyser.setEmpType(rs.getString("EmpType"));

								analyser.setTotHoursWorked(convertStringToDoubleValue(rs.getString("THW")));
								analyser.setBenefitsAmount(rs.getString("HealthBenefitRates"));
								analyser.setPercent401k(rs.getString("Percentage_401k"));
								analyser.setEducation(rs.getString("Education"));

								analyser.setLeave(convertStringToDoubleValue(rs.getString("Leave")));
								if (analyser.getLeave() == null)
								{
									analyser.setLeave(0.00);
								}

								analyser.setOd(rs.getString("Other_Dollar"));
								analyser.setOtherAmounts(rs.getString("OtherAmount_Hour"));
								analyser.setDiscount(rs.getString("Discount"));

								analyser.setBillRate(convertStringToDoubleValue(rs.getString("BillRate")));

								analyser.setHealth(rs.getString("Health"));
								if (analyser.getHealth() == null || analyser.getHealth().trim().equals("")
										|| analyser.getHealth().trim().equals("null"))
								{
									analyser.setHealth("0");
								}

								analyser.setK401(rs.getString("k401"));
								analyser.setTax(rs.getString("Taxes"));
								analyser.setPayRate(convertStringToDoubleValue(rs.getString("PayRate")));
								analyser.setGa(convertStringToDoubleValue(rs.getString("GA")));
								analyser.setCommission(convertStringToDoubleValue(rs.getString("Commission")));
								analyser.setSpreadWeek(rs.getString("Spread_w"));

								analyser.setOtb(rs.getString("OneTime_Bill"));
								analyser.setOneTimeAmount(rs.getString("OneTime_Pay"));

								analyser.setSsn(rs.getString("SSN"));
								analyser.setDob(rs.getString("DOB"));
								analyser.setJobTitle(rs.getString("Job_Title"));
								analyser.setStartDate(rs.getString("StartDate"));
								analyser.setEndDate(rs.getString("EndDate"));

								analyser.setClientId(rs.getString("ClientId"));
								analyser.setClientAddressId(Integer.valueOf(rs.getString("ClientAddressId")));
								analyser.setClientSiteId(Integer.valueOf(rs.getString("SiteId")));
								analyser.setContractorId(rs.getString("SubContractorId"));

								analyser.setInvoiceAddress(rs.getString("ClientAddress"));
								analyser.setClientManagerName(rs.getString("Manager_Name"));
								analyser.setSiteLocation(rs.getString("SiteAddress"));
								analyser.setClientCompany(rs.getString("Client"));

								analyser.setContCompanyName(rs.getString("Contractor"));
								analyser.setContPayTerm(rs.getString("PaymentTerm"));

								analyser.setComments(rs.getString("Comments"));

								analyser.setRecordStatus(rs.getString("RecordStatus"));

								analyser.setCommName1(rs.getString("Commision_Name1"));
								analyser.setCommPer1(convertStringToDoubleValue(rs.getString("Commision_Percent1")));

								analyser.setCommName2(rs.getString("Commision_Name2"));

								analyser.setCommPer2(convertStringToDoubleValue(rs.getString("Commision_Percent2")));

								analyser.setCommName3(rs.getString("Commision_Name3"));
								analyser.setCommPer3(convertStringToDoubleValue(rs.getString("Commision_Percent3")));

								analyser.setCommName4(rs.getString("Commision_Name4"));
								analyser.setCommPer4(convertStringToDoubleValue(rs.getString("Commision_Percent4")));

								analyser.setProfit(convertStringToDoubleValue(rs.getString("Profit")));
								analyser.setDiscountRate(rs.getString("DiscountRate"));
								analyser.setEffectiveDate(rs.getString("EffectiveDate"));

								analyser.setTermDate(rs.getString("TerminateDate"));
								analyser.setReason(rs.getString("Reason"));

								analyser.setBranch(rs.getString("Branch"));
								analyser.setCurrency(rs.getString("Currency"));
								analyser.setRdoPTO(rs.getString("PTO"));

								analyser.setMobilePager(rs.getString("Mobile_Pager"));
								analyser.setInitial(rs.getString("Initial"));
								analyser.setMrmrs(rs.getString("Mrmrs"));
								analyser.setJrsr(rs.getString("Jrsr"));

								analyser.setCommision1(rs.getString("Commision1"));
								analyser.setCommision2(rs.getString("Commision2"));
								analyser.setCommision3(rs.getString("Commision3"));
								analyser.setCommision4(rs.getString("Commision4"));

								analyser.setInternalAccounting(rs.getString("Internal_Accounting"));

								analyser.setCommEffDate(rs.getString("CommEffDate"));

								analyser.setTemps(rs.getString("Temps"));
								analyser.setAnnualPay(rs.getString("AnnualPay"));
								analyser.setContractVehicle(rs.getString("ContractVehicle"));

								analyser.setJobPayRate(rs.getString("JobPayRate"));
								analyser.setJobBillRate(rs.getString("JobBillRate"));

								analyser.setPonumber(rs.getString("PoNumber"));

								analyser.setLiaisonName(rs.getString("LiaisonName"));

								analyser.setJobCategory(rs.getString("JobCategory"));
								analyser.setCountry(rs.getString("Country"));

								String val = rs.getString("CheckedReferences");
								if (val != null)
								{
									if (val.equals("1"))
									{
										references = true;
									}
									else
									{
										references = false;
									}
								}

								val = rs.getString("H1");
								if (val != null)
								{
									if (val.equals("1"))
									{
										h1 = true;
									}
									else
									{
										h1 = false;
									}
								}

								analyser.setReferences(references);
								analyser.setH1(h1);

								analyser.setBranchInter(rs.getString("BranchInter"));
								analyser.setCommentsInter(rs.getString("CommentsInter"));

								analyser.setCommName1Inter(rs.getString("Commision_Name1Inter"));
								analyser.setCommPer1Inter(rs.getString("Commision_Percent1Inter"));

								analyser.setCommName2Inter(rs.getString("Commision_Name2Inter"));
								analyser.setCommPer2Inter(rs.getString("Commision_Percent2Inter"));

								analyser.setCommName3Inter(rs.getString("Commision_Name3Inter"));
								analyser.setCommPer3Inter(rs.getString("Commision_Percent3Inter"));

								analyser.setCommName4Inter(rs.getString("Commision_Name4Inter"));
								analyser.setCommPer4Inter(rs.getString("Commision_Percent4Inter"));

								analyser.setCommision1Inter(rs.getString("Commision1Inter"));
								analyser.setCommision2Inter(rs.getString("Commision2Inter"));
								analyser.setCommision3Inter(rs.getString("Commision3Inter"));
								analyser.setCommision4Inter(rs.getString("Commision4Inter"));

								analyser.setSplit(rs.getString("Split"));

								val = rs.getString("ExecOrphan");
								if (val != null)
								{
									if (val.equals("1"))
									{
										execOrphan = true;
									}
									else
									{
										execOrphan = false;
									}
								}
								analyser.setExecOrphan(execOrphan);

								val = rs.getString("RecruiterOrphan");
								if (val != null)
								{
									if (val.equals("1"))
									{
										recruiterOrphan = true;
									}
									else
									{
										recruiterOrphan = false;
									}
								}
								analyser.setRecruiterOrphan(recruiterOrphan);

								val = rs.getString("Other1Orphan");
								if (val != null)
								{
									if (val.equals("1"))
									{
										other1Orphan = true;
									}
									else
									{
										other1Orphan = false;
									}
								}
								analyser.setOther1Orphan(other1Orphan);

								val = rs.getString("Other2Orphan");
								if (val != null)
								{
									if (val.equals("1"))
									{
										other2Orphan = true;
									}
									else
									{
										other2Orphan = false;
									}
								}
								analyser.setOther2Orphan(other2Orphan);

								val = rs.getString("ExecOrphanInter");
								if (val != null)
								{
									if (val.equals("1"))
									{
										execOrphanInter = true;
									}
									else
									{
										execOrphanInter = false;
									}
								}
								analyser.setExecOrphanInter(execOrphanInter);

								val = rs.getString("RecruiterOrphanInter");
								if (val != null)
								{
									if (val.equals("1"))
									{
										recruiterOrphanInter = true;
									}
									else
									{
										recruiterOrphanInter = false;
									}
								}
								analyser.setRecruiterOrphanInter(recruiterOrphanInter);

								val = rs.getString("Other1OrphanInter");
								if (val != null)
								{
									if (val.equals("1"))
									{
										other1OrphanInter = true;
									}
									else
									{
										other1OrphanInter = false;
									}
								}
								analyser.setOther1OrphanInter(other1OrphanInter);

								val = rs.getString("Other2OrphanInter");
								if (val != null)
								{
									if (val.equals("1"))
									{
										other2OrphanInter = true;
									}
									else
									{
										other2OrphanInter = false;
									}
								}
								analyser.setOther2OrphanInter(other2OrphanInter);

								val = rs.getString("PermanentPlacement");
								if (val != null)
								{
									if (val.equals("1"))
									{
										placement = true;
									}
									else
									{
										placement = false;
									}
								}
								analyser.setPlacement(placement);

								val = rs.getString("Com");
								if (val != null)
								{
									if (val.equals("1"))
									{
										com = true;
									}
									else
									{
										com = false;
									}
								}
								analyser.setCom(com);

								analyser.setDoubleTime(rs.getString("DoubleTime"));
								analyser.setDoubleTimeBill(rs.getString("DoubleTimeBill"));
								analyser.setPerdiem(rs.getString("PerDiem"));
								analyser.setPlacementAmount(rs.getString("PlacementAmount"));
								analyser.setPlacementDate(rs.getString("PlacementDate"));
								analyser.setSalaryAmount(rs.getString("PlacementSalaryAmount"));
								analyser.setPlacementPercentage(rs.getString("PlacementPercentage"));

								//10/25/2019 Tayyab
								val = rs.getString("chkReferralFee");
								analyser.setChkReferralFee(val == null ? "N" : val);
								//analyser.setChkReferralFee(rs.getString("chkReferralFee"));
								analyser.setReferralFeeAmount(rs.getString("referralFeeAmount"));

								analyser.setUnEmployedForTwoMonths(rs.getString("unEmployedForTwoMonths"));

								analyser.setDentalInsurance(rs.getString("dentalInsurance"));

								//10/25/2019 Tayyab
								val = rs.getString("stdBenefit");
								analyser.setStdBenefit(val == null ? "N" : val);
								//analyser.setStdBenefit(rs.getString("stdBenefit"));

								//10/25/2019 Tayyab
								val = rs.getString("ltdBenefit");
								analyser.setLtdBenefit(val == null ? "N" : val);
								//analyser.setLtdBenefit(rs.getString("ltdBenefit"));

								analyser.setConsultantJobBoard(rs.getString("consultantJobBoard"));

								analyser.setEmployeeVeteran(rs.getString("EmployeeVeteran"));
								analyser.setGender(rs.getString("Gender"));
								analyser.setWorkSiteState(rs.getString("WorkSiteState")); // add
								// WorkSiteState
								analyser.setAnalyzerCategory1(rs.getString("AnalyzerCategory1"));
								analyser.setAnalyzerCategory2(rs.getString("AnalyzerCategory2"));
								analyser.setFlsaStatus(rs.getString("FlsaStatus"));
								analyser.setFlsaReference(rs.getString("FlsaReferance"));
								analyser.setCustom1(rs.getString("Custom1"));
								analyser.setParentId(rs.getString("Parentid"));
								analyser.setDealType(rs.getString("DealType"));
								analyser.setComm(rs.getString("Comm"));
								analyser.setCustom2(rs.getString("Custom2"));
								analyser.setIsBonusEligible(rs.getString("IsBonusEligible"));
								analyser.setBonusAmount(convertStringToDoubleValue(rs.getString("BonusAmount")));
								analyser.setBonusPercentage(rs.getString("BonusPercentage"));
								analyser.setPermGAAmount(convertStringToDoubleValue(rs.getString("PermGAAmount")));
								// ADDED EW FEILDS FOR COMMISSION
								analyser.setSplitCommissionPercentage1(rs.getString("SplitCommissionPercentage1"));
								analyser.setSplitCommissionPercentage2(rs.getString("SplitCommissionPercentage2"));
								analyser.setSplitCommissionPercentage3(rs.getString("SplitCommissionPercentage3"));
								analyser.setSplitCommissionPercentage4(rs.getString("SplitCommissionPercentage4"));
								analyser.setSkillCategory(rs.getString("SkillCategory"));
								analyser.setVertical(rs.getString("Vertical"));
								analyser.setEmployeeClass(rs.getString("EmployeeClass"));
								analyser.setImmigrationCost(convertStringToDoubleValue(rs.getString("ImmigrationCost"))); // 20160627
								analyser.setEquipmentCost(convertStringToDoubleValue(rs.getString("EquipmentCost")));
								analyser.setProduct(rs.getString("Product"));
								analyser.setNonBillableCost(convertStringToDoubleValue(rs.getString("NonBillableCost")));
								analyser.setTravelRequired(rs.getString("TravelRequired"));
								analyser.setCommissionModel1(rs.getString("CommissionModel1"));
								analyser.setCommissionModel2(rs.getString("CommissionModel2"));
								analyser.setCommissionModel3(rs.getString("CommissionModel3"));
								analyser.setCommissionModel4(rs.getString("CommissionModel4"));
								analyser.setRecruitingClassification(rs.getString("RecruitingClassification")); // 20170330

								analyser.setSickLeaveCost(convertStringToDoubleValue(rs.getString("SickLeaveCost"))); // 20171206
								analyser.setFalseTermination(rs.getString("FalseTermination"));
								analyser.setCommissionPerson5(rs.getString("CommissionPerson5"));
								analyser.setCommissionPerson6(rs.getString("CommissionPerson6"));
								analyser.setCommissionPerson7(rs.getString("CommissionPerson7"));
								analyser.setCommissionPerson8(rs.getString("CommissionPerson8"));
								analyser.setCommissionPerson9(rs.getString("CommissionPerson9"));
								analyser.setCommissionPercentage5(convertStringToDoubleValue(rs.getString("CommissionPercentage5")));
								analyser.setCommissionPercentage6(convertStringToDoubleValue(rs.getString("CommissionPercentage6")));
								analyser.setCommissionPercentage7(convertStringToDoubleValue(rs.getString("CommissionPercentage7")));
								analyser.setCommissionPercentage8(convertStringToDoubleValue(rs.getString("CommissionPercentage8")));
								analyser.setCommissionPercentage9(convertStringToDoubleValue(rs.getString("CommissionPercentage9")));
								analyser.setCommission5(convertStringToDoubleValue(rs.getString("Commission5")));
								analyser.setCommission6(convertStringToDoubleValue(rs.getString("Commission6")));
								analyser.setCommission7(convertStringToDoubleValue(rs.getString("Commission7")));
								analyser.setCommission8(convertStringToDoubleValue(rs.getString("Commission8")));
								analyser.setCommission9(convertStringToDoubleValue(rs.getString("Commission9")));
								analyser.setProjectCost(convertStringToDoubleValue(rs.getString("ProjectCost")));
								analyser.setSickLeavePerHourRate(convertStringToDoubleValue(rs.getString("SickLeavePerHourRate")));
								analyser.setSickLeaveCap(rs.getString("SickLeaveCap"));
								analyser.setManagingDirector(rs.getString("ManagingDirector"));

								// ---------- 16-08-2018 -------------------//
								analyser.setBillablePTO(convertStringToDoubleValue(rs.getString("BillablePTO")));
								analyser.setNonBillablePTO(convertStringToDoubleValue(rs.getString("NonBillablePTO")));
								analyser.setBillablePTOCost(convertStringToDoubleValue(rs.getString("BillablePTOCost")));
								analyser.setNonBillablePTOCost(convertStringToDoubleValue(rs.getString("NonBillablePTOCost")));
								analyser.setTotalHolidays(convertStringToDoubleValue(rs.getString("TotalHolidays")));
								analyser.setBillableHolidays(convertStringToDoubleValue(rs.getString("BillableHolidays")));
								analyser.setNonBillableHolidays(convertStringToDoubleValue(rs.getString("NonBillableHolidays")));
								analyser.setBillableHolidaysCost(convertStringToDoubleValue(rs.getString("BillableHolidaysCost")));
								analyser.setNonBillableHolidaysCost(convertStringToDoubleValue(rs.getString("NonBillableHolidaysCost")));

								analyser.setGrossProfitPercentage(convertStringToDoubleValue(rs.getString("GrossProfitPercentage")));
								analyser.setGrossProfit(convertStringToDoubleValue(rs.getString("GrossProfit")));
								analyser.setCommissionableProfit(convertStringToDoubleValue(rs.getString("CommissionableProfit")));
								analyser.setRejectionStatus(rs.getInt("RejectionStatus"));
								analyser.setRejectionReason(rs.getString("RejectionReason"));
								analyser.setRejectedBy(rs.getString("RejectedBy"));
								analyser.setRejectionDate(rs.getTimestamp("RejectionDate"));
								analyser.setModifiedBy(rs.getString("UserId"));
								analyser.setGeoOffice(rs.getString("GeoOffice"));
								analyser.setpTOLimitToOverrideSick(convertStringToDoubleValue(rs.getString("PTOLimitToOverrideSick")));

								analyser.setCommissionPersonGrade1(rs.getString("CommissionPersonGrade1"));
								analyser.setCommissionPersonGrade2(rs.getString("CommissionPersonGrade2"));
								analyser.setCommissionPersonGrade3(rs.getString("CommissionPersonGrade3"));
								analyser.setCommissionPersonGrade4(rs.getString("CommissionPersonGrade4"));
								analyser.setSickLeaveType(rs.getString("SickLeaveType"));

								analyser.setPortfolio(rs.getString("Portfolio"));
								analyser.setPortfolioDescription(rs.getString("PortfolioDescription"));

								analyser.setCompanyCode(rs.getString("CompanyCode"));
								analyser.setDealPortfolio1AE1(rs.getString("DealPortfolio1_AE1"));
								analyser.setDealPortfolio2REC1(rs.getString("DealPortfolio2_REC1"));
								analyser.setDealPortfolio3AE2(rs.getString("DealPortfolio3_AE2"));
								analyser.setDealPortfolio4REC2(rs.getString("DealPortfolio4_REC2"));
								analyser.setBullhornBatchDataProcessedId(rs.getLong("BullhornBatchDataProcessedId"));
								analyser.setCoSellStatus(rs.getString("CoSellStatus"));
								analyser.setDataSource(rs.getString("DataSource"));
								analyser.setBullhornBatchCode(rs.getString("BullhornBatchCode"));
								analyser.setBullhornBatchAnalyzerStagingId(rs.getLong("BullhornBatchAnalyzerStagingId"));
								analyser.setPlacementType(rs.getString("PlacementType"));	
								/*
								 * Added as part of Tax and Sick Leave Project
								 */
								analyser.setBullhornPlacementId(rs.getInt("BullhornPlacementId"));
								analyser.setBullhornBatchInfoId(rs.getInt("BullhornBatchInfoId"));
								analyser.setBullhornTerminationDataProcessedId(rs.getLong("BullhornTerminationDataProcessedId"));
								analyser.setBullhornTerminationDataStagingId(rs.getLong("BullhornTerminationDataStagingId"));
								analyser.setTerminationBullhornBatchInfoId(rs.getInt("TerminationBullhornBatchInfoId"));
								analyser.setIsModificationRequired(rs.getString("IsModificationRequired"));
								analyser.setOverrideTermination(rs.getString("OverrideTermination"));
								analyser.setWorkFromSource(rs.getString("WorkFromSource"));
								analyser.setMajorityWorkPerformed(rs.getString("MajorityWorkPerformed"));
								analyser.setSickLeaveSource(rs.getString("SickLeaveSource"));
								list.add(analyser);
							}
						}
						else if (action.equals("CommissionHistory"))	//History Details Report
						{
							while (rs.next())
							{

								analyser = new AnalyserDTO();
								analyser.setManagingDirector(rs.getString("MANAGINGDIRECTOR"));
								analyser.setAnalyserId(rs.getString("ANALYSERID"));
								analyser.setParentId(rs.getString("PARENTID"));
								analyser.setNewParentId(rs.getString("NEWPARENTID"));
								analyser.setlName(rs.getString("LNAME"));
								analyser.setfName(rs.getString("FNAME"));

								analyser.setStartDate(rs.getString("STARTDATE"));
								analyser.setEffectiveDate(rs.getString("EFFECTIVEDATE"));
								analyser.setTermDate(rs.getString("TERMINATEDATE"));
								analyser.setCommEffDate(rs.getString("COMMEFFDATE"));
								analyser.setEndDate(rs.getString("ENDDATE"));
								analyser.setSubmissionDate(rs.getString("SUBMISSIONDATE"));
								// analyser.setModifiedBy(rs.getString("MODIFIEDBY"));

								analyser.setBranch(rs.getString("BRANCH"));
								analyser.setRecordStatus(rs.getString("RECORDSTATUS"));
								analyser.setEmpType(rs.getString("EMPTYPE"));
								analyser.setPayRate(convertStringToDoubleValue(rs.getString("PAYRATE")));
								analyser.setBillRate(convertStringToDoubleValue(rs.getString("BILLRATE")));
								analyser.setProfit(convertStringToDoubleValue(rs.getString("PROFIT")));
								analyser.setGrossProfit(convertStringToDoubleValue(rs.getString("GP%")));

								analyser.setCommName1(rs.getString("COMMISION_NAME1"));
								analyser.setCommPer1(convertStringToDoubleValue(rs.getString("COMMISION_PERCENT1")));
								analyser.setCommision1(rs.getString("COMMISION1"));
								analyser.setSplitCommissionPercentage1(rs.getString("SPLITCOMMISSIONPERCENTAGE1"));
								analyser.setExecOrphan(rs.getString("EXECORPHAN").equals("0") ? false : true);

								analyser.setCommName2(rs.getString("COMMISION_NAME2"));
								String res = rs.getString("COMMISION_PERCENT2");
								if (res != null && !res.equals(""))
								{
									if (res.contains("."))
									{
										res = res.substring(0, res.indexOf('.'));
									}
									Double val = Double.parseDouble(res);
									analyser.setCommPer2(val);
								}
								analyser.setCommision2(rs.getString("COMMISION2"));
								analyser.setSplitCommissionPercentage2(rs.getString("SPLITCOMMISSIONPERCENTAGE2"));
								analyser.setRecruiterOrphan(rs.getString("RECRUITERORPHAN").equals("0") ? false : true);

								analyser.setCommName3(rs.getString("COMMISION_NAME3"));
								analyser.setCommPer3(convertStringToDoubleValue(rs.getString("COMMISION_PERCENT3")));
								analyser.setCommision3(rs.getString("COMMISION3"));
								analyser.setSplitCommissionPercentage3(rs.getString("SPLITCOMMISSIONPERCENTAGE3"));
								analyser.setOther1Orphan(rs.getString("OTHER1ORPHAN").equals("0") ? false : true);

								analyser.setCommName4(rs.getString("COMMISION_NAME4"));
								analyser.setCommPer4(convertStringToDoubleValue(rs.getString("COMMISION_PERCENT4")));
								analyser.setCommision4(rs.getString("COMMISION4"));
								analyser.setSplitCommissionPercentage4(rs.getString("SPLITCOMMISSIONPERCENTAGE4"));
								analyser.setOther2Orphan(rs.getString("OTHER2ORPHAN").equals("0") ? false : true);

								analyser.setClientId(rs.getString("CLIENTID"));
								analyser.setJobTitle(rs.getString("JOB_TITLE"));
								analyser.setOtb(rs.getString("ONETIME_BILL"));
								analyser.setOneTimeAmount(rs.getString("ONETIME_PAY"));
								analyser.setApprovedByManager(rs.getString("APPROVED_BY_MANAGER"));
								analyser.setApprovalDate(rs.getTimestamp("APPROVALDATE"));

								analyser.setIsRehired(rs.getString("ISREHIRED"));
								analyser.setGa(convertStringToDoubleValue(rs.getString("GA")));
								analyser.setTax(rs.getString("TAXES"));
								analyser.setTemps(rs.getString("TEMPS"));
								analyser.setVertical(rs.getString("VERTICAL"));
								analyser.setProduct(rs.getString("PRODUCT"));
								analyser.setEmployeeClass(rs.getString("EMPLOYEECLASS"));
								analyser.setImmigrationCost(convertStringToDoubleValue(rs.getString("IMMIGRATIONCOST")));
								analyser.setEquipmentCost(convertStringToDoubleValue(rs.getString("EQUIPMENTCOST")));
								analyser.setNonBillableCost(convertStringToDoubleValue(rs.getString("NONBILLABLECOST")));
								//get the result by providing the same camel case used in the stored procedure.
								analyser.setModifiedBy(rs.getString("UserId"));
								//								

								analyser.setSickLeaveCost(rs.getDouble("sickLeaveCost"));
								analyser.setFalseTermination(rs.getString("falseTermination"));
								analyser.setCommissionPerson5(rs.getString("commissionPerson5"));
								analyser.setCommissionPerson6(rs.getString("commissionPerson6"));
								analyser.setCommissionPerson7(rs.getString("commissionPerson7"));
								analyser.setCommissionPerson8(rs.getString("commissionPerson8"));
								analyser.setCommissionPerson9(rs.getString("commissionPerson9"));

								analyser.setCommissionPercentage5(rs.getDouble("commissionPercentage5"));
								analyser.setCommissionPercentage6(rs.getDouble("commissionPercentage6"));
								analyser.setCommissionPercentage7(rs.getDouble("commissionPercentage7"));
								analyser.setCommissionPercentage8(rs.getDouble("commissionPercentage8"));
								analyser.setCommissionPercentage9(rs.getDouble("commissionPercentage9"));

								analyser.setCommission5(rs.getDouble("commission5"));
								analyser.setCommission6(rs.getDouble("commission6"));
								analyser.setCommission7(rs.getDouble("commission7"));
								analyser.setCommission8(rs.getDouble("commission8"));
								analyser.setCommission9(rs.getDouble("commission9"));

								analyser.setProjectCost(rs.getDouble("projectCost"));
								analyser.setSickLeavePerHourRate(rs.getDouble("SickLeavePerHourRate"));
								analyser.setSickLeaveCap(rs.getString("SickLeaveCap"));
								analyser.setManagingDirector(rs.getString("MANAGINGDIRECTOR"));

								analyser.setRejectionDate(rs.getTimestamp("RejectionDate"));
								analyser.setRejectedBy(rs.getString("RejectedBy"));
								analyser.setRejectionReason(rs.getString("Reason"));
								analyser.setRejectionStatus(rs.getInt("RejectionStatus"));
								analyser.setSickLeaveType(rs.getString("SickLeaveType"));

								analyser.setRdoPTO(rs.getString("PTO"));
								analyser.setLeave(rs.getDouble("Leave"));
								analyser.setTotalHolidays(rs.getDouble("TotalHolidays"));
								analyser.setBillablePTO(rs.getDouble("BillablePTO"));
								analyser.setNonBillablePTO(rs.getDouble("NonBillablePTO"));
								analyser.setBillableHolidays(rs.getDouble("BillableHolidays"));
								analyser.setNonBillableHolidays(rs.getDouble("NonBillableHolidays"));
								analyser.setGrossProfitPercentage(rs.getDouble("GrossProfitPercentage"));
								analyser.setGrossProfit(rs.getDouble("GrossProfit"));
								analyser.setCommissionableProfit(rs.getDouble("CommissionableProfit"));
								analyser.setSpreadWeek(rs.getString("Spread_w"));
								analyser.setDiscountRate(rs.getString("DiscountRate"));
								analyser.setDiscount(rs.getString("Discount"));
								analyser.setFlsaStatus(rs.getString("FLSAStatus"));
								analyser.setpTOLimitToOverrideSick(rs.getDouble("PTOLimitToOverrideSick"));
								analyser.setRecruitingClassification(rs.getString("RecruitingClassification"));

								analyser.setContractorId(rs.getString("SubContractorId"));
								analyser.setClientSiteId(rs.getInt("SiteId"));
								analyser.setClientAddressId(rs.getInt("ClientAddressId"));

								analyser.setPortfolio(rs.getString("Portfolio"));
								analyser.setPortfolioDescription(rs.getString("PortfolioDescription"));
								analyser.setDealActivityAmount(rs.getString("DealActivityAmount"));
								analyser.setBullhornPlacementId(rs.getInt("BullhornPlacementId"));
								analyser.setDealPortfolio1AE1(rs.getString("DealPortfolio1_AE1"));
								analyser.setDealPortfolio2REC1(rs.getString("DealPortfolio2_REC1"));
								analyser.setDealPortfolio3AE2(rs.getString("DealPortfolio3_AE2"));
								analyser.setDealPortfolio4REC2(rs.getString("DealPortfolio4_REC2"));
								analyser.setCompanyCode(rs.getString("CompanyCode"));

								list.add(analyser);
							}
						}

						callStmt.close();
						rs.close();
						callStmt = null;
						rs = null;
					}
					else
					{
						analyser = null;
					}
				}
				else
				{
					throw new Exception("Connection is null");
				}
			}
			catch (Exception ex)
			{
				System.out.println("Exception in " + action + " method of AnalyserRepositoryImpl");
				ex.printStackTrace();
			}
			finally
			{
				if (rs != null) rs.close();
				if (callStmt != null) callStmt.close();
			}
		}

		/**
		 * @return the list
		 */
		public List<AnalyserDTO> getList()
		{
			return list;
		}

		private Double convertStringToDoubleValue(String value){
			if(value != null && !value.isEmpty()){
				try{
					return Double.parseDouble(value);
				}catch(NumberFormatException nfe){
					return 0.0;
				}
			}
			return 0.0;
		}
	}

	@Override
	public String getGenericDescription(String workSiteState, String siteId, String dummyParameter)
	{
		Session session = entityManager.unwrap(Session.class);
		String action = "GenericDescription";
		if(workSiteState.equalsIgnoreCase("FALSETERMINATION")){
			AnalyserMiscelleneousWork work = null;
			work = new AnalyserMiscelleneousWork(workSiteState, siteId, Integer.parseInt(dummyParameter), "", "", action);
			session.doWork(work);
			String result = work.getResult();
			return result;
		} else {
			String type = workSiteState;
			String commissionPersonName = siteId;
			AnalyserGenericWork work = new AnalyserGenericWork(type, commissionPersonName);
			session.doWork(work);
			String result = work.getResult();
			return result;
		}
	}

	@Override
	public boolean isItemPendingForUser(Integer analyserId, String itemName)
	{
		Session session = entityManager.unwrap(Session.class);
		String action = "ItemPendingForUser";
		AnalyserMiscelleneousWork work = new AnalyserMiscelleneousWork("", "0", analyserId, itemName, "", action);
		session.doWork(work);
		String result = work.getResult();
		boolean status = false;
		if (!result.equals("0")) status = true;
		return status;
	}

	@Override
	public String approveAnalyser(Integer analyserId, String userId)
	{
		Session session = entityManager.unwrap(Session.class);
		String action = "Approve";
		AnalyserMiscelleneousWork work = new AnalyserMiscelleneousWork("", "0", analyserId, "", userId, action);
		session.doWork(work);
		String result = work.getResult();
		/*
		 * boolean status = false; if (!result.equals("0")) status = true;
		 */
		return result;
	}

	@Override
	public AnalyserTerminateDTO getAnalyserTerminateInfo(String userId, Integer analyserId)
	{
		Session session = entityManager.unwrap(Session.class);
		String action = "TerminateInfo";
		AnalyserMiscelleneousWork work = new AnalyserMiscelleneousWork(userId, analyserId, "", "", "", "",
				"", action);
		session.doWork(work);
		AnalyserTerminateDTO result = work.getAnalyserTerminateInfo();
		return result;
	}

	@Override
	public String terminateAnalyser(String userId, Integer analyserId, String terminateDate, String reason, String eligibleForHire,
			String dentalInsurance, String falseTermination)
	{
		Session session = entityManager.unwrap(Session.class);
		String action = "Terminate";
		AnalyserMiscelleneousWork work = new AnalyserMiscelleneousWork(userId, analyserId, terminateDate, reason, eligibleForHire, dentalInsurance,
				falseTermination, action);
		session.doWork(work);
		String result = work.getResult();
		/*
		 * boolean status = false; if (!result.equals("0")) status = true;
		 */
		return result;
	}

	@Override
	public String updateTerminateAnalyser(String userId, Integer analyserId, String terminateDate, String reason, String eligibleForHire,
			String dentalInsurance, String falseTermination)
	{
		Session session = entityManager.unwrap(Session.class);
		String action = "UpdateTerminate";
		AnalyserMiscelleneousWork work = new AnalyserMiscelleneousWork(userId, analyserId, terminateDate, reason, eligibleForHire, dentalInsurance,
				falseTermination, action);
		session.doWork(work);
		String result = work.getResult();
		/*
		 * boolean status = false; if (!result.equals("0")) status = true;
		 */
		return result;
	}

	public String getWorkforceShifts(Integer analyserId)
	{
		Session session = entityManager.unwrap(Session.class);
		String action = "WorkforceShifts";
		AnalyserMiscelleneousWork work = new AnalyserMiscelleneousWork("", "0", analyserId, "", "", action);
		session.doWork(work);
		String result = work.getResult();
		/*
		 * boolean status = false; if (!result.equals("0")) status = true;
		 */
		return result;
	}

	@Override
	public List<String> getVerticalsList()
	{
		Session session = entityManager.unwrap(Session.class);
		AnalyserMiscelleneousWork work = new AnalyserMiscelleneousWork(VERTICALS_LIST);
		session.doWork(work);
		List<String> list = work.getList();
		return list;
	}

	private static class AnalyserGenericWork implements Work
	{

		private Logger logger = LoggerFactory.getLogger(getClass());
		private String type;
		private String commissionPersonName;
		private String result;

		public AnalyserGenericWork(String type, String commissionPersonName)
		{
			super();
			this.type = type;
			this.commissionPersonName = commissionPersonName;
		}

		@Override
		public void execute(Connection connection) throws SQLException
		{
			CallableStatement callStmt = null;
			ResultSet rs = null;
			result = "0";
			try
			{
				if (connection != null)
				{
					String query = "{call " + FacesUtils.SCHEMA_WIRELESS + ".getGenericDescription('" + type + "','" + commissionPersonName + "','')}";

					System.out.println("Executing Query in AnalyserGenericWork :  " + query);

					logger.info("AnalyserGenericWork Query is : " + query);
					callStmt = connection.prepareCall(query);

					rs = callStmt.executeQuery();
					if (rs != null)
					{
						if (rs.next())
						{
							try
							{
								result = rs.getString("Result");
							}
							catch (Exception ex)
							{
								System.err.println("Error getting value from result set ... " + ex.getMessage());
								result = "-1";
							}

						}
						callStmt.close();
						rs.close();
						callStmt = null;
						rs = null;
					}
				}else
				{
					throw new Exception("Connection is null");
				}
			}catch (Exception ex)
			{
				System.out.println("Exception in AnalyserGenericWork AnalyserRepositoryImpl");
				ex.printStackTrace();
				result = "0";
			}
			finally
			{
				if (rs != null) rs.close();
				if (callStmt != null) callStmt.close();
			}

		}

		/**
		 * @return the result
		 */
		public String getResult()
		{
			return result;
		}

	}

	private static class AnalyserMiscelleneousWork implements Work
	{

		private Logger logger = LoggerFactory.getLogger(getClass());

		Integer analyserId;
		String itemName;
		String workSiteState;
		String siteId;
		String userId;
		String terminateDate;
		String reason;
		String eligibleForHire;
		String dentalInsurance;
		String falseTermination;
		String action;
		String result = "";
		Integer parentId;
		String rejectReason;
		List<String> list;

		AnalyserTerminateDTO analyserTerminateInfo;

		public AnalyserMiscelleneousWork(String action)
		{
			super();
			this.action = action;
		}

		public AnalyserMiscelleneousWork(String workSiteState, String siteId, Integer analyserId, String itemName, String userId, String action)
		{
			super();
			this.workSiteState = workSiteState;
			this.siteId = siteId;
			this.analyserId = analyserId;
			this.itemName = itemName;
			this.userId = userId;
			this.action = action;
		}

		public AnalyserMiscelleneousWork(String userId, Integer analyserId, String terminateDate, String reason, String eligibleForHire,
				String dentalInsurance, String falseTermination, String action)
		{
			this.userId = userId;
			this.analyserId = analyserId;
			this.terminateDate = terminateDate;
			this.reason = reason;
			this.eligibleForHire = eligibleForHire;
			this.dentalInsurance = dentalInsurance;
			this.falseTermination = falseTermination;
			this.action = action;
		}

		public AnalyserMiscelleneousWork(String userId, Integer analyserId, Integer parentId, String rejectReason, String action)
		{
			this.userId = userId;
			this.analyserId = analyserId;
			this.parentId = parentId;
			this.rejectReason = rejectReason;
			this.action = action;
		}

		@Override
		public void execute(Connection connection) throws SQLException
		{
			CallableStatement callStmt = null;
			ResultSet rs = null;
			result = "0";
			boolean gotResults = false;
			try
			{
				if (connection != null)
				{
					String query = "";
					if (action.equals("GenericDescription"))
					{
						if(workSiteState.equalsIgnoreCase("FALSETERMINATION")){
							query = "{call " + FacesUtils.SCHEMA_WIRELESS + ".getGenericDescription('" + workSiteState + "','" + siteId + "','"+analyserId+"')}";
						}else{
							query = "{call " + FacesUtils.SCHEMA_WIRELESS + ".getGenericDescription('" + workSiteState + "','" + siteId + "','')}";
						}

						System.out.println("Executing Query in AnalyserMiscelleneousWork :  " + query);
					}
					else if (action.equals("ItemPendingForUser"))
					{
						query = "{call " + FacesUtils.SCHEMA_WIRELESS + ".spGetIssuesCountForUser('" + analyserId + "', '" + itemName + "')}";
						System.out.println("query " + query);
					}
					else if (action.equals("Approve"))
					{
						query = "{call " + FacesUtils.SCHEMA_WIRELESS + ".spApproveAnalyser(" + analyserId + ",'" + userId + "')}";
						System.out.println("Executing Query in AnalyserMiscelleneousWork :  " + query);
					}
					else if (action.equals("Terminate"))
					{
						query = "{call " + FacesUtils.SCHEMA_WIRELESS + ".spAnalyserTerminater(" + analyserId + ",'" + userId + "','" + terminateDate
								+ "','" + reason + "','" + eligibleForHire + "','" + dentalInsurance + "','" + falseTermination + "')}";
						System.out.println("Executing Query in AnalyserMiscelleneousWork :  " + query);
					}
					else if (action.equals("UpdateTerminate"))
					{
						query = "{call " + FacesUtils.SCHEMA_WIRELESS + ".spUpdateTermination(" + analyserId + ",'" + userId + "','" + terminateDate
								+ "','" + reason + "','" + eligibleForHire + "','" + dentalInsurance + "','" + falseTermination + "')}";
						System.out.println("Executing Query in AnalyserMiscelleneousWork :  " + query);
					}
					else if (action.equals("WorkforceShifts"))
					{
						query = "{call " + FacesUtils.SCHEMA_WIRELESS + ".spGetShiftInfoFromDevice('" + analyserId + "')}";
						System.out.println("Executing Query in AnalyserMiscelleneousWork :  " + query);
					}
					else if (action.equals("verticalsList"))
					{
						query = "{call " + FacesUtils.SCHEMA_DBO + ".getVerticalList}";
						System.out.println("Executing Query for getVerticalList in AnalyserMiscelleneousWork :  " + query);
					}
					else if (action.equalsIgnoreCase("Reject"))
					{
						query = "{call " + FacesUtils.SCHEMA_DBO + ".RejectModifiedAnalyzer('" + userId + "'," + analyserId + "," + parentId + ",'"
								+ rejectReason + "')}";
						System.out.println("Executing Query for RejectModifiedAnalyzer in AnalyserMiscelleneousWork :  " + query);
					}
					else if (action.equalsIgnoreCase("TerminateInfo"))
					{
						query = "{call " + FacesUtils.SCHEMA_WIRELESS + ".spGetTerminationInfo('" + userId + "'," + analyserId + ")}";
						System.out.println("Executing Query for spGetTerminationInfo in AnalyserMiscelleneousWork :  " + query);
					}

					logger.info("AnalyserMiscelleneousWork Query is : " + query);
					callStmt = connection.prepareCall(query);
					if (action.equalsIgnoreCase("Reject"))
					{
						gotResults = callStmt.execute();
					}
					else
					{
						rs = callStmt.executeQuery();
					}
					if (rs != null)
					{
						if (action.equals("GenericDescription"))
						{
							if (rs.next())
							{
								try
								{
									result = rs.getString("Result");
								}
								catch (Exception ex)
								{
									System.err.println("Error getting value from result set ... " + ex.getMessage());
									result = "-1";
								}

							}
						}
						else if (action.equals("ItemPendingForUser"))
						{
							if (rs.next())
							{
								result = rs.getString("Records");
							}
						}
						else if (action.equals("Approve"))
						{
							if (rs.next())
							{
								result = rs.getString(1);
							}
						}
						else if (action.equals("Terminate"))
						{
							if (rs.next())
							{
								result = "" + rs.getInt(1);
							}
						}
						else if (action.equals("UpdateTerminate"))
						{
							if (rs.next())
							{
								result = "" + rs.getInt(1);
							}
						}
						else if (action.equals("WorkforceShifts"))
						{
							if (rs.next())
							{
								result = rs.getString(1);
							}
						}
						else if (action.equals("verticalsList"))
						{
							list = new ArrayList<String>();
							while (rs.next())
							{
								list.add(rs.getString(1));
							}
						}
						else if (action.equals("TerminateInfo"))
						{
							analyserTerminateInfo = new AnalyserTerminateDTO();
							while (rs.next())
							{

								//								AnalyserId	ParentId	TerminateDate	Reason	EligibleForReHire	k401	Health	dentalInsurance	FALSETERMINATION	UserId	SubmissionDate
								//								248251		242076								NULL			0	0					N		N		Stephanie.Eason@DISYS.COM	2018-12-17 15:42:17.563
								analyserTerminateInfo.setAnalyserId(rs.getString("AnalyserId"));
								analyserTerminateInfo.setTerminateDate(rs.getString("TerminateDate"));
								analyserTerminateInfo.setReason(rs.getString("Reason"));
								analyserTerminateInfo.setEligibleForRehire(parseBooleanFromString(rs.getString("EligibleForReHire")));
								analyserTerminateInfo.setK401Savings(parseBooleanFromString(rs.getString("k401")));
								analyserTerminateInfo.setHealthInsurance(parseBooleanFromString(rs.getString("Health")));
								analyserTerminateInfo.setDentalInsurance(parseBooleanFromString(rs.getString("dentalInsurance")));
								analyserTerminateInfo.setFalseTermination(parseBooleanFromString(rs.getString("FALSETERMINATION")));
							}
						}
						callStmt.close();
						rs.close();
						callStmt = null;
						rs = null;
					}
					else
					{
						if (action.equals("Approve"))
						{
							result = "1";
						}
						else if (action.equalsIgnoreCase("Reject"))
						{
							if (!gotResults)
							{
								result = "1";
							}
							else
							{
								rs = callStmt.getResultSet();
							}
						}
					}
				}
				else
				{
					throw new Exception("Connection is null");
				}
			}
			catch (Exception ex)
			{
				System.out.println("Exception in AnalyserMiscelleneousWork AnalyserRepositoryImpl");
				ex.printStackTrace();
				if (action.equals("Approve") || action.equals("Terminate"))
				{
					result = "1";
				}
			}
			finally
			{
				if (rs != null) rs.close();
				if (callStmt != null) callStmt.close();
			}
		}

		private boolean parseBooleanFromString(String value){
			try{
				//there is some value
				if(value != null && !value.isEmpty()){
					if(value.equalsIgnoreCase("NULL")){
						return false;
					}
					if(value.equals("0") || value.equalsIgnoreCase("N") || value.equalsIgnoreCase("FALSE") || value.equalsIgnoreCase("OFF")){
						return false;
					}else if(value.equals("1") || value.equalsIgnoreCase("Y") || value.equalsIgnoreCase("ON") || value.equalsIgnoreCase("TRUE")){
						return true;
					}
				}
				return false;
			}catch(Exception ex){
				return false;
			}
		}

		/**
		 * @return the result
		 */
		public String getResult()
		{
			return result;
		}

		/**
		 * @return the list
		 */
		public List<String> getList()
		{
			return list;
		}

		/**
		 * @return the analyserTerminateInfo
		 */
		public AnalyserTerminateDTO getAnalyserTerminateInfo()
		{
			return analyserTerminateInfo;
		}

	}

	@Override
	public Integer spGetDuplicateFLSA(String flsaReference, Integer parentId, char mode)
	{
		Session session = entityManager.unwrap(Session.class);
		String action = "FLSA";
		AnalyserDuplicateCheckWork work = new AnalyserDuplicateCheckWork(flsaReference, parentId, mode, action);
		session.doWork(work);
		Integer count = work.getCount();
		return count;
	}

	@Override
	public Integer spGetDuplicateSSN(String ssn, Integer parentId, char mode)
	{
		Session session = entityManager.unwrap(Session.class);
		String action = "SSN";
		AnalyserDuplicateCheckWork work = new AnalyserDuplicateCheckWork(ssn, parentId, mode, action);
		session.doWork(work);
		Integer count = work.getCount();
		return count;
	}

	private static class AnalyserDuplicateCheckWork implements Work
	{

		private Logger logger = LoggerFactory.getLogger(getClass());

		String item;
		String action;
		Integer count;
		Integer parentId;
		char mode;

		public AnalyserDuplicateCheckWork(String item, Integer parentId, char mode, String action)
		{
			super();
			this.item = item;
			this.action = action;
			this.parentId = parentId;
			this.mode = mode;
		}

		@Override
		public void execute(Connection connection) throws SQLException
		{
			CallableStatement callStmt = null;
			ResultSet rs = null;
			count = 0;
			try
			{
				if (connection != null)
				{
					String query = "";
					if (action.equals("SSN"))
					{
						query = "{call " + FacesUtils.SCHEMA_WIRELESS + ".spGetDuplicateSSN('" + item + "'," + parentId + ",'" + mode + "')}";
						System.out.println("Executing Query in AnalyserMiscelleneousWork :  " + query);
					}
					else if (action.equals("FLSA"))
					{
						query = "{call " + FacesUtils.SCHEMA_WIRELESS + ".spGetDuplicateFLSA('" + item + "'," + parentId + ",'" + mode + "')}";
						System.out.println("query " + query);
					}

					logger.info("AnalyserDuplicateCheckWork Query is : " + query);

					callStmt = connection.prepareCall(query);
					rs = callStmt.executeQuery();
					if (rs != null)
					{
						if (rs.next())
						{
							count = rs.getInt("duplicate");
						}
					}
					callStmt.close();
					rs.close();
					callStmt = null;
					rs = null;
				}
			}
			catch (Exception ex)
			{
				System.out.println("Exception in AnalyserDuplicateCheckWork ");
				ex.printStackTrace();
			}
			finally
			{
				if (rs != null) rs.close();
				if (callStmt != null) callStmt.close();
			}
		}

		/**
		 * @return the count
		 */
		public Integer getCount()
		{
			return count;
		}
	}

	@Override
	public List<Analyser> getAnalyzerDataForUpdate(Integer parentId)
	{
		Session session = entityManager.unwrap(Session.class);
		AnalyserUpdateDataWork work = new AnalyserUpdateDataWork(parentId, "List");
		session.doWork(work);
		List<Analyser> list = work.getList();
		if (list != null)
		{
			return list;
		}
		return null;
	}

	private static class AnalyserUpdateDataWork implements Work
	{

		private Logger logger = LoggerFactory.getLogger(this.getClass());

		private List<Analyser> list;
		String action;
		String userId;
		String columnName;
		Integer analyserId;
		Integer parentId;
		String oldValue;
		String newValue;

		String result;

		/**
		 * @param analyserId
		 */
		public AnalyserUpdateDataWork(Integer parentId, String action)
		{
			super();
			this.parentId = parentId;
			this.action = action;
		}

		public AnalyserUpdateDataWork(String userId, String columnName, Integer analyserId, Integer parentId, String oldValue, String newValue,
				String action)
		{
			super();
			this.userId = userId;
			this.columnName = columnName;
			this.analyserId = analyserId;
			this.parentId = parentId;
			this.oldValue = oldValue;
			this.newValue = newValue;
			this.action = action;
		}

		@Override
		public void execute(Connection connection) throws SQLException
		{
			if (action.equals("List"))
			{
				list = new ArrayList<Analyser>();
				CallableStatement callStmt = null;
				ResultSet rs = null;
				Analyser analyser = null;

				try
				{
					if (connection != null)
					{
						String query = "";
						query = "{call " + FacesUtils.SCHEMA_WIRELESS + ".spGetAnalyzerDataForUpdate(" + parentId + ")}";
						System.out.println("query " + query);
						logger.debug("Query is spGetAnalyzerDataForUpdate : " + query);

						callStmt = connection.prepareCall(query);
						rs = callStmt.executeQuery();
						if (rs != null)
						{
							while (rs.next())
							{

								analyser = new Analyser();

								analyser.setAnalyserId(rs.getLong("AnalyserId"));
								analyser.setParentId(rs.getString("ParentId"));
								analyser.setNewParentId(rs.getInt("NewParentId"));

								analyser.setFirstName(rs.getString("FName"));
								analyser.setLastName(rs.getString("LName"));

								analyser.setDob(rs.getString("DOB"));

								analyser.setSsn(rs.getString("SSN"));

								analyser.setStartDate(rs.getString("StartDate"));
								analyser.setEndDate(rs.getString("EndDate"));
								analyser.setEffectiveDate(rs.getString("EffectiveDate"));
								analyser.setCommEffectiveDate(rs.getString("CommEffDate"));
								analyser.setTerminateDate(rs.getString("TerminateDate"));
								analyser.setReason(rs.getString("Reason"));

								analyser.setIsRehired(rs.getString("IsRehired"));
								analyser.setRecordStatus(rs.getString("RecordStatus"));

								analyser.setPortfolio(rs.getString("Portfolio"));
								analyser.setPortfolioDescription(rs.getString("PortfolioDescription"));
								analyser.setCompanyCode(rs.getString("CompanyCode"));
								list.add(analyser);
							}
						}

						callStmt.close();
						rs.close();
						callStmt = null;
						rs = null;
					}
				}
				catch (Exception ex)
				{
					System.out.println("Exception in AnalyserUpdateImpl");
					ex.printStackTrace();
				}
				finally
				{
					if (rs != null) rs.close();
					if (callStmt != null) callStmt.close();
				}
			}
			else if (action.equals("update"))
			{
				CallableStatement callStmt = null;
				ResultSet rs = null;

				try
				{
					if (connection != null)
					{
						String query = "";
						query = "{call " + FacesUtils.SCHEMA_DBO + ".UpdateAnalyzerRestrictedData('" + userId + "','" + columnName + "'," + analyserId
								+ "," + parentId + ",'" + oldValue + "','" + newValue + "')}";

						System.out.println("query " + query);

						logger.debug("UpdateAnalyzerRestrictedData query is : " + query);

						callStmt = connection.prepareCall(query);
						rs = callStmt.executeQuery();
						if (rs != null)
						{
							while (rs.next())
							{
								result = "" + rs.getString(1);
							}
							// con.close();
							callStmt.close();
							rs.close();
							// con = null;
							callStmt = null;
							rs = null;
						}
						else
						{
							result = "-1";
							logger.debug("Analyser could not be updated");
							throw new Exception("Analyser could not be updated");
						}
						// Integer res = callStmt.executeUpdate();

						/*
						 * boolean gotResults = callStmt.executeUpdate(); if
						 * (!gotResults) { result = "-1";
						 * //System.out.println("No results returned"); } else {
						 * rs = callStmt.getResultSet(); result =
						 * rs.getString("0"); }
						 */
						callStmt = null;
						rs = null;
						// result = res.toString();
					}
				}
				catch (Exception ex)
				{
					System.out.println("Exception in AnalyserUpdateImpl");
					ex.printStackTrace();
				}
				finally
				{
					if (rs != null) rs.close();
					if (callStmt != null) callStmt.close();
				}
			}

		}

		/**
		 * @return the list
		 */
		public List<Analyser> getList()
		{
			return list;
		}

		/**
		 * @return the result
		 */
		public String getResult()
		{
			return result;
		}

	}

	@Override
	public String updateAnalyserRestrictedData(String userId, String columnName, Integer analyserId, Integer parentId, String oldValue,
			String newValue)
	{
		System.out.println("I am about to update the analyser with values ");
		System.out.println("User Id : " + userId);
		System.out.println("Column name : " + columnName);
		System.out.println("Analyser Id : " + analyserId);
		System.out.println("Parent Id : " + parentId);
		System.out.println("Old value : " + oldValue);
		System.out.println("New Value : " + newValue);

		Session session = entityManager.unwrap(Session.class);
		AnalyserUpdateDataWork work = new AnalyserUpdateDataWork(userId, columnName, analyserId, parentId, oldValue, newValue, "update");
		session.doWork(work);
		String result = work.getResult();
		return result;
	}

	public static void main(String[] args)
	{
		String res = "3.5";
		if (res != null && !res.equals(""))
		{
			if (res.contains("."))
			{
				res = res.substring(0, res.indexOf('.'));
			}
			Integer val = Integer.parseInt(res);
			System.out.println(val);
		}
	}

	@Override
	public String rehireAnalyser(AnalyserDTO analyser, String userId)
	{
		String response = "";
		Session session = entityManager.unwrap(Session.class);

		AnalyserRehireWork work = new AnalyserRehireWork(analyser, userId);
		session.doWork(work);
		response = work.getStatus();
		return response;
	}

	private static class AnalyserRehireWork implements Work
	{

		private Logger logger = LoggerFactory.getLogger(getClass());

		String status;

		AnalyserDTO analyser;
		String userId;

		public AnalyserRehireWork(AnalyserDTO analyser, String userId)
		{
			this.analyser = analyser;
			this.userId = userId;
		}

		@Override
		public void execute(Connection connection) throws SQLException
		{
			status = "Its done!";
			CallableStatement callStmt = null;
			ResultSet rs = null;

			String analyserId = analyser.getAnalyserId();
			analyser = new AnalyserDTO();
			int actionType = 5;
			try
			{
				String insertStoreProc = "{call " + FacesUtils.SCHEMA_WIRELESS
						+ ".spAddUpdateAnalyserNew(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";

				callStmt = connection.prepareCall(insertStoreProc);

				/*
				 * 
				 * Query to rehire analyser.
				 * 
				 */

				int intVal = Integer.valueOf(analyserId);
				StringBuilder queryToAddAnalyser = new StringBuilder();
				queryToAddAnalyser.append("USE [Analyser] GO DECLARE	@return_value int EXEC	@return_value = [wireless].[spAddUpdateAnalyserNew]");
				// @intActionType
				callStmt.setInt(1, actionType);
				queryToAddAnalyser.append("@intActionType = " + actionType + ",");
				// @varLoggedUserID
				callStmt.setString(2, userId);
				queryToAddAnalyser.append("@varLoggedUserID = N'" + userId + "',");
				// @intAnalyserID
				callStmt.setInt(3, intVal);
				queryToAddAnalyser.append("@intAnalyserID = " + intVal + ",");
				// @varLName
				callStmt.setString(4, analyser.getlName());
				queryToAddAnalyser.append("@varLName = N'" + analyser.getlName() + "',");
				// @varFName
				callStmt.setString(5, analyser.getfName());
				queryToAddAnalyser.append("@varFName = N'" + analyser.getfName() + "',");
				// @varEmpType
				callStmt.setString(6, analyser.getEmpType());
				queryToAddAnalyser.append("@varEmpType = N'" + analyser.getEmpType() + "',");
				// @varAddress1
				callStmt.setString(7, analyser.getAddress1());
				queryToAddAnalyser.append("@varAddress1 = N'" + analyser.getAddress1() + "',");
				// @varAddress2
				callStmt.setString(8, analyser.getAddress2());
				queryToAddAnalyser.append("@varAddress2 = N'" + analyser.getAddress2() + "',");
				// @varCity
				callStmt.setString(9, analyser.getCity());
				queryToAddAnalyser.append("@varCity = N'" + analyser.getCity() + "',");
				// @varState
				callStmt.setString(10, analyser.getState());
				queryToAddAnalyser.append("@varState = N'" + analyser.getState() + "',");
				// @varZip
				callStmt.setString(11, analyser.getZip());
				queryToAddAnalyser.append("@varZip = N'" + analyser.getZip() + "',");
				// @varTel
				callStmt.setString(12, analyser.getTelephone());
				queryToAddAnalyser.append("@varTel = N'" + analyser.getTelephone() + "',");
				// @varWokphone
				callStmt.setString(13, analyser.getWorkPhone());
				queryToAddAnalyser.append("@varWokphone = N'" + analyser.getWorkPhone() + "',");
				// @varFax
				callStmt.setString(14, analyser.getFax());
				queryToAddAnalyser.append("@varFax = N'" + analyser.getFax() + "',");
				// @varEmail

				String email = ( analyser.getEmail() == null || analyser.getEmail().trim().length() == 0 ) ? "empty@empty.com" : analyser.getEmail();
				callStmt.setString(15, email);
				queryToAddAnalyser.append("@varEmail = N'" + email + "',");
				// @varSsn
				callStmt.setString(16, analyser.getSsn());
				queryToAddAnalyser.append("@varSsn = N'" + analyser.getSsn() + "',");
				// @varDob
				callStmt.setString(17, analyser.getDob());
				queryToAddAnalyser.append("@varDob = N'" + analyser.getDob() + "',");
				// @varJobTitle
				callStmt.setString(18, analyser.getJobTitle());
				queryToAddAnalyser.append("@varJobTitle = N'" + analyser.getJobTitle() + "',");
				// @varStartDate
				callStmt.setString(19, analyser.getStartDate());
				queryToAddAnalyser.append("@varStartDate = N'" + analyser.getStartDate() + "',");
				// @varEndDate
				callStmt.setString(20, analyser.getEndDate());
				queryToAddAnalyser.append("@varEndDate = N'" + analyser.getEndDate() + "',");
				// @intClientId
				intVal = 0;
				callStmt.setInt(21, intVal);
				queryToAddAnalyser.append("@intClientId = " + intVal + ",");
				// @intClientAddressId
				intVal = 0;
				callStmt.setInt(22, intVal);
				queryToAddAnalyser.append("@intClientAddressId = " + intVal + ",");
				// @intClientSiteId
				callStmt.setInt(23, analyser.getClientSiteId());
				queryToAddAnalyser.append("@intClientSiteId = " + analyser.getClientSiteId() + ",");
				// @varContractorId
				callStmt.setString(24, analyser.getContractorId());
				queryToAddAnalyser.append("@varContractorId = N'" + analyser.getContractorId() + "',");
				// @varCommName1
				callStmt.setString(25, analyser.getCommName1());
				queryToAddAnalyser.append("@varCommName1 = N'" + analyser.getCommName1() + "',");
				// @varPer1
				callStmt.setString(26, String.valueOf(analyser.getCommPer1()));
				queryToAddAnalyser.append("@varPer1 = N'" + analyser.getCommPer1() + "',");
				// @varCommName2
				callStmt.setString(27, String.valueOf(analyser.getCommName2()));
				queryToAddAnalyser.append("@varCommName2 = N'" + analyser.getCommName2() + "',");
				// @varPer2
				callStmt.setString(28, String.valueOf(analyser.getCommPer2()));
				queryToAddAnalyser.append("@varPer2 = N'" + analyser.getCommPer2() + "',");
				// @varCommName3
				callStmt.setString(29, analyser.getCommName3());
				queryToAddAnalyser.append("@varCommName3 = N'" + analyser.getCommName3() + "',");
				// @varPer3
				callStmt.setString(30, String.valueOf(analyser.getCommPer3()));
				queryToAddAnalyser.append("@varPer3 = N'" + analyser.getCommPer3() + "',");
				// @varCommName4
				callStmt.setString(31, analyser.getCommName4());
				queryToAddAnalyser.append("@varCommName4 = N'" + analyser.getCommName4() + "',");
				// @varPer4
				callStmt.setString(32, String.valueOf(analyser.getCommPer4()));
				queryToAddAnalyser.append("@varPer4 = N'" + analyser.getCommPer4() + "',");
				// @varCommName5
				callStmt.setString(33, analyser.getCommissionPerson5());
				queryToAddAnalyser.append("@varCommName5 = N'" + analyser.getCommissionPerson5() + "',");
				// @varPer5
				callStmt.setString(34, analyser.getCommission5().toString());
				queryToAddAnalyser.append("@varPer5 = N'" + analyser.getCommission5() + "',");
				// @varTHW
				callStmt.setString(35, analyser.getTotHoursWorked().toString());
				queryToAddAnalyser.append("@varTHW = N'" + analyser.getTotHoursWorked().toString() + "',");
				// @varBenefitsAmount
				callStmt.setString(36, analyser.getBenefitsAmount());
				queryToAddAnalyser.append("@varBenefitsAmount = N'" + analyser.getBenefitsAmount() + "',");
				// @varPercent401k
				callStmt.setString(37, analyser.getPercent401k());
				queryToAddAnalyser.append("@varPercent401k = N'" + analyser.getPercent401k() + "',");
				// @varEducation
				callStmt.setString(38, analyser.getEducation());
				queryToAddAnalyser.append("@varEducation = N'" + analyser.getEducation() + "',");
				// @varLeave
				callStmt.setString(39, analyser.getLeave().toString());
				queryToAddAnalyser.append("@varLeave = N'" + analyser.getLeave().toString() + "',");
				// @varOtherDollar
				callStmt.setString(40, analyser.getOd());
				queryToAddAnalyser.append("@varOtherDollar = N'" + analyser.getOd() + "',");
				// @varOtherAmounts
				callStmt.setString(41, analyser.getOtherAmounts());
				queryToAddAnalyser.append("@varOtherAmounts = N'" + analyser.getOtherAmounts() + "',");
				// @varDiscount
				callStmt.setString(42, analyser.getDiscount());
				queryToAddAnalyser.append("@varDiscount = N'" + analyser.getDiscount() + "',");
				// @varBillRate
				callStmt.setString(43, analyser.getBillRate().toString());
				queryToAddAnalyser.append("@varBillRate = N'" + analyser.getBillRate().toString() + "',");
				// @varHealth
				callStmt.setString(44, analyser.getHealth());
				queryToAddAnalyser.append("@varHealth = N'" + analyser.getHealth() + "',");
				// @vark401
				callStmt.setString(45, analyser.getK401());
				queryToAddAnalyser.append("@vark401 = N'" + analyser.getK401() + "',");
				// @varTax
				callStmt.setString(46, analyser.getTax());
				queryToAddAnalyser.append("@varTax = N'" + analyser.getTax() + "',");
				// @varPayRate
				callStmt.setString(47, analyser.getPayRate().toString());
				queryToAddAnalyser.append("@varPayRate = N'" + analyser.getPayRate().toString() + "',");
				// @varGA
				callStmt.setString(48, analyser.getGa().toString());
				queryToAddAnalyser.append("@varGA = N'" + analyser.getGa().toString() + "',");
				// @varCommission
				callStmt.setString(49, analyser.getCommission().toString());
				queryToAddAnalyser.append("@varCommission = N'" + analyser.getCommission().toString() + "',");
				// @varSpreadWeek
				callStmt.setString(50, analyser.getSpreadWeek());
				queryToAddAnalyser.append("@varSpreadWeek = N'" + analyser.getSpreadWeek() + "',");
				// @varOtb
				callStmt.setString(51, analyser.getOtb());
				queryToAddAnalyser.append("@varOtb = N'" + analyser.getOtb() + "',");
				// @varOneTimeAmount
				callStmt.setString(52, analyser.getOneTimeAmount());
				queryToAddAnalyser.append("@varOneTimeAmount = N'" + analyser.getOneTimeAmount() + "',");
				// @varComments
				callStmt.setString(53, analyser.getComments());
				queryToAddAnalyser.append("@varComments = N'" + analyser.getComments() + "',");
				// @varDiscountRate
				callStmt.setString(54, analyser.getDiscountRate());
				queryToAddAnalyser.append("@varDiscountRate = N'" + analyser.getDiscountRate() + "',");
				// @varEffectiveDate
				callStmt.setString(55, analyser.getEffectiveDate());
				queryToAddAnalyser.append("@varEffectiveDate = N'" + analyser.getEffectiveDate() + "',");
				// @varProfit
				callStmt.setString(56, analyser.getProfit().toString());
				queryToAddAnalyser.append("@varProfit = N'" + analyser.getProfit().toString() + "',");
				// @varTermDate
				callStmt.setString(57, analyser.getTermDate());
				queryToAddAnalyser.append("@varTermDate = N'" + analyser.getTermDate() + "',");
				// @varReason
				callStmt.setString(58, analyser.getReason());
				queryToAddAnalyser.append("@varReason = N'" + analyser.getReason() + "',");
				// @varBranch
				callStmt.setString(59, analyser.getBranch());
				queryToAddAnalyser.append("@varBranch = N'" + analyser.getBranch() + "',");
				// @varPto
				callStmt.setString(60, analyser.getRdoPTO());
				queryToAddAnalyser.append("@varPto = N'" + analyser.getRdoPTO() + "',");
				// @varCurrency
				callStmt.setString(61, analyser.getCurrency());
				queryToAddAnalyser.append("@varCurrency = N'" + analyser.getCurrency() + "',");
				// @varMob_Pager
				callStmt.setString(62, analyser.getMobilePager());
				queryToAddAnalyser.append("@varMob_Pager = N'" + analyser.getMobilePager() + "',");
				// @varInitial
				callStmt.setString(63, analyser.getInitial());
				queryToAddAnalyser.append("@varInitial = N'" + analyser.getInitial() + "',");
				// @varMrmrs
				callStmt.setString(64, analyser.getMrmrs());
				queryToAddAnalyser.append("@varMrmrs = N'" + analyser.getMrmrs() + "',");
				// @varJrsr
				callStmt.setString(65, analyser.getJrsr());
				queryToAddAnalyser.append("@varJrsr = N'" + analyser.getJrsr() + "',");
				// @varCommision1
				callStmt.setString(66, analyser.getCommision1());
				queryToAddAnalyser.append("@varCommision1 = N'" + analyser.getCommision1() + "',");
				// @varCommision2
				callStmt.setString(67, analyser.getCommision2());
				queryToAddAnalyser.append("@varCommision2 = N'" + analyser.getCommision2() + "',");
				// @varCommision3
				callStmt.setString(68, analyser.getCommision3());
				queryToAddAnalyser.append("@varCommision3 = N'" + analyser.getCommision3() + "',");
				// @varCommision4
				callStmt.setString(69, analyser.getCommision4());
				queryToAddAnalyser.append("@varCommision4 = N'" + analyser.getCommision4() + "',");
				// @varCommEffDate
				callStmt.setString(70, analyser.getCommEffDate());
				queryToAddAnalyser.append("@varCommEffDate = N'" + analyser.getCommEffDate() + "',");
				// @varTemps
				callStmt.setString(71, analyser.getTemps());
				queryToAddAnalyser.append("@varTemps = N'" + analyser.getTemps() + "',");
				// @varAnnualPay
				callStmt.setString(72, analyser.getAnnualPay());
				queryToAddAnalyser.append("@varAnnualPay = N'" + analyser.getAnnualPay() + "',");
				// @varContractVehicle
				callStmt.setString(73, analyser.getContractVehicle());
				queryToAddAnalyser.append("@varContractVehicle = N'" + analyser.getContractVehicle() + "',");
				if (analyser.getRecordStatus() == null || analyser.getRecordStatus().trim().equals(""))
				{
					analyser.setRecordStatus("1");
				}
				// @intRecordStatus
				callStmt.setInt(74, Integer.valueOf(analyser.getRecordStatus()));
				queryToAddAnalyser.append("@intRecordStatus = " + Integer.valueOf(analyser.getRecordStatus()) + ",");
				// @varLiaisonName
				callStmt.setString(75, analyser.getLiaisonName());
				queryToAddAnalyser.append("@varLiaisonName = N'" + analyser.getLiaisonName() + "',");
				// @intReferences
				callStmt.setInt(76, 0);
				queryToAddAnalyser.append("@intReferences = " + 0 + ",");
				// @intH1
				callStmt.setInt(77, 0);
				queryToAddAnalyser.append("@intH1 = " + 0 + ",");
				// @varJobCategory
				callStmt.setString(78, analyser.getJobCategory());
				queryToAddAnalyser.append("@varJobCategory = N'" + analyser.getJobCategory() + "',");
				// @execOrphan
				callStmt.setString(79, "");
				queryToAddAnalyser.append("@execOrphan = N'',");
				// @recruiterOrphan
				callStmt.setString(80, "");
				queryToAddAnalyser.append("@recruiterOrphan = N'',");
				// @other1Orphan
				callStmt.setString(81, "");
				queryToAddAnalyser.append("@other1Orphan = N'',");
				// @other2Orphan
				callStmt.setString(82, "");
				queryToAddAnalyser.append("@other2Orphan = N'',");
				// @varJobPayRate
				callStmt.setString(83, analyser.getJobPayRate());
				queryToAddAnalyser.append("@varJobPayRate = N'" + analyser.getJobPayRate() + "',");
				// @varJobBillRate
				callStmt.setString(84, analyser.getJobBillRate());
				queryToAddAnalyser.append("@varJobBillRate = N'" + analyser.getJobBillRate() + "',");
				// @varPoNumber
				callStmt.setString(85, analyser.getPonumber());
				queryToAddAnalyser.append("@varPoNumber = N'" + analyser.getPonumber() + "',");
				// @varDoubleTime
				callStmt.setString(86, analyser.getDoubleTime());
				queryToAddAnalyser.append("@varDoubleTime = N'" + analyser.getDoubleTime() + "',");
				// @varDoubleTimeBill
				callStmt.setString(87, analyser.getDoubleTimeBill());
				queryToAddAnalyser.append("@varDoubleTimeBill = N'" + analyser.getDoubleTimeBill() + "',");
				// @varPerdiem
				callStmt.setString(88, analyser.getPerdiem());
				queryToAddAnalyser.append("@varPerdiem = N'" + analyser.getPerdiem() + "',");
				// @varWorkEmail
				callStmt.setString(89, analyser.getWorkEmail());
				queryToAddAnalyser.append("@varWorkEmail = N'" + analyser.getWorkEmail() + "',");
				// @varChkReferralFee
				callStmt.setString(90, analyser.getChkReferralFee());
				queryToAddAnalyser.append("@varChkReferralFee = N'" + analyser.getChkReferralFee() + "',");
				// @varReferralFeeAmount
				callStmt.setString(91, analyser.getReferralFeeAmount());
				queryToAddAnalyser.append("@varReferralFeeAmount = N'" + analyser.getReferralFeeAmount() + "',");
				// @varDentalInsurance
				callStmt.setString(92, analyser.getDentalInsurance());
				queryToAddAnalyser.append("@varDentalInsurance = N'" + analyser.getDentalInsurance() + "',");
				// @varStdBenefit
				callStmt.setString(93, analyser.getStdBenefit());
				queryToAddAnalyser.append("@varStdBenefit = N'" + analyser.getStdBenefit() + "',");
				// @varLtdBenefit
				callStmt.setString(94, analyser.getLtdBenefit());
				queryToAddAnalyser.append("@varLtdBenefit = N'" + analyser.getLtdBenefit() + "',");
				// @varConsultantJobBoard
				callStmt.setString(95, analyser.getConsultantJobBoard());
				queryToAddAnalyser.append("@varConsultantJobBoard = N'" + analyser.getConsultantJobBoard() + "',");
				// @varDentalInsuranceAmount
				callStmt.setString(96, analyser.getDentalInsuranceAmount().toString());
				queryToAddAnalyser.append("@varDentalInsuranceAmount = N'" + analyser.getDentalInsuranceAmount().toString() + "',");
				// @varUnEmployedForTwoMonths
				callStmt.setString(97, analyser.getUnEmployedForTwoMonths());
				queryToAddAnalyser.append("@varUnEmployedForTwoMonths = N'" + analyser.getUnEmployedForTwoMonths() + "',");
				// @varEmployeeVeteran
				callStmt.setString(98, analyser.getEmployeeVeteran());
				queryToAddAnalyser.append("@varEmployeeVeteran = N'" + analyser.getEmployeeVeteran() + "',");
				// @gender
				callStmt.setString(99, analyser.getGender());
				queryToAddAnalyser.append("@gender = N'" + analyser.getGender() + "',");
				// @WorkSiteState
				callStmt.setString(100, analyser.getWorkSiteState());
				queryToAddAnalyser.append("@WorkSiteState = N'" + analyser.getWorkSiteState() + "',");
				// @AnalyzerCategory1
				callStmt.setString(101, analyser.getAnalyzerCategory1());
				queryToAddAnalyser.append("@AnalyzerCategory1 = N'" + analyser.getAnalyzerCategory1() + "',");
				// @AnalyzerCategory2
				callStmt.setString(102, analyser.getAnalyzerCategory2());
				queryToAddAnalyser.append("@AnalyzerCategory2 = N'" + analyser.getAnalyzerCategory2() + "',");
				// @FLSAStatus
				callStmt.setString(103, analyser.getFlsaStatus());
				queryToAddAnalyser.append("@FLSAStatus = N'" + analyser.getFlsaStatus() + "',");
				// @FLSAReferance
				callStmt.setString(104, analyser.getFlsaReference());
				queryToAddAnalyser.append("@FLSAReferance = N'" + analyser.getFlsaReference() + "',");
				// @Custom1
				callStmt.setString(105, analyser.getCustom1());
				queryToAddAnalyser.append("@Custom1 = N'" + analyser.getCustom1() + "',");
				// @Com
				callStmt.setString(106, "");
				queryToAddAnalyser.append("@Com = N'',");
				// @Custom2
				callStmt.setString(107, analyser.getCustom2());
				queryToAddAnalyser.append("@Custom2 = N'" + analyser.getCustom2() + "',");
				// @Comm
				callStmt.setString(108, analyser.getComm());
				queryToAddAnalyser.append("@Comm = N'" + analyser.getComm() + "',");
				// @DealType
				callStmt.setString(109, analyser.getDealType());
				queryToAddAnalyser.append("@DealType = N'" + analyser.getDealType() + "',");
				// @IsBonusEligible
				callStmt.setString(110, analyser.getIsBonusEligible());
				queryToAddAnalyser.append("@IsBonusEligible = N'" + analyser.getIsBonusEligible() + "',");
				// @BonusAmount
				callStmt.setDouble(111, analyser.getBonusAmount());
				queryToAddAnalyser.append("@BonusAmount = " + analyser.getBonusAmount() + ",");
				// @BonusPercentage
				callStmt.setString(112, analyser.getBonusPercentage());
				queryToAddAnalyser.append("@BonusPercentage = N'" + analyser.getBonusPercentage() + "',");
				// @PermGAAmount
				callStmt.setDouble(113, analyser.getPermGAAmount());
				queryToAddAnalyser.append("@PermGAAmount = " + analyser.getPermGAAmount() + ",");
				// @splitCommissionPercentage1
				callStmt.setString(114, analyser.getSplitCommissionPercentage1());
				queryToAddAnalyser.append("@splitCommissionPercentage1 = N'" + analyser.getSplitCommissionPercentage1() + "',");
				// @splitCommissionPercentage2
				callStmt.setString(115, analyser.getSplitCommissionPercentage2());
				queryToAddAnalyser.append("@splitCommissionPercentage2 = N'" + analyser.getSplitCommissionPercentage2() + "',");
				// @splitCommissionPercentage3
				callStmt.setString(116, analyser.getSplitCommissionPercentage3());
				queryToAddAnalyser.append("@splitCommissionPercentage3 = N'" + analyser.getSplitCommissionPercentage3() + "',");
				// @splitCommissionPercentage4
				callStmt.setString(117, analyser.getSplitCommissionPercentage4());
				queryToAddAnalyser.append("@splitCommissionPercentage4 = N'" + analyser.getSplitCommissionPercentage4() + "',");
				// @skillCategory
				callStmt.setString(118, analyser.getSkillCategory());
				queryToAddAnalyser.append("@skillCategory = N'" + analyser.getSkillCategory() + "',");
				// @vertical
				callStmt.setString(119, analyser.getVertical());
				queryToAddAnalyser.append("@vertical = N'" + analyser.getVertical() + "',");
				// @employeeClass
				callStmt.setString(120, analyser.getEmployeeClass());
				queryToAddAnalyser.append("@employeeClass = N'" + analyser.getEmployeeClass() + "',");
				// @verticalTimesheetType
				callStmt.setString(121, analyser.getVerticalTimesheetType());
				queryToAddAnalyser.append("@verticalTimesheetType = N'" + analyser.getVerticalTimesheetType() + "',");
				// @ImmigrationCost
				callStmt.setDouble(122, analyser.getImmigrationCost());
				queryToAddAnalyser.append("@ImmigrationCost = " + analyser.getImmigrationCost() + ",");
				// @EquipmentCost
				callStmt.setDouble(123, analyser.getEquipmentCost());
				queryToAddAnalyser.append("@EquipmentCost = " + analyser.getEquipmentCost() + ",");
				// @Product
				callStmt.setString(124, analyser.getProduct());
				queryToAddAnalyser.append("@Product = N'" + analyser.getProduct() + "',");
				// @NonBillableCost
				callStmt.setDouble(125, analyser.getNonBillableCost());
				queryToAddAnalyser.append("@NonBillableCost = " + analyser.getNonBillableCost() + ",");
				// @TravelRequired
				callStmt.setString(126, analyser.getTravelRequired());
				queryToAddAnalyser.append("@TravelRequired = N'" + analyser.getTravelRequired() + "',");
				// @CommissionModel1
				callStmt.setString(127, analyser.getCommissionModel1());
				queryToAddAnalyser.append("@CommissionModel1 = N'" + analyser.getCommissionModel1() + "',");
				// @CommissionModel2
				callStmt.setString(128, analyser.getCommissionModel2());
				queryToAddAnalyser.append("@CommissionModel2 = N'" + analyser.getCommissionModel2() + "',");
				// @CommissionModel3
				callStmt.setString(129, analyser.getCommissionModel3());
				queryToAddAnalyser.append("@CommissionModel3 = N'" + analyser.getCommissionModel3() + "',");
				// @CommissionModel4
				callStmt.setString(130, analyser.getCommissionModel4());
				queryToAddAnalyser.append("@CommissionModel4 = N'" + analyser.getCommissionModel4() + "',");
				// @RecruitingClassification
				callStmt.setString(131, analyser.getRecruitingClassification());
				queryToAddAnalyser.append("@RecruitingClassification = N'" + analyser.getRecruitingClassification() + "',");
				// @SickLeaveCost
				callStmt.setDouble(132, analyser.getSickLeaveCost());
				queryToAddAnalyser.append("@SickLeaveCost = " + analyser.getSickLeaveCost() + ",");
				// @FalseTermination
				callStmt.setString(133, analyser.getFalseTermination());
				queryToAddAnalyser.append("@FalseTermination = N'" + analyser.getFalseTermination() + "',");
				// @CommissionPerson5
				callStmt.setString(134, analyser.getCommissionPerson5());
				queryToAddAnalyser.append("@CommissionPerson5 = N'" + analyser.getCommissionPerson5() + "',");
				// @CommissionPerson6
				callStmt.setString(135, analyser.getCommissionPerson6());
				queryToAddAnalyser.append("@CommissionPerson6 = N'" + analyser.getCommissionPerson6() + "',");
				// @CommissionPerson7
				callStmt.setString(136, analyser.getCommissionPerson7());
				queryToAddAnalyser.append("@CommissionPerson7 = N'" + analyser.getCommissionPerson7() + "',");
				// @CommissionPerson8
				callStmt.setString(137, analyser.getCommissionPerson8());
				queryToAddAnalyser.append("@CommissionPerson8 = N'" + analyser.getCommissionPerson8() + "',");
				// @CommissionPerson9
				callStmt.setString(138, analyser.getCommissionPerson9());
				queryToAddAnalyser.append("@CommissionPerson9 = N'" + analyser.getCommissionPerson9() + "',");
				// @CommissionPercentage5
				callStmt.setDouble(139, analyser.getCommissionPercentage5());
				queryToAddAnalyser.append("@CommissionPercentage5 = " + analyser.getCommissionPercentage5() + ",");
				// @CommissionPercentage6
				callStmt.setDouble(140, analyser.getCommissionPercentage6());
				queryToAddAnalyser.append("@CommissionPercentage6 = " + analyser.getCommissionPercentage6() + ",");
				// @CommissionPercentage7
				callStmt.setDouble(141, analyser.getCommissionPercentage7());
				queryToAddAnalyser.append("@CommissionPercentage7 = " + analyser.getCommissionPercentage7() + ",");
				// @CommissionPercentage8
				callStmt.setDouble(142, analyser.getCommissionPercentage8());
				queryToAddAnalyser.append("@CommissionPercentage8 = " + analyser.getCommissionPercentage8() + ",");
				// @CommissionPercentage9
				callStmt.setDouble(143, analyser.getCommissionPercentage9());
				queryToAddAnalyser.append("@CommissionPercentage9 = " + analyser.getCommissionPercentage9() + ",");
				// @Commission5
				callStmt.setDouble(144, analyser.getCommission5());
				queryToAddAnalyser.append("@Commission5 = " + analyser.getCommission5() + ",");
				// @Commission6
				callStmt.setDouble(145, analyser.getCommission6());
				queryToAddAnalyser.append("@Commission6 = " + analyser.getCommission6() + ",");
				// @Commission7
				callStmt.setDouble(146, analyser.getCommission7());
				queryToAddAnalyser.append("@Commission7 = " + analyser.getCommission7() + ",");
				// @Commission8
				callStmt.setDouble(147, analyser.getCommission8());
				queryToAddAnalyser.append("@Commission8 = " + analyser.getCommission8() + ",");
				// @Commission9
				callStmt.setDouble(148, analyser.getCommission9());
				queryToAddAnalyser.append("@Commission9 = " + analyser.getCommission9() + ",");
				// @ProjectCost
				callStmt.setDouble(149, 0.00);
				queryToAddAnalyser.append("@ProjectCost = " + 0.00 + ",");
				// @SickLeavePerHourRate
				callStmt.setDouble(150, analyser.getSickLeavePerHourRate());
				queryToAddAnalyser.append("@SickLeavePerHourRate = " + analyser.getSickLeavePerHourRate() + ",");
				// @SickLeaveCap
				callStmt.setString(151, "");
				queryToAddAnalyser.append("@SickLeaveCap = N'',");
				// @ManagingDirector
				callStmt.setString(152, "");
				queryToAddAnalyser.append("@ManagingDirector = N'',");
				// @CommissionPersonGrade1
				callStmt.setString(153, "");
				queryToAddAnalyser.append("@CommissionPersonGrade1 = N'',");
				// @CommissionPersonGrade2
				callStmt.setString(154, "");
				queryToAddAnalyser.append("@CommissionPersonGrade2 = N'',");
				// @CommissionPersonGrade3
				callStmt.setString(155, "");
				queryToAddAnalyser.append("@CommissionPersonGrade3 = N'',");
				// @CommissionPersonGrade4
				callStmt.setString(156, "");
				queryToAddAnalyser.append("@CommissionPersonGrade4 = N'',");
				// @IsAddressUSPSValidated
				callStmt.setString(157, "");
				queryToAddAnalyser.append("@IsAddressUSPSValidated = N'',");
				// @UsPSAddressValidationDate
				callStmt.setString(158, "");
				queryToAddAnalyser.append("@UsPSAddressValidationDate = N'',");
				// @billablePTO
				callStmt.setDouble(159, analyser.getBillablePTO());
				queryToAddAnalyser.append("@billablePTO = " + analyser.getBillablePTO() + ",");
				// @nonBillablePTO
				callStmt.setDouble(160, analyser.getNonBillablePTO());
				queryToAddAnalyser.append("@nonBillablePTO = " + analyser.getNonBillablePTO() + ",");
				// @billablePTOCost
				callStmt.setDouble(161, analyser.getBillablePTOCost());
				queryToAddAnalyser.append("@billablePTOCost = " + analyser.getBillablePTOCost() + ",");
				// @nonBillablePTOCost
				callStmt.setDouble(162, analyser.getNonBillablePTOCost());
				queryToAddAnalyser.append("@nonBillablePTOCost = " + analyser.getNonBillablePTOCost() + ",");
				// @totalHolidays
				callStmt.setDouble(163, analyser.getTotalHolidays());
				queryToAddAnalyser.append("@totalHolidays = " + analyser.getTotalHolidays() + ",");
				// @billableHolidays
				callStmt.setDouble(164, analyser.getBillableHolidays());
				queryToAddAnalyser.append("@billableHolidays = " + analyser.getBillableHolidays() + ",");
				// @nonBillableHolidays
				callStmt.setDouble(165, analyser.getNonBillableHolidays());
				queryToAddAnalyser.append("@nonBillableHolidays = " + analyser.getNonBillableHolidays() + ",");
				// @billableHolidaysCost
				callStmt.setDouble(166, analyser.getBillableHolidaysCost());
				queryToAddAnalyser.append("@billableHolidaysCost = " + analyser.getBillableHolidaysCost() + ",");
				// @nonBillableHolidaysCost
				callStmt.setDouble(167, analyser.getNonBillableHolidaysCost());
				queryToAddAnalyser.append("@nonBillableHolidaysCost = " + analyser.getNonBillableHolidaysCost() + ",");
				// @intPermanentPlacement
				callStmt.setString(168, "");
				queryToAddAnalyser.append("@intPermanentPlacement = N'',");
				// @varPlacementAmount
				callStmt.setString(169, "");
				queryToAddAnalyser.append("@varPlacementAmount = N'',");
				// @varSalaryAmount
				callStmt.setString(170, "");
				queryToAddAnalyser.append("@varSalaryAmount = N'',");
				// @varPlacementPercentage
				callStmt.setString(171, "");
				queryToAddAnalyser.append("@varPlacementPercentage = N'',");
				// @varPlacementDate
				callStmt.setString(172, "");
				queryToAddAnalyser.append("@varPlacementDate = N'',");
				// @intDummy
				callStmt.setString(173, "");
				queryToAddAnalyser.append("@intDummy = N'',");
				// @intDummy1
				callStmt.setString(174, "");
				queryToAddAnalyser.append("@intDummy1 = N'',");
				// @execOrphanInter
				callStmt.setString(175, "");
				queryToAddAnalyser.append("@execOrphanInter = N'',");
				// @varBranchInter
				callStmt.setString(176, "");
				queryToAddAnalyser.append("@varBranchInter = N'',");
				// @varCommentsInter
				callStmt.setString(177, "");
				queryToAddAnalyser.append("@varCommentsInter = N'',");
				// @varCommName1Inter
				callStmt.setString(178, "");
				queryToAddAnalyser.append("@varCommName1Inter = N'',");
				// @varPer1Inter
				callStmt.setString(179, "");
				queryToAddAnalyser.append("@varPer1Inter = N'',");
				// @varCommName2Inter
				callStmt.setString(180, "");
				queryToAddAnalyser.append("@varCommName2Inter = N'',");
				// @varPer2Inter
				callStmt.setString(181, "");
				queryToAddAnalyser.append("@varPer2Inter = N'',");
				// @varCommName3Inter
				callStmt.setString(182, "");
				queryToAddAnalyser.append("@varCommName3Inter = N'',");
				// @varPer3Inter
				callStmt.setString(183, "");
				queryToAddAnalyser.append("@varPer3Inter = N'',");
				// @varCommName4Inter
				callStmt.setString(184, "");
				queryToAddAnalyser.append("@varCommName4Inter = N'',");
				// @varPer4Inter
				callStmt.setString(185, "");
				queryToAddAnalyser.append("@varPer4Inter = N'',");
				// @varCommision1Inter
				callStmt.setString(186, "");
				queryToAddAnalyser.append("@varCommision1Inter = N'',");
				// @varCommision2Inter
				callStmt.setString(187, "");
				queryToAddAnalyser.append("@varCommision2Inter = N'',");
				// @varCommision3Inter
				callStmt.setString(188, "");
				queryToAddAnalyser.append("@varCommision3Inter = N'',");
				// @varCommision4Inter
				callStmt.setString(189, "");
				queryToAddAnalyser.append("@varCommision4Inter = N'',");
				// @intSplit
				callStmt.setInt(190, 0);
				queryToAddAnalyser.append("@intSplit = " + 0 + ",");
				// @varCountry
				callStmt.setString(191, "USA");
				queryToAddAnalyser.append("@varCountry = N'USA',");
				// @bitInternational
				callStmt.setString(192, "");
				queryToAddAnalyser.append("@bitInternational = N'',");
				// @other1OrphanInter
				callStmt.setString(193, "");
				queryToAddAnalyser.append("@other1OrphanInter = N'',");
				// @other2OrphanInter
				callStmt.setString(194, "");
				queryToAddAnalyser.append("@other2OrphanInter = N'',");
				// @recruiterOrphanInter
				callStmt.setString(195, "");
				queryToAddAnalyser.append("@recruiterOrphanInter = N'',");



				// @grossProfitPercentage
				callStmt.setDouble(196, 0.00);
				queryToAddAnalyser.append("@grossProfitPercentage = 0.00,");


				// @grossProfit
				callStmt.setDouble(197, 0.00);
				queryToAddAnalyser.append("@grossProfit = 0.00,");

				// @commissionableProfit1
				callStmt.setDouble(198, 0.00);
				queryToAddAnalyser.append("@commissionableProfit = 0.00,");

				// @rejectionStatus
				/*callStmt.setInt(199, analyser.getRejectionStatus());
				queryToAddAnalyser.append("@rejectionStatus = " + analyser.getRejectionStatus() + ",");

				// @rejectionReason
				callStmt.setString(200, analyser.getRejectionReason());
				queryToAddAnalyser.append("@rejectionReason = N'" + analyser.getRejectionReason() + "',");

				// rejectedBy
				callStmt.setString(201, analyser.getRejectedBy());
				queryToAddAnalyser.append("rejectedBy = N'" + analyser.getRejectedBy() + "',");

				// @rejectionDate
				callStmt.setTimestamp(202, analyser.getRejectionDate());
				queryToAddAnalyser.append("@rejectionDate = '" + analyser.getRejectionDate() + "',");*/

				// @geoOffice1
				callStmt.setString(199, "");
				queryToAddAnalyser.append("@geoOffice = N'',");

				callStmt.setString(200, "");
				queryToAddAnalyser.append("@longitude = N'',");

				callStmt.setString(201, "");
				queryToAddAnalyser.append("@latitude = N'',");

				// @pTOLimitToOverrideSick
				callStmt.setDouble(202, analyser.getpTOLimitToOverrideSick());
				queryToAddAnalyser.append("@pTOLimitToOverrideSick = ").append(analyser.getpTOLimitToOverrideSick()+",");

				// @DistanceFromWorksite
				callStmt.setString(203, analyser.getDistanceFromWorksite());
				queryToAddAnalyser.append("@DistanceFromWorksite = '" + analyser.getDistanceFromWorksite() + "', ");

				// @SickLeaveType
				callStmt.setString(204, analyser.getSickLeaveType());
				queryToAddAnalyser.append("@varSickLeaveType = '" + analyser.getDistanceFromWorksite() + "' , ");

				// @varPortfolio
				callStmt.setString(205, analyser.getPortfolio());
				queryToAddAnalyser.append("\n@varPortfolio = '" + analyser.getPortfolio() + "' , ");

				// portfolioDescription
				callStmt.setString(206, analyser.getPortfolioDescription());
				queryToAddAnalyser.append("\n@varPortfolioDescription = '" + analyser.getPortfolioDescription() + "' ");

				queryToAddAnalyser.append("SELECT	'Return Value' = @return_value GO ");

				System.out.println(queryToAddAnalyser.toString());
				logger.debug("Query to add update analyser is : " + queryToAddAnalyser.toString());

				// execute insertDBUSER store procedure
				rs = callStmt.executeQuery();
				if (rs != null)
				{
					while (rs.next())
					{
						status = "" + rs.getInt(1);
					}
					callStmt.close();
					rs.close();
					callStmt = null;
					rs = null;
				}
				else
				{
					status = "-1";
					System.err.println("Analyser could not be rehired");
					logger.debug("Analyser could not be rehired");
					throw new Exception("Analyser could not be rehire");
				}
			}
			catch (Exception ex)
			{
				status = "1";
				System.err.println("Exception : " + ex.getMessage());
				System.out.println("Exception in rehireAnalyser method of AnalyserRepositoryImpl");
				logger.debug("Exception in rehireAnalyser method of AnalyserRepositoryImpl");
				logger.debug("Exception " + ex.getMessage());
				ex.printStackTrace();
			}
			finally
			{
				if (rs != null) rs.close();
				if (callStmt != null) callStmt.close();
			}
		}

		/**
		 * @return the status
		 */
		public String getStatus()
		{
			return status;
		}

	}

	@Override
	public String rejectAnalyser(String userId, Integer analyserId, Integer parentId, String rejectReason)
	{
		Session session = entityManager.unwrap(Session.class);
		String action = "Reject";
		AnalyserMiscelleneousWork work = new AnalyserMiscelleneousWork(userId, analyserId, parentId, rejectReason, action);
		session.doWork(work);
		String result = work.getResult();
		return result;
	}

	@Override
	public List<Analyser> spGetRejectedAnalyserList(String userId, String orderBy, String searchString, String companyCode)
	{
		Session session = entityManager.unwrap(Session.class);
		AnalyserRejectedListWork work = new AnalyserRejectedListWork(userId, orderBy, searchString, companyCode);
		session.doWork(work);
		List<Analyser> list = work.getList();
		return list;
	}

	private static class AnalyserRejectedListWork implements Work
	{

		private Logger loggerAnalyserListWork = LoggerFactory.getLogger(AnalyserListWork.class);

		private List<Analyser> list;
		private Analyser analyser;
		String userId;
		String orderBy;
		String searchString;
		String companyCode;
		/**
		 * @param userId
		 * @param userList
		 * @param orderBy
		 * @param searchString
		 */
		public AnalyserRejectedListWork(String userId, String orderBy, String searchString, String companyCode)
		{
			super();
			this.userId = userId;
			this.orderBy = orderBy;
			this.searchString = searchString;
			this.companyCode = companyCode;
		}

		@Override
		public void execute(Connection connection) throws SQLException
		{

			analyser = new Analyser();
			list = new ArrayList<Analyser>();
			ResultSet rs = null;
			CallableStatement cstmt = null;

			try
			{
				cstmt = connection.prepareCall("{call " + FacesUtils.SCHEMA_WIRELESS + ".spGetRejectedAnalyserList(?, ?, ?, ?)}");
				cstmt.setString("varLoggedOnUserID", userId);
				cstmt.setString("varOrderBy", orderBy);
				cstmt.setString("varSearchString", searchString);
				cstmt.setString("varCompanyCode", companyCode);

				cstmt.setFetchSize(100);
				rs = cstmt.executeQuery();
				list = getRSRowInfo(rs);
				System.out.println(list.size());
			}
			catch (Exception e)
			{
				loggerAnalyserListWork.error(e.getMessage());
				loggerAnalyserListWork.debug(e.getMessage());
				e.printStackTrace();
			}
			finally
			{
				if (rs != null) rs.close();
				if (cstmt != null) cstmt.close();
			}
		}

		private List<Analyser> getRSRowInfo(ResultSet rs) throws Exception
		{
			ResultSetMetaData rsMeta;
			try
			{
				rsMeta = rs.getMetaData();
				int iRowCount = rsMeta.getColumnCount();
				while (rs.next())
				{
					analyser = new Analyser();
					for (int i = 0; i < iRowCount; i++)
					{
						String columnName = rsMeta.getColumnLabel(i + 1);
						// put values into the array list
						try
						{
							if (columnName.equalsIgnoreCase("PARENTID"))
							{
								analyser.setParentId((String) getRSColumnValue(rs, rsMeta, i + 1));
							}
							else if (columnName.equalsIgnoreCase("ANALYSERID"))
							{
								long aId = ((BigDecimal) getRSColumnValue(rs, rsMeta, i + 1)).longValue();
								analyser.setAnalyserId(new Long(aId));
							}
							else if (columnName.equalsIgnoreCase("LNAME"))
							{
								analyser.setLastName((String) getRSColumnValue(rs, rsMeta, i + 1));
							}
							else if (columnName.equalsIgnoreCase("FNAME"))
							{
								analyser.setFirstName((String) getRSColumnValue(rs, rsMeta, i + 1));
							}
							else if (columnName.equalsIgnoreCase("RejectionStatus"))
							{
								analyser.setRejectionStatus((Integer) getRSColumnValue(rs, rsMeta, i + 1));
							}
							else if (columnName.equalsIgnoreCase("RejectedBy"))
							{
								analyser.setRejectedBy((String) getRSColumnValue(rs, rsMeta, i + 1));
							}
							else if (columnName.equalsIgnoreCase("RejectionReason"))
							{
								analyser.setRejectionReason((String) getRSColumnValue(rs, rsMeta, i + 1));
							}
							else if (columnName.equalsIgnoreCase("RejectionDate"))
							{
								analyser.setRejectionDate((Timestamp) getRSColumnValue(rs, rsMeta, i + 1));
							}

							else if (columnName.equalsIgnoreCase("BRANCH"))
							{
								analyser.setBranch((String) getRSColumnValue(rs, rsMeta, i + 1));
							}

							else if (columnName.equalsIgnoreCase("VERTICAL"))
							{
								analyser.setVertical((String) getRSColumnValue(rs, rsMeta, i + 1));
							}

							else if (columnName.equalsIgnoreCase("COMPANY"))
							{
								analyser.setClientCompanyName((String) getRSColumnValue(rs, rsMeta, i + 1));
							}
							else if (columnName.equalsIgnoreCase("CompanyCode"))
							{
								analyser.setCompanyCode((String) getRSColumnValue(rs, rsMeta, i + 1));
							}
						}
						catch (Exception ex)
						{
							continue;
						}
					}
					list.add(analyser);

				}
				return list;
			}
			catch (SQLException e)
			{
				String sz = "An error occured while attempting to parse resultset column information from the database.  Error type :  "
						+ e.getSQLState() + ".  Error Code : " + e.getErrorCode();
				Exception err = new Exception(sz);
				throw err;
			}
		}

		private Object getRSColumnValue(ResultSet rs, ResultSetMetaData rsMeta, int pos) throws Exception
		{
			try
			{
				int iType = (int) rsMeta.getColumnType(pos);
				switch (iType)
				{
				case Types.BIGINT:
					return new Long(rs.getLong(pos));
				case Types.NUMERIC:
				{
					java.math.BigDecimal bigDecimal = rs.getBigDecimal(pos);
					if (bigDecimal == null) return null;
					else if (bigDecimal.scale() == 0) return new Long(bigDecimal.longValue());
					else return new Double(bigDecimal.doubleValue());
				}

				case Types.CHAR:
				{
					String s = rs.getString(pos);
					// if (rs.wasNull()) return "";
					return s;
				}
				case Types.TIMESTAMP:
				{
					java.sql.Timestamp d = rs.getTimestamp(pos);
					return d;
				}
				case Types.DATE:
				{
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
				case Types.VARCHAR:
				{
					String s = rs.getString(pos);
					return s;
				}
				case Types.DECIMAL:
				{
					BigDecimal s = rs.getBigDecimal(pos);
					return s;
				}
				default:
					return rs.getObject(pos);
				}
			}
			catch (SQLException e)
			{
				String sz = "An error occured while attempting to parse resultset column information from the database.  Error type :  "
						+ e.getSQLState() + ".  Error Code : " + e.getErrorCode();
				Exception err = new Exception(sz);
				throw err;
			}

		}

		/**
		 * @return the list
		 */
		public List<Analyser> getList()
		{
			return list;
		}

	}

	@Override
	public List<Object> getAnalyzerDataDumpFile(String userId) {
		StoredProcedureQuery query = entityManager.createStoredProcedureQuery("getAllHistoryData")
				.registerStoredProcedureParameter("varLoggedOnUserID", String.class, ParameterMode.IN)
				.setParameter("varLoggedOnUserID", userId);

		query.execute();

		@SuppressWarnings("unchecked")
		List<Object> objList = query.getResultList();		
		return objList;
	}

	@Override
	public String getGenericDescriptionGeneral(String actionType, String value)
	{
		Session session = entityManager.unwrap(Session.class);
		AnalyserGenericWork work = null;
		String result = "";
		if(actionType.equalsIgnoreCase("MANAGINGDIRECTOREMPLID"))
		{
			work = new AnalyserGenericWork(actionType, value);
			session.doWork(work);
			result = work.getResult();
		} 
		else if(actionType.equalsIgnoreCase("PSREGIONCODE"))
		{
			work = new AnalyserGenericWork(actionType, value);
			session.doWork(work);
			result = work.getResult();
		}
		return result;
	}

	@Override
	public String auditPortofolioWSCall(AnalyserDTO analyser, PortfolioDownstreamRequestDTO request, PortfolioDownstreamResponseDTO response) {
		Session session = entityManager.unwrap(Session.class);
		PortofolioAuditWork work = null;
		String result = "";
		work = new PortofolioAuditWork(analyser, request, response);
		session.doWork(work);		
		return result;
	}

	private static class PortofolioAuditWork implements Work
	{

		private Logger logger = LoggerFactory.getLogger(getClass());
		AnalyserDTO analyser;
		PortfolioDownstreamRequestDTO request;
		PortfolioDownstreamResponseDTO response;

		public PortofolioAuditWork(AnalyserDTO analyser, PortfolioDownstreamRequestDTO request, PortfolioDownstreamResponseDTO response)
		{
			super();
			this.analyser = analyser;
			this.request  = request;
			this.response = response;
		}

		@Override
		public void execute(Connection connection) throws SQLException
		{
			CallableStatement callStmt = null;
			int anlayserId = StringUtils.isBlank(analyser.getAnalyserId()) ? 0 : Integer.parseInt(analyser.getAnalyserId());
			int parentId = StringUtils.isBlank(analyser.getParentId())? 0 : Integer.parseInt(analyser.getParentId()) ;
			
			try
			{
				if (connection != null)
				{
					String query = "{call " + FacesUtils.SCHEMA_DBO + ".spAuditPortfolioWebService('" + FacesUtils.getCurrentUserId() 
					+ "'," + anlayserId + "," + parentId + ",'" + request.getUrl() + "','" + response.getRawResponse() + "','" 
					+ response.getCallRequestedTime() + "','" + response.getResponseReceivedTime() + "','" + analyser.getCompanyCode()+ "','" 
					+ response.getTimeTakenInMilliSec() + "'," + response.getStatusCode()+ ",'" + response.getComments()+ "'"
					+ ")}";

					System.out.println("Executing Query in PortofolioAuditWork :  " + query);
					logger.info("PortofolioAuditWork Query is : " + query);
					
					callStmt = connection.prepareCall(query);
					callStmt.execute();
					callStmt.close();
					callStmt = null;
				}
			}catch (Exception ex)
			{
				System.out.println("Exception in AnalyserPortofolioWork AnalyserRepositoryImpl");
				response.setComments(ex.getMessage());
				ex.printStackTrace();
			}
			finally
			{

				if (callStmt != null) 
					callStmt.close();
			}

		}
	}

}