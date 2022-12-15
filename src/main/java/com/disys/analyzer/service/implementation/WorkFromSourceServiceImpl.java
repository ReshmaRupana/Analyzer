package com.disys.analyzer.service.implementation;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.disys.analyzer.model.dto.WorkFromSourceDTO;
import com.disys.analyzer.repository.WorkFromSourceRepository;
import com.disys.analyzer.service.WorkFromSourceService;

/**
 * @author Reshma Rupanagudi
 * 
 */
@Service
@Transactional
public class WorkFromSourceServiceImpl implements WorkFromSourceService, Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private WorkFromSourceRepository workFromSourceRepository;
	
	@Autowired
	Map<String, List<WorkFromSourceDTO>> workFromSourceDownBean;

	@Override
	public List<WorkFromSourceDTO> getWorkFromSourceList(String userId, String companyCode) 
	{
		if(workFromSourceDownBean.containsKey(companyCode)) {
			System.out.println("WorkFromSourceBean has key : " + companyCode);
			return workFromSourceDownBean.get(companyCode);
		}
		List<WorkFromSourceDTO> list = workFromSourceRepository.getWorkFromSourceList(userId, companyCode);
		workFromSourceDownBean.put(companyCode, list);
		return list;
	}
	
	@Override
	public List<WorkFromSourceDTO> getWorkFromSourceDescription(String userId, String companyCode, String recordCode) 
	{
		return workFromSourceRepository.getWorkFromSourceDescription(userId, companyCode, recordCode);
	}
	
}
