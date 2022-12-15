package com.disys.analyzer.service.implementation;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.disys.analyzer.model.dto.SickLeaveTypeDTO;
import com.disys.analyzer.repository.SickLeaveTypeRepository;
import com.disys.analyzer.service.SickLeaveTypeService;
/**
 * @author Saravanan Dhurairaj
 * 
 */
@Service
@Transactional
public class SickLeaveTypeServiceImpl implements SickLeaveTypeService, Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private SickLeaveTypeRepository sickLeaveTypeRepository;
	
	@Autowired
	Map<String, List<SickLeaveTypeDTO>> sickLeaveTypeDownBean;
	
	@Override
	public List<SickLeaveTypeDTO> getSickLeaveTypeList(String userId, String companyCode) 
	{
		if(sickLeaveTypeDownBean.containsKey(companyCode)) {
			System.out.println("sickLeaveTypeDownBean has key : " + companyCode);
			return sickLeaveTypeDownBean.get(companyCode);
		}
		List<SickLeaveTypeDTO> list = sickLeaveTypeRepository.getSickLeaveTypeList(userId, companyCode);
		sickLeaveTypeDownBean.put(companyCode, list);
		return list;
	}
	
	@Override
	public List<SickLeaveTypeDTO> getSickLeaveTypeDescription(String userId, String companyCode, String recordCode) 
	{
		return sickLeaveTypeRepository.getSickLeaveTypeDescription(userId, companyCode, recordCode);
	}
	
}
