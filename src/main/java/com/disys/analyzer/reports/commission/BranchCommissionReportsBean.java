/**
 * 
 */
package com.disys.analyzer.reports.commission;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
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
import javax.servlet.ServletContext;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
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

/**
 * @author Sajid
 * 
 */
@ManagedBean
@ViewScoped
public class BranchCommissionReportsBean extends SpringBeanAutowiringSupport implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Logger logger = LoggerFactory.getLogger(getClass());

	private String managingDirector;
	private List<SelectItem> managingDirectorList;
	
	private String vertical;
	private List<SelectItem> verticalList;
	
	@Autowired
	private AnalyserCommisionPersonService service;

	private String person;
	private List<SelectItem> commissionPersons;

	private String month;
	private List<SelectItem> monthsList;
	
	private String reportType;
	private String commissionType;
	private String reportingYear;
	

	private List<Map<String, Object>> reportData;
	private List<Map<String, Object>> filteredList;

	private final static String FILE_NAME = "BranchCommissionReport";
	private final static String FILE_EXTENSION = ".xlsx";

	private String completeFilePath;
	private StreamedContent generatedExcelFile;
	private String newFileName;

	private String branch;

	private List<SelectItem> branchesList;
	private List<SelectItem> yearsList;
	
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

	public BranchCommissionReportsBean() {
		logger.debug("Inside BranchCommissionReportsBean");
		
		Calendar c = Calendar.getInstance();
		int year = c.get(Calendar.YEAR);
		yearsList = new ArrayList<SelectItem>();
		yearsList.add(new SelectItem(year, "" + year));
		yearsList.add(new SelectItem(year - 1, "" + (year - 1)));
		yearsList.add(new SelectItem(year - 2, "" + (year - 2)));
	}

	public void eraseFilter() {
		this.reportData = null;
	}

	public void exportToExcel() throws Exception{
		if (reportData == null || reportData.size() == 0) {
			FacesUtils
					.addGlobalMessage(FacesMessageSeverity.ERROR,
							"No data to export",
							"There is no data to export to excel!");
			throw new ReportDataNotFoundException("No data to export");
		}

		List<String> header = new ArrayList<String>();
		header.add("Branch");
		header.add("Cell Phone");
		header.add("Pay Rate");
		header.add("Work Email");
		header.add("Employee Type");
		header.add("AE");
		header.add("Start Date");
		header.add("Liaison");
		header.add("First Name");
		header.add("Email");
		header.add("Home Phone");
		header.add("Company");
		header.add("End Date");
		header.add("Recruiter");
		header.add("OT Rate");
		header.add("Sort Order");
		header.add("Last Name");
		header.add("Sub Company");
		header.add("Address");
		header.add("Bill Rate");
		header.add("Start Date");
		header.add("Perdiem");
		header.add("Work Phone");
		header.add("Subcontractor Email");

		/*BRANCH=Arizona, CELLPHONE=, PAYRATE=35.0, WORKEMAIL=, EMPTYPE=w2, AE=Abhinaya Anand, 
				STARTDATE=2017-11-28 00:00:00.0, CL=Ashley Chmielowski, FIRSTNAME=Jones, EMAIL=adam@taes.com, 
				HOMEPHONE=, COMPANY=ABB - PM Services Project, ENDDATE=01/19/2018, RECRUITER=, OTRATE=35, 
				SORTORDER=0, LASTNAME=Adam, SUBCOMPANY=null, ADDRESS= , ,  , BILLRATE=80.0, 
				STARTDATEFORMATTED=Nov 28, 2017, PERDIEM=0.0, WORKPHONE=, SUBEMAIL=null*/

		// Recruiter Branch No of Consultants Total Billing Total Profit

		XSSFWorkbook workbook = new XSSFWorkbook();

		CellStyle cellStyle1 = workbook.createCellStyle();
		cellStyle1.setFillForegroundColor(IndexedColors.RED.index);

		XSSFSheet sheet = workbook.createSheet("Branch Commission Report");

		int rowNum = 0;
		int colNum = 0;
		System.out.println("Creating excel");

		Row row = sheet.createRow(rowNum++);
		// create excel file header
		/*for (String cellHeader : header) {
			Cell cell = row.createCell(colNum++);
			cell.setCellValue(cellHeader);
		}*/
		

		List<Map<String, Object>> reportDataCopy = reportData;
		
		/*
		 * 
		 * To print column information from the result set
		 */
		int count = 0;
		for (Map<String, Object> map : reportDataCopy) {
			row = sheet.createRow(rowNum++);
			colNum = 0;
			Cell cell = null;
			Iterator<Map.Entry<String, Object>> iterator = map.entrySet()
					.iterator();
			count++;
			if (count > 1) {
				break;
			}
			while (iterator.hasNext()) {
				Map.Entry<String, Object> entry = iterator.next();
				try {
					cell = row.createCell(colNum++);
					String val = "";
					if(entry != null){
						val = entry.toString();
						val = val.substring(0,val.indexOf('='));
					}
					cell.setCellValue(val);
				} catch (Exception ex) {
					logger.debug("Exception in "+logger.getName()+" while writing to the file for : "+entry.toString() +" by user id : "+FacesUtils.getCurrentUserId()
							+ ex.getLocalizedMessage());
					continue;
				}
			}
		}

		for (Map<String, Object> map : reportDataCopy) {
			row = sheet.createRow(rowNum++);
			colNum = 0;
			Cell cell = null;
			Iterator<Map.Entry<String, Object>> iterator = map.entrySet()
					.iterator();
			while (iterator.hasNext()) {
				Map.Entry<String, Object> entry = iterator.next();
				cell = row.createCell(colNum++);
				//check if the value is a numeric or double
				if(FacesUtils.checkIfValueIsDouble(entry.getValue().toString())){
					cell.setCellValue(Double.parseDouble(entry.getValue().toString()));
				}else{
					//check if the value is a numeric or double
					if(FacesUtils.checkIfValueIsDouble(entry.getValue().toString())){
						cell.setCellValue(Double.parseDouble(entry.getValue().toString()));
					}else{
						cell.setCellValue(entry.getValue().toString());
					}
				}
			}
		}

		try {
			String realPath = FacesUtils.getTempFilePath();

			Long dateTime = System.currentTimeMillis();

			// D:\Softwares\Apache\tomcat-8.0.23\webapps\Analyzer\

			completeFilePath = realPath + FILE_NAME + "-" + dateTime
					+ FILE_EXTENSION;
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

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
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
			generatedExcelFile = new DefaultStreamedContent(stream,
					"application/vnd.ms-excel", filePath);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void print() {
		if (reportData == null || reportData.size() == 0) {
			FacesUtils.addGlobalMessage(FacesMessageSeverity.ERROR,
					"No data to print", "There is no data to print!");
			return;
		}
	}

	public void generateReport() {
		logger.debug("About to create CONSULTANTS_BY_CLIENT_REPORT");

		String reportName = "BRANCH_COMMISSION_REPORT";

		// BRANCH_COMMISSION_REPORT.SQL={call
//		{call wireless.spGetCommissionEmployees('[0]','[1]','[2]','[3]','[4]','[5]','[6]')}
//		{call wireless.spGetCommissionEmployees('Gregory.Armstrong@DISYS.COM','1','2017','ALL','ALL','0','ALL')}

		String optionalParams = null;

		if (optionalParams == null) {
			optionalParams = "";
		}

		String reportParam = FacesUtils.getCurrentUserId();
		
		reportParam = reportParam + "|" + month + "|" + reportingYear + "|" + branch + "|" + person + "|" + reportType + "|" + commissionType;

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
	 * @return the monthsList
	 */
	public List<SelectItem> getMonthsList() {
		if(monthsList == null){
			monthsList = new ArrayList<SelectItem>();
			monthsList.add(new SelectItem("1","Jan"));
            monthsList.add(new SelectItem("2","Feb"));
            monthsList.add(new SelectItem("3","Mar"));
            monthsList.add(new SelectItem("4","Apr"));
            monthsList.add(new SelectItem("5","May"));
            monthsList.add(new SelectItem("6","Jun")); 
            monthsList.add(new SelectItem("7","Jul"));
            monthsList.add(new SelectItem("8","Aug"));
            monthsList.add(new SelectItem("9","Sep"));
            monthsList.add(new SelectItem("10","Oct")); 
            monthsList.add(new SelectItem("11","Nov"));
            monthsList.add(new SelectItem("12","Dec"));
		}
		return monthsList;
	}

	/**
	 * @param monthsList
	 *            the monthsList to set
	 */
	public void setMonthsList(List<SelectItem> monthsList) {
		this.monthsList = monthsList;
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
	 * @return the person
	 */
	public String getPerson() {
		return person;
	}

	/**
	 * @param person the person to set
	 */
	public void setPerson(String person) {
		this.person = person;
	}

	/**
	 * @return the reportType
	 */
	public String getReportType() {
		return reportType;
	}

	/**
	 * @param reportType the reportType to set
	 */
	public void setReportType(String reportType) {
		this.reportType = reportType;
	}

	/**
	 * @return the commissionType
	 */
	public String getCommissionType() {
		return commissionType;
	}

	/**
	 * @param commissionType the commissionType to set
	 */
	public void setCommissionType(String commissionType) {
		this.commissionType = commissionType;
	}

	/**
	 * @return the reportingYear
	 */
	public String getReportingYear() {
		return reportingYear;
	}

	/**
	 * @param reportingYear the reportingYear to set
	 */
	public void setReportingYear(String reportingYear) {
		this.reportingYear = reportingYear;
	}

	/**
	 * @return the filteredList
	 */
	public List<Map<String, Object>> getFilteredList() {
		return filteredList;
	}

	/**
	 * @param filteredList the filteredList to set
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
	 * @param managingDirector the managingDirector to set
	 */
	public void setManagingDirector(String managingDirector) {
		this.managingDirector = managingDirector;
	}

	/**
	 * @return the managingDirectorList
	 */
	public List<SelectItem> getManagingDirectorList() {
		if(managingDirectorList == null){
			Map<String, String> commPersonMap = new HashMap<String, String>();
			String userId = FacesUtils.getCurrentUserId();
			String type = "MD";
			managingDirectorList = new ArrayList<SelectItem>();
			commPersonMap = service.getCommPersonList(userId, type);
			Iterator<Map.Entry<String, String>> iterator = commPersonMap.entrySet().iterator();
			while(iterator.hasNext()){
				Map.Entry<String, String> obj = iterator.next();
				if(obj.getValue() == null){
					managingDirectorList.add(new SelectItem(obj.getKey(), ""));
				}else{
					managingDirectorList.add(new SelectItem(obj.getKey(), obj.getValue().toString()));
				}
			}
		}
		return managingDirectorList;
	}

	/**
	 * @param managingDirectorList the managingDirectorList to set
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
	 * @param vertical the vertical to set
	 */
	public void setVertical(String vertical) {
		this.vertical = vertical;
	}

	/**
	 * @return the verticalList
	 */
	public List<SelectItem> getVerticalList() {
		if (verticalList == null) {
			verticalList = new ArrayList<SelectItem>();
			verticalList.add(new SelectItem("Banking", "Banking"));
			verticalList.add(new SelectItem("Diversified", "Diversified"));
			verticalList.add(new SelectItem("Energy", "Energy"));
			verticalList.add(new SelectItem("Finance and Accounting",
					"Finance and Accounting"));
			verticalList.add(new SelectItem("Health", "Health"));
			verticalList.add(new SelectItem("Hi Tech", "Hi Tech"));
		}
		return verticalList;
	}

	/**
	 * @param verticalList the verticalList to set
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
}
