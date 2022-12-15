/**
 * 
 */
package com.disys.analyzer.jsf.bean;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import org.springframework.web.jsf.FacesContextUtils;

import com.disys.analyzer.config.util.Constants;
import com.disys.analyzer.config.util.FacesMessageSeverity;
import com.disys.analyzer.config.util.FacesUtils;
import com.disys.analyzer.model.dto.CommissionPersonGradeDTO;
import com.disys.analyzer.model.dto.UserDTO;
import com.disys.analyzer.service.AnalyserCommisionPersonService;

/**
 * @author Asim Shafique
 * @since Oct 19, 2018
 */
@ManagedBean
@ViewScoped
public class CommissionPersonGradeBean extends SpringBeanAutowiringSupport implements Serializable
{
	private static final String user_id = "user_id";
	private static final String apd_code = "adp_code";
	private static final long serialVersionUID = -7116434779894912673L;
	private Logger logger = LoggerFactory.getLogger(SickLeaveCostBean.class);
	private List<CommissionPersonGradeDTO> dataList;
	private List<CommissionPersonGradeDTO> filteredList;
	private CommissionPersonGradeDTO personDto;
	private List<UserDTO> activeCommPersonList;
	private Boolean showUpdateButton;
	private Integer actionType;
	private String effectiveDate;
	private SimpleDateFormat sdf;
	
	@Autowired private AnalyserCommisionPersonService analyserCommisionPersonService;
	
	public CommissionPersonGradeBean()
	{
		
	}
	
	@PostConstruct
	public void init()
	{
		try
		{
			FacesContextUtils.getRequiredWebApplicationContext(FacesContext.getCurrentInstance()).getAutowireCapableBeanFactory().autowireBean(this);
			fillActiveCommPersonList();
			initilizeDefaultValues();
		}
		catch (BeansException e)
		{
			logger.debug(e.getMessage());
			displayMessage("Error in Commisison Person Grade " + e.getMessage(), true);
			e.printStackTrace();
		}
		catch (IllegalStateException e)
		{
			logger.debug(e.getMessage());
			displayMessage("Error in Commisison Person Grade " + e.getMessage(), true);
			e.printStackTrace();
		}
		catch (Exception e)
		{
			logger.debug(e.getMessage());
			displayMessage("Error in Commisison Person Grade " + e.getMessage(), true);
			e.printStackTrace();
		}
	}
	
	private void fillActiveCommPersonList()
	{
		this.activeCommPersonList = analyserCommisionPersonService.spGetActiveCommissionPersons(FacesUtils.getCurrentUserId(), Constants.DEFAULT_COMPANY_CODE);
	}
	
	private void initilizeDefaultValues()
	{
		sdf = new SimpleDateFormat(FacesUtils.DATE_FORMAT);
		this.effectiveDate = sdf.format(new Date());
		this.personDto = new CommissionPersonGradeDTO();
		this.personDto.setScoreCardGrade("A");
		this.showUpdateButton = false;
		this.actionType = 1;
		this.dataList = analyserCommisionPersonService.spGetCommissionPersonGrade(FacesUtils.getCurrentUserId());
		
	}
	
	private boolean validateRecord(CommissionPersonGradeDTO person, boolean addMode) throws ParseException
	{
		if (person.getCommissionPersonName() == null || person.getCommissionPersonName().trim().length() == 0)
		{
			displayMessage("Please select commission person", true);
			return false;
		}
		if (person.getScoreCardGrade() == null || person.getScoreCardGrade().trim().length() == 0)
		{
			displayMessage("Please select grade", true);
			return false;
		}
		if (this.effectiveDate == null)
		{
			displayMessage("Please select effective date", true);
			return false;
		}
		/*else 
		{
			if(addMode)
			{
				String dateAry [] = this.effectiveDate.split("/");
				String year = dateAry[2];
				String month = dateAry[0];
				String day = dateAry[1];
		        LocalDate date1 = LocalDate.of(Integer.valueOf(year), Integer.valueOf(month), Integer.valueOf(day));
		        LocalDate date2 = LocalDate.now();
		        if (date1.isBefore(date2))
				{
					displayMessage("Effective date should be greater than current date", true);
					return false;
				}
			}
		}*/
		
		return true;
	}
	
	public void clearForm()
	{
		try
		{
			initilizeDefaultValues();
		}
		catch (Exception e)
		{
			logger.debug(e.getMessage());
			displayMessage("Error in Commisison Person Grade " + e.getMessage(), true);
			e.printStackTrace();
		}
		
	}
	
	public void editCommPersonGrade(CommissionPersonGradeDTO commissionPersonGradeDTO)
	{
		this.showUpdateButton = true;
		this.personDto = commissionPersonGradeDTO;
		this.actionType = 2;
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(this.personDto.getEffectiveDate().getTime());
		System.out.println("Calander time :: " + cal.getTime());
		this.effectiveDate = sdf.format(cal.getTime());
	}
	
	public void addUpdateCommPersonGrade()
	{
		String msg = null;
		try
		{
			if (validateRecord(personDto, (this.actionType == 1)))
			{
				if(this.personDto.getCommissionPersonGradeId() == null)
				{
					this.personDto.setCommissionPersonGradeId(0);
				}
				this.personDto.setEffectiveDate(new java.sql.Timestamp(sdf.parse(this.effectiveDate).getTime()));
				this.personDto.setCommissionPersonEmail(fetchValue(user_id));
				this.personDto.setCommissionPersonEmplId(fetchValue(apd_code));
				this.personDto.setEntryDateTime(new java.sql.Timestamp(Calendar.getInstance().getTimeInMillis()));
				String result = analyserCommisionPersonService.spAddUpdateCommissionPersonGrade(FacesUtils.getCurrentUserId(), this.personDto, this.actionType);
				if (actionType == 1)
				{
					initilizeDefaultValues();
					msg = result.equalsIgnoreCase("0") ? "Successfully Saved Commission person grade"
					        : result.equalsIgnoreCase("2") ? "Effective date must be less than 90 days old and greater than last effective date of selected"
					        		: result.equalsIgnoreCase("3") ? "Effective date must be less than 90 days old and greater than last effective date of selected"		
					                : "Cannot Saved Commission person grade";
				}
				else
				{
					this.dataList = analyserCommisionPersonService.spGetCommissionPersonGrade(FacesUtils.getCurrentUserId());
					msg = result.equalsIgnoreCase("0") ? "Successfully updated Commission person grade"
					        : result.equalsIgnoreCase("2") ? "Effective date must be less than 90 days old and greater than last effective date of selected"
					        		: result.equalsIgnoreCase("3") ? "Effective date must be less than 90 days old and greater than last effective date of selected"
					                : "Cannot update Commission person grade";
				}
				displayMessage(msg, (!result.equalsIgnoreCase("0")));
			}
		}
		catch (Exception e)
		{
			logger.debug(e.getMessage());
			msg = actionType == 1 ? "Error on save Commisison Person Grade" : "Error on Commisison Person Grade";
			displayMessage(msg, true);
			e.printStackTrace();
		}
	}
	
	private String fetchValue(String column)
	{
		if (this.activeCommPersonList != null && activeCommPersonList.size() > 0)
		{
			for (UserDTO userDTO : this.activeCommPersonList)
			{
				if (userDTO.getName().trim().equalsIgnoreCase(this.personDto.getCommissionPersonName().trim()))
				{
					if (column.equalsIgnoreCase(user_id))
					{
						return userDTO.getUserId();
					}
					else
					{
						return userDTO.getApdCode();
					}
				}
			}
		}
		return "";
	}
	
	private void displayMessage(String msg, boolean error)
	{
		FacesUtils.addGlobalMessage(error ? FacesMessageSeverity.ERROR : FacesMessageSeverity.INFO, msg);
	}
	
	public Logger getLogger()
	{
		return logger;
	}
	
	public void setLogger(Logger logger)
	{
		this.logger = logger;
	}

	public List<CommissionPersonGradeDTO> getDataList()
	{
		return dataList;
	}

	public void setDataList(List<CommissionPersonGradeDTO> dataList)
	{
		this.dataList = dataList;
	}

	public List<CommissionPersonGradeDTO> getFilteredList()
	{
		return filteredList;
	}

	public void setFilteredList(List<CommissionPersonGradeDTO> filteredList)
	{
		this.filteredList = filteredList;
	}

	public CommissionPersonGradeDTO getPersonDto()
	{
		return personDto;
	}

	public void setPersonDto(CommissionPersonGradeDTO personDto)
	{
		this.personDto = personDto;
	}

	public List<UserDTO> getActiveCommPersonList()
	{
		return activeCommPersonList;
	}

	public void setActiveCommPersonList(List<UserDTO> activeCommPersonList)
	{
		this.activeCommPersonList = activeCommPersonList;
	}

	public Boolean getShowUpdateButton()
	{
		return showUpdateButton;
	}

	public void setShowUpdateButton(Boolean showUpdateButton)
	{
		this.showUpdateButton = showUpdateButton;
	}

	public Integer getActionType()
	{
		return actionType;
	}

	public void setActionType(Integer actionType)
	{
		this.actionType = actionType;
	}

	public String getEffectiveDate()
	{
		return effectiveDate;
	}

	public void setEffectiveDate(String effectiveDate)
	{
		this.effectiveDate = effectiveDate;
	}
	
	public static void main (String args []) throws ParseException {
//		String dateStr = "10/21/2018";
//		SimpleDateFormat sdf = new SimpleDateFormat(FacesUtils.DATE_FORMAT);
//		Date date = sdf.parse(dateStr);
//		Calendar cal = Calendar.getInstance();
//		cal.setTime(date);
//		cal.set(Calendar.HOUR_OF_DAY, 0);
//		cal.set(Calendar.MINUTE, 0);
//		cal.set(Calendar.SECOND, 0);
//		System.out.println("Current date :: " + cal.getTime());
//		Calendar cal1 = Calendar.getInstance();
//		//cal1.setTime(new Date());
//		cal1.set(Calendar.HOUR_OF_DAY, 0);
//		cal1.set(Calendar.MINUTE, 0);
//		cal1.set(Calendar.SECOND, 0);
//		System.out.println("Current date 1:: " + cal.getTime());
//		System.out.println("Compare :: " + cal1.before(cal));
//		if(cal1.before(cal))
//		{
//			System.out.println("in if statement");
//		}
		
		DateTimeFormatter sdf = DateTimeFormatter.ofPattern(FacesUtils.DATE_FORMAT);
        LocalDate date1 = LocalDate.of(2018, 01, 01);
        
        LocalDate date2 = LocalDate.now();
        
        //LocalDate date2 = LocalDate.of(2010, 01, 31);

        System.out.println("date1 : " + sdf.format(date1));
        System.out.println("date2 : " + sdf.format(date2));

        System.out.println("Is...");
        if (date1.isAfter(date2)) {
            System.out.println("Date1 is after Date2");
        }

        if (date1.isBefore(date2)) {
            System.out.println("Date1 is before Date2");
        }

        if (date1.isEqual(date2)) {
            System.out.println("Date1 is equal Date2");
        }
		
	}
}