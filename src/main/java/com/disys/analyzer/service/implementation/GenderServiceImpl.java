package com.disys.analyzer.service.implementation;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.disys.analyzer.model.dto.GenderDTO;
import com.disys.analyzer.repository.GenderRepository;
import com.disys.analyzer.service.GenderService;
/**
 * @author Saravanan Dhurairaj
 * 
 */
@Service
@Transactional
public class GenderServiceImpl implements GenderService, Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private GenderRepository genderRepository;
	
	@Autowired
	Map<String, List<GenderDTO>> genderDropDownBean;
	
	
	@Override
	public List<GenderDTO> getGenderList(String userId, String companyCode) 
	{
		if(genderDropDownBean.containsKey(companyCode)) {
			System.out.println("genderDropDownBean has key : " + companyCode);
			return genderDropDownBean.get(companyCode);
		}
		List<GenderDTO> list = genderRepository.getGenderList(userId, companyCode);
		genderDropDownBean.put(companyCode, list);
		return list;
	}
	
	@Override
	public List<GenderDTO> getGenderDescription(String userId, String companyCode, String recordCode) 
	{
		return genderRepository.getGenderDescription(userId, companyCode, recordCode);
	}
	
}
