package com.disys.analyzer.service.implementation;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.disys.analyzer.model.dto.BullhornBatchDataIssuesDTO;
import com.disys.analyzer.repository.BullhornBatchDataIssuesRepository;
import com.disys.analyzer.service.BullhornBatchDataIssuesService;
/**
 * @author Sajid
 * 
 */
@Service
@Transactional
public class BullhornBatchDataIssuesServiceImpl implements BullhornBatchDataIssuesService, Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private BullhornBatchDataIssuesRepository bullhornBatchDataIssuesRepository;
	
	@Override
	public List<BullhornBatchDataIssuesDTO> getBullhornBatchDataIssuesList(String userId, Integer dataProcessedId) {
		return bullhornBatchDataIssuesRepository.getBullhornBatchDataIssuesList(userId, dataProcessedId);
	}
}
