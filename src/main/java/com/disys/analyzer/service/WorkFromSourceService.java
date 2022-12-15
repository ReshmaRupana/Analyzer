package com.disys.analyzer.service;

import java.util.List;

import com.disys.analyzer.model.dto.WorkFromSourceDTO;

public interface WorkFromSourceService {
	
	public List<WorkFromSourceDTO> getWorkFromSourceList(String userId, String companyCode);
	public List<WorkFromSourceDTO> getWorkFromSourceDescription(String userId, String companyCode, String recordCode);
	
}
