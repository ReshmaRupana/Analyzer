package com.disys.analyzer.service;

import java.util.List;
import com.disys.analyzer.model.dto.SickLeaveTypeDTO;

public interface SickLeaveTypeService {
	
	public List<SickLeaveTypeDTO> getSickLeaveTypeList(String userId, String companyCode);
	public List<SickLeaveTypeDTO> getSickLeaveTypeDescription(String userId, String companyCode, String recordCode);
	
}
