/**
 * 
 */
package com.disys.analyzer.jsf.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.model.SelectItem;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import org.springframework.web.jsf.FacesContextUtils;

import com.disys.analyzer.config.util.Constants;
import com.disys.analyzer.config.util.FacesMessageSeverity;
import com.disys.analyzer.config.util.FacesUtils;
import com.disys.analyzer.model.Role;
import com.disys.analyzer.model.User;
import com.disys.analyzer.service.ApplicationUserService;
import com.disys.analyzer.service.RoleService;

/**
 * @author Sajid
 * @since Sep 13, 2018
 *
 */
@ManagedBean
@ViewScoped
public class ApplicationUserBean extends SpringBeanAutowiringSupport implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4209133766296292860L;

	private Logger logger = LoggerFactory.getLogger(ApplicationUserBean.class);

	@Autowired
	private ApplicationUserService service;

	@Autowired
	private RoleService roleService;

	private List<SelectItem> listOfRoles;

	/*
	 * @Autowired private RoleRepository roleRepository;
	 */

	private List<User> applicationUsersList;
	private List<User> filteredList;
	// private List<Integer> internalUserIds;
	private User applicationUser;
	private User selectedUser;

	private String searchStringFirstName;
	private String searchStringLastName;
	private String searchString;
	private boolean showUpdateButton;

	private String userId;
	private String action;

	private List<SelectItem> uSAStates;
	private String selectedUserId;
	private String employeeId;
	private List<SelectItem> selectedManagerId;
	private String companyCode;
	// private List<SelectItem> allUserRoles;

	@PostConstruct
	private void postConstruct() {

		FacesContextUtils.getRequiredWebApplicationContext(FacesContext.getCurrentInstance())
				.getAutowireCapableBeanFactory().autowireBean(this);

	}

	public ApplicationUserBean() {
		// check if it is a list page or is it for creating or modifying the
		// record

		FacesContext fc = FacesUtils.getFacesContext();
		ExternalContext externalContext = fc.getExternalContext();
		Map<String, String> requestMap = externalContext.getRequestParameterMap();

		requestMap.entrySet().forEach(entry -> {
			System.out.println("Key : " + entry.getKey() + " Value : " + entry.getValue());
			if (entry.getKey().equals("userId")) {
				userId = entry.getValue();
			} else if (entry.getKey().equals("action")) {
				action = entry.getValue();
			}
		});

		if (userId != null) {
			showUpdateButton = true;
			applicationUser = service.findByUsername(userId);
			if (applicationUser != null) {
				System.err.println("Application user found : " + userId);
			}
		} else {
			showUpdateButton = false;
		}
		// action = Modify
		/*
		 * if(action != null && !(action.equals("Modify"))){ eraseFilter(); }
		 */

		if (action == null) {
			eraseFilter();
		}
	}

	public void filter() {
		try {
			if ((searchString == null || searchString.equals(""))) {
				searchString = "ALL";

			}
			if(companyCode == null || companyCode.isEmpty())
			{
				companyCode = Constants.DEFAULT_COMPANY_CODE;
			}

		} catch (NullPointerException ex) {
			searchString = "ALL";
		}
		applicationUsersList = service.getUserList(FacesUtils.getCurrentUserId(), searchString, companyCode);
		logger.info("Total list size is : " + applicationUsersList.size());
	}

	/*
	 * public void filter() { try { User user =
	 * service.findByEmail(FacesUtils.getCurrentUserId()); if
	 * ((searchStringLastName == null || searchStringLastName.equals("")) &&
	 * (searchStringFirstName == null || searchStringFirstName.isEmpty())) { if
	 * (user != null) { searchStringLastName = user.getLastName(); } else {
	 * searchStringLastName = "ALL"; } } if (searchStringFirstName == null ||
	 * searchStringFirstName.equals("")) { if (user != null) {
	 * searchStringFirstName = user.getFirstName(); } else {
	 * searchStringFirstName = "ALL"; } } } catch (NullPointerException ex) {
	 * searchStringLastName = "ALL"; searchStringFirstName = "ALL"; }
	 * applicationUsersList = service.getUserList(
	 * FacesUtils.getCurrentUserId(), "0", searchStringLastName,
	 * searchStringFirstName); logger.info("Total list size is : " +
	 * applicationUsersList.size()); }
	 */

	public void eraseFilter() {
		this.applicationUser = new User();
		this.selectedUser = new User();
		this.searchString = "";
		// this.searchStringFirstName = "";
		// this.searchStringLastName = "";
		this.filter();
	}

	public void remove(AjaxBehaviorEvent event) {
		try {
			// service.delete(selectedAnalyserLiaison.getLiaisonID());
			String result = service.deleteUser(selectedUserId, FacesUtils.getCurrentUserId());
			if (result.equals("0")) {
				String infoMessage = "Deleted successfully.";
				FacesUtils.addGlobalMessage(FacesMessageSeverity.INFO, infoMessage, selectedUserId);
			} else {
				String infoMessage = "Couldn't delete.";
				FacesUtils.addGlobalMessage(FacesMessageSeverity.ERROR, infoMessage, selectedUserId);
			}
			eraseFilter();
		} catch (Exception e) {
			String errorMessage = "Couldn't be deleted.";
			FacesUtils.addGlobalMessage(FacesMessageSeverity.ERROR, errorMessage, e.getMessage());
		}
	}

	public void resetPassword(AjaxBehaviorEvent event) {
		try {
			// setGenericStatus(User.getUserId(), userId, null, null, null,
			// null, null, "TERMINATEUSER");
			String result = service.terminateUser(FacesUtils.getCurrentUserId(), selectedUserId, null, null, null, null,
					null, "RESETPASSWORD");
			// default password disys is set

			if (result != null && result.equals("1")) {
				String infoMessage = "Password reseted successfully. Ask administrator for the default password";
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, infoMessage,
						selectedUserId);
				FacesUtils.getFacesContext().addMessage(null, message);
				return;
				//FacesUtils.addGlobalMessage(FacesMessageSeverity.INFO, infoMessage, selectedUserId);
			} else {
				String infoMessage = "Password reset failure.";
				FacesUtils.addGlobalMessage(FacesMessageSeverity.ERROR, infoMessage, selectedUserId);
			}

			eraseFilter();
		} catch (Exception e) {
			String errorMessage = "Couldn't reset password.";
			FacesUtils.addGlobalMessage(FacesMessageSeverity.ERROR, errorMessage, e.getMessage());
		}
	}

	public void terminate(AjaxBehaviorEvent event) {
		try {
			String result = service.terminateUser(FacesUtils.getCurrentUserId(), selectedUserId, null, null, null, null,
					null, "TERMINATEUSER");
			// default password disys is set

			if (result != null && result.equals("1")) {
				String infoMessage = "User terminated successfully";
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, infoMessage,
						selectedUserId);
				FacesUtils.getFacesContext().addMessage(null, message);
				return;
				//FacesUtils.addGlobalMessage(FacesMessageSeverity.INFO, infoMessage, selectedUserId);
			} else {
				String infoMessage = "Couldn't terminate user.";
				FacesUtils.addGlobalMessage(FacesMessageSeverity.ERROR, infoMessage, selectedUserId);
			}
			eraseFilter();
		} catch (Exception e) {
			String errorMessage = "Couldn't terminate user.";
			FacesUtils.addGlobalMessage(FacesMessageSeverity.ERROR, errorMessage, e.getMessage());
		}
	}

	public void userSelected(AjaxBehaviorEvent event) {
		try {
			selectedUserId = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap()
					.get("selectedUserId");
			System.out.println("Id is  : " + selectedUserId);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void save() {
		try {

			userIdValidation();

			employeeIdValidation();

			if (applicationUser.getZip() != null && applicationUser.getZip().equals("")) {
				if (applicationUser.getZip().contains("-")) {
					String val = applicationUser.getZip();
					applicationUser.setLocation(createLocFromZip(val));
				}
			}

			String result = service.addUser(applicationUser, FacesUtils.getCurrentUserId(), 1);
			if (result.equals("0")) {
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "User saved successfully",
						"User operation is successfully completed!");

				FacesUtils.redirect("/protected/application-users.xhtml", message);
			} else {
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Error in operation!");
				FacesUtils.getFacesContext().addMessage(null, message);
				return;
			}
		} catch (Exception e) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Error in operation!");
			FacesUtils.getFacesContext().addMessage(null, message);
			return;
		}
	}

	public void update() {
		try {
			
			String result = service.addUser(applicationUser, FacesUtils.getCurrentUserId(), 2);
			if (result.equals("0")) {
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "User updated successfully",
						"User operation is successfully completed!");

				FacesUtils.redirect("/protected/application-users.xhtml", message);
			}else if(result.equals("error.systemerror.UserAlreadyExistsError")){
				
				FacesMessage message = new FacesMessage(
						FacesMessage.SEVERITY_ERROR, "Employee Id value must be unique, a user with the same Employee Id already exists in the system",
						"Error in operation!");
				FacesUtils.getFacesContext().addMessage(null, message);
				return;
				
			} else {
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Error in operation!");
				FacesUtils.getFacesContext().addMessage(null, message);
				return;
			}
		} catch (Exception e) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Error in operation!");
			FacesUtils.getFacesContext().addMessage(null, message);
			return;
		}
	}

	/*
	 * Getters and Setters
	 */

	/**
	 * @return the applicationUser
	 */
	public User getApplicationUser() {
		return applicationUser;
	}

	/**
	 * @param applicationUser
	 *            the applicationUser to set
	 */
	public void setApplicationUser(User applicationUser) {
		this.applicationUser = applicationUser;
	}

	/**
	 * @return the applicationUsersList
	 */
	public List<User> getApplicationUsersList() {
		return applicationUsersList;
	}

	/**
	 * @param applicationUsersList
	 *            the applicationUsersList to set
	 */
	public void setApplicationUsersList(List<User> applicationUsersList) {
		this.applicationUsersList = applicationUsersList;
	}

	/**
	 * @return the listOfRoles
	 */
	public List<SelectItem> getListOfRoles() {
		if (listOfRoles == null) {
			listOfRoles = new ArrayList<SelectItem>();
			List<Role> list = roleService.getRoleList(FacesUtils.getCurrentUserId(), 0);
			list.forEach(l -> listOfRoles.add(new SelectItem(l.getRoleId(), l.getRoleDesc())));
		}
		return listOfRoles;
	}

	/**
	 * @param listOfRoles
	 *            the listOfRoles to set
	 */
	public void setListOfRoles(List<SelectItem> listOfRoles) {
		this.listOfRoles = listOfRoles;
	}

	/**
	 * @return the selectedUser
	 */
	public User getSelectedUser() {
		return selectedUser;
	}

	/**
	 * @param selectedUser
	 *            the selectedUser to set
	 */
	public void setSelectedUser(User selectedUser) {
		this.selectedUser = selectedUser;
	}

	/**
	 * @return the searchStringFirstName
	 */
	public String getSearchStringFirstName() {
		return searchStringFirstName;
	}

	/**
	 * @param searchStringFirstName
	 *            the searchStringFirstName to set
	 */
	public void setSearchStringFirstName(String searchStringFirstName) {
		this.searchStringFirstName = searchStringFirstName;
	}

	/**
	 * @return the searchStringLastName
	 */
	public String getSearchStringLastName() {
		return searchStringLastName;
	}

	/**
	 * @param searchStringLastName
	 *            the searchStringLastName to set
	 */
	public void setSearchStringLastName(String searchStringLastName) {
		this.searchStringLastName = searchStringLastName;
	}

	/**
	 * @return the showUpdateButton
	 */
	public boolean isShowUpdateButton() {
		return showUpdateButton;
	}

	/**
	 * @param showUpdateButton
	 *            the showUpdateButton to set
	 */
	public void setShowUpdateButton(boolean showUpdateButton) {
		this.showUpdateButton = showUpdateButton;
	}

	/**
	 * @return the action
	 */
	public String getAction() {
		return action;
	}

	/**
	 * @param action
	 *            the action to set
	 */
	public void setAction(String action) {
		this.action = action;
	}

	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * @param userId
	 *            the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getSearchString() {
		return searchString;
	}

	public void setSearchString(String searchString) {
		this.searchString = searchString;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	/**
	 * @return the uSAStates
	 */
	public List<SelectItem> getuSAStates() {
		if (uSAStates == null) {
			uSAStates = new ArrayList<SelectItem>();
			uSAStates.add(new SelectItem("AL", "Alabama"));
			uSAStates.add(new SelectItem("AK", "Alaska"));
			uSAStates.add(new SelectItem("AZ", "Arizona"));
			uSAStates.add(new SelectItem("AR", "Arkansas"));
			uSAStates.add(new SelectItem("CA", "California"));
			uSAStates.add(new SelectItem("CO", "Colorado"));
			uSAStates.add(new SelectItem("CT", "Connecticut"));
			uSAStates.add(new SelectItem("DE", "Delaware"));
			uSAStates.add(new SelectItem("DC", "District Of Columbia"));
			uSAStates.add(new SelectItem("FL", "Florida"));
			uSAStates.add(new SelectItem("GA", "Georgia"));
			uSAStates.add(new SelectItem("HI", "Hawaii"));
			uSAStates.add(new SelectItem("ID", "Idaho"));
			uSAStates.add(new SelectItem("IL", "Illinois"));
			uSAStates.add(new SelectItem("IN", "Indiana"));
			uSAStates.add(new SelectItem("IA", "Iowa"));
			uSAStates.add(new SelectItem("KS", "Kansas"));
			uSAStates.add(new SelectItem("KY", "Kentucky"));
			uSAStates.add(new SelectItem("LA", "Louisiana"));
			uSAStates.add(new SelectItem("ME", "Maine"));
			uSAStates.add(new SelectItem("MD", "Maryland"));
			uSAStates.add(new SelectItem("MA", "Massachusetts"));
			uSAStates.add(new SelectItem("MI", "Michigan"));
			uSAStates.add(new SelectItem("MN", "Minnesota"));
			uSAStates.add(new SelectItem("MS", "Mississippi"));
			uSAStates.add(new SelectItem("MO", "Missouri"));
			uSAStates.add(new SelectItem("MT", "Montana"));
			uSAStates.add(new SelectItem("NE", "Nebraska"));
			uSAStates.add(new SelectItem("NV", "Nevada"));
			uSAStates.add(new SelectItem("NH", "New Hampshire"));
			uSAStates.add(new SelectItem("NJ", "New Jersey"));
			uSAStates.add(new SelectItem("NM", "New Mexico"));
			uSAStates.add(new SelectItem("NY", "New York"));
			uSAStates.add(new SelectItem("NC", "North Carolina"));
			uSAStates.add(new SelectItem("ND", "North Dakota"));
			uSAStates.add(new SelectItem("OH", "Ohio"));
			uSAStates.add(new SelectItem("OK", "Oklahoma"));
			uSAStates.add(new SelectItem("OR", "Oregon"));
			uSAStates.add(new SelectItem("PA", "Pennsylvania"));
			uSAStates.add(new SelectItem("RI", "Rhode Island"));
			uSAStates.add(new SelectItem("SC", "South Carolina"));
			uSAStates.add(new SelectItem("SD", "South Dakota"));
			uSAStates.add(new SelectItem("TN", "Tennessee"));
			uSAStates.add(new SelectItem("TX", "Texas"));
			uSAStates.add(new SelectItem("UT", "Utah"));
			uSAStates.add(new SelectItem("VT", "Vermont"));
			uSAStates.add(new SelectItem("VA", "Virginia"));
			uSAStates.add(new SelectItem("WA", "Washington"));
			uSAStates.add(new SelectItem("WV", "West Virginia"));
			uSAStates.add(new SelectItem("WI", "Wisconsin"));
			uSAStates.add(new SelectItem("WY", "Wyoming"));

		}
		return uSAStates;
	}

	/**
	 * @param uSAStates
	 *            the uSAStates to set
	 */
	public void setuSAStates(List<SelectItem> uSAStates) {
		this.uSAStates = uSAStates;
	}

	/**
	 * @return the selectedUserId
	 */
	public String getSelectedUserId() {
		return selectedUserId;
	}

	/**
	 * @param selectedUserId
	 *            the selectedUserId to set
	 */
	public void setSelectedUserId(String selectedUserId) {
		this.selectedUserId = selectedUserId;
	}

	/**
	 * @return the filteredList
	 */
	public List<User> getFilteredList() {
		return filteredList;
	}

	/**
	 * @param filteredList
	 *            the filteredList to set
	 */
	public void setFilteredList(List<User> filteredList) {
		this.filteredList = filteredList;
	}

	public static void main(String[] args) {
		String zip = "12345-6789";
		Integer location;
		String part1 = zip.substring(0, zip.indexOf('-'));
		String part2 = zip.substring(zip.indexOf('-') + 1);
		System.out.println(part1);
		System.out.println(part2);
		String loc = part1 + "" + part2;
		try {
			location = Integer.valueOf(loc);
		} catch (NumberFormatException e) {
			System.err.println("Error converting zip address to loc " + e.getMessage());
			// set location to -1
			location = -1;
		}
		System.out.println(location);
	}

	private Integer createLocFromZip(String zip) {
		Integer location;
		String part1 = zip.substring(0, zip.indexOf('-'));
		String part2 = zip.substring(zip.indexOf('-') + 1);
		System.out.println(part1);
		System.out.println(part2);
		String loc = part1 + "" + part2;
		try {
			location = Integer.valueOf(loc);
		} catch (NumberFormatException e) {
			String message = "Error converting zip address to loc " + e.getMessage();
			System.err.println(message);
			logger.error(message);
			// set location to -1
			location = -1;
		}
		return location;
	}

	public List<SelectItem> getAllManagerId() {
		String userId = FacesUtils.getCurrentUserId();
		if (selectedManagerId == null) {
			selectedManagerId = new ArrayList<SelectItem>();
			List<String> list = service.getAllManagerId(userId);
			for (String managerIdList : list) {
				if (managerIdList != null) {
					SelectItem item = new SelectItem(managerIdList, managerIdList);
					selectedManagerId.add(item);
				}
			}
		}
		return selectedManagerId;
	}

	/**
	 * @return the filteredList
	 */
	public List<SelectItem> getSelectedManagerId() {
		return selectedManagerId;
	}

	/**
	 * @param selectedManagerId
	 *            the selectedManagerId to set
	 */
	public void setSelectedManagerId(List<SelectItem> selectedManagerId) {
		this.selectedManagerId = selectedManagerId;
	}

	public String userIdValidation() {

		userId = applicationUser.getUserId();

		userId = service.userIdValidation(userId);

		if (!userId.equals("0")) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
					" User Id value must be unique, a user with the same Id or email already exists in the system ",
					"Error in operation!");
			FacesUtils.getFacesContext().addMessage(null, message);
	
			return null;
		} 
	
			return userId;
		
	}

	public String employeeIdValidation() {

		employeeId = applicationUser.getADPCode();

		if (employeeId == null || employeeId.equals("")) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Employee Id is Required",
					"Error in operation!");
			FacesUtils.getFacesContext().addMessage(null, message);
			
			return null;
		}

		employeeId = service.employeeIdValidation(employeeId);

		if (!employeeId.equals("0")) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
					" Employee Id value must be unique, a user with the same Employee Id already exists in the system ",
					"Error in operation!");
			FacesUtils.getFacesContext().addMessage(null, message);
			
			return null;
		} 
			
		return employeeId;
		
	}

	public String getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	
}
