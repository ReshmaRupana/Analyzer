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
import com.disys.analyzer.model.dto.WorkFromSourceDTO;
import com.disys.analyzer.service.WorkFromSourceService;

@ManagedBean
@ViewScoped
public class WorkFromSourceBean extends SpringBeanAutowiringSupport implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4209133766296292860L;

	private Logger logger = LoggerFactory.getLogger(WorkFromSourceBean.class);
	
	private List<SelectItem> workFromSourceList;
	String userId = FacesUtils.getCurrentUserId();
	
	@Autowired 
	private WorkFromSourceService service;
	
	public WorkFromSourceBean() 
	{
		System.out.println("WorkFromSourceBean constructor called....");
		logger.info("WorkFromSourceBean constructor called....");
	}
	
	public List<SelectItem> getWorkFromSourceByCompanyCodeList(String compCode) 
	{
		if (workFromSourceList == null) 
		{
			workFromSourceList = new ArrayList<SelectItem>();
			try
			{
				List<WorkFromSourceDTO> workFromSourceLists = service.getWorkFromSourceList(userId, compCode);
				if (workFromSourceLists != null)
				{
					for (WorkFromSourceDTO c:workFromSourceLists)
					{
						
						workFromSourceList.add(new SelectItem(c.getWorkFromSourceCode(), c.getWorkFromSourceDescription()));
					}
				}
			}
			catch (Exception e)
			{
				System.out.println("Exception in WorkFromSourceBean --> getWorkFromSourceByCompanyCodeList.");
				logger.debug("Exception in WorkFromSourceBean --> getWorkFromSourceByCompanyCodeList.");
				e.printStackTrace();
			}
		}
		return workFromSourceList;
	}
	
	public String getWorkFromSourceDescription(String compCode, String recordCode) 
	{
			List<WorkFromSourceDTO> workFromSourceDescriptionList = new ArrayList<WorkFromSourceDTO>();
			try
			{
				workFromSourceDescriptionList = service.getWorkFromSourceDescription(userId, compCode, recordCode);
			}
			catch (Exception e)
			{
				System.out.println("Exception in WorkFromSourceBean --> getWorkFromSourceDescription.");
				logger.debug("Exception in WorkFromSourceBean --> getWorkFromSourceDescription.");
				e.printStackTrace();
			}
			WorkFromSourceDTO workFromSourceDTO = workFromSourceDescriptionList.isEmpty()?null: workFromSourceDescriptionList.get(0);
			if(workFromSourceDTO != null) {
				return workFromSourceDTO.getWorkFromSourceDescription().isEmpty()? workFromSourceDTO.getWorkFromSourceCode() : workFromSourceDescriptionList.get(0).getWorkFromSourceDescription();
			}
			return "";
	}

	/**
	 * @return the workFromSourceList
	 */
	public List<SelectItem> getWorkFromSourceList() {
		return workFromSourceList;
	}
	/**
	 * @param workFromSourceList the workFromSourceList to set
	 */
	public void setWorkFromSourceList(List<SelectItem> workFromSourceList) {
		this.workFromSourceList = workFromSourceList;
	}
	
	

}
