package com.disys.analyzer.service;

import java.util.List;

import com.disys.analyzer.model.dto.BullhornBatchProcessDTO;

/**
 * @author Sajid
 * 
 */

public interface BullhornBatchProcessService {
	public List<BullhornBatchProcessDTO> getBullhornBatchProcessList(String userId, String processCode, String batchCode, String searchString);
}
