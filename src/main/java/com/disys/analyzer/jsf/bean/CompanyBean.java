package com.disys.analyzer.jsf.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import com.disys.analyzer.config.util.FacesUtils;
import com.disys.analyzer.model.dto.CompanyDTO;
import com.disys.analyzer.service.CompanyService;

@ManagedBean
@SessionScoped
public class CompanyBean extends SpringBeanAutowiringSupport implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4209133766296292860L;

	private Logger logger = LoggerFactory.getLogger(CompanyBean.class);
	
	private List<SelectItem> companyList;
	String userId = FacesUtils.getCurrentUserId();
	
	@Autowired 
	private CompanyService service;
	
	public CompanyBean() 
	{
		System.out.println("CompanyBean constructor called....");
		logger.info("CompanyBean constructor called....");
	
	}

	public List<SelectItem> getCompanies() 
	{
		if (companyList == null) 
		{
			companyList = new ArrayList<SelectItem>();
			
			try
			{

				List<CompanyDTO> companys = service.getCompanyList(userId);
				if (companys != null)
				{
					for (CompanyDTO c:companys)
					{
						
						companyList.add(new SelectItem(c.getCompanyCode(), c.getCompanyDescription()));
					}
				}
			}
			catch (Exception e)
			{
				System.out.println("Exception in CompanyBean --> getCompanyLists.");
				logger.debug("Exception in CompanyBean --> getCompanyLists.");
				e.printStackTrace();
			}
		}
		return companyList;
	}
		
	/**
	 * @return the companyList
	 */
	public List<SelectItem> getCompanyList() {
		return companyList;
	}
	/**
	 * @param companyList the companyList to set
	 */
	public void setCompanyList(List<SelectItem> companyList) {
		this.companyList = companyList;
	}
	
	public String getCompanyDescription(String recordCode) 
	{
			List<CompanyDTO> companyList = new ArrayList<CompanyDTO>();
			try
			{
				companyList = service.getCompanyDescription(userId, recordCode);
			}
			catch (Exception e)
			{
				System.out.println("Exception in CompanyBean --> getCompanyDescription.");
				logger.debug("Exception in CompanyBean --> getCompanyDescription.");
				e.printStackTrace();
			}
			CompanyDTO companyDTO = companyList.isEmpty()?null: companyList.get(0);
			if(companyDTO != null) {
				return companyDTO.getCompanyDescription().isEmpty()? companyDTO.getCompanyCode() : companyList.get(0).getCompanyDescription();
			}
			return "";
	}
	
}
