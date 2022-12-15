/**
 * 
 */
package com.disys.analyzer.repository.custom;

import java.util.List;

import com.disys.analyzer.model.Role;

/**
 * @author Sajid
 * 
 */
public interface RoleRepositoryCustom {
	public List<Role> getRoleList(String userId, int type);
	
	public Role getRole(String userId, Integer roleId);
	
	public String addUpdateRole(Integer roleId, String roleDesc, String userId,int actionType);
	
	public Role getUserRole(String userId);
}
