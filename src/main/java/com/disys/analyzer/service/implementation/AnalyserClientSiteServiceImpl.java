/**
 * 
 */
package com.disys.analyzer.service.implementation;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.disys.analyzer.model.AnalyserClientSite;
import com.disys.analyzer.model.dto.AnalyserClientSiteDTO;
import com.disys.analyzer.repository.AnalyserClientSiteRepository;
import com.disys.analyzer.service.AnalyserClientSiteService;

/**
 * @author Sajid
 * 
 */
@Service
@Transactional(readOnly=true)
public class AnalyserClientSiteServiceImpl implements
		AnalyserClientSiteService, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	private AnalyserClientSiteRepository repository;
	
	/* (non-Javadoc)
	 * @see com.disys.analyzer.service.AnalyserClientSiteService#getAnalyserAllSites(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public List<AnalyserClientSiteDTO> getAnalyserAllSites(String userId,
			String userList, String orderBy, String searchString) {
		// TODO Auto-generated method stub
		return repository.getAnalyserAllSites(userId, userList, orderBy, searchString);
	}

	@Override
	public String addUpdateAnalyserSite(AnalyserClientSite analyserClientSite,
			String userId, int actionType) {
		return repository.addUpdateAnalyserSite(analyserClientSite, userId,actionType);
	}

	@Override
	public AnalyserClientSite getAnalyserClientSiteInfo(String analyserSiteId) {
		return repository.getAnalyserClientSiteInfo(analyserSiteId);
	}
	
	public List<AnalyserClientSiteDTO> getAnalyserAllSitesSickCaluculations(String userId, String clientId, String homeZip, String workFromSource){
		// TODO Auto-generated method stub
		return repository.getAnalyserAllSitesSickCaluculations(userId, clientId, homeZip, workFromSource);
	}
}
