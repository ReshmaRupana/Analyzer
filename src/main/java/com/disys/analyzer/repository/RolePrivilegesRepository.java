/**
 * 
 */
package com.disys.analyzer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.disys.analyzer.model.RolePrivilege;
import java.util.List;

/**
 * @author Sajid
 * 
 */
public interface RolePrivilegesRepository extends JpaRepository<RolePrivilege, Integer>{

	public List<RolePrivilege> findByIdRoleId(Integer roleId);
	/*BookId key
	Region field
	findByBookIdRegion
	
	id key
	roleId field
	findByIdRoleId*/
}
