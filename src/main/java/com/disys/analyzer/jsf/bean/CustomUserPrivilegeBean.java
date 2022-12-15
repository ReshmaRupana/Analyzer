package com.disys.analyzer.jsf.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import org.springframework.web.jsf.FacesContextUtils;

import com.disys.analyzer.config.util.Constants;
import com.disys.analyzer.config.util.FacesMessageSeverity;
import com.disys.analyzer.config.util.FacesUtils;
import com.disys.analyzer.model.Resource;
import com.disys.analyzer.model.dto.UserDTO;
import com.disys.analyzer.service.AnalyserCommisionPersonService;
import com.disys.analyzer.service.ApplicationUserService;
import com.disys.analyzer.service.ResourceService;

@ManagedBean
@ViewScoped
public class CustomUserPrivilegeBean extends SpringBeanAutowiringSupport implements Serializable
{
	private static final long serialVersionUID = 4091589023676966490L;
	private Logger logger = LoggerFactory.getLogger(getClass());
	private List<Resource> list;
	private List<Resource> filteredList;
	private List<Resource> unAssignedResource;
	private List<Resource> assignedResource;
	private List<UserDTO> activeUserList;
	private String userId;
	private String prevUserId;
	private Integer resId;
	private Integer parentId;
	private Integer assigned;
	@Autowired private ResourceService resourceService;
	//@Autowired private AnalyserCommisionPersonService service;
	@Autowired private ApplicationUserService service;
	
	
	public CustomUserPrivilegeBean()
	{
		
	}
	
	@PostConstruct
	private void init()
	{
		try
		{
			FacesContextUtils.getRequiredWebApplicationContext(FacesContext.getCurrentInstance()).getAutowireCapableBeanFactory().autowireBean(this);
			fillActiveUserList();
			fillCustomUserPrivileges();
		}
		catch (BeansException e)
		{
			logger.debug(e.getMessage());
			displayMessage("Error in CustomUserPrivilegeBean init " + e.getMessage(), true);
			e.printStackTrace();
		}
		catch (IllegalStateException e)
		{
			logger.debug(e.getMessage());
			displayMessage("Error in CustomUserPrivilegeBean init " + e.getMessage(), true);
			e.printStackTrace();
		}
		catch (Exception e)
		{
			logger.debug(e.getMessage());
			displayMessage("Error in CustomUserPrivilegeBean init " + e.getMessage(), true);
			e.printStackTrace();
		}
	}
	
	private void displayMessage(String msg, boolean error)
	{
		FacesUtils.addGlobalMessage(error ? FacesMessageSeverity.ERROR : FacesMessageSeverity.INFO, msg);
	}
	
	private void fillActiveUserList()
	{
		activeUserList = service.spGetUserListForPriviledges(FacesUtils.getCurrentUserId());
		if (activeUserList != null && !activeUserList.isEmpty())
		{
			userId = activeUserList.get(0).getUserId();
			prevUserId = userId;
		}
	}
	
	private void fillCustomUserPrivileges()
	{
		list = new ArrayList<Resource>(0);
		if (userId != null && userId.trim().length() > 0)
		{
			unAssignedResource = resourceService.spGetUserRolePrivileges(userId, 1);
			if (unAssignedResource == null)
			{
				unAssignedResource = new ArrayList<Resource>(0);
			}
			assignedResource = resourceService.spGetUserRolePrivileges(userId, 2);
			if (assignedResource == null)
			{
				assignedResource = new ArrayList<Resource>(0);
			}
			unAssignedResource = unAssignedResource.stream()
			        .filter(o1 -> assignedResource.stream().noneMatch(o2 -> o2.getResId().equals(o1.getResId()))).collect(Collectors.toList());
			list.addAll(assignedResource);
			list.addAll(unAssignedResource);
			prevUserId = userId;
		}
	}
	
	public void searchUserRolePrivileges()
	{
		try
		{
			fillCustomUserPrivileges();
		}
		catch (Exception e)
		{
			logger.debug(e.getMessage());
			displayMessage("Error in CustomUserPrivilegeBean search " + e.getMessage(), true);
			e.printStackTrace();
		}
	}
	
	public void changeStatus()
	{
		try
		{
			if (prevUserId.trim().equalsIgnoreCase(userId.trim()))
			{
				FacesContext fc = FacesUtils.getFacesContext();
				ExternalContext externalContext = fc.getExternalContext();
				Map<String, String> requestMap = externalContext.getRequestParameterMap();
				requestMap.entrySet().forEach(entry ->
				{
					System.out.println("Key : " + entry.getKey() + " Value : " + entry.getValue());
					if (entry.getKey().equals("resId"))
					{
						resId = Integer.valueOf(entry.getValue());
					}
					if (entry.getKey().equals("assigned"))
					{
						assigned = Integer.valueOf(entry.getValue());
					}
					if (entry.getKey().equals("parentId"))
					{
						parentId = Integer.valueOf(entry.getValue());
					}
				});
				
				String result = resourceService.spAddCustomUserPrivileges(FacesUtils.getCurrentUserId(), userId, resId, parentId, assigned);
				if (result.equals("0"))
				{
					fillCustomUserPrivileges();
					displayMessage("Custom privileg assigned/deassigned to user", false);
				}
				else
				{
					displayMessage("Cannot assigned/deassigned custom privilege", true);
				}
			}
			else
			{
				displayMessage("Cannot assigned/deassigned custom privileges does not belong to this user", true);
			}
		}
		catch (Exception e)
		{
			logger.debug(e.getMessage());
			displayMessage("Error in CustomUserPrivilegeBean assign/deassign " + e.getMessage(), true);
			e.printStackTrace();
		}
	}
	
	public List<Resource> getList()
	{
		return list;
	}
	
	public void setList(List<Resource> list)
	{
		this.list = list;
	}
	
	public List<Resource> getFilteredList()
	{
		return filteredList;
	}
	
	public void setFilteredList(List<Resource> filteredList)
	{
		this.filteredList = filteredList;
	}
	
	public List<UserDTO> getActiveUserList()
	{
		return activeUserList;
	}
	
	public void setActiveUserList(List<UserDTO> activeUserList)
	{
		this.activeUserList = activeUserList;
	}
	
	public String getUserId()
	{
		return userId;
	}
	
	public void setUserId(String userId)
	{
		this.userId = userId;
	}
	
}
