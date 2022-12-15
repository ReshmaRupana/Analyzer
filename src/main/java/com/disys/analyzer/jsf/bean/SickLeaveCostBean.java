/**
 * 
 */
package com.disys.analyzer.jsf.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import org.springframework.web.jsf.FacesContextUtils;

import com.disys.analyzer.config.util.FacesMessageSeverity;
import com.disys.analyzer.config.util.FacesUtils;
import com.disys.analyzer.model.SickLeaveCost;
import com.disys.analyzer.model.dto.StatesDTO;
import com.disys.analyzer.service.AnalyserService;
import com.disys.analyzer.service.SickLeaveCostService;

/**
 * @author Sajid
 * @since Nov 15, 2019
 */
@ManagedBean
@ViewScoped
public class SickLeaveCostBean extends SpringBeanAutowiringSupport implements Serializable
{
	private static final long serialVersionUID = -7116434779894912673L;
	private Logger logger = LoggerFactory.getLogger(SickLeaveCostBean.class);
	@Autowired private SickLeaveCostService sickLeaveCostService;
	@Autowired private AnalyserService service;
	private List<SickLeaveCost> dataList;
	private List<SickLeaveCost> filteredList;
	private SickLeaveCost sickLeaveCost;
	private List<SelectItem> stateList;
	private Boolean showUpdateButton;
	private String oldZipCode;
	private Integer actionType;
	
	public SickLeaveCostBean()
	{
		
	}
	
	@PostConstruct
	public void init()
	{
		try
		{
			FacesContextUtils.getRequiredWebApplicationContext(FacesContext.getCurrentInstance()).getAutowireCapableBeanFactory().autowireBean(this);
			fillStateListValues();
			initilizeDefaultValues();
		}
		catch (BeansException e)
		{
			e.printStackTrace();
			displayMessage("Erro in Sick Leave Cost " + e.getMessage(), true);
		}
		catch (IllegalStateException e)
		{
			e.printStackTrace();
			displayMessage("Erro in Sick Leave Cost " + e.getMessage(), true);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			displayMessage("Erro in Sick Leave Cost " + e.getMessage(), true);
		}
	}
	
	public void clearForm()
	{
		try
		{
			initilizeDefaultValues();
		}
		catch (Exception e)
		{
			displayMessage("Erro in Sick Leave Cost " + e.getMessage(), true);
		}
		
	}
	
	public void addUpdateSickLeaveCost()
	{
		String msg = null;
		try
		{
			if (validateRecord(sickLeaveCost, (actionType == 1)))
			{
				if(this.sickLeaveCost.getIsPtoEligible() == null || this.sickLeaveCost.getIsPtoEligible().trim().length() == 0)
				{
					this.sickLeaveCost.setIsPtoEligible("YES");
				}
				this.sickLeaveCost.setWorksiteStateDescription(fetchWorksiteSateDescription(this.sickLeaveCost.getWorksiteStateCode()));
				String result = sickLeaveCostService.spAddUpdateSickLeaveCost(FacesUtils.getCurrentUserId(), this.sickLeaveCost, this.oldZipCode,
				        this.actionType);
				if (actionType == 1)
				{
					initilizeDefaultValues();
					msg = result.equalsIgnoreCase("0") ? "Successfully Save Sick Leave Cost" : "Cannot Save Sick Leave Cost";
				}
				else
				{
					this.dataList = sickLeaveCostService.sickLeaveData(FacesUtils.getCurrentUserId());
					msg = result.equalsIgnoreCase("0") ? "Successfully Update Sick Leave Cost" : "Cannot Update Sick Leave Cost";
				}
				displayMessage(msg, result.equalsIgnoreCase("1"));
			}
		}
		catch (Exception e)
		{
			msg = actionType == 1 ? "Error on save Sick Leave Cost" : "Error on update Sick Leave Cost";
			displayMessage(msg, true);
			e.printStackTrace();
		}
	}
	
	public void editSickLeaveCost(SickLeaveCost sickLeaveCost)
	{
		this.showUpdateButton = true;
		this.sickLeaveCost = new SickLeaveCost(sickLeaveCost);
		this.oldZipCode = this.sickLeaveCost.getZipCode();
		this.actionType = 2;
	}
	
	private void initilizeDefaultValues()
	{
		this.dataList = new ArrayList<SickLeaveCost>(0);
		this.sickLeaveCost = new SickLeaveCost();
		this.showUpdateButton = false;
		this.oldZipCode = "";
		this.actionType = 1;
		this.dataList = sickLeaveCostService.sickLeaveData(FacesUtils.getCurrentUserId());
	}
	
	private void fillStateListValues()
	{
		stateList = new ArrayList<SelectItem>(0);
		List<StatesDTO> statesDTOList = sickLeaveCostService.usStateData(FacesUtils.getCurrentUserId());
		if (statesDTOList != null && !statesDTOList.isEmpty())
		{
			for (StatesDTO statesDTO : statesDTOList)
			{
				stateList.add(new SelectItem(statesDTO.getStateCode(), statesDTO.getStateName()));
			}
		}
	}
	
	private boolean validateRecord(SickLeaveCost sickLeaveCost, boolean addMode)
	{
		if (sickLeaveCost.getWorksiteStateCode() == null || sickLeaveCost.getWorksiteStateCode().trim().length() == 0)
		{
			FacesUtils.addGlobalMessage(FacesMessageSeverity.ERROR, "Please select Worksite State Code");
			return false;
		}
		if (sickLeaveCost.getZipCode() == null || sickLeaveCost.getZipCode().trim().length() == 0)
		{
			FacesUtils.addGlobalMessage(FacesMessageSeverity.ERROR, "Please enter zip code");
			return false;
		}
		else if (sickLeaveCost.getZipCode().trim().length() != 5)
		{
			FacesUtils.addGlobalMessage(FacesMessageSeverity.ERROR, "Zip code must be equal to 5 characters");
			return false;
		}
		else
		{
			oldZipCode = addMode ? "" : oldZipCode == null ? "" : oldZipCode;
			Integer duplicate = sickLeaveCostService.spGetDuplicateZipCodeInSickLeaveCost(sickLeaveCost.getZipCode(), oldZipCode,
			        (addMode ? "A" : "U"), FacesUtils.getCurrentUserId());
			if (duplicate == 1)
			{
				FacesUtils.addGlobalMessage(FacesMessageSeverity.ERROR, "Zip code is duplicate");
				return false;
			}
		}
		if (sickLeaveCost.getCityName() == null || sickLeaveCost.getCityName().trim().length() == 0)
		{
			FacesUtils.addGlobalMessage(FacesMessageSeverity.ERROR, "Please enter city name");
			return false;
		}
		if (sickLeaveCost.getSickHoursCost() == null)
		{
			FacesUtils.addGlobalMessage(FacesMessageSeverity.ERROR, "Please enter sick hours cost");
			return false;
		}
		if (sickLeaveCost.getAnnualCap() == null)
		{
			FacesUtils.addGlobalMessage(FacesMessageSeverity.ERROR, "Please enter sick leave cap");
			return false;
		}
		
		if (sickLeaveCost.getPtoLimitToOverrideSick() == null)
		{
			FacesUtils.addGlobalMessage(FacesMessageSeverity.ERROR, "Please enter pto limit");
			return false;
		}
		else
		{
			try
			{
				Double value = sickLeaveCost.getPtoLimitToOverrideSick();
			}
			catch (NumberFormatException ne)
			{
				FacesUtils.addGlobalMessage(FacesMessageSeverity.ERROR, "Please enter valid pto limit");
				return false;
			}
		}
		if(sickLeaveCost.getPtoLimitToOverrideSick() < 0)
		{
			FacesUtils.addGlobalMessage(FacesMessageSeverity.ERROR, "Pto limit should be equal or greater than 0");
			return false;
		}
		return true;
	}
	
	private String fetchWorksiteSateDescription(String value)
	{
		String label = null;
		if (this.stateList != null && stateList.size() > 0)
		{
			for (SelectItem item : this.stateList)
			{
				if (item.getValue().toString().trim().equalsIgnoreCase(value.trim()))
				{
					label = item.getLabel();
				}
			}
		}
		return label;
	}
	
	private void displayMessage(String msg, boolean error)
	{
		FacesUtils.addGlobalMessage(error ? FacesMessageSeverity.ERROR : FacesMessageSeverity.INFO, msg);
	}
	
	public Logger getLogger()
	{
		return logger;
	}
	
	public void setLogger(Logger logger)
	{
		this.logger = logger;
	}
	
	public SickLeaveCostService getSickLeaveCostService()
	{
		return sickLeaveCostService;
	}
	
	public void setSickLeaveCostService(SickLeaveCostService sickLeaveCostService)
	{
		this.sickLeaveCostService = sickLeaveCostService;
	}
	
	public AnalyserService getService()
	{
		return service;
	}
	
	public void setService(AnalyserService service)
	{
		this.service = service;
	}
	
	public List<SickLeaveCost> getDataList()
	{
		return dataList;
	}
	
	public void setDataList(List<SickLeaveCost> dataList)
	{
		this.dataList = dataList;
	}
	
	public List<SickLeaveCost> getFilteredList()
	{
		return filteredList;
	}
	
	public void setFilteredList(List<SickLeaveCost> filteredList)
	{
		this.filteredList = filteredList;
	}
	
	public SickLeaveCost getSickLeaveCost()
	{
		return sickLeaveCost;
	}
	
	public void setSickLeaveCost(SickLeaveCost sickLeaveCost)
	{
		this.sickLeaveCost = sickLeaveCost;
	}
	
	public List<SelectItem> getStateList()
	{
		return stateList;
	}
	
	public void setStateList(List<SelectItem> stateList)
	{
		this.stateList = stateList;
	}
	
	public Boolean getShowUpdateButton()
	{
		return showUpdateButton;
	}
	
	public void setShowUpdateButton(Boolean showUpdateButton)
	{
		this.showUpdateButton = showUpdateButton;
	}
	
	public String getOldZipCode()
	{
		return oldZipCode;
	}
	
	public void setOldZipCode(String oldZipCode)
	{
		this.oldZipCode = oldZipCode;
	}
}