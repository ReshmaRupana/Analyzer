/**
 * 
 */
package com.disys.analyzer.service.implementation;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.disys.analyzer.model.OrganizationGroup;
import com.disys.analyzer.model.User;
import com.disys.analyzer.repository.OrganizationGroupRepository;
import com.disys.analyzer.service.OrganizationGroupService;

/**
 * @author Sajid
 * 
 */
@Service
@Transactional
public class OrganizationGroupServiceImpl implements OrganizationGroupService, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final Logger logger = LoggerFactory
			.getLogger(OrganizationGroupServiceImpl.class);

	@Autowired
	OrganizationGroupRepository organizationGroupRepository;

	@Override
	public long count() {
		long count = organizationGroupRepository.count();
		logger.debug("Total count : " + count);
		return count;
	}

	@Override
	public OrganizationGroup findByGroupName(String groupName, Integer orgId) {
		logger.debug("Group name  : " + groupName);
		logger.debug(" of Organization " + orgId);
		return organizationGroupRepository.findByGroupDescriptionAndOrgId(
				groupName, orgId);
	}

	@Override
	public void save(Iterable<OrganizationGroup> organizationGroups) {
		logger.debug("Saving groups");
		organizationGroupRepository.save(organizationGroups);

	}

	@Override
	public List<OrganizationGroup> getGroupsList(String userId) {
		return organizationGroupRepository.getGroupsList(userId);
	}

	@Override
	public String save(OrganizationGroup organizationGroup, String userId,
			int type) {
		logger.debug("About to save group");
		return organizationGroupRepository.addUpdateGroup(organizationGroup,
				userId, type);

	}

	@Override
	public String update(OrganizationGroup organizationGroup, String userId,
			int type) {
		logger.debug("About to update group");
		return organizationGroupRepository.addUpdateGroup(organizationGroup,
				userId, type);
	}

	@Override
	public String deleteGroup(String groupId, String userId) {
		logger.debug("About to delete group with Id : " + groupId);
		return organizationGroupRepository.deleteGroup(groupId, userId);
	}

	@Override
	public List<User> getGroupUserList(String userId, boolean assignType,
			boolean userType, String groupId) {
		return organizationGroupRepository.getGroupUserList(userId, assignType,
				userType, groupId);
	}

	@Override
	public OrganizationGroup findByGroupId(Integer groupId) {
		return organizationGroupRepository.findByGroupId(groupId);
	}

	@Override
	public String assignDeassignUserToGroup(String adminUserId, int assignType,
			int userType, String groupId, String userId) {
		return organizationGroupRepository.assignDeassignUserToGroup(
				adminUserId, assignType, userType, groupId, userId);
	}

	@Override
	public List<OrganizationGroup> getUserGroups(String userId) {
		return organizationGroupRepository.getUserGroups(userId);
	}

	@Override
	public HashMap<Integer, String> getUsersForAGroup(String userId,
			String groupId) {
		return organizationGroupRepository.getUsersForAGroup(userId, groupId);
	}

}