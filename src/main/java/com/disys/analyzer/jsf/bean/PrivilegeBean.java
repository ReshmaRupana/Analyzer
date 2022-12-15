/**
 * 
 */
package com.disys.analyzer.jsf.bean;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

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

import com.disys.analyzer.config.util.FacesMessageSeverity;
import com.disys.analyzer.config.util.FacesUtils;
import com.disys.analyzer.model.OrganizationGroup;
import com.disys.analyzer.model.Resource;
import com.disys.analyzer.service.ResourceService;

/**
 * @author Sajid
 * @since Dec 20, 2017
 */
@ManagedBean
@ViewScoped
public class PrivilegeBean extends SpringBeanAutowiringSupport implements Serializable {
	private Logger logger = LoggerFactory.getLogger(getClass());
	private static final long serialVersionUID = 5967963415877687580L;
	@Autowired
	private ResourceService resourceService;
	private List<Resource> sourcePrivileges;
	private List<Resource> filteredList;
	private String resourceDescription;
	private Resource resource;
	private boolean showUpdateButton;

	public PrivilegeBean() {
		this.showUpdateButton = false;
		this.resource = new Resource();
	}

	@PostConstruct
	public void init() {
		try {
			FacesContextUtils.getRequiredWebApplicationContext(FacesContext.getCurrentInstance())
					.getAutowireCapableBeanFactory().autowireBean(this);
			sourcePrivileges = resourceService.findAll();
			if (sourcePrivileges != null) {
				System.out.println("Total privileges are :  " + sourcePrivileges.size());
			} else {
				FacesUtils.addGlobalMessage(FacesMessageSeverity.FATAL, "No privileges defined",
						"There are no privileges defined in the system, ask adminsitrator to define privileges first");
			}
		} catch (Exception ex) {
			logger.debug(ex.getMessage());
		}
	}

	public void saveResource() {
		logger.debug("About to save privillages");
		try {
			Integer maxId = resourceService.getMaxPK();
			maxId = maxId == null ? 1 : maxId + 1;
			resource.setResId(maxId);
			resource.setEditAllowed(false);
			resource = resourceService.save(resource);
			if (resource != null && resource.getResId() != null) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Privilege added successfully"));
			} else {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error saving privilege"));
			}
			sourcePrivileges = resourceService.findAll();
			showUpdateButton = false;
			this.resource = new Resource();
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Error saving privilege" + e.getMessage()));
		}
	}

	public void saveUpdateResource() {
		logger.debug("About to save/update privillages");
		try {

			boolean isUnique = resourceService.isRecordUnique(resource.getResDesc(), resource.getResId());
			if (isUnique) {
				resource.setEditAllowed(false);
				resource = resourceService.save(resource);
				if (resource != null && resource.getResId() != null) {
					FacesContext.getCurrentInstance().addMessage(null,
							new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Privilege saved successfully"));
				} else {
					FacesContext.getCurrentInstance().addMessage(null,
							new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Error Saving Privilege"));
				}
				sourcePrivileges = resourceService.findAll();
				showUpdateButton = false;
				this.resource = new Resource();
			} else {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Duplicate Privilege"));
			}
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Error Saving Privilege"));
		}
	}

	public void clearForm() {
		logger.debug("Clearing form input fields");
		showUpdateButton = false;
		this.resource = new Resource();
	}

	public void editResource(AjaxBehaviorEvent event) {
		try {
			Integer resourceId = Integer.valueOf(FacesContext.getCurrentInstance().getExternalContext()
					.getRequestParameterMap().get("selectedResourceId"));
			if (sourcePrivileges != null) {
				Optional<Resource> optionalResource = sourcePrivileges.stream()
						.filter(x -> x.getResId().equals(resourceId)).findFirst();
				this.resource = optionalResource.get();
			} else {
				logger.debug("No data in the list");
			}
			showUpdateButton = true;

		} catch (Exception ex) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Error editing privilege" + ex.getMessage()));
		}
	}

	public void deleteResource(AjaxBehaviorEvent event) {
		try {
			Integer resourceId = Integer.valueOf(FacesContext.getCurrentInstance().getExternalContext()
					.getRequestParameterMap().get("selectedResourceId"));
			boolean isReferenceFound = resourceService.isReferenceFound(resourceId);
			if (!isReferenceFound) {
				if (sourcePrivileges != null) {
					Optional<Resource> optionalResource = sourcePrivileges.stream()
							.filter(x -> x.getResId().equals(resourceId)).findFirst();
					resourceService.delete(optionalResource.get());
					this.resource = new Resource();
					showUpdateButton = false;
					FacesContext.getCurrentInstance().addMessage(null,
							new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Privilege deleted successfully"));
				} else {
					logger.debug("No data in the list");
				}
				sourcePrivileges = resourceService.findAll();
				showUpdateButton = true;
			} else {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
						"Error", "Cannot delete privilege as reference found"));
			}

		} catch (Exception ex) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Error in deleting privilege"));
		}
	}

	public void search() {
		if (sourcePrivileges != null) {
			Optional<Resource> g = sourcePrivileges.stream()
					.filter(x -> x.getResDesc().equalsIgnoreCase(resourceDescription)).findFirst();
			Resource res = g.get();
			System.out.println("Resource : " + res.getResDesc());
		} else {
			logger.debug("No data in the list");
		}
	}

	/**
	 * @return the sourcePrivileges
	 */
	public List<Resource> getSourcePrivileges() {
		return sourcePrivileges;
	}

	/**
	 * @param sourcePrivileges
	 *            the sourcePrivileges to set
	 */
	public void setSourcePrivileges(List<Resource> sourcePrivileges) {
		this.sourcePrivileges = sourcePrivileges;
	}

	/**
	 * @return the resourceDescription
	 */
	public String getResourceDescription() {
		return resourceDescription;
	}

	/**
	 * @param resourceDescription
	 *            the resourceDescription to set
	 */
	public void setResourceDescription(String resourceDescription) {
		this.resourceDescription = resourceDescription;
	}

	/**
	 * @return the resource
	 */
	public Resource getResource() {
		return resource;
	}

	/**
	 * @param resource
	 *            the resource to set
	 */
	public void setResource(Resource resource) {
		this.resource = resource;
	}

	/**
	 * @return the filteredList
	 */
	public List<Resource> getFilteredList() {
		return filteredList;
	}

	/**
	 * @param filteredList
	 *            the filteredList to set
	 */
	public void setFilteredList(List<Resource> filteredList) {
		this.filteredList = filteredList;
	}

	public boolean isShowUpdateButton() {
		return showUpdateButton;
	}

	public void setShowUpdateButton(boolean showUpdateButton) {
		this.showUpdateButton = showUpdateButton;
	}

}
