/**
 * 
 */
package com.disys.analyzer.service;

import java.util.List;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.disys.analyzer.model.AnalyserClient;
import com.disys.analyzer.model.PSBillingType;
import com.disys.analyzer.model.PSOperatingUnitOrVertical;
import com.disys.analyzer.model.PSProduct;
import com.disys.analyzer.model.dto.AnalyserClientDTO;
import com.disys.analyzer.model.dto.AnalyserClientDatabaseDTO;
import com.disys.analyzer.model.dto.GenderDTO;

/**
 * @author Sajid
 * 
 */
public interface AnalyserClientService {
	public Page<AnalyserClient> findAll(Example<AnalyserClient> example,
			Pageable pageable);

	// public Map<Integer, String> getClientList(String userId);
	public List<Object[]> getClientList(String userId);

	public List<AnalyserClientDTO> getAnalyserAllClients(String userId,
			String userList, String orderBy, String searchString,
			String vertical, String product, String branch, String companyCode);
	
	public List<AnalyserClientDTO> spGetAnalyserAllDistinctClient(String userId, String userList, String orderBy, String searchString, String companyCode);
	
	public AnalyserClientDatabaseDTO getClientDatabaseInfo(String clientId);
	
	public String addUpdateAnalyserClientDatabase(
			AnalyserClientDatabaseDTO client, String userId, int actionType);
	
	public String changeClientStatus(String userId, Integer clientId, Integer status);
	
	public String changeClientSiteStatus(String userId, Integer clientId, Integer siteId, Integer status);
	
	public String changeClientAddressStatus(String userId, Integer clientId,Integer addressId, Integer status);
	
	public String getTotalDiscount(String userId, Integer clientId);
	
	public List<PSOperatingUnitOrVertical> getPSVerticals(String userId, Integer pSVerticalId, Integer fetchAll);
	public List<PSProduct> getPSProducts(String userId, Integer pSProductId, Integer fetchAll);
	public List<PSBillingType> getPSBillingTypes(String userId, Integer pSBillingTypeId, Integer fetchAll);
	
	public List<AnalyserClientDTO> getAnalyserClientList(String userId, String companyCode);
	public List<AnalyserClientDTO> getAnalyserClientDescription(String userId, String companyCode, String recordCode);
}
