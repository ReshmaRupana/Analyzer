package com.disys.analyzer.repository.custom;

import java.util.List;

import com.disys.analyzer.model.dto.EmployeeTypeDTO;

public interface EmployeeTypeRepositoryCustom {
	
	public List<EmployeeTypeDTO> getEmployeeTypeList(String userId, String companyCode);
	public List<EmployeeTypeDTO> getEmployeeTypeDescription(String userId, String companyCode, String recordCode);
	
}
