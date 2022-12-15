package com.disys.analyzer.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.disys.analyzer.model.dto.BullhornBatchInfoDTO;
import com.disys.analyzer.repository.custom.BullhornBatchInfoRepositoryCustom;

/**
 * @author Sajid
 * 
 */

public interface BullhornBatchInfoRepository extends JpaRepository<BullhornBatchInfoDTO, Long>, BullhornBatchInfoRepositoryCustom {

	List<BullhornBatchInfoDTO> getBullhornBatchInfoList(String userId, String processCode, String batchCode, String searchString);

	BullhornBatchInfoDTO getBullhornBatchInfoById(String userId, Integer batchInfoId);
	
	public void executeProcess(String userId, String batchInfoId);

}
