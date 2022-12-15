/**
 * 
 */
package com.disys.analyzer.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.disys.analyzer.model.AnalyserCommisionPerson;
import com.disys.analyzer.repository.custom.AnalyserCommisionPersonRepositoryCustom;

/**
 * @author Sajid
 * 
 */
public interface AnalyserCommisionPersonRepository extends
		PagingAndSortingRepository<AnalyserCommisionPerson, Integer>,
		AnalyserCommisionPersonRepositoryCustom {

	static final String query = "Select PersonName from Analyser_Commision_Persons where org_id = (select org_id from Users where user_id=?1) and status = 1 order by PersonName;";

	@Query(value = query, nativeQuery = true)
	public List<String> findByPersonName(String userId);

	/**
	 * 
	 * 
	 * User findFirstByOrderByLastnameAsc();
	 * 
	 * User findTopByOrderByAgeDesc();
	 * 
	 * Page<User> queryFirst10ByLastname(String lastname, Pageable pageable);
	 * 
	 * Slice<User> findTop3ByLastname(String lastname, Pageable pageable);
	 * 
	 * List<User> findFirst10ByLastname(String lastname, Sort sort);
	 * 
	 * List<User> findTop10ByLastname(String lastname, Pageable pageable);
	 * 
	 * 
	 * 
	 */
}
