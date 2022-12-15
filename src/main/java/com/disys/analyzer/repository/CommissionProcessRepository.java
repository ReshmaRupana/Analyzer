/**
 * 
 */
package com.disys.analyzer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.disys.analyzer.model.CommissionProcess;
import com.disys.analyzer.repository.custom.CommissionProcessRepositoryCustom;

/**
 * @author Sajid
 *
 */
public interface CommissionProcessRepository extends
		JpaRepository<CommissionProcess, Integer>,
		CommissionProcessRepositoryCustom {
	
	CommissionProcess findByAccountingPeriodAndFiscalYear(Integer accountingPeriod, Integer fiscalYear);

}
