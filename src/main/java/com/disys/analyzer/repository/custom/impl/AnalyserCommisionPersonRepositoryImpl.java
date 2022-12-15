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
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Session;
import org.hibernate.jdbc.Work;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.disys.analyzer.config.database.DBConnection;
import com.disys.analyzer.config.util.Constants;
import com.disys.analyzer.config.util.FacesUtils;
import com.disys.analyzer.config.util.Util;
import com.disys.analyzer.model.AnalyserCommisionPerson;
import com.disys.analyzer.model.AnalyserCommisionPersonPK;
import com.disys.analyzer.model.dto.CommissionPersonGradeDTO;
import com.disys.analyzer.model.dto.AnalyserCommisionPersonDTO;
import com.disys.analyzer.model.dto.UserDTO;
import com.disys.analyzer.repository.custom.AnalyserCommisionPersonRepositoryCustom;

/**
 * @author Sajid
 * 
 */
@Repository
@Transactional(readOnly = true)
public class AnalyserCommisionPersonRepositoryImpl implements AnalyserCommisionPersonRepositoryCustom
{
	
	public Logger logger = LoggerFactory.getLogger(getClass());
	
	public static final String USERS_LIST = "commissionUsers";
	public static final String IRC_USERS_LIST = "ircUsersList";
	public static final String ADD = "ADD";
	public static final String ACTIVE_COMMISSION_PERSONS_LIST = "commissionPersonsList";
	public static final String ACTIVE_USERS_LIST = "activeUsersList";
	public static final String ACTIVE_USERS_LIST_LIAISON = "activeUsersListLiaison";	
	public static final String COMMISSION_PERSON_INFO = "commissionPersonInfo";
	public static final String UPDATE = "Update";
	public static final String MD = "MD";
	public static final String AE = "AE";
	public static final String REC = "REC";
	public static final String AGM = "AGM";
	public static final String ALL = "ALL";
	
	@PersistenceContext EntityManager entityManager;
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.disys.analyzer.repository.custom.
	 * AnalyserCommissionPersonRepositoryCustom#getCommissionUsersList(java.lang
	 * .String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public List<AnalyserCommisionPerson> getCommissionUsersList(String userId, String userList, String orderBy, String searchString, String companyCode)
	{
		Session session = entityManager.unwrap(Session.class);
		AnalyserCommisionPersonWork work = new AnalyserCommisionPersonWork(userId, userList, orderBy, searchString, USERS_LIST, companyCode);
		session.doWork(work);
		List<AnalyserCommisionPerson> list = work.getList();
		return list;
	}
	
	private static class AnalyserCommisionPersonWork implements Work
	{
		
		private List<AnalyserCommisionPerson> list;
		private List<String> namesList;
		private AnalyserCommisionPerson person;
		private List<UserDTO> userDtoList;
		String userId;
		String userList;
		String orderBy;
		String searchString;
		String commissionPersonId;
		boolean cpg;
		
		String action;
		
		
		String personName;
		String commissionPersonUserId;
		String commissionPersonRole;
		String commissionPersonClassification;
		String companyCode;
		
		private String status;
		
		CallableStatement cstmt = null;
		
		/**
		 * @param userId
		 * @param userList
		 * @param orderBy
		 * @param searchString
		 * @param companyCode
		 */
		public AnalyserCommisionPersonWork(String userId, String userList, String orderBy, String searchString, String action, String companyCode)
		{
			super();
			this.userId = userId;
			this.userList = userList;
			this.orderBy = orderBy;
			this.searchString = searchString;
			this.action = action;
			this.companyCode = companyCode;
		}
		
		public AnalyserCommisionPersonWork(String userId, String userList, String orderBy, String searchString, String action, boolean cpg, String companyCode)
		{
			this(userId, userList, orderBy, searchString, action, companyCode);
			this.cpg = cpg;
		}
		
		public AnalyserCommisionPersonWork(String userId,String personName,String commissionPersonUserId,
				String commissionPersonRole,String commissionPersonClassification, String action, String companycode)
		{
			super();
			this.userId = userId;
			this.personName = personName;
			this.commissionPersonUserId = commissionPersonUserId;
			this.commissionPersonRole = commissionPersonRole;
			this.commissionPersonClassification = commissionPersonClassification;
			this.action = action;
			this.companyCode = companycode;
		}
		
		public AnalyserCommisionPersonWork(String userId, String commissionPersonId, String action)
		{
			this.userId = userId;
			this.commissionPersonId = commissionPersonId;
			this.action = action;
		}

		@Override
		public void execute(Connection connection) throws SQLException
		{
			// TODO Auto-generated method stub
			
			person = new AnalyserCommisionPerson();
			list = new ArrayList<AnalyserCommisionPerson>();
			
			if (action.equals(USERS_LIST))
			{
				try
				{
					StringBuilder queryToAnalyserComm = new StringBuilder();
					queryToAnalyserComm.append(
					        "USE [Analyser] \n GO \n DECLARE	@return_value int \n EXEC	@return_value = [wireless].[spGetAnalyserAllComm] ");
					queryToAnalyserComm.append("\n @varLoggedOnUserID = N'" + userId + "',");
					queryToAnalyserComm.append("\n @varUserList = N'" + userList + "',");
					queryToAnalyserComm.append("\n @varOrderBy = N'" + orderBy + "',");
					queryToAnalyserComm.append("\n @varSearchString = N'" + searchString + "'");
					queryToAnalyserComm.append("\n @varCompanyCode = N'" + companyCode + "'");
					queryToAnalyserComm.append("\n SELECT	'Return Value' = @return_value \n GO ");
					System.out.println("Query :: " + queryToAnalyserComm.toString());
					cstmt = connection.prepareCall("{call " + FacesUtils.SCHEMA_WIRELESS + ".spGetAnalyserAllComm(?, ?, ?, ?, ?)}");
					cstmt.setString("varLoggedOnUserID", userId);
					cstmt.setString("varUserList", userList);
					cstmt.setString("varOrderBy", orderBy);
					cstmt.setString("varSearchString", searchString);
					cstmt.setString("varCompanyCode", companyCode);
				}
				catch (Exception e)
				{
					System.out.println("Exception in " + USERS_LIST + " of AnalyseCommissionPersonRepositoryImpl " + e.getLocalizedMessage());
				}
				
			}
			else if (action.equals(IRC_USERS_LIST))
			{
				
				StringBuilder queryToAnalyserComm = new StringBuilder();
				queryToAnalyserComm.append(
				        "USE [Analyser] \n GO \n DECLARE	@return_value int \n EXEC	@return_value = [wireless].[spGetIRCCommissionPersons] ");
				queryToAnalyserComm.append("\n @varLoggedOnUserID = N'" + userId + "',");
				queryToAnalyserComm.append("\n @varUserList = N'" + userList + "',");
				queryToAnalyserComm.append("\n @varOrderBy = N'" + orderBy + "',");
				queryToAnalyserComm.append("\n @varSearchString = N'" + searchString + "'");
				queryToAnalyserComm.append("\n SELECT	'Return Value' = @return_value \n GO ");
				System.out.println("Query :: " + queryToAnalyserComm.toString());
				
				cstmt = connection.prepareCall("{call " + FacesUtils.SCHEMA_WIRELESS + ".spGetIRCCommissionPersons(?, ?, ?, ?)}");
				cstmt.setString("varLoggedOnUserID", userId);
				cstmt.setString("varUserList", userList);
				cstmt.setString("varOrderBy", orderBy);
				cstmt.setString("varSearchString", searchString);
			}
			else if (action.equals(ACTIVE_COMMISSION_PERSONS_LIST))
			{
				try
				{	
					StringBuilder queryToAnalyserComm = new StringBuilder();
					queryToAnalyserComm.append(
					        "USE [Analyser] \n GO \n DECLARE	@return_value int \n EXEC	@return_value = [wireless].[spGetActiveCommissionPersons] ");
					queryToAnalyserComm.append("\n @varLoggedInUserId = N'" + userId + "',");
					queryToAnalyserComm.append("\n @varRoleName = N'" + userList + "'");
					queryToAnalyserComm.append("\n @varCompanyCode = N'" + companyCode + "'");
					queryToAnalyserComm.append("\n SELECT	'Return Value' = @return_value \n GO ");
					System.out.println("Query :: " + queryToAnalyserComm.toString());
					cstmt = connection.prepareCall("{call " + FacesUtils.SCHEMA_WIRELESS + ".spGetActiveCommissionPersons(?, ?, ?)}");
					cstmt.setString("varLoggedInUserId", userId);
					cstmt.setString("varRoleName", userList);
					cstmt.setString("varCompanyCode", companyCode);
				}
				catch (Exception e)
				{
					System.out.println("Exception in " + ACTIVE_COMMISSION_PERSONS_LIST + " of AnalyseCommissionPersonRepositoryImpl "
					        + e.getLocalizedMessage());
				}
				
			}
			else if (action.equals(ACTIVE_USERS_LIST))
			{
				try
				{
					StringBuilder queryToActiveUsers = new StringBuilder();
					queryToActiveUsers
					        .append("USE [Analyser] \n GO \n DECLARE	@return_value int \n EXEC	@return_value = ["+ FacesUtils.SCHEMA_WIRELESS +"].[spGetUserListForCommissionPerson] ");
					queryToActiveUsers.append("\n @varLoggedInUserId = N'" + userId + "'");
					queryToActiveUsers.append("\n @varCompanyCode = N'" + companyCode + "'");
					queryToActiveUsers.append("\n SELECT	'Return Value' = @return_value \n GO ");
					System.out.println("Query :: " + queryToActiveUsers.toString());
					cstmt = connection.prepareCall("{call " + FacesUtils.SCHEMA_WIRELESS + ".spGetUserListForCommissionPerson(?, ?)}");
					cstmt.setString("varLoggedInUserId", userId);
					cstmt.setString("varCompanyCode", companyCode);
				}
				catch (Exception e)
				{
					System.out.println("Exception in " + ACTIVE_USERS_LIST_LIAISON + " of AnalyseCommissionPersonRepositoryImpl " + e.getLocalizedMessage());
				}
				
			}
			
			else  if (action.equals(COMMISSION_PERSON_INFO)){
				try
				{
					StringBuilder queryToAnalyserCommissionPerson = new StringBuilder();
					queryToAnalyserCommissionPerson.append(
					        "USE [Analyser] \n GO \n DECLARE	@return_value int \n EXEC	@return_value = [wireless].[spGetAnalyserCommissionPerson] ");
					queryToAnalyserCommissionPerson.append("\n @varLoggedOnUserID = N'" + userId + "',");
					queryToAnalyserCommissionPerson.append("\n @varCommissionPersonId = N'" + commissionPersonId + "'");
					queryToAnalyserCommissionPerson.append("\n SELECT	'Return Value' = @return_value \n GO ");
					System.out.println("Query :: " + queryToAnalyserCommissionPerson.toString());
					cstmt = connection.prepareCall("{call " + FacesUtils.SCHEMA_WIRELESS + ".spGetAnalyserCommissionPerson(?, ?)}");
					cstmt.setString("varLoggedOnUserID", userId);
					cstmt.setString("varCommissionPersonId", commissionPersonId);
				}
				catch (Exception e)
				{
					System.out.println("Exception in " + USERS_LIST + " of AnalyseCommissionPersonRepositoryImpl " + e.getLocalizedMessage());
				}
			} else  if (action.equals(UPDATE)){
				try
				{
					StringBuilder queryToAnalyserCommissionPerson = new StringBuilder();
					queryToAnalyserCommissionPerson.append("USE [Analyser]\n "
							+ "\n  GO"
							+ "\n  DECLARE	@return_value int"
							+ "\n  EXEC	@return_value = [wireless].[spUpdateCommissionPersonAttributes]"
							+ "\n  @varLoggedOnUserID = N'"+userId+"',"
							+ "\n  @varCommissionPersonName = N'"+personName+"',"
							+ "\n  @varCommissionPersonUserId = N'"+commissionPersonUserId+"',"
							+ "\n  @varRole = N'"+commissionPersonRole+"',"
							+ "\n  @varClassification = N'"+commissionPersonClassification+"',"							
							+ "\n  @varCompanyCode = N'"+companyCode+"'"
							+ "\n  SELECT	'Return Value' = @return_value"
							+ "\n  GO");
					System.out.println("Query :: " + queryToAnalyserCommissionPerson.toString());
					cstmt = connection.prepareCall("{call " + FacesUtils.SCHEMA_WIRELESS + ".spUpdateCommissionPersonAttributes(?, ?, ?, ?, ?, ?)}");
					cstmt.setString("varLoggedOnUserID", userId);
					cstmt.setString("varCommissionPersonName", personName);
					cstmt.setString("varCommissionPersonUserId", commissionPersonUserId);
					cstmt.setString("varRole", commissionPersonRole);
					cstmt.setString("varClassification", commissionPersonClassification);
					cstmt.setString("varCompanyCode", companyCode);
				}
				catch (Exception e)
				{
					System.out.println("Exception in " + UPDATE + " of AnalyseCommissionPersonRepositoryImpl " + e.getLocalizedMessage());
				}
			}
			ResultSet rs = cstmt.executeQuery();
			
			try
			{
				if (action.equalsIgnoreCase(ACTIVE_COMMISSION_PERSONS_LIST))
				{
					ResultSetMetaData rsMeta;
					try
					{
						rsMeta = rs.getMetaData();
						int iRowCount = rsMeta.getColumnCount();
						namesList = new ArrayList<String>();
						userDtoList = new ArrayList<UserDTO>();
						UserDTO userDTO = null;
						while (rs.next())
						{
							if (cpg)
							{
								userDTO = new UserDTO();
							}
							for (int i = 0; i < iRowCount; i++)
							{
								String columnName = rsMeta.getColumnLabel(i + 1);
								try
								{
									if (columnName.equalsIgnoreCase("PersonName"))
									{
										if (cpg)
										{
											userDTO.setName((String) getRSColumnValue(rs, rsMeta, i + 1));
										}
										else
										{
											namesList.add((String) getRSColumnValue(rs, rsMeta, i + 1));
										}
									}
									if (cpg)
									{
										if (columnName.equalsIgnoreCase("USER_ID"))
										{
											userDTO.setUserId((String) getRSColumnValue(rs, rsMeta, i + 1));
										}
										else if (columnName.equalsIgnoreCase("ADPCode"))
										{
											userDTO.setApdCode((String) getRSColumnValue(rs, rsMeta, i + 1));
										}
									}
								}
								catch (Exception ex)
								{
									continue;
								}
							}
							userDtoList.add(userDTO);
						}
					}
					catch (SQLException e)
					{
						String sz = "An error occured while attempting to parse resultset column information from the database for AnalyserCommisionPersonWork.  Error type :  "
						        + e.getSQLState() + ".  Error Code : " + e.getErrorCode();
						Exception err = new Exception(sz);
						throw err;
					}
				}
				else if (action.equalsIgnoreCase(ACTIVE_USERS_LIST) || action.equalsIgnoreCase(ACTIVE_USERS_LIST_LIAISON))
				{
					ResultSetMetaData rsMeta;
					try
					{
						rsMeta = rs.getMetaData();
						int iRowCount = rsMeta.getColumnCount();
						userDtoList = new ArrayList<UserDTO>();
						UserDTO userDTO = null;
						while (rs.next())
						{
							userDTO = new UserDTO();
							for (int i = 0; i < iRowCount; i++)
							{
								String columnName = rsMeta.getColumnLabel(i + 1);
								try
								{
									if (columnName.equalsIgnoreCase("Name"))
									{
										userDTO.setName((String) getRSColumnValue(rs, rsMeta, i + 1));
									}
									else if (columnName.equalsIgnoreCase("USER_ID"))
									{
										userDTO.setUserId((String) getRSColumnValue(rs, rsMeta, i + 1));
									}
									else if (columnName.equalsIgnoreCase("APDCode"))
									{
										userDTO.setApdCode((String) getRSColumnValue(rs, rsMeta, i + 1));
									}
								}
								catch (Exception ex)
								{
									continue;
								}
							}
							userDtoList.add(userDTO);
						}
					}
					catch (SQLException e)
					{
						String sz = "An error occured while attempting to parse resultset column information from the database for AnalyserCommisionPersonWork.  Error type :  "
						        + e.getSQLState() + ".  Error Code : " + e.getErrorCode();
						Exception err = new Exception(sz);
						throw err;
					}
				} else if (action.equalsIgnoreCase(COMMISSION_PERSON_INFO)) {
					try
					{
						person = new AnalyserCommisionPerson();
						if (person.getId() == null)
						{
							person.setId(new AnalyserCommisionPersonPK());
						}
						if (rs != null && rs.next())
						{
							try
							{								
								person.setPersonName(rs.getString("PersonName"));
								person.getId().setUserId(rs.getString("USER_ID"));
								//person.setAdpCode(rs.getString("ADPCode"));
								person.setCommissionPersonRole(rs.getString("CommissionPersonRole"));
								person.setCommissionPersonClassification(rs.getString("CommissionPersonClassification"));
								person.setCompanyCode(rs.getString("CompanyCode"));
							}
							catch (SQLException ex)
							{
								String sz = "An SQL error occured while attempting to parse resultset from the database for getting analyser commission person.  Error type :  "
								        + ex.getSQLState() + ".  Error Code : " + ex.getErrorCode();
								Exception err = new Exception(sz);
								throw err;
							}
							catch (Exception ex)
							{
								String sz = "An exception occured while attempting to get analyser commission person.  "+ex.getLocalizedMessage();
								Exception err = new Exception(sz);
								throw err;
							}
						}
					}
					catch (SQLException e)
					{
						String sz = "An error occured while attempting to parse resultset column information from the database for AnalyserCommisionPersonWork.  Error type :  "
						        + e.getSQLState() + ".  Error Code : " + e.getErrorCode();
						Exception err = new Exception(sz);
						throw err;
					}
				} else if(action.equals(UPDATE)){
					if (rs.next())
					{
						status = "" + rs.getInt(1);
					}
				}
				else
				{
					list = getRSRowInfo(rs);
					System.out.println(list.size());
				}
			}
			catch (Exception e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			finally
			{
				if (rs != null) rs.close();
				if (cstmt != null) cstmt.close();
			}
			
		}
		
		private List<AnalyserCommisionPerson> getRSRowInfo(ResultSet rs) throws Exception
		{
			ResultSetMetaData rsMeta;
			try
			{
				rsMeta = rs.getMetaData();
				int iRowCount = rsMeta.getColumnCount();
				while (rs.next())
				{
					person = new AnalyserCommisionPerson();
					if (person.getId() == null)
					{
						person.setId(new AnalyserCommisionPersonPK());
					}
					for (int i = 0; i < iRowCount; i++)
					{
						String columnName = rsMeta.getColumnLabel(i + 1);
						// put values into the array list
						try
						{
							if (columnName.equalsIgnoreCase("PersonName"))
							{
								person.setPersonName((String) getRSColumnValue(rs, rsMeta, i + 1));
							}
							else if (columnName.equalsIgnoreCase("Updated_By"))
							{
								person.setUpdatedBy((String) getRSColumnValue(rs, rsMeta, i + 1));
							}
							else if (columnName.equalsIgnoreCase("User_id"))
							{
								person.getId().setUserId((String) getRSColumnValue(rs, rsMeta, i + 1));
							}
							else if (columnName.equalsIgnoreCase("Status"))
							{
								person.setStatus((Integer) getRSColumnValue(rs, rsMeta, i + 1));
							}
							else if (columnName.equalsIgnoreCase("ADPCode"))
							{
								person.setAdpCode((String) getRSColumnValue(rs, rsMeta, i + 1));
							}
							else if (columnName.equalsIgnoreCase("Updated_Date"))
							{
								person.setUpdatedDate((Timestamp) getRSColumnValue(rs, rsMeta, i + 1));
							}
							else if (columnName.equalsIgnoreCase("CompanyCode"))
							{
								person.setCompanyCode((String) getRSColumnValue(rs, rsMeta, i + 1));
							}
						}
						catch (Exception ex)
						{
							continue;
						}
					}
					list.add(person);
					
				}
				return list;
			}
			catch (SQLException e)
			{
				String sz = "An error occured while attempting to parse resultset column information from the database for AnalyserCommisionPersonWork.  Error type :  "
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
		public List<AnalyserCommisionPerson> getList()
		{
			return list;
		}
		
		/**
		 * @return the namesList
		 */
		public List<String> getNamesList()
		{
			return namesList;
		}
		
		public List<UserDTO> getUserDtoList()
		{
			return userDtoList;
		}
		
		public AnalyserCommisionPerson getPerson(){
			return person;
		}

		/**
		 * @return the status
		 */
		public String getStatus()
		{
			return status;
		}
		
	}
	
	public String addUpdateAnalyserComm(String commPerson, String commPersonOld, String userId, int actionType)
	{
		Session session = entityManager.unwrap(Session.class);
		AnalyserMiscelleneousWork work = new AnalyserMiscelleneousWork(commPerson, commPersonOld, userId, actionType, "ADD");
		session.doWork(work);
		String result = work.getResult();
		return result;
	}
	
	@Override
	public Map<String, String> getCommPersonList(String userId, String type)
	{
		Session session = entityManager.unwrap(Session.class);
		AnalyserMiscelleneousWork work = new AnalyserMiscelleneousWork(userId, type, IRC_USERS_LIST);
		session.doWork(work);
		Map<String, String> list = work.getList();
		return list;
	}	
	
	private static class AnalyserMiscelleneousWork implements Work
	{
		
		private Logger logger = LoggerFactory.getLogger(getClass());
		
		String commPerson;
		String commPersonOld;
		String userId;
		int actionType;
		String userType;
		
		String result = "";
		
		String action = "";
		
		Map<String, String> list;
		
		public AnalyserMiscelleneousWork(String commPerson, String commPersonOld, String userId, int actionType, String action)
		{
			super();
			this.commPerson = commPerson;
			this.commPersonOld = commPersonOld;
			this.userId = userId;
			this.actionType = actionType;
			this.action = action;
		}
		
		public AnalyserMiscelleneousWork(String userId, String userType, String action)
		{
			super();
			this.userId = userId;
			this.userType = userType;
			this.action = action;
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
					String query = "";
					
					if (action.equals(ADD))
					{
						query = "{call " + FacesUtils.SCHEMA_WIRELESS + ".spAddUpdateAnalyserComm(" + actionType + ",'" + userId + "','"
						        + Util.removeSingleQuote(commPerson) + "','" + Util.removeSingleQuote(commPersonOld) + "')}";
					}
					else if (action.equals(IRC_USERS_LIST))
					{
						query = "{call " + FacesUtils.SCHEMA_WIRELESS + ".spAnalyzerCommissionPersons('" + userId + "','" + userType + "')}";
					}
					
					System.out.println("Executing Query in addUpdateAnalyserComm " + query);
					
					logger.info("AnalyserMiscelleneousWork Query is : " + query);
					
					callStmt = connection.prepareCall(query);
					rs = callStmt.executeQuery();
					if (rs != null)
					{
						if (action.equals(ADD))
						{
							while (rs.next())
							{
								result = "" + rs.getInt(1);
							}
						}
						else if (action.equals(IRC_USERS_LIST))
						{
							while (rs.next())
							{
								if (rs.getRow() == 1)
								{
									list = new LinkedHashMap<String, String>();
								}
								list.put(rs.getString(1), rs.getString(1));
							}
						}
						callStmt.close();
						rs.close();
						callStmt = null;
						rs = null;
					}
					else
					{
						throw new Exception("AnalyserComm could not be inserted");
					}
				}
				else
				{
					if (action.equals(ADD))
					{
						throw new Exception("Connection is null");
					}
					else if (action.equals(IRC_USERS_LIST))
					{
						list = new HashMap<String, String>();
					}
				}
			}
			catch (Exception ex)
			{
				System.out.println("Exception in addUpdateAnalyserComm method");
				ex.printStackTrace();
				if (action.equals(ADD))
				{
					result = "1";
				}
				else if (action.equals(IRC_USERS_LIST))
				{
					list = new HashMap<String, String>();
				}
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
		
		/**
		 * @return the list
		 */
		public Map<String, String> getList()
		{
			return list;
		}
	}
	
	@Override
	public String changeCommissionPersonStatus(String userId, String updatedBy, String personName, Integer status)
	{
		Connection con = null;
		CallableStatement callStmt = null;
		ResultSet rs = null;
		String result = "0";
		int res = 0;
		try
		{
			con = DBConnection.getConnection();
			if (con != null)
			{
				String query = null;
				query = "{call " + FacesUtils.SCHEMA_WIRELESS + ".spSetStatusAnalyserCommisionPersons('" + userId + "', '" + updatedBy + "', '"
				        + personName + "', " + status + ")}";
				
				System.out.println("Executing Query in spSetStatusAnalyserCommisionPersons -- :  " + query);
				logger.debug("Executing Query in spSetStatusAnalyserCommisionPersons -- :  " + query);
				System.out.println("In DBHelper User ID = " + userId);
				System.out.println("In DBHelper Person Name = " + personName);
				System.out.println("In DBHelper Status = " + status);
				callStmt = con.prepareCall(query);
				res = callStmt.executeUpdate();
				
				if (res != 0) // return 0 if success or "" otherwise
				    result = "";
				
				con.close();
				callStmt.close();
				if (rs != null)
				{
					rs.close();
				}
				con = null;
				callStmt = null;
				rs = null;
				
			}
			else
			{
				throw new Exception("Connection is null");
			}
		}
		catch (Exception ex)
		{
			System.out.println("Exception in changeClientAddressStatus method of AnalyserClientRepositoryImpl");
			ex.printStackTrace();
			return "System Error";
		}
		finally
		{
			DBConnection.closeConnection(con, callStmt, rs);
		}
		
		return result;
	}
	
	@Override
	public List<UserDTO> spGetActiveUsers(String userId, String companyCode)
	{
		Session session = entityManager.unwrap(Session.class);
		AnalyserCommisionPersonWork work = new AnalyserCommisionPersonWork(userId, "", "", "", ACTIVE_USERS_LIST, companyCode);
		session.doWork(work);
		List<UserDTO> list = work.getUserDtoList();
		return list;
	}
	
	@Override
	public List<UserDTO> spGetActiveCommissionPersons(String userId, String companyCode)
	{
		Session session = entityManager.unwrap(Session.class);
		AnalyserCommisionPersonWork work = new AnalyserCommisionPersonWork(userId, "", "", "", ACTIVE_COMMISSION_PERSONS_LIST, true, companyCode);
		session.doWork(work);
		List<UserDTO> list = work.getUserDtoList();
		return list;
	}
	
	@Override
	public List<CommissionPersonGradeDTO> spGetCommissionPersonGrade(String userId)
	{
		Session session = entityManager.unwrap(Session.class);
		CommissionPersonGradeWork work = new CommissionPersonGradeWork(userId);
		session.doWork(work);
		return work.getList();
	}
	
	private static class CommissionPersonGradeWork implements Work
	{
		private List<CommissionPersonGradeDTO> list;
		CommissionPersonGradeDTO person;
		String userId;
		CallableStatement cstmt = null;
		ResultSet rs = null;
		
		public CommissionPersonGradeWork(String userId)
		{
			super();
			this.userId = userId;
		}
		
		@Override
		public void execute(Connection connection) throws SQLException
		{
			list = new ArrayList<CommissionPersonGradeDTO>(0);
			try
			{
				StringBuilder queryToAnalyserComm = new StringBuilder();
				queryToAnalyserComm.append(
				        "USE [Analyser] \n GO \n DECLARE	@return_value int \n EXEC	@return_value = [wireless].[spGetCommissionPersonGrade] ");
				queryToAnalyserComm.append("\n @varLoggedInUserId = N'" + userId + "'");
				queryToAnalyserComm.append("\n SELECT	'Return Value' = @return_value \n GO ");
				System.out.println("Query :: " + queryToAnalyserComm.toString());
				cstmt = connection.prepareCall("{call " + FacesUtils.SCHEMA_WIRELESS + ".spGetCommissionPersonGrade(?)}");
				cstmt.setString("varLoggedInUserId", userId);
				rs = cstmt.executeQuery();
				list = getRSRowInfo(rs);
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
			finally
			{
				if (rs != null) rs.close();
				if (cstmt != null) cstmt.close();
			}
			
		}
		
		private List<CommissionPersonGradeDTO> getRSRowInfo(ResultSet rs) throws Exception
		{
			ResultSetMetaData rsMeta;
			try
			{
				rsMeta = rs.getMetaData();
				int iRowCount = rsMeta.getColumnCount();
				while (rs.next())
				{
					person = new CommissionPersonGradeDTO();
					for (int i = 0; i < iRowCount; i++)
					{
						String columnName = rsMeta.getColumnLabel(i + 1);
						// put values into the array list
						try
						{
							if (columnName.equalsIgnoreCase("CommissionPersonGradeId"))
							{
								person.setCommissionPersonGradeId((Integer) getRSColumnValue(rs, rsMeta, i + 1));
							}
							else if (columnName.equalsIgnoreCase("CommissionPersonEmplId"))
							{
								person.setCommissionPersonEmplId((String) getRSColumnValue(rs, rsMeta, i + 1));
							}
							else if (columnName.equalsIgnoreCase("CommissionPersonName"))
							{
								person.setCommissionPersonName((String) getRSColumnValue(rs, rsMeta, i + 1));
							}
							else if (columnName.equalsIgnoreCase("CommissionPersonEmail"))
							{
								person.setCommissionPersonEmail((String) getRSColumnValue(rs, rsMeta, i + 1));
							}
							else if (columnName.equalsIgnoreCase("EffectiveDate"))
							{
								person.setEffectiveDate((java.sql.Timestamp) getRSColumnValue(rs, rsMeta, i + 1));
							}
							else if (columnName.equalsIgnoreCase("ScoreCardGrade"))
							{
								person.setScoreCardGrade((String) getRSColumnValue(rs, rsMeta, i + 1));
							}
							else if (columnName.equalsIgnoreCase("EntryDateTime"))
							{
								person.setEntryDateTime((java.sql.Timestamp) getRSColumnValue(rs, rsMeta, i + 1));
							}
						}
						catch (Exception ex)
						{
							System.out.println("Exception :: " + ex.getMessage());
							continue;
						}
					}
					list.add(person);
				}
				return list;
			}
			catch (SQLException e)
			{
				String sz = "An error occured while attempting to parse resultset column information from the database for AnalyserCommisionPersonWork.  Error type :  "
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
		
		public List<CommissionPersonGradeDTO> getList()
		{
			return list;
		}
		
	}
	
	@Override
	public String spAddUpdateCommissionPersonGrade(String userId, CommissionPersonGradeDTO personDto, Integer actionType)
	{
		String response = "";
		Session session = entityManager.unwrap(Session.class);
		CommPersonGradeWork work = new CommPersonGradeWork(userId, personDto, actionType);
		session.doWork(work);
		response = work.getStatus();
		return response;
	}
	
	private static class CommPersonGradeWork implements Work
	{
		private Logger logger = LoggerFactory.getLogger(getClass());
		String status;
		CommissionPersonGradeDTO personDto;
		String userId;
		Integer actionType;
		
		public CommPersonGradeWork(String userId, CommissionPersonGradeDTO personDto, Integer actionType)
		{
			super();
			this.personDto = personDto;
			this.userId = userId;
			this.actionType = actionType;
		}
		
		@Override
		public void execute(Connection connection) throws SQLException
		{
			status = "0";
			CallableStatement callStmt = null;
			ResultSet rs = null;
			try
			{
				String insertStoreProc = "{call " + FacesUtils.SCHEMA_WIRELESS + ".spAddUpdateCommissionPersonGrade(?,?,?,?,?,?,?,?,?)}";
				StringBuilder queryToAddUpdateGrade = new StringBuilder();
				queryToAddUpdateGrade.append("USE [Analyser] GO DECLARE	@return_value int EXEC	@return_value = [wireless].[spAddUpdateCommissionPersonGrade]");
				queryToAddUpdateGrade.append("@intActionType = " + actionType + ",");
				queryToAddUpdateGrade.append("@varLoggedUserID = N'" + userId + "',");
				queryToAddUpdateGrade.append("@intCommId = " + personDto.getCommissionPersonGradeId() + ",");
				queryToAddUpdateGrade.append("@varEmplId = N'" + personDto.getCommissionPersonEmplId() + "',");
				queryToAddUpdateGrade.append("@varPersonName = N'" + personDto.getCommissionPersonName() + "',");
				queryToAddUpdateGrade.append("@varPersonEmail = N'" + personDto.getCommissionPersonEmail() + "',");
				queryToAddUpdateGrade.append("@varEffectiveDate = " + personDto.getEffectiveDate() + ",");
				queryToAddUpdateGrade.append("@varScoreCardGrade = N'" + personDto.getScoreCardGrade() + "',");
				queryToAddUpdateGrade.append("@varEntryDateTime = " + personDto.getEntryDateTime() + "");
				queryToAddUpdateGrade.append("SELECT	'Return Value' = @return_value GO ");
				System.out.println(queryToAddUpdateGrade.toString());
				logger.debug("Query to spAddUpdateCommissionPersonGrade is : " + queryToAddUpdateGrade.toString());
				
				callStmt = connection.prepareCall(insertStoreProc);
				
				// @intActionType
				callStmt.setInt(1, actionType);
				
				// @varLoggedUserID
				callStmt.setString(2, userId);
				
				// @intCommId
				callStmt.setInt(3, personDto.getCommissionPersonGradeId());
				
				// @varEmplId
				callStmt.setString(4, personDto.getCommissionPersonEmplId());
				
				// @varPersonName
				callStmt.setString(5, personDto.getCommissionPersonName());
				
				// @varPersonEmail
				callStmt.setString(6, personDto.getCommissionPersonEmail());
				
				// @varEffectiveDate
				callStmt.setTimestamp(7, personDto.getEffectiveDate());
				
				// @varScoreCardGrade
				callStmt.setString(8, personDto.getScoreCardGrade());
				
				// @varEntryDateTime
				callStmt.setTimestamp(9, personDto.getEntryDateTime());
				
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
		
		public String getStatus()
		{
			return status;
		}
		
	}

	@Override
	public AnalyserCommisionPerson getAnalyserCommissionPerson(String userId, String commissionPersonId)
	{
		Session session = entityManager.unwrap(Session.class);
		AnalyserCommisionPersonWork work = new AnalyserCommisionPersonWork(userId, commissionPersonId, COMMISSION_PERSON_INFO);
		session.doWork(work);
		AnalyserCommisionPerson person = work.getPerson();
		return person;
	}
	
	@Override
	public Boolean updateAnalyserCommissionPerson(String userId,String personName,String commissionPersonUserId,
			String commissionPersonRole,String commissionPersonClassification, String companyCode){
		Session session = entityManager.unwrap(Session.class);
		AnalyserCommisionPersonWork work = new AnalyserCommisionPersonWork(userId,personName,commissionPersonUserId,
				commissionPersonRole,commissionPersonClassification,UPDATE, companyCode);
		session.doWork(work);
		String status = work.getStatus();
		if(status.equals("1")){
			return true;
		}
		return false;
	}
	

	@Override
	public List<AnalyserCommisionPersonDTO> getCommisionPersonList(String userId, String companyCode, String roleCode, String classificationCode) 
	{
		Session session = entityManager.unwrap(Session.class);
		AnalyserCommisionPersonRepositoryWork work = new AnalyserCommisionPersonRepositoryWork(userId, companyCode, roleCode, classificationCode);	
		session.doWork(work);
		List<AnalyserCommisionPersonDTO> list = work.getList();
		return list;
	}
	
	@Override
	public List<AnalyserCommisionPersonDTO> getMDCommissionPersonList(String userId, String companyCode) 
	{
		return getCommisionPersonList( userId, companyCode, Constants.STRING_CONSTANT_ALL, MD);
	}
	
	@Override
	public List<AnalyserCommisionPersonDTO> getAECommissionPersonList(String userId, String companyCode) 
	{
		return getCommisionPersonList( userId, companyCode, AE, Constants.STRING_CONSTANT_ALL);

	}
	
	@Override
	public List<AnalyserCommisionPersonDTO> getRecruiterCommissionPersonList(String userId, String companyCode) 
	{
		return getCommisionPersonList( userId, companyCode, REC, Constants.STRING_CONSTANT_ALL);
	}
	@Override
	public List<AnalyserCommisionPersonDTO> getAllCommissionPersonList(String userId, String companyCode) 
	{
		return getCommisionPersonList( userId, companyCode, Constants.STRING_CONSTANT_ALL, Constants.STRING_CONSTANT_ALL);

	}
	@Override
	public List<AnalyserCommisionPersonDTO> getMSPCommissionPersonList(String userId, String companyCode) 
	{
		return getCommisionPersonList( userId, companyCode, Constants.STRING_CONSTANT_ALL, AGM);

	}
	
	private static class AnalyserCommisionPersonRepositoryWork implements Work {
		public Logger logger = LoggerFactory.getLogger(getClass());
		private List<AnalyserCommisionPersonDTO> list;
		private AnalyserCommisionPersonDTO analyserCommisionPersonDTO;
		String userId;
		String companyCode;
		String classificationCode;
		String roleCode;
		CallableStatement cstmt = null;
		ResultSet rs = null;

		/**
		 * @param userId
		 */
		public AnalyserCommisionPersonRepositoryWork(String userId, String companyCode, String roleCode, String classificationCode) {
			super();
			this.userId = userId;
			this.companyCode=companyCode;
			this.roleCode=roleCode;
			this.classificationCode=classificationCode;
		}

		@Override
		public void execute(Connection connection) throws SQLException {

			list = new ArrayList<AnalyserCommisionPersonDTO>();
			analyserCommisionPersonDTO = new AnalyserCommisionPersonDTO();

				String query = "{call " + FacesUtils.SCHEMA_DBO
						+ ".spGetCommissionPersonList('" + userId + "','" + companyCode + "','"+roleCode+"', '"+classificationCode+"')}";

				System.out.println("Query in AnalyserCommisionPersonRepositoryWork " + query);

				logger.debug("Query in AnalyserCommisionPersonRepositoryWork " + query);

				cstmt = connection.prepareCall(query);
				rs = cstmt.executeQuery();

			try {
					if (rs != null) 
					{
						while (rs.next()) 
						{
							analyserCommisionPersonDTO.setPersonName(rs.getString("PersonName"));														
							list.add(analyserCommisionPersonDTO);
							analyserCommisionPersonDTO = new AnalyserCommisionPersonDTO();
						}
					} 
					else 
					{
						list = new ArrayList<AnalyserCommisionPersonDTO>();
					}
					System.out.println("List size in AnalyserCommisionPersonRepositoryWork " + list.size());
					logger.debug("List size in AnalyserCommisionPersonRepositoryWork " + list.size());
			} 
			catch (Exception e) 
			{
				System.out.println("Exception in AnalyserCommisionPersonRepositoryWork --> execute.");
				logger.debug("Exception in AnalyserCommisionPersonRepositoryWork --> execute.");
				e.printStackTrace();
			} 
			finally 
			{
				if (rs != null)
					rs.close();
				if (cstmt != null)
					cstmt.close();
			}
		}

		/**
		 * @return the list
		 */
		public List<AnalyserCommisionPersonDTO> getList() {
			return list;
		}

	}
}