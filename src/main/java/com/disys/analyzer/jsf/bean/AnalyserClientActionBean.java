/**
 * 
 */
package com.disys.analyzer.jsf.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import org.springframework.web.jsf.FacesContextUtils;

import com.disys.analyzer.config.util.Constants;
import com.disys.analyzer.config.util.FacesUtils;
import com.disys.analyzer.model.PSBillingType;
import com.disys.analyzer.model.PSOperatingUnitOrVertical;
import com.disys.analyzer.model.PSProduct;
import com.disys.analyzer.model.dto.AnalyserClientDatabaseDTO;
import com.disys.analyzer.reports.util.ReportUtil;
import com.disys.analyzer.service.AnalyserClientService;

/**
 * @author Sajid
 * @since Oct 24, 2018
 */
@ManagedBean
@ViewScoped
public class AnalyserClientActionBean extends SpringBeanAutowiringSupport implements Serializable
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4213592558797672968L;
	
	private static Logger logger = LoggerFactory.getLogger(AnalyserClientActionBean.class);
	
	@Autowired
	private AnalyserClientService service;
	
	private AnalyserClientDatabaseDTO client;
	
	private String clientId;
	private String action;
	
	private boolean showSaveButton;
	
	private List<SelectItem> contactState;
	private List<SelectItem> noticeState;
	private List<SelectItem> termEnd;
	private List<SelectItem> paymentTerms;
	private List<SelectItem> invoiceFrequency;
	private List<SelectItem> timesheetFrequency;
	private List<SelectItem> disputeTimeFrame;
	private List<SelectItem> insWorkmans;
	private List<SelectItem> insEmployersLiability;
	private List<SelectItem> insCGL;
	private List<SelectItem> insAuto;
	private List<SelectItem> insUmbrella;
	private List<SelectItem> insProfessional;
	private List<SelectItem> insCancel;
	private List<SelectItem> industry;
	private List<SelectItem> insBondAmount;
	private List<SelectItem> typeOfAgreement;
	private List<SelectItem> rightToHireMonths;
	private List<SelectItem> subContractingAllowed;
	private List<SelectItem> promptPay;
	
	private List<SelectItem> pSVerticalIds;
	private List<SelectItem> pSVerticalCodes;
	private List<SelectItem> pSVerticalDescriptions;
	private List<PSOperatingUnitOrVertical> allVerticals;
	
	private List<SelectItem> pSProductIds;
	private List<SelectItem> pSProductCodes;
	private List<SelectItem> pSProductDescriptions;
	private List<PSProduct> allProducts;
	
	private List<SelectItem> pSBillingTypeIds;
	private List<SelectItem> pSBillingTypeCodes;
	private List<SelectItem> pSBillingTypeLongNames;
	private List<PSBillingType> allBillingTypes;
	
	private List<SelectItem> entityNames;
	
	@PostConstruct
	public void init()
	{
		try
		{
			FacesContextUtils.getRequiredWebApplicationContext(FacesContext.getCurrentInstance()).getAutowireCapableBeanFactory().autowireBean(this);
			getAllVerticals();
			getAllProducts();
			getAllBillingTypes();
		}
		catch (Exception ex)
		{
			logger.debug(ex.getMessage());
		}
	}
	
	public AnalyserClientActionBean()
	{
		
		// String userId = FacesUtils.getCurrentUserId();
		FacesContext fc = FacesUtils.getFacesContext();
		ExternalContext externalContext = fc.getExternalContext();
		Map<String, String> requestMap = externalContext.getRequestParameterMap();
		
		requestMap.entrySet().forEach(entry ->
		{
			System.out.println("Key : " + entry.getKey() + " Value : " + entry.getValue());
			if (entry.getKey().equals("clientId"))
			{
				clientId = entry.getValue();
			}
			else if (entry.getKey().equals("action"))
			{
				action = entry.getValue();
			}
		});
		
		if (clientId == null)
		{
			client = new AnalyserClientDatabaseDTO();
			action = "Add";
			showSaveButton = true;
			
		}
		else
		{
			/*
			 * analyser = analyserService.getAnalyserInfo(analyserId);
			 * System.out.println("Successfully loaded analyser with id : " +
			 * analyser.getAnalyserId());
			 * logger.debug("Successfully loaded analyser with id : " +
			 * analyser.getAnalyserId());
			 */
			client = service.getClientDatabaseInfo(clientId);
			
			if (client.getChkBackgroundAmount() != null)
			{
				if (client.getChkBackgroundAmount().equals("Yes") || client.getChkBackgroundAmount().equals("Y"))
				{
					client.setChkBackgroundAmount("true");
				}
				else
				{
					client.setChkBackgroundAmount("false");
				}
			}
			else
			{
				client.setChkBackgroundAmount("false");
			}
			
			if (client.getChkCreditCheckAmount() != null)
			{
				if (client.getChkCreditCheckAmount().equals("Yes") || client.getChkCreditCheckAmount().equals("Y"))
				{
					client.setChkCreditCheckAmount("true");
				}
				else
				{
					client.setChkCreditCheckAmount("false");
				}
			}
			else
			{
				client.setChkCreditCheckAmount("false");
			}
			
			if (client.getChkDrugTestAmount() != null)
			{
				if (client.getChkDrugTestAmount().equals("Yes") || client.getChkDrugTestAmount().equals("Y"))
				{
					client.setChkDrugTestAmount("true");
				}
				else
				{
					client.setChkDrugTestAmount("false");
				}
			}
			else
			{
				client.setChkDrugTestAmount("false");
			}
			
			if (client.getTmContract() != null)
			{
				if (client.getTmContract().equals("Yes") || client.getTmContract().equals("Y"))
				{
					client.setTmContract("true");
				}
				else
				{
					client.setTmContract("false");
				}
			}
			else
			{
				client.setTmContract("false");
			}
			
			showSaveButton = false;
		}
	}
	
	public void saveClient()
	{
		
		logger.debug("About to save analyser form...");
		int actionType = 1; // -- insert
		
		if (client.getChkBackgroundAmount().equals("true"))
		{
			client.setChkBackgroundAmount("Yes");
		}
		
		if (client.getChkCreditCheckAmount().equals("true"))
		{
			client.setChkCreditCheckAmount("Yes");
		}
		
		if (client.getChkDrugTestAmount().equals("true"))
		{
			client.setChkDrugTestAmount("Yes");
		}		
		if(client.getCompanyCode() == null || client.getCompanyCode().isEmpty())
		{
			client.setCompanyCode(Constants.DEFAULT_COMPANY_CODE);
		}
		String status = service.addUpdateAnalyserClientDatabase(client, FacesUtils.getCurrentUserId(), actionType);
		
		System.out.println("Saved Status : " + status);
		if (status == "1")
		{
			System.out.println("There was an error saving the analyser");
			
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Error saving Analyser client!");
			FacesUtils.getFacesContext().addMessage(null, message);
			return;
		}
		
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Analyzer Client Saved", "Analyser operation is successfully completed!");
		
		FacesUtils.redirect("/protected/analyser-clients.xhtml", message);
	}
	
	public void updateClient()
	{
		logger.debug("About to update analyser form...");
		int actionType = 2; // -- Modify
		
		if (client.getChkBackgroundAmount().equals("true"))
		{
			client.setChkBackgroundAmount("Yes");
		}
		
		if (client.getChkCreditCheckAmount().equals("true"))
		{
			client.setChkCreditCheckAmount("Yes");
		}
		
		if (client.getChkDrugTestAmount().equals("true"))
		{
			client.setChkDrugTestAmount("Yes");
		}
		if(client.getCompanyCode() == null || client.getCompanyCode().isEmpty())
		{
			client.setCompanyCode(Constants.DEFAULT_COMPANY_CODE);
		}
		String status = service.addUpdateAnalyserClientDatabase(client, FacesUtils.getCurrentUserId(), actionType);
		
		System.out.println("Update Status : " + status);
		if (status == "1")
		{
			System.out.println("There was an error updating the analyser");
			
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Error updating Analyser client!");
			FacesUtils.getFacesContext().addMessage(null, message);
			return;
		}
		
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Analyzer Client Updated", "Analyser operation is successfully completed!");
		
		FacesUtils.redirect("/protected/analyser-clients.xhtml", message);
	}
	
	public void discardChanges()
	{
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Analyzer Changes Discarded", "Changes discarded!");
		FacesUtils.redirect("/protected/analyser-clients.xhtml", message);
	}
	
	/**
	 * @return the client
	 */
	public AnalyserClientDatabaseDTO getClient()
	{
		return client;
	}
	
	/**
	 * @param client
	 *            the client to set
	 */
	public void setClient(AnalyserClientDatabaseDTO client)
	{
		this.client = client;
	}
	
	/**
	 * @return the clientId
	 */
	public String getClientId()
	{
		return clientId;
	}
	
	/**
	 * @param clientId
	 *            the clientId to set
	 */
	public void setClientId(String clientId)
	{
		this.clientId = clientId;
	}
	
	/**
	 * @return the action
	 */
	public String getAction()
	{
		return action;
	}
	
	/**
	 * @param action
	 *            the action to set
	 */
	public void setAction(String action)
	{
		this.action = action;
	}
	
	/**
	 * @return the showSaveButton
	 */
	public boolean isShowSaveButton()
	{
		return showSaveButton;
	}
	
	/**
	 * @param showSaveButton
	 *            the showSaveButton to set
	 */
	public void setShowSaveButton(boolean showSaveButton)
	{
		this.showSaveButton = showSaveButton;
	}
	
	/**
	 * @return the contactState
	 */
	public List<SelectItem> getContactState()
	{
		if (contactState == null)
		{
			contactState = new ArrayList<SelectItem>();
			contactState.add(new SelectItem("AL", "Alabama"));
			contactState.add(new SelectItem("AK", "Alaska"));
			contactState.add(new SelectItem("AZ", "Arizona"));
			contactState.add(new SelectItem("AR", "Arkansas"));
			contactState.add(new SelectItem("CA", "California"));
			contactState.add(new SelectItem("CO", "Colorado"));
			contactState.add(new SelectItem("CT", "Connecticut"));
			contactState.add(new SelectItem("DE", "Delaware"));
			contactState.add(new SelectItem("DC", "District Of Columbia"));
			contactState.add(new SelectItem("FL", "Florida"));
			contactState.add(new SelectItem("GA", "Georgia"));
			contactState.add(new SelectItem("HI", "Hawaii"));
			contactState.add(new SelectItem("ID", "Idaho"));
			contactState.add(new SelectItem("IL", "Illinois"));
			contactState.add(new SelectItem("IN", "Indiana"));
			contactState.add(new SelectItem("IA", "Iowa"));
			contactState.add(new SelectItem("KS", "Kansas"));
			contactState.add(new SelectItem("KY", "Kentucky"));
			contactState.add(new SelectItem("LA", "Louisiana"));
			contactState.add(new SelectItem("ME", "Maine"));
			contactState.add(new SelectItem("MD", "Maryland"));
			contactState.add(new SelectItem("MA", "Massachusetts"));
			contactState.add(new SelectItem("MI", "Michigan"));
			contactState.add(new SelectItem("MN", "Minnesota"));
			contactState.add(new SelectItem("MS", "Mississippi"));
			contactState.add(new SelectItem("MO", "Missouri"));
			contactState.add(new SelectItem("MT", "Montana"));
			contactState.add(new SelectItem("NE", "Nebraska"));
			contactState.add(new SelectItem("NV", "Nevada"));
			contactState.add(new SelectItem("NH", "New Hampshire"));
			contactState.add(new SelectItem("NJ", "New Jersey"));
			contactState.add(new SelectItem("NM", "New Mexico"));
			contactState.add(new SelectItem("NY", "New York"));
			contactState.add(new SelectItem("NC", "North Carolina"));
			contactState.add(new SelectItem("ND", "North Dakota"));
			contactState.add(new SelectItem("OH", "Ohio"));
			contactState.add(new SelectItem("OK", "Oklahoma"));
			contactState.add(new SelectItem("OR", "Oregon"));
			contactState.add(new SelectItem("PA", "Pennsylvania"));
			contactState.add(new SelectItem("RI", "Rhode Island"));
			contactState.add(new SelectItem("SC", "South Carolina"));
			contactState.add(new SelectItem("SD", "South Dakota"));
			contactState.add(new SelectItem("TN", "Tennessee"));
			contactState.add(new SelectItem("TX", "Texas"));
			contactState.add(new SelectItem("UT", "Utah"));
			contactState.add(new SelectItem("VT", "Vermont"));
			contactState.add(new SelectItem("VA", "Virginia"));
			contactState.add(new SelectItem("WA", "Washington"));
			contactState.add(new SelectItem("WV", "West Virginia"));
			contactState.add(new SelectItem("WI", "Wisconsin"));
			contactState.add(new SelectItem("WY", "Wyoming"));
		}
		return contactState;
	}
	
	/**
	 * @param contactState
	 *            the contactState to set
	 */
	public void setContactState(List<SelectItem> contactState)
	{
		this.contactState = contactState;
	}
	
	/**
	 * @return the noticeState
	 */
	public List<SelectItem> getNoticeState()
	{
		if (noticeState == null)
		{
			noticeState = new ArrayList<SelectItem>();
			noticeState.add(new SelectItem("AL", "Alabama"));
			noticeState.add(new SelectItem("AK", "Alaska"));
			noticeState.add(new SelectItem("AZ", "Arizona"));
			noticeState.add(new SelectItem("AR", "Arkansas"));
			noticeState.add(new SelectItem("CA", "California"));
			noticeState.add(new SelectItem("CO", "Colorado"));
			noticeState.add(new SelectItem("CT", "Connecticut"));
			noticeState.add(new SelectItem("DE", "Delaware"));
			noticeState.add(new SelectItem("DC", "District Of Columbia"));
			noticeState.add(new SelectItem("FL", "Florida"));
			noticeState.add(new SelectItem("GA", "Georgia"));
			noticeState.add(new SelectItem("HI", "Hawaii"));
			noticeState.add(new SelectItem("ID", "Idaho"));
			noticeState.add(new SelectItem("IL", "Illinois"));
			noticeState.add(new SelectItem("IN", "Indiana"));
			noticeState.add(new SelectItem("IA", "Iowa"));
			noticeState.add(new SelectItem("KS", "Kansas"));
			noticeState.add(new SelectItem("KY", "Kentucky"));
			noticeState.add(new SelectItem("LA", "Louisiana"));
			noticeState.add(new SelectItem("ME", "Maine"));
			noticeState.add(new SelectItem("MD", "Maryland"));
			noticeState.add(new SelectItem("MA", "Massachusetts"));
			noticeState.add(new SelectItem("MI", "Michigan"));
			noticeState.add(new SelectItem("MN", "Minnesota"));
			noticeState.add(new SelectItem("MS", "Mississippi"));
			noticeState.add(new SelectItem("MO", "Missouri"));
			noticeState.add(new SelectItem("MT", "Montana"));
			noticeState.add(new SelectItem("NE", "Nebraska"));
			noticeState.add(new SelectItem("NV", "Nevada"));
			noticeState.add(new SelectItem("NH", "New Hampshire"));
			noticeState.add(new SelectItem("NJ", "New Jersey"));
			noticeState.add(new SelectItem("NM", "New Mexico"));
			noticeState.add(new SelectItem("NY", "New York"));
			noticeState.add(new SelectItem("NC", "North Carolina"));
			noticeState.add(new SelectItem("ND", "North Dakota"));
			noticeState.add(new SelectItem("OH", "Ohio"));
			noticeState.add(new SelectItem("OK", "Oklahoma"));
			noticeState.add(new SelectItem("OR", "Oregon"));
			noticeState.add(new SelectItem("PA", "Pennsylvania"));
			noticeState.add(new SelectItem("RI", "Rhode Island"));
			noticeState.add(new SelectItem("SC", "South Carolina"));
			noticeState.add(new SelectItem("SD", "South Dakota"));
			noticeState.add(new SelectItem("TN", "Tennessee"));
			noticeState.add(new SelectItem("TX", "Texas"));
			noticeState.add(new SelectItem("UT", "Utah"));
			noticeState.add(new SelectItem("VT", "Vermont"));
			noticeState.add(new SelectItem("VA", "Virginia"));
			noticeState.add(new SelectItem("WA", "Washington"));
			noticeState.add(new SelectItem("WV", "West Virginia"));
			noticeState.add(new SelectItem("WI", "Wisconsin"));
			noticeState.add(new SelectItem("WY", "Wyoming"));
		}
		return noticeState;
	}
	
	/**
	 * @param noticeState
	 *            the noticeState to set
	 */
	public void setNoticeState(List<SelectItem> noticeState)
	{
		this.noticeState = noticeState;
	}
	
	/**
	 * @return the termEnd
	 */
	public List<SelectItem> getTermEnd()
	{
		if (termEnd == null)
		{
			termEnd = new ArrayList<SelectItem>();
			termEnd.add(new SelectItem("Date", "Date"));
			termEnd.add(new SelectItem("Notice Of", "Notice Of"));
			termEnd.add(new SelectItem("Work Order End", "Work Order End"));
			termEnd.add(new SelectItem("Other", "Other"));
		}
		return termEnd;
	}
	
	/**
	 * @param termEnd
	 *            the termEnd to set
	 */
	public void setTermEnd(List<SelectItem> termEnd)
	{
		this.termEnd = termEnd;
	}
	
	/**
	 * @return the paymentTerms
	 */
	public List<SelectItem> getPaymentTerms()
	{
		if (paymentTerms == null)
		{
			paymentTerms = new ArrayList<SelectItem>();
			paymentTerms.add(new SelectItem("Net10", "Net10"));
			paymentTerms.add(new SelectItem("Net15", "Net15"));
			paymentTerms.add(new SelectItem("Net20", "Net20"));
			paymentTerms.add(new SelectItem("Net30", "Net30"));
			paymentTerms.add(new SelectItem("Net45", "Net45"));
			paymentTerms.add(new SelectItem("Net60", "Net60"));
			paymentTerms.add(new SelectItem("Other", "Other"));
		}
		return paymentTerms;
	}
	
	/**
	 * @param paymentTerms
	 *            the paymentTerms to set
	 */
	public void setPaymentTerms(List<SelectItem> paymentTerms)
	{
		this.paymentTerms = paymentTerms;
	}
	
	/**
	 * @return the invoiceFrequency
	 */
	public List<SelectItem> getInvoiceFrequency()
	{
		if (invoiceFrequency == null)
		{
			invoiceFrequency = new ArrayList<SelectItem>();
			invoiceFrequency.add(new SelectItem("Weekly", "Weekly"));
			invoiceFrequency.add(new SelectItem("BiWeekly", "BiWeekly"));
			invoiceFrequency.add(new SelectItem("SemiMonthly", "SemiMonthly"));
			invoiceFrequency.add(new SelectItem("Monthly", "Monthly"));
			invoiceFrequency.add(new SelectItem("Other", "Other"));
		}
		return invoiceFrequency;
	}
	
	/**
	 * @param invoiceFrequency
	 *            the invoiceFrequency to set
	 */
	public void setInvoiceFrequency(List<SelectItem> invoiceFrequency)
	{
		this.invoiceFrequency = invoiceFrequency;
	}
	
	/**
	 * @return the timesheetFrequency
	 */
	public List<SelectItem> getTimesheetFrequency()
	{
		if (timesheetFrequency == null)
		{
			timesheetFrequency = new ArrayList<SelectItem>();
			timesheetFrequency.add(new SelectItem("1", "6P2 Weekly (Sun-Sat)"));
			timesheetFrequency.add(new SelectItem("2", "FXA Weekly (Sun-Sat)"));
			timesheetFrequency.add(new SelectItem("3", "6P2 Weekly (Sat-Fri)"));
			timesheetFrequency.add(new SelectItem("4", "36M Weekly (Mon-Sun)"));
			timesheetFrequency.add(new SelectItem("5", "6P2 Weekly (Fri-Thu)"));
			timesheetFrequency.add(new SelectItem("B", "Bi-Weekly"));
			timesheetFrequency.add(new SelectItem("E", "Bi-Weekly (Exxon)"));
			timesheetFrequency.add(new SelectItem("S", "Semi-Monthly"));
			timesheetFrequency.add(new SelectItem("M", "Monthly"));
		}
		return timesheetFrequency;
	}
	
	/**
	 * @param timesheetFrequency
	 *            the timesheetFrequency to set
	 */
	public void setTimesheetFrequency(List<SelectItem> timesheetFrequency)
	{
		this.timesheetFrequency = timesheetFrequency;
	}
	
	/**
	 * @return the disputeTimeFrame
	 */
	public List<SelectItem> getDisputeTimeFrame()
	{
		if (disputeTimeFrame == null)
		{
			disputeTimeFrame = new ArrayList<SelectItem>();
			disputeTimeFrame.add(new SelectItem("10 days", "10 days"));
			disputeTimeFrame.add(new SelectItem("30 days", "30 days"));
			disputeTimeFrame.add(new SelectItem("45 days", "45 days"));
			disputeTimeFrame.add(new SelectItem("Not Addressed", "Not Addressed"));
			disputeTimeFrame.add(new SelectItem("Other", "Other"));
		}
		return disputeTimeFrame;
	}
	
	/**
	 * @param disputeTimeFrame
	 *            the disputeTimeFrame to set
	 */
	public void setDisputeTimeFrame(List<SelectItem> disputeTimeFrame)
	{
		this.disputeTimeFrame = disputeTimeFrame;
	}
	
	/**
	 * @return the insWorkmans
	 */
	public List<SelectItem> getInsWorkmans()
	{
		if (insWorkmans == null)
		{
			insWorkmans = new ArrayList<SelectItem>();
			insWorkmans.add(new SelectItem("Statutory", "Statutory"));
			insWorkmans.add(new SelectItem("None", "None"));
			insWorkmans.add(new SelectItem("1M", "1M"));
			insWorkmans.add(new SelectItem("2M", "2M"));
			insWorkmans.add(new SelectItem("3M", "3M"));
			insWorkmans.add(new SelectItem("4M", "4M"));
			insWorkmans.add(new SelectItem("5M", "5M"));
			insWorkmans.add(new SelectItem("10M", "10M"));
		}
		return insWorkmans;
	}
	
	/**
	 * @param insWorkmans
	 *            the insWorkmans to set
	 */
	public void setInsWorkmans(List<SelectItem> insWorkmans)
	{
		this.insWorkmans = insWorkmans;
	}
	
	/**
	 * @return the insEmployersLiability
	 */
	public List<SelectItem> getInsEmployersLiability()
	{
		if (insEmployersLiability == null)
		{
			insEmployersLiability = new ArrayList<SelectItem>();
			insEmployersLiability.add(new SelectItem("1M", "1M"));
			insEmployersLiability.add(new SelectItem("2M", "2M"));
			insEmployersLiability.add(new SelectItem("3M", "3M"));
			insEmployersLiability.add(new SelectItem("4M", "4M"));
			insEmployersLiability.add(new SelectItem("5M", "5M"));
			insEmployersLiability.add(new SelectItem("10M", "10M"));
			insEmployersLiability.add(new SelectItem("Other", "Other"));
			insEmployersLiability.add(new SelectItem("None", "None"));
		}
		return insEmployersLiability;
	}
	
	/**
	 * @param insEmployersLiability
	 *            the insEmployersLiability to set
	 */
	public void setInsEmployersLiability(List<SelectItem> insEmployersLiability)
	{
		this.insEmployersLiability = insEmployersLiability;
	}
	
	/**
	 * @return the insCGL
	 */
	public List<SelectItem> getInsCGL()
	{
		if (insCGL == null)
		{
			insCGL = new ArrayList<SelectItem>();
			insCGL.add(new SelectItem("1M", "1M"));
			insCGL.add(new SelectItem("2M", "2M"));
			insCGL.add(new SelectItem("3M", "3M"));
			insCGL.add(new SelectItem("4M", "4M"));
			insCGL.add(new SelectItem("5M", "5M"));
			insCGL.add(new SelectItem("10M", "10M"));
			insCGL.add(new SelectItem("Other", "Other"));
			insCGL.add(new SelectItem("None", "None"));
		}
		return insCGL;
	}
	
	/**
	 * @param insCGL
	 *            the insCGL to set
	 */
	public void setInsCGL(List<SelectItem> insCGL)
	{
		this.insCGL = insCGL;
	}
	
	/**
	 * @return the insAuto
	 */
	public List<SelectItem> getInsAuto()
	{
		if (insAuto == null)
		{
			insAuto = new ArrayList<SelectItem>();
			insAuto.add(new SelectItem("1M", "1M"));
			insAuto.add(new SelectItem("2M", "2M"));
			insAuto.add(new SelectItem("3M", "3M"));
			insAuto.add(new SelectItem("4M", "4M"));
			insAuto.add(new SelectItem("5M", "5M"));
			insAuto.add(new SelectItem("10M", "10M"));
			insAuto.add(new SelectItem("Other", "Other"));
			insAuto.add(new SelectItem("None", "None"));
		}
		return insAuto;
	}
	
	/**
	 * @param insAuto
	 *            the insAuto to set
	 */
	public void setInsAuto(List<SelectItem> insAuto)
	{
		this.insAuto = insAuto;
	}
	
	/**
	 * @return the insUmbrella
	 */
	public List<SelectItem> getInsUmbrella()
	{
		if (insUmbrella == null)
		{
			insUmbrella = new ArrayList<SelectItem>();
			insUmbrella.add(new SelectItem("1M", "1M"));
			insUmbrella.add(new SelectItem("2M", "2M"));
			insUmbrella.add(new SelectItem("3M", "3M"));
			insUmbrella.add(new SelectItem("4M", "4M"));
			insUmbrella.add(new SelectItem("5M", "5M"));
			insUmbrella.add(new SelectItem("10M", "10M"));
			insUmbrella.add(new SelectItem("Other", "Other"));
			insUmbrella.add(new SelectItem("None", "None"));
		}
		return insUmbrella;
	}
	
	/**
	 * @param insUmbrella
	 *            the insUmbrella to set
	 */
	public void setInsUmbrella(List<SelectItem> insUmbrella)
	{
		this.insUmbrella = insUmbrella;
	}
	
	/**
	 * @return the insProfessional
	 */
	public List<SelectItem> getInsProfessional()
	{
		if (insProfessional == null)
		{
			insProfessional = new ArrayList<SelectItem>();
			insProfessional.add(new SelectItem("1M", "1M"));
			insProfessional.add(new SelectItem("2M", "2M"));
			insProfessional.add(new SelectItem("3M", "3M"));
			insProfessional.add(new SelectItem("4M", "4M"));
			insProfessional.add(new SelectItem("5M", "5M"));
			insProfessional.add(new SelectItem("10M", "10M"));
			insProfessional.add(new SelectItem("Other", "Other"));
			insProfessional.add(new SelectItem("None", "None"));
		}
		return insProfessional;
	}
	
	/**
	 * @param insProfessional
	 *            the insProfessional to set
	 */
	public void setInsProfessional(List<SelectItem> insProfessional)
	{
		this.insProfessional = insProfessional;
	}
	
	/**
	 * @return the insCancel
	 */
	public List<SelectItem> getInsCancel()
	{
		if (insCancel == null)
		{
			insCancel = new ArrayList<SelectItem>();
			insCancel.add(new SelectItem("10 Days", "10 Days"));
			insCancel.add(new SelectItem("15 Days", "15 Days"));
			insCancel.add(new SelectItem("30 Days", "30 Days"));
		}
		return insCancel;
	}
	
	/**
	 * @param insCancel
	 *            the insCancel to set
	 */
	public void setInsCancel(List<SelectItem> insCancel)
	{
		this.insCancel = insCancel;
	}
	
	/**
	 * @return the industry
	 */
	public List<SelectItem> getIndustry()
	{
		if (industry == null)
		{
			
			industry = new ArrayList<SelectItem>();
			industry.add(new SelectItem("Communications", "Communications"));
			industry.add(new SelectItem("Defense & Security", "Defense & Security"));
			industry.add(new SelectItem("Education & Training", "Education & Training"));
			industry.add(new SelectItem("Energy", "Energy"));
			industry.add(new SelectItem("Financial Services", "Financial Services"));
			industry.add(new SelectItem("Government & Public Services", "Government & Public Services"));
			industry.add(new SelectItem("Healthcare & Life Sciences", "Healthcare & Life Sciences"));
			industry.add(new SelectItem("Hospitality & Leisure", "Hospitality & Leisure"));
			industry.add(new SelectItem("Manufacturing", "Manufacturing"));
			industry.add(new SelectItem("Non-Profit", "Non-Profit"));
			industry.add(new SelectItem("Retail & Consumer Goods", "Retail & Consumer Goods"));
			industry.add(new SelectItem("Technology & Consulting", "Technology & Consulting"));
			industry.add(new SelectItem("Transportation", "Transportation"));
			industry.add(new SelectItem("Other", "Other"));
		}
		return industry;
	}
	
	/**
	 * @param industry
	 *            the industry to set
	 */
	public void setIndustry(List<SelectItem> industry)
	{
		this.industry = industry;
	}
	
	/**
	 * @return the insBondAmount
	 */
	public List<SelectItem> getInsBondAmount()
	{
		if (insBondAmount == null)
		{
			insBondAmount = new ArrayList<SelectItem>();
			insBondAmount.add(new SelectItem("1M", "1M"));
			insBondAmount.add(new SelectItem("2M", "2M"));
			insBondAmount.add(new SelectItem("3M", "3M"));
			insBondAmount.add(new SelectItem("4M", "4M"));
			insBondAmount.add(new SelectItem("5M", "5M"));
			insBondAmount.add(new SelectItem("10M", "10M"));
		}
		return insBondAmount;
	}
	
	/**
	 * @param insBondAmount
	 *            the insBondAmount to set
	 */
	public void setInsBondAmount(List<SelectItem> insBondAmount)
	{
		this.insBondAmount = insBondAmount;
	}
	
	/**
	 * @return the typeOfAgreement
	 */
	public List<SelectItem> getTypeOfAgreement()
	{
		if (typeOfAgreement == null)
		{
			typeOfAgreement = new ArrayList<SelectItem>();
			typeOfAgreement.add(new SelectItem("", ""));
			typeOfAgreement.add(new SelectItem("Recruitment or Referral Fee", "Recruitment or Referral Fee"));
			typeOfAgreement.add(new SelectItem("Direct Client Placement", "Direct Client Placement"));
			typeOfAgreement.add(new SelectItem("Subcontract", "Subcontract"));
			typeOfAgreement.add(new SelectItem("Other", "Other"));
			
		}
		return typeOfAgreement;
	}
	
	/**
	 * @param typeOfAgreement
	 *            the typeOfAgreement to set
	 */
	public void setTypeOfAgreement(List<SelectItem> typeOfAgreement)
	{
		this.typeOfAgreement = typeOfAgreement;
	}
	
	/**
	 * @return the rightToHireMonths
	 */
	public List<SelectItem> getRightToHireMonths()
	{
		if (rightToHireMonths == null)
		{
			rightToHireMonths = new ArrayList<SelectItem>();
			rightToHireMonths.add(new SelectItem("1", "1"));
			rightToHireMonths.add(new SelectItem("2", "2"));
			rightToHireMonths.add(new SelectItem("3", "3"));
			rightToHireMonths.add(new SelectItem("4", "4"));
			rightToHireMonths.add(new SelectItem("5", "5"));
			rightToHireMonths.add(new SelectItem("6", "6"));
			rightToHireMonths.add(new SelectItem("Other", "Other"));
			
		}
		return rightToHireMonths;
	}
	
	/**
	 * @param rightToHireMonths
	 *            the rightToHireMonths to set
	 */
	public void setRightToHireMonths(List<SelectItem> rightToHireMonths)
	{
		this.rightToHireMonths = rightToHireMonths;
	}
	
	/**
	 * @return the subContractingAllowed
	 */
	public List<SelectItem> getSubContractingAllowed()
	{
		if (subContractingAllowed == null)
		{
			subContractingAllowed = new ArrayList<SelectItem>();
			subContractingAllowed.add(new SelectItem("Yes", "Yes"));
			subContractingAllowed.add(new SelectItem("With Approval", "With Approval"));
			subContractingAllowed.add(new SelectItem("No", "No"));
			subContractingAllowed.add(new SelectItem("W2 Employees Only", "W2 Employees Only"));
			subContractingAllowed.add(new SelectItem("Not Addressed", "Not Addressed"));
		}
		return subContractingAllowed;
	}
	
	/**
	 * @param subContractingAllowed
	 *            the subContractingAllowed to set
	 */
	public void setSubContractingAllowed(List<SelectItem> subContractingAllowed)
	{
		this.subContractingAllowed = subContractingAllowed;
	}
	
	/**
	 * @return the promptPay
	 */
	public List<SelectItem> getPromptPay()
	{
		if (promptPay == null)
		{
			promptPay = new ArrayList<SelectItem>();
			promptPay.add(new SelectItem("None", "None"));
			promptPay.add(new SelectItem("5-Days", "5-Days"));
			promptPay.add(new SelectItem("10-Days", "10-Days"));
			promptPay.add(new SelectItem("15-Days", "15-Days"));
			promptPay.add(new SelectItem("20-Days", "20-Days"));
			promptPay.add(new SelectItem("25-Days", "25-Days"));
			promptPay.add(new SelectItem("30-Days", "30-Days"));
		}
		return promptPay;
	}
	
	/**
	 * @param promptPay
	 *            the promptPay to set
	 */
	public void setPromptPay(List<SelectItem> promptPay)
	{
		this.promptPay = promptPay;
	}
	
	public void onVerticalChange()
	{
		if (client.getpSVerticalId() != null)
		{
			pSVerticalCodes = new ArrayList<SelectItem>();
			pSVerticalDescriptions = new ArrayList<SelectItem>();
			for (PSOperatingUnitOrVertical obj : allVerticals)
			{
				if (obj.getPSVerticalId() == client.getpSVerticalId())
				{
					pSVerticalCodes.add(new SelectItem(obj.getPSVerticalCode(), obj.getPSVerticalCode()));
					pSVerticalDescriptions.add(new SelectItem(obj.getVerticalDescription(), obj.getVerticalDescription()));
				}
			}
		}
	}
	
	/**
	 * @return the pSVerticalIds
	 */
	public List<SelectItem> getpSVerticalIds()
	{
		if (pSVerticalIds == null)
		{
			pSVerticalIds = new ArrayList<SelectItem>();
			if (allVerticals != null && allVerticals.size() > 0)
			{
				for (PSOperatingUnitOrVertical obj : allVerticals)
				{
					pSVerticalIds.add(new SelectItem(obj.getPSVerticalId(), obj.getPSVerticalId().toString()));
				}
			}
		}
		return pSVerticalIds;
	}
	
	/**
	 * @param pSVerticalIds
	 *            the pSVerticalIds to set
	 */
	public void setpSVerticalIds(List<SelectItem> pSVerticalIds)
	{
		this.pSVerticalIds = pSVerticalIds;
	}
	
	/**
	 * @return the pSVerticalCodes
	 */
	public List<SelectItem> getpSVerticalCodes()
	{
		if (pSVerticalCodes == null)
		{
			pSVerticalCodes = new ArrayList<SelectItem>();
			if (allVerticals != null && allVerticals.size() > 0)
			{
				for (PSOperatingUnitOrVertical obj : allVerticals)
				{
					pSVerticalCodes.add(new SelectItem(obj.getPSVerticalCode(), obj.getPSVerticalCode()));
				}
			}
		}
		return pSVerticalCodes;
	}
	
	/**
	 * @param pSVerticalCodes
	 *            the pSVerticalCodes to set
	 */
	public void setpSVerticalCodes(List<SelectItem> pSVerticalCodes)
	{
		this.pSVerticalCodes = pSVerticalCodes;
	}
	
	/**
	 * @return the pSVerticalDescriptions
	 */
	public List<SelectItem> getpSVerticalDescriptions()
	{
		if (pSVerticalDescriptions == null)
		{
			pSVerticalDescriptions = new ArrayList<SelectItem>();
			if (allVerticals != null && allVerticals.size() > 0)
			{
				for (PSOperatingUnitOrVertical obj : allVerticals)
				{
					pSVerticalDescriptions.add(new SelectItem(obj.getVerticalDescription(), obj.getVerticalDescription()));
				}
			}
		}
		return pSVerticalDescriptions;
	}
	
	/**
	 * @param pSVerticalDescriptions
	 *            the pSVerticalDescriptions to set
	 */
	public void setpSVerticalDescriptions(List<SelectItem> pSVerticalDescriptions)
	{
		this.pSVerticalDescriptions = pSVerticalDescriptions;
	}
	
	/**
	 * @return the allVerticals
	 */
	public List<PSOperatingUnitOrVertical> getAllVerticals()
	{
		if (allVerticals == null || allVerticals.size() == 0)
		{
			allVerticals = new ArrayList<PSOperatingUnitOrVertical>();
			allVerticals = service.getPSVerticals(FacesUtils.getCurrentUserId(), 0, 1);
		}
		return allVerticals;
	}
	
	/**
	 * @param allVerticals
	 *            the allVerticals to set
	 */
	public void setAllVerticals(List<PSOperatingUnitOrVertical> allVerticals)
	{
		this.allVerticals = allVerticals;
	}
	
	/**
	 * @return the entityNames
	 */
	public List<SelectItem> getEntityNames()
	{
		if (entityNames == null)
		{
			List<Map<String, Object>> entityNamesData = null;
			entityNames = new ArrayList<SelectItem>();
			
			ReportUtil report = new ReportUtil();
			try
			{
				entityNamesData = report.getEntityNames(FacesUtils.getCurrentUserId());
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
			for (int i = 0; i < entityNamesData.size(); i++)
			{
				Map<String, Object> map = entityNamesData.get(i);
				String id = (String) map.get("ENTITYNAME");
				String desc = (String) map.get("ENTITYNAMEVALUE");
				entityNames.add(new SelectItem(id, desc));
			}
			
		}
		return entityNames;
	}
	
	/**
	 * @param entityNames
	 *            the entityNames to set
	 */
	public void setEntityNames(List<SelectItem> entityNames)
	{
		this.entityNames = entityNames;
	}
	
	/**
	 * @return the pSProductIds
	 */
	public List<SelectItem> getpSProductIds()
	{
		if (pSProductIds == null)
		{
			pSProductIds = new ArrayList<SelectItem>();
			if (allProducts != null && allProducts.size() > 0)
			{
				for (PSProduct obj : allProducts)
				{
					pSProductIds.add(new SelectItem(obj.getPSProductId(), obj.getPSProductId().toString()));
				}
			}
		}
		return pSProductIds;
	}
	
	/**
	 * @param pSProductIds
	 *            the pSProductIds to set
	 */
	public void setpSProductIds(List<SelectItem> pSProductIds)
	{
		this.pSProductIds = pSProductIds;
	}
	
	/**
	 * @return the pSProductCodes
	 */
	public List<SelectItem> getpSProductCodes()
	{
		if (pSProductCodes == null)
		{
			pSProductCodes = new ArrayList<SelectItem>();
			if (allProducts != null && allProducts.size() > 0)
			{
				for (PSProduct obj : allProducts)
				{
					pSProductCodes.add(new SelectItem(obj.getPSProductCode(), obj.getPSProductCode()));
				}
			}
		}
		return pSProductCodes;
	}
	
	/**
	 * @param pSProductCodes
	 *            the pSProductCodes to set
	 */
	public void setpSProductCodes(List<SelectItem> pSProductCodes)
	{
		this.pSProductCodes = pSProductCodes;
	}
	
	/**
	 * @return the pSProductDescriptions
	 */
	public List<SelectItem> getpSProductDescriptions()
	{
		if (pSProductDescriptions == null)
		{
			pSProductDescriptions = new ArrayList<SelectItem>();
			if (allProducts != null && allProducts.size() > 0)
			{
				for (PSProduct obj : allProducts)
				{
					pSProductDescriptions.add(new SelectItem(obj.getProductDescription(), obj.getProductDescription()));
				}
			}
		}
		return pSProductDescriptions;
	}
	
	/**
	 * @param pSProductDescriptions
	 *            the pSProductDescriptions to set
	 */
	public void setpSProductDescriptions(List<SelectItem> pSProductDescriptions)
	{
		this.pSProductDescriptions = pSProductDescriptions;
	}
	
	/**
	 * @return the allProducts
	 */
	public List<PSProduct> getAllProducts()
	{
		if (allProducts == null || allProducts.size() == 0)
		{
			allProducts = new ArrayList<PSProduct>();
			allProducts = service.getPSProducts(FacesUtils.getCurrentUserId(), 0, 1);
		}
		return allProducts;
	}
	
	/**
	 * @param allProducts
	 *            the allProducts to set
	 */
	public void setAllProducts(List<PSProduct> allProducts)
	{
		this.allProducts = allProducts;
	}
	
	/**
	 * @return the pSBillingTypeIds
	 */
	public List<SelectItem> getpSBillingTypeIds()
	{
		if (pSBillingTypeIds == null)
		{
			pSBillingTypeIds = new ArrayList<SelectItem>();
			if (allBillingTypes != null && allBillingTypes.size() > 0)
			{
				for (PSBillingType obj : allBillingTypes)
				{
					pSBillingTypeIds.add(new SelectItem(obj.getPSBillingTypeId(), obj.getPSBillingTypeId().toString()));
				}
			}
		}
		return pSBillingTypeIds;
	}
	
	/**
	 * @param pSBillingTypeIds
	 *            the pSBillingTypeIds to set
	 */
	public void setpSBillingTypeIds(List<SelectItem> pSBillingTypeIds)
	{
		this.pSBillingTypeIds = pSBillingTypeIds;
	}
	
	/**
	 * @return the pSBillingTypeCodes
	 */
	public List<SelectItem> getpSBillingTypeCodes()
	{
		if (pSBillingTypeCodes == null)
		{
			pSBillingTypeCodes = new ArrayList<SelectItem>();
			if (allBillingTypes != null && allBillingTypes.size() > 0)
			{
				for (PSBillingType obj : allBillingTypes)
				{
					pSBillingTypeCodes.add(new SelectItem(obj.getPSBillingTypeCode(), obj.getPSBillingTypeCode()));
				}
			}
		}
		return pSBillingTypeCodes;
	}
	
	/**
	 * @param pSBillingTypeCodes
	 *            the pSBillingTypeCodes to set
	 */
	public void setpSBillingTypeCodes(List<SelectItem> pSBillingTypeCodes)
	{
		this.pSBillingTypeCodes = pSBillingTypeCodes;
	}
	
	/**
	 * @return the pSBillingTypeLongNames
	 */
	public List<SelectItem> getpSBillingTypeLongNames()
	{
		if (pSBillingTypeLongNames == null)
		{
			pSBillingTypeLongNames = new ArrayList<SelectItem>();
			if (allBillingTypes != null && allBillingTypes.size() > 0)
			{
				for (PSBillingType obj : allBillingTypes)
				{
					pSBillingTypeLongNames.add(new SelectItem(obj.getPSBillingTypeLongName(), obj.getPSBillingTypeLongName()));
				}
			}
		}
		return pSBillingTypeLongNames;
	}
	
	/**
	 * @param pSBillingTypeLongNames
	 *            the pSBillingTypeLongNames to set
	 */
	public void setpSBillingTypeLongNames(List<SelectItem> pSBillingTypeLongNames)
	{
		this.pSBillingTypeLongNames = pSBillingTypeLongNames;
	}
	
	/**
	 * @return the allBillingTypes
	 */
	public List<PSBillingType> getAllBillingTypes()
	{
		if (allBillingTypes == null || allBillingTypes.size() == 0)
		{
			allBillingTypes = new ArrayList<PSBillingType>();
			allBillingTypes = service.getPSBillingTypes(FacesUtils.getCurrentUserId(), 0, 1);
		}
		return allBillingTypes;
	}
	
	/**
	 * @param allBillingTypes
	 *            the allBillingTypes to set
	 */
	public void setAllBillingTypes(List<PSBillingType> allBillingTypes)
	{
		this.allBillingTypes = allBillingTypes;
	}
	
}