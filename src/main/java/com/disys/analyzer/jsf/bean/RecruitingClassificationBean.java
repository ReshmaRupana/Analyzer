/**
 * 
 */
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
import com.disys.analyzer.model.dto.RecruitingClassificationDTO;
import com.disys.analyzer.service.RecruitingClassificationService;

/**
 * @author Sajid
 * @since March 26, 2019
 *
 */
@ManagedBean
//@SessionScoped	//so that it will initialize at the session level and don't call this for each page
@ViewScoped
public class RecruitingClassificationBean extends SpringBeanAutowiringSupport implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(RecruitingClassificationBean.class);
	
	private List<SelectItem> listWithoutAll;
	private List<SelectItem> listWithAll;
	
	private List<SelectItem> recruitingClassificationList;
	String userId = FacesUtils.getCurrentUserId();
	
	@Autowired
	RecruitingClassificationService recruitingClassificationService;
	
	public RecruitingClassificationBean(){
		logger.info("RecruitingClassificationBean initialized");
	}
	
	
	/**
	 * @return the list
	 */
	public List<SelectItem> getListWithoutAll() 
	{
		if (listWithoutAll == null) 
		{
			listWithoutAll = new ArrayList<SelectItem>();
			try
			{
				List<RecruitingClassificationDTO> recruitingClassifications = recruitingClassificationService.getRecruitingClassificationsList(FacesUtils.getCurrentUserId(), false);
				if (recruitingClassifications != null)
				{
					for (RecruitingClassificationDTO p:recruitingClassifications)
					{
						listWithoutAll.add(new SelectItem(p.getRecruitingClassificationValue(), p.getRecruitingClassificationName()));
					}
				}
			}
			catch (Exception e)
			{
				System.out.println("Exception in RecruitingClassificationBean --> getListWithoutAll.");
				logger.debug("Exception in RecruitingClassificationBean --> getListWithoutAll.");
				e.printStackTrace();
			}
		}
		return listWithoutAll;
	}

	/**
	 * @param list the list to set
	 */
	public void setListWithoutAll(List<SelectItem> listWithoutAll) {
		this.listWithoutAll = listWithoutAll;
	}


	/**
	 * @return the listWithAll
	 */
	public List<SelectItem> getListWithAll()
	{
		if (listWithAll == null) 
		{
			listWithAll = new ArrayList<SelectItem>();
			try
			{
				List<RecruitingClassificationDTO> recruitingClassifications = recruitingClassificationService.getRecruitingClassificationsList(FacesUtils.getCurrentUserId(), true);
				if (recruitingClassifications != null)
				{
					for (RecruitingClassificationDTO p:recruitingClassifications)
					{
						listWithAll.add(new SelectItem(p.getRecruitingClassificationValue(), p.getRecruitingClassificationName()));
					}
				}
			}
			catch (Exception e)
			{
				System.out.println("Exception in RecruitingClassificationBean --> getListWithAll.");
				logger.debug("Exception in RecruitingClassificationBean --> getListWithAll.");
				e.printStackTrace();
			}
		}
		return listWithAll;
	}


	/**
	 * @param listWithAll the listWithAll to set
	 */
	public void setListWithAll(List<SelectItem> listWithAll)
	{
		this.listWithAll = listWithAll;
	}
	
	public List<SelectItem> getRecruitingClassificationsByCompanyCodeList(String compCode) 
	{
		if (recruitingClassificationList == null) 
		{
			recruitingClassificationList = new ArrayList<SelectItem>();
			try
			{
				List<RecruitingClassificationDTO> recruitingClassificationLists = recruitingClassificationService.getRecruitingClassificationsList(userId, compCode);
				if (recruitingClassificationLists != null)
				{
					for (RecruitingClassificationDTO p:recruitingClassificationLists)
					{						
						recruitingClassificationList.add(new SelectItem(p.getRecruitingClassificationValue(), p.getRecruitingClassificationName()));
					}
				}
			}
			catch (Exception e)
			{
				System.out.println("Exception in RecruitingClassificationBean --> getRecruitingClassificationsByCompanyCodeList.");
				logger.debug("Exception in RecruitingClassificationBean --> getRecruitingClassificationsByCompanyCodeList.");
				e.printStackTrace();
			}
		}
		return recruitingClassificationList;
	}
	
	public String getRecruitingClassificationsDescription(String compCode, String recordCode) 
	{
			List<RecruitingClassificationDTO> recruitingClassificationDescriptionList = new ArrayList<RecruitingClassificationDTO>();
			try
			{
				recruitingClassificationDescriptionList = recruitingClassificationService.getRecruitingClassificationsDescription(userId, compCode, recordCode);
			}
			catch (Exception e)
			{
				System.out.println("Exception in RecruitingClassificationBean --> getRecruitingClassificationsDescription.");
				logger.debug("Exception in RecruitingClassificationBean --> getRecruitingClassificationsDescription.");
				e.printStackTrace();
			}
			RecruitingClassificationDTO recruitingClassificationDTO = recruitingClassificationDescriptionList.isEmpty()?null: recruitingClassificationDescriptionList.get(0);
			if(recruitingClassificationDTO != null) {
				return recruitingClassificationDTO.getRecruitingClassificationName().isEmpty()? recruitingClassificationDTO.getRecruitingClassificationValue() : recruitingClassificationDescriptionList.get(0).getRecruitingClassificationName();
			}
			return "";
	}


	public List<SelectItem> getRecruitingClassificationList() {
		return recruitingClassificationList;
	}


	public void setRecruitingClassificationList(List<SelectItem> recruitingClassificationList) {
		this.recruitingClassificationList = recruitingClassificationList;
	}
	
}
