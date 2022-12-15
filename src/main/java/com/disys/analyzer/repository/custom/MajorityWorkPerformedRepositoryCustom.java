package com.disys.analyzer.repository.custom;

import java.util.List;
import com.disys.analyzer.model.dto.MajorityWorkPerformedDTO;

public interface MajorityWorkPerformedRepositoryCustom {
	
	public List<MajorityWorkPerformedDTO> getMajorityWorkPerformedList(String userId, String companyCode);
	public List<MajorityWorkPerformedDTO> getMajorityWorkPerformedDescription(String userId, String companyCode, String recordCode);
}
