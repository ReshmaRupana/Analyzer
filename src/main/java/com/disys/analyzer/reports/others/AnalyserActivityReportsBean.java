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
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.ServletContext;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import org.springframework.web.jsf.FacesContextUtils;

import com.disys.analyzer.config.util.Constants;
import com.disys.analyzer.config.util.FacesMessageSeverity;
import com.disys.analyzer.config.util.FacesUtils;
import com.disys.analyzer.config.util.WriteToFile;
import com.disys.analyzer.exception.ReportDataNotFoundException;
import com.disys.analyzer.model.dto.AnalyserCommisionPersonDTO;
import com.disys.analyzer.model.dto.EntityDTO;
import com.disys.analyzer.model.dto.OfficeDTO;
import com.disys.analyzer.model.dto.VerticalDTO;
import com.disys.analyzer.reports.util.ReportCriteria;
import com.disys.analyzer.reports.util.ReportUtil;
import com.disys.analyzer.service.AnalyserCommisionPersonService;
import com.disys.analyzer.service.AnalyserService;
import com.disys.analyzer.service.EntityService;
import com.disys.analyzer.service.OfficeService;
import com.disys.analyzer.service.VerticalService;

/**
 * @author Sajid
 * @since Apr 14, 2020
 */
@ManagedBean
@ViewScoped
public class AnalyserActivityReportsBean extends SpringBeanAutowiringSupport implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	private String startDate;
	private String endDate;
	
	private String managingDirector;
	private List<SelectItem> managingDirectorList;
	
	private String vertical;
	private List<SelectItem> verticalList;
	
	private String reportType;
	
	@Autowired
	private AnalyserCommisionPersonService service;
	@Autowired
	private AnalyserService analyserService;
	
	private List<Map<String, Object>> reportData;
	private List<Map<String, Object>> filteredList;
	
	private final static String FILE_NAME = "AnalyserActivityReport";
	private final static String FILE_EXTENSION = ".xlsx";
	
	private String completeFilePath;
	private StreamedContent generatedExcelFile;
	private String newFileName;
	
	private List<SelectItem> branchesList;
	private String branch;
	
	private String sMonth;
	private String eMonth;
	
	private String sYear;
	private String eYear;
	
	private List<SelectItem> monthsList;
	private List<SelectItem> yearsList;
	
	private String reportMonth;
	private String reportYear;
	
	private HashMap<String, Object> analyserId;
	
	private List<Map<String, Object>> childReportData;
	private List<Map<String, Object>> childFilteredList;
	
	private final static String CHILD_FILE_NAME = "AnalyserHistoryDetailReport";
	
	private String completeChildFilePath;
	private StreamedContent generatedChildExcelFile;
	private String newChildFileName;
	
	private Integer numberOfMonths;
	
	private List<String> monthsName;
	
	private List<Integer> months;
	
	private String startMonthName;
	private String centerMonthName;
	private String endMonthName;
	
	private List<SelectItem> entityNames;
	private String entityName;
	
	private String cMonth;
	
	private List<SelectItem> commissionPersons;
	private String person;
	
	private Integer roleId;
	private List<SelectItem> reportTypeList;
	
	private Map<String, String> columnNameMappings;
	private Map<String, Integer> columnSequenceMappings;
	
	private String userId=FacesUtils.getCurrentUserId();
	private List<SelectItem> officeList;
	private List<SelectItem> mdCommisionPersonList;
	private List<SelectItem> verticalLists;
	private List<SelectItem> entityList;
	private List<SelectItem> commisionPersonList;
	private String companyCode;
	
	@Autowired
	OfficeService officeService;
	
	@Autowired
	VerticalService verticalService;
	
	@Autowired 
	private EntityService entityService;
	
	@Autowired
	Map<String, List<AnalyserCommisionPersonDTO>> commissionPersonDropDownBean;
	
	@PostConstruct
	public void init()
	{
		try
		{
			FacesContextUtils.getRequiredWebApplicationContext(FacesContext.getCurrentInstance()).getAutowireCapableBeanFactory().autowireBean(this);
		}
		catch (Exception ex)
		{
			logger.debug(ex.getMessage());
		}
	}
	
	public AnalyserActivityReportsBean()
	{
		logger.debug("Inside AnalyserEntryReportsBean");
		this.companyCode = Constants.DEFAULT_COMPANY_CODE;
		
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
		
		String month = String.valueOf(c.get(Calendar.MONTH)+1);
		sMonth = month;
		eMonth = month;

		sYear = String.valueOf(year);
		eYear = String.valueOf(year);
		
		logger.debug("sMonth = "+sMonth);
		System.out.println("sMonth = "+sMonth);
		logger.debug("eMonth = "+eMonth);
		System.out.println("eMonth = "+eMonth);
		logger.debug("sYear = "+sYear);
		System.out.println("sYear = "+sYear);
		logger.debug("eYear = "+eYear);
		System.out.println("eYear = "+eYear);
		
		roleId = FacesUtils.getCurrentUserRole();
		logger.debug("roleId = "+roleId);
		System.out.println("roleId = "+roleId);
	}
	
	public void eraseFilter()
	{
		this.reportData = null;
	}
	
	//Not Used Anymore
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
		columnNameMappings.put("PARENTID", "Parent Id");
		columnNameMappings.put("CONSULTANTNAME", "Consultant Name");
		columnNameMappings.put("MANAGINGDIRECTOR", "MD");
		//columnNameMappings.put("VERTICAL", "Vertical");	//04/21/2020
		//columnNameMappings.put("PRODUCT", "Product");		//04/21/2020
		columnNameMappings.put("DEALTYPE", "Deal Type");
		columnNameMappings.put("STARTDATE", "Start Date");
		columnNameMappings.put("ENDDATE", "End Date");
		columnNameMappings.put("TERMINATIONDATE", "Term Date");
		columnNameMappings.put("BILLRATE", "Bill Rate");
		columnNameMappings.put("PAYRATE", "Pay Rate");
		columnNameMappings.put("PROFIT", "Net Profit");
		columnNameMappings.put("GA", "GA");
		columnNameMappings.put("EMPLOYEETYPE", "Type");
		columnNameMappings.put("GPAMOUNT", "GP $");
		columnNameMappings.put("NETGP", "Net GP");
		columnNameMappings.put("GPPERCENTAGE", "GP %");
		
		
		columnNameMappings.put("COMMISSION", "Commission");
		columnNameMappings.put("EMPLOYEECATEGORY", "Employee Category");
		columnNameMappings.put("RECRUITINGCLASSIFICATION", "Recruiting Classification");
		columnNameMappings.put("AE1", "Account Executive 1");
		columnNameMappings.put("REC1", "Recruiter 1");
		columnNameMappings.put("AE2", "Account Executive 2");
		columnNameMappings.put("REC2", "Recruiter 2");
		columnNameMappings.put("JOBTITLE", "Job Title");
		//columnNameMappings.put("OFFICE", "Office");	//04/21/2020
		columnNameMappings.put("CLIENTNAME", "Client Name");
		columnNameMappings.put("REASON", "Reason");
		columnNameMappings.put("CLASSIFICATION1", "Classification 1");
		columnNameMappings.put("CLASSIFICATION2", "Classification 2");
		columnNameMappings.put("ENTITYNAME", "Entity Name");
		columnNameMappings.put("WORKSITESTATE", "Worksite State");
		columnNameMappings.put("WORKSITECITY", "Worksite City");
		columnNameMappings.put("WORKSITEMANAGERNAME", "Worksite Manager Name");
		columnNameMappings.put("WORKSITEMANAGEREMAIL", "Worksite Manager Email");
		columnNameMappings.put("CLIENTID", "Client Id");
		columnNameMappings.put("WORSITEID", "Worksite Id");
		columnNameMappings.put("PSCLIENTID", "PS Client Id");
		columnNameMappings.put("MSPCLIENTPARTNER", "MSP Client Partner");
		columnNameMappings.put("LASTACTIVITYDATE", "Last Activity Date");
		columnNameMappings.put("LASTREHIREDATE", "Last Rehire Date");
		columnNameMappings.put("FIRSTENTRYDATE", "First Entry Date");
		
		columnNameMappings.put("CURRENTOFFICE", "Current Office");
		columnNameMappings.put("CURRENTVERTICAL", "Current Vertical");
		columnNameMappings.put("CURRENTPRODUCT", "Current Product");
		columnNameMappings.put("CURRENTDEALTYPE", "Current Deal Type");
		columnNameMappings.put("LASTACTIVITYMONTH", "Last Activity Month");
		columnNameMappings.put("LASTACTIVITYYEAR", "Last Activity Year");
		columnNameMappings.put("RECRUITER1EMPLID", "Recruiter1 Empl Id");
		columnNameMappings.put("ISIRCRECRUITER", "IRC Recruiter");
		//columnNameMappings.put("COMPANYCODE", "Current Company Code");
		
		columnNameMappings.put("ACTIVITYTYPE", "Activity Type");
		
		//TODO need to get Month name here and then display
		columnNameMappings.put("COL1", startMonthName+" - N");
		columnNameMappings.put("COL1A", startMonthName+" - A");
		columnNameMappings.put("COL1F", startMonthName+" - F");
		columnNameMappings.put("COL1C", startMonthName+" - C");
		columnNameMappings.put("MONTH1", "Month-1-" + startMonthName);
		columnNameMappings.put("YEAR1", "Year-1");
		
		columnNameMappings.put("COL2", centerMonthName+" - N");
		columnNameMappings.put("COL2A", centerMonthName+" - A");
		columnNameMappings.put("COL2F", centerMonthName+" - F");
		columnNameMappings.put("COL2C", centerMonthName+" - C");
		columnNameMappings.put("MONTH2", "Month-2-" + centerMonthName);
		columnNameMappings.put("YEAR2", "Year-2");
		
		columnNameMappings.put("COL3", endMonthName+" - N");
		columnNameMappings.put("COL3A", endMonthName+" - A");
		columnNameMappings.put("COL3F", endMonthName+" - F");
		columnNameMappings.put("COL3C", endMonthName+" - C");
		columnNameMappings.put("MONTH3", "Month-3-" + endMonthName);
		columnNameMappings.put("YEAR3", "Year-3");
		
		
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("Analyser Activity Report");
		
		CellStyle cellStyle = workbook.createCellStyle();
		Font font = workbook.createFont();
		font.setBoldweight(Font.BOLDWEIGHT_BOLD);
		cellStyle.setFont(font);
		
		CreationHelper createHelper = workbook.getCreationHelper();
		CellStyle cellStyleDate = workbook.createCellStyle();
		
		DataFormat df = workbook.createDataFormat();
		
		int rowNum = 0;
		int colNum = 0;
		System.out.println("Creating excel");
		
		Row row = null;
		List<Map<String, Object>> reportDataCopy = reportData;
		
		List<Integer> skipColumnsFromPrint = new ArrayList<Integer>();
		/*
		 * 
		 * To print column information from the result set
		 */
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
							System.out.println("AnalyserActivityReportsBean --> exportToExcel --> The date is : "+entry.getValue().toString());
							logger.debug("AnalyserActivityReportsBean --> exportToExcel --> The date is : "+entry.getValue().toString());

							SimpleDateFormat sdf = null;
							if(entry.getValue().toString().contains("-")){
								sdf = new SimpleDateFormat(FacesUtils.DATE_FORMAT_2);
							} else if(entry.getValue().toString().contains("/")){
								sdf = new SimpleDateFormat("dd/MM/yyyy");
								//sdf = new SimpleDateFormat("MM/dd/yyyy");
								if (sdf != null)
								{
									System.out.println("AnalyserActivityReportsBean --> exportToExcel --> Excel Cell FORMATTED Date Value = "+sdf);
									logger.debug("AnalyserActivityReportsBean --> exportToExcel --> Excel Cell FORMATTED Date Value = "+sdf);
								}
							}
						    java.util.Date d=null;
						    try {
						        d= sdf.parse(entry.getValue().toString());
						    } catch (ParseException e) {
								System.out.println("AnalyserActivityReportsBean --> exportToExcel --> Date NOT converted properly : "+entry.getValue().toString());
								logger.debug("AnalyserActivityReportsBean --> exportToExcel --> Date NOT converted properly : "+entry.getValue().toString());
						        d=null;
						        e.printStackTrace();
						        continue;
						    }
						    cellStyleDate.setDataFormat((short)14);
						    cell.setCellValue(d);
							if (d != null)
							{
								System.out.println("AnalyserActivityReportsBean --> exportToExcel --> Excel Cell Date Value = "+d);
								logger.debug("AnalyserActivityReportsBean --> exportToExcel --> Excel Cell Date Value = "+d);
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
//			ServletContext ctx = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
//			String realPath = ctx.getRealPath("/");
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
	
	public void exportChildFileToExcel() throws Exception
	{
		if (childReportData == null || childReportData.size() == 0)
		{
			FacesUtils.addGlobalMessage(FacesMessageSeverity.ERROR, "No data to export", "There is no data to export to excel!");
			throw new ReportDataNotFoundException("No data to export");
		}
		
		List<String> header = new ArrayList<String>();
		
		XSSFWorkbook workbook = new XSSFWorkbook();
		
		CellStyle cellStyle1 = workbook.createCellStyle();
		cellStyle1.setFillForegroundColor(IndexedColors.RED.index);
		
		XSSFSheet sheet = workbook.createSheet("Analyser Detail History report");
		
		int rowNum = 0;
		int colNum = 0;
		System.out.println("Creating excel");
		
		Row row = sheet.createRow(rowNum++);
		// create excel file header
		/*
		 * for (String cellHeader : header) { Cell cell =
		 * row.createCell(colNum++); cell.setCellValue(cellHeader); }
		 */
		List<Map<String, Object>> reportDataCopy = childReportData;
		
		/*
		 * 
		 * To print column information from the result set
		 */
		int count = 0;
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
				Map.Entry<String, Object> entry = iterator.next();
				try
				{
					cell = row.createCell(colNum++);
					String val = "";
					if (entry != null)
					{
						val = entry.toString();
						val = val.substring(0, val.indexOf('='));
					}
					cell.setCellValue(val);
				}
				catch (Exception ex)
				{
					logger.debug("Exception in "+logger.getName()+" while writing to the file for : "+entry.toString() +" by user id : "+FacesUtils.getCurrentUserId() + ex.getLocalizedMessage());
					continue;
				}
			}
		}
		
		for (Map<String, Object> map : reportDataCopy)
		{
			row = sheet.createRow(rowNum++);
			colNum = 0;
			Cell cell = null;
			Iterator<Map.Entry<String, Object>> iterator = map.entrySet().iterator();
			while (iterator.hasNext())
			{
				Map.Entry<String, Object> entry = iterator.next();
				try
				{
					cell = row.createCell(colNum++);				
					// check if the value is a numeric or double
					if (FacesUtils.checkIfValueIsDouble(entry.getValue().toString()))
					{
						cell.setCellValue(Double.parseDouble(entry.getValue().toString()));
					}
					else
					{
						cell.setCellValue(entry.getValue().toString());
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
			ServletContext ctx = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
			String realPath = FacesUtils.getTempFilePath();
			
			Long dateTime = System.currentTimeMillis();
			
			completeChildFilePath = realPath + CHILD_FILE_NAME + "-" + dateTime + FILE_EXTENSION;
			this.setCompleteChildFilePath(completeChildFilePath);
			this.setNewChildFileName(CHILD_FILE_NAME + "-" + dateTime + FILE_EXTENSION);
			logger.debug("Child File for downloading is : " + completeChildFilePath);
			System.out.println("Child File for downloading is : " + completeChildFilePath);
			
			WriteToFile objWriteToFile = new WriteToFile();
			File file = objWriteToFile.createFile(completeChildFilePath);
			
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
	
	public void downloadChildExcelFile(String filePath)
	{
		try
		{
			if (filePath == null || filePath.equals(""))
			{
				filePath = newChildFileName;
			}
			// This points to the root.
			//InputStream stream = FacesContext.getCurrentInstance().getExternalContext().getResourceAsStream("/" + filePath);
			InputStream stream = new FileInputStream(FacesUtils.getTempFilePath() + filePath);
			generatedChildExcelFile = new DefaultStreamedContent(stream, "application/vnd.ms-excel", filePath);
		}
		catch (Exception e)
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
			// This points to the root.
			//InputStream stream = FacesContext.getCurrentInstance().getExternalContext().getResourceAsStream(FacesUtils.getTempFilePath() + filePath);
			InputStream stream = new FileInputStream(FacesUtils.getTempFilePath() + filePath);
			generatedExcelFile = new DefaultStreamedContent(stream, "application/vnd.ms-excel", filePath);
		}
		catch (Exception e)
		{
			e.printStackTrace();
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
	
	public void onRowSelect(SelectEvent event)
	{
		Integer aId = 0;
		Iterator<Map.Entry<String, Object>> iterator = analyserId.entrySet().iterator();
		while (iterator.hasNext())
		{
			Map.Entry<String, Object> obj = iterator.next();
			if (obj.getKey().equals("PARENTID"))
			{
				aId = Integer.valueOf(obj.getValue().toString());
			}
			else
			{
				try
				{
					System.out.println(obj.getKey() + ":" + obj.getValue().toString());
				}
				catch (NullPointerException e)
				{
					System.err.println(obj.getKey() + " has null value");
					continue;
				}
				
			}
		}
		// System.out.println("Analyser id is : "+analyserId);
		getEmployeeDetailHistory(aId);
		
	}
	
	public void onRowUnselect(UnselectEvent event)
	{
		System.out.println("unselect");
	}
	
	public void getEmployeeDetailHistory(Integer analyserId)
	{
		
		logger.debug("About to create EMPLOYEE_DETAIL_REPORT-2 - Child Report");
		System.out.println("About to create EMPLOYEE_DETAIL_REPORT-2 - Child Report");
		
		String reportName = "EMPLOYEE_DETAIL_REPORT-2";
		// EMPLOYEE_DETAIL_REPORT-1.SQL
		
		// always get the userid for security reasons
		String reportParam = FacesUtils.getCurrentUserId();
		String action = "";
		
		reportParam = reportParam + "|" + "ALL" + "|" + analyserId + "|" + reportMonth + "|" + reportYear + "|" + null + "|" + null + "|" + null + "|" + reportType + "|";
		
		// maruf@disys.com|ALL|198347|1/1/2018|1/31/2018|null|null|null|subDate|
		
		logger.debug("reportParam = "+reportParam);
		System.out.println("reportParam = "+reportParam);
		
		ReportCriteria rc = null;
		childReportData = new ArrayList<Map<String, Object>>();
		try
		{
			rc = new ReportCriteria(reportName, reportParam);
			childReportData = rc.getReport();
			System.out.println("Child Report List Size = "+childReportData.size());
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void generateReport()
	{
		logger.debug("About to create EMPLOYEE_DETAIL_REPORT-1 - Master Report");
		System.out.println("About to create EMPLOYEE_DETAIL_REPORT-1 - Master Report");
		
		String reportName = "EMPLOYEE_DETAIL_REPORT-1";
		// EMPLOYEE_DETAIL_REPORT-1.SQL
		
		// always get the userid for security reasons
		String reportParam = FacesUtils.getCurrentUserId();
		
		reportParam = reportParam + "|" + reportMonth + "|" + reportYear + "|" + vertical + "|" + reportType + "|" + managingDirector
				+ "|" + branch + "|" + entityName + "|" + person + "|" +companyCode;
		
		logger.debug("reportParam = "+reportParam);
		System.out.println("reportParam = "+reportParam);
		
		logger.debug("reportMonth = "+reportMonth);
		System.out.println("reportMonth = "+reportMonth);
		logger.debug("reportYear = "+reportYear);
		System.out.println("reportYear = "+reportYear);
		
		Integer startMonthNumber, centerMonthNumber, endMonthNumber;
		Integer startYear, endYear;
		try
		{
			startMonthNumber = Integer.parseInt(reportMonth.substring(0,reportMonth.indexOf('/')));
			logger.debug("startMonthNumber = "+startMonthNumber);
			System.out.println("startMonthNumber = "+startMonthNumber);
			
			endMonthNumber = Integer.parseInt(reportYear.substring(0,reportYear.indexOf('/')));
			logger.debug("endMonthNumber = "+endMonthNumber);
			System.out.println("endMonthNumber = "+endMonthNumber);
			
			startYear = Integer.parseInt(reportMonth.substring(reportMonth.length()-4));
			logger.debug("startYear = "+startYear);
			System.out.println("startYear = "+startYear);
			
			endYear = Integer.parseInt(reportYear.substring(reportYear.length()-4));
			logger.debug("endYear = "+endYear);
			System.out.println("endYear = "+endYear);
			
			centerMonthNumber = endMonthNumber;
			if (startYear.intValue() == endYear.intValue())
			{
				if ((endMonthNumber - startMonthNumber) >= 2)
				{
					centerMonthNumber = (endMonthNumber - 1);
				}
			}
			else
			{
				if (startMonthNumber == 12)
				{
					if (endMonthNumber == 1)
					{
						centerMonthNumber = endMonthNumber;	//Jan
					}
					else
					{
						centerMonthNumber = (endMonthNumber - 1);	//Feb
					}
				}
				else if (startMonthNumber == 11)
				{
					if (endMonthNumber == 1)
					{
						centerMonthNumber = (startMonthNumber + 1);	//Jan
					}
				}	
			}
			logger.debug("centerMonthNumber = "+centerMonthNumber);
			System.out.println("centerMonthNumber = "+centerMonthNumber);
			
			logger.debug("Reset values of sMonth, cMonth & eMonth");
			System.out.println("Reset values of sMonth, cMonth & eMonth");
			
			sMonth = startMonthNumber.toString();
			cMonth = centerMonthNumber.toString();
			eMonth = endMonthNumber.toString();
			logger.debug("sMonth = "+sMonth);
			System.out.println("sMonth = "+sMonth);
			logger.debug("cMonth = "+cMonth);
			System.out.println("cMonth = "+cMonth);
			logger.debug("eMonth = "+eMonth);
			System.out.println("eMonth = "+eMonth);
			
			/*//12/06/2019 Tayyab
			if(startMonthNumber == endMonthNumber){
				centerMonthNumber = startMonthNumber;
			}
			else
			{
				centerMonthNumber = startMonthNumber+1;
				if(centerMonthNumber>=12)
				{
					centerMonthNumber = 0;
				}
			}
			*/
		}
		catch(Exception ex)
		{
			logger.debug("Exception while parsing month numbers "+ex.getMessage()+" for dates : "+reportMonth+" --- endDate : "+reportYear);
			startMonthNumber = 0;
			centerMonthNumber = 0;
			endMonthNumber = 0;
		}
		
		startMonthName = getMonthName(startMonthNumber);
		logger.debug("First call to getMonthName with startMonthName = "+startMonthName);
		System.out.println("First call to getMonthName with startMonthName = "+startMonthName);

		centerMonthName = getMonthName(centerMonthNumber);
		logger.debug("Second call to getMonthName with centerMonthName = "+centerMonthName);
		System.out.println("Second call to getMonthName with centerMonthName = "+centerMonthName);
		
		endMonthName = getMonthName(endMonthNumber);
		logger.debug("Third call to getMonthName with endMonthName = "+endMonthName);
		System.out.println("Third call to getMonthName with endMonthName = "+endMonthName);
		
		ReportCriteria rc = null;
		reportData = new ArrayList<Map<String, Object>>();
		try
		{
			rc = new ReportCriteria(reportName, reportParam);
			reportData = rc.getReport();// get the data from the arraylis
			System.out.println("Master Report List Size = "+reportData.size());
			logger.debug("Master Report List Size = "+reportData.size());
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
	 * @return the startDate
	 */
	public String getStartDate()
	{
		return startDate;
	}
	
	/**
	 * @param startDate
	 *            the startDate to set
	 */
	public void setStartDate(String startDate)
	{
		this.startDate = startDate;
	}
	
	/**
	 * @return the endDate
	 */
	public String getEndDate()
	{
		return endDate;
	}
	
	/**
	 * @param endDate
	 *            the endDate to set
	 */
	public void setEndDate(String endDate)
	{
		this.endDate = endDate;
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
	 * @return the managingDirector
	 */
	public String getManagingDirector()
	{
		return managingDirector;
	}
	
	/**
	 * @param managingDirector
	 *            the managingDirector to set
	 */
	public void setManagingDirector(String managingDirector)
	{
		this.managingDirector = managingDirector;
	}
	
	/**
	 * @return the managingDirectorList
	 */
	public List<SelectItem> getManagingDirectorList()
	{
		if (managingDirectorList == null)
		{
			Map<String, String> commPersonMap = new HashMap<String, String>();
			String userId = FacesUtils.getCurrentUserId();
			String type = "MD";
			managingDirectorList = new ArrayList<SelectItem>();
			commPersonMap = service.getCommPersonList(userId, type);
			Iterator<Map.Entry<String, String>> iterator = commPersonMap.entrySet().iterator();
			while (iterator.hasNext())
			{
				Map.Entry<String, String> obj = iterator.next();
				if (obj.getValue() == null)
				{
					// managingDirectorList.add(new SelectItem(obj.getKey(),
					// ""));
				}
				else
				{
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
	public void setManagingDirectorList(List<SelectItem> managingDirectorList)
	{
		this.managingDirectorList = managingDirectorList;
	}
	
	/**
	 * @return the vertical
	 */
	public String getVertical()
	{
		return vertical;
	}
	
	/**
	 * @param vertical
	 *            the vertical to set
	 */
	public void setVertical(String vertical)
	{
		this.vertical = vertical;
	}
	
	/**
	 * @return the verticalList
	 */
	public List<SelectItem> getVerticalList()
	{
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
	public void setVerticalList(List<SelectItem> verticalList)
	{
		this.verticalList = verticalList;
	}
	
	public String getBranch()
	{
		return branch;
	}
	
	public void setBranch(String branch)
	{
		this.branch = branch;
	}
	
	public List<SelectItem> getBranchesList()
	{
		if (branchesList == null)
		{
			branchesList = new ArrayList<SelectItem>();
			
			ReportUtil report = new ReportUtil();
			List<Map<String, Object>> dataType = null;
			try
			{
				dataType = report.getBranches(FacesUtils.getCurrentUserId(), "0");
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
			for (int i = 0; i < dataType.size(); i++)
			{
				Map<String, Object> map = dataType.get(i);
				String id = (String) map.get("BRANCHNAME");
				String desc = (String) map.get("BRANCHNAME");
				branchesList.add(new SelectItem(id, desc));
			}
			
		}
		return branchesList;
	}
	
	public void setBranchesList(List<SelectItem> branchesList)
	{
		this.branchesList = branchesList;
	}
	
	/**
	 * @return the reportType
	 */
	public String getReportType()
	{
		return reportType;
	}
	
	/**
	 * @param reportType
	 *            the reportType to set
	 */
	public void setReportType(String reportType)
	{
		this.reportType = reportType;
	}
	
	/**
	 * @return the sMonth
	 */
	public String getsMonth()
	{
		return sMonth;
	}
	
	/**
	 * @param sMonth
	 *            the sMonth to set
	 */
	public void setsMonth(String sMonth)
	{
		this.sMonth = sMonth;
	}
	
	/**
	 * @return the eMonth
	 */
	public String geteMonth()
	{
		return eMonth;
	}
	
	/**
	 * @param eMonth
	 *            the eMonth to set
	 */
	public void seteMonth(String eMonth)
	{
		this.eMonth = eMonth;
	}
	
	/**
	 * @return the sYear
	 */
	public String getsYear()
	{
		return sYear;
	}
	
	/**
	 * @param sYear
	 *            the sYear to set
	 */
	public void setsYear(String sYear)
	{
		this.sYear = sYear;
	}
	
	/**
	 * @return the eYear
	 */
	public String geteYear()
	{
		return eYear;
	}
	
	/**
	 * @param eYear
	 *            the eYear to set
	 */
	public void seteYear(String eYear)
	{
		this.eYear = eYear;
	}
	
	public String getFirstMonthName()
	{
		logger.debug("Inside getFirstMonthName");
		System.out.println("Inside getFirstMonthName");
		
		Integer startMonth = Integer.valueOf(sMonth);
		logger.debug("startMonth = "+startMonth);
		System.out.println("startMonth = "+startMonth);
		
		logger.debug("Call to getMonthName(startMonth % 13) with monthNumber  = "+startMonth);
		System.out.println("Call to getMonthName(startMonth % 13) with monthNumber  = "+startMonth);	
		
		//return getMonthName(startMonth % 12);	//12/05/2019 Tayyab
		return getMonthName((startMonth % 13));	//12/105/2019 Tayyab
	}
	
	public String getSecondMonthName()
	{
		logger.debug("Inside getSecondMonthName");
		System.out.println("Inside getSecondMonthName");
		
		//Integer startMonth = Integer.valueOf(sMonth);	//12/06/2019 Tayyab
		Integer centerMonth = Integer.valueOf(cMonth);
		logger.debug("centerMonth = "+centerMonth);
		System.out.println("centerMonth = "+centerMonth);
		
		logger.debug("Call to getMonthName((centerMonth) % 13) with monthNumber  = "+centerMonth);
		System.out.println("Call to getMonthName((centerMonth) % 13) with monthNumber  = "+centerMonth);	
		//return getMonthName((startMonth + 1) % 12);	//12/05/2019 Tayyab
		return getMonthName((centerMonth) % 13);
	}
	
	public String getThirdMonthName()
	{
		logger.debug("Inside getThirdMonthName");
		System.out.println("Inside getThirdMonthName");
		
		//Integer startMonth = Integer.valueOf(sMonth);	//12/06/2019 Tayyab
		Integer endMonth = Integer.valueOf(eMonth);
		logger.debug("endMonth = "+endMonth);
		System.out.println("endMonth = "+endMonth);
		
		logger.debug("Call to getMonthName((endMonth) % 13) with monthNumber  = "+endMonth);
		System.out.println("Call to getMonthName((endMonth) % 13) with monthNumber  = "+endMonth);
		//return getMonthName((startMonth + 2) % 12);	//12/05/2019 Tayyab
		return getMonthName((endMonth) % 13);
	}
	
	public String getMonthName(Integer monthNumber)
	{
		logger.debug("Inside getMonthName");
		System.out.println("Inside getMonthName");
		logger.debug("monthNumber = "+monthNumber);
		System.out.println("monthNumber = "+monthNumber);
		logger.debug("monthsName = "+monthsName);
		System.out.println("monthsName = "+monthsName);
		
		if (monthsName == null)
		{
			monthsName = getMonthsName();
		}
		logger.debug("monthsName = "+monthsName);
		System.out.println("monthsName = "+monthsName);
		logger.debug("monthsName.get(monthNumber - 1) = "+monthsName.get(monthNumber - 1));
		System.out.println("monthsName.get(monthNumber - 1) = "+monthsName.get(monthNumber - 1));
		
		return monthsName.get(monthNumber - 1);
	}
	
	/**
	 * @return the monthsList
	 */
	public List<SelectItem> getMonthsList()
	{
		if (monthsList == null)
		{
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
	 * @param monthsList
	 *            the monthsList to set
	 */
	public void setMonthsList(List<SelectItem> monthsList)
	{
		this.monthsList = monthsList;
	}
	
	/**
	 * @return the reportMonth
	 */
	public String getReportMonth()
	{
		return reportMonth;
	}
	
	/**
	 * @param reportMonth
	 *            the reportMonth to set
	 */
	public void setReportMonth(String reportMonth)
	{
		this.reportMonth = reportMonth;
	}
	
	/**
	 * @return the reportYear
	 */
	public String getReportYear()
	{
		return reportYear;
	}
	
	/**
	 * @param reportYear
	 *            the reportYear to set
	 */
	public void setReportYear(String reportYear)
	{
		this.reportYear = reportYear;
	}
	
	/**
	 * @return the analyserId
	 */
	public HashMap<String, Object> getAnalyserId()
	{
		return analyserId;
	}
	
	/**
	 * @param analyserId
	 *            the analyserId to set
	 */
	public void setAnalyserId(HashMap<String, Object> analyserId)
	{
		this.analyserId = analyserId;
	}
	
	/**
	 * @return the childReportData
	 */
	public List<Map<String, Object>> getChildReportData()
	{
		return childReportData;
	}
	
	/**
	 * @param childReportData
	 *            the childReportData to set
	 */
	public void setChildReportData(List<Map<String, Object>> childReportData)
	{
		this.childReportData = childReportData;
	}
	
	/**
	 * @return the childFilteredList
	 */
	public List<Map<String, Object>> getChildFilteredList()
	{
		return childFilteredList;
	}
	
	/**
	 * @param childFilteredList
	 *            the childFilteredList to set
	 */
	public void setChildFilteredList(List<Map<String, Object>> childFilteredList)
	{
		this.childFilteredList = childFilteredList;
	}
	
	/**
	 * @return the completeChildFilePath
	 */
	public String getCompleteChildFilePath()
	{
		return completeChildFilePath;
	}
	
	/**
	 * @param completeChildFilePath
	 *            the completeChildFilePath to set
	 */
	public void setCompleteChildFilePath(String completeChildFilePath)
	{
		this.completeChildFilePath = completeChildFilePath;
	}
	
	/**
	 * @return the generatedChildExcelFile
	 */
	public StreamedContent getGeneratedChildExcelFile()
	{
		return generatedChildExcelFile;
	}
	
	/**
	 * @param generatedChildExcelFile
	 *            the generatedChildExcelFile to set
	 */
	public void setGeneratedChildExcelFile(StreamedContent generatedChildExcelFile)
	{
		this.generatedChildExcelFile = generatedChildExcelFile;
	}
	
	/**
	 * @return the newChildFileName
	 */
	public String getNewChildFileName()
	{
		return newChildFileName;
	}
	
	/**
	 * @param newChildFileName
	 *            the newChildFileName to set
	 */
	public void setNewChildFileName(String newChildFileName)
	{
		this.newChildFileName = newChildFileName;
	}
	
	/**
	 * @param generatedExcelFile
	 *            the generatedExcelFile to set
	 */
	public void setGeneratedExcelFile(StreamedContent generatedExcelFile)
	{
		this.generatedExcelFile = generatedExcelFile;
	}
	
	/**
	 * @return the numberOfMonths
	 */
	public Integer getNumberOfMonths()
	{
		return numberOfMonths;
	}
	
	/**
	 * @param numberOfMonths
	 *            the numberOfMonths to set
	 */
	public void setNumberOfMonths(Integer numberOfMonths)
	{
		this.numberOfMonths = numberOfMonths;
	}
	
	/**
	 * @return the monthsName
	 */
	public List<String> getMonthsName()
	{
		if (monthsName == null)
		{
			monthsName = new ArrayList<String>(12);
			monthsName.add("Jan");
			monthsName.add("Feb");
			monthsName.add("Mar");
			monthsName.add("Apr");
			monthsName.add("May");
			monthsName.add("Jun");
			monthsName.add("Jul");
			monthsName.add("Aug");
			monthsName.add("Sep");
			monthsName.add("Oct");
			monthsName.add("Nov");
			monthsName.add("Dec");
		}
		return monthsName;
	}
	
	/**
	 * @param monthsName
	 *            the monthsName to set
	 */
	public void setMonthsName(List<String> monthsName)
	{
		this.monthsName = monthsName;
	}
	
	/**
	 * @return the months
	 */
	public List<Integer> getMonths()
	{
		logger.debug("Inside getMonths");
		System.out.println("Inside getMonths");
		logger.debug("months = "+months);
		System.out.println("months = "+months);
		logger.debug("sMonth = "+sMonth);
		System.out.println("sMonth = "+sMonth);
		
		if (months == null)
		{
			months = new ArrayList<Integer>();
			if (sMonth == null)
			{
				months.add(1);
			}
			else
			{
				Integer startMonth = Integer.valueOf(sMonth);
				logger.debug("startMonth = "+startMonth);
				System.out.println("startMonth = "+startMonth);
				
				logger.debug("numberOfMonths = "+numberOfMonths);
				System.out.println("numberOfMonths = "+numberOfMonths);
				for (int i = 0; i < numberOfMonths; i++)
				{
					months.add((startMonth) % 12);
					startMonth++;
					
				}
				logger.debug("After For Loop, months = "+months);
				System.out.println("After For Loop, months = "+months);
				logger.debug("After For Loop, startMonth = "+startMonth);
				System.out.println("After For Loop, startMonth = "+startMonth);
			}
		}
		return months;
	}
	
	/**
	 * @param months
	 *            the months to set
	 */
	public void setMonths(List<Integer> months)
	{
		this.months = months;
	}
	
	/**
	 * @return the yearsList
	 */
	public List<SelectItem> getYearsList()
	{
		return yearsList;
	}
	
	/**
	 * @param yearsList
	 *            the yearsList to set
	 */
	public void setYearsList(List<SelectItem> yearsList)
	{
		this.yearsList = yearsList;
	}

	/**
	 * @return the startMonthName
	 */
	public String getStartMonthName()
	{
		return startMonthName;
	}

	/**
	 * @param startMonthName the startMonthName to set
	 */
	public void setStartMonthName(String startMonthName)
	{
		this.startMonthName = startMonthName;
	}

	/**
	 * @return the endMonthName
	 */
	public String getEndMonthName()
	{
		return endMonthName;
	}

	/**
	 * @param endMonthName the endMonthName to set
	 */
	public void setEndMonthName(String endMonthName)
	{
		this.endMonthName = endMonthName;
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
	
	public static void main(String[] args){
		String date = "1/1/2018";
		Integer startMonthNumber, endMonthNumber;
		startMonthNumber = Integer.parseInt(date.substring(0,date.indexOf('/')));
		System.out.println(startMonthNumber);
	}

	/**
	 * @return the cMonth
	 */
	public String getcMonth() {
		return cMonth;
	}

	/**
	 * @param cMonth the cMonth to set
	 */
	public void setcMonth(String cMonth) {
		this.cMonth = cMonth;
	}
	
	/**
	 * @param commissionPersons
	 *            the commissionPersons to set
	 */
	public void setCommissionPersons(List<SelectItem> commissionPersons) {
		this.commissionPersons = commissionPersons;
	}
	
	/**
	 * @return the commissionPersons
	 */
	public List<SelectItem> getCommissionPersons() {
		if (commissionPersons == null) {
			commissionPersons = new ArrayList<SelectItem>();

			ReportUtil report = new ReportUtil();
			List<Map<String, Object>> dataType = null;
			try {
				dataType = report.getAEs(FacesUtils.getCurrentUserId(), "0");
			} catch (Exception e) {
				e.printStackTrace();
			}
			//commissionPersons.add(new SelectItem("MSS", "MSS Business Development"));
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
	 * @return the reportTypeList
	 */
	public List<SelectItem> getReportTypeList() 
	{
		if (reportTypeList == null)
		{
			reportTypeList = new ArrayList<SelectItem>();
			reportTypeList.add(new SelectItem("subDate", "Submission Date"));
			//reportTypeList.add(new SelectItem("stDate", "Start Date"));
			reportTypeList.add(new SelectItem("TRANSACTIONAL", "Transactional"));
			/*
			if (roleId == 3)
			{
				reportTypeList.add(new SelectItem("TRANSACTIONAL", "Transactional"));
			}
			*/
		}
		
		return reportTypeList;
	}

	/**
	 * @param reportTypeList the reportTypeList to set
	 */
	public void setReportTypeList(List<SelectItem> reportTypeList) {
		this.reportTypeList = reportTypeList;
	}

	/**
	 * @return the roleId
	 */
	public Integer getRoleId() {
		return roleId;
	}

	/**
	 * @param roleId the roleId to set
	 */
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	
	public void exportToExcelNew() throws Exception
	{
		logger.debug("Inside exportMasterFileToExcelNew.");
		System.out.println("Inside exportMasterFileToExcelNew.");
		
		if (reportData == null || reportData.size() == 0)
		{
			FacesUtils.addGlobalMessage(FacesMessageSeverity.ERROR, "No data to export", "There is no data to export to excel!");
			throw new ReportDataNotFoundException("No data to export");
		}
		
		logger.debug("Creating excel.");
		System.out.println("Creating excel");
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("Analyser Activity Report");
		CellStyle cellStyle = workbook.createCellStyle();	
		Font font = workbook.createFont();
		font.setBoldweight(Font.BOLDWEIGHT_BOLD);
		cellStyle.setFont(font);
		//cellStyle1.setFillForegroundColor(IndexedColors.RED.index);
		
		CreationHelper createHelper = workbook.getCreationHelper();
		CellStyle cellStyleDate = workbook.createCellStyle();
		DataFormat df = workbook.createDataFormat();
		
		// create excel file header
		int rowNum = 0;
		int colNum = 0;
		Row row = sheet.createRow(rowNum++);
		generateExcelHeadersLists();
		ArrayList<String> excelHeaders = new ArrayList<String>(columnNameMappings.values());
		int excelHeadersSize = excelHeaders.size();
		System.out.println("excelHeadersSize : " + excelHeadersSize);
		
		for (String cellHeader : excelHeaders) 
		{
			Cell cell = row.createCell(colNum++);
			cell.setCellStyle(cellStyle);
			cell.setCellValue(cellHeader);
		}
		//printHeaders();
		
		List<Map<String, Object>> reportDataCopy = reportData;
		for (Map<String, Object> map : reportDataCopy) 
		{
			//System.out.println("rowNum : " + rowNum);
			row = sheet.createRow(rowNum++);
			Cell cell = null;
			int cellSeqNumber = 0;
			Iterator<Map.Entry<String, Object>> iterator = map.entrySet().iterator();
			//System.out.println("map.entrySet().toString() : " + map.entrySet().toString());
			//System.out.println("map.entrySet().toArray().toString() : " + map.entrySet().toArray().toString());
			
			//creating all excel cells
			for (int i =0; i< excelHeadersSize; i++)
			{
				row.createCell(i);
			}

			while (iterator.hasNext()) 
			{
				Map.Entry<String, Object> entry = iterator.next();
				try 
				{
					//System.out.println("Entry entry..toString() is : " +entry.toString());
					//System.out.println("Entry entry.getValue().toString() is : " +entry.getValue().toString());
					//System.out.println("Entry entry.getKey().toString() is : " +entry.getKey().toString());
					
					cellSeqNumber = getDataHeaderIndex(entry.getKey().toString());
					//System.out.println("cellSeqNumber : " + cellSeqNumber);
					cell = row.getCell(cellSeqNumber);
					
					if (FacesUtils.checkIfValueIsDouble(entry.getValue().toString()))
					{
						cell.setCellValue(Double.parseDouble(entry.getValue().toString()));
					} 
					else if (FacesUtils.isValidDate(entry.getValue().toString())) 
					{
						SimpleDateFormat sdf = null;
						if(entry.getValue().toString().contains("-"))
						{
							sdf = new SimpleDateFormat(FacesUtils.DATE_FORMAT_2);
						} 
						else if(entry.getValue().toString().contains("/"))
						{
							sdf = new SimpleDateFormat("dd/MM/yyyy");
						}
					    java.util.Date d=null;
					    try 
					    {
					        d= sdf.parse(entry.getValue().toString());
					    } 
					    catch (ParseException e) 
					    {
							System.out.println("AnalyserActivityReportsBean --> exportToExcelNEW --> Exception Date NOT converted properly : "+entry.getValue().toString());
							logger.debug("AnalyserActivityReportsBean --> exportToExcelNEW --> Exception Date NOT converted properly : "+entry.getValue().toString());
					        d=null;
					        e.printStackTrace();
					        continue;
					    }
					    cellStyleDate.setDataFormat((short)14);
					    cell.setCellValue(d);
					    cell.setCellStyle(cellStyleDate);
					}
					else
					{
						cell.setCellValue(entry.getValue().toString());
					}

				} 
				catch (Exception ex) 
				{
					System.out.println("Exception in Analyzer Activity Excel NEW Data Extraction in AnalyserActivityReportsBean (second For Loop) while writing to the file for : "+entry.toString() + ex.getLocalizedMessage());
					logger.debug("Exception in Analyzer Activity Excel NEW Data Extraction in AnalyserActivityReportsBean (second For Loop) while writing to the file for : "+entry.toString() + ex.getLocalizedMessage());
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
			logger.debug("File for downloading in Export to Excel NEW is : " + completeFilePath);
			System.out.println("File for downloading in Export to Excel NEW is : " + completeFilePath);
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
			System.out.println("Error Writing Excel file in Export to Excel NEW . FileNotFoundException = "+e.getLocalizedMessage());
			logger.debug("Error Writing Excel file in Export to Excel NEW . FileNotFoundException = "+e.getLocalizedMessage());
			e.printStackTrace();
		} 
		catch (IOException e) 
		{
			System.out.println("Error Writing Excel file in Export to Excel NEW. IOException = "+e.getLocalizedMessage());
			logger.debug("Error Writing Excel file in Export to Excel NEW. IOException = "+e.getLocalizedMessage());
			e.printStackTrace();
		}
	}
	
	public void generateExcelHeadersLists () 
	{
		System.out.println("Generating New Excel File Headers");
		logger.debug("Generating New Excel File Headers");
		//Map<String, String> columnNameMappings = new LinkedHashMap<String, String>();
		columnNameMappings = new LinkedHashMap<String, String>();
		columnNameMappings.put("ANALYSERID", "Analyzer Id");
		columnNameMappings.put("PARENTID", "Parent Id");
		columnNameMappings.put("CONSULTANTNAME", "Consultant Name");
		columnNameMappings.put("STARTDATE", "Start Date");
		columnNameMappings.put("ENDDATE", "End Date");
		columnNameMappings.put("TERMINATIONDATE", "Term Date");
		//columnNameMappings.put("MANAGINGDIRECTOR", "MD");
		columnNameMappings.put("AE1", "Account Executive 1");
		columnNameMappings.put("REC1", "Recruiter 1");
		columnNameMappings.put("AE2", "Account Executive 2");
		columnNameMappings.put("REC2", "Recruiter 2");
		columnNameMappings.put("JOBTITLE", "Job Title");
		columnNameMappings.put("CLIENTNAME", "Client Name");
		columnNameMappings.put("REASON", "Reason");
		columnNameMappings.put("CURRENTOFFICE", "Current Office");
		columnNameMappings.put("CURRENTPORTFOLIOCODE", "Current Portfolio Code");
		columnNameMappings.put("CURRENTPORTFOLIODESCRIPTION", "Current Portfolio Description");
		columnNameMappings.put("CURRENTVERTICAL", "Current Vertical");
		columnNameMappings.put("CURRENTPRODUCT", "Current Product");
		columnNameMappings.put("CURRENTDEALTYPE", "Current Deal Type");
		columnNameMappings.put("CURRENTMANAGINGDIRECTOR", "Current MD");
		columnNameMappings.put("CURRENTMSPCLIENTPARTNER", "Current MSP Client Partner");
		columnNameMappings.put("LASTACTIVITYMONTH", "Last Activity Month");
		columnNameMappings.put("LASTACTIVITYYEAR", "Last Activity Year");
		columnNameMappings.put("RECRUITER1EMPLID", "Recruiter1 Empl Id");
		columnNameMappings.put("ISIRCRECRUITER", "IRC Recruiter");
		columnNameMappings.put("BILLRATE", "Bill Rate");
		columnNameMappings.put("PAYRATE", "Pay Rate");
		columnNameMappings.put("PROFIT", "Net Profit");
		columnNameMappings.put("GA", "GA");
		columnNameMappings.put("COMMISSION", "Commission");
		columnNameMappings.put("EMPLOYEECATEGORY", "Employee Category");
		columnNameMappings.put("EMPLOYEETYPE", "Type");
		columnNameMappings.put("GPAMOUNT", "GP $");
		columnNameMappings.put("GPPERCENTAGE", "GP %");
		columnNameMappings.put("NETGP", "Net GP");
		columnNameMappings.put("RECRUITINGCLASSIFICATION", "Recruiting Classification");
		columnNameMappings.put("CLASSIFICATION1", "Classification 1");
		columnNameMappings.put("CLASSIFICATION2", "Classification 2");
		columnNameMappings.put("ENTITYNAME", "Entity Name");
		columnNameMappings.put("WORKSITESTATE", "Worksite State");
		columnNameMappings.put("WORKSITECITY", "Worksite City");
		columnNameMappings.put("WORKSITEMANAGERNAME", "Worksite Manager Name");
		columnNameMappings.put("WORKSITEMANAGEREMAIL", "Worksite Manager Email");
		columnNameMappings.put("CLIENTID", "Client Id");
		columnNameMappings.put("WORSITEID", "Worksite Id");
		columnNameMappings.put("PSCLIENTID", "PS Client Id");
		columnNameMappings.put("LASTACTIVITYDATE", "Last Activity Date");
		columnNameMappings.put("FIRSTENTRYDATE", "First Entry Date");
		columnNameMappings.put("LASTREHIREDATE", "Last Rehire Date");
		columnNameMappings.put("MSPCLIENTPARTNER", "MSP Client Partner");
		columnNameMappings.put("AE1EMPLID", "AE1 Empl Id");
		columnNameMappings.put("AE2EMPLID", "AE2 Empl Id");
		columnNameMappings.put("RECRUITER2EMPLID", "Recruiter2 Empl Id");
		columnNameMappings.put("MDEMPLID", "MD Empl Id");
		columnNameMappings.put("MSPCLIENTPARTNEREMPLID", "MSP Client Partner Empl Id");
		columnNameMappings.put("CURRENTMSPCLIENTPARTNEREMPLID", "Current MSP Client Partner Empl Id");
		columnNameMappings.put("CURRENTCOMPANYCODE","Current Company Code");
		columnNameMappings.put("AE1PORTFOLIO","AE1 Portfolio");
		columnNameMappings.put("REC1PORTFOLIO","REC1 Portfolio");
		columnNameMappings.put("AE2PORTFOLIO","AE2 Portfolio");
		columnNameMappings.put("REC2PORTFOLIO","REC2 Portfolio");
		columnNameMappings.put("COMPANYCODE","Company Code");
		
		//columnNameMappings.put("DEALTYPE", "Deal Type");
		columnNameMappings.put("ACTIVITYTYPE", "Activity Type");
		columnNameMappings.put("COL1", startMonthName+" - N");
		columnNameMappings.put("COL1A", startMonthName+" - A");
		columnNameMappings.put("COL1F", startMonthName+" - F");
		columnNameMappings.put("COL1C", startMonthName+" - C");
		columnNameMappings.put("MONTH1", "Month-1-" + startMonthName);
		columnNameMappings.put("YEAR1", "Year-1");
		
		System.out.println("centerMonthName = "+centerMonthName);
		if (centerMonthName != null && centerMonthName.length() > 1 && !centerMonthName.equals(startMonthName))
		{
			logger.debug("Adding Center Month = "+centerMonthName);
			System.out.println("Adding Center Month = "+centerMonthName);
			columnNameMappings.put("COL2", centerMonthName+" - N");
			columnNameMappings.put("COL2A", centerMonthName+" - A");
			columnNameMappings.put("COL2F", centerMonthName+" - F");
			columnNameMappings.put("COL2C", centerMonthName+" - C");
			columnNameMappings.put("MONTH2", "Month-2-" + centerMonthName);
			columnNameMappings.put("YEAR2", "Year-2");
		}
		System.out.println("endMonthName = "+endMonthName);
		if (endMonthName != null && endMonthName.length() > 1 && !endMonthName.equals(startMonthName))
		{
			logger.debug("Adding End Month = "+endMonthName);
			System.out.println("Adding End Month = "+endMonthName);
			columnNameMappings.put("COL3", endMonthName+" - N");
			columnNameMappings.put("COL3A", endMonthName+" - A");
			columnNameMappings.put("COL3F", endMonthName+" - F");
			columnNameMappings.put("COL3C", endMonthName+" - C");
			columnNameMappings.put("MONTH3", "Month-3-" + endMonthName);
			columnNameMappings.put("YEAR3", "Year-3");
		}
		//excelHeaders = new ArrayList<String>(columnNameMappings.values());
		//dataHeaders = new ArrayList<String>(columnNameMappings.keySet());
		
		columnSequenceMappings = new HashMap<String, Integer>();
		int seq = 0;
		ArrayList<String> dataHeaders = new ArrayList<String>(columnNameMappings.keySet());
		for (String col : dataHeaders)
		{
			columnSequenceMappings.put(col, new Integer(seq++));
			System.out.println("Columns at Sequence = "+seq+" and Name = "+col);
			logger.debug("Columns at Sequence = "+seq+" and Name = "+col);
		}
	}
	
	public int getDataHeaderIndex (String str)
	{
		int index = -1;
		if(columnSequenceMappings.containsKey(str))
		{
			index = ((Integer)columnSequenceMappings.get(str)).intValue();
		}
		//System.out.println("Excel Column Header "+str+" has index = "+index);
		return index;
	}

	public void printHeaders()
	{
		int seq = 0;
		System.out.println("Excel Column Headers ARE:");
		for (String cellHeader : new ArrayList<String>(columnNameMappings.values())) 
		{
			System.out.println("Excel Column Header at Sequence = "+(seq++)+" and Name = "+cellHeader);
		}
		
		seq = 0;
		System.out.println("DATA Column Headers ARE:");
		for (String cellHeader : new ArrayList<String>(columnNameMappings.keySet())) 
		{
			System.out.println("Excel Column Header at Sequence = "+(seq++)+" and Name = "+cellHeader);
		}
	}
	
	public Map<String, String> getColumnNameMappings() {
		return columnNameMappings;
	}

	public void setColumnNameMappings(Map<String, String> columnNameMappings) {
		this.columnNameMappings = columnNameMappings;
	}

	public Map<String, Integer> getColumnSequenceMappings() {
		return columnSequenceMappings;
	}

	public void setColumnSequenceMappings(Map<String, Integer> columnSequenceMappings) {
		this.columnSequenceMappings = columnSequenceMappings;
	}
	
	/*
	public void exportToExcelNew() throws Exception
	{
		logger.debug("Inside exportMasterFileToExcelNew.");
		System.out.println("Inside exportMasterFileToExcelNew.");
		
		if (reportData == null || reportData.size() == 0)
		{
			FacesUtils.addGlobalMessage(FacesMessageSeverity.ERROR, "No data to export", "There is no data to export to excel!");
			throw new ReportDataNotFoundException("No data to export");
		}
		
		logger.debug("Creating excel.");
		System.out.println("Creating excel");
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("Analyser Activity Report");
		CellStyle cellStyle = workbook.createCellStyle();	
		Font font = workbook.createFont();
		font.setBoldweight(Font.BOLDWEIGHT_BOLD);
		cellStyle.setFont(font);
		//cellStyle1.setFillForegroundColor(IndexedColors.RED.index);
		
		CreationHelper createHelper = workbook.getCreationHelper();
		CellStyle cellStyleDate = workbook.createCellStyle();
		DataFormat df = workbook.createDataFormat();
		
		// create excel file header
		int rowNum = 0;
		int colNum = 0;
		Row row = sheet.createRow(rowNum++);
		ReportHeaders headers = new ReportHeaders();
		for (String cellHeader : headers.getExcelHeaders()) 
		{
			Cell cell = row.createCell(colNum++);
			cell.setCellStyle(cellStyle);
			cell.setCellValue(cellHeader);
		}
		//headers.printHeaders();
		
		List<Map<String, Object>> reportDataCopy = reportData;
		for (Map<String, Object> map : reportDataCopy) 
		{
			System.out.println("rowNum : " + rowNum);
			row = sheet.createRow(rowNum++);
			Cell cell = null;
			int cellSeqNumber = 0;
			Iterator<Map.Entry<String, Object>> iterator = map.entrySet().iterator();
			//System.out.println("map.entrySet().toString() : " + map.entrySet().toString());
			//System.out.println("map.entrySet().toArray().toString() : " + map.entrySet().toArray().toString());
			//creating all excel cells
			for (int i =0; i< headers.getNumberOfExcelColumns(); i++)
			{
				row.createCell(i);
			}
			System.out.println("headers.getNumberOfExcelColumns() : " + headers.getNumberOfExcelColumns());
			while (iterator.hasNext()) 
			{
				Map.Entry<String, Object> entry = iterator.next();
				try 
				{
					//System.out.println("Entry entry..toString() is : " +entry.toString());
					//System.out.println("Entry entry.getValue().toString() is : " +entry.getValue().toString());
					//System.out.println("Entry entry.getKey().toString() is : " +entry.getKey().toString());
					
					//cell = row.createCell(colNum++);
					cellSeqNumber = headers.getDataHeaderIndex(entry.getKey().toString());
					//System.out.println("cellSeqNumber : " + cellSeqNumber);
					cell = row.getCell(cellSeqNumber);
					
					if (FacesUtils.checkIfValueIsDouble(entry.getValue().toString()))
					{
						cell.setCellValue(Double.parseDouble(entry.getValue().toString()));
					} 
					else if (FacesUtils.isValidDate(entry.getValue().toString())) 
					{
						SimpleDateFormat sdf = null;
						if(entry.getValue().toString().contains("-"))
						{
							sdf = new SimpleDateFormat(FacesUtils.DATE_FORMAT_2);
						} 
						else if(entry.getValue().toString().contains("/"))
						{
							sdf = new SimpleDateFormat("dd/MM/yyyy");
						}
					    java.util.Date d=null;
					    try 
					    {
					        d= sdf.parse(entry.getValue().toString());
					    } 
					    catch (ParseException e) 
					    {
							System.out.println("AnalyserActivityReportsBean --> exportToExcelNEW --> Exception Date NOT converted properly : "+entry.getValue().toString());
							logger.debug("AnalyserActivityReportsBean --> exportToExcelNEW --> Exception Date NOT converted properly : "+entry.getValue().toString());
					        d=null;
					        e.printStackTrace();
					        continue;
					    }
					    cellStyleDate.setDataFormat((short)14);
					    cell.setCellValue(d);
					    cell.setCellStyle(cellStyleDate);
					}
					else
					{
						cell.setCellValue(entry.getValue().toString());
					}

				} 
				catch (Exception ex) 
				{
					System.out.println("Exception in Analyzer Activity Excel NEW Data Extraction in AnalyserActivityReportsBean (second For Loop) while writing to the file for : "+entry.toString() + ex.getLocalizedMessage());
					logger.debug("Exception in Analyzer Activity Excel NEW Data Extraction in AnalyserActivityReportsBean (second For Loop) while writing to the file for : "+entry.toString() + ex.getLocalizedMessage());
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
			logger.debug("File for downloading in Export to Excel NEW is : " + completeFilePath);
			System.out.println("File for downloading in Export to Excel NEW is : " + completeFilePath);
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
			System.out.println("Error Writing Excel file in Export to Excel NEW . FileNotFoundException = "+e.getLocalizedMessage());
			logger.debug("Error Writing Excel file in Export to Excel NEW . FileNotFoundException = "+e.getLocalizedMessage());
			e.printStackTrace();
		} 
		catch (IOException e) 
		{
			System.out.println("Error Writing Excel file in Export to Excel NEW. IOException = "+e.getLocalizedMessage());
			logger.debug("Error Writing Excel file in Export to Excel NEW. IOException = "+e.getLocalizedMessage());
			e.printStackTrace();
		}
	}
	
	class ReportHeaders
	{
		private Map<String, String> columnNameMappings;
		private ArrayList<String> excelHeaders;
		private ArrayList<String> dataHeaders;
		private Map<String, Integer> columnSequenceMappings;
		
		ReportHeaders () 
		{
			Map<String, String> columnNameMappings = new LinkedHashMap<String, String>();
			columnNameMappings.put("ANALYSERID", "Analyzer Id");
			columnNameMappings.put("PARENTID", "Parent Id");
			columnNameMappings.put("CONSULTANTNAME", "Consultant Name");
			columnNameMappings.put("MANAGINGDIRECTOR", "MD");
			columnNameMappings.put("DEALTYPE", "Deal Type");
			columnNameMappings.put("STARTDATE", "Start Date");
			columnNameMappings.put("ENDDATE", "End Date");
			columnNameMappings.put("TERMINATIONDATE", "Term Date");
			columnNameMappings.put("BILLRATE", "Bill Rate");
			columnNameMappings.put("PAYRATE", "Pay Rate");
			columnNameMappings.put("PROFIT", "Net Profit");
			columnNameMappings.put("GA", "GA");
			columnNameMappings.put("EMPLOYEETYPE", "Type");
			columnNameMappings.put("GPAMOUNT", "GP $");
			columnNameMappings.put("NETGP", "Net GP");
			columnNameMappings.put("GPPERCENTAGE", "GP %");
			columnNameMappings.put("COMMISSION", "Commission");
			columnNameMappings.put("EMPLOYEECATEGORY", "Employee Category");
			columnNameMappings.put("RECRUITINGCLASSIFICATION", "Recruiting Classification");
			columnNameMappings.put("AE1", "Account Executive 1");
			columnNameMappings.put("REC1", "Recruiter 1");
			columnNameMappings.put("AE2", "Account Executive 2");
			columnNameMappings.put("REC2", "Recruiter 2");
			columnNameMappings.put("JOBTITLE", "Job Title");
			columnNameMappings.put("CLIENTNAME", "Client Name");
			columnNameMappings.put("REASON", "Reason");
			columnNameMappings.put("CLASSIFICATION1", "Classification 1");
			columnNameMappings.put("CLASSIFICATION2", "Classification 2");
			columnNameMappings.put("ENTITYNAME", "Entity Name");
			columnNameMappings.put("WORKSITESTATE", "Worksite State");
			columnNameMappings.put("WORKSITECITY", "Worksite City");
			columnNameMappings.put("WORKSITEMANAGERNAME", "Worksite Manager Name");
			columnNameMappings.put("WORKSITEMANAGEREMAIL", "Worksite Manager Email");
			columnNameMappings.put("CLIENTID", "Client Id");
			columnNameMappings.put("WORSITEID", "Worksite Id");
			columnNameMappings.put("PSCLIENTID", "PS Client Id");
			columnNameMappings.put("MSPCLIENTPARTNER", "MSP Client Partner");
			columnNameMappings.put("LASTACTIVITYDATE", "Last Activity Date");
			columnNameMappings.put("LASTREHIREDATE", "Last Rehire Date");
			columnNameMappings.put("FIRSTENTRYDATE", "First Entry Date");
			columnNameMappings.put("CURRENTOFFICE", "Current Office");
			columnNameMappings.put("CURRENTVERTICAL", "Current Vertical");
			columnNameMappings.put("CURRENTPRODUCT", "Current Product");
			columnNameMappings.put("CURRENTDEALTYPE", "Current Deal Type");
			columnNameMappings.put("LASTACTIVITYMONTH", "Last Activity Month");
			columnNameMappings.put("LASTACTIVITYYEAR", "Last Activity Year");
			columnNameMappings.put("RECRUITER1EMPLID", "Recruiter1 Empl Id");
			columnNameMappings.put("ISIRCRECRUITER", "IRC Recruiter");
			columnNameMappings.put("ACTIVITYTYPE", "Activity Type");
			columnNameMappings.put("COL1", startMonthName+" - N");
			columnNameMappings.put("COL1A", startMonthName+" - A");
			columnNameMappings.put("COL1F", startMonthName+" - F");
			columnNameMappings.put("COL1C", startMonthName+" - C");
			columnNameMappings.put("MONTH1", "Month-1-" + startMonthName);
			columnNameMappings.put("YEAR1", "Year-1");
			System.out.println("centerMonthName = "+centerMonthName);
			if (centerMonthName != null && centerMonthName.length() > 1 && !centerMonthName.equals(startMonthName))
			{
				logger.debug("Adding Center Month = "+centerMonthName);
				System.out.println("Adding Center Month = "+centerMonthName);
				columnNameMappings.put("COL2", centerMonthName+" - N");
				columnNameMappings.put("COL2A", centerMonthName+" - A");
				columnNameMappings.put("COL2F", centerMonthName+" - F");
				columnNameMappings.put("COL2C", centerMonthName+" - C");
				columnNameMappings.put("MONTH2", "Month-2-" + centerMonthName);
				columnNameMappings.put("YEAR2", "Year-2");
			}
			System.out.println("endMonthName = "+endMonthName);
			if (endMonthName != null && endMonthName.length() > 1 && !endMonthName.equals(startMonthName))
			{
				logger.debug("Adding End Month = "+endMonthName);
				System.out.println("Adding End Month = "+endMonthName);
				columnNameMappings.put("COL3", endMonthName+" - N");
				columnNameMappings.put("COL3A", endMonthName+" - A");
				columnNameMappings.put("COL3F", endMonthName+" - F");
				columnNameMappings.put("COL3C", endMonthName+" - C");
				columnNameMappings.put("MONTH3", "Month-3-" + endMonthName);
				columnNameMappings.put("YEAR3", "Year-3");
			}
			excelHeaders = new ArrayList<String>(columnNameMappings.values());
			dataHeaders = new ArrayList<String>(columnNameMappings.keySet());
			
			columnSequenceMappings = new HashMap<String, Integer>();
			int seq = 0;
			for (String col : dataHeaders)
			{
				columnSequenceMappings.put(col, new Integer(seq++));
				//System.out.println("Columns at Sequence = "+seq+" and Name = "+col);
			}
		}
		
		int getDataHeaderIndex (String str)
		{
			int index = -1;
			if(columnSequenceMappings.containsKey(str))
			{
				index = ((Integer)columnSequenceMappings.get(str)).intValue();
			}
			//System.out.println("Excel Column Header "+str+" has index = "+index);
			return index;
		}

		void printHeaders()
		{
			int seq = 0;
			System.out.println("Excel Column Headers ARE:");
			for (String cellHeader : excelHeaders) 
			{
				System.out.println("Excel Column Header at Sequence = "+(seq++)+" and Name = "+cellHeader);
			}
			
			seq = 0;
			System.out.println("DATA Column Headers ARE:");
			for (String cellHeader : dataHeaders) 
			{
				System.out.println("Excel Column Header at Sequence = "+(seq++)+" and Name = "+cellHeader);
			}
		}
		
		int getNumberOfExcelColumns ()
		{
			return excelHeaders.size();
		}
		
		public Map<String, String> getColumnNameMappings() {
			return columnNameMappings;
		}

		public void setColumnNameMappings(Map<String, String> columnNameMappings) {
			this.columnNameMappings = columnNameMappings;
		}

		public List<String> getExcelHeaders() {
			return excelHeaders;
		}

		public void setExcelHeaders(ArrayList<String> excelHeaders) {
			this.excelHeaders = excelHeaders;
		}

		public List<String> getDataHeaders() {
			return dataHeaders;
		}

		public void setDataHeaders(ArrayList<String> dataHeaders) {
			this.dataHeaders = dataHeaders;
		}

		public Map<String, Integer> getColumnSequenceMappings() {
			return columnSequenceMappings;
		}

		public void setColumnSequenceMappings(Map<String, Integer> columnSequenceMappings) {
			this.columnSequenceMappings = columnSequenceMappings;
		}
	}
	*/
	
	public List<SelectItem> getOfficeByCompanyCodeList(String companyCode) 
	{
		officeList = new ArrayList<SelectItem>();
		if (!companyCode.equalsIgnoreCase(Constants.STRING_CONSTANT_ALL)) 
		{
			try
			{
				List<OfficeDTO> officeLists = officeService.getOfficeList(userId, companyCode);
				if (officeLists != null)
				{
					for (OfficeDTO c:officeLists)
					{						
						officeList.add(new SelectItem(c.getOfficeDescription(), c.getOfficeDescription()));
					}
				}
			}
			catch (Exception e)
			{
				System.out.println("Exception in AnalyserActivityReportsBean --> getOfficeByCompanyCodeList.");
				logger.debug("Exception in AnalyserActivityReportsBean --> getOfficeByCompanyCodeList.");
				e.printStackTrace();
			}
		}
		return officeList;
	}

	public List<SelectItem> getOfficeList() {
		return officeList;
	}

	public void setOfficeList(List<SelectItem> officeList) {
		this.officeList = officeList;
	}
	public List<SelectItem> getMDCommissionPersonList(String companyCode) 
	{
		mdCommisionPersonList = new ArrayList<SelectItem>();
		if (!companyCode.equalsIgnoreCase(Constants.STRING_CONSTANT_ALL)) 
		{
			try
			{
				List<AnalyserCommisionPersonDTO> commisionPersonLists = service.getMDCommissionPersonList(userId, companyCode);
				if (commisionPersonLists != null)
				{
					for (AnalyserCommisionPersonDTO c:commisionPersonLists)
					{						
						mdCommisionPersonList.add(new SelectItem(c.getPersonName(), c.getPersonName()));
					}
				}
			}
			catch (Exception e)
			{
				System.out.println("Exception in AnalyserActivityReportsBean --> getMDCommissionPersonList.");
				logger.debug("Exception in AnalyserActivityReportsBean --> getMDCommissionPersonList.");
				e.printStackTrace();
			}
		}
		return mdCommisionPersonList;
	}
	public List<SelectItem> getMdCommisionPersonList() {
		return mdCommisionPersonList;
	}

	public void setMdCommisionPersonList(List<SelectItem> mdCommisionPersonList) {
		this.mdCommisionPersonList = mdCommisionPersonList;
	}
	
	public List<SelectItem> getVerticalByCompanyCodeList(String companyCode) 
	{
		verticalLists = new ArrayList<SelectItem>();
		if (!companyCode.equalsIgnoreCase(Constants.STRING_CONSTANT_ALL)) 
		{
			try
			{
				List<VerticalDTO> vertical = verticalService.getVerticalList(userId, companyCode);
				if (vertical != null)
				{
					/**
					* @@TODO
					* If the Constants.DEFAULT_COMPANY_CODE is DISYS we are adding below list value in the VerticalLists list selectItem 
					*/
					if (companyCode.equalsIgnoreCase(Constants.DEFAULT_COMPANY_CODE))
					{
						verticalLists.add(new SelectItem("AllWOFNA", "All without F&A"));
					}
					for (VerticalDTO c:vertical)
					{						
						verticalLists.add(new SelectItem(c.getVerticalDescription(), c.getVerticalDescription()));
					}
				}
			}
			catch (Exception e)
			{
				System.out.println("Exception in AnalyserActivityReportsBean --> getVerticalByCompanyCodeList.");
				logger.debug("Exception in AnalyserActivityReportsBean --> getVerticalByCompanyCodeList.");
				e.printStackTrace();
			}
		}
		return verticalLists;
	}

	public List<SelectItem> getVerticalLists() {
		return verticalLists;
	}

	public void setVerticalLists(List<SelectItem> verticalLists) {
		this.verticalLists = verticalLists;
	}
	
	public List<SelectItem> getEntityByCompanyCodeList(String companyCode) 
	{
		entityList = new ArrayList<SelectItem>();
		if (!companyCode.equalsIgnoreCase(Constants.STRING_CONSTANT_ALL)) 
		{
			try
			{
				List<EntityDTO> entityLists = entityService.getEntityList(userId, companyCode);
				if (entityLists != null)
				{
					for (EntityDTO c:entityLists)
					{						
						entityList.add(new SelectItem(c.getEntityCode(), c.getEntityDescription()));
					}
				}
			}
			catch (Exception e)
			{
				System.out.println("Exception in AnalyserActivityReportsBean --> getEntityByCompanyCodeList.");
				logger.debug("Exception in AnalyserActivityReportsBean --> getEntityByCompanyCodeList.");
				e.printStackTrace();
			}
		}
		return entityList;
	}

	public List<SelectItem> getEntityList() {
		return entityList;
	}

	public void setEntityList(List<SelectItem> entityList) {
		this.entityList = entityList;
	}
	
	public List<SelectItem> getAllCommissionPersonList(String companyCode) 
	{
		commisionPersonList = new ArrayList<SelectItem>();
		if (!companyCode.equalsIgnoreCase(Constants.STRING_CONSTANT_ALL)) {
			try {
				List<AnalyserCommisionPersonDTO> commisionPersonLists = service.getAllCommissionPersonList(userId,
						companyCode);
				if (commisionPersonLists != null) {
					for (AnalyserCommisionPersonDTO c : commisionPersonLists) {
						commisionPersonList.add(new SelectItem(c.getPersonName(), c.getPersonName()));
					}
				}
			} catch (Exception e) {
				System.out.println("Exception in AnalyserActivityReportsBean --> getAllCommissionPersonList .");
				logger.debug("Exception in AnalyserActivityReportsBean --> getAllCommissionPersonList .");
				e.printStackTrace();
			}
		}
		return commisionPersonList;
	}
	
	public List<SelectItem> getCommisionPersonList() {
		return commisionPersonList;
	}

	public void setCommisionPersonList(List<SelectItem> commisionPersonList) {
		this.commisionPersonList = commisionPersonList;
	}
	
	public void onCompanyCodeChange(javax.faces.event.AjaxBehaviorEvent event) {
		
	}

	public String getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}
}
