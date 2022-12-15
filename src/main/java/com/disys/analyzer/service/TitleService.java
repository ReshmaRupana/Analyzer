/**
 * 
 */
package com.disys.analyzer.service;

import java.util.List;

import com.disys.analyzer.model.Title;

/**
 * @author Sajid
 * 
 */
public interface TitleService
{
	public Title findByResourceName(String resourceName);
	
	public Title findByTitle(String title);
	
	public void saveTitle(Title title);
	
	public Title updateTitle(Title title);
	
	public List<Title> getAllTitles();
	
	public boolean isTitleUnique(String title, Integer id);
	
	public boolean isResourceNameUnique(String resourceName, Integer id);
	
}
