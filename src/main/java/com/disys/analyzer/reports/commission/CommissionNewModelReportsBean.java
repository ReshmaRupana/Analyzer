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
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpSession;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
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
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.disys.analyzer.config.util.FacesMessageSeverity;
import com.disys.analyzer.config.util.FacesUtils;
import com.disys.analyzer.config.util.WriteToFile;
import com.disys.analyzer.reports.util.CommissionDetails;
import com.disys.analyzer.reports.util.CommissionStatementSetupData;
import com.disys.analyzer.reports.util.CommissionUtil;
import com.disys.analyzer.reports.util.PeopleSoftJournalData;
import com.disys.analyzer.reports.util.ReportUtil;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfGState;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;

/**
 * @author Sajid
 * 
 */

@ManagedBean
@ViewScoped
public class CommissionNewModelReportsBean extends SpringBeanAutowiringSupport implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Logger logger = LoggerFactory.getLogger(getClass());

	private String month;
	private List<SelectItem> monthsList;

	private String person;
	private List<SelectItem> commissionPersons;
	private String reportingYear;
	private List<SelectItem> yearsList;

	private final static String MASTER_FILE_NAME = "CommissionDetailsReport";
	private final static String CHILD_FILE_NAME = "CommissionProjectGLDetailsReport";
	private final static String FILE_EXTENSION = ".xlsx";

	private String completeFilePath;
	private StreamedContent generatedExcelFile;
	private String newFileName;

	private String completeChildFilePath;
	private StreamedContent generatedChildExcelFile;
	private String newChildFileName;

	private final static String COMMISSION_STATEMENT_FILE_NAME = "CommissionStatement";
	private final static String PDF_FILE_EXTENSION = ".pdf";

	private String completeStatementFilePath;
	private StreamedContent generatedPdfFile;
	private String newPdfFileName;

	private String period;
	private String currentCommission;
	private String ytdCommission;
	private String commissionPersonName;
	private String commissionPersonId;

	private CommissionStatementSetupData commissionPersonInfo;
	private List<CommissionDetails> commissionDetails;
	private List<CommissionDetails> filteredList;

	private CommissionDetails selectedCommissionDetail;

	private String projectId;

	private List<PeopleSoftJournalData> journalData;
	private List<PeopleSoftJournalData> journalDataFilteredList;

	Boolean showCharts = false;

	private Font calibriFont;
	private Font calibriFontBody;
	private BaseFont watermarkBaseFont;
	private BaseFont calibriBaseFont;
	private float greyFill;

	private int type1CommissionRecordsCount;
	private int type2CommissionRecordsCount;
	private int type3CommissionRecordsCount;
	
	private boolean reportCreated;

	public CommissionNewModelReportsBean() {
		logger.debug("Inside CommissionNewModelReportsBean");
		System.out.println("Inside CommissionNewModelReportsBean");

		Calendar c = Calendar.getInstance();
		int year = c.get(Calendar.YEAR);
		yearsList = new ArrayList<SelectItem>();
		yearsList.add(new SelectItem(year, "" + year));
		yearsList.add(new SelectItem(year - 1, "" + (year - 1)));
		yearsList.add(new SelectItem(year - 2, "" + (year - 2)));
		
		String fontPath = "c:/windows/fonts/calibri.ttf";
		FontFactory.register(fontPath, "calibriFont");
		calibriFont = FontFactory.getFont("calibriFont");
		logger.debug("calibriFont = "+calibriFont);
		System.out.println("calibriFont = "+calibriFont);
		calibriFontBody = FontFactory.getFont("calibriFont");
		logger.debug("calibriFontBody = "+calibriFontBody);
		System.out.println("calibriFontBody = "+calibriFontBody);
		calibriBaseFont = calibriFontBody.getBaseFont();
		logger.debug("calibriBaseFont = "+calibriBaseFont);
		System.out.println("calibriBaseFont = "+calibriBaseFont);
		reportCreated = false;
	}

	public String generateCommissionStatementFileName() {
		String outputFile;
		String currentDate;
		Calendar cal = Calendar.getInstance();
		String temp;

		logger.debug("Inside generateCommissionStatementFileName.");
		System.out.println("Inside generateCommissionStatementFileName.");
		
		outputFile = Integer.toString(cal.get(Calendar.YEAR));
		currentDate = Integer.toString(cal.get(Calendar.YEAR));
		if (cal.get(Calendar.MONTH) <= 8) {
			outputFile += "0" + Integer.toString(cal.get(Calendar.MONTH) + 1);
			temp = "0" + Integer.toString(cal.get(Calendar.MONTH) + 1);
		} else {
			outputFile += Integer.toString(cal.get(Calendar.MONTH) + 1);
			temp = Integer.toString(cal.get(Calendar.MONTH) + 1);
		}

		if (cal.get(Calendar.DAY_OF_MONTH) <= 9) {
			outputFile += "0"
					+ Integer.toString(cal.get(Calendar.DAY_OF_MONTH)) + "-";
			currentDate = "0"
					+ Integer.toString(cal.get(Calendar.DAY_OF_MONTH)) + "/"
					+ currentDate;
		} else {
			outputFile += Integer.toString(cal.get(Calendar.DAY_OF_MONTH))
					+ "-";
			currentDate = Integer.toString(cal.get(Calendar.DAY_OF_MONTH))
					+ "/" + currentDate;
		}

		outputFile += "-" + this.getCommissionPersonName().toLowerCase() + "-"
				+ System.currentTimeMillis() + ".pdf";

		// remove spaces if any
		outputFile = outputFile.replaceAll(" ", "");
		currentDate = temp + "/" + currentDate;

		logger.debug("currentDate = "+currentDate);
		System.out.println("currentDate = " + currentDate);
		logger.debug("outputFile = "+outputFile);
		System.out.println("outputFile = " + outputFile);
		return outputFile;
	}

	public void downloadPdfStatement(String filePath) {
		
		logger.debug("Inside downloadPdfStatement.");
		System.out.println("Inside downloadPdfStatement.");
		logger.debug("filePath = "+filePath);
		System.out.println("filePath = " + filePath);
		
		if(!reportCreated){
			FacesUtils
			.addGlobalMessage(FacesMessageSeverity.ERROR,
					"No data to export",
					"There is no data to download!");
			return;
		}
		try {
			if (filePath == null || filePath.equals("")) {
				filePath = this.getNewPdfFileName();
				logger.debug("filePath = "+filePath);
				System.out.println("filePath = " + filePath);
			}
			// This points to the root.
			InputStream stream = FacesContext.getCurrentInstance()
					.getExternalContext().getResourceAsStream("/" + filePath);
			generatedPdfFile = new DefaultStreamedContent(stream,
					"application/pdf", filePath);
			
			logger.debug("generatedPdfFile = "+generatedPdfFile);
			System.out.println("generatedPdfFile = " + generatedPdfFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void createPdfStatement() {
		
		logger.debug("Inside createPdfStatement.");
		System.out.println("Inside createPdfStatement.");
		
		if(!reportCreated){
			FacesUtils
			.addGlobalMessage(FacesMessageSeverity.ERROR,
					"No data to export",
					"There is no data to export to PDF!");
			return;
		}
		Document document = new Document();
		PdfWriter writer;
		Paragraph pg;
		PdfPTable table;
		PdfPCell cell;
		CommissionStatementSetupData ssd = commissionPersonInfo;

		try {
			logger.debug("Generating Pdf.");
			System.out.println("Generating Pdf.");

			/*statementName = generateCommissionStatementFileName();
			pdfStatementUrl = this.statementHostUrl+statementName;
			calibriFont.setSize(18);
			calibriFont.setStyle(Font.BOLD+Font.UNDERLINE);
			*/
			document.setPageSize(PageSize.LETTER.rotate());
			// document.setMargins(36, 72, 108, 144);
			// writer.setPdfVersion(PdfWriter.VERSION_1_6);

			String realPath = FacesUtils.getTempFilePath();

			Long dateTime = System.currentTimeMillis();

			// D:\Softwares\Apache\tomcat-8.0.23\webapps\Analyzer\
			
			String newFileName = COMMISSION_STATEMENT_FILE_NAME + "-"
					+ dateTime + PDF_FILE_EXTENSION;

			completeFilePath = realPath + newFileName;
			this.setCompleteStatementFilePath(completeFilePath);
			this.setNewPdfFileName(newFileName);
			
			logger.debug("newFileName = "+newFileName);
			System.out.println("newFileName = " + newFileName);
			logger.debug("File for downloading is : " + completeFilePath);
			System.out.println("File for downloading is : " + completeFilePath);

			WriteToFile objWriteToFile = new WriteToFile();
			File file = objWriteToFile.createFile(completeFilePath);

			FileOutputStream outputStream = new FileOutputStream(file);

			writer = PdfWriter.getInstance(document, outputStream);
			writer.setPageEvent(new PdfStatementFooter());
			document.open();

			pg = new Paragraph(ssd.getCompanyName(), calibriFont);
			pg.setAlignment(Element.ALIGN_CENTER);
			pg.setSpacingAfter(10);
			document.add(pg);
			// document.add(Chunk.NEWLINE);

			calibriFont.setSize(14);
			calibriFont.setStyle(Font.BOLD);
			pg = new Paragraph(ssd.getReportHeader(), calibriFont);
			pg.setAlignment(Element.ALIGN_LEFT);
			pg.setSpacingAfter(5);
			document.add(pg);

			calibriFont.setSize(10);
			calibriFont.setStyle(Font.BOLD);

			calibriFontBody.setSize(10);

			// Commission Header Table
			table = new PdfPTable(5); // 5 columns

			table.setHorizontalAlignment(Element.ALIGN_LEFT); // By default
																// Center
			table.setWidthPercentage(90); // as By default table has only 80%
											// space available

			cell = new PdfPCell(new Paragraph(
					ssd.getCommissionPersonEmplIdLabel(), calibriFont));
			table.addCell(cell);
			cell = new PdfPCell(new Paragraph(ssd.getCommissionPersonEmplId(),
					calibriFontBody));
			table.addCell(cell);

			cell = new PdfPCell(new Paragraph(ssd.getCommissionPeriodLabel(),
					calibriFont));
			table.addCell(cell);
			cell = new PdfPCell(new Paragraph(ssd.getCommissionPeriod(),
					calibriFontBody));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(cell);

			cell = new PdfPCell(new Paragraph(ssd.getYtdCommissionLabel(),
					calibriFont));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(cell);

			// Next Row
			cell = new PdfPCell(new Paragraph(
					ssd.getCommissionPersonNameLabel(), calibriFont));
			table.addCell(cell);
			cell = new PdfPCell(new Paragraph(ssd.getCommissionPersonName(),
					calibriFontBody));
			table.addCell(cell);

			cell = new PdfPCell(new Paragraph(ssd.getCurrentCommissionLabel(),
					calibriFont));
			table.addCell(cell);
			cell = new PdfPCell(new Paragraph(NumberFormat.getCurrencyInstance(
					Locale.US).format(ssd.getCurrentCommission()),
					calibriFontBody));
			cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			table.addCell(cell);

			cell = new PdfPCell(new Paragraph(NumberFormat.getCurrencyInstance(
					Locale.US).format(ssd.getYtdCommission()), calibriFontBody));
			cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			table.addCell(cell);

			table.setSpacingAfter(10);
			document.add(table); // Adding Header Table

			// Commission Summary Table
			pg = new Paragraph(ssd.getSubHeader1(), calibriFont);
			pg.setAlignment(Element.ALIGN_LEFT);
			pg.setSpacingAfter(5);
			document.add(pg);

			table = new PdfPTable(4); // 4 columns
			float[] widths = { 2f, 1f, 1f, 1f };
			table.setHorizontalAlignment(Element.ALIGN_LEFT); // By default
																// Center
			table.setWidthPercentage(60); // as By default table has only 80%
											// space available
			table.setWidths(widths);

			// Header Row
			cell = new PdfPCell(new Paragraph(ssd.getHeaderTableColumn1Label(),
					calibriFont));
			cell.setGrayFill(greyFill);
			table.addCell(cell);
			cell = new PdfPCell(new Paragraph(ssd.getHeaderTableColumn2Label(),
					calibriFont));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setGrayFill(greyFill);
			table.addCell(cell);
			cell = new PdfPCell(new Paragraph(ssd.getHeaderTableColumn3Label(),
					calibriFont));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setGrayFill(greyFill);
			table.addCell(cell);
			cell = new PdfPCell(new Paragraph(ssd.getHeaderTableColumn4Label(),
					calibriFont));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setGrayFill(greyFill);
			table.addCell(cell);

			// Type-1 & 2 (Staffing and Managed Staffing Commissions)
			cell = new PdfPCell(new Paragraph(
					ssd.getStaffingAndSolutionsCommissionLabel(),
					calibriFontBody));
			table.addCell(cell);
			cell = new PdfPCell(new Paragraph(NumberFormat.getCurrencyInstance(
					Locale.US).format(
					ssd.getStaffingAndSolutionsCommissionYtd()),
					calibriFontBody));
			cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			table.addCell(cell);
			cell = new PdfPCell(new Paragraph(NumberFormat.getCurrencyInstance(
					Locale.US).format(
					ssd.getStaffingAndSolutionsCommissionBalance()),
					calibriFontBody));
			cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			table.addCell(cell);
			cell = new PdfPCell(new Paragraph(NumberFormat.getCurrencyInstance(
					Locale.US).format(
					ssd.getStaffingAndSolutionsCommissionCurrent()),
					calibriFontBody));
			cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			table.addCell(cell);

			// Type-3 (Managed Services Commission)
			cell = new PdfPCell(new Paragraph(ssd.getServicesCommissionLabel(),
					calibriFontBody));
			table.addCell(cell);
			cell = new PdfPCell(new Paragraph(NumberFormat.getCurrencyInstance(
					Locale.US).format(ssd.getServicesCommissionYtd()),
					calibriFontBody));
			cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			table.addCell(cell);
			cell = new PdfPCell(new Paragraph(NumberFormat.getCurrencyInstance(
					Locale.US).format(ssd.getServicesCommissionBalance()),
					calibriFontBody));
			cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			table.addCell(cell);
			cell = new PdfPCell(new Paragraph(NumberFormat.getCurrencyInstance(
					Locale.US).format(ssd.getServicesCommissionCurrent()),
					calibriFontBody));
			cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			table.addCell(cell);

			// Total Earned Commission
			cell = new PdfPCell(new Paragraph(ssd.getTotalCommissionLabel(),
					calibriFontBody));
			table.addCell(cell);
			cell = new PdfPCell(new Paragraph(NumberFormat.getCurrencyInstance(
					Locale.US).format(ssd.getTotalCommissionYtd()),
					calibriFontBody));
			cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			table.addCell(cell);
			cell = new PdfPCell(new Paragraph(NumberFormat.getCurrencyInstance(
					Locale.US).format(ssd.getTotalCommissionBalance()),
					calibriFontBody));
			cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			table.addCell(cell);
			cell = new PdfPCell(new Paragraph(NumberFormat.getCurrencyInstance(
					Locale.US).format(ssd.getTotalCommissionCurrent()),
					calibriFontBody));
			cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			table.addCell(cell);

			table.setSpacingAfter(10);
			document.add(table); // Ading Summary Table

			// Staffing = Type-1 Commission Table
			pg = new Paragraph(ssd.getSubHeader2(), calibriFont);
			pg.setAlignment(Element.ALIGN_LEFT);
			pg.setSpacingAfter(5);
			document.add(pg);

			calibriFont.setSize(8);
			calibriFont.setStyle(Font.BOLD);
			calibriFontBody.setSize(8);
			// 07/29/2016
			// document.add(getCommissionTable(1, type1CommissionRecordsCount,
			// getCommissionDetailsRecordsCount())); //1 for Staffing
			if (isHybridCommissionCalculationModel()) {
				try{
					PdfPTable table1 = getCommissionTable(4, type1CommissionRecordsCount,getCommissionDetailsRecordsCount());
					System.out.println("Table created..."+table1);
					document.add(table1); // 4 for Staffing Estimation based with Type Id 4
				}catch(Exception ex){
					System.err.println("Exception : "+ex.getMessage());
				}
				
			} else {
				PdfPTable table1 = getCommissionTable(1, type1CommissionRecordsCount,getCommissionDetailsRecordsCount());
				System.out.println("Table1 created..."+table1);
				document.add(table1); // 1 for Staffing Actuals based with Type Id 1
			}
			calibriFontBody.setSize(6);
			pg = new Paragraph(ssd.getLegendLabel(), calibriFontBody);
			pg.setAlignment(Element.ALIGN_LEFT);
			pg.setSpacingAfter(10);
			document.add(pg);

			// Managed Staffing Type-2 Commission Table
			pg = new Paragraph(ssd.getSubHeader3(), calibriFont);
			pg.setAlignment(Element.ALIGN_LEFT);
			pg.setSpacingAfter(5);
			document.add(pg);

			calibriFont.setSize(8);
			calibriFont.setStyle(Font.BOLD);
			calibriFontBody.setSize(8);
			document.add(getCommissionTable(2, type2CommissionRecordsCount,
					getCommissionDetailsRecordsCount())); // 2 for type-2
															// managed staffing

			calibriFontBody.setSize(6);
			pg = new Paragraph(ssd.getLegendLabel(), calibriFontBody);
			pg.setAlignment(Element.ALIGN_LEFT);
			pg.setSpacingAfter(10);
			document.add(pg);

			// Managed Services Type-3 Commission Table
			pg = new Paragraph(ssd.getSubHeader4(), calibriFont);
			pg.setAlignment(Element.ALIGN_LEFT);
			pg.setSpacingAfter(5);
			document.add(pg);

			calibriFont.setSize(8);
			calibriFont.setStyle(Font.BOLD);
			calibriFontBody.setSize(8);
			document.add(getCommissionTable(3, type3CommissionRecordsCount,
					getCommissionDetailsRecordsCount())); // 2 for type-3
															// managed services

			calibriFontBody.setSize(6);
			pg = new Paragraph(ssd.getLegendLabel(), calibriFontBody);
			pg.setAlignment(Element.ALIGN_LEFT);
			pg.setSpacingAfter(10);
			document.add(pg);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}catch (Exception e) {
			System.out.println("Exception in createPdfStatement = "
					+ e.toString());
			e.printStackTrace();
		}
		document.close();
	}

	// 07/29/2016
	// Added New
	public boolean isHybridCommissionCalculationModel() {
		if (Integer.valueOf(reportingYear) > 2016) {
			return true;
		} else if (Integer.valueOf(reportingYear) == 2016) {
			if (Integer.valueOf(month) >= 6) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public int getCommissionDetailsRecordsCount() {
		if (commissionDetails == null) {
			return 0;
		} else {
			return commissionDetails.size();
		}
	}

	public void eraseFilter() {
		this.commissionDetails = null;
		showCharts = false;
		reportCreated = false;
	}

	public void exportMasterFileToExcel() {
		
		logger.debug("Inside exportMasterFileToExcel.");
		System.out.println("Inside exportMasterFileToExcel.");
		
		if (commissionDetails == null || commissionDetails.size() == 0) {
			FacesUtils
					.addGlobalMessage(FacesMessageSeverity.ERROR,
							"No data to export",
							"There is no data to export to excel!");
			return;
		}

		List<String> header = new ArrayList<String>();
		header.add("Project ID");
		header.add("EMPLID");
		header.add("Consultant Name");
		header.add("Hours");
		header.add("Comm. Rate");
		header.add("ProRated Commission $");
		header.add("Calculation Model");
		header.add("Commission Model");
		header.add("Revenue");
		header.add("Cost");
		header.add("Commission Revenue");
		header.add("Commission Cost");
		header.add("Discount Rate %");
		header.add("Discount Amount");
		header.add("G&A Rate %");
		header.add("G&A Amount $");
		header.add("Business Type");
		header.add("Flat Fee ?");
		header.add("Gross Profit $");
		header.add("Analyzer GP%");
		header.add("Actual GP%");
		header.add("Comm Profit");
		header.add("Role Name");
		header.add("Comm Rate %");
		header.add("Prorate Factor");
		header.add("Customer Id");
		header.add("Customer Name");
		header.add("For Quota only?");
		header.add("Orphan?");
		header.add("CCCF");
		header.add("CCAF");
		header.add("Master Project ID");
		header.add("Master Project Revenue");
		header.add("Master Project Cost");
		header.add("All Projects Revenue");
		header.add("All Projects Cost");
		header.add("Expense Revenue");
		header.add("Count Of Child Projects");
		header.add("Category Code");
		header.add("Employee Type");
		header.add("Job Title");
		header.add("Project GL Location");
		header.add("Estimated Commission Percentage %");
		header.add("Commission Person JobCode");
		header.add("Flat Fee Commission $");
		header.add("Project GP For Quota Attainment");
		header.add("Commission Payment%");
		header.add("Accelerator Commission $");
		header.add("Accelerator Commission %");
		header.add("BillRate");
		header.add("PayRate");

		/*Type
		Account
		Monetary Amount
		Trans Ref #
		Line Description
		Resource Type
		Resource Category
		Analysis Type
		Voucher Id
		Line Source
		Journal Description*/

		/*systemSource
		account
		monetaryAmount
		transactionRefNumber
		lineDescription
		resourceType
		resourceCategory
		analysisType
		journalLineRef_VoucherId
		journalLineSource
		journalDescription*/

		XSSFWorkbook workbook = new XSSFWorkbook();

		CellStyle cellStyle1 = workbook.createCellStyle();
		cellStyle1.setFillForegroundColor(IndexedColors.RED.index);

		XSSFSheet sheet = workbook.createSheet("Commission Details");

		int rowNum = 0;
		int colNum = 0;
		
		logger.debug("Creating excel.");
		System.out.println("Creating excel");

		Row row = sheet.createRow(rowNum++);
		// create excel file header
		for (String cellHeader : header) {
			Cell cell = row.createCell(colNum++);
			cell.setCellValue(cellHeader);
		}

		colNum = 0;
		Cell cell = null;

		for (CommissionDetails comm : commissionDetails) {
			row = sheet.createRow(rowNum++);
			try {
				cell = row.createCell(colNum++);
				cell.setCellValue(comm.getProjectId());

				cell = row.createCell(colNum++);
				cell.setCellValue(comm.getEmplId());

				cell = row.createCell(colNum++);
				cell.setCellValue(comm.getEmployeeName());

				cell = row.createCell(colNum++);
				cell.setCellValue(comm.getHours());

				cell = row.createCell(colNum++);
				cell.setCellValue(comm.getEstimatedCommissionRate());

				cell = row.createCell(colNum++);
				cell.setCellValue(comm.getProRatedCommissionAmount());

				cell = row.createCell(colNum++);
				cell.setCellValue(comm.getCommissionCalculationModel());

				cell = row.createCell(colNum++);
				cell.setCellValue(comm.getAnalyzerCommissionModelCode());

				cell = row.createCell(colNum++);
				cell.setCellValue(comm.getRevenue());

				cell = row.createCell(colNum++);
				cell.setCellValue(comm.getCost());

				cell = row.createCell(colNum++);
				cell.setCellValue(comm.getCommissionRevenue());

				cell = row.createCell(colNum++);
				cell.setCellValue(comm.getCost());

				cell = row.createCell(colNum++);
				cell.setCellValue(comm.getCommissionDiscountRate());

				cell = row.createCell(colNum++);
				cell.setCellValue(comm.getCommissionDiscountAmount());

				cell = row.createCell(colNum++);
				cell.setCellValue(comm.getCommissionGARate());

				cell = row.createCell(colNum++);
				cell.setCellValue(comm.getCommissionGAAmount());

				cell = row.createCell(colNum++);
				cell.setCellValue(comm.getBusinessTypeName());

				cell = row.createCell(colNum++);
				cell.setCellValue(comm.getIsFlatFeeCommissionRecord());

				cell = row.createCell(colNum++);
				cell.setCellValue(comm.getGrossProfit());

				cell = row.createCell(colNum++);
				cell.setCellValue(comm.getEstimatedGPPercentage());

				cell = row.createCell(colNum++);
				cell.setCellValue(comm.getActualGrossProfitPercentage());

				cell = row.createCell(colNum++);
				cell.setCellValue(comm.getCommissionProfit());

				cell = row.createCell(colNum++);
				cell.setCellValue(comm.getRoleName());

				cell = row.createCell(colNum++);
				cell.setCellValue(comm.getActualCommissionPercentage());

				cell = row.createCell(colNum++);
				cell.setCellValue(comm.getProRateFactor());

				cell = row.createCell(colNum++);
				cell.setCellValue(comm.getCustomerId());

				cell = row.createCell(colNum++);
				cell.setCellValue(comm.getCustomerName());

				cell = row.createCell(colNum++);
				cell.setCellValue(comm.getIsCommissionPersonForQuota());

				cell = row.createCell(colNum++);
				cell.setCellValue(comm.getCommissionPersonOrphan());

				cell = row.createCell(colNum++);
				cell.setCellValue(comm.getCccf());

				cell = row.createCell(colNum++);
				cell.setCellValue(comm.getCcaf());

				cell = row.createCell(colNum++);
				cell.setCellValue(comm.getMasterProjectId());

				cell = row.createCell(colNum++);
				cell.setCellValue(comm.getMasterProjectRevenue());

				cell = row.createCell(colNum++);
				cell.setCellValue(comm.getMasterProjectCost());

				cell = row.createCell(colNum++);
				cell.setCellValue(comm.getSumOfAllProjectsRevenue());

				cell = row.createCell(colNum++);
				cell.setCellValue(comm.getSumOfAllProjectsCost());

				cell = row.createCell(colNum++);
				cell.setCellValue(comm.getExpenseRevenue());

				cell = row.createCell(colNum++);
				cell.setCellValue(comm.getCountOfChildProjects());

				cell = row.createCell(colNum++);
				cell.setCellValue(comm.getCategoryCode());

				cell = row.createCell(colNum++);
				cell.setCellValue(comm.getEmployeeType());

				cell = row.createCell(colNum++);
				cell.setCellValue(comm.getJobTitle());

				cell = row.createCell(colNum++);
				cell.setCellValue(comm.getProjectGLLocation());

				cell = row.createCell(colNum++);
				cell.setCellValue(comm.getEstimatedCommissionPercentage());

				cell = row.createCell(colNum++);
				cell.setCellValue(comm.getCommissionPersonJobCode());

				cell = row.createCell(colNum++);
				cell.setCellValue(comm.getFlatFeeCommissionAmount());

				cell = row.createCell(colNum++);
				cell.setCellValue(comm.getProjectGPForQuotaAttainment());

				cell = row.createCell(colNum++);
				cell.setCellValue(comm.getAppliedCommissionPaymentPercentage());

				cell = row.createCell(colNum++);
				cell.setCellValue(comm
						.getAdditionalAcceleratorCommissionAmount());

				cell = row.createCell(colNum++);
				cell.setCellValue(comm
						.getCurrentAppliedAcceleratorCommPercentage());

				cell = row.createCell(colNum++);
				cell.setCellValue(comm.getAnalyzerBillRate());

				cell = row.createCell(colNum++);
				cell.setCellValue(comm.getAnalyzerPayRate());

				colNum = 0;

			} catch (Exception ex) {
				logger.debug("Exception in Commission new model report while writing to the file for  by user id : "+FacesUtils.getCurrentUserId()
						+ ex.getLocalizedMessage());
				colNum = 0;
				continue;
			}
		}

		try {
			String realPath = FacesUtils.getTempFilePath();

			Long dateTime = System.currentTimeMillis();

			// D:\Softwares\Apache\tomcat-8.0.23\webapps\Analyzer\

			completeFilePath = realPath + MASTER_FILE_NAME + "-" + dateTime
					+ FILE_EXTENSION;
			this.setCompleteFilePath(completeFilePath);
			this.setNewFileName(MASTER_FILE_NAME + "-" + dateTime
					+ FILE_EXTENSION);
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
		if (commissionDetails == null || commissionDetails.size() == 0) {
			FacesUtils.addGlobalMessage(FacesMessageSeverity.ERROR,
					"No data to print", "There is no data to print!");
			return;
		}
	}

	public void generateReport() {
		logger.debug("About to create Commission New Model Report");

		// always get the userid for security reasons
		CommissionUtil comm;

		comm = new CommissionUtil(Integer.valueOf(month),
				Integer.valueOf(reportingYear), person,
				FacesUtils.getCurrentUserId());

		HttpSession session = FacesUtils.getRequestObject().getSession(false);// getCurrentRequestFromFacesContext().getSession(false);
		session.setAttribute("reportingYear", reportingYear);
		session.setAttribute("personId", person);

		// load header and pdf statement summary data
		commissionPersonInfo = comm.loadStatementSetupData();
		// load commission details for employee
		commissionDetails = comm.loadCommissionDetailsForEmployee();
		
		type1CommissionRecordsCount = comm.getType1CommissionRecordsCount();
		type2CommissionRecordsCount = comm.getType2CommissionRecordsCount();
		type3CommissionRecordsCount = comm.getType3CommissionRecordsCount();
		
		period = comm.getCommissionPeriod();
		currentCommission = comm.getCurrentCommission();
		ytdCommission = comm.getYtdCommission();
		commissionPersonName = comm.getCommissionPersonName();

		showCharts = true;
		reportCreated = true;
	}

	public void onRowSelect(SelectEvent event) {
		// FacesMessage msg = new FacesMessage("Car Selected", ((CommissionUtil)
		// event.getObject()).getP);
		// String projectId = (CommissionDetails)
		// event.getObject()).getProjectId();
		System.out.println("Project id is : "
				+ selectedCommissionDetail.getProjectId());
		getPSJournalData(selectedCommissionDetail.getProjectId());
		// FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void onRowUnselect(UnselectEvent event) {
		// FacesMessage msg = new FacesMessage("Car Unselected", ((Car)
		// event.getObject()).getId());
		// FacesContext.getCurrentInstance().addMessage(null, msg);
		System.out.println("unselect");
	}

	// public void getPSJournalData(AjaxBehaviorEvent event) {
	private void getPSJournalData(String projectId) {
		try {

			/*Map<String, String> params = FacesContext.getCurrentInstance()
					.getExternalContext().getRequestParameterMap();
			projectId = (String) params.get("projectId");*/
			System.out.println("Project id is : " + projectId);
			CommissionUtil comm = new CommissionUtil(Integer.valueOf(month),
					Integer.valueOf(reportingYear), person,
					FacesUtils.getCurrentUserId());
			journalData = comm.loadPeopleSoftJournalData(projectId);
			System.out.println("Journal data loaded : " + journalData.size());
		} catch (Exception ex) {
			System.err.println("Unable to load project id details");
		}
	}

	public void exportChildFileToExcel() {
		if (journalData == null || journalData.size() == 0) {
			FacesUtils
					.addGlobalMessage(FacesMessageSeverity.ERROR,
							"No data to export",
							"There is no data to export to excel!");
			return;
		}

		List<String> header = new ArrayList<String>();
		header.add("Project ID");
		header.add("Journal ID");
		header.add("Location");
		header.add("Type");
		header.add("Account");
		header.add("Monetary Amount");
		header.add("Trans Ref #");
		header.add("Line Description");
		header.add("Resource Type");
		header.add("Resource Category");
		header.add("Analysis Type");
		header.add("Voucher Id");
		header.add("Line Source");
		header.add("Discount Amount");
		header.add("Journal Description");

		XSSFWorkbook workbook = new XSSFWorkbook();

		CellStyle cellStyle1 = workbook.createCellStyle();
		cellStyle1.setFillForegroundColor(IndexedColors.RED.index);

		XSSFSheet sheet = workbook.createSheet("Commission Project GL Details");

		int rowNum = 0;
		int colNum = 0;
		System.out.println("Creating excel");

		Row row = sheet.createRow(rowNum++);
		// create excel file header
		for (String cellHeader : header) {
			Cell cell = row.createCell(colNum++);
			cell.setCellValue(cellHeader);
		}

		colNum = 0;
		Cell cell = null;

		for (PeopleSoftJournalData psData : journalData) {
			row = sheet.createRow(rowNum++);
			try {
				cell = row.createCell(colNum++);
				cell.setCellValue(psData.getProjectId());

				cell = row.createCell(colNum++);
				cell.setCellValue(psData.getJournalId());

				cell = row.createCell(colNum++);
				cell.setCellValue(psData.getChartField1());

				cell = row.createCell(colNum++);
				cell.setCellValue(psData.getSystemSource());

				cell = row.createCell(colNum++);
				cell.setCellValue(psData.getAccount());

				cell = row.createCell(colNum++);
				cell.setCellValue(psData.getMonetaryAmount());

				cell = row.createCell(colNum++);
				cell.setCellValue(psData.getTransactionRefNumber());

				cell = row.createCell(colNum++);
				cell.setCellValue(psData.getLineDescription());

				cell = row.createCell(colNum++);
				cell.setCellValue(psData.getResourceType());

				cell = row.createCell(colNum++);
				cell.setCellValue(psData.getResourceCategory());

				cell = row.createCell(colNum++);
				cell.setCellValue(psData.getAnalysisType());

				cell = row.createCell(colNum++);
				cell.setCellValue(psData.getJournalLineRef_VoucherId());

				cell = row.createCell(colNum++);
				cell.setCellValue(psData.getJournalLineSource());

				cell = row.createCell(colNum++);
				cell.setCellValue(psData.getJournalDescription());

				colNum = 0;

			} catch (Exception ex) {
				logger.debug("Exception in Commission New Model while writing to the file by user id : "+FacesUtils.getCurrentUserId()
						+ ex.getLocalizedMessage());
				colNum = 0;
				continue;
			}
		}

		try {
			String realPath = FacesUtils.getTempFilePath();

			Long dateTime = System.currentTimeMillis();

			// D:\Softwares\Apache\tomcat-8.0.23\webapps\Analyzer\

			completeChildFilePath = realPath + CHILD_FILE_NAME + "-" + dateTime
					+ FILE_EXTENSION;
			this.setCompleteChildFilePath(completeChildFilePath);
			this.setNewChildFileName(CHILD_FILE_NAME + "-" + dateTime
					+ FILE_EXTENSION);
			logger.debug("Child File for downloading is : "
					+ completeChildFilePath);
			System.out.println("Child File for downloading is : "
					+ completeChildFilePath);

			WriteToFile objWriteToFile = new WriteToFile();
			File file = objWriteToFile.createFile(completeChildFilePath);

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

	public void downloadChildExcelFile(String filePath) {
		try {
			if (filePath == null || filePath.equals("")) {
				filePath = newChildFileName;
			}
			// This points to the root.
			InputStream stream = FacesContext.getCurrentInstance()
					.getExternalContext().getResourceAsStream("/" + filePath);
			generatedChildExcelFile = new DefaultStreamedContent(stream,
					"application/vnd.ms-excel", filePath);
		} catch (Exception e) {
			e.printStackTrace();
		}

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

			// TODO get role Id and show only when role id is 3

			ReportUtil report = new ReportUtil();
			List<Map<String, Object>> dataType = null;
			try {
				dataType = report.getAEs(FacesUtils.getCurrentUserId(), "3");
			} catch (Exception e) {
				e.printStackTrace();
			}
			for (int i = 0; i < dataType.size(); i++) {
				Map<String, Object> map = dataType.get(i);
				String id = (String) map.get("ADPCODE");
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
	 * @param person
	 *            the person to set
	 */
	public void setPerson(String person) {
		this.person = person;
	}

	/**
	 * @return the reportingYear
	 */
	public String getReportingYear() {
		return reportingYear;
	}

	/**
	 * @param reportingYear
	 *            the reportingYear to set
	 */
	public void setReportingYear(String reportingYear) {
		this.reportingYear = reportingYear;
	}

	/**
	 * @return the period
	 */
	public String getPeriod() {
		return period;
	}

	/**
	 * @param period
	 *            the period to set
	 */
	public void setPeriod(String period) {
		this.period = period;
	}

	/**
	 * @return the currentCommission
	 */
	public String getCurrentCommission() {
		return currentCommission;
	}

	/**
	 * @param currentCommission
	 *            the currentCommission to set
	 */
	public void setCurrentCommission(String currentCommission) {
		this.currentCommission = currentCommission;
	}

	/**
	 * @return the ytdCommission
	 */
	public String getYtdCommission() {
		return ytdCommission;
	}

	/**
	 * @param ytdCommission
	 *            the ytdCommission to set
	 */
	public void setYtdCommission(String ytdCommission) {
		this.ytdCommission = ytdCommission;
	}

	/**
	 * @return the commissionPersonName
	 */
	public String getCommissionPersonName() {
		return commissionPersonName;
	}

	/**
	 * @param commissionPersonName
	 *            the commissionPersonName to set
	 */
	public void setCommissionPersonName(String commissionPersonName) {
		this.commissionPersonName = commissionPersonName;
	}

	/**
	 * @return the commissionPersonId
	 */
	public String getCommissionPersonId() {
		return commissionPersonId;
	}

	/**
	 * @param commissionPersonId
	 *            the commissionPersonId to set
	 */
	public void setCommissionPersonId(String commissionPersonId) {
		this.commissionPersonId = commissionPersonId;
	}

	/**
	 * @return the commissionDetails
	 */
	public List<CommissionDetails> getCommissionDetails() {
		return commissionDetails;
	}

	/**
	 * @param commissionDetails
	 *            the commissionDetails to set
	 */
	public void setCommissionDetails(List<CommissionDetails> commissionDetails) {
		this.commissionDetails = commissionDetails;
	}

	/**
	 * @param filteredList
	 *            the filteredList to set
	 */
	public void setFilteredList(List<CommissionDetails> filteredList) {
		this.filteredList = filteredList;
	}

	/**
	 * @return the filteredList
	 */
	public List<CommissionDetails> getFilteredList() {
		return filteredList;
	}

	/**
	 * @return the selectedCommissionDetail
	 */
	public CommissionDetails getSelectedCommissionDetail() {
		return selectedCommissionDetail;
	}

	/**
	 * @param selectedCommissionDetail
	 *            the selectedCommissionDetail to set
	 */
	public void setSelectedCommissionDetail(
			CommissionDetails selectedCommissionDetail) {
		this.selectedCommissionDetail = selectedCommissionDetail;
	}

	/**
	 * @return the commissionPersonInfo
	 */
	public CommissionStatementSetupData getCommissionPersonInfo() {
		return commissionPersonInfo;
	}

	/**
	 * @param commissionPersonInfo
	 *            the commissionPersonInfo to set
	 */
	public void setCommissionPersonInfo(
			CommissionStatementSetupData commissionPersonInfo) {
		this.commissionPersonInfo = commissionPersonInfo;
	}

	/**
	 * @return the projectId
	 */
	public String getProjectId() {
		return projectId;
	}

	/**
	 * @param projectId
	 *            the projectId to set
	 */
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	/**
	 * @return the journalData
	 */
	public List<PeopleSoftJournalData> getJournalData() {
		return journalData;
	}

	/**
	 * @param journalData
	 *            the journalData to set
	 */
	public void setJournalData(List<PeopleSoftJournalData> journalData) {
		this.journalData = journalData;
	}

	/**
	 * @return the journalDataFilteredList
	 */
	public List<PeopleSoftJournalData> getJournalDataFilteredList() {
		return journalDataFilteredList;
	}

	/**
	 * @param journalDataFilteredList
	 *            the journalDataFilteredList to set
	 */
	public void setJournalDataFilteredList(
			List<PeopleSoftJournalData> journalDataFilteredList) {
		this.journalDataFilteredList = journalDataFilteredList;
	}

	/**
	 * @return the yearsList
	 */
	public List<SelectItem> getYearsList() {
		return yearsList;
	}

	/**
	 * @param yearsList
	 *            the yearsList to set
	 */
	public void setYearsList(List<SelectItem> yearsList) {
		this.yearsList = yearsList;
	}

	/**
	 * @return the completeChildFilePath
	 */
	public String getCompleteChildFilePath() {
		return completeChildFilePath;
	}

	/**
	 * @param completeChildFilePath
	 *            the completeChildFilePath to set
	 */
	public void setCompleteChildFilePath(String completeChildFilePath) {
		this.completeChildFilePath = completeChildFilePath;
	}

	/**
	 * @return the generatedChildExcelFile
	 */
	public StreamedContent getGeneratedChildExcelFile() {
		return generatedChildExcelFile;
	}

	/**
	 * @return the newChildFileName
	 */
	public String getNewChildFileName() {
		return newChildFileName;
	}

	/**
	 * @param newChildFileName
	 *            the newChildFileName to set
	 */
	public void setNewChildFileName(String newChildFileName) {
		this.newChildFileName = newChildFileName;
	}

	public Boolean getShowCharts() {
		return showCharts;
	}

	public void setShowCharts(Boolean showCharts) {
		this.showCharts = showCharts;
	}

	/**
	 * @return the completeStatementFilePath
	 */
	public String getCompleteStatementFilePath() {
		return completeStatementFilePath;
	}

	/**
	 * @param completeStatementFilePath
	 *            the completeStatementFilePath to set
	 */
	public void setCompleteStatementFilePath(String completeStatementFilePath) {
		this.completeStatementFilePath = completeStatementFilePath;
	}

	/**
	 * @return the newPdfFileName
	 */
	public String getNewPdfFileName() {
		return newPdfFileName;
	}

	/**
	 * @param newPdfFileName
	 *            the newPdfFileName to set
	 */
	public void setNewPdfFileName(String newPdfFileName) {
		this.newPdfFileName = newPdfFileName;
	}

	public PdfPTable getCommissionTable(int businessTypeId,
			int businessTypeRecordCount, int totalRecords) {
		PdfPCell pdfCell;
		// 20170509
		PdfPTable pdfTable = null;
		float[] widthsCommDetails = null;
		if (businessTypeId == 4 || businessTypeId == 1) {
			widthsCommDetails = new float[] { 2f, 3f, 1f, 1f, 1f, 1f, 1f, 1f,
					1f, 1f, 1f, 1f };
			pdfTable = new PdfPTable(12); // 12 columns
		} else {
			widthsCommDetails = new float[] { 2f, 3f, 1f, 1f, 1f, 1f, 1f, 1f,
					1f, 1f, 1f, 1f, 1f };
			pdfTable = new PdfPTable(13); // 13 columns
		}
		// 20170509
		CommissionDetails cd;
		int btId;
		CommissionStatementSetupData ssd = commissionPersonInfo;

		try {
			System.out.println("Generating Commission Details Table.");

			pdfTable.setHorizontalAlignment(Element.ALIGN_LEFT); // By default
																	// Center
			pdfTable.setWidthPercentage(100); // as By default table has only
												// 80% space available
			pdfTable.setWidths(widthsCommDetails);
			pdfTable.setHeaderRows(1);

			pdfCell = new PdfPCell(new Paragraph(
					ssd.getDetailTableColumnLabel1(), calibriFont)); // Consultant
																		// Name
			pdfCell.setGrayFill(greyFill);
			pdfTable.addCell(pdfCell);
			pdfCell = new PdfPCell(new Paragraph(
					ssd.getDetailTableColumnLabel2(), calibriFont)); // Customer
																		// Name
			pdfCell.setGrayFill(greyFill);
			pdfTable.addCell(pdfCell);
			pdfCell = new PdfPCell(new Paragraph(
					ssd.getDetailTableColumnLabel3(), calibriFont)); // Hours
			pdfCell.setHorizontalAlignment(Element.ALIGN_CENTER);
			pdfCell.setGrayFill(greyFill);
			pdfTable.addCell(pdfCell);
			// 20170509
			if (businessTypeId == 4 || businessTypeId == 1) {
				pdfCell = new PdfPCell(new Paragraph(
						ssd.getDetailTableColumnLabel4(), calibriFont)); // /Rev/Rate
			} else {
				// pdfCell = new PdfPCell( new Paragraph("Revenue",
				// calibriFont)); // -- New added Col. 20170509
				pdfCell = new PdfPCell(new Paragraph(
						ssd.getDetailTableColumnLabel4B(), calibriFont)); // --
																			// REVENUE
																			// ...New
																			// added
																			// Col.
																			// 20170509
			} // 20170509

			pdfCell.setHorizontalAlignment(Element.ALIGN_CENTER);
			pdfCell.setGrayFill(greyFill);
			pdfTable.addCell(pdfCell);
			pdfCell = new PdfPCell(new Paragraph(
					ssd.getDetailTableColumnLabel5(), calibriFont)); // Cost
			pdfCell.setHorizontalAlignment(Element.ALIGN_CENTER);
			pdfCell.setGrayFill(greyFill);
			pdfTable.addCell(pdfCell);
			// 20170509
			pdfCell = new PdfPCell(new Paragraph(
					ssd.getDetailTableColumnLabel7(), calibriFont)); // GP //
																		// 20170509-COLUMN
																		// SWITCH
			pdfCell.setHorizontalAlignment(Element.ALIGN_CENTER);
			pdfCell.setGrayFill(greyFill);
			pdfTable.addCell(pdfCell);
			pdfCell = new PdfPCell(new Paragraph(
					ssd.getDetailTableColumnLabel6(), calibriFont)); // GA //
																		// 20170509-COLUMN
																		// SWITCH
			pdfCell.setHorizontalAlignment(Element.ALIGN_CENTER);
			pdfCell.setGrayFill(greyFill);
			pdfTable.addCell(pdfCell);
			if (businessTypeId == 2 || businessTypeId == 3) {
				pdfCell = new PdfPCell(new Paragraph(
						ssd.getDetailTableColumnLabel13(), calibriFont)); // CP
																			// //
																			// --
																			// New
																			// added
																			// Col.
																			// 20170509
				pdfCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				pdfCell.setGrayFill(greyFill);
				pdfTable.addCell(pdfCell);
			}
			// 20170509
			pdfCell = new PdfPCell(new Paragraph(
					ssd.getDetailTableColumnLabel8(), calibriFont)); // GP%
			pdfCell.setHorizontalAlignment(Element.ALIGN_CENTER);
			pdfCell.setGrayFill(greyFill);
			pdfTable.addCell(pdfCell);
			pdfCell = new PdfPCell(new Paragraph(
					ssd.getDetailTableColumnLabel9(), calibriFont)); // PRF
			pdfCell.setHorizontalAlignment(Element.ALIGN_CENTER);
			pdfCell.setGrayFill(greyFill);
			pdfTable.addCell(pdfCell);
			pdfCell = new PdfPCell(new Paragraph(
					ssd.getDetailTableColumnLabel10(), calibriFont)); // Comm%
			pdfCell.setHorizontalAlignment(Element.ALIGN_CENTER);
			pdfCell.setGrayFill(greyFill);
			pdfTable.addCell(pdfCell);
			pdfCell = new PdfPCell(new Paragraph(
					ssd.getDetailTableColumnLabel11(), calibriFont)); // AF
			pdfCell.setHorizontalAlignment(Element.ALIGN_CENTER);
			pdfCell.setGrayFill(greyFill);
			pdfTable.addCell(pdfCell);
			pdfCell = new PdfPCell(new Paragraph(
					ssd.getDetailTableColumnLabel12(), calibriFont)); // Comm
			pdfCell.setHorizontalAlignment(Element.ALIGN_CENTER);
			pdfCell.setGrayFill(greyFill);
			pdfTable.addCell(pdfCell);

			if (businessTypeRecordCount <= 0) {
				for (int i = 0; i < 12; i++) {
					pdfCell = new PdfPCell(new Paragraph("", calibriFontBody));
					pdfTable.addCell(pdfCell);
				}
			} else {
				for (int i = 0; i < totalRecords; i++) {
					cd = (CommissionDetails) commissionDetails.get(i);
					btId = cd.getBusinessTypeId();

					if (businessTypeId == btId) {
						pdfCell = new PdfPCell(new Paragraph(
								cd.getEmployeeName(), calibriFontBody)); // getDetailTableColumnLabel1
																			// ...
																			// Consultant
																			// Name
						pdfTable.addCell(pdfCell);
						pdfCell = new PdfPCell(new Paragraph(
								cd.getCustomerName(), calibriFontBody)); // getDetailTableColumnLabel2
																			// ...
																			// Customer
																			// Name
						pdfTable.addCell(pdfCell);
						pdfCell = new PdfPCell(new Paragraph(new Double(
								cd.getHours()).toString(), calibriFontBody)); // getDetailTableColumnLabel3
																				// ...
																				// Hours
						pdfCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
						pdfTable.addCell(pdfCell);
						// Added 07/29/2016
						// pdfCell = new PdfPCell( new
						// Paragraph(NumberFormat.getCurrencyInstance(Locale.US).format(cd.getCommissionRevenue()),
						// calibriFontBody));
						if (isHybridCommissionCalculationModel()) {
							if (businessTypeId == 2 || businessTypeId == 3) {
								pdfCell = new PdfPCell(new Paragraph(
										NumberFormat.getCurrencyInstance(
												Locale.US).format(
												cd.getCommissionRevenue()),
										calibriFontBody)); // getDetailTableColumnLabel4
															// .. Rev/Rate
							} else {
								pdfCell = new PdfPCell(
										new Paragraph(
												NumberFormat
														.getCurrencyInstance(
																Locale.US)
														.format(cd
																.getEstimatedCommissionRate()),
												calibriFontBody)); // getDetailTableColumnLabel4
																	// ..
																	// Rev/Rate
							}
						} else {
							pdfCell = new PdfPCell(new Paragraph(NumberFormat
									.getCurrencyInstance(Locale.US).format(
											cd.getCommissionRevenue()),
									calibriFontBody)); // getDetailTableColumnLabel4
														// .... .. Rev/Rate
						}
						pdfCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
						pdfTable.addCell(pdfCell);
						pdfCell = new PdfPCell(new Paragraph(NumberFormat
								.getCurrencyInstance(Locale.US).format(
										cd.getCommissionCost()),
								calibriFontBody)); // //
													// getDetailTableColumnLabel5
													// .. Cost
						pdfCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
						pdfTable.addCell(pdfCell);
						// 20170509
						pdfCell = new PdfPCell(new Paragraph(NumberFormat
								.getCurrencyInstance(Locale.US).format(
										cd.getGrossProfit()), calibriFontBody)); // getDetailTableColumnLabel7
																					// ..
																					// GP.
						pdfCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
						pdfTable.addCell(pdfCell);
						pdfCell = new PdfPCell(new Paragraph(NumberFormat
								.getCurrencyInstance(Locale.US).format(
										cd.getCommissionGAAmount()),
								calibriFontBody)); // getDetailTableColumnLabel6
													// ...GA
						pdfCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
						pdfTable.addCell(pdfCell);
						if (businessTypeId == 2 || businessTypeId == 3) {
							pdfCell = new PdfPCell(new Paragraph(NumberFormat
									.getCurrencyInstance(Locale.US)
									.format(cd.getCommissionProfit())
									.toString(), calibriFontBody)); // getDetailTableColumnLabel13
																	// .. CP
																	// 20170509
							pdfCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
							pdfTable.addCell(pdfCell);
						}
						// 20170509
						pdfCell = new PdfPCell(new Paragraph(new Double(
								cd.getGrossProfitPercentage()).toString(),
								calibriFontBody)); // getDetailTableColumnLabel8
													// .. GP %
						pdfCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
						pdfTable.addCell(pdfCell);
						pdfCell = new PdfPCell(new Paragraph(new Double(
								cd.getProRateFactor()).toString(),
								calibriFontBody)); // getDetailTableColumnLabel9
													// .. PRF
						pdfCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
						pdfTable.addCell(pdfCell);
						pdfCell = new PdfPCell(new Paragraph(new Double(
								cd.getActualCommissionPercentage()).toString(),
								calibriFontBody)); // getDetailTableColumnLabel10
													// .. Comm%
						pdfCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
						pdfTable.addCell(pdfCell);
						pdfCell = new PdfPCell(new Paragraph(new Double(
								cd.getCcaf()).toString(), calibriFontBody)); // getDetailTableColumnLabel11..
																				// AF
						pdfCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
						pdfTable.addCell(pdfCell);
						pdfCell = new PdfPCell(new Paragraph(NumberFormat
								.getCurrencyInstance(Locale.US).format(
										cd.getProRatedCommissionAmount()),
								calibriFontBody)); // getDetailTableColumnLabel12..Comm
						pdfCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
						pdfTable.addCell(pdfCell);
					}
				}
			}
		} catch (Exception e) {
			System.out.println("Exception in getCommissionTable = "
					+ e.toString());
			e.printStackTrace();
		}
		return pdfTable;
	}

	private class PdfStatementFooter extends PdfPageEventHelper {
			
		public PdfStatementFooter() {
		}

		public void onEndPage(PdfWriter writer, Document document) {
			try {
				logger.debug("Inside PdfStatementFooter --> onEndPage.");
				System.out.println("Inside PdfStatementFooter --> onEndPage.");

				PdfContentByte cb = writer.getDirectContent();

				cb.saveState();

				String pageNumber = "Page " + writer.getPageNumber();
				float textBase = document.bottom() - 20;
				PdfGState gstate = new PdfGState();

				cb.beginText();
				cb.setFontAndSize(calibriBaseFont, 8);
				cb.setTextMatrix(document.left(), textBase);
				cb.showText(pageNumber);

				// add watermark
				watermarkBaseFont = BaseFont.createFont(BaseFont.TIMES_ROMAN,
						BaseFont.WINANSI, BaseFont.NOT_EMBEDDED);
				cb.setFontAndSize(watermarkBaseFont, 128);
				cb.setColorFill(BaseColor.BLUE);
				gstate.setFillOpacity(0.1f);
				gstate.setStrokeOpacity(0.1f);
				cb.setGState(gstate);
				cb.showTextAligned(Element.ALIGN_CENTER, "DISYS", (document
						.getPageSize().getWidth() / 2) + 50, (document
						.getPageSize().getHeight() / 2) - 10, 45); // rotation

				cb.endText();

				cb.restoreState();
			} catch (Exception e) {
				System.out
						.println("Exception in PdfStatementFooter --> onEndPage = "
								+ e.toString());
				e.printStackTrace();
			}
		}
	}

	/**
	 * @return the calibriFont
	 */
	public Font getCalibriFont() {
		return calibriFont;
	}

	/**
	 * @param calibriFont the calibriFont to set
	 */
	public void setCalibriFont(Font calibriFont) {
		this.calibriFont = calibriFont;
	}

	/**
	 * @return the calibriFontBody
	 */
	public Font getCalibriFontBody() {
		return calibriFontBody;
	}

	/**
	 * @param calibriFontBody the calibriFontBody to set
	 */
	public void setCalibriFontBody(Font calibriFontBody) {
		this.calibriFontBody = calibriFontBody;
	}

	/**
	 * @return the watermarkBaseFont
	 */
	public BaseFont getWatermarkBaseFont() {
		return watermarkBaseFont;
	}

	/**
	 * @param watermarkBaseFont the watermarkBaseFont to set
	 */
	public void setWatermarkBaseFont(BaseFont watermarkBaseFont) {
		this.watermarkBaseFont = watermarkBaseFont;
	}

	/**
	 * @return the calibriBaseFont
	 */
	public BaseFont getCalibriBaseFont() {
		return calibriBaseFont;
	}

	/**
	 * @param calibriBaseFont the calibriBaseFont to set
	 */
	public void setCalibriBaseFont(BaseFont calibriBaseFont) {
		this.calibriBaseFont = calibriBaseFont;
	}

	/**
	 * @return the greyFill
	 */
	public float getGreyFill() {
		return greyFill;
	}

	/**
	 * @param greyFill the greyFill to set
	 */
	public void setGreyFill(float greyFill) {
		this.greyFill = greyFill;
	}

	/**
	 * @return the type1CommissionRecordsCount
	 */
	public int getType1CommissionRecordsCount() {
		return type1CommissionRecordsCount;
	}

	/**
	 * @param type1CommissionRecordsCount the type1CommissionRecordsCount to set
	 */
	public void setType1CommissionRecordsCount(int type1CommissionRecordsCount) {
		this.type1CommissionRecordsCount = type1CommissionRecordsCount;
	}

	/**
	 * @return the type2CommissionRecordsCount
	 */
	public int getType2CommissionRecordsCount() {
		return type2CommissionRecordsCount;
	}

	/**
	 * @param type2CommissionRecordsCount the type2CommissionRecordsCount to set
	 */
	public void setType2CommissionRecordsCount(int type2CommissionRecordsCount) {
		this.type2CommissionRecordsCount = type2CommissionRecordsCount;
	}

	/**
	 * @return the type3CommissionRecordsCount
	 */
	public int getType3CommissionRecordsCount() {
		return type3CommissionRecordsCount;
	}

	/**
	 * @param type3CommissionRecordsCount the type3CommissionRecordsCount to set
	 */
	public void setType3CommissionRecordsCount(int type3CommissionRecordsCount) {
		this.type3CommissionRecordsCount = type3CommissionRecordsCount;
	}

	/**
	 * @return the generatedPdfFile
	 */
	public StreamedContent getGeneratedPdfFile() {
		return generatedPdfFile;
	}

	/**
	 * @return the reportCreated
	 */
	public boolean isReportCreated() {
		return reportCreated;
	}

	/**
	 * @param reportCreated the reportCreated to set
	 */
	public void setReportCreated(boolean reportCreated) {
		this.reportCreated = reportCreated;
	}
}