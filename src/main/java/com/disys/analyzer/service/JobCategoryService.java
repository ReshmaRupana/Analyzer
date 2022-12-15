package com.disys.analyzer.service;

import java.util.List;

import com.disys.analyzer.model.dto.JobCategoryDTO;

public interface JobCategoryService {
	
	public List<JobCategoryDTO> getJobCategoryList(String userId, String companyCode);
	public List<JobCategoryDTO> getJobCategoryDescription(String userId, String companyCode, String recordCode);
	
}
