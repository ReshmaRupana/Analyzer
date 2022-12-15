/**
 * 
 */
package com.disys.analyzer.service.implementation;

import java.io.Serializable;
import java.util.List;

import javax.faces.model.SelectItem;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.disys.analyzer.model.User;
import com.disys.analyzer.model.dto.UserDTO;
import com.disys.analyzer.repository.ApplicationUserRepository;
import com.disys.analyzer.repository.RoleRepository;
import com.disys.analyzer.service.ApplicationUserService;

/**
 * @author Sajid
 * 
 */
@Service
@Transactional
public class ApplicationUserServiceImpl implements ApplicationUserService, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final Logger logger = LoggerFactory
			.getLogger(ApplicationUserServiceImpl.class);

	@Autowired
	private ApplicationUserRepository applicationUserRepository;

	@Autowired
	private RoleRepository roleRepository;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.disys.analyzer.service.ApplicationUserService#findByUsername(java
	 * .lang.String)
	 */
	@Override
	public User findByUsername(String username) {
		return applicationUserRepository.findByUserId(username);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.disys.analyzer.service.ApplicationUserService#findByEmail(java.lang
	 * .String)
	 */
	@Override
	public User findByEmail(String email) {
		return applicationUserRepository.findByUserId(email);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.disys.analyzer.service.ApplicationUserService#checkUserExists(java
	 * .lang.String, java.lang.String)
	 */
	@Override
	public boolean checkUserExists(String username, String email) {
		// TODO Auto-generated method stub
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.disys.analyzer.service.ApplicationUserService#checkUsernameExists
	 * (java.lang.String)
	 */
	@Override
	public boolean checkUsernameExists(String username) {
		// TODO Auto-generated method stub
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.disys.analyzer.service.ApplicationUserService#checkEmailExists(java
	 * .lang.String)
	 */
	@Override
	public boolean checkEmailExists(String email) {
		// TODO Auto-generated method stub
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.disys.analyzer.service.ApplicationUserService#save(com.disys.analyzer
	 * .model.ApplicationUser)
	 */
	@Override
	public void save(User user) {
		applicationUserRepository.save(user);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.disys.analyzer.service.ApplicationUserService#saveUser(com.disys.
	 * analyzer.model.ApplicationUser)
	 */
	@Override
	public User saveUser(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.disys.analyzer.service.ApplicationUserService#findUserList()
	 */
	@Override
	public List<User> findUserList() {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.disys.analyzer.service.ApplicationUserService#enableUser(java.lang
	 * .String)
	 */
	@Override
	public void enableUser(String username) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.disys.analyzer.service.ApplicationUserService#disableUser(java.lang
	 * .String)
	 */
	@Override
	public void disableUser(String username) {
		// TODO Auto-generated method stub

	}

	@Override
	public Long count() {
		long count = applicationUserRepository.count();
		logger.debug("Total count : " + count);
		return count;
	}

	@Override
	public Page<User> findAll(Example<User> example, Pageable pageable) {
		return applicationUserRepository.findAll(example, pageable);
	}

	@Override
	public long count(Example<User> example) {
		return applicationUserRepository.count(example);
	}

	@Override
	public Integer getRoleId(String userId) {
		return applicationUserRepository.getRoleId(userId);
	}

	/*@Override
	public List<User> getUserList(String userId, String assignedDevice,
			String lastName, String firstName) {
		return applicationUserRepository.getUserList(userId, assignedDevice,
				lastName, firstName);
	}*/
	@Override
	public List<User> getUserList(String userId, String searchString, String companyCode) {
		return applicationUserRepository.getUserList(userId, searchString, companyCode);
	}
	@Override
	public String addUser(User user, String loggedUserId, int actionType) {
		return applicationUserRepository.addUpdateUser(user, loggedUserId,
				actionType);
	}
	
	@Override
	public String userIdValidation(String newUserId) {
		
		return applicationUserRepository.userIdValidation(newUserId);
	}
	
	@Override
	public String employeeIdValidation(String newEmployeeId) {
		
		return applicationUserRepository.employeeIdValidation(newEmployeeId);
	}
	@Override
	public List<String> getAllManagerId(String userId)
	{
		return applicationUserRepository.getAllManagerId(userId);
		
	}

	@Override
	public String updateUser(User user, String loggedUserId, int actionType) {
		return applicationUserRepository.addUpdateUser(user, loggedUserId,
				actionType);
	}

	@Override
	public String deleteUser(String userId, String loggedUserId) {
		return applicationUserRepository.deleteUser(userId, loggedUserId);
	}

	@Override
	public String terminateUser(String userId, String uniqueRowId1,
			String uniqueRowId2, String idType1, String idType2,
			String newStatus, String remarks, String mode) {
		return applicationUserRepository.setGenericStatus(userId, uniqueRowId1,
				uniqueRowId2, idType1, idType2, newStatus, remarks, mode);
	}

	@Override
	public String resetPassword(String userId, String uniqueRowId1,
			String uniqueRowId2, String idType1, String idType2,
			String newStatus, String remarks, String mode) {
		return applicationUserRepository.setGenericStatus(userId, uniqueRowId1,
				uniqueRowId2, idType1, idType2, newStatus, remarks, mode);
	}

	@Override
	public Integer getOrgId(String userId) {
		return applicationUserRepository.getOrgId(userId);
	}

	@Override
	public String setRandomUserPassword(String userId) {
		return applicationUserRepository.setRandomUserPassword(userId);
	}

	@Override
	public String modifyUserPassword(String userId, String password) {
		return applicationUserRepository.modifyUserPassword(userId, password);
	}

	@Override
	public int spSetUserLoginAttempt(String loggedUserID, String userIPAddress, String loginStatus) {
		return applicationUserRepository.spSetUserLoginAttempt(loggedUserID, userIPAddress, loginStatus);
	}

	@Override
	public void spLogoutUserActivity(String loggedUserID, int userLogId) {
		applicationUserRepository.spLogoutUserActivity(loggedUserID, userLogId);		
	}
	@Override
	public List<UserDTO> spGetUserListForPriviledges(String userId)
	{
		return applicationUserRepository.spGetUserListForPriviledges(userId);
	}

}
