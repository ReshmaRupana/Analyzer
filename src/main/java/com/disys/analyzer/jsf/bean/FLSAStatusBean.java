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
import com.disys.analyzer.model.dto.FLSAStatusDTO;
import com.disys.analyzer.service.FLSAStatusService;

@ManagedBean
@ViewScoped
public class FLSAStatusBean extends SpringBeanAutowiringSupport implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4209133766296292860L;

	private Logger logger = LoggerFactory.getLogger(FLSAStatusBean.class);
	
	private List<SelectItem> flsaStatusList;
	String userId = FacesUtils.getCurrentUserId();
	
	@Autowired 
	private FLSAStatusService service;
	
	public FLSAStatusBean() 
	{
		System.out.println("FLSAStatusBean constructor called....");
		logger.info("FLSAStatusBean constructor called....");
	}
	
	public List<SelectItem> getFLSAStatusByCompanyCodeList(String compCode) 
	{
		if (flsaStatusList == null) 
		{
			flsaStatusList = new ArrayList<SelectItem>();
			try
			{
				List<FLSAStatusDTO> flsaStatusLists = service.getFLSAStatusList(userId, compCode);
				if (flsaStatusLists != null)
				{
					for (FLSAStatusDTO c:flsaStatusLists)
					{
						
						flsaStatusList.add(new SelectItem(c.getFlsaStatusCode(), c.getFlsaStatusDescription()));
					}
				}
			}
			catch (Exception e)
			{
				System.out.println("Exception in FLSAStatusBean --> getFLSAStatusByCompanyCodeList.");
				logger.debug("Exception in FLSAStatusBean --> getFLSAStatusByCompanyCodeList.");
				e.printStackTrace();
			}
		}
		return flsaStatusList;
	}
	
	public String getFLSAStatusDescription(String compCode, String recordCode) 
	{
			List<FLSAStatusDTO> flsaStatusDescriptionList = new ArrayList<FLSAStatusDTO>();
			try
			{
				flsaStatusDescriptionList = service.getFLSAStatusDescription(userId, compCode, recordCode);
			}
			catch (Exception e)
			{
				System.out.println("Exception in FLSAStatusBean --> getFLSAStatusDescription.");
				logger.debug("Exception in FLSAStatusBean --> getFLSAStatusDescription.");
				e.printStackTrace();
			}
			FLSAStatusDTO flsaStatusDTO = flsaStatusDescriptionList.isEmpty()?null: flsaStatusDescriptionList.get(0);
			if(flsaStatusDTO != null) {
				return flsaStatusDTO.getFlsaStatusDescription().isEmpty()? flsaStatusDTO.getFlsaStatusCode() : flsaStatusDescriptionList.get(0).getFlsaStatusDescription();
			}
			return "";
	}

	/**
	 * @return the flsaStatusList
	 */
	public List<SelectItem> getFlsaStatusList() {
		return flsaStatusList;
	}
	/**
	 * @param flsaStatusList the flsaStatusList to set
	 */
	public void setFlsaStatusList(List<SelectItem> flsaStatusList) {
		this.flsaStatusList = flsaStatusList;
	}
	
	

}
