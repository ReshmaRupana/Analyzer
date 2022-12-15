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
import com.disys.analyzer.model.dto.EmployeeCategoryDTO;
import com.disys.analyzer.model.dto.OfficeDTO;
import com.disys.analyzer.model.dto.RecruitingClassificationDTO;
import com.disys.analyzer.model.dto.VerticalDTO;
import com.disys.analyzer.reports.util.ReportCriteria;
import com.disys.analyzer.reports.util.ReportMetaData;
import com.disys.analyzer.reports.util.ReportUtil;
import com.disys.analyzer.service.AnalyserCategory2Service;
import com.disys.analyzer.service.AnalyserClientService;
import com.disys.analyzer.service.AnalyserCommisionPersonService;
import com.disys.analyzer.service.AnalyserService;
import com.disys.analyzer.service.CompanyService;
import com.disys.analyzer.service.EmployeeCategoryService;
import com.disys.analyzer.service.OfficeService;
import com.disys.analyzer.service.RecruitingClassificationService;
import com.disys.analyzer.service.VerticalService;

/**
 * @author Sajid
 * @since Dec 18, 2017
 */

@ManagedBean
@ViewScoped
public class EndDateReportsBean extends SpringBeanAutowiringSupport implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5878004793171375024L;

	private Logger logger = LoggerFactory.getLogger(getClass());

	private String startDate;
	private String endDate;
	private String companyCode;

	private String managingDirector;
	private List<SelectItem> managingDirectorList;

	private String vertical;
	private List<SelectItem> verticalList;

	@Autowired
	private AnalyserCommisionPersonService service;

	private List<SelectItem> commissionPersons;

	private String ae;
	private String recruiter;
	private String client;

	private String employeeType;
	private String classification1;
	private String classification2;
	private String recruitingTeam;

	private List<SelectItem> classificationList1;
	private List<SelectItem> classificationList2;
	private List<SelectItem> recruitingTeamList;
	private List<SelectItem> employeeTypeList;

	private List<SelectItem> clients;

	private List<Map<String, Object>> reportData;
	private List<Map<String, Object>> filteredList;

	private final static String FILE_NAME = "EndDateReport";
	private final static String FILE_EXTENSION = ".xlsx";

	private String completeFilePath;
	private StreamedContent generatedExcelFile;
	private String newFileName;

	private List<SelectItem> branchesList;
	private String branch;
	
	private List<SelectItem> officeList;
	private List<SelectItem> analyserClientList;
	private List<SelectItem> employeeCategoryList;
	private List<SelectItem> analyserCategory2List;
	private List<SelectItem> recruitingClassificationList;
	private List<SelectItem> verticalLists;
	private List<SelectItem> mdCommisionPersonList;
	private List<SelectItem> aeCommisionPersonList;
	private List<SelectItem> recCommisionPersonList;
	private String userId=FacesUtils.getCurrentUserId();
	
	
	@Autowired
	CompanyService companyService;
	
	@Autowired
	OfficeService officeService;
	
	@Autowired
	AnalyserClientService analyserClientService;
	
	@Autowired
	EmployeeCategoryService employeeCategoryService;
	
	@Autowired
	AnalyserService analyserService;
	
	@Autowired
	AnalyserCategory2Service analyserCategory2Service;
	
	@Autowired
	RecruitingClassificationService recruitingClassificationService;
	
	@Autowired
	VerticalService verticalService;
	
	@Autowired
	Map<String, List<AnalyserCommisionPersonDTO>> commissionPersonDropDownBean;

	@PostConstruct
	public void init() {
		try {
			FacesContextUtils.getRequiredWebApplicationContext(FacesContext.getCurrentInstance())
					.getAutowireCapableBeanFactory().autowireBean(this);
		} catch (Exception ex) {
			logger.debug(ex.getMessage());
		}
	}

	public EndDateReportsBean() {
		this.companyCode = Constants.DEFAULT_COMPANY_CODE;
		logger.debug("Inside EndDateReportsBean");
	}

	public void eraseFilter() {
		this.reportData = null;
		this.companyCode = "DISYS";
		this.branch= "";
		this.startDate= "";
		this.endDate= "";
		this.managingDirector= "";
		this.ae = "";
		this.recruiter = "";
		this.client= "";

		this.employeeType= "";
		this.vertical= "";
		this.classification1= "";
		this.classification2= "";
		this.recruitingTeam= "";
	}

	public void exportToExcel() throws Exception {
		if (reportData == null || reportData.size() == 0) {
			FacesUtils.addGlobalMessage(FacesMessageSeverity.ERROR, "No data to export",
					"There is no data to export to excel!");
			throw new ReportDataNotFoundException("No data to export");
		}

		// {COMPANY=American Express via Tapfin, LASTNAME=Nayak,
		// FIRSTNAME=Lopamudra, TITLE=Java Developer, BILLRATE=80.0,
		// PROFIT=14.47, AE=Nicole Gerberding, RECRUITER=Alisa Zapalova,
		// MANAGINGDIRECTOR=Albert Barsoumian, ENDDATE=01/04/19, REASON=,
		// TERMINATEDATE=, SUBMITTEDON=Dec 4 2018 11:01AM, STATUS=Approved,
		// SORTORDER=0, EMPLOYEETYPE=IT,
		// VERTICAL=Banking, STARTDATE=06/25/2018, ANALYZERCATEGORY2=,
		// CITY=Scottsdale,
		// PAYRATE=54.00, TELEPHONE=(314)-359-7795, STATE=AZ, EMPTYPE=w2,
		// RECRUITINGCLASSIFICATION=Vertical, OFFICE=Phoenix}

		Map<String, String> columnNameMappings = new HashMap<String, String>();
		columnNameMappings.put("COMPANY", "Company");
		columnNameMappings.put("LASTNAME", "Last Name");
		columnNameMappings.put("FIRSTNAME", "First Name");
		columnNameMappings.put("TITLE", "Title");
		columnNameMappings.put("BILLRATE", "Bill Rate");
		columnNameMappings.put("PROFIT", "Profit");
		columnNameMappings.put("AE", "Account Executive");
		columnNameMappings.put("RECRUITER", "Recruiter");
		columnNameMappings.put("MANAGINGDIRECTOR", "Managing Director");
		columnNameMappings.put("CITY", "City");
		columnNameMappings.put("STATE", "State");
		columnNameMappings.put("EMPTYPE", "Employee Type");
		columnNameMappings.put("PAYRATE", "Pay Rate");
		columnNameMappings.put("TELEPHONE", "Telephone");
		columnNameMappings.put("EMPLOYEETYPE", "Employee Category");
		columnNameMappings.put("STARTDATE", "Start Date");
		columnNameMappings.put("ENDDATE", "End Date");
		columnNameMappings.put("SUBMITTEDON", "Submitted On");
		columnNameMappings.put("STATUS", "Status");
		columnNameMappings.put("VERTICAL", "Vertical");
		columnNameMappings.put("ANALYZERCATEGORY2", "Classification 2");
		columnNameMappings.put("RECRUITINGCLASSIFICATION", "Recruiting Team");
		columnNameMappings.put("OFFICE", "Office");
		columnNameMappings.put("COMPANYCODE", "Company Code");

		XSSFWorkbook workbook = new XSSFWorkbook();

		CellStyle cellStyle = workbook.createCellStyle();
		Font font = workbook.createFont();
		font.setBoldweight(Font.BOLDWEIGHT_BOLD);
		cellStyle.setFont(font);

		XSSFSheet sheet = workbook.createSheet("End Date Report");

		/*
		 * 
		 * To print column information from the result set
		 */
		int rowNum = 0;
		int colNum = 0;
		System.out.println("Creating excel");

		Row row = null;
		List<Map<String, Object>> reportDataCopy = reportData;
		List<Integer> skipColumnsFromPrint = new ArrayList<Integer>();
		int count = 0;
		Integer columnNumber = 0;
		for (Map<String, Object> map : reportDataCopy) {
			row = sheet.createRow(rowNum++);
			colNum = 0;
			Cell cell = null;
			Iterator<Map.Entry<String, Object>> iterator = map.entrySet().iterator();
			count++;
			if (count > 1) {
				break;
			}
			while (iterator.hasNext()) {
				columnNumber++;
				Map.Entry<String, Object> entry = iterator.next();
				try {
					String val = "";
					if (entry != null) {
						val = entry.toString();
						val = val.substring(0, val.indexOf('='));
					}
					if (!val.isEmpty() && columnNameMappings.containsKey(val)) {
						cell = row.createCell(colNum++);
						cell.setCellStyle(cellStyle);
						cell.setCellValue(columnNameMappings.get(val));
					} else {
						skipColumnsFromPrint.add(columnNumber);
					}
				} catch (Exception ex) {
					logger.debug(
							"Exception in " + logger.getName() + " while writing to the file for : " + entry.toString()
									+ " by user id : " + FacesUtils.getCurrentUserId() + ex.getLocalizedMessage());
					continue;
				}
			}
		}

		// header row is printed now, start from the second row
		rowNum = 1;
		for (Map<String, Object> map : reportDataCopy) {
			row = sheet.createRow(rowNum++);
			colNum = 0;
			Cell cell = null;
			Integer innerColumnNumber = 0;
			Iterator<Map.Entry<String, Object>> iterator = map.entrySet().iterator();
			while (iterator.hasNext()) {
				Map.Entry<String, Object> entry = iterator.next();
				try {
					innerColumnNumber++;
					// check if we want to show this column on excel file
					if (!skipColumnsFromPrint.contains(innerColumnNumber)) {
						cell = row.createCell(colNum++);
						// check if the value is a numeric or double
						if (FacesUtils.checkIfValueIsDouble(entry.getValue().toString())) {
							cell.setCellValue(Double.parseDouble(entry.getValue().toString()));
						} else {
							cell.setCellValue(entry.getValue().toString());
						}
					}
				} catch (Exception ex) {
					logger.debug(
							"Exception in " + logger.getName() + " while writing to the file for : " + entry.toString()
									+ " by user id : " + FacesUtils.getCurrentUserId() + ex.getLocalizedMessage());
					continue;
				}
			}
		}

		try {
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
		logger.debug("About to create Revenue by TERMINATION_REPORT");

		String reportName = "TERMINATION_REPORT";

		// {call wireless.spGetTerminationReport
		// ('[0]','[1]','[2]','[3]','[4]','[5]','[6]','[7]','[8]','[9]','[10]'))}
		// {call wireless.spGetTerminationReport
		// ('Gregory.Armstrong@DISYS.COM','ALL','All','ALL','ALL','ALL','12/01/2017','12/18/2017','ALL','ALL','ALL'))}

		// always get the userid for security reasons
		String reportParam = FacesUtils.getCurrentUserId();

		// reportParam = reportParam + "|" + startDate + "|" + endDate + "|" +
		// branch;

		reportParam = reportParam + "|" + managingDirector + "|" + employeeType + "|" + vertical + "|" + ae + "|"
				+ recruiter + "|" + startDate + "|" + endDate + "|" + client + "|" + classification2 + "|"
				+ recruitingTeam + "|" + branch + "|" + companyCode;
		
		System.out.println("End Date Report parameters are = "+reportParam);
		logger.debug("End Date Report parameters are :" +reportParam);

		ReportCriteria rc = null;
		reportData = new ArrayList<Map<String, Object>>();
		try {
			rc = new ReportCriteria(reportName, reportParam);
			reportData = rc.getReport();// get the data from the arraylis
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
	 * @return the startDate
	 */
	public String getStartDate() {
		return startDate;
	}

	/**
	 * @param startDate
	 *            the startDate to set
	 */
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	/**
	 * @return the endDate
	 */
	public String getEndDate() {
		return endDate;
	}

	/**
	 * @param endDate
	 *            the endDate to set
	 */
	public void setEndDate(String endDate) {
		this.endDate = endDate;
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
				// TODO Auto-generated catch block
				e.printStackTrace();
			} // application as servlet context
			DbWork db = new DbWork();
			String status = "1"; // client status 1=Active, 0=In-Active
			String[] param = new String[2];
			String queryString = null;
			try {
				queryString = (String) reportMetaData.getData("CLIENTLIST.SQL");
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			param[0] = FacesUtils.getCurrentUserId();
			param[1] = status;

			List<Map<String, Object>> dataType = null;
			try {
				dataType = db.getResult(queryString, param, "SP");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// To avoid Null Pointer Exception
			if (dataType == null) {
				dataType = new ArrayList<Map<String, Object>>();
			}

			for (int i = 0; i < dataType.size(); i++) {
				Map<String, Object> map = dataType.get(i);
				String id = (String) map.get("COMPANY");
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
	 * @return the ae
	 */
	public String getAe() {
		return ae;
	}

	/**
	 * @param ae
	 *            the ae to set
	 */
	public void setAe(String ae) {
		this.ae = ae;
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
	 * @return the classificationList1
	 */
	public List<SelectItem> getClassificationList1() {
		if (classificationList1 == null) {
			classificationList1 = new ArrayList<SelectItem>();
			classificationList1.add(new SelectItem("ndt", "NDT"));
			classificationList1.add(new SelectItem("idt", "IDT"));
			classificationList1.add(new SelectItem("dss", "DSS"));
			classificationList1.add(new SelectItem("rpo", "RPO"));
		}
		return classificationList1;
	}

	/**
	 * @param classificationList1
	 *            the classificationList1 to set
	 */
	public void setClassificationList1(List<SelectItem> classificationList1) {
		this.classificationList1 = classificationList1;
	}

	/**
	 * @return the classificationList2
	 */
	public List<SelectItem> getClassificationList2() {
		if (classificationList2 == null) {
			classificationList2 = new ArrayList<SelectItem>();
			classificationList2.add(new SelectItem("gsoc", "GSOC"));
		}
		return classificationList2;
	}

	/**
	 * @param classificationList2
	 *            the classificationList2 to set
	 */
	public void setClassificationList2(List<SelectItem> classificationList2) {
		this.classificationList2 = classificationList2;
	}

	/**
	 * @return the recruitingTeamList
	 */
	public List<SelectItem> getRecruitingTeamList() {
		if (recruitingTeamList == null) {
			recruitingTeamList = new ArrayList<SelectItem>();
			recruitingTeamList.add(new SelectItem("Vertical", "Vertical"));
			recruitingTeamList.add(new SelectItem("IRC", "IRC"));
		}
		return recruitingTeamList;
	}

	/**
	 * @param recruitingTeamList
	 *            the recruitingTeamList to set
	 */
	public void setRecruitingTeamList(List<SelectItem> recruitingTeamList) {
		this.recruitingTeamList = recruitingTeamList;
	}

	/**
	 * @return the employeeTypeList
	 */
	public List<SelectItem> getEmployeeTypeList() {
		if (employeeTypeList == null) {
			employeeTypeList = new ArrayList<SelectItem>();
			employeeTypeList.add(new SelectItem("IT", "IT"));
			employeeTypeList.add(new SelectItem("FA", "F&A"));
			employeeTypeList.add(new SelectItem("PT", "Part Time"));
			employeeTypeList.add(new SelectItem("RFT / Hourly", "RFT / Hourly"));
			employeeTypeList.add(new SelectItem("Projects", "Projects"));
			employeeTypeList.add(new SelectItem("IBT - Field Techs", "IBT - Field Techs"));
		}
		return employeeTypeList;
	}

	/**
	 * @param employeeTypeList
	 *            the employeeTypeList to set
	 */
	public void setEmployeeTypeList(List<SelectItem> employeeTypeList) {
		this.employeeTypeList = employeeTypeList;
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
					// managingDirectorList.add(new SelectItem(obj.getKey(),
					// ""));
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
		 */

		if (verticalList == null) {
			verticalList = new ArrayList<SelectItem>(10);
			verticalList.add(new SelectItem("AllWOFNA", "All without F&A"));
			List<String> list = analyserService.getVerticalsList();
			for (String ver : list) {
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
				System.out.println("Exception in EndDateReportsBean --> getOfficeByCompanyCodeList.");
				logger.debug("Exception in EndDateReportsBean --> getOfficeByCompanyCodeList.");
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
				System.out.println("Exception in EndDateReportsBean --> getAnalyserClientByCompanyCodeList.");
				logger.debug("Exception in EndDateReportsBean --> getAnalyserClientByCompanyCodeList.");
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
	
	public List<SelectItem> getEmployeeCategoryByCompanyCodeList(String companyCode) 
	{
		employeeCategoryList = new ArrayList<SelectItem>();
		if (!companyCode.equalsIgnoreCase(Constants.STRING_CONSTANT_ALL)) 
		{
			try
			{
				List<EmployeeCategoryDTO> employeeCategoryLists = employeeCategoryService.getEmployeeCategoryList(userId, companyCode);
				if (employeeCategoryLists != null)
				{
					for (EmployeeCategoryDTO c:employeeCategoryLists)
					{
						
						employeeCategoryList.add(new SelectItem(c.getEmployeeCategoryCode(), c.getEmployeeCategoryDescription()));
					}
				}
			}
			catch (Exception e)
			{
				System.out.println("Exception in EndDateReportsBean --> getEmployeeCategoryByCompanyCodeList.");
				logger.debug("Exception in EndDateReportsBean --> getEmployeeCategoryByCompanyCodeList.");
				e.printStackTrace();
			}
		}
		return employeeCategoryList;
	}

	public List<SelectItem> getEmployeeCategoryList() {
		return employeeCategoryList;
	}

	public void setEmployeeCategoryList(List<SelectItem> employeeCategoryList) {
		this.employeeCategoryList = employeeCategoryList;
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
				System.out.println("Exception in EndDateReportsBean --> getAnalyserCategory2ByCompanyCodeList.");
				logger.debug("Exception in EndDateReportsBean --> getAnalyserCategory2ByCompanyCodeList.");
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
				System.out.println("Exception in EndDateReportsBean --> getRecruitingClassificationsByCompanyCodeList.");
				logger.debug("Exception in EndDateReportsBean --> getRecruitingClassificationsByCompanyCodeList.");
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
	public List<SelectItem> getVerticalByCompanyCodeList(String companyCode) 
	{
		System.out.println("Vertical companyCode : " + companyCode);
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
					//FIXME Sajid list for only end data has this All without F&A
					if (companyCode.equalsIgnoreCase(Constants.DEFAULT_COMPANY_CODE))
					{
						verticalLists.add(0, new SelectItem("AllWOFNA", "All without F&A"));
					}
					for (VerticalDTO c:vertical)
					{						
						verticalLists.add(new SelectItem(c.getVerticalDescription(), c.getVerticalDescription()));
					}
				}
			}
			catch (Exception e)
			{
				System.out.println("Exception in EndDateReportsBean --> getVerticalByCompanyCodeList.");
				logger.debug("Exception in EndDateReportsBean --> getVerticalByCompanyCodeList.");
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
				System.out.println("Exception in EndDateReportsBean --> getMDCommissionPersonList.");
				logger.debug("Exception in EndDateReportsBean --> getMDCommissionPersonList.");
				e.printStackTrace();
			}
		}
		return mdCommisionPersonList;
	}
	
	public List<SelectItem> getAECommissionPersonList(String companyCode) 
	{
		aeCommisionPersonList = new ArrayList<SelectItem>();
		if (!companyCode.equalsIgnoreCase(Constants.STRING_CONSTANT_ALL)) 
		{
			try
			{
				List<AnalyserCommisionPersonDTO> commisionPersonLists = service.getAECommissionPersonList(userId, companyCode);
				if (commisionPersonLists != null)
				{
					for (AnalyserCommisionPersonDTO c:commisionPersonLists)
					{						
						aeCommisionPersonList.add(new SelectItem(c.getPersonName(), c.getPersonName()));
					}
				}
			}
			catch (Exception e)
			{
				System.out.println("Exception in EndDateReportsBean --> getAECommissionPersonList.");
				logger.debug("Exception in EndDateReportsBean --> getAECommissionPersonList.");
				e.printStackTrace();
			}
		}
		return aeCommisionPersonList;
	}

	public List<SelectItem> getRecruiterCommissionPersonList(String companyCode) 
	{
		recCommisionPersonList = new ArrayList<SelectItem>();
		if (!companyCode.equalsIgnoreCase(Constants.STRING_CONSTANT_ALL)) 
		{
			try
			{
				List<AnalyserCommisionPersonDTO> commisionPersonLists = service.getRecruiterCommissionPersonList(userId, companyCode);
				if (commisionPersonLists != null)
				{
					for (AnalyserCommisionPersonDTO c:commisionPersonLists)
					{						
						recCommisionPersonList.add(new SelectItem(c.getPersonName(), c.getPersonName()));
					}
				}
			}
			catch (Exception e)
			{
				System.out.println("Exception in EndDateReportsBean --> getRecruiterCommissionPersonList.");
				logger.debug("Exception in EndDateReportsBean --> getRecruiterCommissionPersonList.");
				e.printStackTrace();
			}
		}
		return recCommisionPersonList;
	}

	public List<SelectItem> getMdCommisionPersonList() {
		return mdCommisionPersonList;
	}

	public void setMdCommisionPersonList(List<SelectItem> mdCommisionPersonList) {
		this.mdCommisionPersonList = mdCommisionPersonList;
	}

	public List<SelectItem> getAeCommisionPersonList() {
		return aeCommisionPersonList;
	}

	public void setAeCommisionPersonList(List<SelectItem> aeCommisionPersonList) {
		this.aeCommisionPersonList = aeCommisionPersonList;
	}

	public List<SelectItem> getRecCommisionPersonList() {
		return recCommisionPersonList;
	}

	public void setRecCommisionPersonList(List<SelectItem> recCommisionPersonList) {
		this.recCommisionPersonList = recCommisionPersonList;
	}
	
	public void onCompanyCodeChange(javax.faces.event.AjaxBehaviorEvent event) {
//		HtmlSelectOneMenu selectedItem = (HtmlSelectOneMenu)event.getSource();
		
//		System.out.println(" Family : " + event.getComponent());
//		System.out.println(" HtmlSelectOneMenu : " + selectedItem.getValue().toString());
		/*
		this.branchesList = getBranchesList();
		this.branchesList.add(new SelectItem("DISYSXXX", "DISYSXXXXX"));
	*/}

	public String getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}
		
}