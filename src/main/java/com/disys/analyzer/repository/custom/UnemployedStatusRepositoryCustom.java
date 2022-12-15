package com.disys.analyzer.repository.custom;

import java.util.List;
import com.disys.analyzer.model.dto.UnemployedStatusDTO;

public interface UnemployedStatusRepositoryCustom {
	
	public List<UnemployedStatusDTO> getUnemployedStatusList(String userId, String companyCode);
	public List<UnemployedStatusDTO> getUnemployedStatusDescription(String userId, String companyCode, String recordCode);
}
