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
import com.disys.analyzer.model.dto.EmployeeTypeDTO;
import com.disys.analyzer.service.EmployeeTypeService;

@ManagedBean
@ViewScoped
public class EmployeeTypeBean extends SpringBeanAutowiringSupport implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4209133766296292860L;

	private Logger logger = LoggerFactory.getLogger(EmployeeTypeBean.class);
	
	private List<SelectItem> employeeTypeList;
	String userId = FacesUtils.getCurrentUserId();
	
	@Autowired 
	private EmployeeTypeService service;
	
	public EmployeeTypeBean() 
	{
		System.out.println("EmployeeTypeBean constructor called....");
		logger.info("EmployeeTypeBean constructor called....");
	}
	
	public List<SelectItem> getEmployeeTypeByCompanyCodeList(String compCode) 
	{
		if (employeeTypeList == null) 
		{
			employeeTypeList = new ArrayList<SelectItem>();
			try
			{
				List<EmployeeTypeDTO> employeeTypeLists = service.getEmployeeTypeList(userId, compCode);
				if (employeeTypeLists != null)
				{
					for (EmployeeTypeDTO c:employeeTypeLists)
					{
						
						employeeTypeList.add(new SelectItem(c.getEmployeeTypeCode(), c.getEmployeeTypeDescription()));
					}
				}
			}
			catch (Exception e)
			{
				System.out.println("Exception in EmployeeTypeBean --> getEmployeeTypeByCompanyCodeList.");
				logger.debug("Exception in EmployeeTypeBean --> getEmployeeTypeByCompanyCodeList.");
				e.printStackTrace();
			}
		}
		return employeeTypeList;
	}
	
	public String getEmployeeTypeDescription(String compCode, String recordCode) 
	{
			List<EmployeeTypeDTO> employeeTypeDescriptionList = new ArrayList<EmployeeTypeDTO>();
			try
			{
				employeeTypeDescriptionList = service.getEmployeeTypeDescription(userId, compCode, recordCode);
			}
			catch (Exception e)
			{
				System.out.println("Exception in EmployeeTypeBean --> getEmployeeTypeDescription.");
				logger.debug("Exception in EmployeeTypeBean --> getEmployeeTypeDescription.");
				e.printStackTrace();
			}
			EmployeeTypeDTO employeeTypeDTO = employeeTypeDescriptionList.isEmpty()?null: employeeTypeDescriptionList.get(0);
			if(employeeTypeDTO != null) {
				return employeeTypeDTO.getEmployeeTypeDescription().isEmpty()? employeeTypeDTO.getEmployeeTypeCode() : employeeTypeDescriptionList.get(0).getEmployeeTypeDescription();
			}
			return "";
	}

	/**
	 * @return the employeeTypeList
	 */
	public List<SelectItem> getEmployeeTypeList() {
		return employeeTypeList;
	}
	/**
	 * @param employeeTypeList the employeeTypeList to set
	 */
	public void setEmployeeTypeList(List<SelectItem> employeeTypeList) {
		this.employeeTypeList = employeeTypeList;
	}
	
	

}
