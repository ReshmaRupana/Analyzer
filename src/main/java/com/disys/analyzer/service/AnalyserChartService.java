/**
 * 
 */
package com.disys.analyzer.service;

import java.util.List;

import com.disys.analyzer.model.dto.ChartDataMapping;

/**
 * @author Sajid
 * 
 * 
 */
public interface AnalyserChartService {
	public List<ChartDataMapping> getTotalCommissionInYearOnCommissionType(
			String commissionPersonEmplIdOrOprId, String fiscalYear);

	public List<ChartDataMapping> getCommissionInEachMonthOnType(
			String commissionPersonEmplIdOrOprId, String fiscalYear);

	public List<ChartDataMapping> getComparablePayableCommissionOnPerMonthBasis(
			String commissionPersonEmplIdOrOprId, String fiscalYear);

	public List<ChartDataMapping> getPayableCommissionOnPerMonthBasis(
			String commissionPersonEmplIdOrOprId, String fiscalYear);
}
