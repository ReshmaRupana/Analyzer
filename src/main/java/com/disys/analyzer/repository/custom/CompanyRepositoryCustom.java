package com.disys.analyzer.repository.custom;

import java.util.List;
import com.disys.analyzer.model.dto.CompanyDTO;

public interface CompanyRepositoryCustom {
	public List<CompanyDTO> getCompanyList(String userId);
	public List<CompanyDTO> getCompanyDescription(String userId, String recordCode);
}
