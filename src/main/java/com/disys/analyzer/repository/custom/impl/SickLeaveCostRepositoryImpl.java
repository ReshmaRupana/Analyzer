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
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.disys.analyzer.config.util.FacesUtils;
import com.disys.analyzer.model.SickLeaveCost;
import com.disys.analyzer.model.dto.StatesDTO;
import com.disys.analyzer.repository.custom.SickLeaveCostRepositoryCustom;

@Repository
@Transactional(readOnly = true)
public class SickLeaveCostRepositoryImpl implements SickLeaveCostRepositoryCustom
{
	
	public Logger logger = LoggerFactory.getLogger(getClass());
	
	@PersistenceContext EntityManager entityManager;
	
	@Override
	public List<StatesDTO> usStateData(String userId)
	{
		Session session = entityManager.unwrap(Session.class);
		USStateListWork work = new USStateListWork(userId);
		session.doWork(work);
		return work.getList();
	}
	
	private static class USStateListWork implements Work
	{
		private Logger logger = LoggerFactory.getLogger(getClass());
		String userId;
		String result = "0";
		List<StatesDTO> list;
		StatesDTO statesDTO;
		
		public USStateListWork(String userId)
		{
			super();
			this.userId = userId;
			this.list = new ArrayList<StatesDTO>(0);
		}
		
		@Override
		public void execute(Connection connection) throws SQLException
		{
			ResultSet rs = null;
			CallableStatement cstmt = null;
			try
			{
				cstmt = connection.prepareCall("{call " + FacesUtils.SCHEMA_WIRELESS + ".spGetUSStates(?)}");
				cstmt.setString("varLoggedInUserId", userId);
				rs = cstmt.executeQuery();
				list = getRSRowInfo(rs);
			}
			catch (Exception e)
			{
				logger.error(e.getMessage());
				logger.debug(e.getMessage());
				e.printStackTrace();
			}
			finally
			{
				if (rs != null) rs.close();
				if (cstmt != null) cstmt.close();
			}
		}
		
		private List<StatesDTO> getRSRowInfo(ResultSet rs) throws Exception
		{
			ResultSetMetaData rsMeta;
			try
			{
				
				rsMeta = rs.getMetaData();
				int iRowCount = rsMeta.getColumnCount();
				String label = null, value = null;
				while (rs.next())
				{
					statesDTO = new StatesDTO();
					for (int i = 0; i < iRowCount; i++)
					{
						String columnName = rsMeta.getColumnLabel(i + 1);
						// put values into the array list
						try
						{
							if (columnName.equalsIgnoreCase("StateName"))
							{
								statesDTO.setStateName((String) getRSColumnValue(rs, rsMeta, i + 1));
							}
							else if (columnName.equalsIgnoreCase("State"))
							{
								statesDTO.setStateCode((String) getRSColumnValue(rs, rsMeta, i + 1));
							}
							else if (columnName.equalsIgnoreCase("SUI"))
							{
								statesDTO.setSui((String) getRSColumnValue(rs, rsMeta, i + 1));
							}
						}
						catch (Exception ex)
						{
							continue;
						}
					}
					list.add(statesDTO);
					
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
		
		public String getResult()
		{
			return result;
		}
		
		public List<StatesDTO> getList()
		{
			return list;
		}
	}

	@Override
	public List<SickLeaveCost> sickLeaveData(String userId)
	{
		Session session = entityManager.unwrap(Session.class);
		SickLeaveDataWork work = new SickLeaveDataWork(userId);
		session.doWork(work);
		return work.getList();
	}
	
	private static class SickLeaveDataWork implements Work
	{
		private Logger logger = LoggerFactory.getLogger(getClass());
		String userId;
		String result = "0";
		List<SickLeaveCost> list;
		SickLeaveCost sickLeaveCost;
		
		public SickLeaveDataWork(String userId)
		{
			super();
			this.userId = userId;
			this.list = new ArrayList<SickLeaveCost>(0);
		}
		
		@Override
		public void execute(Connection connection) throws SQLException
		{
			ResultSet rs = null;
			CallableStatement cstmt = null;
			try
			{
				cstmt = connection.prepareCall("{call " + FacesUtils.SCHEMA_WIRELESS + ".spGetSickLeaveData(?)}");
				cstmt.setString("varLoggedInUserId", userId);
				rs = cstmt.executeQuery();
				list = getRSRowInfo(rs);
			}
			catch (Exception e)
			{
				logger.error(e.getMessage());
				logger.debug(e.getMessage());
				e.printStackTrace();
			}
			finally
			{
				if (rs != null) rs.close();
				if (cstmt != null) cstmt.close();
			}
		}
		
		private List<SickLeaveCost> getRSRowInfo(ResultSet rs) throws Exception
		{
			ResultSetMetaData rsMeta;
			try
			{
				
				rsMeta = rs.getMetaData();
				int iRowCount = rsMeta.getColumnCount();
				String label = null, value = null;
				while (rs.next())
				{
					sickLeaveCost = new SickLeaveCost();
					for (int i = 0; i < iRowCount; i++)
					{
						String columnName = rsMeta.getColumnLabel(i + 1);
						// put values into the array list
						try
						{
							if (columnName.equalsIgnoreCase("WorksiteStateCode"))
							{
								sickLeaveCost.setWorksiteStateCode((String) getRSColumnValue(rs, rsMeta, i + 1));
							}
							else if (columnName.equalsIgnoreCase("WorksiteStateDescription"))
							{
								sickLeaveCost.setWorksiteStateDescription((String) getRSColumnValue(rs, rsMeta, i + 1));
							}
							else if (columnName.equalsIgnoreCase("ZipCode"))
							{
								sickLeaveCost.setZipCode((String) getRSColumnValue(rs, rsMeta, i + 1));
							}
							else if (columnName.equalsIgnoreCase("CityName"))
							{
								sickLeaveCost.setCityName((String) getRSColumnValue(rs, rsMeta, i + 1));
							}
							else if (columnName.equalsIgnoreCase("SickHoursCost"))
							{
								sickLeaveCost.setSickHoursCost((Double) getRSColumnValue(rs, rsMeta, i + 1));
							}
							else if (columnName.equalsIgnoreCase("PlaceName"))
							{
								sickLeaveCost.setPlaceName((String) getRSColumnValue(rs, rsMeta, i + 1));
							}
							else if (columnName.equalsIgnoreCase("StateAbbreviation"))
							{
								sickLeaveCost.setStateAbbreviation((String) getRSColumnValue(rs, rsMeta, i + 1));
							}
							else if (columnName.equalsIgnoreCase("HourlySalaryRate"))
							{
								sickLeaveCost.setHourlySalaryRate((String) getRSColumnValue(rs, rsMeta, i + 1));
							}
							else if (columnName.equalsIgnoreCase("AnnualCap"))
							{
								sickLeaveCost.setAnnualCap((Integer) getRSColumnValue(rs, rsMeta, i + 1));
							}
							else if (columnName.equalsIgnoreCase("PTO"))
							{
								sickLeaveCost.setPto((String) getRSColumnValue(rs, rsMeta, i + 1));
							}
							else if (columnName.equalsIgnoreCase("IsPTOEligible"))
							{
								sickLeaveCost.setIsPtoEligible((String) getRSColumnValue(rs, rsMeta, i + 1));
							}
							else if (columnName.equalsIgnoreCase("PTOLimitToOverrideSick"))
							{
								sickLeaveCost.setPtoLimitToOverrideSick((Double) getRSColumnValue(rs, rsMeta, i + 1));
							}
						}
						catch (Exception ex)
						{
							System.out.println("Exception in procedure [spGetSickLeaveData] :: [SickLeaveCostRepositoryImpl] " + ex.getMessage());
							continue;
						}
					}
					list.add(sickLeaveCost);
					
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
		
		public String getResult()
		{
			return result;
		}
		
		public List<SickLeaveCost> getList()
		{
			return list;
		}
	}

	@Override
	public Integer spGetDuplicateZipCodeInSickLeaveCost(String zipCode, String oldZipCode, String mode, String userId)
	{
		Session session = entityManager.unwrap(Session.class);
		ZipCodeDuplicateCheckWork work = new ZipCodeDuplicateCheckWork(zipCode, oldZipCode, mode, userId);
		session.doWork(work);
		Integer count = work.getCount();
		return count;
	}
	
	private static class ZipCodeDuplicateCheckWork implements Work
	{
		private Logger logger = LoggerFactory.getLogger(getClass());
		String zipCode;
		String oldZipCode;
		String mode;
		String userId;
		Integer count;
		
		public ZipCodeDuplicateCheckWork(String zipCode, String oldZipCode, String mode, String userId)
		{
			super();
			this.zipCode = zipCode;
			this.oldZipCode = oldZipCode;
			this.mode = mode;
			this.userId = userId;
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
					query = "{call " + FacesUtils.SCHEMA_WIRELESS + ".spGetDuplicateZipCodeInSickLeaveCost('" + zipCode + "','" + oldZipCode + "','" + mode + "','" + userId +"')}";
					logger.info("SickLeaveCost Zip Code Duplicate Check Query is : " + query);
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
	public String spAddUpdateSickLeaveCost(String userId, SickLeaveCost sickLeaveCost, String oldZipCode, Integer actionType)
	{
		Session session = entityManager.unwrap(Session.class);
		SickLeaveCostAddUpdateWork work = new SickLeaveCostAddUpdateWork(userId, sickLeaveCost, oldZipCode, actionType);
		session.doWork(work);
		String result = work.getResult();
		return result;
	}
	
	private static class SickLeaveCostAddUpdateWork implements Work
	{
		public Logger logger = LoggerFactory.getLogger(getClass());
		String userId;
		SickLeaveCost sickLeaveCost;
		Integer actionType;
		String oldZipCode;
		String result = "1";
		
		public SickLeaveCostAddUpdateWork(String userId, SickLeaveCost sickLeaveCost, String oldZipCode, Integer actionType)
		{
			super();
			this.actionType = actionType;
			this.userId = userId;
			this.sickLeaveCost = sickLeaveCost;
			this.oldZipCode = oldZipCode;
		}
		
		@Override
		public void execute(Connection connection) throws SQLException
		{
			CallableStatement cs = null;
			ResultSet rs = null;
			try
			{
				String insertStoreProc = "{call " + FacesUtils.SCHEMA_WIRELESS + ".spAddUpdateSickLeaveCost(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
				cs = connection.prepareCall(insertStoreProc);
				
				StringBuilder queryToAddUpdateResource = new StringBuilder();
				queryToAddUpdateResource.append("USE [Analyser] GO DECLARE	@return_value int EXEC	@return_value = [wireless].[spAddUpdateSickLeaveCost]");
				
				// @intActionType
				cs.setInt(1, actionType);
				queryToAddUpdateResource.append("@intActionType = " + actionType + ",");
				
				// @varLoggedUserID
				cs.setString(2, userId);
				queryToAddUpdateResource.append("@varLoggedUserID = N'" + userId + "',");
				
				// @varWorksiteStateCode
				cs.setString(3, sickLeaveCost.getWorksiteStateCode().trim());
				queryToAddUpdateResource.append("@varWorksiteStateCode = N'" + sickLeaveCost.getWorksiteStateCode().trim() + "',");
				
				// @varWorksiteStateDescription
				cs.setString(4, sickLeaveCost.getWorksiteStateDescription().trim());
				queryToAddUpdateResource.append("@varWorksiteStateDescription = N'" + sickLeaveCost.getWorksiteStateDescription().trim() + "',");
				
				// @varZipCode
				cs.setString(5, sickLeaveCost.getZipCode().trim());
				queryToAddUpdateResource.append("@varZipCode = N'" + sickLeaveCost.getZipCode().trim() + "',");
				
				// @varCityName
				cs.setString(6, sickLeaveCost.getCityName().trim());
				queryToAddUpdateResource.append("@varCityName = N'" + sickLeaveCost.getCityName().trim() + "',");
				
				// @SickHoursCost
				cs.setDouble(7, sickLeaveCost.getSickHoursCost());
				queryToAddUpdateResource.append("@SickHoursCost = " + sickLeaveCost.getSickHoursCost() + ",");
				
				// @varPlaceName
				String placeName = (sickLeaveCost.getPlaceName() == null) ? "" : sickLeaveCost.getPlaceName().trim();
				cs.setString(8, placeName);
				queryToAddUpdateResource.append("@varPlaceName = N'" + placeName + "',");
				
				// @varStateAbbreviation
				String stateAbbreviation = (sickLeaveCost.getStateAbbreviation() == null) ? "" : sickLeaveCost.getStateAbbreviation().trim();
				cs.setString(9, stateAbbreviation);
				queryToAddUpdateResource.append("@varStateAbbreviation = N'" + stateAbbreviation + "',");
				
				// @varHourlySalaryRate
				String hourlySalaryRate = (sickLeaveCost.getHourlySalaryRate() == null) ? "" : sickLeaveCost.getHourlySalaryRate().trim();
				cs.setString(10, hourlySalaryRate);
				queryToAddUpdateResource.append("@varHourlySalaryRate = N'" + hourlySalaryRate + "',");
				
				// @AnnualCap
				cs.setDouble(11, sickLeaveCost.getAnnualCap());
				queryToAddUpdateResource.append("@AnnualCap = " + sickLeaveCost.getAnnualCap() + ",");
				
				// @varPTO
				String pto = (sickLeaveCost.getPto() == null) ? "" : sickLeaveCost.getPto().trim();
				cs.setString(12, pto);
				queryToAddUpdateResource.append("@varPTO = N'" + pto + "',");
				
				// @varIsPTOEligible
				String isPTOEligible = (sickLeaveCost.getIsPtoEligible() == null) ? "" : sickLeaveCost.getIsPtoEligible().trim();
				cs.setString(13, isPTOEligible);
				queryToAddUpdateResource.append("@varIsPTOEligible = N'" + isPTOEligible + "',");
				
				// @varOldZipCode
				oldZipCode = (oldZipCode == null) ? "-1" : oldZipCode;
				cs.setString(14, oldZipCode);
				queryToAddUpdateResource.append("@varOldZipCode = N'" + oldZipCode + "',");
				
				// @ptoLimitToOverrideSick
				cs.setDouble(15, sickLeaveCost.getPtoLimitToOverrideSick());
				queryToAddUpdateResource.append("@ptoLimitToOverrideSick = " + sickLeaveCost.getPtoLimitToOverrideSick() + "");
				
				queryToAddUpdateResource.append("SELECT	'Return Value' = @return_value GO ");
				System.out.println(queryToAddUpdateResource.toString());
				logger.debug("Query to add update resource is : " + queryToAddUpdateResource.toString());
				rs = cs.executeQuery();
				
				if (rs != null)
				{
					while (rs.next())
					{
						result = String.valueOf(rs.getInt(1));
					}
				}
				else
				{
					result = "-1";
				}
				
			}
			catch (Exception ex)
			{
				ex.printStackTrace();
				result = "1";
			}
			finally
			{
				if (rs != null) rs.close();
				if (cs != null) cs.close();
			}
			
		}
		
		public String getResult()
		{
			return result;
		}
	}

	@Override
	public Integer validateClientSite(String userId, Integer siteId, Integer clientId)
	{
		Session session = entityManager.unwrap(Session.class);
		ValidateClientSite work = new ValidateClientSite(userId, siteId, clientId);
		session.doWork(work);
		Integer result = work.getResult();
		return result;
	}
	
	private static class ValidateClientSite implements Work
	{
		private Logger logger = LoggerFactory.getLogger(getClass());
		String userId;
		Integer siteId, clientId;
		Integer result;
		
		public ValidateClientSite(String userId, Integer siteId, Integer clientId)
		{
			super();
			this.userId = userId;
			this.siteId = siteId;
			this.clientId = clientId;
		}
		
		@Override
		public void execute(Connection connection) throws SQLException
		{
			CallableStatement callStmt = null;
			ResultSet rs = null;
			result = 0;
			try
			{
				if (connection != null)
				{
					String query = "";
					query = "{call " + FacesUtils.SCHEMA_WIRELESS + ".spValidateClientSite('" + userId + "'," + siteId + "," + clientId + ")}";
					logger.debug("spValidateClientSite Query : " + query);
					System.out.println("spValidateClientSite Query : " + query);
					callStmt = connection.prepareCall(query);
					rs = callStmt.executeQuery();
					if (rs != null)
					{
						if (rs.next())
						{
							result = rs.getInt(1);
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
				ex.printStackTrace();
			}
			finally
			{
				if (rs != null) rs.close();
				if (callStmt != null) callStmt.close();
			}
		}
		
		public Integer getResult()
		{
			return result;
		}
	}
	
	@Override
	public SickLeaveCost spGetClientPtoLimit(String userId, Integer siteId, Integer clientId)
	{
		Session session = entityManager.unwrap(Session.class);
		ClientPTOLimitWork work = new ClientPTOLimitWork(userId, siteId, clientId);
		session.doWork(work);
		SickLeaveCost sickLeaveCost = work.getSickLeaveCost();
		return sickLeaveCost;
	}
	
	/*
	 * Added as part of sick Leave project
	 */
	@Override
	public SickLeaveCost spGetSickLeaveDetails(String userId, Integer siteId, String homeZip, String workfromSource, String majorityWorkPerformed)
	{
		Session session = entityManager.unwrap(Session.class);
		SickLeaveDetailsWork work = new SickLeaveDetailsWork(userId, siteId, homeZip, workfromSource, majorityWorkPerformed);
		session.doWork(work);
		SickLeaveCost sickLeaveCost = work.getSickLeaveCost();
		return sickLeaveCost;
	}
	
	
	private static class ClientPTOLimitWork implements Work
	{
		private Logger logger = LoggerFactory.getLogger(getClass());
		String userId;
		Integer siteId, clientId;
		SickLeaveCost sickLeaveCost;
		
		public ClientPTOLimitWork(String userId, Integer siteId, Integer clientId)
		{
			super();
			this.userId = userId;
			this.siteId = siteId;
			this.clientId = clientId;
		}
		
		@Override
		public void execute(Connection connection) throws SQLException
		{
			CallableStatement callStmt = null;
			ResultSet rs = null;
			sickLeaveCost = new SickLeaveCost();
			//set default value in case there is any exception
			sickLeaveCost.setPtoLimitToOverrideSick(0.0);
			sickLeaveCost.setSickHoursCost(0.0);
			sickLeaveCost.setAnnualCap(0);
			try
			{
				if (connection != null)
				{
					String query = "";
					query = "{call " + FacesUtils.SCHEMA_WIRELESS + ".spGetClientPtoLimit('" + userId + "'," + siteId + "," + clientId + ")}";
					logger.info("spGetClientPtoLimit Query : " + query);
					System.out.println("spGetClientPtoLimit Query : " + query);
					callStmt = connection.prepareCall(query);
					rs = callStmt.executeQuery();
					if (rs != null)
					{
						if (rs.next())
						{
							sickLeaveCost.setPtoLimitToOverrideSick(rs.getDouble(1));
							sickLeaveCost.setSickHoursCost(rs.getDouble(2));
							sickLeaveCost.setAnnualCap(rs.getInt(3));
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
				ex.printStackTrace();
			}
			finally
			{
				if (rs != null) rs.close();
				if (callStmt != null) callStmt.close();
			}
		}
		
		public SickLeaveCost getSickLeaveCost()
		{
			return sickLeaveCost;
		}
	}
	
	/*
	 * Added as part of sick Leave project
	 */
	private static class SickLeaveDetailsWork implements Work
	{
		private Logger logger = LoggerFactory.getLogger(getClass());
		String userId, homeZip, workFromSource, majorityWorkPerformed;
		Integer siteId;

		
		SickLeaveCost sickLeaveCost;
		
		public SickLeaveDetailsWork(String userId, Integer siteId, String homeZip, String workFromSource, String majorityWorkPerformed )
		{
			super();
			this.userId = userId;
			this.siteId = siteId;
			this.homeZip = homeZip;
			this.workFromSource = workFromSource;
			this.majorityWorkPerformed = majorityWorkPerformed;
		}
		
		@Override
		public void execute(Connection connection) throws SQLException
		{
			CallableStatement callStmt = null;
			ResultSet rs = null;
			sickLeaveCost = new SickLeaveCost();
			//set default value in case there is any exception
			sickLeaveCost.setPtoLimitToOverrideSick(0.0);
			sickLeaveCost.setSickHoursCost(0.0);
			sickLeaveCost.setAnnualCap(0);
			try
			{
				if (connection != null)
				{
					String query = "";
					query = "{call " + FacesUtils.SCHEMA_DBO + ".spGetQualifiedSickLeaveDetails('" + userId + "'," + siteId + ",'" + homeZip +
							 "','" + workFromSource + "','" + majorityWorkPerformed +
							"')}";
					logger.info("spGetQualifiedSickLeaveDetails Query : " + query);
					System.out.println("spGetQualifiedSickLeaveDetails Query : " + query);
					callStmt = connection.prepareCall(query);
					rs = callStmt.executeQuery();
					if (rs != null)
					{
						if (rs.next())
						{
							sickLeaveCost.setPtoLimitToOverrideSick(rs.getDouble(1));
							sickLeaveCost.setSickHoursCost(rs.getDouble(2));
							sickLeaveCost.setAnnualCap(rs.getInt(3));
							sickLeaveCost.setSickLeaveSource(rs.getString(4));
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
				ex.printStackTrace();
			}
			finally
			{
				if (rs != null) rs.close();
				if (callStmt != null) callStmt.close();
			}
		}
		
		public SickLeaveCost getSickLeaveCost()
		{
			return sickLeaveCost;
		}
	}
	
}
