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
import com.disys.analyzer.model.dto.HolidayDTO;
import com.disys.analyzer.service.HolidayService;

@ManagedBean
@ViewScoped
public class HolidayBean extends SpringBeanAutowiringSupport implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4209133766296292860L;

	private Logger logger = LoggerFactory.getLogger(HolidayBean.class);
	
	private List<SelectItem> holidayList;
	String userId = FacesUtils.getCurrentUserId();
	
	@Autowired 
	private HolidayService service;
	
	public HolidayBean() 
	{
		System.out.println("HolidayBean constructor called....");
		logger.info("HolidayBean constructor called....");
	}
	
	public List<SelectItem> getHolidayByCompanyCodeList(String compCode) 
	{
		if (holidayList == null) 
		{
			holidayList = new ArrayList<SelectItem>();
			try
			{
				List<HolidayDTO> holidayLists = service.getHolidayList(userId, compCode);
				if (holidayLists != null)
				{
					for (HolidayDTO c:holidayLists)
					{						
						holidayList.add(new SelectItem(c.getHolidayCode(), c.getHolidayDescription()));
					}
				}
			}
			catch (Exception e)
			{
				System.out.println("Exception in HolidayBean --> getHolidayByCompanyCodeList.");
				logger.debug("Exception in HolidayBean --> getHolidayByCompanyCodeList.");
				e.printStackTrace();
			}
		}
		return holidayList;
	}
	
	public String getHolidayDescription(String compCode, String recordCode) 
	{
			List<HolidayDTO> holidayDescriptionList = new ArrayList<HolidayDTO>();
			try
			{
				holidayDescriptionList = service.getHolidayDescription(userId, compCode, recordCode);
			}
			catch (Exception e)
			{
				System.out.println("Exception in HolidayBean --> getHolidayDescription.");
				logger.debug("Exception in HolidayBean --> getHolidayDescription.");
				e.printStackTrace();
			}
			HolidayDTO holidayDTO = holidayDescriptionList.isEmpty()?null: holidayDescriptionList.get(0);
			if(holidayDTO != null) {
				return holidayDTO.getHolidayDescription().isEmpty()? holidayDTO.getHolidayCode() : holidayDescriptionList.get(0).getHolidayDescription();
			}
			return "";
	}

	/**
	 * @return the holidayList
	 */

	public List<SelectItem> getHolidayList() {
		return holidayList;
	}
	/**
	 * @param holidayList the holidayList to set
	 */
	public void setHolidayList(List<SelectItem> holidayList) {
		this.holidayList = holidayList;
	}
	
}
