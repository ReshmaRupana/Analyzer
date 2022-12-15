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

import com.disys.analyzer.model.Analyser;
import com.disys.analyzer.model.dto.AnalyserDTO;
import com.disys.analyzer.model.dto.AnalyserTerminateDTO;
import com.disys.analyzer.model.dto.PortfolioDownstreamRequestDTO;
import com.disys.analyzer.model.dto.PortfolioDownstreamResponseDTO;
import com.disys.analyzer.repository.AnalyserRepository;
import com.disys.analyzer.service.AnalyserService;

/**
 * @author Sajid
 * 
 */
@Service
@Transactional(readOnly = true)
public class AnalyserServiceImpl implements AnalyserService, Serializable
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Autowired private AnalyserRepository repository;
	
	@Override
	public String addUpdateAnalyser(AnalyserDTO analyser, String userId, int actionType)
	{
		return repository.addUpdateAnalyser(analyser, userId, actionType);
	}
	
	public long count(Example<Analyser> example)
	{
		return repository.count(example);
	}
	
	@Override
	public Page<Analyser> findAll(Example<Analyser> example, Pageable pageable)
	{
		return repository.findAll(example, pageable);
	}
	
	@Override
	public List<Analyser> getAnalyserList(String userId, String userList, String orderBy, String searchString,  String companyCode)
	{
		return repository.spGetAnalyserList(userId, userList, orderBy, searchString, companyCode);
	}
	
	@Override
	public List<Analyser> getRejectedAnalyserList(String userId, String orderBy, String searchString, String companyCode)
	{
		return repository.spGetRejectedAnalyserList(userId, orderBy, searchString, companyCode);
	}
	
	@Override
	public AnalyserDTO getAnalyserInfo(String analyserId)
	{
		List<AnalyserDTO> list = repository.spGetAnalyserInfo(analyserId);
		if (list == null)
		{
			return null;
		}
		else
		{
			AnalyserDTO analyser = list.get(0);
			return analyser;
		}
	}
	
	@Override
	public List<AnalyserDTO> getAnalyserHistory(String userId, String analyserId, String orderBy, String searchString)
	{
		return repository.spGetAnalyserHistory(userId, analyserId, orderBy, searchString);
	}
	
	@Override
	public String getGenericDescription(String workSiteState, String siteId, String dummyParameter)
	{
		return repository.getGenericDescription(workSiteState, siteId, dummyParameter);
	}
	
	@Override
	public boolean isItemPendingForUser(Integer analyserId, String itemName)
	{
		return repository.isItemPendingForUser(analyserId, itemName);
	}
	
	@Override
	public String approveAnalyser(Integer analyserId, String userId)
	{
		return repository.approveAnalyser(analyserId, userId);
	}
	
	@Override
	public String terminateAnalyser(String userId, Integer analyserId, String terminateDate, String reason, String eligibleForHire,
	        String dentalInsurance, String falseTermination)
	{
		return repository.terminateAnalyser(userId, analyserId, terminateDate, reason, eligibleForHire, dentalInsurance, falseTermination);
	}
	
	@Override
	public String updateTerminateAnalyser(String userId, Integer analyserId, String terminateDate, String reason, String eligibleForHire,
	        String dentalInsurance, String falseTermination)
	{
		return repository.updateTerminateAnalyser(userId, analyserId, terminateDate, reason, eligibleForHire, dentalInsurance, falseTermination);
	}
	
	@Override
	public String getWorkforceShifts(Integer analyserId)
	{
		return repository.getWorkforceShifts(analyserId);
	}
	
	@Override
	public Double[] getHealthAnd401K(Long analyserId)
	{
		List<Object[]> results = repository.getHealthAnd401K(analyserId);
		Double returnValues[] = new Double[2];
		try
		{
			for (Object[] obj : results)
			{
				System.out.println("Value : " + obj[0]);
				returnValues[0] = Double.valueOf(obj[0].toString());
				returnValues[1] = Double.valueOf(obj[1].toString());
			}
		}
		catch (Exception ex)
		{
			System.out.println("Method getHealthAnd401K, error concerting EligibleForHire And DentalInsurance values to double.");
			ex.printStackTrace();
			returnValues[0] = 0.0;
			returnValues[1] = 0.0;
		}
		
		return returnValues;
	}
	
	@Override
	public List<AnalyserDTO> getAnalyserCommissionHistory(String userId, String analyserId, String searchString, String orderBy)
	{
		return repository.getAnalyserCommissionHistory(userId, analyserId, searchString, orderBy);
	}
	
	@Override
	public Integer findDuplicateSsn(String ssn, Integer parentId, char mode)
	{
		return repository.spGetDuplicateSSN(ssn, parentId, mode);
	}
	
	@Override
	public Integer findDuplicateFlsaReference(String flsaReference, Integer parentId, char mode)
	{
		return repository.spGetDuplicateFLSA(flsaReference, parentId, mode);
	}
	
	@Override
	public List<Analyser> getAnalyzerDataForUpdate(Integer parentId)
	{
		return repository.getAnalyzerDataForUpdate(parentId);
	}
	
	@Override
	public String updateAnalyserRestrictedData(String userId, String columnName, Integer analyzerId, Integer parentId, String oldValue,
	        String newValue)
	{
		return repository.updateAnalyserRestrictedData(userId, columnName, analyzerId, parentId, oldValue, newValue);
	}
	
	@Override
	public List<String> getVerticalsList()
	{
		return repository.getVerticalsList();
	}
	
	@Override
	public String rehireAnalyser(AnalyserDTO analyser, String userId)
	{
		return repository.rehireAnalyser(analyser, userId);
	}
	
	@Override
	public String rejectAnalyser(String userId, Integer analyserId, Integer parentId, String rejectReason)
	{
		return repository.rejectAnalyser(userId, analyserId, parentId, rejectReason);
	}

	@Override
	public AnalyserTerminateDTO getAnalyserTerminateInfo(String userId, Integer analyserId)
	{
		return repository.getAnalyserTerminateInfo(userId, analyserId);
	}
	
	@Override
	public String getGenericDescriptionGeneral(String actionType, String value)
	{
		return repository.getGenericDescriptionGeneral(actionType, value);
	}
	
	@Override
	public String auditPortofolioWSCall(AnalyserDTO analyser, PortfolioDownstreamRequestDTO request, PortfolioDownstreamResponseDTO response) {
		return repository.auditPortofolioWSCall(analyser, request, response);
	}
}