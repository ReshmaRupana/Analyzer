/**
 * 
 */
package com.disys.analyzer.repository.custom.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Session;
import org.hibernate.jdbc.Work;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.disys.analyzer.config.util.FacesUtils;
import com.disys.analyzer.config.util.Util;
import com.disys.analyzer.model.User;
import com.disys.analyzer.model.dto.UserDTO;
import com.disys.analyzer.repository.custom.ApplicationUserRepositoryCustom;

import com.disys.analyzer.security.service.util.PasswordBasedEncryption;

/**
 * @author Sajid
 * 
 */
public class ApplicationUserRepositoryImpl implements
		ApplicationUserRepositoryCustom {
	public Logger logger = LoggerFactory.getLogger(getClass());
	
	public static final String PASSWORD_RESET = "reset";
	public static final String PASSWORD_MODIFY = "modify";

	@PersistenceContext
	EntityManager entityManager;

	@Override
	public List<User> getUserList(String userId, String searchString, String companyCode) {
		Session session = entityManager.unwrap(Session.class);
		ApplicationUserRepositoryWork work = new ApplicationUserRepositoryWork(
				userId, searchString, companyCode);
		session.doWork(work);
		List<User> list = work.getList();
		return list;
	}

	private static class ApplicationUserRepositoryWork implements Work {
		public Logger logger = LoggerFactory.getLogger(getClass());
		private List<User> list;
		private User user;
		String userId;		
		String searchString;
		String companyCode;

		ResultSet rs = null;

		
		public ApplicationUserRepositoryWork(String userId,  String searchString, String companyCode) {
			super();
			this.userId = userId;
			this.searchString = searchString;
			this.companyCode = companyCode;
		}

		@Override
		public void execute(Connection connection) throws SQLException {
			
			list = new ArrayList<User>();
			user = new User();
			CallableStatement cstmt = null;			
			String query = "{call " + FacesUtils.SCHEMA_WIRELESS
					+ ".spGetDisysUserList('" + userId + "','" + searchString + "', '" + companyCode + "')}";
			System.out.println("Query in spGetDisysUserList " + query);
			logger.debug("Query in spGetDisysUserList " + query);

			cstmt = connection.prepareCall(query);
			rs = cstmt.executeQuery();

			try {
				if (rs != null) {
					while (rs.next()) {
						user.setFirstName(rs.getString("FIRST_NAME"));						
						user.setLastName(rs.getString("LAST_NAME"));
						user.setUserId(rs.getString("USER_ID"));
						user.setADPCode(rs.getString("ADPCode"));						
						user.setRoleId(rs.getInt("ROL_ID"));
						user.setManagerId(rs.getString("MANAGER_ID"));
						user.setADAnalyzerGroupSyncId(rs.getInt("ADAnalyzerGroupSyncId"));
						user.setBatchId(rs.getInt("BATCHID"));						
						user.setStatus(rs.getInt("STATUS"));
						user.setUpdatedBy(rs.getString("UPDATED_BY"));
						user.setUpdatedDate(rs.getTimestamp("UPDATED_DATE"));
						user.setCompanyCode(rs.getString("CompanyCode"));
						list.add(user);
						user = new User();
					}
				} else {
					list = new ArrayList<User>();
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

		public List<User> getList() {
			return list;
		}
	}

	
	
	
	
	/*@Override
	public List<User> getUserList(String userId, String assignedDevice,
			String lastName, String firstName) {
		Session session = entityManager.unwrap(Session.class);
		ApplicationUserRepositoryWork work = new ApplicationUserRepositoryWork(
				userId, assignedDevice, lastName, firstName);
		session.doWork(work);
		List<User> list = work.getList();
		return list;
	}

	private static class ApplicationUserRepositoryWork implements Work {
		public Logger logger = LoggerFactory.getLogger(getClass());
		private List<User> list;
		private User user;
		String userId;
		String assignedDevice;
		String lastName;
		String firstName;

		ResultSet rs = null;

		
		  @param userId
		  @param assignedDevice
		 
		public ApplicationUserRepositoryWork(String userId,
				String assignedDevice, String lastName, String firstName) {
			super();
			this.userId = userId;
			this.assignedDevice = assignedDevice;
			this.lastName = lastName;
			this.firstName = firstName;
		}

		@Override
		public void execute(Connection connection) throws SQLException {

			list = new ArrayList<User>();
			user = new User();
			CallableStatement cstmt = null;
			CallableStatement cstmt = connection.prepareCall("{call "
					+ FacesUtils.SCHEMA_WIRELESS + ".spGetUserList(?, ?)}");
			String query = "{call " + FacesUtils.SCHEMA_WIRELESS
					+ ".spGetUserList22('" + userId + "'," + assignedDevice
					+ ",'" + lastName + "'" + ",'" + firstName + "')}";

			System.out.println("Query in spGetUserList rpo------------ " + query);
			logger.debug("Query in spGetUserList " + query);

			cstmt = connection.prepareCall(query);
			rs = cstmt.executeQuery();

			try {

				if (rs != null) {
					while (rs.next()) {
						user.setFirstName(rs.getString(1));
						user.setUserId(rs.getString(2));
						user.setStatus(rs.getInt(3));
						user.setUpdatedBy(rs.getString(4));
						user.setUpdatedDate(rs.getTimestamp(5));
						user.setLastName(rs.getString(6));
						user.setADAnalyzerGroupSyncId(rs.getInt(7));
						user.setBatchId(rs.getInt(8));
						user.setADPCode(rs.getString(9));
						list.add(user);
						user = new User();
					}
				} else {
					list = new ArrayList<User>();
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

		
		  @return the list
		 
		public List<User> getList() {
			return list;
		}
	}*/

	@Override
	public String deleteUser(String userId, String loggedUserId) {
		Session session = entityManager.unwrap(Session.class);
		String action = "Delete";
		ApplicationUserCRUDWork work = new ApplicationUserCRUDWork(userId,
				loggedUserId, action);
		session.doWork(work);
		String result = work.getResult();
		return result;
	}

	/**
	 * @author Saravanan.Dhurairaj  
	 * userIdValidation is string method.
	 * It is used to get userId value based on userId code to check duplicate
	 * Procedure used dbo.spGetDuplicateValidationCount
	 * Table used Users
	 * return the value
	 */
	
	@Override
	public String userIdValidation(String newUserId) 
	{
		Session session = entityManager.unwrap(Session.class);
		AnalyserNewUserId work = new AnalyserNewUserId(newUserId);
		session.doWork(work);
		String result = work.getResult();
		return result;
	}

	/**
	 * @author Saravanan.Dhurairaj  
	 * AnalyserNewUserId is class used for dbConncetion and set, get and return the retrieve from user table
	 * Procedure used dbo.spGetDuplicateValidationCount
	 * Table used Users
	 * finally close our connection
	 */
	
	private static class AnalyserNewUserId implements Work{
		private Logger logger = LoggerFactory.getLogger(this.getClass());
		String result;
		String newUserId;
		String USERID="USERID";
		/**
		 * @param newUserId
		 */
		public AnalyserNewUserId(String newUserId)
		{
			super();
			this.newUserId = newUserId;
			
		}
		/**
		 * @return the result
		 */
		public String getResult()
		{
			return result;
		}
		@Override
		public void execute(Connection connection) throws SQLException {
			// TODO Auto-generated method stub
			CallableStatement callStmt = null;
			ResultSet rs = null;
			try{
				if (connection != null)
				{
						String query = "";
						query = "{call " + FacesUtils.SCHEMA_WIRELESS + ".spGetDuplicateValidationCount('" + newUserId + "','"+USERID+"')}";
						System.out.println("query " + query);
						logger.debug("Query is getDuplicateUserIdCheck : " + query);
						callStmt = connection.prepareCall(query);
						rs = callStmt.executeQuery();
						if(rs != null){
							while(rs.next()){
								 result=rs.getString("RESULT");
								}
						}
						callStmt.close();
						rs.close();
						callStmt = null;
						rs = null;
				}
			}catch (Exception ex) {
				System.out.println("Exception in retrieve UserId");
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
	 * @author Saravanan.Dhurairaj  
	 * userIdValidation is string method.
	 * It is used to get userId value based on userId code to check duplicate
	 * Procedure used dbo.getDuplicateUserIdCheck
	 * Table used Users
	 * return the value
	 */
	
	@Override
	public String employeeIdValidation(String newEmployeeId) 
	{
		Session session = entityManager.unwrap(Session.class);
		AnalyserNewEmployeeId work = new AnalyserNewEmployeeId(newEmployeeId);
		session.doWork(work);
		String result = work.getResult();
		return result;
	}
	
	/**
	 * @author Saravanan.Dhurairaj  
	 * AnalyserNewUserId is class used for dbConncetion and set, get and return the retrieve from user table
	 * Procedure used dbo.spGetDuplicateValidationCount
	 * Table used Users
	 * finally close our connection
	 */
	
	private static class AnalyserNewEmployeeId implements Work{
		private Logger logger = LoggerFactory.getLogger(this.getClass());
		String result;
		String newEmployeeId;
		String EMPLID="EMPLID";
		/**
		 * @param newEmployeeId
		 */
		public AnalyserNewEmployeeId(String newEmployeeId)
		{
			super();
			this.newEmployeeId = newEmployeeId;
			
		}
		/**
		 * @return the result
		 */
		public String getResult()
		{
			return result;
		}
		@Override
		public void execute(Connection connection) throws SQLException {
			// TODO Auto-generated method stub			
			CallableStatement callStmt = null;
			ResultSet rs = null;									
			try{
				if (connection != null)
				{
						String query = "";
						query = "{call " + FacesUtils.SCHEMA_WIRELESS + ".spGetDuplicateValidationCount('" + newEmployeeId + "','"+EMPLID+"')}";						
						System.out.println("query " + query);
						logger.debug("Query is spGetDuplicateValidationCount : " + query);						
						callStmt = connection.prepareCall(query);
						rs = callStmt.executeQuery();
						if(rs != null){
							while(rs.next()){
								 result=rs.getString("RESULT");
								}
						}
						callStmt.close();
						rs.close();
						callStmt = null;
						rs = null;
				}
			}catch (Exception ex) {
				System.out.println("Exception in retrieve Employee Id");
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
	 * @author Saravanan.Dhurairaj  
	 * getAllManagerId is list of string method.
	 * It is used to get manager id value  
	 * Procedure used dbo.spGetDisysManagerList
	 * Table used USERS
	 * return the list value
	 */
	
	@Override
	public List<String> getAllManagerId(String userId) 
	{
		Session session = entityManager.unwrap(Session.class);
		AnalyserGetAllManagerId work = new AnalyserGetAllManagerId(userId);
		session.doWork(work);
		List<String> list = work.getList();
		if (list != null)
		{
			return list;
		}
		return null;
	}
	
	/**
	 * @author Saravanan.Dhurairaj  
	 * AnalyserGetAllManagerId is class used for dbConncetion and set, get and return the retrieve from USERS table
	 * Procedure used wireless.spGetDisysManagerList
	 * Table used USERS
	 * finally close our connection
	 */
	private static class AnalyserGetAllManagerId implements Work{
		private Logger logger = LoggerFactory.getLogger(this.getClass());
		List<String> list;
		String userId;
		/**
		 * @param userId
		 */
		public AnalyserGetAllManagerId(String userId)
		{
			super();
			this.userId = userId;
			
		}
		
		
		public List<String> getList() {
			// TODO Auto-generated method stub
			return list;
		}


		@Override
		public void execute(Connection connection) throws SQLException {
			// TODO Auto-generated method stub
			list = new ArrayList<String>();
			CallableStatement callStmt = null;
			ResultSet rs = null;
			try{
				if (connection != null)
				{
						String query = "";
						query = "{call " + FacesUtils.SCHEMA_DBO + ".spGetDisysManagerList('" + userId + "')}";						
						System.out.println("query " + query);
						logger.debug("Query is GetAllManagerId : " + query);						
						callStmt = connection.prepareCall(query);
						rs = callStmt.executeQuery();
						if(rs != null){
							while(rs.next()){	
								String value=rs.getString("USER_ID");							
								list.add(value);
							}
						}
						callStmt.close();
						rs.close();
						callStmt = null;
						rs = null;
				}
			}catch (Exception ex) {
				System.out.println("Exception in retrieve GetAllManagerId");
				ex.printStackTrace();
			}
			finally
			{
				if (rs != null) rs.close();
				if (callStmt != null) callStmt.close();
			}
		}

	}
	@Override
	public String addUpdateUser(User user, String loggedUserId, int actionType) {
		Session session = entityManager.unwrap(Session.class);
		String action = "Add";
		ApplicationUserCRUDWork work = new ApplicationUserCRUDWork(user,
				loggedUserId, actionType, action);
		
		session.doWork(work);
		String result = work.getResult();	
		return result;
	}

	private static class ApplicationUserCRUDWork implements Work {
		public Logger logger = LoggerFactory.getLogger(getClass());
		CallableStatement cstmt = null;
		ResultSet rs = null;
		String result = "0";
		private User user;
		private String loggedUserId;
		private String userId;
		private int actionType;

		private String action;

		public ApplicationUserCRUDWork(String userId, String loggedUserId,
				String action) {
			this.userId = userId;
			this.loggedUserId = loggedUserId;
			this.action = action;
		}

		public ApplicationUserCRUDWork(User user, String loggedUserId,
				int actionType, String action) {
			this.user = user;
			this.loggedUserId = loggedUserId;
			this.actionType = actionType;
			this.action = action;
		}

		@Override
		public void execute(Connection connection) throws SQLException {

			try {
				String query = null;
				if (action.equals("Add")) {
					String userId = user.getUserId();
					/*
					if(user.getUserId() != null){
						result="User Id value must be unique, a user with the same Employee Id already exists in the system";
					}
					*/
					if (userId == null || userId.trim().equals("")) {
						userId = "null";
					}
					System.out
							.println("Executing Query in addupdateuser {call "
									+ FacesUtils.SCHEMA_WIRELESS
									+ ".spAddUpdateUsers"
									+ "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");

					/*
					 * commented since new record will always active and only active
					 * records can be modified int status = 0; if (user.getStatus())
					 * { status=1; }
					 */
					int status = 1;

					int overTime = 0;
					if (user.getOvertime()) {
						overTime = 1;
					}

					if (user.getAutoExpenseLimit() == null || user.getAutoExpenseLimit().trim().equals("")) {
						user.setAutoExpenseLimit("0");
					}

					if (user.getOtherExpenseLimit() == null || user.getOtherExpenseLimit().trim().equals("")) {
						user.setOtherExpenseLimit("0");
					}

					if (user.getLunchHour() == null || user.getLunchHour().trim().equals("")) {						
						user.setLunchHour("0");
					}
					
					System.out.println("first Name " + user.getFirstName());
					System.out.println("last Name " + user.getLastName());
					System.out.println("Employee ID " + user.getADPCode());
					
					StringBuffer sb = new StringBuffer("{call "
							+ FacesUtils.SCHEMA_WIRELESS + ".spAddUpdateUsers(");
					sb.append(actionType + ",'");
					sb.append(loggedUserId + "','");
					sb.append(userId + "','");
					
					if (actionType == 1) {
						sb.append(PasswordBasedEncryption.getInstance()
								.encrypt(user.getPassword()) + "','");
					} else {
						sb.append(user.getPassword() + "','");
					}
					sb.append(user.getLastName() + "','");
					sb.append(user.getFirstName() + "','");
					sb.append(user.getAddr1() + "','");
					sb.append(user.getAddr2() + "','");
					sb.append(user.getCity() + "','");
					sb.append(user.getState() + "','");
					sb.append(user.getZip() + "','");
					sb.append(user.getAlternateEmail() + "','");
					sb.append(user.getPhone() + "','");
					sb.append(status + "','");
					sb.append(user.getRoleId() + "',");
					sb.append(user.getTimeZone() + ",'");
					sb.append(user.getLunchHour() + "',");
					sb.append(user.getAutoExpenseLimit() + ",");
					sb.append(user.getOtherExpenseLimit() + ",'");
					sb.append(overTime + "', '");
					sb.append(user.getManagerId() + "', '");
					sb.append(user.getADPCode() + "', '");
					sb.append(user.getPayrollCode() + "',");
					sb.append(user.getLocation() + ", '");
					sb.append(user.getCompanyCode() + "')}");

					System.out.println(" The procedure for user add update is "
							+ sb.toString());

					logger.debug(" The procedure for user add update is "
							+ sb.toString());

					cstmt = connection.prepareCall(sb.toString());

					rs = cstmt.executeQuery();
					
					if (rs != null) {
						
						while (rs.next()) {
							
							result = rs.getString(1);
							
						}
					} else {
						throw new Exception("Customer could not be inserted");
					}
				} else if (action.equals("Delete")) {
					query = "{call " + FacesUtils.SCHEMA_WIRELESS
							+ ".spDeleteUsers('" + userId + "','"
							+ loggedUserId + "')}";

					System.out.println(" The procedure for deleting user is "
							+ query);

					logger.debug(" The procedure for deleting user is " + query);

					cstmt = connection.prepareCall(query);
					rs = cstmt.executeQuery();

					if (rs != null) {
						if (rs.next()) {
							result = rs.getString(1);
						}
					} else {
						result = "1";
					}
				}

			} catch (Exception ex) {
				result = "Error occured while create/modify user.";
				System.out.println("Exception in addUpdateUser method");
				ex.printStackTrace();
				if (action.equals("Delete")) {
					result = "1";
				}
			} finally {
				if (rs != null)
					rs.close();
				if (cstmt != null)
					cstmt.close();
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
	public String setGenericStatus(String userId, String uniqueRowId1,
			String uniqueRowId2, String idType1, String idType2,
			String newStatus, String remarks, String mode) {
		Session session = entityManager.unwrap(Session.class);
		// String action = "WorkforceShifts";
		ApplicationMiscelleneousWork work = new ApplicationMiscelleneousWork(
				userId, uniqueRowId1, uniqueRowId2, idType1, idType2,
				newStatus, remarks, mode);
		session.doWork(work);
		String result = work.getResult();
		return result;
	}

	private static class ApplicationMiscelleneousWork implements Work {

		private Logger logger = LoggerFactory.getLogger(getClass());

		String userId;
		String uniqueRowId1;
		String uniqueRowId2;
		String idType1;
		String idType2;
		String newStatus;
		String remarks;
		String mode;

		String result = "";

		public ApplicationMiscelleneousWork(String userId, String uniqueRowId1,
				String uniqueRowId2, String idType1, String idType2,
				String newStatus, String remarks, String mode) {
			super();
			this.userId = userId;
			this.uniqueRowId1 = uniqueRowId1;
			this.uniqueRowId2 = uniqueRowId2;
			this.idType1 = idType1;
			this.idType2 = idType2;
			this.newStatus = newStatus;
			this.remarks = remarks;
			this.mode = mode;
		}

		@Override
		public void execute(Connection connection) throws SQLException {
			CallableStatement callStmt = null;
			ResultSet rs = null;
			result = "0";
			try {
				if (connection != null) {
					String query = "";
					query = "{call " + FacesUtils.SCHEMA_WIRELESS
							+ ".spSetGenericStatus('" + userId + "', '"
							+ uniqueRowId1 + "', '" + uniqueRowId2 + "', '"
							+ idType1 + "', '" + idType2 + "', '" + newStatus
							+ "', '" + Util.replaceSingleQuote(remarks)
							+ "', '" + mode + "')}";

					System.out
							.println("Executing Query in ApplicationMiscelleneousWork :  "
									+ query);

					logger.info("ApplicationMiscelleneousWork Query is : "
							+ query);

					callStmt = connection.prepareCall(query);
					rs = callStmt.executeQuery();
					if (rs != null) {
						if (rs.next()) {
							result = rs.getString("Result");
						}
						callStmt.close();
						rs.close();
						callStmt = null;
						rs = null;
					}
				} else {
					throw new Exception("Connection is null");
				}
			} catch (Exception ex) {
				System.out
						.println("Exception in setGenericStatus method of ApplicationMiscelleneousWork");
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
	public String setRandomUserPassword(String userId) {
		Session session = entityManager.unwrap(Session.class);
		String action = PASSWORD_RESET;
		UserPasswordResetWork work = new UserPasswordResetWork(userId, action);
		session.doWork(work);
		String result = work.getResult();
		return result;
	}
	
	
	@Override
	public String modifyUserPassword(String userId, String password) {
		Session session = entityManager.unwrap(Session.class);
		String action = PASSWORD_MODIFY;
		UserPasswordResetWork work = new UserPasswordResetWork(userId,password, action);
		session.doWork(work);
		String result = work.getResult();
		return result;
	}

	private static class UserPasswordResetWork implements Work {

		private Logger logger = LoggerFactory.getLogger(getClass());

		String userId;
		String action;
		String password;

		String result = "0";
		String randomPassword = "";
		String randomEncryptedPassword = "";

		public UserPasswordResetWork(String userId, String action) {
			super();
			this.userId = userId;
			this.action = action;
		}
		
		public UserPasswordResetWork(String userId, String password, String action) {
			super();
			this.userId = userId;
			this.password = password;
			this.action = action;
		}

		@Override
		public void execute(Connection connection) throws SQLException {
			CallableStatement callStmt = null;
			ResultSet rs = null;
			PreparedStatement pstmt = null;
			result = "0";
			try {
				if (connection != null) {
					String query = "";
					
					if(action.equals(PASSWORD_RESET)){
						randomPassword = PasswordBasedEncryption.getInstance().generateRandomPassword();
						System.out.println("Random password is " + randomPassword);
						randomEncryptedPassword = PasswordBasedEncryption
								.getInstance().encrypt(randomPassword);
						
						query = "{call " + FacesUtils.SCHEMA_WIRELESS
								+ ".spProcessRandomPassword('" + userId + "','"
								+ randomPassword + "','" + randomEncryptedPassword
								+ "')}";

						System.out
								.println("Executing Query in UserPasswordResetWork :  "
										+ query);

						logger.info("UserPasswordResetWork Query is : " + query);

						try{
							callStmt = connection.prepareCall(query);
							boolean gotResults = callStmt.execute();
							if (!gotResults)
							{
								result = "0";
							}
							else
							{
								rs = callStmt.getResultSet();
								if (rs != null) {
									if (rs.next()) {
										result = rs.getString(1);
									}
									callStmt.close();
									rs.close();
									callStmt = null;
									rs = null;
								}
							}
						}catch(Exception ex){
							
						}finally
						{
							if (rs != null) rs.close();
							if (callStmt != null) callStmt.close();
						}
					}else if(action.equals(PASSWORD_MODIFY)){
						StringBuffer sb = new StringBuffer("update Users ");
						sb.append("set password=? where user_id =  ?");
						System.out
								.println(" The procedure for user password update is "
										+ sb.toString());
						pstmt = connection.prepareStatement(sb.toString());
						pstmt.setString(1, password);
						pstmt.setString(2, userId);
						int count = pstmt.executeUpdate();
						if (count == 1) {
							result = "0";
						} else {
							throw new Exception("password could not be updated");
						}
					}
				} else {
					throw new Exception("Connection is null");
				}
			} catch (Exception ex) {
				System.out
						.println("Exception in setRandomUserPassword method of UserPasswordResetWork");
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
	public List<UserDTO> spGetUserListForPriviledges(String userId)
	{
		Session session = entityManager.unwrap(Session.class);
		UserListForPriviledgesWork work = new UserListForPriviledgesWork(userId);
		session.doWork(work);
		List<UserDTO> list = work.getList();
		return list;
	}
	
	private static class UserListForPriviledgesWork implements Work
	{
		private Logger logger = LoggerFactory.getLogger(getClass());
		private List<UserDTO> list;
		private UserDTO userDTO;
		String userId;
		ResultSet rs = null;
		
		public UserListForPriviledgesWork(String userId)
		{
			this.userId = userId;
		}
		@Override
		public void execute(Connection connection) throws SQLException
		{
			list = new ArrayList<UserDTO>();
			userDTO = new UserDTO();
			CallableStatement cstmt = null;			
			String query = "{call " + FacesUtils.SCHEMA_WIRELESS
					+ ".spGetUserListForPriviledges('" + userId + "')}";
			System.out.println("Query in spGetUserListForPriviledges " + query);
			logger.debug("Query in spGetUserListForPriviledges " + query);

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


