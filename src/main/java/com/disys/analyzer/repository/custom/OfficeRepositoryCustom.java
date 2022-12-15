package com.disys.analyzer.repository.custom;

import java.util.List;
import com.disys.analyzer.model.dto.OfficeDTO;

public interface OfficeRepositoryCustom {
	
	public List<OfficeDTO> getOfficeList(String userId, String companyCode);
	public List<OfficeDTO> getOfficeDescription(String userId, String companyCode, String recordCode);
}
