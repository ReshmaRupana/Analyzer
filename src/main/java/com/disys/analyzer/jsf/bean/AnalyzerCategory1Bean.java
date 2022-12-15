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
import com.disys.analyzer.model.dto.AnalyzerCategory1DTO;
import com.disys.analyzer.service.AnalyzerCategory1Service;

@ManagedBean
@ViewScoped
public class AnalyzerCategory1Bean extends SpringBeanAutowiringSupport implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4209133766296292860L;

	private Logger logger = LoggerFactory.getLogger(AnalyzerCategory1Bean.class);
	
	private List<SelectItem> analyzerCategory1List;
	String userId = FacesUtils.getCurrentUserId();
	
	@Autowired 
	private AnalyzerCategory1Service service;
	
	public AnalyzerCategory1Bean() 
	{
		System.out.println("AnalyzerCategory1Bean constructor called....");
		logger.info("AnalyzerCategory1Bean constructor called....");
	}
	
	public List<SelectItem> getAnalyzerCategory1ByCompanyCodeList(String compCode) 
	{
		if (analyzerCategory1List == null) 
		{
			analyzerCategory1List = new ArrayList<SelectItem>();
			try
			{
				List<AnalyzerCategory1DTO> analyzerCategory1Lists = service.getAnalyzerCategory1List(userId, compCode);
				if (analyzerCategory1Lists != null)
				{
					for (AnalyzerCategory1DTO c:analyzerCategory1Lists)
					{						
						analyzerCategory1List.add(new SelectItem(c.getAnalyzerCategory1Code(), c.getAnalyzerCategory1Description()));
					}
				}
			}
			catch (Exception e)
			{
				System.out.println("Exception in AnalyzerCategory1Bean --> getAnalyzerCategory1ByCompanyCodeList.");
				logger.debug("Exception in AnalyzerCategory1Bean --> getAnalyzerCategory1ByCompanyCodeList.");
				e.printStackTrace();
			}
		}
		return analyzerCategory1List;
	}
	
	public String getAnalyzerCategory1Description(String compCode, String recordCode) 
	{
			List<AnalyzerCategory1DTO> analyzerCategory1DescriptionList = new ArrayList<AnalyzerCategory1DTO>();
			try
			{
				analyzerCategory1DescriptionList = service.getAnalyzerCategory1Description(userId, compCode, recordCode);
			}
			catch (Exception e)
			{
				System.out.println("Exception in AnalyzerCategory1Bean --> getAnalyzerCategory1Description.");
				logger.debug("Exception in AnalyzerCategory1Bean --> getAnalyzerCategory1Description.");
				e.printStackTrace();
			}
			AnalyzerCategory1DTO analyzerCategory1DTO = analyzerCategory1DescriptionList.isEmpty()?null: analyzerCategory1DescriptionList.get(0);
			if(analyzerCategory1DTO != null) {
				return analyzerCategory1DTO.getAnalyzerCategory1Description().isEmpty()? analyzerCategory1DTO.getAnalyzerCategory1Code() : analyzerCategory1DescriptionList.get(0).getAnalyzerCategory1Description();
			}
			return "";
	}

	/**
	 * @return the analyzerCategory1List
	 */
	public List<SelectItem> getAnalyzerCategory1List() {
		return analyzerCategory1List;
	}
	/**
	 * @param analyzerCategory1List the analyzerCategory1List to set
	 */
	public void setAnalyzerCategory1List(List<SelectItem> analyzerCategory1List) {
		this.analyzerCategory1List = analyzerCategory1List;
	}

	
}
