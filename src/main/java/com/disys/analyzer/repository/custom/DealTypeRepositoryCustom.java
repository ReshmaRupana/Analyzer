package com.disys.analyzer.repository.custom;

import java.util.List;

import com.disys.analyzer.model.dto.DealTypeDTO;

public interface DealTypeRepositoryCustom {
	
	public List<DealTypeDTO> getDealTypeList(String userId, String companyCode);
	public List<DealTypeDTO> getDealTypeDescription(String userId, String companyCode, String recordCode);
}
