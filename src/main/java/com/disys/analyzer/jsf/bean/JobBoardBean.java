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
import com.disys.analyzer.model.dto.JobBoardDTO;
import com.disys.analyzer.service.JobBoardService;

@ManagedBean
@ViewScoped
public class JobBoardBean extends SpringBeanAutowiringSupport implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4209133766296292860L;

	private Logger logger = LoggerFactory.getLogger(JobBoardBean.class);
	
	private List<SelectItem> jobBoardList;
	String userId = FacesUtils.getCurrentUserId();
	
	@Autowired 
	private JobBoardService service;
	
	public JobBoardBean() 
	{
		System.out.println("JobBoardBean constructor called....");
		logger.info("JobBoardBean constructor called....");
	}
	
	public List<SelectItem> getJobBoardByCompanyCodeList(String compCode) 
	{
		if (jobBoardList == null) 
		{
			jobBoardList = new ArrayList<SelectItem>();
			try
			{
				List<JobBoardDTO> jobBoardLists = service.getJobBoardList(userId, compCode);
				if (jobBoardLists != null)
				{
					for (JobBoardDTO c:jobBoardLists)
					{						
						jobBoardList.add(new SelectItem(c.getJobBoardCode(), c.getJobBoardDescription()));
					}
				}
			}
			catch (Exception e)
			{
				System.out.println("Exception in JobBoardBean --> getJobBoardByCompanyCodeList.");
				logger.debug("Exception in JobBoardBean --> getJobBoardByCompanyCodeList.");
				e.printStackTrace();
			}
		}
		return jobBoardList;
	}
	
	public String getJobBoardDescription(String compCode, String recordCode) 
	{
			List<JobBoardDTO> jobBoardDescriptionList = new ArrayList<JobBoardDTO>();
			try
			{
				jobBoardDescriptionList = service.getJobBoardDescription(userId, compCode, recordCode);
			}
			catch (Exception e)
			{
				System.out.println("Exception in JobBoardBean --> getJobBoardDescription.");
				logger.debug("Exception in JobBoardBean --> getJobBoardDescription.");
				e.printStackTrace();
			}
			JobBoardDTO jobBoardDTO = jobBoardDescriptionList.isEmpty()?null: jobBoardDescriptionList.get(0);
			if(jobBoardDTO != null) {
				return jobBoardDTO.getJobBoardDescription().isEmpty()? jobBoardDTO.getJobBoardCode() : jobBoardDescriptionList.get(0).getJobBoardDescription();
			}
			return "";
	}

	/**
	 * @return the jobBoardList
	 */	

	public List<SelectItem> getJobBoardList() {
		return jobBoardList;
	}
	/**
	 * @param jobBoardList the jobBoardList to set
	 */
	public void setJobBoardList(List<SelectItem> jobBoardList) {
		this.jobBoardList = jobBoardList;
	}

	
}
