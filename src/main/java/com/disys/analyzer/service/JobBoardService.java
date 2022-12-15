package com.disys.analyzer.service;

import java.util.List;

import com.disys.analyzer.model.dto.JobBoardDTO;

public interface JobBoardService {
	
	public List<JobBoardDTO> getJobBoardList(String userId, String companyCode);
	public List<JobBoardDTO> getJobBoardDescription(String userId, String companyCode, String recordCode);
	
}
