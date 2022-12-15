package com.disys.analyzer.service;

import java.util.List;

import com.disys.analyzer.model.dto.StatesAllDTO;

public interface StatesService {

	public List<StatesAllDTO> getAllStatess(String userId);
	
}
