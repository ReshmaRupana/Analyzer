package com.disys.analyzer.repository.custom;

import java.util.List;

import com.disys.analyzer.model.dto.CountryDTO;

public interface CountryRepositoryCustom {
	
	public List<CountryDTO> getAllCountries(String userId);
}
