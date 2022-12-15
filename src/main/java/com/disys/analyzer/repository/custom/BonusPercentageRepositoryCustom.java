package com.disys.analyzer.repository.custom;

import java.util.List;

import com.disys.analyzer.model.dto.BonusPercentageDTO;

public interface BonusPercentageRepositoryCustom {
	
	public List<BonusPercentageDTO> getBonusPercentageList(String userId, String companyCode);
	public List<BonusPercentageDTO> getBonusPercentageDescription(String userId, String companyCode, String recordCode);
}
