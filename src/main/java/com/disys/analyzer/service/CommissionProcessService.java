/**
 * 
 */
package com.disys.analyzer.service;

import java.util.List;
import java.util.Map;

import com.disys.analyzer.model.CommissionProcess;

/**
 * @author Sajid
 *
 */
public interface CommissionProcessService {
	List<Map<String, Object>> findAllOrderByAccountingPeriod();
	List<Map<String, Object>> findAccountingPeriod();
	
	CommissionProcess findByAccountingPeriodAndFiscalYear(Integer accountingPeriod,Integer fiscalYear);
	
	public Integer saveCommissionProcess(CommissionProcess commissionProcess);
	public Integer updateCommissionProcess(CommissionProcess commissionProcess);
	
	public String executeCommissionProcess(CommissionProcess commissionProcess);
	
	public String finalizeCommissionProcess(Integer accountingPeriod,Integer fiscalYear);
	
	public void executeAsynchronously(CommissionProcess commissionProcess);
}
