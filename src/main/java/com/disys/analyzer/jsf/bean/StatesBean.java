package com.disys.analyzer.jsf.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import com.disys.analyzer.config.util.FacesUtils;
import com.disys.analyzer.model.dto.StatesAllDTO;
import com.disys.analyzer.service.StatesService;

@ManagedBean
@SessionScoped
public class StatesBean extends SpringBeanAutowiringSupport implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4209133766296292860L;

	private Logger logger = LoggerFactory.getLogger(StatesBean.class);
	
	private List<SelectItem> selectedState;
	String userId = FacesUtils.getCurrentUserId();
	
	@Autowired 
	private StatesService service;
	
	public StatesBean() 
	{
		System.out.println("StatesBean constructor called....");
		logger.info("StatesBean constructor called....");
	}
	/**
	 * @author Saravanan.Dhurairaj  
	 * getList is list method.
	 * It is used to get all statescode, statesName from States table
	 * Procedure used dbo.getAllstates
	 * Table used states
	 * 
	 */
	
	public List<SelectItem> getList() 
	{
		if (selectedState == null) 
		{
			selectedState = new ArrayList<SelectItem>();
			try
			{
				List<StatesAllDTO> states = service.getAllStatess(FacesUtils.getCurrentUserId());
		
				if (states != null)
				{
					for (StatesAllDTO s:states)
					{
						
						selectedState.add(new SelectItem(s.getStateCode(), s.getStateName()));
					}
				}
			}
			catch (Exception e)
			{
				System.out.println("Exception in StatesBean --> getList.");
				logger.debug("Exception in StatesBean --> getList.");
				e.printStackTrace();
			}
		}
		return selectedState;
	}
	/**
	 * @return the selectedState
	 */
	public List<SelectItem> getSelectedState() {
		return selectedState;
	}
	/**
	 * @param selectedState the selectedState to set
	 */
	public void setSelectedState(List<SelectItem> selectedState) {
		this.selectedState = selectedState;
	}

	
	
}
