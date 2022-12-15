package com.disys.analyzer.repository.custom;

import java.util.List;
import com.disys.analyzer.model.dto.WorkFromSourceDTO;

public interface WorkFromSourceRepositoryCustom {
	
	public List<WorkFromSourceDTO> getWorkFromSourceList(String userId, String companyCode);
	public List<WorkFromSourceDTO> getWorkFromSourceDescription(String userId, String companyCode, String recordCode);
}
