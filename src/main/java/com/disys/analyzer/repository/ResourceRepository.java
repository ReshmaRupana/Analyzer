/**
 * 
 */
package com.disys.analyzer.repository;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.disys.analyzer.model.Resource;
import com.disys.analyzer.repository.custom.ResourceRepositoryCustom;

/**
 * @author Sajid
 * 
 */
public interface ResourceRepository extends PagingAndSortingRepository<Resource, Integer>, ResourceRepositoryCustom
{
	Resource findByResDesc(String resourceDescription);
	
	Resource findByResId(Integer resourceId);
	
	public Page<Resource> findAll(Example<Resource> example, Pageable pageable);
	
}