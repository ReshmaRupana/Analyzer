/**
 * 
 */
package com.disys.analyzer.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.disys.analyzer.model.OrganizationGroup;
import com.disys.analyzer.repository.custom.OrganizationGroupRepositoryCustom;

/**
 * @author Sajid
 * 
 */
public interface OrganizationGroupRepository extends
		JpaRepository<OrganizationGroup, Long>,
		OrganizationGroupRepositoryCustom {
	OrganizationGroup findByGroupDescriptionAndOrgId(String groupDescription,
			Integer orgId);

	/*
	 StringBuffer query = new StringBuffer("  ");
	 query.append(" from organization_groups where ");
	 query.append(" org_id = (select org_id from users where user_id = ?)");
	 query.append(" order by 2");
	 */
	@Query(value = "select g.Group_ID, g.Group_Description, g.Active, g.Org_ID FROM Organization_Groups g where g.Org_ID = (Select Org_ID from Users u where u.USER_ID = ?1) order by 2", nativeQuery = true)
	public List<OrganizationGroup> getGroupsList(String userId);
	
	public OrganizationGroup findByGroupId(Integer groupId);
}
