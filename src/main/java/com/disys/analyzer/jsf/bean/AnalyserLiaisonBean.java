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
import javax.faces.event.ActionEvent;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.model.SelectItem;

import org.primefaces.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import org.springframework.web.jsf.FacesContextUtils;

import com.disys.analyzer.config.util.Constants;
import com.disys.analyzer.config.util.FacesMessageSeverity;
import com.disys.analyzer.config.util.FacesUtils;
import com.disys.analyzer.model.AnalyserLiaison;
import com.disys.analyzer.model.dto.AnalyserLiaisonDTO;
import com.disys.analyzer.model.dto.UserDTO;
import com.disys.analyzer.reports.util.ReportCriteria;
import com.disys.analyzer.service.AnalyserCommisionPersonService;
import com.disys.analyzer.service.AnalyserLiaisonService;
import com.disys.analyzer.service.OrganizationGroupService;

/**
 * @author Sajid
 * @since Oct 24, 2017
 */
@ManagedBean
@ViewScoped
public class AnalyserLiaisonBean extends SpringBeanAutowiringSupport implements Serializable
{
	private static final long serialVersionUID = 2301867273672822244L;
	private static final Logger logger = LoggerFactory.getLogger(AnalyserLiaisonBean.class);
	private AnalyserLiaison analyserLiaison;
	private AnalyserLiaison selectedAnalyserLiaison;
	private String buttonTitle;
	private List<Map<String, Object>> reportData;
	private List<Map<String, Object>> filteredList;
	private String searchString;
	private String liaisonID;
	private Integer status;
	private List<AnalyserLiaison> analyserLiaisonList;
	private List<AnalyserLiaison> analyserLiaisonFilterList;
	private List<SelectItem> analyserLiaisonLists;
	String userId = FacesUtils.getCurrentUserId();
	private String companyCode;
	
	@Autowired
	private AnalyserLiaisonService service;
	
	@Autowired
	private OrganizationGroupService organizationGroupService;
	
	@Autowired
	private Map<String, List<AnalyserLiaisonDTO>> analyserLiaisonDownBean;
	
	private List<UserDTO> activeUserList;
	
	@Autowired
	private AnalyserCommisionPersonService analyserCommisionPersonService;
	
	private Integer selectedValueIndex;
	
	public AnalyserLiaisonBean()
	{
		this.companyCode = Constants.DEFAULT_COMPANY_CODE;
		analyserLiaison = new AnalyserLiaison();
		selectedAnalyserLiaison = new AnalyserLiaison();
	}
	
	@PostConstruct
	public void init()
	{
		FacesContextUtils.getRequiredWebApplicationContext(FacesContext.getCurrentInstance()).getAutowireCapableBeanFactory().autowireBean(this);
		eraseFilter();
		//initActiveUserList();
	}
	
	public List<UserDTO> initActiveUserList()
	{
		if(companyCode == null || companyCode.isEmpty())
		{
			companyCode = Constants.DEFAULT_COMPANY_CODE;
		}
		activeUserList = service.spGetActiveUsersLiaison(FacesUtils.getCurrentUserId(), companyCode);
		System.out.println("AnalyserLiasion Active users list size is : "+activeUserList.size());
		return activeUserList;		
	}
	
	public void eraseFilter()
	{
		this.analyserLiaison = new AnalyserLiaison(1);
		this.selectedAnalyserLiaison = new AnalyserLiaison(1);
		this.searchString = "";
		buttonTitle = "Save";
		//this.filter();
	}
	
	public void filter()
	{		
		
		// ANALYSERLIAISON_LIST_REPORT.SQL={call "+ FacesUtils.SCHEMA_WIRELESS
		// +".spGetAnalyserAllLiaison('[0]','[1]','[2]','[3]')}
		
		String optionalParams = null;
		
		if (optionalParams == null)
		{
			optionalParams = "";
		}
		
		// always get the userid for security reasons
		
		if (searchString == null || searchString.trim().equals(""))
		{
			searchString = "ALL";
		}
				
		String sortOrder = "LiaisonName";
		
		// TODO for time being, set it to 0, change it after discussing it with
		// Mr.Tayyab
		String userList = "0";
		if(companyCode == null || companyCode.isEmpty())
		{
			companyCode = Constants.DEFAULT_COMPANY_CODE;
		}
		
		analyserLiaisonList = service.getAnalyserAllLiaison(FacesUtils.getCurrentUserId(), userList, sortOrder, searchString.trim(), companyCode);
		
		System.out.println("call wireless.spGetAnalyserAllLiaison('" + FacesUtils.getCurrentUserId() + "','" + userList + "','" + sortOrder + "','" + searchString.trim() + "','" +companyCode+"')");
		logger.info("Total list size is : " + analyserLiaisonList.size());		
	
	}
	
	public void save(ActionEvent actionEvent)
	{
		try
		{
			if (selectedAnalyserLiaison.getLiaisonName() == null || selectedAnalyserLiaison.getLiaisonName().trim().length() == 0)
			{
				FacesUtils.addGlobalMessage(FacesMessageSeverity.ERROR, "Select person name to save");
				return;
			}
			int actionType = 1;
			String infoMessage = "Analyser liaison person saved successfully";
			// TODO in case of update, send Liaison name as 2nd parameter.
			String result = service.addUpdateAnalyserLiaison(selectedAnalyserLiaison.getLiaisonName(), "", FacesUtils.getCurrentUserId(), actionType);
			System.out.println("Result is : " + result);
			
			if (result.equals("0"))
			{
				infoMessage = "Analyser Liaison was successfully created";
				logger.debug("Analyser saved successfully, list will be fetched again.");
				logger.debug("Analyser Liaison list cleared on save action.");
				analyserLiaisonDownBean.clear();
				initActiveUserList();
				System.out.println(infoMessage);
			}
			else if (result.equals("1"))
			{
				infoMessage = "Could not create or modify analyser Liaison -- General error ";
				System.out.println(infoMessage);
			}
			else if (result.equals("2"))
			{
				infoMessage = "Could not create Analyser Liaison -- analyser Liaison already exist";
				System.out.println(infoMessage);
			}
			else if (result.equals("3"))
			{
				infoMessage = "Could not delete or modify -- analyser Liaison exists ";
				System.out.println(infoMessage);
			}
			
			FacesUtils.addGlobalMessage(FacesMessageSeverity.INFO, infoMessage);
			eraseFilter();
		}
		catch (Exception e)
		{
			String errorMessage = "Error saving analyser commission person";
			FacesUtils.addGlobalMessage(FacesMessageSeverity.ERROR, errorMessage);
			logger.error(errorMessage);
		}
	}
	
	public void remove(AjaxBehaviorEvent event)
	{
		try
		{
			if (selectedAnalyserLiaison.getLiaisonID() != null)
			{
				service.delete(selectedAnalyserLiaison.getLiaisonID());
				String infoMessage = "Analyser Liaison delete successfully.";
				FacesUtils.addGlobalMessage(FacesMessageSeverity.INFO, infoMessage, selectedAnalyserLiaison.getLiaisonName());
				initActiveUserList();
			}
			else
			{
				String infoMessage = "Can delete Analyser Liaison";
				FacesUtils.addGlobalMessage(FacesMessageSeverity.ERROR, infoMessage, selectedAnalyserLiaison.getLiaisonName() + " Liaison id is null");
			}
			eraseFilter();
		}
		catch (Exception e)
		{
			String errorMessage = "Analyser Liaison couldn't be deleted.";
			FacesUtils.addGlobalMessage(FacesMessageSeverity.ERROR, errorMessage, e.getMessage());
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
			if (entry.getKey().equals("liaisonID"))
			{
				liaisonID = entry.getValue();
			}
			if (entry.getKey().equals("status"))
			{
				status = Integer.valueOf(entry.getValue());
			}
		});
		
		if (status == 1)
		{
			status = 0;
		}
		else
		{
			status = 1;
		}
		
		String result = service.changeAnalyserLiaisonStatus(FacesUtils.getCurrentUserId(), liaisonID, status);
		System.out.println("Status Updated..." + result);
		logger.debug("Analyser Liaison list cleared on Activate/De-activate action.");
		analyserLiaisonDownBean.clear();
		this.filter();
	}
	
	public void update(AjaxBehaviorEvent event)
	{
		buttonTitle = "Update";
	}
	
	public void clearInputForm(ActionEvent event)
	{
		buttonTitle = "Save";
		selectedAnalyserLiaison = new AnalyserLiaison();
		companyCode = Constants.DEFAULT_COMPANY_CODE;
	}
	
	/**
	 * @return the analyserLiaison
	 */
	public AnalyserLiaison getAnalyserLiaison()
	{
		return analyserLiaison;
	}
	
	/**
	 * @param analyserLiaison
	 *            the analyserLiaison to set
	 */
	public void setAnalyserLiaison(AnalyserLiaison analyserLiaison)
	{
		this.analyserLiaison = analyserLiaison;
	}
	
	public void selectAnalyserLiaisonFromDialog(AnalyserLiaison analyserLiaison)
	{
		RequestContext.getCurrentInstance().closeDialog(analyserLiaison);
	}
	
	/**
	 * @param service
	 *            the service to set
	 */
	public void setService(AnalyserLiaisonService service)
	{
		this.service = service;
	}
	
	/**
	 * @return the selectedAnalyserLiaison
	 */
	public AnalyserLiaison getSelectedAnalyserLiaison()
	{
		return selectedAnalyserLiaison;
	}
	
	/**
	 * @param selectedAnalyserLiaison
	 *            the selectedAnalyserLiaison to set
	 */
	public void setSelectedAnalyserLiaison(AnalyserLiaison selectedAnalyserLiaison)
	{
		this.selectedAnalyserLiaison = selectedAnalyserLiaison;
	}
	
	/**
	 * @return the buttonTitle
	 */
	public String getButtonTitle()
	{
		return buttonTitle;
	}
	
	/**
	 * @param buttonTitle
	 *            the buttonTitle to set
	 */
	public void setButtonTitle(String buttonTitle)
	{
		this.buttonTitle = buttonTitle;
	}
	
	/**
	 * @return the reportData
	 */
	public List<Map<String, Object>> getReportData()
	{
		return reportData;
	}
	
	/**
	 * @param reportData
	 *            the reportData to set
	 */
	public void setReportData(List<Map<String, Object>> reportData)
	{
		this.reportData = reportData;
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
	public List<Map<String, Object>> getFilteredList()
	{
		return filteredList;
	}
	
	/**
	 * @param filteredList
	 *            the filteredList to set
	 */
	public void setFilteredList(List<Map<String, Object>> filteredList)
	{
		this.filteredList = filteredList;
	}
	
	public String getLiaisonID()
	{
		return liaisonID;
	}
	
	public void setLiaisonID(String liaisonID)
	{
		this.liaisonID = liaisonID;
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
	 * @return the analyserLiaisonList
	 */
	public List<AnalyserLiaison> getAnalyserLiaisonList()
	{
		return analyserLiaisonList;
	}
	
	/**
	 * @param analyserLiaisonList
	 *            the analyserLiaisonList to set
	 */
	public void setAnalyserLiaisonList(List<AnalyserLiaison> analyserLiaisonList)
	{
		this.analyserLiaisonList = analyserLiaisonList;
	}
	
	/**
	 * @return the analyserLiaisonFilterList
	 */
	public List<AnalyserLiaison> getAnalyserLiaisonFilterList()
	{
		return analyserLiaisonFilterList;
	}
	
	/**
	 * @param analyserLiaisonFilterList
	 *            the analyserLiaisonFilterList to set
	 */
	public void setAnalyserLiaisonFilterList(List<AnalyserLiaison> analyserLiaisonFilterList)
	{
		this.analyserLiaisonFilterList = analyserLiaisonFilterList;
	}
	
	/**
	 * @return the activeUserList
	 */
	public List<UserDTO> getActiveUserList()
	{
		return activeUserList;
	}
	
	/**
	 * @param activeUserList
	 *            the activeUserList to set
	 */
	public void setActiveUserList(List<UserDTO> activeUserList)
	{
		this.activeUserList = activeUserList;
	}
	
	/**
	 * @return the selectedValueIndex
	 */
	public Integer getSelectedValueIndex()
	{
		return selectedValueIndex;
	}
	
	/**
	 * @param selectedValueIndex
	 *            the selectedValueIndex to set
	 */
	public void setSelectedValueIndex(Integer selectedValueIndex)
	{
		this.selectedValueIndex = selectedValueIndex;
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

	public List<SelectItem> getAnalyserLiaisonByCompanyCodeList(String compCode) 
	{
		if (analyserLiaisonLists == null) 
		{
			analyserLiaisonLists = new ArrayList<SelectItem>();
			try
			{
				List<AnalyserLiaisonDTO> analyserLiaisonList = service.getAnalyserLiaisonList(userId, compCode);
				if (analyserLiaisonList != null)
				{
					for (AnalyserLiaisonDTO c:analyserLiaisonList)
					{						
						analyserLiaisonLists.add(new SelectItem(c.getAnalyserLiaisonNameDescription(), c.getAnalyserLiaisonNameDescription()));
					}
				}
			}
			catch (Exception e)
			{
				System.out.println("Exception in AnalyserLiaisonBean --> getAnalyserLiaisonByCompanyCodeList.");
				logger.debug("Exception in AnalyserLiaisonBean --> getAnalyserLiaisonByCompanyCodeList.");
				e.printStackTrace();
			}
		}
		return analyserLiaisonLists;
	}
	
	public String getAnalyserLiaisonDescription(String compCode, String recordCode) 
	{
			List<AnalyserLiaisonDTO> analyserLiaisonDescriptionList = new ArrayList<AnalyserLiaisonDTO>();
			try
			{
				analyserLiaisonDescriptionList = service.getAnalyserLiaisonDescription(userId, compCode, recordCode);
			}
			catch (Exception e)
			{
				System.out.println("Exception in AnalyserLiaisonBean --> getAnalyserLiaisonDescription.");
				logger.debug("Exception in AnalyserLiaisonBean --> getAnalyserLiaisonDescription.");
				e.printStackTrace();
			}
			AnalyserLiaisonDTO analyserLiaisonDTO = analyserLiaisonDescriptionList.isEmpty()?null: analyserLiaisonDescriptionList.get(0);
			if(analyserLiaisonDTO != null) {
				return analyserLiaisonDTO.getAnalyserLiaisonNameDescription().isEmpty()? analyserLiaisonDTO.getAnalyserLiaisonNameDescription() : analyserLiaisonDescriptionList.get(0).getAnalyserLiaisonNameDescription();
			}
			return "";
	}
	
	public List<SelectItem> getAnalyserLiaisonLists() {
		return analyserLiaisonLists;
	}

	public void setAnalyserLiaisonLists(List<SelectItem> analyserLiaisonLists) {
		this.analyserLiaisonLists = analyserLiaisonLists;
	}
	public void onCompanyCodeChange(javax.faces.event.AjaxBehaviorEvent event) {
	}
	
}