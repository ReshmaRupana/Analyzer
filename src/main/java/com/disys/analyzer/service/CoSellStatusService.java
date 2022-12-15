package com.disys.analyzer.service;

import java.util.List;

import com.disys.analyzer.model.dto.CoSellStatusDTO;

public interface CoSellStatusService {
	
	public List<CoSellStatusDTO> getCoSellStatusList(String userId, String companyCode);
	public List<CoSellStatusDTO> getCoSellStatusDescription(String userId, String companyCode, String recordCode);
	
}
