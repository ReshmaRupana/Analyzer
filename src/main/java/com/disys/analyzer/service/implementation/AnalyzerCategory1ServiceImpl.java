package com.disys.analyzer.service.implementation;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.disys.analyzer.model.dto.AnalyzerCategory1DTO;
import com.disys.analyzer.repository.AnalyzerCategory1Repository;
import com.disys.analyzer.service.AnalyzerCategory1Service;
/**
 * @author Saravanan Dhurairaj
 * 
 */
@Service
@Transactional
public class AnalyzerCategory1ServiceImpl implements AnalyzerCategory1Service, Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private AnalyzerCategory1Repository analyzerCategory1Repository;
	
	@Autowired
	Map<String, List<AnalyzerCategory1DTO>> analyzerCategory1DownBean;
	
	@Override
	public List<AnalyzerCategory1DTO> getAnalyzerCategory1List(String userId, String companyCode) 
	{
		if(analyzerCategory1DownBean.containsKey(companyCode)) {
			System.out.println("analyzerCategory1DownBean has key : " + companyCode);
			return analyzerCategory1DownBean.get(companyCode);
		}
		List<AnalyzerCategory1DTO> list = analyzerCategory1Repository.getAnalyzerCategory1List(userId, companyCode);
		analyzerCategory1DownBean.put(companyCode, list);
		return list;
	}
	
	@Override
	public List<AnalyzerCategory1DTO> getAnalyzerCategory1Description(String userId, String companyCode, String recordCode) 
	{
		return analyzerCategory1Repository.getAnalyzerCategory1Description(userId, companyCode, recordCode);
	}
	
}
