/**
 * 
 */
package com.disys.analyzer.reports.util;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.disys.analyzer.config.database.DBConnection;
import com.disys.analyzer.config.util.Constants;
import com.disys.analyzer.config.util.FacesUtils;
/**
 * @author Sajid
 * 
 */
public class CommissionUtil {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	private List<CommissionDetails> commDetailsList;
	private List<PeopleSoftJournalData> psJournalDataList;
	private CommissionStatementSetupData statementData;
	private List<CommissionStatementSetupData> statementDataList;

	private int accountingPeriod;
	private int fiscalYear;
	private String userId;
	private String employeeId;
	private int type1CommissionRecordsCount;
	private int type2CommissionRecordsCount;
	private int type3CommissionRecordsCount;
	private String statementPath;
	private String statementName;
	private String statementHostUrl;
	private String pdfStatementUrl;
	
	public CommissionUtil() {
	}

	public CommissionUtil(int ap, int fy, String emplId, String uId) {
		System.out.println("Now Consutructor for Commission Util Called");
		accountingPeriod = ap;
		fiscalYear = fy;
		userId = uId;
		employeeId = emplId;

		statementPath = Constants.tempFormsForEmployeesFileLocation;
		statementHostUrl = Constants.tempFormsForEmployeesUrlLocation;
		System.out.println("CommissionUtil --> statementPath = "
				+ statementPath);
	}

	// 07/29/2016
	// Added New
	public boolean isHybridCommissionCalculationModel() {
		if (fiscalYear > 2016) {
			return true;
		} else if (fiscalYear == 2016) {
			if (accountingPeriod >= 6) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public String getStatementHostUrl() {
		return statementHostUrl;
	}

	public void setStatementHostUrl(String statementHostUrl) {
		this.statementHostUrl = statementHostUrl;
	}

	public String getPdfStatementUrl() {
		return pdfStatementUrl;
	}

	public void setPdfStatementUrl(String pdfStatementUrl) {
		this.pdfStatementUrl = pdfStatementUrl;
	}

	public String getStatementPath() {
		return statementPath;
	}

	public void setStatementPath(String statementPath) {
		this.statementPath = statementPath;
	}

	public String getStatementName() {
		return statementName;
	}

	public void setStatementName(String statementName) {
		this.statementName = statementName;
	}

	public String getYtdCommission() {
		return NumberFormat.getCurrencyInstance(Locale.US).format(
				statementData.getYtdCommission());
	}

	public String getCommissionPersonName() {
		return statementData.getCommissionPersonName();
	}

	public List<CommissionDetails> getCommDetailsList() {
		return commDetailsList;
	}

	public void setCommDetailsList(List<CommissionDetails> commDetailsList) {
		this.commDetailsList = commDetailsList;
	}

	public int getAccountingPeriod() {
		return accountingPeriod;
	}

	public void setAccountingPeriod(int accountingPeriod) {
		this.accountingPeriod = accountingPeriod;
	}

	public int getFiscalYear() {
		return fiscalYear;
	}

	public void setFiscalYear(int fiscalYear) {
		this.fiscalYear = fiscalYear;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getCommissionPeriod() {
		return statementData.getCommissionPeriod();
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getCurrentCommission() {
		return NumberFormat.getCurrencyInstance(Locale.US).format(
				statementData.getCurrentCommission());
	}

	public List<PeopleSoftJournalData> getPsJournalDataList() {
		return psJournalDataList;
	}

	public void setPsJournalDataList(List<PeopleSoftJournalData> psJournalDataList) {
		this.psJournalDataList = psJournalDataList;
	}

	public CommissionStatementSetupData getStatementData() {
		return statementData;
	}

	public void setStatementData(CommissionStatementSetupData statementData) {
		this.statementData = statementData;
	}

	public int getType1CommissionRecordsCount() {
		return type1CommissionRecordsCount;
	}

	public void setType1CommissionRecordsCount(int type1CommissionRecordsCount) {
		this.type1CommissionRecordsCount = type1CommissionRecordsCount;
	}

	public int getType2CommissionRecordsCount() {
		return type2CommissionRecordsCount;
	}

	public void setType2CommissionRecordsCount(int type2CommissionRecordsCount) {
		this.type2CommissionRecordsCount = type2CommissionRecordsCount;
	}

	public int getType3CommissionRecordsCount() {
		return type3CommissionRecordsCount;
	}

	public void setType3CommissionRecordsCount(int type3CommissionRecordsCount) {
		this.type3CommissionRecordsCount = type3CommissionRecordsCount;
	}

	public List<CommissionDetails> loadCommissionDetailsForEmployee() {
		Connection con = null;
		CallableStatement callStmt = null;
		ResultSet rs = null;
		String query = null;
		CommissionDetails cd;

		logger.debug("Employee Id : "+employeeId);
		System.out.println("employeeId = " + employeeId);
		try {
			con = DBConnection.getConnection();
			if (con != null) {
				query = "{call "+ FacesUtils.SCHEMA_WIRELESS +".GetCommissionDetailsForEmployee("
						+ accountingPeriod + ", " + fiscalYear + ", '"
						+ employeeId + "', '" + userId + "')}";

				System.out
						.println("Executing Query in CommissionUtil --> loadCommissionDetailsForEmployee --> (query = "
								+ query);
				
				logger.debug("Executing Query in CommissionUtil --> loadCommissionDetailsForEmployee --> (query = "
						+ query);

				callStmt = con.prepareCall(query);
				Boolean gotResult = callStmt.execute();
				commDetailsList = new ArrayList<CommissionDetails>();
				if(gotResult)
				{
					rs = callStmt.getResultSet();
					type1CommissionRecordsCount = 0;
					type2CommissionRecordsCount = 0;
					type3CommissionRecordsCount = 0;
	
					while (rs.next()) {
						cd = new CommissionDetails();
						cd.setCommissionDetailId(rs.getInt("CommissionDetailId"));
						cd.setProjectId(rs.getString("ProjectId"));
						cd.setAccountingPeriod(rs.getInt("AccountingPeriod"));
						cd.setFiscalYear(rs.getInt("FiscalYear"));
						cd.setRevenue(rs.getDouble("Revenue"));
						cd.setRevenueRowsCount(rs.getInt("RevenueRowsCount"));
						cd.setCost(rs.getDouble("Cost"));
						cd.setCostRowsCount(rs.getInt("CostRowsCount"));
						cd.setExpenseRevenue(rs.getDouble("ExpenseRevenue"));
						cd.setExpenseRevenueRowsCount(rs
								.getInt("ExpenseRevenueRowsCount"));
						cd.setExpenseCost(rs.getDouble("ExpenseCost"));
						cd.setExpenseCostRowsCount(rs
								.getInt("ExpenseCostRowsCount"));
						cd.setMasterProjectRevenue(rs
								.getDouble("MasterProjectRevenue"));
						cd.setMasterProjectCost(rs.getDouble("MasterProjectCost"));
						cd.setMasterProjectExpenseRevenue(rs
								.getDouble("MasterProjectExpenseRevenue"));
						cd.setMasterProjectExpenseCost(rs
								.getDouble("MasterProjectExpenseCost"));
						cd.setDataSource(rs.getString("DataSource"));
						cd.setMasterProjectId(rs.getString("MasterProjectId"));
						cd.setIsMasterProject(rs.getString("IsMasterProject"));
						cd.setBillingType(rs.getString("BillingType"));
						cd.setBusinessTypeName(rs.getString("BusinessTypeName"));
						cd.setIsProjectExistInGL(rs.getString("IsProjectExistInGL"));
						cd.setCustomerId(rs.getString("CustomerId"));
						cd.setCustomerName(rs.getString("CustomerName"));
						cd.setCommissionDiscountRate(rs
								.getDouble("CommissionDiscountRate"));
						cd.setCommissionGARate(rs.getDouble("CommissionGARate"));
						cd.setSumOfAllProjectsCost(rs
								.getDouble("SumOfAllProjectsCost"));
						cd.setCccf(rs.getDouble("Cccf"));
						cd.setCommissionCost(rs.getDouble("CommissionCost"));
						cd.setSumOfAllProjectsRevenue(rs
								.getDouble("SumOfAllProjectsRevenue"));
						cd.setGrossRevenue(rs.getDouble("GrossRevenue"));
						cd.setCommissionDiscountAmount(rs
								.getDouble("CommissionDiscountAmount"));
						cd.setCommissionRevenue(rs.getDouble("CommissionRevenue"));
						cd.setGrossProfit(rs.getDouble("GrossProfit"));
						cd.setGrossProfitPercentage(rs
								.getDouble("GrossProfitPercentage"));
						cd.setCommissionGAAmount(rs.getDouble("CommissionGAAmount"));
						cd.setCommissionProfit(rs.getDouble("CommissionProfit"));
						cd.setCcaf(rs.getDouble("Ccaf"));
						cd.setCategoryCode(rs.getString("CategoryCode"));
						cd.setAssignmentId(rs.getString("AssignmentId"));
						cd.setWorksiteAddressSeq(rs.getInt("WorksiteAddressSeq"));
						cd.setSumOfAllProjectsExpenseCost(rs
								.getDouble("SumOfAllProjectsExpenseCost"));
						cd.setSumOfAllProjectsExpenseRevenue(rs
								.getDouble("SumOfAllProjectsExpenseRevenue"));
						cd.setSumOfAllChildProjectsCost(rs
								.getDouble("SumOfAllChildProjectsCost"));
						cd.setSumOfAllChildProjectsRevenue(rs
								.getDouble("SumOfAllChildProjectsRevenue"));
						cd.setSumOfAllChildProjectsExpenseCost(rs
								.getDouble("SumOfAllChildProjectsExpenseCost"));
						cd.setSumOfAllChildProjectsExpenseRevenue(rs
								.getDouble("SumOfAllChildProjectsExpenseRevenue"));
						cd.setChildAdjustedCost(rs.getDouble("ChildAdjustedCost"));
						cd.setChildAdjustedRevenue(rs
								.getDouble("ChildAdjustedRevenue"));
						cd.setRevenueForDiscount(rs.getDouble("RevenueForDiscount"));
						cd.setBranchCode(rs.getString("BranchCode"));
						cd.setEmployeeType(rs.getString("EmployeeType"));
						cd.setEmplId(rs.getString("EmplId"));
						cd.setEmplRcd(rs.getInt("EmplRcd"));
						cd.setJobTitle(rs.getString("JobTitle"));
						cd.setEmployeeName(rs.getString("EmployeeName"));
						cd.setRoleName(rs.getString("RoleName"));
						cd.setCommissionPersonOprId(rs
								.getString("CommissionPersonOprId"));
						cd.setCommissionPersonOrphan(rs
								.getString("CommissionPersonOrphan"));
						cd.setEstimatedCommissionPercentage(rs
								.getDouble("EstimatedCommissionPercentage"));
						cd.setCommissionPersonName(rs
								.getString("CommissionPersonName"));
						cd.setCommissionPersonEmplId(rs
								.getString("CommissionPersonEmplId"));
						cd.setParentId(rs.getInt("ParentId"));
						cd.setEstimatedGPPercentage(rs
								.getDouble("EstimatedGPPercentage"));
						cd.setAssignmentDescription(rs
								.getString("AssignmentDescription"));
						cd.setActualGrossProfitPercentage(rs
								.getDouble("ActualGrossProfitPercentage"));
						cd.setStartGPPercent(rs.getDouble("StartGPPercent"));
						cd.setEndGPPercent(rs.getDouble("EndGPPercent"));
						cd.setActualCommissionPercentage(rs
								.getDouble("ActualCommissionPercentage"));
						cd.setProRateFactor(rs.getDouble("ProRateFactor"));
						cd.setCommissionEffectiveDate(rs
								.getString("CommissionEffectiveDate"));
						cd.setGrossCommissionAmount(rs
								.getDouble("GrossCommissionAmount"));
						cd.setAccountsCommissionLineDescr(rs
								.getString("AccountsCommissionLineDescr"));
						cd.setCommissionFinancialDetailId(rs
								.getInt("CommissionFinancialDetailId"));
						cd.setCommissionProcessId(rs.getInt("CommissionProcessId"));
						cd.setCommissionPersonDetailId(rs
								.getInt("CommissionPersonDetailId"));
						cd.setExpenseCostId(rs.getInt("ExpenseCostId"));
						cd.setCommissionCostId(rs.getInt("CommissionCostId"));
						cd.setExpenseRevenueId(rs.getInt("ExpenseRevenueId"));
						cd.setCommissionRevenueId(rs.getInt("CommissionRevenueId"));
						cd.setMasterChildProjectDetailId(rs
								.getInt("MasterChildProjectDetailId"));
						cd.setUSStateCode(rs.getString("USStateCode"));
						cd.setBusinessTypeId(rs.getInt("BusinessTypeId"));
						cd.setProjectCategoryId(rs.getInt("ProjectCategoryId"));
						cd.setCommissionPersonRoleId(rs
								.getInt("CommissionPersonRoleId"));
						cd.setEmployeeProRateFactorId(rs
								.getInt("EmployeeProRateFactorId"));
						cd.setCommissionPersonAnalyzerId(rs
								.getInt("CommissionPersonAnalyzerId"));
						cd.setCommissionTierId(rs.getInt("CommissionTierId"));
						cd.setCalculatedCommissionPercentage(rs
								.getDouble("CalculatedCommissionPercentage"));
						cd.setCommissionAdjustmentReason(rs
								.getString("CommissionAdjustmentReason"));
						cd.setCommissionPersonDeptId(rs
								.getString("CommissionPersonDeptId"));
						cd.setCountOfChildProjects(rs
								.getInt("CountOfChildProjects"));
						cd.setIsCommissionPersonTerminated(rs
								.getString("IsCommissionPersonTerminated"));
						cd.setIsSalaryHigherThanLimit(rs
								.getString("IsSalaryHigherThanLimit"));
						cd.setCommissionPersonTerminationDate(rs
								.getString("CommissionPersonTerminationDate"));
						cd.setIsCommissionPersonForQuota(rs
								.getString("IsCommissionPersonForQuota"));
						cd.setProRatedCommissionAmount(rs
								.getDouble("ProRatedCommissionAmount"));
						cd.setProjectGLLocation(rs.getString("ProjectGLLocation"));
						cd.setCommissionPersonLocation(rs
								.getString("CommissionPersonLocation"));
						// cd.setProjectId(rs.getString("ProjectId"));
						cd.setHours(rs.getDouble("Hours"));
						cd.setCommissionEntryType(rs
								.getString("CommissionEntryType"));
						cd.setBonusRevenue(rs.getDouble("BonusRevenue"));
						cd.setBonusCost(rs.getDouble("BonusCost"));
						cd.setBonusProfit(rs.getDouble("BonusProfit"));
						// ADDED NEW COLUMS 20160724
						cd.setChildAdjustedGA(rs.getDouble("ChildAdjustedGA"));
						cd.setNetProfit(rs.getDouble("NetProfit"));
						cd.setTotalCommission(rs.getDouble("TotalCommission"));
						cd.setChildAdjustedProfit(rs
								.getDouble("ChildAdjustedProfit"));
						cd.setCommissionPersonSplitPercentage(rs
								.getDouble("CommissionPersonSplitPercentage"));
						cd.setAnalyzerSubmissionDate(rs
								.getString("AnalyzerSubmissionDate"));
						cd.setAnalyzerOperatingUnit(rs
								.getString("AnalyzerOperatingUnit"));
						cd.setSkillCatgory(rs.getString("SkillCatgory"));
						cd.setAnalyzerClientId(rs.getString("AnalyzerClientId"));
						cd.setAnalyzerBusinessUnit(rs
								.getString("AnalyzerBusinessUnit"));
						cd.setSplitProRateFactor(rs.getDouble("SplitProRateFactor"));
						cd.setTerminationProRateFactor(rs
								.getDouble("TerminationProRateFactor"));
						cd.setAnnualSalaryProRateFactor(rs
								.getDouble("AnnualSalaryProRateFactor"));
						cd.setOperatingUnitOrVertical(rs
								.getString("OperatingUnitOrVertical"));
						cd.setVerticalDescription(rs
								.getString("VerticalDescription"));
						cd.setGLTemplateBusinessUnit(rs
								.getString("GLTemplateBusinessUnit"));
						cd.setGLTemplateLedger(rs.getString("GLTemplateLedger"));
						cd.setGLTemplateAccount(rs.getString("GLTemplateAccount"));
						cd.setGLProduct(rs.getString("GLProduct"));
						cd.setGLProductDescription(rs
								.getString("GLProductDescription"));
						cd.setPSClientId(rs.getString("PSClientId"));
						cd.setPSClientName(rs.getString("PSClientName"));
						cd.setGLTemplateLocation(rs.getString("GLTemplateLocation"));
						cd.setGLTemplateReference(rs
								.getString("GLTemplateReference"));
						cd.setGLTemplateProjectBU(rs
								.getString("GLTemplateProjectBU"));
						cd.setGLTemplateAnalysisType(rs
								.getString("GLTemplateAnalysisType"));
						cd.setActivityId(rs.getString("ActivityId"));
						cd.setOperatingUnitFromVerticalVW(rs
								.getString("OperatingUnitFromVerticalVW"));
						cd.setProductFromVerticalVW(rs
								.getString("ProductFromVerticalVW"));
						cd.setPSClientIdFromVerticalVW(rs
								.getString("PSClientIdFromVerticalVW"));
						cd.setDeptIdFromVerticalVW(rs
								.getString("DeptIdFromVerticalVW"));
						cd.setAnalyzerTerminationDate(rs
								.getString("AnalyzerTerminationDate"));
						cd.setAssignmentEndDate(rs.getString("AssignmentEndDate"));
						cd.setBonusGA(rs.getDouble("BonusGA"));
						cd.setAnalyzerId(rs.getString("AnalyzerId"));
						cd.setBusinessUnitHR(rs.getString("BusinessUnitHR"));
						cd.setGLDeptId(rs.getString("GLDeptId"));
						cd.setAnalyzerBillableCost(rs
								.getDouble("AnalyzerBillableCost"));
						cd.setAnalyzerBillRate(rs.getDouble("AnalyzerBillRate"));
						cd.setAnalyzerBonusAmount(rs
								.getDouble("AnalyzerBonusAmount"));
						cd.setAnalyzerDiscountAmount(rs
								.getDouble("AnalyzerDiscountAmount"));
						cd.setAnalyzerDiscountPercentage(rs
								.getDouble("AnalyzerDiscountPercentage"));
						cd.setAnalyzerEndDate(rs.getString("AnalyzerEndDate"));
						cd.setAnalyzerEquipmentCost(rs
								.getDouble("AnalyzerEquipmentCost"));
						cd.setAnalyzerGA(rs.getDouble("AnalyzerGA"));
						cd.setAnalyzerHealthAmount(rs
								.getDouble("AnalyzerHealthAmount"));
						cd.setAnalyzerImmigrationCost(rs
								.getDouble("AnalyzerImmigrationCost"));
						cd.setAnalyzerK401Amount(rs.getDouble("AnalyzerK401Amount"));
						cd.setAnalyzerLeaveAmount(rs
								.getDouble("AnalyzerLeaveAmount"));
						cd.setAnalyzerOtherAmountPerDeal(rs
								.getDouble("AnalyzerOtherAmountPerDeal"));
						cd.setAnalyzerOtherAmountPerHour(rs
								.getDouble("AnalyzerOtherAmountPerHour"));
						cd.setAnalyzerPayRate(rs.getDouble("AnalyzerPayRate"));
						cd.setAnalyzerPerDiemAmount(rs
								.getDouble("AnalyzerPerDiemAmount"));
						cd.setAnalyzerProfit(rs.getDouble("AnalyzerProfit"));//
						cd.setAnalyzerPTODays(rs.getDouble("AnalyzerPTODays"));
						cd.setAnalyzerReferralFee(rs
								.getDouble("AnalyzerReferralFee"));
						cd.setAnalyzerSpread(rs.getDouble("AnalyzerSpread"));
						cd.setAnalyzerStartDate(rs.getString("AnalyzerStartDate"));
						cd.setAnalyzerTaxesOrFringe(rs
								.getDouble("AnalyzerTaxesOrFringe"));
						cd.setAnalyzerTotalCommissionAmount(rs
								.getDouble("AnalyzerTotalCommissionAmount"));
						cd.setAnalyzerTotalHoursWorked(rs
								.getDouble("AnalyzerTotalHoursWorked"));
						cd.setAssignmentEntryDate(rs
								.getString("AssignmentEntryDate"));
						cd.setAssignmentLastUpdateDate(rs
								.getString("AssignmentLastUpdateDate"));
						cd.setCommissionCalculationModel(rs
								.getString("CommissionCalculationModel"));
						cd.setEffectiveDate(rs.getString("EffectiveDate"));
						cd.setEffectiveSequence(rs.getInt("EffectiveSequence"));
						cd.setEstimatedCommissionRate(rs
								.getDouble("EstimatedCommissionRate"));
						cd.setEstimatedOverallDealCost(rs
								.getDouble("EstimatedOverallDealCost"));
						cd.setOrderId(rs.getString("OrderId"));
						cd.setOrderLine(rs.getInt("OrderLine"));
						cd.setVendorId(rs.getString("VendorId"));
						// Added New Columns 20170307
						cd.setCommissionModelId(rs.getInt("CommissionModelId"));
						cd.setAnalyzerCommissionModelCode(rs
								.getString("AnalyzerCommissionModelCode"));
						cd.setAnalyzerDealType(rs.getString("AnalyzerDealType"));
						cd.setCommissionPersonJobCode(rs
								.getString("CommissionPersonJobCode"));
						cd.setPSJobEffectiveDate(rs.getString("PSJobEffectiveDate"));
						cd.setPSJobEffectiveSequence(rs
								.getInt("PSJobEffectiveSequence"));
						cd.setQuotaEffectiveDate(rs.getString("QuotaEffectiveDate"));
						cd.setQuotaEffectiveSequence(rs
								.getInt("QuotaEffectiveSequence"));
						cd.setYearlyQuotaAttainmentAmount(rs
								.getDouble("YearlyQuotaAttainmentAmount"));
						cd.setQuotaEntryId(rs.getInt("QuotaEntryId"));
						cd.setQuotaCommissionJobCodeId(rs
								.getInt("QuotaCommissionJobCodeId"));
						cd.setQuotaAttainmentCommPercentage(rs
								.getDouble("QuotaAttainmentCommPercentage"));
						cd.setQuotaCommissionRateId(rs
								.getInt("QuotaCommissionRateId"));
						cd.setIsFlatFeeCommissionRecord(rs
								.getString("IsFlatFeeCommissionRecord"));
						cd.setFlatFeeCommissionId(rs.getInt("FlatFeeCommissionId"));
						cd.setFlatFeeCommissionAmount(rs
								.getDouble("FlatFeeCommissionAmount"));
						cd.setIsFlatFeeCommissionAlreadyPaid(rs
								.getString("IsFlatFeeCommissionAlreadyPaid"));
						cd.setIsFlatFeeCommissionPayable(rs
								.getString("IsFlatFeeCommissionPayable"));
						cd.setFlatFeePaymentTrackId(rs
								.getInt("FlatFeePaymentTrackId"));
						cd.setFlatFeeProRateFactor(rs
								.getDouble("FlatFeeProRateFactor"));
						cd.setPreviousYTDAttainedGPQuotaAmount(rs
								.getDouble("PreviousYTDAttainedGPQuotaAmount"));
						cd.setCurrentMonthGPQuotaAmount(rs
								.getDouble("CurrentMonthGPQuotaAmount"));
						cd.setCurrentYTDAttainedGPQuotaAmount(rs
								.getDouble("CurrentYTDAttainedGPQuotaAmount"));
						cd.setProjectGPForQuotaAttainment(rs
								.getDouble("ProjectGPForQuotaAttainment"));
						cd.setEmploymentProRateFactor(rs
								.getDouble("EmploymentProRateFactor"));
						// New Columns for Grid 20170516
						cd.setIsCommPersonEligibleForAcceleratorComm(rs
								.getString("IsCommPersonEligibleForAcceleratorComm"));
						cd.setCurrentAppliedAcceleratorCommPercentage(rs
								.getDouble("CurrentAppliedAcceleratorCommPercentage"));
						cd.setCommissionQuotaAttainmentId(rs
								.getInt("CommissionQuotaAttainmentId"));
						cd.setAdditionalAcceleratorCommissionAmount(rs
								.getDouble("AdditionalAcceleratorCommissionAmount"));
						cd.setGrossCommissionAmountBeforeAccelerator(rs
								.getDouble("GrossCommissionAmountBeforeAccelerator"));
						cd.setAppliedCommissionPaymentPercentage(rs
								.getDouble("AppliedCommissionPaymentPercentage"));
	
						// Set the counters
						if (cd.getBusinessTypeId() == 1
								|| cd.getBusinessTypeId() == 4) {
							type1CommissionRecordsCount++;
						} else if (cd.getBusinessTypeId() == 2) {
							type2CommissionRecordsCount++;
						}
						if (cd.getBusinessTypeId() == 3) {
							type3CommissionRecordsCount++;
						}
						// Add Object to list
						commDetailsList.add(cd);
					}
				}
				return commDetailsList;
			} else {
				System.out
						.println("Connection is NULL in CommissionUtil --> getcommissionDetailsRecords.");
			}
		} catch (Exception ex) {
			System.out
					.println("Exception in getcommissionDetailsRecords method of CommissionUtil.");
			ex.printStackTrace();
		} finally {
			DBConnection.closeConnection(con, callStmt, rs);
		}
		return commDetailsList;
	}

	public List<PeopleSoftJournalData> loadPeopleSoftJournalData(String projectId) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = null;
		PeopleSoftJournalData jd;

		System.out.println("projectId = " + projectId);
		try {
			con = DBConnection.getConnection();
			if (con != null) {
				query = "{call "+ FacesUtils.SCHEMA_WIRELESS +".GetPeopleSoftJournalData("
						+ accountingPeriod + ", " + fiscalYear + ", '"
						+ projectId + "', '" + userId + "')}";

				System.out
						.println("Executing Query in CommissionUtil --> loadPeopleSoftJournalData --> (query = "
								+ query);

				logger.debug("Executing Query in CommissionUtil --> loadPeopleSoftJournalData --> (query = "
								+ query);
				
				pstmt = con.prepareStatement(query);
				rs = pstmt.executeQuery();

				psJournalDataList = new ArrayList<PeopleSoftJournalData>();

				while (rs.next()) {
					jd = new PeopleSoftJournalData();
					jd.setPeopleSoftJournalDataId(rs
							.getInt("PeopleSoftJournalDataId"));
					jd.setAccountingPeriod(rs.getInt("AccountingPeriod"));
					jd.setFiscalYear(rs.getInt("FiscalYear"));
					jd.setJournalId(rs.getString("JournalId"));
					jd.setJournalDate(rs.getString("JournalDate"));
					jd.setJournalLine(rs.getInt("JournalLine"));
					jd.setAccount(rs.getString("Account"));
					jd.setChartField1(rs.getString("ChartField1"));
					jd.setProjectId(rs.getString("ProjectId"));
					jd.setActivityId(rs.getString("ActivityId"));
					jd.setResourceType(rs.getString("ResourceType"));
					jd.setResourceCategory(rs.getString("ResourceCategory"));
					jd.setAnalysisType(rs.getString("AnalysisType"));
					jd.setMonetaryAmount(rs.getDouble("MonetaryAmount"));
					jd.setLineDescription(rs.getString("LineDescription"));
					jd.setSource(rs.getString("Source"));
					jd.setJournalLineRef_VoucherId(rs
							.getString("JournalLineRef_VoucherId"));
					jd.setTransactionRefNumber(rs
							.getString("TransactionRefNumber"));
					jd.setDepartmentId(rs.getString("DepartmentId"));
					jd.setOperatingUnit(rs.getString("OperatingUnit"));
					jd.setBusinessUnit(rs.getString("BusinessUnit"));
					jd.setJournalLineSource(rs.getString("JournalLineSource"));
					jd.setJournalTotalLines(rs.getInt("JournalTotalLines"));
					jd.setPostingDate(rs.getString("PostingDate"));
					jd.setJournalDescription(rs.getString("JournalDescription"));
					jd.setSystemSource(rs.getString("SystemSource"));
					jd.setDescription254(rs.getString("Description254"));
					jd.setCommissionProcessId(rs.getInt("CommissionProcessId"));
					// Add Object to list
					psJournalDataList.add(jd);
				}
			} else {
				System.out
						.println("Connection is NULL in CommissionUtil --> getPeopleSoftJournalData.");
				logger.error("Connection is NULL in CommissionUtil --> getPeopleSoftJournalData.");
			}
			return psJournalDataList;
		} catch (Exception ex) {
			System.out
					.println("Exception in getPeopleSoftJournalData method of CommissionUtil.");
			logger.error("Exception in getPeopleSoftJournalData method of CommissionUtil.");
			ex.printStackTrace();
		} finally {
			DBConnection.closeConnection(con, pstmt, rs);
		}
		return psJournalDataList;
	}

	public int getPeopleSoftJournalDataCount() {
		if (psJournalDataList == null)
			return 0;
		else
			return psJournalDataList.size();
	}

	public int getCommissionDetailsRecordsCount() {
		if (commDetailsList == null)
			return 0;
		else
			return commDetailsList.size();
	}

	public void printCommissionDetailsOnConsole() {
		CommissionDetails cd;

		System.out.println("COMMISSION PRINT ON CONSOLE (RECORDS = "
				+ this.getCommissionDetailsRecordsCount() + ").");
		logger.debug("COMMISSION PRINT ON CONSOLE (RECORDS = "
				+ this.getCommissionDetailsRecordsCount() + ").");
		for (int i = 0; i < this.getCommissionDetailsRecordsCount(); i++) {
			cd = (CommissionDetails) commDetailsList.get(i);
			cd.printOnConsole();
			System.out
					.println("\nPRINTING NEXT RECORD ///////////////////////////////////////////////////////////////// \n");
		}
	}

	public void printJournalDataOnConsole() {
		PeopleSoftJournalData jd;

		System.out.println("PeopleSoftJournalData PRINT ON CONSOLE (RECORDS = "
				+ this.getPeopleSoftJournalDataCount() + ").");
		logger.debug("PeopleSoftJournalData PRINT ON CONSOLE (RECORDS = "
				+ this.getPeopleSoftJournalDataCount() + ").");
		for (int i = 0; i < this.getPeopleSoftJournalDataCount(); i++) {
			jd = (PeopleSoftJournalData) psJournalDataList.get(i);
			jd.printOnConsole1();
			System.out
					.println("\nPRINTING NEXT RECORD ///////////////////////////////////////////////////////////////// \n");
		}
	}

	public void printGridListDataOnConsole(List dataList) {
		Map m;
		String key, value;

		if (dataList == null) {
			System.out.println("List is Empty No records Loaded ");
		} else {
			System.out.println("dataList For Grid Records Count = "
					+ dataList.size() + ").");
			for (int i = 0; i < dataList.size(); i++) {
				m = (Map) dataList.get(i);
				for (Iterator it = m.keySet().iterator(); it.hasNext();) {
					key = (String) it.next();
					value = (String) m.get(key);

					System.out.print("Key = " + key + ", Value = " + value
							+ " , ");
				}
				System.out
						.println("\nPRINTING NEXT RECORD ///////////////////////////////////////////////////////////////// \n");
			}
		}
	}

	public List getCommissionDetailsListForGridData() {
		List list = new ArrayList();
		CommissionDetails cd;
		Map m = null;

		// loadCommissionDetailsForEmployee(); //nned to be called explicitly

		System.out.println("ANALYZER COMMISSION PRINT ON CONSOLE (RECORDS = "
				+ this.getCommissionDetailsRecordsCount() + ").");
		logger.debug("ANALYZER COMMISSION PRINT ON CONSOLE (RECORDS = "
				+ this.getCommissionDetailsRecordsCount() + ").");
		for (int i = 0; i < this.getCommissionDetailsRecordsCount(); i++) {
			m = new HashMap();
			cd = (CommissionDetails) commDetailsList.get(i);

			m.put("commissionDetailId",
					new Double(cd.getCommissionDetailId()).toString());
			// m.put("revenue",
			// NumberFormat.getCurrencyInstance(Locale.US).format(cd.getRevenue()));
			m.put("revenue", new Double(cd.getRevenue()).toString());
			m.put("cost", new Double(cd.getCost()).toString());
			m.put("projectId", cd.getProjectId());
			m.put("masterProjectId", cd.getMasterProjectId());
			m.put("emplId", cd.getEmplId());
			m.put("employeeName", cd.getEmployeeName());
			m.put("commissionDiscountRate",
					new Double(cd.getCommissionDiscountRate()).toString());
			m.put("commissionDiscountAmount",
					new Double(cd.getCommissionDiscountAmount()).toString());
			m.put("commissionRevenue",
					new Double(cd.getCommissionRevenue()).toString());
			m.put("commissionCost",
					new Double(cd.getCommissionCost()).toString());
			m.put("commissionGARate",
					new Double(cd.getCommissionGARate()).toString());
			m.put("commissionGAAmount",
					new Double(cd.getCommissionGAAmount()).toString());
			m.put("expenseRevenue",
					new Double(cd.getExpenseRevenue()).toString());
			m.put("grossProfit", new Double(cd.getGrossProfit()).toString());
			m.put("estimatedGPPercentage",
					new Double(cd.getEstimatedGPPercentage()).toString());
			m.put("estimatedCommissionPercentage",
					new Double(cd.getEstimatedCommissionPercentage())
							.toString());
			m.put("actualCommissionPercentage",
					new Double(cd.getActualCommissionPercentage()).toString());
			m.put("isCommissionPersonForQuota",
					cd.getIsCommissionPersonForQuota());
			m.put("commissionPersonOrphan", cd.getCommissionPersonOrphan());
			m.put("sumOfAllProjectsRevenue",
					new Double(cd.getSumOfAllProjectsRevenue()).toString());
			m.put("sumOfAllProjectsCost",
					new Double(cd.getSumOfAllProjectsCost()).toString());
			m.put("cccf", new Double(cd.getCccf()).toString());
			m.put("ccaf", new Double(cd.getCcaf()).toString());
			m.put("proRateFactor", new Double(cd.getProRateFactor()).toString());
			m.put("proRatedCommissionAmount",
					new Double(cd.getProRatedCommissionAmount()).toString());
			m.put("accountingPeriod",
					new Integer(cd.getAccountingPeriod()).toString());
			m.put("fiscalYear", new Integer(cd.getFiscalYear()).toString());
			m.put("hours", new Double(cd.getHours()).toString());
			m.put("businessTypeName", cd.getBusinessTypeName());
			m.put("masterProjectRevenue",
					new Double(cd.getMasterProjectRevenue()).toString());
			m.put("masterProjectCost",
					new Double(cd.getMasterProjectCost()).toString());
			m.put("customerId", cd.getCustomerId());
			m.put("customerName", cd.getCustomerName());
			m.put("commissionPersonEmplId", cd.getCommissionPersonEmplId());
			m.put("commissionPersonOprId", cd.getCommissionPersonOprId());
			m.put("countOfChildProjects",
					new Integer(cd.getCountOfChildProjects()).toString());
			m.put("grossRevenue", new Double(cd.getGrossRevenue()).toString());
			m.put("actualGrossProfitPercentage",
					new Double(cd.getActualGrossProfitPercentage()).toString());
			m.put("commissionProfit",
					new Double(cd.getCommissionProfit()).toString());
			m.put("categoryCode", cd.getCategoryCode());
			m.put("branchCode", cd.getBranchCode());
			m.put("employeeType", cd.getEmployeeType());
			m.put("jobTitle", cd.getJobTitle());
			m.put("roleName", cd.getRoleName());
			m.put("commissionEffectiveDate", cd.getCommissionEffectiveDate());
			m.put("projectGLLocation", cd.getProjectGLLocation());
			m.put("isCommissionPersonTerminated",
					cd.getIsCommissionPersonTerminated());
			m.put("isSalaryHigherThanLimit", cd.getIsSalaryHigherThanLimit());
			m.put("childAdjustedCost",
					new Double(cd.getChildAdjustedCost()).toString());
			m.put("childAdjustedRevenue",
					new Double(cd.getChildAdjustedRevenue()).toString());
			m.put("grossCommissionAmount",
					new Double(cd.getGrossCommissionAmount()).toString());
			// m.put("commissionAdjustmentReason",
			// cd.getCommissionAdjustmentReason());
			// added new columns 20160727
			m.put("splitProRateFactor",
					new Double(cd.getSplitProRateFactor()).toString());//
			m.put("terminationProRateFactor",
					new Double(cd.getTerminationProRateFactor()).toString());//
			m.put("annualSalaryProRateFactor",
					new Double(cd.getAnnualSalaryProRateFactor()).toString());//
			m.put("commissionPersonSplitPercentage",
					new Double(cd.getCommissionPersonSplitPercentage())
							.toString());//
			m.put("analyzerId", cd.getAnalyzerId());//
			m.put("analyzerBillRate",
					new Double(cd.getAnalyzerBillRate()).toString());//
			m.put("analyzerPayRate",
					new Double(cd.getAnalyzerPayRate()).toString());//
			m.put("commissionCalculationModel",
					cd.getCommissionCalculationModel());//
			m.put("estimatedCommissionRate",
					new Double(cd.getEstimatedCommissionRate()).toString());//
			m.put("orderId", cd.getOrderId());//
			m.put("parentId", new Integer(cd.getParentId()).toString()); //
			m.put("assignmentId", cd.getAssignmentId());//
			m.put("bonusCost", new Double(cd.getBonusCost()).toString());//
			m.put("bonusProfit", new Double(cd.getBonusProfit()).toString());//
			m.put("bonusRevenue", new Double(cd.getBonusRevenue()).toString());//
			m.put("analyzerProfit",
					new Double(cd.getAnalyzerProfit()).toString());
			/*		m.put("childAdjustedGA", new Double(cd.getChildAdjustedGA()).toString());
					m.put("netProfit", new Double(cd.getNetProfit()).toString());
					m.put("totalCommission", new Double(cd.getTotalCommission()).toString());
					m.put("childAdjustedProfit", new Double(cd.getChildAdjustedProfit()).toString()); */
			/*		m.put("analyzerSubmissionDate", cd.getAnalyzerSubmissionDate());
					m.put("analyzerOperatingUnit", cd.getAnalyzerOperatingUnit());
					m.put("skillCatgory", cd.getSkillCatgory());
					m.put("analyzerClientId", cd.getAnalyzerClientId());
					m.put("analyzerBusinessUnit", cd.getAnalyzerBusinessUnit()); */
			/*		m.put("operatingUnitOrVertical", cd.getOperatingUnitOrVertical());
					m.put("verticalDescription", cd.getVerticalDescription());
					m.put("gLTemplateBusinessUnit", cd.getGLTemplateBusinessUnit());
					m.put("gLTemplateLedger", cd.getGLTemplateLedger());
					m.put("gLTemplateAccount", cd.getGLTemplateAccount());
					m.put("gLProduct", cd.getGLProduct());
					m.put("gLProductDescription", cd.getGLProductDescription());
					m.put("pSClientId", cd.getPSClientId());
					m.put("pSClientName", cd.getPSClientName());
					m.put("gLTemplateLocation", cd.getGLTemplateLocation());
					m.put("gLTemplateReference", cd.getGLTemplateReference());
					m.put("gLTemplateProjectBU", cd.getGLTemplateProjectBU());
					m.put("gLTemplateAnalysisType", cd.getGLTemplateAnalysisType());
					m.put("operatingUnitFromVerticalVW", cd.getOperatingUnitFromVerticalVW());
					m.put("productFromVerticalVW", cd.getProductFromVerticalVW());
					m.put("pSClientIdFromVerticalVW", cd.getPSClientIdFromVerticalVW());
					m.put("deptIdFromVerticalVW", cd.getDeptIdFromVerticalVW());
					m.put("analyzerTerminationDate", cd.getAnalyzerTerminationDate());
					m.put("assignmentEndDate", cd.getAssignmentEndDate());
					m.put("bonusGA", new Double(cd.getBonusGA()).toString());	*/
			// m.put("businessUnitHR", cd.getBusinessUnitHR());
			// m.put("gLDeptId", cd.getGLDeptId());
			// m.put("analyzerBillableCost", new
			// Double(cd.getAnalyzerBillableCost()).toString());
			/*		m.put("analyzerPerDiemAmount", new Double(cd.getAnalyzerPerDiemAmount()).toString());			
					m.put("analyzerPTODays", new Double(cd.getAnalyzerPTODays()).toString());
					m.put("analyzerReferralFee", new Double(cd.getAnalyzerReferralFee()).toString());
					m.put("analyzerSpread", new Double(cd.getAnalyzerSpread()).toString());
					m.put("analyzerStartDate", cd.getAnalyzerStartDate());
					m.put("analyzerTaxesOrFringe", new Double(cd.getAnalyzerTaxesOrFringe()).toString());
					m.put("analyzerTotalCommissionAmount", new Double(cd.getAnalyzerTotalCommissionAmount()).toString());
					m.put("analyzerTotalHoursWorked", new Double(cd.getAnalyzerTotalHoursWorked()).toString());
					m.put("assignmentEntryDate", cd.getAssignmentEntryDate());
					m.put("assignmentLastUpdateDate", cd.getAssignmentLastUpdateDate()); */
			// m.put("orderLine", new Integer(cd.getOrderLine()).toString());
			// m.put("vendorId", cd.getVendorId());
			// m.put("estimatedOverallDealCost", new
			// Double(cd.getEstimatedOverallDealCost()).toString());
			// m.put("effectiveDate", cd.getEffectiveDate());
			// m.put("effectiveSequence", new
			// Integer(cd.getEffectiveSequence()).toString());
			/*		m.put("analyzerBonusAmount", new Double(cd.getAnalyzerBonusAmount()).toString());
			m.put("analyzerDiscountAmount", new Double(cd.getAnalyzerDiscountAmount()).toString());
			m.put("analyzerDiscountPercentage", new Double(cd.getAnalyzerDiscountPercentage()).toString());
			m.put("analyzerEndDate", cd.getAnalyzerEndDate());
			m.put("analyzerEquipmentCost", new Double(cd.getAnalyzerEquipmentCost()).toString());
			m.put("analyzerGA", new Double(cd.getAnalyzerGA()).toString());
			m.put("analyzerHealthAmount", new Double(cd.getAnalyzerHealthAmount()).toString());
			m.put("analyzerImmigrationCost", new Double(cd.getAnalyzerImmigrationCost()).toString());
			m.put("analyzerK401Amount", new Double(cd.getAnalyzerK401Amount()).toString());
			m.put("analyzerLeaveAmount", new Double(cd.getAnalyzerLeaveAmount()).toString());
			m.put("analyzerOtherAmountPerDeal", new Double(cd.getAnalyzerOtherAmountPerDeal()).toString());
			m.put("analyzerOtherAmountPerHour", new Double(cd.getAnalyzerOtherAmountPerHour()).toString());*/

			// Added New Columns 20170307
			m.put("analyzerCommissionModelCode",
					cd.getAnalyzerCommissionModelCode());
			m.put("commissionPersonJobCode", cd.getCommissionPersonJobCode());
			m.put("isFlatFeeCommissionRecord",
					cd.getIsFlatFeeCommissionRecord());
			m.put("flatFeeCommissionAmount",
					new Double(cd.getFlatFeeCommissionAmount()).toString());
			m.put("projectGPForQuotaAttainment",
					new Double(cd.getProjectGPForQuotaAttainment()).toString());

			// ADDED NEW COLUMNS IN GRID 20170516 dsiplay in report

			m.put("appliedCommissionPaymentPercentage",
					new Double(cd.getAppliedCommissionPaymentPercentage())
							.toString());
			m.put("additionalAcceleratorCommissionAmount",
					new Double(cd.getAdditionalAcceleratorCommissionAmount())
							.toString());
			m.put("CurrentAppliedAcceleratorCommPercentage",
					new Double(cd.getCurrentAppliedAcceleratorCommPercentage())
							.toString());

			/**
			 * Hours, BusinessTypeName, MasterProjectRevenue, MasterProjectCost,
			 * CustomerId, CustomerName, CommissionPersonEmplid,
			 * CommissionPersonOprId, CountOfChildProjects, GrossRevenue,
			 * ActualGrossProfitPercentage, CommissionProfit, CategoryCode,
			 * BranchCode, EmployeeType, Job Title, RoleName,
			 * CommissionEffectiveDate, ProjectGLLocation,
			 * IsCommissionPersonTerminated, IsSalaryHigherThanLimit,
			 * ChildAdjustCost, ChildAdjustRevenue, GrossCommissionAmount,
			 * CommissionAdjustmentReason
			 * 
			 * Add following to Report as well Hours, BusinessTypeName,
			 * MasterProjectRevenue, MasterProjectCost, CustomerId,
			 * CustomerName, CountOfChildProjects, ActualGrossProfitPercentage,
			 * CommissionProfit, CategoryCode, EmployeeType, Job Title,
			 * RoleName, ProjectGLLocation,
			 * 
			 */

			list.add(m);
		}
		return list;
	}

	public List getJournalDataListForGridData(String projectId) {
		List list = new ArrayList();
		PeopleSoftJournalData pjd;
		Map m = null;

		// loadPeopleSoftJournalData(projectId); //need to be called explicitly

		System.out.println("ANALYZER COMMISSION PRINT ON CONSOLE (RECORDS = "
				+ this.getPeopleSoftJournalDataCount() + ").");
		logger.debug("ANALYZER COMMISSION PRINT ON CONSOLE (RECORDS = "
				+ this.getPeopleSoftJournalDataCount() + ").");
		for (int i = 0; i < this.getPeopleSoftJournalDataCount(); i++) {
			m = new HashMap();
			pjd = (PeopleSoftJournalData) psJournalDataList.get(i);
			m.put("peopleSoftJournalDataId",
					new Integer(pjd.getPeopleSoftJournalDataId()).toString());
			m.put("projectId", pjd.getProjectId());
			m.put("journalId", pjd.getJournalId());
			m.put("journalDate", pjd.getJournalDate());
			m.put("journalLine", new Integer(pjd.getJournalLine()).toString());
			m.put("account", pjd.getAccount());
			m.put("chartField1", pjd.getChartField1());
			m.put("monetaryAmount",
					new Double(pjd.getMonetaryAmount()).toString());
			m.put("resourceType", pjd.getResourceType());
			m.put("transactionRefNumber", pjd.getTransactionRefNumber());
			m.put("lineDescription", pjd.getLineDescription());
			m.put("accountingPeriod",
					new Integer(pjd.getAccountingPeriod()).toString());
			m.put("fiscalYear", new Integer(pjd.getFiscalYear()).toString());
			m.put("activityId", pjd.getActivityId());
			m.put("resourceCategory", pjd.getResourceCategory());
			m.put("analysisType", pjd.getAnalysisType());
			m.put("source", pjd.getSource());
			m.put("journalLineRef_VoucherId", pjd.getJournalLineRef_VoucherId());
			m.put("departmentId", pjd.getDepartmentId());
			m.put("operatingUnit", pjd.getOperatingUnit());
			m.put("journalLineSource", pjd.getJournalLineSource());
			m.put("systemSource", pjd.getSystemSource());
			m.put("journalDescription", pjd.getJournalDescription());

			list.add(m);
		}
		return list;
	}

	public CommissionStatementSetupData loadStatementSetupData() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = null;

		try {
			con = DBConnection.getConnection();
			if (con != null) {
				query = "{call "+ FacesUtils.SCHEMA_WIRELESS +".getCommissionStatementData("
						+ accountingPeriod + ", " + fiscalYear + ", '"
						+ employeeId + "', '" + userId + "')}";

				System.out
						.println("Executing Query in CommissionUtil --> loadHeaderCommissionInfo --> (query = "
								+ query);
				logger.debug("Executing Query in CommissionUtil --> loadHeaderCommissionInfo --> (query = "
								+ query);

				pstmt = con.prepareStatement(query);
				rs = pstmt.executeQuery();

				statementDataList = new ArrayList<CommissionStatementSetupData>();
				
				while (rs.next()) {
					statementData = new CommissionStatementSetupData();
					statementData.setCommissionPersonEmplId(employeeId);

					statementData.setCompanyName(rs.getString("CompanyName"));
					statementData.setReportHeader(rs.getString("ReportHeader"));
					statementData.setCommissionPersonEmplIdLabel(rs
							.getString("CommissionPersonEmplIdLabel"));
					statementData.setCommissionPersonName(rs
							.getString("CommissionPersonName"));
					statementData.setCommissionPersonNameLabel(rs
							.getString("CommissionPersonNameLabel"));
					statementData.setCommissionPeriod(rs
							.getString("CommissionPeriod"));
					statementData.setCommissionPeriodLabel(rs
							.getString("CommissionPeriodLabel"));
					statementData.setCurrentCommission(rs
							.getDouble("CurrentCommission"));
					statementData.setCurrentCommissionLabel(rs
							.getString("CurrentCommissionLabel"));
					statementData.setYtdCommission(rs
							.getDouble("YtdCommission"));
					statementData.setYtdCommissionLabel(rs
							.getString("YtdCommissionLabel"));
					statementData.setSubHeader1(rs.getString("SubHeader1"));
					statementData.setSubHeader2(rs.getString("SubHeader2"));
					statementData.setSubHeader3(rs.getString("SubHeader3"));
					statementData.setSubHeader4(rs.getString("SubHeader4"));
					statementData.setHeaderTableColumn1Label(rs
							.getString("HeaderTableColumn1Label"));
					statementData.setHeaderTableColumn2Label(rs
							.getString("HeaderTableColumn2Label"));
					statementData.setHeaderTableColumn3Label(rs
							.getString("HeaderTableColumn3Label"));
					statementData.setHeaderTableColumn4Label(rs
							.getString("HeaderTableColumn4Label"));
					statementData.setLegendLabel(rs.getString("LegendLabel"));
					statementData.setDetailTableColumnLabel1(rs
							.getString("DetailTableColumnLabel1"));
					statementData.setDetailTableColumnLabel2(rs
							.getString("DetailTableColumnLabel2"));
					statementData.setDetailTableColumnLabel3(rs
							.getString("DetailTableColumnLabel3"));
					statementData.setDetailTableColumnLabel4(rs
							.getString("DetailTableColumnLabel4"));
					statementData.setDetailTableColumnLabel5(rs
							.getString("DetailTableColumnLabel5"));
					statementData.setDetailTableColumnLabel6(rs
							.getString("DetailTableColumnLabel6"));
					statementData.setDetailTableColumnLabel7(rs
							.getString("DetailTableColumnLabel7"));
					statementData.setDetailTableColumnLabel8(rs
							.getString("DetailTableColumnLabel8"));
					statementData.setDetailTableColumnLabel9(rs
							.getString("DetailTableColumnLabel9"));
					statementData.setDetailTableColumnLabel10(rs
							.getString("DetailTableColumnLabel10"));
					statementData.setDetailTableColumnLabel11(rs
							.getString("DetailTableColumnLabel11"));
					statementData.setDetailTableColumnLabel12(rs
							.getString("DetailTableColumnLabel12"));
					statementData.setImageName(rs.getString("ImageName"));
					statementData.setStaffingAndSolutionsCommissionYtd(rs
							.getDouble("StaffingAndSolutionsCommissionYtd"));
					statementData
							.setStaffingAndSolutionsCommissionBalance(rs
									.getDouble("StaffingAndSolutionsCommissionBalance"));
					statementData
							.setStaffingAndSolutionsCommissionCurrent(rs
									.getDouble("StaffingAndSolutionsCommissionCurrent"));
					statementData.setStaffingAndSolutionsCommissionLabel(rs
							.getString("StaffingAndSolutionsCommissionLabel"));
					statementData.setServicesCommissionYtd(rs
							.getDouble("ServicesCommissionYtd"));
					statementData.setServicesCommissionBalance(rs
							.getDouble("ServicesCommissionBalance"));
					statementData.setServicesCommissionCurrent(rs
							.getDouble("ServicesCommissionCurrent"));
					statementData.setServicesCommissionLabel(rs
							.getString("ServicesCommissionLabel"));
					statementData.setTotalCommissionYtd(rs
							.getDouble("TotalCommissionYtd"));
					statementData.setTotalCommissionBalance(rs
							.getDouble("TotalCommissionBalance"));
					statementData.setTotalCommissionCurrent(rs
							.getDouble("TotalCommissionCurrent"));
					statementData.setTotalCommissionLabel(rs
							.getString("TotalCommissionLabel"));
					statementData.setDetailTableColumnLabel4B(rs
							.getString("DetailTableColumnLabel4B")); // -- New
																		// added
																		// Col.
																		// 20170509
					statementData.setDetailTableColumnLabel13(rs
							.getString("DetailTableColumnLabel13")); // -- New
																		// added
																		// Col.
																		// 20170509
					statementDataList.add(statementData);
				}
				if(statementDataList != null && statementDataList.size() > 0){
					return statementDataList.get(0);
				}else{
					return null;
				}
			} else {
				System.out
						.println("Connection is NULL in CommissionUtil --> loadHeaderCommissionInfo.");
				logger.error("Connection is NULL in CommissionUtil --> loadHeaderCommissionInfo.");
			}
		} catch (Exception ex) {
			System.out
					.println("Exception in loadHeaderCommissionInfo method of CommissionUtil.");
			logger.error("Exception in loadHeaderCommissionInfo method of CommissionUtil.");
			ex.printStackTrace();
		} finally {
			DBConnection.closeConnection(con, pstmt, rs);
		}
		return null;
	}

	public void printStatementSetupDataOnConsole() {
		System.out.println("fiscalYear = " + fiscalYear);
		System.out.println("accountingPeriod = " + accountingPeriod);
		System.out.println("userId = " + userId);
		System.out.println("employeeId = " + employeeId);
		System.out.println("this.getAccountingPeriod() = "
				+ this.getAccountingPeriod());
		System.out.println("this.getCommissionPeriod() = "
				+ this.getCommissionPeriod());
		System.out.println("this.getCommissionPersonName() = "
				+ this.getCommissionPersonName());
		System.out.println("this.getCurrentCommission() = "
				+ this.getCurrentCommission());
		System.out.println("this.getEmployeeId() = " + this.getEmployeeId());
		System.out.println("this.getFiscalYear() = " + this.getFiscalYear());
		System.out.println("this.getUserId() = " + this.getUserId());
		System.out.println("this.getYtdCommission() = "
				+ this.getYtdCommission());
		System.out.println("this.getAccountingPeriod() = "
				+ this.getAccountingPeriod());
		System.out.println("this.getType1CommissionRecordsCount() = "
				+ this.getType1CommissionRecordsCount());
		System.out.println("this.getType2CommissionRecordsCount() = "
				+ this.getType2CommissionRecordsCount());
		System.out.println("this.getType3CommissionRecordsCount() = "
				+ this.getType3CommissionRecordsCount());

		statementData.printOnConsole();
	}

	/*public void createPdfStatement() {
		Document document = new Document();
		PdfWriter writer;
		Paragraph pg;
		PdfPTable table;
		PdfPCell cell;
		CommissionStatementSetupData ssd = statementData;

		try {
			System.out.println("Generating Pdf.");

			statementName = generateCommissionStatementFileName();
			pdfStatementUrl = this.statementHostUrl + statementName;
			calibriFont.setSize(18);
			calibriFont.setStyle(Font.BOLD + Font.UNDERLINE);

			document.setPageSize(PageSize.LETTER.rotate());
			// document.setMargins(36, 72, 108, 144);
			// writer.setPdfVersion(PdfWriter.VERSION_1_6);

			writer = PdfWriter.getInstance(document, new FileOutputStream(
					statementPath + statementName));
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
				document.add(getCommissionTable(4, type1CommissionRecordsCount,
						getCommissionDetailsRecordsCount())); // 4 for Staffing
																// Estimation
																// based with
																// Type Id 4
			} else {
				document.add(getCommissionTable(1, type1CommissionRecordsCount,
						getCommissionDetailsRecordsCount())); // 1 for Staffing
																// Actuals based
																// with Type Id
																// 1
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
		} catch (Exception e) {
			System.out.println("Exception in createPdfStatement = "
					+ e.toString());
			e.printStackTrace();
		}
		document.close();
	}*/

	/*public PdfPTable getCommissionTable(int businessTypeId,
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
		List list = new ArrayList();
		CommissionDetails cd;
		int btId;
		CommissionStatementSetupData ssd = statementData;

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
					cd = (CommissionDetails) commDetailsList.get(i);
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
	}*/

	/*private class PdfStatementFooter extends PdfPageEventHelper {
		public PdfStatementFooter() {
		}

		public void onEndPage(PdfWriter writer, Document document) {
			try {
				PdfContentByte cb = writer.getDirectContent();

				cb.saveState();

				String pageNumber = "Page " + writer.getPageNumber();
				float textBase = document.bottom() - 20;
				float textSize = calibriBaseFont.getWidthPoint(pageNumber, 8);
				PdfGState gstate = new PdfGState();

				cb.beginText();
				cb.setFontAndSize(calibriBaseFont, 8);
				cb.setTextMatrix(document.left(), textBase);
				cb.showText(pageNumber);

				// add watermark
				watermarkBaseFont = BaseFont.createFont(BaseFont.TIMES_ROMAN,
						BaseFont.WINANSI, BaseFont.NOT_EMBEDDED);
				cb.setFontAndSize(watermarkBaseFont, 128);
				cb.setColorFill(Color.BLUE);
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
	}*/

	public String generateCommissionStatementFileName() {
		String outputFile;
		String currentDate;
		Calendar cal = Calendar.getInstance();
		String temp;

		outputFile = Integer.toString(cal.get(Calendar.YEAR));
		currentDate = Integer.toString(cal.get(Calendar.YEAR));
		if (cal.get(Calendar.MONTH) <= 8) {
			outputFile += "0" + Integer.toString(cal.get(Calendar.MONTH) + 1); // as
																				// Jan
																				// =
																				// 0
			temp = "0" + Integer.toString(cal.get(Calendar.MONTH) + 1);
		} else {
			outputFile += Integer.toString(cal.get(Calendar.MONTH) + 1); // since
																			// 9+1
																			// =
																			// 10
																			// so
																			// no
																			// prefix
																			// of
																			// 0
																			// required
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

		outputFile += this.getEmployeeId() + "-"
				+ this.getCommissionPersonName().toLowerCase() + "-"
				+ System.currentTimeMillis() + ".pdf";

		// remove spaces if any
		outputFile = outputFile.replaceAll(" ", "");
		currentDate = temp + "/" + currentDate;

		System.out.println("outputFile = " + outputFile);
		return outputFile;
	}

	/**
	 * @return the statementDataList
	 */
	public List<CommissionStatementSetupData> getStatementDataList() {
		return statementDataList;
	}

	/**
	 * @param statementDataList the statementDataList to set
	 */
	public void setStatementDataList(
			List<CommissionStatementSetupData> statementDataList) {
		this.statementDataList = statementDataList;
	}
}
