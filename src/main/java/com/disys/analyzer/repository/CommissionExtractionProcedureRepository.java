/**
 * 
 */
package com.disys.analyzer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.disys.analyzer.model.CommissionExtractionProcedure;
import com.disys.analyzer.repository.custom.CommissionExtractionProcedureRepositoryCustom;

/**
 * @author Sajid
 *
 */
public interface CommissionExtractionProcedureRepository extends
		JpaRepository<CommissionExtractionProcedure, Integer>,
		CommissionExtractionProcedureRepositoryCustom {

}
