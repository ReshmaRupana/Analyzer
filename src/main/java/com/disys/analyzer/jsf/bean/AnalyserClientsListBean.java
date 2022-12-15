/**
 * 
 */
package com.disys.analyzer.jsf.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import org.springframework.web.jsf.FacesContextUtils;

import com.disys.analyzer.config.util.Constants;
import com.disys.analyzer.config.util.FacesUtils;
import com.disys.analyzer.model.dto.AnalyserClientDTO;
import com.disys.analyzer.service.AnalyserClientService;

/**
 * @author Sajid
 * @since Dec 25, 2017
 */
@ManagedBean
@ViewScoped
public class AnalyserClientsListBean extends SpringBeanAutowiringSupport implements Serializable
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7828415206292245248L;
	
	private Logger logger = LoggerFactory.getLogger(AnalyserClientBean.class);
	
	@Autowired
	private AnalyserClientService service;
	
	private List<AnalyserClientDTO> list;
	private List<AnalyserClientDTO> filteredList;
	
	private String searchString;
	
	private Integer clientId;
	private Integer status;
	
	private Boolean enableAdministrativeActions;
	private String companyCode;
	
	@PostConstruct
	public void init()
	{
		// Main query -- {call
		// wireless.spGetAnalyserAllDistinctClient('[0]','[1]','[2]','[3]')}
		// 17/12/25 08:32:08 After query -- {call
		// wireless.spGetAnalyserAllDistinctClient('Gregory.Armstrong@DISYS.COM','0','Company','ALL')}
		
		FacesContextUtils.getRequiredWebApplicationContext(FacesContext.getCurrentInstance()).getAutowireCapableBeanFactory().autowireBean(this);
	}
	
	public AnalyserClientsListBean()
	{
		System.out.println("In AnalyserClientsListBean");
		
		enableAdministrativeActions = FacesUtils.getAdministrativeActions();
		
		this.eraseFilter();
	}
	
	public void filter()
	{
		
		if (searchString.equals("") || searchString == null)
		{
			searchString = "ALL";
		}
		if(companyCode == null || companyCode.isEmpty())
		{
			companyCode = Constants.DEFAULT_COMPANY_CODE;
		}
		
		list = service.spGetAnalyserAllDistinctClient(FacesUtils.getCurrentUserId(), "0", "Company", searchString.trim(), companyCode);
		
		System.out.println("call wireless.spGetAnalyserAllDistinctClient('" + FacesUtils.getCurrentUserId() + "','" + "0" + "','" + "Company" + "','" + searchString.trim() + "','" +companyCode+"')");
		logger.info("Total list size is : " + list.size());
	}
	
	public void eraseFilter()
	{
		this.searchString = "";
		this.filter();
	}
	
	public void changeStatus()
	{
		
		FacesContext fc = FacesUtils.getFacesContext();
		ExternalContext externalContext = fc.getExternalContext();
		Map<String, String> requestMap = externalContext.getRequestParameterMap();
		
		requestMap.entrySet().forEach(entry ->
		{
			System.out.println("Key : " + entry.getKey() + " Value : " + entry.getValue());
			if (entry.getKey().equals("clientId"))
			{
				clientId = Integer.valueOf(entry.getValue());
			}
			if (entry.getKey().equals("status"))
			{
				status = Integer.valueOf(entry.getValue());
			}
		});
		
		String result = service.changeClientStatus(FacesUtils.getCurrentUserId(), clientId, status);
		System.out.println("Status Updated..." + result);
		this.filter();
	}
	
	/**
	 * @return the list
	 */
	public List<AnalyserClientDTO> getList()
	{
		if (list == null)
		{
			list = new ArrayList<AnalyserClientDTO>();
			/*
			 * String userList = "0"; String orderBy = "Company"; list =
			 * service.spGetAnalyserAllDistinctClient(
			 * FacesUtils.getCurrentUserId(), userList, orderBy, searchString);
			 * logger.debug("List size is : " + list.size());
			 */
		}
		return list;
	}
	
	/**
	 * @param list
	 *            the list to set
	 */
	public void setList(List<AnalyserClientDTO> list)
	{
		this.list = list;
	}
	
	/**
	 * @return the searchString
	 */
	public String getSearchString()
	{
		return searchString;
	}
	
	/**
	 * @param searchString
	 *            the searchString to set
	 */
	public void setSearchString(String searchString)
	{
		this.searchString = searchString;
	}
	
	/**
	 * @return the filteredList
	 */
	public List<AnalyserClientDTO> getFilteredList()
	{
		return filteredList;
	}
	
	/**
	 * @param filteredList
	 *            the filteredList to set
	 */
	public void setFilteredList(List<AnalyserClientDTO> filteredList)
	{
		this.filteredList = filteredList;
	}
	
	/**
	 * @return the status
	 */
	public Integer getStatus()
	{
		return status;
	}
	
	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(Integer status)
	{
		this.status = status;
	}
	
	/**
	 * @param clientId
	 *            the clientId to set
	 */
	public void setClientId(Integer clientId)
	{
		this.clientId = clientId;
	}
	
	/**
	 * @return the enableAdministrativeActions
	 */
	public Boolean getEnableAdministrativeActions()
	{
		return enableAdministrativeActions;
	}
	
	/**
	 * @param enableAdministrativeActions
	 *            the enableAdministrativeActions to set
	 */
	public void setEnableAdministrativeActions(Boolean enableAdministrativeActions)
	{
		this.enableAdministrativeActions = enableAdministrativeActions;
	}
	/**
	 * @return the companyCode
	 */
	public String getCompanyCode() {
		return companyCode;
	}
	/**
	 * @param companyCode
	 *            the companyCode to set
	 */
	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}
	
	
}
