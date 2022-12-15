package com.disys.analyzer.repository.custom;

import java.util.List;

import com.disys.analyzer.model.dto.CoSellStatusDTO;

public interface CoSellStatusRepositoryCustom {
	
	public List<CoSellStatusDTO> getCoSellStatusList(String userId, String companyCode);
	public List<CoSellStatusDTO> getCoSellStatusDescription(String userId, String companyCode, String recordCode);
}
