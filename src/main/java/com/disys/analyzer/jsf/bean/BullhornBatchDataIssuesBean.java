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
import com.disys.analyzer.model.dto.BullhornBatchDataIssuesDTO;
import com.disys.analyzer.service.BullhornBatchDataIssuesService;

/**
 * @author Sajid
 * 
 */

@ManagedBean
@SessionScoped
public class BullhornBatchDataIssuesBean extends SpringBeanAutowiringSupport implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4209133766296292860L;

	private Logger logger = LoggerFactory.getLogger(BullhornBatchDataIssuesBean.class);
	
	private List<SelectItem> bullhornBatchDataIssuesList;
	String userId = FacesUtils.getCurrentUserId();
	
	@Autowired 
	private BullhornBatchDataIssuesService service;
	
	public BullhornBatchDataIssuesBean() 
	{
		System.out.println("BullhornBatchDataIssuesBean constructor called....");
		logger.info("BullhornBatchDataIssuesBean constructor called....");
	
	}

	public List<BullhornBatchDataIssuesDTO> getBullhornBatchDataIssuesList(Integer dataProcessedId) 
	{
		List<BullhornBatchDataIssuesDTO> bullhornBatchDataIssuesList = new ArrayList<BullhornBatchDataIssuesDTO>();
			try
			{

				bullhornBatchDataIssuesList = service.getBullhornBatchDataIssuesList(this.userId, dataProcessedId);
				
			}
			catch (Exception e)
			{
				System.out.println("Exception in BullhornBatchDataIssuesBean --> getBullhornBatchDataIssuesLists.");
				logger.debug("Exception in BullhornBatchDataIssuesBean --> getBullhornBatchDataIssuesLists.");
				e.printStackTrace();
			}
			
			return bullhornBatchDataIssuesList;
	}
		
	/**
	 * @return the bullhornBatchDataIssuesList
	 */
	public List<SelectItem> getBullhornBatchDataIssuesList() {
		return bullhornBatchDataIssuesList;
	}
	/**
	 * @param bullhornBatchDataIssuesList the bullhornBatchDataIssuesList to set
	 */
	public void setBullhornBatchDataIssuesList(List<SelectItem> bullhornBatchDataIssuesList) {
		this.bullhornBatchDataIssuesList = bullhornBatchDataIssuesList;
	}
	
}
