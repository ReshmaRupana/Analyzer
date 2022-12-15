package com.disys.analyzer.service;

import java.util.List;

import com.disys.analyzer.model.dto.BullhornBatchDataProcessedDTO;

/**
 * @author Sajid
 * 
 */

public interface BullhornBatchDataProcessedService {
	public List<BullhornBatchDataProcessedDTO> getBullhornBatchDataProcessedList(String userId,
			Integer bullhornBatchInfoId);
}
