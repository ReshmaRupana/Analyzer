/**
 * 
 */
package com.disys.analyzer.repository.custom;

import java.util.List;

import javax.faces.model.SelectItem;

import com.disys.analyzer.model.User;
import com.disys.analyzer.model.dto.UserDTO;

/**
 * @author Sajid
 * 
 */
public interface ApplicationUserRepositoryCustom {
	/*public List<User> getUserList(String userId, String assignedDevice, String lastName, String firstName) ;*/
	
	public List<User> getUserList(String userId, String searchString, String companyCode) ;
	
	public String addUpdateUser(User user, String loggedUserId, int actionType);
	
	public String deleteUser(String userId, String loggedUserId);
	
	public String setGenericStatus(String userId, String uniqueRowId1, String uniqueRowId2, String idType1, String idType2, String newStatus, String remarks, String mode);
	
	public String setRandomUserPassword(String userId);
	public String modifyUserPassword(String userId, String password);
	public String userIdValidation(String newUserId);
	public String employeeIdValidation(String newEmployeeId);
	public List<String> getAllManagerId(String managerId);
	
	public List<UserDTO> spGetUserListForPriviledges(String userId);
	
}
