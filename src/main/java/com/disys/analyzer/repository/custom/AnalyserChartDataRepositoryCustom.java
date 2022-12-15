/**
 * 
 */
package com.disys.analyzer.repository.custom;

import java.util.List;

import com.disys.analyzer.model.dto.ChartDataMapping;

/**
* @author Sajid
 * @since 14th May, 2018
 *
 */
public interface AnalyserChartDataRepositoryCustom {

	public List<ChartDataMapping> getTotalCommissionInYearOnCommissionType(
			String commissionPersonEmplIdOrOprId, String fiscalYear);

	public List<ChartDataMapping> getCommissionInEachMonthOnType(
			String commissionPersonEmplIdOrOprId, String fiscalYear);

	public List<ChartDataMapping> getComparablePayableCommissionOnPerMonthBasis(
			String commissionPersonEmplIdOrOprId, String fiscalYear);

	public List<ChartDataMapping> getPayableCommissionOnPerMonthBasis(
			String commissionPersonEmplIdOrOprId, String fiscalYear);
}
