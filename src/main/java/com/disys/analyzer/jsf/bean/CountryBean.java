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
import com.disys.analyzer.model.dto.CountryDTO;
import com.disys.analyzer.service.CountryService;

@ManagedBean
@SessionScoped
public class CountryBean extends SpringBeanAutowiringSupport implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4209133766296292860L;

	private Logger logger = LoggerFactory.getLogger(CountryBean.class);
	
	private List<SelectItem> selectedCountry;
	String userId = FacesUtils.getCurrentUserId();
	
	@Autowired 
	private CountryService service;
	
	public CountryBean() 
	{
		System.out.println("CountryBean constructor called....");
		logger.info("CountryBean constructor called....");
	}
	/**
	 * @author Saravanan.Dhurairaj  
	 * getCountryList is list string method.
	 * It is used to get all CountryList  from states table
	 * Procedure used [dbo].[getAllCountries]
	 * Table used states
	 * 
	 */
	public List<SelectItem> getCountryList() 
	{
		if (selectedCountry == null) 
		{
			selectedCountry = new ArrayList<SelectItem>();
			try
			{
				List<CountryDTO> countries = service.getAllCountries(FacesUtils.getCurrentUserId());
				if (countries != null)
				{
					for (CountryDTO c:countries)
					{
						
						selectedCountry.add(new SelectItem(c.getCountryCode(), c.getCountryName()));
					}
				}
			}
			catch (Exception e)
			{
				System.out.println("Exception in CountryBean --> getCountryList.");
				logger.debug("Exception in CountryBean --> getCountryList.");
				e.printStackTrace();
			}
		}
		return selectedCountry;
	}
	
	/**
	 * @return the selectedCountry
	 */
	public List<SelectItem> getSelectedCountry() {
		return selectedCountry;
	}
	/**
	 * @param selectedCountry the selectedCountry to set
	 */
	public void setSelectedCountry(List<SelectItem> selectedCountry) {
		this.selectedCountry = selectedCountry;
	}	
	
}
