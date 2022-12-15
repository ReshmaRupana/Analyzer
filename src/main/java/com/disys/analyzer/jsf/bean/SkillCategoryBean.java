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
import com.disys.analyzer.model.dto.SkillCategoryDTO;
import com.disys.analyzer.service.SkillCategoryService;

@ManagedBean
@ViewScoped
public class SkillCategoryBean extends SpringBeanAutowiringSupport implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4209133766296292860L;

	private Logger logger = LoggerFactory.getLogger(SkillCategoryBean.class);
	
	private List<SelectItem> skillCategoryList;
	String userId = FacesUtils.getCurrentUserId();
	
	@Autowired 
	private SkillCategoryService service;
	
	public SkillCategoryBean() 
	{
		System.out.println("SkillCategoryBean constructor called....");
		logger.info("SkillCategoryBean constructor called....");
	}
	
	public List<SelectItem> getSkillCategoryByCompanyCodeList(String compCode) 
	{
		if (skillCategoryList == null) 
		{
			skillCategoryList = new ArrayList<SelectItem>();
			try
			{
				List<SkillCategoryDTO> skillCategoryLists = service.getSkillCategoryList(userId, compCode);
				if (skillCategoryLists != null)
				{
					for (SkillCategoryDTO c:skillCategoryLists)
					{						
						skillCategoryList.add(new SelectItem(c.getSkillCategoryCode(), c.getSkillCategoryDescription()));
					}
				}
			}
			catch (Exception e)
			{
				System.out.println("Exception in SkillCategoryBean --> getSkillCategoryByCompanyCodeList.");
				logger.debug("Exception in SkillCategoryBean --> getSkillCategoryByCompanyCodeList.");
				e.printStackTrace();
			}
		}
		return skillCategoryList;
	}
	
	public String getSkillCategoryDescription(String compCode, String recordCode) 
	{
			List<SkillCategoryDTO> skillCategoryDescriptionList = new ArrayList<SkillCategoryDTO>();
			try
			{
				skillCategoryDescriptionList = service.getSkillCategoryDescription(userId, compCode, recordCode);
			}
			catch (Exception e)
			{
				System.out.println("Exception in SkillCategoryBean --> getSkillCategoryDescription.");
				logger.debug("Exception in SkillCategoryBean --> getSkillCategoryDescription.");
				e.printStackTrace();
			}
			SkillCategoryDTO skillCategoryDTO = skillCategoryDescriptionList.isEmpty()?null: skillCategoryDescriptionList.get(0);
			if(skillCategoryDTO != null) {
				return skillCategoryDTO.getSkillCategoryDescription().isEmpty()? skillCategoryDTO.getSkillCategoryCode() : skillCategoryDescriptionList.get(0).getSkillCategoryDescription();
			}
			return "";
	}

	/**
	 * @return the skillCategoryList
	 */
	public List<SelectItem> getSkillCategoryList() {
		return skillCategoryList;
	}

	/**
	 * @param skillCategoryList the skillCategoryList to set
	 */
	public void setSkillCategoryList(List<SelectItem> skillCategoryList) {
		this.skillCategoryList = skillCategoryList;
	}

	
}
