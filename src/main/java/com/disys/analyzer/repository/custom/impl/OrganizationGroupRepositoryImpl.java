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
import java.util.HashMap;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Session;
import org.hibernate.jdbc.Work;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.disys.analyzer.config.util.FacesUtils;
import com.disys.analyzer.model.OrganizationGroup;
import com.disys.analyzer.model.User;
import com.disys.analyzer.repository.custom.OrganizationGroupRepositoryCustom;

/**
 * @author Sajid
 * 
 */
@Service
@Transactional(readOnly = true)
public class OrganizationGroupRepositoryImpl implements
		OrganizationGroupRepositoryCustom {

	public Logger logger = LoggerFactory.getLogger(getClass());

	public static String GROUP_ACTION = "Group";
	public static String GROUP_ADD_UPDATE = "AddUpdateGroup";
	public static String GROUP_DELETE = "DeleteGroup";

	public static String GROUP_USER = "UsersGroup";
	public static String GROUP_USERS_LIST = "GroupUsersList";

	public static String ADD_UPDATE_GROUP = " {call "
			+ FacesUtils.SCHEMA_WIRELESS + ".spAddUpdateGroup(?,?,?,?,?)}";

	public static String ASSIGN_DEASSIGN_GROUP = " {call "
			+ FacesUtils.SCHEMA_WIRELESS + ".spAssignDeassignGroup(?,?,?,?,?)}";

	@PersistenceContext
	EntityManager entityManager;

	@Override
	public String addUpdateGroup(OrganizationGroup group, String userId,
			int type) {
		Session session = entityManager.unwrap(Session.class);
		GroupRepositoryWork work = new GroupRepositoryWork(group, userId, type,
				GROUP_ADD_UPDATE);
		session.doWork(work);
		String result = work.getResult();
		return result;
	}

	@Override
	public String deleteGroup(String groupId, String userId) {
		Session session = entityManager.unwrap(Session.class);
		GroupRepositoryWork work = new GroupRepositoryWork(groupId, userId,
				GROUP_DELETE);
		session.doWork(work);
		String result = work.getResult();
		return result;
	}

	private static class GroupRepositoryWork implements Work {
		public Logger logger = LoggerFactory.getLogger(getClass());

		private OrganizationGroup group;
		String userId;
		int type;
		String action;
		String groupId;

		CallableStatement cstmt = null;
		ResultSet rs = null;

		String result;

		/**
		 * @param userId
		 */
		public GroupRepositoryWork(OrganizationGroup group, String userId,
				int type, String action) {
			super();
			this.group = group;
			this.userId = userId;
			this.type = type;
			this.action = action;
		}

		public GroupRepositoryWork(String groupId, String userId, String action) {
			super();
			this.groupId = groupId;
			this.userId = userId;
			this.action = action;
		}

		@Override
		public void execute(Connection connection) throws SQLException {

			try {
				if (action.equals(GROUP_ADD_UPDATE)) {

					Integer groupId = group.getGroupId();
					if (groupId == null) {
						groupId = 0;
					}
					int active = 1;

					String query = "{call " + FacesUtils.SCHEMA_WIRELESS
							+ ".spAddUpdateGroup(" + type + ",'" + userId
							+ "'," + groupId + ",'"
							+ group.getGroupDescription() + "'," + active
							+ ")}";

					System.out.println("Executing Query in " + GROUP_ADD_UPDATE
							+ "" + query);

					logger.debug("Executing Query in " + GROUP_ADD_UPDATE + ""
							+ query);

					cstmt = connection.prepareCall(query);
					rs = cstmt.executeQuery();

				} else if (action.equals(GROUP_DELETE)) {
					String query = "{call " + FacesUtils.SCHEMA_WIRELESS
							+ ".spDeleteGroup('" + userId + "','" + groupId
							+ "')}";

					System.out.println("Query in deleteGroup " + query);

					logger.debug("Query in deleteGroup " + query);

					cstmt = connection.prepareCall(query);
					rs = cstmt.executeQuery();

					cstmt = connection.prepareCall(query);
					rs = cstmt.executeQuery();
				}

				if (action.equals(GROUP_ADD_UPDATE)) {
					if (rs != null) {
						while (rs.next()) {
							result = "" + rs.getInt(1);
						}
					} else {
						throw new Exception("Group CRUD operation failed");
					}
				} else if (action.equals(GROUP_DELETE)) {
					if (rs != null) {
						if (rs.next()) {
							result = rs.getString(1);
						}
					} else {
						result = "1";
					}
				}

			} catch (Exception e) {
				if (action.equals(GROUP_ADD_UPDATE)) {
					result = "1";
					System.out.println("Exception in " + GROUP_ADD_UPDATE);
					System.out
							.println("Exception in addUpdateGroup method of GroupSessionEJBBean");
				}
				e.printStackTrace();
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
	public List<User> getGroupUserList(String userId, boolean assignType,
			boolean userType, String groupId) {
		Session session = entityManager.unwrap(Session.class);
		ApplicationGroupUsersWork work = new ApplicationGroupUsersWork(userId,
				assignType, userType, groupId);
		session.doWork(work);
		List<User> result = work.getList();
		return result;
	}

	private static class ApplicationGroupUsersWork implements Work {
		public Logger logger = LoggerFactory.getLogger(getClass());
		private List<User> list;
		private User user;
		String userId;
		String groupId;
		boolean assignType;
		boolean userType;

		ResultSet rs = null;

		/**
		 * @param userId
		 * @param assignedDevice
		 */
		public ApplicationGroupUsersWork(String userId, boolean assignType,
				boolean userType, String groupId) {
			super();
			this.userId = userId;
			this.assignType = assignType;
			this.userType = userType;
			this.groupId = groupId;
		}

		@Override
		public void execute(Connection connection) throws SQLException {

			list = new ArrayList<User>();
			user = new User();
			PreparedStatement pstmt = null;
			// assign type is either, assign aur de assign
			// userType is either manager or user

			StringBuffer query = new StringBuffer();
			if (assignType) {
				query.append("select user_id, (last_name+','+first_name) as name ");
				query.append("from users where org_id = (select org_id from users ");
				query.append("where user_id = ?) ");
				query.append(" and user_id not in (select user_id from ");
				if (userType) {
					query.append(" manager_groups ");
				} else {
					query.append(" user_groups ");
				}
				query.append("where group_id =?) ");
			} else {
				query.append("select us.user_id, (us.last_name+','+us.first_name) ");
				query.append(" as name from users us, ");
				if (userType) {
					query.append(" manager_groups ugs ");
				} else {
					query.append(" user_groups ugs ");
				}
				query.append("where  us.user_id = ugs.user_id and ugs.group_id = ?");
			}

//			query.append(" order by 2");
			query.append("  and Password != 'terminated' order by 1");
			logger.debug("Query in Organization group repository impl "
					+ query.toString());
			pstmt = connection.prepareStatement(query.toString());
			if (assignType) {
				pstmt.setString(1, userId);
				pstmt.setString(2, groupId);
			} else {
				pstmt.setString(1, groupId);
			}

			rs = pstmt.executeQuery();

			try {

				if (rs != null) {
					while (rs.next()) {
						user.setUserId(rs.getString(1));
						user.setFirstName(rs.getString(2));
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
				if (pstmt != null)
					pstmt.close();
			}
		}

		/**
		 * @return the list
		 */
		public List<User> getList() {
			return list;
		}
	}

	@Override
	public String assignDeassignUserToGroup(String adminUserId, int assignType,
			int userType, String groupId, String userId) {
		Session session = entityManager.unwrap(Session.class);
		AssignGroupUsersWork work = new AssignGroupUsersWork(adminUserId,
				assignType, userType, groupId, userId);
		session.doWork(work);
		String result = work.getResult();
		return result;
	}

	private static class AssignGroupUsersWork implements Work {
		public Logger logger = LoggerFactory.getLogger(getClass());
		String userId;
		String groupId;
		int assignType;
		int userType;
		String adminUserId;
		String result = "0";

		ResultSet rs = null;
		CallableStatement callStmt = null;

		public AssignGroupUsersWork(String adminUserId, int assignType,
				int userType, String groupId, String userId) {
			this.adminUserId = adminUserId;
			this.assignType = assignType;
			this.userType = userType;
			this.groupId = groupId;
			this.userId = userId;
		}

		@Override
		public void execute(Connection connection) throws SQLException {

			try {
				System.out.println("Executing Query in assignDeassigngroup of "
						+ ASSIGN_DEASSIGN_GROUP);
				logger.debug("Executing Query in assignDeassigngroup of "
						+ ASSIGN_DEASSIGN_GROUP);

				callStmt = connection.prepareCall(ASSIGN_DEASSIGN_GROUP);
				callStmt.setString(1, adminUserId);
				callStmt.setInt(2, assignType);
				callStmt.setInt(3, userType);
				callStmt.setString(4, groupId);
				callStmt.setString(5, userId);
				rs = callStmt.executeQuery();
				if (rs != null) {
					while (rs.next()) {
						// System.out.println(" result " + rs.getInt(1));
						result = "" + rs.getInt(1);
					}
					callStmt.close();
					rs.close();
					callStmt = null;
					rs = null;
				} else {
					throw new Exception("Could not be asigned or dedassigned");
				}
			} catch (Exception ex) {
				ex.printStackTrace();
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
	public List<OrganizationGroup> getUserGroups(String userId) {
		Session session = entityManager.unwrap(Session.class);
		String action = GROUP_USER;
		OrganizationGroupWork work = new OrganizationGroupWork(userId, action);
		session.doWork(work);
		List<OrganizationGroup> list = work.getList();
		return list;
	}

	@Override
	public HashMap<Integer, String> getUsersForAGroup(String userId,
			String groupId) {
		HashMap<Integer, String> map = new HashMap<Integer, String>();
		String action = GROUP_USERS_LIST;
		Session session = entityManager.unwrap(Session.class);
		OrganizationGroupWork work = new OrganizationGroupWork(userId, groupId,
				action);
		session.doWork(work);
		map = work.getMap();
		return map;
	}

	private static class OrganizationGroupWork implements Work {
		public Logger logger = LoggerFactory.getLogger(getClass());
		String userId;
		String groupId;

		String action;

		ResultSet rs = null;
		CallableStatement callStmt = null;

		List<OrganizationGroup> list = null;
		OrganizationGroup group = null;
		HashMap<Integer, String> map = new HashMap<Integer, String>();

		public OrganizationGroupWork(String userId, String action) {
			this.userId = userId;
			this.action = action;
		}

		public OrganizationGroupWork(String userId, String groupId,
				String action) {
			this.userId = userId;
			this.groupId = groupId;
			this.action = action;
		}

		@Override
		public void execute(Connection connection) throws SQLException {
			try {
				if (action.equals(GROUP_USER)) {
					group = new OrganizationGroup();
					list = new ArrayList<OrganizationGroup>();
					String query = "{call " + FacesUtils.SCHEMA_WIRELESS
							+ ".spGetUserGroups('" + userId + "')}";

					System.out.println("Query in getUserGroups of " + query);
					logger.debug("Query in getUserGroups of " + query);
					callStmt = connection.prepareCall(query);
					rs = callStmt.executeQuery();

					rs = callStmt.executeQuery();
					if (rs != null) {
						while (rs.next()) {
							group = new OrganizationGroup();
							group.setGroupDescription(rs
									.getString("Group_Description"));
							group.setGroupId(rs.getInt("Group_ID"));
							list.add(group);
						}
						callStmt.close();
						rs.close();
						callStmt = null;
						rs = null;
					} else {
						throw new Exception("No User Record Found");
					}
				} else if (action.equals(GROUP_USERS_LIST)) {
					String query = "{call " + FacesUtils.SCHEMA_WIRELESS
							+ ".spGetUserForAGroup('" + userId + "', '"
							+ groupId + "')}";

					System.out.println("Query in getUserGroups of " + query);
					logger.debug("Query in getUserGroups of " + query);
					callStmt = connection.prepareCall(query);
					rs = callStmt.executeQuery();

					rs = callStmt.executeQuery();
					if (rs != null) {
						while (rs.next()) {
							map.put(rs.getInt("User_ID"),
									rs.getString("User_Name"));
						}
						callStmt.close();
						rs.close();
						callStmt = null;
						rs = null;
					} else {
						throw new Exception("No User Record Found");
					}
				}

			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}

		/**
		 * @return the list
		 */
		public List<OrganizationGroup> getList() {
			return list;
		}

		/**
		 * @return the map
		 */
		public HashMap<Integer, String> getMap() {
			return map;
		}

	}

}