package com.disys.analyzer.service.implementation;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.disys.analyzer.model.dto.JobCategoryDTO;
import com.disys.analyzer.repository.JobCategoryRepository;
import com.disys.analyzer.service.JobCategoryService;

/**
 * @author Saravanan Dhurairaj
 * 
 */
@Service
@Transactional
public class JobCategoryServiceImpl implements JobCategoryService, Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private JobCategoryRepository jobCategoryRepository;
	
	@Autowired
	Map<String, List<JobCategoryDTO>> jobCategoryDownBean;
	
	@Override
	public List<JobCategoryDTO> getJobCategoryList(String userId, String companyCode) 
	{		
		if(jobCategoryDownBean.containsKey(companyCode)) {
			System.out.println("jobCategoryDownBean has key : " + companyCode);
			return jobCategoryDownBean.get(companyCode);
		}
		List<JobCategoryDTO> list = jobCategoryRepository.getJobCategoryList(userId, companyCode);
		jobCategoryDownBean.put(companyCode, list);
		return list;
	}
	
	@Override
	public List<JobCategoryDTO> getJobCategoryDescription(String userId, String companyCode, String recordCode) 
	{
		return jobCategoryRepository.getJobCategoryDescription(userId, companyCode, recordCode);
	}
	
}
