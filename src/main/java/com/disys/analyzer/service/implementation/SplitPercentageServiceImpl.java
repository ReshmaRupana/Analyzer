package com.disys.analyzer.service.implementation;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.disys.analyzer.model.dto.SplitPercentageDTO;
import com.disys.analyzer.repository.SplitPercentageRepository;
import com.disys.analyzer.service.SplitPercentageService;
/**
 * @author Saravanan Dhurairaj
 * 
 */
@Service
@Transactional
public class SplitPercentageServiceImpl implements SplitPercentageService, Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private SplitPercentageRepository splitPercentageRepository;
	
	@Autowired
	Map<String, List<SplitPercentageDTO>> splitPercentageDropDownBean;
	
	/* (non-Javadoc)
	 * @see com.disys.analyzer.service.SplitPercentageService#getSplitPercentage(java.lang.String)
	 */
	@Override
	public List<SplitPercentageDTO> getSplitPercentageList(String userId, String companyCode) 
	{
		if(splitPercentageDropDownBean.containsKey(companyCode)) {
			System.out.println("splitPercentageDropDownBean has key : " + companyCode);
			return splitPercentageDropDownBean.get(companyCode);
		}
		List<SplitPercentageDTO> list = splitPercentageRepository.getSplitPercentageList(userId, companyCode);
		splitPercentageDropDownBean.put(companyCode, list);
		return list; 
	}
	
	@Override
	public List<SplitPercentageDTO> getSplitPercentageDescription(String userId, String companyCode, String recordCode) 
	{
		return splitPercentageRepository.getSplitPercentageDescription(userId, companyCode, recordCode);
	}
	
}
