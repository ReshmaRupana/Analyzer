/**
 * 
 */
package com.disys.analyzer.jsf.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.disys.analyzer.config.util.FacesUtils;
import com.disys.analyzer.model.OrganizationGroup;
import com.disys.analyzer.service.ApplicationUserService;
import com.disys.analyzer.service.OrganizationGroupService;

/**
 * @author Sajid
 * @since Dec 20, 2019
 */
@ManagedBean
@ViewScoped
public class GroupsBean extends SpringBeanAutowiringSupport implements
		Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1502253802886299060L;

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private OrganizationGroupService service;

	@Autowired
	private ApplicationUserService userService;

	private OrganizationGroup group;
	private List<OrganizationGroup> list;

	private boolean showUpdateButton;

	private Integer selectedGroupId;

	// get a list of groups, edit here, show a column, edit group or assign de
	// assign groups

	public GroupsBean() {
		logger.debug("In groups bean");
		this.group = new OrganizationGroup();
		showUpdateButton = false;
	}

	public void saveGroup() {
		logger.debug("About to save group");
		// group.setActive(true);
		// group.setOrgId(userService.getOrgId(FacesUtils.getCurrentUserId()));
		String result = service.save(group, FacesUtils.getCurrentUserId(), 1);
		if (result.equals("0")) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Group added successfully"));
		} else if (result.equals("1")) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Error saving group"));
		} else if (result.equals("2")) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Group already exist"));
		}
		recreateDataTable();
		this.group = new OrganizationGroup();
		showUpdateButton = false;
	}

	public void clearForm() {
		logger.debug("Clearing form input fields");
		showUpdateButton = false;
		this.group = new OrganizationGroup();
	}

	public void editGroup(AjaxBehaviorEvent event) {
		try {
			selectedGroupId = Integer.valueOf(FacesContext.getCurrentInstance()
					.getExternalContext().getRequestParameterMap()
					.get("selectedGroupId"));
			System.out.println("Id is  : " + selectedGroupId);
			// find object from the list
			if (list != null) {
				Optional<OrganizationGroup> g = list.stream()
						.filter(x -> x.getGroupId().equals(selectedGroupId))
						.findFirst();
				group = g.get();
			} else {
				logger.debug("No data in the list");
			}
			logger.debug("About to edit group with id : " + selectedGroupId);
			showUpdateButton = true;

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void updateGroup() {
		logger.debug("About to update group with id : " + group.getGroupId());
		String result = service.update(group, FacesUtils.getCurrentUserId(), 2);
		if (result.equals("0")) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Group updated successfully"));
		} else if (result.equals("1")) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Error updated group"));
		} else if (result.equals("2")) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Group already exist"));
		}
		// update the data table and clear the input form
		recreateDataTable();
		this.group = new OrganizationGroup();
		this.showUpdateButton = false;
	}

	public void deleteGroup(AjaxBehaviorEvent event) {
		try {
			selectedGroupId = Integer.valueOf(FacesContext.getCurrentInstance()
					.getExternalContext().getRequestParameterMap()
					.get("selectedGroupId"));
			System.out.println("Id is  : " + selectedGroupId);

			logger.debug("About to delete group with id : " + selectedGroupId);

			String result = service.deleteGroup(selectedGroupId.toString(),
					FacesUtils.getCurrentUserId());
			if (result.equals("0")) {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage("Group deleted successfully"));
			} else if (result.equals("1")) {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage("Group couldn't be deleted"));
			}
			// update the data table and clear the input form
			recreateDataTable();
			this.group = new OrganizationGroup();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @return the group
	 */
	public OrganizationGroup getGroup() {
		return group;
	}

	/**
	 * @param group
	 *            the group to set
	 */
	public void setGroup(OrganizationGroup group) {
		this.group = group;
	}

	public void recreateDataTable(){
		list = null;
		this.getList();
	}
	
	/**
	 * @return the list
	 */
	public List<OrganizationGroup> getList() {
		if (list == null) {
			list = new ArrayList<OrganizationGroup>();
			try {
				list = service.getGroupsList(FacesUtils.getCurrentUserId());
				if (list != null) {
					logger.debug("Fetch list size is : " + list.size());
				}
			} catch (Exception ex) {
				logger.error("No result found " + ex.getMessage());
			}
		}
		return list;
	}

	/**
	 * @param list
	 *            the list to set
	 */
	public void setList(List<OrganizationGroup> list) {
		this.list = list;
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
	 * @return the selectedGroupId
	 */
	public Integer getSelectedGroupId() {
		return selectedGroupId;
	}

	/**
	 * @param selectedGroupId
	 *            the selectedGroupId to set
	 */
	public void setSelectedGroupId(Integer selectedGroupId) {
		this.selectedGroupId = selectedGroupId;
	}
}