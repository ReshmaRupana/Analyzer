/**
 * 
 */
package com.disys.analyzer.jsf.bean;

import java.io.Serializable;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import org.springframework.web.jsf.FacesContextUtils;

import com.disys.analyzer.config.util.Constants;
import com.disys.analyzer.config.util.FacesMessageSeverity;
import com.disys.analyzer.config.util.FacesUtils;
import com.disys.analyzer.model.dto.Supplier;
import com.disys.analyzer.service.AnalyserSubContractorService;

/**
 * @author Sajid
 * @since August 21, 2018
 *
 */
@ManagedBean
@ViewScoped
public class AnalyserSubcontractorInfoBean extends SpringBeanAutowiringSupport
implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static Logger logger = LoggerFactory
			.getLogger(AnalyserSubcontractorInfoBean.class);
	
	private String contractorId;
	
	@Autowired
	AnalyserSubContractorService service;

	private Supplier supplier;	
	
	@PostConstruct
	public void init() {
		try {
			FacesContextUtils
					.getRequiredWebApplicationContext(
							FacesContext.getCurrentInstance())
					.getAutowireCapableBeanFactory().autowireBean(this);
		} catch (Exception ex) {
			logger.debug(ex.getMessage());
		}
	}

	public AnalyserSubcontractorInfoBean() {
		FacesContext fc = FacesUtils.getFacesContext();
		ExternalContext externalContext = fc.getExternalContext();
		Map<String, String> requestMap = externalContext
				.getRequestParameterMap();

		requestMap.entrySet().forEach(
				entry -> {
					System.out.println("Key : " + entry.getKey() + " Value : "
							+ entry.getValue());
					if (entry.getKey().equals("contractorId")) {
						contractorId = entry.getValue();
					}
				});
		
		supplier = service.getSupplierInfo(contractorId);
		System.out.println("Supplier found with id : "+contractorId);
	}
	
	public void discardChanges()
	{
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Analyzer commission person changes discarded", "Changes discarded!");
		FacesUtils.redirect("/protected/analyser-subcontractors.xhtml", message);
	}
	
	public void update()
	{
		try
		{	
			if(supplier.getLegalName() == null || supplier.getLegalName().trim().length() == 0)
			{
				FacesUtils.addGlobalMessage(FacesMessageSeverity.ERROR, "Company name missing");
				return;
			}
			if(supplier.getCompanyCode() == null || supplier.getCompanyCode().isEmpty())
			{
				supplier.setCompanyCode(Constants.DEFAULT_COMPANY_CODE);
			}
			Boolean result = service.updateSupplierInfo(FacesUtils.getCurrentUserId(), Integer.valueOf(contractorId),
					supplier.getCompanyName(), supplier.getAddress1(), supplier.getAddress2(), supplier.getCity(),
					supplier.getState(), supplier.getZipCode(),supplier.getCompanyCode());
			System.out.println("Result is : " + result);
			
			FacesMessage message = null;
			if (result)
			{
				message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Subcontractor updated", "Operation is successfully completed!");
			}
			else
			{
				message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Couldn't update", "Operation not successfully!");
			}
				
			FacesUtils.redirect("/protected/analyser-subcontractors.xhtml", message);
		}
		catch (Exception e)
		{
			String errorMessage = "Error updating subcontractor";
			FacesUtils.addGlobalMessage(FacesMessageSeverity.ERROR, errorMessage);
			logger.error(errorMessage);
		}
	}

	public String getContractorId()
	{
		return contractorId;
	}

	public void setContractorId(String contractorId)
	{
		this.contractorId = contractorId;
	}

	public Supplier getSupplier()
	{
		return supplier;
	}

	public void setSupplier(Supplier supplier)
	{
		this.supplier = supplier;
	}
}
