package com.disys.analyzer.service.implementation;

import java.io.Serializable;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.disys.analyzer.model.dto.TerminationReasonDTO;
import com.disys.analyzer.repository.TerminationReasonRepository;
import com.disys.analyzer.service.TerminationReasonService;
/**
 * @author Saravanan Dhurairaj
 * 
 */
@Service
@Transactional
public class TerminationReasonServiceImpl implements TerminationReasonService, Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private TerminationReasonRepository terminationReasonRepository;
	
	@Override
	public List<TerminationReasonDTO> getTerminationReasonList(String userId, String companyCode) 
	{
		return terminationReasonRepository.getTerminationReasonList(userId, companyCode);
	}
	
	@Override
	public List<TerminationReasonDTO> getTerminationReasonDescription(String userId, String companyCode, String recordCode) 
	{
		return terminationReasonRepository.getTerminationReasonDescription(userId, companyCode, recordCode);
	}
	
}
