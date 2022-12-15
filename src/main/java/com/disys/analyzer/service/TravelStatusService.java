package com.disys.analyzer.service;

import java.util.List;
import com.disys.analyzer.model.dto.TravelStatusDTO;

public interface TravelStatusService {
	
	public List<TravelStatusDTO> getTravelStatusList(String userId, String companyCode);
	public List<TravelStatusDTO> getTravelStatusDescription(String userId, String companyCode, String recordCode);
	
}
