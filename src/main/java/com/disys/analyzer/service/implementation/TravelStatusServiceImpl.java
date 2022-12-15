package com.disys.analyzer.service.implementation;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.disys.analyzer.model.dto.TravelStatusDTO;
import com.disys.analyzer.repository.TravelStatusRepository;
import com.disys.analyzer.service.TravelStatusService;
/**
 * @author Saravanan Dhurairaj
 * 
 */
@Service
@Transactional
public class TravelStatusServiceImpl implements TravelStatusService, Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private TravelStatusRepository travelStatusRepository;
	
	@Autowired
	Map<String, List<TravelStatusDTO>> travelStatusDownBean;
	
	@Override
	public List<TravelStatusDTO> getTravelStatusList(String userId, String companyCode) 
	{
		if(travelStatusDownBean.containsKey(companyCode)) {
			System.out.println("travelStatusDownBean has key : " + companyCode);
			return travelStatusDownBean.get(companyCode);
		}
		List<TravelStatusDTO> list = travelStatusRepository.getTravelStatusList(userId, companyCode);
		travelStatusDownBean.put(companyCode, list);
		return list;
	}
	
	@Override
	public List<TravelStatusDTO> getTravelStatusDescription(String userId, String companyCode, String recordCode) 
	{
		return travelStatusRepository.getTravelStatusDescription(userId, companyCode, recordCode);
	}
	
}
