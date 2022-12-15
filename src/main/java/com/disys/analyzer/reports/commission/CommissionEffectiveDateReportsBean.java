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
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
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
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.disys.analyzer.config.util.FacesMessageSeverity;
import com.disys.analyzer.config.util.FacesUtils;
import com.disys.analyzer.config.util.WriteToFile;
import com.disys.analyzer.exception.ReportDataNotFoundException;
import com.disys.analyzer.reports.util.ReportCriteria;

/**
 * @author Sajid
 * 
 */
@ManagedBean
@ViewScoped
public class CommissionEffectiveDateReportsBean extends SpringBeanAutowiringSupport  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Logger logger = LoggerFactory.getLogger(getClass());

	private String name;
	private String parentId;
	

	private List<Map<String, Object>> reportData;
	private List<Map<String, Object>> filteredList;

	private final static String FILE_NAME = "CommissionEffectiveDateDetailReport";
	private final static String FILE_EXTENSION = ".xlsx";

	private String completeFilePath;
	private StreamedContent generatedExcelFile;
	private String newFileName;

	public CommissionEffectiveDateReportsBean() {
		logger.debug("Inside CommissionEffectiveDateReportsBean");
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
				try {
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
		logger.debug("About to create COMMISSION_DETAIL_REPORT");

		String reportName = "COMMISSION_DETAIL_REPORT";
		
//		COMMISSION_DETAIL_REPORT.SQL={call dbo.spGetCommissionHistory('[0]','[1]', '[2]')}
//		17/12/18 11:01:12 Sql in DBWork is :{call wireless.spGetCommissionHistory('Gregory.Armstrong@DISYS.COM','Sajid', '216784')}

		String optionalParams = null;

		if (optionalParams == null) {
			optionalParams = "";
		}

		String reportParam = FacesUtils.getCurrentUserId();
		
		reportParam = reportParam + "|" + name + "|" + parentId;

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
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the parentId
	 */
	public String getParentId() {
		return parentId;
	}

	/**
	 * @param parentId the parentId to set
	 */
	public void setParentId(String parentId) {
		this.parentId = parentId;
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
	
}
