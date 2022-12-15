package com.disys.analyzer.service;

import java.util.List;

import com.disys.analyzer.model.dto.BonusPercentageDTO;

public interface BonusPercentageService {
	
	public List<BonusPercentageDTO> getBonusPercentageList(String userId, String companyCode);
	public List<BonusPercentageDTO> getBonusPercentageDescription(String userId, String companyCode, String recordCode);
	
}
