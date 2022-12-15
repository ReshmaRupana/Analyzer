package com.disys.analyzer.service.implementation;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.disys.analyzer.model.dto.DealTypeDTO;
import com.disys.analyzer.repository.DealTypeRepository;
import com.disys.analyzer.service.DealTypeService;

/**
 * @author Saravanan Dhurairaj
 * 
 */
@Service
@Transactional
public class DealTypeServiceImpl implements DealTypeService, Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private DealTypeRepository dealTypeRepository;
	
	@Autowired
	Map<String, List<DealTypeDTO>> dealTypeDropDownBean;
	
	@Override
	public List<DealTypeDTO> getDealTypeList(String userId, String companyCode) 
	{
		if(dealTypeDropDownBean.containsKey(companyCode)) {
			System.out.println("dealTypeDropDownBean has key : " + companyCode);
			return dealTypeDropDownBean.get(companyCode);
		}
		List<DealTypeDTO> list = dealTypeRepository.getDealTypeList(userId, companyCode);
		dealTypeDropDownBean.put(companyCode, list);
		return list;
	}
	
	@Override
	public List<DealTypeDTO> getDealTypeDescription(String userId, String companyCode, String recordCode) 
	{
		return dealTypeRepository.getDealTypeDescription(userId, companyCode, recordCode);
	}
	
}
