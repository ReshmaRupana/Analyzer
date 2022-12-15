package com.disys.analyzer.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.disys.analyzer.model.dto.BullhornBatchDataProcessedDTO;
import com.disys.analyzer.repository.custom.BullhornBatchDataProcessedRepositoryCustom;

/**
 * @author Sajid
 * 
 */

public interface BullhornBatchDataProcessedRepository extends JpaRepository<BullhornBatchDataProcessedDTO, Long>, BullhornBatchDataProcessedRepositoryCustom {

	List<BullhornBatchDataProcessedDTO> getBullhornBatchDataProcessedList(String userId,
			Integer bullhornBatchInfoId);

}
