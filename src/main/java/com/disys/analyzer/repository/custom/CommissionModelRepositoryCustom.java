package com.disys.analyzer.repository.custom;

import java.util.List;

import com.disys.analyzer.model.dto.CommissionModelDTO;

public interface CommissionModelRepositoryCustom {
	
	public List<CommissionModelDTO> getCommissionModelList(String userId, String companyCode);
	public List<CommissionModelDTO> getCommissionModelDescription(String userId, String companyCode, String recordCode);
}
