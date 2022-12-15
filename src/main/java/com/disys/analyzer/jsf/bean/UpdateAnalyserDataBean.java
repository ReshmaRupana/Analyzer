/**
 * 
 */
package com.disys.analyzer.jsf.bean;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import org.springframework.web.jsf.FacesContextUtils;

import com.disys.analyzer.config.util.Constants;
import com.disys.analyzer.config.util.FacesUtils;
import com.disys.analyzer.model.Analyser;
import com.disys.analyzer.model.dto.PortfolioDTO;
import com.disys.analyzer.model.dto.TerminationReasonDTO;
import com.disys.analyzer.service.AnalyserService;
import com.disys.analyzer.service.PortfolioService;
import com.disys.analyzer.service.TerminationReasonService;

/**
 * @author Sajid
 * @since March 31, 2020
 */
@ManagedBean
@ViewScoped
public class UpdateAnalyserDataBean extends SpringBeanAutowiringSupport implements Serializable
{
	private static final long serialVersionUID = -7157302311661156860L;
	private Logger logger = LoggerFactory.getLogger(UpdateAnalyserDataBean.class);
	private Analyser analyser;
	private List<Analyser> analyserList;
	private List<Analyser> filteredList;
	private List<Analyser> newAnalyserList;
	private Analyser selectedAnalyser;
	private String parentId;
	private boolean updateRecord;
	Integer itemPositionInList;
	private List<SelectItem> reasons;
	private static Date minDate;
	private static Date maxDate;
	
	private List<SelectItem> terminationReasonList;
	String userId = FacesUtils.getCurrentUserId();
	private List<SelectItem> selectedPortfolio;
	
	@Autowired 
	private AnalyserService service;
	
	@Autowired 
	private TerminationReasonService terminationReasonservice;
	
	@Autowired 
	private PortfolioService portfolioService;
	
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
	
	public UpdateAnalyserDataBean()
	{
		analyser = new Analyser();
		selectedAnalyser = new Analyser();
		minDate = getDate("01/01/1950");
		maxDate = getDate("01/01/2001");
	}
	
	public void filter()
	{
		Integer id = -1;
		if (this.parentId == null || this.parentId.trim().equals(""))
		{
			id = -1;
		}
		else if (this.parentId != null && this.parentId.trim().length() > 0)
		{
			try
			{
				id = Integer.parseInt(this.parentId);
			}
			catch (NumberFormatException ne)
			{
				id = -1;
			}
		}
		analyserList = service.getAnalyzerDataForUpdate(id);
		
		System.out.println("{call " + FacesUtils.SCHEMA_WIRELESS + ".spGetAnalyzerDataForUpdate(userId, id)");
		System.out.println(
		        "{call " + FacesUtils.SCHEMA_WIRELESS + ".spGetAnalyzerDataForUpdate(" + FacesUtils.getCurrentUserId() + ", " + id + ")");
		
		logger.debug("{call " + FacesUtils.SCHEMA_WIRELESS + ".spGetAnalyzerDataForUpdate(userId,parentId)");
		logger.debug("{call " + FacesUtils.SCHEMA_WIRELESS + ".spGetAnalyzerDataForUpdate(" + FacesUtils.getCurrentUserId() + "," + id + ")");
		
		logger.info("Total list size is : " + analyserList.size());
		
		// logger.info("Total list size is : " + list.getSize());
		analyser = new Analyser();
		selectedAnalyser = new Analyser();
		itemPositionInList = -1;
		this.terminationReasonList=null;
		this.selectedPortfolio=null;
	}
	
	public void eraseFilter()
	{
		this.parentId = "";
		updateRecord = false;
		analyser = new Analyser();
		selectedAnalyser = new Analyser();
		analyserList = null;
		filteredList = null;
		itemPositionInList = -1;
		this.terminationReasonList=null;
		this.selectedPortfolio=null;
	}
	
	private String fixDate(String date)
	{
		if (date != null && date.trim().length() > 0 && date.trim().length() < 10)
		{
			String[] splittedDate = date.split("/");
			if (splittedDate[0].length() < 2)
			{
				splittedDate[0] = "0".concat(splittedDate[0]);
			}
			if (splittedDate[1].length() < 2)
			{
				splittedDate[1] = "0".concat(splittedDate[1]);
			}
			date = splittedDate[0] + "/" + splittedDate[1] + "/" + splittedDate[2];
		}
		return date;
	}
	
	public void modifyAnalyserData()
	{
		Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		Long analyserId = Long.valueOf(params.get("analyserId"));
		System.out.println("Analyser id is : " + analyserId);
		selectedAnalyser = new Analyser();
		for (int i = 0; i < analyserList.size(); i++)
		{
			if (analyserList.get(i).getAnalyserId().equals(analyserId))
			{
				selectedAnalyser = new Analyser(analyserList.get(i));
				selectedAnalyser.setDob(fixDate(selectedAnalyser.getDob()));
				selectedAnalyser.setStartDate(fixDate(selectedAnalyser.getStartDate()));
				selectedAnalyser.setEndDate(fixDate(selectedAnalyser.getEndDate()));
				selectedAnalyser.setEffectiveDate(fixDate(selectedAnalyser.getEffectiveDate()));
				selectedAnalyser.setCommEffectiveDate(fixDate(selectedAnalyser.getCommEffectiveDate()));
				selectedAnalyser.setTerminateDate(fixDate(selectedAnalyser.getTerminateDate()));				
				itemPositionInList = i;
				break;
			}			
		}		
		updateRecord = true;
		
	}
	
	public void updateAnalyserData()
	{
		Integer id = -1;
		if (this.parentId == null || this.parentId.trim().equals(""))
		{
			id = -1;
		}
		else if (this.parentId != null && this.parentId.trim().length() > 0)
		{
			try
			{
				id = Integer.parseInt(this.parentId);
			}
			catch (NumberFormatException ne)
			{
				id = -1;
			}
		}
		newAnalyserList = service.getAnalyzerDataForUpdate(Integer.valueOf(id));
		boolean valid = true;
		String name = null, summary = null, result = null;
		Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		String buttonName = params.get("javax.faces.partial.execute");
		if (buttonName != null)
		{
			buttonName = buttonName.substring(0, buttonName.indexOf(' '));	//Which button was clicked by user
		}
		String userId = FacesUtils.getCurrentUserId();
		
		if (buttonName.equalsIgnoreCase("dataTableForm:lastNameButton"))
		{
			name = params.get("dataTableForm:lastName");
			if (name == null || name.trim().length() == 0)
			{
				summary = "Last name required";
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(summary));
				return;
			}
			System.out.println("Original name is : " + selectedAnalyser.getLastName());
			System.out.println("Name to update is : " + name);
			result = service.updateAnalyserRestrictedData(userId, "LName", Integer.parseInt(selectedAnalyser.getAnalyserId().toString()),
			        Integer.valueOf(selectedAnalyser.getParentId()), newAnalyserList.get(itemPositionInList).getLastName(),
			        selectedAnalyser.getLastName().trim());
			valid = result.equals("1");
			summary = valid ? "Last name updated successfully" : "Error updating last name";
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(summary));
		}
		else if (buttonName.equalsIgnoreCase("dataTableForm:firstNameButton"))
		{
			name = params.get("dataTableForm:firstName");
			if (name == null || name.trim().length() == 0)
			{
				summary = "First name required";
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(summary));
				return;
			}
			System.out.println("Original name is : " + selectedAnalyser.getFirstName());
			System.out.println("Name to update is : " + name);
			result = service.updateAnalyserRestrictedData(userId, "FName", Integer.parseInt(selectedAnalyser.getAnalyserId().toString()),
			        Integer.valueOf(selectedAnalyser.getParentId()), newAnalyserList.get(itemPositionInList).getFirstName(),
			        selectedAnalyser.getFirstName().trim());
			valid = result.equals("1");
			summary = valid ? "First name updated successfully" : "Error updating first name";
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(summary));
		}
		else if (buttonName.equalsIgnoreCase("dataTableForm:dobButton"))
		{
			String dob = params.get("dataTableForm:dob");
			if (dob == null || dob.trim().length() == 0)
			{
				summary = "DOB required";
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(summary));
				return;
			}
//			Date convertedDate = getDate(dob);
//			Calendar calendar = Calendar.getInstance();
//			Integer currentYear = calendar.get(Calendar.YEAR);
//			Integer currentMont = calendar.get(Calendar.MONTH);
//			System.out.println("Current Year :: " + currentYear + " :: Current Month :: " + currentMont);
//			calendar.setTime(convertedDate);
//			Integer dobYear = calendar.get(Calendar.YEAR);
//			Integer dobMonth = calendar.get(Calendar.MONTH);
//			System.out.println("DOB Year :: " + dobYear + " :: DOB Month :: " + dobMonth);
//			Integer ageInYear = currentYear - dobYear;
//			Integer ageInMonth = currentMont - dobMonth;
//			System.out.println("Age Difference in year = " + ageInYear + " :: Age difference in month = " + ageInMonth);
//		    if (ageInMonth < 0 || (ageInMonth == 0 && Calendar.getInstance().getTimeInMillis() < convertedDate.getTime())) {
//		    	ageInYear--;
//		    }
//		    System.out.println("Calculate Age Difference :: " + ageInYear);
			//if (isCurrentDateGreaterThanNextDate(minDate, convertedDate) || isCurrentDateGreaterThanNextDate(convertedDate, maxDate))
			
			Date convertedDate = getDate(dob);
			Date currentDate = new Date();
			DateFormat formatter = new SimpleDateFormat("yyyyMMdd");                           
		    int d1 = Integer.parseInt(formatter.format(convertedDate));                            
		    int d2 = Integer.parseInt(formatter.format(currentDate));                          
		    int age = (d2 - d1) / 10000;    
		    System.out.println("Calculate Age Difference = " + age);
			if(age < 18)
			{
				//summary = "DOB must be between 01/01/1950 and 01/01/2001";
				summary = "DOB should be equal or greater than 18 years.";
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(summary));
				return;
			}
			System.out.println("Original dob is : " + selectedAnalyser.getDob());
			System.out.println("DOB to update is : " + dob);
			result = service.updateAnalyserRestrictedData(userId, "DOB", Integer.parseInt(selectedAnalyser.getAnalyserId().toString()),
			        Integer.valueOf(selectedAnalyser.getParentId()), newAnalyserList.get(itemPositionInList).getDob(),
			        selectedAnalyser.getDob().trim());
			
			valid = result.equals("1");
			summary = valid ? "DOB updated successfully" : "Error updating dob";
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(summary));
		}
		else if (buttonName.equalsIgnoreCase("dataTableForm:ssnButton"))
		{
			name = params.get("dataTableForm:ssn");
			if (name == null || name.trim().length() == 0)
			{
				summary = "SSN required";
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(summary));
				return;
			}
			if (name.trim().length() != 11)
			{
				summary = "SSN format is not valid";
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(summary));
				return;
			}
			System.out.println("Original SSN Name is : " + selectedAnalyser.getSsn());
			System.out.println("SSN to update is : " + name);
			result = service.updateAnalyserRestrictedData(userId, "SSN", Integer.parseInt(selectedAnalyser.getAnalyserId().toString()),
			        Integer.valueOf(selectedAnalyser.getParentId()), newAnalyserList.get(itemPositionInList).getSsn(),
			        selectedAnalyser.getSsn().trim());
			valid = result.equals("1");
			summary = valid ? "SSN updated successfully" : "Error updating ssn";
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(summary));
		}
		else if (buttonName.equalsIgnoreCase("dataTableForm:reasonButton"))
		{
			valid = false;
			String reason = params.get("dataTableForm:reason");
			if (reason != null && reason.trim().length() > 0)
			{
				System.out.println("Original reason is : " + selectedAnalyser.getReason());
				System.out.println("reason to update is : " + reason);
				result = service.updateAnalyserRestrictedData(userId, "Reason", Integer.parseInt(selectedAnalyser.getAnalyserId().toString()),
				        Integer.valueOf(selectedAnalyser.getParentId()), newAnalyserList.get(itemPositionInList).getReason(),
				        selectedAnalyser.getReason().trim());
				valid = result.equals("1");
				summary = valid ? "Reason updated successfully" : "Error updating reason";
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(summary));
			}
		}
		else if (buttonName.equalsIgnoreCase("dataTableForm:isRehiredButton"))
		{
			String isRehired = params.get("dataTableForm:isRehired");
			if (isRehired != null && isRehired.trim().length() > 0)
			{
				System.out.println("Original isRehired is : " + selectedAnalyser.getIsRehired());
				System.out.println("isRehired to update is : " + isRehired);
				result = service.updateAnalyserRestrictedData(userId, "isRehired", Integer.parseInt(selectedAnalyser.getAnalyserId().toString()),
				        Integer.valueOf(selectedAnalyser.getParentId()), newAnalyserList.get(itemPositionInList).getIsRehired(),
				        selectedAnalyser.getIsRehired().trim());
				valid = result.equals("1");
				summary = valid ? "isRehired updated successfully" : "Error updating isRehired";
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(summary));
			}
		}
		else if (buttonName.equalsIgnoreCase("dataTableForm:startDateButton"))
		{
			name = params.get("dataTableForm:startDate");
			if (name == null || name.trim().length() == 0)
			{
				summary = "Start Date required";
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(summary));
				return;
			}
			Date currentDate = getDate(name);
			System.out.println(currentDate);
			if (isCurrentDateGreaterThanNextDate(maxDate, currentDate))
			{
				summary = "Start Date must be greater than 01/01/2001";
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(summary));
				return;
			}
			System.out.println("Original Start date is : " + selectedAnalyser.getStartDate());
			System.out.println("Start date to update is : " + name);
			result = service.updateAnalyserRestrictedData(userId, "StartDate", Integer.parseInt(selectedAnalyser.getAnalyserId().toString()),
			        Integer.valueOf(selectedAnalyser.getParentId()), newAnalyserList.get(itemPositionInList).getStartDate(),
			        selectedAnalyser.getStartDate().trim());
			valid = result.equals("1");
			summary = valid ? "Start Date updated successfully" : "Error updating start date";
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(summary));
		}
		else if (buttonName.equalsIgnoreCase("dataTableForm:endDateButton"))
		{
			name = params.get("dataTableForm:endDate");
			if (name == null || name.trim().length() == 0)
			{
				summary = "End Date required";
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(summary));
				return;
			}
			System.out.println("End Date   :: " + name);
			System.out.println("Start Date :: " + newAnalyserList.get(itemPositionInList).getStartDate());
			Date currentDate = getDate(name);
			Date startDate = getDate(newAnalyserList.get(itemPositionInList).getStartDate());
			if (isCurrentDateGreaterThanNextDate(startDate, currentDate))
			{
				summary = "End Date must be greater than start date";
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(summary));
				return;
			}
			System.out.println("Original End date is : " + newAnalyserList.get(itemPositionInList).getEndDate());
			System.out.println("Start date to update is : " + name);
			result = service.updateAnalyserRestrictedData(userId, "EndDate", Integer.parseInt(selectedAnalyser.getAnalyserId().toString()),
			        Integer.valueOf(selectedAnalyser.getParentId()), newAnalyserList.get(itemPositionInList).getEndDate(),
			        selectedAnalyser.getEndDate().trim());
			valid = result.equals("1");
			summary = valid ? "End Date updated successfully" : "Error updating end date";
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(summary));
		}
		else if (buttonName.equalsIgnoreCase("dataTableForm:terminateDateButton"))
		{
			name = params.get("dataTableForm:terminateDate");
			if (name == null || name.trim().length() == 0)
			{
				summary = "Terminate Date required";
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(summary));
				return;
			}
			System.out.println("Terminate Date   :: " + name);
			System.out.println("Start Date       :: " + newAnalyserList.get(itemPositionInList).getStartDate());
			Date terminateDate = getDate(name);
			Date startDate = getDate(newAnalyserList.get(itemPositionInList).getStartDate());
			if (isCurrentDateGreaterThanNextDate(startDate, terminateDate))
			{
				summary = "Terminate Date must be greater than start date";
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(summary));
				return;
			}
			result = service.updateAnalyserRestrictedData(userId, "TerminateDate", Integer.parseInt(selectedAnalyser.getAnalyserId().toString()),
			        Integer.valueOf(selectedAnalyser.getParentId()), newAnalyserList.get(itemPositionInList).getTerminateDate(),
			        selectedAnalyser.getTerminateDate().trim());
			valid = result.equals("1");
			summary = valid ? "Terminate Date updated successfully" : "Error updating Terminate date";
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(summary));
		}
		else if (buttonName.equalsIgnoreCase("dataTableForm:effectiveDateButton"))
		{
			name = params.get("dataTableForm:effectiveDate");
			if (name == null || name.trim().length() == 0)
			{
				summary = "Effective Date required";
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(summary));
				return;
			}
			Date effectiveDate = getDate(name);
			Date startDate = getDate(newAnalyserList.get(itemPositionInList).getStartDate());
			if (isCurrentDateGreaterThanNextDate(startDate, effectiveDate))
			{
				summary = "Effective Date must be greater than start date";
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(summary));
				return;
			}
			
			for (int i = 0; i < itemPositionInList; i++)
			{
				Date prevEffectiveDate = getDate(newAnalyserList.get(i).getEffectiveDate());
				if(prevEffectiveDate == null)
				{
					continue;
				}
				if (isCurrentDateGreaterThanNextDate(prevEffectiveDate, effectiveDate))
				{
					summary = "Effective Date must be greater than or equal to previous effective dates";
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(summary));
					return;
				}
			}
			for (int i = (itemPositionInList + 1); i < newAnalyserList.size(); i++)
			{
				Date nextEffectiveDate = getDate(newAnalyserList.get(i).getEffectiveDate());
				if(nextEffectiveDate == null)
				{
					continue;
				}
				if (isCurrentDateGreaterThanNextDate(effectiveDate, nextEffectiveDate))
				{
					summary = "Effective Date must be less than or equal to subsequent effective dates";
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(summary));
					return;
				}
			}
			result = service.updateAnalyserRestrictedData(userId, "EffectiveDate", Integer.parseInt(selectedAnalyser.getAnalyserId().toString()),
			        Integer.valueOf(selectedAnalyser.getParentId()), newAnalyserList.get(itemPositionInList).getEffectiveDate(),
			        selectedAnalyser.getEffectiveDate().trim());
			valid = result.equals("1");
			summary = valid ? "Effective Date updated successfully" : "Error updating Effective date";
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(summary));
		}
		else if (buttonName.equals("dataTableForm:commEffectiveDateButton"))
		{
			name = params.get("dataTableForm:commEffectiveDate");
			
			name = params.get("dataTableForm:commEffectiveDate");
			if (name == null || name.trim().length() == 0)
			{
				summary = "Commission Effective Date required";
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(summary));
				return;
			}
			Date commEffectiveDate = getDate(name);
			Date startDate = getDate(newAnalyserList.get(itemPositionInList).getStartDate());
			if (isCurrentDateGreaterThanNextDate(startDate, commEffectiveDate))
			{
				summary = "Commission Effective Date must be greater than start date";
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(summary));
				return;
			}
			for (int i = 0; i < itemPositionInList; i++)
			{
				Date prevCommEffectiveDate = getDate(newAnalyserList.get(i).getCommEffectiveDate());
				if (isCurrentDateGreaterThanNextDate(prevCommEffectiveDate, commEffectiveDate))
				{
					summary = "Commission effective date must be greater than or equal to previous commission effective dates";
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(summary));
					return;
				}
			}
			for (int i = (itemPositionInList + 1); i < newAnalyserList.size(); i++)
			{
				Date nextCommEffectiveDate = getDate(newAnalyserList.get(i).getCommEffectiveDate());
				if (isCurrentDateGreaterThanNextDate(commEffectiveDate, nextCommEffectiveDate))
				{
					summary = "Commission effective Date must be less than or equal to subsequent commission effective dates";
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(summary));
					return;
				}
			}
			result = service.updateAnalyserRestrictedData(userId, "CommEffDate", Integer.parseInt(selectedAnalyser.getAnalyserId().toString()),
			        Integer.valueOf(selectedAnalyser.getParentId()), newAnalyserList.get(itemPositionInList).getCommEffectiveDate(),
			        selectedAnalyser.getCommEffectiveDate().trim());
			valid = result.equals("1");
			summary = valid ? "Commission Effective Date updated successfully" : "Commission Error updating Effective date";
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(summary));
		}
		else if (buttonName.equalsIgnoreCase("dataTableForm:commissionPersonGrade1Button"))
		{
			String commissionPersonGrade1 = params.get("dataTableForm:commissionPersonGrade1");
			if (commissionPersonGrade1 != null)
			{
				System.out.println("Original commissionPersonGrade1 is : " + selectedAnalyser.getCommissionPersonGrade1());
				System.out.println("commissionPersonGrade1 to update is : " + commissionPersonGrade1);
				logger.debug("Original commissionPersonGrade1 is : " + selectedAnalyser.getCommissionPersonGrade1());
				logger.debug("commissionPersonGrade1 to update is : " + commissionPersonGrade1);
				result = service.updateAnalyserRestrictedData(userId, "CommissionPersonGrade1", Integer.parseInt(selectedAnalyser.getAnalyserId().toString()),
				        Integer.valueOf(selectedAnalyser.getParentId()), newAnalyserList.get(itemPositionInList).getCommissionPersonGrade1(),
				        selectedAnalyser.getCommissionPersonGrade1().trim());
				valid = result.equals("1");
				summary = valid ? "AE-1 Grade updated successfully" : "Error updating AE-1 Grade";
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(summary));
			}
		}
		else if (buttonName.equalsIgnoreCase("dataTableForm:commissionPersonGrade3Button"))
		{
			String commissionPersonGrade3 = params.get("dataTableForm:commissionPersonGrade3");
			if (commissionPersonGrade3 != null)
			{
				System.out.println("Original commissionPersonGrade3 is : " + selectedAnalyser.getCommissionPersonGrade3());
				System.out.println("commissionPersonGrade3 to update is : " + commissionPersonGrade3);
				logger.debug("Original commissionPersonGrade3 is : " + selectedAnalyser.getCommissionPersonGrade3());
				logger.debug("commissionPersonGrade3 to update is : " + commissionPersonGrade3);
				result = service.updateAnalyserRestrictedData(userId, "CommissionPersonGrade3", Integer.parseInt(selectedAnalyser.getAnalyserId().toString()),
				        Integer.valueOf(selectedAnalyser.getParentId()), newAnalyserList.get(itemPositionInList).getCommissionPersonGrade3(),
				        selectedAnalyser.getCommissionPersonGrade3().trim());
				valid = result.equals("1");
				summary = valid ? "AE-2 Grade updated successfully" : "Error updating AE-2 Grade";
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(summary));
			}
		}
		else if (buttonName.equalsIgnoreCase("dataTableForm:portfolioButton"))
		{
			String portfolio = params.get("dataTableForm:portfolio");
			if (portfolio == null || portfolio.trim().length() == 0)
			{
				summary = "Portfolio is required";
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(summary));
				return;
			}
			
			if (portfolio != null && portfolio.trim().length() > 0)
			{
				System.out.println("Original portfolio is : " + selectedAnalyser.getPortfolio());
				System.out.println("portfolio to update is : " + portfolio);
				result = service.updateAnalyserRestrictedData(userId, "Portfolio", Integer.parseInt(selectedAnalyser.getAnalyserId().toString()),
				        Integer.valueOf(selectedAnalyser.getParentId()), newAnalyserList.get(itemPositionInList).getPortfolio(),
				        selectedAnalyser.getPortfolio().trim());
				valid = result.equals("1");
				summary = valid ? "Portfolio updated successfully" : "Error updating portfolio";
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(summary));
			}
		}
		else if (buttonName.equalsIgnoreCase("dataTableForm:companyCodeButton"))
		{
			String companyCode = params.get("dataTableForm:companyCode");
			if (companyCode == null || companyCode.trim().length() == 0)
			{
				summary = "CompanyCode is required";
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(summary));
				return;
			}
			
			if (companyCode != null && companyCode.trim().length() > 0)
			{
				System.out.println("Original companyCode is : " + selectedAnalyser.getCompanyCode());
				System.out.println("companyCode to update is : " + companyCode);
				result = service.updateAnalyserRestrictedData(userId, "CompanyCode", Integer.parseInt(selectedAnalyser.getAnalyserId().toString()),
				        Integer.valueOf(selectedAnalyser.getParentId()), newAnalyserList.get(itemPositionInList).getCompanyCode(),
				        selectedAnalyser.getCompanyCode().trim());
				valid = result.equals("1");
				summary = valid ? "CompanyCode updated successfully" : "Error updating CompanyCode";
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(summary));
			}
		}
		
		
		if (valid)
		{
			analyserList = service.getAnalyzerDataForUpdate(id);
		}
	}
	
	private Date getDate(String dateInString)
	{
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
		// String dateInString = "7-Jun-2013";
		try
		{
			if (dateInString != null && dateInString != "")
			{
				Date date = formatter.parse(dateInString);
				System.out.println(date);
				System.out.println(formatter.format(date));
				return date;
			}
			else
			{
				System.out.println("Date is null or empty so returning null.");
				return null;
			}
		}
		catch (ParseException e)
		{
			e.printStackTrace();
			return null;
		}
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
	public Analyser getSelectedAnalyser()
	{
		return selectedAnalyser;
	}
	
	/**
	 * @param selectedAnalyser
	 *            the selectedAnalyser to set
	 */
	public void setSelectedAnalyser(Analyser selectedAnalyser)
	{
		this.selectedAnalyser = selectedAnalyser;
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
	
	/**
	 * @return the parentId
	 */
	public String getParentId()
	{
		return parentId;
	}
	
	/**
	 * @param parentId
	 *            the parentId to set
	 */
	public void setParentId(String parentId)
	{
		this.parentId = parentId;
	}
	
	/**
	 * @return the updateRecord
	 */
	public boolean isUpdateRecord()
	{
		return updateRecord;
	}
	
	/**
	 * @param updateRecord
	 *            the updateRecord to set
	 */
	public void setUpdateRecord(boolean updateRecord)
	{
		this.updateRecord = updateRecord;
	}
	
	/**
	 * @return the itemPositionInList
	 */
	public Integer getItemPositionInList()
	{
		return itemPositionInList;
	}
	
	/**
	 * @param itemPositionInList
	 *            the itemPositionInList to set
	 */
	public void setItemPositionInList(Integer itemPositionInList)
	{
		this.itemPositionInList = itemPositionInList;
	}
	
	/*private boolean isCurrentDateGreaterThanPrevious(Date currentDate, Date previousDate)
	{
		if (currentDate.compareTo(previousDate) < 0)
		{
			return true;
		}
		else
		{
			return false;
		}
	}*/
	
	private boolean isCurrentDateGreaterThanNextDate(Date currentDate, Date nextDate)
	{
		return currentDate.compareTo(nextDate) > 0;
	}
	
	public static void main(String[] argv)
	{
		
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		String dateInString = "07/06/2013";
		
		String nextDateInString = "08/06/2018";
		
		try
		{
			
			// Date date1 = formatter.parse(dateInString);
			//
			// Date date2 = formatter.parse(nextDateInString);
			//
			// if (date1.compareTo(date2) > 0) {
			// System.out.println("Date1 is after Date2");
			// } else if (date1.compareTo(date2) < 0) {
			// System.out.println("Date1 is before Date2");
			// } else if (date1.compareTo(date2) == 0) {
			// System.out.println("Date1 is equal to Date2");
			// }
			
			UpdateAnalyserDataBean obj = new UpdateAnalyserDataBean();
			
			String correctDate = "08/01/2018";
			String errorDate1 = "08/1/2018";
			String errorDate2 = "8/01/2018";
			String errorDate3 = "8/1/2018";
			String errorDate4 = null;
			String errorDate5 = "";
			String res = obj.fixDate(errorDate1);
			System.out.println("Error Date 1 :: " + errorDate1 + " :: Correct Date 1:: " + res);
			res = obj.fixDate(errorDate2);
			System.out.println("Error Date 2 :: " + errorDate2 + " :: Correct Date 2:: " + res);
			res = obj.fixDate(errorDate3);
			System.out.println("Error Date 3 :: " + errorDate3 + " :: Correct Date 3:: " + res);
			
			res = obj.fixDate(errorDate4);
			System.out.println("Error Date 4 :: " + errorDate4 + " :: Correct Date 4:: " + res);
			
			res = obj.fixDate(errorDate5);
			System.out.println("Error Date 5 :: " + errorDate5 + " :: Correct Date 3:: " + res);
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
	}
	
	/**
	 * @return the newAnalyserList
	 */
	public List<Analyser> getNewAnalyserList()
	{
		return newAnalyserList;
	}
	
	/**
	 * @param newAnalyserList
	 *            the newAnalyserList to set
	 */
	public void setNewAnalyserList(List<Analyser> newAnalyserList)
	{
		this.newAnalyserList = newAnalyserList;
	}
	
	/**
	 * @return the reasons
	 */
	public List<SelectItem> getReasons()
	{
		if (reasons == null)
		{
			reasons = new ArrayList<SelectItem>();
			
			reasons.add(new SelectItem("V - COVID-19", "V - COVID-19"));
			reasons.add(new SelectItem("I - COVID-19", "I - COVID-19"));
			reasons.add(new SelectItem("V - Resignation: New Job, Same Industry or Competitor", "V - Resignation: New Job, Same Industry or Competitor"));
			reasons.add(new SelectItem("V - Resignation: New Job, Different Industry", "V - Resignation: New Job, Different Industry"));
			reasons.add(new SelectItem("V - Resignation: Employed by Client", "V - Resignation: Employed by Client"));
			reasons.add(new SelectItem("V - Resignation: Personal Reasons (Relocation), Walk Off or Job Abandonment",
			        "V - Resignation: Personal Reasons (Relocation), Walk Off or Job Abandonment"));
			reasons.add(new SelectItem("P - End of Job Assignment", "P - End of Job Assignment"));
			reasons.add(new SelectItem("I - Involuntary Termination (Performance, Behavior, Policy Violation)",
			        "I - Involuntary Termination (Performance, Behavior, Policy Violation)"));
			reasons.add(new SelectItem("I - Job Elimination", "I - Job Elimination"));
			reasons.add(new SelectItem("I - Death/Disability", "I - Death/Disability"));
			reasons.add(new SelectItem("M - Analyzer Modification", "M - Analyzer Modification"));
			reasons.add(new SelectItem("M - Never started", "M - Never started"));
			reasons.add(
			        new SelectItem("T-Transfer/Change of Assignment (does transfer to PS)", "T-Transfer/Change of Assignment (does transfer to PS)"));
		}
		return reasons;
	}
	
	/**
	 * @param reasons
	 *            the reasons to set
	 */
	public void setReasons(List<SelectItem> reasons)
	{
		this.reasons = reasons;
	}
	
	public List<SelectItem> getTerminationReasonBeanByCompanyCodeList(String companyCode) 
	{
		if(companyCode == null || companyCode.isEmpty())
		{
			companyCode = Constants.DEFAULT_COMPANY_CODE;			
		}
		if (terminationReasonList == null) 
		{
			terminationReasonList = new ArrayList<SelectItem>();
			try
			{
				List<TerminationReasonDTO> reasonLists = terminationReasonservice.getTerminationReasonList(userId, companyCode);
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
				System.out.println("Exception in UpdateAnalyserDataBean --> getTerminationReasonBeanByCompanyCodeList.");
				logger.debug("Exception in UpdateAnalyserDataBean --> getTerminationReasonBeanByCompanyCodeList.");
				e.printStackTrace();
			}
		}
		return terminationReasonList;
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
				List<PortfolioDTO> portfolio = portfolioService.getAllPortfolios(userId, companyCode);

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
				System.out.println("Exception in UpdateAnalyserDataBean --> getPortfolioList.");
				logger.debug("Exception in UpdateAnalyserDataBean --> getPortfolioList.");
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