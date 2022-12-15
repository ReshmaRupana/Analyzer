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
import com.disys.analyzer.model.dto.DealTypeDTO;
import com.disys.analyzer.service.DealTypeService;

@ManagedBean
@ViewScoped
public class DealTypeBean extends SpringBeanAutowiringSupport implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4209133766296292860L;

	private Logger logger = LoggerFactory.getLogger(DealTypeBean.class);
	
	private List<SelectItem> dealTypeList;
	String userId = FacesUtils.getCurrentUserId();
	
	@Autowired 
	private DealTypeService service;
	
	public DealTypeBean() 
	{
		System.out.println("DealTypeBean constructor called....");
		logger.info("DealTypeBean constructor called....");
	}
	
	public List<SelectItem> getDealTypeByCompanyCodeList(String compCode) 
	{
		if (dealTypeList == null) 
		{
			dealTypeList = new ArrayList<SelectItem>();
			try
			{
				List<DealTypeDTO> dealTypeLists = service.getDealTypeList(userId, compCode);
				if (dealTypeLists != null)
				{
					for (DealTypeDTO c:dealTypeLists)
					{
						
						dealTypeList.add(new SelectItem(c.getDealTypeCode(), c.getDealTypeDescription()));
					}
				}
			}
			catch (Exception e)
			{
				System.out.println("Exception in DealTypeBean --> getDealTypeByCompanyCodeList.");
				logger.debug("Exception in DealTypeBean --> getDealTypeByCompanyCodeList.");
				e.printStackTrace();
			}
		}
		return dealTypeList;
	}
	
	public String getDealTypeDescription(String compCode, String recordCode) 
	{
			List<DealTypeDTO> dealTypeDescriptionList = new ArrayList<DealTypeDTO>();
			try
			{
				dealTypeDescriptionList = service.getDealTypeDescription(userId, compCode, recordCode);
			}
			catch (Exception e)
			{
				System.out.println("Exception in DealTypeBean --> getDealTypeDescription.");
				logger.debug("Exception in DealTypeBean --> getDealTypeDescription.");
				e.printStackTrace();
			}
			DealTypeDTO dealTypeDTO = dealTypeDescriptionList.isEmpty()?null: dealTypeDescriptionList.get(0);
			if(dealTypeDTO != null) {
				return dealTypeDTO.getDealTypeDescription().isEmpty()? dealTypeDTO.getDealTypeCode() : dealTypeDescriptionList.get(0).getDealTypeDescription();
			}
			return "";
	}

	
	/**
	 * @return the dealTypeList
	 */
	public List<SelectItem> getDealTypeList() {
		return dealTypeList;
	}
	/**
	 * @param dealTypeList the dealTypeList to set
	 */
	public void setDealTypeList(List<SelectItem> dealTypeList) {
		this.dealTypeList = dealTypeList;
	}
}
