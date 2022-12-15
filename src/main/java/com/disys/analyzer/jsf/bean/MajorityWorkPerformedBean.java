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
import com.disys.analyzer.model.dto.MajorityWorkPerformedDTO;
import com.disys.analyzer.service.MajorityWorkPerformedService;

@ManagedBean
@ViewScoped
/*
 * Added as part of New Projects Specification
 */
public class MajorityWorkPerformedBean extends SpringBeanAutowiringSupport implements Serializable{

	private static final long serialVersionUID = 4209133766296292860L;

	private Logger logger = LoggerFactory.getLogger(MajorityWorkPerformedBean.class);
	
	private List<SelectItem> majorityWorkPerformedList;
	String userId = FacesUtils.getCurrentUserId();
	
	@Autowired 
	private MajorityWorkPerformedService service;
	
	public MajorityWorkPerformedBean() 
	{
		System.out.println("MajorityWorkPerformedBean constructor called....");
		logger.info("MajorityWorkPerformedBean constructor called....");
	}
	
	public List<SelectItem> getMajorityWorkPerformedByCompanyCodeList(String compCode) 
	{
		if (majorityWorkPerformedList == null) 
		{
			majorityWorkPerformedList = new ArrayList<SelectItem>();
			try
			{
				List<MajorityWorkPerformedDTO> majorityWorkPerformedLists = service.getMajorityWorkPerformedList(userId, compCode);
				if (majorityWorkPerformedLists != null)
				{
					for (MajorityWorkPerformedDTO c:majorityWorkPerformedLists)
					{
						
						majorityWorkPerformedList.add(new SelectItem(c.getMajorityWorkPerformedCode(), c.getMajorityWorkPerformedDescription()));
					}
				}
			}
			catch (Exception e)
			{
				System.out.println("Exception in MajorityWorkPerformedBean --> getMajorityWorkPerformedByCompanyCodeList.");
				logger.debug("Exception in MajorityWorkPerformedBean --> getMajorityWorkPerformedByCompanyCodeList.");
				e.printStackTrace();
			}
		}
		return majorityWorkPerformedList;
	}
	
	public String getMajorityWorkPerformedDescription(String compCode, String recordCode) 
	{
			List<MajorityWorkPerformedDTO> majorityWorkPerformedDescriptionList = new ArrayList<MajorityWorkPerformedDTO>();
			try
			{
				majorityWorkPerformedDescriptionList = service.getMajorityWorkPerformedDescription(userId, compCode, recordCode);
			}
			catch (Exception e)
			{
				System.out.println("Exception in MajorityWorkPerformedBean --> getMajorityWorkPerformedDescription.");
				logger.debug("Exception in MajorityWorkPerformedBean --> getMajorityWorkPerformedDescription.");
				e.printStackTrace();
			}
			MajorityWorkPerformedDTO MajorityWorkPerformedDTO = majorityWorkPerformedDescriptionList.isEmpty()?null: majorityWorkPerformedDescriptionList.get(0);
			if(MajorityWorkPerformedDTO != null) {
				return MajorityWorkPerformedDTO.getMajorityWorkPerformedDescription().isEmpty()? MajorityWorkPerformedDTO.getMajorityWorkPerformedCode() : majorityWorkPerformedDescriptionList.get(0).getMajorityWorkPerformedDescription();
			}
			return "";
	}

	/**
	 * @return the majorityWorkPerformedList
	 */
	public List<SelectItem> getmajorityWorkPerformedList() {
		return majorityWorkPerformedList;
	}
	/**
	 * @param majorityWorkPerformedList the majorityWorkPerformedList to set
	 */
	public void setmajorityWorkPerformedList(List<SelectItem> majorityWorkPerformedList) {
		this.majorityWorkPerformedList = majorityWorkPerformedList;
	}
	
	

}
