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
import com.disys.analyzer.model.dto.SickLeaveTypeDTO;
import com.disys.analyzer.service.SickLeaveTypeService;

@ManagedBean
@ViewScoped
public class SickLeaveTypeBean extends SpringBeanAutowiringSupport implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4209133766296292860L;

	private Logger logger = LoggerFactory.getLogger(SickLeaveTypeBean.class);
	
	private List<SelectItem> sickLeaveTypeList;
	String userId = FacesUtils.getCurrentUserId();
	
	@Autowired 
	private SickLeaveTypeService service;
	
	public SickLeaveTypeBean() 
	{
		System.out.println("SickLeaveTypeBean constructor called....");
		logger.info("SickLeaveTypeBean constructor called....");
	}
	
	public List<SelectItem> getSickLeaveTypeByCompanyCodeList(String compCode) 
	{
		if (sickLeaveTypeList == null) 
		{
			sickLeaveTypeList = new ArrayList<SelectItem>();
			try
			{
				List<SickLeaveTypeDTO> sickLeaveTypeLists = service.getSickLeaveTypeList(userId, compCode);
				if (sickLeaveTypeLists != null)
				{
					for (SickLeaveTypeDTO c:sickLeaveTypeLists)
					{						
						sickLeaveTypeList.add(new SelectItem(c.getSickLeaveTypeCode(), c.getSickLeaveTypeDescription()));
					}
				}
			}
			catch (Exception e)
			{
				System.out.println("Exception in SickLeaveTypeBean --> getSickLeaveTypeByCompanyCodeList.");
				logger.debug("Exception in SickLeaveTypeBean --> getSickLeaveTypeByCompanyCodeList.");
				e.printStackTrace();
			}
		}
		return sickLeaveTypeList;
	}
	
	public String getSickLeaveTypeDescription(String compCode, String recordCode) 
	{
			List<SickLeaveTypeDTO> sickLeaveTypeDescriptionList = new ArrayList<SickLeaveTypeDTO>();
			try
			{
				sickLeaveTypeDescriptionList = service.getSickLeaveTypeDescription(userId, compCode, recordCode);
			}
			catch (Exception e)
			{
				System.out.println("Exception in SickLeaveTypeBean --> getSickLeaveTypeDescription.");
				logger.debug("Exception in SickLeaveTypeBean --> getSickLeaveTypeDescription.");
				e.printStackTrace();
			}
			SickLeaveTypeDTO sickLeaveTypeDTO = sickLeaveTypeDescriptionList.isEmpty()?null: sickLeaveTypeDescriptionList.get(0);
			if(sickLeaveTypeDTO != null) {
				return sickLeaveTypeDTO.getSickLeaveTypeDescription().isEmpty()? sickLeaveTypeDTO.getSickLeaveTypeCode() : sickLeaveTypeDescriptionList.get(0).getSickLeaveTypeDescription();
			}
			return "";
	}
	/**
	 * @return the sickLeaveTypeList
	 */
	public List<SelectItem> getSickLeaveTypeList() {
		return sickLeaveTypeList;
	}
	/**
	 * @param sickLeaveTypeList the sickLeaveTypeList to set
	 */
	
	public void setSickLeaveTypeList(List<SelectItem> sickLeaveTypeList) {
		this.sickLeaveTypeList = sickLeaveTypeList;
	}
	
}
