package com.disys.analyzer.service.implementation;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.disys.analyzer.model.dto.BonusPercentageDTO;
import com.disys.analyzer.repository.BonusPercentageRepository;
import com.disys.analyzer.service.BonusPercentageService;
/**
 * @author Saravanan Dhurairaj
 * 
 */
@Service
@Transactional
public class BonusPercentageServiceImpl implements BonusPercentageService, Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private BonusPercentageRepository bonusPercentageRepository;
	
	@Autowired
	Map<String, List<BonusPercentageDTO>> bonusPercentageDownBean;
	
	@Override
	public List<BonusPercentageDTO> getBonusPercentageList(String userId, String companyCode) 
	{
		if(bonusPercentageDownBean.containsKey(companyCode)) {
			System.out.println("bonusPercentageDownBean has key : " + companyCode);
			return bonusPercentageDownBean.get(companyCode);
		}
		List<BonusPercentageDTO> list = bonusPercentageRepository.getBonusPercentageList(userId, companyCode);
		bonusPercentageDownBean.put(companyCode, list);
		return list;
	}
	
	@Override
	public List<BonusPercentageDTO> getBonusPercentageDescription(String userId, String companyCode, String recordCode) 
	{
		return bonusPercentageRepository.getBonusPercentageDescription(userId, companyCode, recordCode);
	}
	
}
