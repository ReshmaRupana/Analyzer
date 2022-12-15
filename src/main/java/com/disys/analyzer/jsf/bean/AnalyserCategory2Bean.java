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
import com.disys.analyzer.model.dto.AnalyserCategory2DTO;
import com.disys.analyzer.service.AnalyserCategory2Service;

@ManagedBean
@ViewScoped
public class AnalyserCategory2Bean extends SpringBeanAutowiringSupport implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4209133766296292860L;

	private Logger logger = LoggerFactory.getLogger(AnalyserCategory2Bean.class);
	
	private List<SelectItem> analyserCategory2List;
	String userId = FacesUtils.getCurrentUserId();
	
	@Autowired 
	private AnalyserCategory2Service service;
	
	public AnalyserCategory2Bean() 
	{
		System.out.println("AnalyserCategory2Bean constructor called....");
		logger.info("AnalyserCategory2Bean constructor called....");
	}
	
	public List<SelectItem> getAnalyserCategory2ByCompanyCodeList(String compCode) 
	{
		if (analyserCategory2List == null) 
		{
			analyserCategory2List = new ArrayList<SelectItem>();
			try
			{
				List<AnalyserCategory2DTO> analyserCategory2Lists = service.getAnalyserCategory2List(userId, compCode);
				if (analyserCategory2Lists != null)
				{
					for (AnalyserCategory2DTO c:analyserCategory2Lists)
					{
						
						analyserCategory2List.add(new SelectItem(c.getAnalyserCategory2Code(), c.getAnalyserCategory2Description()));
					}
				}
			}
			catch (Exception e)
			{
				System.out.println("Exception in AnalyserCategory2Bean --> getAnalyserCategory2ByCompanyCodeList.");
				logger.debug("Exception in AnalyserCategory2Bean --> getAnalyserCategory2ByCompanyCodeList.");
				e.printStackTrace();
			}
		}
		return analyserCategory2List;
	}
	
	public String getAnalyserCategory2Description(String compCode, String recordCode) 
	{
			List<AnalyserCategory2DTO> analyzerCategory2DescriptionList = new ArrayList<AnalyserCategory2DTO>();
			try
			{
				analyzerCategory2DescriptionList = service.getAnalyserCategory2Description(userId, compCode, recordCode);
			}
			catch (Exception e)
			{
				System.out.println("Exception in AnalyserCategory2Bean --> getAnalyzerCategory2Description.");
				logger.debug("Exception in AnalyserCategory2Bean --> getAnalyzerCategory2Description.");
				e.printStackTrace();
			}
			AnalyserCategory2DTO analyserCategory2DTO = analyzerCategory2DescriptionList.isEmpty()?null: analyzerCategory2DescriptionList.get(0);
			if(analyserCategory2DTO != null) {
				return analyserCategory2DTO.getAnalyserCategory2Description().isEmpty()? analyserCategory2DTO.getAnalyserCategory2Code() : analyzerCategory2DescriptionList.get(0).getAnalyserCategory2Description();
			}
			return "";
	}

	/**
	 * @return the analyserCategory2List
	 */
	public List<SelectItem> getAnalyserCategory2List() {
		return analyserCategory2List;
	}
	/**
	 * @param analyserCategory2List the analyserCategory2List to set
	 */

	public void setAnalyserCategory2List(List<SelectItem> analyserCategory2List) {
		this.analyserCategory2List = analyserCategory2List;
	}
	
}
