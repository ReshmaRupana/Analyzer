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

import com.disys.analyzer.model.AnalyserClient;
import com.disys.analyzer.repository.custom.AnalyserClientRepositoryCustom;

/**
 * @author Sajid
 * 
 */
public interface AnalyserClientRepository extends
		PagingAndSortingRepository<AnalyserClient, Integer>, AnalyserClientRepositoryCustom {
	public Page<AnalyserClient> findAll(Example<AnalyserClient> example,
			Pageable pageable);

	// static final String query =
	// "Select PersonName from AnalyserClient where org_id = (select org_id from Users where user_id=?1) order by PersonName;";

	public static final StringBuffer query = new StringBuffer(
			" select Client_ID, ");

	// @Query(value = query, nativeQuery = true)
	// public ArrayList getClientList(String userId);
	@Query(nativeQuery=true)
//	public Map<Integer, String> getClientList(@Param("userId")String userId);
	public List<Object[]> getClientList(@Param("userId")String userId);
}
