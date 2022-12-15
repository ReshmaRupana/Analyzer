/**
 * 
 */
package com.disys.analyzer.repository.custom;

import java.util.List;

import org.springframework.data.jpa.repository.query.Procedure;

import com.disys.analyzer.model.Analyser;
import com.disys.analyzer.model.dto.AnalyserDTO;
import com.disys.analyzer.model.dto.AnalyserTerminateDTO;
import com.disys.analyzer.model.dto.PortfolioDownstreamRequestDTO;
import com.disys.analyzer.model.dto.PortfolioDownstreamResponseDTO;

/**
 * @author Sajid
 * 
 */
public interface AnalyserRepositoryCustom {

	@Procedure
	public String addUpdateAnalyser(AnalyserDTO analyser, String userId,
			int actionType);

	@Procedure
	public List<Analyser> spGetAnalyserList(String userId, String userList,
			String orderBy, String searchString, String companyCode);
	
	@Procedure
	public List<Analyser> spGetRejectedAnalyserList(String userId, String orderBy, String searchString, String companyCode);
	
	@Procedure
	public List<Object> getAnalyzerDataDumpFile(String userId);
	
	
	public List<AnalyserDTO> spGetAnalyserInfo(String analyserId);
	
	public List<AnalyserDTO> spGetAnalyserHistory(String userId, String analyserId, String orderBy, String searchString);
	
	public String getGenericDescription(String workSiteState,String siteId, String dummyParameter);
	
	public boolean isItemPendingForUser(Integer analyserId, String itemName);
	
	public String approveAnalyser(Integer analyserId, String userId);
	
	public String terminateAnalyser(String userId, Integer analyserId, String terminateDate, String reason, String eligibleForHire, String dentalInsurance,String falseTermination);
	
	public String updateTerminateAnalyser(String userId, Integer analyserId, String terminateDate, String reason, String eligibleForHire, String dentalInsurance,String falseTermination);
	
	public String getWorkforceShifts(Integer analyserId);
	
	public List<AnalyserDTO> getAnalyserCommissionHistory(String userId,String analyserId, String searchString, String orderBy);
	
	public Integer spGetDuplicateFLSA(String flsaReference, Integer parentId, char mode);
	
	public Integer spGetDuplicateSSN(String ssn, Integer parentId, char mode);
	
	public List<Analyser> getAnalyzerDataForUpdate(Integer parentId);
	
	public String updateAnalyserRestrictedData(String userId,String columnName,Integer analyserId,Integer parentId,String oldValue,String newValue);
	
	public List<String> getVerticalsList();
	
	public String rehireAnalyser(AnalyserDTO analyser, String userId);
	
	public String rejectAnalyser(String userId, Integer analyserId, Integer parentId, String rejectReason);
	
	public AnalyserTerminateDTO getAnalyserTerminateInfo(String userId, Integer analyserId);
	
	public String getGenericDescriptionGeneral(String actionType, String value);
	
	public String auditPortofolioWSCall(AnalyserDTO analyser, PortfolioDownstreamRequestDTO request, PortfolioDownstreamResponseDTO response);
	
}