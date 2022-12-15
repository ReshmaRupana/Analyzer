/**
 * 
 */
package com.disys.analyzer.repository.custom;

import java.util.List;

import org.springframework.data.jpa.repository.query.Procedure;

import com.disys.analyzer.model.PSBillingType;
import com.disys.analyzer.model.PSOperatingUnitOrVertical;
import com.disys.analyzer.model.PSProduct;
import com.disys.analyzer.model.dto.AnalyserClientDTO;
import com.disys.analyzer.model.dto.AnalyserClientDatabaseDTO;

/**
 * @author Sajid
 * 
 */
public interface AnalyserClientRepositoryCustom
{
	
	@Procedure
	public List<AnalyserClientDTO> getAnalyserAllClients(String userId, String userList, String orderBy, String searchString, String vertical,
	        String product, String branch, String companyCode);
	
	public List<AnalyserClientDTO> spGetAnalyserAllDistinctClient(String userId, String userList, String orderBy, String searchString, String companyCode);
	
	public AnalyserClientDatabaseDTO getClientDatabaseInfo(String clientId);
	
	public String addUpdateAnalyserClientDatabase(AnalyserClientDatabaseDTO client, String userId, int actionType);
	
	public String changeClientStatus(String userId, Integer clientId, Integer status);
	
	public String changeClientSiteStatus(String userId, Integer clientId, Integer siteId, Integer status);
	
	public String changeClientAddressStatus(String userId, Integer clientId, Integer addresssId, Integer status);
	
	public String getTotalDiscount(String userId, Integer clientId);
	
	public List<PSOperatingUnitOrVertical> getPSVerticals(String userId, Integer pSVerticalId, Integer fetchAll);
	
	public List<PSProduct> getPSProducts(String userId, Integer pSProductId, Integer fetchAll);
	public List<PSBillingType> getPSBillingTypes(String userId, Integer pSBillingTypeId, Integer fetchAll);
	public List<AnalyserClientDTO> getAnalyserClientList(String userId, String companyCode);
	public List<AnalyserClientDTO> getAnalyserClientDescription(String userId, String companyCode, String recordCode);
}
