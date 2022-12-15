/**
 * 
 */
package com.disys.analyzer.service.implementation;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.disys.analyzer.model.dto.ChartDataMapping;
import com.disys.analyzer.repository.AnalyserChartDataRepository;
import com.disys.analyzer.service.AnalyserChartService;

/**
 * @author Sajid
 * 
 */
@Service
@Transactional(readOnly = true)
public class AnalyserChartServiceImpl implements AnalyserChartService, Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	private AnalyserChartDataRepository repositoy;

	@Override
	public List<ChartDataMapping> getTotalCommissionInYearOnCommissionType(
			String commissionPersonEmplIdOrOprId, String fiscalYear) {
		return repositoy.getTotalCommissionInYearOnCommissionType(commissionPersonEmplIdOrOprId, fiscalYear);
	}

	@Override
	public List<ChartDataMapping> getCommissionInEachMonthOnType(
			String commissionPersonEmplIdOrOprId, String fiscalYear) {
		return repositoy.getCommissionInEachMonthOnType(commissionPersonEmplIdOrOprId, fiscalYear);
	}

	@Override
	public List<ChartDataMapping> getComparablePayableCommissionOnPerMonthBasis(
			String commissionPersonEmplIdOrOprId, String fiscalYear) {
		return repositoy.getComparablePayableCommissionOnPerMonthBasis(commissionPersonEmplIdOrOprId, fiscalYear);
	}

	@Override
	public List<ChartDataMapping> getPayableCommissionOnPerMonthBasis(
			String commissionPersonEmplIdOrOprId, String fiscalYear) {
		return repositoy.getPayableCommissionOnPerMonthBasis(commissionPersonEmplIdOrOprId, fiscalYear);
	}

}
