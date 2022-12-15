package com.disys.analyzer.repository.custom;

import java.util.List;
import com.disys.analyzer.model.dto.FLSAStatusDTO;

public interface FLSAStatusRepositoryCustom {
	
	public List<FLSAStatusDTO> getFLSAStatusList(String userId, String companyCode);
	public List<FLSAStatusDTO> getFLSAStatusDescription(String userId, String companyCode, String recordCode);
}
