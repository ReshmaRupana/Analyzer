package com.disys.analyzer.service;

import java.util.List;

import com.disys.analyzer.model.dto.EmployeeCategoryDTO;

public interface EmployeeCategoryService {
	
	public List<EmployeeCategoryDTO> getEmployeeCategoryList(String userId, String companyCode);
	public List<EmployeeCategoryDTO> getEmployeeCategoryDescription(String userId, String companyCode, String recordCode);
	
}
