/**
 * 
 */
package com.disys.analyzer.repository.custom;

import java.util.HashMap;import java.util.LinkedHashMap;
import java.util.List;

import com.disys.analyzer.model.OrganizationGroup;
import com.disys.analyzer.model.User;

/**
 * @author Sajid
 * 
 */
public interface OrganizationGroupRepositoryCustom {

	public String addUpdateGroup(OrganizationGroup group,String userId, 
			int type);

	public String deleteGroup(String groupId, String userId);
	
	public List<User> getGroupUserList(String userId, boolean assignType,boolean userType, String groupId);
	
	public String assignDeassignUserToGroup(String adminUserId, int assignType,
			int userType, String groupId, String userId);
	
	public List<OrganizationGroup> getUserGroups(String userId);
	
	public HashMap<Integer,String> getUsersForAGroup(String userId, String groupId);
}