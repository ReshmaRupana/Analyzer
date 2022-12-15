/**
 * 
 */
package com.disys.analyzer.repository;

import java.util.List;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.disys.analyzer.model.User;
import com.disys.analyzer.repository.custom.ApplicationUserRepositoryCustom;

/**
 * @author Sajid
 * @since Jul 3, 2020
 *
 */
public interface ApplicationUserRepository extends
		PagingAndSortingRepository<User, Long>, ApplicationUserRepositoryCustom {
	// User findByUsername(String username);

	User findByUserId(String email);

	User findByInternalUserId(Integer id);

	List<User> findAll();

	public Page<User> findAll(Example<User> example, Pageable pageable);

	/*List<User> findByOrganizationOrgId(Integer orgId);*/
	List<User> findByOrgId(Integer orgId);

	@Query(value = "SELECT u.internal_user_id FROM Users u where u.ORG_ID = ?1", nativeQuery = true)
	public List<Integer> findAllByInternalUserId(Integer orgId);

	public long count(Example<User> example);

	@Query(value = "SELECT u.ROL_ID FROM Users u where u.USER_ID = ?1", nativeQuery = true)
	public Integer getRoleId(String userId);
	
	@Query(value = "SELECT u.ORG_ID FROM Users u where u.USER_ID = ?1", nativeQuery = true)
	public Integer getOrgId(String userId);

	@Transactional
	@Procedure(name = "spSetUserLoginAttempt")
	public int spSetUserLoginAttempt(@Param("varLoggedUserID") String loggedUserID, @Param("varIPAddress") String userIPAddress, @Param("varLoginStatus") String loginStatus);
	
	@Transactional
	@Procedure(name = "spLogoutUserActivity")
	public void spLogoutUserActivity(@Param("varLoggedUserID") String loggedUserID, @Param("varUserLogId") int userLogId);
	
	@Transactional
	@Procedure(name = "spGetAnalyzerPropertyValue")
	public void spGetAnalyzerPropertyValue(@Param("varLoggedUserID") String loggedUserID, @Param("varUserLogId") int userLogId);
	
	@Transactional
	@Procedure(name = "spAddADAnalyzerUserEntry")
	public void spAddADAnalyzerUserEntry(@Param("varLoggedUserID") String loggedUserID, @Param("varADLoginId") String aDLoginId, @Param("varFirstName") String firstName, @Param("varLastName") String lastName, @Param("varStatus") String status);
	
	@Transactional
	@Procedure(name = "spAddUserBatchJobAudit")
	public void spAddUserBatchJobAudit(@Param("varLoggedUserID") String loggedUserID, @Param("varUserLogId") int userLogId);
	
	@Transactional
	@Procedure(name = "spAddActivateADUserToAnalyzer")
	public void spAddActivateADUserToAnalyzer(@Param("varLoggedUserID") String loggedUserID, @Param("varUserLogId") int userLogId);
	
	@Transactional
	@Procedure(name = "spProcessADSyncData")
	public void spProcessADSyncData(@Param("varLoggedUserID") String loggedUserID);
}
