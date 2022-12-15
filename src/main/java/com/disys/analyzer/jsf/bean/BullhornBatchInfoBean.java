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
import com.disys.analyzer.model.dto.BullhornBatchInfoDTO;
import com.disys.analyzer.service.BullhornBatchInfoService;

/**
 * @author Sajid
 * 
 */

@ManagedBean
@SessionScoped
public class BullhornBatchInfoBean extends SpringBeanAutowiringSupport implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4209133766296292860L;
	
	private String bullhornBatchCode;
	
	private String bullhornBatchInfoId;
	
	private List<BullhornBatchInfoDTO> filteredList;

	private Logger logger = LoggerFactory.getLogger(BullhornBatchInfoBean.class);
	
	private List<SelectItem> bullhornBatchInfoList;
	String userId = FacesUtils.getCurrentUserId();
	
	@Autowired 
	private BullhornBatchInfoService service;
	
	public BullhornBatchInfoBean() 
	{
		System.out.println("BullhornBatchInfoBean constructor called....");
		logger.info("BullhornBatchInfoBean constructor called....");
	
	}

	public List<BullhornBatchInfoDTO> getBullhornBatchInfoList(String processCode, String batchCode, String searchSting) 
	{
		List<BullhornBatchInfoDTO> bullhornBatchInfoList = new ArrayList<BullhornBatchInfoDTO>();
			try
			{

				bullhornBatchInfoList = service.getBullhornBatchInfoList(this.userId, processCode, batchCode, searchSting);
				
			}
			catch (Exception e)
			{
				System.out.println("Exception in BullhornBatchInfoBean --> getBullhornBatchInfoLists.");
				logger.debug("Exception in BullhornBatchInfoBean --> getBullhornBatchInfoLists.");
				e.printStackTrace();
			}
			
			return bullhornBatchInfoList;
	}
		
	/**
	 * @return the bullhornBatchInfoList
	 */
	public List<SelectItem> getBullhornBatchInfoList() {
		return bullhornBatchInfoList;
	}
	/**
	 * @param bullhornBatchInfoList the bullhornBatchInfoList to set
	 */
	public void setBullhornBatchInfoList(List<SelectItem> bullhornBatchInfoList) {
		this.bullhornBatchInfoList = bullhornBatchInfoList;
	}

	public String getBullhornBatchCode() {
		return bullhornBatchCode;
	}

	public void setBullhornBatchCode(String bullhornBatchCode) {
		this.bullhornBatchCode = bullhornBatchCode;
	}

	public String getBullhornBatchInfoId() {
		return bullhornBatchInfoId;
	}

	public void setBullhornBatchInfoId(String bullhornBatchInfoId) {
		this.bullhornBatchInfoId = bullhornBatchInfoId;
	}

	public List<BullhornBatchInfoDTO> getFilteredList() {
		return filteredList;
	}

	public void setFilteredList(List<BullhornBatchInfoDTO> filteredList) {
		this.filteredList = filteredList;
	}
	
}
