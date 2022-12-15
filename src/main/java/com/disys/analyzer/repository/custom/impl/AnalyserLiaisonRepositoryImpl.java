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
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.disys.analyzer.config.util.Constants;
import com.disys.analyzer.config.util.FacesUtils;
import com.disys.analyzer.model.AnalyserLiaison;
import com.disys.analyzer.model.dto.AnalyserLiaisonDTO;
import com.disys.analyzer.model.dto.UserDTO;
import com.disys.analyzer.repository.custom.AnalyserLiaisonRepositoryCustom;


/**
 * @author Sajid
 * 
 *
 */
@Repository
@Transactional(readOnly = true)
public class AnalyserLiaisonRepositoryImpl implements AnalyserLiaisonRepositoryCustom
{
	
	public Logger logger = LoggerFactory.getLogger(getClass());
	
	@PersistenceContext
	EntityManager entityManager;
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.disys.analyzer.repository.custom.AnalyserLiaisonRepositoryCustom#
	 * getAllAnalyserLiaison(java.lang.String)
	 */
	@Override
	public List<AnalyserLiaison> getAllAnalyserLiaison(String userId, String userList, String orderBy, String searchString, String action)
	{
		Session session = entityManager.unwrap(Session.class);
		AnalyserLiaisonWork work = new AnalyserLiaisonWork(userId, userList, orderBy, searchString, action);
		session.doWork(work);
		List<AnalyserLiaison> list = work.getList();
		return list;
	}
	
	private static class AnalyserLiaisonWork implements Work
	{
		
		private List<AnalyserLiaison> list;
		private AnalyserLiaison liaison;
		
		String userId;
		String userList;
		String orderBy;
		String searchString;
		
		String action;
		
		CallableStatement cstmt = null;
		
		/**
		 * @param userId
		 */
		public AnalyserLiaisonWork(String userId, String userList, String orderBy, String searchString, String action)
		{
			super();
			this.userId = userId;
			this.userList = userList;
			this.orderBy = orderBy;
			this.searchString = searchString;
			this.action = action;
		}
		
		@Override
		public void execute(Connection connection) throws SQLException
		{
			liaison = new AnalyserLiaison();
			list = new ArrayList<AnalyserLiaison>();
			try
			{
				StringBuilder queryToAnalyserComm = new StringBuilder();
				queryToAnalyserComm.append("USE [Analyser] \n GO \n DECLARE	@return_value int \n EXEC	@return_value = [wireless].[spGetAnalyserAllLiaison] ");
				queryToAnalyserComm.append("\n @varLoggedOnUserID = N'" + userId + "',");
				queryToAnalyserComm.append("\n @varUserList = N'" + userList + "',");
				queryToAnalyserComm.append("\n @varOrderBy = N'" + orderBy + "',");
				queryToAnalyserComm.append("\n @varSearchString = N'" + searchString + "'");
				
				queryToAnalyserComm.append("\n SELECT	'Return Value' = @return_value \n GO ");
				System.out.println("Query :: " + queryToAnalyserComm.toString());
				cstmt = connection.prepareCall("{call " + FacesUtils.SCHEMA_WIRELESS + ".spGetAnalyserAllLiaison(?, ?, ?, ?)}");
				cstmt.setString("varLoggedOnUserID", userId);
				cstmt.setString("varUserList", userList);
				cstmt.setString("varOrderBy", orderBy);
				cstmt.setString("varSearchString", searchString);
				
				ResultSet rs = cstmt.executeQuery();
				
				try
				{
					list = getRSRowInfo(rs);
					System.out.println(list.size());
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
			catch (Exception e)
			{
				System.out.println("Exception in AnalyserLiaisonRepositoryImpl " + e.getLocalizedMessage());
			}
		}
		
		private List<AnalyserLiaison> getRSRowInfo(ResultSet rs) throws Exception
		{
			ResultSetMetaData rsMeta;
			try
			{
				rsMeta = rs.getMetaData();
				int iRowCount = rsMeta.getColumnCount();
				while (rs.next())
				{
					liaison = new AnalyserLiaison();
					for (int i = 0; i < iRowCount; i++)
					{
						String columnName = rsMeta.getColumnLabel(i + 1);
						// put values into the array list
						try
						{
							if (columnName.equalsIgnoreCase("LiaisonName"))
							{
								liaison.setLiaisonName((String) getRSColumnValue(rs, rsMeta, i + 1));
							}
							else if (columnName.equalsIgnoreCase("Updated_By"))
							{
								liaison.setUpdatedBy((String) getRSColumnValue(rs, rsMeta, i + 1));
							}
							else if (columnName.equalsIgnoreCase("LiaisonId"))
							{
								liaison.setLiaisonID((String) getRSColumnValue(rs, rsMeta, i + 1));
							}
							else if (columnName.equalsIgnoreCase("Status"))
							{
								liaison.setStatus((Integer) getRSColumnValue(rs, rsMeta, i + 1));
							}
						}
						catch (Exception ex)
						{
							continue;
						}
					}
					list.add(liaison);
					
				}
				return list;
			}
			catch (SQLException e)
			{
				String sz = "An error occured while attempting to parse resultset column information from the database for AnalyserLiaisonWork.  Error type :  " + e.getSQLState() + ".  Error Code : "
						+ e.getErrorCode();
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
						else
							return new Double(bigDecimal.doubleValue());
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
				String sz = "An error occured while attempting to parse resultset column information from the database.  Error type :  " + e.getSQLState() + ".  Error Code : " + e.getErrorCode();
				Exception err = new Exception(sz);
				throw err;
			}
			
		}
		
		/**
		 * @return the list
		 */
		public List<AnalyserLiaison> getList()
		{
			return list;
		}
		
	}
	
	@Override
	public List<AnalyserLiaisonDTO> getAnalyserLiaisonList(String userId, String companyCode) 
	{
		Session session = entityManager.unwrap(Session.class);
		AnalyserLiaisonRepositoryWork work = new AnalyserLiaisonRepositoryWork(userId, companyCode);	
		session.doWork(work);
		List<AnalyserLiaisonDTO> list = work.getList();
		return list;
	}
	
	private static class AnalyserLiaisonRepositoryWork implements Work {
		public Logger logger = LoggerFactory.getLogger(getClass());
		private List<AnalyserLiaisonDTO> list;
		private AnalyserLiaisonDTO analyserLiaisonDTO;
		String userId;
		String companyCode;
		String recordCode=Constants.STRING_CONSTANT_ALL;
		CallableStatement cstmt = null;
		ResultSet rs = null;

		/**
		 * @param userId
		 */
		public AnalyserLiaisonRepositoryWork(String userId, String companyCode) {
			super();
			this.userId = userId;
			this.companyCode=companyCode;
		}

		@Override
		public void execute(Connection connection) throws SQLException {

			list = new ArrayList<AnalyserLiaisonDTO>();
			analyserLiaisonDTO = new AnalyserLiaisonDTO();

				String query = "{call " + FacesUtils.SCHEMA_DBO
						+ ".spGetAnalyzerLiaisonList('" + userId + "','" + companyCode + "','"+recordCode+"')}";

				System.out.println("Query in AnalyserLiaisonRepositoryWork " + query);

				logger.debug("Query in AnalyserLiaisonRepositoryWork " + query);

				cstmt = connection.prepareCall(query);
				rs = cstmt.executeQuery();

			try {
					if (rs != null) 
					{
						while (rs.next()) 
						{
							analyserLiaisonDTO.setAnalyserLiaisonIdCode(rs.getString("LiaisonID"));
							analyserLiaisonDTO.setAnalyserLiaisonNameDescription(rs.getString("LiaisonName"));														
							list.add(analyserLiaisonDTO);
							analyserLiaisonDTO = new AnalyserLiaisonDTO();
						}
					} 
					else 
					{
						list = new ArrayList<AnalyserLiaisonDTO>();
					}
					System.out.println("List size in AnalyserLiaisonRepositoryWork " + list.size());
					logger.debug("List size in AnalyserLiaisonRepositoryWork " + list.size());
			} 
			catch (Exception e) 
			{
				System.out.println("Exception in AnalyserLiaisonRepositoryWork --> execute.");
				logger.debug("Exception in AnalyserLiaisonRepositoryWork --> execute.");
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
		public List<AnalyserLiaisonDTO> getList() {
			return list;
		}

	}
	

	@Override
	public List<AnalyserLiaisonDTO> getAnalyserLiaisonDescription(String userId, String companyCode, String recordCode) 
	{
		Session session = entityManager.unwrap(Session.class);
		AnalyserLiaisonDescriptionRepositoryWork work = new AnalyserLiaisonDescriptionRepositoryWork(userId, companyCode, recordCode);	
		session.doWork(work);
		List<AnalyserLiaisonDTO> list = work.getList();
		return list;
	}
	
	private static class AnalyserLiaisonDescriptionRepositoryWork implements Work {
		public Logger logger = LoggerFactory.getLogger(getClass());
		private List<AnalyserLiaisonDTO> list;
		private AnalyserLiaisonDTO analyserLiaisonDTO;
		String userId;
		String companyCode;
		String recordCode;
		CallableStatement cstmt = null;
		ResultSet rs = null;

		/**
		 * @param userId
		 */
		public AnalyserLiaisonDescriptionRepositoryWork(String userId, String companyCode, String recordCode) {
			super();
			this.userId = userId;
			this.companyCode=companyCode;
			this.recordCode=recordCode;
		}

		@Override
		public void execute(Connection connection) throws SQLException {

			list = new ArrayList<AnalyserLiaisonDTO>();
			analyserLiaisonDTO = new AnalyserLiaisonDTO();

				String query = "{call " + FacesUtils.SCHEMA_DBO
						+ ".spGetAnalyzerLiaisonDescription('" + userId + "','" + companyCode + "','"+recordCode+"')}";

				System.out.println("Query in AnalyserLiaisonDescriptionRepositoryWork " + query);

				logger.debug("Query in AnalyserLiaisonDescriptionRepositoryWork " + query);

				cstmt = connection.prepareCall(query);
				rs = cstmt.executeQuery();

			try {
					if (rs != null) 
					{
						while (rs.next()) 
						{
							analyserLiaisonDTO.setAnalyserLiaisonNameDescription(rs.getString("LiaisonName").isEmpty()?rs.getString("LiaisonName"):rs.getString("LiaisonName"));														
							list.add(analyserLiaisonDTO);
							analyserLiaisonDTO = new AnalyserLiaisonDTO();
						}
					} 
					else 
					{
						list = new ArrayList<AnalyserLiaisonDTO>();
					}
					System.out.println("List size in AnalyserLiaisonDescriptionRepositoryWork " + list.size());
					logger.debug("List size in AnalyserLiaisonDescriptionRepositoryWork " + list.size());
			} 
			catch (Exception e) 
			{
				System.out.println("Exception in AnalyserLiaisonDescriptionRepositoryWork --> execute.");
				logger.debug("Exception in AnalyserLiaisonDescriptionRepositoryWork --> execute.");
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
		public List<AnalyserLiaisonDTO> getList() {
			return list;
		}

	
	}
	@Override
	public List<AnalyserLiaison> getAnalyserAllLiaison(String userId, String userList, String sortOrder, String searchString, String companyCode)
	{
		Session session = entityManager.unwrap(Session.class);
		AnalyserLiaisonWorkRepository work = new AnalyserLiaisonWorkRepository(userId, userList, sortOrder, searchString, companyCode);
		session.doWork(work);
		List<AnalyserLiaison> list = work.getList();
		return list;
	}
	
	private static class AnalyserLiaisonWorkRepository implements Work {
		public Logger logger = LoggerFactory.getLogger(getClass());
		private List<AnalyserLiaison> list;
		private AnalyserLiaison analyserLiaison;
		String userId;
		String userList;
		String sortOrder;
		String searchString;
		String companyCode;

		ResultSet rs = null;

		
		public AnalyserLiaisonWorkRepository(String userId, String userList, String sortOrder, String searchString, String companyCode) {
			super();
			this.userId = userId;
			this.userList = userList;
			this.sortOrder = sortOrder;
			this.searchString = searchString;
			this.companyCode = companyCode;
		}

		@Override
		public void execute(Connection connection) throws SQLException {
			
			list = new ArrayList<AnalyserLiaison>();
			analyserLiaison = new AnalyserLiaison();
			CallableStatement cstmt = null;			
			String query = "{call " + FacesUtils.SCHEMA_WIRELESS
					+ ".spGetAnalyserAllLiaison('" + userId + "','" + userList + "','" + sortOrder + "','" + searchString + "', '" + companyCode + "')}";
			System.out.println("Query in spGetAnalyserAllLiaison " + query);
			logger.debug("Query in spGetAnalyserAllLiaison " + query);

			cstmt = connection.prepareCall(query);
			rs = cstmt.executeQuery();

			try {
				if (rs != null) {
					while (rs.next()) {
						analyserLiaison.setLiaisonID(rs.getString("LIAISONID"));
						analyserLiaison.setLiaisonName(rs.getString("LIAISONNAME"));						
						analyserLiaison.setStatus(rs.getInt("STATUS"));
						analyserLiaison.setUpdatedBy(rs.getString("UPDATED_BY"));						
						analyserLiaison.setCompanyCode(rs.getString("COMPANYCODE"));
						list.add(analyserLiaison);
						analyserLiaison = new AnalyserLiaison();
					}
				} else {
					list = new ArrayList<AnalyserLiaison>();
				}

				System.out.println("List size in get user list " + list.size());
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (rs != null)
					rs.close();
				if (cstmt != null)
					cstmt.close();
			}
		}

		public List<AnalyserLiaison> getList() {
			return list;
		}
	}
	
	@Override
	public List<UserDTO> spGetActiveUsersLiaison(String userId, String companyCode)
	{
		Session session = entityManager.unwrap(Session.class);
		ActiveUsersLiaisonWork work = new ActiveUsersLiaisonWork(userId, companyCode);
		session.doWork(work);
		List<UserDTO> list = work.getList();
		return list;
	}
	
	private static class ActiveUsersLiaisonWork implements Work {
		public Logger logger = LoggerFactory.getLogger(getClass());
		private List<UserDTO> list;
		private UserDTO userDTO;
		String userId;		
		String companyCode;

		ResultSet rs = null;

		
		public ActiveUsersLiaisonWork(String userId, String companyCode) {
			super();
			this.userId = userId;
			this.companyCode = companyCode;
		}

		@Override
		public void execute(Connection connection) throws SQLException {
			
			list = new ArrayList<UserDTO>();
			userDTO = new UserDTO();
			CallableStatement cstmt = null;			
			String query = "{call " + FacesUtils.SCHEMA_WIRELESS
					+ ".spGetUserListForLiaison('" + userId + "', '" + companyCode + "')}";
			System.out.println("Query in spGetUserListForLiaison " + query);
			logger.debug("Query in spGetUserListForLiaison " + query);

			cstmt = connection.prepareCall(query);
			rs = cstmt.executeQuery();

			try {
				if (rs != null) {
					while (rs.next()) {
						userDTO.setName(rs.getString("Name"));						
						userDTO.setUserId(rs.getString("USER_ID"));
						userDTO.setApdCode(rs.getString("ADPCode"));												
						list.add(userDTO);
						userDTO = new UserDTO();
					}
				} else {
					list = new ArrayList<UserDTO>();					
				}

				System.out.println("List size in get user list " + list.size());
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (rs != null)
					rs.close();
				if (cstmt != null)
					cstmt.close();
			}
		}

		public List<UserDTO> getList() {
			return list;
		}
	}
}
