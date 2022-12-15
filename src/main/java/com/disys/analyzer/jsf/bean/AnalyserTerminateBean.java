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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.primefaces.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import org.springframework.web.jsf.FacesContextUtils;
import com.disys.analyzer.config.util.FacesUtils;
import com.disys.analyzer.model.dto.AnalyserTerminateDTO;
import com.disys.analyzer.service.AnalyserService;

/**
 * @author Sajid
 * @since Mar 30, 2020
 */
@ManagedBean
@ViewScoped
public class AnalyserTerminateBean extends SpringBeanAutowiringSupport
		implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8764044632434450059L;

	private Logger logger = LoggerFactory.getLogger(getClass());

	private String terminateDate;
	private boolean eligibleForRehire;
	private boolean dentalInsurance;
	private boolean healthInsurance;
	private boolean k401Savings;
	private String reason;
	private String oldTerminateDate;
	private boolean falseTermination;
	private String analyserId;
	private String update;
	private String startDate;
	
	private List<SelectItem> reasons;

	@Autowired
	private AnalyserService service;

	private Boolean showUpdateButton;
	private String companyCode;
	
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

	public AnalyserTerminateBean() {

		FacesContext fc = FacesUtils.getFacesContext();
		ExternalContext externalContext = fc.getExternalContext();
		Map<String, String> requestMap = externalContext
				.getRequestParameterMap();				
		
		eligibleForRehire = true;
		dentalInsurance = true;
		falseTermination = false;

		analyserId = requestMap.get("analyserId");
		update = requestMap.get("update");
		companyCode = requestMap.get("companyCode");
		
		showUpdateButton = false;
		
		System.out.println("AnalyserTerminateBean constructor CompanyCode " +companyCode);
		logger.info("AnalyserTerminateBean constructor CompanyCode " +companyCode);
		
		if(String.valueOf(update).equals("true")){
			
			AnalyserTerminateDTO obj = new AnalyserTerminateDTO();
			obj = service.getAnalyserTerminateInfo(FacesUtils.getCurrentUserId(), Integer.valueOf(analyserId));
			
			oldTerminateDate = obj.getTerminateDate();
			startDate = service.getWorkforceShifts(Integer.valueOf(analyserId));
			terminateDate = obj.getTerminateDate();
			reason = obj.getReason();
			
			healthInsurance = obj.isHealthInsurance();
			dentalInsurance = obj.isDentalInsurance();
			falseTermination = obj.isFalseTermination();
			k401Savings = obj.isK401Savings();
			eligibleForRehire = obj.isEligibleForRehire();
			
			showUpdateButton = true;
			
			
		} else {
			oldTerminateDate = service.getWorkforceShifts(Integer.valueOf(analyserId));
			
			//01/23/2019
			startDate = service.getWorkforceShifts(Integer.valueOf(analyserId));
			Double values[] = new Double[2];
			values = service.getHealthAnd401K(Long.valueOf(analyserId));
			if (values[0] > 0) {
				healthInsurance = true;
			} else {
				healthInsurance = false;
			}

			if (values[1] > 0) {
				k401Savings = true;
			} else {
				k401Savings = false;
			}
		}
		
		
	}

	public void terminateAnalyser() {

		String param1 = "null";
		String param2 = "null";
		String param3 = "null";

		if (eligibleForRehire) {
			param1 = "Y";
		}else{
			param1 = "N";
		}
		if (dentalInsurance) {
			param2 = "Y";
		}else{
			param2 = "N";
		}
		
		if(falseTermination){
			param3 = "Y";
		}else{
			param3 = "N";
		}

		String result = service.terminateAnalyser(
				FacesUtils.getCurrentUserId(), Integer.valueOf(analyserId),
				terminateDate, reason, param1, param2,param3);

		String title = "Success";
		String detail = "";
		// Returns: 0 for Success 1 for Failure and 2 Already Exists
		FacesMessage message = null;
		if (result.equals("0")) {
			detail = "Successfully terminated";
			message = new FacesMessage(FacesMessage.SEVERITY_INFO, title,
					detail);
		} else if (result.equals("1")) {
			detail = "Faild to terminate analyser";
			message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Failure",
					detail);
		} else if (result.equals("2")) {
			detail = "Already Exists";
			message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Failure",
					detail);
		}

		HttpServletRequest request = FacesUtils.getRequestObject();
		HttpSession session = request.getSession();
		session.setAttribute("message", message);

		terminateAnalyserFromDialog(analyserId);
	}
	
	
	
	public void updateTerminateAnalyser() {

		String param1 = "null";
		String param2 = "null";
		String param3 = "null";
		
		if (eligibleForRehire) {
			param1 = "Y";
		}else{
			param1 = "N";
		}
		if (dentalInsurance) {
			param2 = "Y";
		}else{
			param2 = "N";
		}
		
		if(falseTermination){
			param3 = "Y";
		}else{
			param3 = "N";
		}
		
		String result = service.updateTerminateAnalyser(
				FacesUtils.getCurrentUserId(), Integer.valueOf(analyserId),
				terminateDate, reason, param1, param2,param3);

		String title = "Success";
		String detail = "";
		// Returns: 0 for Success 1 for Failure
		FacesMessage message = null;
		if (result.equals("0")) {
			logger.error("PRINT 'ERROR - NO MATCHING RECORDS FOUND.'");
			detail = "Failed to update terminated analyser";
			message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Failure",
					detail);
		} else if (result.equals("1")) {
			detail = "Successfully updated";
			message = new FacesMessage(FacesMessage.SEVERITY_INFO, title,
					detail);
		}

		HttpServletRequest request = FacesUtils.getRequestObject();
		HttpSession session = request.getSession();
		session.setAttribute("message", message);

		terminateAnalyserFromDialog(analyserId);
	}

	public void terminateAnalyserFromDialog(String analyserId) {
		RequestContext.getCurrentInstance().closeDialog(analyserId);
	}

	/**
	 * @return the terminateDate
	 */
	public String getTerminateDate() {
		return terminateDate;
	}

	/**
	 * @param terminateDate
	 *            the terminateDate to set
	 */
	public void setTerminateDate(String terminateDate) {
		this.terminateDate = terminateDate;
	}

	/**
	 * @return the eligibleForRehire
	 */
	public boolean isEligibleForRehire() {
		return eligibleForRehire;
	}

	/**
	 * @param eligibleForRehire
	 *            the eligibleForRehire to set
	 */
	public void setEligibleForRehire(boolean eligibleForRehire) {
		this.eligibleForRehire = eligibleForRehire;
	}

	/**
	 * @return the dentalInsurance
	 */
	public boolean isDentalInsurance() {
		return dentalInsurance;
	}

	/**
	 * @param dentalInsurance
	 *            the dentalInsurance to set
	 */
	public void setDentalInsurance(boolean dentalInsurance) {
		this.dentalInsurance = dentalInsurance;
	}

	/**
	 * @return the healthInsurance
	 */
	public boolean isHealthInsurance() {
		return healthInsurance;
	}

	/**
	 * @param healthInsurance
	 *            the healthInsurance to set
	 */
	public void setHealthInsurance(boolean healthInsurance) {
		this.healthInsurance = healthInsurance;
	}

	/**
	 * @return the k401Savings
	 */
	public boolean isK401Savings() {
		return k401Savings;
	}

	/**
	 * @param k401Savings
	 *            the k401Savings to set
	 */
	public void setK401Savings(boolean k401Savings) {
		this.k401Savings = k401Savings;
	}

	/**
	 * @return the reason
	 */
	public String getReason() {
		return reason;
	}

	/**
	 * @param reason
	 *            the reason to set
	 */
	public void setReason(String reason) {
		this.reason = reason;
	}

	/**
	 * @return the analyserId
	 */
	public String getAnalyserId() {
		return analyserId;
	}

	/**
	 * @param analyserId
	 *            the analyserId to set
	 */
	public void setAnalyserId(String analyserId) {
		this.analyserId = analyserId;
	}

	/**
	 * @return the reasons
	 */
	public List<SelectItem> getReasons() {
		if (reasons == null) {
			reasons = new ArrayList<SelectItem>();

			reasons.add(new SelectItem("V - COVID-19", "V - COVID-19"));
			reasons.add(new SelectItem("I - COVID-19", "I - COVID-19"));
			reasons.add(new SelectItem(
					"V - Resignation: New Job, Same Industry or Competitor",
					"V - Resignation: New Job, Same Industry or Competitor"));
			reasons.add(new SelectItem(
					"V - Resignation: New Job, Different Industry",
					"V - Resignation: New Job, Different Industry"));
			reasons.add(new SelectItem("V - Resignation: Employed by Client",
					"V - Resignation: Employed by Client"));
			reasons.add(new SelectItem(
					"V - Resignation: Personal Reasons (Relocation), Walk Off or Job Abandonment",
					"V - Resignation: Personal Reasons (Relocation), Walk Off or Job Abandonment"));
			reasons.add(new SelectItem("P - End of Job Assignment",
					"P - End of Job Assignment"));
			reasons.add(new SelectItem(
					"I - Involuntary Termination (Performance, Behavior, Policy Violation)",
					"I - Involuntary Termination (Performance, Behavior, Policy Violation)"));
			reasons.add(new SelectItem("I - Job Elimination",
					"I - Job Elimination"));
			reasons.add(new SelectItem("I - Death/Disability",
					"I - Death/Disability"));
			reasons.add(new SelectItem("M - Analyzer Modification",
					"M - Analyzer Modification"));
			reasons.add(new SelectItem("M - Never started", "M - Never started"));
			reasons.add(new SelectItem(
					"T-Transfer/Change of Assignment (does transfer to PS)",
					"T-Transfer/Change of Assignment (does transfer to PS)"));
		}
		return reasons;
	}

	/**
	 * @param reasons
	 *            the reasons to set
	 */
	public void setReasons(List<SelectItem> reasons) {
		this.reasons = reasons;
	}

	/**
	 * @return the oldTerminateDate
	 */
	public String getOldTerminateDate() {
		return oldTerminateDate;
	}

	/**
	 * @param oldTerminateDate the oldTerminateDate to set
	 */
	public void setOldTerminateDate(String oldTerminateDate) {
		this.oldTerminateDate = oldTerminateDate;
	}

	/**
	 * @return the falseTermination
	 */
	public boolean isFalseTermination() {
		return falseTermination;
	}

	/**
	 * @param falseTermination the falseTermination to set
	 */
	public void setFalseTermination(boolean falseTermination) {
		this.falseTermination = falseTermination;
	}

	/**
	 * @return the update
	 */
	public String getUpdate()
	{
		return update;
	}

	/**
	 * @param update the update to set
	 */
	public void setUpdate(String update)
	{
		this.update = update;
	}

	/**
	 * @return the showUpdateButton
	 */
	public Boolean getShowUpdateButton()
	{
		return showUpdateButton;
	}

	/**
	 * @param showUpdateButton the showUpdateButton to set
	 */
	public void setShowUpdateButton(Boolean showUpdateButton)
	{
		this.showUpdateButton = showUpdateButton;
	}

	/**
	 * @return the startDate
	 */
	public String getStartDate() {
		return startDate;
	}

	/**
	 * @param startDate the startDate to set
	 */
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	/**
	 * @return the companyCode
	 */
	public String getCompanyCode() {
		return companyCode;
	}
	/**
	 * @param companyCode the companyCode to set
	 */
	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}
	
	
}
