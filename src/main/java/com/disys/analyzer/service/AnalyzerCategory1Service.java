package com.disys.analyzer.service;

import java.util.List;

import com.disys.analyzer.model.dto.AnalyzerCategory1DTO;

public interface AnalyzerCategory1Service {
	
	public List<AnalyzerCategory1DTO> getAnalyzerCategory1List(String userId, String companyCode);
	public List<AnalyzerCategory1DTO> getAnalyzerCategory1Description(String userId, String companyCode, String recordCode);
	
}
