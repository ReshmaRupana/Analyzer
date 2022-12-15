/**
 * 
 */
package com.disys.analyzer.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.disys.analyzer.model.Role;
import com.disys.analyzer.repository.custom.RoleRepositoryCustom;

/**
 * @author Sajid
 * 
 *
 */
public interface RoleRepository extends JpaRepository<Role, Long>,RoleRepositoryCustom {

	public Role findByRoleDesc(String roleDesc);

	public List<Role> findAll();

	public List<Role> findAllByOrgId(Integer orgId);

	public Role findByRoleAbbrev(String roleAbbreviation);

}
