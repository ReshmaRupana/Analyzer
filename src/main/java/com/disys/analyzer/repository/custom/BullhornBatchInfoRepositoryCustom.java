package com.disys.analyzer.repository.custom;

import java.util.List;
import com.disys.analyzer.model.dto.BullhornBatchInfoDTO;

/**
 * @author Sajid
 * 
 */

public interface BullhornBatchInfoRepositoryCustom {
	public List<BullhornBatchInfoDTO> getBullhornBatchInfoList(String userId, String processCode, String batchCode, String searchString);
	public BullhornBatchInfoDTO getBullhornBatchInfoById(String userId, Integer batchInfoId);
	public void executeProcess(String userId, String batchInfoId);
}
