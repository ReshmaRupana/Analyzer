package com.disys.analyzer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.disys.analyzer.model.dto.BullhornBatchProcessDTO;
import com.disys.analyzer.repository.custom.BullhornBatchProcessRepositoryCustom;

/**
 * @author Sajid
 * 
 */

public interface BullhornBatchProcessRepository extends JpaRepository<BullhornBatchProcessDTO, Long>, BullhornBatchProcessRepositoryCustom {

}
