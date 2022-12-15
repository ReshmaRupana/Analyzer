package com.disys.analyzer.repository.custom;

import java.util.List;
import com.disys.analyzer.model.dto.SplitPercentageDTO;

public interface SplitPercentageRepositoryCustom {
	
	public List<SplitPercentageDTO> getSplitPercentageList(String userId, String companyCode);
	public List<SplitPercentageDTO> getSplitPercentageDescription(String userId, String companyCode, String recordCode);
}
