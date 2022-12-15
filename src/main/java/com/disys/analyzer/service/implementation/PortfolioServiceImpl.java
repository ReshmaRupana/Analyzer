package com.disys.analyzer.service.implementation;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.disys.analyzer.model.dto.PortfolioDTO;
import com.disys.analyzer.repository.PortfolioRepository;
import com.disys.analyzer.service.PortfolioService;
/**
 * @author Saravanan Dhurairaj
 * 
 */
@Service
@Transactional
public class PortfolioServiceImpl implements PortfolioService, Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private PortfolioRepository portfolioRepository;
	
	/* (non-Javadoc)
	 * @see com.disys.analyzer.service.PortfolioService#getAllPortfolios(java.lang.String)
	 */
	
	@Override
	public List<PortfolioDTO> getAllPortfolios(String userId, String companyCode) 
	{
		return portfolioRepository.getAllPortfolios(userId, companyCode);
	}
}
