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
import com.disys.analyzer.model.dto.CoSellStatusDTO;
import com.disys.analyzer.service.CoSellStatusService;

@ManagedBean
@ViewScoped
public class CoSellStatusBean extends SpringBeanAutowiringSupport implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4209133766296292860L;

	private Logger logger = LoggerFactory.getLogger(CoSellStatusBean.class);
	
	private List<SelectItem> coSellStatusList;
	String userId = FacesUtils.getCurrentUserId();
	
	@Autowired 
	private CoSellStatusService service;
	
	public CoSellStatusBean() 
	{
		System.out.println("CoSellStatusBean constructor called....");
		logger.info("CoSellStatusBean constructor called....");
	}
	
	public List<SelectItem> getCoSellStatusByCompanyCodeList(String compCode) 
	{
		if (coSellStatusList == null) 
		{
			coSellStatusList = new ArrayList<SelectItem>();
			try
			{
				List<CoSellStatusDTO> coSellStatusLists = service.getCoSellStatusList(userId, compCode);
				if (coSellStatusLists != null)
				{
					for (CoSellStatusDTO c:coSellStatusLists)
					{						
						coSellStatusList.add(new SelectItem(c.getCoSellStatusCode(), c.getCoSellStatusDescription()));
					}
				}
			}
			catch (Exception e)
			{
				System.out.println("Exception in CoSellStatusBean --> getCoSellStatusByCompanyCodeList.");
				logger.debug("Exception in CoSellStatusBean --> getCoSellStatusByCompanyCodeList.");
				e.printStackTrace();
			}
		}
		return coSellStatusList;
	}
	
	public String getCoSellStatusDescription(String compCode, String recordCode) 
	{
			List<CoSellStatusDTO> coSellStatusDescriptionList = new ArrayList<CoSellStatusDTO>();
			try
			{
				coSellStatusDescriptionList = service.getCoSellStatusDescription(userId, compCode, recordCode);
			}
			catch (Exception e)
			{
				System.out.println("Exception in CoSellStatusBean --> getCoSellStatusDescription.");
				logger.debug("Exception in CoSellStatusBean --> getCoSellStatusDescription.");
				e.printStackTrace();
			}
			CoSellStatusDTO coSellStatusDTO = coSellStatusDescriptionList.isEmpty()?null: coSellStatusDescriptionList.get(0);
			if(coSellStatusDTO != null) {
				return coSellStatusDTO.getCoSellStatusDescription().isEmpty()? coSellStatusDTO.getCoSellStatusCode() : coSellStatusDescriptionList.get(0).getCoSellStatusDescription();
			}
			return "";
	}

	/**
	 * @return the coSellStatusList
	 */
	public List<SelectItem> getCoSellStatusList() {
		return coSellStatusList;
	}

	/**
	 * @param coSellStatusList the coSellStatusList to set
	 */
	public void setCoSellStatusList(List<SelectItem> coSellStatusList) {
		this.coSellStatusList = coSellStatusList;
	}

	
}
