package com.disys.analyzer.repository.custom;

import java.util.List;

import com.disys.analyzer.model.dto.EntityDTO;

public interface EntityRepositoryCustom {
	
	public List<EntityDTO> getEntityList(String userId, String companyCode);
	public List<EntityDTO> getEntityDescription(String userId, String companyCode, String recordCode);
}
