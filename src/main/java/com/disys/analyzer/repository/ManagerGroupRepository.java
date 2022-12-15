/**
 * 
 */
package com.disys.analyzer.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.disys.analyzer.model.ManagerGroup;

/**
 * @author Sajid
 * 
 */
public interface ManagerGroupRepository extends JpaRepository<ManagerGroup, Integer>{
	//static final String query = "Select user_id from ManagerGroups order by user_id;";

	//@Query(value = query, nativeQuery = true)
	//public List<String> findByPersonName(String userId);
	
	public List<ManagerGroup> findAll();
}
