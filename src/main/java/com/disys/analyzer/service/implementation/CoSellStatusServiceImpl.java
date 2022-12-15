package com.disys.analyzer.service.implementation;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.disys.analyzer.model.dto.CoSellStatusDTO;
import com.disys.analyzer.repository.CoSellStatusRepository;
import com.disys.analyzer.service.CoSellStatusService;
/**
 * @author Saravanan Dhurairaj
 * 
 */
@Service
@Transactional
public class CoSellStatusServiceImpl implements CoSellStatusService, Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private CoSellStatusRepository coSellStatusRepository;
	
	@Autowired
	Map<String, List<CoSellStatusDTO>> coSellStatusDropDownBean;
	
	@Override
	public List<CoSellStatusDTO> getCoSellStatusList(String userId, String companyCode) 
	{
		if(coSellStatusDropDownBean.containsKey(companyCode)) {
			System.out.println("coSellStatusDropDownBean has key : " + companyCode);
			return coSellStatusDropDownBean.get(companyCode);
		}
		List<CoSellStatusDTO> list = coSellStatusRepository.getCoSellStatusList(userId, companyCode);
		coSellStatusDropDownBean.put(companyCode, list);
		return list;
	}
	
	@Override
	public List<CoSellStatusDTO> getCoSellStatusDescription(String userId, String companyCode, String recordCode) 
	{
		return coSellStatusRepository.getCoSellStatusDescription(userId, companyCode, recordCode);
	}
	
}
