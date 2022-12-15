package com.disys.analyzer.service;

import java.util.List;
import com.disys.analyzer.model.dto.UnemployedStatusDTO;

public interface UnemployedStatusService {
	
	public List<UnemployedStatusDTO> getUnemployedStatusList(String userId, String companyCode);
	public List<UnemployedStatusDTO> getUnemployedStatusDescription(String userId, String companyCode, String recordCode);
	
}
