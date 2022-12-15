/**
 * 
 */
package com.disys.analyzer.service;

import java.util.List;

import com.disys.analyzer.model.AnalyserClientSite;
import com.disys.analyzer.model.dto.AnalyserClientSiteDTO;

/**
 * @author Sajid
 * 
 */
public interface AnalyserClientSiteService {
	List<AnalyserClientSiteDTO> getAnalyserAllSites(String userId,
			String userList, String orderBy, String searchString);
	
	String addUpdateAnalyserSite(AnalyserClientSite analyserClientSite, String userId, int actionType);
	
	public AnalyserClientSite getAnalyserClientSiteInfo(String analyserSiteId);
	
	List<AnalyserClientSiteDTO> getAnalyserAllSitesSickCaluculations(String userId, String clientId, String homeZip, String workFromSource);
}
