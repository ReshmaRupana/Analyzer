package com.disys.analyzer.service.implementation;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.disys.analyzer.model.dto.VeteranStatusDTO;
import com.disys.analyzer.repository.VeteranStatusRepository;
import com.disys.analyzer.service.VeteranStatusService;
/**
 * @author Saravanan Dhurairaj
 * 
 */
@Service
@Transactional
public class VeteranStatusServiceImpl implements VeteranStatusService, Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private VeteranStatusRepository veteranStatusRepository;
	
	@Autowired
	Map<String, List<VeteranStatusDTO>> veteranStatusDownBean;
	
	@Override
	public List<VeteranStatusDTO> getVeteranStatusList(String userId, String companyCode) 
	{
		if(veteranStatusDownBean.containsKey(companyCode)) {
			System.out.println("veteranStatusDownBean has key : " + companyCode);
			return veteranStatusDownBean.get(companyCode);
		}
		List<VeteranStatusDTO> list = veteranStatusRepository.getVeteranStatusList(userId, companyCode);
		veteranStatusDownBean.put(companyCode, list);
		return list;
	}
	
	@Override
	public List<VeteranStatusDTO> getVeteranStatusDescription(String userId, String companyCode, String recordCode) 
	{
		return veteranStatusRepository.getVeteranStatusDescription(userId, companyCode, recordCode);
	}
	
}
