/**
 * 
 */
package com.disys.analyzer.reports.revenue;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import org.apache.poi.ss.usermodel.CellType;
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

import com.disys.analyzer.config.database.DbWork;
import com.disys.analyzer.config.util.Constants;
import com.disys.analyzer.config.util.FacesMessageSeverity;
import com.disys.analyzer.config.util.FacesUtils;
import com.disys.analyzer.config.util.WriteToFile;
import com.disys.analyzer.exception.ReportDataNotFoundException;
import com.disys.analyzer.model.dto.AnalyserCategory2DTO;
import com.disys.analyzer.model.dto.AnalyserClientDTO;
import com.disys.analyzer.model.dto.AnalyserCommisionPersonDTO;
import com.disys.analyzer.model.dto.DealTypeDTO;
import com.disys.analyzer.model.dto.EntityDTO;
import com.disys.analyzer.model.dto.OfficeDTO;
import com.disys.analyzer.model.dto.ProductDTO;
import com.disys.analyzer.model.dto.RecruitingClassificationDTO;
import com.disys.analyzer.model.dto.VerticalDTO;
import com.disys.analyzer.reports.util.ReportCriteria;
import com.disys.analyzer.reports.util.ReportMetaData;
import com.disys.analyzer.reports.util.ReportUtil;
import com.disys.analyzer.service.AnalyserCategory2Service;
import com.disys.analyzer.service.AnalyserClientService;
import com.disys.analyzer.service.AnalyserCommisionPersonService;
import com.disys.analyzer.service.AnalyserService;
import com.disys.analyzer.service.DealTypeService;
import com.disys.analyzer.service.EntityService;
import com.disys.analyzer.service.OfficeService;
import com.disys.analyzer.service.ProductService;
import com.disys.analyzer.service.RecruitingClassificationService;
import com.disys.analyzer.service.VerticalService;

/**
 * @author Sajid
 * @since Dec 24, 2020
 */

@ManagedBean
@ViewScoped
public class RevenueByClientReportsBean extends SpringBeanAutowiringSupport implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 9045343638788688890L;

	private Logger logger = LoggerFactory.getLogger(getClass());

	private List<SelectItem> commissionPersons;

	private String commissionPerson;

	private List<Map<String, Object>> reportData;
	private List<Map<String, Object>> filteredList;

	private final static String FILE_NAME = "RevenueByClientReport";
	private final static String FILE_EXTENSION = ".xlsx";

	private String completeFilePath;

	private StreamedContent generatedExcelFile;

	private String newFileName;

	private String vertical;
	private String product;
	private String analyzerCategory1;
	private String analyzerCategory2;

	private String client;
	private List<SelectItem> clients;

	private String industry;

	private List<SelectItem> verticals;
	private List<SelectItem> products;
	private List<SelectItem> category1;
	private List<SelectItem> category2;

	private List<SelectItem> industries;

	private String managingDirector;
	private List<SelectItem> managingDirectorList;

	private String recruitingTeam;
	private String dealType;
	
	private String branch;
	private List<SelectItem> branchesList;
	private String companyCode;
	Integer roleId = FacesUtils.getCurrentUserRole();
	

	@Autowired
	private AnalyserCommisionPersonService service;
	
	@Autowired
	private AnalyserService analyserService;
	
	private List<SelectItem> entityNames;
	private String entityName;

	private boolean userRoleAllowedDownloadAccess;
	
	private String userId=FacesUtils.getCurrentUserId();
	private List<SelectItem> officeList;
	private List<SelectItem> mdCommisionPersonList;
	private List<SelectItem> commisionPersonList;
	private List<SelectItem> analyserClientList;
	private List<SelectItem> recruitingClassificationList;
	private List<SelectItem> analyserCategory2List;
	private List<SelectItem> productList;
	private List<SelectItem> verticalLists;
	private List<SelectItem> dealTypeList;
	private List<SelectItem> entityList;		
	
	@Autowired
	OfficeService officeService;
	
	@Autowired
	AnalyserClientService analyserClientService;
	
	@Autowired
	RecruitingClassificationService recruitingClassificationService;
	
	@Autowired
	AnalyserCategory2Service analyserCategory2Service;
	
	@Autowired
	ProductService productService;
	
	@Autowired
	VerticalService verticalService;	
	
	@Autowired 
	private DealTypeService dealTypeService;
	
	@Autowired 
	private EntityService entityService;
	
	@PostConstruct
	public void init() {
		try {
			FacesContextUtils.getRequiredWebApplicationContext(FacesContext.getCurrentInstance())
					.getAutowireCapableBeanFactory().autowireBean(this);
			// TODO check if both the services are autowired or not
		} catch (Exception ex) {
			logger.debug(ex.getMessage());
		}
	}
	

	public RevenueByClientReportsBean() {
		this.companyCode = Constants.DEFAULT_COMPANY_CODE;
		logger.debug("Inside RevenueByClientReportsBean");
		if(ReportUtil.isUserRoleAllowedDownloadAccess())
		{
			System.out.println("Download Acces ENABLED");
			logger.debug("Download Acces ENABLED");
			userRoleAllowedDownloadAccess=true;
		}
		else
		{
			System.out.println("Download Acces DISABLED");
			logger.debug("Download Acces DISABLED");
			userRoleAllowedDownloadAccess=false;
		}
	}

	public void eraseFilter() {
		this.reportData = null;
	}

	
	/*public void exportToExcel() throws Exception {
		if (reportData == null || reportData.size() == 0) {
			FacesUtils.addGlobalMessage(FacesMessageSeverity.ERROR, "No data to export",
					"There is no data to export to excel!");
			throw new ReportDataNotFoundException("No data to export");
		}
		
		Map<String, String> columnNameMappings = new HashMap<String, String>();
		columnNameMappings.put("PARENTID", "Parent Id");
		columnNameMappings.put("CONSULTANTNAME", "Consultant Name");
		columnNameMappings.put("MANAGINGDIRECTOR", "MD");
		columnNameMappings.put("VERTICAL", "Vertical");
		columnNameMappings.put("PRODUCT", "Product");
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
		
	}*/
	public void exportToExcel() throws ReportDataNotFoundException {
		
		if (reportData == null || reportData.size() == 0) {
			FacesUtils.addGlobalMessage(FacesMessageSeverity.ERROR, "No data to export",
					"There is no data to export to excel!");
			throw new ReportDataNotFoundException("No data to export");
		}
		Map<String, String> columnNameMappings = new HashMap<String, String>();
		columnNameMappings.put("STARTDATEFORMATTED","Start Date");
		columnNameMappings.put("COMPANY","Company");
		columnNameMappings.put("LASTNAME","Last Name");
		columnNameMappings.put("FIRSTNAME","First Name");
		columnNameMappings.put("EMAIL","Email");
		columnNameMappings.put("HOMEPHONE","Home Phone");
		columnNameMappings.put("CELLPHONE","Cell Phone");
		columnNameMappings.put("BILLRATE","Bill Rate");
		columnNameMappings.put("PAYRATE","Pay Rate");
		columnNameMappings.put("PROFIT","Profit");
		columnNameMappings.put("AE","AE");
		columnNameMappings.put("RECRUITER","Recruiter");
		columnNameMappings.put("PRODUCT","Product");
		columnNameMappings.put("ENDDATE","End Date");
		columnNameMappings.put("EMPTYPE","Employee Type");
		columnNameMappings.put("INDUSTRY","Industry");
		columnNameMappings.put("JOBTITLE","Job Title");
		columnNameMappings.put("AE2","AE-2");
		columnNameMappings.put("RECRUITER2","Recruiter-2");
		columnNameMappings.put("CL","Liaison");
		columnNameMappings.put("ANALYZERCATEGORY1","Classification 1");
		columnNameMappings.put("ANALYZERCATEGORY2","Classification 2");
		columnNameMappings.put("GP","GP%");
		columnNameMappings.put("CITY","City");
		columnNameMappings.put("STATE","State");
		columnNameMappings.put("VERTICAL","Vertical");
		columnNameMappings.put("DEALTYPE","Deal Type");
		columnNameMappings.put("MANAGINGDIRECTOR","Managing Director");
		columnNameMappings.put("RECRUITINGCLASSIFICATION","Recruiting Classification");
		columnNameMappings.put("PROJECTCOST","Project Cost");
		columnNameMappings.put("OFFICE","Office");
		
		columnNameMappings.put("PORTFOLIO","Portfolio");
		columnNameMappings.put("PORTFOLIODESCRIPTION","Portfolio Description");
		
		//columnNameMappings.put("COMMISSIONPERSON5","Vertical Sales");
		columnNameMappings.put("COMMISSIONPERSON6","IT-GS-Practice");
		columnNameMappings.put("COMMISSIONPERSON7","IT-GS-Delivery");
		columnNameMappings.put("COMMISSIONPERSON8","IT-GS-BDE");
		columnNameMappings.put("COMMISSIONPERSON9","IT-GS-Proposal");
		columnNameMappings.put("ENTITYNAME","Entity Name");
		
		columnNameMappings.put("COM","COM");
		columnNameMappings.put("AE1ORPHAN","AE1 Orphan");
		columnNameMappings.put("REC1ORPHAN","Rec1 Orphan");
		columnNameMappings.put("AE2ORPHAN","AE2 Orphan");
		columnNameMappings.put("REC2ORPHAN","Rec2 orphan");
		columnNameMappings.put("WORKSITEMANAGERNAME","Worksite manager name");
		columnNameMappings.put("WORKSITEMANAGEREMAIL","Worksite manager email");
		columnNameMappings.put("MSPNAME","MSP Client Partner");
		columnNameMappings.put("PARENTID","Parent Id");
		columnNameMappings.put("EMPLOYEECATEGORY","Employee Category");
		
		columnNameMappings.put("EMPLOYEECLASS","Employee Class");
		columnNameMappings.put("FLSASTATUS","Flsa Status");
		
		columnNameMappings.put("AE1EMPLID","AE1 EmplId");
		columnNameMappings.put("AE2EMPLID","AE2 EmplId");
		columnNameMappings.put("RECRUITER1EMPLID","Recruiter1 EmplId");
		columnNameMappings.put("RECRUITER2EMPLID","Recruiter2 EmplId");
		columnNameMappings.put("MSPCLIENTPARTNEREMPLID","MSP EmplId");
		columnNameMappings.put("MDEMPLID","MD EmplId");
		columnNameMappings.put("COMPANYCODE","Company Code");
		columnNameMappings.put("COSELLSTATUS","CoSellStatus");
		columnNameMappings.put("DEALPORTFOLIO1AE1","DealPortfolio1AE1");
		columnNameMappings.put("DEALPORTFOLIO2REC1","DealPortfolio2REC1");
		columnNameMappings.put("DEALPORTFOLIO3AE2","DealPortfolio3AE2");
		columnNameMappings.put("DEALPORTFOLIO4REC2","DealPortfolio4REC2");
		
		columnNameMappings.put("SPLITCOMMISSIONPERCENTAGE1","AE1 Split Percentage");
		columnNameMappings.put("SPLITCOMMISSIONPERCENTAGE2","REC1 Split Percentage");
		columnNameMappings.put("SPLITCOMMISSIONPERCENTAGE3","AE2 Split Percentage");
		columnNameMappings.put("SPLITCOMMISSIONPERCENTAGE4","REC2 Split Percentage");
		columnNameMappings.put("COMMISSIONABLEPROFIT","CommissionableProfit");
		columnNameMappings.put("BULLHORNPLACEMENTID","BullhornPlacementId");
		
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("Revenue By Client");
		
		CellStyle cellStyle = workbook.createCellStyle();
		Font font = workbook.createFont();
		font.setBoldweight(Font.BOLDWEIGHT_BOLD);
		cellStyle.setFont(font);
		
		CreationHelper createHelper = workbook.getCreationHelper();
		CellStyle cellStyleDate = workbook.createCellStyle();  
//		cellStyleDate.setDataFormat(  
//            createHelper.createDataFormat()
//            .getFormat("m/d/yy h:mm"));
		
		//short dateFormat = createHelper.createDataFormat().getFormat("m/d/yy");
		//cellStyle.setDataFormat(dateFormat);
		
//		.getFormat("MM/dd/yyyy"));
		
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
			if(roleId != 3){
				map.remove("SPLITCOMMISSIONPERCENTAGE1");
				map.remove("SPLITCOMMISSIONPERCENTAGE2");
				map.remove("SPLITCOMMISSIONPERCENTAGE3");
				map.remove("SPLITCOMMISSIONPERCENTAGE4");
				map.remove("COMMISSIONABLEPROFIT");
			}			
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
			if(roleId != 3){
				map.remove("SPLITCOMMISSIONPERCENTAGE1");
				map.remove("SPLITCOMMISSIONPERCENTAGE2");
				map.remove("SPLITCOMMISSIONPERCENTAGE3");
				map.remove("SPLITCOMMISSIONPERCENTAGE4");
				map.remove("COMMISSIONABLEPROFIT");
			}
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
							System.out.println("RevenueByClientReportsBean --> exportToExcel --> The date is : "+entry.getValue().toString());
							logger.debug("RevenueByClientReportsBean --> exportToExcel --> The date is : "+entry.getValue().toString());
							
							//SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
							SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");	//07/30/2019
						    java.util.Date d=null;
						    try {
						        d= sdf.parse(entry.getValue().toString());
								if (sdf != null)
								{
									System.out.println("RevenueByClientReportsBean --> exportToExcel --> Excel Cell FORMATTED Date Value = "+sdf);
									logger.debug("RevenueByClientReportsBean --> exportToExcel --> Excel Cell FORMATTED Date Value = "+sdf);
								}
						    } catch (ParseException e) {
								System.out.println("RevenueByClientReportsBean --> exportToExcel --> Date NOT converted properly : "+entry.getValue().toString());
								logger.debug("RevenueByClientReportsBean --> exportToExcel --> Date NOT converted properly : "+entry.getValue().toString());
						        d=null;
						        e.printStackTrace();
						        continue;
						    }
						    cellStyleDate.setDataFormat((short)14);
						    cell.setCellValue(d);
							if (d != null)
							{
								System.out.println("RevenueByClientReportsBean --> exportToExcel --> Excel Cell Date Value = "+d);
								logger.debug("RevenueByClientReportsBean --> exportToExcel --> Excel Cell Date Value = "+d);
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
		logger.debug("About to create Revenue by Client report");

		String reportName = "REVENUE_BY_CLIENT_REPORT";

		String reportStartDate = "01/01/1900";
		String reportEndDate = "01/01/2020";

		// String reportDescription = "REVENUE BY LOCATION";

		// String sortOrder = "";

		// String location; branch
		// String person = commissionPerson;
		// String empType = employeeType;

		// String optionalParams = (String)request.getParameter("op");
		String optionalParams = null;

		if (optionalParams == null) {
			optionalParams = "";
		}

		// client = client.replaceAll("&", "*");
		// industry = industry.replaceAll("&", "*");

		String sortOrder = "COMPANY";
		String reportParam = FacesUtils.getCurrentUserId();

		reportParam = reportParam + "|" + managingDirector + "|" + commissionPerson + "|" + reportStartDate + "|"
				+ reportEndDate + "|" + client + "|" + recruitingTeam + "|" + analyzerCategory2 + "|" + product + "|"
				+ vertical + "|" + "|" + dealType + "|" + branch + "|" + sortOrder+"|"+ entityName + "|" +companyCode;	

		System.out.println("Report parameters are = "+reportParam);
		logger.debug("Report parameters are :" +reportParam);
		ReportCriteria rc = null;
		reportData = new ArrayList<Map<String, Object>>();
		try {
			rc = new ReportCriteria(reportName, reportParam);
			reportData = rc.getReport();// get the data from the arraylist
			System.out.println(reportData.size());
			logger.debug("Client report Size is : " + reportData.size());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

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
	 * @param commissionPersons
	 *            the commissionPersons to set
	 */
	public void setCommissionPersons(List<SelectItem> commissionPersons) {
		this.commissionPersons = commissionPersons;
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
	 * @return the analyzerCategory1
	 */
	public List<SelectItem> getCategory1() {
		if (category1 == null) {
			category1 = new ArrayList<SelectItem>();
			category1.add(new SelectItem("NDT", "NDT"));
			category1.add(new SelectItem("IDT", "IDT"));
			category1.add(new SelectItem("DSS", "DSS"));
			category1.add(new SelectItem("RPO", "RPO"));
		}
		return category1;
	}

	/**
	 * @param analyzerCategory1
	 *            the analyzerCategory1 to set
	 */
	public void setCategory1(List<SelectItem> category1) {
		this.category1 = category1;
	}

	/**
	 * @return the analyzerCategory2
	 */
	public List<SelectItem> getCategory2() {
		if (category2 == null) {
			category2 = new ArrayList<SelectItem>();
			//category2.add(new SelectItem("GSOC", "GSOC"));
			category2.add(new SelectItem("Canada", "Canada"));
			category2.add(new SelectItem("D2M", "D2M"));
			category2.add(new SelectItem("Digital CBU", "Digital CBU"));
			category2.add(new SelectItem("ERP CBU", "ERP CBU"));
			category2.add(new SelectItem("Go", "Go"));
			category2.add(new SelectItem("Implementation", "Implementation"));
			category2.add(new SelectItem("India A/B", "India A/B"));
			category2.add(new SelectItem("Java CBU", "Java CBU"));
			category2.add(new SelectItem("PERM CBU", "PERM CBU"));
		}
		return category2;
	}

	/**
	 * @param analyzerCategory2
	 *            the analyzerCategory2 to set
	 */
	public void setCategory2(List<SelectItem> category2) {
		this.category2 = category2;
	}

	/**
	 * @return the verticals
	 */
	public List<SelectItem> getVerticals() {
		/*
		 * if (verticals == null) { verticals = new ArrayList<SelectItem>();
		 * verticals.add(new SelectItem("Banking", "Banking"));
		 * verticals.add(new SelectItem("Diversified", "Diversified"));
		 * verticals.add(new SelectItem("Energy", "Energy")); verticals.add(new
		 * SelectItem("Finance and Accounting", "Finance and Accounting"));
		 * verticals.add(new SelectItem("Health", "Health")); verticals.add(new
		 * SelectItem("Hi Tech", "Hi Tech")); } return verticals;
		 */

		if (verticals == null)
		{
			verticals = new ArrayList<SelectItem>(10);
			verticals.add(new SelectItem("AllWOFNA", "All without F&A"));
			List<String> list = analyserService.getVerticalsList();
			for (String ver : list)
			{
				verticals.add(new SelectItem(ver, ver));
			}
		}
		return verticals;
	}

	/**
	 * @param verticals
	 *            the verticals to set
	 */
	public void setVerticals(List<SelectItem> verticals) {
		this.verticals = verticals;
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
	 * @return the analyzerCategory1
	 */
	public String getAnalyzerCategory1() {
		return analyzerCategory1;
	}

	/**
	 * @param analyzerCategory1
	 *            the analyzerCategory1 to set
	 */
	public void setAnalyzerCategory1(String analyzerCategory1) {
		this.analyzerCategory1 = analyzerCategory1;
	}

	/**
	 * @return the analyzerCategory2
	 */
	public String getAnalyzerCategory2() {
		return analyzerCategory2;
	}

	/**
	 * @param analyzerCategory2
	 *            the analyzerCategory2 to set
	 */
	public void setAnalyzerCategory2(String analyzerCategory2) {
		this.analyzerCategory2 = analyzerCategory2;
	}

	/**
	 * @return the client
	 */
	public String getClient() {
		return client;
	}

	/**
	 * @param client
	 *            the client to set
	 */
	public void setClient(String client) {
		this.client = client;
	}

	/**
	 * @return the industry
	 */
	public String getIndustry() {
		return industry;
	}

	/**
	 * @param industry
	 *            the industry to set
	 */
	public void setIndustry(String industry) {
		this.industry = industry;
	}

	/**
	 * @return the clients
	 */
	public List<SelectItem> getClients() {
		if (clients == null) {
			clients = new ArrayList<SelectItem>();
			// ActiveOnly 0 = ALL , 1 = Only Active, 2 = Liaison
			ReportMetaData reportMetaData = null;
			try {
				reportMetaData = new ReportMetaData();
			} catch (Exception e) {
				e.printStackTrace();
			} // application as servlet context
			DbWork db = new DbWork();
			String status = "1"; // client status 1=Active, 0=In-Active
			String[] param = new String[2];
			String queryString = null;
			try {
				queryString = (String) reportMetaData.getData("CLIENTLISTWITHID.SQL");
			} catch (Exception e1) {
				e1.printStackTrace();
			}

			param[0] = FacesUtils.getCurrentUserId();
			param[1] = status;

			List<Map<String, Object>> dataType = null;
			try {
				dataType = db.getResult(queryString, param, "SP");
			} catch (Exception e) {
				e.printStackTrace();
			}

			// To avoid Null Pointer Exception
			if (dataType == null) {
				dataType = new ArrayList<Map<String, Object>>();
			}

			for (int i = 0; i < dataType.size(); i++) {
				Map<String, Object> map = dataType.get(i);
				String id = ((Integer) map.get("CLIENTID")).toString();
				String desc = (String) map.get("COMPANY");
				clients.add(new SelectItem(id, desc));
			}
		}
		return clients;
	}

	/**
	 * @param clients
	 *            the clients to set
	 */
	public void setClients(List<SelectItem> clients) {
		this.clients = clients;
	}

	/**
	 * @return the industries
	 */
	public List<SelectItem> getIndustries() {
		if (industries == null) {
			industries = new ArrayList<SelectItem>();

			industries.add(new SelectItem("Communications", "Communications"));
			industries.add(new SelectItem("Defense & Security", "Defense & Security"));
			industries.add(new SelectItem("Education & Training", "Education & Training"));
			industries.add(new SelectItem("Energy", "Energy"));
			industries.add(new SelectItem("Financial Services", "Financial Services"));
			industries.add(new SelectItem("Government & Public Services", "Government & Public Services"));
			industries.add(new SelectItem("Healthcare & Life Sciences", "Healthcare & Life Sciences"));
			industries.add(new SelectItem("Hospitality & Leisure", "Hospitality & Leisure"));
			industries.add(new SelectItem("Manufacturing", "Manufacturing"));
			industries.add(new SelectItem("Non-Profit", "Non-Profit"));
			industries.add(new SelectItem("Retail & Consumer Goods", "Retail & Consumer Goods"));
			industries.add(new SelectItem("Technology & Consulting", "Technology & Consulting"));
			industries.add(new SelectItem("Transportation", "Transportation"));
			industries.add(new SelectItem("Other", "Other"));

		}
		return industries;
	}

	/**
	 * @param industries
	 *            the industries to set
	 */
	public void setIndustries(List<SelectItem> industries) {
		this.industries = industries;
	}

	/**
	 * @return the commissionPerson
	 */
	public String getCommissionPerson() {
		return commissionPerson;
	}

	/**
	 * @param commissionPerson
	 *            the commissionPerson to set
	 */
	public void setCommissionPerson(String commissionPerson) {
		this.commissionPerson = commissionPerson;
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

	public String getRecruitingTeam() {
		return recruitingTeam;
	}

	public void setRecruitingTeam(String recruitingTeam) {
		this.recruitingTeam = recruitingTeam;
	}

	public String getDealType() {
		return dealType;
	}

	public void setDealType(String dealType) {
		this.dealType = dealType;
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
	
	public static void main(String[] args) 
	{
		String num = "0.150";
		Double d = Double.parseDouble(num);
		System.out.println("D = "+d);
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

	/**
	 * @return the userRoleAllowedDownloadAccess
	 */
	public boolean isUserRoleAllowedDownloadAccess() {
		return userRoleAllowedDownloadAccess;
	}

	/**
	 * @param userRoleAllowedDownloadAccess
	 *            the userRoleAllowedDownloadAccess to set
	 */
	public void setUserRoleAllowedDownloadAccess(boolean userRoleAllowedDownloadAccess) {
		this.userRoleAllowedDownloadAccess = userRoleAllowedDownloadAccess;
	}
	
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
				System.out.println("Exception in RevenueByClientReportsBean --> getOfficeByCompanyCodeList.");
				logger.debug("Exception in RevenueByClientReportsBean --> getOfficeByCompanyCodeList.");
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
				System.out.println("Exception in RevenueByClientReportsBean --> getMDCommissionPersonList.");
				logger.debug("Exception in RevenueByClientReportsBean --> getMDCommissionPersonList.");
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
	
	public List<SelectItem> getAllCommissionPersonList(String companyCode) 
	{
		commisionPersonList = new ArrayList<SelectItem>();
		if (!companyCode.equalsIgnoreCase(Constants.STRING_CONSTANT_ALL)) 
		{
			try
			{
				List<AnalyserCommisionPersonDTO> commisionPersonLists = service.getAllCommissionPersonList(userId, companyCode);
				
				if (commisionPersonLists != null)
				{	
					/**
					* @@TODO
					* If the Company Code is DISYS we are adding below list value in the CommisionPersonList list selectItem 
					*/
					if (companyCode.equalsIgnoreCase(Constants.DEFAULT_COMPANY_CODE))
					{
						commisionPersonList.add(new SelectItem("BDD", "Business Development Data"));
						commisionPersonList.add(new SelectItem("NBDD", "NAM Business Development"));
						commisionPersonList.add(new SelectItem("MSS", "MSS Business Development Data"));
					}	
					for (AnalyserCommisionPersonDTO c:commisionPersonLists)
					{						
						commisionPersonList.add(new SelectItem(c.getPersonName(), c.getPersonName()));
					}
				}
			}
			catch (Exception e)
			{
				System.out.println("Exception in RevenueByClientReportsBean --> getAllCommissionPersonList .");
				logger.debug("Exception in RevenueByClientReportsBean --> getAllCommissionPersonList .");
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


	public List<SelectItem> getAnalyserClientByCompanyCodeList(String companyCode) 
	{
		analyserClientList = new ArrayList<SelectItem>();
		if (!companyCode.equalsIgnoreCase(Constants.STRING_CONSTANT_ALL)) 
		{
			try
			{
				List<AnalyserClientDTO> analyserClientLists = analyserClientService.getAnalyserClientList(userId, companyCode);
				
				if (analyserClientLists != null)
				{
					for (AnalyserClientDTO c:analyserClientLists)
					{						
						analyserClientList.add(new SelectItem(c.getClientId(), c.getCompany()));
					}
				}
			}
			catch (Exception e)
			{
				System.out.println("Exception in RevenueByClientReportsBean --> getAnalyserClientByCompanyCodeList.");
				logger.debug("Exception in RevenueByClientReportsBean --> getAnalyserClientByCompanyCodeList.");
				e.printStackTrace();
			}
		}
		return analyserClientList;
	}

	public List<SelectItem> getAnalyserClientList() {
		return analyserClientList;
	}

	public void setAnalyserClientList(List<SelectItem> analyserClientList) {
		this.analyserClientList = analyserClientList;
	}
	
	public List<SelectItem> getRecruitingClassificationsByCompanyCodeList(String companyCode) 
	{
		recruitingClassificationList = new ArrayList<SelectItem>();
		if (!companyCode.equalsIgnoreCase(Constants.STRING_CONSTANT_ALL)) 
		{
			try
			{
				List<RecruitingClassificationDTO> recruitingClassificationLists = recruitingClassificationService.getRecruitingClassificationsList(userId, companyCode);
				if (recruitingClassificationLists != null)
				{
					for (RecruitingClassificationDTO p:recruitingClassificationLists)
					{						
						recruitingClassificationList.add(new SelectItem(p.getRecruitingClassificationValue(), p.getRecruitingClassificationName()));
					}
				}
			}
			catch (Exception e)
			{
				System.out.println("Exception in RevenueByClientReportsBean --> getRecruitingClassificationsByCompanyCodeList.");
				logger.debug("Exception in RevenueByClientReportsBean --> getRecruitingClassificationsByCompanyCodeList.");
				e.printStackTrace();
			}
		}
		return recruitingClassificationList;
	}

	public List<SelectItem> getRecruitingClassificationList() {
		return recruitingClassificationList;
	}

	public void setRecruitingClassificationList(List<SelectItem> recruitingClassificationList) {
		this.recruitingClassificationList = recruitingClassificationList;
	}
	
	public List<SelectItem> getAnalyserCategory2ByCompanyCodeList(String companyCode) 
	{
		analyserCategory2List = new ArrayList<SelectItem>();
		if (!companyCode.equalsIgnoreCase(Constants.STRING_CONSTANT_ALL)) 
		{
			try
			{
				List<AnalyserCategory2DTO> analyserCategory2Lists = analyserCategory2Service.getAnalyserCategory2List(userId, companyCode);
				if (analyserCategory2Lists != null)
				{
					for (AnalyserCategory2DTO c:analyserCategory2Lists)
					{
						
						analyserCategory2List.add(new SelectItem(c.getAnalyserCategory2Code(), c.getAnalyserCategory2Description()));
					}
				}
			}
			catch (Exception e)
			{
				System.out.println("Exception in RevenueByClientReportsBean --> getAnalyserCategory2ByCompanyCodeList.");
				logger.debug("Exception in RevenueByClientReportsBean --> getAnalyserCategory2ByCompanyCodeList.");
				e.printStackTrace();
			}
		}
		return analyserCategory2List;
	}

	public List<SelectItem> getAnalyserCategory2List() {
		return analyserCategory2List;
	}

	public void setAnalyserCategory2List(List<SelectItem> analyserCategory2List) {
		this.analyserCategory2List = analyserCategory2List;
	}
	
	public List<SelectItem> getProductByCompanyCodeList(String companyCode) 
	{
		productList = new ArrayList<SelectItem>();
		if (!companyCode.equalsIgnoreCase(Constants.STRING_CONSTANT_ALL)) 
		{
			try
			{
				List<ProductDTO> productLists = productService.getProductList(userId, companyCode);
				if (productLists != null)
				{
					for (ProductDTO p:productLists)
					{						
						productList.add(new SelectItem(p.getProductDescription(), p.getProductLabel()));
					}
				}
			}
			catch (Exception e)
			{
				System.out.println("Exception in RevenueByClientReportsBean --> getProductByCompanyCodeList.");
				logger.debug("Exception in RevenueByClientReportsBean --> getProductByCompanyCodeList.");
				e.printStackTrace();
			}
		}
		return productList;
	}
	
	public List<SelectItem> getProductList() {
		return productList;
	}

	public void setProductList(List<SelectItem> productList) {
		this.productList = productList;
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
					* If the Company Code is DISYS we are adding below list value in the VerticalLists list selectItem 
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
				System.out.println("Exception in RevenueByClientReportsBean --> getVerticalByCompanyCodeList.");
				logger.debug("Exception in RevenueByClientReportsBean --> getVerticalByCompanyCodeList.");
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
	
	public List<SelectItem> getDealTypeByCompanyCodeList(String companyCode) 
	{
		dealTypeList = new ArrayList<SelectItem>();
		if (!companyCode.equalsIgnoreCase(Constants.STRING_CONSTANT_ALL)) 
		{
			try
			{
				List<DealTypeDTO> dealTypeLists = dealTypeService.getDealTypeList(userId, companyCode);
				if (dealTypeLists != null)
				{
					for (DealTypeDTO c:dealTypeLists)
					{
						
						dealTypeList.add(new SelectItem(c.getDealTypeCode(), c.getDealTypeDescription()));
					}
				}
			}
			catch (Exception e)
			{
				System.out.println("Exception in RevenueByClientReportsBean --> getDealTypeByCompanyCodeList.");
				logger.debug("Exception in RevenueByClientReportsBean --> getDealTypeByCompanyCodeList.");
				e.printStackTrace();
			}
		}
		return dealTypeList;
	}

	public List<SelectItem> getDealTypeList() {
		return dealTypeList;
	}

	public void setDealTypeList(List<SelectItem> dealTypeList) {
		this.dealTypeList = dealTypeList;
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
				System.out.println("Exception in EntityBean --> getEntityByCompanyCodeList.");
				logger.debug("Exception in EntityBean --> getEntityByCompanyCodeList.");
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
	
	public void onCompanyCodeChange(javax.faces.event.AjaxBehaviorEvent event) {/*
		HtmlSelectOneMenu selectedItem = (HtmlSelectOneMenu)event.getSource();
		
//		System.out.println(" Family : " + event.getComponent());
//		System.out.println(" HtmlSelectOneMenu : " + selectedItem.getValue().toString());
		
		this.branchesList = getBranchesList();
		this.branchesList.add(new SelectItem("DISYSXXX", "DISYSXXXXX"));
	*/}


	public String getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
		
}