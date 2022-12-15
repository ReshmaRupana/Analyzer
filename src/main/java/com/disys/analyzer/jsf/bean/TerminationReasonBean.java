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
import com.disys.analyzer.model.dto.TerminationReasonDTO;
import com.disys.analyzer.service.TerminationReasonService;

@ManagedBean
@ViewScoped
public class TerminationReasonBean extends SpringBeanAutowiringSupport implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4209133766296292860L;

	private Logger logger = LoggerFactory.getLogger(TerminationReasonBean.class);
	
	private List<SelectItem> terminationReasonList;
	String userId = FacesUtils.getCurrentUserId();
	
	@Autowired 
	private TerminationReasonService service;
	
	public TerminationReasonBean() 
	{
		System.out.println("TerminationReasonBean constructor called....");
		logger.info("TerminationReasonBean constructor called....");
	}
	
	public List<SelectItem> getTerminationReasonBeanByCompanyCodeList(String compCode) 
	{
		if (terminationReasonList == null) 
		{
			terminationReasonList = new ArrayList<SelectItem>();
			try
			{
				List<TerminationReasonDTO> reasonLists = service.getTerminationReasonList(userId, compCode);
				if (reasonLists != null)
				{
					for (TerminationReasonDTO c:reasonLists)
					{						
						terminationReasonList.add(new SelectItem(c.getTerminationReasonCode(), c.getTerminationReasonDescription()));
					}
				}
			}
			catch (Exception e)
			{
				System.out.println("Exception in TerminationReasonBean --> getTerminationReasonBeanByCompanyCodeList.");
				logger.debug("Exception in TerminationReasonBean --> getTerminationReasonBeanByCompanyCodeList.");
				e.printStackTrace();
			}
		}
		return terminationReasonList;
	}
	
	public String getReasonDescription(String compCode, String recordCode) 
	{
			List<TerminationReasonDTO> terminationReasonDescriptionList = new ArrayList<TerminationReasonDTO>();
			try
			{
				terminationReasonDescriptionList = service.getTerminationReasonDescription(userId, compCode, recordCode);
			}
			catch (Exception e)
			{
				System.out.println("Exception in TerminationReasonBean --> getTerminationReasonDescription.");
				logger.debug("Exception in TerminationReasonBean --> getTerminationReasonDescription.");
				e.printStackTrace();
			}
			TerminationReasonDTO terminationReasonDTO = terminationReasonDescriptionList.isEmpty()?null: terminationReasonDescriptionList.get(0);
			if(terminationReasonDTO != null) {
				return terminationReasonDTO.getTerminationReasonDescription().isEmpty()? terminationReasonDTO.getTerminationReasonCode() : terminationReasonDescriptionList.get(0).getTerminationReasonDescription();
			}
			return "";
	}

	/**
	 * @return the terminationReasonList
	 */
	public List<SelectItem> getTerminationReasonList() {
		return terminationReasonList;
	}
	/**
	 * @param terminationReasonList the terminationReasonList to set
	 */
	public void setTerminationReasonList(List<SelectItem> terminationReasonList) {
		this.terminationReasonList = terminationReasonList;
	}

	
}
