package com.disys.analyzer.repository.custom;

import java.util.List;
import com.disys.analyzer.model.dto.BullhornBatchProcessDTO;

/**
 * @author Sajid
 * 
 */

public interface BullhornBatchProcessRepositoryCustom {
	public List<BullhornBatchProcessDTO> getBullhornBatchProcessList(String userId, String processCode, String batchCode, String searchString);
	public List<BullhornBatchProcessDTO> getBullhornBatchProcessDescription(String userId, String recordCode);
}
