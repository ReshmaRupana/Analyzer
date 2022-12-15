/**
 * 
 */
package com.disys.analyzer.repository;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.disys.analyzer.model.AnalyserClientAddress;
import com.disys.analyzer.repository.custom.AnalyserClientAddressRepositoryCustom;

/**
 * @author Sajid
 * 
 */
public interface AnalyserClientAddressRepository extends
		PagingAndSortingRepository<AnalyserClientAddress, Integer>, AnalyserClientAddressRepositoryCustom {
	public Page<AnalyserClientAddress> findAll(
			Example<AnalyserClientAddress> example, Pageable pageable);
}
