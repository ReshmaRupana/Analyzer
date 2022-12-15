package com.disys.analyzer.service;

import java.util.List;

import com.disys.analyzer.model.dto.BullhornBatchDataIssuesDTO;

/**
 * @author Sajid
 * 
 */

public interface BullhornBatchDataIssuesService {

	public List<BullhornBatchDataIssuesDTO> getBullhornBatchDataIssuesList(String userId, Integer dataProcessedId);
}
