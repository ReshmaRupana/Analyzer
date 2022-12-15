package com.disys.analyzer.repository.custom;

import java.util.List;

import com.disys.analyzer.model.dto.TerminationReasonDTO;

public interface TerminationReasonRepositoryCustom {
	
	public List<TerminationReasonDTO> getTerminationReasonList(String userId, String companyCode);
	public List<TerminationReasonDTO> getTerminationReasonDescription(String userId, String companyCode, String recordCode);
}
