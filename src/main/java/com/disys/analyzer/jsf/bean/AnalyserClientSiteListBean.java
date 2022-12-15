/**
 * 
 */
package com.disys.analyzer.jsf.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import org.springframework.web.jsf.FacesContextUtils;

import com.disys.analyzer.config.util.FacesUtils;
import com.disys.analyzer.reports.util.ReportCriteria;
import com.disys.analyzer.service.AnalyserClientService;
import com.disys.analyzer.service.OrganizationGroupService;

/**
 * @author Sajid
 * @since Dec 25, 2017
 */
@ManagedBean
@ViewScoped
public class AnalyserClientSiteListBean extends SpringBeanAutowiringSupport
		implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2343479451809817683L;

	private Logger logger = LoggerFactory.getLogger(getClass());

	private String clientId;
	private Integer clientSiteId;
	private Integer status;
	private String action;

	private String searchString;

	@Autowired
	private OrganizationGroupService organizationGroupService;

	private List<Map<String, Object>> reportData;
	private List<Map<String, Object>> filteredList;
	
	private Boolean enableAdministrativeActions;
	
	@Autowired
	private AnalyserClientService service;

	@PostConstruct
	public void init() {
		FacesContextUtils
				.getRequiredWebApplicationContext(
						FacesContext.getCurrentInstance())
				.getAutowireCapableBeanFactory().autowireBean(this);
	}

	public AnalyserClientSiteListBean() {
		logger.debug("In AnalyserClientSiteListBean");
		try {
			FacesContext fc = FacesUtils.getFacesContext();
			ExternalContext externalContext = fc.getExternalContext();
			Map<String, String> requestMap = externalContext
					.getRequestParameterMap();

			requestMap.entrySet().forEach(
					entry -> {
						System.out.println("Key : " + entry.getKey()
								+ " Value : " + entry.getValue());
						if (entry.getKey().equals("clientId")) {
							clientId = entry.getValue();
						} else if (entry.getKey().equals("action")) {
							action = entry.getValue();
						}
					});
			
			
			enableAdministrativeActions = FacesUtils.getAdministrativeActions();

			generateReport();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void generateReport() {
		/*StringBuffer userList = new StringBuffer("");

		try {
			List<OrganizationGroup> groups = organizationGroupService
					.getUserGroups(FacesUtils.getCurrentUserId());
			boolean first = true;
			for (OrganizationGroup group : groups) {
				HashMap<Integer, String> map = organizationGroupService
						.getUsersForAGroup(FacesUtils.getCurrentUserId(), group
								.getGroupId().toString());

				Iterator<Entry<Integer, String>> iter = map.entrySet()
						.iterator();
				while (iter.hasNext()) {
					Entry<Integer, String> item = iter.next();
					if (first) {
						userList.append(item.getKey());
						first = false;
					} else {
						userList.append("," + item.getKey());
					}
				}

			}
			System.out.println("USERLIST: " + userList.toString());

		} catch (Exception e) {
			e.printStackTrace();
		}*/

		// TODO change after discussion with Mr.Tayyab
		String userList = "0";
		String reportName = "ANALYSERCLIENTSITE_LIST_REPORT";

		// String sortOrder = "Company|Updated_By|Client_Id";
		String sortOrder = "Manager_Name";
		if (searchString == null || searchString.equals("")) {
			searchString = "ALL";
		}
		// always get the userid for security reasons
		String reportParam = FacesUtils.getCurrentUserId();
		reportParam = reportParam + "|" + userList + "|" + clientId + "|"
				+ sortOrder + "|" + searchString + "|true"  ;

		logger.debug("ANALYSERCLIENTSITE_LIST_REPORT Report Parameters : "+reportParam);
		ReportCriteria rc = null;
		reportData = new ArrayList<Map<String, Object>>();
		try {
			rc = new ReportCriteria(reportName, reportParam);
			reportData = rc.getReport();// get the data from the arraylis
			System.out.println(reportData.size());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public void changeStatus()
	{
		
		FacesContext fc = FacesUtils.getFacesContext();
		ExternalContext externalContext = fc.getExternalContext();
		Map<String, String> requestMap = externalContext.getRequestParameterMap();
		
		requestMap.entrySet().forEach(entry ->
		{
			System.out.println("Key : " + entry.getKey() + " Value : " + entry.getValue());
			if (entry.getKey().equals("analyserSiteId"))
			{
				clientSiteId = Integer.valueOf(entry.getValue());
			}
			if (entry.getKey().equals("status"))
			{
				status = Integer.valueOf(entry.getValue());
			}
		});
		
		if(status == 1){
			status = 0;
		}else{
			status = 1;
		}
		String result = service.changeClientSiteStatus(FacesUtils.getCurrentUserId(), Integer.valueOf(clientId), clientSiteId, status);
		System.out.println("Status Updated..."+result);
		this.generateReport();
	}

	public void chooseAnalyserWorkSiteLocation() {
		try {
			Map<String, Object> options = FacesUtils.createDialogOptions();
			Map<String, List<String>> parameters = new HashMap<String, List<String>>();

			List<String> clientIdsList = new ArrayList<String>();
			clientIdsList.add(clientId);

			parameters.put("clientId", clientIdsList);

			RequestContext.getCurrentInstance().openDialog(
					"dialog/analyser-work-site-location.xhtml", options,
					parameters);
		} catch (Exception ex) {
			ex.printStackTrace();
			System.err.println(ex.getLocalizedMessage());
			FacesMessage message = new FacesMessage(
					FacesMessage.SEVERITY_ERROR, "Error",
					"There was an error opening client site");
			FacesUtils.addMessage(message);
			return;
		}
	}

	public void onClientWorkSiteAdded(SelectEvent event) {
		try {

			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
					"Success", "Analyser client site added successfully");
			FacesContext.getCurrentInstance().addMessage(null, message);

			this.generateReport();

		} catch (Exception ex) {
			logger.debug("Exception while handling subcontract "
					+ ex.getMessage());
		}

	}

	/**
	 * @return the clientId
	 */
	public String getClientId() {
		return clientId;
	}

	/**
	 * @param clientId
	 *            the clientId to set
	 */
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	/**
	 * @return the action
	 */
	public String getAction() {
		return action;
	}

	/**
	 * @param action
	 *            the action to set
	 */
	public void setAction(String action) {
		this.action = action;
	}

	/**
	 * @return the reportData
	 */
	public List<Map<String, Object>> getReportData() {
		return reportData;
	}

	/**
	 * @param reportData
	 *            the reportData to set
	 */
	public void setReportData(List<Map<String, Object>> reportData) {
		this.reportData = reportData;
	}

	/**
	 * @return the searchString
	 */
	public String getSearchString() {
		return searchString;
	}

	/**
	 * @param searchString
	 *            the searchString to set
	 */
	public void setSearchString(String searchString) {
		this.searchString = searchString;
	}

	/**
	 * @return the filteredList
	 */
	public List<Map<String, Object>> getFilteredList() {
		return filteredList;
	}

	/**
	 * @param filteredList
	 *            the filteredList to set
	 */
	public void setFilteredList(List<Map<String, Object>> filteredList) {
		this.filteredList = filteredList;
	}

	public Integer getClientSiteId()
	{
		return clientSiteId;
	}

	public void setClientSiteId(Integer clientSiteId)
	{
		this.clientSiteId = clientSiteId;
	}

	public Integer getStatus()
	{
		return status;
	}

	public void setStatus(Integer status)
	{
		this.status = status;
	}

	/**
	 * @return the enableAdministrativeActions
	 */
	public Boolean getEnableAdministrativeActions()
	{
		return enableAdministrativeActions;
	}

	/**
	 * @param enableAdministrativeActions the enableAdministrativeActions to set
	 */
	public void setEnableAdministrativeActions(Boolean enableAdministrativeActions)
	{
		this.enableAdministrativeActions = enableAdministrativeActions;
	}

}
