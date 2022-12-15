package com.disys.analyzer.repository.custom;

public interface TitleRepositoryCustom
{
	public boolean isTitleUnique(String title, Integer id);
	
	public boolean isResourceNameUnique(String resourceName, Integer id);
}
