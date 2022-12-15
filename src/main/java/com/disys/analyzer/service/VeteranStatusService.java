package com.disys.analyzer.service;

import java.util.List;
import com.disys.analyzer.model.dto.VeteranStatusDTO;

public interface VeteranStatusService {
	
	public List<VeteranStatusDTO> getVeteranStatusList(String userId, String companyCode);
	public List<VeteranStatusDTO> getVeteranStatusDescription(String userId, String companyCode, String recordCode);
	
}
