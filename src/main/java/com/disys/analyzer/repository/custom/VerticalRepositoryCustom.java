package com.disys.analyzer.repository.custom;

import java.util.List;
import com.disys.analyzer.model.dto.VerticalDTO;

public interface VerticalRepositoryCustom {
	
	public List<VerticalDTO> getVerticalList(String userId, String companyCode);
	public List<VerticalDTO> getVerticalDescription(String userId, String companyCode, String recordCode);
}
