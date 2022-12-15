/**
 * 
 */
package com.disys.analyzer.service;

import java.util.HashMap;import java.util.LinkedHashMap;
import java.util.List;

import com.disys.analyzer.model.OrganizationGroup;
import com.disys.analyzer.model.User;

/**
 * @author Sajid
 * 
 */
public interface OrganizationGroupService {

	public long count();

	OrganizationGroup findByGroupName(String groupName, Integer orgId);

	public void save(Iterable<OrganizationGroup> organizationGroups);

	public List<OrganizationGroup> getGroupsList(String userId);

	public String save(OrganizationGroup organizationGroup, String userId,
			int type);

	public String update(OrganizationGroup organizationGroup, String userId,
			int type);

	public String deleteGroup(String groupId, String userId);

	public List<User> getGroupUserList(String userId, boolean assignType,
			boolean userType, String groupId);

	public OrganizationGroup findByGroupId(Integer groupId);

	public String assignDeassignUserToGroup(String adminUserId, int assignType,
			int userType, String groupId, String userId);
	
	public List<OrganizationGroup> getUserGroups(String userId);
	
	public HashMap<Integer,String> getUsersForAGroup(String userId, String groupId);
}
