package com.disys.analyzer.service;

import java.util.List;
import com.disys.analyzer.model.dto.OfficeDTO;

public interface OfficeService {
	
	public List<OfficeDTO> getOfficeList(String userId, String companyCode);
	public List<OfficeDTO> getOfficeDescription(String userId, String companyCode, String recordCode);
	
}
