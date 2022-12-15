/**
 * 
 */
package com.disys.analyzer.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.disys.analyzer.model.dto.ChartDataMapping;
import com.disys.analyzer.repository.custom.AnalyserChartDataRepositoryCustom;

/**
 * @author Sajid
 * 
 *
 */
public interface AnalyserChartDataRepository extends
		PagingAndSortingRepository<ChartDataMapping, Integer>,
		AnalyserChartDataRepositoryCustom {

}
