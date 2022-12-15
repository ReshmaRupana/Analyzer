package com.disys.analyzer.repository.custom;

import java.util.List;

import com.disys.analyzer.model.SickLeaveCost;
import com.disys.analyzer.model.dto.StatesDTO;

public interface SickLeaveCostRepositoryCustom
{
	public List<StatesDTO> usStateData(String userId);
	
	public List<SickLeaveCost> sickLeaveData(String userId);
	
	public Integer spGetDuplicateZipCodeInSickLeaveCost(String zipCode, String oldZipCode, String mode, String userId);
	
	public String spAddUpdateSickLeaveCost (String userId, SickLeaveCost sickLeaveCost, String oldZipCode, Integer actionType);
	
	public SickLeaveCost spGetClientPtoLimit(String userId, Integer siteId, Integer clientId);
	
	public SickLeaveCost spGetSickLeaveDetails(String userId, Integer siteId, String homeZip, String workFromSource, String MajorityWorkFrom); 
	
	public Integer validateClientSite(String userId, Integer siteId, Integer clientId);
}
