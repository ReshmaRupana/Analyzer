package com.disys.analyzer.repository.custom;

import java.util.List;
import com.disys.analyzer.model.dto.JobBoardDTO;

public interface JobBoardRepositoryCustom {
	
	public List<JobBoardDTO> getJobBoardList(String userId, String companyCode);
	public List<JobBoardDTO> getJobBoardDescription(String userId, String companyCode, String recordCode);
}
