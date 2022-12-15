package com.disys.analyzer.service;

import java.util.List;

import com.disys.analyzer.model.dto.EntityDTO;

public interface EntityService {
	
	public List<EntityDTO> getEntityList(String userId, String companyCode);
	public List<EntityDTO> getEntityDescription(String userId, String companyCode, String recordCode);
	
}
