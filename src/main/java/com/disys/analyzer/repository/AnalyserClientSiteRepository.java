/**
 * 
 */
package com.disys.analyzer.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.disys.analyzer.model.AnalyserClientSite;
import com.disys.analyzer.repository.custom.AnalyserClientSiteRepositoryCustom;

/**
 * @author Sajid
 * 
 */
public interface AnalyserClientSiteRepository extends
		PagingAndSortingRepository<AnalyserClientSite, Integer>,
		AnalyserClientSiteRepositoryCustom {

	public AnalyserClientSite findByZipCode(String zipCode);
}
