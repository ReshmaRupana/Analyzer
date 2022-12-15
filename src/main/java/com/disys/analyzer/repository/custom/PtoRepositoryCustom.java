package com.disys.analyzer.repository.custom;

import java.util.List;
import com.disys.analyzer.model.dto.PtoDTO;

public interface PtoRepositoryCustom {
	
	public List<PtoDTO> getPtoList(String userId, String companyCode);
	public List<PtoDTO> getPtoDescription(String userId, String companyCode, String recordCode);
}
