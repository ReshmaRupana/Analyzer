package com.disys.analyzer.repository.custom;

import java.util.List;

import com.disys.analyzer.model.dto.EmployeeClassDTO;

public interface EmployeeClassRepositoryCustom {
	
	public List<EmployeeClassDTO> getEmployeeClassList(String userId, String companyCode);
	public List<EmployeeClassDTO> getEmployeeClassDescription(String userId, String companyCode, String recordCode);
}
