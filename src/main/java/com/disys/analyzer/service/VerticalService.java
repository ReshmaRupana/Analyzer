package com.disys.analyzer.service;

import java.util.List;
import com.disys.analyzer.model.dto.VerticalDTO;

public interface VerticalService {
	
	public List<VerticalDTO> getVerticalList(String userId, String companyCode);
	public List<VerticalDTO> getVerticalDescription(String userId, String companyCode, String recordCode);
	
}
