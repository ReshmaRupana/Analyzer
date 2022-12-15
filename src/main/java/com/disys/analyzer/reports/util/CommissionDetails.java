/**
 * 
 */
package com.disys.analyzer.reports.util;

import java.io.Serializable;

/**
 * @author Sajid
 * 
 */
public class CommissionDetails implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1298476616270249023L;
	private int commissionDetailId; // PK
	private String projectId;
	private int accountingPeriod;
	private int fiscalYear;
	private double revenue;
	private int revenueRowsCount;
	private double cost;
	private int costRowsCount;
	private double expenseRevenue;
	private int expenseRevenueRowsCount;
	private double expenseCost;
	private int expenseCostRowsCount;
	private double masterProjectRevenue;
	private double masterProjectCost;
	private double masterProjectExpenseRevenue;
	private double masterProjectExpenseCost;
	private String dataSource;
	private String masterProjectId;
	private String isMasterProject;
	private String billingType;
	private String businessTypeName;
	private String isProjectExistInGL;
	private String customerId;
	private String customerName;
	private double commissionDiscountRate;
	private double commissionGARate;
	private double sumOfAllProjectsCost;
	private double cccf;
	private double commissionCost;
	private double sumOfAllProjectsRevenue;
	private double grossRevenue;
	private double commissionDiscountAmount;
	private double commissionRevenue;
	private double grossProfit;
	private double grossProfitPercentage;
	private double commissionGAAmount;
	private double commissionProfit;
	private double ccaf;
	private String categoryCode;
	private String assignmentId;
	private int worksiteAddressSeq;
	private double sumOfAllProjectsExpenseCost;
	private double sumOfAllProjectsExpenseRevenue;
	private double sumOfAllChildProjectsCost;
	private double sumOfAllChildProjectsRevenue;
	private double sumOfAllChildProjectsExpenseCost;
	private double sumOfAllChildProjectsExpenseRevenue;
	private double childAdjustedCost;
	private double childAdjustedRevenue;
	private double revenueForDiscount;
	private String branchCode;
	private String employeeType;
	private String emplId;
	private int emplRcd;
	private String jobTitle;
	private String employeeName;
	private String roleName;
	private String commissionPersonOprId;
	private String commissionPersonOrphan;
	private double estimatedCommissionPercentage;
	private String commissionPersonName;
	private String commissionPersonEmplId;
	private int parentId;
	private double estimatedGPPercentage;
	private String assignmentDescription;
	private double actualGrossProfitPercentage;
	private double startGPPercent;
	private double endGPPercent;
	private double actualCommissionPercentage;
	private double proRateFactor;
	private String commissionEffectiveDate;
	private double grossCommissionAmount;
	private String accountsCommissionLineDescr;
	private int commissionFinancialDetailId; // FK
	private int commissionProcessId;
	private int commissionPersonDetailId;
	private int expenseCostId;
	private int commissionCostId;
	private int expenseRevenueId;
	private int commissionRevenueId;
	private int masterChildProjectDetailId;
	private String uSStateCode;
	private int businessTypeId;
	private int projectCategoryId;
	private int commissionPersonRoleId;
	private int employeeProRateFactorId;
	private int commissionPersonAnalyzerId;
	private int commissionTierId; // FK
	private double calculatedCommissionPercentage;
	private String commissionAdjustmentReason;
	private String commissionPersonDeptId;
	private int countOfChildProjects;
	private String isCommissionPersonTerminated;
	private String isSalaryHigherThanLimit;
	private String commissionPersonTerminationDate;
	private String isCommissionPersonForQuota;
	private double proRatedCommissionAmount;
	private String projectGLLocation;
	private String commissionPersonLocation;
	private double hours;
	private String commissionEntryType;
	private double bonusRevenue;
	private double bonusCost;
	private double bonusProfit;
	// added new columns 20160724
	private double childAdjustedGA;
	private double netProfit;
	private double totalCommission;
	private double childAdjustedProfit;
	private double commissionPersonSplitPercentage;
	private String analyzerSubmissionDate;
	private String analyzerOperatingUnit;
	private String skillCatgory;
	private String analyzerClientId;
	private String analyzerBusinessUnit;
	private double splitProRateFactor;
	private double terminationProRateFactor;
	private double annualSalaryProRateFactor;
	private String operatingUnitOrVertical;
	private String verticalDescription;
	private String gLTemplateBusinessUnit;
	private String gLTemplateLedger;
	private String gLTemplateAccount;
	private String gLProduct;
	private String gLProductDescription;
	private String pSClientId;
	private String pSClientName;
	private String gLTemplateLocation;
	private String gLTemplateReference;
	private String gLTemplateProjectBU;
	private String gLTemplateAnalysisType;
	private String activityId;
	private String operatingUnitFromVerticalVW;
	private String productFromVerticalVW;
	private String pSClientIdFromVerticalVW;
	private String deptIdFromVerticalVW;
	private String analyzerTerminationDate;
	private String assignmentEndDate;
	private double bonusGA;
	private String analyzerId;
	private String businessUnitHR;
	private String gLDeptId;
	private double analyzerBillableCost;
	private double analyzerBillRate;
	private double analyzerBonusAmount;
	private double analyzerDiscountAmount;
	private double analyzerDiscountPercentage;
	private String analyzerEndDate;
	private double analyzerEquipmentCost;
	private double analyzerGA;
	private double analyzerHealthAmount;
	private double analyzerImmigrationCost;
	private double analyzerK401Amount;
	private double analyzerLeaveAmount;
	private double analyzerOtherAmountPerDeal;
	private double analyzerOtherAmountPerHour;
	private double analyzerPayRate;
	private double analyzerPerDiemAmount;
	private double analyzerProfit;
	private double analyzerPTODays;
	private double analyzerReferralFee;
	private double analyzerSpread;
	private String analyzerStartDate;
	private double analyzerTaxesOrFringe;
	private double analyzerTotalCommissionAmount;
	private double analyzerTotalHoursWorked;
	private String assignmentEntryDate;
	private String assignmentLastUpdateDate;
	private String commissionCalculationModel;
	private String effectiveDate;
	private int effectiveSequence;
	private double estimatedCommissionRate;
	private double estimatedOverallDealCost;
	private String orderId;
	private int orderLine;
	private String vendorId;
	// Added New Columns 20170307
	private int commissionModelId;
	private String analyzerCommissionModelCode;
	private String analyzerDealType;
	private String commissionPersonJobCode;
	private String pSJobEffectiveDate;
	private int pSJobEffectiveSequence;
	private String quotaEffectiveDate;
	private int quotaEffectiveSequence;
	private double yearlyQuotaAttainmentAmount;
	private int quotaEntryId;
	private int quotaCommissionJobCodeId;
	private double quotaAttainmentCommPercentage;
	private int quotaCommissionRateId;
	private String isFlatFeeCommissionRecord;
	private int flatFeeCommissionId;
	private double flatFeeCommissionAmount;
	private String isFlatFeeCommissionAlreadyPaid;
	private String isFlatFeeCommissionPayable;
	private int flatFeePaymentTrackId;
	private double flatFeeProRateFactor;
	private double previousYTDAttainedGPQuotaAmount;
	private double currentMonthGPQuotaAmount;
	private double currentYTDAttainedGPQuotaAmount;
	private double projectGPForQuotaAttainment;
	private double employmentProRateFactor;
	// Added New Columns in Grid 20170515
	private String isCommPersonEligibleForAcceleratorComm;
	private double currentAppliedAcceleratorCommPercentage;
	private int commissionQuotaAttainmentId;
	private double additionalAcceleratorCommissionAmount;
	private double grossCommissionAmountBeforeAccelerator;
	private double appliedCommissionPaymentPercentage;

	public CommissionDetails() {
	}

	public int getCommissionDetailId() {
		return commissionDetailId;
	}

	public void setCommissionDetailId(int commissionDetailId) {
		this.commissionDetailId = commissionDetailId;
	}

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
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

	public double getRevenue() {
		return revenue;
	}

	public void setRevenue(double revenue) {
		this.revenue = revenue;
	}

	public int getRevenueRowsCount() {
		return revenueRowsCount;
	}

	public void setRevenueRowsCount(int revenueRowsCount) {
		this.revenueRowsCount = revenueRowsCount;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public int getCostRowsCount() {
		return costRowsCount;
	}

	public void setCostRowsCount(int costRowsCount) {
		this.costRowsCount = costRowsCount;
	}

	public double getExpenseRevenue() {
		return expenseRevenue;
	}

	public void setExpenseRevenue(double expenseRevenue) {
		this.expenseRevenue = expenseRevenue;
	}

	public int getExpenseRevenueRowsCount() {
		return expenseRevenueRowsCount;
	}

	public void setExpenseRevenueRowsCount(int expenseRevenueRowsCount) {
		this.expenseRevenueRowsCount = expenseRevenueRowsCount;
	}

	public double getExpenseCost() {
		return expenseCost;
	}

	public void setExpenseCost(double expenseCost) {
		this.expenseCost = expenseCost;
	}

	public int getExpenseCostRowsCount() {
		return expenseCostRowsCount;
	}

	public void setExpenseCostRowsCount(int expenseCostRowsCount) {
		this.expenseCostRowsCount = expenseCostRowsCount;
	}

	public double getMasterProjectRevenue() {
		return masterProjectRevenue;
	}

	public void setMasterProjectRevenue(double masterProjectRevenue) {
		this.masterProjectRevenue = masterProjectRevenue;
	}

	public double getMasterProjectCost() {
		return masterProjectCost;
	}

	public void setMasterProjectCost(double masterProjectCost) {
		this.masterProjectCost = masterProjectCost;
	}

	public double getMasterProjectExpenseRevenue() {
		return masterProjectExpenseRevenue;
	}

	public void setMasterProjectExpenseRevenue(
			double masterProjectExpenseRevenue) {
		this.masterProjectExpenseRevenue = masterProjectExpenseRevenue;
	}

	public double getMasterProjectExpenseCost() {
		return masterProjectExpenseCost;
	}

	public void setMasterProjectExpenseCost(double masterProjectExpenseCost) {
		this.masterProjectExpenseCost = masterProjectExpenseCost;
	}

	public String getDataSource() {
		return dataSource;
	}

	public void setDataSource(String dataSource) {
		this.dataSource = dataSource;
	}

	public String getMasterProjectId() {
		return masterProjectId;
	}

	public void setMasterProjectId(String masterProjectId) {
		this.masterProjectId = masterProjectId;
	}

	public String getIsMasterProject() {
		return isMasterProject;
	}

	public void setIsMasterProject(String isMasterProject) {
		this.isMasterProject = isMasterProject;
	}

	public String getBillingType() {
		return billingType;
	}

	public void setBillingType(String billingType) {
		this.billingType = billingType;
	}

	public String getBusinessTypeName() {
		return businessTypeName;
	}

	public void setBusinessTypeName(String businessTypeName) {
		this.businessTypeName = businessTypeName;
	}

	public String getIsProjectExistInGL() {
		return isProjectExistInGL;
	}

	public void setIsProjectExistInGL(String isProjectExistInGL) {
		this.isProjectExistInGL = isProjectExistInGL;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public double getCommissionDiscountRate() {
		return commissionDiscountRate;
	}

	public void setCommissionDiscountRate(double commissionDiscountRate) {
		this.commissionDiscountRate = commissionDiscountRate;
	}

	public double getCommissionGARate() {
		return commissionGARate;
	}

	public void setCommissionGARate(double commissionGARate) {
		this.commissionGARate = commissionGARate;
	}

	public double getSumOfAllProjectsCost() {
		return sumOfAllProjectsCost;
	}

	public void setSumOfAllProjectsCost(double sumOfAllProjectsCost) {
		this.sumOfAllProjectsCost = sumOfAllProjectsCost;
	}

	public double getCommissionCost() {
		return commissionCost;
	}

	public void setCommissionCost(double commissionCost) {
		this.commissionCost = commissionCost;
	}

	public double getSumOfAllProjectsRevenue() {
		return sumOfAllProjectsRevenue;
	}

	public void setSumOfAllProjectsRevenue(double sumOfAllProjectsRevenue) {
		this.sumOfAllProjectsRevenue = sumOfAllProjectsRevenue;
	}

	public double getGrossRevenue() {
		return grossRevenue;
	}

	public void setGrossRevenue(double grossRevenue) {
		this.grossRevenue = grossRevenue;
	}

	public double getCommissionDiscountAmount() {
		return commissionDiscountAmount;
	}

	public void setCommissionDiscountAmount(double commissionDiscountAmount) {
		this.commissionDiscountAmount = commissionDiscountAmount;
	}

	public double getCommissionRevenue() {
		return commissionRevenue;
	}

	public void setCommissionRevenue(double commissionRevenue) {
		this.commissionRevenue = commissionRevenue;
	}

	public double getGrossProfit() {
		return grossProfit;
	}

	public void setGrossProfit(double grossProfit) {
		this.grossProfit = grossProfit;
	}

	public double getGrossProfitPercentage() {
		return grossProfitPercentage;
	}

	public void setGrossProfitPercentage(double grossProfitPercentage) {
		this.grossProfitPercentage = grossProfitPercentage;
	}

	public double getCommissionGAAmount() {
		return commissionGAAmount;
	}

	public void setCommissionGAAmount(double commissionGAAmount) {
		this.commissionGAAmount = commissionGAAmount;
	}

	public double getCommissionProfit() {
		return commissionProfit;
	}

	public void setCommissionProfit(double commissionProfit) {
		this.commissionProfit = commissionProfit;
	}

	public String getCategoryCode() {
		return categoryCode;
	}

	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}

	public String getAssignmentId() {
		return assignmentId;
	}

	public void setAssignmentId(String assignmentId) {
		this.assignmentId = assignmentId;
	}

	public int getWorksiteAddressSeq() {
		return worksiteAddressSeq;
	}

	public void setWorksiteAddressSeq(int worksiteAddressSeq) {
		this.worksiteAddressSeq = worksiteAddressSeq;
	}

	public double getSumOfAllProjectsExpenseCost() {
		return sumOfAllProjectsExpenseCost;
	}

	public void setSumOfAllProjectsExpenseCost(
			double sumOfAllProjectsExpenseCost) {
		this.sumOfAllProjectsExpenseCost = sumOfAllProjectsExpenseCost;
	}

	public double getSumOfAllProjectsExpenseRevenue() {
		return sumOfAllProjectsExpenseRevenue;
	}

	public void setSumOfAllProjectsExpenseRevenue(
			double sumOfAllProjectsExpenseRevenue) {
		this.sumOfAllProjectsExpenseRevenue = sumOfAllProjectsExpenseRevenue;
	}

	public double getSumOfAllChildProjectsCost() {
		return sumOfAllChildProjectsCost;
	}

	public void setSumOfAllChildProjectsCost(double sumOfAllChildProjectsCost) {
		this.sumOfAllChildProjectsCost = sumOfAllChildProjectsCost;
	}

	public double getSumOfAllChildProjectsRevenue() {
		return sumOfAllChildProjectsRevenue;
	}

	public void setSumOfAllChildProjectsRevenue(
			double sumOfAllChildProjectsRevenue) {
		this.sumOfAllChildProjectsRevenue = sumOfAllChildProjectsRevenue;
	}

	public double getSumOfAllChildProjectsExpenseCost() {
		return sumOfAllChildProjectsExpenseCost;
	}

	public void setSumOfAllChildProjectsExpenseCost(
			double sumOfAllChildProjectsExpenseCost) {
		this.sumOfAllChildProjectsExpenseCost = sumOfAllChildProjectsExpenseCost;
	}

	public double getSumOfAllChildProjectsExpenseRevenue() {
		return sumOfAllChildProjectsExpenseRevenue;
	}

	public void setSumOfAllChildProjectsExpenseRevenue(
			double sumOfAllChildProjectsExpenseRevenue) {
		this.sumOfAllChildProjectsExpenseRevenue = sumOfAllChildProjectsExpenseRevenue;
	}

	public double getChildAdjustedCost() {
		return childAdjustedCost;
	}

	public void setChildAdjustedCost(double childAdjustedCost) {
		this.childAdjustedCost = childAdjustedCost;
	}

	public double getChildAdjustedRevenue() {
		return childAdjustedRevenue;
	}

	public void setChildAdjustedRevenue(double childAdjustedRevenue) {
		this.childAdjustedRevenue = childAdjustedRevenue;
	}

	public double getRevenueForDiscount() {
		return revenueForDiscount;
	}

	public void setRevenueForDiscount(double revenueForDiscount) {
		this.revenueForDiscount = revenueForDiscount;
	}

	public String getBranchCode() {
		return branchCode;
	}

	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}

	public String getEmployeeType() {
		return employeeType;
	}

	public void setEmployeeType(String employeeType) {
		this.employeeType = employeeType;
	}

	public String getEmplId() {
		return emplId;
	}

	public void setEmplId(String emplId) {
		this.emplId = emplId;
	}

	public int getEmplRcd() {
		return emplRcd;
	}

	public void setEmplRcd(int emplRcd) {
		this.emplRcd = emplRcd;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getCommissionPersonOprId() {
		return commissionPersonOprId;
	}

	public void setCommissionPersonOprId(String commissionPersonOprId) {
		this.commissionPersonOprId = commissionPersonOprId;
	}

	public String getCommissionPersonOrphan() {
		return commissionPersonOrphan;
	}

	public void setCommissionPersonOrphan(String commissionPersonOrphan) {
		this.commissionPersonOrphan = commissionPersonOrphan;
	}

	public double getEstimatedCommissionPercentage() {
		return estimatedCommissionPercentage;
	}

	public void setEstimatedCommissionPercentage(
			double estimatedCommissionPercentage) {
		this.estimatedCommissionPercentage = estimatedCommissionPercentage;
	}

	public String getCommissionPersonName() {
		return commissionPersonName;
	}

	public void setCommissionPersonName(String commissionPersonName) {
		this.commissionPersonName = commissionPersonName;
	}

	public String getCommissionPersonEmplId() {
		return commissionPersonEmplId;
	}

	public void setCommissionPersonEmplId(String commissionPersonEmplId) {
		this.commissionPersonEmplId = commissionPersonEmplId;
	}

	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	public double getEstimatedGPPercentage() {
		return estimatedGPPercentage;
	}

	public void setEstimatedGPPercentage(double estimatedGPPercentage) {
		this.estimatedGPPercentage = estimatedGPPercentage;
	}

	public String getAssignmentDescription() {
		return assignmentDescription;
	}

	public void setAssignmentDescription(String assignmentDescription) {
		this.assignmentDescription = assignmentDescription;
	}

	public double getActualGrossProfitPercentage() {
		return actualGrossProfitPercentage;
	}

	public void setActualGrossProfitPercentage(
			double actualGrossProfitPercentage) {
		this.actualGrossProfitPercentage = actualGrossProfitPercentage;
	}

	public double getStartGPPercent() {
		return startGPPercent;
	}

	public void setStartGPPercent(double startGPPercent) {
		this.startGPPercent = startGPPercent;
	}

	public double getEndGPPercent() {
		return endGPPercent;
	}

	public void setEndGPPercent(double endGPPercent) {
		this.endGPPercent = endGPPercent;
	}

	public double getActualCommissionPercentage() {
		return actualCommissionPercentage;
	}

	public void setActualCommissionPercentage(double actualCommissionPercentage) {
		this.actualCommissionPercentage = actualCommissionPercentage;
	}

	public double getProRateFactor() {
		return proRateFactor;
	}

	public void setProRateFactor(double proRateFactor) {
		this.proRateFactor = proRateFactor;
	}

	public String getCommissionEffectiveDate() {
		return commissionEffectiveDate;
	}

	public void setCommissionEffectiveDate(String commissionEffectiveDate) {
		this.commissionEffectiveDate = commissionEffectiveDate;
	}

	public double getGrossCommissionAmount() {
		return grossCommissionAmount;
	}

	public void setGrossCommissionAmount(double grossCommissionAmount) {
		this.grossCommissionAmount = grossCommissionAmount;
	}

	public String getAccountsCommissionLineDescr() {
		return accountsCommissionLineDescr;
	}

	public void setAccountsCommissionLineDescr(
			String accountsCommissionLineDescr) {
		this.accountsCommissionLineDescr = accountsCommissionLineDescr;
	}

	public int getCommissionFinancialDetailId() {
		return commissionFinancialDetailId;
	}

	public void setCommissionFinancialDetailId(int commissionFinancialDetailId) {
		this.commissionFinancialDetailId = commissionFinancialDetailId;
	}

	public int getCommissionProcessId() {
		return commissionProcessId;
	}

	public void setCommissionProcessId(int commissionProcessId) {
		this.commissionProcessId = commissionProcessId;
	}

	public int getCommissionPersonDetailId() {
		return commissionPersonDetailId;
	}

	public void setCommissionPersonDetailId(int commissionPersonDetailId) {
		this.commissionPersonDetailId = commissionPersonDetailId;
	}

	public int getExpenseCostId() {
		return expenseCostId;
	}

	public void setExpenseCostId(int expenseCostId) {
		this.expenseCostId = expenseCostId;
	}

	public int getCommissionCostId() {
		return commissionCostId;
	}

	public void setCommissionCostId(int commissionCostId) {
		this.commissionCostId = commissionCostId;
	}

	public int getExpenseRevenueId() {
		return expenseRevenueId;
	}

	public void setExpenseRevenueId(int expenseRevenueId) {
		this.expenseRevenueId = expenseRevenueId;
	}

	public int getCommissionRevenueId() {
		return commissionRevenueId;
	}

	public void setCommissionRevenueId(int commissionRevenueId) {
		this.commissionRevenueId = commissionRevenueId;
	}

	public int getMasterChildProjectDetailId() {
		return masterChildProjectDetailId;
	}

	public void setMasterChildProjectDetailId(int masterChildProjectDetailId) {
		this.masterChildProjectDetailId = masterChildProjectDetailId;
	}

	public int getBusinessTypeId() {
		return businessTypeId;
	}

	public void setBusinessTypeId(int businessTypeId) {
		this.businessTypeId = businessTypeId;
	}

	public int getProjectCategoryId() {
		return projectCategoryId;
	}

	public void setProjectCategoryId(int projectCategoryId) {
		this.projectCategoryId = projectCategoryId;
	}

	public int getCommissionPersonRoleId() {
		return commissionPersonRoleId;
	}

	public void setCommissionPersonRoleId(int commissionPersonRoleId) {
		this.commissionPersonRoleId = commissionPersonRoleId;
	}

	public int getEmployeeProRateFactorId() {
		return employeeProRateFactorId;
	}

	public void setEmployeeProRateFactorId(int employeeProRateFactorId) {
		this.employeeProRateFactorId = employeeProRateFactorId;
	}

	public int getCommissionPersonAnalyzerId() {
		return commissionPersonAnalyzerId;
	}

	public void setCommissionPersonAnalyzerId(int commissionPersonAnalyzerId) {
		this.commissionPersonAnalyzerId = commissionPersonAnalyzerId;
	}

	public int getCommissionTierId() {
		return commissionTierId;
	}

	public void setCommissionTierId(int commissionTierId) {
		this.commissionTierId = commissionTierId;
	}

	public double getCalculatedCommissionPercentage() {
		return calculatedCommissionPercentage;
	}

	public void setCalculatedCommissionPercentage(
			double calculatedCommissionPercentage) {
		this.calculatedCommissionPercentage = calculatedCommissionPercentage;
	}

	public String getCommissionAdjustmentReason() {
		return commissionAdjustmentReason;
	}

	public void setCommissionAdjustmentReason(String commissionAdjustmentReason) {
		this.commissionAdjustmentReason = commissionAdjustmentReason;
	}

	public String getCommissionPersonDeptId() {
		return commissionPersonDeptId;
	}

	public void setCommissionPersonDeptId(String commissionPersonDeptId) {
		this.commissionPersonDeptId = commissionPersonDeptId;
	}

	public int getCountOfChildProjects() {
		return countOfChildProjects;
	}

	public void setCountOfChildProjects(int countOfChildProjects) {
		this.countOfChildProjects = countOfChildProjects;
	}

	public String getIsCommissionPersonTerminated() {
		return isCommissionPersonTerminated;
	}

	public void setIsCommissionPersonTerminated(
			String isCommissionPersonTerminated) {
		this.isCommissionPersonTerminated = isCommissionPersonTerminated;
	}

	public String getIsSalaryHigherThanLimit() {
		return isSalaryHigherThanLimit;
	}

	public void setIsSalaryHigherThanLimit(String isSalaryHigherThanLimit) {
		this.isSalaryHigherThanLimit = isSalaryHigherThanLimit;
	}

	public String getCommissionPersonTerminationDate() {
		return commissionPersonTerminationDate;
	}

	public void setCommissionPersonTerminationDate(
			String commissionPersonTerminationDate) {
		this.commissionPersonTerminationDate = commissionPersonTerminationDate;
	}

	public String getIsCommissionPersonForQuota() {
		return isCommissionPersonForQuota;
	}

	public void setIsCommissionPersonForQuota(String isCommissionPersonForQuota) {
		this.isCommissionPersonForQuota = isCommissionPersonForQuota;
	}

	public double getProRatedCommissionAmount() {
		return proRatedCommissionAmount;
	}

	public void setProRatedCommissionAmount(double proRatedCommissionAmount) {
		this.proRatedCommissionAmount = proRatedCommissionAmount;
	}

	public String getProjectGLLocation() {
		return projectGLLocation;
	}

	public void setProjectGLLocation(String projectGLLocation) {
		this.projectGLLocation = projectGLLocation;
	}

	public String getCommissionPersonLocation() {
		return commissionPersonLocation;
	}

	public void setCommissionPersonLocation(String commissionPersonLocation) {
		this.commissionPersonLocation = commissionPersonLocation;
	}

	public double getCccf() {
		return cccf;
	}

	public void setCccf(double cccf) {
		this.cccf = cccf;
	}

	public double getCcaf() {
		return ccaf;
	}

	public void setCcaf(double ccaf) {
		this.ccaf = ccaf;
	}

	public String getUSStateCode() {
		return uSStateCode;
	}

	public void setUSStateCode(String uSStateCode) {
		this.uSStateCode = uSStateCode;
	}

	public double getHours() {
		return hours;
	}

	public void setHours(double hours) {
		this.hours = hours;
	}

	public String getCommissionEntryType() {
		return commissionEntryType;
	}

	public void setCommissionEntryType(String commissionEntryType) {
		this.commissionEntryType = commissionEntryType;
	}

	public double getBonusRevenue() {
		return bonusRevenue;
	}

	public void setBonusRevenue(double bonusRevenue) {
		this.bonusRevenue = bonusRevenue;
	}

	public double getBonusCost() {
		return bonusCost;
	}

	public void setBonusCost(double bonusCost) {
		this.bonusCost = bonusCost;
	}

	public double getBonusProfit() {
		return bonusProfit;
	}

	public void setBonusProfit(double bonusProfit) {
		this.bonusProfit = bonusProfit;
	}

	// added new columns 20160724

	public double getChildAdjustedGA() {
		return childAdjustedGA;
	}

	public void setChildAdjustedGA(double childAdjustedGA) {
		this.childAdjustedGA = childAdjustedGA;
	}

	public double getNetProfit() {
		return netProfit;
	}

	public void setNetProfit(double netProfit) {
		this.netProfit = netProfit;
	}

	public double getTotalCommission() {
		return totalCommission;
	}

	public void setTotalCommission(double totalCommission) {
		this.totalCommission = totalCommission;
	}

	public double getChildAdjustedProfit() {
		return childAdjustedProfit;
	}

	public void setChildAdjustedProfit(double childAdjustedProfit) {
		this.childAdjustedProfit = childAdjustedProfit;
	}

	public double getCommissionPersonSplitPercentage() {
		return commissionPersonSplitPercentage;
	}

	public void setCommissionPersonSplitPercentage(
			double commissionPersonSplitPercentage) {
		this.commissionPersonSplitPercentage = commissionPersonSplitPercentage;
	}

	public String getAnalyzerSubmissionDate() {
		return analyzerSubmissionDate;
	}

	public void setAnalyzerSubmissionDate(String analyzerSubmissionDate) {
		this.analyzerSubmissionDate = analyzerSubmissionDate;
	}

	public String getAnalyzerOperatingUnit() {
		return analyzerOperatingUnit;
	}

	public void setAnalyzerOperatingUnit(String analyzerOperatingUnit) {
		this.analyzerOperatingUnit = analyzerOperatingUnit;
	}

	public String getSkillCatgory() {
		return skillCatgory;
	}

	public void setSkillCatgory(String skillCatgory) {
		this.skillCatgory = skillCatgory;
	}

	public String getAnalyzerClientId() {
		return analyzerClientId;
	}

	public void setAnalyzerClientId(String analyzerClientId) {
		this.analyzerClientId = analyzerClientId;
	}

	public String getAnalyzerBusinessUnit() {
		return analyzerBusinessUnit;
	}

	public void setAnalyzerBusinessUnit(String analyzerBusinessUnit) {
		this.analyzerBusinessUnit = analyzerBusinessUnit;
	}

	public double getSplitProRateFactor() {
		return splitProRateFactor;
	}

	public void setSplitProRateFactor(double splitProRateFactor) {
		this.splitProRateFactor = splitProRateFactor;
	}

	public double getTerminationProRateFactor() {
		return terminationProRateFactor;
	}

	public void setTerminationProRateFactor(double terminationProRateFactor) {
		this.terminationProRateFactor = terminationProRateFactor;
	}

	public double getAnnualSalaryProRateFactor() {
		return annualSalaryProRateFactor;
	}

	public void setAnnualSalaryProRateFactor(double annualSalaryProRateFactor) {
		this.annualSalaryProRateFactor = annualSalaryProRateFactor;
	}

	public String getOperatingUnitOrVertical() {
		return operatingUnitOrVertical;
	}

	public void setOperatingUnitOrVertical(String operatingUnitOrVertical) {
		this.operatingUnitOrVertical = operatingUnitOrVertical;
	}

	public String getVerticalDescription() {
		return verticalDescription;
	}

	public void setVerticalDescription(String verticalDescription) {
		this.verticalDescription = verticalDescription;
	}

	public String getGLTemplateBusinessUnit() {
		return gLTemplateBusinessUnit;
	}

	public void setGLTemplateBusinessUnit(String gLTemplateBusinessUnit) {
		this.gLTemplateBusinessUnit = gLTemplateBusinessUnit;
	}

	public String getGLTemplateLedger() {
		return gLTemplateLedger;
	}

	public void setGLTemplateLedger(String gLTemplateLedger) {
		this.gLTemplateLedger = gLTemplateLedger;
	}

	public String getGLTemplateAccount() {
		return gLTemplateAccount;
	}

	public void setGLTemplateAccount(String gLTemplateAccount) {
		this.gLTemplateAccount = gLTemplateAccount;
	}

	public String getGLProduct() {
		return gLProduct;
	}

	public void setGLProduct(String gLProduct) {
		this.gLProduct = gLProduct;
	}

	public String getGLProductDescription() {
		return gLProductDescription;
	}

	public void setGLProductDescription(String gLProductDescription) {
		this.gLProductDescription = gLProductDescription;
	}

	public String getPSClientId() {
		return pSClientId;
	}

	public void setPSClientId(String pSClientId) {
		this.pSClientId = pSClientId;
	}

	public String getPSClientName() {
		return pSClientName;
	}

	public void setPSClientName(String pSClientName) {
		this.pSClientName = pSClientName;
	}

	public String getGLTemplateLocation() {
		return gLTemplateLocation;
	}

	public void setGLTemplateLocation(String gLTemplateLocation) {
		this.gLTemplateLocation = gLTemplateLocation;
	}

	public String getGLTemplateReference() {
		return gLTemplateReference;
	}

	public void setGLTemplateReference(String gLTemplateReference) {
		this.gLTemplateReference = gLTemplateReference;
	}

	public String getGLTemplateProjectBU() {
		return gLTemplateProjectBU;
	}

	public void setGLTemplateProjectBU(String gLTemplateProjectBU) {
		this.gLTemplateProjectBU = gLTemplateProjectBU;
	}

	public String getGLTemplateAnalysisType() {
		return gLTemplateAnalysisType;
	}

	public void setGLTemplateAnalysisType(String gLTemplateAnalysisType) {
		this.gLTemplateAnalysisType = gLTemplateAnalysisType;
	}

	public String getActivityId() {
		return activityId;
	}

	public void setActivityId(String activityId) {
		this.activityId = activityId;
	}

	public String getOperatingUnitFromVerticalVW() {
		return operatingUnitFromVerticalVW;
	}

	public void setOperatingUnitFromVerticalVW(
			String operatingUnitFromVerticalVW) {
		this.operatingUnitFromVerticalVW = operatingUnitFromVerticalVW;
	}

	public String getProductFromVerticalVW() {
		return productFromVerticalVW;
	}

	public void setProductFromVerticalVW(String productFromVerticalVW) {
		this.productFromVerticalVW = productFromVerticalVW;
	}

	public String getPSClientIdFromVerticalVW() {
		return pSClientIdFromVerticalVW;
	}

	public void setPSClientIdFromVerticalVW(String pSClientIdFromVerticalVW) {
		this.pSClientIdFromVerticalVW = pSClientIdFromVerticalVW;
	}

	public String getDeptIdFromVerticalVW() {
		return deptIdFromVerticalVW;
	}

	public void setDeptIdFromVerticalVW(String deptIdFromVerticalVW) {
		this.deptIdFromVerticalVW = deptIdFromVerticalVW;
	}

	public String getAnalyzerTerminationDate() {
		return analyzerTerminationDate;
	}

	public void setAnalyzerTerminationDate(String analyzerTerminationDate) {
		this.analyzerTerminationDate = analyzerTerminationDate;
	}

	public String getAssignmentEndDate() {
		return assignmentEndDate;
	}

	public void setAssignmentEndDate(String assignmentEndDate) {
		this.assignmentEndDate = assignmentEndDate;
	}

	public double getBonusGA() {
		return bonusGA;
	}

	public void setBonusGA(double bonusGA) {
		this.bonusGA = bonusGA;
	}

	public String getAnalyzerId() {
		return analyzerId;
	}

	public void setAnalyzerId(String analyzerId) {
		this.analyzerId = analyzerId;
	}

	public String getBusinessUnitHR() {
		return businessUnitHR;
	}

	public void setBusinessUnitHR(String businessUnitHR) {
		this.businessUnitHR = businessUnitHR;
	}

	public String getGLDeptId() {
		return gLDeptId;
	}

	public void setGLDeptId(String gLDeptId) {
		this.gLDeptId = gLDeptId;
	}

	public double getAnalyzerBillableCost() {
		return analyzerBillableCost;
	}

	public void setAnalyzerBillableCost(double analyzerBillableCost) {
		this.analyzerBillableCost = analyzerBillableCost;
	}

	public double getAnalyzerBillRate() {
		return analyzerBillRate;
	}

	public void setAnalyzerBillRate(double analyzerBillRate) {
		this.analyzerBillRate = analyzerBillRate;
	}

	public double getAnalyzerBonusAmount() {
		return analyzerBonusAmount;
	}

	public void setAnalyzerBonusAmount(double analyzerBonusAmount) {
		this.analyzerBonusAmount = analyzerBonusAmount;
	}

	public double getAnalyzerDiscountAmount() {
		return analyzerDiscountAmount;
	}

	public void setAnalyzerDiscountAmount(double analyzerDiscountAmount) {
		this.analyzerDiscountAmount = analyzerDiscountAmount;
	}

	public double getAnalyzerDiscountPercentage() {
		return analyzerDiscountPercentage;
	}

	public void setAnalyzerDiscountPercentage(double analyzerDiscountPercentage) {
		this.analyzerDiscountPercentage = analyzerDiscountPercentage;
	}

	public String getAnalyzerEndDate() {
		return analyzerEndDate;
	}

	public void setAnalyzerEndDate(String analyzerEndDate) {
		this.analyzerEndDate = analyzerEndDate;
	}

	public double getAnalyzerEquipmentCost() {
		return analyzerEquipmentCost;
	}

	public void setAnalyzerEquipmentCost(double analyzerEquipmentCost) {
		this.analyzerEquipmentCost = analyzerEquipmentCost;
	}

	public double getAnalyzerGA() {
		return analyzerGA;
	}

	public void setAnalyzerGA(double analyzerGA) {
		this.analyzerGA = analyzerGA;
	}

	public double getAnalyzerHealthAmount() {
		return analyzerHealthAmount;
	}

	public void setAnalyzerHealthAmount(double analyzerHealthAmount) {
		this.analyzerHealthAmount = analyzerHealthAmount;
	}

	public double getAnalyzerImmigrationCost() {
		return analyzerImmigrationCost;
	}

	public void setAnalyzerImmigrationCost(double analyzerImmigrationCost) {
		this.analyzerImmigrationCost = analyzerImmigrationCost;
	}

	public double getAnalyzerK401Amount() {
		return analyzerK401Amount;
	}

	public void setAnalyzerK401Amount(double analyzerK401Amount) {
		this.analyzerK401Amount = analyzerK401Amount;
	}

	public double getAnalyzerLeaveAmount() {
		return analyzerLeaveAmount;
	}

	public void setAnalyzerLeaveAmount(double analyzerLeaveAmount) {
		this.analyzerLeaveAmount = analyzerLeaveAmount;
	}

	public double getAnalyzerOtherAmountPerDeal() {
		return analyzerOtherAmountPerDeal;
	}

	public void setAnalyzerOtherAmountPerDeal(double analyzerOtherAmountPerDeal) {
		this.analyzerOtherAmountPerDeal = analyzerOtherAmountPerDeal;
	}

	public double getAnalyzerOtherAmountPerHour() {
		return analyzerOtherAmountPerHour;
	}

	public void setAnalyzerOtherAmountPerHour(double analyzerOtherAmountPerHour) {
		this.analyzerOtherAmountPerHour = analyzerOtherAmountPerHour;
	}

	public double getAnalyzerPayRate() {
		return analyzerPayRate;
	}

	public void setAnalyzerPayRate(double analyzerPayRate) {
		this.analyzerPayRate = analyzerPayRate;
	}

	public double getAnalyzerPerDiemAmount() {
		return analyzerPerDiemAmount;
	}

	public void setAnalyzerPerDiemAmount(double analyzerPerDiemAmount) {
		this.analyzerPerDiemAmount = analyzerPerDiemAmount;
	}

	public double getAnalyzerProfit() {
		return analyzerProfit;
	}

	public void setAnalyzerProfit(double analyzerProfit) {
		this.analyzerProfit = analyzerProfit;
	}

	public double getAnalyzerPTODays() {
		return analyzerPTODays;
	}

	public void setAnalyzerPTODays(double analyzerPTODays) {
		this.analyzerPTODays = analyzerPTODays;
	}

	public double getAnalyzerReferralFee() {
		return analyzerReferralFee;
	}

	public void setAnalyzerReferralFee(double analyzerReferralFee) {
		this.analyzerReferralFee = analyzerReferralFee;
	}

	public double getAnalyzerSpread() {
		return analyzerSpread;
	}

	public void setAnalyzerSpread(double analyzerSpread) {
		this.analyzerSpread = analyzerSpread;
	}

	public String getAnalyzerStartDate() {
		return analyzerStartDate;
	}

	public void setAnalyzerStartDate(String analyzerStartDate) {
		this.analyzerStartDate = analyzerStartDate;
	}

	public double getAnalyzerTaxesOrFringe() {
		return analyzerTaxesOrFringe;
	}

	public void setAnalyzerTaxesOrFringe(double analyzerTaxesOrFringe) {
		this.analyzerTaxesOrFringe = analyzerTaxesOrFringe;
	}

	public double getAnalyzerTotalCommissionAmount() {
		return analyzerTotalCommissionAmount;
	}

	public void setAnalyzerTotalCommissionAmount(
			double analyzerTotalCommissionAmount) {
		this.analyzerTotalCommissionAmount = analyzerTotalCommissionAmount;
	}

	public double getAnalyzerTotalHoursWorked() {
		return analyzerTotalHoursWorked;
	}

	public void setAnalyzerTotalHoursWorked(double analyzerTotalHoursWorked) {
		this.analyzerTotalHoursWorked = analyzerTotalHoursWorked;
	}

	public String getAssignmentEntryDate() {
		return assignmentEntryDate;
	}

	public void setAssignmentEntryDate(String assignmentEntryDate) {
		this.assignmentEntryDate = assignmentEntryDate;
	}

	public String getAssignmentLastUpdateDate() {
		return assignmentLastUpdateDate;
	}

	public void setAssignmentLastUpdateDate(String assignmentLastUpdateDate) {
		this.assignmentLastUpdateDate = assignmentLastUpdateDate;
	}

	public String getCommissionCalculationModel() {
		return commissionCalculationModel;
	}

	public void setCommissionCalculationModel(String commissionCalculationModel) {
		this.commissionCalculationModel = commissionCalculationModel;
	}

	public String getEffectiveDate() {
		return effectiveDate;
	}

	public void setEffectiveDate(String effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	public int getEffectiveSequence() {
		return effectiveSequence;
	}

	public void setEffectiveSequence(int effectiveSequence) {
		this.effectiveSequence = effectiveSequence;
	}

	public double getEstimatedCommissionRate() {
		return estimatedCommissionRate;
	}

	public void setEstimatedCommissionRate(double estimatedCommissionRate) {
		this.estimatedCommissionRate = estimatedCommissionRate;
	}

	public double getEstimatedOverallDealCost() {
		return estimatedOverallDealCost;
	}

	public void setEstimatedOverallDealCost(double estimatedOverallDealCost) {
		this.estimatedOverallDealCost = estimatedOverallDealCost;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public int getOrderLine() {
		return orderLine;
	}

	public void setOrderLine(int orderLine) {
		this.orderLine = orderLine;
	}

	public String getVendorId() {
		return vendorId;
	}

	public void setVendorId(String vendorId) {
		this.vendorId = vendorId;
	}

	// Added New Columns 20170307

	public int getCommissionModelId() {
		return commissionModelId;
	}

	public void setCommissionModelId(int commissionModelId) {
		this.commissionModelId = commissionModelId;
	}

	public String getAnalyzerCommissionModelCode() {
		return analyzerCommissionModelCode;
	}

	public void setAnalyzerCommissionModelCode(
			String analyzerCommissionModelCode) {
		this.analyzerCommissionModelCode = analyzerCommissionModelCode;
	}

	public String getAnalyzerDealType() {
		return analyzerDealType;
	}

	public void setAnalyzerDealType(String analyzerDealType) {
		this.analyzerDealType = analyzerDealType;
	}

	public String getCommissionPersonJobCode() {
		return commissionPersonJobCode;
	}

	public void setCommissionPersonJobCode(String commissionPersonJobCode) {
		this.commissionPersonJobCode = commissionPersonJobCode;
	}

	public String getPSJobEffectiveDate() {
		return pSJobEffectiveDate;
	}

	public void setPSJobEffectiveDate(String pSJobEffectiveDate) {
		this.pSJobEffectiveDate = pSJobEffectiveDate;
	}

	public int getPSJobEffectiveSequence() {
		return pSJobEffectiveSequence;
	}

	public void setPSJobEffectiveSequence(int pSJobEffectiveSequence) {
		this.pSJobEffectiveSequence = pSJobEffectiveSequence;
	}

	public String getQuotaEffectiveDate() {
		return quotaEffectiveDate;
	}

	public void setQuotaEffectiveDate(String quotaEffectiveDate) {
		this.quotaEffectiveDate = quotaEffectiveDate;
	}

	public int getQuotaEffectiveSequence() {
		return quotaEffectiveSequence;
	}

	public void setQuotaEffectiveSequence(int quotaEffectiveSequence) {
		this.quotaEffectiveSequence = quotaEffectiveSequence;
	}

	public double getYearlyQuotaAttainmentAmount() {
		return yearlyQuotaAttainmentAmount;
	}

	public void setYearlyQuotaAttainmentAmount(
			double yearlyQuotaAttainmentAmount) {
		this.yearlyQuotaAttainmentAmount = yearlyQuotaAttainmentAmount;
	}

	public int getQuotaEntryId() {
		return quotaEntryId;
	}

	public void setQuotaEntryId(int quotaEntryId) {
		this.quotaEntryId = quotaEntryId;
	}

	public int getQuotaCommissionJobCodeId() {
		return quotaCommissionJobCodeId;
	}

	public void setQuotaCommissionJobCodeId(int quotaCommissionJobCodeId) {
		this.quotaCommissionJobCodeId = quotaCommissionJobCodeId;
	}

	public double getQuotaAttainmentCommPercentage() {
		return quotaAttainmentCommPercentage;
	}

	public void setQuotaAttainmentCommPercentage(
			double quotaAttainmentCommPercentage) {
		this.quotaAttainmentCommPercentage = quotaAttainmentCommPercentage;
	}

	public int getQuotaCommissionRateId() {
		return quotaCommissionRateId;
	}

	public void setQuotaCommissionRateId(int quotaCommissionRateId) {
		this.quotaCommissionRateId = quotaCommissionRateId;
	}

	public String getIsFlatFeeCommissionRecord() {
		return isFlatFeeCommissionRecord;
	}

	public void setIsFlatFeeCommissionRecord(String isFlatFeeCommissionRecord) {
		this.isFlatFeeCommissionRecord = isFlatFeeCommissionRecord;
	}

	public int getFlatFeeCommissionId() {
		return flatFeeCommissionId;
	}

	public void setFlatFeeCommissionId(int flatFeeCommissionId) {
		this.flatFeeCommissionId = flatFeeCommissionId;
	}

	public double getFlatFeeCommissionAmount() {
		return flatFeeCommissionAmount;
	}

	public void setFlatFeeCommissionAmount(double flatFeeCommissionAmount) {
		this.flatFeeCommissionAmount = flatFeeCommissionAmount;
	}

	public String getIsFlatFeeCommissionAlreadyPaid() {
		return isFlatFeeCommissionAlreadyPaid;
	}

	public void setIsFlatFeeCommissionAlreadyPaid(
			String isFlatFeeCommissionAlreadyPaid) {
		this.isFlatFeeCommissionAlreadyPaid = isFlatFeeCommissionAlreadyPaid;
	}

	public String getIsFlatFeeCommissionPayable() {
		return isFlatFeeCommissionPayable;
	}

	public void setIsFlatFeeCommissionPayable(String isFlatFeeCommissionPayable) {
		this.isFlatFeeCommissionPayable = isFlatFeeCommissionPayable;
	}

	public int getFlatFeePaymentTrackId() {
		return flatFeePaymentTrackId;
	}

	public void setFlatFeePaymentTrackId(int flatFeePaymentTrackId) {
		this.flatFeePaymentTrackId = flatFeePaymentTrackId;
	}

	public double getFlatFeeProRateFactor() {
		return flatFeeProRateFactor;
	}

	public void setFlatFeeProRateFactor(double flatFeeProRateFactor) {
		this.flatFeeProRateFactor = flatFeeProRateFactor;
	}

	public double getPreviousYTDAttainedGPQuotaAmount() {
		return previousYTDAttainedGPQuotaAmount;
	}

	public void setPreviousYTDAttainedGPQuotaAmount(
			double previousYTDAttainedGPQuotaAmount) {
		this.previousYTDAttainedGPQuotaAmount = previousYTDAttainedGPQuotaAmount;
	}

	public double getCurrentMonthGPQuotaAmount() {
		return currentMonthGPQuotaAmount;
	}

	public void setCurrentMonthGPQuotaAmount(double currentMonthGPQuotaAmount) {
		this.currentMonthGPQuotaAmount = currentMonthGPQuotaAmount;
	}

	public double getCurrentYTDAttainedGPQuotaAmount() {
		return currentYTDAttainedGPQuotaAmount;
	}

	public void setCurrentYTDAttainedGPQuotaAmount(
			double currentYTDAttainedGPQuotaAmount) {
		this.currentYTDAttainedGPQuotaAmount = currentYTDAttainedGPQuotaAmount;
	}

	public double getProjectGPForQuotaAttainment() {
		return projectGPForQuotaAttainment;
	}

	public void setProjectGPForQuotaAttainment(
			double projectGPForQuotaAttainment) {
		this.projectGPForQuotaAttainment = projectGPForQuotaAttainment;
	}

	public double getEmploymentProRateFactor() {
		return employmentProRateFactor;
	}

	public void setEmploymentProRateFactor(double employmentProRateFactor) {
		this.employmentProRateFactor = employmentProRateFactor;
	}

	// 20170516 NEW COLUMNS ADDED FOR GRID

	public String getIsCommPersonEligibleForAcceleratorComm() {
		return isCommPersonEligibleForAcceleratorComm;
	}

	public void setIsCommPersonEligibleForAcceleratorComm(
			String isCommPersonEligibleForAcceleratorComm) {
		this.isCommPersonEligibleForAcceleratorComm = isCommPersonEligibleForAcceleratorComm;
	}

	public double getCurrentAppliedAcceleratorCommPercentage() {
		return currentAppliedAcceleratorCommPercentage;
	}

	public void setCurrentAppliedAcceleratorCommPercentage(
			double currentAppliedAcceleratorCommPercentage) {
		this.currentAppliedAcceleratorCommPercentage = currentAppliedAcceleratorCommPercentage;
	}

	public int getCommissionQuotaAttainmentId() {
		return commissionQuotaAttainmentId;
	}

	public void setCommissionQuotaAttainmentId(int commissionQuotaAttainmentId) {
		this.commissionQuotaAttainmentId = commissionQuotaAttainmentId;
	}

	public double getAdditionalAcceleratorCommissionAmount() {
		return additionalAcceleratorCommissionAmount;
	}

	public void setAdditionalAcceleratorCommissionAmount(
			double additionalAcceleratorCommissionAmount) {
		this.additionalAcceleratorCommissionAmount = additionalAcceleratorCommissionAmount;
	}

	public double getGrossCommissionAmountBeforeAccelerator() {
		return grossCommissionAmountBeforeAccelerator;
	}

	public void setGrossCommissionAmountBeforeAccelerator(
			double grossCommissionAmountBeforeAccelerator) {
		this.grossCommissionAmountBeforeAccelerator = grossCommissionAmountBeforeAccelerator;
	}

	public double getAppliedCommissionPaymentPercentage() {
		return appliedCommissionPaymentPercentage;
	}

	public void setAppliedCommissionPaymentPercentage(
			double appliedCommissionPaymentPercentage) {
		this.appliedCommissionPaymentPercentage = appliedCommissionPaymentPercentage;
	}

	public void printOnConsole() {
		System.out.println("projectId = " + projectId);
		System.out.println("emplId = " + emplId);
		System.out.println("revenue = " + revenue);
		System.out.println("cost = " + cost);
		System.out.println("cccf = " + cccf);
		System.out.println("ccaf = " + ccaf);
		System.out.println("commissionCost = " + commissionCost);
		System.out.println("commissionDiscountAmount = "
				+ commissionDiscountAmount);
		System.out
				.println("commissionDiscountRate = " + commissionDiscountRate);
		System.out.println("commissionGAAmount = " + commissionGAAmount);
		System.out.println("commissionGARate = " + commissionGARate);
		System.out
				.println("commissionPersonOrphan = " + commissionPersonOrphan);
		System.out.println("commissionRevenue = " + commissionRevenue);
		System.out.println("employeeName = " + employeeName);
		System.out.println("estimatedCommissionPercentage = "
				+ estimatedCommissionPercentage);
		System.out.println("expenseRevenue = " + expenseRevenue);
		System.out.println("isCommissionPersonForQuota = "
				+ isCommissionPersonForQuota);
		System.out.println("isMasterProject = " + isMasterProject);
		System.out.println("proRatedCommissionAmount = "
				+ proRatedCommissionAmount);
		System.out.println("proRateFactor = " + proRateFactor);
		System.out.println("grossProfitPercentage = " + grossProfitPercentage);
		System.out.println("grossProfit = " + grossProfit);
		System.out.println("actualCommissionPercentage = "
				+ actualCommissionPercentage);
		System.out.println("sumOfAllProjectsRevenue = "
				+ sumOfAllProjectsRevenue);
		System.out.println("sumOfAllProjectsCost = " + sumOfAllProjectsCost);
		System.out.println("hours = " + hours);
		System.out.println("commissionEntryType = " + commissionEntryType);
		System.out.println("bonusRevenue = " + bonusRevenue);
		System.out.println("bonusCost = " + bonusCost);
		System.out.println("bonusProfit = " + bonusProfit);
		System.out.println("childAdjustedGA = " + childAdjustedGA);
		System.out.println("netProfit = " + netProfit);
		System.out.println("totalCommission = " + totalCommission);
		System.out.println("childAdjustedProfit = " + childAdjustedProfit);
		System.out.println("commissionPersonSplitPercentage = "
				+ commissionPersonSplitPercentage);
		System.out
				.println("analyzerSubmissionDate = " + analyzerSubmissionDate);
		System.out.println("analyzerOperatingUnit = " + analyzerOperatingUnit);
		System.out.println("skillCatgory = " + skillCatgory);
		System.out.println("analyzerClientId = " + analyzerClientId);
		System.out.println("analyzerBusinessUnit = " + analyzerBusinessUnit);
		System.out.println("splitProRateFactor = " + splitProRateFactor);
		System.out.println("terminationProRateFactor = "
				+ terminationProRateFactor);
		System.out.println("annualSalaryProRateFactor = "
				+ annualSalaryProRateFactor);
		System.out.println("operatingUnitOrVertical = "
				+ operatingUnitOrVertical);
		System.out.println("verticalDescription = " + verticalDescription);
		System.out
				.println("gLTemplateBusinessUnit = " + gLTemplateBusinessUnit);
		System.out.println("gLTemplateLedger = " + gLTemplateLedger);
		System.out.println("gLTemplateAccount = " + gLTemplateAccount);
		System.out.println("gLProduct = " + gLProduct);
		System.out.println("gLProductDescription = " + gLProductDescription);
		System.out.println("pSClientId = " + pSClientId);
		System.out.println("pSClientName = " + pSClientName);
		System.out.println("gLTemplateLocation = " + gLTemplateLocation);
		System.out.println("gLTemplateReference = " + gLTemplateReference);
		System.out.println("gLTemplateProjectBU = " + gLTemplateProjectBU);
		System.out
				.println("gLTemplateAnalysisType = " + gLTemplateAnalysisType);
		System.out.println("activityId = " + activityId);
		System.out.println("operatingUnitFromVerticalVW = "
				+ operatingUnitFromVerticalVW);
		System.out.println("productFromVerticalVW = " + productFromVerticalVW);
		System.out.println("pSClientIdFromVerticalVW = "
				+ pSClientIdFromVerticalVW);
		System.out.println("deptIdFromVerticalVW = " + deptIdFromVerticalVW);
		System.out.println("analyzerTerminationDate = "
				+ analyzerTerminationDate);
		System.out.println("assignmentEndDate = " + assignmentEndDate);
		System.out.println("analyzerId = " + analyzerId);
		System.out.println("businessUnitHR = " + businessUnitHR);
		System.out.println("gLDeptId = " + gLDeptId);
		System.out.println("analyzerBillableCost = " + analyzerBillableCost);
		System.out.println("analyzerBillRate = " + analyzerBillRate);
		System.out.println("analyzerBonusAmount = " + analyzerBonusAmount);
		System.out
				.println("analyzerDiscountAmount = " + analyzerDiscountAmount);
		System.out.println("analyzerDiscountPercentage = "
				+ analyzerDiscountPercentage);
		System.out.println("analyzerEndDate = " + analyzerEndDate);
		System.out.println("analyzerEquipmentCost = " + analyzerEquipmentCost);
		System.out.println("analyzerGA = " + analyzerGA);
		System.out.println("analyzerHealthAmount = " + analyzerHealthAmount);
		System.out.println("analyzerImmigrationCost = "
				+ analyzerImmigrationCost);
		System.out.println("analyzerK401Amount = " + analyzerK401Amount);
		System.out.println("analyzerLeaveAmount = " + analyzerLeaveAmount);
		System.out.println("analyzerOtherAmountPerDeal = "
				+ analyzerOtherAmountPerDeal);
		System.out.println("analyzerOtherAmountPerHour = "
				+ analyzerOtherAmountPerHour);
		System.out.println("analyzerPayRate = " + analyzerPayRate);
		System.out.println("analyzerPerDiemAmount = " + analyzerPerDiemAmount);
		System.out.println("analyzerProfit = " + analyzerProfit);
		System.out.println("analyzerPTODays = " + analyzerPTODays);
		System.out.println("analyzerReferralFee = " + analyzerReferralFee);
		System.out.println("analyzerSpread = " + analyzerSpread);
		System.out.println("analyzerStartDate = " + analyzerStartDate);
		System.out.println("analyzerTaxesOrFringe = " + analyzerTaxesOrFringe);
		System.out.println("analyzerTotalCommissionAmount = "
				+ analyzerTotalCommissionAmount);
		System.out.println("analyzerTotalHoursWorked = "
				+ analyzerTotalHoursWorked);
		System.out.println("assignmentEntryDate = " + assignmentEntryDate);
		System.out.println("assignmentLastUpdateDate = "
				+ assignmentLastUpdateDate);
		System.out.println("commissionCalculationModel = "
				+ commissionCalculationModel);
		System.out.println("effectiveDate = " + effectiveDate);
		System.out.println("effectiveSequence = " + effectiveSequence);
		System.out.println("estimatedCommissionRate = "
				+ estimatedCommissionRate);
		System.out.println("estimatedOverallDealCost = "
				+ estimatedOverallDealCost);
		System.out.println("orderId = " + orderId);
		System.out.println("orderLine = " + orderLine);
		System.out.println("vendorId = " + vendorId);
		// Added New Columns 20170307
		System.out.println("commissionModelId = " + commissionModelId);
		System.out.println("analyzerDealType = " + analyzerDealType);
		System.out.println("commissionPersonJobCode = "
				+ commissionPersonJobCode);
		System.out.println("pSJobEffectiveDate = " + pSJobEffectiveDate);
		System.out
				.println("pSJobEffectiveSequence = " + pSJobEffectiveSequence);
		System.out.println("quotaEffectiveDate = " + quotaEffectiveDate);
		System.out
				.println("quotaEffectiveSequence = " + quotaEffectiveSequence);
		System.out.println("yearlyQuotaAttainmentAmount = "
				+ yearlyQuotaAttainmentAmount);
		System.out.println("quotaEntryId = " + quotaEntryId);
		System.out.println("quotaCommissionJobCodeId = "
				+ quotaCommissionJobCodeId);
		System.out.println("quotaAttainmentCommPercentage = "
				+ quotaAttainmentCommPercentage);
		System.out.println("quotaCommissionRateId = " + quotaCommissionRateId);
		System.out.println("isFlatFeeCommissionRecord = "
				+ isFlatFeeCommissionRecord);
		System.out.println("flatFeeCommissionId = " + flatFeeCommissionId);
		System.out.println("flatFeeCommissionAmount = "
				+ flatFeeCommissionAmount);
		System.out.println("isFlatFeeCommissionAlreadyPaid = "
				+ isFlatFeeCommissionAlreadyPaid);
		System.out.println("isFlatFeeCommissionPayable = "
				+ isFlatFeeCommissionPayable);
		System.out.println("flatFeePaymentTrackId = " + flatFeePaymentTrackId);
		System.out.println("flatFeeProRateFactor = " + flatFeeProRateFactor);
		System.out.println("previousYTDAttainedGPQuotaAmount = "
				+ previousYTDAttainedGPQuotaAmount);
		System.out.println("currentMonthGPQuotaAmount = "
				+ currentMonthGPQuotaAmount);
		System.out.println("currentYTDAttainedGPQuotaAmount = "
				+ currentYTDAttainedGPQuotaAmount);
		System.out.println("projectGPForQuotaAttainment = "
				+ projectGPForQuotaAttainment);
		System.out.println("employmentProRateFactor = "
				+ employmentProRateFactor);
		// 20170516
		System.out.println("isCommPersonEligibleForAcceleratorComm = "
				+ isCommPersonEligibleForAcceleratorComm);
		System.out.println("currentAppliedAcceleratorCommPercentage = "
				+ currentAppliedAcceleratorCommPercentage);
		System.out.println("commissionQuotaAttainmentId = "
				+ commissionQuotaAttainmentId);
		System.out.println("additionalAcceleratorCommissionAmount = "
				+ additionalAcceleratorCommissionAmount);
		System.out.println("grossCommissionAmountBeforeAccelerator = "
				+ grossCommissionAmountBeforeAccelerator);
		System.out.println("appliedCommissionPaymentPercentage = "
				+ appliedCommissionPaymentPercentage);

	}
}
