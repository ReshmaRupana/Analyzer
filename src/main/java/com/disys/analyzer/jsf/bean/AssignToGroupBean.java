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

import org.primefaces.event.TransferEvent;
import org.primefaces.model.DualListModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import org.springframework.web.jsf.FacesContextUtils;

import com.disys.analyzer.config.util.FacesMessageSeverity;
import com.disys.analyzer.config.util.FacesUtils;
import com.disys.analyzer.model.Analyser;
import com.disys.analyzer.model.OrganizationGroup;
import com.disys.analyzer.model.User;
import com.disys.analyzer.service.OrganizationGroupService;

/**
 * @author Sajid
 * @since Dec 20, 2018
 */
@ManagedBean
@ViewScoped
public class AssignToGroupBean extends SpringBeanAutowiringSupport implements Serializable
{
	private static final long serialVersionUID = 8559449988414987811L;
	private Logger logger = LoggerFactory.getLogger(getClass());
	private List<User> sourceList;
	private List<User> targetList;
	private Integer groupId;
	private String action;
	private String userType;
	private DualListModel<User> resourcesList;
	private OrganizationGroup group;
	private String buttonLabel;
	@Autowired private OrganizationGroupService service;
	private List<User>  filteredList;
	
	@PostConstruct
	public void init()
	{
		try
		{
			FacesContextUtils.getRequiredWebApplicationContext(FacesContext.getCurrentInstance()).getAutowireCapableBeanFactory().autowireBean(this);
			FacesContext fc = FacesUtils.getFacesContext();
			ExternalContext externalContext = fc.getExternalContext();
			Map<String, String> requestMap = externalContext.getRequestParameterMap();
			requestMap.entrySet().forEach(entry ->
			{
				System.out.println("Key : " + entry.getKey() + " Value : " + entry.getValue());
				if (entry.getKey().equals("groupId"))
				{
					groupId = Integer.valueOf(entry.getValue());
				}
				else if (entry.getKey().equals("action"))
				{
					action = entry.getValue();
				}
				else if (entry.getKey().equals("userType"))
				{
					userType = entry.getValue();
				}
			});
			
			if (groupId != null)
			{
				System.out.println("Group Id : " + groupId);
				System.out.println("Action is : " + action);
				System.out.println("userType : " + userType);
				
				group = service.findByGroupId(groupId);
				
				boolean tempAssignType = true;
				if (action.equals("Deassign"))
				{
					tempAssignType = false;
					buttonLabel = "Deassign";
				}
				else
				{
					buttonLabel = "Assign";
				}
				boolean tempUserType = false;
				if (userType.equals("Manager"))
				{
					tempUserType = true;
				}
				
				sourceList = service.getGroupUserList(FacesUtils.getCurrentUserId(), tempAssignType, tempUserType, groupId.toString());
				
				targetList = new ArrayList<User>(0);
			}
			
			if (sourceList != null)
			{
				System.out.println("Total privileges are :  " + sourceList.size());
				
				System.out.println("Target list size : " + targetList.size());
				
				if (resourcesList == null)
				{
					resourcesList = new DualListModel<User>();
					
					resourcesList.setSource(sourceList);
					resourcesList.setTarget(targetList);
				}
			}
			else
			{
				FacesUtils.addGlobalMessage(FacesMessageSeverity.FATAL, "No users defined",
				        "There are no users defined in the system, ask adminsitrator to add user first");
			}
			
		}
		catch (Exception ex)
		{
			logger.debug(ex.getMessage());
		}
	}
	
	// public void saveSelectedList()
	// {
	// targetList = resourcesList.getTarget();
	// System.out.println("Target list size : " + targetList.size());
	//
	// targetList.forEach(b -> System.out.println(b.getFirstName()));
	//
	// int tempAssignType = 2;
	// if (action.equals("Assign"))
	// {
	// tempAssignType = 1;
	// }
	//
	// int tempUserType = 2;
	// if (userType.equals("User"))
	// {
	// tempUserType = 1;
	// }
	//
	// int count = 0;
	// String result;
	// for (User user : targetList)
	// {
	// result = service.assignDeassignUserToGroup(FacesUtils.getCurrentUserId(),
	// tempAssignType, tempUserType, groupId.toString(),
	// user.getUserId());
	// if (result.equals("0"))
	// {
	// count++;
	// }
	// }
	//
	// FacesMessage message = null;
	// if (count == targetList.size())
	// {
	// message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Groups",
	// "Operation is successfully completed!");
	// }
	// else
	// {
	// message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Groups",
	// "Operation is not completed!");
	// }
	//
	// FacesUtils.redirect("/protected/add-group.xhtml", message);
	// }
	
	public void selectedList()
	{
		targetList = resourcesList.getTarget();
		System.out.println("Target list size : " + targetList.size());
		
		/* targetPrivileges.forEach(b -> System.out.println(b.getResDesc())); */
		
		int tempAssignType = 2;
		if (action.equals("Assign"))
		{
			tempAssignType = 1;
		}
		
		int tempUserType = 2;
		if (userType.equals("User"))
		{
			tempUserType = 1;
		}
		
		int count = 0;
		String result;
		for (User user : targetList)
		{
			result = service.assignDeassignUserToGroup(FacesUtils.getCurrentUserId(), tempAssignType, tempUserType, groupId.toString(),
			        user.getUserId());
			if (result.equals("0"))
			{
				count++;
			}
		}
		/*
		 * targetList.forEach(b -> {
		 * service.assignDeassignUserToGroup(FacesUtils.getCurrentUserId(),
		 * tempAssignType, tempUserType, groupId.toString(),
		 * targetList.get(0).getUserId()); });
		 */
		FacesMessage message = null;
		if (count == targetList.size())
		{
			message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Groups", "Operation is successfully completed!");
		}
		else
		{
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Groups", "Operation is not completed!");
		}
		
		FacesUtils.redirect("/protected/add-group.xhtml", message);
	}
	
	public void onTransfer(TransferEvent event)
	{
		StringBuilder builder = new StringBuilder();
		for (Object item : event.getItems())
		{
			builder.append(((User) item).getUserId()).append("<br />");
		}
		
		FacesMessage msg = new FacesMessage();
		msg.setSeverity(FacesMessage.SEVERITY_INFO);
		msg.setSummary("User Transferred");
		msg.setDetail(builder.toString());
		
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	
	public void assignUserToGroup()
	{
		try
		{
			Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
			String userId = (String) params.get("userId");
			System.out.println("Selected User ID :: " + userId);
			if (userId != null && sourceList != null && !sourceList.isEmpty())
			{
				for (User sourceUser : sourceList)
				{
					if (sourceUser.getUserId().equals(userId))
					{
						targetList.add(sourceUser);
						sourceList.remove(sourceUser);
						break;
					}
				}
				System.out.println("Target Source List Size :: " + targetList.size());
			}
			else
			{
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Selected user is null"));
			}
		}
		catch (Exception e)
		{
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Cannot assign user to group"));
		}
	}
	
	public void saveSelectedList()
	{
		System.out.println("Target list size : " + targetList.size());
		targetList.forEach(b -> System.out.println(b.getFirstName()));
		int tempAssignType = 2;
		if (action.equals("Assign"))
		{
			tempAssignType = 1;
		}
		int tempUserType = 2;
		if (userType.equals("User"))
		{
			tempUserType = 1;
		}
		int count = 0;
		String result;
		for (User user : targetList)
		{
			result = service.assignDeassignUserToGroup(FacesUtils.getCurrentUserId(), tempAssignType, tempUserType, groupId.toString(),
			        user.getUserId());
			if (result.equals("0"))
			{
				count++;
			}
		}
		FacesMessage message = null;
		if (count == targetList.size())
		{
			message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Groups", "Operation is successfully completed!");
		}
		else
		{
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Groups", "Operation is not completed!");
		}
		
		FacesUtils.redirect("/protected/add-group.xhtml", message);
	}
	
	/**
	 * @return the sourceList
	 */
	public List<User> getSourceList()
	{
		return sourceList;
	}
	
	/**
	 * @param sourceList
	 *            the sourceList to set
	 */
	public void setSourceList(List<User> sourceList)
	{
		this.sourceList = sourceList;
	}
	
	/**
	 * @return the targetList
	 */
	public List<User> getTargetList()
	{
		return targetList;
	}
	
	/**
	 * @param targetList
	 *            the targetList to set
	 */
	public void setTargetList(List<User> targetList)
	{
		this.targetList = targetList;
	}
	
	/**
	 * @return the groupId
	 */
	public Integer getGroupId()
	{
		return groupId;
	}
	
	/**
	 * @param groupId
	 *            the groupId to set
	 */
	public void setGroupId(Integer groupId)
	{
		this.groupId = groupId;
	}
	
	/**
	 * @return the action
	 */
	public String getAction()
	{
		return action;
	}
	
	/**
	 * @param action
	 *            the action to set
	 */
	public void setAction(String action)
	{
		this.action = action;
	}
	
	/**
	 * @return the userType
	 */
	public String getUserType()
	{
		return userType;
	}
	
	/**
	 * @param userType
	 *            the userType to set
	 */
	public void setUserType(String userType)
	{
		this.userType = userType;
	}
	
	/**
	 * @return the resourcesList
	 */
	public DualListModel<User> getResourcesList()
	{
		return resourcesList;
	}
	
	/**
	 * @param resourcesList
	 *            the resourcesList to set
	 */
	public void setResourcesList(DualListModel<User> resourcesList)
	{
		this.resourcesList = resourcesList;
	}
	
	/**
	 * @return the group
	 */
	public OrganizationGroup getGroup()
	{
		return group;
	}
	
	/**
	 * @param group
	 *            the group to set
	 */
	public void setGroup(OrganizationGroup group)
	{
		this.group = group;
	}
	
	public String getButtonLabel()
	{
		return buttonLabel;
	}
	
	public void setButtonLabel(String buttonLabel)
	{
		this.buttonLabel = buttonLabel;
	}

	/**
	 * @return the filteredList
	 */
	public List<User> getFilteredList() {
		return filteredList;
	}

	/**
	 * @param filteredList the filteredList to set
	 */
	public void setFilteredList(List<User> filteredList) {
		this.filteredList = filteredList;
	}
}
