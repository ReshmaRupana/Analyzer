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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
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
import org.springframework.web.jsf.FacesContextUtils;

import com.disys.analyzer.config.util.FacesMessageSeverity;
import com.disys.analyzer.config.util.FacesUtils;
import com.disys.analyzer.config.util.WriteToFile;
import com.disys.analyzer.exception.ReportDataNotFoundException;
import com.disys.analyzer.reports.util.ReportCriteria;
import com.disys.analyzer.reports.util.ReportUtil;
import com.disys.analyzer.service.AnalyserCommisionPersonService;
import com.disys.analyzer.service.AnalyserService;

/**
 * @author Sajid
 * 
 */
@ManagedBean
@ViewScoped
public class MonthlyActivityReportsBean extends SpringBeanAutowiringSupport implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4733945234780462774L;

	private Logger logger = LoggerFactory.getLogger(getClass());

	private String managingDirector;
	private List<SelectItem> managingDirectorList;

	private String vertical;
	private List<SelectItem> verticalList;

	@Autowired
	private AnalyserCommisionPersonService service;
	@Autowired
	private AnalyserService analyserService;

	private List<SelectItem> commissionPersons;
	private List<SelectItem> monthsList;
	private List<SelectItem> recruiters;
	private List<SelectItem> products;

	private String person;
	private String recruiter;
	private String employeeType;
	private String year;
	private String month;
	private String classification1;
	private String classification2;
	private String recruitingTeam;
	private String product;

	private String dealType;

	private List<Map<String, Object>> reportData;
	private List<Map<String, Object>> filteredList;

	private final static String FILE_NAME = "MonthlyActivityReport";
	private final static String FILE_EXTENSION = ".xlsx";

	private String completeFilePath;
	private StreamedContent generatedExcelFile;
	private String newFileName;

	private List<SelectItem> branchesList;
	
	private List<SelectItem> yearsList;

	private String branch;
	
	private List<SelectItem> entityNames;
	private String entityName;
	

	@PostConstruct
	public void init() {
		try {
			FacesContextUtils.getRequiredWebApplicationContext(FacesContext.getCurrentInstance())
					.getAutowireCapableBeanFactory().autowireBean(this);
		} catch (Exception ex) {
			logger.debug(ex.getMessage());
		}
	}

	public MonthlyActivityReportsBean() {
		logger.debug("Inside MonthlyActivityReportsBean");
		
		Calendar c = Calendar.getInstance();
		int year = c.get(Calendar.YEAR);
		yearsList = new ArrayList<SelectItem>();
		yearsList.add(new SelectItem(year, "" + year));
		yearsList.add(new SelectItem(year - 1, "" + (year - 1)));
		yearsList.add(new SelectItem(year - 2, "" + (year - 2)));
		yearsList.add(new SelectItem(year - 3, "" + (year - 3)));
		yearsList.add(new SelectItem(year - 4, "" + (year - 4)));
		yearsList.add(new SelectItem(year - 5, "" + (year - 5)));
		yearsList.add(new SelectItem(year - 6, "" + (year - 6)));
	}

	public void eraseFilter() {
		this.reportData = null;
	}

	public void exportToExcel() throws Exception
	{
		if (reportData == null || reportData.size() == 0)
		{
			FacesUtils.addGlobalMessage(FacesMessageSeverity.ERROR, "No data to export", "There is no data to export to excel!");
			throw new ReportDataNotFoundException("No data to export");
		}
		// These are the column headers from the database with their actual name
		// mappings to show on Excel file.
		Map<String, String> columnNameMappings = new HashMap<String, String>();
		columnNameMappings.put("AE", "AE");
		columnNameMappings.put("RECRUITER", "Recruiter");
		columnNameMappings.put("OTHER1", "AE-2");
		columnNameMappings.put("OTHER2", "Recruiter-2");
		columnNameMappings.put("LASTNAME", "Last Name");
		columnNameMappings.put("FIRSTNAME", "First Name");
		columnNameMappings.put("TITLE", "Title");
		columnNameMappings.put("MANAGINGDIRECTOR", "MD");
		columnNameMappings.put("BRANCH", "Office");
		columnNameMappings.put("CITY", "Worksite City");
		columnNameMappings.put("STATE", "Worksite State");
		columnNameMappings.put("CLIENT", "Client");
		columnNameMappings.put("PAYRATE", "Pay Rate");
		columnNameMappings.put("BILLRATE", "Bill Rate");
		columnNameMappings.put("PROFIT", "Profit");
		columnNameMappings.put("STATUS", "Activity Type");
		columnNameMappings.put("SUBMISSIONDATE", "Activity Date");
		columnNameMappings.put("ANALYZERCATEGORY1", "Classification 1");
		columnNameMappings.put("ANALYZERCATEGORY2", "Classification 2");
		columnNameMappings.put("DEALTYPE", "Deal Type");
		columnNameMappings.put("CATEGORY", "Emp Category");
		columnNameMappings.put("VERTICAL", "Vertical");
		columnNameMappings.put("RECRUITINGCLASSIFICATION", "Recruiting Team");
		columnNameMappings.put("PRODUCT", "Product");
		columnNameMappings.put("STARTDATE", "Start Date");
		columnNameMappings.put("ENDDATE", "End Date");
		columnNameMappings.put("TERMINATEDATE", "Terminate Date");
		columnNameMappings.put("REASON", "Reason");
		//columnNameMappings.put("SORTORDER", "GP %");
		columnNameMappings.put("GP", "GP%");
		//columnNameMappings.put("COLOR", "Jan - C");
		columnNameMappings.put("ENTITYNAME","Entity Name");
		
		XSSFWorkbook workbook = new XSSFWorkbook();
		
		CellStyle cellStyle = workbook.createCellStyle();
		Font font = workbook.createFont();
		font.setBoldweight(Font.BOLDWEIGHT_BOLD);
		cellStyle.setFont(font);
		
		CreationHelper createHelper = workbook.getCreationHelper();
		CellStyle cellStyleDate = workbook.createCellStyle();
		
		XSSFSheet sheet = workbook.createSheet("Monthly Activity Report");
		
		int rowNum = 0;
		int colNum = 0;
		System.out.println("Creating excel");
		
		Row row = null;
		List<Map<String, Object>> reportDataCopy = reportData;
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
		
		//header row is printed now, start from the second row
		rowNum = 1;
		for (Map<String, Object> map : reportDataCopy)
		{
			row = sheet.createRow(rowNum++);
			colNum = 0;
			Cell cell = null;
			Integer innerColumnNumber = 0;
			Iterator<Map.Entry<String, Object>> iterator = map.entrySet().iterator();
			while (iterator.hasNext())
			{
				Map.Entry<String, Object> entry = iterator.next();
				try
				{
					innerColumnNumber++;
					// check if we want to show this column on excel file
					if (!skipColumnsFromPrint.contains(innerColumnNumber))
					{
						cell = row.createCell(colNum++);
						// check if the value is a numeric or double
						if (FacesUtils.checkIfValueIsDouble(entry.getValue().toString()))
						{
							cell.setCellValue(Double.parseDouble(entry.getValue().toString()));
						} else if (FacesUtils.isValidDate(entry.getValue().toString())) {
							//cell.setCellValue(dateFormat.parse(entry.getValue().toString()));
							//System.out.println("The date is : "+entry.getValue().toString());
							System.out.println("MonthlyActivityReportsBean --> exportToExcel --> The date is : "+entry.getValue().toString());
							logger.debug("MonthlyActivityReportsBean --> exportToExcel --> The date is : "+entry.getValue().toString());

							SimpleDateFormat sdf = null;
							if(entry.getValue().toString().contains("-")){
								sdf = new SimpleDateFormat(FacesUtils.DATE_FORMAT_2);
							} else if(entry.getValue().toString().contains("/")){
								sdf = new SimpleDateFormat("dd/MM/yyyy");
								//sdf = new SimpleDateFormat("MM/dd/yyyy");
								if (sdf != null)
								{
									System.out.println("MonthlyActivityReportsBean --> exportToExcel --> Excel Cell FORMATTED Date Value = "+sdf);
									logger.debug("MonthlyActivityReportsBean --> exportToExcel --> Excel Cell FORMATTED Date Value = "+sdf);
								}
							}
						    java.util.Date d=null;
						    try {
						        d= sdf.parse(entry.getValue().toString());
						    } catch (ParseException e) {
								System.out.println("MonthlyActivityReportsBean --> exportToExcel --> Date NOT converted properly : "+entry.getValue().toString());
								logger.debug("MonthlyActivityReportsBean --> exportToExcel --> Date NOT converted properly : "+entry.getValue().toString());
						        d=null;
						        e.printStackTrace();
						        continue;
						    }
						    cellStyleDate.setDataFormat((short)14);
						    cell.setCellValue(d);
							if (d != null)
							{
								System.out.println("MonthlyActivityReportsBean --> exportToExcel --> Excel Cell Date Value = "+d);
								logger.debug("MonthlyActivityReportsBean --> exportToExcel --> Excel Cell Date Value = "+d);
							}
						    cell.setCellStyle(cellStyleDate);
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
			
			// D:\Softwares\Apache\tomcat-8.0.23\webapps\Analyzer\
			
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

	public void downloadExcelFile(String filePath) {
		try {
			if (filePath == null || filePath.equals("")) {
				filePath = newFileName;
			}
			// This points to the root.
			InputStream stream = new FileInputStream(FacesUtils.getTempFilePath() + filePath);
			generatedExcelFile = new DefaultStreamedContent(stream, "application/vnd.ms-excel", filePath);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void print() {
		if (reportData == null || reportData.size() == 0) {
			FacesUtils.addGlobalMessage(FacesMessageSeverity.ERROR, "No data to print", "There is no data to print!");
			return;
		}
	}

	public void generateReport() {
		logger.debug("About to create Revenue by MONTHLY_ACTIVITY_REPORT");

		String reportName = "MONTHLY_ACTIVITY_REPORT";

		// call wireless.spGetMonthlyActivityReport
		// ('[0]','[1]','[2]','[3]','[4]','[5]','[6]','[7]','[8]','[9]','[10]','[11]','[12]')}
		// call wireless.spGetMonthlyActivityReport
		// ('Gregory.Armstrong@DISYS.COM','2','Arizona','MSS','Aashish
		// Antil','0','2017','IT','ndt','gsoc','Vertical','STAFF AUG','BRANCH')}

		/*
		 * {call wireless.spGetMonthlyActivityReport
		 * ('maruf@disys.com','1','Alan Wilhite','MSS','Abirami
		 * Subramanian','0','2018','IT','Vertical','STAFF
		 * AUG','Banking','ITA','BRANCH')}
		 */
		String orderBy = "BRANCH";
		Integer type = 0;
		// always get the userid for security reasons
		String reportParam = FacesUtils.getCurrentUserId();
		if(managingDirector == null || managingDirector.trim().isEmpty()){
			managingDirector = "ALL";
		}

		reportParam = reportParam + "|" + month + "|" + managingDirector + "|" + person + "|" + recruiter + "|" + type
				+ "|" + year + "|" + employeeType + "|" + recruitingTeam + "|" + product + "|" + vertical + "|"
				+ dealType + "|" + orderBy + "|" + branch+"|"+entityName;

		/*
		 * reportParam = reportParam + "|" + month + "|" + vertical + "|" +
		 * managingDirector + "|" + person + "|" + recruiter + "|" + type + "|"
		 * + year + "|ALL|ALL" + "|" + recruitingTeam + "|" + product + "|" +
		 * orderBy;
		 */

		ReportCriteria rc = null;
		reportData = new ArrayList<Map<String, Object>>();
		try {
			rc = new ReportCriteria(reportName, reportParam);
			reportData = rc.getReport();// get the data from the array list
			System.out.println(reportData.size());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @return the reportData
	 */
	public List<Map<String, Object>> getReportData() {
		return reportData;
	}

	/**
	 * @param reportData
	 *            the reportData to set
	 */
	public void setReportData(List<Map<String, Object>> reportData) {
		this.reportData = reportData;
	}

	/**
	 * @return the completeFilePath
	 */
	public String getCompleteFilePath() {
		return completeFilePath;
	}

	/**
	 * @param completeFilePath
	 *            the completeFilePath to set
	 */
	public void setCompleteFilePath(String completeFilePath) {
		this.completeFilePath = completeFilePath;
	}

	/**
	 * @return the newFileName
	 */
	public String getNewFileName() {
		return newFileName;
	}

	/**
	 * @param newFileName
	 *            the newFileName to set
	 */
	public void setNewFileName(String newFileName) {
		this.newFileName = newFileName;
	}

	/**
	 * @return the generatedExcelFile
	 */
	public StreamedContent getGeneratedExcelFile() {
		return generatedExcelFile;
	}

	/**
	 * @return the month
	 */
	public String getMonth() {
		return month;
	}

	/**
	 * @param month
	 *            the month to set
	 */
	public void setMonth(String month) {
		this.month = month;
	}

	/**
	 * @return the year
	 */
	public String getYear() {
		return year;
	}

	/**
	 * @param year
	 *            the year to set
	 */
	public void setYear(String year) {
		this.year = year;
	}

	/**
	 * @return the person
	 */
	public String getPerson() {
		return person;
	}

	/**
	 * @param person
	 *            the person to set
	 */
	public void setPerson(String person) {
		this.person = person;
	}

	/**
	 * @param commissionPersons
	 *            the commissionPersons to set
	 */
	public void setCommissionPersons(List<SelectItem> commissionPersons) {
		this.commissionPersons = commissionPersons;
	}

	/**
	 * @param monthsList
	 *            the monthsList to set
	 */
	public void setMonthsList(List<SelectItem> monthsList) {
		this.monthsList = monthsList;
	}

	/**
	 * @return the monthsList
	 */
	public List<SelectItem> getMonthsList() {
		if (monthsList == null) {
			monthsList = new ArrayList<SelectItem>();
			monthsList.add(new SelectItem("1", "Jan"));
			monthsList.add(new SelectItem("2", "Feb"));
			monthsList.add(new SelectItem("3", "Mar"));
			monthsList.add(new SelectItem("4", "Apr"));
			monthsList.add(new SelectItem("5", "May"));
			monthsList.add(new SelectItem("6", "Jun"));
			monthsList.add(new SelectItem("7", "Jul"));
			monthsList.add(new SelectItem("8", "Aug"));
			monthsList.add(new SelectItem("9", "Sep"));
			monthsList.add(new SelectItem("10", "Oct"));
			monthsList.add(new SelectItem("11", "Nov"));
			monthsList.add(new SelectItem("12", "Dec"));
		}
		return monthsList;
	}

	/**
	 * @return the commissionPersons
	 */
	public List<SelectItem> getCommissionPersons() {
		if (commissionPersons == null) {
			commissionPersons = new ArrayList<SelectItem>();

			// TODO get role Id and show only when role id is 3

			ReportUtil report = new ReportUtil();
			List<Map<String, Object>> dataType = null;
			try {
				dataType = report.getAEs(FacesUtils.getCurrentUserId(), "0");
			} catch (Exception e) {
				e.printStackTrace();
			}
			commissionPersons.add(new SelectItem("MSS", "MSS Business Development"));
			for (int i = 0; i < dataType.size(); i++) {
				Map<String, Object> map = dataType.get(i);
				String id = (String) map.get("PERSONNAME");
				String desc = (String) map.get("PERSONNAME");
				commissionPersons.add(new SelectItem(id, desc));
			}

		}
		return commissionPersons;
	}

	/**
	 * @return the recruiters
	 */
	public List<SelectItem> getRecruiters() {
		if (recruiters == null) {
			recruiters = new ArrayList<SelectItem>();
			ReportUtil report = new ReportUtil();
			List<Map<String, Object>> dataType = null;
			try {
				dataType = report.getAEs(FacesUtils.getCurrentUserId(), "0");
			} catch (Exception e) {
				e.printStackTrace();
			}
			for (int i = 0; i < dataType.size(); i++) {
				Map<String, Object> map = dataType.get(i);
				String id = (String) map.get("PERSONNAME");
				String desc = (String) map.get("PERSONNAME");
				recruiters.add(new SelectItem(id, desc));
			}

		}
		return recruiters;
	}

	/**
	 * @param recruiters
	 *            the recruiters to set
	 */
	public void setRecruiters(List<SelectItem> recruiters) {
		this.recruiters = recruiters;
	}

	/**
	 * @return the products
	 */
	public List<SelectItem> getProducts() {
		if (products == null) {
			products = new ArrayList<SelectItem>();
			products.add(new SelectItem("STAFF AUG", "STAFF AUG"));
			products.add(new SelectItem("SERVICES", "SERVICES"));
			products.add(new SelectItem("PERM", "PERM"));
		}
		return products;
	}

	/**
	 * @param products
	 *            the products to set
	 */
	public void setProducts(List<SelectItem> products) {
		this.products = products;
	}

	/**
	 * @return the recruiter
	 */
	public String getRecruiter() {
		return recruiter;
	}

	/**
	 * @param recruiter
	 *            the recruiter to set
	 */
	public void setRecruiter(String recruiter) {
		this.recruiter = recruiter;
	}

	/**
	 * @return the employeeType
	 */
	public String getEmployeeType() {
		return employeeType;
	}

	/**
	 * @param employeeType
	 *            the employeeType to set
	 */
	public void setEmployeeType(String employeeType) {
		this.employeeType = employeeType;
	}

	/**
	 * @return the classification1
	 */
	public String getClassification1() {
		return classification1;
	}

	/**
	 * @param classification1
	 *            the classification1 to set
	 */
	public void setClassification1(String classification1) {
		this.classification1 = classification1;
	}

	/**
	 * @return the classification2
	 */
	public String getClassification2() {
		return classification2;
	}

	/**
	 * @param classification2
	 *            the classification2 to set
	 */
	public void setClassification2(String classification2) {
		this.classification2 = classification2;
	}

	/**
	 * @return the recruitingTeam
	 */
	public String getRecruitingTeam() {
		return recruitingTeam;
	}

	/**
	 * @param recruitingTeam
	 *            the recruitingTeam to set
	 */
	public void setRecruitingTeam(String recruitingTeam) {
		this.recruitingTeam = recruitingTeam;
	}

	/**
	 * @return the product
	 */
	public String getProduct() {
		return product;
	}

	/**
	 * @param product
	 *            the product to set
	 */
	public void setProduct(String product) {
		this.product = product;
	}

	/**
	 * @return the filteredList
	 */
	public List<Map<String, Object>> getFilteredList() {
		return filteredList;
	}

	/**
	 * @param filteredList
	 *            the filteredList to set
	 */
	public void setFilteredList(List<Map<String, Object>> filteredList) {
		this.filteredList = filteredList;
	}

	/**
	 * @return the managingDirector
	 */
	public String getManagingDirector() {
		return managingDirector;
	}

	/**
	 * @param managingDirector
	 *            the managingDirector to set
	 */
	public void setManagingDirector(String managingDirector) {
		this.managingDirector = managingDirector;
	}

	/**
	 * @return the managingDirectorList
	 */
	public List<SelectItem> getManagingDirectorList() {
		if (managingDirectorList == null) {
			Map<String, String> commPersonMap = new HashMap<String, String>();
			String userId = FacesUtils.getCurrentUserId();
			String type = "MD";
			managingDirectorList = new ArrayList<SelectItem>();
			commPersonMap = service.getCommPersonList(userId, type);
			Iterator<Map.Entry<String, String>> iterator = commPersonMap.entrySet().iterator();
			while (iterator.hasNext()) {
				Map.Entry<String, String> obj = iterator.next();
				if (obj.getValue() == null) {
					//managingDirectorList.add(new SelectItem(obj.getKey(), ""));
				} else {
					managingDirectorList.add(new SelectItem(obj.getKey(), obj.getValue().toString()));
				}
			}
		}
		return managingDirectorList;
	}

	/**
	 * @param managingDirectorList
	 *            the managingDirectorList to set
	 */
	public void setManagingDirectorList(List<SelectItem> managingDirectorList) {
		this.managingDirectorList = managingDirectorList;
	}

	/**
	 * @return the vertical
	 */
	public String getVertical() {
		return vertical;
	}

	/**
	 * @param vertical
	 *            the vertical to set
	 */
	public void setVertical(String vertical) {
		this.vertical = vertical;
	}

	/**
	 * @return the verticalList
	 */
	public List<SelectItem> getVerticalList() {
		/*
		 * if (verticalList == null) { verticalList = new
		 * ArrayList<SelectItem>(); verticalList.add(new SelectItem("Banking",
		 * "Banking")); verticalList.add(new SelectItem("Diversified",
		 * "Diversified")); verticalList.add(new SelectItem("Energy",
		 * "Energy")); verticalList.add(new SelectItem("Finance and Accounting",
		 * "Finance and Accounting")); verticalList.add(new SelectItem("Health",
		 * "Health")); verticalList.add(new SelectItem("Hi Tech", "Hi Tech")); }
		 * return verticalList;
		 */

		if (verticalList == null)
		{
			verticalList = new ArrayList<SelectItem>(10);
			verticalList.add(new SelectItem("AllWOFNA", "All without F&A"));
			List<String> list = analyserService.getVerticalsList();
			for (String ver : list)
			{
				verticalList.add(new SelectItem(ver, ver));
			}
		}
		return verticalList;
	}

	/**
	 * @param verticalList
	 *            the verticalList to set
	 */
	public void setVerticalList(List<SelectItem> verticalList) {
		this.verticalList = verticalList;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public List<SelectItem> getBranchesList() {
		if (branchesList == null) {
			branchesList = new ArrayList<SelectItem>();

			ReportUtil report = new ReportUtil();
			List<Map<String, Object>> dataType = null;
			try {
				dataType = report.getBranches(FacesUtils.getCurrentUserId(), "0");
			} catch (Exception e) {
				e.printStackTrace();
			}
			for (int i = 0; i < dataType.size(); i++) {
				Map<String, Object> map = dataType.get(i);
				String id = (String) map.get("BRANCHNAME");
				String desc = (String) map.get("BRANCHNAME");
				branchesList.add(new SelectItem(id, desc));
			}

		}
		return branchesList;
	}

	public void setBranchesList(List<SelectItem> branchesList) {
		this.branchesList = branchesList;
	}

	/**
	 * @return the dealType
	 */
	public String getDealType() {
		return dealType;
	}

	/**
	 * @param dealType
	 *            the dealType to set
	 */
	public void setDealType(String dealType) {
		this.dealType = dealType;
	}

	/**
	 * @return the yearsList
	 */
	public List<SelectItem> getYearsList() {
		return yearsList;
	}

	/**
	 * @param yearsList the yearsList to set
	 */
	public void setYearsList(List<SelectItem> yearsList) {
		this.yearsList = yearsList;
	}
	
	/**
	 * @return the entityNames
	 */
	public List<SelectItem> getEntityNames()
	{
		if (entityNames == null)
		{
			List<Map<String, Object>>  entityNamesData = null;
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
	 * @param entityNames the entityNames to set
	 */
	public void setEntityNames(List<SelectItem> entityNames)
	{
		this.entityNames = entityNames;
	}

	/**
	 * @return the entityName
	 */
	public String getEntityName()
	{
		return entityName;
	}

	/**
	 * @param entityName the entityName to set
	 */
	public void setEntityName(String entityName)
	{
		this.entityName = entityName;
	}
}