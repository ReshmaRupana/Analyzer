package com.disys.analyzer.service;

import java.util.List;

import com.disys.analyzer.model.dto.CommissionModelDTO;

public interface CommissionModelService {
	
	public List<CommissionModelDTO> getCommissionModelList(String userId, String companyCode);
	public List<CommissionModelDTO> getCommissionModelDescription(String userId, String companyCode, String recordCode);
	
}
