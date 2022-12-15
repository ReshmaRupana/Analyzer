package com.disys.analyzer.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Transient;

/**
 * The persistent class for the Analyser database table.
 * 
 */
@Entity
// @Table(schema="Analyser.dbo")
@NamedQueries(value = { @NamedQuery(name = "Analyser.findAll", query = "SELECT a FROM Analyser a"),
        @NamedQuery(name = "Analyser.getHealthAnd401K", query = "Select a.health, a.k401 from Analyser a " + "where  a.analyserId = :analyserId"),
        @NamedQuery(name = "Analyser.findDuplicateSsn", query = "Select a.ssn from Analyser a " + "where a.ssn = :ssn"),
        @NamedQuery(name = "Analyser.findDuplicateFlsaReference", query = "Select a.FLSAReferance from Analyser a "
                + "where a.FLSAReferance = :flsaReference") })
public class Analyser implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy = GenerationType.AUTO) @Column(name = "AnalyserId") private Long analyserId;
	
	@Column(name = "Address1") private String address1;
	
	@Column(name = "Address2") private String address2;
	
	@Column(name = "AnalyzerCategory1") private String analyzerCategory1;
	
	@Column(name = "AnalyzerCategory2") private String analyzerCategory2;
	
	@Column(name = "AnnualPay") private String annualPay;
	
	@Column(name = "App_Bkup") private Integer appBackup;
	
	@Column(name = "ApprovalDate") private Timestamp approvalDate;
	
	@Column(name = "Approved_By_Admin") private String approvedByAdmin;
	
	@Column(name = "Approved_By_Manager") private String approvedByManager;
	
	@Column(name = "BillRate") private String billRate;
	
	@Column(name = "BonusAmount") private BigDecimal bonusAmount;
	
	@Column(name = "BonusPercentage") private String bonusPercentage;
	
	@Column(name = "Branch") private String branch;
	
	@Column(name = "BranchInter") private String branchInter;
	
	@Column(name = "CheckedReferences") private String checkedReferences;
	
	private String chkReferralFee;
	
	@Column(name = "City") private String city;
	
	@Column(name = "ClientAddressId") private Integer clientAddressId;
	
	@Column(name = "ClientId") private Integer clientId;
	
	@Column(name = "Com") private String com;
	
	@Column(name = "Comm") private String comm;
	
	@Column(name = "CommEffDate") private String commEffectiveDate;
	
	@Column(name = "Comments") private String comments;
	
	@Column(name = "CommentsInter") private String commentsInter;
	
	@Column(name = "Commision_Name1") private String commisionName1;
	
	@Column(name = "Commision_Name1Inter") private String commisionName1Inter;
	
	@Column(name = "Commision_Name2") private String commisionName2;
	
	@Column(name = "Commision_Name2Inter") private String commisionName2Inter;
	
	@Column(name = "Commision_Name3") private String commisionName3;
	
	@Column(name = "Commision_Name3Inter") private String commisionName3Inter;
	
	@Column(name = "Commision_Name4") private String commisionName4;
	
	@Column(name = "Commision_Name4Inter") private String commisionName4Inter;
	
	@Column(name = "Commision_Percent1") private String commisionPercent1;
	
	@Column(name = "Commision_Percent1Inter") private String commisionPercent1Inter;
	
	@Column(name = "Commision_Percent2") private String commisionPercent2;
	
	@Column(name = "Commision_Percent2Inter") private String commisionPercent2Inter;
	
	@Column(name = "Commision_Percent3") private String commisionPercent3;
	
	@Column(name = "Commision_Percent3Inter") private String commisionPercent3Inter;
	
	@Column(name = "Commision_Percent4") private String commisionPercent4;
	
	@Column(name = "Commision_Percent4Inter") private String commisionPercent4Inter;
	
	@Column(name = "Commision1") private BigDecimal commision1;
	
	@Column(name = "Commision1Inter") private String commision1Inter;
	
	@Column(name = "Commision2") private BigDecimal commision2;
	
	@Column(name = "Commision2Inter") private String commision2Inter;
	
	@Column(name = "Commision3") private BigDecimal commision3;
	
	@Column(name = "Commision3Inter") private String commision3Inter;
	
	@Column(name = "Commision4") private BigDecimal commision4;
	
	@Column(name = "Commision4Inter") private String commision4Inter;
	
	@Column(name = "Commission") private BigDecimal commission;
	
	@Column(name = "CommissionModel1") private String commissionModel1;
	
	@Column(name = "CommissionModel2") private String commissionModel2;
	
	@Column(name = "CommissionModel3") private String commissionModel3;
	
	@Column(name = "CommissionModel4") private String commissionModel4;
	
	@Column(name = "ConsultantJobBoard") private String consultantJobBoard;
	
	@Column(name = "ContractVehicle") private String contractVehicle;
	
	@Column(name = "Country") private String country;
	
	@Column(name = "Currency") private String currency;
	
	@Column(name = "Custom1") private String custom1;
	
	@Column(name = "Custom2") private String custom2;
	
	@Column(name = "DealType") private String dealType;
	
	private String dentalInsurance;
	
	@Column(name = "DentalInsuranceAmount") private BigDecimal dentalInsuranceAmount;
	
	@Column(name = "Discount") private BigDecimal discount;
	
	@Column(name = "DiscountRate") private String discountRate;
	
	@Column(name = "DOB") private String dob;
	
	@Column(name = "DoubleTime") private String doubleTime;
	
	@Column(name = "DoubleTimeBill") private String doubleTimeBill;
	
	@Column(name = "Education") private String education;
	
	@Column(name = "EffectiveDate") private String effectiveDate;
	
	private String eligibleForReHire;
	
	@Column(name = "Email") private String email;
	
	@Column(name = "EMPLOYEECLASS") private String employeeClass;
	
	@Column(name = "EmployeeVeteran") private String employeeVeteran;
	
	@Column(name = "EmpType") private String employeeType;
	
	@Column(name = "EndDate") private String endDate;
	
	@Column(name = "EnteredBy") private String enteredBy;
	
	@Column(name = "EntryDate") private Timestamp entryDate;
	
	@Column(name = "EquipmentCost") private BigDecimal equipmentCost;
	
	@Column(name = "ExecOrphan") private String execOrphan;
	
	@Column(name = "ExecOrphanInter") private String execOrphanInter;
	
	@Column(name = "Fax") private String fax;
	
	private String FLSAReferance;
	
	private String FLSAStatus;
	
	@Column(name = "FName") private String firstName;
	
	@Column(name = "LName") private String lastName;
	
	@Column(name = "GA") private BigDecimal ga;
	
	@Column(name = "Gender") private String gender;
	
	@Column(name = "H1") private String h1;
	
	@Column(name = "Health") private String health;
	
	@Column(name = "HealthBenefitRates") private String healthBenefitRates;
	
	@Column(name = "ImmigrationCost") private BigDecimal immigrationCost;
	
	@Column(name = "Initial") private String initial;
	
	@Column(name = "Internal_Accounting") private String internalAccounting;
	
	@Column(name = "IsBonusEligible") private String isBonusEligible;
	
	@Column(name = "IsHire") private String isHire;
	
	@Column(name = "IsProcessed") private String isProcessed;
	
	@Column(name = "IsRehired") private String isRehired;
	
	@Column(name = "Job_Title") private String jobTitle;
	
	@Column(name = "JobBillRate") private String jobBillRate;
	
	@Column(name = "JobCategory") private String jobCategory;
	
	@Column(name = "JobPayRate") private String jobPayRate;
	
	@Column(name = "Jrsr") private String jrsr;
	
	private String k401;
	
	@Column(name = "Leave") private String leave;
	
	@Column(name = "LiaisonName") private String liaisonName;
	
	@Column(name = "LtdBenefit") private String ltdBenefit;
	
	@Column(name = "Mobile_Pager") private String mobilePager;
	
	@Column(name = "Mrmrs") private String mrmrs;
	
	@Column(name = "NewParentId") private Integer newParentId;
	
	@Column(name = "NonBillableCost") private BigDecimal nonBillableCost;
	
	@Column(name = "OldDtPayRate") private BigDecimal oldDtPayRate;
	
	@Column(name = "OldOtPayRate") private BigDecimal oldOtPayRate;
	
	@Column(name = "OldPayRate") private BigDecimal oldPayRate;
	
	@Column(name = "OldRateChangedAnalyserId") private BigDecimal oldRateChangedAnalyserId;
	
	@Column(name = "OneTime_Bill") private String oneTimeBill;
	
	@Column(name = "OneTime_Pay") private String oneTimePay;
	
	@Column(name = "Org_Id") private Integer orgId;
	
	@Column(name = "Other_Dollar") private String otherDollar;
	
	@Column(name = "Other1Orphan") private String other1Orphan;
	
	@Column(name = "Other1OrphanInter") private String other1OrphanInter;
	
	@Column(name = "Other2Orphan") private String other2Orphan;
	
	@Column(name = "Other2OrphanInter") private String other2OrphanInter;
	
	@Column(name = "OtherAmount_Hour") private String otherAmountHour;
	
	@Column(name = "ParentId") private String parentId;
	
	@Column(name = "PayPeriodType") private String payPeriodType;
	
	@Column(name = "PayRate") private String payRate;
	
	@Column(name = "PayRateChangedBy") private String payRateChangedBy;
	
	@Column(name = "PayRateChangeEffectiveDate") private Timestamp payRateChangeEffectiveDate;
	
	@Column(name = "PayRateChangeEntryDate") private Timestamp payRateChangeEntryDate;
	
	@Column(name = "PayRateChangeFlag") private String payRateChangeFlag;
	
	@Column(name = "PayRateChangeProcessedBy") private String payRateChangeProcessedBy;
	
	@Column(name = "PayRateChangeProcessedDate") private Timestamp payRateChangeProcessedDate;
	
	@Column(name = "PayRateChangeProcessedFlag") private String payRateChangeProcessedFlag;
	
	@Column(name = "Percentage_401k") private String percentage401k;
	
	@Column(name = "PerDiem") private String perDiem;
	
	@Column(name = "PermanentPlacement") private String permanentPlacement;
	
	@Column(name = "PermGAAmount") private BigDecimal permGAAmount;
	
	@Column(name = "PlacementAmount") private BigDecimal placementAmount;
	
	@Column(name = "PlacementDate") private String placementDate;
	
	@Column(name = "PlacementPercentage") private String placementPercentage;
	
	@Column(name = "PlacementSalaryAmount") private String placementSalaryAmount;
	
	@Column(name = "PoNumber") private String poNumber;
	
	@Column(name = "ProcessDate") private Timestamp processDate;
	
	@Column(name = "Product") private String product;
	
	@Column(name = "Profit") private BigDecimal profit;
	
	@Column(name = "PTO") private String pto;
	
	@Column(name = "Reason") private String reason;
	
	@Column(name = "RecordStatus") private String recordStatus;
	
	@Column(name = "RecruiterOrphan") private String recruiterOrphan;
	
	@Column(name = "RecruiterOrphanInter") private String recruiterOrphanInter;
	
	@Column(name = "RecruitingClassification") private String recruitingClassification;
	
	private String referralFeeAmount;
	
	@Column(name = "RehireDate") private Timestamp rehireDate;
	
	@Column(name = "RehiredBy") private String rehiredBy;
	
	@Column(name = "SiteId") private Integer siteId;
	
	@Column(name = "SKILLCATEGORY") private String skillCategory;
	
	@Column(name = "Split") private Integer split;
	
	@Column(name = "SPLITCOMMISSIONPERCENTAGE1") private String splitCommissionPercentage1;
	
	@Column(name = "SPLITCOMMISSIONPERCENTAGE2") private String splitCommissionPercentage2;
	
	@Column(name = "SPLITCOMMISSIONPERCENTAGE3") private String splitCommissionPercentage3;
	
	@Column(name = "SPLITCOMMISSIONPERCENTAGE4") private String splitCommissionPercentage4;
	
	@Column(name = "Spread_w") private BigDecimal spreadW;
	
	@Column(name = "SSN") private String ssn;
	
	@Column(name = "StartDate") private String startDate;
	
	@Column(name = "State") private String state;
	
	@Column(name = "StdBenefit") private String stdBenefit;
	
	@Column(name = "SubContractorId") private Integer subContractorId;
	
	@Column(name = "SubmissionDate") private Timestamp submissionDate;
	
	@Column(name = "Taxes") private BigDecimal taxes;
	
	@Column(name = "Telephone") private String telephone;
	
	@Column(name = "Temps") private String temps;
	
	@Column(name = "TerminateDate") private String terminateDate;
	
	@Column(name = "THW") private String thw;
	
	@Column(name = "TravelRequired") private String travelRequired;
	
	@Column(name = "UnEmployedForTwoMonths") private String unEmployedForTwoMonths;
	
	@Column(name = "UserId") private String userId;
	
	@Column(name = "VERTICAL") private String vertical;
	
	@Column(name = "VERTICALTIMESHEETTYPE") private String verticaltimesheettype;
	
	private String workEmail;
	
	@Column(name = "WorkPhone") private String workPhone;
	
	@Column(name = "WorksiteState") private String worksiteState;
	
	@Column(name = "Zipcode") private String zipCode;
	
	@Column(name = "PROJECTCOST") private Double projectCost;
	
	@Column(name = "SICKLEAVEPERHOURRATE") private Double sickLeavePerHourRate;
	
	@Column(name = "SickLeaveCost") private Double sickLeaveCost;
	
	@Column(name = "FALSETERMINATION") private String falseTermination;
	
	@Column(name = "SICKLEAVECAP") private Integer sickLeaveCap;
	
	@Column(name = "MANAGINGDIRECTOR") private String managingDirector;
	
	@Column(name = "CommissionPerson5") private String commisionName5;
	@Column(name = "CommisionPercentage5") private String commisionPercent5;
	@Column(name = "Commission5") private Double commision5;
	
	@Column(name = "CommissionPerson6") private String commisionName6;
	@Column(name = "CommisionPercentage6") private String commisionPercent6;
	@Column(name = "Commission6") private Double commision6;
	
	@Column(name = "CommissionPerson7") private String commisionName7;
	@Column(name = "CommisionPercentage7") private String commisionPercent7;
	@Column(name = "Commission7") private Double commision7;
	
	@Column(name = "CommissionPerson8") private String commisionName8;
	@Column(name = "CommisionPercentage8") private String commisionPercent8;
	@Column(name = "Commission8") private Double commision8;
	
	@Column(name = "CommissionPerson9") private String commisionName9;
	@Column(name = "CommisionPercentage9") private String commisionPercent9;
	@Column(name = "Commission9") private Double commision9;
	
	@Column(name = "CommissionPersonGrade1") private String commissionPersonGrade1;
	
	@Column(name = "CommissionPersonGrade2") private String commissionPersonGrade2;
	
	@Column(name = "CommissionPersonGrade3") private String commissionPersonGrade3;
	
	@Column(name = "CommissionPersonGrade4") private String commissionPersonGrade4;
	
	@Column(name = "ISAddressUSPSValidated") private String isAddressUSPSValidated;
	
	@Column(name = "USPSAddressValidationDate") private Timestamp uSPSAddressValidationDate;
	
	@Transient private String terminated;
	
	// ---------- 16-08-2018 -------------------//
	@Column(name = "BillablePTO") private Double billablePTO;
	
	@Column(name = "NonBillablePTO") private Double nonBillablePTO;
	
	@Column(name = "BillablePTOCost") private Double billablePTOCost;
	
	@Column(name = "NonBillablePTOCost") private Double nonBillablePTOCost;
	
	@Column(name = "TotalHolidays") private Double totalHolidays;
	
	@Column(name = "BillableHolidays") private Double billableHolidays;
	
	@Column(name = "NonBillableHolidays") private Double nonBillableHolidays;
	
	@Column(name = "BillableHolidaysCost") private Double billableHolidaysCost;
	
	@Column(name = "NonBillableHolidaysCost") private Double nonBillableHolidaysCost;
	
	@Column(name = "GrossProfitPercentage") private Double grossProfitPercentage;
	@Column(name = "GrossProfit") private Double grossProfit;
	@Column(name = "CommissionableProfit") private Double commissionableProfit;
	@Column(name = "RejectionStatus") private Integer rejectionStatus;
	@Column(name = "RejectionReason") private String rejectionReason;
	@Column(name = "RejectedBy") private String rejectedBy;
	@Column(name = "RejectionDate") private Timestamp rejectionDate;
	@Column(name = "GeoOffice") private String geoOffice;

	@Column(name = "Longitude")
	private String longitude;
	
	@Column(name = "Latitude")
	private String latitude;
	
	
	@Transient
	private String clientCompanyName;
	
	@Column(name = "SickLeaveType")
	private String sickLeaveType;
	
	@Column(name = "Portfolio") private String portfolio;
	@Column(name = "PTOLimitToOverrideSick") private Double pTOLimitToOverrideSick;
	@Column(name = "DistanceFromWorksite") private String distanceFromWorksite;
	@Column(name = "PortfolioDescription") private String portfolioDescription;
	
	@Column(name = "CompanyCode") private String companyCode;	
	@Column(name = "DealPortfolio1_AE1") private String dealPortfolio1AE1;
	@Column(name = "DealPortfolio2_REC1") private String dealPortfolio2REC1;
	@Column(name = "DealPortfolio3_AE2") private String dealPortfolio3AE2;
	@Column(name = "DealPortfolio4_REC2") private String dealPortfolio4REC2;
	@Column(name = "BullhornBatchDataProcessedId") private Integer bullhornBatchDataProcessedId;
	@Column(name = "CoSellStatus") private String coSellStatus;
	@Column(name = "DataSource") private String dataSource;
	@Column(name = "BullhornBatchCode") private String bullhornBatchCode;
	@Column(name = "BullhornBatchAnalyzerStagingId") private Integer bullhornBatchAnalyzerStagingId;
	@Column(name = "PlacementType") private String placementType;
	@Column(name = "BullhornPlacementId") private Integer bullhornPlacementId;
	@Column(name = "DealActivityAmount") private String dealActivityAmount;
	
	/*
	 * Added as part of Tax and Sick Leave Project
	 */
	@Column(name = "BullhornBatchInfoId") private Integer bullhornBatchInfoId;
	@Column(name = "IsModificationRequired") private String isModificationRequired;
	@Column(name = "BullhornTerminationDataProcessedId") private Long bullhornTerminationDataProcessedId;
	@Column(name = "BullhornTerminationDataStagingId") private Long bullhornTerminationDataStagingId;
	@Column(name = "TerminationBullhornBatchInfoId") private Integer terminationBullhornBatchInfoId;
	@Column(name = "OverrideTermination") private String overrideTermination;
	@Column(name = "WorkFromSource") private String workFromSource;
	@Column(name = "MajorityWorkPerformed") private String majorityWorkPerformed;
	@Column(name = "SickLeaveSource") private String sickLeaveSource;
	

	public Analyser()
	{
	}
	
	
	
	public Analyser(Analyser analyser)
	{
		this.parentId = analyser.getParentId();
		this.newParentId = analyser.getNewParentId();
		this.analyserId = analyser.getAnalyserId();
		this.lastName = analyser.getLastName();
		this.firstName = analyser.getFirstName();
		this.dob = analyser.getDob();
		this.ssn = analyser.getSsn();
		this.startDate = analyser.getStartDate();
		this.endDate = analyser.getEndDate();
		this.effectiveDate = analyser.getEffectiveDate();
		this.commEffectiveDate = analyser.getCommEffectiveDate();
		this.terminateDate = analyser.getTerminateDate();
		this.reason = analyser.getReason();
		this.recordStatus = analyser.getRecordStatus();
		this.isRehired = analyser.getIsRehired();
		this.grossProfitPercentage = analyser.getGrossProfitPercentage();
		this.grossProfit = analyser.getGrossProfit();
		this.commissionableProfit = analyser.getCommissionableProfit();
		this.rejectionStatus = analyser.getRejectionStatus();
		this.rejectionReason = analyser.getRejectionReason();
		this.rejectedBy = analyser.getRejectedBy();
		this.rejectionDate = analyser.getRejectionDate();
		this.geoOffice = analyser.getGeoOffice();
		this.portfolio=analyser.getPortfolio();
		this.portfolioDescription=analyser.getPortfolioDescription();
		this.companyCode=analyser.getCompanyCode();
	}



	public Long getAnalyserId()
	{
		return this.analyserId;
	}
	
	public void setAnalyserId(Long analyserId)
	{
		this.analyserId = analyserId;
	}
	
	public String getAddress1()
	{
		return this.address1;
	}
	
	public void setAddress1(String address1)
	{
		this.address1 = address1;
	}
	
	public String getAddress2()
	{
		return this.address2;
	}
	
	public void setAddress2(String address2)
	{
		this.address2 = address2;
	}
	
	public String getAnalyzerCategory1()
	{
		return this.analyzerCategory1;
	}
	
	public void setAnalyzerCategory1(String analyzerCategory1)
	{
		this.analyzerCategory1 = analyzerCategory1;
	}
	
	public String getAnalyzerCategory2()
	{
		return this.analyzerCategory2;
	}
	
	public void setAnalyzerCategory2(String analyzerCategory2)
	{
		this.analyzerCategory2 = analyzerCategory2;
	}
	
	public String getAnnualPay()
	{
		return this.annualPay;
	}
	
	public void setAnnualPay(String annualPay)
	{
		this.annualPay = annualPay;
	}
	
	public Timestamp getApprovalDate()
	{
		return this.approvalDate;
	}
	
	public void setApprovalDate(Timestamp approvalDate)
	{
		this.approvalDate = approvalDate;
	}
	
	public String getBillRate()
	{
		return this.billRate;
	}
	
	public void setBillRate(String billRate)
	{
		this.billRate = billRate;
	}
	
	public BigDecimal getBonusAmount()
	{
		return this.bonusAmount;
	}
	
	public void setBonusAmount(BigDecimal bonusAmount)
	{
		this.bonusAmount = bonusAmount;
	}
	
	public String getBonusPercentage()
	{
		return this.bonusPercentage;
	}
	
	public void setBonusPercentage(String bonusPercentage)
	{
		this.bonusPercentage = bonusPercentage;
	}
	
	public String getBranch()
	{
		return this.branch;
	}
	
	public void setBranch(String branch)
	{
		this.branch = branch;
	}
	
	public String getBranchInter()
	{
		return this.branchInter;
	}
	
	public void setBranchInter(String branchInter)
	{
		this.branchInter = branchInter;
	}
	
	public String getCheckedReferences()
	{
		return this.checkedReferences;
	}
	
	public void setCheckedReferences(String checkedReferences)
	{
		this.checkedReferences = checkedReferences;
	}
	
	public String getChkReferralFee()
	{
		return this.chkReferralFee;
	}
	
	public void setChkReferralFee(String chkReferralFee)
	{
		this.chkReferralFee = chkReferralFee;
	}
	
	public String getCity()
	{
		return this.city;
	}
	
	public void setCity(String city)
	{
		this.city = city;
	}
	
	public Integer getClientAddressId()
	{
		return this.clientAddressId;
	}
	
	public void setClientAddressId(Integer clientAddressId)
	{
		this.clientAddressId = clientAddressId;
	}
	
	public Integer getClientId()
	{
		return this.clientId;
	}
	
	public void setClientId(Integer clientId)
	{
		this.clientId = clientId;
	}
	
	public String getCom()
	{
		return this.com;
	}
	
	public void setCom(String com)
	{
		this.com = com;
	}
	
	public String getComm()
	{
		return this.comm;
	}
	
	public void setComm(String comm)
	{
		this.comm = comm;
	}
	
	public String getComments()
	{
		return this.comments;
	}
	
	public void setComments(String comments)
	{
		this.comments = comments;
	}
	
	public String getCommentsInter()
	{
		return this.commentsInter;
	}
	
	public void setCommentsInter(String commentsInter)
	{
		this.commentsInter = commentsInter;
	}
	
	public BigDecimal getCommision1()
	{
		return this.commision1;
	}
	
	public void setCommision1(BigDecimal commision1)
	{
		this.commision1 = commision1;
	}
	
	public String getCommision1Inter()
	{
		return this.commision1Inter;
	}
	
	public void setCommision1Inter(String commision1Inter)
	{
		this.commision1Inter = commision1Inter;
	}
	
	public BigDecimal getCommision2()
	{
		return this.commision2;
	}
	
	public void setCommision2(BigDecimal commision2)
	{
		this.commision2 = commision2;
	}
	
	public String getCommision2Inter()
	{
		return this.commision2Inter;
	}
	
	public void setCommision2Inter(String commision2Inter)
	{
		this.commision2Inter = commision2Inter;
	}
	
	public BigDecimal getCommision3()
	{
		return this.commision3;
	}
	
	public void setCommision3(BigDecimal commision3)
	{
		this.commision3 = commision3;
	}
	
	public String getCommision3Inter()
	{
		return this.commision3Inter;
	}
	
	public void setCommision3Inter(String commision3Inter)
	{
		this.commision3Inter = commision3Inter;
	}
	
	public BigDecimal getCommision4()
	{
		return this.commision4;
	}
	
	public void setCommision4(BigDecimal commision4)
	{
		this.commision4 = commision4;
	}
	
	public String getCommision4Inter()
	{
		return this.commision4Inter;
	}
	
	public void setCommision4Inter(String commision4Inter)
	{
		this.commision4Inter = commision4Inter;
	}
	
	public BigDecimal getCommission()
	{
		return this.commission;
	}
	
	public void setCommission(BigDecimal commission)
	{
		this.commission = commission;
	}
	
	public String getCommissionModel1()
	{
		return this.commissionModel1;
	}
	
	public void setCommissionModel1(String commissionModel1)
	{
		this.commissionModel1 = commissionModel1;
	}
	
	public String getCommissionModel2()
	{
		return this.commissionModel2;
	}
	
	public void setCommissionModel2(String commissionModel2)
	{
		this.commissionModel2 = commissionModel2;
	}
	
	public String getCommissionModel3()
	{
		return this.commissionModel3;
	}
	
	public void setCommissionModel3(String commissionModel3)
	{
		this.commissionModel3 = commissionModel3;
	}
	
	public String getCommissionModel4()
	{
		return this.commissionModel4;
	}
	
	public void setCommissionModel4(String commissionModel4)
	{
		this.commissionModel4 = commissionModel4;
	}
	
	public String getConsultantJobBoard()
	{
		return this.consultantJobBoard;
	}
	
	public void setConsultantJobBoard(String consultantJobBoard)
	{
		this.consultantJobBoard = consultantJobBoard;
	}
	
	public String getContractVehicle()
	{
		return this.contractVehicle;
	}
	
	public void setContractVehicle(String contractVehicle)
	{
		this.contractVehicle = contractVehicle;
	}
	
	public String getCountry()
	{
		return this.country;
	}
	
	public void setCountry(String country)
	{
		this.country = country;
	}
	
	public String getCurrency()
	{
		return this.currency;
	}
	
	public void setCurrency(String currency)
	{
		this.currency = currency;
	}
	
	public String getCustom1()
	{
		return this.custom1;
	}
	
	public void setCustom1(String custom1)
	{
		this.custom1 = custom1;
	}
	
	public String getCustom2()
	{
		return this.custom2;
	}
	
	public void setCustom2(String custom2)
	{
		this.custom2 = custom2;
	}
	
	public String getDealType()
	{
		return this.dealType;
	}
	
	public void setDealType(String dealType)
	{
		this.dealType = dealType;
	}
	
	public String getDentalInsurance()
	{
		return this.dentalInsurance;
	}
	
	public void setDentalInsurance(String dentalInsurance)
	{
		this.dentalInsurance = dentalInsurance;
	}
	
	public BigDecimal getDentalInsuranceAmount()
	{
		return this.dentalInsuranceAmount;
	}
	
	public void setDentalInsuranceAmount(BigDecimal dentalInsuranceAmount)
	{
		this.dentalInsuranceAmount = dentalInsuranceAmount;
	}
	
	public BigDecimal getDiscount()
	{
		return this.discount;
	}
	
	public void setDiscount(BigDecimal discount)
	{
		this.discount = discount;
	}
	
	public String getDiscountRate()
	{
		return this.discountRate;
	}
	
	public void setDiscountRate(String discountRate)
	{
		this.discountRate = discountRate;
	}
	
	public String getDob()
	{
		return this.dob;
	}
	
	public void setDob(String dob)
	{
		this.dob = dob;
	}
	
	public String getDoubleTime()
	{
		return this.doubleTime;
	}
	
	public void setDoubleTime(String doubleTime)
	{
		this.doubleTime = doubleTime;
	}
	
	public String getDoubleTimeBill()
	{
		return this.doubleTimeBill;
	}
	
	public void setDoubleTimeBill(String doubleTimeBill)
	{
		this.doubleTimeBill = doubleTimeBill;
	}
	
	public String getEducation()
	{
		return this.education;
	}
	
	public void setEducation(String education)
	{
		this.education = education;
	}
	
	public String getEffectiveDate()
	{
		return this.effectiveDate;
	}
	
	public void setEffectiveDate(String effectiveDate)
	{
		this.effectiveDate = effectiveDate;
	}
	
	public String getEligibleForReHire()
	{
		return this.eligibleForReHire;
	}
	
	public void setEligibleForReHire(String eligibleForReHire)
	{
		this.eligibleForReHire = eligibleForReHire;
	}
	
	public String getEmail()
	{
		return this.email;
	}
	
	public void setEmail(String email)
	{
		this.email = email;
	}
	
	public String getEmployeeVeteran()
	{
		return this.employeeVeteran;
	}
	
	public void setEmployeeVeteran(String employeeVeteran)
	{
		this.employeeVeteran = employeeVeteran;
	}
	
	public String getEndDate()
	{
		return this.endDate;
	}
	
	public void setEndDate(String endDate)
	{
		this.endDate = endDate;
	}
	
	public String getEnteredBy()
	{
		return this.enteredBy;
	}
	
	public void setEnteredBy(String enteredBy)
	{
		this.enteredBy = enteredBy;
	}
	
	public Timestamp getEntryDate()
	{
		return this.entryDate;
	}
	
	public void setEntryDate(Timestamp entryDate)
	{
		this.entryDate = entryDate;
	}
	
	public BigDecimal getEquipmentCost()
	{
		return this.equipmentCost;
	}
	
	public void setEquipmentCost(BigDecimal equipmentCost)
	{
		this.equipmentCost = equipmentCost;
	}
	
	public String getExecOrphan()
	{
		return this.execOrphan;
	}
	
	public void setExecOrphan(String execOrphan)
	{
		this.execOrphan = execOrphan;
	}
	
	public String getExecOrphanInter()
	{
		return this.execOrphanInter;
	}
	
	public void setExecOrphanInter(String execOrphanInter)
	{
		this.execOrphanInter = execOrphanInter;
	}
	
	public String getFax()
	{
		return this.fax;
	}
	
	public void setFax(String fax)
	{
		this.fax = fax;
	}
	
	public String getFLSAReferance()
	{
		return this.FLSAReferance;
	}
	
	public void setFLSAReferance(String FLSAReferance)
	{
		this.FLSAReferance = FLSAReferance;
	}
	
	public String getFLSAStatus()
	{
		return this.FLSAStatus;
	}
	
	public void setFLSAStatus(String FLSAStatus)
	{
		this.FLSAStatus = FLSAStatus;
	}
	
	public BigDecimal getGa()
	{
		return this.ga;
	}
	
	public void setGa(BigDecimal ga)
	{
		this.ga = ga;
	}
	
	public String getGender()
	{
		return this.gender;
	}
	
	public void setGender(String gender)
	{
		this.gender = gender;
	}
	
	public String getH1()
	{
		return this.h1;
	}
	
	public void setH1(String h1)
	{
		this.h1 = h1;
	}
	
	public String getHealth()
	{
		return this.health;
	}
	
	public void setHealth(String health)
	{
		this.health = health;
	}
	
	public String getHealthBenefitRates()
	{
		return this.healthBenefitRates;
	}
	
	public void setHealthBenefitRates(String healthBenefitRates)
	{
		this.healthBenefitRates = healthBenefitRates;
	}
	
	public BigDecimal getImmigrationCost()
	{
		return this.immigrationCost;
	}
	
	public void setImmigrationCost(BigDecimal immigrationCost)
	{
		this.immigrationCost = immigrationCost;
	}
	
	public String getInitial()
	{
		return this.initial;
	}
	
	public void setInitial(String initial)
	{
		this.initial = initial;
	}
	
	public String getIsBonusEligible()
	{
		return this.isBonusEligible;
	}
	
	public void setIsBonusEligible(String isBonusEligible)
	{
		this.isBonusEligible = isBonusEligible;
	}
	
	public String getIsHire()
	{
		return this.isHire;
	}
	
	public void setIsHire(String isHire)
	{
		this.isHire = isHire;
	}
	
	public String getIsProcessed()
	{
		return this.isProcessed;
	}
	
	public void setIsProcessed(String isProcessed)
	{
		this.isProcessed = isProcessed;
	}
	
	public String getIsRehired()
	{
		return this.isRehired;
	}
	
	public void setIsRehired(String isRehired)
	{
		this.isRehired = isRehired;
	}
	
	public String getJobBillRate()
	{
		return this.jobBillRate;
	}
	
	public void setJobBillRate(String jobBillRate)
	{
		this.jobBillRate = jobBillRate;
	}
	
	public String getJobCategory()
	{
		return this.jobCategory;
	}
	
	public void setJobCategory(String jobCategory)
	{
		this.jobCategory = jobCategory;
	}
	
	public String getJobPayRate()
	{
		return this.jobPayRate;
	}
	
	public void setJobPayRate(String jobPayRate)
	{
		this.jobPayRate = jobPayRate;
	}
	
	public String getJrsr()
	{
		return this.jrsr;
	}
	
	public void setJrsr(String jrsr)
	{
		this.jrsr = jrsr;
	}
	
	public String getK401()
	{
		return this.k401;
	}
	
	public void setK401(String k401)
	{
		this.k401 = k401;
	}
	
	public String getLeave()
	{
		return this.leave;
	}
	
	public void setLeave(String leave)
	{
		this.leave = leave;
	}
	
	public String getLiaisonName()
	{
		return this.liaisonName;
	}
	
	public void setLiaisonName(String liaisonName)
	{
		this.liaisonName = liaisonName;
	}
	
	public String getLtdBenefit()
	{
		return this.ltdBenefit;
	}
	
	public void setLtdBenefit(String ltdBenefit)
	{
		this.ltdBenefit = ltdBenefit;
	}
	
	public String getMrmrs()
	{
		return this.mrmrs;
	}
	
	public void setMrmrs(String mrmrs)
	{
		this.mrmrs = mrmrs;
	}
	
	public Integer getNewParentId()
	{
		return this.newParentId;
	}
	
	public void setNewParentId(Integer newParentId)
	{
		this.newParentId = newParentId;
	}
	
	public BigDecimal getNonBillableCost()
	{
		return this.nonBillableCost;
	}
	
	public void setNonBillableCost(BigDecimal nonBillableCost)
	{
		this.nonBillableCost = nonBillableCost;
	}
	
	public BigDecimal getOldDtPayRate()
	{
		return this.oldDtPayRate;
	}
	
	public void setOldDtPayRate(BigDecimal oldDtPayRate)
	{
		this.oldDtPayRate = oldDtPayRate;
	}
	
	public BigDecimal getOldOtPayRate()
	{
		return this.oldOtPayRate;
	}
	
	public void setOldOtPayRate(BigDecimal oldOtPayRate)
	{
		this.oldOtPayRate = oldOtPayRate;
	}
	
	public BigDecimal getOldPayRate()
	{
		return this.oldPayRate;
	}
	
	public void setOldPayRate(BigDecimal oldPayRate)
	{
		this.oldPayRate = oldPayRate;
	}
	
	public BigDecimal getOldRateChangedAnalyserId()
	{
		return this.oldRateChangedAnalyserId;
	}
	
	public void setOldRateChangedAnalyserId(BigDecimal oldRateChangedAnalyserId)
	{
		this.oldRateChangedAnalyserId = oldRateChangedAnalyserId;
	}
	
	public String getOther1Orphan()
	{
		return this.other1Orphan;
	}
	
	public void setOther1Orphan(String other1Orphan)
	{
		this.other1Orphan = other1Orphan;
	}
	
	public String getOther1OrphanInter()
	{
		return this.other1OrphanInter;
	}
	
	public void setOther1OrphanInter(String other1OrphanInter)
	{
		this.other1OrphanInter = other1OrphanInter;
	}
	
	public String getOther2Orphan()
	{
		return this.other2Orphan;
	}
	
	public void setOther2Orphan(String other2Orphan)
	{
		this.other2Orphan = other2Orphan;
	}
	
	public String getOther2OrphanInter()
	{
		return this.other2OrphanInter;
	}
	
	public void setOther2OrphanInter(String other2OrphanInter)
	{
		this.other2OrphanInter = other2OrphanInter;
	}
	
	public String getParentId()
	{
		return this.parentId;
	}
	
	public void setParentId(String parentId)
	{
		this.parentId = parentId;
	}
	
	public String getPayPeriodType()
	{
		return this.payPeriodType;
	}
	
	public void setPayPeriodType(String payPeriodType)
	{
		this.payPeriodType = payPeriodType;
	}
	
	public String getPayRate()
	{
		return this.payRate;
	}
	
	public void setPayRate(String payRate)
	{
		this.payRate = payRate;
	}
	
	public String getPayRateChangedBy()
	{
		return this.payRateChangedBy;
	}
	
	public void setPayRateChangedBy(String payRateChangedBy)
	{
		this.payRateChangedBy = payRateChangedBy;
	}
	
	public Timestamp getPayRateChangeEffectiveDate()
	{
		return this.payRateChangeEffectiveDate;
	}
	
	public void setPayRateChangeEffectiveDate(Timestamp payRateChangeEffectiveDate)
	{
		this.payRateChangeEffectiveDate = payRateChangeEffectiveDate;
	}
	
	public Timestamp getPayRateChangeEntryDate()
	{
		return this.payRateChangeEntryDate;
	}
	
	public void setPayRateChangeEntryDate(Timestamp payRateChangeEntryDate)
	{
		this.payRateChangeEntryDate = payRateChangeEntryDate;
	}
	
	public String getPayRateChangeFlag()
	{
		return this.payRateChangeFlag;
	}
	
	public void setPayRateChangeFlag(String payRateChangeFlag)
	{
		this.payRateChangeFlag = payRateChangeFlag;
	}
	
	public String getPayRateChangeProcessedBy()
	{
		return this.payRateChangeProcessedBy;
	}
	
	public void setPayRateChangeProcessedBy(String payRateChangeProcessedBy)
	{
		this.payRateChangeProcessedBy = payRateChangeProcessedBy;
	}
	
	public Timestamp getPayRateChangeProcessedDate()
	{
		return this.payRateChangeProcessedDate;
	}
	
	public void setPayRateChangeProcessedDate(Timestamp payRateChangeProcessedDate)
	{
		this.payRateChangeProcessedDate = payRateChangeProcessedDate;
	}
	
	public String getPayRateChangeProcessedFlag()
	{
		return this.payRateChangeProcessedFlag;
	}
	
	public void setPayRateChangeProcessedFlag(String payRateChangeProcessedFlag)
	{
		this.payRateChangeProcessedFlag = payRateChangeProcessedFlag;
	}
	
	public String getPerDiem()
	{
		return this.perDiem;
	}
	
	public void setPerDiem(String perDiem)
	{
		this.perDiem = perDiem;
	}
	
	public String getPermanentPlacement()
	{
		return this.permanentPlacement;
	}
	
	public void setPermanentPlacement(String permanentPlacement)
	{
		this.permanentPlacement = permanentPlacement;
	}
	
	public BigDecimal getPermGAAmount()
	{
		return this.permGAAmount;
	}
	
	public void setPermGAAmount(BigDecimal permGAAmount)
	{
		this.permGAAmount = permGAAmount;
	}
	
	public BigDecimal getPlacementAmount()
	{
		return this.placementAmount;
	}
	
	public void setPlacementAmount(BigDecimal placementAmount)
	{
		this.placementAmount = placementAmount;
	}
	
	public String getPlacementDate()
	{
		return this.placementDate;
	}
	
	public void setPlacementDate(String placementDate)
	{
		this.placementDate = placementDate;
	}
	
	public String getPlacementPercentage()
	{
		return this.placementPercentage;
	}
	
	public void setPlacementPercentage(String placementPercentage)
	{
		this.placementPercentage = placementPercentage;
	}
	
	public String getPlacementSalaryAmount()
	{
		return this.placementSalaryAmount;
	}
	
	public void setPlacementSalaryAmount(String placementSalaryAmount)
	{
		this.placementSalaryAmount = placementSalaryAmount;
	}
	
	public String getPoNumber()
	{
		return this.poNumber;
	}
	
	public void setPoNumber(String poNumber)
	{
		this.poNumber = poNumber;
	}
	
	public Timestamp getProcessDate()
	{
		return this.processDate;
	}
	
	public void setProcessDate(Timestamp processDate)
	{
		this.processDate = processDate;
	}
	
	public String getProduct()
	{
		return this.product;
	}
	
	public void setProduct(String product)
	{
		this.product = product;
	}
	
	public BigDecimal getProfit()
	{
		return this.profit;
	}
	
	public void setProfit(BigDecimal profit)
	{
		this.profit = profit;
	}
	
	public String getPto()
	{
		return this.pto;
	}
	
	public void setPto(String pto)
	{
		this.pto = pto;
	}
	
	public String getReason()
	{
		return this.reason;
	}
	
	public void setReason(String reason)
	{
		this.reason = reason;
	}
	
	public String getRecordStatus()
	{
		return this.recordStatus;
	}
	
	public void setRecordStatus(String recordStatus)
	{
		this.recordStatus = recordStatus;
	}
	
	public String getRecruiterOrphan()
	{
		return this.recruiterOrphan;
	}
	
	public void setRecruiterOrphan(String recruiterOrphan)
	{
		this.recruiterOrphan = recruiterOrphan;
	}
	
	public String getRecruiterOrphanInter()
	{
		return this.recruiterOrphanInter;
	}
	
	public void setRecruiterOrphanInter(String recruiterOrphanInter)
	{
		this.recruiterOrphanInter = recruiterOrphanInter;
	}
	
	public String getRecruitingClassification()
	{
		return this.recruitingClassification;
	}
	
	public void setRecruitingClassification(String recruitingClassification)
	{
		this.recruitingClassification = recruitingClassification;
	}
	
	public String getReferralFeeAmount()
	{
		return this.referralFeeAmount;
	}
	
	public void setReferralFeeAmount(String referralFeeAmount)
	{
		this.referralFeeAmount = referralFeeAmount;
	}
	
	public Timestamp getRehireDate()
	{
		return this.rehireDate;
	}
	
	public void setRehireDate(Timestamp rehireDate)
	{
		this.rehireDate = rehireDate;
	}
	
	public String getRehiredBy()
	{
		return this.rehiredBy;
	}
	
	public void setRehiredBy(String rehiredBy)
	{
		this.rehiredBy = rehiredBy;
	}
	
	public Integer getSiteId()
	{
		return this.siteId;
	}
	
	public void setSiteId(Integer siteId)
	{
		this.siteId = siteId;
	}
	
	public String getSkillCategory()
	{
		return this.skillCategory;
	}
	
	public void setSkillCategory(String skillCategory)
	{
		this.skillCategory = skillCategory;
	}
	
	public Integer getSplit()
	{
		return this.split;
	}
	
	public void setSplit(Integer split)
	{
		this.split = split;
	}
	
	public String getSsn()
	{
		return this.ssn;
	}
	
	public void setSsn(String ssn)
	{
		this.ssn = ssn;
	}
	
	public String getStartDate()
	{
		return this.startDate;
	}
	
	public void setStartDate(String startDate)
	{
		this.startDate = startDate;
	}
	
	public String getState()
	{
		return this.state;
	}
	
	public void setState(String state)
	{
		this.state = state;
	}
	
	public String getStdBenefit()
	{
		return this.stdBenefit;
	}
	
	public void setStdBenefit(String stdBenefit)
	{
		this.stdBenefit = stdBenefit;
	}
	
	public Integer getSubContractorId()
	{
		return this.subContractorId;
	}
	
	public void setSubContractorId(Integer subContractorId)
	{
		this.subContractorId = subContractorId;
	}
	
	public Timestamp getSubmissionDate()
	{
		return this.submissionDate;
	}
	
	public void setSubmissionDate(Timestamp submissionDate)
	{
		this.submissionDate = submissionDate;
	}
	
	public BigDecimal getTaxes()
	{
		return this.taxes;
	}
	
	public void setTaxes(BigDecimal taxes)
	{
		this.taxes = taxes;
	}
	
	public String getTelephone()
	{
		return this.telephone;
	}
	
	public void setTelephone(String telephone)
	{
		this.telephone = telephone;
	}
	
	public String getTemps()
	{
		return this.temps;
	}
	
	public void setTemps(String temps)
	{
		this.temps = temps;
	}
	
	public String getTerminateDate()
	{
		return this.terminateDate;
	}
	
	public void setTerminateDate(String terminateDate)
	{
		this.terminateDate = terminateDate;
	}
	
	public String getThw()
	{
		return this.thw;
	}
	
	public void setThw(String thw)
	{
		this.thw = thw;
	}
	
	public String getTravelRequired()
	{
		return this.travelRequired;
	}
	
	public void setTravelRequired(String travelRequired)
	{
		this.travelRequired = travelRequired;
	}
	
	public String getUnEmployedForTwoMonths()
	{
		return this.unEmployedForTwoMonths;
	}
	
	public void setUnEmployedForTwoMonths(String unEmployedForTwoMonths)
	{
		this.unEmployedForTwoMonths = unEmployedForTwoMonths;
	}
	
	public String getUserId()
	{
		return this.userId;
	}
	
	public void setUserId(String userId)
	{
		this.userId = userId;
	}
	
	public String getVertical()
	{
		return this.vertical;
	}
	
	public void setVertical(String vertical)
	{
		this.vertical = vertical;
	}
	
	public String getVerticaltimesheettype()
	{
		return this.verticaltimesheettype;
	}
	
	public void setVerticaltimesheettype(String verticaltimesheettype)
	{
		this.verticaltimesheettype = verticaltimesheettype;
	}
	
	public String getWorkEmail()
	{
		return this.workEmail;
	}
	
	public void setWorkEmail(String workEmail)
	{
		this.workEmail = workEmail;
	}
	
	public String getWorkPhone()
	{
		return this.workPhone;
	}
	
	public void setWorkPhone(String workPhone)
	{
		this.workPhone = workPhone;
	}
	
	public String getWorksiteState()
	{
		return this.worksiteState;
	}
	
	public void setWorksiteState(String worksiteState)
	{
		this.worksiteState = worksiteState;
	}
	
	public String getZipCode()
	{
		return this.zipCode;
	}
	
	public void setZipCode(String zipCode)
	{
		this.zipCode = zipCode;
	}
	
	/**
	 * @return the appBackup
	 */
	public Integer getAppBackup()
	{
		return appBackup;
	}
	
	/**
	 * @param appBackup
	 *            the appBackup to set
	 */
	public void setAppBackup(Integer appBackup)
	{
		this.appBackup = appBackup;
	}
	
	/**
	 * @return the approvedByAdmin
	 */
	public String getApprovedByAdmin()
	{
		return approvedByAdmin;
	}
	
	/**
	 * @param approvedByAdmin
	 *            the approvedByAdmin to set
	 */
	public void setApprovedByAdmin(String approvedByAdmin)
	{
		this.approvedByAdmin = approvedByAdmin;
	}
	
	/**
	 * @return the approvedByManager
	 */
	public String getApprovedByManager()
	{
		return approvedByManager;
	}
	
	/**
	 * @param approvedByManager
	 *            the approvedByManager to set
	 */
	public void setApprovedByManager(String approvedByManager)
	{
		this.approvedByManager = approvedByManager;
	}
	
	/**
	 * @return the commEffectiveDate
	 */
	public String getCommEffectiveDate()
	{
		return commEffectiveDate;
	}
	
	/**
	 * @param commEffectiveDate
	 *            the commEffectiveDate to set
	 */
	public void setCommEffectiveDate(String commEffectiveDate)
	{
		this.commEffectiveDate = commEffectiveDate;
	}
	
	/**
	 * @return the commisionName1
	 */
	public String getCommisionName1()
	{
		return commisionName1;
	}
	
	/**
	 * @param commisionName1
	 *            the commisionName1 to set
	 */
	public void setCommisionName1(String commisionName1)
	{
		this.commisionName1 = commisionName1;
	}
	
	/**
	 * @return the commisionName1Inter
	 */
	public String getCommisionName1Inter()
	{
		return commisionName1Inter;
	}
	
	/**
	 * @param commisionName1Inter
	 *            the commisionName1Inter to set
	 */
	public void setCommisionName1Inter(String commisionName1Inter)
	{
		this.commisionName1Inter = commisionName1Inter;
	}
	
	/**
	 * @return the commisionName2
	 */
	public String getCommisionName2()
	{
		return commisionName2;
	}
	
	/**
	 * @param commisionName2
	 *            the commisionName2 to set
	 */
	public void setCommisionName2(String commisionName2)
	{
		this.commisionName2 = commisionName2;
	}
	
	/**
	 * @return the commisionName2Inter
	 */
	public String getCommisionName2Inter()
	{
		return commisionName2Inter;
	}
	
	/**
	 * @param commisionName2Inter
	 *            the commisionName2Inter to set
	 */
	public void setCommisionName2Inter(String commisionName2Inter)
	{
		this.commisionName2Inter = commisionName2Inter;
	}
	
	/**
	 * @return the commisionName3
	 */
	public String getCommisionName3()
	{
		return commisionName3;
	}
	
	/**
	 * @param commisionName3
	 *            the commisionName3 to set
	 */
	public void setCommisionName3(String commisionName3)
	{
		this.commisionName3 = commisionName3;
	}
	
	/**
	 * @return the commisionName3Inter
	 */
	public String getCommisionName3Inter()
	{
		return commisionName3Inter;
	}
	
	/**
	 * @param commisionName3Inter
	 *            the commisionName3Inter to set
	 */
	public void setCommisionName3Inter(String commisionName3Inter)
	{
		this.commisionName3Inter = commisionName3Inter;
	}
	
	/**
	 * @return the commisionName4
	 */
	public String getCommisionName4()
	{
		return commisionName4;
	}
	
	/**
	 * @param commisionName4
	 *            the commisionName4 to set
	 */
	public void setCommisionName4(String commisionName4)
	{
		this.commisionName4 = commisionName4;
	}
	
	/**
	 * @return the commisionName4Inter
	 */
	public String getCommisionName4Inter()
	{
		return commisionName4Inter;
	}
	
	/**
	 * @param commisionName4Inter
	 *            the commisionName4Inter to set
	 */
	public void setCommisionName4Inter(String commisionName4Inter)
	{
		this.commisionName4Inter = commisionName4Inter;
	}
	
	/**
	 * @return the commisionPercent1
	 */
	public String getCommisionPercent1()
	{
		return commisionPercent1;
	}
	
	/**
	 * @param commisionPercent1
	 *            the commisionPercent1 to set
	 */
	public void setCommisionPercent1(String commisionPercent1)
	{
		this.commisionPercent1 = commisionPercent1;
	}
	
	/**
	 * @return the commisionPercent1Inter
	 */
	public String getCommisionPercent1Inter()
	{
		return commisionPercent1Inter;
	}
	
	/**
	 * @param commisionPercent1Inter
	 *            the commisionPercent1Inter to set
	 */
	public void setCommisionPercent1Inter(String commisionPercent1Inter)
	{
		this.commisionPercent1Inter = commisionPercent1Inter;
	}
	
	/**
	 * @return the commisionPercent2
	 */
	public String getCommisionPercent2()
	{
		return commisionPercent2;
	}
	
	/**
	 * @param commisionPercent2
	 *            the commisionPercent2 to set
	 */
	public void setCommisionPercent2(String commisionPercent2)
	{
		this.commisionPercent2 = commisionPercent2;
	}
	
	/**
	 * @return the commisionPercent2Inter
	 */
	public String getCommisionPercent2Inter()
	{
		return commisionPercent2Inter;
	}
	
	/**
	 * @param commisionPercent2Inter
	 *            the commisionPercent2Inter to set
	 */
	public void setCommisionPercent2Inter(String commisionPercent2Inter)
	{
		this.commisionPercent2Inter = commisionPercent2Inter;
	}
	
	/**
	 * @return the commisionPercent3
	 */
	public String getCommisionPercent3()
	{
		return commisionPercent3;
	}
	
	/**
	 * @param commisionPercent3
	 *            the commisionPercent3 to set
	 */
	public void setCommisionPercent3(String commisionPercent3)
	{
		this.commisionPercent3 = commisionPercent3;
	}
	
	/**
	 * @return the commisionPercent3Inter
	 */
	public String getCommisionPercent3Inter()
	{
		return commisionPercent3Inter;
	}
	
	/**
	 * @param commisionPercent3Inter
	 *            the commisionPercent3Inter to set
	 */
	public void setCommisionPercent3Inter(String commisionPercent3Inter)
	{
		this.commisionPercent3Inter = commisionPercent3Inter;
	}
	
	/**
	 * @return the commisionPercent4
	 */
	public String getCommisionPercent4()
	{
		return commisionPercent4;
	}
	
	/**
	 * @param commisionPercent4
	 *            the commisionPercent4 to set
	 */
	public void setCommisionPercent4(String commisionPercent4)
	{
		this.commisionPercent4 = commisionPercent4;
	}
	
	/**
	 * @return the commisionPercent4Inter
	 */
	public String getCommisionPercent4Inter()
	{
		return commisionPercent4Inter;
	}
	
	/**
	 * @param commisionPercent4Inter
	 *            the commisionPercent4Inter to set
	 */
	public void setCommisionPercent4Inter(String commisionPercent4Inter)
	{
		this.commisionPercent4Inter = commisionPercent4Inter;
	}
	
	/**
	 * @return the commisionPercent5
	 */
	public String getCommisionPercent5()
	{
		return commisionPercent5;
	}
	
	/**
	 * @param commisionPercent5
	 *            the commisionPercent5 to set
	 */
	public void setCommisionPercent5(String commisionPercent5)
	{
		this.commisionPercent5 = commisionPercent5;
	}
	
	/**
	 * @return the employeeClass
	 */
	public String getEmployeeClass()
	{
		return employeeClass;
	}
	
	/**
	 * @param employeeClass
	 *            the employeeClass to set
	 */
	public void setEmployeeClass(String employeeClass)
	{
		this.employeeClass = employeeClass;
	}
	
	/**
	 * @return the employeeType
	 */
	public String getEmployeeType()
	{
		return employeeType;
	}
	
	/**
	 * @param employeeType
	 *            the employeeType to set
	 */
	public void setEmployeeType(String employeeType)
	{
		this.employeeType = employeeType;
	}
	
	/**
	 * @return the firstName
	 */
	public String getFirstName()
	{
		return firstName;
	}
	
	/**
	 * @param firstName
	 *            the firstName to set
	 */
	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}
	
	/**
	 * @return the lastName
	 */
	public String getLastName()
	{
		return lastName;
	}
	
	/**
	 * @param lastName
	 *            the lastName to set
	 */
	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}
	
	/**
	 * @return the internalAccounting
	 */
	public String getInternalAccounting()
	{
		return internalAccounting;
	}
	
	/**
	 * @param internalAccounting
	 *            the internalAccounting to set
	 */
	public void setInternalAccounting(String internalAccounting)
	{
		this.internalAccounting = internalAccounting;
	}
	
	/**
	 * @return the jobTitle
	 */
	public String getJobTitle()
	{
		return jobTitle;
	}
	
	/**
	 * @param jobTitle
	 *            the jobTitle to set
	 */
	public void setJobTitle(String jobTitle)
	{
		this.jobTitle = jobTitle;
	}
	
	/**
	 * @return the mobilePager
	 */
	public String getMobilePager()
	{
		return mobilePager;
	}
	
	/**
	 * @param mobilePager
	 *            the mobilePager to set
	 */
	public void setMobilePager(String mobilePager)
	{
		this.mobilePager = mobilePager;
	}
	
	/**
	 * @return the oneTimeBill
	 */
	public String getOneTimeBill()
	{
		return oneTimeBill;
	}
	
	/**
	 * @param oneTimeBill
	 *            the oneTimeBill to set
	 */
	public void setOneTimeBill(String oneTimeBill)
	{
		this.oneTimeBill = oneTimeBill;
	}
	
	/**
	 * @return the oneTimePay
	 */
	public String getOneTimePay()
	{
		return oneTimePay;
	}
	
	/**
	 * @param oneTimePay
	 *            the oneTimePay to set
	 */
	public void setOneTimePay(String oneTimePay)
	{
		this.oneTimePay = oneTimePay;
	}
	
	/**
	 * @return the orgId
	 */
	public Integer getOrgId()
	{
		return orgId;
	}
	
	/**
	 * @param orgId
	 *            the orgId to set
	 */
	public void setOrgId(Integer orgId)
	{
		this.orgId = orgId;
	}
	
	/**
	 * @return the otherDollar
	 */
	public String getOtherDollar()
	{
		return otherDollar;
	}
	
	/**
	 * @param otherDollar
	 *            the otherDollar to set
	 */
	public void setOtherDollar(String otherDollar)
	{
		this.otherDollar = otherDollar;
	}
	
	/**
	 * @return the otherAmountHour
	 */
	public String getOtherAmountHour()
	{
		return otherAmountHour;
	}
	
	/**
	 * @param otherAmountHour
	 *            the otherAmountHour to set
	 */
	public void setOtherAmountHour(String otherAmountHour)
	{
		this.otherAmountHour = otherAmountHour;
	}
	
	/**
	 * @return the percentage401k
	 */
	public String getPercentage401k()
	{
		return percentage401k;
	}
	
	/**
	 * @param percentage401k
	 *            the percentage401k to set
	 */
	public void setPercentage401k(String percentage401k)
	{
		this.percentage401k = percentage401k;
	}
	
	/**
	 * @return the spreadW
	 */
	public BigDecimal getSpreadW()
	{
		return spreadW;
	}
	
	/**
	 * @param spreadW
	 *            the spreadW to set
	 */
	public void setSpreadW(BigDecimal spreadW)
	{
		this.spreadW = spreadW;
	}
	
	/**
	 * @return the splitCommissionPercentage1
	 */
	public String getSplitCommissionPercentage1()
	{
		return splitCommissionPercentage1;
	}
	
	/**
	 * @param splitCommissionPercentage1
	 *            the splitCommissionPercentage1 to set
	 */
	public void setSplitCommissionPercentage1(String splitCommissionPercentage1)
	{
		this.splitCommissionPercentage1 = splitCommissionPercentage1;
	}
	
	/**
	 * @return the splitCommissionPercentage2
	 */
	public String getSplitCommissionPercentage2()
	{
		return splitCommissionPercentage2;
	}
	
	/**
	 * @param splitCommissionPercentage2
	 *            the splitCommissionPercentage2 to set
	 */
	public void setSplitCommissionPercentage2(String splitCommissionPercentage2)
	{
		this.splitCommissionPercentage2 = splitCommissionPercentage2;
	}
	
	/**
	 * @return the splitCommissionPercentage3
	 */
	public String getSplitCommissionPercentage3()
	{
		return splitCommissionPercentage3;
	}
	
	/**
	 * @param splitCommissionPercentage3
	 *            the splitCommissionPercentage3 to set
	 */
	public void setSplitCommissionPercentage3(String splitCommissionPercentage3)
	{
		this.splitCommissionPercentage3 = splitCommissionPercentage3;
	}
	
	/**
	 * @return the splitCommissionPercentage4
	 */
	public String getSplitCommissionPercentage4()
	{
		return splitCommissionPercentage4;
	}
	
	/**
	 * @param splitCommissionPercentage4
	 *            the splitCommissionPercentage4 to set
	 */
	public void setSplitCommissionPercentage4(String splitCommissionPercentage4)
	{
		this.splitCommissionPercentage4 = splitCommissionPercentage4;
	}
	
	/**
	 * @return the terminated
	 */
	public String getTerminated()
	{
		return terminated;
	}
	
	/**
	 * @param terminated
	 *            the terminated to set
	 */
	public void setTerminated(String terminated)
	{
		this.terminated = terminated;
	}
	
	/**
	 * @return the projectCost
	 */
	public Double getProjectCost()
	{
		return projectCost;
	}
	
	/**
	 * @param projectCost
	 *            the projectCost to set
	 */
	public void setProjectCost(Double projectCost)
	{
		this.projectCost = projectCost;
	}
	
	/**
	 * @return the sickLeavePerHourRate
	 */
	public Double getSickLeavePerHourRate()
	{
		return sickLeavePerHourRate;
	}
	
	/**
	 * @param sickLeavePerHourRate
	 *            the sickLeavePerHourRate to set
	 */
	public void setSickLeavePerHourRate(Double sickLeavePerHourRate)
	{
		this.sickLeavePerHourRate = sickLeavePerHourRate;
	}
	
	/**
	 * @return the sickLeaveCost
	 */
	public Double getSickLeaveCost()
	{
		return sickLeaveCost;
	}
	
	/**
	 * @param sickLeaveCost
	 *            the sickLeaveCost to set
	 */
	public void setSickLeaveCost(Double sickLeaveCost)
	{
		this.sickLeaveCost = sickLeaveCost;
	}
	
	/**
	 * @return the sickLeaveCap
	 */
	public Integer getSickLeaveCap()
	{
		return sickLeaveCap;
	}
	
	/**
	 * @param sickLeaveCap
	 *            the sickLeaveCap to set
	 */
	public void setSickLeaveCap(Integer sickLeaveCap)
	{
		this.sickLeaveCap = sickLeaveCap;
	}
	
	/**
	 * @return the managingDirector
	 */
	public String getManagingDirector()
	{
		return managingDirector;
	}
	
	/**
	 * @param managingDirector
	 *            the managingDirector to set
	 */
	public void setManagingDirector(String managingDirector)
	{
		this.managingDirector = managingDirector;
	}
	
	/**
	 * @return the commisionName5
	 */
	public String getCommisionName5()
	{
		return commisionName5;
	}
	
	/**
	 * @param commisionName5
	 *            the commisionName5 to set
	 */
	public void setCommisionName5(String commisionName5)
	{
		this.commisionName5 = commisionName5;
	}
	
	/**
	 * @return the commision5
	 */
	public Double getCommision5()
	{
		return commision5;
	}
	
	/**
	 * @param commision5
	 *            the commision5 to set
	 */
	public void setCommision5(Double commision5)
	{
		this.commision5 = commision5;
	}
	
	/**
	 * @return the commisionName6
	 */
	public String getCommisionName6()
	{
		return commisionName6;
	}
	
	/**
	 * @param commisionName6
	 *            the commisionName6 to set
	 */
	public void setCommisionName6(String commisionName6)
	{
		this.commisionName6 = commisionName6;
	}
	
	/**
	 * @return the commisionPercent6
	 */
	public String getCommisionPercent6()
	{
		return commisionPercent6;
	}
	
	/**
	 * @param commisionPercent6
	 *            the commisionPercent6 to set
	 */
	public void setCommisionPercent6(String commisionPercent6)
	{
		this.commisionPercent6 = commisionPercent6;
	}
	
	/**
	 * @return the commision6
	 */
	public Double getCommision6()
	{
		return commision6;
	}
	
	/**
	 * @param commision6
	 *            the commision6 to set
	 */
	public void setCommision6(Double commision6)
	{
		this.commision6 = commision6;
	}
	
	/**
	 * @return the commisionName7
	 */
	public String getCommisionName7()
	{
		return commisionName7;
	}
	
	/**
	 * @param commisionName7
	 *            the commisionName7 to set
	 */
	public void setCommisionName7(String commisionName7)
	{
		this.commisionName7 = commisionName7;
	}
	
	/**
	 * @return the commisionPercent7
	 */
	public String getCommisionPercent7()
	{
		return commisionPercent7;
	}
	
	/**
	 * @param commisionPercent7
	 *            the commisionPercent7 to set
	 */
	public void setCommisionPercent7(String commisionPercent7)
	{
		this.commisionPercent7 = commisionPercent7;
	}
	
	/**
	 * @return the commision7
	 */
	public Double getCommision7()
	{
		return commision7;
	}
	
	/**
	 * @param commision7
	 *            the commision7 to set
	 */
	public void setCommision7(Double commision7)
	{
		this.commision7 = commision7;
	}
	
	/**
	 * @return the commisionName8
	 */
	public String getCommisionName8()
	{
		return commisionName8;
	}
	
	/**
	 * @param commisionName8
	 *            the commisionName8 to set
	 */
	public void setCommisionName8(String commisionName8)
	{
		this.commisionName8 = commisionName8;
	}
	
	/**
	 * @return the commisionPercent8
	 */
	public String getCommisionPercent8()
	{
		return commisionPercent8;
	}
	
	/**
	 * @param commisionPercent8
	 *            the commisionPercent8 to set
	 */
	public void setCommisionPercent8(String commisionPercent8)
	{
		this.commisionPercent8 = commisionPercent8;
	}
	
	/**
	 * @return the commision8
	 */
	public Double getCommision8()
	{
		return commision8;
	}
	
	/**
	 * @param commision8
	 *            the commision8 to set
	 */
	public void setCommision8(Double commision8)
	{
		this.commision8 = commision8;
	}
	
	/**
	 * @return the commisionName9
	 */
	public String getCommisionName9()
	{
		return commisionName9;
	}
	
	/**
	 * @param commisionName9
	 *            the commisionName9 to set
	 */
	public void setCommisionName9(String commisionName9)
	{
		this.commisionName9 = commisionName9;
	}
	
	/**
	 * @return the commisionPercent9
	 */
	public String getCommisionPercent9()
	{
		return commisionPercent9;
	}
	
	/**
	 * @param commisionPercent9
	 *            the commisionPercent9 to set
	 */
	public void setCommisionPercent9(String commisionPercent9)
	{
		this.commisionPercent9 = commisionPercent9;
	}
	
	/**
	 * @return the commision9
	 */
	public Double getCommision9()
	{
		return commision9;
	}
	
	/**
	 * @param commision9
	 *            the commision9 to set
	 */
	public void setCommision9(Double commision9)
	{
		this.commision9 = commision9;
	}
	
	/**
	 * @return the falseTermination
	 */
	public String getFalseTermination()
	{
		return falseTermination;
	}
	
	/**
	 * @param falseTermination
	 *            the falseTermination to set
	 */
	public void setFalseTermination(String falseTermination)
	{
		this.falseTermination = falseTermination;
	}
	
	/**
	 * @return the commissionPersonGrade1
	 */
	public String getCommissionPersonGrade1()
	{
		return commissionPersonGrade1;
	}
	
	/**
	 * @param commissionPersonGrade1
	 *            the commissionPersonGrade1 to set
	 */
	public void setCommissionPersonGrade1(String commissionPersonGrade1)
	{
		this.commissionPersonGrade1 = commissionPersonGrade1;
	}
	
	/**
	 * @return the commissionPersonGrade2
	 */
	public String getCommissionPersonGrade2()
	{
		return commissionPersonGrade2;
	}
	
	/**
	 * @param commissionPersonGrade2
	 *            the commissionPersonGrade2 to set
	 */
	public void setCommissionPersonGrade2(String commissionPersonGrade2)
	{
		this.commissionPersonGrade2 = commissionPersonGrade2;
	}
	
	/**
	 * @return the commissionPersonGrade3
	 */
	public String getCommissionPersonGrade3()
	{
		return commissionPersonGrade3;
	}
	
	/**
	 * @param commissionPersonGrade3
	 *            the commissionPersonGrade3 to set
	 */
	public void setCommissionPersonGrade3(String commissionPersonGrade3)
	{
		this.commissionPersonGrade3 = commissionPersonGrade3;
	}
	
	/**
	 * @return the commissionPersonGrade4
	 */
	public String getCommissionPersonGrade4()
	{
		return commissionPersonGrade4;
	}
	
	/**
	 * @param commissionPersonGrade4
	 *            the commissionPersonGrade4 to set
	 */
	public void setCommissionPersonGrade4(String commissionPersonGrade4)
	{
		this.commissionPersonGrade4 = commissionPersonGrade4;
	}
	
	/**
	 * @return the isAddressUSPSValidated
	 */
	public String getIsAddressUSPSValidated()
	{
		return isAddressUSPSValidated;
	}
	
	/**
	 * @param isAddressUSPSValidated
	 *            the isAddressUSPSValidated to set
	 */
	public void setIsAddressUSPSValidated(String isAddressUSPSValidated)
	{
		this.isAddressUSPSValidated = isAddressUSPSValidated;
	}
	
	/**
	 * @return the uSPSAddressValidationDate
	 */
	public Timestamp getuSPSAddressValidationDate()
	{
		return uSPSAddressValidationDate;
	}
	
	/**
	 * @param uSPSAddressValidationDate
	 *            the uSPSAddressValidationDate to set
	 */
	public void setuSPSAddressValidationDate(Timestamp uSPSAddressValidationDate)
	{
		this.uSPSAddressValidationDate = uSPSAddressValidationDate;
	}
	
	public Double getBillablePTO()
	{
		return billablePTO;
	}
	
	public void setBillablePTO(Double billablePTO)
	{
		this.billablePTO = billablePTO;
	}
	
	public Double getNonBillablePTO()
	{
		return nonBillablePTO;
	}
	
	public void setNonBillablePTO(Double nonBillablePTO)
	{
		this.nonBillablePTO = nonBillablePTO;
	}
	
	public Double getBillablePTOCost()
	{
		return billablePTOCost;
	}
	
	public void setBillablePTOCost(Double billablePTOCost)
	{
		this.billablePTOCost = billablePTOCost;
	}
	
	public Double getNonBillablePTOCost()
	{
		return nonBillablePTOCost;
	}
	
	public void setNonBillablePTOCost(Double nonBillablePTOCost)
	{
		this.nonBillablePTOCost = nonBillablePTOCost;
	}
	
	public double getTotalHolidays()
	{
		return totalHolidays;
	}
	
	public void setTotalHolidays(Double totalHolidays)
	{
		this.totalHolidays = totalHolidays;
	}
	
	public Double getBillableHolidays()
	{
		return billableHolidays;
	}
	
	public void setBillableHolidays(Double billableHolidays)
	{
		this.billableHolidays = billableHolidays;
	}
	
	public Double getNonBillableHolidays()
	{
		return nonBillableHolidays;
	}
	
	public void setNonBillableHolidays(Double nonBillableHolidays)
	{
		this.nonBillableHolidays = nonBillableHolidays;
	}
	
	public Double getBillableHolidaysCost()
	{
		return billableHolidaysCost;
	}
	
	public void setBillableHolidaysCost(Double billableHolidaysCost)
	{
		this.billableHolidaysCost = billableHolidaysCost;
	}
	
	public Double getNonBillableHolidaysCost()
	{
		return nonBillableHolidaysCost;
	}
	
	public void setNonBillableHolidaysCost(Double nonBillableHolidaysCost)
	{
		this.nonBillableHolidaysCost = nonBillableHolidaysCost;
	}



	public Double getGrossProfitPercentage()
	{
		return grossProfitPercentage;
	}



	public void setGrossProfitPercentage(Double grossProfitPercentage)
	{
		this.grossProfitPercentage = grossProfitPercentage;
	}



	public Double getGrossProfit()
	{
		return grossProfit;
	}



	public void setGrossProfit(Double grossProfit)
	{
		this.grossProfit = grossProfit;
	}



	public Double getCommissionableProfit()
	{
		return commissionableProfit;
	}



	public void setCommissionableProfit(Double commissionableProfit)
	{
		this.commissionableProfit = commissionableProfit;
	}



	public Integer getRejectionStatus()
	{
		return rejectionStatus;
	}



	public void setRejectionStatus(Integer rejectionStatus)
	{
		this.rejectionStatus = rejectionStatus;
	}



	public String getRejectionReason()
	{
		return rejectionReason;
	}



	public void setRejectionReason(String rejectionReason)
	{
		this.rejectionReason = rejectionReason;
	}



	public String getRejectedBy()
	{
		return rejectedBy;
	}



	public void setRejectedBy(String rejectedBy)
	{
		this.rejectedBy = rejectedBy;
	}



	public Timestamp getRejectionDate()
	{
		return rejectionDate;
	}



	public void setRejectionDate(Timestamp rejectionDate)
	{
		this.rejectionDate = rejectionDate;
	}



	public String getGeoOffice()
	{
		return geoOffice;
	}



	public void setGeoOffice(String geoOffice)
	{
		this.geoOffice = geoOffice;
	}
	

	public String getLongitude()
	{
		return longitude;
	}

	public void setLongitude(String longitude)
	{
		this.longitude = longitude;
	}

	public String getLatitude()
	{
		return latitude;
	}

	public void setLatitude(String latitude)
	{
		this.latitude = latitude;
	}



	/**
	 * @return the clientCompanyName
	 */
	public String getClientCompanyName()
	{
		return clientCompanyName;
	}



	/**
	 * @param clientCompanyName the clientCompanyName to set
	 */
	public void setClientCompanyName(String clientCompanyName)
	{
		this.clientCompanyName = clientCompanyName;
	}



	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "Analyser [analyserId=" + analyserId + ", address1=" + address1 + ", address2=" + address2 + ", analyzerCategory1=" + analyzerCategory1 + ", analyzerCategory2=" + analyzerCategory2
				+ ", annualPay=" + annualPay + ", appBackup=" + appBackup + ", approvalDate=" + approvalDate + ", approvedByAdmin=" + approvedByAdmin + ", approvedByManager=" + approvedByManager
				+ ", billRate=" + billRate + ", bonusAmount=" + bonusAmount + ", bonusPercentage=" + bonusPercentage + ", branch=" + branch + ", branchInter=" + branchInter + ", checkedReferences="
				+ checkedReferences + ", chkReferralFee=" + chkReferralFee + ", city=" + city + ", clientAddressId=" + clientAddressId + ", clientId=" + clientId + ", com=" + com + ", comm=" + comm
				+ ", commEffectiveDate=" + commEffectiveDate + ", comments=" + comments + ", commentsInter=" + commentsInter + ", commisionName1=" + commisionName1 + ", commisionName1Inter="
				+ commisionName1Inter + ", commisionName2=" + commisionName2 + ", commisionName2Inter=" + commisionName2Inter + ", commisionName3=" + commisionName3 + ", commisionName3Inter="
				+ commisionName3Inter + ", commisionName4=" + commisionName4 + ", commisionName4Inter=" + commisionName4Inter + ", commisionPercent1=" + commisionPercent1
				+ ", commisionPercent1Inter=" + commisionPercent1Inter + ", commisionPercent2=" + commisionPercent2 + ", commisionPercent2Inter=" + commisionPercent2Inter + ", commisionPercent3="
				+ commisionPercent3 + ", commisionPercent3Inter=" + commisionPercent3Inter + ", commisionPercent4=" + commisionPercent4 + ", commisionPercent4Inter=" + commisionPercent4Inter
				+ ", commision1=" + commision1 + ", commision1Inter=" + commision1Inter + ", commision2=" + commision2 + ", commision2Inter=" + commision2Inter + ", commision3=" + commision3
				+ ", commision3Inter=" + commision3Inter + ", commision4=" + commision4 + ", commision4Inter=" + commision4Inter + ", commission=" + commission + ", commissionModel1="
				+ commissionModel1 + ", commissionModel2=" + commissionModel2 + ", commissionModel3=" + commissionModel3 + ", commissionModel4=" + commissionModel4 + ", consultantJobBoard="
				+ consultantJobBoard + ", contractVehicle=" + contractVehicle + ", country=" + country + ", currency=" + currency + ", custom1=" + custom1 + ", custom2=" + custom2 + ", dealType="
				+ dealType + ", dentalInsurance=" + dentalInsurance + ", dentalInsuranceAmount=" + dentalInsuranceAmount + ", discount=" + discount + ", discountRate=" + discountRate + ", dob=" + dob
				+ ", doubleTime=" + doubleTime + ", doubleTimeBill=" + doubleTimeBill + ", education=" + education + ", effectiveDate=" + effectiveDate + ", eligibleForReHire=" + eligibleForReHire
				+ ", email=" + email + ", employeeClass=" + employeeClass + ", employeeVeteran=" + employeeVeteran + ", employeeType=" + employeeType + ", endDate=" + endDate + ", enteredBy="
				+ enteredBy + ", entryDate=" + entryDate + ", equipmentCost=" + equipmentCost + ", execOrphan=" + execOrphan + ", execOrphanInter=" + execOrphanInter + ", fax=" + fax
				+ ", FLSAReferance=" + FLSAReferance + ", FLSAStatus=" + FLSAStatus + ", firstName=" + firstName + ", lastName=" + lastName + ", ga=" + ga + ", gender=" + gender + ", h1=" + h1
				+ ", health=" + health + ", healthBenefitRates=" + healthBenefitRates + ", immigrationCost=" + immigrationCost + ", initial=" + initial + ", internalAccounting=" + internalAccounting
				+ ", isBonusEligible=" + isBonusEligible + ", isHire=" + isHire + ", isProcessed=" + isProcessed + ", isRehired=" + isRehired + ", jobTitle=" + jobTitle + ", jobBillRate="
				+ jobBillRate + ", jobCategory=" + jobCategory + ", jobPayRate=" + jobPayRate + ", jrsr=" + jrsr + ", k401=" + k401 + ", leave=" + leave + ", liaisonName=" + liaisonName
				+ ", ltdBenefit=" + ltdBenefit + ", mobilePager=" + mobilePager + ", mrmrs=" + mrmrs + ", newParentId=" + newParentId + ", nonBillableCost=" + nonBillableCost + ", oldDtPayRate="
				+ oldDtPayRate + ", oldOtPayRate=" + oldOtPayRate + ", oldPayRate=" + oldPayRate + ", oldRateChangedAnalyserId=" + oldRateChangedAnalyserId + ", oneTimeBill=" + oneTimeBill
				+ ", oneTimePay=" + oneTimePay + ", orgId=" + orgId + ", otherDollar=" + otherDollar + ", other1Orphan=" + other1Orphan + ", other1OrphanInter=" + other1OrphanInter
				+ ", other2Orphan=" + other2Orphan + ", other2OrphanInter=" + other2OrphanInter + ", otherAmountHour=" + otherAmountHour + ", parentId=" + parentId + ", payPeriodType="
				+ payPeriodType + ", payRate=" + payRate + ", payRateChangedBy=" + payRateChangedBy + ", payRateChangeEffectiveDate=" + payRateChangeEffectiveDate + ", payRateChangeEntryDate="
				+ payRateChangeEntryDate + ", payRateChangeFlag=" + payRateChangeFlag + ", payRateChangeProcessedBy=" + payRateChangeProcessedBy + ", payRateChangeProcessedDate="
				+ payRateChangeProcessedDate + ", payRateChangeProcessedFlag=" + payRateChangeProcessedFlag + ", percentage401k=" + percentage401k + ", perDiem=" + perDiem + ", permanentPlacement="
				+ permanentPlacement + ", permGAAmount=" + permGAAmount + ", placementAmount=" + placementAmount + ", placementDate=" + placementDate + ", placementPercentage=" + placementPercentage
				+ ", placementSalaryAmount=" + placementSalaryAmount + ", poNumber=" + poNumber + ", processDate=" + processDate + ", product=" + product + ", profit=" + profit + ", pto=" + pto
				+ ", reason=" + reason + ", recordStatus=" + recordStatus + ", recruiterOrphan=" + recruiterOrphan + ", recruiterOrphanInter=" + recruiterOrphanInter + ", recruitingClassification="
				+ recruitingClassification + ", referralFeeAmount=" + referralFeeAmount + ", rehireDate=" + rehireDate + ", rehiredBy=" + rehiredBy + ", siteId=" + siteId + ", skillCategory="
				+ skillCategory + ", split=" + split + ", splitCommissionPercentage1=" + splitCommissionPercentage1 + ", splitCommissionPercentage2=" + splitCommissionPercentage2
				+ ", splitCommissionPercentage3=" + splitCommissionPercentage3 + ", splitCommissionPercentage4=" + splitCommissionPercentage4 + ", spreadW=" + spreadW + ", ssn=" + ssn
				+ ", startDate=" + startDate + ", state=" + state + ", stdBenefit=" + stdBenefit + ", subContractorId=" + subContractorId + ", submissionDate=" + submissionDate + ", taxes=" + taxes
				+ ", telephone=" + telephone + ", temps=" + temps + ", terminateDate=" + terminateDate + ", thw=" + thw + ", travelRequired=" + travelRequired + ", unEmployedForTwoMonths="
				+ unEmployedForTwoMonths + ", userId=" + userId + ", vertical=" + vertical + ", verticaltimesheettype=" + verticaltimesheettype + ", workEmail=" + workEmail + ", workPhone="
				+ workPhone + ", worksiteState=" + worksiteState + ", zipCode=" + zipCode + ", projectCost=" + projectCost + ", sickLeavePerHourRate=" + sickLeavePerHourRate + ", sickLeaveCost="
				+ sickLeaveCost + ", falseTermination=" + falseTermination + ", sickLeaveCap=" + sickLeaveCap + ", managingDirector=" + managingDirector + ", commisionName5=" + commisionName5
				+ ", commisionPercent5=" + commisionPercent5 + ", commision5=" + commision5 + ", commisionName6=" + commisionName6 + ", commisionPercent6=" + commisionPercent6 + ", commision6="
				+ commision6 + ", commisionName7=" + commisionName7 + ", commisionPercent7=" + commisionPercent7 + ", commision7=" + commision7 + ", commisionName8=" + commisionName8
				+ ", commisionPercent8=" + commisionPercent8 + ", commision8=" + commision8 + ", commisionName9=" + commisionName9 + ", commisionPercent9=" + commisionPercent9 + ", commision9="
				+ commision9 + ", commissionPersonGrade1=" + commissionPersonGrade1 + ", commissionPersonGrade2=" + commissionPersonGrade2 + ", commissionPersonGrade3=" + commissionPersonGrade3
				+ ", commissionPersonGrade4=" + commissionPersonGrade4 + ", isAddressUSPSValidated=" + isAddressUSPSValidated + ", uSPSAddressValidationDate=" + uSPSAddressValidationDate
				+ ", terminated=" + terminated + ", billablePTO=" + billablePTO + ", nonBillablePTO=" + nonBillablePTO + ", billablePTOCost=" + billablePTOCost + ", nonBillablePTOCost="
				+ nonBillablePTOCost + ", totalHolidays=" + totalHolidays + ", billableHolidays=" + billableHolidays + ", nonBillableHolidays=" + nonBillableHolidays + ", billableHolidaysCost="
				+ billableHolidaysCost + ", nonBillableHolidaysCost=" + nonBillableHolidaysCost + ", grossProfitPercentage=" + grossProfitPercentage + ", grossProfit=" + grossProfit
				+ ", commissionableProfit=" + commissionableProfit + ", rejectionStatus=" + rejectionStatus + ", rejectionReason=" + rejectionReason + ", rejectedBy=" + rejectedBy
				+ ", rejectionDate=" + rejectionDate + ", geoOffice=" + geoOffice + ", longitude=" + longitude + ", latitude=" + latitude + ", clientCompanyName=" + clientCompanyName 
				+ ", pTOLimitToOverrideSick=" + pTOLimitToOverrideSick + ", distanceFromWorksite=" + distanceFromWorksite + ", sickLeaveType=" + sickLeaveType 
				+ ", portfolio=" + portfolio + ", portfolioDescription=" + portfolioDescription + ", bullhornPlacementId = " +bullhornPlacementId+ ", dealActivityAmount = " +dealActivityAmount 
				+ ", bullhornBatchInfoId=" + bullhornBatchInfoId + ", isModificationRequired=" + isModificationRequired + ", bullhornTerminationDataProcessedId= " +bullhornTerminationDataProcessedId+ ""
				+ ", bullhornTerminationDataStagingId= " +bullhornTerminationDataStagingId + ", terminationBullhornBatchInfoId= " + terminationBullhornBatchInfoId 
				+ ", overrideTermination=" + overrideTermination + ", workFromSource=" + workFromSource + ", majorityWorkPerformed= " +majorityWorkPerformed+ ", sickLeaveSource= " +sickLeaveSource 
				
				+ "]";
	}

	/**
	 * @return the sickLeaveType
	 */
	public String getSickLeaveType()
	{
		return sickLeaveType;
	}

	/**
	 * @param sickLeaveType the sickLeaveType to set
	 */
	public void setSickLeaveType(String sickLeaveType)
	{
		this.sickLeaveType = sickLeaveType;
	}

	/**
	 * @return the portfolio
	 */
	public String getPortfolio() {
		return portfolio;
	}

	/**
	 * @param portfolio the portfolio to set
	 */
	public void setPortfolio(String portfolio) {
		this.portfolio = portfolio;
	}

	/**
	 * @return the pTOLimitToOverrideSick
	 */
	public Double getpTOLimitToOverrideSick() {
		return pTOLimitToOverrideSick;
	}

	/**
	 * @param pTOLimitToOverrideSick the pTOLimitToOverrideSick to set
	 */
	public void setpTOLimitToOverrideSick(Double pTOLimitToOverrideSick) {
		this.pTOLimitToOverrideSick = pTOLimitToOverrideSick;
	}

	/**
	 * @return the distanceFromWorksite
	 */
	public String getDistanceFromWorksite() {
		return distanceFromWorksite;
	}

	/**
	 * @param distanceFromWorksite the distanceFromWorksite to set
	 */
	public void setDistanceFromWorksite(String distanceFromWorksite) {
		this.distanceFromWorksite = distanceFromWorksite;
	}

	/**
	 * @return the portfolioDescription
	 */
	public String getPortfolioDescription() {
		return portfolioDescription;
	}

	/**
	 * @param portfolioDescription the portfolioDescription to set
	 */
	public void setPortfolioDescription(String portfolioDescription) {
		this.portfolioDescription = portfolioDescription;
	}
	
	/**
	 * @return the companyCode
	 */
	public String getCompanyCode() {
		return companyCode;
	}
	
	/**
	 * @param companyCode the companyCode to set
	 */
	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}
	/**
	 * @return the dealPortfolio1AE1
	 */
	public String getDealPortfolio1AE1() {
		return dealPortfolio1AE1;
	}
	/**
	 * @param dealPortfolio1AE1 the dealPortfolio1AE1 to set
	 */
	public void setDealPortfolio1AE1(String dealPortfolio1AE1) {
		this.dealPortfolio1AE1 = dealPortfolio1AE1;
	}
	/**
	 * @return the dealPortfolio2REC1
	 */
	public String getDealPortfolio2REC1() {
		return dealPortfolio2REC1;
	}
	/**
	 * @param dealPortfolio2REC1 the dealPortfolio2REC1 to set
	 */
	public void setDealPortfolio2REC1(String dealPortfolio2REC1) {
		this.dealPortfolio2REC1 = dealPortfolio2REC1;
	}
	/**
	 * @return the dealPortfolio3AE2
	 */
	public String getDealPortfolio3AE2() {
		return dealPortfolio3AE2;
	}
	/**
	 * @param dealPortfolio3AE2 the dealPortfolio3AE2 to set
	 */
	public void setDealPortfolio3AE2(String dealPortfolio3AE2) {
		this.dealPortfolio3AE2 = dealPortfolio3AE2;
	}
	/**
	 * @return the dealPortfolio4REC2
	 */
	public String getDealPortfolio4REC2() {
		return dealPortfolio4REC2;
	}
	/**
	 * @param dealPortfolio4REC2 the dealPortfolio4REC2 to set
	 */
	public void setDealPortfolio4REC2(String dealPortfolio4REC2) {
		this.dealPortfolio4REC2 = dealPortfolio4REC2;
	}
	/**
	 * @return the bullhornBatchDataProcessedId
	 */
	public Integer getBullhornBatchDataProcessedId() {
		return bullhornBatchDataProcessedId;
	}
	/**
	 * @param bullhornBatchDataProcessedId the bullhornBatchDataProcessedId to set
	 */
	public void setBullhornBatchDataProcessedId(Integer bullhornBatchDataProcessedId) {
		this.bullhornBatchDataProcessedId = bullhornBatchDataProcessedId;
	}
	/**
	 * @return the coSellStatus
	 */
	public String getCoSellStatus() {
		return coSellStatus;
	}
	/**
	 * @param coSellStatus the coSellStatus to set
	 */
	public void setCoSellStatus(String coSellStatus) {
		this.coSellStatus = coSellStatus;
	}
	/**
	 * @return the dataSource
	 */
	public String getDataSource() {
		return dataSource;
	}
	/**
	 * @param dataSource the dataSource to set
	 */
	public void setDataSource(String dataSource) {
		this.dataSource = dataSource;
	}
	/**
	 * @return the bullhornBatchCode
	 */
	public String getBullhornBatchCode() {
		return bullhornBatchCode;
	}
	/**
	 * @param bullhornBatchCode the bullhornBatchCode to set
	 */
	public void setBullhornBatchCode(String bullhornBatchCode) {
		this.bullhornBatchCode = bullhornBatchCode;
	}
	
	/**
	 * @return the bullhornBatchAnalyzerStagingId
	 */
	public Integer getBullhornBatchAnalyzerStagingId() {
		return bullhornBatchAnalyzerStagingId;
	}
	/**
	 * @param bullhornBatchAnalyzerStagingId the bullhornBatchAnalyzerStagingId to set
	 */
	public void setBullhornBatchAnalyzerStagingId(Integer bullhornBatchAnalyzerStagingId) {
		this.bullhornBatchAnalyzerStagingId = bullhornBatchAnalyzerStagingId;
	}
	/**
	 * @return the placementType
	 */
	public String getPlacementType() {
		return placementType;
	}
	/**
	 * @param placementType the placementType to set
	 */
	public void setPlacementType(String placementType) {
		this.placementType = placementType;
	}
	
	/**
	 * @return the bullhornPlacementId
	 */	
	public Integer getBullhornPlacementId() {
		return bullhornPlacementId;
	}

	/**
	 * @param bullhornPlacementId the bullhornPlacementId to set
	 */
	public void setBullhornPlacementId(Integer bullhornPlacementId) {
		this.bullhornPlacementId = bullhornPlacementId;
	}
	
	/**
	 * @return the dealActivityAmount
	 */
	public String getDealActivityAmount() {
		return dealActivityAmount;
	}
	/**
	 * @param dealActivityAmount the dealActivityAmount to set
	 */
	public void setDealActivityAmount(String dealActivityAmount) {
		this.dealActivityAmount = dealActivityAmount;
	}

	public Integer getBullhornBatchInfoId() {
		return bullhornBatchInfoId;
	}



	public void setBullhornBatchInfoId(Integer bullhornBatchInfoId) {
		this.bullhornBatchInfoId = bullhornBatchInfoId;
	}



	public String getIsModificationRequired() {
		return isModificationRequired;
	}



	public void setIsModificationRequired(String isModificationRequired) {
		this.isModificationRequired = isModificationRequired;
	}



	public Long getBullhornTerminationDataProcessedId() {
		return bullhornTerminationDataProcessedId;
	}



	public void setBullhornTerminationDataProcessedId(Long bullhornTerminationDataProcessedId) {
		this.bullhornTerminationDataProcessedId = bullhornTerminationDataProcessedId;
	}



	public Long getBullhornTerminationDataStagingId() {
		return bullhornTerminationDataStagingId;
	}



	public void setBullhornTerminationDataStagingId(Long bullhornTerminationDataStagingId) {
		this.bullhornTerminationDataStagingId = bullhornTerminationDataStagingId;
	}



	public Integer getTerminationBullhornBatchInfoId() {
		return terminationBullhornBatchInfoId;
	}



	public void setTerminationBullhornBatchInfoId(Integer terminationBullhornBatchInfoId) {
		this.terminationBullhornBatchInfoId = terminationBullhornBatchInfoId;
	}



	public String getOverrideTermination() {
		return overrideTermination;
	}



	public void setOverrideTermination(String overrideTermination) {
		this.overrideTermination = overrideTermination;
	}
	
	public String getWorkFromSource() {
		return workFromSource;
	}



	public void setWorkFromSource(String workFromSource) {
		this.workFromSource = workFromSource;
	}



	public String getMajorityWorkPerformed() {
		return majorityWorkPerformed;
	}



	public void setMajorityWorkPerformed(String majorityWorkPerformed) {
		this.majorityWorkPerformed = majorityWorkPerformed;
	}



	public String getSickLeaveSource() {
		return sickLeaveSource;
	}



	public void setSickLeaveSource(String sickLeaveSource) {
		this.sickLeaveSource = sickLeaveSource;
	}
}