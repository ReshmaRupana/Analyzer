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
import javax.faces.model.SelectItem;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import org.springframework.web.jsf.FacesContextUtils;

import com.disys.analyzer.config.util.Constants;
import com.disys.analyzer.config.util.FacesUtils;
import com.disys.analyzer.model.AnalyserSubContractor;
import com.disys.analyzer.service.AnalyserSubContractorService;
import com.disys.analyzer.service.ApplicationUserService;

/**
 * @author Sajid
 * @since Dec 25, 2017
 */
@ManagedBean
@ViewScoped
public class AnalyserSubcontractorsListBean extends SpringBeanAutowiringSupport implements Serializable
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7828415206292245248L;
	
	private Logger logger = LoggerFactory.getLogger(AnalyserClientBean.class);
	
	@Autowired private AnalyserSubContractorService service;
	
	@Autowired private ApplicationUserService applicationUserService;
	
	private List<AnalyserSubContractor> list;
	
	private String searchString;
	private String status;
	
	private List<SelectItem> recordStatus;
	
	private String userId;
	private String companyName;
	private String orderBy;
	
	private boolean showAddButton;
	
	private Integer contractorId;
	private Integer st;
	
	private Boolean enableAdministrativeActions;
	private String companyCode;
	@PostConstruct
	public void init()
	{
		FacesContextUtils.getRequiredWebApplicationContext(FacesContext.getCurrentInstance()).getAutowireCapableBeanFactory().autowireBean(this);
	}
	
	public AnalyserSubcontractorsListBean()
	{
		System.out.println("In AnalyserSubcontractorsListBean");
		
		userId = FacesUtils.getCurrentUserId();
		companyName = "-";
		orderBy = "LegalName";
		
		showAddButton = false;
		
		enableAdministrativeActions = FacesUtils.getAdministrativeActions();
		
		
		this.eraseFilter();
	}
	
	public void filter()
	{
		// if (searchString.equals("") || searchString == null ||
		// searchString.equals("ALL"))
		// {
		// searchString = "ALL";
		// }
		// else
		// {
		// companyName = searchString;
		// orderBy = "null";
		// searchString = "FORACOMPANY";
		// }
		//
		// if (status.equals("") || status == null)
		// {
		// status = "ALL";
		// }
		// else
		// {
		// if (!searchString.equalsIgnoreCase("FORACOMPANY"))
		// {
		// companyName = "-";
		// orderBy = "null";
		// searchString = status;
		// status = "99";
		// }
		// }
		
		list = new ArrayList<AnalyserSubContractor>(0);
		
		// list = service.getAnalyserAllContractors(userId, companyName,
		// orderBy, searchString, status);
		if(status == null || status.trim().length() == 0)
		{
			status = "ALL";
		}
		companyName = "ALL";
		if(companyCode == null || companyCode.isEmpty())
		{
			companyCode = Constants.DEFAULT_COMPANY_CODE;
		}
		
		list = service.getAnalyserAllContractors(userId, companyName, status, companyCode);
		
		System.out.println("List Size : " + list.size());
		System.out.println("exec wireless.spGetAnalyserAllContractorNew '" + userId + "','" + companyName + "','" + status + "','" + companyCode + "';");
		
		logger.info("Total list size is : " + list.size());
	}
	
	public void eraseFilter()
	{
		this.searchString = "";
		this.status = "";
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
			if (entry.getKey().equals("contractorId"))
			{
				contractorId = Integer.valueOf(entry.getValue());
			}
			if (entry.getKey().equals("status"))
			{
				st = Integer.valueOf(entry.getValue());
			}
		});
		
		String result = service.changeSubcontractorStatus(FacesUtils.getCurrentUserId(), contractorId, st);
		System.out.println("Status Updated..." + result);
		if (this.status.equals("99"))
		{
			this.status = "ALL";
		}
		this.filter();
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
	 * @return the status
	 */
	public String getStatus()
	{
		return status;
	}
	
	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(String status)
	{
		this.status = status;
	}
	
	/**
	 * @return the list
	 */
	public List<AnalyserSubContractor> getList()
	{
		return list;
	}
	
	/**
	 * @param list
	 *            the list to set
	 */
	public void setList(List<AnalyserSubContractor> list)
	{
		this.list = list;
	}
	
	/**
	 * @return the userId
	 */
	public String getUserId()
	{
		return userId;
	}
	
	/**
	 * @param userId
	 *            the userId to set
	 */
	public void setUserId(String userId)
	{
		this.userId = userId;
	}
	
	/**
	 * @return the companyName
	 */
	public String getCompanyName()
	{
		return companyName;
	}
	
	/**
	 * @param companyName
	 *            the companyName to set
	 */
	public void setCompanyName(String companyName)
	{
		this.companyName = companyName;
	}
	
	/**
	 * @return the orderBy
	 */
	public String getOrderBy()
	{
		return orderBy;
	}
	
	/**
	 * @param orderBy
	 *            the orderBy to set
	 */
	public void setOrderBy(String orderBy)
	{
		this.orderBy = orderBy;
	}
	
	/**
	 * @return the recordStatus
	 */
	public List<SelectItem> getRecordStatus()
	{
		
		Integer roleId = applicationUserService.getRoleId(this.getUserId());
		
		recordStatus = new ArrayList<SelectItem>();
		
		// if (roleId == 3 || roleId == 443 || roleId == 447 || roleId == 448)
		// {
		// // TODO show add new contractor only to these users.
		// showAddButton = true;
		// /*
		// * <td width="20%" class="verdana11"><a
		// * href="javascript:addContractor()" class="verdana13">Add New
		// * Subcontractor</a></td>
		// */
		// recordStatus.add(new SelectItem("NOTSUBMITTED", "Not Yet
		// Submitted"));
		// }
		//
		// if (roleId == 443 || roleId == 3)
		// {
		// recordStatus.add(new SelectItem("APPROVED", "Apporved"));
		// recordStatus.add(new SelectItem("UNAPPROVED", "Not Yet Reviewed"));
		// recordStatus.add(new SelectItem("CONDITIONALLYAPPROVED",
		// "Conditionally Approved"));
		// recordStatus.add(new SelectItem("REJECTED", "Rejected"));
		// }
		
		if (roleId == 3 || roleId == 443 || roleId == 447 || roleId == 448)
		{
			showAddButton = true;
		}
		recordStatus.add(new SelectItem("1", "Approved"));
		recordStatus.add(new SelectItem("2", "Conditionally Approved"));
		recordStatus.add(new SelectItem("3", "Rejected"));
		recordStatus.add(new SelectItem("0", "Not Approved"));
		return recordStatus;
	}
	
	/**
	 * @param recordStatus
	 *            the recordStatus to set
	 */
	public void setRecordStatus(List<SelectItem> recordStatus)
	{
		this.recordStatus = recordStatus;
	}
	
	/**
	 * @return the showAddButton
	 */
	public boolean isShowAddButton()
	{
		return showAddButton;
	}
	
	/**
	 * @param showAddButton
	 *            the showAddButton to set
	 */
	public void setShowAddButton(boolean showAddButton)
	{
		this.showAddButton = showAddButton;
	}
	
	public Integer getContractorId()
	{
		return contractorId;
	}
	
	public void setContractorId(Integer contractorId)
	{
		this.contractorId = contractorId;
	}
	
	public Integer getSt()
	{
		return st;
	}
	
	public void setSt(Integer st)
	{
		this.st = st;
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