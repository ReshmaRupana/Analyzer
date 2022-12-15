package com.disys.analyzer.repository.custom;

import java.util.List;
import com.disys.analyzer.model.dto.BullhornBatchDataIssuesDTO;

/**
 * @author Sajid
 * 
 */

public interface BullhornBatchDataIssuesRepositoryCustom {
	public List<BullhornBatchDataIssuesDTO> getBullhornBatchDataIssuesList(String userId, Integer dataProcessedId);
}
