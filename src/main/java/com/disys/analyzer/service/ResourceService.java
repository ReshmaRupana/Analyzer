/**
 * 
 */
package com.disys.analyzer.service;

import java.util.List;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.disys.analyzer.model.Resource;

/**
 * @author Sajid
 * 
 */
public interface ResourceService
{
	public Long count(Example<Resource> example);
	
	public void save(Iterable<Resource> resources);
	
	public Resource save(Resource resources);
	
	public Page<Resource> findAll(Example<Resource> example, Pageable pageable);
	
	public List<Resource> getRolePrivileges(String userId, Integer roleId);
	
	public List<Resource> findAll();
	
	public String setPrivilege(String userId, Integer roleId, Integer resId, int act);
	
	public Integer getMaxPK();
	
	public void delete(Resource resources);
	
	public boolean isRecordUnique(String resDesc, Integer resId);
	
	public boolean isReferenceFound(Integer resId);
	
	public List<Resource> getParentResources();
	
	public String spAddUpdateResources(String userId, Resource resource, Integer actionType);
	
	public List<Resource> findAllWithParent(String userId);
	
	public List<Resource> spGetUserRolePrivileges(String userId, Integer actionType);
	
	public String spAddCustomUserPrivileges (String loggedInUser, String userId, Integer resourceId, Integer parentId, Integer actionType);
	
	public List<Resource> spGetLoginUserRolePrivileges(String userId, Integer roleId);
}
