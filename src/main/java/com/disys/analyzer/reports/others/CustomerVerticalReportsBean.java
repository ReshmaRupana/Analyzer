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
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
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
public class CustomerVerticalReportsBean extends SpringBeanAutowiringSupport implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Logger logger = LoggerFactory.getLogger(getClass());

	private String vertical;
	private String product;

	private List<SelectItem> verticals;
	private List<SelectItem> products;

	private List<Map<String, Object>> reportData;
	private List<Map<String, Object>> filteredList;

	private final static String FILE_NAME = "CustomerVerticalReport";
	private final static String FILE_EXTENSION = ".xlsx";

	private String completeFilePath;
	private StreamedContent generatedExcelFile;
	private String newFileName;

	public CustomerVerticalReportsBean() {
		logger.debug("Inside CustomerVerticalReportsBean");
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

		header.add("Client ID");
		header.add("Company");
		header.add("PS Client ID");
		header.add("Vertical Code");
		header.add("Vertical Description");
		header.add("Product Code");
		header.add("Product Description");
		header.add("Billingtype Code");
		header.add("Billingtype Description");
		header.add("Servicearea Code");
		header.add("Servicearea Description");
		header.add("Salesforceclient Code");
		header.add("Salesforceclient Description");
		
//		CLIENT ID	COMPANY	PSCLIENTID	VERTICAL CODE	VERTICAL DESCRIPTION	PRODUCT CODE	PRODUCT DESCRIPTION	
//		BILLINGTYPE CODE	BILLINGTYPE DESCRIPTION	SERVICEAREA CODE	SERVICEAREA DESCRIPTION	
//		SALESFORCECLIENT CODE	SALESFORCECLIENT DESCRIPTION

		XSSFWorkbook workbook = new XSSFWorkbook();

		CellStyle cellStyle1 = workbook.createCellStyle();
		cellStyle1.setFillForegroundColor(IndexedColors.RED.index);

		XSSFSheet sheet = workbook.createSheet("Customer Vertical Report");

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
		logger.debug("About to create Revenue by CUSTOMER_VERTICAL_REPORT");

		String reportName = "CUSTOMER_VERTICAL_REPORT";

//		CUSTOMER_VERTICAL_REPORT.SQL={call "+ FacesUtils.SCHEMA_WIRELESS +".spGetCustomerVerticalreport('[0]','[1]','[2]','[3]','[4]')}
//		spGetCustomerVerticalreport('Gregory.Armstrong@DISYS.COM','Hi Tech','All','COMPANY','dummy')}

		
		String sortOrder = "COMPANY";
		String dummy = "dummy";
		// always get the userid for security reasons
		String reportParam = FacesUtils.getCurrentUserId();
		reportParam = reportParam + "|" + vertical + "|" + product + "|" + sortOrder + "|" + dummy;

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
	 * @return the verticals
	 */
	public List<SelectItem> getVerticals() {
		if (verticals == null) {
			verticals = new ArrayList<SelectItem>();
			verticals.add(new SelectItem("Banking", "Banking"));
			verticals.add(new SelectItem("Diversified", "Diversified"));
			verticals.add(new SelectItem("Energy", "Energy"));
			verticals.add(new SelectItem("Finance and Accounting",
					"Finance and Accounting"));
			verticals.add(new SelectItem("Health", "Health"));
			verticals.add(new SelectItem("Hi Tech", "Hi Tech"));
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
	 * @param vertical the vertical to set
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
	 * @param product the product to set
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
	 * @param filteredList the filteredList to set
	 */
	public void setFilteredList(List<Map<String, Object>> filteredList) {
		this.filteredList = filteredList;
	}
}