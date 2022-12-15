package com.disys.analyzer.service.implementation;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.disys.analyzer.model.dto.AnalyserCategory2DTO;
import com.disys.analyzer.repository.AnalyserCategory2Repository;
import com.disys.analyzer.service.AnalyserCategory2Service;


/**
 * @author Saravanan Dhurairaj
 * 
 */
@Service
@Transactional
public class AnalyserCategory2ServiceImpl implements AnalyserCategory2Service, Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private AnalyserCategory2Repository analyserCategory2Repository;
	
	@Autowired
	Map<String, List<AnalyserCategory2DTO>> analyserCategory2DropDownBean;

	@Override
	public List<AnalyserCategory2DTO> getAnalyserCategory2List(String userId, String companyCode) 
	{
		if(analyserCategory2DropDownBean.containsKey(companyCode)) {
			System.out.println("analyserCategory2DropDownBean has key : " + companyCode);
			return analyserCategory2DropDownBean.get(companyCode);
		}
		List<AnalyserCategory2DTO> list = analyserCategory2Repository.getAnalyserCategory2List(userId, companyCode);
		analyserCategory2DropDownBean.put(companyCode, list);
		return list;		
	}
	
	@Override
	public List<AnalyserCategory2DTO> getAnalyserCategory2Description(String userId, String companyCode, String recordCode) 
	{
		return analyserCategory2Repository.getAnalyserCategory2Description(userId, companyCode, recordCode);
	}
	
}
