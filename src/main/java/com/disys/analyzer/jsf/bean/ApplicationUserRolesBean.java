/**
 * 
 */
package com.disys.analyzer.jsf.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import org.springframework.web.jsf.FacesContextUtils;

import com.disys.analyzer.config.util.FacesUtils;
import com.disys.analyzer.model.Role;
import com.disys.analyzer.service.RoleService;

/**
 * @author Sajid
 * @since Oct 5, 2019
 */
@ManagedBean
@ViewScoped
public class ApplicationUserRolesBean extends SpringBeanAutowiringSupport
		implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8479381577140858229L;

	static Logger logger = LoggerFactory
			.getLogger(ApplicationUserRolesBean.class);

	@Autowired
	private RoleService roleService;

	private List<Role> userRoles;
	private List<Role> filteredList;

	private Long userRoleId;

	private Role userRole;

	boolean showUpdateButton;

	@PostConstruct
	public void init() {
		try {

			FacesContextUtils
					.getRequiredWebApplicationContext(
							FacesContext.getCurrentInstance())
					.getAutowireCapableBeanFactory().autowireBean(this);
		} catch (Exception ex) {
			logger.debug(ex.getMessage());
		}
	}

	public ApplicationUserRolesBean() {
		userRoles = new ArrayList<Role>();
		userRole = new Role();
		showUpdateButton = false;
	}

	public void getAllUserRoles() {
		// TODO for admin user it should be 1, otherwise 0
		int type = 1;
		userRoles = roleService
				.getRoleList(FacesUtils.getCurrentUserId(), type);
	}

	public void saveRole() {
		//insert
		int actionType = 1;
		//pass role id as null
		String result = roleService.addUpdateRole(userRole.getRoleId(),
				userRole.getRoleDesc(), FacesUtils.getCurrentUserId(),
				actionType);
		if (result.equals("0")) {
			System.out.println("Role created successfully");
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Success", "Role created successfully"));
		} else if (result.equals("1")) {
			System.out
					.println("Could not create or modify Role -- General error ");
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Failure", "Could not create Role"));
		} else if (result.equals("2")) {
			System.out.println("Could not create Role -- role already exist");
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Failure", "Role already exist"));
		}
		this.getAllUserRoles();
	}

	public void updateRole() {
		logger.debug("About to update role with id : " + userRole.getRoleId());
		// roleRepository.save(userRole);
		// update
		int actionType = 2;
		String result = roleService.addUpdateRole(userRole.getRoleId(),
				userRole.getRoleDesc(), FacesUtils.getCurrentUserId(),
				actionType);
		if (result.equals("0")) {
			System.out.println("Role updated successfully");
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Success", "Role updated successfully"));
		} else if (result.equals("1")) {
			System.out
					.println("Could not create or modify Role -- General error ");
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Failure", "Could not update Role"));
		} else if (result.equals("2")) {
			System.out.println("Could not update Role -- role already exist");
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Failure", "Role arleady exist"));
		}
		this.getAllUserRoles();
	}

	public void clearForm() {
		logger.debug("Clearing form input fields");
		showUpdateButton = false;
		this.userRole = new Role();
	}

	public void editRole(AjaxBehaviorEvent event) {
		logger.debug("Edit role  : "+userRole.getRoleId());
		/*String id = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id");
		userRole = roleRepository.findOne(Long.valueOf(id));*/
		//userRole = role;
		showUpdateButton = true;
	}

	public Long getUserRoleId() {
		return userRoleId;
	}

	public void setUserRoleId(Long userRoleId) {
		this.userRoleId = userRoleId;
	}

	public Role getUserRole() {
		return userRole;
	}

	public void setUserRole(Role userRole) {
		this.userRole = userRole;
	}

	public boolean isShowUpdateButton() {
		return showUpdateButton;
	}

	public void setShowUpdateButton(boolean showUpdateButton) {
		this.showUpdateButton = showUpdateButton;
	}

	/**
	 * @return the userRoles
	 */
	public List<Role> getUserRoles() {
		if (userRoles == null || userRoles.size() == 0) {
			userRoles = new ArrayList<Role>();
			int type = 1;
			userRoles = roleService.getRoleList(FacesUtils.getCurrentUserId(),
					type);
		}
		return userRoles;
	}

	/**
	 * @param userRoles
	 *            the userRoles to set
	 */
	public void setUserRoles(List<Role> userRoles) {
		this.userRoles = userRoles;
	}

	/**
	 * @return the filteredList
	 */
	public List<Role> getFilteredList() {
		return filteredList;
	}

	/**
	 * @param filteredList the filteredList to set
	 */
	public void setFilteredList(List<Role> filteredList) {
		this.filteredList = filteredList;
	}
}
