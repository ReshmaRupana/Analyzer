/**
 * 
 */
package com.disys.analyzer.service;

import java.util.List;

import com.disys.analyzer.model.Role;

/**
 * @author Sajid
 * 
 */
public interface RoleService {
	public long count();

	public Role findByRoleName(String roleName);

	public void save(Iterable<Role> roles);

	public void save(Role role);
	
	public Role findByRoleAbbrev(String roleAbbreviation);
	
	public List<Role> getRoleList(String userId, int type);
	
	public Role getRole(String userId, Integer roleId);
	
	public String addUpdateRole(Integer roleId, String roleDesc, String userId,int actionType);
	
	public Role getUserRole(String userId);
}
