/**
 * 
 */
package com.disys.analyzer.jsf.bean;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import org.springframework.web.jsf.FacesContextUtils;

import com.disys.analyzer.config.util.Constants;
import com.disys.analyzer.config.util.FacesUtils;
import com.disys.analyzer.model.Analyser;
import com.disys.analyzer.model.dto.AnalyserDTO;
import com.disys.analyzer.service.AnalyserService;

/**
 * @author Sajid
 * @since Nov 13, 2019
 */
@ManagedBean
@ViewScoped
public class RejectedAnalyserListBean extends SpringBeanAutowiringSupport implements Serializable
{
	private static final long serialVersionUID = -7157302311661156860L;
	private Logger logger = LoggerFactory.getLogger(AnalyserListBean.class);
	@Autowired private AnalyserService service;
	private Analyser analyser;
	private List<Analyser> analyserList;
	private List<Analyser> filteredList;
	private AnalyserDTO selectedAnalyser;
	private String searchString;
	private String companyCodeLocal;
	
	@PostConstruct
	public void init()
	{
		try
		{
			FacesContextUtils.getRequiredWebApplicationContext(FacesContext.getCurrentInstance()).getAutowireCapableBeanFactory().autowireBean(this);
			eraseFilter();
		}
		catch (Exception ex)
		{
			logger.debug(ex.getMessage());
		}
	}
	
	public RejectedAnalyserListBean()
	{
		FacesContext fc = FacesUtils.getFacesContext();
		ExternalContext externalContext = fc.getExternalContext();
		Map<String, String> requestMap = externalContext
				.getRequestParameterMap();
		requestMap.entrySet().forEach(entry ->
		{
			System.out.println("Key : " + entry.getKey() + " Value : " + entry.getValue());
			if (entry.getKey().equals("compCode"))
			{
				if(entry.getValue() == null || entry.getValue().isEmpty())
				{	
					companyCodeLocal = Constants.DEFAULT_COMPANY_CODE;
				}
				else
				{	
					companyCodeLocal = entry.getValue();
				}
				
			}});
		
		System.out.println("RejectedAnalyserListBean constructor companyCode " +companyCodeLocal);
		logger.info("RejectedAnalyserListBean constructor companyCode " +companyCodeLocal);
		
		analyser = new Analyser();
	}
	
	public void filter()
	{
		if (searchString.equals("") || searchString == null)
		{
			searchString = "ALL";
		}
		
		analyserList = service.getRejectedAnalyserList(FacesUtils.getCurrentUserId(), "null", searchString, companyCodeLocal);
		
		System.out.println("{call " + FacesUtils.SCHEMA_WIRELESS + ".spGetRejectedAnalyserList(userId, orderBy, searchString, companyCodeLocal)");
		System.out.println(
		        "{exec " + FacesUtils.SCHEMA_WIRELESS + ".spGetRejectedAnalyserList('" + FacesUtils.getCurrentUserId() + "', 'null' , '" + searchString + "', '" + companyCodeLocal + "')");
		logger.debug("{exec " + FacesUtils.SCHEMA_WIRELESS + ".spGetRejectedAnalyserList(userId, userList, orderBy, searchString, companyCodeLocal)");
		logger.debug(
		        "{exec " + FacesUtils.SCHEMA_WIRELESS + ".spGetRejectedAnalyserList('" + FacesUtils.getCurrentUserId() + "', 'null', '" + searchString + "', '" + companyCodeLocal + "')");
		
		logger.info("Total list size is : " + analyserList.size());
	}
	
	public void eraseFilter()
	{
		this.searchString = "";
		this.filter();
	}
	
	public void pageSizeChangeListener(AjaxBehaviorEvent event)
	{
		this.filter();
	}
	
	/**
	 * @return the analyser
	 */
	public Analyser getAnalyser()
	{
		return analyser;
	}
	
	/**
	 * @param analyser
	 *            the analyser to set
	 */
	public void setAnalyser(Analyser analyser)
	{
		this.analyser = analyser;
	}
	
	/**
	 * @return the analyserList
	 */
	public List<Analyser> getAnalyserList()
	{
		return analyserList;
	}
	
	/**
	 * @param analyserList
	 *            the analyserList to set
	 */
	public void setAnalyserList(List<Analyser> analyserList)
	{
		this.analyserList = analyserList;
	}
	
	/**
	 * @return the selectedAnalyser
	 */
	public AnalyserDTO getSelectedAnalyser()
	{
		return selectedAnalyser;
	}
	
	/**
	 * @param selectedAnalyser
	 *            the selectedAnalyser to set
	 */
	public void setSelectedAnalyser(AnalyserDTO selectedAnalyser)
	{
		this.selectedAnalyser = selectedAnalyser;
	}
	
	/**
	 * @return the searchString
	 */
	public String getSearchString()
	{
		return searchString;
	}
	
	/**
	 * @param searchString
	 *            the searchString to set
	 */
	public void setSearchString(String searchString)
	{
		this.searchString = searchString;
	}
	
	/**
	 * @return the filteredList
	 */
	public List<Analyser> getFilteredList()
	{
		return filteredList;
	}
	
	/**
	 * @param filteredList
	 *            the filteredList to set
	 */
	public void setFilteredList(List<Analyser> filteredList)
	{
		this.filteredList = filteredList;
	}
	
	public AnalyserService getService()
	{
		return service;
	}
	
	public void setService(AnalyserService service)
	{
		this.service = service;
	}

	public String getCompanyCodeLocal() {
		return companyCodeLocal;
	}

	public void setCompanyCodeLocal(String companyCodeLocal) {
		this.companyCodeLocal = companyCodeLocal;
	}
	
}