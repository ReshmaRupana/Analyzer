package com.disys.analyzer.service;

import java.util.List;
import com.disys.analyzer.model.dto.GenderDTO;

public interface GenderService {
	
	public List<GenderDTO> getGenderList(String userId, String companyCode);
	public List<GenderDTO> getGenderDescription(String userId, String companyCode, String recordCode);
	
}
