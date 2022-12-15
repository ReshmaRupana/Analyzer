package com.disys.analyzer.service;

import java.util.List;
import com.disys.analyzer.model.dto.TerminationReasonDTO;

public interface TerminationReasonService {
	
	public List<TerminationReasonDTO> getTerminationReasonList(String userId, String companyCode);
	public List<TerminationReasonDTO> getTerminationReasonDescription(String userId, String companyCode, String recordCode);
	
}
