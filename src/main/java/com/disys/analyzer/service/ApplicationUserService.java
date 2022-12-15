/**
 * 
 */
package com.disys.analyzer.service;

import java.util.List;

import javax.faces.model.SelectItem;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.disys.analyzer.model.User;
import com.disys.analyzer.model.dto.UserDTO;

/**
 * @author Sajid
 * 
 */
public interface ApplicationUserService {

	User findByUsername(String username);

	User findByEmail(String email);

	boolean checkUserExists(String username, String email);

	boolean checkUsernameExists(String username);

	boolean checkEmailExists(String email);

	void save(User user);

	// User createUser(User user, Set<UserRole> userRoles);

	User saveUser(User user);

	List<User> findUserList();

	void enableUser(String username);

	void disableUser(String username);

	Long count();

	public Page<User> findAll(Example<User> example, Pageable pageable);

	public long count(Example<User> example);

	public Integer getRoleId(String userId);

/*public List<User> getUserList(String userId, String assignedDevice,
			String lastName, String firstName);*/
	
	public List<User> getUserList(String userId, String searchString, String companyCode);
	
	public String addUser(User user, String loggedUserId, int actionType);
	
	public String userIdValidation(String newUserId);
	
	public String employeeIdValidation(String newEmployeeId);

	public String updateUser(User user, String loggedUserId, int actionType);

	public String deleteUser(String userId, String loggedUserId);

	public String terminateUser(String userId, String uniqueRowId1,
			String uniqueRowId2, String idType1, String idType2,
			String newStatus, String remarks, String mode);
	
	public String resetPassword(String userId, String uniqueRowId1,
			String uniqueRowId2, String idType1, String idType2,
			String newStatus, String remarks, String mode);
	
	
	public Integer getOrgId(String userId);
	public String setRandomUserPassword(String userId);
	public String modifyUserPassword(String userId, String password);
	
	public int spSetUserLoginAttempt(String loggedUserID, String userIPAddress, String loginStatus);
	
	public void spLogoutUserActivity(String loggedUserID, int userLogId);

	public List<String> getAllManagerId(String userId);
	
	public List<UserDTO> spGetUserListForPriviledges(String userId);
	
}