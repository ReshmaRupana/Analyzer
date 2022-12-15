package com.disys.analyzer.service.implementation;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.disys.analyzer.model.dto.CountryDTO;
import com.disys.analyzer.repository.CountryRepository;
import com.disys.analyzer.service.CountryService;
/**
 * @author Saravanan Dhurairaj
 * 
 */
@Service
@Transactional
public class CountryServiceImpl implements CountryService, Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private CountryRepository countryRepository;
	
	/* (non-Javadoc)
	 * @see com.disys.analyzer.service.CountryService#getAllCountries(java.lang.String)
	 */
	@Override
	public List<CountryDTO> getAllCountries(String userId) 
	{
		return countryRepository.getAllCountries(userId);
	}
	
}
