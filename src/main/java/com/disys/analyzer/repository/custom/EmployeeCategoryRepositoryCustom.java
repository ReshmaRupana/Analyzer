package com.disys.analyzer.repository.custom;

import java.util.List;

import com.disys.analyzer.model.dto.EmployeeCategoryDTO;

public interface EmployeeCategoryRepositoryCustom {
	
	public List<EmployeeCategoryDTO> getEmployeeCategoryList(String userId, String companyCode);
	public List<EmployeeCategoryDTO> getEmployeeCategoryDescription(String userId, String companyCode, String recordCode);
}
