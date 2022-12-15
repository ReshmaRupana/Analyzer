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
import com.disys.analyzer.model.dto.EmployeeCategoryDTO;
import com.disys.analyzer.service.EmployeeCategoryService;

@ManagedBean
@ViewScoped
public class EmployeeCategoryBean extends SpringBeanAutowiringSupport implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4209133766296292860L;

	private Logger logger = LoggerFactory.getLogger(EmployeeCategoryBean.class);
	
	private List<SelectItem> employeeCategoryList;
	String userId = FacesUtils.getCurrentUserId();
	
	@Autowired 
	private EmployeeCategoryService service;
	
	public EmployeeCategoryBean() 
	{
		System.out.println("EmployeeCategoryBean constructor called....");
		logger.info("EmployeeCategoryBean constructor called....");
	}
	
	public List<SelectItem> getEmployeeCategoryByCompanyCodeList(String compCode) 
	{
		if (employeeCategoryList == null) 
		{
			employeeCategoryList = new ArrayList<SelectItem>();
			try
			{
				List<EmployeeCategoryDTO> employeeCategoryLists = service.getEmployeeCategoryList(userId, compCode);
				if (employeeCategoryLists != null)
				{
					for (EmployeeCategoryDTO c:employeeCategoryLists)
					{
						
						employeeCategoryList.add(new SelectItem(c.getEmployeeCategoryCode(), c.getEmployeeCategoryDescription()));
					}
				}
			}
			catch (Exception e)
			{
				System.out.println("Exception in EmployeeCategoryBean --> getEmployeeCategoryByCompanyCodeList.");
				logger.debug("Exception in EmployeeCategoryBean --> getEmployeeCategoryByCompanyCodeList.");
				e.printStackTrace();
			}
		}
		return employeeCategoryList;
	}
	
	public String getEmployeeCategoryDescription(String compCode, String recordCode) 
	{
			List<EmployeeCategoryDTO> employeeCategoryDescriptionList = new ArrayList<EmployeeCategoryDTO>();
			try
			{
				employeeCategoryDescriptionList = service.getEmployeeCategoryDescription(userId, compCode, recordCode);
			}
			catch (Exception e)
			{
				System.out.println("Exception in EmployeeCategoryBean --> getEmployeeCategoryDescription.");
				logger.debug("Exception in EmployeeCategoryBean --> getEmployeeCategoryDescription.");
				e.printStackTrace();
			}
			EmployeeCategoryDTO employeeCategoryDTO = employeeCategoryDescriptionList.isEmpty()?null: employeeCategoryDescriptionList.get(0);
			if(employeeCategoryDTO != null) {
				return employeeCategoryDTO.getEmployeeCategoryDescription().isEmpty()? employeeCategoryDTO.getEmployeeCategoryCode() : employeeCategoryDescriptionList.get(0).getEmployeeCategoryDescription();
			}
			return "";
	}

	/**
	 * @return the employeeCategoryList
	 */
	public List<SelectItem> getEmployeeCategoryList() {
		return employeeCategoryList;
	}
	/**
	 * @param employeeCategoryList the employeeCategoryList to set
	 */
	public void setEmployeeCategoryList(List<SelectItem> employeeCategoryList) {
		this.employeeCategoryList = employeeCategoryList;
	}
	
	

}
