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
public class ConsultantsTotalHoursReportsBean extends
		SpringBeanAutowiringSupport implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Logger logger = LoggerFactory.getLogger(getClass());

	private String startDate;
	private String endDate;
	private String consultantName;

	
	private String managingDirector;
	private List<SelectItem> managingDirectorList;
	
	private String vertical;
	private List<SelectItem> verticalList;
	
	@Autowired
	private AnalyserCommisionPersonService service;
	
	private List<Map<String, Object>> reportData;
	private List<Map<String, Object>> filteredList;

	private final static String FILE_NAME = "ConsultantsTotalHoursReport";
	private final static String FILE_EXTENSION = ".xlsx";

	private String completeFilePath;
	private StreamedContent generatedExcelFile;
	private String newFileName;
	
	private String branch;
	private List<SelectItem> branchesList;
	
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

	public ConsultantsTotalHoursReportsBean() {
		logger.debug("Inside ConsultantsTotalHoursReportsBean");
		generateReport();
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

		header.add("Company");
		header.add("Last Name");
		header.add("First Name");
		header.add("Bill Rate");
		header.add("Profit");
		header.add("AE");
		header.add("Recruiter");
		header.add("Branch");
		header.add("Terminate Date");
		header.add("Submitted On");
		header.add("Start Date");
		header.add("Invoice Email");
		header.add("Sort Order");

		XSSFWorkbook workbook = new XSSFWorkbook();

		CellStyle cellStyle1 = workbook.createCellStyle();
		cellStyle1.setFillForegroundColor(IndexedColors.RED.index);

		XSSFSheet sheet = workbook.createSheet("Consultants Hours Report");

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
					cell.setCellValue(entry.getValue().toString());
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

			workbook.write(outputStream);
			workbook.close();

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
		logger.debug("About to create CONSULTANT_HOURS_REPORT");

		String reportName = "CONSULTANT_HOURS_REPORT";

		// CONSULTANT_HOURS_REPORT.SQL={call
		// "+ FacesUtils.SCHEMA_WIRELESS +".spGetConsultantHoursReport('[0]', '[1]', '[2]', '[3]', '[4]')}
		// spGetConsultantHoursReport('Gregory.Armstrong@DISYS.COM',
		// '11/01/2017', '12/18/2017', 'ALL', 'Ashley')}

		// String optionalParams = (String)request.getParameter("op");
		String optionalParams = null;

		if (optionalParams == null) {
			optionalParams = "";
		}

		String reportParam = FacesUtils.getCurrentUserId();
		reportParam = reportParam + "|" + startDate + "|" + endDate + "|"
				+ branch + "|" + consultantName;

		// call
		// wireless.spGetConsultantsBySubcontractorReport('Gregory.Armstrong@DISYS.COM')

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
	 * @return the consultantName
	 */
	public String getConsultantName() {
		return consultantName;
	}

	/**
	 * @param consultantName
	 *            the consultantName to set
	 */
	public void setConsultantName(String consultantName) {
		this.consultantName = consultantName;
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

}