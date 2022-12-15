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
import com.disys.analyzer.model.dto.GenderDTO;
import com.disys.analyzer.service.GenderService;

@ManagedBean
@ViewScoped
public class GenderBean extends SpringBeanAutowiringSupport implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4209133766296292860L;

	private Logger logger = LoggerFactory.getLogger(GenderBean.class);
	
	private List<SelectItem> genderList;
	String userId = FacesUtils.getCurrentUserId();
	
	@Autowired 
	private GenderService service;
	
	public GenderBean() 
	{
		System.out.println("GenderBean constructor called....");
		logger.info("GenderBean constructor called....");
	}
	
	public List<SelectItem> getGenderByCompanyCodeList(String compCode) 
	{
		if (genderList == null) 
		{
			genderList = new ArrayList<SelectItem>();
			try
			{
				List<GenderDTO> genderLists = service.getGenderList(userId, compCode);
				if (genderLists != null)
				{
					for (GenderDTO c:genderLists)
					{						
						genderList.add(new SelectItem(c.getGenderCode(), c.getGenderDescription()));
					}
				}
			}
			catch (Exception e)
			{
				System.out.println("Exception in GenderBean --> getGenderByCompanyCodeList.");
				logger.debug("Exception in GenderBean --> getGenderByCompanyCodeList.");
				e.printStackTrace();
			}
		}
		return genderList;
	}
	
	public String getGenderDescription(String compCode, String recordCode) 
	{
			List<GenderDTO> genderDescriptionList = new ArrayList<GenderDTO>();
			try
			{
				genderDescriptionList = service.getGenderDescription(userId, compCode, recordCode);
			}
			catch (Exception e)
			{
				System.out.println("Exception in GenderBean --> getGenderDescription.");
				logger.debug("Exception in GenderBean --> getGenderDescription.");
				e.printStackTrace();
			}
			GenderDTO genderDTO = genderDescriptionList.isEmpty()?null: genderDescriptionList.get(0);
			if(genderDTO != null) {
				return genderDTO.getGenderDescription().isEmpty()? genderDTO.getGenderCode() : genderDescriptionList.get(0).getGenderDescription();
			}
			return "";
	}

	/**
	 * @return the genderList
	 */
	public List<SelectItem> getGenderList() {
		return genderList;
	}

	/**
	 * @param genderList the genderList to set
	 */
	public void setGenderList(List<SelectItem> genderList) {
		this.genderList = genderList;
	}

	
}
