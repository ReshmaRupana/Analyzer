/**
 * 
 */
package com.disys.analyzer.reports.others;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.disys.analyzer.config.util.FacesMessageSeverity;
import com.disys.analyzer.config.util.FacesUtils;
import com.disys.analyzer.config.util.WriteToFile;
import com.disys.analyzer.exception.ReportDataNotFoundException;
import com.disys.analyzer.reports.util.ReportCriteria;
import com.disys.analyzer.service.ApplicationUserService;

import java.time.LocalDate;
import javax.servlet.ServletContext;
import org.springframework.beans.factory.annotation.Value;

/**
 * @author Sajid
 * 
 */
@ManagedBean
@ViewScoped
public class AnalyzerIRCReportsBean extends SpringBeanAutowiringSupport implements Serializable
{
	private static final long serialVersionUID = 5482158626813916046L;
	private Logger logger = LoggerFactory.getLogger(getClass());
	private final static String FILE_NAME = "AnalyzerIRCReport";
	private final static String FILE_EXTENSION = ".xlsx";
	private List<Map<String, Object>> reportData;
	private List<Map<String, Object>> filteredList;
	private String completeFilePath;
	private StreamedContent generatedExcelFile;
	private String newFileName;
	
	private String searchString;
	private String period;
	private String status;
	private String history;
	
	private List<SelectItem> reportPeriod;
	
	private Boolean enableExportButton;
	private Integer roleId;
	
	private String monthlyReportDate;
	private Boolean enableAdministrativeActions;
	
	@Value("#{'${environment}'=='development' ? '${development.download.path}' : '${production.download.path}'}")
	private String downloadFilePath;
	
	@Autowired 
	private ApplicationUserService applicationUserService;
	
	public AnalyzerIRCReportsBean()
	{
		logger.debug("Inside AnalyzerIRCReportsBean");
		roleId = applicationUserService.getRoleId(FacesUtils.getCurrentUserId());
		logger.debug("Current user role is : "+roleId);
		
		if(roleId == 3){
			enableExportButton = false;
			enableAdministrativeActions = true;
		} else {
			enableExportButton = checkIfItsWorkingHour();
			enableAdministrativeActions = false;
		}
		//generateReport();
	}
	
	public void eraseFilter()
	{
		this.reportData = null;
	}
	
	public void exportToExcel() throws Exception
	{
		List<Map<String, Object>> reportDataCopy = new ArrayList<Map<String, Object>>(0);
		try
		{
			if(enableExportButton){
				FacesUtils.addGlobalMessage(FacesMessageSeverity.ERROR, "Error", "You can download report only before 8 AM or after 7PM");
				return;
			}
			String reportName = "ANALYZER_IRC_REPORT";
			if(searchString == null || searchString.isEmpty()){
				searchString = "ALL";
			}
			
			Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
			
			if(params != null && params.size() > 0){
				Iterator<Map.Entry<String, String>> iterator = params.entrySet().iterator();
				while (iterator.hasNext())
				{
					Map.Entry<String, String> obj = iterator.next();
					 System.out.println("=== ( "+obj.getKey() + " = "+obj.getValue().toString()+" )");
					 logger.debug("=== ( "+obj.getKey() + " = "+obj.getValue().toString()+" )");
				}
			}
			
			if(status == null || status.isEmpty()){
				status = "Active";
			}
			
			if(history == null || history.isEmpty()){
				history = "Active";
			}
			
			String reportParam = FacesUtils.getCurrentUserId() + "|" + 1 + "|" + searchString + "|" + status + "|" + history + "|" + period;
			ReportCriteria rc = new ReportCriteria(reportName, reportParam);
			reportDataCopy = rc.getReport();
			System.out.println(reportDataCopy.size());
		}
		catch (Exception e)
		{
			logger.error("Error in ANALYZER_IRC_REPORT :: " + e.getMessage());
		}
		if (reportDataCopy == null || reportDataCopy.size() == 0)
		{
			FacesUtils.addGlobalMessage(FacesMessageSeverity.ERROR, "No data to export", "There is no data to export to excel!");
			throw new ReportDataNotFoundException("No data to export");
		}
		
		/*
		 * Following payload is received from SP
		 */
		
		Map<String, String> columnNameMappings = new HashMap<String, String>();
		
		columnNameMappings.put("CANDIDATE_NAME", "Candidate Name");
		columnNameMappings.put("JOB_TITLE", "Job Title");
		columnNameMappings.put("CLIENTNAME", "Client Name");
		columnNameMappings.put("WORKSITEADDRESS1", "Work Location");
		columnNameMappings.put("DEALTYPE", "Deal Type");
		columnNameMappings.put("LNAME", "Last Name");
		columnNameMappings.put("FNAME", "First Name");
		columnNameMappings.put("SUBCONTRACTORNAME", "Subcontractor Name");
		columnNameMappings.put("MANAGER_NAME", "Manager Name");
		columnNameMappings.put("WORKSITEADDRESS2", "Work Address 2");
		columnNameMappings.put("WORKSITECITY", "Work Site City");
		columnNameMappings.put("WORKSITESTATE", "Work Site State");
		columnNameMappings.put("WORKSITEZIPCODE", "Work Site Zipcode");
		columnNameMappings.put("WORKSITE_PHONE", "Work Site Phone");
		columnNameMappings.put("WORKSITEFAX", "Work Site Fax");
		columnNameMappings.put("WORKSITE_EMAIL", "Work Site Email");
		columnNameMappings.put("ADDRESS1", "Address");
		columnNameMappings.put("ADDRESS2", "Address");
		columnNameMappings.put("SUBMISSIONDATE", "Submission Date");
		columnNameMappings.put("COMMISION1", "Commission 1");
		columnNameMappings.put("COMMISION2", "Commission 2");
		columnNameMappings.put("COMMISION3", "Commission 3");
		columnNameMappings.put("COMMISION4", "Commission 4");
		columnNameMappings.put("DOB", "Date of Birth");
		columnNameMappings.put("EMAIL", "Email");
		columnNameMappings.put("EMPTYPE", "Employee Type");
		columnNameMappings.put("THW", "Total Hours Worked");
		columnNameMappings.put("BILLRATE", "Bill Rate");
		columnNameMappings.put("PAYRATE", "Pay Rate");
		columnNameMappings.put("CLIENTID", "Client ID");
		columnNameMappings.put("CLIENTADDRESSID", "Client Address ID");
		columnNameMappings.put("SITEID", "Site ID");
		columnNameMappings.put("STARTDATE", "Start Date");
		columnNameMappings.put("ENDDATE", "End Date");
		columnNameMappings.put("EFFECTIVEDATE", "Effective Date");
		columnNameMappings.put("PTO", "PTO");
		columnNameMappings.put("OFFICE", "Office");
		columnNameMappings.put("HRBP", "Liaison Name");
		columnNameMappings.put("JOBCATEGORY", "Job Category");
		
		columnNameMappings.put("EMPLOYEECATEGORY","Employee Category");
		columnNameMappings.put("UNEMPLOYEDFORTWOMONTHS", "Unemployed For Two Months");
		columnNameMappings.put("CONSULTANTJOBBOARD", "Consultant Job Board");
		columnNameMappings.put("GENDER", "Gender");
		columnNameMappings.put("FLSASTATUS", "Flsa Status");
		columnNameMappings.put("VERTICAL", "Vertical");
		columnNameMappings.put("PRODUCT", "Product");
		columnNameMappings.put("TRAVELREQUIRED", "Travel Required");
		columnNameMappings.put("RECRUITINGCLASSIFICATION", "Recruiting Team");
		columnNameMappings.put("MANAGINGDIRECTOR", "Managing Director");
		columnNameMappings.put("STATE", "State");
		columnNameMappings.put("COMMEFFDATE", "Commission Effective Date");
		columnNameMappings.put("AE", "Account Executive 1");
		columnNameMappings.put("COMMISSIONMODEL1", "Commission Model 1");
		columnNameMappings.put("COMMISION_PERCENT1", "Commission percentage 1");
		columnNameMappings.put("COMMISSIONPERSONGRADE1", "Commission Person Grade 1");
		columnNameMappings.put("EXECORPHAN", "Exec Orphan");
		columnNameMappings.put("SPLITCOMMISSIONPERCENTAGE1", "Split Commission Percentage 1");
		columnNameMappings.put("REC", "Recruiter 1");
		columnNameMappings.put("COMMISSIONMODEL2", "Commission Model 2");
		columnNameMappings.put("COMMISION_PERCENT2", "Commission percentage 2");
		columnNameMappings.put("RECRUITERORPHAN", "Recruiter Orphan");
		columnNameMappings.put("SPLITCOMMISSIONPERCENTAGE2", "Split Commission Percentage 2");
		columnNameMappings.put("ONETIME_BILL", "One time bill");
		columnNameMappings.put("ANNUALPAY", "Annual Pay");
		columnNameMappings.put("DOUBLETIME", "Double time ");
		columnNameMappings.put("SUBCONTRACTORID", "Subcontractor ID");
		columnNameMappings.put("EMPLOYEECLASS", "Employee Class");
		columnNameMappings.put("ONETIME_PAY", "One time pay");
		columnNameMappings.put("FLSAREFERANCE", "Flsa Reference");
		
		columnNameMappings.put("GROSSPROFIT", "Gross Profit");
		columnNameMappings.put("CUSTOM1", "Custom 1");
		columnNameMappings.put("CUSTOM2", "Custom 2");
		columnNameMappings.put("TAXES", "Taxes");
		columnNameMappings.put("INITIAL", "Initial");
		columnNameMappings.put("COMMISSIONABLEPROFIT", "Commissionable Profit");
		columnNameMappings.put("COMMISSION", "Commission");
		columnNameMappings.put("CITY", "City");
		columnNameMappings.put("ZIPCODE", "Zipcode");
		columnNameMappings.put("TELEPHONE", "Telephone");
		columnNameMappings.put("WORKPHONE", "Work phone");
		columnNameMappings.put("MOBILE_PAGER", "Mobile/Pager");
		columnNameMappings.put("WORKEMAIL", "Work Email");
		columnNameMappings.put("COMMISSIONPERSONGRADE2", "Commission Person Grade 2");
		columnNameMappings.put("AE2", "Account Executive 1 / Account Executive 2");
		columnNameMappings.put("COMMISSIONMODEL3", "Commission Model 3");
		columnNameMappings.put("COMMISION_PERCENT3", "Commission percentage 3");
		columnNameMappings.put("COMMISSIONPERSONGRADE3", "Commission Person Grade 3");
		columnNameMappings.put("OTHER1ORPHAN", "Other 1 Orphan");
		columnNameMappings.put("SPLITCOMMISSIONPERCENTAGE3", "Split Commission Percentage 3");
		
		columnNameMappings.put("REC2", "Recruiter 2");
		columnNameMappings.put("COMMISSIONMODEL4", "Commission Model 4");
		columnNameMappings.put("COMMISION_PERCENT4", "Commission percentage 4");
		columnNameMappings.put("COMMISSIONPERSONGRADE4", "Commission Person Grade 4");
		columnNameMappings.put("OTHER2ORPHAN", "Other 2 Orphan");
		columnNameMappings.put("SPLITCOMMISSIONPERCENTAGE4", "Split Commission Percentage 4");
		columnNameMappings.put("SICKLEAVEPERHOURRATE", "Sick leave per hour rate");
		columnNameMappings.put("SICKLEAVECAP", "Sick leave cap");
		columnNameMappings.put("PTOLIMITTOOVERRIDESICK", "Pto Limit to over ride sick");
		columnNameMappings.put("DISCOUNT", "Discount");
		columnNameMappings.put("OTHER_DOLLAR", "Other Dollar");
		columnNameMappings.put("DISCOUNTRATE", "Discount Rate");
		columnNameMappings.put("ANALYSERID", "Analyser ID");
		columnNameMappings.put("PARENTID", "Parent ID");
		
		columnNameMappings.put("LEAVE", "Leave");
		columnNameMappings.put("PSCLIENTID", "PS Client ID");
		columnNameMappings.put("GROSSPROFITPERCENTAGE", "Gross Profit Percentage");
		columnNameMappings.put("TERMINATEDATE", "Terminate Date");
		columnNameMappings.put("SUBMISSIONDATE","Submission Date");
		columnNameMappings.put("USERID", "User ID");
		columnNameMappings.put("PROFIT", "Profit");
		columnNameMappings.put("GA", "GA");
		columnNameMappings.put("REASON", "Reason");
		columnNameMappings.put("INDUSTRY", "Industry");
		columnNameMappings.put("SICKLEAVECOST", "Sick Leave Cost");
		columnNameMappings.put("OTHERAMOUNT_HOUR", "Other Amount Per Hour");
		columnNameMappings.put("REJECTIONSTATUS", "Rejection Status");
		columnNameMappings.put("REJECTEDBY", "Rejected By");
		columnNameMappings.put("REJECTIONDATE", "Rejection Date");
		columnNameMappings.put("REGION", "Region");
		
		
		
		columnNameMappings.put("EQUIPMENTCOST", "Equipment Cost");
		columnNameMappings.put("DOUBLETIMEBILL", "Double time bill");
		
		
		
		columnNameMappings.put("VS","MSP Client Partner/VS");
		columnNameMappings.put("PRAC","PRAC");
		columnNameMappings.put("DELIV","DELIV");
		columnNameMappings.put("BDE","BDE");
		columnNameMappings.put("PROP","PROP");
		columnNameMappings.put("VS_P","MSP_P");
		columnNameMappings.put("PRAC_P","PRAC_P");
		columnNameMappings.put("DELIV_P","DELIV_P");
		columnNameMappings.put("BDE_P","BDE_P");
		columnNameMappings.put("PROP_P","PROP_P");
		columnNameMappings.put("VS_A","MSP_A");
		columnNameMappings.put("PRAC_A","PRAC_A");
		columnNameMappings.put("DELIV_A","DELIV_A");
		columnNameMappings.put("BDE_A","BDE_A");
		columnNameMappings.put("PROP_A","PROP_A");
		columnNameMappings.put("ANALYZERCATEGORY2","Analyzer Category 2");
		columnNameMappings.put("ISBONUSELIGIBLE", "Is bonus eligible");
		columnNameMappings.put("BONUSAMOUNT","Bonus Amount");
		columnNameMappings.put("BONUSPERCENTAGE", "Bonus Percentage");
		columnNameMappings.put("SKILLCATEGORY","Skill Category");
		columnNameMappings.put("EMPLOYEEVETERAN","Employee Veteran");
		columnNameMappings.put("PERDIEM","Per Diem");
		columnNameMappings.put("PROJECTCOST","Project Cost");
		columnNameMappings.put("EQUIPMENTCOST","Equipment Cost");
		columnNameMappings.put("BILLABLECOST","Billable Cost");
		columnNameMappings.put("IMMIGRATIONCOST","Immigration Cost");
		columnNameMappings.put("REFERRALFEEAMOUNT","Referral Fee Amount");
		columnNameMappings.put("BILLABLEPTO","Billable PTO");
		columnNameMappings.put("NONBILLABLEPTO","Non Billable PTO");
		columnNameMappings.put("BILLABLEPTOCOST","Billable PTO Cost");
		columnNameMappings.put("NONBILLABLEPTOCOST","Non Billable PTO Cost");
		columnNameMappings.put("TOTALHOLIDAYS","Total Holidays");
		columnNameMappings.put("BILLABLEHOLIDAYS","Billable Holidays");
		columnNameMappings.put("NONBILLABLEHOLIDAYS","Non Billable Holidays");
		columnNameMappings.put("BILLABLEHOLIDAYSCOST","Billable Holidays Cost");
		columnNameMappings.put("NONBILLABLEHOLIDAYSCOST","Non Billable Holidays Cost");
		columnNameMappings.put("FALSETERMINATION","False Termination");
		columnNameMappings.put("NEWPARENTID","New Parent Id");
		columnNameMappings.put("DISTANCEFROMWORKSITE","Distance From Worksite");
		columnNameMappings.put("ISADDRESSUSPSVALIDATED","Is USPS Address validated");
		columnNameMappings.put("USPSADDRESSVALIDATIONDATE","USPS Address Validation Date");
		columnNameMappings.put("APP_BKUP","App_Bkup");
		columnNameMappings.put("APPROVALDATE","Approval Date");
		columnNameMappings.put("APPROVED_BY_MANAGER","Approved By Manager");
		columnNameMappings.put("ISREHIRED","Is Rehired");
		columnNameMappings.put("REHIREDATE","Rehire Date");
		columnNameMappings.put("RECORDSTATUS","Record Status");
		columnNameMappings.put("COMPANYCODE","Company Code");
		
		XSSFWorkbook workbook = new XSSFWorkbook();
		
		CellStyle cellStyle = workbook.createCellStyle();
		Font font = workbook.createFont();
		font.setBoldweight(Font.BOLDWEIGHT_BOLD);
		cellStyle.setFont(font);
		XSSFSheet sheet = workbook.createSheet("Analyzer IRC");
		/*
		 * 
		 * To print column information from the result set
		 */
		int rowNum = 0;
		int colNum = 0;
		System.out.println("Creating excel");
		
		Row row = null;
		List<Integer> skipColumnsFromPrint = new ArrayList<Integer>();
		int count = 0;
		Integer columnNumber = 0;
		for (Map<String, Object> map : reportDataCopy)
		{
			row = sheet.createRow(rowNum++);
			colNum = 0;
			Cell cell = null;
			Iterator<Map.Entry<String, Object>> iterator = map.entrySet().iterator();
			count++;
			if (count > 1)
			{
				break;
			}
			while (iterator.hasNext())
			{
				columnNumber++;
				Map.Entry<String, Object> entry = iterator.next();
				try
				{
					String val = "";
					if (entry != null)
					{
						val = entry.toString();
						val = val.substring(0, val.indexOf('='));
					}
					if (!val.isEmpty() && columnNameMappings.containsKey(val))
					{
						/*
						if (val.equalsIgnoreCase("WORKSITEADDRESS2") || val.equalsIgnoreCase("ADDRESS2") || val.equalsIgnoreCase("AE2") || val.equalsIgnoreCase("REC2"))
						{
							continue;
						}
						*/
						cell = row.createCell(colNum++);
						cell.setCellStyle(cellStyle);
						cell.setCellValue(columnNameMappings.get(val));
					}
					else
					{
						skipColumnsFromPrint.add(columnNumber);
					}
				}
				catch (Exception ex)
				{
					logger.debug("Exception in "+logger.getName()+" while writing to the file for : "+entry.toString() +" by user id : "+FacesUtils.getCurrentUserId() + ex.getLocalizedMessage());
					continue;
				}
			}
		}
		
		// header row is printed now, start from the second row
		rowNum = 1;
		for (Map<String, Object> map : reportDataCopy)
		{
			row = sheet.createRow(rowNum++);
			colNum = 0;
			Cell cell = null;
			Integer innerColumnNumber = 0;
			Iterator<Map.Entry<String, Object>> iterator = map.entrySet().iterator();
			/*int recRecord = 0;
			int recRecordColumn = 0;
			String recRecordVal = "";
			
			int aeRecord = 0;
			int aeRecordColumn = 0;
			String aeRecordVal = "";
			
			int location = 0;
			int locationColumn = 0;
			String locationVal = "";
			
			int workLocation = 0;
			int workLocationColumn = 0;
			String workLocationVal = "";*/
			while (iterator.hasNext())
			{
				Map.Entry<String, Object> entry = iterator.next();
				try
				{
					innerColumnNumber++;
					// check if we want to show this column on excel file
					if (!skipColumnsFromPrint.contains(innerColumnNumber))
					{
						/*if (entry.getKey().equalsIgnoreCase("ADDRESS1") || entry.getKey().equalsIgnoreCase("ADDRESS2"))
						{
							if (location == 0)
							{
								locationColumn = colNum;
								cell = row.createCell(colNum++);
							}
							locationVal = locationVal + entry.getValue().toString() + " ";
							if (location == 1)
							{
								cell = row.getCell(locationColumn);
								cell.setCellValue(locationVal);
							}
							location += 1;
						}
						else if (entry.getKey().equalsIgnoreCase("WORKSITEADDRESS1") || entry.getKey().equalsIgnoreCase("WORKSITEADDRESS2"))
						{
							if (workLocation == 0)
							{
								workLocationColumn = colNum;
								cell = row.createCell(colNum++);
							}
							workLocationVal = workLocationVal + entry.getValue().toString() + " ";
							if (workLocation == 1)
							{
								cell = row.getCell(workLocationColumn);
								cell.setCellValue(workLocationVal);
							}
							workLocation += 1;
						}
						else if (entry.getKey().equalsIgnoreCase("REC") || entry.getKey().equalsIgnoreCase("REC2"))
						{
							if (recRecord == 0)
							{
								recRecordColumn = colNum;
								cell = row.createCell(colNum++);
							}
							recRecordVal = recRecordVal + entry.getValue().toString() + "/";
							if (recRecord == 1)
							{
								cell = row.getCell(recRecordColumn);
								cell.setCellValue(recRecordVal);
							}
							recRecord += 1;
						}
						else if (entry.getKey().equalsIgnoreCase("AE") || entry.getKey().equalsIgnoreCase("AE2"))
						{
							if (aeRecord == 0)
							{
								aeRecordColumn = colNum;
								cell = row.createCell(colNum++);
							}
							aeRecordVal = aeRecordVal + entry.getValue().toString() + "/";
							if (aeRecord == 1)
							{
								cell = row.getCell(aeRecordColumn);
								cell.setCellValue(aeRecordVal);
							}
							aeRecord += 1;
						}
						else
						{
							cell = row.createCell(colNum++);
							if (FacesUtils.checkIfValueIsDouble(entry.getValue().toString()))
							{
								cell.setCellValue(Double.parseDouble(entry.getValue().toString()));
							}
							else
							{
								cell.setCellValue(entry.getValue().toString());
							}
						}*/
							cell = row.createCell(colNum++);
							if (FacesUtils.checkIfValueIsDouble(entry.getValue().toString()))
							{
								cell.setCellValue(Double.parseDouble(entry.getValue().toString()));
							}
							else
							{
								cell.setCellValue(entry.getValue().toString());
							}						
					}
				}
				catch (Exception ex)
				{
					logger.debug("Exception in "+logger.getName()+" while writing to the file for : "+entry.toString() +" by user id : "+FacesUtils.getCurrentUserId() + ex.getLocalizedMessage());
					continue;
				}
			}
		}
		
		try
		{
			String realPath = FacesUtils.getTempFilePath();
			Long dateTime = System.currentTimeMillis();
			completeFilePath = realPath + FILE_NAME + "-" + dateTime + FILE_EXTENSION;
			this.setCompleteFilePath(completeFilePath);
			this.setNewFileName(FILE_NAME + "-" + dateTime + FILE_EXTENSION);
			logger.debug("File for downloading is : " + completeFilePath);
			System.out.println("File for downloading is : " + completeFilePath);
			
			WriteToFile objWriteToFile = new WriteToFile();
			File file = objWriteToFile.createFile(completeFilePath);
			
			FileOutputStream outputStream = new FileOutputStream(file);
			
			workbook.write(outputStream);
			workbook.close();
			
			outputStream.flush();
			outputStream.close();
			downloadExcelFile(FILE_NAME + "-" + dateTime + FILE_EXTENSION);
			
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
	}
	
	public void downloadExcelFile(String filePath)
	{
		try
		{
			if (filePath == null || filePath.equals(""))
			{
				filePath = newFileName;
			}
			InputStream stream = new FileInputStream(FacesUtils.getTempFilePath() + filePath);
			generatedExcelFile = new DefaultStreamedContent(stream, "application/vnd.ms-excel", filePath);
		}
		catch (Exception e)
		{
			System.err.println("Empty file path");
			return;
		}
		
	}
	
	public void print()
	{
		if (reportData == null || reportData.size() == 0)
		{
			FacesUtils.addGlobalMessage(FacesMessageSeverity.ERROR, "No data to print", "There is no data to print!");
			return;
		}
	}
	
	public void generateReport()
	{
		logger.debug("About to create Analyzer IRC report");
		String reportName = "ANALYZER_IRC_REPORT";
		if(searchString == null || searchString.isEmpty()){
			searchString = "ALL";
		}
		String reportParam = FacesUtils.getCurrentUserId() + "|" + 0 + "|" + searchString + "|" + status + "|" + history+"|"+period;
		ReportCriteria rc = null;
		reportData = new ArrayList<Map<String, Object>>();
		try
		{
			rc = new ReportCriteria(reportName, reportParam);
			reportData = rc.getReport();// get the data from the array list
			System.out.println(reportData.size());
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * @return the reportData
	 */
	public List<Map<String, Object>> getReportData()
	{
		return reportData;
	}
	
	/**
	 * @param reportData
	 *            the reportData to set
	 */
	public void setReportData(List<Map<String, Object>> reportData)
	{
		this.reportData = reportData;
	}
	
	/**
	 * @return the completeFilePath
	 */
	public String getCompleteFilePath()
	{
		return completeFilePath;
	}
	
	/**
	 * @param completeFilePath
	 *            the completeFilePath to set
	 */
	public void setCompleteFilePath(String completeFilePath)
	{
		this.completeFilePath = completeFilePath;
	}
	
	/**
	 * @return the newFileName
	 */
	public String getNewFileName()
	{
		return newFileName;
	}
	
	/**
	 * @param newFileName
	 *            the newFileName to set
	 */
	public void setNewFileName(String newFileName)
	{
		this.newFileName = newFileName;
	}
	
	/**
	 * @return the generatedExcelFile
	 */
	public StreamedContent getGeneratedExcelFile()
	{
		return generatedExcelFile;
	}
	
	/**
	 * @return the filteredList
	 */
	public List<Map<String, Object>> getFilteredList()
	{
		return filteredList;
	}
	
	/**
	 * @param filteredList
	 *            the filteredList to set
	 */
	public void setFilteredList(List<Map<String, Object>> filteredList)
	{
		this.filteredList = filteredList;
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
	 * @return the status
	 */
	public String getStatus()
	{
		return status;
	}
	
	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(String status)
	{
		this.status = status;
	}
	
	/**
	 * @return the history
	 */
	public String getHistory()
	{
		return history;
	}
	
	/**
	 * @param history
	 *            the history to set
	 */
	public void setHistory(String history)
	{
		this.history = history;
	}

	/**
	 * @return the reportPeriod
	 */
	public List<SelectItem> getReportPeriod()
	{
		if(reportPeriod == null){
			//ALL, Week, Month, Year, Quarter (Excel will always send Year instead of ALL)
			reportPeriod = new ArrayList<SelectItem>();
			reportPeriod.add(new SelectItem("ALL", "All"));
			reportPeriod.add(new SelectItem("Week", "Current Week"));
			reportPeriod.add(new SelectItem("Month", "Month"));
			reportPeriod.add(new SelectItem("Quarter", "Quarter"));
//			reportPeriod.add(new SelectItem("Year", "Year"));
		}
		return reportPeriod;
	}

	/**
	 * @param reportPeriod the reportPeriod to set
	 */
	public void setReportPeriod(List<SelectItem> reportPeriod)
	{
		this.reportPeriod = reportPeriod;
	}

	/**
	 * @return the period
	 */
	public String getPeriod()
	{
		return period;
	}

	/**
	 * @param period the period to set
	 */
	public void setPeriod(String period)
	{
		this.period = period;
	}
	
	public static void main(String[] args)
	{
		
		 
	}
	
	private Boolean checkIfItsWorkingHour(){
		Calendar now = Calendar.getInstance();

		 int hour = now.get(Calendar.HOUR_OF_DAY); // Get hour in 24 hour format
		 int minute = now.get(Calendar.MINUTE);

		 Date date = parseDate(hour + ":" + minute);
		 Date dateCompareOne = parseDate("08:00");
		 Date dateCompareTwo = parseDate("19:00");

		 if (dateCompareOne.before( date ) && dateCompareTwo.after(date)) {
		    System.out.println("It is working hour, dont activate the button");
		    return true;
		 } else {
			 System.out.println("You can process data now.");
			 return false;
		 }
	}
	
	private Date parseDate(String date) {

	    final String inputFormat = "HH:mm";
	    SimpleDateFormat inputParser = new SimpleDateFormat(inputFormat, Locale.US);
	    try {
	         return inputParser.parse(date);
	    } catch (java.text.ParseException e) {
	         return new Date(0);
	    }
	 }

	/**
	 * @return the enableExportButton
	 */
	public Boolean getEnableExportButton()
	{
		return enableExportButton;
	}

	/**
	 * @param enableExportButton the enableExportButton to set
	 */
	public void setEnableExportButton(Boolean enableExportButton)
	{
		this.enableExportButton = enableExportButton;
	}

	/**
	 * @return the roleId
	 */
	public Integer getRoleId()
	{
		return roleId;
	}

	/**
	 * @param roleId the roleId to set
	 */
	public void setRoleId(Integer roleId)
	{
		this.roleId = roleId;
	}
	
	public void downloadMonthlyDataDumpFile()
	{
		String fileName = FacesUtils.getMonthlyAnalyserDataDumpFileName() + FILE_EXTENSION;
		ServletContext ctx = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
		String realPath = ctx.getRealPath("/");
		String filePath = realPath + downloadFilePath + "\\" + fileName;
		System.out.println("Download file Path: " + filePath);
		try
		{
			// This points to the root.
			InputStream stream = new FileInputStream(filePath);
			//InputStream stream = FacesContext.getCurrentInstance().getExternalContext().getResourceAsStream("/"+filePath);
			logger.debug("Monthly Download File Path : " + filePath);
			generatedExcelFile = new DefaultStreamedContent(stream, "application/vnd.ms-excel", fileName);
			setMonthlyReportDate(LocalDate.now().withDayOfMonth(1).toString());
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
	}
	
	public String getMonthlyReportDate() {
		if(monthlyReportDate == null) {
			return LocalDate.now().withDayOfMonth(1).toString();
		}
		return monthlyReportDate;
	}

	public void setMonthlyReportDate(String monthlyReportDate) {
		this.monthlyReportDate = monthlyReportDate;
	}
	
	public Boolean getEnableAdministrativeActions() {
		return enableAdministrativeActions;
	}

	public void setEnableAdministrativeActions(Boolean enableAdministrativeActions) {
		this.enableAdministrativeActions = enableAdministrativeActions;
	}
	
}