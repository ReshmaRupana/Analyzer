/**
 * 
 */
package com.disys.analyzer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.disys.analyzer.model.Title;
import com.disys.analyzer.repository.custom.TitleRepositoryCustom;

/**
 * @author Sajid
 * 
 */
public interface TitleRepository extends JpaRepository<Title, Integer>, TitleRepositoryCustom{
	
	public Title findByResourceName(String resourceName);
	
	public Title findByResourceNameContaining(String resourceName);

	public Title findByTitle(String title);

}
