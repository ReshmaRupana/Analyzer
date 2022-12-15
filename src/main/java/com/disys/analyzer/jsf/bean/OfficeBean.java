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
import com.disys.analyzer.model.dto.OfficeDTO;
import com.disys.analyzer.service.OfficeService;

@ManagedBean
@ViewScoped
public class OfficeBean extends SpringBeanAutowiringSupport implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4209133766296292860L;

	private Logger logger = LoggerFactory.getLogger(OfficeBean.class);
	
	private List<SelectItem> officeList;
	String userId = FacesUtils.getCurrentUserId();
	
	@Autowired 
	private OfficeService service;
	
	public OfficeBean() 
	{
		System.out.println("OfficeBean constructor called....");
		logger.info("OfficeBean constructor called....");
	}
	
	public List<SelectItem> getOfficeByCompanyCodeList(String compCode) 
	{
		if (officeList == null) 
		{
			officeList = new ArrayList<SelectItem>();
			try
			{
				List<OfficeDTO> officeLists = service.getOfficeList(userId, compCode);
				if (officeLists != null)
				{
					for (OfficeDTO c:officeLists)
					{						
						officeList.add(new SelectItem(c.getOfficeDescription(), c.getOfficeDescription()));
					}
				}
			}
			catch (Exception e)
			{
				System.out.println("Exception in OfficeBean --> getOfficeByCompanyCodeList.");
				logger.debug("Exception in OfficeBean --> getOfficeByCompanyCodeList.");
				e.printStackTrace();
			}
		}
		return officeList;
	}
	
	public String getOfficeDescription(String compCode, String recordCode) 
	{
			List<OfficeDTO> officeDescriptionList = new ArrayList<OfficeDTO>();
			try
			{
				officeDescriptionList = service.getOfficeDescription(userId, compCode, recordCode);
			}
			catch (Exception e)
			{
				System.out.println("Exception in OfficeBean --> getOfficeDescription.");
				logger.debug("Exception in OfficeBean --> getOfficeDescription.");
				e.printStackTrace();
			}
			OfficeDTO officeDTO = officeDescriptionList.isEmpty()?null: officeDescriptionList.get(0);
			if(officeDTO != null) {
				return officeDTO.getOfficeDescription().isEmpty()? officeDTO.getOfficeDescription() : officeDescriptionList.get(0).getOfficeDescription();
			}
			return "";
	}

	/**
	 * @return the officeList
	 */
	public List<SelectItem> getOfficeList() {
		return officeList;
	}
	/**
	 * @param officeList the officeList to set
	 */
	public void setOfficeList(List<SelectItem> officeList) {
		this.officeList = officeList;
	}

	
}
