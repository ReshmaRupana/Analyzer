package com.disys.analyzer.service;

import java.util.List;

import com.disys.analyzer.model.dto.EmployeeTypeDTO;

public interface EmployeeTypeService {
	
	public List<EmployeeTypeDTO> getEmployeeTypeList(String userId, String companyCode);
	public List<EmployeeTypeDTO> getEmployeeTypeDescription(String userId, String companyCode, String recordCode);
	
}
