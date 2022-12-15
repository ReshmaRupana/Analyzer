/**
 * 
 */
package com.disys.analyzer.service.implementation;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.disys.analyzer.model.SickLeaveCost;
import com.disys.analyzer.model.dto.StatesDTO;
import com.disys.analyzer.repository.SickLeaveCostRepository;
import com.disys.analyzer.service.SickLeaveCostService;

/**
 * @author Sajid
 * 
 */
@Service
@Transactional
public class SickLeaveCostServiceImpl implements SickLeaveCostService, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	private SickLeaveCostRepository repository;

	/* (non-Javadoc)
	 * @see com.disys.analyzer.service.SickLeaveCostService#findByWorksiteStateCode(java.lang.String)
	 */
	@Override
	public SickLeaveCost findByWorksiteStateCode(String workSiteId) {
		return repository.findByWorksiteStateCode(workSiteId);
	}

	@Override
	public SickLeaveCost findByZipCode(String zipCode) {
		return repository.findByZipCode(zipCode);
	}
	
	@Override
	public List<StatesDTO> usStateData(String userId)
	{
		return repository.usStateData(userId);
	}
	
	@Override
	public List<SickLeaveCost> sickLeaveData(String userId)
	{
		return repository.sickLeaveData(userId);
	}
	
	@Override
	public Integer spGetDuplicateZipCodeInSickLeaveCost(String zipCode, String oldZipCode, String mode, String userId)
	{
		return repository.spGetDuplicateZipCodeInSickLeaveCost(zipCode, oldZipCode, mode, userId);
	}
	
	@Override
	public String spAddUpdateSickLeaveCost(String userId, SickLeaveCost sickLeaveCost, String oldZipCode, Integer actionType)
	{
		return repository.spAddUpdateSickLeaveCost(userId, sickLeaveCost, oldZipCode, actionType);
	}

	@Override
	public SickLeaveCost spGetClientPtoLimit(String userId, Integer siteId, Integer clientId)
	{
		return repository.spGetClientPtoLimit(userId, siteId, clientId);
	}

	@Override
	public SickLeaveCost spGetSickLeaveDetails (String userId, Integer siteId, String homeZip, String workFromSource, String majorityWorkPerformed) {
		return repository.spGetSickLeaveDetails(userId, siteId, homeZip, workFromSource, majorityWorkPerformed);
	}
	@Override
	public Boolean validateClientSite(String userId, Integer siteId, Integer clientId)
	{
		Integer val = repository.validateClientSite(userId, siteId, clientId);
		if (val == 0){
			return false;
		} else {
			return true;
		}
	}
}