package com.disys.analyzer.repository.custom;

import java.util.List;
import com.disys.analyzer.model.dto.VeteranStatusDTO;

public interface VeteranStatusRepositoryCustom {
	
	public List<VeteranStatusDTO> getVeteranStatusList(String userId, String companyCode);
	public List<VeteranStatusDTO> getVeteranStatusDescription(String userId, String companyCode, String recordCode);
}
