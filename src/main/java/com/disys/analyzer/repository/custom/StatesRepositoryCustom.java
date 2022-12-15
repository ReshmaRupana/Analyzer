package com.disys.analyzer.repository.custom;

import java.util.List;

import com.disys.analyzer.model.dto.StatesAllDTO;

public interface StatesRepositoryCustom {

	public List<StatesAllDTO> getAllStatess(String userId);
}
