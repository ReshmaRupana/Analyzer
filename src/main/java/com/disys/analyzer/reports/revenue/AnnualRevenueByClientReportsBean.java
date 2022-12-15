/**
 * 
 */
package com.disys.analyzer.reports.revenue;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

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
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.disys.analyzer.config.database.DbWork;
import com.disys.analyzer.config.util.FacesMessageSeverity;
import com.disys.analyzer.config.util.FacesUtils;
import com.disys.analyzer.config.util.WriteToFile;
import com.disys.analyzer.exception.ReportDataNotFoundException;
import com.disys.analyzer.reports.util.ReportCriteria;
import com.disys.analyzer.reports.util.ReportMetaData;

/**
 * @author Sajid
 * 
 */
@ManagedBean
@ViewScoped
public class AnnualRevenueByClientReportsBean  extends SpringBeanAutowiringSupport implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Logger logger = LoggerFactory.getLogger(getClass());

	private List<Map<String, Object>> reportData;
	private List<Map<String, Object>> filteredList;
	
	private String client;
	private List<SelectItem> clients;
	
	private String reportYear;

	private final static String FILE_NAME = "AnnualRevenueByClientReport";
	private final static String FILE_EXTENSION = ".xlsx";

	private String completeFilePath;

	private StreamedContent generatedExcelFile;
	
	private String newFileName;
	
	private List<SelectItem> yearsList;

	public AnnualRevenueByClientReportsBean() {
		logger.debug("Inside AnnualRevenueByClientReportsBean");
		
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

	public void exportToExcel() throws ReportDataNotFoundException{
		if (reportData == null || reportData.size() == 0) {
			FacesUtils
					.addGlobalMessage(FacesMessageSeverity.ERROR,
							"No data to export",
							"There is no data to export to excel!");
			throw new ReportDataNotFoundException("No data to export");
		}

		List<String> header = new ArrayList<String>();
		header.add("Branch");
		header.add("Total Revenue");
		header.add("Sort Order");
		header.add("Total Profit");
		header.add("Client Margin");
		header.add("Yearly Profit");
		header.add("Yearly Revenue");
		header.add("No of Consultants");
		
//		{BRANCH=, TOTALREVENUE=65.0, SORTORDER=0, TOTALPROFIT=-61.85, CLIENTMARGIN=-95.15, YEARLYPROFIT=-128648.0, YEARLYREVENUE=135200.0, TOTALCONSULTANTS=1}
		
//		BRANCH	NO OF CONSULTANTS	TOTAL REVENUE	TOTAL PROFIT	MARGIN

		// Recruiter Branch No of Consultants Total Billing Total Profit

		XSSFWorkbook workbook = new XSSFWorkbook();

		CellStyle cellStyle1 = workbook.createCellStyle();
		cellStyle1.setFillForegroundColor(IndexedColors.RED.index);

		XSSFSheet sheet = workbook.createSheet("REVENUE BY LOCATION");

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

			completeFilePath = realPath + FILE_NAME + "-" + dateTime + FILE_EXTENSION ;
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

			//fc.responseComplete();

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

	public void downloadExcelFile(String filePath){
		try{
			if(filePath == null || filePath.equals("")){
				filePath = newFileName;
			}
			//This points to the root.
	    	InputStream stream = FacesContext.getCurrentInstance().getExternalContext().getResourceAsStream("/"+filePath);
	    	generatedExcelFile = new DefaultStreamedContent(stream, "application/vnd.ms-excel", filePath);	
		}catch(Exception e){
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
		logger.debug("About to create Revenue by Location report");

		String reportName = "REVENUE_BY_LOCATION_REPORT";

//		String reportDescription = "REVENUE BY LOCATION";

//		String sortOrder = "";
		
		// String location; branch
		// String person = commissionPerson;
		// String empType = employeeType;

		// String optionalParams = (String)request.getParameter("op");
		String optionalParams = null;

		if (optionalParams == null) {
			optionalParams = "";
		}

		// always get the userid for security reasons

		String reportParam = FacesUtils.getCurrentUserId();

		// String excelType = request.getParameter("rx");
		
//		REVENUE_BY_LOCATION_REPORT.SQL={call dbo.spGetRevenueByLocationReport('[0]','[1]','[2]','[3]')}

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
	 * @param newFileName the newFileName to set
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
	 * @return the reportYear
	 */
	public String getReportYear() {
		return reportYear;
	}

	/**
	 * @param reportYear the reportYear to set
	 */
	public void setReportYear(String reportYear) {
		this.reportYear = reportYear;
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
