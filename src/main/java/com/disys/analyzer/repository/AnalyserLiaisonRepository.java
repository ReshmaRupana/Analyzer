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

import com.disys.analyzer.model.AnalyserLiaison;
import com.disys.analyzer.repository.custom.AnalyserLiaisonRepositoryCustom;

/**
 * @author Sajid
 * 
 */
public interface AnalyserLiaisonRepository extends PagingAndSortingRepository<AnalyserLiaison, String>, AnalyserLiaisonRepositoryCustom
{
	public List<AnalyserLiaison> findByOrgId(Integer orgId);
	
	public Page<AnalyserLiaison> findAll(Pageable pageable);
	
	public Page<AnalyserLiaison> findAll(Example<AnalyserLiaison> example, Pageable pageable);
	
	@Query(nativeQuery = true)
	public List<String> getLiaisonList(@Param("userId") String userId);
}
