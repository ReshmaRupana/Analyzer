package com.disys.analyzer.service.implementation;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.disys.analyzer.model.dto.VerticalDTO;
import com.disys.analyzer.repository.VerticalRepository;
import com.disys.analyzer.service.VerticalService;
/**
 * @author Saravanan Dhurairaj
 * 
 */
@Service
@Transactional
public class VerticalServiceImpl implements VerticalService, Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private VerticalRepository verticalRepository;
	
	@Autowired
	Map<String, List<VerticalDTO>> verticalDropDownBean;
	
	@Override
	public List<VerticalDTO> getVerticalList(String userId, String companyCode) 
	{
		if(verticalDropDownBean.containsKey(companyCode)) {
			System.out.println("getVerticalList has key : " + companyCode);
			return verticalDropDownBean.get(companyCode);
		}
		List<VerticalDTO> list = verticalRepository.getVerticalList(userId, companyCode);
		verticalDropDownBean.put(companyCode, list);
		return list;
	}
	
	@Override
	public List<VerticalDTO> getVerticalDescription(String userId, String companyCode, String recordCode) 
	{
		return verticalRepository.getVerticalDescription(userId, companyCode, recordCode);
	}
	
}
