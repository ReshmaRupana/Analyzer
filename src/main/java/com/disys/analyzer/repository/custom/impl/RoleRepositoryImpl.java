/**
 * 
 */
package com.disys.analyzer.repository.custom.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
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
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.disys.analyzer.config.util.FacesUtils;
import com.disys.analyzer.model.Role;
import com.disys.analyzer.repository.custom.RoleRepositoryCustom;

/**
 * @author Sajid
 * 
 */
@Repository
@Transactional(readOnly = true)
public class RoleRepositoryImpl implements RoleRepositoryCustom {

	public Logger logger = LoggerFactory.getLogger(getClass());

	public static String ROLE_LIST_ACTION = "RoleList";
	public static String ROLE_ACTION = "Role";
	public static String ROLE_ADD_UPDATE = "AddUpdateRole";
	public static String USER_ROLE	= "UserRole";

	@PersistenceContext
	EntityManager entityManager;

	@Override
	public List<Role> getRoleList(String userId, int type) {
		Session session = entityManager.unwrap(Session.class);
		RoleRepositoryWork work = new RoleRepositoryWork(userId, type,
				ROLE_LIST_ACTION);
		session.doWork(work);
		List<Role> list = work.getList();
		return list;
	}

	@Override
	public Role getRole(String userId, Integer roleId) {
		Session session = entityManager.unwrap(Session.class);
		RoleRepositoryWork work = new RoleRepositoryWork(userId, roleId,
				ROLE_ACTION);
		session.doWork(work);
		List<Role> list = work.getList();
		if (list != null) {
			return list.get(0);
		}
		return new Role();
	}
	

	@Override
	public Role getUserRole(String userId) {
		Session session = entityManager.unwrap(Session.class);
		RoleRepositoryWork work = new RoleRepositoryWork(userId,
				USER_ROLE);
		session.doWork(work);
		List<Role> list = work.getList();
		if (list != null) {
			return list.get(0);
		}
		return new Role();
	}

	@Override
	public String addUpdateRole(Integer roleId, String roleDesc, String userId,int actionType) {
		Session session = entityManager.unwrap(Session.class);
		RoleRepositoryWork work = new RoleRepositoryWork(roleId, roleDesc, userId, actionType, ROLE_ADD_UPDATE);
		session.doWork(work);
		return work.getResult();
	}
	
	private static class RoleRepositoryWork implements Work {
		public Logger logger = LoggerFactory.getLogger(getClass());
		private List<Role> list;
		private Role role;
		String userId;
		int type;
		String action;
		
		CallableStatement cstmt = null;
		ResultSet rs = null;
		
		String result;
		Integer roleId;
		String roleDesc;

		/**
		 * @param userId
		 */
		public RoleRepositoryWork(String userId, String action) {
			super();
			this.userId = userId;
			this.action = action;
		}
		
		/**
		 * @param userId
		 * @param type
		 */
		public RoleRepositoryWork(String userId, int type, String action) {
			super();
			this.userId = userId;
			this.type = type;
			this.action = action;
		}

		public RoleRepositoryWork(Integer roleId, String roleDesc,
				String userId, int actionType, String action) {
			super();
			this.roleId = roleId;
			this.roleDesc = roleDesc;
			this.userId = userId;
			this.type = actionType;
			this.action = action;
		}

		@Override
		public void execute(Connection connection) throws SQLException {

			list = new ArrayList<Role>();
			role = new Role();

			if (action.equals(ROLE_ACTION)) {
				String query = "{call " + FacesUtils.SCHEMA_WIRELESS
						+ ".spGetRole('" + userId + "'," + type + ")}";

				System.out.println("Query in getRole " + query);

				logger.debug("Query in getRole " + query);

				cstmt = connection.prepareCall(query);
				rs = cstmt.executeQuery();

			} else if (action.equals(ROLE_LIST_ACTION)) {
				String query = "{call " + FacesUtils.SCHEMA_WIRELESS
						+ ".spGetRoleList('" + userId + "'," + type + ")}";

				System.out.println("Query in getRoleList " + query);
				logger.debug("Query in getRoleList " + query);

				cstmt = connection.prepareCall(query);
				rs = cstmt.executeQuery();
			}else  if (action.equals(ROLE_ADD_UPDATE)) {
				String query = "{call " + FacesUtils.SCHEMA_WIRELESS
						+ ".spAddUpdateRole(" + type + ",'"
						+ userId + "'," + roleId + ",'" + roleDesc + "')}";

				System.out.println("Query in "+ROLE_ADD_UPDATE+" " + query);
				logger.debug("Query in "+ROLE_ADD_UPDATE+" " + query);

				cstmt = connection.prepareCall(query);
				rs = cstmt.executeQuery();
			}else if (action.equals(USER_ROLE)) {
				String query = "{call " + FacesUtils.SCHEMA_WIRELESS
						+ ".spGetUserRole('" + userId + "')}";

				System.out.println("Query in "+USER_ROLE+" " + query);

				logger.debug("Query in "+USER_ROLE+" " + query);

				cstmt = connection.prepareCall(query);
				rs = cstmt.executeQuery();

			}

			try {
				if(action.equals(ROLE_ADD_UPDATE)){
					if (rs != null) {
						while (rs.next()) {
							result = "" + rs.getInt(1);
						}
					} else {
						throw new Exception("Role could not be inserted");
					}
				}else {
					if (rs != null) {
						while (rs.next()) {
							role.setRoleId(rs.getInt(1));
							role.setRoleDesc(rs.getString(2));
							list.add(role);
							role = new Role();
						}
					} else {
						list = new ArrayList<Role>();
					}
					System.out.println("List size in RoleRepositoryWork "
							+ list.size());
				}
				
			} catch (Exception e) {
				if(action.equals(ROLE_ADD_UPDATE)){
					result = "1";
					System.out.println("Exception in "+ROLE_ADD_UPDATE);
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
		 * @return the list
		 */
		public List<Role> getList() {
			return list;
		}

		/**
		 * @return the result
		 */
		public String getResult() {
			return result;
		}
	}

}