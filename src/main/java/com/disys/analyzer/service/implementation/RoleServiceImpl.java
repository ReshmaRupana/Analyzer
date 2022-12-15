/**
 * 
 */
package com.disys.analyzer.service.implementation;

import java.io.Serializable;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.disys.analyzer.model.Role;
import com.disys.analyzer.repository.RoleRepository;
import com.disys.analyzer.service.RoleService;

/**
 * @author Sajid
 * 
 */
@Service
@Transactional
public class RoleServiceImpl implements RoleService, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final Logger logger = LoggerFactory
			.getLogger(RoleServiceImpl.class);

	@Autowired
	RoleRepository roleRepository;

	@Override
	public long count() {
		long count = roleRepository.count();
		logger.debug("Total count : " + count);
		return count;
	}

	@Override
	public Role findByRoleName(String roleName) {
		logger.debug("Role name  : " + roleName);
		return roleRepository.findByRoleDesc(roleName);
	}

	@Override
	public void save(Iterable<Role> roles) {
		logger.debug("Saving roles");
		roleRepository.save(roles);
	}

	@Override
	public void save(Role role) {
		logger.debug("About to save role");
		roleRepository.save(role);

	}

	@Override
	public Role findByRoleAbbrev(String roleAbbreviation) {
		logger.debug("Role Abbreviation  : " + roleAbbreviation);
		return roleRepository.findByRoleAbbrev(roleAbbreviation);
	}

	@Override
	public List<Role> getRoleList(String userId, int type) {
		return roleRepository.getRoleList(userId, type);
	}

	@Override
	public Role getRole(String userId, Integer roleId) {
		return roleRepository.getRole(userId, roleId);
	}

	@Override
	public String addUpdateRole(Integer roleId, String roleDesc, String userId,
			int actionType) {
		return roleRepository.addUpdateRole(roleId, roleDesc, userId,
				actionType);
	}

	@Override
	public Role getUserRole(String userId) {
		return roleRepository.getUserRole(userId);
	}

}
