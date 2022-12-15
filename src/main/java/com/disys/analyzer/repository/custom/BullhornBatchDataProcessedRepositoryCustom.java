package com.disys.analyzer.repository.custom;

import java.util.List;
import com.disys.analyzer.model.dto.BullhornBatchDataProcessedDTO;

/**
 * @author Sajid
 * 
 */

public interface BullhornBatchDataProcessedRepositoryCustom {
	List<BullhornBatchDataProcessedDTO> getBullhornBatchDataProcessedList(String userId,
			Integer bullhornBatchInfoId);
}
