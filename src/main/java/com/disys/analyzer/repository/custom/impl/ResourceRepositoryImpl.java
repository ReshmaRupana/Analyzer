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

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.jdbc.Work;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.disys.analyzer.config.util.FacesUtils;
import com.disys.analyzer.model.Resource;
import com.disys.analyzer.repository.custom.ResourceRepositoryCustom;
import com.disys.analyzer.security.service.util.PasswordBasedEncryption;

/**
 * @author Sajid
 * 
 */
@Repository
@Transactional(readOnly = true)
public class ResourceRepositoryImpl implements ResourceRepositoryCustom
{
	
	public Logger logger = LoggerFactory.getLogger(getClass());
	
	public static String RESOURCE_LIST_ACTION = "ResourceList";
	public static String RESOURCE_ACTION = "Resource";
	public static String SET_PRIVILEGE = "SetPrivilege";
	public static String ALL_RESOURCES_WITH_PARENTS = "allresourceswithparents";
	public static String CUSTOM_USER_PRIVILEGES = "customuserprivileges";
	public static String LOGIN_USER_PRIVILEGES = "loginuserprivileges";
	
	@PersistenceContext EntityManager entityManager;
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.disys.analyzer.repository.custom.ResourceRepositoryCustom#
	 * getResourcePrivileges(java.lang.String, java.lang.Integer)
	 */
	@Override
	public List<Resource> getRolePrivileges(String userId, Integer resourceId)
	{
		Session session = entityManager.unwrap(Session.class);
		ResourceRepositoryWork work = new ResourceRepositoryWork(userId, resourceId, RESOURCE_LIST_ACTION);
		session.doWork(work);
		List<Resource> list = work.getList();
		return list;
	}
	
	@Override
	public String setPrivilege(String userId, Integer roleId, Integer resId, int act)
	{
		Session session = entityManager.unwrap(Session.class);
		ResourceRepositoryWork work = new ResourceRepositoryWork(userId, roleId, resId, act, SET_PRIVILEGE);
		session.doWork(work);
		return work.getResult();
	}
	
	private static class ResourceRepositoryWork implements Work
	{
		public Logger logger = LoggerFactory.getLogger(getClass());
		private List<Resource> list;
		private Resource resource;
		String userId;
		Integer roleId;
		String action;
		Integer actionType;
		Integer resId;
		int act;
		CallableStatement cstmt = null;
		ResultSet rs = null;
		String result;
		
		public ResourceRepositoryWork(String userId, Integer roleId, String action)
		{
			super();
			this.userId = userId;
			this.roleId = roleId;
			this.action = action;
			this.actionType = roleId;
		}
		
		public ResourceRepositoryWork(String userId, Integer roleId, Integer resId, int act, String action)
		{
			super();
			this.userId = userId;
			this.roleId = roleId;
			this.resId = resId;
			this.act = act;
			this.action = action;
		}
		
		@Override
		public void execute(Connection connection) throws SQLException
		{
			
			list = new ArrayList<Resource>();
			resource = new Resource();
			
			if (action.equals(RESOURCE_LIST_ACTION))
			{
				String query = "{call " + FacesUtils.SCHEMA_WIRELESS + ".spGetRolePrivileges('" + userId + "'," + roleId + ")}";
				
				System.out.println("Query in getResourceList " + query);
				logger.debug("Query in getResourceList " + query);
				
				cstmt = connection.prepareCall(query);
				rs = cstmt.executeQuery();
			}
			if (action.equals(LOGIN_USER_PRIVILEGES))
			{
				String query = "{call " + FacesUtils.SCHEMA_WIRELESS + ".spGetLoginUserRolePrivileges('" + userId + "'," + roleId + ")}";
				System.out.println("Query in spGetLoginUserRolePrivileges " + query);
				logger.debug("Query in spGetLoginUserRolePrivileges " + query);
				cstmt = connection.prepareCall(query);
				rs = cstmt.executeQuery();
			}
			else if (action.equals(ALL_RESOURCES_WITH_PARENTS))
			{
				String query = "{call " + FacesUtils.SCHEMA_WIRELESS + ".spGetAllResourceWithParent('" + userId + "')}";
				System.out.println("Query in spGetAllResourceWithParent " + query);
				logger.debug("Query in spGetAllResourceWithParent " + query);
				cstmt = connection.prepareCall(query);
				rs = cstmt.executeQuery();
			}
			else if (action.equals(CUSTOM_USER_PRIVILEGES))
			{
				String query = "{call " + FacesUtils.SCHEMA_WIRELESS + ".spGetUserRolePrivileges('" + userId + "'," + actionType + ")}";
				System.out.println("Query in spGetUserRolePrivileges " + query);
				logger.debug("Query in spGetUserRolePrivileges " + query);
				cstmt = connection.prepareCall(query);
				rs = cstmt.executeQuery();
			}
			else if (action.equals(SET_PRIVILEGE))
			{
				String query = "{call " + FacesUtils.SCHEMA_WIRELESS + ".spSetPrivilege('" + userId + "'," + roleId + "," + resId + "," + act + ")}";
				
				System.out.println("Query in " + SET_PRIVILEGE + " " + query);
				logger.debug("Query in " + SET_PRIVILEGE + " " + query);
				
				cstmt = connection.prepareCall(query);
				rs = cstmt.executeQuery();
			}
			
			try
			{
				if (action.equals(RESOURCE_LIST_ACTION) || action.equals(LOGIN_USER_PRIVILEGES))
				{
					if (rs != null)
					{
						while (rs.next())
						{
							resource.setResId(rs.getInt(2));
							resource.setResDesc(rs.getString(3));
							resource.setParentId(rs.getInt(5));
							resource.setFirstParent(rs.getString(6));
							resource.setPageName(rs.getString(7));
							resource.setMenuItem(rs.getInt(8));
							resource.setStatus(rs.getInt(9));
							resource.setSecondParent(rs.getString(10));
							resource.setTitleId(rs.getInt(11));
							if(action.equalsIgnoreCase(LOGIN_USER_PRIVILEGES))
							{
								resource.setSecondParentId( rs.getInt(12));
							}
							if (resource.getParentId() == null)
							{
								resource.setParentId(-2);
							}
							if(resource.getSecondParentId() == null)
							{
								resource.setSecondParentId(0);
							}
							if (resource.getFirstParent() != null && resource.getFirstParent().trim().length() > 0)
							{
								if (resource.getSecondParent() == null || resource.getSecondParent().trim().length() == 0)
								{
									resource.setSecondParent("");
								}
								String name = resource.getSecondParent().length() > 0 ? resource.getFirstParent() + " / " + resource.getSecondParent()
								        : resource.getFirstParent();
								resource.setResWithParentName(resource.getResDesc() + " - (" + name + ")");
							}
							else
							{
								resource.setFirstParent("");
								resource.setSecondParent("");
								resource.setResWithParentName(resource.getResDesc());
							}
							list.add(resource);
							resource = new Resource();
						}
					}
					else
					{
						list = new ArrayList<Resource>(0);
					}
					System.out.println("List size in ResourceRepositoryWork " + list.size());
				}
				else if (action.equals(ALL_RESOURCES_WITH_PARENTS))
				{
					if (rs != null)
					{
						while (rs.next())
						{
							resource.setResId(rs.getInt(2));
							resource.setResDesc(rs.getString(3));
							resource.setParentId(rs.getInt(5));
							resource.setFirstParent(rs.getString(6));
							resource.setStatus(rs.getInt(7));
							resource.setSecondParent(rs.getString(8));
							resource.setTitleId(rs.getInt(9));
							if (resource.getFirstParent() != null && resource.getFirstParent().trim().length() > 0)
							{
								if (resource.getSecondParent() == null || resource.getSecondParent().trim().length() == 0)
								{
									resource.setSecondParent("");
								}
								String name = resource.getSecondParent().length() > 0 ? resource.getFirstParent() + "/" + resource.getSecondParent()
								        : resource.getFirstParent();
								resource.setResWithParentName(resource.getResDesc() + " - (" + name + ")");
							}
							else
							{
								resource.setFirstParent("");
								resource.setSecondParent("");
								resource.setResWithParentName(resource.getResDesc());
							}
							if (resource.getParentId() != -1)
							{
								if(resource.getParentId() > 0 && resource.getTitleId() == 0)
								{
									System.out.println("Resource Name :: " + resource.getResDesc());
									continue;
								}
								list.add(resource);
							}
							resource = new Resource();
						}
					}
					else
					{
						list = new ArrayList<Resource>(0);
					}
					System.out.println("List size in ResourceRepositoryWork " + list.size());
				}
				else if (action.equals(CUSTOM_USER_PRIVILEGES))
				{
					if (rs != null)
					{
						while (rs.next())
						{
							resource.setResId(rs.getInt(1));
							resource.setResDesc(rs.getString(2));
							resource.setFirstParent(rs.getString(3));
							resource.setSecondParent(rs.getString(4));
							resource.setAssigned(rs.getInt(5));
							resource.setParentId(rs.getInt(6));
							if (resource.getFirstParent() != null && resource.getFirstParent().trim().length() > 0)
							{
								if (resource.getSecondParent() == null || resource.getSecondParent().trim().length() == 0)
								{
									resource.setSecondParent("");
								}
								String name = resource.getSecondParent().length() > 0 ? resource.getFirstParent() + "/" + resource.getSecondParent()
								        : resource.getFirstParent();
								resource.setResWithParentName(resource.getResDesc() + " - (" + name + ")");
							}
							else
							{
								resource.setFirstParent("");
								resource.setSecondParent("");
								resource.setResWithParentName(resource.getResDesc());
							}
							list.add(resource);
							resource = new Resource();
						}
					}
					else
					{
						list = new ArrayList<Resource>(0);
					}
					System.out.println("List size in ResourceRepositoryWork " + list.size());
				}
				else if (action.equals(SET_PRIVILEGE))
				{
					if (rs != null)
					{
						while (rs.next())
						{
							result = "" + rs.getInt(1);
						}
					}
					else
					{
						throw new Exception("Privilege could not be set");
					}
				}
				
			}
			catch (Exception e)
			{
				e.printStackTrace();
				if (action.equals(SET_PRIVILEGE))
				{
					result = "1";
				}
			}
			finally
			{
				if (rs != null) rs.close();
				if (cstmt != null) cstmt.close();
			}
		}
		
		/**
		 * @return the list
		 */
		public List<Resource> getList()
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
	public Integer getMaxPK()
	{
		Session session = entityManager.unwrap(Session.class);
		return (Integer) session.createQuery("select max(model.resId) from Resource as model").uniqueResult();
	}
	
	@Override
	public boolean isRecordUnique(String resDesc, Integer resId)
	{
		StringBuilder stb = new StringBuilder(100);
		stb.append("select model.resId from Resource as model ");
		stb.append(" where trim(lower(model.resDesc)) = :resDesc ");
		if (resId != null)
		{
			stb.append(" and model.resId <> :resId ");
		}
		Session session = entityManager.unwrap(Session.class);
		Query query = session.createQuery(stb.toString());
		query.setParameter("resDesc", resDesc.trim().toLowerCase());
		if (resId != null)
		{
			query.setParameter("resId", resId);
		}
		query.setMaxResults(1);
		return query.uniqueResult() == null;
	}
	
	@Override
	public boolean isReferenceFound(Integer resId)
	{
		StringBuilder stb = new StringBuilder(100);
		stb.append("select model.id.resId from RolePrivilege as model ");
		stb.append(" where model.id.resId = :resId ");
		Session session = entityManager.unwrap(Session.class);
		Query query = session.createQuery(stb.toString());
		query.setParameter("resId", resId);
		query.setMaxResults(1);
		return query.uniqueResult() != null;
	}
	
	@Override
	public List<Resource> getParentResources()
	{
		StringBuilder stb = new StringBuilder(100);
		stb.append("select model from Resource as model ");
		stb.append(" where model.parentId = -1 order by resId ");
		return entityManager.createQuery(stb.toString()).getResultList();
	}
	
	@Override
	public String spAddUpdateResources(String userId, Resource resource, Integer actionType)
	{
		Session session = entityManager.unwrap(Session.class);
		ResourceAddUpdateWork work = new ResourceAddUpdateWork(userId, resource, actionType);
		session.doWork(work);
		String result = work.getResult();
		return result;
	}
	
	private static class ResourceAddUpdateWork implements Work
	{
		public Logger logger = LoggerFactory.getLogger(getClass());
		String userId;
		String resDesc;
		Integer parentId;
		Integer titleId;
		Integer actionType;
		Integer menuItem;
		String result = "1";
		
		public ResourceAddUpdateWork(String userId, Resource resource, Integer actionType)
		{
			super();
			this.actionType = actionType;
			this.userId = userId;
			this.parentId = resource.getParentId();
			this.titleId = resource.getTitleId();
			this.resDesc = resource.getResDesc();
			this.menuItem = resource.getMenuItem();
		}
		
		@Override
		public void execute(Connection connection) throws SQLException
		{
			CallableStatement cs = null;
			ResultSet rs = null;
			try
			{
				String insertStoreProc = "{call " + FacesUtils.SCHEMA_WIRELESS + ".spAddUpdateResources(?,?,?,?,?,?)}";
				cs = connection.prepareCall(insertStoreProc);
				
				StringBuilder queryToAddUpdateResource = new StringBuilder();
				queryToAddUpdateResource
				        .append("USE [Analyser] GO DECLARE	@return_value int EXEC	@return_value = [wireless].[spAddUpdateResources]");
				
				// @intActionType
				cs.setInt(1, actionType);
				queryToAddUpdateResource.append("@intActionType = " + actionType + ",");
				
				// @varLoggedUserID
				cs.setString(2, userId);
				queryToAddUpdateResource.append("@varLoggedUserID = N'" + userId + "',");
				
				// @intParentID
				cs.setInt(3, parentId);
				queryToAddUpdateResource.append("@intParentID = " + parentId + ",");
				
				// @intTitleID
				cs.setInt(4, titleId);
				queryToAddUpdateResource.append("@intTitleID = " + titleId + ",");
				
				// @varResDesc
				cs.setString(5, resDesc);
				queryToAddUpdateResource.append("@varResDesc = N'" + resDesc + "',");
				
				// intMenuItem
				cs.setInt(6, menuItem);
				queryToAddUpdateResource.append("@intMenuItem = " + menuItem + " ");
				
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
		
		/**
		 * @return the result
		 */
		public String getResult()
		{
			return result;
		}
	}
	
	@Override
	public List<Resource> findAllWithParent(String userId)
	{
		Session session = entityManager.unwrap(Session.class);
		ResourceRepositoryWork work = new ResourceRepositoryWork(userId, 0, ALL_RESOURCES_WITH_PARENTS);
		session.doWork(work);
		return work.getList();
	}
	
	@Override
	public List<Resource> spGetUserRolePrivileges(String userId, Integer actionType)
	{
		Session session = entityManager.unwrap(Session.class);
		ResourceRepositoryWork work = new ResourceRepositoryWork(userId, actionType, CUSTOM_USER_PRIVILEGES);
		session.doWork(work);
		return work.getList();
	}

	@Override
	public String spAddCustomUserPrivileges(String loggedInUser, String userId, Integer resourceId, Integer parentId, Integer actionType)
	{
		Session session = entityManager.unwrap(Session.class);
		CustomPrivilegAddUpdateWork work = new CustomPrivilegAddUpdateWork(loggedInUser, userId, resourceId, parentId, actionType);
		session.doWork(work);
		String result = work.getResult();
		return result;
	}
	
	private static class CustomPrivilegAddUpdateWork implements Work
	{
		public Logger logger = LoggerFactory.getLogger(getClass());
		Integer actionType;
		String loggedInUser;
		String userId;
		Integer parentId;
		Integer resourceId;
		String result = "1";
		
		public CustomPrivilegAddUpdateWork(String loggedInUser, String userId, Integer resourceId, Integer parentId, Integer actionType)
		{
			super();
			this.actionType = actionType;
			this.loggedInUser = loggedInUser;
			this.userId = userId;
			this.parentId = parentId;
			this.resourceId = resourceId;
		}
		
		@Override
		public void execute(Connection connection) throws SQLException
		{
			CallableStatement cs = null;
			ResultSet rs = null;
			try
			{
				String insertStoreProc = "{call " + FacesUtils.SCHEMA_WIRELESS + ".spAddCustomUserPrivileges(?,?,?,?,?)}";
				cs = connection.prepareCall(insertStoreProc);
				
				StringBuilder query = new StringBuilder();
				query
				        .append("USE [Analyser] GO DECLARE	@return_value int EXEC	@return_value = [wireless].[spAddCustomUserPrivileges]");
				
				// @intActionType
				cs.setInt(1, actionType);
				query.append("@intActionType = " + actionType + ",");
				
				// @varLoggedUserID
				cs.setString(2, loggedInUser);
				query.append("@varLoggedUserID = N'" + loggedInUser + "',");
				
				// @varUserId
				cs.setString(3, userId);
				query.append("@varUserId = N'" + userId + "',");
				
				// @intResourceId
				cs.setInt(4, resourceId);
				query.append("@intResourceId = " + resourceId + ",");
				
				// @intParentID
				cs.setInt(5, parentId);
				query.append("@intParentID = " + parentId);
				
				query.append(" SELECT	'Return Value' = @return_value GO ");
				
				System.out.println(query.toString());
				logger.debug("Query to add update resource is : " + query.toString());
				
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
	public List<Resource> spGetLoginUserRolePrivileges(String userId, Integer resourceId)
	{
		Session session = entityManager.unwrap(Session.class);
		ResourceRepositoryWork work = new ResourceRepositoryWork(userId, resourceId, LOGIN_USER_PRIVILEGES);
		session.doWork(work);
		List<Resource> list = work.getList();
		return list;
	}
}
