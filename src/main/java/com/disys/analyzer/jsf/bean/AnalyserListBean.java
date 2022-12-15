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
import javax.faces.event.AjaxBehaviorEvent;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import org.springframework.web.jsf.FacesContextUtils;

import com.disys.analyzer.config.util.Constants;
import com.disys.analyzer.config.util.FacesUtils;
import com.disys.analyzer.model.Analyser;
import com.disys.analyzer.model.dto.AnalyserDTO;
import com.disys.analyzer.service.AnalyserService;

/**
 * @author Sajid
 * @since Nov 13, 2018
 */
@ManagedBean
@ViewScoped
public class AnalyserListBean extends SpringBeanAutowiringSupport implements Serializable
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7157302311661156860L;
	private Logger logger = LoggerFactory.getLogger(AnalyserListBean.class);
	
	@Autowired
	private AnalyserService service;
	
	private Analyser analyser;
	
	private List<Analyser> analyserList;
	private List<Analyser> filteredList;
	
	private AnalyserDTO selectedAnalyser;
	
	private String searchString;
	
	private boolean developmentEnvironment;
	
	private Boolean enableAdministrativeActions;
	
	private String companyCodeLocal;
	
	private String enableCompanySpecificAddModifyPageURL;
	
	private String enableCompanySpecificViewPageURL;
	
	@PostConstruct
	public void init()
	{
		try
		{
			FacesContextUtils.getRequiredWebApplicationContext(FacesContext.getCurrentInstance()).getAutowireCapableBeanFactory().autowireBean(this);
			eraseFilter();
		}
		catch (Exception ex)
		{
			logger.debug(ex.getMessage());
		}
	}
	
	public AnalyserListBean()
	{
		FacesContext fc = FacesUtils.getFacesContext();
		ExternalContext externalContext = fc.getExternalContext();
		Map<String, String> requestMap = externalContext
				.getRequestParameterMap();
		requestMap.entrySet().forEach(entry ->
		{
			System.out.println("Key : " + entry.getKey() + " Value : " + entry.getValue());
			if (entry.getKey().equals("compCode"))
			{
				if(entry.getValue() == null || entry.getValue().isEmpty())
				{					
					companyCodeLocal = Constants.DEFAULT_COMPANY_CODE;
				}
				else
				{					
					companyCodeLocal = entry.getValue();
				}
				
			}});

		System.out.println("AnalyzerListBean constructor companyCode " +companyCodeLocal);
		logger.info("AnalyzerListBean constructor companyCode " +companyCodeLocal);
		if(companyCodeLocal == null || companyCodeLocal.trim().equals("") ){
			System.out.println("Company Code Null Set DISYS");
			logger.debug("Company Code Null Set DISYS");
			companyCodeLocal = Constants.DEFAULT_COMPANY_CODE;
			enableCompanySpecificAddModifyPageURL="analyser";
			enableCompanySpecificViewPageURL="analyser-view";
		}
		
		if(companyCodeLocal.equalsIgnoreCase("DISYS")){
			System.out.println("DISYS Page Enable URL");
			logger.debug("DISYS Page Enable URL");
			enableCompanySpecificAddModifyPageURL="analyser";
			enableCompanySpecificViewPageURL="analyser-view";
		}else if(companyCodeLocal.equalsIgnoreCase("Signature")){
			System.out.println("Signature Page Enable URL");
			logger.debug("Signature Page Enable URL");
			enableCompanySpecificAddModifyPageURL="analyser-sig";
			enableCompanySpecificViewPageURL="analyser-view-sig";
		}
		analyser = new Analyser();
		// developmentEnvironment = true; //developemnt
		developmentEnvironment = false; // production
		Integer roleId = FacesUtils.getCurrentUserRole();
		if (roleId == 2 || roleId == 3 || roleId == 4)
		{
			enableAdministrativeActions = true;
		}
		else
		{
			enableAdministrativeActions = false;
		}
	}
	
	public void filter()
	{
		if (searchString.equals("") || searchString == null)
		{
			searchString = "ALL";
		}
		
		analyserList = service.getAnalyserList(FacesUtils.getCurrentUserId(), "0", "null", searchString, companyCodeLocal);
		
		String procedureCall = "{call " + FacesUtils.SCHEMA_WIRELESS + ".spGetAnalyserList('" + FacesUtils.getCurrentUserId() + "', '0', 'null','" + searchString + "', '" + companyCodeLocal + "')";
		
		System.out.println("{call " + FacesUtils.SCHEMA_WIRELESS + ".spGetAnalyserList(userId, userList, orderBy, searchString, companyCodeLocal)");
		System.out.println(procedureCall);
		
		logger.debug("{call " + FacesUtils.SCHEMA_WIRELESS + ".spGetAnalyserList(userId, userList, orderBy, searchString, companyCodeLocal)");
		logger.debug(procedureCall);
		
		logger.info("Total list size is : " + analyserList.size());
		
		// logger.info("Total list size is : " + list.getSize());
		this.searchString = "";
	}
	
	public void eraseFilter()
	{
		this.searchString = "";
		this.filter();
	}
	
	public void onAnalyserApproval(SelectEvent event)
	{
		try
		{
			HttpServletRequest request = FacesUtils.getRequestObject();
			HttpSession session = request.getSession();
			FacesMessage message = (FacesMessage) session.getAttribute("message");
			
			FacesContext.getCurrentInstance().addMessage(null, message);
			this.filter();
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
	}
	
	public void onAnalyserTermination(SelectEvent event)
	{
		try
		{
			HttpServletRequest request = FacesUtils.getRequestObject();
			HttpSession session = request.getSession();
			FacesMessage message = (FacesMessage) session.getAttribute("message");
			
			FacesContext.getCurrentInstance().addMessage(null, message);
			this.filter();
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
	}
	
	public void pageSizeChangeListener(AjaxBehaviorEvent event)
	{
		this.filter();
	}
	
	public void viewAnalyserHistory()
	{
		Map<String, Object> options = FacesUtils.createDialogOptions();
		
		Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		String analyserId = (String) params.get("analyserId");
		String parentId = (String) params.get("parentId");
		Map<String, List<String>> parameters = new HashMap<String, List<String>>();
		List<String> analyserIdList = new ArrayList<String>();
		analyserIdList.add(analyserId);
		parameters.put("analyserId", analyserIdList);
		
		List<String> rejectedParentIdList = new ArrayList<String>(1);
		rejectedParentIdList.add(parentId);
		parameters.put("parentId", rejectedParentIdList);
		
		RequestContext.getCurrentInstance().openDialog("dialog/selected_analyser_history.xhtml", options, parameters);
	}
	
	// added by asim
	public void viewRejectAnalyserDialog()
	{
		Map<String, Object> options = FacesUtils.createDialogOptions();
		Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		String rejectedAnalyserId = (String) params.get("analyserId");
		String rejectedParentId = (String) params.get("parentId");
		Map<String, List<String>> parameters = new HashMap<String, List<String>>();
		
		List<String> analyserIdList = new ArrayList<String>(1);
		analyserIdList.add(rejectedAnalyserId);
		parameters.put("analyserId", analyserIdList);
		
		List<String> rejectedParentIdList = new ArrayList<String>(1);
		rejectedParentIdList.add(rejectedParentId);
		parameters.put("parentId", rejectedParentIdList);
		RequestContext.getCurrentInstance().openDialog("dialog/selected_analyzer_reject.xhtml", options, parameters);
	}
	
	public void onAnalyserRejection(SelectEvent event)
	{
		try
		
		{
			HttpServletRequest request = FacesUtils.getRequestObject();
			HttpSession session = request.getSession();
			FacesMessage message = (FacesMessage) session.getAttribute("message");
			FacesContext.getCurrentInstance().addMessage(null, message);
			this.filter();
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
	}
	
	public void terminateAnalyser()
	{
		Map<String, Object> options = FacesUtils.createDialogOptions();
		
		Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		String analyserId = (String) params.get("analyserId");
		String companyCode = (String) params.get("compCode");
		
		Map<String, List<String>> parameters = new HashMap<String, List<String>>();
		List<String> analyserIdList = new ArrayList<String>();
		analyserIdList.add(analyserId);
		parameters.put("analyserId", analyserIdList);
		
		List<String> updateList = new ArrayList<String>();
		updateList.add("false");
		parameters.put("update", updateList);
		
		List<String> companyCodeList = new ArrayList<String>();
		companyCodeList.add(companyCode);
		parameters.put("companyCode", companyCodeList);
		System.out.println("AnalyzerListBean TerminateAnalyser CompanyCode " +companyCodeLocal);
		logger.info("AnalyzerListBean TerminateAnalyser CompanyCode " +companyCodeLocal);
		
		RequestContext.getCurrentInstance().openDialog("dialog/analyser-terminate.xhtml", options, parameters);
	}
	
	public void updateTerminateAnalyser()
	{
		Map<String, Object> options = FacesUtils.createDialogOptions();
		
		Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		String analyserId = (String) params.get("analyserId");
		String companyCode = (String) params.get("compCode");
		
		Map<String, List<String>> parameters = new HashMap<String, List<String>>();
		List<String> analyserIdList = new ArrayList<String>();
		analyserIdList.add(analyserId);
		parameters.put("analyserId", analyserIdList);
		

		List<String> updateList = new ArrayList<String>();
		updateList.add("true");
		parameters.put("update", updateList);
		
		List<String> companyCodeList = new ArrayList<String>();
		companyCodeList.add(companyCode);
		parameters.put("companyCode", companyCodeList);
		
		RequestContext.getCurrentInstance().openDialog("dialog/analyser-terminate.xhtml", options, parameters);
	}
	
	public void rehireAnalyser()
	{
		FacesMessage message = null;
		try
		{
			Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
			String analyserId = (String) params.get("analyserId");
			
			if (analyserId != null)
			{
				if (analyserId.trim().length() != 0)
				{
					
					AnalyserDTO analyser = new AnalyserDTO();
					String result = rehireProcess(analyser, analyserId, 5);
					
					if (result.equals("0"))
					{
						message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Success", "Rehired Successfully");
					}
					else
					{
						message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Failure", "Rehire operation failed");
					}
					FacesUtils.getFacesContext().addMessage(null, message);
					
					this.filter();
				}
			}
			else
			{
				message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Failure", "You haven't select an analyser.");
				FacesUtils.getFacesContext().addMessage(null, message);
			}
			
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
			message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Failure", "There was a problem.");
			FacesUtils.getFacesContext().addMessage(null, message);
		}
	}
	
	private String rehireProcess(AnalyserDTO analyser, String analyserId, Integer actionType)
	{
		logger.debug("About to rehire analyser ...");
		
		analyser.setAnalyserId(analyserId);
		analyser.setClientId("0");
		analyser.setClientAddressId(0);
		analyser.setClientSiteId(0);
		
		String status = service.rehireAnalyser(analyser, FacesUtils.getCurrentUserId());
		System.out.println("Rehired Status : " + status);
		return status;
	}
	
	public void viewAnalyserCommissionHistory()
	{
		Map<String, Object> options = FacesUtils.createDialogOptions();
		
		Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		String analyserId = (String) params.get("analyserId");
		
		Map<String, List<String>> parameters = new HashMap<String, List<String>>();
		List<String> analyserIdList = new ArrayList<String>();
		analyserIdList.add(analyserId);
		parameters.put("analyserId", analyserIdList);
		
		RequestContext.getCurrentInstance().openDialog("dialog/selected_analyser_commission_history.xhtml", options, parameters);
		
	}
	
	/**
	 * @return the analyser
	 */
	public Analyser getAnalyser()
	{
		return analyser;
	}
	
	/**
	 * @param analyser
	 *            the analyser to set
	 */
	public void setAnalyser(Analyser analyser)
	{
		this.analyser = analyser;
	}
	
	/**
	 * @return the analyserList
	 */
	public List<Analyser> getAnalyserList()
	{
		return analyserList;
	}
	
	/**
	 * @param analyserList
	 *            the analyserList to set
	 */
	public void setAnalyserList(List<Analyser> analyserList)
	{
		this.analyserList = analyserList;
	}
	
	/**
	 * @return the selectedAnalyser
	 */
	public AnalyserDTO getSelectedAnalyser()
	{
		return selectedAnalyser;
	}
	
	/**
	 * @param selectedAnalyser
	 *            the selectedAnalyser to set
	 */
	public void setSelectedAnalyser(AnalyserDTO selectedAnalyser)
	{
		this.selectedAnalyser = selectedAnalyser;
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
	public List<Analyser> getFilteredList()
	{
		return filteredList;
	}
	
	/**
	 * @param filteredList
	 *            the filteredList to set
	 */
	public void setFilteredList(List<Analyser> filteredList)
	{
		this.filteredList = filteredList;
	}
	
	/**
	 * @return the developmentEnvironment
	 */
	public boolean isDevelopmentEnvironment()
	{
		return developmentEnvironment;
	}
	
	/**
	 * @param developmentEnvironment
	 *            the developmentEnvironment to set
	 */
	public void setDevelopmentEnvironment(boolean developmentEnvironment)
	{
		this.developmentEnvironment = developmentEnvironment;
	}
	
	public AnalyserService getService()
	{
		return service;
	}
	
	public void setService(AnalyserService service)
	{
		this.service = service;
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
	 * @return the companyCodeLocal
	 */
	public String getCompanyCodeLocal() {
		return companyCodeLocal;
	}
	/**
	 * @param companyCodeLocal
	 *            the companyCodeLocal to set
	 */
	public void setCompanyCodeLocal(String companyCodeLocal) {
		this.companyCodeLocal = companyCodeLocal;
	}
	/**
	 * @return the enableCompanySpecificAddModifyPageURL
	 */
	public String getEnableCompanySpecificAddModifyPageURL() {
		return enableCompanySpecificAddModifyPageURL;
	}
	/**
	 * @param enableCompanySpecificAddModifyPageURL
	 *            the enableCompanySpecificAddModifyPageURL to set
	 */
	public void setEnableCompanySpecificAddModifyPageURL(String enableCompanySpecificAddModifyPageURL) {
		this.enableCompanySpecificAddModifyPageURL = enableCompanySpecificAddModifyPageURL;
	}
	/**
	 * @return the enableCompanySpecificViewPageURL
	 */
	public String getEnableCompanySpecificViewPageURL() {
		return enableCompanySpecificViewPageURL;
	}
	/**
	 * @param enableCompanySpecificViewPageURL
	 *            the enableCompanySpecificViewPageURL to set
	 */
	public void setEnableCompanySpecificViewPageURL(String enableCompanySpecificViewPageURL) {
		this.enableCompanySpecificViewPageURL = enableCompanySpecificViewPageURL;
	}
	
	
}