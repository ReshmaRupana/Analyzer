package com.disys.analyzer.model.dto;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author Sajid
 * 
 */

@Entity
public class BullhornBatchDataProcessedDTO implements Serializable
{

	@Id
	private Integer bullhornBatchDataProcessedId;
	private static final long serialVersionUID = 6128885673928232458L;
	private String address1;
	private String address2;
	private String ae2EmplId;
	private String ae1EmplId;
	private String analyzerCategory1;
	private String analyzerCategory2Classification;
	private Double annualPay;
	private Integer assignedAnalyzerId;
	private Integer assignedParentId;
	private String assignedSSN;
	private Double billableCost;
	private Integer billableHolidays;
	private Integer billablePTO;
	private Double billRate;
	private Boolean bonusEligible;
	private Integer bonusPercentage;
	private Integer bullhornBatchDataRawId;
	private String bullhornBatchCode;
	private Integer bullhornRecordId;
	private Integer checkedReferences;
	private Integer chkReferralFee;
	private String city;
	private Integer clientAddressId;
	private Integer clientId;
	private String commEffDate;
	private String comments;
	private String commisionName1AE1;
	private String commisionName2REC1;
	private String commisionName3AE2;
	private String commisionName4REC2;
	private Double commisionPercent1AE1;
	private Double commisionPercent2REC1;
	private Double commisionPercent3AE2;
	private Double commisionPercent4REC2;
	private Double commission5MSP;
	private String commissionModel1AE1;
	private String commissionModel2REC1;
	private String commissionModel3AE2;
	private String commissionModel4REC2;
	private Double commissionPercentage5MSP;
	private String commissionPerson5MSP;
	private String commissionPersonGrade1AE1;
	private String commissionPersonGrade3AE2;
	private String companyCode;
	private String consultantJobBoard;
	private Integer cther1OrphanAE2;
	private String dealPortfolio1AE1;
	private String dealPortfolio2REC1;
	private String dealPortfolio3AE2;
	private String dealPortfolio4REC2;
	private String dealType;
	private String dentalInsurance;
	private String dob;
	private Double doubleTime;
	private Double doubleTimeBill;
	private String effectiveDate;
	private String email;
	private String employeeCategory;
	private String employeeClass;
	private String employeeType;
	private String employeeVeteran;
	private String endDate;
	private Timestamp entryDateTime;
	private Double equipmentCost;
	private Integer execOrphanAE1;
	private String fax;
	private String firstName;
	private String flsaReferance;
	private String flsaStatus;
	private String gender;
	private String geoOffice;
	private Integer h1;
	private String hrbpEmplId;
	private Double immigrationCost;
	private String importComments;
	private String initial;
	private String jobCategory;
	private String jobTitle;
	private String lastName;
	private String liaisonName;
	private String ltdBenefit;
	private String managingDirector;
	private String managingDirectorEmplId;
	private String mobilePager;
	private String mspEmplId;
	private Integer nonBillableHolidays;
	private Integer nonBillablePTO;
	private String office;
	private Double oneTimeBill;
	private Double oneTimePay;
	private Integer other1OrphanAE2;
	private Integer other2OrphanREC2;
	private Double otherAmountHour;
	private Double otherDollar;
	private Double payRate;
	private Double perDiem;
	private Integer placementId;
	private String portfolio;
	private String portfolioDescription;
	private Boolean processed;
	private Timestamp processingDateTime;
	private String product;
	private Double projectCost;
	private String psClientId;
	private Integer psCustomerAddressId;
	private String psVendorId;
	private Integer pto;
	private String rec1EmplId;
	private String rec2EmplId;
	private Boolean recordAccepted;
	private String recordHasErrors;
	private String recordProcessingStatus;
	private Integer recruiterOrphanREC1;
	private String recruitingClassification;
	private Double referralFeeAmount;
	private String sickLeaveType;
	private Integer siteId;
	private String skillCategory;
	private Integer splitCommissionPercentage2REC1;
	private Integer splitCommissionPercentage1AE1;
	private Integer splitCommissionPercentage3AE2;
	private Integer splitCommissionPercentage4REC2;
	private String ssn;
	private Boolean ssnGenerated;
	private String startDate;
	private String state;
	private String stdBenefit;
	private Integer subContractorId;
	private String telephone;
	private Integer thw;
	private Double totalHolidays;
	private String transactionType;
	private String travelRequired;
	private Boolean unEmployedForTwoMonths;
	private Timestamp updateDateTime;
	private String vertical;
	private String workEmail;
	private String workPhone;
	private String worksiteAddress1;
	private String worksiteAddress2;
	private String worksiteCity;
	private String worksiteManagerEmail;
	private String worksiteManagerFirstName;
	private String worksiteManagerLastName;
	private String worksiteManagerPhone;
	private String worksiteState;
	private String worksiteZipCode;
	private String zipcode;
	
	private Timestamp analyzerSiteCreationDate;
	private Integer bullhornBatchAnalyzerStagingId;
	private Integer bullhornPlacementId;
	private Integer bullhornWorksiteDataId;
	private String isAnalyzerSiteCreated;
	private String isTransferredToAnalyzer;
	private String isTransferredToAnalyzerStaging;
	private String isWorksiteAddressDataValidated;
	private String isWorksiteChanged;
	private String submittedByEmail;
	private String submittedByEmplId;	
	private Timestamp transferToAnalyzerDate;
	private Timestamp transferToAnalyzerStagingDate;
	private Timestamp worksiteChangeDate;
	
	private Integer bullhornTerminationDataProcessedId;
	private String terminateDate;
	private String reason;
	private String eligibleForReHire;
	private String overrideTermination;
	private String falseTermination;
	private Integer bullhornTerminationDataRawId;
	private String terminationDateOverrideDate;
	private Integer bullhornTerminationDataStagingId;
	private String isRecordHasErrors;
	private String dataSource;
	private Integer bullhornBatchInfoId;
	private String placementType;
	private String coSellStatus;
	private String processingComments;
	
	public BullhornBatchDataProcessedDTO() {
		super();
		// TODO init here
	}

	public String getAddress1() {
		return address1;
	}

	public String getAddress2() {
		return address2;
	}

	public String getAe2EmplId() {
		return ae2EmplId;
	}

	public String getAnalyzerCategory1() {
		return analyzerCategory1;
	}

	public String getAnalyzerCategory2Classification() {
		return analyzerCategory2Classification;
	}

	public Double getAnnualPay() {
		return annualPay;
	}

	public Integer getAssignedAnalyzerId() {
		return assignedAnalyzerId;
	}

	public Integer getAssignedParentId() {
		return assignedParentId;
	}

	public String getAssignedSSN() {
		return assignedSSN;
	}

	public Double getBillableCost() {
		return billableCost;
	}

	public Integer getBillableHolidays() {
		return billableHolidays;
	}

	public Integer getBillablePTO() {
		return billablePTO;
	}

	public Double getBillRate() {
		return billRate;
	}

	public Integer getBonusPercentage() {
		return bonusPercentage;
	}

	public Integer getBullhornBatchDataProcessedId() {
		return bullhornBatchDataProcessedId;
	}

	public Integer getBullhornBatchDataRawId() {
		return bullhornBatchDataRawId;
	}

	public Integer getBullhornRecordId() {
		return bullhornRecordId;
	}

	public Integer getCheckedReferences() {
		return checkedReferences;
	}

	public Integer getChkReferralFee() {
		return chkReferralFee;
	}

	public String getCity() {
		return city;
	}

	public Integer getClientAddressId() {
		return clientAddressId;
	}

	public Integer getClientId() {
		return clientId;
	}

	public String getCommEffDate() {
		return commEffDate;
	}

	public String getComments() {
		return comments;
	}

	public String getCommisionName1AE1() {
		return commisionName1AE1;
	}

	public String getCommisionName2REC1() {
		return commisionName2REC1;
	}

	public String getCommisionName3AE2() {
		return commisionName3AE2;
	}

	public String getCommisionName4REC2() {
		return commisionName4REC2;
	}

	public Double getCommisionPercent1AE1() {
		return commisionPercent1AE1;
	}

	public Double getCommisionPercent2REC1() {
		return commisionPercent2REC1;
	}

	public Double getCommisionPercent3AE2() {
		return commisionPercent3AE2;
	}

	public Double getCommisionPercent4REC2() {
		return commisionPercent4REC2;
	}

	public Double getCommission5MSP() {
		return commission5MSP;
	}

	public String getCommissionModel1AE1() {
		return commissionModel1AE1;
	}

	public String getCommissionModel2REC1() {
		return commissionModel2REC1;
	}

	public String getCommissionModel3AE2() {
		return commissionModel3AE2;
	}

	public String getCommissionModel4REC2() {
		return commissionModel4REC2;
	}

	public Double getCommissionPercentage5MSP() {
		return commissionPercentage5MSP;
	}

	public String getCommissionPerson5MSP() {
		return commissionPerson5MSP;
	}

	public String getCommissionPersonGrade1AE1() {
		return commissionPersonGrade1AE1;
	}

	public String getCommissionPersonGrade3AE2() {
		return commissionPersonGrade3AE2;
	}

	public String getCompanyCode() {
		return companyCode;
	}

	public String getConsultantJobBoard() {
		return consultantJobBoard;
	}

	public Integer getCther1OrphanAE2() {
		return cther1OrphanAE2;
	}

	public String getDealPortfolio1AE1() {
		return dealPortfolio1AE1;
	}

	public String getDealPortfolio2REC1() {
		return dealPortfolio2REC1;
	}

	public String getDealPortfolio3AE2() {
		return dealPortfolio3AE2;
	}

	public String getDealPortfolio4REC2() {
		return dealPortfolio4REC2;
	}

	public String getDealType() {
		return dealType;
	}

	public String getDob() {
		return dob;
	}

	public Double getDoubleTime() {
		return doubleTime;
	}

	public Double getDoubleTimeBill() {
		return doubleTimeBill;
	}

	public String getEffectiveDate() {
		return effectiveDate;
	}

	public String getEmail() {
		return email;
	}

	public String getEmployeeCategory() {
		return employeeCategory;
	}

	public String getEmployeeClass() {
		return employeeClass;
	}

	public String getEmployeeType() {
		return employeeType;
	}

	public String getEmployeeVeteran() {
		return employeeVeteran;
	}

	public String getEndDate() {
		return endDate;
	}

	public Double getEquipmentCost() {
		return equipmentCost;
	}

	public Integer getExecOrphanAE1() {
		return execOrphanAE1;
	}

	public String getFax() {
		return fax;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getFlsaReferance() {
		return flsaReferance;
	}

	public String getFlsaStatus() {
		return flsaStatus;
	}

	public String getGender() {
		return gender;
	}

	public String getGeoOffice() {
		return geoOffice;
	}

	public Integer getH1() {
		return h1;
	}

	public String getHrbpEmplId() {
		return hrbpEmplId;
	}

	public Double getImmigrationCost() {
		return immigrationCost;
	}

	public String getImportComments() {
		return importComments;
	}

	public String getInitial() {
		return initial;
	}

	public String getJobCategory() {
		return jobCategory;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public String getLastName() {
		return lastName;
	}

	public String getLiaisonName() {
		return liaisonName;
	}

	public String getLtdBenefit() {
		return ltdBenefit;
	}

	public String getManagingDirector() {
		return managingDirector;
	}

	public String getManagingDirectorEmplId() {
		return managingDirectorEmplId;
	}

	public String getMobilePager() {
		return mobilePager;
	}

	public String getMspEmplId() {
		return mspEmplId;
	}

	public Integer getNonBillableHolidays() {
		return nonBillableHolidays;
	}

	public Integer getNonBillablePTO() {
		return nonBillablePTO;
	}

	public String getOffice() {
		return office;
	}

	public Double getOneTimeBill() {
		return oneTimeBill;
	}

	public Double getOneTimePay() {
		return oneTimePay;
	}

	public Integer getOther2OrphanREC2() {
		return other2OrphanREC2;
	}

	public Double getOtherAmountHour() {
		return otherAmountHour;
	}

	public Double getOtherDollar() {
		return otherDollar;
	}

	public Double getPayRate() {
		return payRate;
	}

	public Double getPerDiem() {
		return perDiem;
	}

	public Integer getPlacementId() {
		return placementId;
	}

	public String getPortfolio() {
		return portfolio;
	}

	public String getPortfolioDescription() {
		return portfolioDescription;
	}

	public String getProduct() {
		return product;
	}

	public Double getProjectCost() {
		return projectCost;
	}

	public String getPsClientId() {
		return psClientId;
	}

	public Integer getPsCustomerAddressId() {
		return psCustomerAddressId;
	}

	public String getPsVendorId() {
		return psVendorId;
	}

	public Integer getPto() {
		return pto;
	}

	public String getRec1EmplId() {
		return rec1EmplId;
	}

	public String getRec2EmplId() {
		return rec2EmplId;
	}

	public String getRecordProcessingStatus() {
		return recordProcessingStatus;
	}

	public Integer getRecruiterOrphanREC1() {
		return recruiterOrphanREC1;
	}

	public String getRecruitingClassification() {
		return recruitingClassification;
	}

	public Double getReferralFeeAmount() {
		return referralFeeAmount;
	}

	public String getSickLeaveType() {
		return sickLeaveType;
	}

	public Integer getSiteId() {
		return siteId;
	}

	public String getSkillCategory() {
		return skillCategory;
	}
	
	
	
	public Integer getSplitCommissionPercentage2REC1() {
		return splitCommissionPercentage2REC1;
	}

	public void setSplitCommissionPercentage2REC1(Integer splitCommissionPercentage2REC1) {
		this.splitCommissionPercentage2REC1 = splitCommissionPercentage2REC1;
	}

	public Integer getSplitCommissionPercentage1AE1() {
		return splitCommissionPercentage1AE1;
	}

	public void setSplitCommissionPercentage1AE1(Integer splitCommissionPercentage1AE1) {
		this.splitCommissionPercentage1AE1 = splitCommissionPercentage1AE1;
	}

	public Integer getSplitCommissionPercentage3AE2() {
		return splitCommissionPercentage3AE2;
	}

	public Integer getSplitCommissionPercentage4REC2() {
		return splitCommissionPercentage4REC2;
	}

	public String getSsn() {
		return ssn;
	}

	public String getStartDate() {
		return startDate;
	}

	public String getState() {
		return state;
	}

	public String getStdBenefit() {
		return stdBenefit;
	}

	public Integer getSubContractorId() {
		return subContractorId;
	}

	public String getTelephone() {
		return telephone;
	}

	public Integer getThw() {
		return thw;
	}

	public Double getTotalHolidays() {
		return totalHolidays;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public String getTravelRequired() {
		return travelRequired;
	}

	public Boolean getUnEmployedForTwoMonths() {
		return unEmployedForTwoMonths;
	}

	public String getVertical() {
		return vertical;
	}

	public String getWorkEmail() {
		return workEmail;
	}

	public String getWorkPhone() {
		return workPhone;
	}

	public String getWorksiteAddress1() {
		return worksiteAddress1;
	}

	public String getWorksiteAddress2() {
		return worksiteAddress2;
	}

	public String getWorksiteCity() {
		return worksiteCity;
	}

	public String getWorksiteManagerEmail() {
		return worksiteManagerEmail;
	}

	public String getWorksiteManagerFirstName() {
		return worksiteManagerFirstName;
	}

	public String getWorksiteManagerLastName() {
		return worksiteManagerLastName;
	}

	public String getWorksiteManagerPhone() {
		return worksiteManagerPhone;
	}

	public String getWorksiteState() {
		return worksiteState;
	}

	public String getWorksiteZipCode() {
		return worksiteZipCode;
	}

	public String getZipcode() {
		return zipcode;
	}

	public Boolean isBonusEligible() {
		return bonusEligible;
	}

	public Boolean isRecordAccepted() {
		return recordAccepted;
	}

	public Boolean isSsnGenerated() {
		return ssnGenerated;
	}

	public Boolean istProcessed() {
		return processed;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public void setAe2EmplId(String ae2EmplId) {
		this.ae2EmplId = ae2EmplId;
	}

	public void setAnalyzerCategory1(String analyzerCategory1) {
		this.analyzerCategory1 = analyzerCategory1;
	}

	public void setAnalyzerCategory2Classification(String analyzerCategory2Classification) {
		this.analyzerCategory2Classification = analyzerCategory2Classification;
	}

	public void setAnnualPay(Double annualPay) {
		this.annualPay = annualPay;
	}

	public void setAssignedAnalyzerId(Integer assignedAnalyzerId) {
		this.assignedAnalyzerId = assignedAnalyzerId;
	}

	public void setAssignedParentId(Integer assignedParentId) {
		this.assignedParentId = assignedParentId;
	}

	public void setAssignedSSN(String assignedSSN) {
		this.assignedSSN = assignedSSN;
	}

	public void setBillableCost(Double billableCost) {
		this.billableCost = billableCost;
	}

	public void setBillableHolidays(Integer billableHolidays) {
		this.billableHolidays = billableHolidays;
	}

	public void setBillablePTO(Integer billablePTO) {
		this.billablePTO = billablePTO;
	}

	public void setBillRate(Double billRate) {
		this.billRate = billRate;
	}

	public void setBonusEligible(Boolean bonusEligible) {
		this.bonusEligible = bonusEligible;
	}

	public void setBonusPercentage(Integer bonusPercentage) {
		this.bonusPercentage = bonusPercentage;
	}

	public void setBullhornBatchDataProcessedId(Integer bullhornBatchDataProcessedId) {
		this.bullhornBatchDataProcessedId = bullhornBatchDataProcessedId;
	}

	public void setBullhornBatchDataRawId(Integer bullhornBatchDataRawId) {
		this.bullhornBatchDataRawId = bullhornBatchDataRawId;
	}

	public void setBullhornRecordId(Integer bullhornRecordId) {
		this.bullhornRecordId = bullhornRecordId;
	}

	public void setCheckedReferences(Integer checkedReferences) {
		this.checkedReferences = checkedReferences;
	}

	public void setChkReferralFee(Integer chkReferralFee) {
		this.chkReferralFee = chkReferralFee;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public void setClientAddressId(Integer clientAddressId) {
		this.clientAddressId = clientAddressId;
	}

	public void setClientId(Integer clientId) {
		this.clientId = clientId;
	}

	public void setCommEffDate(String commEffDate) {
		this.commEffDate = commEffDate;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public void setCommisionName1AE1(String commisionName1AE1) {
		this.commisionName1AE1 = commisionName1AE1;
	}

	public void setCommisionName2REC1(String commisionName2REC1) {
		this.commisionName2REC1 = commisionName2REC1;
	}

	public void setCommisionName3AE2(String commisionName3AE2) {
		this.commisionName3AE2 = commisionName3AE2;
	}

	public void setCommisionName4REC2(String commisionName4REC2) {
		this.commisionName4REC2 = commisionName4REC2;
	}

	public void setCommisionPercent1AE1(Double commisionPercent1AE1) {
		this.commisionPercent1AE1 = commisionPercent1AE1;
	}

	public void setCommisionPercent2REC1(Double commisionPercent2REC1) {
		this.commisionPercent2REC1 = commisionPercent2REC1;
	}

	public void setCommisionPercent3AE2(Double commisionPercent3AE2) {
		this.commisionPercent3AE2 = commisionPercent3AE2;
	}

	public void setCommisionPercent4REC2(Double commisionPercent4REC2) {
		this.commisionPercent4REC2 = commisionPercent4REC2;
	}

	public void setCommission5MSP(Double commission5msp) {
		commission5MSP = commission5msp;
	}

	public void setCommissionModel1AE1(String commissionModel1AE1) {
		this.commissionModel1AE1 = commissionModel1AE1;
	}

	public void setCommissionModel2REC1(String commissionModel2REC1) {
		this.commissionModel2REC1 = commissionModel2REC1;
	}

	public void setCommissionModel3AE2(String commissionModel3AE2) {
		this.commissionModel3AE2 = commissionModel3AE2;
	}

	public void setCommissionModel4REC2(String commissionModel4REC2) {
		this.commissionModel4REC2 = commissionModel4REC2;
	}

	public void setCommissionPercentage5MSP(Double commissionPercentage5MSP) {
		this.commissionPercentage5MSP = commissionPercentage5MSP;
	}

	public void setCommissionPerson5MSP(String commissionPerson5MSP) {
		this.commissionPerson5MSP = commissionPerson5MSP;
	}

	public void setCommissionPersonGrade1AE1(String commissionPersonGrade1AE1) {
		this.commissionPersonGrade1AE1 = commissionPersonGrade1AE1;
	}

	public void setCommissionPersonGrade3AE2(String commissionPersonGrade3AE2) {
		this.commissionPersonGrade3AE2 = commissionPersonGrade3AE2;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	public void setConsultantJobBoard(String consultantJobBoard) {
		this.consultantJobBoard = consultantJobBoard;
	}

	public void setCther1OrphanAE2(Integer cther1OrphanAE2) {
		this.cther1OrphanAE2 = cther1OrphanAE2;
	}

	public void setDealPortfolio1AE1(String dealPortfolio1AE1) {
		this.dealPortfolio1AE1 = dealPortfolio1AE1;
	}

	public void setDealPortfolio2REC1(String dealPortfolio2REC1) {
		this.dealPortfolio2REC1 = dealPortfolio2REC1;
	}

	public void setDealPortfolio3AE2(String dealPortfolio3AE2) {
		this.dealPortfolio3AE2 = dealPortfolio3AE2;
	}

	public void setDealPortfolio4REC2(String dealPortfolio4REC2) {
		this.dealPortfolio4REC2 = dealPortfolio4REC2;
	}

	public void setDealType(String dealType) {
		this.dealType = dealType;
	}

	public void setDentalInsurance(String dentalInsurance) {
		this.dentalInsurance = dentalInsurance;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public void setDoubleTime(Double doubleTime) {
		this.doubleTime = doubleTime;
	}

	public void setDoubleTimeBill(Double doubleTimeBill) {
		this.doubleTimeBill = doubleTimeBill;
	}

	public void setEffectiveDate(String effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setEmployeeCategory(String employeeCategory) {
		this.employeeCategory = employeeCategory;
	}

	public void setEmployeeClass(String employeeClass) {
		this.employeeClass = employeeClass;
	}

	public void setEmployeeType(String employeeType) {
		this.employeeType = employeeType;
	}

	public void setEmployeeVeteran(String employeeVeteran) {
		this.employeeVeteran = employeeVeteran;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public void setEquipmentCost(Double equipmentCost) {
		this.equipmentCost = equipmentCost;
	}

	public void setExecOrphanAE1(Integer execOrphanAE1) {
		this.execOrphanAE1 = execOrphanAE1;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setFlsaReferance(String flsaReferance) {
		this.flsaReferance = flsaReferance;
	}

	public void setFlsaStatus(String flsaStatus) {
		this.flsaStatus = flsaStatus;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public void setGeoOffice(String geoOffice) {
		this.geoOffice = geoOffice;
	}

	public void setH1(Integer h1) {
		this.h1 = h1;
	}

	public void setHrbpEmplId(String hrbpEmplId) {
		this.hrbpEmplId = hrbpEmplId;
	}

	public void setImmigrationCost(Double immigrationCost) {
		this.immigrationCost = immigrationCost;
	}

	public void setImportComments(String importComments) {
		this.importComments = importComments;
	}

	public void setInitial(String initial) {
		this.initial = initial;
	}

	public void setJobCategory(String jobCategory) {
		this.jobCategory = jobCategory;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setLiaisonName(String liaisonName) {
		this.liaisonName = liaisonName;
	}

	public void setLtdBenefit(String ltdBenefit) {
		this.ltdBenefit = ltdBenefit;
	}

	public void setManagingDirector(String managingDirector) {
		this.managingDirector = managingDirector;
	}

	public void setManagingDirectorEmplId(String managingDirectorEmplId) {
		this.managingDirectorEmplId = managingDirectorEmplId;
	}

	public void setMobilePager(String mobilePager) {
		this.mobilePager = mobilePager;
	}

	public void setMspEmplId(String mspEmplId) {
		this.mspEmplId = mspEmplId;
	}

	public void setNonBillableHolidays(Integer nonBillableHolidays) {
		this.nonBillableHolidays = nonBillableHolidays;
	}

	public void setNonBillablePTO(Integer nonBillablePTO) {
		this.nonBillablePTO = nonBillablePTO;
	}

	public void setOffice(String office) {
		this.office = office;
	}

	public void setOneTimeBill(Double oneTimeBill) {
		this.oneTimeBill = oneTimeBill;
	}

	public void setOneTimePay(Double oneTimePay) {
		this.oneTimePay = oneTimePay;
	}

	public void setOther2OrphanREC2(Integer other2OrphanREC2) {
		this.other2OrphanREC2 = other2OrphanREC2;
	}

	public void setOtherAmountHour(Double otherAmountHour) {
		this.otherAmountHour = otherAmountHour;
	}

	public void setOtherDollar(Double otherDollar) {
		this.otherDollar = otherDollar;
	}

	public void setPayRate(Double payRate) {
		this.payRate = payRate;
	}

	public void setPerDiem(Double perDiem) {
		this.perDiem = perDiem;
	}

	public void setPlacementId(Integer placementId) {
		this.placementId = placementId;
	}

	public void setPortfolio(String portfolio) {
		this.portfolio = portfolio;
	}

	public void setPortfolioDescription(String portfolioDescription) {
		this.portfolioDescription = portfolioDescription;
	}

	public void setProcessed(Boolean processed) {
		this.processed = processed;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public void setProjectCost(Double projectCost) {
		this.projectCost = projectCost;
	}

	public void setPsClientId(String psClientId) {
		this.psClientId = psClientId;
	}

	public void setPsCustomerAddressId(Integer psCustomerAddressId) {
		this.psCustomerAddressId = psCustomerAddressId;
	}

	public void setPsVendorId(String psVendorId) {
		this.psVendorId = psVendorId;
	}

	public void setPto(Integer pto) {
		this.pto = pto;
	}

	public void setRec1EmplId(String rec1EmplId) {
		this.rec1EmplId = rec1EmplId;
	}

	public void setRec2EmplId(String rec2EmplId) {
		this.rec2EmplId = rec2EmplId;
	}

	public void setRecordAccepted(Boolean recordAccepted) {
		this.recordAccepted = recordAccepted;
	}

	public void setRecordProcessingStatus(String recordProcessingStatus) {
		this.recordProcessingStatus = recordProcessingStatus;
	}

	public void setRecruiterOrphanREC1(Integer recruiterOrphanREC1) {
		this.recruiterOrphanREC1 = recruiterOrphanREC1;
	}

	public void setRecruitingClassification(String recruitingClassification) {
		this.recruitingClassification = recruitingClassification;
	}

	public void setReferralFeeAmount(Double referralFeeAmount) {
		this.referralFeeAmount = referralFeeAmount;
	}

	public void setSickLeaveType(String sickLeaveType) {
		this.sickLeaveType = sickLeaveType;
	}

	public void setSiteId(Integer siteId) {
		this.siteId = siteId;
	}

	public void setSkillCategory(String skillCategory) {
		this.skillCategory = skillCategory;
	}

	public void setSplitCommissionPercentage3AE2(Integer splitCommissionPercentage3AE2) {
		this.splitCommissionPercentage3AE2 = splitCommissionPercentage3AE2;
	}

	public void setSplitCommissionPercentage4REC2(Integer splitCommissionPercentage4REC2) {
		this.splitCommissionPercentage4REC2 = splitCommissionPercentage4REC2;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
	}

	public void setSsnGenerated(Boolean ssnGenerated) {
		this.ssnGenerated = ssnGenerated;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public void setState(String state) {
		this.state = state;
	}

	public void setStdBenefit(String stdBenefit) {
		this.stdBenefit = stdBenefit;
	}

	public void setSubContractorId(Integer subContractorId) {
		this.subContractorId = subContractorId;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}


	public void setThw(Integer thw) {
		this.thw = thw;
	}

	public void setTotalHolidays(Double totalHolidays) {
		this.totalHolidays = totalHolidays;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public void setTravelRequired(String travelRequired) {
		this.travelRequired = travelRequired;
	}

	public void setUnEmployedForTwoMonths(Boolean unEmployedForTwoMonths) {
		this.unEmployedForTwoMonths = unEmployedForTwoMonths;
	}

	public void setVertical(String vertical) {
		this.vertical = vertical;
	}

	public void setWorkEmail(String workEmail) {
		this.workEmail = workEmail;
	}

	public void setWorkPhone(String workPhone) {
		this.workPhone = workPhone;
	}

	public void setWorksiteAddress1(String worksiteAddress1) {
		this.worksiteAddress1 = worksiteAddress1;
	}

	public void setWorksiteAddress2(String worksiteAddress2) {
		this.worksiteAddress2 = worksiteAddress2;
	}

	public void setWorksiteCity(String worksiteCity) {
		this.worksiteCity = worksiteCity;
	}

	public void setWorksiteManagerEmail(String worksiteManagerEmail) {
		this.worksiteManagerEmail = worksiteManagerEmail;
	}

	public void setWorksiteManagerFirstName(String worksiteManagerFirstName) {
		this.worksiteManagerFirstName = worksiteManagerFirstName;
	}

	public void setWorksiteManagerLastName(String worksiteManagerLastName) {
		this.worksiteManagerLastName = worksiteManagerLastName;
	}

	public void setWorksiteManagerPhone(String worksiteManagerPhone) {
		this.worksiteManagerPhone = worksiteManagerPhone;
	}

	public void setWorksiteState(String worksiteState) {
		this.worksiteState = worksiteState;
	}

	public void setWorksiteZipCode(String worksiteZipCode) {
		this.worksiteZipCode = worksiteZipCode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	@Override
	public String toString() {
		return "BullhornBatchDataProcessedDTO [bullhornBatchDataProcessedId=" + bullhornBatchDataProcessedId
				+ ", lastName=" + lastName + ", firstName=" + firstName + ", state=" + state + ", email=" + email
				+ ", ssn=" + ssn + ", dob=" + dob + ", jobTitle=" + jobTitle + ", employeeType=" + employeeType
				+ ", thw=" + thw + ", billRate=" + billRate + ", payRate=" + payRate + ", clientId=" + clientId
				+ ", psClientId=" + psClientId + ", startDate=" + startDate + ", endDate=" + endDate
				+ ", effectiveDate=" + effectiveDate + ", pto=" + pto + ", office=" + office + ", hrbpEmplId="
				+ hrbpEmplId + ", liaisonName=" + liaisonName + ", jobCategory=" + jobCategory + ", employeeCategory="
				+ employeeCategory + ", unEmployedForTwoMonths=" + unEmployedForTwoMonths + ", consultantJobBoard="
				+ consultantJobBoard + ", gender=" + gender + ", flsaStatus=" + flsaStatus + ", vertical=" + vertical
				+ ", product=" + product + ", travelRequired=" + travelRequired + ", recruitingClassification="
				+ recruitingClassification + ", managingDirectorEmplId=" + managingDirectorEmplId
				+ ", managingDirector=" + managingDirector + ", dealType=" + dealType + ", commEffDate=" + commEffDate
				+ ", ae1EmplId=" + ae1EmplId + ", commisionName1AE1=" + commisionName1AE1 + ", commissionModel1AE1="
				+ commissionModel1AE1 + ", commisionPercent1AE1=" + commisionPercent1AE1
				+ ", commissionPersonGrade1AE1=" + commissionPersonGrade1AE1 + ", execOrphanAE1=" + execOrphanAE1
				+ ", splitCommissinPercentage1AE1=" + splitCommissionPercentage1AE1 + ", rec1EmplId=" + rec1EmplId
				+ ", commisionName2REC1=" + commisionName2REC1 + ", commissionModel2REC1=" + commissionModel2REC1
				+ ", commisionPercent2REC1=" + commisionPercent2REC1 + ", recruiterOrphanREC1=" + recruiterOrphanREC1
				+ ", splitCommisionPercentage2REC1=" + splitCommissionPercentage2REC1 + ", initial=" + initial
				+ ", address1=" + address1 + ", address2=" + address2 + ", city=" + city + ", zipcode=" + zipcode
				+ ", telephone=" + telephone + ", workPhone=" + workPhone + ", mobilePager=" + mobilePager
				+ ", workEmail=" + workEmail + ", ae2EmplId=" + ae2EmplId + ", commisionName3AE2=" + commisionName3AE2
				+ ", commissionModel3AE2=" + commissionModel3AE2 + ", commisionPercent3AE2=" + commisionPercent3AE2
				+ ", commissionPersonGrade3AE2=" + commissionPersonGrade3AE2 + ", cther1OrphanAE2=" + cther1OrphanAE2
				+ ", splitCommissionPercentage3AE2=" + splitCommissionPercentage3AE2 + ", rec2EmplId=" + rec2EmplId
				+ ", commisionName4REC2=" + commisionName4REC2 + ", commissionModel4REC2=" + commissionModel4REC2
				+ ", commisionPercent4REC2=" + commisionPercent4REC2 + ", other2OrphanREC2=" + other2OrphanREC2
				+ ", splitCommissionPercentage4REC2=" + splitCommissionPercentage4REC2 + ", billablePTO=" + billablePTO
				+ ", nonBillablePTO=" + nonBillablePTO + ", totalHolidays=" + totalHolidays + ", billableHolidays="
				+ billableHolidays + ", nonBillableHolidays=" + nonBillableHolidays + ", sickLeaveType=" + sickLeaveType
				+ ", oneTimeBill=" + oneTimeBill + ", annualPay=" + annualPay + ", doubleTimeBill=" + doubleTimeBill
				+ ", subContractorId=" + subContractorId + ", psVendorId=" + psVendorId + ", flsaReferance="
				+ flsaReferance + ", bonusEligible=" + bonusEligible + ", bonusPercentage=" + bonusPercentage
				+ ", employeeClass=" + employeeClass + ", equipmentCost=" + equipmentCost + ", oneTimePay=" + oneTimePay
				+ ", doubleTime=" + doubleTime + ", comments=" + comments + ", geoOffice=" + geoOffice
				+ ", employeeVeteran=" + employeeVeteran + ", checkedReferences=" + checkedReferences + ", fax=" + fax
				+ ", otherDollar=" + otherDollar + ", otherAmountHour=" + otherAmountHour + ", perDiem=" + perDiem
				+ ", h1=" + h1 + ", dentalInsurance=" + dentalInsurance + ", chkReferralFee=" + chkReferralFee
				+ ", referralFeeAmount=" + referralFeeAmount + ", stdBenefit=" + stdBenefit + ", ltdBenefit="
				+ ltdBenefit + ", analyzerCategory2Classification=" + analyzerCategory2Classification
				+ ", skillCategory=" + skillCategory + ", immigrationCost=" + immigrationCost + ", billableCost="
				+ billableCost + ", projectCost=" + projectCost + ", portfolio=" + portfolio + ", mspEmplId="
				+ mspEmplId + ", commissionPerson5MSP=" + commissionPerson5MSP + ", commissionPercentage5MSP="
				+ commissionPercentage5MSP + ", commission5MSP=" + commission5MSP + ", siteId=" + siteId
				+ ", worksiteAddress1=" + worksiteAddress1 + ", worksiteAddress2=" + worksiteAddress2
				+ ", worksiteCity=" + worksiteCity + ", worksiteState=" + worksiteState + ", worksiteZipCode="
				+ worksiteZipCode + ", worksiteManagerFirstName=" + worksiteManagerFirstName + ", worksiteManagerEmail="
				+ worksiteManagerEmail + ", worksiteManagerPhone=" + worksiteManagerPhone + ", worksiteManagerLastName="
				+ worksiteManagerLastName + ", clientAddressId=" + clientAddressId + ", psCustomerAddressId="
				+ psCustomerAddressId + ", analyzerCategory1=" + analyzerCategory1 + ", portfolioDescription="
				+ portfolioDescription + ", bullhornBatchId=" + bullhornBatchCode + ", transactionType=" + transactionType
				+ ", bullhornRecordId=" + bullhornRecordId + ", placementId=" + placementId
				+ ", recordProcessingStatus=" + recordProcessingStatus + ", companyCode=" + companyCode + ", processed="
				+ processed + ", importComments=" + importComments + ", dealPortfolio1AE1=" + dealPortfolio1AE1
				+ ", dealPortfolio2REC1=" + dealPortfolio2REC1 + ", dealPortfolio3AE2=" + dealPortfolio3AE2
				+ ", dealPortfolio4REC2=" + dealPortfolio4REC2 + ", recordAccepted=" + recordAccepted
				+ ", assignedAnalyzerId=" + assignedAnalyzerId + ", assignedParentId=" + assignedParentId
				+ ", assignedSSN=" + assignedSSN + ", ssnGenerated=" + ssnGenerated + ", recordHasErrors="
				+ recordHasErrors + ", bullhornBatchDataRawId=" + bullhornBatchDataRawId + "]";
	}

	public String getBullhornBatchCode() {
		return bullhornBatchCode;
	}

	public void setBullhornBatchCode(String bullhornBatchCode) {
		this.bullhornBatchCode = bullhornBatchCode;
	}

	public Integer getOther1OrphanAE2() {
		return other1OrphanAE2;
	}

	public void setOther1OrphanAE2(Integer other1OrphanAE2) {
		this.other1OrphanAE2 = other1OrphanAE2;
	}

	public Timestamp getEntryDateTime() {
		return entryDateTime;
	}

	public void setEntryDateTime(Timestamp entryDateTime) {
		this.entryDateTime = entryDateTime;
	}

	public Timestamp getProcessingDateTime() {
		return processingDateTime;
	}

	public void setProcessingDateTime(Timestamp processingDateTime) {
		this.processingDateTime = processingDateTime;
	}

	public Timestamp getUpdateDateTime() {
		return updateDateTime;
	}

	public void setUpdateDateTime(Timestamp updateDateTime) {
		this.updateDateTime = updateDateTime;
	}

	public Boolean getBonusEligible() {
		return bonusEligible;
	}

	public Boolean getProcessed() {
		return processed;
	}

	public Boolean getRecordAccepted() {
		return recordAccepted;
	}

	public String getRecordHasErrors() {
		return recordHasErrors;
	}

	public void setRecordHasErrors(String recordHasErrors) {
		this.recordHasErrors = recordHasErrors;
	}

	public Boolean getSsnGenerated() {
		return ssnGenerated;
	}

	public Timestamp getAnalyzerSiteCreationDate() {
		return analyzerSiteCreationDate;
	}

	public void setAnalyzerSiteCreationDate(Timestamp analyzerSiteCreationDate) {
		this.analyzerSiteCreationDate = analyzerSiteCreationDate;
	}

	public Integer getBullhornBatchAnalyzerStagingId() {
		return bullhornBatchAnalyzerStagingId;
	}

	public void setBullhornBatchAnalyzerStagingId(Integer bullhornBatchAnalyzerStagingId) {
		this.bullhornBatchAnalyzerStagingId = bullhornBatchAnalyzerStagingId;
	}

	public Integer getBullhornPlacementId() {
		return bullhornPlacementId;
	}

	public void setBullhornPlacementId(Integer bullhornPlacementId) {
		this.bullhornPlacementId = bullhornPlacementId;
	}

	public Integer getBullhornWorksiteDataId() {
		return bullhornWorksiteDataId;
	}

	public void setBullhornWorksiteDataId(Integer bullhornWorksiteDataId) {
		this.bullhornWorksiteDataId = bullhornWorksiteDataId;
	}

	public String getIsAnalyzerSiteCreated() {
		return isAnalyzerSiteCreated;
	}

	public void setIsAnalyzerSiteCreated(String isAnalyzerSiteCreated) {
		this.isAnalyzerSiteCreated = isAnalyzerSiteCreated;
	}

	public String getIsTransferredToAnalyzer() {
		return isTransferredToAnalyzer;
	}

	public void setIsTransferredToAnalyzer(String isTransferredToAnalyzer) {
		this.isTransferredToAnalyzer = isTransferredToAnalyzer;
	}

	public String getIsTransferredToAnalyzerStaging() {
		return isTransferredToAnalyzerStaging;
	}

	public void setIsTransferredToAnalyzerStaging(String isTransferredToAnalyzerStaging) {
		this.isTransferredToAnalyzerStaging = isTransferredToAnalyzerStaging;
	}

	public String getIsWorksiteAddressDataValidated() {
		return isWorksiteAddressDataValidated;
	}

	public void setIsWorksiteAddressDataValidated(String isWorksiteAddressDataValidated) {
		this.isWorksiteAddressDataValidated = isWorksiteAddressDataValidated;
	}

	public String getIsWorksiteChanged() {
		return isWorksiteChanged;
	}

	public void setIsWorksiteChanged(String isWorksiteChanged) {
		this.isWorksiteChanged = isWorksiteChanged;
	}

	public String getSubmittedByEmail() {
		return submittedByEmail;
	}

	public void setSubmittedByEmail(String submittedByEmail) {
		this.submittedByEmail = submittedByEmail;
	}

	public String getSubmittedByEmplId() {
		return submittedByEmplId;
	}

	public void setSubmittedByEmplId(String submittedByEmplId) {
		this.submittedByEmplId = submittedByEmplId;
	}

	public Timestamp getTransferToAnalyzerDate() {
		return transferToAnalyzerDate;
	}

	public void setTransferToAnalyzerDate(Timestamp transferToAnalyzerDate) {
		this.transferToAnalyzerDate = transferToAnalyzerDate;
	}

	public Timestamp getTransferToAnalyzerStagingDate() {
		return transferToAnalyzerStagingDate;
	}

	public void setTransferToAnalyzerStagingDate(Timestamp transferToAnalyzerStagingDate) {
		this.transferToAnalyzerStagingDate = transferToAnalyzerStagingDate;
	}

	public Timestamp getWorksiteChangeDate() {
		return worksiteChangeDate;
	}

	public void setWorksiteChangeDate(Timestamp worksiteChangeDate) {
		this.worksiteChangeDate = worksiteChangeDate;
	}	

	public Integer getBullhornTerminationDataProcessedId() {
		return bullhornTerminationDataProcessedId;
	}

	public void setBullhornTerminationDataProcessedId(Integer bullhornTerminationDataProcessedId) {
		this.bullhornTerminationDataProcessedId = bullhornTerminationDataProcessedId;
	}	

	public String getTerminateDate() {
		return terminateDate;
	}

	public void setTerminateDate(String terminateDate) {
		this.terminateDate = terminateDate;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getEligibleForReHire() {
		return eligibleForReHire;
	}

	public void setEligibleForReHire(String eligibleForReHire) {
		this.eligibleForReHire = eligibleForReHire;
	}

	public String getOverrideTermination() {
		return overrideTermination;
	}

	public void setOverrideTermination(String overrideTermination) {
		this.overrideTermination = overrideTermination;
	}

	public String getFalseTermination() {
		return falseTermination;
	}

	public void setFalseTermination(String falseTermination) {
		this.falseTermination = falseTermination;
	}

	public Integer getBullhornTerminationDataRawId() {
		return bullhornTerminationDataRawId;
	}

	public void setBullhornTerminationDataRawId(Integer bullhornTerminationDataRawId) {
		this.bullhornTerminationDataRawId = bullhornTerminationDataRawId;
	}	

	public String getTerminationDateOverrideDate() {
		return terminationDateOverrideDate;
	}

	public void setTerminationDateOverrideDate(String terminationDateOverrideDate) {
		this.terminationDateOverrideDate = terminationDateOverrideDate;
	}

	public Integer getBullhornTerminationDataStagingId() {
		return bullhornTerminationDataStagingId;
	}

	public void setBullhornTerminationDataStagingId(Integer bullhornTerminationDataStagingId) {
		this.bullhornTerminationDataStagingId = bullhornTerminationDataStagingId;
	}

	public String getIsRecordHasErrors() {
		return isRecordHasErrors;
	}

	public void setIsRecordHasErrors(String isRecordHasErrors) {
		this.isRecordHasErrors = isRecordHasErrors;
	}

	public String getDataSource() {
		return dataSource;
	}

	public void setDataSource(String dataSource) {
		this.dataSource = dataSource;
	}

	public Integer getBullhornBatchInfoId() {
		return bullhornBatchInfoId;
	}

	public void setBullhornBatchInfoId(Integer bullhornBatchInfoId) {
		this.bullhornBatchInfoId = bullhornBatchInfoId;
	}

	public String getAe1EmplId() {
		return ae1EmplId;
	}

	public void setAe1EmplId(String ae1EmplId) {
		this.ae1EmplId = ae1EmplId;
	}

	public String getPlacementType() {
		return placementType;
	}

	public void setPlacementType(String placementType) {
		this.placementType = placementType;
	}


	public String getCoSellStatus() {
		return coSellStatus;
	}

	public void setCoSellStatus(String coSellStatus) {
		this.coSellStatus = coSellStatus;
	}

	public String getProcessingComments() {
		return processingComments;
	}

	public void setProcessingComments(String processingComments) {
		this.processingComments = processingComments;
	}

	public String getDentalInsurance() {
		return dentalInsurance;
	}
	
	
}
