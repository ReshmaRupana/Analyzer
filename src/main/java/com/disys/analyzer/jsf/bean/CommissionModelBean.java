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
import com.disys.analyzer.model.dto.CommissionModelDTO;

import com.disys.analyzer.service.CommissionModelService;

@ManagedBean
@ViewScoped
public class CommissionModelBean extends SpringBeanAutowiringSupport implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4209133766296292860L;

	private Logger logger = LoggerFactory.getLogger(CommissionModelBean.class);
	
	private List<SelectItem> commissionModelList;
	String userId = FacesUtils.getCurrentUserId();
	
	@Autowired 
	private CommissionModelService service;
	
	public CommissionModelBean() 
	{
		System.out.println("CommissionModelBean constructor called....");
		logger.info("CommissionModelBean constructor called....");
	}
	
	public List<SelectItem> getCommissionModelByCompanyCodeList(String compCode) 
	{
		if (commissionModelList == null) 
		{
			commissionModelList = new ArrayList<SelectItem>();
			try
			{
				List<CommissionModelDTO> commissionModelLists = service.getCommissionModelList(userId, compCode);
				if (commissionModelLists != null)
				{
					for (CommissionModelDTO c:commissionModelLists)
					{
						
						commissionModelList.add(new SelectItem(c.getCommissionModelCode(), c.getCommissionModelDescription()));
					}
				}
			}
			catch (Exception e)
			{
				System.out.println("Exception in CommissionModelBean --> getCommissionModelByCompanyCodeList.");
				logger.debug("Exception in CommissionModelBean --> getCommissionModelByCompanyCodeList.");
				e.printStackTrace();
			}
		}
		return commissionModelList;
	}
	
	public String getCommissionModelDescription(String compCode, String recordCode) 
	{
			List<CommissionModelDTO> commissionModelList = new ArrayList<CommissionModelDTO>();
			try
			{
				commissionModelList = service.getCommissionModelDescription(userId, compCode, recordCode);
			}
			catch (Exception e)
			{
				System.out.println("Exception in CommissionModelBean --> getCommissionModelDescription.");
				logger.debug("Exception in CommissionModelBean --> getCommissionModelDescription.");
				e.printStackTrace();
			}
			CommissionModelDTO commissionModelDTO = commissionModelList.isEmpty()?null: commissionModelList.get(0);
			if(commissionModelDTO != null) {
				return commissionModelDTO.getCommissionModelDescription().isEmpty()? commissionModelDTO.getCommissionModelCode() : commissionModelList.get(0).getCommissionModelDescription();
			}
			return "";
	}

	/**
	 * @return the commissionModelList
	 */
	public List<SelectItem> getCommissionModelList() {
		return commissionModelList;
	}
	/**
	 * @param commissionModelList the commissionModelList to set
	 */
	public void setCommissionModelList(List<SelectItem> commissionModelList) {
		this.commissionModelList = commissionModelList;
	}

}
