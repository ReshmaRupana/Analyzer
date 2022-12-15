/**
 * 
 */
package com.disys.analyzer.repository;

import java.util.List;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.disys.analyzer.model.Analyser;
import com.disys.analyzer.model.dto.AnalyserDTO;
import com.disys.analyzer.repository.custom.AnalyserRepositoryCustom;

/**
 * @author Sajid
 * 
 */
public interface AnalyserRepository extends
		PagingAndSortingRepository<Analyser, Long>, AnalyserRepositoryCustom {
	public long count(Example<Analyser> example);

	public Page<Analyser> findAll(Example<Analyser> example,
			Pageable pageable);
	
	@Query(nativeQuery=true)
	public List<Object[]> getHealthAnd401K(@Param("analyserId")Long analyserId);
	
	/*@Query(nativeQuery=true)
	public List<String> findDuplicateSsn(@Param("ssn") String ssn);
	
	@Query(nativeQuery=true)
	public List<String> findDuplicateFlsaReference(@Param("flsaReference") String flsaReference);*/
	
}
