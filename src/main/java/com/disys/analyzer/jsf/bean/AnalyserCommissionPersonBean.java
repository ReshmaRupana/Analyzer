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
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import org.springframework.web.jsf.FacesContextUtils;

import com.disys.analyzer.config.util.Constants;
import com.disys.analyzer.config.util.FacesMessageSeverity;
import com.disys.analyzer.config.util.FacesUtils;
import com.disys.analyzer.model.AnalyserCommisionPerson;
import com.disys.analyzer.model.dto.AnalyserCommisionPersonDTO;
import com.disys.analyzer.model.dto.UserDTO;
import com.disys.analyzer.service.AnalyserCommisionPersonService;

/**
 * @author Sajid
 * @since Dec 2, 2018
 */
@ManagedBean
@ViewScoped
public class AnalyserCommissionPersonBean extends SpringBeanAutowiringSupport implements Serializable
{
	private static final long serialVersionUID = -2369732243308919916L;
	private Logger logger = LoggerFactory.getLogger(getClass());
	private List<AnalyserCommisionPerson> list;
	private List<AnalyserCommisionPerson> filteredList;
	private List<UserDTO> activeUserList;
	private AnalyserCommisionPerson person;
	private AnalyserCommisionPerson selectedPerson;
	private String searchString;
	private String buttonTitle;
	private boolean showUpdateButton;
	private String personName;
	private Integer status;
	private String updatedBy;
	
	private String userId;
	private String action;
	private List<SelectItem> commisionPersonList;
	private List<SelectItem> mdCommisionPersonList;
	private List<SelectItem> aeCommisionPersonList;
	private List<SelectItem> recCommisionPersonList;
	private List<SelectItem> mspCommisionPersonList;
	@Autowired private AnalyserCommisionPersonService service;
	private String companyCode;
	
	@Autowired
	Map<String, List<AnalyserCommisionPersonDTO>>	mdCommissionPersonDropDownBean;
	
	@Autowired
	Map<String, List<AnalyserCommisionPersonDTO>>	mspClientPartnerDropDownBean;
	
	@Autowired
	Map<String, List<AnalyserCommisionPersonDTO>> commissionPersonDropDownBean;
	
	private void clearDropDownBeans() {
		mdCommissionPersonDropDownBean.clear();
		mspClientPartnerDropDownBean.clear();
		commissionPersonDropDownBean.clear();
	}
	
	@PostConstruct
	public void init()
	{
		try
		{
			FacesContextUtils.getRequiredWebApplicationContext(FacesContext.getCurrentInstance()).getAutowireCapableBeanFactory().autowireBean(this);
		}
		catch (Exception ex)
		{
			logger.debug(ex.getMessage());
		}
	}
	
	public AnalyserCommissionPersonBean()
	{
		logger.debug("In AnalyserCommissionPersonBean()");
		this.companyCode = Constants.DEFAULT_COMPANY_CODE;
		
		FacesContext fc = FacesUtils.getFacesContext();
		ExternalContext externalContext = fc.getExternalContext();
		Map<String, String> requestMap = externalContext.getRequestParameterMap();
		
		requestMap.entrySet().forEach(entry ->
		{
			System.out.println("Key : " + entry.getKey() + " Value : " + entry.getValue());
			if (entry.getKey().equals("userId"))
			{
				userId = entry.getValue();
			}
			else if (entry.getKey().equals("action"))
			{
				action = entry.getValue();
			}
		});
		
		if(userId != null){
			//get selected person from DB
			selectedPerson = service.getAnalyserCommissionPerson(FacesUtils.getCurrentUserId(), userId);
			if(selectedPerson != null){
				System.out.println("Selected person code is : "+selectedPerson.getAdpCode());
			}
		} else {
			person = new AnalyserCommisionPerson();
			selectedPerson = new AnalyserCommisionPerson();
			list = new ArrayList<AnalyserCommisionPerson>();
			
			eraseFilter();
			//initActiveUserList();	//To remove extra procedure call from Analyzer page
		}
		
		showUpdateButton = false;
	}
	
	public List<UserDTO> initActiveUserList()	//To remove extra procedure call from Analyzer page
	{
		if(companyCode == null || companyCode.isEmpty())
		{
			companyCode = Constants.DEFAULT_COMPANY_CODE;
		}
		activeUserList = service.spGetActiveUsers(FacesUtils.getCurrentUserId(), companyCode);
		System.out.println("AnalyserCommissionPerson Active users list size is : "+activeUserList.size());
		return activeUserList;	//To remove extra procedure call from Analyzer page
	}
	
	public void eraseFilter()
	{
		// TODO get current user from the session and get organization id
		person = new AnalyserCommisionPerson();
		selectedPerson = new AnalyserCommisionPerson();
		list = new ArrayList<AnalyserCommisionPerson>();
		// buttonTitle = "Save";
		searchString = "";
		showUpdateButton = false;
		//filter();	//To remove extra procedure call from Analyzer page
	}
	
	public void filter()
	{
		list = new ArrayList<AnalyserCommisionPerson>();
		
		String userId = FacesUtils.getCurrentUserId();
		String userList = "0";
		String orderBy = "PersonName";
		try
		{
			if (searchString == null || searchString.equals(""))
			{
				searchString = "ALL";
			}
		}
		catch (NullPointerException ex)
		{
			searchString = "ALL";
		}
		if(companyCode == null || companyCode.isEmpty())
		{
			companyCode = Constants.DEFAULT_COMPANY_CODE;
		}
		list = service.getCommissionUsersList(userId, userList, orderBy, searchString, companyCode);
		logger.info("Total list size is : " + list.size());
	}
	
	public void save(ActionEvent actionEvent)
	{
		try
		{
			if(selectedPerson.getPersonName() == null || selectedPerson.getPersonName().trim().length() == 0)
			{
				FacesUtils.addGlobalMessage(FacesMessageSeverity.ERROR, "Select person name to save");
				return;
			}
			
			int actionType = 1;
			
			String result = service.addUpdateAnalyserComm(selectedPerson.getPersonName(), "", FacesUtils.getCurrentUserId(), actionType);
			System.out.println("Result is : " + result);
			
			String infoMessage = "Analyser commission person saved successfully";
			
			if (result.equals("0"))
			{
				infoMessage = "Analyser commission person was successfully created";
				initActiveUserList();
				System.out.println(infoMessage);
			}
			else if (result.equals("1"))
			{
				infoMessage = "Could not create or modify Analyser commission person -- General error ";
				System.out.println(infoMessage);
			}
			else if (result.equals("2"))
			{
				infoMessage = "Could not create Analyser -- Analyser commission person already exist";
				System.out.println(infoMessage);
			}
			else if (result.equals("3"))
			{
				infoMessage = "Could not delete or modify -- Analyser commission person exists ";
				System.out.println(infoMessage);
			}
			
			FacesUtils.addGlobalMessage(FacesMessageSeverity.INFO, infoMessage);
			eraseFilter();
			
			logger.debug("Clear dropdown beans on save Analyser Commission Person Bean");
			System.out.println("Clear dropdown beans on save Analyser Commission Person Bean");
			clearDropDownBeans();
		}
		catch (Exception e)
		{
			String errorMessage = "Error saving analyser commission person";
			FacesUtils.addGlobalMessage(FacesMessageSeverity.ERROR, errorMessage);
			logger.error(errorMessage);
		}
	}
	
	public void discardChanges()
	{
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Analyzer commission person changes discarded", "Changes discarded!");
		FacesUtils.redirect("/protected/analyser-commission-person.xhtml", message);
	}
	
	public void update()
	{
		try
		{
			/*
			 * 	@varLoggedOnUserID VARCHAR(50),
				@varCommissionPersonName Varchar(50),
				@varCommissionPersonUserId Varchar(100),
				@varRole Varchar(10) = '',
				@varClassification Varchar(25) = ''
				@varCompanyCode Varchar(25) = ''
			 */
			
			if(selectedPerson.getPersonName() == null || selectedPerson.getPersonName().trim().length() == 0)
			{
				FacesUtils.addGlobalMessage(FacesMessageSeverity.ERROR, "Select person name to update");
				return;
			}
			if(selectedPerson.getCompanyCode() == null || selectedPerson.getCompanyCode().isEmpty())
			{
				selectedPerson.setCompanyCode(Constants.DEFAULT_COMPANY_CODE);
			}
			
			
			Boolean result = service.updateAnalyserCommissionPerson(FacesUtils.getCurrentUserId(), selectedPerson.getPersonName(), selectedPerson.getId().getUserId(),
					selectedPerson.getCommissionPersonRole(), selectedPerson.getCommissionPersonClassification(), selectedPerson.getCompanyCode());
			System.out.println("Result is : " + result);
			
			logger.debug("Clear dropdown beans on update Analyser Commission Person Bean");
			System.out.println("Clear dropdown beans on update Analyser Commission Person Bean");
			clearDropDownBeans();
			
			FacesMessage message = null;
			if (result)
			{
				message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Analyzer Commission Person updated", "Operation is successfully completed!");
			}
			else
			{
				message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Couldn't update", "Operation not successfully!");
			}
				
			FacesUtils.redirect("/protected/analyser-commission-person.xhtml", message);
		}
		catch (Exception e)
		{
			String errorMessage = "Error updating analyser commission person";
			FacesUtils.addGlobalMessage(FacesMessageSeverity.ERROR, errorMessage);
			logger.error(errorMessage);
		}
	}
	
	public void editCommissionPerson(AnalyserCommisionPerson analyserCommisionPerson)
	{
		logger.debug("Edit commission person : " + person.getId());
		/*
		 * String id = FacesContext.getCurrentInstance().getExternalContext().
		 * getRequestParameterMap().get("id"); userRole =
		 * roleRepository.findOne(Long.valueOf(id));
		 */
		// userRole = role;
		showUpdateButton = true;
		this.selectedPerson.setPersonName(analyserCommisionPerson.getPersonName());
	}
	
	public void changeStatus()
	{
		
		FacesContext fc = FacesUtils.getFacesContext();
		ExternalContext externalContext = fc.getExternalContext();
		Map<String, String> requestMap = externalContext.getRequestParameterMap();
		requestMap.entrySet().forEach(entry ->
		{
			System.out.println("Key : " + entry.getKey() + " Value : " + entry.getValue());
			if (entry.getKey().equals("personName"))
			{
				personName = entry.getValue();
			}
			if (entry.getKey().equals("status"))
			{
				status = Integer.valueOf(entry.getValue());
			}
			if (entry.getKey().equals("updatedBy"))
			{
				updatedBy = entry.getValue();
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
		
		String result = service.changeCommissionPersonStatus(FacesUtils.getCurrentUserId(), updatedBy, personName, status);
		System.out.println("Status Updated..." + result);
		logger.debug("Clear dropdown beans on change status Analyser Commission Person Bean");
		System.out.println("Clear dropdown beans on change status Analyser Commission Person Bean");
		clearDropDownBeans();
		// write a procedure to update status
		this.filter();
	}
	
	public void clearInputForm()
	{
		buttonTitle = "Save";
		showUpdateButton = false;
		person = new AnalyserCommisionPerson();
		selectedPerson = new AnalyserCommisionPerson();
		companyCode = Constants.DEFAULT_COMPANY_CODE;
	}
	
	/**
	 * @return the list
	 */
	public List<AnalyserCommisionPerson> getList()
	{
		return list;
	}
	
	/**
	 * @param list
	 *            the list to set
	 */
	public void setList(List<AnalyserCommisionPerson> list)
	{
		this.list = list;
	}
	
	/**
	 * @return the person
	 */
	public AnalyserCommisionPerson getPerson()
	{
		return person;
	}
	
	/**
	 * @param person
	 *            the person to set
	 */
	public void setPerson(AnalyserCommisionPerson person)
	{
		this.person = person;
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
	 * @return the selectedPerson
	 */
	public AnalyserCommisionPerson getSelectedPerson()
	{
		return selectedPerson;
	}
	
	/**
	 * @param selectedPerson
	 *            the selectedPerson to set
	 */
	public void setSelectedPerson(AnalyserCommisionPerson selectedPerson)
	{
		this.selectedPerson = selectedPerson;
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
	public List<AnalyserCommisionPerson> getFilteredList()
	{
		return filteredList;
	}
	
	/**
	 * @param filteredList
	 *            the filteredList to set
	 */
	public void setFilteredList(List<AnalyserCommisionPerson> filteredList)
	{
		this.filteredList = filteredList;
	}
	
	public String getPersonName()
	{
		return personName;
	}
	
	public void setPersonName(String personName)
	{
		this.personName = personName;
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
	 * @return the updatedBy
	 */
	public String getUpdatedBy()
	{
		return updatedBy;
	}
	
	/**
	 * @param updatedBy
	 *            the updatedBy to set
	 */
	public void setUpdatedBy(String updatedBy)
	{
		this.updatedBy = updatedBy;
	}
	
	public List<UserDTO> getActiveUserList()
	{
		return activeUserList;
	}
	
	public void setActiveUserList(List<UserDTO> activeUserList)
	{
		this.activeUserList = activeUserList;
	}

	/**
	 * @return the userId
	 */
	public String getUserId()
	{
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(String userId)
	{
		this.userId = userId;
	}

	/**
	 * @return the action
	 */
	public String getAction()
	{
		return action;
	}

	/**
	 * @param action the action to set
	 */
	public void setAction(String action)
	{
		this.action = action;
	}
	
	public List<SelectItem> getMDCommissionPersonList(String companyCode) 
	{
		if (mdCommisionPersonList == null) 
		{
			mdCommisionPersonList = new ArrayList<SelectItem>();
			try
			{
				List<AnalyserCommisionPersonDTO> mdCommisionPersonLists = service.getMDCommissionPersonList(FacesUtils.getCurrentUserId(), companyCode);
				if (mdCommisionPersonLists != null)
				{
					for (AnalyserCommisionPersonDTO c:mdCommisionPersonLists)
					{						
						mdCommisionPersonList.add(new SelectItem(c.getPersonName(), c.getPersonName()));
					}
				}
			}
			catch (Exception e)
			{
				System.out.println("Exception in AnalyserCommissionPersonBean --> getMDCommissionPersonList.");
				logger.debug("Exception in AnalyserCommissionPersonBean --> getMDCommissionPersonList.");
				e.printStackTrace();
			}
		}
		return mdCommisionPersonList;
	}
	
	public List<SelectItem> getAECommissionPersonList(String companyCode) 
	{
		if (aeCommisionPersonList == null) 
		{
			aeCommisionPersonList = new ArrayList<SelectItem>();
			try
			{
				List<AnalyserCommisionPersonDTO> aeCommisionPersonLists = service.getAECommissionPersonList(FacesUtils.getCurrentUserId(), companyCode);
				if (aeCommisionPersonLists != null)
				{
					for (AnalyserCommisionPersonDTO c:aeCommisionPersonLists)
					{						
						aeCommisionPersonList.add(new SelectItem(c.getPersonName(), c.getPersonName()));
					}
				}
			}
			catch (Exception e)
			{
				System.out.println("Exception in AnalyserCommissionPersonBean --> getAECommissionPersonList.");
				logger.debug("Exception in AnalyserCommissionPersonBean --> getAECommissionPersonList.");
				e.printStackTrace();
			}
		}
		return aeCommisionPersonList;
	}

	public List<SelectItem> getRecruiterCommissionPersonList(String companyCode) 
	{
		if (recCommisionPersonList == null) 
		{
			recCommisionPersonList = new ArrayList<SelectItem>();
			try
			{
				List<AnalyserCommisionPersonDTO> recCommisionPersonLists = service.getRecruiterCommissionPersonList(FacesUtils.getCurrentUserId(), companyCode);
				if (recCommisionPersonLists != null)
				{
					for (AnalyserCommisionPersonDTO c:recCommisionPersonLists)
					{						
						recCommisionPersonList.add(new SelectItem(c.getPersonName(), c.getPersonName()));
					}
				}
			}
			catch (Exception e)
			{
				System.out.println("Exception in AnalyserCommissionPersonBean --> getRecruiterCommissionPersonList.");
				logger.debug("Exception in AnalyserCommissionPersonBean --> getRecruiterCommissionPersonList.");
				e.printStackTrace();
			}
		}
		return recCommisionPersonList;
	}
	
	public List<SelectItem> getAllCommissionPersonList(String companyCode) 
	{
		if (commisionPersonList == null) 
		{
			commisionPersonList = new ArrayList<SelectItem>();
			try
			{
				List<AnalyserCommisionPersonDTO> commisionPersonLists = service.getAllCommissionPersonList(FacesUtils.getCurrentUserId(), companyCode);
				if (commisionPersonLists != null)
				{
					for (AnalyserCommisionPersonDTO c:commisionPersonLists)
					{						
						commisionPersonList.add(new SelectItem(c.getPersonName(), c.getPersonName()));
					}
				}
			}
			catch (Exception e)
			{
				System.out.println("Exception in AnalyserCommissionPersonBean --> getAllCommissionPersonList .");
				logger.debug("Exception in AnalyserCommissionPersonBean --> getAllCommissionPersonList .");
				e.printStackTrace();
			}
		}
		return commisionPersonList;
	}
	
	public List<SelectItem> getMSPCommissionPersonList(String companyCode) 
	{
		if (mspCommisionPersonList == null) 
		{
			mspCommisionPersonList = new ArrayList<SelectItem>();
			try
			{
				List<AnalyserCommisionPersonDTO> mspCommisionPersonLists = service.getMSPCommissionPersonList(FacesUtils.getCurrentUserId(), companyCode);
				if (mspCommisionPersonLists != null)
				{
					for (AnalyserCommisionPersonDTO c:mspCommisionPersonLists)
					{						
						mspCommisionPersonList.add(new SelectItem(c.getPersonName(), c.getPersonName()));
					}
				}
			}
			catch (Exception e)
			{
				System.out.println("Exception in AnalyserCommissionPersonBean --> getMSPCommissionPersonList.");
				logger.debug("Exception in AnalyserCommissionPersonBean --> getMSPCommissionPersonList.");
				e.printStackTrace();
			}
		}
		return mspCommisionPersonList;
	}
	
	public List<SelectItem> getCommisionPersonList() {
		return commisionPersonList;
	}

	public void setCommisionPersonList(List<SelectItem> commisionPersonList) {
		this.commisionPersonList = commisionPersonList;
	}

	public List<SelectItem> getMdCommisionPersonList() {
		return mdCommisionPersonList;
	}

	public void setMdCommisionPersonList(List<SelectItem> mdCommisionPersonList) {
		this.mdCommisionPersonList = mdCommisionPersonList;
	}

	public List<SelectItem> getAeCommisionPersonList() {
		return aeCommisionPersonList;
	}

	public void setAeCommisionPersonList(List<SelectItem> aeCommisionPersonList) {
		this.aeCommisionPersonList = aeCommisionPersonList;
	}

	public List<SelectItem> getRecCommisionPersonList() {
		return recCommisionPersonList;
	}

	public void setRecCommisionPersonList(List<SelectItem> recCommisionPersonList) {
		this.recCommisionPersonList = recCommisionPersonList;
	}

	public List<SelectItem> getMspCommisionPersonList() {
		return mspCommisionPersonList;
	}

	public void setMspCommisionPersonList(List<SelectItem> mspCommisionPersonList) {
		this.mspCommisionPersonList = mspCommisionPersonList;
	}

	public String getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}
	
	public void onCompanyCodeChange(javax.faces.event.AjaxBehaviorEvent event) {
	}

}
