package com.disys.analyzer.service;

import java.util.List;
import com.disys.analyzer.model.dto.PtoDTO;

public interface PtoService {
	
	public List<PtoDTO> getPtoList(String userId, String companyCode);
	public List<PtoDTO> getPtoDescription(String userId, String companyCode, String recordCode);
	
}
