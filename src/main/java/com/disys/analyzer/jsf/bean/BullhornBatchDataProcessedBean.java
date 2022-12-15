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
import com.disys.analyzer.model.dto.BullhornBatchDataProcessedDTO;
import com.disys.analyzer.service.BullhornBatchDataProcessedService;

/**
 * @author Sajid
 * 
 */

@ManagedBean
@SessionScoped
public class BullhornBatchDataProcessedBean extends SpringBeanAutowiringSupport implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4209133766296292860L;
	
	private String bullhornBatchDataProcessedId;

	private Logger logger = LoggerFactory.getLogger(BullhornBatchDataProcessedBean.class);
	
	private List<BullhornBatchDataProcessedDTO> bullhornBatchDataProcessedList;
	String userId = FacesUtils.getCurrentUserId();
	
	@Autowired 
	private BullhornBatchDataProcessedService service;
	
	
	public List<BullhornBatchDataProcessedDTO> getBullhornBatchDataProcessedList(Integer batchInfoId) 
	{
		List<BullhornBatchDataProcessedDTO> bullhornBatchDataProcessedList = new ArrayList<BullhornBatchDataProcessedDTO>();
			try
			{

				bullhornBatchDataProcessedList = service.getBullhornBatchDataProcessedList(this.userId, batchInfoId);
				
			}
			catch (Exception e)
			{
				System.out.println("Exception in BullhornBatchDataProcessedBean --> getBullhornBatchDataProcessedLists.");
				logger.debug("Exception in BullhornBatchDataProcessedBean --> getBullhornBatchDataProcessedLists.");
				e.printStackTrace();
			}
			
			return bullhornBatchDataProcessedList;
	}
	
	public BullhornBatchDataProcessedBean() 
	{
		System.out.println("BullhornBatchDataProcessedBean constructor called....");
		logger.info("BullhornBatchDataProcessedBean constructor called....");
	
	}
		
	/**
	 * @return the bullhornBatchDataProcessedList
	 */
	public List<BullhornBatchDataProcessedDTO> getBullhornBatchDataProcessedList() {
		
		return this.bullhornBatchDataProcessedList;
	}

	public void setBullhornBatchDataProcessedList(List<BullhornBatchDataProcessedDTO> bullhornBatchDataProcessedList) {
		this.bullhornBatchDataProcessedList = bullhornBatchDataProcessedList;
	}

	public String getBullhornBatchDataProcessedId() {
		return bullhornBatchDataProcessedId;
	}

	public void setBullhornBatchDataProcessedId(String bullhornBatchDataProcessedId) {
		this.bullhornBatchDataProcessedId = bullhornBatchDataProcessedId;
	}
}
