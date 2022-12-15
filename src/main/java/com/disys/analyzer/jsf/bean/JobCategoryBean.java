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
import com.disys.analyzer.model.dto.JobCategoryDTO;
import com.disys.analyzer.service.JobCategoryService;

@ManagedBean
@ViewScoped
public class JobCategoryBean extends SpringBeanAutowiringSupport implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4209133766296292860L;

	private Logger logger = LoggerFactory.getLogger(JobCategoryBean.class);
	
	private List<SelectItem> jobCategoryList;
	String userId = FacesUtils.getCurrentUserId();
	
	@Autowired 
	private JobCategoryService service;
	
	public JobCategoryBean() 
	{
		System.out.println("JobCategoryBean constructor called....");
		logger.info("JobCategoryBean constructor called....");
	}
	
	public List<SelectItem> getJobCategoryByCompanyCodeList(String compCode) 
	{
		if (jobCategoryList == null) 
		{
			jobCategoryList = new ArrayList<SelectItem>();
			try
			{
				List<JobCategoryDTO> jobCategoryLists = service.getJobCategoryList(userId, compCode);
				if (jobCategoryLists != null)
				{
					for (JobCategoryDTO c:jobCategoryLists)
					{
						
						jobCategoryList.add(new SelectItem(c.getJobCategoryCode(), c.getJobCategoryDescription()));
					}
				}
			}
			catch (Exception e)
			{
				System.out.println("Exception in JobCategoryBean --> getJobCategoryByCompanyCodeList.");
				logger.debug("Exception in JobCategoryBean --> getJobCategoryByCompanyCodeList.");
				e.printStackTrace();
			}
		}
		return jobCategoryList;
	}
	
	public String getJobCategoryDescription(String compCode, String recordCode) 
	{
			List<JobCategoryDTO> jobCategoryDescriptionList = new ArrayList<JobCategoryDTO>();
			try
			{
				jobCategoryDescriptionList = service.getJobCategoryDescription(userId, compCode, recordCode);
			}
			catch (Exception e)
			{
				System.out.println("Exception in JobCategoryBean --> getJobCategoryDescription.");
				logger.debug("Exception in JobCategoryBean --> getJobCategoryDescription.");
				e.printStackTrace();
			}
			JobCategoryDTO jobCategoryDTO = jobCategoryDescriptionList.isEmpty()?null: jobCategoryDescriptionList.get(0);
			if(jobCategoryDTO != null) {
				return jobCategoryDTO.getJobCategoryDescription().isEmpty()? jobCategoryDTO.getJobCategoryCode() : jobCategoryDescriptionList.get(0).getJobCategoryDescription();
			}
			return "";
	}

	/**
	 * @return the jobCategoryList
	 */
	public List<SelectItem> getJobCategoryList() {
		return jobCategoryList;
	}
	/**
	 * @param jobCategoryList the jobCategoryList to set
	 */
	public void setJobCategoryList(List<SelectItem> jobCategoryList) {
		this.jobCategoryList = jobCategoryList;
	}
	
	

}
