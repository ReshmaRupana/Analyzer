/**
 * 
 */
package com.disys.analyzer.jsf.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import org.springframework.web.jsf.FacesContextUtils;

import com.disys.analyzer.config.util.FacesMessageSeverity;
import com.disys.analyzer.config.util.FacesUtils;
import com.disys.analyzer.model.Resource;
import com.disys.analyzer.model.Title;
import com.disys.analyzer.service.ResourceService;
import com.disys.analyzer.service.TitleService;

/**
 * @author Sajid
 * @since Dec 27, 2019
 */
@ManagedBean
@ViewScoped
public class TitleActionBean extends SpringBeanAutowiringSupport implements Serializable
{
	private static final long serialVersionUID = 8998219220937730517L;
	private Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired private TitleService titleService;
	@Autowired private ResourceService resourceService;
	private List<Title> list;
	private List<Title> filteredList;
	private Title title;
	private boolean showUpdateButton;
	private List<Resource> resourceList;
	
	@PostConstruct
	public void init()
	{
		try
		{
			FacesContextUtils.getRequiredWebApplicationContext(FacesContext.getCurrentInstance()).getAutowireCapableBeanFactory().autowireBean(this);
		}
		catch (BeansException e)
		{
			e.printStackTrace();
		}
		catch (IllegalStateException e)
		{
			e.printStackTrace();
		}
	}
	
	public TitleActionBean()
	{
		logger.debug("In title action bean");
		list = new ArrayList<Title>();
		this.getAllTitles();
		this.getParentResources();
		title = new Title();
		showUpdateButton = false;
	}
	
	private void getParentResources() {
		resourceList = new ArrayList<Resource>(4);
		resourceList = resourceService.getParentResources();
	}
	
	public void getAllTitles()
	{
		list = new ArrayList<Title>();
		list = titleService.getAllTitles();
	}
	
	public void saveTitle()
	{
		try
		{
			if(title.getResourceName() == null || title.getResourceName().trim().equals(""))
			{
				FacesUtils.addGlobalMessage(FacesMessageSeverity.ERROR, "Please enter resource name");
				return;
			}
			if(title.getTitle() == null || title.getTitle().trim().equals(""))
			{
				FacesUtils.addGlobalMessage(FacesMessageSeverity.ERROR, "Please enter resource title");
				return;
			}
			if(title.getResourceId() == null)
			{
				FacesUtils.addGlobalMessage(FacesMessageSeverity.ERROR, "Please select parent resource");
				return;
			}
			if(!title.getResourceName().trim().contains(".xhtml"))
			{
				FacesUtils.addGlobalMessage(FacesMessageSeverity.ERROR, "Invalid resource name");
				return;
			}
			if(!titleService.isResourceNameUnique(title.getResourceName(), null))
			{
				FacesUtils.addGlobalMessage(FacesMessageSeverity.ERROR, "Duplicate resource name");
				return;
			}
			if(!titleService.isTitleUnique(title.getTitle(), null))
			{
				FacesUtils.addGlobalMessage(FacesMessageSeverity.ERROR, "Duplicate resource title");
				return;
			}
			title.setResourceName(title.getResourceName().trim());
			title.setTitle(title.getTitle().trim());
			if(title.getMenuItem() == null)
			{
				title.setMenuItem(0);
			}
			titleService.saveTitle(title);
			Resource resource = new Resource();
			resource.setParentId(title.getResourceId());
			resource.setResDesc(title.getTitle().trim());
			resource.setTitleId(title.getId());
			resource.setMenuItem(title.getMenuItem());
			resource.setStatus(1);
			String result = resourceService.spAddUpdateResources(FacesUtils.getCurrentUserId(), resource, 1);
			FacesUtils.addGlobalMessage(FacesMessageSeverity.INFO, "Successfully save title");
			getAllTitles();
			clearForm();
		}
		catch (Exception e)
		{
			FacesUtils.addGlobalMessage(FacesMessageSeverity.ERROR, "Cannot save title");
			e.printStackTrace();
		}
	}
	
	public void updateTitle()
	{
		try
		{
			if(title.getResourceName() == null || title.getResourceName().trim().equals(""))
			{
				FacesUtils.addGlobalMessage(FacesMessageSeverity.ERROR, "Please enter resource name");
				return;
			}
			if(title.getTitle() == null || title.getTitle().trim().equals(""))
			{
				FacesUtils.addGlobalMessage(FacesMessageSeverity.ERROR, "Please enter resource title");
				return;
			}
			if(title.getResourceId() == null)
			{
				FacesUtils.addGlobalMessage(FacesMessageSeverity.ERROR, "Please select parent resource");
				return;
			}
			if(!title.getResourceName().trim().contains(".xhtml"))
			{
				FacesUtils.addGlobalMessage(FacesMessageSeverity.ERROR, "Invalid resource name");
				return;
			}
			if(!titleService.isResourceNameUnique(title.getResourceName(), title.getId()))
			{
				FacesUtils.addGlobalMessage(FacesMessageSeverity.ERROR, "Duplicate resource name");
				return;
			}
			if(!titleService.isTitleUnique(title.getTitle(), title.getId()))
			{
				FacesUtils.addGlobalMessage(FacesMessageSeverity.ERROR, "Duplicate resource title");
				return;
			}
			title.setResourceName(title.getResourceName().trim());
			title.setTitle(title.getTitle().trim());
			titleService.updateTitle(title);
			Resource resource = new Resource();
			resource.setParentId(title.getResourceId());
			resource.setResDesc(title.getTitle().trim());
			resource.setTitleId(title.getId());
			String result = resourceService.spAddUpdateResources(FacesUtils.getCurrentUserId(), resource, 2);
			FacesUtils.addGlobalMessage(FacesMessageSeverity.INFO, "Successfully updated title");
			getAllTitles();
		}
		catch (Exception e)
		{
			FacesUtils.addGlobalMessage(FacesMessageSeverity.ERROR, "Cannot update title");
			e.printStackTrace();
		}
	}
	
	public void clearForm()
	{
		logger.debug("Clearing form input fields");
		showUpdateButton = false;
		this.title = new Title();
	}
	
	public void editTitle(AjaxBehaviorEvent event)
	{
		logger.debug("Edit title  : " + title.getId());
		showUpdateButton = true;
	}
	
	/**
	 * @return the list
	 */
	public List<Title> getList()
	{
		return list;
	}
	
	/**
	 * @param list
	 *            the list to set
	 */
	public void setList(List<Title> list)
	{
		this.list = list;
	}
	
	/**
	 * @return the title
	 */
	public Title getTitle()
	{
		return title;
	}
	
	/**
	 * @param title
	 *            the title to set
	 */
	public void setTitle(Title title)
	{
		this.title = title;
	}
	
	/**
	 * @return the showUpdateButton
	 */
	public boolean isShowUpdateButton()
	{
		return showUpdateButton;
	}
	
	/**
	 * @param showUpdateButton
	 *            the showUpdateButton to set
	 */
	public void setShowUpdateButton(boolean showUpdateButton)
	{
		this.showUpdateButton = showUpdateButton;
	}
	
	/**
	 * @return the filteredList
	 */
	public List<Title> getFilteredList()
	{
		return filteredList;
	}
	
	/**
	 * @param filteredList
	 *            the filteredList to set
	 */
	public void setFilteredList(List<Title> filteredList)
	{
		this.filteredList = filteredList;
	}

	public List<Resource> getResourceList()
	{
		return resourceList;
	}

	public void setResourceList(List<Resource> resourceList)
	{
		this.resourceList = resourceList;
	}
}