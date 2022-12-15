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
import com.disys.analyzer.model.dto.BonusPercentageDTO;
import com.disys.analyzer.service.BonusPercentageService;

@ManagedBean
@ViewScoped
public class BonusPercentageBean extends SpringBeanAutowiringSupport implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4209133766296292860L;

	private Logger logger = LoggerFactory.getLogger(BonusPercentageBean.class);
	
	private List<SelectItem> bonusPercentageList;
	String userId = FacesUtils.getCurrentUserId();
	
	@Autowired 
	private BonusPercentageService service;
	
	public BonusPercentageBean() 
	{
		System.out.println("BonusPercentageBean constructor called....");
		logger.info("BonusPercentageBean constructor called....");
	}
	
	public List<SelectItem> getBonusPercentageByCompanyCodeList(String compCode) 
	{
		if (bonusPercentageList == null) 
		{
			bonusPercentageList = new ArrayList<SelectItem>();
			try
			{
				List<BonusPercentageDTO> bonusPercentageLists = service.getBonusPercentageList(userId, compCode);
				if (bonusPercentageLists != null)
				{
					for (BonusPercentageDTO c:bonusPercentageLists)
					{						
						bonusPercentageList.add(new SelectItem(c.getBonusPercentageCode(), c.getBonusPercentageDescription()));
					}
				}
			}
			catch (Exception e)
			{
				System.out.println("Exception in BonusPercentageBean --> getBonusPercentageByCompanyCodeList.");
				logger.debug("Exception in BonusPercentageBean --> getBonusPercentageByCompanyCodeList.");
				e.printStackTrace();
			}
		}
		return bonusPercentageList;
	}
	
	public String getBonusPercentageDescription(String compCode, String recordCode) 
	{
			List<BonusPercentageDTO> bonusPercentageDescriptionList = new ArrayList<BonusPercentageDTO>();
			try
			{
				bonusPercentageDescriptionList = service.getBonusPercentageDescription(userId, compCode, recordCode);
			}
			catch (Exception e)
			{
				System.out.println("Exception in BonusPercentageBean --> getBonusPercentageDescription.");
				logger.debug("Exception in BonusPercentageBean --> getBonusPercentageDescription.");
				e.printStackTrace();
			}
			BonusPercentageDTO bonusPercentageDTO = bonusPercentageDescriptionList.isEmpty()?null: bonusPercentageDescriptionList.get(0);
			if(bonusPercentageDTO != null) {
				return bonusPercentageDTO.getBonusPercentageDescription().isEmpty()? bonusPercentageDTO.getBonusPercentageCode() : bonusPercentageDescriptionList.get(0).getBonusPercentageDescription();
			}
			return "";
	}

	/**
	 * @return the bonusPercentageList
	 */
	public List<SelectItem> getBonusPercentageList() {
		return bonusPercentageList;
	}

	/**
	 * @param bonusPercentageList the bonusPercentageList to set
	 */
	public void setBonusPercentageList(List<SelectItem> bonusPercentageList) {
		this.bonusPercentageList = bonusPercentageList;
	}

	
}
