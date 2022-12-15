/**
 * 
 */
package com.disys.analyzer.service;

import java.util.List;

import com.disys.analyzer.model.SickLeaveCost;
import com.disys.analyzer.model.dto.StatesDTO;

/**
 * @author Sajid
 * @since Dec 8, 2017
 */
public interface SickLeaveCostService
{
	public SickLeaveCost findByWorksiteStateCode(String workSiteId);
	
	public SickLeaveCost findByZipCode(String zipCode);
	
	public List<StatesDTO> usStateData(String userId);
	
	public List<SickLeaveCost> sickLeaveData(String userId);
	
	public Integer spGetDuplicateZipCodeInSickLeaveCost(String zipCode, String oldZipCode, String mode, String userId);
	
	public String spAddUpdateSickLeaveCost (String userId, SickLeaveCost sickLeaveCost, String oldZipCode, Integer actionType);
	
	public SickLeaveCost spGetClientPtoLimit (String userId, Integer siteId, Integer clientId);
	
	public SickLeaveCost spGetSickLeaveDetails (String userId, Integer siteId, String homeZip, String workFromSource, String MajorityWorkFrom);
	
	public Boolean validateClientSite(String userId, Integer siteId, Integer clientId);
}
