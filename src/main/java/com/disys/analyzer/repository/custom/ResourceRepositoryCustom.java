/**
 * 
 */
package com.disys.analyzer.repository.custom;

import java.util.List;

import com.disys.analyzer.model.Resource;

/**
 * @author Sajid
 * 
 */
public interface ResourceRepositoryCustom {
	public List<Resource> getRolePrivileges(String userId, Integer roleId);
	
	public String setPrivilege(String userId, Integer roleId, Integer resId,int act);
	
	public Integer getMaxPK();
	
	public boolean isRecordUnique(String resDesc, Integer resId);
	
	public boolean isReferenceFound(Integer resId);
	
	public List<Resource> getParentResources();
	
	public String spAddUpdateResources(String userId, Resource resource, Integer actionType);
	
	public List<Resource> findAllWithParent(String userId);
	
	public List<Resource> spGetUserRolePrivileges(String userId, Integer actionType);
	
	public String spAddCustomUserPrivileges(String loggedInUser, String userId, Integer resourceId, Integer parentId, Integer actionType);
	
	public List<Resource> spGetLoginUserRolePrivileges(String userId, Integer roleId);
}
