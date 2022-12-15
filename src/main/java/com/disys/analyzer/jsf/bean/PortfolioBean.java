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

import com.disys.analyzer.config.util.Constants;
import com.disys.analyzer.config.util.FacesUtils;
import com.disys.analyzer.model.dto.PortfolioDTO;
import com.disys.analyzer.service.PortfolioService;

@ManagedBean
@SessionScoped
public class PortfolioBean extends SpringBeanAutowiringSupport implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4209133766296292860L;

	private Logger logger = LoggerFactory.getLogger(PortfolioBean.class);
	
	private List<SelectItem> selectedPortfolio;
	
	@Autowired 
	private PortfolioService service;
	
	public PortfolioBean() 
	{
		System.out.println("PortfolioBean constructor called....");
		logger.info("PortfolioBean constructor called....");
	}
	
	/**
	 * @author Saravanan.Dhurairaj  
	 * getPortfolio is list string method.
	 * It is used to get all portfoiloCode from PortfolioData table
	 * Procedure used dbo.getAllPortfolios
	 * Table used PortfolioData
	 * 
	 */
	public List<SelectItem> getAllPortfolios(String companyCode) 
	{
		if(companyCode == null || companyCode.isEmpty())
		{
			companyCode = Constants.DEFAULT_COMPANY_CODE;			
		}
		if (selectedPortfolio == null) 
		{
			selectedPortfolio = new ArrayList<SelectItem>();
			try
			{
				List<PortfolioDTO> portfolio = service.getAllPortfolios(FacesUtils.getCurrentUserId(), companyCode);

				if (portfolio != null)
				{
					for (PortfolioDTO p:portfolio)
					{
						selectedPortfolio.add(new SelectItem(p.getPortfolioCode(), p.getPortfolioCode().concat(" - ").concat(p.getPortfolioDescription())));
						//selectedPortfolio.add(new SelectItem(p.getPortfolioCode(), p.getPortfolioDescription()));
					}
				}
			}
			catch (Exception e)
			{
				System.out.println("Exception in PortfolioBean --> getPortfolioList.");
				logger.debug("Exception in PortfolioBean --> getPortfolioList.");
				e.printStackTrace();
			}
		}
		return selectedPortfolio;
	}
	
	/**
	 * @return the selectedState
	 */
	public List<SelectItem> getSelectedPortfolio() {
		return selectedPortfolio;
	}
	/**
	 * @param selectedState the selectedState to set
	 */
	public void setSelectedPortfolio(List<SelectItem> selectedPortfolio) {
		this.selectedPortfolio = selectedPortfolio;
	}
	
}
