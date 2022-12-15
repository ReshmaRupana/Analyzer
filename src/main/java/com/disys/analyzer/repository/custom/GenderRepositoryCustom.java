package com.disys.analyzer.repository.custom;

import java.util.List;

import com.disys.analyzer.model.dto.GenderDTO;

public interface GenderRepositoryCustom {
	
	public List<GenderDTO> getGenderList(String userId, String companyCode);
	public List<GenderDTO> getGenderDescription(String userId, String companyCode, String recordCode);
}
