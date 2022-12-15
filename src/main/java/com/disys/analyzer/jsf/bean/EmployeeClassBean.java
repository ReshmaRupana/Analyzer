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
import com.disys.analyzer.model.dto.EmployeeClassDTO;
import com.disys.analyzer.service.EmployeeClassService;

@ManagedBean
@ViewScoped
public class EmployeeClassBean extends SpringBeanAutowiringSupport implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4209133766296292860L;

	private Logger logger = LoggerFactory.getLogger(EmployeeClassBean.class);
	
	private List<SelectItem> employeeClassList;
	String userId = FacesUtils.getCurrentUserId();
	
	@Autowired 
	private EmployeeClassService service;
	
	public EmployeeClassBean() 
	{
		System.out.println("EmployeeClassBean constructor called....");
		logger.info("EmployeeClassBean constructor called....");
	}
	
	public List<SelectItem> getEmployeeClassByCompanyCodeList(String compCode) 
	{
		if (employeeClassList == null) 
		{
			employeeClassList = new ArrayList<SelectItem>();
			try
			{
				List<EmployeeClassDTO> employeeClassLists = service.getEmployeeClassList(userId, compCode);
				if (employeeClassLists != null)
				{
					for (EmployeeClassDTO c:employeeClassLists)
					{
						
						employeeClassList.add(new SelectItem(c.getEmployeeClassCode(), c.getEmployeeClassDescription()));
					}
				}
			}
			catch (Exception e)
			{
				System.out.println("Exception in EmployeeClassBean --> getEmployeeClassList.");
				logger.debug("Exception in EmployeeClassBean --> getEmployeeClassList.");
				e.printStackTrace();
			}
		}
		return employeeClassList;
	}
	
	public String getEmployeeClassDescription(String compCode, String recordCode) 
	{
			List<EmployeeClassDTO> employeeClassDescriptionList = new ArrayList<EmployeeClassDTO>();
			try
			{
				employeeClassDescriptionList = service.getEmployeeClassDescription(userId, compCode, recordCode);
			}
			catch (Exception e)
			{
				System.out.println("Exception in EmployeeClassBean --> getEmployeeClassDescription.");
				logger.debug("Exception in EmployeeClassBean --> getEmployeeClassDescription.");
				e.printStackTrace();
			}
			EmployeeClassDTO employeeClassDTO = employeeClassDescriptionList.isEmpty()?null: employeeClassDescriptionList.get(0);
			if(employeeClassDTO != null) {
				return employeeClassDTO.getEmployeeClassDescription().isEmpty()? employeeClassDTO.getEmployeeClassCode() : employeeClassDescriptionList.get(0).getEmployeeClassDescription();
			}
			return "";
	}

	/**
	 * @return the employeeClassList
	 */	
	public List<SelectItem> getEmployeeClassList() {
		return employeeClassList;
	}
	/**
	 * @param employeeClassList the employeeClassList to set
	 */
	public void setEmployeeClassList(List<SelectItem> employeeClassList) {
		this.employeeClassList = employeeClassList;
	}
	
	

}
