/**
 * 
 */
package com.disys.analyzer.jsf.bean;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
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
import org.apache.poi.ss.usermodel.Font;
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
import com.disys.analyzer.model.CommissionExtractionProcedure;
import com.disys.analyzer.model.CommissionProcess;
import com.disys.analyzer.reports.util.ReportCriteria;
import com.disys.analyzer.service.CommissionExtractionProcedureService;
import com.disys.analyzer.service.CommissionProcessService;

/**
 * @author Sajid
 * @since May 28, 2018
 *
 */
@ManagedBean
@ViewScoped
public class CommissionExtractionProcedureBean extends
		SpringBeanAutowiringSupport implements Serializable {
	private static final long serialVersionUID = -562465364193731981L;
	private static Logger logger = LoggerFactory
			.getLogger(CommissionExtractionProcedureBean.class);
	@Autowired
	private CommissionExtractionProcedureService service;
	@Autowired
	private CommissionProcessService commissionProcessService;
	private CommissionExtractionProcedure commissionExtractionProcedure;
	private List<CommissionExtractionProcedure> filteredList;
	private List<SelectItem> accountingPeriods;
	private List<SelectItem> fiscalYears;
	private String searchString;
	private List<SelectItem> proceduresList;
	private Integer year;
	private Integer month;
	private String procedure;
	private List<Map<String, Object>> reportData;
	private List<Map<String, Object>> filteredData;
	private final static String FILE_NAME = "CommissonExtractionData";
	private final static String FILE_EXTENSION = ".xlsx";
	private String completeFilePath;
	private StreamedContent generatedExcelFile;
	private String newFileName;
	List<CommissionExtractionProcedure> list;
	private boolean enableExportButton;
	private XSSFWorkbook workbook;
	private boolean firstProcedure;
	private boolean thirdProcedure;
	private boolean fourthProcedure;
	private boolean fifthProcedure;
	private boolean sixthProcedure;
	private boolean seventhProcedure;
	private boolean eighthProcedure;
	private boolean ninthProcedure;
	private boolean tenthProcedure;
	private boolean eleventhProcedure;
	private boolean twelvethProcedure;
	private boolean thirteenProcedure;
	private boolean fourteenProcedure;
	private boolean fifteenProcedure;
	private boolean sixteenProcedure;
	private boolean seventeenProcedure;
	private boolean eighteenProcedure;
	private boolean nineteenProcedure;
	private CommissionProcess process;
	private boolean processDiv;
	private List<CommissionProcess> processList;
	private List<String> monthsName;

	public CommissionExtractionProcedureBean() {
		String userId = FacesUtils.getCurrentUserId();
		System.out.println("User id : " + userId);
		enableExportButton = true;
		workbook = new XSSFWorkbook();
		resetProcedureGrid();
		processDiv = false;
	}

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

	public void exportToExcel() throws ReportDataNotFoundException {
		if (this.getProcedure().equals("ALL")) {
			try {
				ServletContext ctx = (ServletContext) FacesContext
						.getCurrentInstance().getExternalContext().getContext();
				String realPath = FacesUtils.getTempFilePath();
				Long dateTime = System.currentTimeMillis();
				completeFilePath = realPath + FILE_NAME + "-" + dateTime
						+ "-ALL" + FILE_EXTENSION;
				this.setCompleteFilePath(completeFilePath);
				this.setNewFileName(FILE_NAME + "-" + dateTime + "-ALL"
						+ FILE_EXTENSION);
				logger.debug("File for downloading is : " + completeFilePath);
				System.out.println("File for downloading is : "
						+ completeFilePath);
				WriteToFile objWriteToFile = new WriteToFile();
				File file = objWriteToFile.createFile(completeFilePath);
				FileOutputStream outputStream = new FileOutputStream(file);
				// workbook = new XSSFWorkbook();
				workbook.write(outputStream);
				// workbook.close();
				outputStream.flush();
				outputStream.close();
				// enableExportButton = false;

			} catch (IOException e) {
				e.printStackTrace();
				FacesUtils.addGlobalMessage(FacesMessageSeverity.ERROR,
						"Error", "Error in exporting data to excel!");
			}

		} else {
			if (reportData == null || reportData.size() == 0) {
				FacesUtils.addGlobalMessage(FacesMessageSeverity.ERROR,
						"No data to export",
						"There is no data to export to excel!");
				throw new ReportDataNotFoundException("No data to export");
			}

			workbook = new XSSFWorkbook();
			List<String> header = new ArrayList<String>();
			CellStyle cellStyle1 = workbook.createCellStyle();
			cellStyle1.setFillForegroundColor(IndexedColors.RED.index);
			XSSFSheet sheet = workbook.createSheet(findExcelSheetName(Integer
					.valueOf(this.getProcedure()) - 1));
			int rowNum = 0;
			int colNum = 0;
			System.out.println("Creating excel file for commission extraction");

			Row row = sheet.createRow(rowNum++);
			// create excel file header
			for (String cellHeader : header) {
				Cell cell = row.createCell(colNum++);
				cell.setCellValue(cellHeader);
			}

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
				String value = "";
				while (iterator.hasNext()) {
					Map.Entry<String, Object> entry = iterator.next();
					try {
						cell = row.createCell(colNum++);
						System.out.println("Entry is : " + entry.toString());
						value = entry.toString();
						if (value != null && value.length() > 0) {
							value = value.substring(0, value.indexOf('='));
						}
						cell.setCellValue(value);
					} catch (Exception ex) {
						logger.debug("Exception in Commission Extraction Procedure Bean while writing to the file for : "+entry.toString()
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
						cell.setCellValue(entry.getValue().toString());
					} catch (Exception ex) {
						logger.debug("Exception in Commission Extraction Procedure Bean while writing to the file for : "+entry.toString()
								+ ex.getLocalizedMessage());
						continue;
					}
				}
			}

			try {
				String realPath = FacesUtils.getTempFilePath();
				Long dateTime = System.currentTimeMillis();

				completeFilePath = realPath
						+ FILE_NAME
						+ "-"
						+ dateTime
						+ "-"
						+ findQueryName(Integer
								.valueOf(this.getProcedure()) - 1)
						+ FILE_EXTENSION;
				this.setCompleteFilePath(completeFilePath);
				this.setNewFileName(FILE_NAME
						+ "-"
						+ dateTime
						+ "-"
						+ findQueryName(Integer.valueOf(this.getProcedure()) - 1)
						+ FILE_EXTENSION);
				logger.debug("File for downloading is : " + completeFilePath);
				System.out.println("File for downloading is : "
						+ completeFilePath);

				WriteToFile objWriteToFile = new WriteToFile();
				File file = objWriteToFile.createFile(completeFilePath);

				FileOutputStream outputStream = new FileOutputStream(file);

				workbook.write(outputStream);
				workbook.close();

				outputStream.flush();
				outputStream.close();

			} catch (IOException e) {
				e.printStackTrace();
				FacesUtils.addGlobalMessage(FacesMessageSeverity.ERROR,
						"Error", "Error in exporting data to excel!");
			}
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

	public String[] getHeaders(Integer number) {

		String[] headers1 = new String[] { "CommissionPersonEmplIdOrOprId",
				"CommissionPersonName", "Amount", "RowsCount" };

		String[] headers2 = new String[] { "CommissionPersonEmplIdOrOprId",
				"CommissionPersonName", "Amount", "RowsCount" };

		String[] headers3 = new String[] { "CommissionPersonEmplIdOrOprId",
				"SequenceNumber", "PayableCommissionType",
				"CurrentBalanceAmount", "PayableCommissionAmount" };

		String[] headers4 = new String[] { "CommissionPersonEmplIdOrOprId",
				"SequenceNumber", "PayableCommissionType",
				"PreviousBalanceAmount",
				"CurrentCommissionAmount,CurrentBalanceAmount",
				"PayableCommissionAmount" };

		String[] headers5 = new String[] { "CommissionPersonEmplIdOrOprId",
				"SequenceNumber", "PayableCommissionType",
				"PreviousBalanceAmount",
				"CurrentCommissionAmount,CurrentBalanceAmount",
				"PayableCommissionAmount" };

		String[] headers6 = new String[] { "BUS UNIT", "LEDGER", "ACCOUNT",
				"DEPT", "OPU", "PRODUCT", "CLIENT", "AMOUNT", "REF",
				"CommissionPersonEmplId", "LINE DESCRIPTION", "PROJ BUS UNIT",
				"PROJID", "LOCATION", "ACTIVITY", "AN TYPE" };

		String[] headers7 = new String[] { "BUS UNIT", "LEDGER", "ACCOUNT",
				"DEPT", "OPU", "PRODUCT", "CLIENT", "AMOUNT", "REF",
				"CommissionPersonEmplId", "LINE DESCRIPTION", "PROJ BUS UNIT",
				"PROJID", "LOCATION", "ACTIVITY", "AN TYPE" };

		String[] headers8 = new String[] { "CommissionDetailId", "ProjectId",
				"AccountingPeriod", "FiscalYear", "Revenue",
				"RevenueRowsCount", "Cost", "CostRowsCount", "ExpenseRevenue",
				"ExpenseRevenueRowsCount", "ExpenseCost",
				"ExpenseCostRowsCount", "MasterProjectRevenue",
				"MasterProjectCost", "MasterProjectExpenseRevenue",
				"MasterProjectExpenseCost", "DataSource", "MasterProjectId",
				"IsMasterProject", "BillingType", "BusinessTypeName",
				"IsProjectExistInGL", "CustomerId", "CustomerName",
				"CommissionDiscountRate", "CommissionGARate",
				"SumOfAllProjectsCost", "CCCF", "CommissionCost",
				"SumOfAllProjectsRevenue", "GrossRevenue",
				"CommissionDiscountAmount", "CommissionRevenue", "GrossProfit",
				"GrossProfitPercentage", "CommissionGAAmount",
				"CommissionProfit", "CCAF", "CategoryCode", "AssignmentId",
				"WorksiteAddressSeq", "SumOfAllProjectsExpenseCost",
				"SumOfAllProjectsExpenseRevenue", "SumOfAllChildProjectsCost",
				"SumOfAllChildProjectsRevenue",
				"SumOfAllChildProjectsExpenseCost",
				"SumOfAllChildProjectsExpenseRevenue", "ChildAdjustedCost",
				"ChildAdjustedRevenue", "RevenueForDiscount",
				"CountOfChildProjects", "ProjectGLLocation", "Hours",
				"BranchCode", "EmployeeType", "EmplId", "EmplRcd", "JobTitle",
				"EmployeeName", "RoleName", "CommissionPersonOprId",
				"CommissionPersonOrphan", "EstimatedCommissionPercentage",
				"CommissionPersonName", "CommissionPersonEmplId",
				"CommissionPersonDeptId", "ParentId", "EstimatedGPPercentage",
				"AssignmentDescription", "ActualGrossProfitPercentage",
				"StartGPPercent", "EndGPPercent", "ActualCommissionPercentage",
				"ProRateFactor", "CommissionAdjustmentReason",
				"CommissionEffectiveDate", "CalculatedCommissionPercentage",
				"GrossCommissionAmount", "ProRatedCommissionAmount",
				"AccountsCommissionLineDescr", "IsCommissionPersonTerminated",
				"IsSalaryHigherThanLimit", "CommissionPersonTerminationDate",
				"IsCommissionPersonForQuota", "CommissionPersonLocation",
				"CommissionEntryType", "CommissionFinancialDetailId",
				"CommissionProcessId", "CommissionPersonDetailId",
				"ExpenseCostId", "CommissionCostId", "ExpenseRevenueId",
				"CommissionRevenueId", "MasterChildProjectDetailId",
				"USStateCode", "BusinessTypeId", "ProjectCategoryId",
				"CommissionPersonRoleId", "EmployeeProRateFactorId",
				"CommissionPersonAnalyzerId", "CommissionTierId", "NetProfit",
				"TotalCommission", "ChildAdjustedGA", "ChildAdjustedProfit",
				"BonusRevenue", "BonusCost", "BonusProfit",
				"CommissionPersonSplitPercentage", "AnalyzerSubmissionDate",
				"AnalyzerOperatingUnit", "SkillCatgory", "AnalyzerClientId",
				"AnalyzerBusinessUnit", "SplitProRateFactor",
				"TerminationProRateFactor", "AnnualSalaryProRateFactor",
				"OperatingUnitOrVertical", "VerticalDescription",
				"GLTemplateBusinessUnit", "GLTemplateLedger",
				"GLTemplateAccount", "GLProduct", "GLProductDescription",
				"PSClientId", "PSClientName", "GLTemplateLocation",
				"GLTemplateReference", "GLTemplateProjectBU",
				"GLTemplateAnalysisType", "ActivityId",
				"OperatingUnitFromVerticalVW", "ProductFromVerticalVW",
				"PSClientIdFromVerticalVW", "DeptIdFromVerticalVW",
				"AnalyzerTerminationDate", "AssignmentEndDate", "BonusGA",
				"AnalyzerId", "BusinessUnitHR", "GLDeptId", "OrderId",
				"OrderLine", "EffectiveDate", "EffectiveSequence",
				"AnalyzerBillRate", "AnalyzerGA",
				"AnalyzerTotalCommissionAmount", "AnalyzerProfit",
				"AnalyzerStartDate", "VendorId", "EstimatedCommissionRate",
				"AnalyzerTotalHoursWorked", "AnalyzerOtherAmountPerHour",
				"AnalyzerOtherAmountPerDeal", "AnalyzerEndDate",
				"AnalyzerPerDiemAmount", "AnalyzerPayRate",
				"AnalyzerDiscountPercentage", "AnalyzerDiscountAmount",
				"AnalyzerSpread", "AnalyzerTaxesOrFringe",
				"AnalyzerLeaveAmount", "AnalyzerPTODays",
				"AnalyzerBonusAmount", "AnalyzerImmigrationCost",
				"AnalyzerEquipmentCost", "AnalyzerBillableCost",
				"AssignmentEntryDate", "AssignmentLastUpdateDate",
				"AnalyzerReferralFee", "AnalyzerHealthAmount",
				"AnalyzerK401Amount", "CommissionCalculationModel",
				"EstimatedOverallDealCost", "CommissionModelId",
				"AnalyzerCommissionModelCode", "AnalyzerDealType",
				"CommissionPersonJobCode", "PSJobEffectiveDate",
				"PSJobEffectiveSequence", "QuotaEffectiveDate",
				"QuotaEffectiveSequence", "YearlyQuotaAttainmentAmount",
				"QuotaEntryId", "QuotaCommissionJobCodeId",
				"QuotaAttainmentCommPercentage", "QuotaCommissionRateId",
				"IsFlatFeeCommissionRecord", "FlatFeeCommissionId",
				"FlatFeeCommissionAmount", "IsFlatFeeCommissionAlreadyPaid",
				"IsFlatFeeCommissionPayable", "FlatFeePaymentTrackId",
				"FlatFeeProRateFactor", "PreviousYTDAttainedGPQuotaAmount",
				"CurrentMonthGPQuotaAmount", "CurrentYTDAttainedGPQuotaAmount",
				"ProjectGPForQuotaAttainment", "EmploymentProRateFactor",
				"IsCommPersonEligibleForAcceleratorComm",
				"CurrentAppliedAcceleratorCommPercentage",
				"CommissionQuotaAttainmentId",
				"AdditionalAcceleratorCommissionAmount",
				"GrossCommissionAmountBeforeAccelerator",
				"AppliedCommissionPaymentPercentage", "OrphanProRateFactor",
				"AnalyzerVertical", "AnalyzerProduct", "ManagingDirector",
				"SickLeaveCost", "SickLeaveRate", "SickLeaveHoursCap",
				"IsFalseTermination", "ServicesProjectCost",
				"IsAddressUSPSValidated", "USPSAddressValidationDate",
				"CommissionPersonScorecardGrade", "IsIncludedInQuota",
				"IsCommissionIncludedInPayroll",
				"EstimatedGrossCommissionForServices",
				"CommissionModelCapAmount", "IsCommissionPaymentCapReached",
				"IsCommiCapReachedInCurrentPeriod",
				"CommAmountOverCapInCurrPeriod", "PreviousCommissionPayment",
				"CurrentCommissionPayment", "TotalCommissionPayment",
				"ProjectCommissionPaymentTrackId",
				"AdditionalAcceleratorCommissionAmountActuals",
				"ProRatedCommissionAmountActuals", "KPIQuotaAmount",
				"KPIQuotaEffectiveDate" };

		String[] headers9 = new String[] { "CommissionFinancialDetailId",
				"ProjectId", "AccountingPeriod", "FiscalYear", "Revenue",
				"RevenueRowsCount", "Cost", "CostRowsCount", "ExpenseRevenue",
				"ExpenseRevenueRowsCount", "ExpenseCost",
				"ExpenseCostRowsCount", "MasterProjectRevenue",
				"MasterProjectCost", "MasterProjectExpenseRevenue",
				"MasterProjectExpenseCost", "DataSource", "MasterProjectId",
				"IsMasterProject", "BillingType", "BusinessTypeName",
				"IsProjectExistInGL", "CustomerId", "CustomerName",
				"CommissionDiscountRate", "CommissionGARate",
				"SumOfAllProjectsCost", "CCCF", "CommissionCost",
				"SumOfAllProjectsRevenue", "GrossRevenue",
				"CommissionDiscountAmount", "CommissionRevenue", "GrossProfit",
				"GrossProfitPercentage", "CommissionGAAmount",
				"CommissionProfit", "CCAF", "CategoryCode", "AssignmentId",
				"WorksiteAddressSeq", "SumOfAllProjectsExpenseCost",
				"SumOfAllProjectsExpenseRevenue", "SumOfAllChildProjectsCost",
				"SumOfAllChildProjectsRevenue",
				"SumOfAllChildProjectsExpenseCost",
				"SumOfAllChildProjectsExpenseRevenue", "ChildAdjustedCost",
				"ChildAdjustedRevenue", "RevenueForDiscount",
				"CountOfChildProjects", "ProjectGLLocation", "Hours",
				"CommissionRevenueId", "CommissionCostId", "ExpenseRevenueId",
				"ExpenseCostId", "CommissionProcessId",
				"MasterChildProjectDetailId", "USStateCode",
				"ProjectCategoryId", "BusinessTypeId", "NetProfit",
				"TotalCommission", "ChildAdjustedGA", "ChildAdjustedProfit",
				"OperatingUnitOrVertical", "VerticalDescription",
				"GLTemplateBusinessUnit", "GLTemplateLedger",
				"GLTemplateAccount", "GLProduct", "GLProductDescription",
				"PSClientId", "PSClientName", "GLTemplateLocation",
				"GLTemplateReference", "GLTemplateProjectBU",
				"GLTemplateAnalysisType", "ActivityId",
				"OperatingUnitFromVerticalVW", "ProductFromVerticalVW",
				"PSClientIdFromVerticalVW", "DeptIdFromVerticalVW",
				"AssignmentEndDate", "BusinessUnitHR", "CommissionHoursId" };

		String[] headers10 = new String[] { "MasterChildProjectDetailId",
				"ProjectId", "MasterProjectId", "BillingType",
				"IsMasterProject", "BusinessTypeName", "IsProjectExistInGL",
				"CategoryCode", "AssignmentId", "WorksiteAddressSeq",
				"CustomerId", "AccountingPeriod", "FiscalYear",
				"BusinessUnitHR", "CustomerName", "CustomerDiscountRate",
				"CommissionGARate", "ProjectGLLocation", "BusinessTypeId",
				"CommissionProcessId", "USStateCode", "ProjectCategoryId",
				"OperatingUnitOrVertical", "VerticalDescription",
				"GLTemplateBusinessUnit", "GLTemplateLedger",
				"GLTemplateAccount", "GLProduct", "GLProductDescription",
				"PSClientId", "PSClientName", "GLTemplateLocation",
				"GLTemplateReference", "GLTemplateProjectBU",
				"GLTemplateAnalysisType", "ActivityId",
				"OperatingUnitFromVerticalVW", "ProductFromVerticalVW",
				"PSClientIdFromVerticalVW", "DeptIdFromVerticalVW",
				"AssignmentEndDate", "HoursForCommission", "CommissionHoursId" };

		// headers for 11 are same as 10

		String[] headers12 = new String[] { "CommissionFinancialDetailId",
				"ProjectId", "AccountingPeriod", "FiscalYear", "Revenue",
				"RevenueRowsCount", "Cost", "CostRowsCount", "ExpenseRevenue",
				"ExpenseRevenueRowsCount", "ExpenseCost",
				"ExpenseCostRowsCount", "MasterProjectRevenue",
				"MasterProjectCost", "MasterProjectExpenseRevenue",
				"MasterProjectExpenseCost", "DataSource", "MasterProjectId",
				"IsMasterProject", "BillingType", "BusinessTypeName",
				"IsProjectExistInGL", "CustomerId", "CustomerName",
				"CommissionDiscountRate", "CommissionGARate",
				"SumOfAllProjectsCost", "CCCF", "CommissionCost",
				"SumOfAllProjectsRevenue", "GrossRevenue",
				"CommissionDiscountAmount", "CommissionRevenue", "GrossProfit",
				"GrossProfitPercentage", "CommissionGAAmount",
				"CommissionProfit", "CCAF", "CategoryCode", "AssignmentId",
				"WorksiteAddressSeq", "SumOfAllProjectsExpenseCost",
				"SumOfAllProjectsExpenseRevenue", "SumOfAllChildProjectsCost",
				"SumOfAllChildProjectsRevenue",
				"SumOfAllChildProjectsExpenseCost",
				"SumOfAllChildProjectsExpenseRevenue", "ChildAdjustedCost",
				"ChildAdjustedRevenue", "RevenueForDiscount",
				"CountOfChildProjects", "ProjectGLLocation", "Hours",
				"CommissionRevenueId", "CommissionCostId", "ExpenseRevenueId",
				"ExpenseCostId", "CommissionProcessId",
				"MasterChildProjectDetailId", "USStateCode",
				"ProjectCategoryId", "BusinessTypeId", "NetProfit",
				"TotalCommission", "ChildAdjustedGA", "ChildAdjustedProfit",
				"OperatingUnitOrVertical", "VerticalDescription",
				"GLTemplateBusinessUnit", "GLTemplateLedger",
				"GLTemplateAccount", "GLProduct", "GLProductDescription",
				"PSClientId", "PSClientName", "GLTemplateLocation",
				"GLTemplateReference", "GLTemplateProjectBU",
				"GLTemplateAnalysisType", "ActivityId",
				"OperatingUnitFromVerticalVW", "ProductFromVerticalVW",
				"PSClientIdFromVerticalVW", "DeptIdFromVerticalVW",
				"AssignmentEndDate", "BusinessUnitHR", "CommissionHoursId" };

		// 13 and 14 also same as 12

		String[] headers15 = new String[] { "FlatFeePaymentTrackId",
				"CommissionPersonEmplId", "ProjectId", "AccountingPeriod",
				"FiscalYear", "FlatFeeCommissionAmount", "CommissionModelCode",
				"FlatFeeCommissionId", "CommissionProcessId",
				"CommissionPersonRoleId", "CommissionModelId" };

		String[] headers16 = new String[] { "CommissionPersonName",
				"CommissionQuotaAttainmentId", "AccountingPeriod",
				"FiscalYear", "CommissionPersonEmplId",
				"CommissionPersonJobCode", "QuotaEffectiveDate",
				"QuotaEffectiveSequence", "YearlyQuotaAttainmentAmount",
				"QuotaEntryId", "QuotaAttainmentCommPercentage",
				"PreviousYTDAttainedGPQuotaAmount",
				"CurrentMonthGPQuotaAmount", "CurrentYTDAttainedGPQuotaAmount",
				"CurrentAppliedAcceleratorCommPercentage",
				"IsCommPersonEligibleForAcceleratorComm",
				"CommissionProcessId", "QuotaCommissionJobCodeId",
				"QuotaCommissionRateId" };

		String[] headers17 = new String[] { "CommissionPersonName",
				"CommissionQuotaAttainmentId", "AccountingPeriod",
				"FiscalYear", "CommissionPersonEmplId",
				"CommissionPersonJobCode", "QuotaEffectiveDate",
				"QuotaEffectiveSequence", "YearlyQuotaAttainmentAmount",
				"QuotaEntryId", "QuotaAttainmentCommPercentage",
				"PreviousYTDAttainedGPQuotaAmount",
				"CurrentMonthGPQuotaAmount", "CurrentYTDAttainedGPQuotaAmount",
				"CurrentAppliedAcceleratorCommPercentage",
				"IsCommPersonEligibleForAcceleratorComm",
				"CommissionProcessId", "QuotaCommissionJobCodeId",
				"QuotaCommissionRateId" };

		// 17 is same as 16

		String[] headers18 = new String[] { "EmplId", "EmployeeName",
				"AnalyzerCommissionModelCode", "CommissionPersonEmplId",
				"CommissionPersonName", "FlatFeeCommissionPayment",
				"NumberOfPayments" };

		String[] headers19 = new String[] { "CommissionPersonEmplId",
				"CommissionPersonName", "RegularCommission",
				"QuotaAttainmentCommission", "TotalCommission",
				"NumberOfRecords" };

		if (number == 1) {
			return headers1;
		} else if (number == 2) {
			return headers2;
		} else if (number == 3) {
			return headers3;
		} else if (number == 4) {
			return headers4;
		} else if (number == 5) {
			return headers5;
		} else if (number == 6) {
			return headers6;
		} else if (number == 7) {
			return headers7;
		} else if (number == 8) {
			return headers8;
		} else if (number == 9) {
			return headers9;
		} else if (number == 10) {
			return headers10;
		} else if (number == 11) {
			return headers10;
		} else if (number == 12) {
			return headers12;
		} else if (number == 13) {
			return headers12;
		} else if (number == 14) {
			return headers12;
		} else if (number == 15) {
			return headers15;
		} else if (number == 16) {
			return headers16;
		} else if (number == 17) {
			return headers17;
		} else if (number == 18) {
			return headers18;
		} else if (number == 19) {
			return headers19;
		}
		return headers19;
	}

	public void generateAndDownloadReport() {
		logger.debug("About to extract commission data");

		process = commissionProcessService.findByAccountingPeriodAndFiscalYear(
				this.getMonth(), this.getYear());

		if (process == null) {
			processDiv = false;
			FacesUtils.addGlobalMessage(FacesMessageSeverity.ERROR,
					"No data to extract for this period",
					"No data to extract for this period!");

		} else if (process.getCompletionPercentage() != 100) {
			processDiv = false;
			FacesUtils
					.addGlobalMessage(
							FacesMessageSeverity.ERROR,
							"Execution process is still executing, wait for it to complete",
							"Execution process is still executing, wait for it to complete!");
		} else {
			processDiv = true;

			processList = new ArrayList<CommissionProcess>();
			processList.add(0, process);
		}

		if (this.getProcedure().equals("ALL")) {
			workbook = new XSSFWorkbook();

			// this is a call for extracting data for all procedures.
			Integer s = 0;
			if(list != null){
				s = list.size();
			}
			
			CellStyle cellStyle = workbook.createCellStyle();
			Font font = workbook.createFont();
			font.setBoldweight(Font.BOLDWEIGHT_BOLD);
			cellStyle.setFont(font);
			
			for (int i = 0; i < s; i++) {
				String reportName = "PROCEDURE_" + (i + 1) + "_REPORT";

				String reportParam = month + "|" + year;

				ReportCriteria rc = null;
				reportData = new ArrayList<Map<String, Object>>();
				try {
					rc = new ReportCriteria(reportName, reportParam);
					reportData = rc.getReportFromCommission();// get the data from the array list
					System.out.println(reportData.size());
				} catch (Exception e) {
					e.printStackTrace();
				}

				XSSFSheet sheet = workbook.createSheet(findExcelSheetName(i));

				int rowNum = 0;
				int colNum = 0;
				System.out.println("Creating excel sheet for "
						+ findExcelSheetName(i));

				Row row = null;
				
				List<Map<String, Object>> reportDataCopy = reportData;
				/*
				 * 
				 * To print column information from the result set
				 */
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
					String value = "";
					while (iterator.hasNext()) {
						Map.Entry<String, Object> entry = iterator.next();
						try {
							System.out
									.println("Entry is : " + entry.toString());
							value = entry.toString();
							if (value != null && value.length() > 0) {
								value = value.substring(0, value.indexOf('='));
							}
							if (!value.isEmpty())
							{
								cell = row.createCell(colNum++);
								cell.setCellStyle(cellStyle);
								cell.setCellValue(value);
							}
							
						} catch (Exception ex) {
							logger.debug("Exception in "+logger.getName()+" while writing to the file for : "+entry.toString() +" by user id : "+FacesUtils.getCurrentUserId()
									+ ex.getLocalizedMessage());
							continue;
						}
					}
				}

				//header row is printed now, start from the second row
				rowNum = 1;
				for (Map<String, Object> map : reportDataCopy) {
					row = sheet.createRow(rowNum++);
					colNum = 0;
					Cell cell = null;
					Iterator<Map.Entry<String, Object>> iterator = map
							.entrySet().iterator();
					while (iterator.hasNext()) {
						Map.Entry<String, Object> entry = iterator.next();
						try {
							cell = row.createCell(colNum++);
							cell.setCellValue(entry.getValue().toString());
						} catch (Exception ex) {
							logger.debug("Exception in Commission Extraction Procedure Bean while writing to the file for : "+entry.toString()
									+ ex.getLocalizedMessage());
							continue;
						}
					}
				}
			} // all three sheets are created now.

		} else {
			String reportName = "PROCEDURE_" + this.getProcedure() + "_REPORT";// this.getProcedure();

			if (this.getProcedure().equals("1")
					|| this.getProcedure().equals("2")) {
				resetProcedureGrid();
				firstProcedure = true;
			} else if (this.getProcedure().equals("3")) {
				resetProcedureGrid();
				thirdProcedure = true;
			} else if (this.getProcedure().equals("4")) {
				resetProcedureGrid();
				fourthProcedure = true;
			} else if (this.getProcedure().equals("5")) {
				resetProcedureGrid();
				fifthProcedure = true;
			} else if (this.getProcedure().equals("6")) {
				resetProcedureGrid();
				sixthProcedure = true;
			} else if (this.getProcedure().equals("7")) {
				resetProcedureGrid();
				seventhProcedure = true;
			} else if (this.getProcedure().equals("8")) {
				resetProcedureGrid();
				eighthProcedure = true;
			} else if (this.getProcedure().equals("9")) {
				resetProcedureGrid();
				ninthProcedure = true;
			} else if (this.getProcedure().equals("10")) {
				resetProcedureGrid();
				tenthProcedure = true;
			} else if (this.getProcedure().equals("11")) {
				resetProcedureGrid();
				eleventhProcedure = true;
			} else if (this.getProcedure().equals("12")) {
				resetProcedureGrid();
				twelvethProcedure = true;
			} else if (this.getProcedure().equals("13")) {
				resetProcedureGrid();
				thirteenProcedure = true;
			} else if (this.getProcedure().equals("14")) {
				resetProcedureGrid();
				fourteenProcedure = true;
			} else if (this.getProcedure().equals("15")) {
				resetProcedureGrid();
				fifteenProcedure = true;
			} else if (this.getProcedure().equals("16")) {
				resetProcedureGrid();
				sixteenProcedure = true;
			} else if (this.getProcedure().equals("17")) {
				resetProcedureGrid();
				seventeenProcedure = true;
			} else if (this.getProcedure().equals("18")) {
				resetProcedureGrid();
				eighteenProcedure = true;
			} else if (this.getProcedure().equals("19")) {
				resetProcedureGrid();
				nineteenProcedure = true;
			}

			String reportParam = month + "|" + year;

			ReportCriteria rc = null;
			reportData = new ArrayList<Map<String, Object>>();
			try {
				rc = new ReportCriteria(reportName, reportParam);
				reportData = rc.getReportFromCommission();// get the data from
															// the array list
				System.out.println(reportData.size());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		// downloadExcelFile("");
	}

	public void resetProcedureGrid() {
		firstProcedure = false;
		thirdProcedure = false;
		fifthProcedure = false;
		sixthProcedure = false;
		seventhProcedure = false;
		eighthProcedure = false;
		ninthProcedure = false;
		tenthProcedure = false;
		eleventhProcedure = false;
		twelvethProcedure = false;
		thirteenProcedure = false;
		fourteenProcedure = false;
		fifteenProcedure = false;
		sixteenProcedure = false;
		seventeenProcedure = false;
		eighteenProcedure = false;
		nineteenProcedure = false;

	}

	public void eraseFilter() {
		this.reportData = null;
	}

	/**
	 * @return the searchString
	 */
	public String getSearchString() {
		return searchString;
	}

	/**
	 * @param searchString
	 *            the searchString to set
	 */
	public void setSearchString(String searchString) {
		this.searchString = searchString;
	}

	/**
	 * @return the accountingPeriods
	 */
	public List<SelectItem> getAccountingPeriods() {
		if (accountingPeriods == null) {
			accountingPeriods = new ArrayList<SelectItem>();
			accountingPeriods.add(new SelectItem(1, "Jan"));
			accountingPeriods.add(new SelectItem(2, "Feb"));
			accountingPeriods.add(new SelectItem(3, "Mar"));
			accountingPeriods.add(new SelectItem(4, "Apr"));
			accountingPeriods.add(new SelectItem(5, "May"));
			accountingPeriods.add(new SelectItem(6, "Jun"));
			accountingPeriods.add(new SelectItem(7, "Jul"));
			accountingPeriods.add(new SelectItem(8, "Aug"));
			accountingPeriods.add(new SelectItem(9, "Sep"));
			accountingPeriods.add(new SelectItem(10, "Oct"));
			accountingPeriods.add(new SelectItem(11, "Nov"));
			accountingPeriods.add(new SelectItem(12, "Dec"));
		}
		return accountingPeriods;
	}

	/**
	 * @param accountingPeriods
	 *            the accountingPeriods to set
	 */
	public void setAccountingPeriods(List<SelectItem> accountingPeriods) {
		this.accountingPeriods = accountingPeriods;
	}

	/**
	 * @return the fiscalYears
	 */
	public List<SelectItem> getFiscalYears() {
		if (fiscalYears == null) {
			Calendar c = Calendar.getInstance();
			int year = c.get(Calendar.YEAR);
			fiscalYears = new ArrayList<SelectItem>();
			fiscalYears.add(new SelectItem(year, "" + year));
			fiscalYears.add(new SelectItem(year - 1, "" + (year - 1)));
			fiscalYears.add(new SelectItem(year - 2, "" + (year - 2)));
		}
		return fiscalYears;
	}

	/**
	 * @param fiscalYears
	 *            the fiscalYears to set
	 */
	public void setFiscalYears(List<SelectItem> fiscalYears) {
		this.fiscalYears = fiscalYears;
	}

	/**
	 * @return the commissionExtractionProcedure
	 */
	public CommissionExtractionProcedure getCommissionExtractionProcedure() {
		return commissionExtractionProcedure;
	}

	/**
	 * @param commissionExtractionProcedure
	 *            the commissionExtractionProcedure to set
	 */
	public void setCommissionExtractionProcedure(
			CommissionExtractionProcedure commissionExtractionProcedure) {
		this.commissionExtractionProcedure = commissionExtractionProcedure;
	}

	/**
	 * @return the filteredList
	 */
	public List<CommissionExtractionProcedure> getFilteredList() {
		return filteredList;
	}

	/**
	 * @param filteredList
	 *            the filteredList to set
	 */
	public void setFilteredList(List<CommissionExtractionProcedure> filteredList) {
		this.filteredList = filteredList;
	}

	
	private String findExcelSheetName(Integer position) {
		return list.get(position).getExcelSheetName();
	}
	
	private String findQueryName(Integer position) {
		return list.get(position).getQueryName();
	}
	
	private String findProcedureName(Integer position) {
		return list.get(position).getProcedureName();
	}

	/**
	 * @return the proceduresList
	 */
	public List<SelectItem> getProceduresList() {
		if (proceduresList == null) {
			proceduresList = new ArrayList<SelectItem>();
			list = service.findAll();
			if (list != null && list.size() > 0) {
				for (CommissionExtractionProcedure pro : list) {
					proceduresList.add(new SelectItem(pro
							.getCommissionExtractionProcedureId(), pro
							.getQueryName()));
				}
			}
		}
		return proceduresList;
	}

	/**
	 * @param proceduresList
	 *            the proceduresList to set
	 */
	public void setProceduresList(List<SelectItem> proceduresList) {
		this.proceduresList = proceduresList;
	}

	/**
	 * @return the year
	 */
	public Integer getYear() {
		return year;
	}

	/**
	 * @param year
	 *            the year to set
	 */
	public void setYear(Integer year) {
		this.year = year;
	}

	/**
	 * @return the month
	 */
	public Integer getMonth() {
		return month;
	}

	/**
	 * @param month
	 *            the month to set
	 */
	public void setMonth(Integer month) {
		this.month = month;
	}

	/**
	 * @return the procedure
	 */
	public String getProcedure() {
		return procedure;
	}

	/**
	 * @param procedure
	 *            the procedure to set
	 */
	public void setProcedure(String procedure) {
		this.procedure = procedure;
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
	 * @return the generatedExcelFile
	 */
	public StreamedContent getGeneratedExcelFile() {
		return generatedExcelFile;
	}

	/**
	 * @param generatedExcelFile
	 *            the generatedExcelFile to set
	 */
	public void setGeneratedExcelFile(StreamedContent generatedExcelFile) {
		this.generatedExcelFile = generatedExcelFile;
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
	 * @return the enableExportButton
	 */
	public boolean isEnableExportButton() {
		return enableExportButton;
	}

	/**
	 * @param enableExportButton
	 *            the enableExportButton to set
	 */
	public void setEnableExportButton(boolean enableExportButton) {
		this.enableExportButton = enableExportButton;
	}

	/**
	 * @return the filteredData
	 */
	public List<Map<String, Object>> getFilteredData() {
		return filteredData;
	}

	/**
	 * @param filteredData
	 *            the filteredData to set
	 */
	public void setFilteredData(List<Map<String, Object>> filteredData) {
		this.filteredData = filteredData;
	}

	/**
	 * @return the firstProcedure
	 */
	public boolean isFirstProcedure() {
		return firstProcedure;
	}

	/**
	 * @param firstProcedure
	 *            the firstProcedure to set
	 */
	public void setFirstProcedure(boolean firstProcedure) {
		this.firstProcedure = firstProcedure;
	}

	/**
	 * @return the fifthProcedure
	 */
	public boolean isFifthProcedure() {
		return fifthProcedure;
	}

	/**
	 * @param fifthProcedure
	 *            the fifthProcedure to set
	 */
	public void setFifthProcedure(boolean fifthProcedure) {
		this.fifthProcedure = fifthProcedure;
	}

	/**
	 * @return the thirdProcedure
	 */
	public boolean isThirdProcedure() {
		return thirdProcedure;
	}

	/**
	 * @param thirdProcedure
	 *            the thirdProcedure to set
	 */
	public void setThirdProcedure(boolean thirdProcedure) {
		this.thirdProcedure = thirdProcedure;
	}

	/**
	 * @return the sixthProcedure
	 */
	public boolean isSixthProcedure() {
		return sixthProcedure;
	}

	/**
	 * @param sixthProcedure
	 *            the sixthProcedure to set
	 */
	public void setSixthProcedure(boolean sixthProcedure) {
		this.sixthProcedure = sixthProcedure;
	}

	/**
	 * @return the seventhProcedure
	 */
	public boolean isSeventhProcedure() {
		return seventhProcedure;
	}

	/**
	 * @param seventhProcedure
	 *            the seventhProcedure to set
	 */
	public void setSeventhProcedure(boolean seventhProcedure) {
		this.seventhProcedure = seventhProcedure;
	}

	/**
	 * @return the eighthProcedure
	 */
	public boolean isEighthProcedure() {
		return eighthProcedure;
	}

	/**
	 * @param eighthProcedure
	 *            the eighthProcedure to set
	 */
	public void setEighthProcedure(boolean eighthProcedure) {
		this.eighthProcedure = eighthProcedure;
	}

	/**
	 * @return the ninthProcedure
	 */
	public boolean isNinthProcedure() {
		return ninthProcedure;
	}

	/**
	 * @param ninthProcedure
	 *            the ninthProcedure to set
	 */
	public void setNinthProcedure(boolean ninthProcedure) {
		this.ninthProcedure = ninthProcedure;
	}

	/**
	 * @return the tenthProcedure
	 */
	public boolean isTenthProcedure() {
		return tenthProcedure;
	}

	/**
	 * @param tenthProcedure
	 *            the tenthProcedure to set
	 */
	public void setTenthProcedure(boolean tenthProcedure) {
		this.tenthProcedure = tenthProcedure;
	}

	/**
	 * @return the eleventhProcedure
	 */
	public boolean isEleventhProcedure() {
		return eleventhProcedure;
	}

	/**
	 * @param eleventhProcedure
	 *            the eleventhProcedure to set
	 */
	public void setEleventhProcedure(boolean eleventhProcedure) {
		this.eleventhProcedure = eleventhProcedure;
	}

	/**
	 * @return the twelthProcedure
	 */
	public boolean isTwelvethProcedure() {
		return twelvethProcedure;
	}

	/**
	 * @param twelthProcedure
	 *            the twelthProcedure to set
	 */
	public void setTwelvethProcedure(boolean twelvethProcedure) {
		this.twelvethProcedure = twelvethProcedure;
	}

	/**
	 * @return the thirteenProcedure
	 */
	public boolean isThirteenProcedure() {
		return thirteenProcedure;
	}

	/**
	 * @param thirteenProcedure
	 *            the thirteenProcedure to set
	 */
	public void setThirteenProcedure(boolean thirteenProcedure) {
		this.thirteenProcedure = thirteenProcedure;
	}

	/**
	 * @return the fourteenProcedure
	 */
	public boolean isFourteenProcedure() {
		return fourteenProcedure;
	}

	/**
	 * @param fourteenProcedure
	 *            the fourteenProcedure to set
	 */
	public void setFourteenProcedure(boolean fourteenProcedure) {
		this.fourteenProcedure = fourteenProcedure;
	}

	/**
	 * @return the fifteenProcedure
	 */
	public boolean isFifteenProcedure() {
		return fifteenProcedure;
	}

	/**
	 * @param fifteenProcedure
	 *            the fifteenProcedure to set
	 */
	public void setFifteenProcedure(boolean fifteenProcedure) {
		this.fifteenProcedure = fifteenProcedure;
	}

	/**
	 * @return the sixteenProcedure
	 */
	public boolean isSixteenProcedure() {
		return sixteenProcedure;
	}

	/**
	 * @param sixteenProcedure
	 *            the sixteenProcedure to set
	 */
	public void setSixteenProcedure(boolean sixteenProcedure) {
		this.sixteenProcedure = sixteenProcedure;
	}

	/**
	 * @return the seventeenProcedure
	 */
	public boolean isSeventeenProcedure() {
		return seventeenProcedure;
	}

	/**
	 * @param seventeenProcedure
	 *            the seventeenProcedure to set
	 */
	public void setSeventeenProcedure(boolean seventeenProcedure) {
		this.seventeenProcedure = seventeenProcedure;
	}

	/**
	 * @return the eighteenProcedure
	 */
	public boolean isEighteenProcedure() {
		return eighteenProcedure;
	}

	/**
	 * @param eighteenProcedure
	 *            the eighteenProcedure to set
	 */
	public void setEighteenProcedure(boolean eighteenProcedure) {
		this.eighteenProcedure = eighteenProcedure;
	}

	/**
	 * @return the nineteenProcedure
	 */
	public boolean isNineteenProcedure() {
		return nineteenProcedure;
	}

	/**
	 * @param nineteenProcedure
	 *            the nineteenProcedure to set
	 */
	public void setNineteenProcedure(boolean nineteenProcedure) {
		this.nineteenProcedure = nineteenProcedure;
	}

	/**
	 * @return the fourthProcedure
	 */
	public boolean isFourthProcedure() {
		return fourthProcedure;
	}

	/**
	 * @param fourthProcedure
	 *            the fourthProcedure to set
	 */
	public void setFourthProcedure(boolean fourthProcedure) {
		this.fourthProcedure = fourthProcedure;
	}

	/**
	 * @return the process
	 */
	public CommissionProcess getProcess() {
		return process;
	}

	/**
	 * @param process
	 *            the process to set
	 */
	public void setProcess(CommissionProcess process) {
		this.process = process;
	}

	/**
	 * @return the processDiv
	 */
	public boolean isProcessDiv() {
		return processDiv;
	}

	/**
	 * @param processDiv
	 *            the processDiv to set
	 */
	public void setProcessDiv(boolean processDiv) {
		this.processDiv = processDiv;
	}

	/**
	 * @return the processList
	 */
	public List<CommissionProcess> getProcessList() {
		return processList;
	}

	/**
	 * @param processList
	 *            the processList to set
	 */
	public void setProcessList(List<CommissionProcess> processList) {
		this.processList = processList;
	}

	public String getMonthName(Integer monthNumber) {
		if (monthsName == null) {
			monthsName = getMonthsName();
		}
		return monthsName.get(monthNumber);
	}

	/**
	 * @return the monthsName
	 */
	public List<String> getMonthsName() {
		if (monthsName == null) {
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
	public void setMonthsName(List<String> monthsName) {
		this.monthsName = monthsName;
	}

}
