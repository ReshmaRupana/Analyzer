package com.disys.analyzer.service;

import java.util.List;

import com.disys.analyzer.model.dto.CountryDTO;

public interface CountryService {
	
	public List<CountryDTO> getAllCountries(String userId);
	
}
