package com.disys.analyzer.repository.custom;

import java.util.List;
import com.disys.analyzer.model.dto.SickLeaveTypeDTO;

public interface SickLeaveTypeRepositoryCustom {
	
	public List<SickLeaveTypeDTO> getSickLeaveTypeList(String userId, String companyCode);
	public List<SickLeaveTypeDTO> getSickLeaveTypeDescription(String userId, String companyCode, String recordCode);
}
