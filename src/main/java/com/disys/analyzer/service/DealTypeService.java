package com.disys.analyzer.service;

import java.util.List;

import com.disys.analyzer.model.dto.DealTypeDTO;

public interface DealTypeService {
	
	public List<DealTypeDTO> getDealTypeList(String userId, String companyCode);
	public List<DealTypeDTO> getDealTypeDescription(String userId, String companyCode, String recordCode);
	
}
