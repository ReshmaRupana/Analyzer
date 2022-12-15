package com.disys.analyzer.service.implementation;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.disys.analyzer.model.dto.MajorityWorkPerformedDTO;
import com.disys.analyzer.repository.MajorityWorkPerformedRepository;
import com.disys.analyzer.service.MajorityWorkPerformedService;

/**
 * @author Reshma Rupanagudi
 * 
 */
@Service
@Transactional
public class MajorityWorkPerformedServiceImpl implements MajorityWorkPerformedService, Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private MajorityWorkPerformedRepository majorityWorkPerformedRepository;
	
	@Autowired
	Map<String, List<MajorityWorkPerformedDTO>> majorityWorkPerformedDownBean;

	@Override
	public List<MajorityWorkPerformedDTO> getMajorityWorkPerformedList(String userId, String companyCode) 
	{
		if(majorityWorkPerformedDownBean.containsKey(companyCode)) {
			System.out.println("MajorityWorkPerformedBean has key : " + companyCode);
			return majorityWorkPerformedDownBean.get(companyCode);
		}
		List<MajorityWorkPerformedDTO> list = majorityWorkPerformedRepository.getMajorityWorkPerformedList(userId, companyCode);
		majorityWorkPerformedDownBean.put(companyCode, list);
		return list;
	}
	
	@Override
	public List<MajorityWorkPerformedDTO> getMajorityWorkPerformedDescription(String userId, String companyCode, String recordCode) 
	{
		return majorityWorkPerformedRepository.getMajorityWorkPerformedDescription(userId, companyCode, recordCode);
	}
	
}
