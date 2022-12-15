/**
 * 
 */
package com.disys.analyzer.service;

import java.util.List;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.disys.analyzer.model.Analyser;
import com.disys.analyzer.model.dto.AnalyserDTO;
import com.disys.analyzer.model.dto.AnalyserTerminateDTO;
import com.disys.analyzer.model.dto.PortfolioDownstreamRequestDTO;
import com.disys.analyzer.model.dto.PortfolioDownstreamResponseDTO;

/**
 * @author Sajid
 * 
 */
public interface AnalyserService
{
	
	public String addUpdateAnalyser(AnalyserDTO analyser, String userId, int actionType);
	
	public long count(Example<Analyser> example);
	
	public Page<Analyser> findAll(Example<Analyser> example, Pageable pageable);
	
	public List<Analyser> getAnalyserList(String userId, String userList, String orderBy, String searchString, String companyCode);
	
	public List<Analyser> getRejectedAnalyserList(String userId, String orderBy, String searchString, String companyCode);
	
	public AnalyserDTO getAnalyserInfo(String analyserId);
	
	public List<AnalyserDTO> getAnalyserHistory(String userId, String analyserId, String orderBy, String searchString);
	
	public String getGenericDescription(String workSiteState, String siteId, String dummyParameter);
	
	public boolean isItemPendingForUser(Integer analyserId, String itemName);
	
	public String approveAnalyser(Integer analyserId, String userId);
	
	public String terminateAnalyser(String userId, Integer analyserId, String terminateDate, String reason, String eligibleForHire,
	        String dentalInsurance, String falseTermination);
	
	public String updateTerminateAnalyser(String userId, Integer analyserId, String terminateDate, String reason, String eligibleForHire,
	        String dentalInsurance, String falseTermination);
	
	public String getWorkforceShifts(Integer analyserId);
	
	public Double[] getHealthAnd401K(Long analyserId);
	
	public List<AnalyserDTO> getAnalyserCommissionHistory(String userId, String analyserId, String searchString, String orderBy);
	
	/*
	 * public List<String> findDuplicateSsn(String ssn); public List<String>
	 * findDuplicateFlsaReference(String flsaReference);
	 */
	
	public Integer findDuplicateSsn(String ssn, Integer parentId, char mode);
	
	public Integer findDuplicateFlsaReference(String flsaReference, Integer parentId, char mode);
	
	public List<Analyser> getAnalyzerDataForUpdate(Integer parentId);
	
	public String updateAnalyserRestrictedData(String userId, String columnName, Integer analyserId, Integer parentId, String oldValue,
	        String newValue);
	
	public List<String> getVerticalsList();
	
	public String rehireAnalyser(AnalyserDTO analyser, String userId);
	
	public String rejectAnalyser(String userId, Integer analyserId, Integer parentId, String rejectReason);
	
	public AnalyserTerminateDTO getAnalyserTerminateInfo(String userId, Integer analyserId);
	
	public String getGenericDescriptionGeneral(String actionType, String value);
	
	public String auditPortofolioWSCall(AnalyserDTO analyser, PortfolioDownstreamRequestDTO request, PortfolioDownstreamResponseDTO response);
	
}
