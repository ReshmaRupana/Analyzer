/**
 * 
 */
package com.disys.analyzer.service.implementation;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.disys.analyzer.model.AnalyserClient;
import com.disys.analyzer.model.PSBillingType;
import com.disys.analyzer.model.PSOperatingUnitOrVertical;
import com.disys.analyzer.model.PSProduct;
import com.disys.analyzer.model.dto.AnalyserClientDTO;
import com.disys.analyzer.model.dto.AnalyserClientDatabaseDTO;
import com.disys.analyzer.model.dto.GenderDTO;
import com.disys.analyzer.repository.AnalyserClientRepository;
import com.disys.analyzer.service.AnalyserClientService;

/**
 * @author Sajid
 * 
 */
@Service
@Transactional
public class AnalyserClientServiceImpl implements AnalyserClientService, Serializable
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Autowired AnalyserClientRepository repository;
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.disys.analyzer.service.AnalyserClientService#findAll(org.
	 * springframework .data.domain.Example,
	 * org.springframework.data.domain.Pageable)
	 */
	@Override
	public Page<AnalyserClient> findAll(Example<AnalyserClient> example, Pageable pageable)
	{
		return repository.findAll(example, pageable);
	}
	
	@Override
	public List<Object[]> getClientList(String userId)
	{
		return repository.getClientList(userId);
	}
	
	@Override
	public List<AnalyserClientDTO> getAnalyserAllClients(String userId, String userList, String orderBy, String searchString, String vertical,
	        String product, String branch, String companyCode)
	{
		// TODO Auto-generated method stub
		return repository.getAnalyserAllClients(userId, userList, orderBy, searchString, vertical, product, branch, companyCode);
	}
	
	@Override
	public List<AnalyserClientDTO> spGetAnalyserAllDistinctClient(String userId, String userList, String orderBy, String searchString, String companyCode)
	{
		return repository.spGetAnalyserAllDistinctClient(userId, userList, orderBy, searchString, companyCode);
	}
	
	@Override
	public AnalyserClientDatabaseDTO getClientDatabaseInfo(String clientId)
	{
		return repository.getClientDatabaseInfo(clientId);
	}
	
	@Override
	public String addUpdateAnalyserClientDatabase(AnalyserClientDatabaseDTO client, String userId, int actionType)
	{
		return repository.addUpdateAnalyserClientDatabase(client, userId, actionType);
	}
	
	@Override
	public String changeClientStatus(String userId, Integer clientId, Integer status)
	{
		return repository.changeClientStatus(userId, clientId, status);
		
	}
	
	@Override
	public String changeClientSiteStatus(String userId, Integer clientId, Integer siteId, Integer status)
	{
		return repository.changeClientSiteStatus(userId, clientId, siteId, status);
	}
	
	@Override
	public String changeClientAddressStatus(String userId, Integer clientId, Integer addresssId, Integer status)
	{
		return repository.changeClientAddressStatus(userId, clientId, addresssId, status);
	}

	@Override
	public String getTotalDiscount(String userId, Integer clientId)
	{
		return repository.getTotalDiscount(userId, clientId);
	}
	
	@Override
	public List<PSOperatingUnitOrVertical> getPSVerticals(String userId, Integer pSVerticalId, Integer fetchAll){
		return repository.getPSVerticals(userId, pSVerticalId, fetchAll);
	}
	
	@Override
	public List<PSProduct> getPSProducts(String userId, Integer pSProductId, Integer fetchAll){
		return repository.getPSProducts(userId, pSProductId, fetchAll);
	}
	
	@Override
	public List<PSBillingType> getPSBillingTypes(String userId, Integer pSBillingTypeId, Integer fetchAll){
		return repository.getPSBillingTypes(userId, pSBillingTypeId, fetchAll);
	}
	
	@Override
	public List<AnalyserClientDTO> getAnalyserClientList(String userId, String companyCode) 
	{
		return repository.getAnalyserClientList(userId, companyCode);
	}
	
	@Override
	public List<AnalyserClientDTO> getAnalyserClientDescription(String userId, String companyCode, String recordCode) 
	{
		return repository.getAnalyserClientDescription(userId, companyCode, recordCode);
	}
}
