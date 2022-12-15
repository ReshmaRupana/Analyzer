package com.disys.analyzer.repository.custom;

import java.util.List;
import com.disys.analyzer.model.dto.TravelStatusDTO;

public interface TravelStatusRepositoryCustom {
	
	public List<TravelStatusDTO> getTravelStatusList(String userId, String companyCode);
	public List<TravelStatusDTO> getTravelStatusDescription(String userId, String companyCode, String recordCode);
}
