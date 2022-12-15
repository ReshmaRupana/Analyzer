package com.disys.analyzer.service;

import java.util.List;

import com.disys.analyzer.model.dto.PortfolioDTO;

public interface PortfolioService {

	public List<PortfolioDTO> getAllPortfolios(String userId, String companyCode);
	
}
