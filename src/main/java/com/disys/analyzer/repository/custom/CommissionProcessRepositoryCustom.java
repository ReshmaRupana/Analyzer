/**
 * 
 */
package com.disys.analyzer.repository.custom;	

import java.util.List;
import java.util.Map;

import com.disys.analyzer.model.CommissionProcess;

/**
 * @author Sajid
 *
 */
public interface CommissionProcessRepositoryCustom {
	List<Map<String, Object>> findAllOrderByAccountingPeriod() throws Exception;
	List<Map<String, Object>> findAccountingPeriod() throws Exception;
	
//	CommissionProcess findByAccountingPeriodAndFiscalYear(Integer accountingPeriod, Integer fiscalYear);
	public Integer saveCommissionProcess(CommissionProcess commissionProcess);
	
	public Integer updateCommissionProcess(CommissionProcess commissionProcess);
	
	public String executeCommissionProcess(CommissionProcess commissionProcess);
	
	public String finalizeCommissionProcess(Integer accountingPeriod,Integer fiscalYear);
}
