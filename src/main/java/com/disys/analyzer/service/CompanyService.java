package com.disys.analyzer.service;

import java.util.List;

import com.disys.analyzer.model.dto.CompanyDTO;

public interface CompanyService {
	public List<CompanyDTO> getCompanyList(String userId);
	public List<CompanyDTO> getCompanyDescription(String userId, String recordCode);
}
