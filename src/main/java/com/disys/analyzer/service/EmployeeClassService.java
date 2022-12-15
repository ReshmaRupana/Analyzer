package com.disys.analyzer.service;

import java.util.List;

import com.disys.analyzer.model.dto.EmployeeClassDTO;

public interface EmployeeClassService {
	
	public List<EmployeeClassDTO> getEmployeeClassList(String userId, String companyCode);
	public List<EmployeeClassDTO> getEmployeeClassDescription(String userId, String companyCode, String recordCode);
	
}
