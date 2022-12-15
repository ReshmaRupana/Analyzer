/**
 * 
 */
package com.disys.analyzer.model.dto;

import java.io.Serializable;
import java.sql.Timestamp;

import org.apache.commons.lang3.builder.DiffBuilder;
import org.apache.commons.lang3.builder.DiffResult;
import org.apache.commons.lang3.builder.Diffable;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * @author Sajid
 *  * @since 12/08/2020
 */
public class AnalyserDTO implements Diffable<AnalyserDTO>, Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6732343507905096317L;
	private String analyserId;
	private String orgId;
	
	private Double totHoursWorked;
	private String benefitsAmount;// Health Benefit Rates
	private String percent401k;
	private String education;
	private Double leave;
	private String od; // Other Dollar
	private String otherAmounts;// Other Amounts per Hour
	private String discount;
	private Double billRate;
	private String health;
	private String k401;
	private String tax; // Taxes
	private Double payRate;
	private Double ga;
	private Double commission;
	private String spreadWeek;
	private String otb; // One time Bill
	private String oneTimeAmount; // One Time Pay
	
	private String lName;
	private String fName;
	private String address1;
	private String address2;
	private String city;
	private String state;
	private String zip;
	private String telephone;
	private String workPhone; // Setter
	private String fax;
	private String email;
	
	private String ssn;
	private String dob;
	private String jobTitle;
	
	private String empType;
	
	private String clientId;
	private String clientCompany;
	
	private Integer clientAddressId;
	private Integer clientSiteId;
	
	private String clientManagerName;
	private String siteLocation;
	private String invoiceAddress;
	private String managerPhone;
	private String startDate;
	private String endDate;
	private String submissionDate;
	
	private String contractorId;
	private String contCompanyName;
	private String contFin;
	private String contPocName;
	private String contPayTerm;
	private String contEmail;
	private String contPhone;
	private String contAddress;
	
	private String commName1;
	private Double commPer1;
	private String commName2;
	private Double commPer2;
	private String commName3;
	private Double commPer3;
	private String commName4;
	private Double commPer4;
	
	private String comments;
	
	private String creatorId;
	
	private String tempSubContId;
	
	private String recordStatus;
	
	private Double profit;
	
	private String discountRate;
	private String effectiveDate;
	
	private String termDate;
	private String reason;
	
	private String branch;
	private String split;
	private String rdoPTO;
	
	private String mobilePager;
	private String initial;
	private String mrmrs;
	private String jrsr;
	
	private String commision1;
	private String commision2;
	private String commision3;
	private String commision4;
	
	private String commEffDate;
	private String ponumber;
	
	private String internalAccounting;
	
	private String liaisonName;
	private Boolean references;
	private Boolean h1;
	
	private String country;
	private String jobCategory;
	
	private String branchInter;
	private String commentsInter;
	
	private String commName1Inter;
	private String commPer1Inter;
	private String commName2Inter;
	private String commPer2Inter;
	private String commName3Inter;
	private String commPer3Inter;
	private String commName4Inter;
	private String commPer4Inter;
	
	private String commision1Inter;
	private String commision2Inter;
	private String commision3Inter;
	private String commision4Inter;
	
	private Boolean execOrphan;
	private Boolean recruiterOrphan;
	private Boolean other1Orphan;
	private Boolean other2Orphan;
	
	private Boolean execOrphanHdnVal;
	private Boolean recruiterOrphanHdnVal;
	private Boolean other1OrphanHdnVal;
	private Boolean other2OrphanHdnVal;
	
	private Boolean execOrphanInter;
	private Boolean recruiterOrphanInter;
	private Boolean other1OrphanInter;
	private Boolean other2OrphanInter;
	
	private String currency;
	
	private String temps;
	private String annualPay;
	private String contractVehicle;
	
	private String jobPayRate;
	private String jobBillRate;
	
	private String DoubleTime;
	
	private String DoubleTimeBill;
	
	private String dtb;
	private String dtp;
	
	private String perdiem;
	private Boolean placement;
	private String placementAmount;
	private String placementDate;
	private String salaryAmount;
	private String placementPercentage;
	
	// new fields
	private String workEmail;
	private String chkReferralFee;
	private String referralFeeAmount;
	private Double volumeDiscount;
	private Double paymentDiscount;
	private Double otherDiscount;
	private Double backgroundAmount;
	private Double drugTestAmount;
	private Double creditCheckAmount;
	private Double backgroundCheckAmount; // sum of three background amounts
	private String clientEmail;
	private String managerEmail;
	
	private String unEmployedForTwoMonths;
	
	private String dentalInsurance;
	private String stdBenefit;
	private String ltdBenefit;
	private String consultantJobBoard;
	private Double dentalInsuranceAmount;
	
	private String paymentTerms;
	private String attention;
	private String distributionMethod;
	private String specialNotes;
	private String invoiceFrequency;
	private String employeeVeteran; // add new list of veteran employee
	private String gender; // add gender type
	private String workSiteState; // add worksitestate
	private String analyzerCategory1;
	private String analyzerCategory2;
	private String flsaStatus;
	private String flsaReference;
	private String custom1;
	private String billingType;
	private String deliveryType;
	private String practiceArea;
	private String parentId;
	private String newParentId;
	private String dealType;
	private String comm;
	private String custom2;
	private Boolean com;
	private String isBonusEligible;
	private Double bonusAmount;
	private String bonusPercentage;
	private Double permGAAmount;
	
	private String splitCommissionPercentage1;
	private String splitCommissionPercentage2;
	private String splitCommissionPercentage3;
	private String splitCommissionPercentage4;
	private String skillCategory;
	private String vertical;
	private String employeeClass;
	private String verticalTimesheetType;
	
	private Double immigrationCost;
	private Double equipmentCost;
	private String product;
	private Double nonBillableCost;
	
	private String travelRequired;
	private String commissionModel1;
	private String commissionModel2;
	private String commissionModel3;
	private String commissionModel4;
	
	private String recruitingClassification;
	
	private Double grossProfit;
	private String clientCompleteAddress;
	
	private String modifiedBy;
	private String approvedByManager;
	private Timestamp approvalDate;
	private String isRehired;
	
	private Double sickLeaveCost;
	private String falseTermination;
	private Double projectCost;
	
	private String managingDirector;
	
	private Double sickLeavePerHourRate;
	private String sickLeaveCap;
	
	private String commissionPerson5;
	private String commissionPerson6;
	private String commissionPerson7;
	private String commissionPerson8;
	private String commissionPerson9;
	
	private Double commissionPercentage5;
	private Double commissionPercentage6;
	private Double commissionPercentage7;
	private Double commissionPercentage8;
	private Double commissionPercentage9;
	
	private Double commission5;
	private Double commission6;
	private Double commission7;
	private Double commission8;
	private Double commission9;
	
	private String commissionPersonGrade1;
	private String commissionPersonGrade2;
	private String commissionPersonGrade3;
	private String commissionPersonGrade4;
	
	private String isAddressUSPSValidated;
	private String uSPSAddressValidationDate;
	
	// ---------- 16-08-2018 -------------------//
	private Double billablePTO;
	private Double nonBillablePTO;
	private Double billablePTOCost;
	private Double nonBillablePTOCost;
	private Double totalHolidays;
	private Double billableHolidays;
	private Double nonBillableHolidays;
	private Double billableHolidaysCost;
	private Double nonBillableHolidaysCost;
	
	private Double grossProfitPercentage;
	private Double commissionableProfit;
	private Integer rejectionStatus;
	private String rejectionReason;
	private String rejectedBy;
	private Timestamp rejectionDate;
	private String geoOffice;
	
	private String longitude;
	private String latitude;
	
	private Double pTOLimitToOverrideSick;
	private String distanceFromWorksite;
	private String sickLeaveType;
	
	private String portfolio;
	private String portfolioDescription;
	
	private String worksiteSelectionMessage;
	
	private String companyCode;
	private String dealPortfolio1AE1;
	private String dealPortfolio2REC1;
	private String dealPortfolio3AE2;
	private String dealPortfolio4REC2;
	private Long bullhornBatchDataProcessedId;
	private String coSellStatus;
	private String dataSource;
	private String bullhornBatchCode;
	private Long bullhornBatchAnalyzerStagingId;
	private String placementType;
	private String vendorCompanyCode;
	private String clientCompanyCode;
	
	private Integer bullhornPlacementId;
	private String dealActivityAmount;
	/*
	 * Added as part of Tax and Sick Leave Project
	 */
	private Integer bullhornBatchInfoId;
	private String isModificationRequired;
	private Long bullhornTerminationDataProcessedId;
	private Long bullhornTerminationDataStagingId;
	private Integer terminationBullhornBatchInfoId;
	private String overrideTermination;
	private String workFromSource;
	private String majorityWorkPerformed;
	private String sickLeaveSource;
	
	public AnalyserDTO()
	{		
		analyserId = "";
		orgId = "";
		
		totHoursWorked = 0.00;
		benefitsAmount = "0";// Health Benefit Rates
		percent401k = "0";
		education = "null";
		leave = 0.00;
		od = ""; // Other Dollar
		otherAmounts = "";// Other Amounts per Hour
		discount = "";
		billRate = 0.00;
		health = "0";
		k401 = "0";
		tax = "0"; // Taxes
		payRate = 0.00;
		ga = 0.00;
		commission = 0.00;
		spreadWeek = "0.00";
		otb = "0.00"; // One time Bill
		oneTimeAmount = "0.00"; // One Time Pay
		
		lName = "";
		fName = "";
		address1 = "";
		address2 = "";
		city = "";
		state = "";
		zip = "";
		telephone = "";
		workPhone = ""; // Setter
		fax = "";
		email = "";
		
		ssn = "";
		dob = "";
		jobTitle = "";
		
		empType = "";
		
		clientId = "";
		clientCompany = "";
		
		clientAddressId = 0;
		clientSiteId = 0;
		
		clientManagerName = "";
		siteLocation = "";
		invoiceAddress = "";
		managerPhone = "";
		startDate = "";
		endDate = "";
		submissionDate = "";
		
		contractorId = "";
		contCompanyName = "";
		contFin = "";
		contPocName = "";
		contPayTerm = "";
		contEmail = "";
		contPhone = "";
		contAddress = "";
		
		commName1 = "";
		commPer1 = 0.00;
		commName2 = "";
		commPer2 = 0.00;
		commName3 = "";
		commPer3 = 0.00;
		commName4 = "";
		commPer4 = 0.00;
		commissionPerson5 = "";
		commissionPercentage5 = 0.00;
		commission5 = 0.00;
		commissionPerson6 = "";
		commissionPercentage6 = 0.00;
		commission6 = 0.00;
		commissionPerson7 = "";
		commissionPercentage7 = 0.00;
		commission7 = 0.00;
		commissionPerson8 = "";
		commissionPercentage8 = 0.00;
		commission8 = 0.00;
		commissionPerson9 = "";
		commissionPercentage9 = 0.00;
		commission9 = 0.00;
		
		sickLeavePerHourRate = 0.00;
		
		comments = "";
		
		creatorId = "";
		
		tempSubContId = "";
		
		recordStatus = "";
		
		profit = 0.00;
		
		discountRate = "0.00";
		effectiveDate = "";
		
		termDate = "";
		reason = "";
		
		branch = "";
		split = "";
		rdoPTO = "";
		
		mobilePager = "";
		initial = "";
		mrmrs = "";
		jrsr = "";
		
		commision1 = "0";
		commision2 = "0";
		commision3 = "0";
		commision4 = "0";
		commission5 = 0.00;
		commission6 = 0.00;
		commission7 = 0.00;
		commission8 = 0.00;
		commission9 = 0.00;
		
		commEffDate = "";
		ponumber = "";
		
		internalAccounting = "";
		
		liaisonName = "";
		references = false;
		h1 = false;
		
		country = "";
		jobCategory = "";
		
		branchInter = "";
		commentsInter = "";
		
		commName1Inter = "";
		commPer1Inter = "";
		commName2Inter = "";
		commPer2Inter = "";
		commName3Inter = "";
		commPer3Inter = "";
		commName4Inter = "";
		commPer4Inter = "";
		
		commision1Inter = "";
		commision2Inter = "";
		commision3Inter = "";
		commision4Inter = "";
		
		execOrphan = false;
		recruiterOrphan = false;
		other1Orphan = false;
		other2Orphan = false;
		
		execOrphanHdnVal = false;
		recruiterOrphanHdnVal = false;
		other1OrphanHdnVal = false;
		other2OrphanHdnVal = false;
		
		execOrphanInter = false;
		recruiterOrphanInter = false;
		other1OrphanInter = false;
		other2OrphanInter = false;
		
		currency = "";
		
		temps = "";
		annualPay = "";
		contractVehicle = "";
		
		jobPayRate = "0.00";
		jobBillRate = "0.00";
		
		DoubleTime = "0.00";
		
		DoubleTimeBill = "0.00";
		
		dtb = "";
		dtp = "";
		
		perdiem = "0.00";
		placement = false;
		placementAmount = "0.00";
		placementDate = "";
		salaryAmount = "0.00";
		placementPercentage = "0";
		
		// new fields
		workEmail = "";
		chkReferralFee = "";
		referralFeeAmount = "0.00";
		volumeDiscount = 0.00;
		paymentDiscount = 0.00;
		otherDiscount = 0.00;
		backgroundAmount = 0.00;
		drugTestAmount = 0.00;
		creditCheckAmount = 0.00;
		backgroundCheckAmount = 0.00; // sum of three background amounts
		clientEmail = "";
		managerEmail = "";
		
		unEmployedForTwoMonths = "";
		
		dentalInsurance = "";
		stdBenefit = "";
		ltdBenefit = "";
		consultantJobBoard = "";
		dentalInsuranceAmount = 0.00;
		
		paymentTerms = "";
		attention = "";
		distributionMethod = "";
		specialNotes = "";
		invoiceFrequency = "";
		employeeVeteran = ""; // add new list of veteran employee
		gender = ""; // add gender type
		workSiteState = ""; // add worksitestate
		analyzerCategory1 = "";
		analyzerCategory2 = "";
		flsaStatus = "";
		flsaReference = "";
		custom1 = "";
		billingType = "";
		deliveryType = "";
		practiceArea = "";
		parentId = "";
		newParentId = "";
		dealType = "";
		comm = "null";
		custom2 = "";
		com = false;
		isBonusEligible = "";
		bonusAmount = 0.00;
		bonusPercentage = "0";
		permGAAmount = 0.00;
		
		splitCommissionPercentage1 = "0";
		splitCommissionPercentage2 = "0";
		splitCommissionPercentage3 = "0";
		splitCommissionPercentage4 = "0";
		skillCategory = "";
		vertical = "";
		employeeClass = "";
		verticalTimesheetType = "";
		
		immigrationCost = 0.00;
		equipmentCost = 0.00;
		product = "";
		nonBillableCost = 0.00;
		
		travelRequired = "";
		commissionModel1 = "";
		commissionModel2 = "";
		commissionModel3 = "";
		commissionModel4 = "";
		
		recruitingClassification = "";
		
		grossProfit = 0.00;
		clientCompleteAddress = "";
		modifiedBy = "";
		approvedByManager = "";
		sickLeaveCost = 0.00;
		
		billablePTO = 0.00;
		nonBillablePTO = 0.00;
		billablePTOCost = 0.00;
		nonBillablePTOCost = 0.00;
		totalHolidays = 0.00;
		billableHolidays = 0.00;
		nonBillableHolidays = 0.00;
		billableHolidaysCost = 0.00;
		nonBillableHolidaysCost = 0.00;
		
		grossProfitPercentage = 0.00;
		grossProfit = 0.00;
		commissionableProfit = 0.00;
		rejectionStatus = 0;
		pTOLimitToOverrideSick = 0.00;
		
		portfolio = "";
		portfolioDescription = "";
		
		worksiteSelectionMessage = "";
		
		companyCode="";
		dealPortfolio1AE1="";
		dealPortfolio2REC1="";
		dealPortfolio3AE2="";
		dealPortfolio4REC2="";
		bullhornBatchDataProcessedId=0L;
		coSellStatus="";
		dataSource="";
		bullhornBatchCode="";
		bullhornBatchAnalyzerStagingId=0L;
		placementType="";
		vendorCompanyCode="";
		clientCompanyCode="";
		bullhornPlacementId=0;
		dealActivityAmount="";
		
		/*
		 * Added as part of Tax and Sick Leave Project
		 */
		
		bullhornBatchInfoId = 0;
		isModificationRequired = "";
		bullhornTerminationDataProcessedId = 0L;
		bullhornTerminationDataStagingId = 0L;
		terminationBullhornBatchInfoId =0;
		overrideTermination = "";
		workFromSource="";
		majorityWorkPerformed="";
		sickLeaveSource="";
	}
	
	
	/**
	 * @return the analyserId
	 */
	public String getAnalyserId()
	{
		return analyserId;
	}
	
	/**
	 * @param analyserId
	 *            the analyserId to set
	 */
	public void setAnalyserId(String analyserId)
	{
		this.analyserId = analyserId;
	}
	
	/**
	 * @return the orgId
	 */
	public String getOrgId()
	{
		return orgId;
	}
	
	/**
	 * @param orgId
	 *            the orgId to set
	 */
	public void setOrgId(String orgId)
	{
		this.orgId = orgId;
	}
	
	/**
	 * @return the benefitsAmount
	 */
	public String getBenefitsAmount()
	{
		return benefitsAmount;
	}
	
	/**
	 * @param benefitsAmount
	 *            the benefitsAmount to set
	 */
	public void setBenefitsAmount(String benefitsAmount)
	{
		this.benefitsAmount = benefitsAmount;
	}
	
	/**
	 * @return the percent401k
	 */
	public String getPercent401k()
	{
		return percent401k;
	}
	
	/**
	 * @param percent401k
	 *            the percent401k to set
	 */
	public void setPercent401k(String percent401k)
	{
		this.percent401k = percent401k;
	}
	
	/**
	 * @return the education
	 */
	public String getEducation()
	{
		return education;
	}
	
	/**
	 * @param education
	 *            the education to set
	 */
	public void setEducation(String education)
	{
		this.education = education;
	}
	
	/**
	 * @return the leave
	 */
	public Double getLeave()
	{
		return leave;
	}
	
	/**
	 * @param leave
	 *            the leave to set
	 */
	public void setLeave(Double leave)
	{
		this.leave = leave;
	}
	
	/**
	 * @return the od
	 */
	public String getOd()
	{
		return od;
	}
	
	/**
	 * @param od
	 *            the od to set
	 */
	public void setOd(String od)
	{
		this.od = od;
	}
	
	/**
	 * @return the otherAmounts
	 */
	public String getOtherAmounts()
	{
		return otherAmounts;
	}
	
	/**
	 * @param otherAmounts
	 *            the otherAmounts to set
	 */
	public void setOtherAmounts(String otherAmounts)
	{
		this.otherAmounts = otherAmounts;
	}
	
	/**
	 * @return the discount
	 */
	public String getDiscount()
	{
		return discount;
	}
	
	/**
	 * @param discount
	 *            the discount to set
	 */
	public void setDiscount(String discount)
	{
		this.discount = discount;
	}
	
	/**
	 * @return the health
	 */
	public String getHealth()
	{
		return health;
	}
	
	/**
	 * @param health
	 *            the health to set
	 */
	public void setHealth(String health)
	{
		this.health = health;
	}
	
	/**
	 * @return the k401
	 */
	public String getK401()
	{
		return k401;
	}
	
	/**
	 * @param k401
	 *            the k401 to set
	 */
	public void setK401(String k401)
	{
		this.k401 = k401;
	}
	
	/**
	 * @return the tax
	 */
	public String getTax()
	{
		return tax;
	}
	
	/**
	 * @param tax
	 *            the tax to set
	 */
	public void setTax(String tax)
	{
		this.tax = tax;
	}
	
	/**
	 * @return the spreadWeek
	 */
	public String getSpreadWeek()
	{
		return spreadWeek;
	}
	
	/**
	 * @param spreadWeek
	 *            the spreadWeek to set
	 */
	public void setSpreadWeek(String spreadWeek)
	{
		this.spreadWeek = spreadWeek;
	}
	
	/**
	 * @return the otb
	 */
	public String getOtb()
	{
		return otb;
	}
	
	/**
	 * @param otb
	 *            the otb to set
	 */
	public void setOtb(String otb)
	{
		this.otb = otb;
	}
	
	/**
	 * @return the oneTimeAmount
	 */
	public String getOneTimeAmount()
	{
		return oneTimeAmount;
	}
	
	/**
	 * @param oneTimeAmount
	 *            the oneTimeAmount to set
	 */
	public void setOneTimeAmount(String oneTimeAmount)
	{
		this.oneTimeAmount = oneTimeAmount;
	}
	
	/**
	 * @return the address1
	 */
	public String getAddress1()
	{
		return address1;
	}
	
	/**
	 * @param address1
	 *            the address1 to set
	 */
	public void setAddress1(String address1)
	{
		this.address1 = address1;
	}
	
	/**
	 * @return the address2
	 */
	public String getAddress2()
	{
		return address2;
	}
	
	/**
	 * @param address2
	 *            the address2 to set
	 */
	public void setAddress2(String address2)
	{
		this.address2 = address2;
	}
	
	/**
	 * @return the city
	 */
	public String getCity()
	{
		return city;
	}
	
	/**
	 * @param city
	 *            the city to set
	 */
	public void setCity(String city)
	{
		this.city = city;
	}
	
	/**
	 * @return the state
	 */
	public String getState()
	{
		return state;
	}
	
	/**
	 * @param state
	 *            the state to set
	 */
	public void setState(String state)
	{
		this.state = state;
	}
	
	/**
	 * @return the zip
	 */
	public String getZip()
	{
		return zip;
	}
	
	/**
	 * @param zip
	 *            the zip to set
	 */
	public void setZip(String zip)
	{
		this.zip = zip;
	}
	
	/**
	 * @return the telephone
	 */
	public String getTelephone()
	{
		return telephone;
	}
	
	/**
	 * @param telephone
	 *            the telephone to set
	 */
	public void setTelephone(String telephone)
	{
		this.telephone = telephone;
	}
	
	/**
	 * @return the workphone
	 */
	public String getWorkPhone()
	{
		return workPhone;
	}
	
	/**
	 * @param workphone
	 *            the workphone to set
	 */
	public void setWorkPhone(String workPhone)
	{
		this.workPhone = workPhone;
	}
	
	/**
	 * @return the fax
	 */
	public String getFax()
	{
		return fax;
	}
	
	/**
	 * @param fax
	 *            the fax to set
	 */
	public void setFax(String fax)
	{
		this.fax = fax;
	}
	
	/**
	 * @return the email
	 */
	public String getEmail()
	{
		return email;
	}
	
	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email)
	{
		this.email = email;
	}
	
	/**
	 * @return the ssn
	 */
	public String getSsn()
	{
		return ssn;
	}
	
	/**
	 * @param ssn
	 *            the ssn to set
	 */
	public void setSsn(String ssn)
	{
		this.ssn = ssn;
	}
	
	/**
	 * @return the dob
	 */
	public String getDob()
	{
		return dob;
	}
	
	/**
	 * @param dob
	 *            the dob to set
	 */
	public void setDob(String dob)
	{
		this.dob = dob;
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
	 * @return the empType
	 */
	public String getEmpType()
	{
		return empType;
	}
	
	/**
	 * @param empType
	 *            the empType to set
	 */
	public void setEmpType(String empType)
	{
		this.empType = empType;
	}
	
	/**
	 * @return the clientId
	 */
	public String getClientId()
	{
		return clientId;
	}
	
	/**
	 * @param clientId
	 *            the clientId to set
	 */
	public void setClientId(String clientId)
	{
		this.clientId = clientId;
	}
	
	/**
	 * @return the clientCompany
	 */
	public String getClientCompany()
	{
		return clientCompany;
	}
	
	/**
	 * @param clientCompany
	 *            the clientCompany to set
	 */
	public void setClientCompany(String clientCompany)
	{
		this.clientCompany = clientCompany;
	}
	
	/**
	 * @return the clientAddressId
	 */
	public Integer getClientAddressId()
	{
		return clientAddressId;
	}
	
	/**
	 * @param clientAddressId
	 *            the clientAddressId to set
	 */
	public void setClientAddressId(Integer clientAddressId)
	{
		this.clientAddressId = clientAddressId;
	}
	
	/**
	 * @return the clientSiteId
	 */
	public Integer getClientSiteId()
	{
		return clientSiteId;
	}
	
	/**
	 * @param clientSiteId
	 *            the clientSiteId to set
	 */
	public void setClientSiteId(Integer clientSiteId)
	{
		this.clientSiteId = clientSiteId;
	}
	
	/**
	 * @return the clientManagerName
	 */
	public String getClientManagerName()
	{
		return clientManagerName;
	}
	
	/**
	 * @param clientManagerName
	 *            the clientManagerName to set
	 */
	public void setClientManagerName(String clientManagerName)
	{
		this.clientManagerName = clientManagerName;
	}
	
	/**
	 * @return the siteLocation
	 */
	public String getSiteLocation()
	{
		return siteLocation;
	}
	
	/**
	 * @param siteLocation
	 *            the siteLocation to set
	 */
	public void setSiteLocation(String siteLocation)
	{
		this.siteLocation = siteLocation;
	}
	
	/**
	 * @return the invoiceAddress
	 */
	public String getInvoiceAddress()
	{
		return invoiceAddress;
	}
	
	/**
	 * @param invoiceAddress
	 *            the invoiceAddress to set
	 */
	public void setInvoiceAddress(String invoiceAddress)
	{
		this.invoiceAddress = invoiceAddress;
	}
	
	/**
	 * @return the managerPhone
	 */
	public String getManagerPhone()
	{
		return managerPhone;
	}
	
	/**
	 * @param managerPhone
	 *            the managerPhone to set
	 */
	public void setManagerPhone(String managerPhone)
	{
		this.managerPhone = managerPhone;
	}
	
	/**
	 * @return the startDate
	 */
	public String getStartDate()
	{
		return startDate;
	}
	
	/**
	 * @param startDate
	 *            the startDate to set
	 */
	public void setStartDate(String startDate)
	{
		this.startDate = startDate;
	}
	
	/**
	 * @return the endDate
	 */
	public String getEndDate()
	{
		return endDate;
	}
	
	/**
	 * @param endDate
	 *            the endDate to set
	 */
	public void setEndDate(String endDate)
	{
		this.endDate = endDate;
	}
	
	/**
	 * @return the contractorId
	 */
	public String getContractorId()
	{
		return contractorId;
	}
	
	/**
	 * @param contractorId
	 *            the contractorId to set
	 */
	public void setContractorId(String contractorId)
	{
		this.contractorId = contractorId;
	}
	
	/**
	 * @return the contCompanyName
	 */
	public String getContCompanyName()
	{
		return contCompanyName;
	}
	
	/**
	 * @param contCompanyName
	 *            the contCompanyName to set
	 */
	public void setContCompanyName(String contCompanyName)
	{
		this.contCompanyName = contCompanyName;
	}
	
	/**
	 * @return the contFin
	 */
	public String getContFin()
	{
		return contFin;
	}
	
	/**
	 * @param contFin
	 *            the contFin to set
	 */
	public void setContFin(String contFin)
	{
		this.contFin = contFin;
	}
	
	/**
	 * @return the contPocName
	 */
	public String getContPocName()
	{
		return contPocName;
	}
	
	/**
	 * @param contPocName
	 *            the contPocName to set
	 */
	public void setContPocName(String contPocName)
	{
		this.contPocName = contPocName;
	}
	
	/**
	 * @return the contPayTerm
	 */
	public String getContPayTerm()
	{
		return contPayTerm;
	}
	
	/**
	 * @param contPayTerm
	 *            the contPayTerm to set
	 */
	public void setContPayTerm(String contPayTerm)
	{
		this.contPayTerm = contPayTerm;
	}
	
	/**
	 * @return the contEmail
	 */
	public String getContEmail()
	{
		return contEmail;
	}
	
	/**
	 * @param contEmail
	 *            the contEmail to set
	 */
	public void setContEmail(String contEmail)
	{
		this.contEmail = contEmail;
	}
	
	/**
	 * @return the contPhone
	 */
	public String getContPhone()
	{
		return contPhone;
	}
	
	/**
	 * @param contPhone
	 *            the contPhone to set
	 */
	public void setContPhone(String contPhone)
	{
		this.contPhone = contPhone;
	}
	
	/**
	 * @return the contAddress
	 */
	public String getContAddress()
	{
		return contAddress;
	}
	
	/**
	 * @param contAddress
	 *            the contAddress to set
	 */
	public void setContAddress(String contAddress)
	{
		this.contAddress = contAddress;
	}
	
	/**
	 * @return the commName1
	 */
	public String getCommName1()
	{
		return commName1;
	}
	
	/**
	 * @param commName1
	 *            the commName1 to set
	 */
	public void setCommName1(String commName1)
	{
		this.commName1 = commName1;
	}
	
	/**
	 * @return the commName2
	 */
	public String getCommName2()
	{
		return commName2;
	}
	
	/**
	 * @param commName2
	 *            the commName2 to set
	 */
	public void setCommName2(String commName2)
	{
		this.commName2 = commName2;
	}
	
	/**
	 * @return the commName3
	 */
	public String getCommName3()
	{
		return commName3;
	}
	
	/**
	 * @param commName3
	 *            the commName3 to set
	 */
	public void setCommName3(String commName3)
	{
		this.commName3 = commName3;
	}
	
	/**
	 * @return the commName4
	 */
	public String getCommName4()
	{
		return commName4;
	}
	
	/**
	 * @param commName4
	 *            the commName4 to set
	 */
	public void setCommName4(String commName4)
	{
		this.commName4 = commName4;
	}
	
	/**
	 * @return the comments
	 */
	public String getComments()
	{
		return comments;
	}
	
	/**
	 * @param comments
	 *            the comments to set
	 */
	public void setComments(String comments)
	{
		this.comments = comments;
	}
	
	/**
	 * @return the creatorId
	 */
	public String getCreatorId()
	{
		return creatorId;
	}
	
	/**
	 * @param creatorId
	 *            the creatorId to set
	 */
	public void setCreatorId(String creatorId)
	{
		this.creatorId = creatorId;
	}
	
	/**
	 * @return the tempSubContId
	 */
	public String getTempSubContId()
	{
		return tempSubContId;
	}
	
	/**
	 * @param tempSubContId
	 *            the tempSubContId to set
	 */
	public void setTempSubContId(String tempSubContId)
	{
		this.tempSubContId = tempSubContId;
	}
	
	/**
	 * @return the recordStatus
	 */
	public String getRecordStatus()
	{
		return recordStatus;
	}
	
	/**
	 * @param recordStatus
	 *            the recordStatus to set
	 */
	public void setRecordStatus(String recordStatus)
	{
		this.recordStatus = recordStatus;
	}
	
	/**
	 * @return the discountRate
	 */
	public String getDiscountRate()
	{
		return discountRate;
	}
	
	/**
	 * @param discountRate
	 *            the discountRate to set
	 */
	public void setDiscountRate(String discountRate)
	{
		this.discountRate = discountRate;
	}
	
	/**
	 * @return the effectiveDate
	 */
	public String getEffectiveDate()
	{
		return effectiveDate;
	}
	
	/**
	 * @param effectiveDate
	 *            the effectiveDate to set
	 */
	public void setEffectiveDate(String effectiveDate)
	{
		this.effectiveDate = effectiveDate;
	}
	
	/**
	 * @return the termDate
	 */
	public String getTermDate()
	{
		return termDate;
	}
	
	/**
	 * @param termDate
	 *            the termDate to set
	 */
	public void setTermDate(String termDate)
	{
		this.termDate = termDate;
	}
	
	/**
	 * @return the reason
	 */
	public String getReason()
	{
		return reason;
	}
	
	/**
	 * @param reason
	 *            the reason to set
	 */
	public void setReason(String reason)
	{
		this.reason = reason;
	}
	
	/**
	 * @return the branch
	 */
	public String getBranch()
	{
		return branch;
	}
	
	/**
	 * @param branch
	 *            the branch to set
	 */
	public void setBranch(String branch)
	{
		this.branch = branch;
	}
	
	/**
	 * @return the split
	 */
	public String getSplit()
	{
		return split;
	}
	
	/**
	 * @param split
	 *            the split to set
	 */
	public void setSplit(String split)
	{
		this.split = split;
	}
	
	/**
	 * @return the rdoPTO
	 */
	public String getRdoPTO()
	{
		return rdoPTO;
	}
	
	/**
	 * @param rdoPTO
	 *            the rdoPTO to set
	 */
	public void setRdoPTO(String rdoPTO)
	{
		this.rdoPTO = rdoPTO;
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
	 * @return the initial
	 */
	public String getInitial()
	{
		return initial;
	}
	
	/**
	 * @param initial
	 *            the initial to set
	 */
	public void setInitial(String initial)
	{
		this.initial = initial;
	}
	
	/**
	 * @return the mrmrs
	 */
	public String getMrmrs()
	{
		return mrmrs;
	}
	
	/**
	 * @param mrmrs
	 *            the mrmrs to set
	 */
	public void setMrmrs(String mrmrs)
	{
		this.mrmrs = mrmrs;
	}
	
	/**
	 * @return the jrsr
	 */
	public String getJrsr()
	{
		return jrsr;
	}
	
	/**
	 * @param jrsr
	 *            the jrsr to set
	 */
	public void setJrsr(String jrsr)
	{
		this.jrsr = jrsr;
	}
	
	/**
	 * @return the commision1
	 */
	public String getCommision1()
	{
		return commision1;
	}
	
	/**
	 * @param commision1
	 *            the commision1 to set
	 */
	public void setCommision1(String commision1)
	{
		this.commision1 = commision1;
	}
	
	/**
	 * @return the commision2
	 */
	public String getCommision2()
	{
		return commision2;
	}
	
	/**
	 * @param commision2
	 *            the commision2 to set
	 */
	public void setCommision2(String commision2)
	{
		this.commision2 = commision2;
	}
	
	/**
	 * @return the commision3
	 */
	public String getCommision3()
	{
		return commision3;
	}
	
	/**
	 * @param commision3
	 *            the commision3 to set
	 */
	public void setCommision3(String commision3)
	{
		this.commision3 = commision3;
	}
	
	/**
	 * @return the commision4
	 */
	public String getCommision4()
	{
		return commision4;
	}
	
	/**
	 * @param commision4
	 *            the commision4 to set
	 */
	public void setCommision4(String commision4)
	{
		this.commision4 = commision4;
	}
	
	/**
	 * @return the commEffDate
	 */
	public String getCommEffDate()
	{
		return commEffDate;
	}
	
	/**
	 * @param commEffDate
	 *            the commEffDate to set
	 */
	public void setCommEffDate(String commEffDate)
	{
		this.commEffDate = commEffDate;
	}
	
	/**
	 * @return the ponumber
	 */
	public String getPonumber()
	{
		return ponumber;
	}
	
	/**
	 * @param ponumber
	 *            the ponumber to set
	 */
	public void setPonumber(String ponumber)
	{
		this.ponumber = ponumber;
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
	 * @return the liaisonName
	 */
	public String getLiaisonName()
	{
		return liaisonName;
	}
	
	/**
	 * @param liaisonName
	 *            the liaisonName to set
	 */
	public void setLiaisonName(String liaisonName)
	{
		this.liaisonName = liaisonName;
	}
	
	/**
	 * @return the references
	 */
	public Boolean isReferences()
	{
		return references;
	}
	
	/**
	 * @param references
	 *            the references to set
	 */
	public void setReferences(Boolean references)
	{
		this.references = references;
	}
	
	/**
	 * @return the h1
	 */
	public Boolean isH1()
	{
		return h1;
	}
	
	/**
	 * @param h1
	 *            the h1 to set
	 */
	public void setH1(Boolean h1)
	{
		this.h1 = h1;
	}
	
	/**
	 * @return the country
	 */
	public String getCountry()
	{
		return country;
	}
	
	/**
	 * @param country
	 *            the country to set
	 */
	public void setCountry(String country)
	{
		this.country = country;
	}
	
	/**
	 * @return the jobCategory
	 */
	public String getJobCategory()
	{
		return jobCategory;
	}
	
	/**
	 * @param jobCategory
	 *            the jobCategory to set
	 */
	public void setJobCategory(String jobCategory)
	{
		this.jobCategory = jobCategory;
	}
	
	/**
	 * @return the branchInter
	 */
	public String getBranchInter()
	{
		return branchInter;
	}
	
	/**
	 * @param branchInter
	 *            the branchInter to set
	 */
	public void setBranchInter(String branchInter)
	{
		this.branchInter = branchInter;
	}
	
	/**
	 * @return the commentsInter
	 */
	public String getCommentsInter()
	{
		return commentsInter;
	}
	
	/**
	 * @param commentsInter
	 *            the commentsInter to set
	 */
	public void setCommentsInter(String commentsInter)
	{
		this.commentsInter = commentsInter;
	}
	
	/**
	 * @return the commName1Inter
	 */
	public String getCommName1Inter()
	{
		return commName1Inter;
	}
	
	/**
	 * @param commName1Inter
	 *            the commName1Inter to set
	 */
	public void setCommName1Inter(String commName1Inter)
	{
		this.commName1Inter = commName1Inter;
	}
	
	/**
	 * @return the commPer1Inter
	 */
	public String getCommPer1Inter()
	{
		return commPer1Inter;
	}
	
	/**
	 * @param commPer1Inter
	 *            the commPer1Inter to set
	 */
	public void setCommPer1Inter(String commPer1Inter)
	{
		this.commPer1Inter = commPer1Inter;
	}
	
	/**
	 * @return the commName2Inter
	 */
	public String getCommName2Inter()
	{
		return commName2Inter;
	}
	
	/**
	 * @param commName2Inter
	 *            the commName2Inter to set
	 */
	public void setCommName2Inter(String commName2Inter)
	{
		this.commName2Inter = commName2Inter;
	}
	
	/**
	 * @return the commPer2Inter
	 */
	public String getCommPer2Inter()
	{
		return commPer2Inter;
	}
	
	/**
	 * @param commPer2Inter
	 *            the commPer2Inter to set
	 */
	public void setCommPer2Inter(String commPer2Inter)
	{
		this.commPer2Inter = commPer2Inter;
	}
	
	/**
	 * @return the commName3Inter
	 */
	public String getCommName3Inter()
	{
		return commName3Inter;
	}
	
	/**
	 * @param commName3Inter
	 *            the commName3Inter to set
	 */
	public void setCommName3Inter(String commName3Inter)
	{
		this.commName3Inter = commName3Inter;
	}
	
	/**
	 * @return the commPer3Inter
	 */
	public String getCommPer3Inter()
	{
		return commPer3Inter;
	}
	
	/**
	 * @param commPer3Inter
	 *            the commPer3Inter to set
	 */
	public void setCommPer3Inter(String commPer3Inter)
	{
		this.commPer3Inter = commPer3Inter;
	}
	
	/**
	 * @return the commName4Inter
	 */
	public String getCommName4Inter()
	{
		return commName4Inter;
	}
	
	/**
	 * @param commName4Inter
	 *            the commName4Inter to set
	 */
	public void setCommName4Inter(String commName4Inter)
	{
		this.commName4Inter = commName4Inter;
	}
	
	/**
	 * @return the commPer4Inter
	 */
	public String getCommPer4Inter()
	{
		return commPer4Inter;
	}
	
	/**
	 * @param commPer4Inter
	 *            the commPer4Inter to set
	 */
	public void setCommPer4Inter(String commPer4Inter)
	{
		this.commPer4Inter = commPer4Inter;
	}
	
	/**
	 * @return the commision1Inter
	 */
	public String getCommision1Inter()
	{
		return commision1Inter;
	}
	
	/**
	 * @param commision1Inter
	 *            the commision1Inter to set
	 */
	public void setCommision1Inter(String commision1Inter)
	{
		this.commision1Inter = commision1Inter;
	}
	
	/**
	 * @return the commision2Inter
	 */
	public String getCommision2Inter()
	{
		return commision2Inter;
	}
	
	/**
	 * @param commision2Inter
	 *            the commision2Inter to set
	 */
	public void setCommision2Inter(String commision2Inter)
	{
		this.commision2Inter = commision2Inter;
	}
	
	/**
	 * @return the commision3Inter
	 */
	public String getCommision3Inter()
	{
		return commision3Inter;
	}
	
	/**
	 * @param commision3Inter
	 *            the commision3Inter to set
	 */
	public void setCommision3Inter(String commision3Inter)
	{
		this.commision3Inter = commision3Inter;
	}
	
	/**
	 * @return the commision4Inter
	 */
	public String getCommision4Inter()
	{
		return commision4Inter;
	}
	
	/**
	 * @param commision4Inter
	 *            the commision4Inter to set
	 */
	public void setCommision4Inter(String commision4Inter)
	{
		this.commision4Inter = commision4Inter;
	}
	
	/**
	 * @return the execOrphan
	 */
	public Boolean isExecOrphan()
	{
		return execOrphan;
	}
	
	/**
	 * @param execOrphan
	 *            the execOrphan to set
	 */
	public void setExecOrphan(Boolean execOrphan)
	{
		this.execOrphan = execOrphan;
	}
	
	/**
	 * @return the recruiterOrphan
	 */
	public Boolean isRecruiterOrphan()
	{
		return recruiterOrphan;
	}
	
	/**
	 * @param recruiterOrphan
	 *            the recruiterOrphan to set
	 */
	public void setRecruiterOrphan(Boolean recruiterOrphan)
	{
		this.recruiterOrphan = recruiterOrphan;
	}
	
	/**
	 * @return the other1Orphan
	 */
	public Boolean isOther1Orphan()
	{
		return other1Orphan;
	}
	
	/**
	 * @param other1Orphan
	 *            the other1Orphan to set
	 */
	public void setOther1Orphan(Boolean other1Orphan)
	{
		this.other1Orphan = other1Orphan;
	}
	
	/**
	 * @return the other2Orphan
	 */
	public Boolean isOther2Orphan()
	{
		return other2Orphan;
	}
	
	/**
	 * @param other2Orphan
	 *            the other2Orphan to set
	 */
	public void setOther2Orphan(Boolean other2Orphan)
	{
		this.other2Orphan = other2Orphan;
	}
	
	/**
	 * @return the execOrphanInter
	 */
	public Boolean isExecOrphanInter()
	{
		return execOrphanInter;
	}
	
	/**
	 * @param execOrphanInter
	 *            the execOrphanInter to set
	 */
	public void setExecOrphanInter(Boolean execOrphanInter)
	{
		this.execOrphanInter = execOrphanInter;
	}
	
	/**
	 * @return the recruiterOrphanInter
	 */
	public Boolean isRecruiterOrphanInter()
	{
		return recruiterOrphanInter;
	}
	
	/**
	 * @param recruiterOrphanInter
	 *            the recruiterOrphanInter to set
	 */
	public void setRecruiterOrphanInter(Boolean recruiterOrphanInter)
	{
		this.recruiterOrphanInter = recruiterOrphanInter;
	}
	
	/**
	 * @return the other1OrphanInter
	 */
	public Boolean isOther1OrphanInter()
	{
		return other1OrphanInter;
	}
	
	/**
	 * @param other1OrphanInter
	 *            the other1OrphanInter to set
	 */
	public void setOther1OrphanInter(Boolean other1OrphanInter)
	{
		this.other1OrphanInter = other1OrphanInter;
	}
	
	/**
	 * @return the other2OrphanInter
	 */
	public Boolean isOther2OrphanInter()
	{
		return other2OrphanInter;
	}
	
	/**
	 * @param other2OrphanInter
	 *            the other2OrphanInter to set
	 */
	public void setOther2OrphanInter(Boolean other2OrphanInter)
	{
		this.other2OrphanInter = other2OrphanInter;
	}
	
	/**
	 * @return the currency
	 */
	public String getCurrency()
	{
		return currency;
	}
	
	/**
	 * @param currency
	 *            the currency to set
	 */
	public void setCurrency(String currency)
	{
		this.currency = currency;
	}
	
	/**
	 * @return the temps
	 */
	public String getTemps()
	{
		return temps;
	}
	
	/**
	 * @param temps
	 *            the temps to set
	 */
	public void setTemps(String temps)
	{
		this.temps = temps;
	}
	
	/**
	 * @return the annualPay
	 */
	public String getAnnualPay()
	{
		return annualPay;
	}
	
	/**
	 * @param annualPay
	 *            the annualPay to set
	 */
	public void setAnnualPay(String annualPay)
	{
		this.annualPay = annualPay;
	}
	
	/**
	 * @return the contractVehicle
	 */
	public String getContractVehicle()
	{
		return contractVehicle;
	}
	
	/**
	 * @param contractVehicle
	 *            the contractVehicle to set
	 */
	public void setContractVehicle(String contractVehicle)
	{
		this.contractVehicle = contractVehicle;
	}
	
	/**
	 * @return the jobPayRate
	 */
	public String getJobPayRate()
	{
		return jobPayRate;
	}
	
	/**
	 * @param jobPayRate
	 *            the jobPayRate to set
	 */
	public void setJobPayRate(String jobPayRate)
	{
		this.jobPayRate = jobPayRate;
	}
	
	/**
	 * @return the jobBillRate
	 */
	public String getJobBillRate()
	{
		return jobBillRate;
	}
	
	/**
	 * @param jobBillRate
	 *            the jobBillRate to set
	 */
	public void setJobBillRate(String jobBillRate)
	{
		this.jobBillRate = jobBillRate;
	}
	
	/**
	 * @return the DoubleTime
	 */
	public String getDoubleTime()
	{
		return DoubleTime;
	}
	
	/**
	 * @param DoubleTime
	 *            the DoubleTime to set
	 */
	public void setDoubleTime(String DoubleTime)
	{
		this.DoubleTime = DoubleTime;
	}
	
	/**
	 * @return the DoubleTimeBill
	 */
	public String getDoubleTimeBill()
	{
		return DoubleTimeBill;
	}
	
	/**
	 * @param DoubleTimeBill
	 *            the DoubleTimeBill to set
	 */
	public void setDoubleTimeBill(String DoubleTimeBill)
	{
		this.DoubleTimeBill = DoubleTimeBill;
	}
	
	/**
	 * @return the dtb
	 */
	public String getDtb()
	{
		return dtb;
	}
	
	/**
	 * @param dtb
	 *            the dtb to set
	 */
	public void setDtb(String dtb)
	{
		this.dtb = dtb;
	}
	
	/**
	 * @return the dtp
	 */
	public String getDtp()
	{
		return dtp;
	}
	
	/**
	 * @param dtp
	 *            the dtp to set
	 */
	public void setDtp(String dtp)
	{
		this.dtp = dtp;
	}
	
	/**
	 * @return the perdiem
	 */
	public String getPerdiem()
	{
		return perdiem;
	}
	
	/**
	 * @param perdiem
	 *            the perdiem to set
	 */
	public void setPerdiem(String perdiem)
	{
		this.perdiem = perdiem;
	}
	
	/**
	 * @return the placement
	 */
	public Boolean isPlacement()
	{
		return placement;
	}
	
	/**
	 * @param placement
	 *            the placement to set
	 */
	public void setPlacement(Boolean placement)
	{
		this.placement = placement;
	}
	
	/**
	 * @return the placementAmount
	 */
	public String getPlacementAmount()
	{
		return placementAmount;
	}
	
	/**
	 * @param placementAmount
	 *            the placementAmount to set
	 */
	public void setPlacementAmount(String placementAmount)
	{
		this.placementAmount = placementAmount;
	}
	
	/**
	 * @return the placementDate
	 */
	public String getPlacementDate()
	{
		return placementDate;
	}
	
	/**
	 * @param placementDate
	 *            the placementDate to set
	 */
	public void setPlacementDate(String placementDate)
	{
		this.placementDate = placementDate;
	}
	
	/**
	 * @return the salaryAmount
	 */
	public String getSalaryAmount()
	{
		return salaryAmount;
	}
	
	/**
	 * @param salaryAmount
	 *            the salaryAmount to set
	 */
	public void setSalaryAmount(String salaryAmount)
	{
		this.salaryAmount = salaryAmount;
	}
	
	/**
	 * @return the placementPercentage
	 */
	public String getPlacementPercentage()
	{
		return placementPercentage;
	}
	
	/**
	 * @param placementPercentage
	 *            the placementPercentage to set
	 */
	public void setPlacementPercentage(String placementPercentage)
	{
		this.placementPercentage = placementPercentage;
	}
	
	/**
	 * @return the workEmail
	 */
	public String getWorkEmail()
	{
		return workEmail;
	}
	
	/**
	 * @param workEmail
	 *            the workEmail to set
	 */
	public void setWorkEmail(String workEmail)
	{
		this.workEmail = workEmail;
	}
	
	/**
	 * @return the chkReferralFee
	 */
	public String getChkReferralFee()
	{
		return chkReferralFee;
	}
	
	/**
	 * @param chkReferralFee
	 *            the chkReferralFee to set
	 */
	public void setChkReferralFee(String chkReferralFee)
	{
		this.chkReferralFee = chkReferralFee;
	}
	
	/**
	 * @return the referralFeeAmount
	 */
	public String getReferralFeeAmount()
	{
		return referralFeeAmount;
	}
	
	/**
	 * @param referralFeeAmount
	 *            the referralFeeAmount to set
	 */
	public void setReferralFeeAmount(String referralFeeAmount)
	{
		this.referralFeeAmount = referralFeeAmount;
	}
	
	/**
	 * @return the volumeDiscount
	 */
	public Double getVolumeDiscount()
	{
		return volumeDiscount;
	}
	
	/**
	 * @param volumeDiscount
	 *            the volumeDiscount to set
	 */
	public void setVolumeDiscount(Double volumeDiscount)
	{
		this.volumeDiscount = volumeDiscount;
	}
	
	/**
	 * @return the paymentDiscount
	 */
	public Double getPaymentDiscount()
	{
		return paymentDiscount;
	}
	
	/**
	 * @param paymentDiscount
	 *            the paymentDiscount to set
	 */
	public void setPaymentDiscount(Double paymentDiscount)
	{
		this.paymentDiscount = paymentDiscount;
	}
	
	/**
	 * @return the otherDiscount
	 */
	public Double getOtherDiscount()
	{
		return otherDiscount;
	}
	
	/**
	 * @param otherDiscount
	 *            the otherDiscount to set
	 */
	public void setOtherDiscount(Double otherDiscount)
	{
		this.otherDiscount = otherDiscount;
	}
	
	/**
	 * @return the backgroundAmount
	 */
	public Double getBackgroundAmount()
	{
		return backgroundAmount;
	}
	
	/**
	 * @param backgroundAmount
	 *            the backgroundAmount to set
	 */
	public void setBackgroundAmount(Double backgroundAmount)
	{
		this.backgroundAmount = backgroundAmount;
	}
	
	/**
	 * @return the drugTestAmount
	 */
	public Double getDrugTestAmount()
	{
		return drugTestAmount;
	}
	
	/**
	 * @param drugTestAmount
	 *            the drugTestAmount to set
	 */
	public void setDrugTestAmount(Double drugTestAmount)
	{
		this.drugTestAmount = drugTestAmount;
	}
	
	/**
	 * @return the creditCheckAmount
	 */
	public Double getCreditCheckAmount()
	{
		return creditCheckAmount;
	}
	
	/**
	 * @param creditCheckAmount
	 *            the creditCheckAmount to set
	 */
	public void setCreditCheckAmount(Double creditCheckAmount)
	{
		this.creditCheckAmount = creditCheckAmount;
	}
	
	/**
	 * @return the backgroundCheckAmount
	 */
	public Double getBackgroundCheckAmount()
	{
		return backgroundCheckAmount;
	}
	
	/**
	 * @param backgroundCheckAmount
	 *            the backgroundCheckAmount to set
	 */
	public void setBackgroundCheckAmount(Double backgroundCheckAmount)
	{
		this.backgroundCheckAmount = backgroundCheckAmount;
	}
	
	/**
	 * @return the clientEmail
	 */
	public String getClientEmail()
	{
		return clientEmail;
	}
	
	/**
	 * @param clientEmail
	 *            the clientEmail to set
	 */
	public void setClientEmail(String clientEmail)
	{
		this.clientEmail = clientEmail;
	}
	
	/**
	 * @return the managerEmail
	 */
	public String getManagerEmail()
	{
		return managerEmail;
	}
	
	/**
	 * @param managerEmail
	 *            the managerEmail to set
	 */
	public void setManagerEmail(String managerEmail)
	{
		this.managerEmail = managerEmail;
	}
	
	/**
	 * @return the unEmployedForTwoMonths
	 */
	public String getUnEmployedForTwoMonths()
	{
		return unEmployedForTwoMonths;
	}
	
	/**
	 * @param unEmployedForTwoMonths
	 *            the unEmployedForTwoMonths to set
	 */
	public void setUnEmployedForTwoMonths(String unEmployedForTwoMonths)
	{
		this.unEmployedForTwoMonths = unEmployedForTwoMonths;
	}
	
	/**
	 * @return the dentalInsurance
	 */
	public String getDentalInsurance()
	{
		return dentalInsurance;
	}
	
	/**
	 * @param dentalInsurance
	 *            the dentalInsurance to set
	 */
	public void setDentalInsurance(String dentalInsurance)
	{
		this.dentalInsurance = dentalInsurance;
	}
	
	/**
	 * @return the stdBenefit
	 */
	public String getStdBenefit()
	{
		return stdBenefit;
	}
	
	/**
	 * @param stdBenefit
	 *            the stdBenefit to set
	 */
	public void setStdBenefit(String stdBenefit)
	{
		this.stdBenefit = stdBenefit;
	}
	
	/**
	 * @return the ltdBenefit
	 */
	public String getLtdBenefit()
	{
		return ltdBenefit;
	}
	
	/**
	 * @param ltdBenefit
	 *            the ltdBenefit to set
	 */
	public void setLtdBenefit(String ltdBenefit)
	{
		this.ltdBenefit = ltdBenefit;
	}
	
	/**
	 * @return the consultantJobBoard
	 */
	public String getConsultantJobBoard()
	{
		return consultantJobBoard;
	}
	
	/**
	 * @param consultantJobBoard
	 *            the consultantJobBoard to set
	 */
	public void setConsultantJobBoard(String consultantJobBoard)
	{
		this.consultantJobBoard = consultantJobBoard;
	}
	
	/**
	 * @return the dentalInsuranceAmount
	 */
	public Double getDentalInsuranceAmount()
	{
		return dentalInsuranceAmount;
	}
	
	/**
	 * @param dentalInsuranceAmount
	 *            the dentalInsuranceAmount to set
	 */
	public void setDentalInsuranceAmount(Double dentalInsuranceAmount)
	{
		this.dentalInsuranceAmount = dentalInsuranceAmount;
	}
	
	/**
	 * @return the paymentTerms
	 */
	public String getPaymentTerms()
	{
		return paymentTerms;
	}
	
	/**
	 * @param paymentTerms
	 *            the paymentTerms to set
	 */
	public void setPaymentTerms(String paymentTerms)
	{
		this.paymentTerms = paymentTerms;
	}
	
	/**
	 * @return the attention
	 */
	public String getAttention()
	{
		return attention;
	}
	
	/**
	 * @param attention
	 *            the attention to set
	 */
	public void setAttention(String attention)
	{
		this.attention = attention;
	}
	
	/**
	 * @return the distributionMethod
	 */
	public String getDistributionMethod()
	{
		return distributionMethod;
	}
	
	/**
	 * @param distributionMethod
	 *            the distributionMethod to set
	 */
	public void setDistributionMethod(String distributionMethod)
	{
		this.distributionMethod = distributionMethod;
	}
	
	/**
	 * @return the specialNotes
	 */
	public String getSpecialNotes()
	{
		return specialNotes;
	}
	
	/**
	 * @param specialNotes
	 *            the specialNotes to set
	 */
	public void setSpecialNotes(String specialNotes)
	{
		this.specialNotes = specialNotes;
	}
	
	/**
	 * @return the invoiceFrequency
	 */
	public String getInvoiceFrequency()
	{
		return invoiceFrequency;
	}
	
	/**
	 * @param invoiceFrequency
	 *            the invoiceFrequency to set
	 */
	public void setInvoiceFrequency(String invoiceFrequency)
	{
		this.invoiceFrequency = invoiceFrequency;
	}
	
	/**
	 * @return the employeeVeteran
	 */
	public String getEmployeeVeteran()
	{
		return employeeVeteran;
	}
	
	/**
	 * @param employeeVeteran
	 *            the employeeVeteran to set
	 */
	public void setEmployeeVeteran(String employeeVeteran)
	{
		this.employeeVeteran = employeeVeteran;
	}
	
	/**
	 * @return the gender
	 */
	public String getGender()
	{
		return gender;
	}
	
	/**
	 * @param gender
	 *            the gender to set
	 */
	public void setGender(String gender)
	{
		this.gender = gender;
	}
	
	/**
	 * @return the workSiteState
	 */
	public String getWorkSiteState()
	{
		return workSiteState;
	}
	
	/**
	 * @param workSiteState
	 *            the workSiteState to set
	 */
	public void setWorkSiteState(String workSiteState)
	{
		this.workSiteState = workSiteState;
	}
	
	/**
	 * @return the analyzerCategory1
	 */
	public String getAnalyzerCategory1()
	{
		return analyzerCategory1;
	}
	
	/**
	 * @param analyzerCategory1
	 *            the analyzerCategory1 to set
	 */
	public void setAnalyzerCategory1(String analyzerCategory1)
	{
		this.analyzerCategory1 = analyzerCategory1;
	}
	
	/**
	 * @return the analyzerCategory2
	 */
	public String getAnalyzerCategory2()
	{
		return analyzerCategory2;
	}
	
	/**
	 * @param analyzerCategory2
	 *            the analyzerCategory2 to set
	 */
	public void setAnalyzerCategory2(String analyzerCategory2)
	{
		this.analyzerCategory2 = analyzerCategory2;
	}
	
	/**
	 * @return the flsaStatus
	 */
	public String getFlsaStatus()
	{
		return flsaStatus;
	}
	
	/**
	 * @param flsaStatus
	 *            the flsaStatus to set
	 */
	public void setFlsaStatus(String flsaStatus)
	{
		this.flsaStatus = flsaStatus;
	}
	
	/**
	 * @return the custom1
	 */
	public String getCustom1()
	{
		return custom1;
	}
	
	/**
	 * @param custom1
	 *            the custom1 to set
	 */
	public void setCustom1(String custom1)
	{
		this.custom1 = custom1;
	}
	
	/**
	 * @return the billingType
	 */
	public String getBillingType()
	{
		return billingType;
	}
	
	/**
	 * @param billingType
	 *            the billingType to set
	 */
	public void setBillingType(String billingType)
	{
		this.billingType = billingType;
	}
	
	/**
	 * @return the deliveryType
	 */
	public String getDeliveryType()
	{
		return deliveryType;
	}
	
	/**
	 * @param deliveryType
	 *            the deliveryType to set
	 */
	public void setDeliveryType(String deliveryType)
	{
		this.deliveryType = deliveryType;
	}
	
	/**
	 * @return the practiceArea
	 */
	public String getPracticeArea()
	{
		return practiceArea;
	}
	
	/**
	 * @param practiceArea
	 *            the practiceArea to set
	 */
	public void setPracticeArea(String practiceArea)
	{
		this.practiceArea = practiceArea;
	}
	
	/**
	 * @return the dealType
	 */
	public String getDealType()
	{
		return dealType;
	}
	
	/**
	 * @param dealType
	 *            the dealType to set
	 */
	public void setDealType(String dealType)
	{
		this.dealType = dealType;
	}
	
	/**
	 * @return the comm
	 */
	public String getComm()
	{
		return comm;
	}
	
	/**
	 * @param comm
	 *            the comm to set
	 */
	public void setComm(String comm)
	{
		this.comm = comm;
	}
	
	/**
	 * @return the custom2
	 */
	public String getCustom2()
	{
		return custom2;
	}
	
	/**
	 * @param custom2
	 *            the custom2 to set
	 */
	public void setCustom2(String custom2)
	{
		this.custom2 = custom2;
	}
	
	/**
	 * @return the com
	 */
	public Boolean isCom()
	{
		return com;
	}
	
	/**
	 * @param com
	 *            the com to set
	 */
	public void setCom(Boolean com)
	{
		this.com = com;
	}
	
	/**
	 * @return the isBonusEligible
	 */
	public String getIsBonusEligible()
	{
		return isBonusEligible;
	}
	
	/**
	 * @param isBonusEligible
	 *            the isBonusEligible to set
	 */
	public void setIsBonusEligible(String isBonusEligible)
	{
		this.isBonusEligible = isBonusEligible;
	}
	
	/**
	 * @return the bonusAmount
	 */
	public Double getBonusAmount()
	{
		return bonusAmount;
	}
	
	/**
	 * @param bonusAmount
	 *            the bonusAmount to set
	 */
	public void setBonusAmount(Double bonusAmount)
	{
		this.bonusAmount = bonusAmount;
	}
	
	/**
	 * @return the bonusPercentage
	 */
	public String getBonusPercentage()
	{
		return bonusPercentage;
	}
	
	/**
	 * @param bonusPercentage
	 *            the bonusPercentage to set
	 */
	public void setBonusPercentage(String bonusPercentage)
	{
		this.bonusPercentage = bonusPercentage;
	}
	
	/**
	 * @return the permGAAmount
	 */
	public Double getPermGAAmount()
	{
		return permGAAmount;
	}
	
	/**
	 * @param permGAAmount
	 *            the permGAAmount to set
	 */
	public void setPermGAAmount(Double permGAAmount)
	{
		this.permGAAmount = permGAAmount;
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
	 * @return the skillCategory
	 */
	public String getSkillCategory()
	{
		return skillCategory;
	}
	
	/**
	 * @param skillCategory
	 *            the skillCategory to set
	 */
	public void setSkillCategory(String skillCategory)
	{
		this.skillCategory = skillCategory;
	}
	
	/**
	 * @return the vertical
	 */
	public String getVertical()
	{
		return vertical;
	}
	
	/**
	 * @param vertical
	 *            the vertical to set
	 */
	public void setVertical(String vertical)
	{
		this.vertical = vertical;
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
	 * @return the verticalTimesheetType
	 */
	public String getVerticalTimesheetType()
	{
		return verticalTimesheetType;
	}
	
	/**
	 * @param verticalTimesheetType
	 *            the verticalTimesheetType to set
	 */
	public void setVerticalTimesheetType(String verticalTimesheetType)
	{
		this.verticalTimesheetType = verticalTimesheetType;
	}
	
	/**
	 * @return the immigrationCost
	 */
	public Double getImmigrationCost()
	{
		return immigrationCost;
	}
	
	/**
	 * @param immigrationCost
	 *            the immigrationCost to set
	 */
	public void setImmigrationCost(Double immigrationCost)
	{
		this.immigrationCost = immigrationCost;
	}
	
	/**
	 * @return the equipmentCost
	 */
	public Double getEquipmentCost()
	{
		return equipmentCost;
	}
	
	/**
	 * @param equipmentCost
	 *            the equipmentCost to set
	 */
	public void setEquipmentCost(Double equipmentCost)
	{
		this.equipmentCost = equipmentCost;
	}
	
	/**
	 * @return the product
	 */
	public String getProduct()
	{
		return product;
	}
	
	/**
	 * @param product
	 *            the product to set
	 */
	public void setProduct(String product)
	{
		this.product = product;
	}
	
	/**
	 * @return the nonBillableCost
	 */
	public Double getNonBillableCost()
	{
		return nonBillableCost;
	}
	
	/**
	 * @param nonBillableCost
	 *            the nonBillableCost to set
	 */
	public void setNonBillableCost(Double nonBillableCost)
	{
		this.nonBillableCost = nonBillableCost;
	}
	
	/**
	 * @return the travelRequired
	 */
	public String getTravelRequired()
	{
		return travelRequired;
	}
	
	/**
	 * @param travelRequired
	 *            the travelRequired to set
	 */
	public void setTravelRequired(String travelRequired)
	{
		this.travelRequired = travelRequired;
	}
	
	/**
	 * @return the commissionModel1
	 */
	public String getCommissionModel1()
	{
		return commissionModel1;
	}
	
	/**
	 * @param commissionModel1
	 *            the commissionModel1 to set
	 */
	public void setCommissionModel1(String commissionModel1)
	{
		this.commissionModel1 = commissionModel1;
	}
	
	/**
	 * @return the commissionModel2
	 */
	public String getCommissionModel2()
	{
		return commissionModel2;
	}
	
	/**
	 * @param commissionModel2
	 *            the commissionModel2 to set
	 */
	public void setCommissionModel2(String commissionModel2)
	{
		this.commissionModel2 = commissionModel2;
	}
	
	/**
	 * @return the commissionModel3
	 */
	public String getCommissionModel3()
	{
		return commissionModel3;
	}
	
	/**
	 * @param commissionModel3
	 *            the commissionModel3 to set
	 */
	public void setCommissionModel3(String commissionModel3)
	{
		this.commissionModel3 = commissionModel3;
	}
	
	/**
	 * @return the commissionModel4
	 */
	public String getCommissionModel4()
	{
		return commissionModel4;
	}
	
	/**
	 * @param commissionModel4
	 *            the commissionModel4 to set
	 */
	public void setCommissionModel4(String commissionModel4)
	{
		this.commissionModel4 = commissionModel4;
	}
	
	/**
	 * @return the recruitingClassification
	 */
	public String getRecruitingClassification()
	{
		return recruitingClassification;
	}
	
	/**
	 * @param recruitingClassification
	 *            the recruitingClassification to set
	 */
	public void setRecruitingClassification(String recruitingClassification)
	{
		this.recruitingClassification = recruitingClassification;
	}
	
	/**
	 * @return the flsaReference
	 */
	public String getFlsaReference()
	{
		return flsaReference;
	}
	
	/**
	 * @param flsaReference
	 *            the flsaReference to set
	 */
	public void setFlsaReference(String flsaReference)
	{
		this.flsaReference = flsaReference;
	}
	
	/**
	 * @return the references
	 */
	public Boolean getReferences()
	{
		return references;
	}
	
	/**
	 * @return the h1
	 */
	public Boolean getH1()
	{
		return h1;
	}
	
	/**
	 * @return the execOrphan
	 */
	public Boolean getExecOrphan()
	{
		return execOrphan;
	}
	
	/**
	 * @return the recruiterOrphan
	 */
	public Boolean getRecruiterOrphan()
	{
		return recruiterOrphan;
	}
	
	/**
	 * @return the other1Orphan
	 */
	public Boolean getOther1Orphan()
	{
		return other1Orphan;
	}
	
	/**
	 * @return the other2Orphan
	 */
	public Boolean getOther2Orphan()
	{
		return other2Orphan;
	}
	
	/**
	 * @return the execOrphanInter
	 */
	public Boolean getExecOrphanInter()
	{
		return execOrphanInter;
	}
	
	/**
	 * @return the recruiterOrphanInter
	 */
	public Boolean getRecruiterOrphanInter()
	{
		return recruiterOrphanInter;
	}
	
	/**
	 * @return the other1OrphanInter
	 */
	public Boolean getOther1OrphanInter()
	{
		return other1OrphanInter;
	}
	
	/**
	 * @return the other2OrphanInter
	 */
	public Boolean getOther2OrphanInter()
	{
		return other2OrphanInter;
	}
	
	/**
	 * @return the placement
	 */
	public Boolean getPlacement()
	{
		return placement;
	}
	
	/**
	 * @return the com
	 */
	public Boolean getCom()
	{
		return com;
	}
	
	/**
	 * @return the lName
	 */
	public String getlName()
	{
		return lName;
	}
	
	/**
	 * @param lName
	 *            the lName to set
	 */
	public void setlName(String lName)
	{
		this.lName = lName;
	}
	
	/**
	 * @return the fName
	 */
	public String getfName()
	{
		return fName;
	}
	
	/**
	 * @param fName
	 *            the fName to set
	 */
	public void setfName(String fName)
	{
		this.fName = fName;
	}
	
	/**
	 * @return the totHoursWorked
	 */
	public Double getTotHoursWorked()
	{
		return totHoursWorked;
	}
	
	/**
	 * @param totHoursWorked
	 *            the totHoursWorked to set
	 */
	public void setTotHoursWorked(Double totHoursWorked)
	{
		this.totHoursWorked = totHoursWorked;
	}
	
	/**
	 * @return the billRate
	 */
	public Double getBillRate()
	{
		return billRate;
	}
	
	/**
	 * @param billRate
	 *            the billRate to set
	 */
	public void setBillRate(Double billRate)
	{
		this.billRate = billRate;
	}
	
	/**
	 * @return the payRate
	 */
	public Double getPayRate()
	{
		return payRate;
	}
	
	/**
	 * @param payRate
	 *            the payRate to set
	 */
	public void setPayRate(Double payRate)
	{
		this.payRate = payRate;
	}
	
	/**
	 * @return the ga
	 */
	public Double getGa()
	{
		return ga;
	}
	
	/**
	 * @param ga
	 *            the ga to set
	 */
	public void setGa(Double ga)
	{
		this.ga = ga;
	}
	
	/**
	 * @return the commission
	 */
	public Double getCommission()
	{
		return commission;
	}
	
	/**
	 * @param commission
	 *            the commission to set
	 */
	public void setCommission(Double commission)
	{
		this.commission = commission;
	}
	
	/**
	 * @return the profit
	 */
	public Double getProfit()
	{
		return profit;
	}
	
	/**
	 * @param profit
	 *            the profit to set
	 */
	public void setProfit(Double profit)
	{
		this.profit = profit;
	}
	
	/**
	 * @return the grossProfit
	 */
	public Double getGrossProfit()
	{
		return grossProfit;
	}
	
	/**
	 * @param grossProfit
	 *            the grossProfit to set
	 */
	public void setGrossProfit(Double grossProfit)
	{
		this.grossProfit = grossProfit;
	}
	
	/**
	 * @return the parentId
	 */
	public String getParentId()
	{
		return parentId;
	}
	
	/**
	 * @param parentId
	 *            the parentId to set
	 */
	public void setParentId(String parentId)
	{
		this.parentId = parentId;
	}
	
	/**
	 * @return the commPer1
	 */
	public Double getCommPer1()
	{
		return commPer1;
	}
	
	/**
	 * @param commPer1
	 *            the commPer1 to set
	 */
	public void setCommPer1(Double commPer1)
	{
		this.commPer1 = commPer1;
	}
	
	/**
	 * @return the commPer2
	 */
	public Double getCommPer2()
	{
		return commPer2;
	}
	
	/**
	 * @param commPer2
	 *            the commPer2 to set
	 */
	public void setCommPer2(Double commPer2)
	{
		this.commPer2 = commPer2;
	}
	
	/**
	 * @return the commPer3
	 */
	public Double getCommPer3()
	{
		return commPer3;
	}
	
	/**
	 * @param commPer3
	 *            the commPer3 to set
	 */
	public void setCommPer3(Double commPer3)
	{
		this.commPer3 = commPer3;
	}
	
	/**
	 * @return the commPer4
	 */
	public Double getCommPer4()
	{
		return commPer4;
	}
	
	/**
	 * @param commPer4
	 *            the commPer4 to set
	 */
	public void setCommPer4(Double commPer4)
	{
		this.commPer4 = commPer4;
	}
	
	/**
	 * @return the clientCompleteAddress
	 */
	public String getClientCompleteAddress()
	{
		return clientCompleteAddress;
	}
	
	/**
	 * @param clientCompleteAddress
	 *            the clientCompleteAddress to set
	 */
	public void setClientCompleteAddress(String clientCompleteAddress)
	{
		this.clientCompleteAddress = clientCompleteAddress;
	}
	
	/**
	 * @return the newParentId
	 */
	public String getNewParentId()
	{
		return newParentId;
	}
	
	/**
	 * @param newParentId
	 *            the newParentId to set
	 */
	public void setNewParentId(String newParentId)
	{
		this.newParentId = newParentId;
	}
	
	/**
	 * @return the submissionDate
	 */
	public String getSubmissionDate()
	{
		return submissionDate;
	}
	
	/**
	 * @param submissionDate
	 *            the submissionDate to set
	 */
	public void setSubmissionDate(String submissionDate)
	{
		this.submissionDate = submissionDate;
	}
	
	/**
	 * @return the modifiedBy
	 */
	public String getModifiedBy()
	{
		return modifiedBy;
	}
	
	/**
	 * @param modifiedBy
	 *            the modifiedBy to set
	 */
	public void setModifiedBy(String modifiedBy)
	{
		this.modifiedBy = modifiedBy;
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
	 * @return the approvalDate
	 */
	public Timestamp getApprovalDate()
	{
		return approvalDate;
	}
	
	/**
	 * @param approvalDate
	 *            the approvalDate to set
	 */
	public void setApprovalDate(Timestamp approvalDate)
	{
		this.approvalDate = approvalDate;
	}
	
	/**
	 * @return the isRehired
	 */
	public String getIsRehired()
	{
		return isRehired;
	}
	
	/**
	 * @param isRehired
	 *            the isRehired to set
	 */
	public void setIsRehired(String isRehired)
	{
		this.isRehired = isRehired;
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
	 * @return the commissionPerson5
	 */
	public String getCommissionPerson5()
	{
		return commissionPerson5;
	}
	
	/**
	 * @param commissionPerson5
	 *            the commissionPerson5 to set
	 */
	public void setCommissionPerson5(String commissionPerson5)
	{
		this.commissionPerson5 = commissionPerson5;
	}
	
	/**
	 * @return the commissionPerson6
	 */
	public String getCommissionPerson6()
	{
		return commissionPerson6;
	}
	
	/**
	 * @param commissionPerson6
	 *            the commissionPerson6 to set
	 */
	public void setCommissionPerson6(String commissionPerson6)
	{
		this.commissionPerson6 = commissionPerson6;
	}
	
	/**
	 * @return the commissionPerson7
	 */
	public String getCommissionPerson7()
	{
		return commissionPerson7;
	}
	
	/**
	 * @param commissionPerson7
	 *            the commissionPerson7 to set
	 */
	public void setCommissionPerson7(String commissionPerson7)
	{
		this.commissionPerson7 = commissionPerson7;
	}
	
	/**
	 * @return the commissionPerson8
	 */
	public String getCommissionPerson8()
	{
		return commissionPerson8;
	}
	
	/**
	 * @param commissionPerson8
	 *            the commissionPerson8 to set
	 */
	public void setCommissionPerson8(String commissionPerson8)
	{
		this.commissionPerson8 = commissionPerson8;
	}
	
	/**
	 * @return the commissionPerson9
	 */
	public String getCommissionPerson9()
	{
		return commissionPerson9;
	}
	
	/**
	 * @param commissionPerson9
	 *            the commissionPerson9 to set
	 */
	public void setCommissionPerson9(String commissionPerson9)
	{
		this.commissionPerson9 = commissionPerson9;
	}
	
	/**
	 * @return the commissionPercentage5
	 */
	public Double getCommissionPercentage5()
	{
		return commissionPercentage5;
	}
	
	/**
	 * @param commissionPercentage5
	 *            the commissionPercentage5 to set
	 */
	public void setCommissionPercentage5(Double commissionPercentage5)
	{
		this.commissionPercentage5 = commissionPercentage5;
	}
	
	/**
	 * @return the commissionPercentage6
	 */
	public Double getCommissionPercentage6()
	{
		return commissionPercentage6;
	}
	
	/**
	 * @param commissionPercentage6
	 *            the commissionPercentage6 to set
	 */
	public void setCommissionPercentage6(Double commissionPercentage6)
	{
		this.commissionPercentage6 = commissionPercentage6;
	}
	
	/**
	 * @return the commissionPercentage7
	 */
	public Double getCommissionPercentage7()
	{
		return commissionPercentage7;
	}
	
	/**
	 * @param commissionPercentage7
	 *            the commissionPercentage7 to set
	 */
	public void setCommissionPercentage7(Double commissionPercentage7)
	{
		this.commissionPercentage7 = commissionPercentage7;
	}
	
	/**
	 * @return the commissionPercentage8
	 */
	public Double getCommissionPercentage8()
	{
		return commissionPercentage8;
	}
	
	/**
	 * @param commissionPercentage8
	 *            the commissionPercentage8 to set
	 */
	public void setCommissionPercentage8(Double commissionPercentage8)
	{
		this.commissionPercentage8 = commissionPercentage8;
	}
	
	/**
	 * @return the commissionPercentage9
	 */
	public Double getCommissionPercentage9()
	{
		return commissionPercentage9;
	}
	
	/**
	 * @param commissionPercentage9
	 *            the commissionPercentage9 to set
	 */
	public void setCommissionPercentage9(Double commissionPercentage9)
	{
		this.commissionPercentage9 = commissionPercentage9;
	}
	
	/**
	 * @return the commission5
	 */
	public Double getCommission5()
	{
		return commission5;
	}
	
	/**
	 * @param commission5
	 *            the commission5 to set
	 */
	public void setCommission5(Double commission5)
	{
		this.commission5 = commission5;
	}
	
	/**
	 * @return the commission6
	 */
	public Double getCommission6()
	{
		return commission6;
	}
	
	/**
	 * @param commission6
	 *            the commission6 to set
	 */
	public void setCommission6(Double commission6)
	{
		this.commission6 = commission6;
	}
	
	/**
	 * @return the commission7
	 */
	public Double getCommission7()
	{
		return commission7;
	}
	
	/**
	 * @param commission7
	 *            the commission7 to set
	 */
	public void setCommission7(Double commission7)
	{
		this.commission7 = commission7;
	}
	
	/**
	 * @return the commission8
	 */
	public Double getCommission8()
	{
		return commission8;
	}
	
	/**
	 * @param commission8
	 *            the commission8 to set
	 */
	public void setCommission8(Double commission8)
	{
		this.commission8 = commission8;
	}
	
	/**
	 * @return the commission9
	 */
	public Double getCommission9()
	{
		return commission9;
	}
	
	/**
	 * @param commission9
	 *            the commission9 to set
	 */
	public void setCommission9(Double commission9)
	{
		this.commission9 = commission9;
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
	 * @return the sickLeaveCap
	 */
	public String getSickLeaveCap()
	{
		return sickLeaveCap;
	}
	
	/**
	 * @param sickLeaveCap
	 *            the sickLeaveCap to set
	 */
	public void setSickLeaveCap(String sickLeaveCap)
	{
		this.sickLeaveCap = sickLeaveCap;
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
	public String getuSPSAddressValidationDate()
	{
		return uSPSAddressValidationDate;
	}
	
	/**
	 * @param uSPSAddressValidationDate
	 *            the uSPSAddressValidationDate to set
	 */
	public void setuSPSAddressValidationDate(String uSPSAddressValidationDate)
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
	
	public Double getTotalHolidays()
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
	
	public Boolean getExecOrphanHdnVal()
	{
		return execOrphanHdnVal;
	}
	
	public void setExecOrphanHdnVal(Boolean execOrphanHdnVal)
	{
		this.execOrphanHdnVal = execOrphanHdnVal;
	}
	
	public Boolean getRecruiterOrphanHdnVal()
	{
		return recruiterOrphanHdnVal;
	}
	
	public void setRecruiterOrphanHdnVal(Boolean recruiterOrphanHdnVal)
	{
		this.recruiterOrphanHdnVal = recruiterOrphanHdnVal;
	}
	
	public Boolean getOther1OrphanHdnVal()
	{
		return other1OrphanHdnVal;
	}
	
	public void setOther1OrphanHdnVal(Boolean other1OrphanHdnVal)
	{
		this.other1OrphanHdnVal = other1OrphanHdnVal;
	}
	
	public Boolean getOther2OrphanHdnVal()
	{
		return other2OrphanHdnVal;
	}
	
	public void setOther2OrphanHdnVal(Boolean other2OrphanHdnVal)
	{
		this.other2OrphanHdnVal = other2OrphanHdnVal;
	}
	
	/**
	 * @return the pTOLimitToOverrideSick
	 */
	public Double getpTOLimitToOverrideSick()
	{
		return pTOLimitToOverrideSick;
	}
	
	/**
	 * @param pTOLimitToOverrideSick
	 *            the pTOLimitToOverrideSick to set
	 */
	public void setpTOLimitToOverrideSick(Double pTOLimitToOverrideSick)
	{
		this.pTOLimitToOverrideSick = pTOLimitToOverrideSick;
	}
	
	/**
	 * @return the distanceFromWorksite
	 */
	public String getDistanceFromWorksite()
	{
		return distanceFromWorksite;
	}
	
	/**
	 * @param distanceFromWorksite
	 *            the distanceFromWorksite to set
	 */
	public void setDistanceFromWorksite(String distanceFromWorksite)
	{
		this.distanceFromWorksite = distanceFromWorksite;
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
	 * @return the worksiteSelectionMessage
	 */
	public String getWorksiteSelectionMessage() {
		return worksiteSelectionMessage;
	}

	/**
	 * @param worksiteSelectionMessage the worksiteSelectionMessage to set
	 */
	public void setWorksiteSelectionMessage(String worksiteSelectionMessage) {
		this.worksiteSelectionMessage = worksiteSelectionMessage;
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
	public Long getBullhornBatchDataProcessedId() {
		return bullhornBatchDataProcessedId;
	}
	/**
	 * @param bullhornBatchDataProcessedId the bullhornBatchDataProcessedId to set
	 */
	public void setBullhornBatchDataProcessedId(Long bullhornBatchDataProcessedId) {
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
	public Long getBullhornBatchAnalyzerStagingId() {
		return bullhornBatchAnalyzerStagingId;
	}
	/**
	 * @param bullhornBatchAnalyzerStagingId the bullhornBatchAnalyzerStagingId to set
	 */
	public void setBullhornBatchAnalyzerStagingId(Long bullhornBatchAnalyzerStagingId) {
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
	 * @return the vendorCompanyCode
	 */
	public String getVendorCompanyCode() {
		return vendorCompanyCode;
	}
	/**
	 * @param vendorCompanyCode the vendorCompanyCode to set
	 */
	public void setVendorCompanyCode(String vendorCompanyCode) {
		this.vendorCompanyCode = vendorCompanyCode;
	}
	/**
	 * @return the clientCompanyCode
	 */
	public String getClientCompanyCode() {
		return clientCompanyCode;
	}
	/**
	 * @param clientCompanyCode the clientCompanyCode to set
	 */
	public void setClientCompanyCode(String clientCompanyCode) {
		this.clientCompanyCode = clientCompanyCode;
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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "AnalyserDTO [analyserId=" + analyserId + ", orgId=" + orgId + ", totHoursWorked=" + totHoursWorked + ", benefitsAmount=" + benefitsAmount + ", percent401k=" + percent401k
				+ ", education=" + education + ", leave=" + leave + ", od=" + od + ", otherAmounts=" + otherAmounts + ", discount=" + discount + ", billRate=" + billRate + ", health=" + health
				+ ", k401=" + k401 + ", tax=" + tax + ", payRate=" + payRate + ", ga=" + ga + ", commission=" + commission + ", spreadWeek=" + spreadWeek + ", otb=" + otb + ", oneTimeAmount="
				+ oneTimeAmount + ", lName=" + lName + ", fName=" + fName + ", address1=" + address1 + ", address2=" + address2 + ", city=" + city + ", state=" + state + ", zip=" + zip
				+ ", telephone=" + telephone + ", workPhone=" + workPhone + ", fax=" + fax + ", email=" + email + ", ssn=" + ssn + ", dob=" + dob + ", jobTitle=" + jobTitle + ", empType=" + empType
				+ ", clientId=" + clientId + ", clientCompany=" + clientCompany + ", clientAddressId=" + clientAddressId + ", clientSiteId=" + clientSiteId + ", clientManagerName="
				+ clientManagerName + ", siteLocation=" + siteLocation + ", invoiceAddress=" + invoiceAddress + ", managerPhone=" + managerPhone + ", startDate=" + startDate + ", endDate=" + endDate
				+ ", submissionDate=" + submissionDate + ", contractorId=" + contractorId + ", contCompanyName=" + contCompanyName + ", contFin=" + contFin + ", contPocName=" + contPocName
				+ ", contPayTerm=" + contPayTerm + ", contEmail=" + contEmail + ", contPhone=" + contPhone + ", contAddress=" + contAddress + ", commName1=" + commName1 + ", commPer1=" + commPer1
				+ ", commName2=" + commName2 + ", commPer2=" + commPer2 + ", commName3=" + commName3 + ", commPer3=" + commPer3 + ", commName4=" + commName4 + ", commPer4=" + commPer4 + ", comments="
				+ comments + ", creatorId=" + creatorId + ", tempSubContId=" + tempSubContId + ", recordStatus=" + recordStatus + ", profit=" + profit + ", discountRate=" + discountRate
				+ ", effectiveDate=" + effectiveDate + ", termDate=" + termDate + ", reason=" + reason + ", branch=" + branch + ", split=" + split + ", rdoPTO=" + rdoPTO + ", mobilePager="
				+ mobilePager + ", initial=" + initial + ", mrmrs=" + mrmrs + ", jrsr=" + jrsr + ", commision1=" + commision1 + ", commision2=" + commision2 + ", commision3=" + commision3
				+ ", commision4=" + commision4 + ", commEffDate=" + commEffDate + ", ponumber=" + ponumber + ", internalAccounting=" + internalAccounting + ", liaisonName=" + liaisonName
				+ ", references=" + references + ", h1=" + h1 + ", country=" + country + ", jobCategory=" + jobCategory + ", branchInter=" + branchInter + ", commentsInter=" + commentsInter
				+ ", commName1Inter=" + commName1Inter + ", commPer1Inter=" + commPer1Inter + ", commName2Inter=" + commName2Inter + ", commPer2Inter=" + commPer2Inter + ", commName3Inter="
				+ commName3Inter + ", commPer3Inter=" + commPer3Inter + ", commName4Inter=" + commName4Inter + ", commPer4Inter=" + commPer4Inter + ", commision1Inter=" + commision1Inter
				+ ", commision2Inter=" + commision2Inter + ", commision3Inter=" + commision3Inter + ", commision4Inter=" + commision4Inter + ", execOrphan=" + execOrphan + ", recruiterOrphan="
				+ recruiterOrphan + ", other1Orphan=" + other1Orphan + ", other2Orphan=" + other2Orphan + ", execOrphanHdnVal=" + execOrphanHdnVal + ", recruiterOrphanHdnVal=" + recruiterOrphanHdnVal
				+ ", other1OrphanHdnVal=" + other1OrphanHdnVal + ", other2OrphanHdnVal=" + other2OrphanHdnVal + ", execOrphanInter=" + execOrphanInter + ", recruiterOrphanInter="
				+ recruiterOrphanInter + ", other1OrphanInter=" + other1OrphanInter + ", other2OrphanInter=" + other2OrphanInter + ", currency=" + currency + ", temps=" + temps + ", annualPay="
				+ annualPay + ", contractVehicle=" + contractVehicle + ", jobPayRate=" + jobPayRate + ", jobBillRate=" + jobBillRate + ", DoubleTime=" + DoubleTime + ", DoubleTimeBill="
				+ DoubleTimeBill + ", dtb=" + dtb + ", dtp=" + dtp + ", perdiem=" + perdiem + ", placement=" + placement + ", placementAmount=" + placementAmount + ", placementDate=" + placementDate
				+ ", salaryAmount=" + salaryAmount + ", placementPercentage=" + placementPercentage + ", workEmail=" + workEmail + ", chkReferralFee=" + chkReferralFee + ", referralFeeAmount="
				+ referralFeeAmount + ", volumeDiscount=" + volumeDiscount + ", paymentDiscount=" + paymentDiscount + ", otherDiscount=" + otherDiscount + ", backgroundAmount=" + backgroundAmount
				+ ", drugTestAmount=" + drugTestAmount + ", creditCheckAmount=" + creditCheckAmount + ", backgroundCheckAmount=" + backgroundCheckAmount + ", clientEmail=" + clientEmail
				+ ", managerEmail=" + managerEmail + ", unEmployedForTwoMonths=" + unEmployedForTwoMonths + ", dentalInsurance=" + dentalInsurance + ", stdBenefit=" + stdBenefit + ", ltdBenefit="
				+ ltdBenefit + ", consultantJobBoard=" + consultantJobBoard + ", dentalInsuranceAmount=" + dentalInsuranceAmount + ", paymentTerms=" + paymentTerms + ", attention=" + attention
				+ ", distributionMethod=" + distributionMethod + ", specialNotes=" + specialNotes + ", invoiceFrequency=" + invoiceFrequency + ", employeeVeteran=" + employeeVeteran + ", gender="
				+ gender + ", workSiteState=" + workSiteState + ", analyzerCategory1=" + analyzerCategory1 + ", analyzerCategory2=" + analyzerCategory2 + ", flsaStatus=" + flsaStatus
				+ ", flsaReference=" + flsaReference + ", custom1=" + custom1 + ", billingType=" + billingType + ", deliveryType=" + deliveryType + ", practiceArea=" + practiceArea + ", parentId="
				+ parentId + ", newParentId=" + newParentId + ", dealType=" + dealType + ", comm=" + comm + ", custom2=" + custom2 + ", com=" + com + ", isBonusEligible=" + isBonusEligible
				+ ", bonusAmount=" + bonusAmount + ", bonusPercentage=" + bonusPercentage + ", permGAAmount=" + permGAAmount + ", splitCommissionPercentage1=" + splitCommissionPercentage1
				+ ", splitCommissionPercentage2=" + splitCommissionPercentage2 + ", splitCommissionPercentage3=" + splitCommissionPercentage3 + ", splitCommissionPercentage4="
				+ splitCommissionPercentage4 + ", skillCategory=" + skillCategory + ", vertical=" + vertical + ", employeeClass=" + employeeClass + ", verticalTimesheetType=" + verticalTimesheetType
				+ ", immigrationCost=" + immigrationCost + ", equipmentCost=" + equipmentCost + ", product=" + product + ", nonBillableCost=" + nonBillableCost + ", travelRequired=" + travelRequired
				+ ", commissionModel1=" + commissionModel1 + ", commissionModel2=" + commissionModel2 + ", commissionModel3=" + commissionModel3 + ", commissionModel4=" + commissionModel4
				+ ", recruitingClassification=" + recruitingClassification + ", grossProfit=" + grossProfit + ", clientCompleteAddress=" + clientCompleteAddress + ", modifiedBy=" + modifiedBy
				+ ", approvedByManager=" + approvedByManager + ", approvalDate=" + approvalDate + ", isRehired=" + isRehired + ", sickLeaveCost=" + sickLeaveCost + ", falseTermination="
				+ falseTermination + ", projectCost=" + projectCost + ", managingDirector=" + managingDirector + ", sickLeavePerHourRate=" + sickLeavePerHourRate + ", sickLeaveCap=" + sickLeaveCap
				+ ", commissionPerson5=" + commissionPerson5 + ", commissionPerson6=" + commissionPerson6 + ", commissionPerson7=" + commissionPerson7 + ", commissionPerson8=" + commissionPerson8
				+ ", commissionPerson9=" + commissionPerson9 + ", commissionPercentage5=" + commissionPercentage5 + ", commissionPercentage6=" + commissionPercentage6 + ", commissionPercentage7="
				+ commissionPercentage7 + ", commissionPercentage8=" + commissionPercentage8 + ", commissionPercentage9=" + commissionPercentage9 + ", commission5=" + commission5 + ", commission6="
				+ commission6 + ", commission7=" + commission7 + ", commission8=" + commission8 + ", commission9=" + commission9 + ", commissionPersonGrade1=" + commissionPersonGrade1
				+ ", commissionPersonGrade2=" + commissionPersonGrade2 + ", commissionPersonGrade3=" + commissionPersonGrade3 + ", commissionPersonGrade4=" + commissionPersonGrade4
				+ ", isAddressUSPSValidated=" + isAddressUSPSValidated + ", uSPSAddressValidationDate=" + uSPSAddressValidationDate + ", billablePTO=" + billablePTO + ", nonBillablePTO="
				+ nonBillablePTO + ", billablePTOCost=" + billablePTOCost + ", nonBillablePTOCost=" + nonBillablePTOCost + ", totalHolidays=" + totalHolidays + ", billableHolidays="
				+ billableHolidays + ", nonBillableHolidays=" + nonBillableHolidays + ", billableHolidaysCost=" + billableHolidaysCost + ", nonBillableHolidaysCost=" + nonBillableHolidaysCost
				+ ", grossProfitPercentage=" + grossProfitPercentage + ", commissionableProfit=" + commissionableProfit + ", rejectionStatus=" + rejectionStatus + ", rejectionReason="
				+ rejectionReason + ", rejectedBy=" + rejectedBy + ", rejectionDate=" + rejectionDate + ", geoOffice=" + geoOffice + ", longitude=" + longitude + ", latitude=" + latitude
				+ ", pTOLimitToOverrideSick=" + pTOLimitToOverrideSick + ", distanceFromWorksite=" + distanceFromWorksite + ", sickLeaveType=" + sickLeaveType 
				+ ", portfolio=" + portfolio + ", portfolioDescription=" + portfolioDescription+ ", companyCode="+companyCode+""
				+ ", dealPortfolio1AE1 = "+dealPortfolio1AE1+", dealPortfolio2REC1 = " +dealPortfolio2REC1+ ", dealPortfolio3AE2 = "+dealPortfolio3AE2+" "
				+ ", dealPortfolio4REC2 = "+dealPortfolio4REC2+", bullhornBatchDataProcessedId = " +bullhornBatchDataProcessedId+", coSellStatus = " +coSellStatus+ ""
				+ ", dataSource = " +dataSource+ ", bullhornBatchCode = " +bullhornBatchCode+ ", bullhornBatchAnalyzerStagingId = " +bullhornBatchAnalyzerStagingId+ ", placementType = " +placementType+ " "
				+ ", bullhornPlacementId = " +bullhornPlacementId+ ", dealActivityAmount = " +dealActivityAmount+  " "
				+ ", bullhornBatchInfoId = " +bullhornBatchInfoId+ ", isModificationRequired = " +isModificationRequired+  " "
				+ ", bullhornTerminationDataProcessedId = " +bullhornTerminationDataProcessedId+ ", bullhornTerminationDataStagingId = " +bullhornTerminationDataStagingId+  " "
				+ ", terminationBullhornBatchInfoId = " +terminationBullhornBatchInfoId+ ", overrideTermination = " +overrideTermination+  " "
				+ ", workFromSource = " +workFromSource+ ", majorityWorkPerformed = " +majorityWorkPerformed+ ", sickLeaveSource = " +sickLeaveSource +"]";
	}
	
	@Override
	public DiffResult diff(AnalyserDTO obj)
	{
		return new DiffBuilder(this, obj, ToStringStyle.SHORT_PREFIX_STYLE)
		.append("Address 1",this.address1,obj.address1 )
		.append("Address 2",this.address2,obj.address2 )
		.append("Analyser Id",this.analyserId,obj.analyserId )
		.append("Analyser Category 1",this.analyzerCategory1,obj.analyzerCategory1 )
		.append("Analyser Category 2",this.analyzerCategory2,obj.analyzerCategory2 )
		.append("Annual Pay",this.annualPay,obj.annualPay )
		.append("Approval Date",this.approvalDate,obj.approvalDate )
		.append("Approved By Manager",this.approvedByManager,obj.approvedByManager )
		.append("Attention",this.attention,obj.attention )
		.append("Background Amount",this.backgroundAmount,obj.backgroundAmount )
		.append("Background Check Amount",this.backgroundCheckAmount,obj.backgroundCheckAmount )
		.append("Benefits Amount",this.benefitsAmount,obj.benefitsAmount )
		.append("Billable Holidays",this.billableHolidays,obj.billableHolidays )
		.append("Billable Holidays Cost",this.billableHolidaysCost,obj.billableHolidaysCost )
		.append("Billable PTO",this.billablePTO,obj.billablePTO )
		.append("Billable PTO Cost",this.billablePTOCost,obj.billablePTOCost )
		.append("Billing Type",this.billingType,obj.billingType )
		.append("Bill Rate",this.billRate,obj.billRate )
		.append("Bonus Amount",this.bonusAmount,obj.bonusAmount )
		.append("Bonus Percentage",this.bonusPercentage,obj.bonusPercentage )
		.append("Branch",this.branch,obj.branch )
		.append("Branch Inter",this.branchInter,obj.branchInter )
		.append("Check Referral Fee",this.chkReferralFee,obj.chkReferralFee )
		.append("City",this.city,obj.city )
		.append("Client Address Id",this.clientAddressId,obj.clientAddressId )
		.append("Client Company",this.clientCompany,obj.clientCompany )
		.append("Client Complete Address",this.clientCompleteAddress,obj.clientCompleteAddress )
		.append("Client Email",this.clientEmail,obj.clientEmail )
		.append("Client Id",this.clientId,obj.clientId )
		.append("Client Manager Name",this.clientManagerName,obj.clientManagerName )
		.append("Client Site Id",this.clientSiteId,obj.clientSiteId )
		.append("Com",this.com,obj.com )
		.append("Comm",this.comm,obj.comm )
		.append("Comm Eff Date",this.commEffDate,obj.commEffDate )
		.append("Comments",this.comments,obj.comments )
		.append("Comments Inter",this.commentsInter,obj.commentsInter )
		.append("Account Exec-1 Amount",this.commision1,obj.commision1 )
		.append("Commision 1 Inter",this.commision1Inter,obj.commision1Inter )
		.append("Recruiter-1 Amount",this.commision2,obj.commision2 )
		.append("Commision 2 Inter",this.commision2Inter,obj.commision2Inter )
		.append("Account Exec-2 Amount",this.commision3,obj.commision3 )
		.append("Commision 3 Inter",this.commision3Inter,obj.commision3Inter )
		.append("Recruiter-2 Amount",this.commision4,obj.commision4 )
		.append("Commision 4 Inter",this.commision4Inter,obj.commision4Inter )
		.append("Commission",this.commission,obj.commission )
		.append("MSP Client Partner Amount",this.commission5,obj.commission5 )
		.append("IT-GS-Practice Amount",this.commission6,obj.commission6 )
		.append("IT-GS-Delivery Amount",this.commission7,obj.commission7 )
		.append("IT-GS-BDE Amount",this.commission8,obj.commission8 )
		.append("IT-GS-Proposal Amount",this.commission9,obj.commission9 )
		.append("Commissionable Profit",this.commissionableProfit,obj.commissionableProfit )
		.append("Commission Model 1",this.commissionModel1,obj.commissionModel1 )
		.append("Commission Model 2",this.commissionModel2,obj.commissionModel2 )
		.append("Commission Model 3",this.commissionModel3,obj.commissionModel3 )
		.append("Commission Model 4",this.commissionModel4,obj.commissionModel4 )
		.append("MSP Client Partner Percentage",this.commissionPercentage5,obj.commissionPercentage5 )
		.append("IT-GS-Practice Percentage",this.commissionPercentage6,obj.commissionPercentage6 )
		.append("IT-GS-Delivery Percentage",this.commissionPercentage7,obj.commissionPercentage7 )
		.append("IT-GS-BDE Percentage",this.commissionPercentage8,obj.commissionPercentage8 )
		.append("IT-GS-Proposal Percentage",this.commissionPercentage9,obj.commissionPercentage9 )
		
		.append("MSP Client Partner",this.commissionPerson5,obj.commissionPerson5 )
		.append("IT-GS-Practice",this.commissionPerson6,obj.commissionPerson6 )
		.append("IT-GS-Delivery",this.commissionPerson7,obj.commissionPerson7 )
		.append("IT-GS-BDE",this.commissionPerson8,obj.commissionPerson8 )
		.append("IT-GS-Proposal",this.commissionPerson9,obj.commissionPerson9 )
		
		.append("Account Exec-1 Grade",this.commissionPersonGrade1,obj.commissionPersonGrade1 )
		.append("Recruiter-1 Grade",this.commissionPersonGrade2,obj.commissionPersonGrade2 )
		.append("Account Exec-2 Grade",this.commissionPersonGrade3,obj.commissionPersonGrade3 )
		.append("Recruiter-2 Grade",this.commissionPersonGrade4,obj.commissionPersonGrade4 )
		
		.append("Account Exec-1",this.commName1,obj.commName1 )
		.append("Comm Name 1 Inter",this.commName1Inter,obj.commName1Inter )
		.append("Recruiter 1",this.commName2,obj.commName2 )
		.append("Comm Name 2 Inter",this.commName2Inter,obj.commName2Inter )
		.append("Account Exec-2",this.commName3,obj.commName3 )
		.append("Comm Name 3 Inter",this.commName3Inter,obj.commName3Inter )
		.append("Recruiter 2",this.commName4,obj.commName4 )
		.append("Comm Name 4 Inter",this.commName4Inter,obj.commName4Inter )
		.append("Account Exec-1 Percentage",this.commPer1,obj.commPer1 )
		.append("Comm Per 1 Inter",this.commPer1Inter,obj.commPer1Inter )
		.append("Recruiter-1 Percentage",this.commPer2,obj.commPer2 )
		.append("Comm Per 2 Inter",this.commPer2Inter,obj.commPer2Inter )
		.append("Account Exec-2 Percentage",this.commPer3,obj.commPer3 )
		.append("Comm Per 3 Inter",this.commPer3Inter,obj.commPer3Inter )
		.append("Recruiter-2 Percentage",this.commPer4,obj.commPer4 )
		.append("Comm Per 4 Inter",this.commPer4Inter,obj.commPer4Inter )
		.append("Consultant Job Board",this.consultantJobBoard,obj.consultantJobBoard )
		.append("Cont Address",this.contAddress,obj.contAddress )
		.append("Cont Company Name",this.contCompanyName,obj.contCompanyName )
		.append("Cont Email",this.contEmail,obj.contEmail )
		.append("Cont Fin",this.contFin,obj.contFin )
		.append("Cont Pay Term",this.contPayTerm,obj.contPayTerm )
		.append("Cont Phone",this.contPhone,obj.contPhone )
		.append("Cont Poc Name",this.contPocName,obj.contPocName )
		.append("Contractor Id",this.contractorId,obj.contractorId )
		.append("Contract Vehicle",this.contractVehicle,obj.contractVehicle )
		.append("Country",this.country,obj.country )
		.append("Creator Id",this.creatorId,obj.creatorId )
		.append("Credit Check Amount",this.creditCheckAmount,obj.creditCheckAmount )
		.append("Currency",this.currency,obj.currency )
		.append("Custom 1",this.custom1,obj.custom1 )
		.append("Custom 2",this.custom2,obj.custom2 )
		.append("Deal Type",this.dealType,obj.dealType )
		.append("Delivery Type",this.deliveryType,obj.deliveryType )
		.append("Dental Insurance",this.dentalInsurance,obj.dentalInsurance )
		.append("Dental Insurance Amount",this.dentalInsuranceAmount,obj.dentalInsuranceAmount )
		.append("Discount",this.discount,obj.discount )
		.append("Discount Rate",this.discountRate,obj.discountRate )
		.append("Distance From Worksite",this.distanceFromWorksite,obj.distanceFromWorksite )
		.append("Distribution Method",this.distributionMethod,obj.distributionMethod )
		.append("Date Of Birth",this.dob,obj.dob )
		.append("Double Time",this.DoubleTime,obj.DoubleTime )
		.append("Double Time Bill",this.DoubleTimeBill,obj.DoubleTimeBill )
		.append("Drug Test Amount",this.drugTestAmount,obj.drugTestAmount )
		.append("dtb",this.dtb,obj.dtb )
		.append("dtp",this.dtp,obj.dtp )
		.append("Education",this.education,obj.education )
		.append("Effective Date",this.effectiveDate,obj.effectiveDate )
		.append("Email",this.email,obj.email )
		.append("Employee Class",this.employeeClass,obj.employeeClass )
		.append("Employee Veteran",this.employeeVeteran,obj.employeeVeteran )
		.append("Emp Type",this.empType,obj.empType )
		.append("End Date",this.endDate,obj.endDate )
		.append("Equipment Cost",this.equipmentCost,obj.equipmentCost )
		.append("Exec Orphan",this.execOrphan,obj.execOrphan )
		.append("Exec Orphan Hdn Val",this.execOrphanHdnVal,obj.execOrphanHdnVal )
		.append("Exec Orphan Inter",this.execOrphanInter,obj.execOrphanInter )
		.append("False Termination",this.falseTermination,obj.falseTermination )
		.append("Fax",this.fax,obj.fax )
		.append("Flsa Reference",this.flsaReference,obj.flsaReference )
		.append("Flsa Status",this.flsaStatus,obj.flsaStatus )
		.append("First Name",this.fName,obj.fName )
		.append("GA",this.ga,obj.ga )
		.append("Gender",this.gender,obj.gender )
		.append("Geo Office",this.geoOffice,obj.geoOffice )
		.append("Gross Profit",this.grossProfit,obj.grossProfit )
		.append("Gross Profit Percentage",this.grossProfitPercentage,obj.grossProfitPercentage )
		.append("H1",this.h1,obj.h1 )
		.append("Health",this.health,obj.health )
		.append("Immigration Cost",this.immigrationCost,obj.immigrationCost )
		.append("Initial",this.initial,obj.initial )
		.append("Internal Accounting",this.internalAccounting,obj.internalAccounting )
		.append("Invoice Address",this.invoiceAddress,obj.invoiceAddress )
		.append("Invoice Frequency",this.invoiceFrequency,obj.invoiceFrequency )
		.append("Is Address USPS Validated",this.isAddressUSPSValidated,obj.isAddressUSPSValidated )
		.append("Is Bonus Eligible",this.isBonusEligible,obj.isBonusEligible )
		.append("Is Rehired",this.isRehired,obj.isRehired )
		.append("Job Bill Rate",this.jobBillRate,obj.jobBillRate )
		.append("Job Category",this.jobCategory,obj.jobCategory )
		.append("Job Pay Rate",this.jobPayRate,obj.jobPayRate )
		.append("Job Title",this.jobTitle,obj.jobTitle )
		.append("jrsr",this.jrsr,obj.jrsr )
		.append("k401",this.k401,obj.k401 )
		.append("Latitude",this.latitude,obj.latitude )
		.append("Leave",this.leave,obj.leave )
		.append("Liaison Name",this.liaisonName,obj.liaisonName )
		.append("Last Name",this.lName,obj.lName )
		.append("Longitude",this.longitude,obj.longitude )
		.append("Ltd Benefit",this.ltdBenefit,obj.ltdBenefit )
		.append("Manager Email",this.managerEmail,obj.managerEmail )
		.append("Manager Phone",this.managerPhone,obj.managerPhone )
		.append("Managing Director",this.managingDirector,obj.managingDirector )
		.append("Mobile Pager",this.mobilePager,obj.mobilePager )
		.append("Modified By",this.modifiedBy,obj.modifiedBy )
		.append("mrmrs",this.mrmrs,obj.mrmrs )
		.append("New Parent Id",this.newParentId,obj.newParentId )
		.append("Non Billable Cost",this.nonBillableCost,obj.nonBillableCost )
		.append("Non Billable Holidays",this.nonBillableHolidays,obj.nonBillableHolidays )
		.append("Non Billable Holidays Cost",this.nonBillableHolidaysCost,obj.nonBillableHolidaysCost )
		.append("Non Billable PTO",this.nonBillablePTO,obj.nonBillablePTO )
		.append("Non Billable PTO Cost",this.nonBillablePTOCost,obj.nonBillablePTOCost )
		.append("Other $",this.od,obj.od )
		.append("One Time Amount",this.oneTimeAmount,obj.oneTimeAmount )
		.append("Org Id",this.orgId,obj.orgId )
		.append("OT Bill",this.otb,obj.otb )
		.append("Other 1 Orphan",this.other1Orphan,obj.other1Orphan )
		.append("other1OrphanHdnVal",this.other1OrphanHdnVal,obj.other1OrphanHdnVal )
		.append("Other 1 Orphan Inter",this.other1OrphanInter,obj.other1OrphanInter )
		.append("Other 2 Orphan",this.other2Orphan,obj.other2Orphan )
		.append("other2OrphanHdnVal",this.other2OrphanHdnVal,obj.other2OrphanHdnVal )
		.append("Other 2 Orphan Inter",this.other2OrphanInter,obj.other2OrphanInter )
		.append("Other Amounts",this.otherAmounts,obj.otherAmounts )
		.append("Other Discount",this.otherDiscount,obj.otherDiscount )
		.append("Parent Id",this.parentId,obj.parentId )
		.append("Payment Discount",this.paymentDiscount,obj.paymentDiscount )
		.append("Payment Terms",this.paymentTerms,obj.paymentTerms )
		.append("Pay Rate",this.payRate,obj.payRate )
		.append("Percent 401 K",this.percent401k,obj.percent401k )
		.append("Perdiem",this.perdiem,obj.perdiem )
		.append("Perm GA Amount",this.permGAAmount,obj.permGAAmount )
		.append("Placement",this.placement,obj.placement )
		.append("Placement Amount",this.placementAmount,obj.placementAmount )
		.append("Placement Date",this.placementDate,obj.placementDate )
		.append("Placement Percentage",this.placementPercentage,obj.placementPercentage )
		.append("Po Number",this.ponumber,obj.ponumber )
		.append("Practice Area",this.practiceArea,obj.practiceArea )
		.append("Product",this.product,obj.product )
		.append("Profit",this.profit,obj.profit )
		.append("Project Cost",this.projectCost,obj.projectCost )
		.append("PTO Limit To Override Sick",this.pTOLimitToOverrideSick,obj.pTOLimitToOverrideSick )
		.append("PTO",this.rdoPTO,obj.rdoPTO )
		.append("Reason",this.reason,obj.reason )
		.append("Record Status",this.recordStatus,obj.recordStatus )
		.append("Recruiter Orphan",this.recruiterOrphan,obj.recruiterOrphan )
		.append("RecruiterOrphanHdnVal",this.recruiterOrphanHdnVal,obj.recruiterOrphanHdnVal )
		.append("Recruiter Orphan Inter",this.recruiterOrphanInter,obj.recruiterOrphanInter )
		.append("Recruiting Classification",this.recruitingClassification,obj.recruitingClassification )
		.append("References",this.references,obj.references )
		.append("Referral Fee Amount",this.referralFeeAmount,obj.referralFeeAmount )
		.append("Rejected By",this.rejectedBy,obj.rejectedBy )
		.append("Rejection Date",this.rejectionDate,obj.rejectionDate )
		.append("Rejection Reason",this.rejectionReason,obj.rejectionReason )
		.append("Rejection Status",this.rejectionStatus,obj.rejectionStatus )
		.append("Salary Amount",this.salaryAmount,obj.salaryAmount )
		.append("Sick Leave Cap",this.sickLeaveCap,obj.sickLeaveCap )
		.append("Sick Leave Cost",this.sickLeaveCost,obj.sickLeaveCost )
		.append("Sick Leave Per Hour Rate",this.sickLeavePerHourRate,obj.sickLeavePerHourRate )
		.append("Sick Leave Type",this.sickLeaveType,obj.sickLeaveType )
		.append("Site Location",this.siteLocation,obj.siteLocation )
		.append("Skill Category",this.skillCategory,obj.skillCategory )
		.append("Special Notes",this.specialNotes,obj.specialNotes )
		.append("Split",this.split,obj.split )
		.append("Split Commission Percentage 1",this.splitCommissionPercentage1,obj.splitCommissionPercentage1 )
		.append("Split Commission Percentage 2",this.splitCommissionPercentage2,obj.splitCommissionPercentage2 )
		.append("Split Commission Percentage 3",this.splitCommissionPercentage3,obj.splitCommissionPercentage3 )
		.append("Split Commission Percentage 4",this.splitCommissionPercentage4,obj.splitCommissionPercentage4 )
		.append("Spread Week",this.spreadWeek,obj.spreadWeek )
		.append("ssn",this.ssn,obj.ssn )
		.append("Start Date",this.startDate,obj.startDate )
		.append("State",this.state,obj.state )
		.append("Std Benefit",this.stdBenefit,obj.stdBenefit )
		.append("Submission Date",this.submissionDate,obj.submissionDate )
		.append("Tax",this.tax,obj.tax )
		.append("Telephone",this.telephone,obj.telephone )
		.append("Employee Category",this.temps,obj.temps )
		.append("Temp Sub Cont Id",this.tempSubContId,obj.tempSubContId )
		.append("Terminate Date",this.termDate,obj.termDate )
		.append("Total Holidays",this.totalHolidays,obj.totalHolidays )
		.append("Total Hours Worked",this.totHoursWorked,obj.totHoursWorked )
		.append("Travel Required",this.travelRequired,obj.travelRequired )
		.append("Un Employed For Two Months",this.unEmployedForTwoMonths,obj.unEmployedForTwoMonths )
		.append("USPS Address Validation Date",this.uSPSAddressValidationDate,obj.uSPSAddressValidationDate )
		.append("Vertical",this.vertical,obj.vertical )
		.append("Vertical Timesheet Type",this.verticalTimesheetType,obj.verticalTimesheetType )
		.append("Volume Discount",this.volumeDiscount,obj.volumeDiscount )
		.append("Work Email",this.workEmail,obj.workEmail )
		.append("Work Phone",this.workPhone,obj.workPhone )
		.append("Work Site State",this.workSiteState,obj.workSiteState )
		.append("Zip",this.zip,obj.zip )
		.append("Portfolio",this.portfolio,obj.portfolio )
		.append("Portfolio Description",this.portfolioDescription,obj.portfolioDescription )
		.append("Portfolio AE1",this.dealPortfolio1AE1,obj.dealPortfolio1AE1 )
		.append("Portfolio REC1",this.dealPortfolio2REC1,obj.dealPortfolio2REC1 )
		.append("Portfolio AE2",this.dealPortfolio3AE2,obj.dealPortfolio3AE2 )
		.append("Portfolio REC2",this.dealPortfolio4REC2,obj.dealPortfolio4REC2 )
		.append("CoSellStatus",this.coSellStatus,obj.coSellStatus )
		.append("Company Code",this.companyCode,obj.companyCode )
		.append("Placement Type",this.placementType,obj.placementType )
		.append("Batch Code",this.bullhornBatchCode,obj.bullhornBatchCode )
		.append("Batch Data Id",this.bullhornBatchDataProcessedId,obj.bullhornBatchDataProcessedId )
		.append("Staging Id",this.bullhornBatchAnalyzerStagingId,obj.bullhornBatchAnalyzerStagingId )
		.append("Data Source",this.dataSource,obj.dataSource )
		.append("Bullhorn PlacementId",this.bullhornPlacementId,obj.bullhornPlacementId )	
		.append("Deal Activity Amount",this.dealActivityAmount,obj.dealActivityAmount )	

		/*
		 * Added as part of Tax and Sick Leave Project
		 */
		
		.append("Bullhorn BatchInfoId",this.bullhornBatchInfoId,obj.bullhornBatchInfoId )	
		.append("Is Modification Required",this.isModificationRequired,obj.isModificationRequired )	
		.append("Bullhorn TerminationData ProcessedId",this.bullhornTerminationDataProcessedId,obj.bullhornTerminationDataProcessedId )	
		.append("Bullhorn TerminationData StagingId",this.bullhornTerminationDataStagingId,obj.bullhornTerminationDataStagingId )	
		.append("Termination Bullhorn BatchInfoId",this.terminationBullhornBatchInfoId,obj.terminationBullhornBatchInfoId )	
		.append("OverrideTermination",this.overrideTermination,obj.overrideTermination )
		.append("Work Performed At", this.workFromSource, obj.workFromSource)
		.append("Majority Of Work", this.majorityWorkPerformed, obj.majorityWorkPerformed)
		.append("Sick Leave Source", this.sickLeaveSource, obj.sickLeaveSource)
		.build();
	}

}