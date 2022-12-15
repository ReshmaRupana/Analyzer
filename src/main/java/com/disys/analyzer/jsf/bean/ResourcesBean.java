/**
 * 
 */
package com.disys.analyzer.jsf.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.primefaces.event.SelectEvent;
import org.primefaces.event.TransferEvent;
import org.primefaces.event.UnselectEvent;
import org.primefaces.model.DualListModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import org.springframework.web.jsf.FacesContextUtils;

import com.disys.analyzer.config.util.FacesMessageSeverity;
import com.disys.analyzer.config.util.FacesUtils;
import com.disys.analyzer.model.Resource;
import com.disys.analyzer.model.Role;
import com.disys.analyzer.service.ResourceService;
import com.disys.analyzer.service.RoleService;

/**
 * @author Sajid
 * @since Nov 17, 2017
 */
@ManagedBean
@ViewScoped
public class ResourcesBean extends SpringBeanAutowiringSupport implements Serializable
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4465387555468330785L;
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	private Resource resource;
	private List<Resource> list;
	
	private Integer roleId;
	private String action;
	
	@Autowired private ResourceService resourceService;
	
	@Autowired private RoleService roleService;
	
	private DualListModel<Resource> resourcesList;
	
	private Role role;
	
	private List<Resource> sourcePrivileges;
	private List<Resource> targetPrivileges;
	
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
				if (entry.getKey().equals("roleId"))
				{
					roleId = Integer.valueOf(entry.getValue());
				}
				else if (entry.getKey().equals("action"))
				{
					action = entry.getValue();
				}
			});
			if (roleId != null)
			{
				role = roleService.getRole(FacesUtils.getCurrentUserId(), roleId);
				if (role != null)
				{
					list = new ArrayList<Resource>(0);
					List<Resource> tempList = resourceService.getRolePrivileges(FacesUtils.getCurrentUserId(), roleId);
					for (Resource res : tempList)
					{
						if (res.getParentId() != null && res.getParentId() != -1)
						{
							if (res.getParentId() > 0 && res.getTitleId() == 0)
							{
								System.out.println("Resource Name :: " + res.getResDesc());
								continue;
							}
							list.add(res);
						}
					}
					sourcePrivileges = resourceService.findAllWithParent(FacesUtils.getCurrentUserId());
					if (sourcePrivileges == null || sourcePrivileges.isEmpty())
					{
						sourcePrivileges = new ArrayList<Resource>(0);
						FacesUtils.addGlobalMessage(FacesMessageSeverity.FATAL, "No privileges defined",
						        "There are no privileges defined in the system, ask adminsitrator to define privileges first");
					}
					targetPrivileges = new ArrayList<>(list);
					if (resourcesList == null)
					{
						resourcesList = new DualListModel<Resource>();
						sourcePrivileges = sourcePrivileges.stream()
						        .filter(o1 -> targetPrivileges.stream().noneMatch(o2 -> o2.getResId().equals(o1.getResId())))
						        .collect(Collectors.toList());
						logger.debug("New source list size is : " + sourcePrivileges.size());
						resourcesList.setSource(sourcePrivileges);
						resourcesList.setTarget(targetPrivileges);
					}
				}
			}
			
		}
		catch (Exception ex)
		{
			logger.debug(ex.getMessage());
		}
	}
	
	public void selectedList()
	{
		sourcePrivileges = resourcesList.getSource();
		System.out.println("Source list size : " + sourcePrivileges.size());
		// setting privileges in the database
		
		// for every privilege in source list, set it to -1
		sourcePrivileges.forEach(a ->
		{
			resourceService.setPrivilege(FacesUtils.getCurrentUserId(), roleId, a.getResId(), -1);
		});
		
		targetPrivileges = resourcesList.getTarget();
		System.out.println("Target list size : " + targetPrivileges.size());
		
		/* targetPrivileges.forEach(b -> System.out.println(b.getResDesc())); */
		
		// for every privilege in target list, set it to 0
		targetPrivileges.forEach(b ->
		{
			resourceService.setPrivilege(FacesUtils.getCurrentUserId(), roleId, b.getResId(), 0);
		});
		
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Privileges", "The Set Privilege operation is successfully completed!");
		
		FacesUtils.redirect("/protected/application-roles.xhtml", message);
		
	}
	
	/*
	 * public ResourcesBean() {
	 * 
	 * }
	 */
	
	public void onTransfer(TransferEvent event)
	{
		FacesMessage msg = new FacesMessage();
		List<Resource> defaultResList = new ArrayList<Resource>(2);
		StringBuilder builder = new StringBuilder();
		for (Object item : event.getItems())
		{
			builder.append(((Resource) item).getResDesc()).append("<br />");
		}
		if (event.isRemove())
		{
			targetPrivileges = (List<Resource>) event.getItems();
			if (targetPrivileges != null && targetPrivileges.size() > 0)
			{
				for (Resource res : targetPrivileges)
				{
					if (res.getResId() == 461 || res.getResId() == 462)
					{
						defaultResList.add(res);
					}
				}
			}
			if (defaultResList.size() > 0)
			{
				resourcesList.getTarget().addAll(defaultResList);
			}
			if (event.getItems().size() == defaultResList.size())
			{
				msg.setSeverity(FacesMessage.SEVERITY_FATAL);
				msg.setSummary("Default roles cannot be transferred");
				msg.setDetail(builder.toString());
			}
			else if (defaultResList.size() > 0)
			{
				msg.setSeverity(FacesMessage.SEVERITY_FATAL);
				msg.setSummary("Roles transferred without default roles");
				msg.setDetail(builder.toString());
			}
			else
			{
				msg.setSeverity(FacesMessage.SEVERITY_INFO);
				msg.setSummary("Roles Transferred");
				msg.setDetail(builder.toString());
			}
		}
		else
		{
			msg.setSeverity(FacesMessage.SEVERITY_INFO);
			msg.setSummary("Roles Transferred");
			msg.setDetail(builder.toString());
		}
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	
	public void onSelect(SelectEvent event)
	{
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Item Selected", event.getObject().toString()));
	}
	
	public void onUnselect(UnselectEvent event)
	{
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Item Unselected", event.getObject().toString()));
	}
	
	/*
	 * public void filter() {
	 * 
	 * list = resourceService.findAll(example, pageRequest);
	 * logger.info("Total list size is : " + list.getSize()); }
	 */
	
	/*
	 * public void eraseFilter() { this.resource = new Resource(); this.list =
	 * new ArrayList<Resource>(); this.filter(); }
	 */
	
	/**
	 * @return the resource
	 */
	public Resource getResource()
	{
		return resource;
	}
	
	/**
	 * @param resource
	 *            the resource to set
	 */
	public void setResource(Resource resource)
	{
		this.resource = resource;
	}
	
	/**
	 * @return the list
	 */
	public List<Resource> getList()
	{
		return list;
	}
	
	/**
	 * @param list
	 *            the list to set
	 */
	public void setList(List<Resource> list)
	{
		this.list = list;
	}
	
	/**
	 * @return the roleId
	 */
	public Integer getRoleId()
	{
		return roleId;
	}
	
	/**
	 * @param roleId
	 *            the roleId to set
	 */
	public void setRoleId(Integer roleId)
	{
		this.roleId = roleId;
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
	 * @return the resourcesList
	 */
	public DualListModel<Resource> getResourcesList()
	{
		return resourcesList;
	}
	
	/**
	 * @param resourcesList
	 *            the resourcesList to set
	 */
	public void setResourcesList(DualListModel<Resource> resourcesList)
	{
		this.resourcesList = resourcesList;
	}
	
	/**
	 * @return the role
	 */
	public Role getRole()
	{
		return role;
	}
	
	/**
	 * @param role
	 *            the role to set
	 */
	public void setRole(Role role)
	{
		this.role = role;
	}
	
	/**
	 * @return the sourcePrivileges
	 */
	public List<Resource> getSourcePrivileges()
	{
		return sourcePrivileges;
	}
	
	/**
	 * @param sourcePrivileges
	 *            the sourcePrivileges to set
	 */
	public void setSourcePrivileges(List<Resource> sourcePrivileges)
	{
		this.sourcePrivileges = sourcePrivileges;
	}
	
	/**
	 * @return the targetPrivileges
	 */
	public List<Resource> getTargetPrivileges()
	{
		return targetPrivileges;
	}
	
	/**
	 * @param targetPrivileges
	 *            the targetPrivileges to set
	 */
	public void setTargetPrivileges(List<Resource> targetPrivileges)
	{
		this.targetPrivileges = targetPrivileges;
	}
	
}
