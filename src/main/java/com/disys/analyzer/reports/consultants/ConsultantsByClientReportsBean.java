/**
 * 
 */
package com.disys.analyzer.reports.consultants;

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
import com.disys.analyzer.config.util.FacesMessageSeverity;
import com.disys.analyzer.config.util.FacesUtils;
import com.disys.analyzer.config.util.WriteToFile;
import com.disys.analyzer.exception.ReportDataNotFoundException;
import com.disys.analyzer.reports.util.ReportCriteria;
import com.disys.analyzer.reports.util.ReportMetaData;
import com.disys.analyzer.reports.util.ReportUtil;
import com.disys.analyzer.service.AnalyserCommisionPersonService;

/**
 * @author Sajid
 * 
 */
@ManagedBean
@ViewScoped
public class ConsultantsByClientReportsBean extends SpringBeanAutowiringSupport implements Serializable{
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
	
	private String client;
	private List<SelectItem> clients;

	private List<Map<String, Object>> reportData;
	private List<Map<String, Object>> filteredList;

	private final static String FILE_NAME = "ConsultantsByClientReport";
	private final static String FILE_EXTENSION = ".xlsx";

	private String completeFilePath;
	private StreamedContent generatedExcelFile;
	private String newFileName;
	
	private String branch;
	private List<SelectItem> branchesList;
	private boolean userRoleAllowedDownloadAccess;
	
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
	
	public ConsultantsByClientReportsBean() {
		logger.debug("Inside ConsultantsByClientReportsBean");		
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

	public void exportToExcel() throws Exception{
		if (reportData == null || reportData.size() == 0) {
			FacesUtils
					.addGlobalMessage(FacesMessageSeverity.ERROR,
							"No data to export",
							"There is no data to export to excel!");
			throw new ReportDataNotFoundException("No data to export");
		}

		//These are the column headers from the database with their actual name mappings to show on Excel file.
		Map<String, String> columnNameMappings = new HashMap<String, String>();
		columnNameMappings.put("BRANCH","Branch");
		columnNameMappings.put("CELLPHONE","Cell Phone");
		columnNameMappings.put("PAYRATE","Pay Rate");
		columnNameMappings.put("WORKEMAIL","Work Email");
		columnNameMappings.put("EMPTYPE","Employee Type");
		columnNameMappings.put("AE","AE");
		columnNameMappings.put("CL","Liaison");
		columnNameMappings.put("EMAIL","Email");
		columnNameMappings.put("HOMEPHONE","Home Phone");
		columnNameMappings.put("COMPANY","Company");
		columnNameMappings.put("LASTNAME","Last Name");
		columnNameMappings.put("FIRSTNAME","First Name");
		columnNameMappings.put("ENDDATE","End Date");
		columnNameMappings.put("RECRUITER","Recruiter");
		columnNameMappings.put("OTRATE","OT Rate");
		columnNameMappings.put("SUBCOMPANY","Sub Company");
		columnNameMappings.put("ADDRESS","Address");
		columnNameMappings.put("BILLRATE","Bill Rate");
		columnNameMappings.put("STARTDATEFORMATTED","Start Date");
		columnNameMappings.put("PERDIEM","Perdiem");
		columnNameMappings.put("WORKPHONE","Work Phone");
		columnNameMappings.put("SUBEMAIL","Subcontractor Email");
		columnNameMappings.put("COMPANYCODE","Company Code");

		/*BRANCH=Arizona, CELLPHONE=, PAYRATE=35.0, WORKEMAIL=, EMPTYPE=w2, AE=Abhinaya Anand, 
				STARTDATE=2017-11-28 00:00:00.0, CL=Ashley Chmielowski, FIRSTNAME=Jones, EMAIL=adam@taes.com, 
				HOMEPHONE=, COMPANY=ABB - PM Services Project, ENDDATE=01/19/2018, RECRUITER=, OTRATE=35, 
				SORTORDER=0, LASTNAME=Adam, SUBCOMPANY=null, ADDRESS= , ,  , BILLRATE=80.0, 
				STARTDATEFORMATTED=Nov 28, 2017, PERDIEM=0.0, WORKPHONE=, SUBEMAIL=null*/

		// Recruiter Branch No of Consultants Total Billing Total Profit

		XSSFWorkbook workbook = new XSSFWorkbook();

		XSSFSheet sheet = workbook.createSheet("Consultants by Client Report");
		CellStyle cellStyle = workbook.createCellStyle();
		Font font = workbook.createFont();
	    font.setBoldweight(Font.BOLDWEIGHT_BOLD);

		int rowNum = 0;
		int colNum = 0;
		System.out.println("Creating excel");

		Row row = null;
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
					if(!val.isEmpty() && columnNameMappings.containsKey(val)){
						cellStyle.setFont(font);
					    cell.setCellStyle(cellStyle);
						cell.setCellValue(columnNameMappings.get(val));
					}
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
				try {
					if(entry.getKey().equals("SORTORDER")){
						continue;
					}
					cell = row.createCell(colNum++);
					//check if the value is a numeric or double
					if(FacesUtils.checkIfValueIsDouble(entry.getValue().toString())){
						cell.setCellValue(Double.parseDouble(entry.getValue().toString()));
					}else{
						cell.setCellValue(entry.getValue().toString());
					}
				} catch (Exception ex) {
					logger.debug("Exception in "+logger.getName()+" while writing to the file for : "+entry.toString() +" by user id : "+FacesUtils.getCurrentUserId()
							+ ex.getLocalizedMessage());
					continue;
				}

				// cell.setCellStyle(cellStyle1);
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

			/*FacesContext fc = FacesContext.getCurrentInstance();

			HttpServletResponse response = (HttpServletResponse) fc
					.getExternalContext().getResponse();

			response.reset();

			response.setContentType("application/vnd.ms-excel");

			response.setHeader("Content-Disposition", "attachment; filename="
					+ file.getName());*/

			workbook.write(outputStream);
			workbook.close();

			// fc.responseComplete();

			/*HttpServletRequest request = (HttpServletRequest) FacesContext
					.getCurrentInstance().getExternalContext().getRequest();

			String location = request.getRequestURL().toString();
			// if your webapp name is Analyzer
			location = location.substring(0, location.indexOf("/Analyzer"));

			fc.getExternalContext().redirect(location);*/

			outputStream.flush();
			outputStream.close();
			// generateReportFile(file);

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

		String reportName = "CONSULTANTS_BY_CLIENT_REPORT";

		// CONSULTANTS_BY_CLIENT_REPORT.SQL={call
		// "+ FacesUtils.SCHEMA_WIRELESS +".spGetConsultantsByClientReport('[0]','[1]','[2]','[3]')}

		String optionalParams = null;

		if (optionalParams == null) {
			optionalParams = "";
		}

		// always get the userid for security reasons
		String sortOrder = "COMPANY";

		String reportParam = FacesUtils.getCurrentUserId();

		reportParam = reportParam + "|" + branch + "|" + client + "|"
				+ sortOrder;

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
}