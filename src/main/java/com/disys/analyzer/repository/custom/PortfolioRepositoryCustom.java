package com.disys.analyzer.repository.custom;

import java.util.List;

import com.disys.analyzer.model.dto.PortfolioDTO;

public interface PortfolioRepositoryCustom {

	public List<PortfolioDTO> getAllPortfolios(String userId, String companyCode);
}
