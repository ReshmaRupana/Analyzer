package com.disys.analyzer.service.implementation;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.disys.analyzer.model.dto.JobBoardDTO;
import com.disys.analyzer.repository.JobBoardRepository;
import com.disys.analyzer.service.JobBoardService;
/**
 * @author Saravanan Dhurairaj
 * 
 */
@Service
@Transactional
public class JobBoardServiceImpl implements JobBoardService, Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private JobBoardRepository jobBoardRepository;
	
	@Autowired
	Map<String, List<JobBoardDTO>> jobBoardDownBean;
	
	@Override
	public List<JobBoardDTO> getJobBoardList(String userId, String companyCode) 
	{
		if(jobBoardDownBean.containsKey(companyCode)) {
			System.out.println("jobBoardDownBean has key : " + companyCode);
			return jobBoardDownBean.get(companyCode);
		}
		List<JobBoardDTO> list = jobBoardRepository.getJobBoardList(userId, companyCode);
		jobBoardDownBean.put(companyCode, list);
		return list;
	}
	
	@Override
	public List<JobBoardDTO> getJobBoardDescription(String userId, String companyCode, String recordCode) 
	{
		return jobBoardRepository.getJobBoardDescription(userId, companyCode, recordCode);
	}
	
}
