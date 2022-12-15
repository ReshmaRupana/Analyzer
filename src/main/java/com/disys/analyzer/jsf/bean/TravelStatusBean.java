package com.disys.analyzer.jsf.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import com.disys.analyzer.config.util.FacesUtils;
import com.disys.analyzer.model.dto.TravelStatusDTO;
import com.disys.analyzer.service.TravelStatusService;

@ManagedBean
@ViewScoped
public class TravelStatusBean extends SpringBeanAutowiringSupport implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4209133766296292860L;

	private Logger logger = LoggerFactory.getLogger(TravelStatusBean.class);
	
	private List<SelectItem> travelStatusList;
	String userId = FacesUtils.getCurrentUserId();
	
	@Autowired 
	private TravelStatusService service;
	
	public TravelStatusBean() 
	{
		System.out.println("TravelStatusBean constructor called....");
		logger.info("TravelStatusBean constructor called....");
	}
	
	public List<SelectItem> getTravelStatusByCompanyCodeList(String compCode) 
	{
		if (travelStatusList == null) 
		{
			travelStatusList = new ArrayList<SelectItem>();
			try
			{
				List<TravelStatusDTO> travelStatusLists = service.getTravelStatusList(userId, compCode);
				if (travelStatusLists != null)
				{
					for (TravelStatusDTO c:travelStatusLists)
					{						
						travelStatusList.add(new SelectItem(c.getTravelStatusCode(), c.getTravelStatusDescription()));
					}
				}
			}
			catch (Exception e)
			{
				System.out.println("Exception in TravelStatusBean --> getTravelStatusByCompanyCodeList.");
				logger.debug("Exception in TravelStatusBean --> getTravelStatusByCompanyCodeList.");
				e.printStackTrace();
			}
		}
		return travelStatusList;
	}
	
	public String getTravelStatusDescription(String compCode, String recordCode) 
	{
			List<TravelStatusDTO> travelStatusDescriptionList = new ArrayList<TravelStatusDTO>();
			try
			{
				travelStatusDescriptionList = service.getTravelStatusDescription(userId, compCode, recordCode);
			}
			catch (Exception e)
			{
				System.out.println("Exception in TravelStatusBean --> getTravelStatusDescription.");
				logger.debug("Exception in TravelStatusBean --> getTravelStatusDescription.");
				e.printStackTrace();
			}
			TravelStatusDTO travelStatusDTO = travelStatusDescriptionList.isEmpty()?null: travelStatusDescriptionList.get(0);
			if(travelStatusDTO != null) {
				return travelStatusDTO.getTravelStatusDescription().isEmpty()? travelStatusDTO.getTravelStatusCode() : travelStatusDescriptionList.get(0).getTravelStatusDescription();
			}
			return "";
	}

	/**
	 * @return the travelStatusList
	 */
	public List<SelectItem> getTravelStatusList() {
		return travelStatusList;
	}
	
	/**
	 * @param travelStatusList the travelStatusList to set
	 */
	public void setTravelStatusList(List<SelectItem> travelStatusList) {
		this.travelStatusList = travelStatusList;
	}

	
}
