package com.disys.analyzer.service;

import java.util.List;

import com.disys.analyzer.model.dto.AnalyserCategory2DTO;

public interface AnalyserCategory2Service {
	
	public List<AnalyserCategory2DTO> getAnalyserCategory2List(String userId, String companyCode);
	public List<AnalyserCategory2DTO> getAnalyserCategory2Description(String userId, String companyCode, String recordCode);
	
}
