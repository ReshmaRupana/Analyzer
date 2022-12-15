package com.disys.analyzer.service.implementation;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.disys.analyzer.model.dto.FLSAStatusDTO;
import com.disys.analyzer.repository.FLSAStatusRepository;
import com.disys.analyzer.service.FLSAStatusService;

/**
 * @author Saravanan Dhurairaj
 * 
 */
@Service
@Transactional
public class FLSAStatusServiceImpl implements FLSAStatusService, Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private FLSAStatusRepository flsaStatusRepository;
	
	@Autowired
	Map<String, List<FLSAStatusDTO>> flsaStatusDownBean;

	@Override
	public List<FLSAStatusDTO> getFLSAStatusList(String userId, String companyCode) 
	{
		if(flsaStatusDownBean.containsKey(companyCode)) {
			System.out.println("flsaStatusDownBean has key : " + companyCode);
			return flsaStatusDownBean.get(companyCode);
		}
		List<FLSAStatusDTO> list = flsaStatusRepository.getFLSAStatusList(userId, companyCode);
		flsaStatusDownBean.put(companyCode, list);
		return list;
	}
	
	@Override
	public List<FLSAStatusDTO> getFLSAStatusDescription(String userId, String companyCode, String recordCode) 
	{
		return flsaStatusRepository.getFLSAStatusDescription(userId, companyCode, recordCode);
	}
	
}
