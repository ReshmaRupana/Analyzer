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
import com.disys.analyzer.model.dto.UnemployedStatusDTO;
import com.disys.analyzer.service.UnemployedStatusService;

@ManagedBean
@ViewScoped
public class UnemployedStatusBean extends SpringBeanAutowiringSupport implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4209133766296292860L;

	private Logger logger = LoggerFactory.getLogger(UnemployedStatusBean.class);
	
	private List<SelectItem> unemployedStatusList;
	String userId = FacesUtils.getCurrentUserId();
	
	@Autowired 
	private UnemployedStatusService service;
	
	public UnemployedStatusBean() 
	{
		System.out.println("UnemployedStatusBean constructor called....");
		logger.info("UnemployedStatusBean constructor called....");
	}
	
	public List<SelectItem> getUnemployedStatusByCompanyCodeList(String compCode) 
	{
		if (unemployedStatusList == null) 
		{
			unemployedStatusList = new ArrayList<SelectItem>();
			try
			{
				List<UnemployedStatusDTO> unemployedStatusLists = service.getUnemployedStatusList(userId, compCode);
				if (unemployedStatusLists != null)
				{
					for (UnemployedStatusDTO c:unemployedStatusLists)
					{						
						unemployedStatusList.add(new SelectItem(c.getUnemployedStatusCode(), c.getUnemployedStatusDescription()));
					}
				}
			}
			catch (Exception e)
			{
				System.out.println("Exception in UnemployedStatusBean --> getUnemployedStatusByCompanyCodeList.");
				logger.debug("Exception in UnemployedStatusBean --> getUnemployedStatusByCompanyCodeList.");
				e.printStackTrace();
			}
		}
		return unemployedStatusList;
	}
	
	public String getUnemployedStatusDescription(String compCode, String recordCode) 
	{
			List<UnemployedStatusDTO> unemployedStatusDescriptionList = new ArrayList<UnemployedStatusDTO>();
			try
			{
				unemployedStatusDescriptionList = service.getUnemployedStatusDescription(userId, compCode, recordCode);
			}
			catch (Exception e)
			{
				System.out.println("Exception in UnemployedStatusBean --> getUnemployedStatusDescription.");
				logger.debug("Exception in UnemployedStatusBean --> getUnemployedStatusDescription.");
				e.printStackTrace();
			}
			UnemployedStatusDTO unemployedStatusDTO = unemployedStatusDescriptionList.isEmpty()?null: unemployedStatusDescriptionList.get(0);
			if(unemployedStatusDTO != null) {
				return unemployedStatusDTO.getUnemployedStatusDescription().isEmpty()? unemployedStatusDTO.getUnemployedStatusCode() : unemployedStatusDescriptionList.get(0).getUnemployedStatusDescription();
			}
			return "";
	}

	/**
	 * @return the unemployedStatusList
	 */
	
	public List<SelectItem> getUnemployedStatusList() {
		return unemployedStatusList;
	}
	/**
	 * @param unemployedStatusList the unemployedStatusList to set
	 */
	public void setUnemployedStatusList(List<SelectItem> unemployedStatusList) {
		this.unemployedStatusList = unemployedStatusList;
	}

	
}
