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
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;

import org.primefaces.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import org.springframework.web.jsf.FacesContextUtils;

import com.disys.analyzer.config.util.FacesMessageSeverity;
import com.disys.analyzer.config.util.FacesUtils;
import com.disys.analyzer.model.AnalyserSubContractor;
import com.disys.analyzer.service.AnalyserSubContractorService;

/**
 * @author Sajid
 * @since Nov 1, 2017
 */
@ManagedBean
@ViewScoped
public class AnalyserSubContractorBean extends SpringBeanAutowiringSupport
		implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5288682887999717306L;

	private Logger logger = LoggerFactory
			.getLogger(AnalyserSubContractorBean.class);

	@Autowired
	private AnalyserSubContractorService service;

	private AnalyserSubContractor selectedAnalyserSubContractor;
	private List<AnalyserSubContractor> filteredList;
	private List<AnalyserSubContractor> list;
	
	
	private String userId;
	private String companyName;
	private String orderBy;
	private String searchString;
	private String recordStatus;
	private String companyCode;


	@PostConstruct
	public void init() {
		FacesContextUtils
				.getRequiredWebApplicationContext(
						FacesContext.getCurrentInstance())
				.getAutowireCapableBeanFactory().autowireBean(this);
	}

	/**
	 * 
	 */
	public AnalyserSubContractorBean() {
		super();
		Map<String,String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		this.userId = FacesUtils.getCurrentUserId();
		
		this.companyName = params.get("companyName");
		this.orderBy = params.get("orderBy");
		this.searchString = params.get("searchString");
		this.recordStatus = params.get("recordStatus");
		this.companyCode = params.get("companyCode");
	}

	public void select(AjaxBehaviorEvent event) {
		try {
			String infoMessage = "Selected AnalyserSubContractor";
			FacesUtils.addGlobalMessage(FacesMessageSeverity.INFO, infoMessage,
					selectedAnalyserSubContractor.getCompany());
		} catch (Exception e) {
			String errorMessage = "Couldn't be selected";
			FacesUtils.addGlobalMessage(FacesMessageSeverity.ERROR,
					errorMessage, e.getMessage());
		}
	}

	/**
	 * @return the list
	 */
	public List<AnalyserSubContractor> getList() {
		if (list == null) {
			list = new ArrayList<AnalyserSubContractor>();

			/*String userId = "Gregory.Armstrong@DISYS.COM";
			String companyName = "A";
			String orderBy = "Company";
			String searchString = "ALL";
			String recordStatus = "99";*/

			//call wireless.spGetAnalyserAllContractor('Gregory.Armstrong@DISYS.COM','-','LegalName','FORACOMPANYACTIVE','1')
			
			list = service.getAnalyserAllContractors(userId, companyName,
					orderBy, searchString, recordStatus, companyCode);
			System.out.println("List Size : " + list.size());
			System.out.println("spGetAnalyserAllContractor('"+userId+"','"+companyName+"','"+orderBy+"','"+searchString+"','"+recordStatus+"', '"+companyCode+"' )");

			logger.debug("List fetched for All contractors");
			logger.debug("spGetAnalyserAllContractor('"+userId+"','"+companyName+"','"+orderBy+"','"+searchString+"','"+recordStatus+"', '"+companyCode+"')");
		}
		return list;
	}

	/**
	 * @param list
	 *            the list to set
	 */
	public void setList(List<AnalyserSubContractor> list) {
		this.list = list;
	}

	/**
	 * @return the selectedAnalyserSubContractor
	 */
	public AnalyserSubContractor getSelectedAnalyserSubContractor() {
		return selectedAnalyserSubContractor;
	}

	/**
	 * @param selectedAnalyserSubContractor
	 *            the selectedAnalyserSubContractor to set
	 */
	public void setSelectedAnalyserSubContractor(
			AnalyserSubContractor selectedAnalyserSubContractor) {
		this.selectedAnalyserSubContractor = selectedAnalyserSubContractor;
	}

	public void selectAnalyserSubContractorFromDialog(
			AnalyserSubContractor analyserSubContractor) {
		RequestContext.getCurrentInstance().closeDialog(analyserSubContractor);
	}

	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * @return the companyName
	 */
	public String getCompanyName() {
		return companyName;
	}

	/**
	 * @param companyName the companyName to set
	 */
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	/**
	 * @return the orderBy
	 */
	public String getOrderBy() {
		return orderBy;
	}

	/**
	 * @param orderBy the orderBy to set
	 */
	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	/**
	 * @return the searchString
	 */
	public String getSearchString() {
		return searchString;
	}

	/**
	 * @param searchString the searchString to set
	 */
	public void setSearchString(String searchString) {
		this.searchString = searchString;
	}

	/**
	 * @return the recordStatus
	 */
	public String getRecordStatus() {
		return recordStatus;
	}

	/**
	 * @param recordStatus the recordStatus to set
	 */
	public void setRecordStatus(String recordStatus) {
		this.recordStatus = recordStatus;
	}

	public List<AnalyserSubContractor> getFilteredList()
	{
		return filteredList;
	}

	public void setFilteredList(List<AnalyserSubContractor> filteredList)
	{
		this.filteredList = filteredList;
	}

	public String getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

}
