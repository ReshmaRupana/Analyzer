package com.disys.analyzer.service.implementation;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.disys.analyzer.model.dto.UnemployedStatusDTO;
import com.disys.analyzer.repository.UnemployedStatusRepository;
import com.disys.analyzer.service.UnemployedStatusService;
/**
 * @author Saravanan Dhurairaj
 * 
 */
@Service
@Transactional
public class UnemployedStatusServiceImpl implements UnemployedStatusService, Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private UnemployedStatusRepository unemployedStatusRepository;
	
	@Autowired
	Map<String, List<UnemployedStatusDTO>> unemployedStatusDownBean;
	
	@Override
	public List<UnemployedStatusDTO> getUnemployedStatusList(String userId, String companyCode) 
	{
		if(unemployedStatusDownBean.containsKey(companyCode)) {
			System.out.println("unemployedStatusDownBean has key : " + companyCode);
			return unemployedStatusDownBean.get(companyCode);
		}
		List<UnemployedStatusDTO> list = unemployedStatusRepository.getUnemployedStatusList(userId, companyCode);
		unemployedStatusDownBean.put(companyCode, list);
		return list;
	}
	
	@Override
	public List<UnemployedStatusDTO> getUnemployedStatusDescription(String userId, String companyCode, String recordCode) 
	{
		return unemployedStatusRepository.getUnemployedStatusDescription(userId, companyCode, recordCode);
	}
	
}
