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
import com.disys.analyzer.model.dto.SplitPercentageDTO;
import com.disys.analyzer.service.SplitPercentageService;

@ManagedBean
@ViewScoped
public class SplitPercentageBean extends SpringBeanAutowiringSupport implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4209133766296292860L;

	private Logger logger = LoggerFactory.getLogger(SplitPercentageBean.class);
	
	private List<SelectItem> splitPercentageList;
	String userId = FacesUtils.getCurrentUserId();
	
	@Autowired 
	private SplitPercentageService service;
	
	public SplitPercentageBean() 
	{
		System.out.println("SplitPercentageBean constructor called....");
		logger.info("SplitPercentageBean constructor called....");
	}
	
	public List<SelectItem> getSplitPercentageByCompanyCodeList(String compCode) 
	{
		if (splitPercentageList == null) 
		{
			splitPercentageList = new ArrayList<SelectItem>();
			try
			{
				List<SplitPercentageDTO> splitPercentageLists = service.getSplitPercentageList(userId, compCode);
				if (splitPercentageLists != null)
				{
					for (SplitPercentageDTO c:splitPercentageLists)
					{
						
						splitPercentageList.add(new SelectItem(c.getSplitPercentageCode(), c.getSplitPercentageDescription()));
					}
				}
			}
			catch (Exception e)
			{
				System.out.println("Exception in SplitPercentageBean --> getSplitPercentageByCompanyCode.");
				logger.debug("Exception in SplitPercentageBean --> getSplitPercentageByCompanyCode.");
				e.printStackTrace();
			}
		}
		return splitPercentageList;
	}
	
	public String getSplitPercentageDescription(String compCode, String recordCode) 
	{
			List<SplitPercentageDTO> splitPercentageDescriptionList = new ArrayList<SplitPercentageDTO>();
			try
			{
				splitPercentageDescriptionList = service.getSplitPercentageDescription(userId, compCode, recordCode);
			}
			catch (Exception e)
			{
				System.out.println("Exception in SplitPercentageBean --> getSplitPercentageDescription.");
				logger.debug("Exception in SplitPercentageBean --> getSplitPercentageDescription.");
				e.printStackTrace();
			}
			SplitPercentageDTO splitPercentageDTO = splitPercentageDescriptionList.isEmpty()?null: splitPercentageDescriptionList.get(0);
			if(splitPercentageDTO != null) {
				return splitPercentageDTO.getSplitPercentageDescription().isEmpty()? splitPercentageDTO.getSplitPercentageCode() : splitPercentageDescriptionList.get(0).getSplitPercentageDescription();
			}
			return "";
	}

	/**
	 * @return the splitPercentageList
	 */
	public List<SelectItem> getSplitPercentageList() {
		return splitPercentageList;
	}
	/**
	 * @param splitPercentageList the splitPercentageList to set
	 */
	public void setSplitPercentageList(List<SelectItem> splitPercentageList) {
		this.splitPercentageList = splitPercentageList;
	}

}
