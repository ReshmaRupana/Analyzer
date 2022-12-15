/**
 * 
 */
package com.disys.analyzer.repository.custom.impl;

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
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.disys.analyzer.config.util.FacesUtils;
import com.disys.analyzer.config.util.Util;
import com.disys.analyzer.model.AnalyserClientSite;
import com.disys.analyzer.model.dto.AnalyserClientSiteDTO;
import com.disys.analyzer.repository.custom.AnalyserClientSiteRepositoryCustom;

/**
 * @author Sajid
 * 
 */
@Repository
@Transactional(readOnly = true)
public class AnalyserClientSiteRepositoryImpl implements
		AnalyserClientSiteRepositoryCustom {
	@PersistenceContext
	EntityManager entityManager;

	/* (non-Javadoc)
	 * @see com.disys.analyzer.repository.AnalyserClientRepositoryCustom#getAnalyserAllClients(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public List<AnalyserClientSiteDTO> getAnalyserAllSites(String userId,
			String userList, String orderBy, String searchString) {

		List<AnalyserClientSiteDTO> clientsList = new ArrayList<AnalyserClientSiteDTO>();

		Session session = entityManager.unwrap(Session.class);
		AnalyserClientSiteDTOWork work = new AnalyserClientSiteDTOWork(userId,
				userList, orderBy, searchString);
		session.doWork(work);
		clientsList = work.getClientSiteList();
		System.out.println("Size is : " + clientsList);
		return clientsList;

	}
	
	
	@Procedure
	public List<AnalyserClientSiteDTO> getAnalyserAllSitesSickCaluculations(String userId, String clientId, String homeZip, String workFromSource){
		List<AnalyserClientSiteDTO> clientsList = new ArrayList<AnalyserClientSiteDTO>();

		Session session = entityManager.unwrap(Session.class);
		AnalyserClientSiteDTOWork work = new AnalyserClientSiteDTOWork(userId,
				clientId, homeZip, workFromSource);
		session.doWork(work);
		clientsList = work.getClientSiteList();
		System.out.println("Size is : " + clientsList);
		return clientsList;
	}

	private static class AnalyserClientSiteDTOWork implements Work {

		List<AnalyserClientSiteDTO> clientSiteList;
		AnalyserClientSiteDTO clientSite;

		String userId;
		String userList;
		String orderBy;
		String searchString;

		public AnalyserClientSiteDTOWork(String userId, String userList,
				String orderBy, String searchString) {
			this.userId = userId;
			this.userList = userList;
			this.orderBy = orderBy;
			this.searchString = searchString;
		}

		@Override
		public void execute(Connection connection) throws SQLException {
			clientSiteList = new ArrayList<AnalyserClientSiteDTO>();
			clientSite = new AnalyserClientSiteDTO();

			CallableStatement cstmt = connection
					.prepareCall("{call "+ FacesUtils.SCHEMA_WIRELESS +".spGetAnalyserAllSite(?, ?, ?, ?)}");
			cstmt.setString("varLoggedOnUserID", userId);
			cstmt.setString("varUserList", userList);
			cstmt.setString("varOrderBy", orderBy);
			cstmt.setString("varSearchString", searchString);

			ResultSet rs = cstmt.executeQuery();

			try {
				clientSiteList = getRSRowInfo(rs);
				System.out.println(clientSiteList.size());
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (rs != null)
					rs.close();
				if (cstmt != null)
					cstmt.close();
			}

		}

		private List<AnalyserClientSiteDTO> getRSRowInfo(ResultSet rs)
				throws Exception {
			ResultSetMetaData rsMeta;
			try {
				rsMeta = rs.getMetaData();
				int iRowCount = rsMeta.getColumnCount();
				while (rs.next()) {
					clientSite = new AnalyserClientSiteDTO();
					for (int i = 0; i < iRowCount; i++) {
						String columnName = rsMeta.getColumnLabel(i + 1);
						// put values into the array list
						try {
							if (columnName.equalsIgnoreCase("Email")) {
								clientSite.setEmail((String) getRSColumnValue(
										rs, rsMeta, i + 1));
							} else if (columnName.equalsIgnoreCase("Company")) {
								clientSite
										.setCompany((String) getRSColumnValue(
												rs, rsMeta, i + 1));
							} else if (columnName
									.equalsIgnoreCase("manager_name")) {
								clientSite
										.setManagerName((String) getRSColumnValue(
												rs, rsMeta, i + 1));
							} else if (columnName.equalsIgnoreCase("Address")) {
								clientSite
										.setCompleteAddress((String) getRSColumnValue(
												rs, rsMeta, i + 1));
							} else if (columnName
									.equalsIgnoreCase("Updated_By")) {
								clientSite
										.setUpdatedBy((String) getRSColumnValue(
												rs, rsMeta, i + 1));
							} else if (columnName.equalsIgnoreCase("Site_ID")) {
								clientSite
										.setSiteId(Integer.valueOf((String)getRSColumnValue(
												rs, rsMeta, i + 1)));
							} else if (columnName.equalsIgnoreCase("Telephone")) {
								clientSite
										.setTelephone((String) getRSColumnValue(
												rs, rsMeta, i + 1));
							} else if (columnName.equalsIgnoreCase("State")) {
								clientSite.setState((String) getRSColumnValue(
										rs, rsMeta, i + 1));
							} else if (columnName.equalsIgnoreCase("Zipcode")) {
								clientSite
										.setZipCode((String) getRSColumnValue(
												rs, rsMeta, i + 1));
							} else if (columnName
									.equalsIgnoreCase("sickLeavePerHourRate")) {
								clientSite
										.setSickLeavePerHourRate((Double) getRSColumnValue(
												rs, rsMeta, i + 1));
							} else if (columnName
									.equalsIgnoreCase("sickLeaveCap")) {
								clientSite
										.setSickLeaveCap((Integer) getRSColumnValue(
												rs, rsMeta, i + 1));
							} else if (columnName
									.equalsIgnoreCase("ISAddressUSPSValidated")) {
								clientSite.setIsAddressUSPSValidated((String) getRSColumnValue(
												rs, rsMeta, i + 1));
							} else if (columnName
									.equalsIgnoreCase("Longitude")) {
								clientSite.setLongitude((String) getRSColumnValue(
										rs, rsMeta, i + 1));
							} else if (columnName
									.equalsIgnoreCase("Latitude")) {
								clientSite.setLatitude((String) getRSColumnValue(
										rs, rsMeta, i + 1));
							}
							else if (columnName.equalsIgnoreCase("client_id")) {
								clientSite
										.setClientId((Integer) getRSColumnValue(
												rs, rsMeta, i + 1));
							} else if (columnName.equalsIgnoreCase("PTOLimitToOverrideSick")) {
								Double val = (Double) getRSColumnValue(rs, rsMeta, i + 1);
								System.out.println("PTOLimitToOverrideSick :: " + val);
								if(val > 0)
								{
									System.out.println("PTOLimitToOverrideSick :: " + val);
								}
								clientSite.setPTOLimitToOverrideSick(val.toString());
							}
						} catch (Exception ex) {
							System.out.println("Exception :: " + ex.getMessage());
							continue;
						}
					}
					clientSiteList.add(clientSite);

				}
				return clientSiteList;
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
		 * @return the clientSiteList
		 */
		public List<AnalyserClientSiteDTO> getClientSiteList() {
			return clientSiteList;
		}
	}

	@Override
	public String addUpdateAnalyserSite(AnalyserClientSite analyserClientSite,
			String userId, int actionType) {
		Session session = entityManager.unwrap(Session.class);
		AnalyserClientSiteWork work = new AnalyserClientSiteWork(
				analyserClientSite, userId, actionType);
		session.doWork(work);
		String result = work.getResult();
		return result;
	}

	private static class AnalyserClientSiteWork implements Work {

		String result = "";
		Logger logger = LoggerFactory.getLogger(getClass());

		AnalyserClientSite analyserClientSite;
		int actionType;
		String userId;

		public AnalyserClientSiteWork(AnalyserClientSite analyserClientSite,
				String userId, int actionType) {
			super();
			this.analyserClientSite = analyserClientSite;
			this.userId = userId;
			this.actionType = actionType;
		}

		@Override
		public void execute(Connection connection) throws SQLException {
			CallableStatement callStmt = null;
			ResultSet rs = null;
			result = "0";
			try {
				if (connection != null) {
					String query = "";
					int recordStatus = 1;
					query = "{call "
							+ FacesUtils.SCHEMA_WIRELESS
							+ ".spAddUpdateAnalyserSite("
							+ actionType
							+ ",'"
							+ userId
							+ "',"
							+ analyserClientSite.getSiteId()
							+ ",'"
							+ analyserClientSite.getClientId()
							+ "','"
							+ Util.removeSingleQuote(analyserClientSite
									.getManagerName())
							+ "','"
							+ Util.replaceSingleQuote(analyserClientSite
									.getAddress1())
							+ "','"
							+ Util.replaceSingleQuote(analyserClientSite
									.getAddress2())
							+ "','"
							+ Util.removeSingleQuote(analyserClientSite
									.getCity())
							+ "','"
							+ analyserClientSite.getState()
							+ "','"
							+ analyserClientSite.getZipCode()
							+ "','"
							+ analyserClientSite.getTelephone()
							+ "','"
							+ analyserClientSite.getFax()
							+ "','"
							+ analyserClientSite.getEmail()
							+ "','"
							+ Util.removeSingleQuote(analyserClientSite
									.getLastName()) + "','"
							+ analyserClientSite.getCountry() + "',"
							+ recordStatus + ",'" 
							+ analyserClientSite.getIsAddressUSPSValidated() + "','"
							+ (analyserClientSite.getuSPSAddressValidationDate() != null ? analyserClientSite.getuSPSAddressValidationDate() : "") + "','"
							+ analyserClientSite.getValidatedBy() + "','"
							+ analyserClientSite.getLatitude() + "','"
							+ analyserClientSite.getLongitude()
							+ "')}";

					System.out
							.println("Executing Query in AnalyserClientSiteWork :  "
									+ query);

					logger.debug("Executing Query in AnalyserClientSiteWork :  "
							+ query);

					callStmt = connection.prepareCall(query);
					rs = callStmt.executeQuery();
					if (rs != null) {
						while (rs.next()) {
							result = "" + rs.getInt(1);
						}
					}

					callStmt.close();
					rs.close();
					callStmt = null;
					rs = null;
				}
			} catch (Exception ex) {
				System.out
						.println("Exception in AnalyserClientSiteWork AnalyserClientSiteRepositoryImpl");
				logger.error("Exception in AnalyserClientSiteWork AnalyserClientSiteRepositoryImpl --- "
						+ ex.getMessage());
				ex.printStackTrace();
			} finally {
				if (rs != null)
					rs.close();
				if (callStmt != null)
					callStmt.close();
			}
		}

		/**
		 * @return the result
		 */
		public String getResult() {
			return result;
		}

	}

	@Override
	public AnalyserClientSite getAnalyserClientSiteInfo(String analyserSiteId) {
		Session session = entityManager.unwrap(Session.class);
		AnalyserClientSiteInfoWork work = new AnalyserClientSiteInfoWork(
				analyserSiteId);
		session.doWork(work);
		AnalyserClientSite address = work.getAnalyserSite();
		return address;
	}

	private static class AnalyserClientSiteInfoWork implements Work {
		public Logger logger = LoggerFactory.getLogger(getClass());
		private AnalyserClientSite analyserSite;
		String analyserSiteId;

		ResultSet rs = null;
		CallableStatement callStmt = null;

		/**
		 * @param analyserSiteId
		 */
		public AnalyserClientSiteInfoWork(String analyserSiteId) {
			super();
			this.analyserSiteId = analyserSiteId;
		}

		@Override
		public void execute(Connection connection) throws SQLException {

			analyserSite = new AnalyserClientSite();

			String query = "select Site_Id, Analyser_ClientSite.Country, Analyser_ClientSite.Client_Id,Analyser_ClientSite.Org_ID,LastName,Manager_Name,Address1,Address2,City, State, ltrim(rtrim(Zipcode)) as Zipcode, Telephone, ltrim(rtrim(Fax)) as Fax, ltrim(rtrim(Email)) as Email,Analyser_ClientSite.Updated_By,Analyser_ClientSite.Status,"
					+ "cast(client.Client_Id as varchar) + "
					+ "'~'"
					+ " + Company  as ClientCompany from Analyser_ClientSite,(select Client_Id,Company from Analyser_Client) client where Analyser_ClientSite.Site_Id = "
					+ analyserSiteId
					+ " and Analyser_ClientSite.Client_Id = client.Client_Id";

			System.out.println("query " + query);

			logger.debug("Query in AnalyserClientAddress " + query);
			callStmt = connection.prepareCall(query);
			rs = callStmt.executeQuery();

			try {

				if (rs != null) {
					if (rs.next()) {
						analyserSite.setSiteId(rs.getInt("Site_Id"));
						analyserSite.setClientId(rs.getInt("Client_Id"));
						analyserSite.setOrgId(rs.getInt("Org_Id"));
						analyserSite.setLastName(rs.getString("LastName"));
						analyserSite.setManagerName(rs
								.getString("Manager_Name"));
						analyserSite.setAddress1(rs.getString("Address1"));
						analyserSite.setAddress2(rs.getString("Address2"));
						analyserSite.setCity(rs.getString("City"));
						analyserSite.setState(rs.getString("State"));
						analyserSite.setZipCode(rs.getString("Zipcode"));
						analyserSite.setTelephone(rs.getString("Telephone"));
						analyserSite.setFax(rs.getString("Fax"));
						analyserSite.setEmail(rs.getString("Email"));
						analyserSite.setUpdatedBy(rs.getString("Updated_By"));

						analyserSite.setCountry(rs.getString("Country"));
					}
				} else {
					analyserSite = null;
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (rs != null)
					rs.close();
				if (callStmt != null)
					callStmt.close();
			}
		}

		/**
		 * @return the analyserSite
		 */
		public AnalyserClientSite getAnalyserSite() {
			return analyserSite;
		}
	}
}