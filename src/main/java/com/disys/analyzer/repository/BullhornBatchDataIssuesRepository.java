package com.disys.analyzer.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.disys.analyzer.model.dto.BullhornBatchDataIssuesDTO;
import com.disys.analyzer.repository.custom.BullhornBatchDataIssuesRepositoryCustom;

/**
 * @author Sajid
 * 
 */

public interface BullhornBatchDataIssuesRepository extends JpaRepository<BullhornBatchDataIssuesDTO, Long>, BullhornBatchDataIssuesRepositoryCustom {

	List<BullhornBatchDataIssuesDTO> getBullhornBatchDataIssuesList(String userId,Integer dataProcessedId);

}
