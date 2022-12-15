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
import com.disys.analyzer.model.dto.VeteranStatusDTO;
import com.disys.analyzer.service.VeteranStatusService;

@ManagedBean
@ViewScoped
public class VeteranStatusBean extends SpringBeanAutowiringSupport implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4209133766296292860L;

	private Logger logger = LoggerFactory.getLogger(VeteranStatusBean.class);
	
	private List<SelectItem> veteranStatusList;
	String userId = FacesUtils.getCurrentUserId();
	
	@Autowired 
	private VeteranStatusService service;
	
	public VeteranStatusBean() 
	{
		System.out.println("VeteranStatusBean constructor called....");
		logger.info("VeteranStatusBean constructor called....");
	}
	
	public List<SelectItem> getVeteranStatusByCompanyCodeList(String compCode) 
	{
		if (veteranStatusList == null) 
		{
			veteranStatusList = new ArrayList<SelectItem>();
			try
			{
				List<VeteranStatusDTO> veteranStatusLists = service.getVeteranStatusList(userId, compCode);
				if (veteranStatusLists != null)
				{
					for (VeteranStatusDTO c:veteranStatusLists)
					{						
						veteranStatusList.add(new SelectItem(c.getVeteranStatusCode(), c.getVeteranStatusDescription()));
					}
				}
			}
			catch (Exception e)
			{
				System.out.println("Exception in VeteranStatusBean --> getVeteranStatusByCompanyCodeList.");
				logger.debug("Exception in VeteranStatusBean --> getVeteranStatusByCompanyCodeList.");
				e.printStackTrace();
			}
		}
		return veteranStatusList;
	}
	
	public String getVeteranStatusDescription(String compCode, String recordCode) 
	{
			List<VeteranStatusDTO> veteranStatusDescriptionList = new ArrayList<VeteranStatusDTO>();
			try
			{
				veteranStatusDescriptionList = service.getVeteranStatusDescription(userId, compCode, recordCode);
			}
			catch (Exception e)
			{
				System.out.println("Exception in VeteranStatusBean --> getVeteranStatusDescription.");
				logger.debug("Exception in VeteranStatusBean --> getVeteranStatusDescription.");
				e.printStackTrace();
			}
			VeteranStatusDTO veteranStatusDTO = veteranStatusDescriptionList.isEmpty()?null: veteranStatusDescriptionList.get(0);
			if(veteranStatusDTO != null) {
				return veteranStatusDTO.getVeteranStatusDescription().isEmpty()? veteranStatusDTO.getVeteranStatusCode() : veteranStatusDescriptionList.get(0).getVeteranStatusDescription();
			}
			return "";
	}

	/**
	 * @return the veteranStatusList
	 */
	public List<SelectItem> getVeteranStatusList() {
		return veteranStatusList;
	}
	/**
	 * @param veteranStatusList the veteranStatusList to set
	 */
	public void setVeteranStatusList(List<SelectItem> veteranStatusList) {
		this.veteranStatusList = veteranStatusList;
	}

	
}
