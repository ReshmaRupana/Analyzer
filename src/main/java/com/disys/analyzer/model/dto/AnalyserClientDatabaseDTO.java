/**
 * 
 */
package com.disys.analyzer.model.dto;

import java.io.Serializable;

/**
 * @author Sajid
 * 
 */
public class AnalyserClientDatabaseDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1029331799366053787L;
	
	private String clientId;
	private String primeSub;
	private String companyName;
	private String agreementType;
	private String otherAgreement;
	private String endClient;
	private String agreementNumber;
	private String contactName;
	private String contactTitle;
	private String contactAddress;
	private String contactCity;
	private String contactState;
	private String contactZip;
	private String contactPhone;
	private String contactFax;
	private String contactEmail;
	private String termStart;
	private String termEnd;
	private String renewals;
	private String noticeAddress;
	private String noticeCity;
	private String noticeState;
	private String noticeZip;
	private String paymentTerms;
	private String promptPay;
	private String eftSetup;
	private String invoiceFrequency;
	private String invoicingAddressInfo;
	private String adminFee;
	private String travelFees;
	private String overtime;
	private String contractLengthFrom;
	private String contractLengthTo;
	private String termination;
	private String disputeTimeframe;
	private String pricingInfo;
	private String insuranceCertificate;
	private String insWaiver;
	private String insWorkmans;
	private String insEmployers;
	private String insCgl;
	private String insAuto;
	private String insUmbrella;
	private String insProfessionalErrors;
	private String insCancelDays;
	private String insPolicyDescription;
	private String insThirdPartyCrimeBond;
	private String insBondAmount;
	private String warrenty;
	private String replacementGuarentee;

	private String mutualConfidentiality;
	private String rightToHire;
	private String rightToHireMonths;
	private String rightToHireFees;
	private String nonComplete;
	private String nonCompleteInformation;
	private String nonSolicitation;
	private String nonSolicitationTimeframe;
	private String subContractingAllowed;
	private String personalAssignedForms;
	private String personalForms;
	private String backgroundCheck;
	private String backgroundCheckInfo;
	private String backgroundCompany;
	private String drugTesting;
	private String drugTestingInfo;
	private String drugTestingCompany;
	private String creditCheck;
	private String creditCheckInfo;
	private String creditCompany;
	private String vms;
	private String vmsname;

	private String country;

	private String updatedBy;
	private String updatedDate;
	private String status;

	private String tmContract;
	private String amount;

	private String chkVolumeDiscount;
	private String chkPaymentDiscount;
	private String chkOtherDiscount;
	private String chkBackgroundAmount;
	private String chkCreditCheckAmount;
	private String chkDrugTestAmount;
	private Double volumeDiscount;
	private Double paymentDiscount;
	private Double otherDiscount;
	private Double backgroundAmount;
	private Double creditCheckAmount;
	private Double drugTestAmount;

	private String timesheetFrequency;

	private String volumeDiscountType1;
	private String volumeDiscountLowLimit1;
	private String volumeDiscountHighLimit1;
	private Double volumeDiscountAmount1;
	private String volumeDiscountType2;
	private String volumeDiscountLowLimit2;
	private String volumeDiscountHighLimit2;
	private Double volumeDiscountAmount2;
	private String volumeDiscountType3;
	private String volumeDiscountLowLimit3;
	private String volumeDiscountHighLimit3;
	private Double volumeDiscountAmount3;
	private String volumeDiscountType4;
	private String volumeDiscountLowLimit4;
	private String volumeDiscountHighLimit4;
	private Double volumeDiscountAmount4;
	private String volumeDiscountType5;
	private String volumeDiscountLowLimit5;
	private String volumeDiscountHighLimit5;
	private Double volumeDiscountAmount5;
	private String volumeDiscountType6;
	private String volumeDiscountLowLimit6;
	private String volumeDiscountHighLimit6;
	private Double volumeDiscountAmount6;
	private String volumeDiscountType7;
	private String volumeDiscountLowLimit7;
	private String volumeDiscountHighLimit7;
	private Double volumeDiscountAmount7;

	private String discountRemarks;
	
	private String tenureDiscountLowLimit1;
	private String tenureDiscountHighLimit1;
	private Double tenureDiscountAmount1;
	private String tenureDiscountLowLimit2;
	private String tenureDiscountHighLimit2;
	private Double tenureDiscountAmount2;
	private String tenureDiscountLowLimit3;
	private String tenureDiscountHighLimit3;
	private Double tenureDiscountAmount3;
	private String tenureDiscountLowLimit4;
	private String tenureDiscountHighLimit4;
	private Double tenureDiscountAmount4;
	private String tenureDiscountLowLimit5;
	private String tenureDiscountHighLimit5;
	private Double tenureDiscountAmount5;
	
	private String clientIndustry;
	
	private Integer totalPTODays;
	private Double billablePTO;
	private Double nonBillablePTO;
	private Integer totalHolidays;
	private Double billableHolidays;
	private Double nonBillableHolidays;
	private String sickLeaveType;
	private Integer pSVerticalId;
	private String pSVerticalCode;
	private String verticalDescription;
	private Integer pSProductId;
	private String pSProductCode;
	private String productDescription;
	private Integer pSBillingTypeId;
	private String pSBillingTypeCode;
	private String pSBillingTypeLongName;
	private String entityName;
	
	private String setId;
	
	private String pSClientId;
	private String portfolio;
	private String companyCode;
	
	/**
	 * @return the clientId
	 */
	public String getClientId() {
		return clientId;
	}

	/**
	 * @param clientId the clientId to set
	 */
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	/**
	 * @return the primeSub
	 */
	public String getPrimeSub() {
		return primeSub;
	}

	/**
	 * @param primeSub the primeSub to set
	 */
	public void setPrimeSub(String primeSub) {
		this.primeSub = primeSub;
	}

	/**
	 * @return the companyName
	 */
	public String getCompanyName() {
		return companyName;
	}

	/**
	 * @param companyName the companyName to set
	 */
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	/**
	 * @return the agreementType
	 */
	public String getAgreementType() {
		return agreementType;
	}

	/**
	 * @param agreementType the agreementType to set
	 */
	public void setAgreementType(String agreementType) {
		this.agreementType = agreementType;
	}

	/**
	 * @return the otherAgreement
	 */
	public String getOtherAgreement() {
		return otherAgreement;
	}

	/**
	 * @param otherAgreement the otherAgreement to set
	 */
	public void setOtherAgreement(String otherAgreement) {
		this.otherAgreement = otherAgreement;
	}

	/**
	 * @return the endClient
	 */
	public String getEndClient() {
		return endClient;
	}

	/**
	 * @param endClient the endClient to set
	 */
	public void setEndClient(String endClient) {
		this.endClient = endClient;
	}

	/**
	 * @return the agreementNumber
	 */
	public String getAgreementNumber() {
		return agreementNumber;
	}

	/**
	 * @param agreementNumber the agreementNumber to set
	 */
	public void setAgreementNumber(String agreementNumber) {
		this.agreementNumber = agreementNumber;
	}

	/**
	 * @return the contactName
	 */
	public String getContactName() {
		return contactName;
	}

	/**
	 * @param contactName the contactName to set
	 */
	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	/**
	 * @return the contactTitle
	 */
	public String getContactTitle() {
		return contactTitle;
	}

	/**
	 * @param contactTitle the contactTitle to set
	 */
	public void setContactTitle(String contactTitle) {
		this.contactTitle = contactTitle;
	}

	/**
	 * @return the contactAddress
	 */
	public String getContactAddress() {
		return contactAddress;
	}

	/**
	 * @param contactAddress the contactAddress to set
	 */
	public void setContactAddress(String contactAddress) {
		this.contactAddress = contactAddress;
	}

	/**
	 * @return the contactCity
	 */
	public String getContactCity() {
		return contactCity;
	}

	/**
	 * @param contactCity the contactCity to set
	 */
	public void setContactCity(String contactCity) {
		this.contactCity = contactCity;
	}

	/**
	 * @return the contactState
	 */
	public String getContactState() {
		return contactState;
	}

	/**
	 * @param contactState the contactState to set
	 */
	public void setContactState(String contactState) {
		this.contactState = contactState;
	}

	/**
	 * @return the contactZip
	 */
	public String getContactZip() {
		return contactZip;
	}

	/**
	 * @param contactZip the contactZip to set
	 */
	public void setContactZip(String contactZip) {
		this.contactZip = contactZip;
	}

	/**
	 * @return the contactPhone
	 */
	public String getContactPhone() {
		return contactPhone;
	}

	/**
	 * @param contactPhone the contactPhone to set
	 */
	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}

	/**
	 * @return the contactFax
	 */
	public String getContactFax() {
		return contactFax;
	}

	/**
	 * @param contactFax the contactFax to set
	 */
	public void setContactFax(String contactFax) {
		this.contactFax = contactFax;
	}

	/**
	 * @return the contactEmail
	 */
	public String getContactEmail() {
		return contactEmail;
	}

	/**
	 * @param contactEmail the contactEmail to set
	 */
	public void setContactEmail(String contactEmail) {
		this.contactEmail = contactEmail;
	}

	/**
	 * @return the termStart
	 */
	public String getTermStart() {
		return termStart;
	}

	/**
	 * @param termStart the termStart to set
	 */
	public void setTermStart(String termStart) {
		this.termStart = termStart;
	}

	/**
	 * @return the termEnd
	 */
	public String getTermEnd() {
		return termEnd;
	}

	/**
	 * @param termEnd the termEnd to set
	 */
	public void setTermEnd(String termEnd) {
		this.termEnd = termEnd;
	}

	/**
	 * @return the renewals
	 */
	public String getRenewals() {
		return renewals;
	}

	/**
	 * @param renewals the renewals to set
	 */
	public void setRenewals(String renewals) {
		this.renewals = renewals;
	}

	/**
	 * @return the noticeAddress
	 */
	public String getNoticeAddress() {
		return noticeAddress;
	}

	/**
	 * @param noticeAddress the noticeAddress to set
	 */
	public void setNoticeAddress(String noticeAddress) {
		this.noticeAddress = noticeAddress;
	}

	/**
	 * @return the noticeCity
	 */
	public String getNoticeCity() {
		return noticeCity;
	}

	/**
	 * @param noticeCity the noticeCity to set
	 */
	public void setNoticeCity(String noticeCity) {
		this.noticeCity = noticeCity;
	}

	/**
	 * @return the noticeState
	 */
	public String getNoticeState() {
		return noticeState;
	}

	/**
	 * @param noticeState the noticeState to set
	 */
	public void setNoticeState(String noticeState) {
		this.noticeState = noticeState;
	}

	/**
	 * @return the noticeZip
	 */
	public String getNoticeZip() {
		return noticeZip;
	}

	/**
	 * @param noticeZip the noticeZip to set
	 */
	public void setNoticeZip(String noticeZip) {
		this.noticeZip = noticeZip;
	}

	/**
	 * @return the paymentTerms
	 */
	public String getPaymentTerms() {
		return paymentTerms;
	}

	/**
	 * @param paymentTerms the paymentTerms to set
	 */
	public void setPaymentTerms(String paymentTerms) {
		this.paymentTerms = paymentTerms;
	}

	/**
	 * @return the promptPay
	 */
	public String getPromptPay() {
		return promptPay;
	}

	/**
	 * @param promptPay the promptPay to set
	 */
	public void setPromptPay(String promptPay) {
		this.promptPay = promptPay;
	}

	/**
	 * @return the eftSetup
	 */
	public String getEftSetup() {
		return eftSetup;
	}

	/**
	 * @param eftSetup the eftSetup to set
	 */
	public void setEftSetup(String eftSetup) {
		this.eftSetup = eftSetup;
	}

	/**
	 * @return the invoiceFrequency
	 */
	public String getInvoiceFrequency() {
		return invoiceFrequency;
	}

	/**
	 * @param invoiceFrequency the invoiceFrequency to set
	 */
	public void setInvoiceFrequency(String invoiceFrequency) {
		this.invoiceFrequency = invoiceFrequency;
	}

	/**
	 * @return the invoicingAddressInfo
	 */
	public String getInvoicingAddressInfo() {
		return invoicingAddressInfo;
	}

	/**
	 * @param invoicingAddressInfo the invoicingAddressInfo to set
	 */
	public void setInvoicingAddressInfo(String invoicingAddressInfo) {
		this.invoicingAddressInfo = invoicingAddressInfo;
	}

	/**
	 * @return the adminFee
	 */
	public String getAdminFee() {
		return adminFee;
	}

	/**
	 * @param adminFee the adminFee to set
	 */
	public void setAdminFee(String adminFee) {
		this.adminFee = adminFee;
	}

	/**
	 * @return the travelFees
	 */
	public String getTravelFees() {
		return travelFees;
	}

	/**
	 * @param travelFees the travelFees to set
	 */
	public void setTravelFees(String travelFees) {
		this.travelFees = travelFees;
	}

	/**
	 * @return the overtime
	 */
	public String getOvertime() {
		return overtime;
	}

	/**
	 * @param overtime the overtime to set
	 */
	public void setOvertime(String overtime) {
		this.overtime = overtime;
	}

	/**
	 * @return the contractLengthFrom
	 */
	public String getContractLengthFrom() {
		return contractLengthFrom;
	}

	/**
	 * @param contractLengthFrom the contractLengthFrom to set
	 */
	public void setContractLengthFrom(String contractLengthFrom) {
		this.contractLengthFrom = contractLengthFrom;
	}

	/**
	 * @return the contractLengthTo
	 */
	public String getContractLengthTo() {
		return contractLengthTo;
	}

	/**
	 * @param contractLengthTo the contractLengthTo to set
	 */
	public void setContractLengthTo(String contractLengthTo) {
		this.contractLengthTo = contractLengthTo;
	}

	/**
	 * @return the termination
	 */
	public String getTermination() {
		return termination;
	}

	/**
	 * @param termination the termination to set
	 */
	public void setTermination(String termination) {
		this.termination = termination;
	}

	/**
	 * @return the disputeTimeframe
	 */
	public String getDisputeTimeframe() {
		return disputeTimeframe;
	}

	/**
	 * @param disputeTimeframe the disputeTimeframe to set
	 */
	public void setDisputeTimeframe(String disputeTimeframe) {
		this.disputeTimeframe = disputeTimeframe;
	}

	/**
	 * @return the pricingInfo
	 */
	public String getPricingInfo() {
		return pricingInfo;
	}

	/**
	 * @param pricingInfo the pricingInfo to set
	 */
	public void setPricingInfo(String pricingInfo) {
		this.pricingInfo = pricingInfo;
	}

	/**
	 * @return the insuranceCertificate
	 */
	public String getInsuranceCertificate() {
		return insuranceCertificate;
	}

	/**
	 * @param insuranceCertificate the insuranceCertificate to set
	 */
	public void setInsuranceCertificate(String insuranceCertificate) {
		this.insuranceCertificate = insuranceCertificate;
	}

	/**
	 * @return the insWaiver
	 */
	public String getInsWaiver() {
		return insWaiver;
	}

	/**
	 * @param insWaiver the insWaiver to set
	 */
	public void setInsWaiver(String insWaiver) {
		this.insWaiver = insWaiver;
	}

	/**
	 * @return the insWorkmans
	 */
	public String getInsWorkmans() {
		return insWorkmans;
	}

	/**
	 * @param insWorkmans the insWorkmans to set
	 */
	public void setInsWorkmans(String insWorkmans) {
		this.insWorkmans = insWorkmans;
	}

	/**
	 * @return the insEmployers
	 */
	public String getInsEmployers() {
		return insEmployers;
	}

	/**
	 * @param insEmployers the insEmployers to set
	 */
	public void setInsEmployers(String insEmployers) {
		this.insEmployers = insEmployers;
	}

	/**
	 * @return the insCgl
	 */
	public String getInsCgl() {
		return insCgl;
	}

	/**
	 * @param insCgl the insCgl to set
	 */
	public void setInsCgl(String insCgl) {
		this.insCgl = insCgl;
	}

	/**
	 * @return the insAuto
	 */
	public String getInsAuto() {
		return insAuto;
	}

	/**
	 * @param insAuto the insAuto to set
	 */
	public void setInsAuto(String insAuto) {
		this.insAuto = insAuto;
	}

	/**
	 * @return the insUmbrella
	 */
	public String getInsUmbrella() {
		return insUmbrella;
	}

	/**
	 * @param insUmbrella the insUmbrella to set
	 */
	public void setInsUmbrella(String insUmbrella) {
		this.insUmbrella = insUmbrella;
	}

	/**
	 * @return the insProfessionalErrors
	 */
	public String getInsProfessionalErrors() {
		return insProfessionalErrors;
	}

	/**
	 * @param insProfessionalErrors the insProfessionalErrors to set
	 */
	public void setInsProfessionalErrors(String insProfessionalErrors) {
		this.insProfessionalErrors = insProfessionalErrors;
	}

	/**
	 * @return the insCancelDays
	 */
	public String getInsCancelDays() {
		return insCancelDays;
	}

	/**
	 * @param insCancelDays the insCancelDays to set
	 */
	public void setInsCancelDays(String insCancelDays) {
		this.insCancelDays = insCancelDays;
	}

	/**
	 * @return the insPolicyDescription
	 */
	public String getInsPolicyDescription() {
		return insPolicyDescription;
	}

	/**
	 * @param insPolicyDescription the insPolicyDescription to set
	 */
	public void setInsPolicyDescription(String insPolicyDescription) {
		this.insPolicyDescription = insPolicyDescription;
	}

	/**
	 * @return the insThirdPartyCrimeBond
	 */
	public String getInsThirdPartyCrimeBond() {
		return insThirdPartyCrimeBond;
	}

	/**
	 * @param insThirdPartyCrimeBond the insThirdPartyCrimeBond to set
	 */
	public void setInsThirdPartyCrimeBond(String insThirdPartyCrimeBond) {
		this.insThirdPartyCrimeBond = insThirdPartyCrimeBond;
	}

	/**
	 * @return the insBondAmount
	 */
	public String getInsBondAmount() {
		return insBondAmount;
	}

	/**
	 * @param insBondAmount the insBondAmount to set
	 */
	public void setInsBondAmount(String insBondAmount) {
		this.insBondAmount = insBondAmount;
	}

	/**
	 * @return the warrenty
	 */
	public String getWarrenty() {
		return warrenty;
	}

	/**
	 * @param warrenty the warrenty to set
	 */
	public void setWarrenty(String warrenty) {
		this.warrenty = warrenty;
	}

	/**
	 * @return the replacementGuarentee
	 */
	public String getReplacementGuarentee() {
		return replacementGuarentee;
	}

	/**
	 * @param replacementGuarentee the replacementGuarentee to set
	 */
	public void setReplacementGuarentee(String replacementGuarentee) {
		this.replacementGuarentee = replacementGuarentee;
	}

	/**
	 * @return the mutualConfidentiality
	 */
	public String getMutualConfidentiality() {
		return mutualConfidentiality;
	}

	/**
	 * @param mutualConfidentiality the mutualConfidentiality to set
	 */
	public void setMutualConfidentiality(String mutualConfidentiality) {
		this.mutualConfidentiality = mutualConfidentiality;
	}

	/**
	 * @return the rightToHire
	 */
	public String getRightToHire() {
		return rightToHire;
	}

	/**
	 * @param rightToHire the rightToHire to set
	 */
	public void setRightToHire(String rightToHire) {
		this.rightToHire = rightToHire;
	}

	/**
	 * @return the rightToHireMonths
	 */
	public String getRightToHireMonths() {
		return rightToHireMonths;
	}

	/**
	 * @param rightToHireMonths the rightToHireMonths to set
	 */
	public void setRightToHireMonths(String rightToHireMonths) {
		this.rightToHireMonths = rightToHireMonths;
	}

	/**
	 * @return the rightToHireFees
	 */
	public String getRightToHireFees() {
		return rightToHireFees;
	}

	/**
	 * @param rightToHireFees the rightToHireFees to set
	 */
	public void setRightToHireFees(String rightToHireFees) {
		this.rightToHireFees = rightToHireFees;
	}

	/**
	 * @return the nonComplete
	 */
	public String getNonComplete() {
		return nonComplete;
	}

	/**
	 * @param nonComplete the nonComplete to set
	 */
	public void setNonComplete(String nonComplete) {
		this.nonComplete = nonComplete;
	}

	/**
	 * @return the nonCompleteInformation
	 */
	public String getNonCompleteInformation() {
		return nonCompleteInformation;
	}

	/**
	 * @param nonCompleteInformation the nonCompleteInformation to set
	 */
	public void setNonCompleteInformation(String nonCompleteInformation) {
		this.nonCompleteInformation = nonCompleteInformation;
	}

	/**
	 * @return the nonSolicitation
	 */
	public String getNonSolicitation() {
		return nonSolicitation;
	}

	/**
	 * @param nonSolicitation the nonSolicitation to set
	 */
	public void setNonSolicitation(String nonSolicitation) {
		this.nonSolicitation = nonSolicitation;
	}

	/**
	 * @return the nonSolicitationTimeframe
	 */
	public String getNonSolicitationTimeframe() {
		return nonSolicitationTimeframe;
	}

	/**
	 * @param nonSolicitationTimeframe the nonSolicitationTimeframe to set
	 */
	public void setNonSolicitationTimeframe(String nonSolicitationTimeframe) {
		this.nonSolicitationTimeframe = nonSolicitationTimeframe;
	}

	/**
	 * @return the subContractingAllowed
	 */
	public String getSubContractingAllowed() {
		return subContractingAllowed;
	}

	/**
	 * @param subContractingAllowed the subContractingAllowed to set
	 */
	public void setSubContractingAllowed(String subContractingAllowed) {
		this.subContractingAllowed = subContractingAllowed;
	}

	/**
	 * @return the personalAssignedForms
	 */
	public String getPersonalAssignedForms() {
		return personalAssignedForms;
	}

	/**
	 * @param personalAssignedForms the personalAssignedForms to set
	 */
	public void setPersonalAssignedForms(String personalAssignedForms) {
		this.personalAssignedForms = personalAssignedForms;
	}

	/**
	 * @return the personalForms
	 */
	public String getPersonalForms() {
		return personalForms;
	}

	/**
	 * @param personalForms the personalForms to set
	 */
	public void setPersonalForms(String personalForms) {
		this.personalForms = personalForms;
	}

	/**
	 * @return the backgroundCheck
	 */
	public String getBackgroundCheck() {
		return backgroundCheck;
	}

	/**
	 * @param backgroundCheck the backgroundCheck to set
	 */
	public void setBackgroundCheck(String backgroundCheck) {
		this.backgroundCheck = backgroundCheck;
	}

	/**
	 * @return the backgroundCheckInfo
	 */
	public String getBackgroundCheckInfo() {
		return backgroundCheckInfo;
	}

	/**
	 * @param backgroundCheckInfo the backgroundCheckInfo to set
	 */
	public void setBackgroundCheckInfo(String backgroundCheckInfo) {
		this.backgroundCheckInfo = backgroundCheckInfo;
	}

	/**
	 * @return the backgroundCompany
	 */
	public String getBackgroundCompany() {
		return backgroundCompany;
	}

	/**
	 * @param backgroundCompany the backgroundCompany to set
	 */
	public void setBackgroundCompany(String backgroundCompany) {
		this.backgroundCompany = backgroundCompany;
	}

	/**
	 * @return the drugTesting
	 */
	public String getDrugTesting() {
		return drugTesting;
	}

	/**
	 * @param drugTesting the drugTesting to set
	 */
	public void setDrugTesting(String drugTesting) {
		this.drugTesting = drugTesting;
	}

	/**
	 * @return the drugTestingInfo
	 */
	public String getDrugTestingInfo() {
		return drugTestingInfo;
	}

	/**
	 * @param drugTestingInfo the drugTestingInfo to set
	 */
	public void setDrugTestingInfo(String drugTestingInfo) {
		this.drugTestingInfo = drugTestingInfo;
	}

	/**
	 * @return the drugTestingCompany
	 */
	public String getDrugTestingCompany() {
		return drugTestingCompany;
	}

	/**
	 * @param drugTestingCompany the drugTestingCompany to set
	 */
	public void setDrugTestingCompany(String drugTestingCompany) {
		this.drugTestingCompany = drugTestingCompany;
	}

	/**
	 * @return the creditCheck
	 */
	public String getCreditCheck() {
		return creditCheck;
	}

	/**
	 * @param creditCheck the creditCheck to set
	 */
	public void setCreditCheck(String creditCheck) {
		this.creditCheck = creditCheck;
	}

	/**
	 * @return the creditCheckInfo
	 */
	public String getCreditCheckInfo() {
		return creditCheckInfo;
	}

	/**
	 * @param creditCheckInfo the creditCheckInfo to set
	 */
	public void setCreditCheckInfo(String creditCheckInfo) {
		this.creditCheckInfo = creditCheckInfo;
	}

	/**
	 * @return the creditCompany
	 */
	public String getCreditCompany() {
		return creditCompany;
	}

	/**
	 * @param creditCompany the creditCompany to set
	 */
	public void setCreditCompany(String creditCompany) {
		this.creditCompany = creditCompany;
	}

	/**
	 * @return the vms
	 */
	public String getVms() {
		return vms;
	}

	/**
	 * @param vms the vms to set
	 */
	public void setVms(String vms) {
		this.vms = vms;
	}

	/**
	 * @return the vmsname
	 */
	public String getVmsname() {
		return vmsname;
	}

	/**
	 * @param vmsname the vmsname to set
	 */
	public void setVmsname(String vmsname) {
		this.vmsname = vmsname;
	}

	/**
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * @param country the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	/**
	 * @return the updatedBy
	 */
	public String getUpdatedBy() {
		return updatedBy;
	}

	/**
	 * @param updatedBy the updatedBy to set
	 */
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	/**
	 * @return the updatedDate
	 */
	public String getUpdatedDate() {
		return updatedDate;
	}

	/**
	 * @param updatedDate the updatedDate to set
	 */
	public void setUpdatedDate(String updatedDate) {
		this.updatedDate = updatedDate;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the tmContract
	 */
	public String getTmContract() {
		return tmContract;
	}

	/**
	 * @param tmContract the tmContract to set
	 */
	public void setTmContract(String tmContract) {
		this.tmContract = tmContract;
	}

	/**
	 * @return the amount
	 */
	public String getAmount() {
		return amount;
	}

	/**
	 * @param amount the amount to set
	 */
	public void setAmount(String amount) {
		this.amount = amount;
	}

	/**
	 * @return the chkVolumeDiscount
	 */
	public String getChkVolumeDiscount() {
		return chkVolumeDiscount;
	}

	/**
	 * @param chkVolumeDiscount the chkVolumeDiscount to set
	 */
	public void setChkVolumeDiscount(String chkVolumeDiscount) {
		this.chkVolumeDiscount = chkVolumeDiscount;
	}

	/**
	 * @return the chkPaymentDiscount
	 */
	public String getChkPaymentDiscount() {
		return chkPaymentDiscount;
	}

	/**
	 * @param chkPaymentDiscount the chkPaymentDiscount to set
	 */
	public void setChkPaymentDiscount(String chkPaymentDiscount) {
		this.chkPaymentDiscount = chkPaymentDiscount;
	}

	/**
	 * @return the chkOtherDiscount
	 */
	public String getChkOtherDiscount() {
		return chkOtherDiscount;
	}

	/**
	 * @param chkOtherDiscount the chkOtherDiscount to set
	 */
	public void setChkOtherDiscount(String chkOtherDiscount) {
		this.chkOtherDiscount = chkOtherDiscount;
	}

	/**
	 * @return the chkBackgroundAmount
	 */
	public String getChkBackgroundAmount() {
		return chkBackgroundAmount;
	}

	/**
	 * @param chkBackgroundAmount the chkBackgroundAmount to set
	 */
	public void setChkBackgroundAmount(String chkBackgroundAmount) {
		this.chkBackgroundAmount = chkBackgroundAmount;
	}

	/**
	 * @return the chkCreditCheckAmount
	 */
	public String getChkCreditCheckAmount() {
		return chkCreditCheckAmount;
	}

	/**
	 * @param chkCreditCheckAmount the chkCreditCheckAmount to set
	 */
	public void setChkCreditCheckAmount(String chkCreditCheckAmount) {
		this.chkCreditCheckAmount = chkCreditCheckAmount;
	}

	/**
	 * @return the chkDrugTestAmount
	 */
	public String getChkDrugTestAmount() {
		return chkDrugTestAmount;
	}

	/**
	 * @param chkDrugTestAmount the chkDrugTestAmount to set
	 */
	public void setChkDrugTestAmount(String chkDrugTestAmount) {
		this.chkDrugTestAmount = chkDrugTestAmount;
	}

	/**
	 * @return the volumeDiscount
	 */
	public Double getVolumeDiscount() {
		return volumeDiscount;
	}

	/**
	 * @param volumeDiscount the volumeDiscount to set
	 */
	public void setVolumeDiscount(Double volumeDiscount) {
		this.volumeDiscount = volumeDiscount;
	}

	/**
	 * @return the paymentDiscount
	 */
	public Double getPaymentDiscount() {
		return paymentDiscount;
	}

	/**
	 * @param paymentDiscount the paymentDiscount to set
	 */
	public void setPaymentDiscount(Double paymentDiscount) {
		this.paymentDiscount = paymentDiscount;
	}

	/**
	 * @return the otherDiscount
	 */
	public Double getOtherDiscount() {
		return otherDiscount;
	}

	/**
	 * @param otherDiscount the otherDiscount to set
	 */
	public void setOtherDiscount(Double otherDiscount) {
		this.otherDiscount = otherDiscount;
	}

	/**
	 * @return the backgroundAmount
	 */
	public Double getBackgroundAmount() {
		return backgroundAmount;
	}

	/**
	 * @param backgroundAmount the backgroundAmount to set
	 */
	public void setBackgroundAmount(Double backgroundAmount) {
		this.backgroundAmount = backgroundAmount;
	}

	/**
	 * @return the creditCheckAmount
	 */
	public Double getCreditCheckAmount() {
		return creditCheckAmount;
	}

	/**
	 * @param creditCheckAmount the creditCheckAmount to set
	 */
	public void setCreditCheckAmount(Double creditCheckAmount) {
		this.creditCheckAmount = creditCheckAmount;
	}

	/**
	 * @return the drugTestAmount
	 */
	public Double getDrugTestAmount() {
		return drugTestAmount;
	}

	/**
	 * @param drugTestAmount the drugTestAmount to set
	 */
	public void setDrugTestAmount(Double drugTestAmount) {
		this.drugTestAmount = drugTestAmount;
	}

	/**
	 * @return the timesheetFrequency
	 */
	public String getTimesheetFrequency() {
		return timesheetFrequency;
	}

	/**
	 * @param timesheetFrequency the timesheetFrequency to set
	 */
	public void setTimesheetFrequency(String timesheetFrequency) {
		this.timesheetFrequency = timesheetFrequency;
	}

	/**
	 * @return the volumeDiscountType1
	 */
	public String getVolumeDiscountType1() {
		return volumeDiscountType1;
	}

	/**
	 * @param volumeDiscountType1 the volumeDiscountType1 to set
	 */
	public void setVolumeDiscountType1(String volumeDiscountType1) {
		this.volumeDiscountType1 = volumeDiscountType1;
	}

	/**
	 * @return the volumeDiscountLowLimit1
	 */
	public String getVolumeDiscountLowLimit1() {
		return volumeDiscountLowLimit1;
	}

	/**
	 * @param volumeDiscountLowLimit1 the volumeDiscountLowLimit1 to set
	 */
	public void setVolumeDiscountLowLimit1(String volumeDiscountLowLimit1) {
		this.volumeDiscountLowLimit1 = volumeDiscountLowLimit1;
	}

	/**
	 * @return the volumeDiscountHighLimit1
	 */
	public String getVolumeDiscountHighLimit1() {
		return volumeDiscountHighLimit1;
	}

	/**
	 * @param volumeDiscountHighLimit1 the volumeDiscountHighLimit1 to set
	 */
	public void setVolumeDiscountHighLimit1(String volumeDiscountHighLimit1) {
		this.volumeDiscountHighLimit1 = volumeDiscountHighLimit1;
	}

	/**
	 * @return the volumeDiscountAmount1
	 */
	public Double getVolumeDiscountAmount1() {
		return volumeDiscountAmount1;
	}

	/**
	 * @param volumeDiscountAmount1 the volumeDiscountAmount1 to set
	 */
	public void setVolumeDiscountAmount1(Double volumeDiscountAmount1) {
		this.volumeDiscountAmount1 = volumeDiscountAmount1;
	}

	/**
	 * @return the volumeDiscountType2
	 */
	public String getVolumeDiscountType2() {
		return volumeDiscountType2;
	}

	/**
	 * @param volumeDiscountType2 the volumeDiscountType2 to set
	 */
	public void setVolumeDiscountType2(String volumeDiscountType2) {
		this.volumeDiscountType2 = volumeDiscountType2;
	}

	/**
	 * @return the volumeDiscountLowLimit2
	 */
	public String getVolumeDiscountLowLimit2() {
		return volumeDiscountLowLimit2;
	}

	/**
	 * @param volumeDiscountLowLimit2 the volumeDiscountLowLimit2 to set
	 */
	public void setVolumeDiscountLowLimit2(String volumeDiscountLowLimit2) {
		this.volumeDiscountLowLimit2 = volumeDiscountLowLimit2;
	}

	/**
	 * @return the volumeDiscountHighLimit2
	 */
	public String getVolumeDiscountHighLimit2() {
		return volumeDiscountHighLimit2;
	}

	/**
	 * @param volumeDiscountHighLimit2 the volumeDiscountHighLimit2 to set
	 */
	public void setVolumeDiscountHighLimit2(String volumeDiscountHighLimit2) {
		this.volumeDiscountHighLimit2 = volumeDiscountHighLimit2;
	}

	/**
	 * @return the volumeDiscountAmount2
	 */
	public Double getVolumeDiscountAmount2() {
		return volumeDiscountAmount2;
	}

	/**
	 * @param volumeDiscountAmount2 the volumeDiscountAmount2 to set
	 */
	public void setVolumeDiscountAmount2(Double volumeDiscountAmount2) {
		this.volumeDiscountAmount2 = volumeDiscountAmount2;
	}

	/**
	 * @return the volumeDiscountType3
	 */
	public String getVolumeDiscountType3() {
		return volumeDiscountType3;
	}

	/**
	 * @param volumeDiscountType3 the volumeDiscountType3 to set
	 */
	public void setVolumeDiscountType3(String volumeDiscountType3) {
		this.volumeDiscountType3 = volumeDiscountType3;
	}

	/**
	 * @return the volumeDiscountLowLimit3
	 */
	public String getVolumeDiscountLowLimit3() {
		return volumeDiscountLowLimit3;
	}

	/**
	 * @param volumeDiscountLowLimit3 the volumeDiscountLowLimit3 to set
	 */
	public void setVolumeDiscountLowLimit3(String volumeDiscountLowLimit3) {
		this.volumeDiscountLowLimit3 = volumeDiscountLowLimit3;
	}

	/**
	 * @return the volumeDiscountHighLimit3
	 */
	public String getVolumeDiscountHighLimit3() {
		return volumeDiscountHighLimit3;
	}

	/**
	 * @param volumeDiscountHighLimit3 the volumeDiscountHighLimit3 to set
	 */
	public void setVolumeDiscountHighLimit3(String volumeDiscountHighLimit3) {
		this.volumeDiscountHighLimit3 = volumeDiscountHighLimit3;
	}

	/**
	 * @return the volumeDiscountAmount3
	 */
	public Double getVolumeDiscountAmount3() {
		return volumeDiscountAmount3;
	}

	/**
	 * @param volumeDiscountAmount3 the volumeDiscountAmount3 to set
	 */
	public void setVolumeDiscountAmount3(Double volumeDiscountAmount3) {
		this.volumeDiscountAmount3 = volumeDiscountAmount3;
	}

	/**
	 * @return the volumeDiscountType4
	 */
	public String getVolumeDiscountType4() {
		return volumeDiscountType4;
	}

	/**
	 * @param volumeDiscountType4 the volumeDiscountType4 to set
	 */
	public void setVolumeDiscountType4(String volumeDiscountType4) {
		this.volumeDiscountType4 = volumeDiscountType4;
	}

	/**
	 * @return the volumeDiscountLowLimit4
	 */
	public String getVolumeDiscountLowLimit4() {
		return volumeDiscountLowLimit4;
	}

	/**
	 * @param volumeDiscountLowLimit4 the volumeDiscountLowLimit4 to set
	 */
	public void setVolumeDiscountLowLimit4(String volumeDiscountLowLimit4) {
		this.volumeDiscountLowLimit4 = volumeDiscountLowLimit4;
	}

	/**
	 * @return the volumeDiscountHighLimit4
	 */
	public String getVolumeDiscountHighLimit4() {
		return volumeDiscountHighLimit4;
	}

	/**
	 * @param volumeDiscountHighLimit4 the volumeDiscountHighLimit4 to set
	 */
	public void setVolumeDiscountHighLimit4(String volumeDiscountHighLimit4) {
		this.volumeDiscountHighLimit4 = volumeDiscountHighLimit4;
	}

	/**
	 * @return the volumeDiscountAmount4
	 */
	public Double getVolumeDiscountAmount4() {
		return volumeDiscountAmount4;
	}

	/**
	 * @param volumeDiscountAmount4 the volumeDiscountAmount4 to set
	 */
	public void setVolumeDiscountAmount4(Double volumeDiscountAmount4) {
		this.volumeDiscountAmount4 = volumeDiscountAmount4;
	}

	/**
	 * @return the volumeDiscountType5
	 */
	public String getVolumeDiscountType5() {
		return volumeDiscountType5;
	}

	/**
	 * @param volumeDiscountType5 the volumeDiscountType5 to set
	 */
	public void setVolumeDiscountType5(String volumeDiscountType5) {
		this.volumeDiscountType5 = volumeDiscountType5;
	}

	/**
	 * @return the volumeDiscountLowLimit5
	 */
	public String getVolumeDiscountLowLimit5() {
		return volumeDiscountLowLimit5;
	}

	/**
	 * @param volumeDiscountLowLimit5 the volumeDiscountLowLimit5 to set
	 */
	public void setVolumeDiscountLowLimit5(String volumeDiscountLowLimit5) {
		this.volumeDiscountLowLimit5 = volumeDiscountLowLimit5;
	}

	/**
	 * @return the volumeDiscountHighLimit5
	 */
	public String getVolumeDiscountHighLimit5() {
		return volumeDiscountHighLimit5;
	}

	/**
	 * @param volumeDiscountHighLimit5 the volumeDiscountHighLimit5 to set
	 */
	public void setVolumeDiscountHighLimit5(String volumeDiscountHighLimit5) {
		this.volumeDiscountHighLimit5 = volumeDiscountHighLimit5;
	}

	/**
	 * @return the volumeDiscountAmount5
	 */
	public Double getVolumeDiscountAmount5() {
		return volumeDiscountAmount5;
	}

	/**
	 * @param volumeDiscountAmount5 the volumeDiscountAmount5 to set
	 */
	public void setVolumeDiscountAmount5(Double volumeDiscountAmount5) {
		this.volumeDiscountAmount5 = volumeDiscountAmount5;
	}

	/**
	 * @return the volumeDiscountType6
	 */
	public String getVolumeDiscountType6() {
		return volumeDiscountType6;
	}

	/**
	 * @param volumeDiscountType6 the volumeDiscountType6 to set
	 */
	public void setVolumeDiscountType6(String volumeDiscountType6) {
		this.volumeDiscountType6 = volumeDiscountType6;
	}

	/**
	 * @return the volumeDiscountLowLimit6
	 */
	public String getVolumeDiscountLowLimit6() {
		return volumeDiscountLowLimit6;
	}

	/**
	 * @param volumeDiscountLowLimit6 the volumeDiscountLowLimit6 to set
	 */
	public void setVolumeDiscountLowLimit6(String volumeDiscountLowLimit6) {
		this.volumeDiscountLowLimit6 = volumeDiscountLowLimit6;
	}

	/**
	 * @return the volumeDiscountHighLimit6
	 */
	public String getVolumeDiscountHighLimit6() {
		return volumeDiscountHighLimit6;
	}

	/**
	 * @param volumeDiscountHighLimit6 the volumeDiscountHighLimit6 to set
	 */
	public void setVolumeDiscountHighLimit6(String volumeDiscountHighLimit6) {
		this.volumeDiscountHighLimit6 = volumeDiscountHighLimit6;
	}

	/**
	 * @return the volumeDiscountAmount6
	 */
	public Double getVolumeDiscountAmount6() {
		return volumeDiscountAmount6;
	}

	/**
	 * @param volumeDiscountAmount6 the volumeDiscountAmount6 to set
	 */
	public void setVolumeDiscountAmount6(Double volumeDiscountAmount6) {
		this.volumeDiscountAmount6 = volumeDiscountAmount6;
	}

	/**
	 * @return the volumeDiscountType7
	 */
	public String getVolumeDiscountType7() {
		return volumeDiscountType7;
	}

	/**
	 * @param volumeDiscountType7 the volumeDiscountType7 to set
	 */
	public void setVolumeDiscountType7(String volumeDiscountType7) {
		this.volumeDiscountType7 = volumeDiscountType7;
	}

	/**
	 * @return the volumeDiscountLowLimit7
	 */
	public String getVolumeDiscountLowLimit7() {
		return volumeDiscountLowLimit7;
	}

	/**
	 * @param volumeDiscountLowLimit7 the volumeDiscountLowLimit7 to set
	 */
	public void setVolumeDiscountLowLimit7(String volumeDiscountLowLimit7) {
		this.volumeDiscountLowLimit7 = volumeDiscountLowLimit7;
	}

	/**
	 * @return the volumeDiscountHighLimit7
	 */
	public String getVolumeDiscountHighLimit7() {
		return volumeDiscountHighLimit7;
	}

	/**
	 * @param volumeDiscountHighLimit7 the volumeDiscountHighLimit7 to set
	 */
	public void setVolumeDiscountHighLimit7(String volumeDiscountHighLimit7) {
		this.volumeDiscountHighLimit7 = volumeDiscountHighLimit7;
	}

	/**
	 * @return the volumeDiscountAmount7
	 */
	public Double getVolumeDiscountAmount7() {
		return volumeDiscountAmount7;
	}

	/**
	 * @param volumeDiscountAmount7 the volumeDiscountAmount7 to set
	 */
	public void setVolumeDiscountAmount7(Double volumeDiscountAmount7) {
		this.volumeDiscountAmount7 = volumeDiscountAmount7;
	}

	/**
	 * @return the discountRemarks
	 */
	public String getDiscountRemarks() {
		return discountRemarks;
	}

	/**
	 * @param discountRemarks the discountRemarks to set
	 */
	public void setDiscountRemarks(String discountRemarks) {
		this.discountRemarks = discountRemarks;
	}

	/**
	 * @return the tenureDiscountLowLimit1
	 */
	public String getTenureDiscountLowLimit1() {
		return tenureDiscountLowLimit1;
	}

	/**
	 * @param tenureDiscountLowLimit1 the tenureDiscountLowLimit1 to set
	 */
	public void setTenureDiscountLowLimit1(String tenureDiscountLowLimit1) {
		this.tenureDiscountLowLimit1 = tenureDiscountLowLimit1;
	}

	/**
	 * @return the tenureDiscountHighLimit1
	 */
	public String getTenureDiscountHighLimit1() {
		return tenureDiscountHighLimit1;
	}

	/**
	 * @param tenureDiscountHighLimit1 the tenureDiscountHighLimit1 to set
	 */
	public void setTenureDiscountHighLimit1(String tenureDiscountHighLimit1) {
		this.tenureDiscountHighLimit1 = tenureDiscountHighLimit1;
	}

	/**
	 * @return the tenureDiscountAmount1
	 */
	public Double getTenureDiscountAmount1() {
		return tenureDiscountAmount1;
	}

	/**
	 * @param tenureDiscountAmount1 the tenureDiscountAmount1 to set
	 */
	public void setTenureDiscountAmount1(Double tenureDiscountAmount1) {
		this.tenureDiscountAmount1 = tenureDiscountAmount1;
	}

	/**
	 * @return the tenureDiscountLowLimit2
	 */
	public String getTenureDiscountLowLimit2() {
		return tenureDiscountLowLimit2;
	}

	/**
	 * @param tenureDiscountLowLimit2 the tenureDiscountLowLimit2 to set
	 */
	public void setTenureDiscountLowLimit2(String tenureDiscountLowLimit2) {
		this.tenureDiscountLowLimit2 = tenureDiscountLowLimit2;
	}

	/**
	 * @return the tenureDiscountHighLimit2
	 */
	public String getTenureDiscountHighLimit2() {
		return tenureDiscountHighLimit2;
	}

	/**
	 * @param tenureDiscountHighLimit2 the tenureDiscountHighLimit2 to set
	 */
	public void setTenureDiscountHighLimit2(String tenureDiscountHighLimit2) {
		this.tenureDiscountHighLimit2 = tenureDiscountHighLimit2;
	}

	/**
	 * @return the tenureDiscountAmount2
	 */
	public Double getTenureDiscountAmount2() {
		return tenureDiscountAmount2;
	}

	/**
	 * @param tenureDiscountAmount2 the tenureDiscountAmount2 to set
	 */
	public void setTenureDiscountAmount2(Double tenureDiscountAmount2) {
		this.tenureDiscountAmount2 = tenureDiscountAmount2;
	}

	/**
	 * @return the tenureDiscountLowLimit3
	 */
	public String getTenureDiscountLowLimit3() {
		return tenureDiscountLowLimit3;
	}

	/**
	 * @param tenureDiscountLowLimit3 the tenureDiscountLowLimit3 to set
	 */
	public void setTenureDiscountLowLimit3(String tenureDiscountLowLimit3) {
		this.tenureDiscountLowLimit3 = tenureDiscountLowLimit3;
	}

	/**
	 * @return the tenureDiscountHighLimit3
	 */
	public String getTenureDiscountHighLimit3() {
		return tenureDiscountHighLimit3;
	}

	/**
	 * @param tenureDiscountHighLimit3 the tenureDiscountHighLimit3 to set
	 */
	public void setTenureDiscountHighLimit3(String tenureDiscountHighLimit3) {
		this.tenureDiscountHighLimit3 = tenureDiscountHighLimit3;
	}

	/**
	 * @return the tenureDiscountAmount3
	 */
	public Double getTenureDiscountAmount3() {
		return tenureDiscountAmount3;
	}

	/**
	 * @param tenureDiscountAmount3 the tenureDiscountAmount3 to set
	 */
	public void setTenureDiscountAmount3(Double tenureDiscountAmount3) {
		this.tenureDiscountAmount3 = tenureDiscountAmount3;
	}

	/**
	 * @return the tenureDiscountLowLimit4
	 */
	public String getTenureDiscountLowLimit4() {
		return tenureDiscountLowLimit4;
	}

	/**
	 * @param tenureDiscountLowLimit4 the tenureDiscountLowLimit4 to set
	 */
	public void setTenureDiscountLowLimit4(String tenureDiscountLowLimit4) {
		this.tenureDiscountLowLimit4 = tenureDiscountLowLimit4;
	}

	/**
	 * @return the tenureDiscountHighLimit4
	 */
	public String getTenureDiscountHighLimit4() {
		return tenureDiscountHighLimit4;
	}

	/**
	 * @param tenureDiscountHighLimit4 the tenureDiscountHighLimit4 to set
	 */
	public void setTenureDiscountHighLimit4(String tenureDiscountHighLimit4) {
		this.tenureDiscountHighLimit4 = tenureDiscountHighLimit4;
	}

	/**
	 * @return the tenureDiscountAmount4
	 */
	public Double getTenureDiscountAmount4() {
		return tenureDiscountAmount4;
	}

	/**
	 * @param tenureDiscountAmount4 the tenureDiscountAmount4 to set
	 */
	public void setTenureDiscountAmount4(Double tenureDiscountAmount4) {
		this.tenureDiscountAmount4 = tenureDiscountAmount4;
	}

	/**
	 * @return the tenureDiscountLowLimit5
	 */
	public String getTenureDiscountLowLimit5() {
		return tenureDiscountLowLimit5;
	}

	/**
	 * @param tenureDiscountLowLimit5 the tenureDiscountLowLimit5 to set
	 */
	public void setTenureDiscountLowLimit5(String tenureDiscountLowLimit5) {
		this.tenureDiscountLowLimit5 = tenureDiscountLowLimit5;
	}

	/**
	 * @return the tenureDiscountHighLimit5
	 */
	public String getTenureDiscountHighLimit5() {
		return tenureDiscountHighLimit5;
	}

	/**
	 * @param tenureDiscountHighLimit5 the tenureDiscountHighLimit5 to set
	 */
	public void setTenureDiscountHighLimit5(String tenureDiscountHighLimit5) {
		this.tenureDiscountHighLimit5 = tenureDiscountHighLimit5;
	}

	/**
	 * @return the tenureDiscountAmount5
	 */
	public Double getTenureDiscountAmount5() {
		return tenureDiscountAmount5;
	}

	/**
	 * @param tenureDiscountAmount5 the tenureDiscountAmount5 to set
	 */
	public void setTenureDiscountAmount5(Double tenureDiscountAmount5) {
		this.tenureDiscountAmount5 = tenureDiscountAmount5;
	}

	/**
	 * @return the clientIndustry
	 */
	public String getClientIndustry() {
		return clientIndustry;
	}

	/**
	 * @param clientIndustry the clientIndustry to set
	 */
	public void setClientIndustry(String clientIndustry) {
		this.clientIndustry = clientIndustry;
	}

	/**
	 * @return the totalPTODays
	 */
	public Integer getTotalPTODays()
	{
		return totalPTODays;
	}

	/**
	 * @param totalPTODays the totalPTODays to set
	 */
	public void setTotalPTODays(Integer totalPTODays)
	{
		this.totalPTODays = totalPTODays;
	}

	/**
	 * @return the billablePTO
	 */
	public Double getBillablePTO()
	{
		return billablePTO;
	}

	/**
	 * @param billablePTO the billablePTO to set
	 */
	public void setBillablePTO(Double billablePTO)
	{
		this.billablePTO = billablePTO;
	}

	/**
	 * @return the nonBillablePTO
	 */
	public Double getNonBillablePTO()
	{
		return nonBillablePTO;
	}

	/**
	 * @param nonBillablePTO the nonBillablePTO to set
	 */
	public void setNonBillablePTO(Double nonBillablePTO)
	{
		this.nonBillablePTO = nonBillablePTO;
	}

	/**
	 * @return the totalHolidays
	 */
	public Integer getTotalHolidays()
	{
		return totalHolidays;
	}

	/**
	 * @param totalHolidays the totalHolidays to set
	 */
	public void setTotalHolidays(Integer totalHolidays)
	{
		this.totalHolidays = totalHolidays;
	}

	/**
	 * @return the billableHolidays
	 */
	public Double getBillableHolidays()
	{
		return billableHolidays;
	}

	/**
	 * @param billableHolidays the billableHolidays to set
	 */
	public void setBillableHolidays(Double billableHolidays)
	{
		this.billableHolidays = billableHolidays;
	}

	/**
	 * @return the nonBillableHolidays
	 */
	public Double getNonBillableHolidays()
	{
		return nonBillableHolidays;
	}

	/**
	 * @param nonBillableHolidays the nonBillableHolidays to set
	 */
	public void setNonBillableHolidays(Double nonBillableHolidays)
	{
		this.nonBillableHolidays = nonBillableHolidays;
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
	 * @return the pSVerticalId
	 */
	public Integer getpSVerticalId()
	{
		return pSVerticalId;
	}

	/**
	 * @param pSVerticalId the pSVerticalId to set
	 */
	public void setpSVerticalId(Integer pSVerticalId)
	{
		this.pSVerticalId = pSVerticalId;
	}

	/**
	 * @return the pSVerticalCode
	 */
	public String getpSVerticalCode()
	{
		return pSVerticalCode;
	}

	/**
	 * @param pSVerticalCode the pSVerticalCode to set
	 */
	public void setpSVerticalCode(String pSVerticalCode)
	{
		this.pSVerticalCode = pSVerticalCode;
	}

	/**
	 * @return the verticalDescription
	 */
	public String getVerticalDescription()
	{
		return verticalDescription;
	}

	/**
	 * @param verticalDescription the verticalDescription to set
	 */
	public void setVerticalDescription(String verticalDescription)
	{
		this.verticalDescription = verticalDescription;
	}

	/**
	 * @return the pSProductId
	 */
	public Integer getpSProductId()
	{
		return pSProductId;
	}

	/**
	 * @param pSProductId the pSProductId to set
	 */
	public void setpSProductId(Integer pSProductId)
	{
		this.pSProductId = pSProductId;
	}

	/**
	 * @return the pSProductCode
	 */
	public String getpSProductCode()
	{
		return pSProductCode;
	}

	/**
	 * @param pSProductCode the pSProductCode to set
	 */
	public void setpSProductCode(String pSProductCode)
	{
		this.pSProductCode = pSProductCode;
	}

	/**
	 * @return the productDescription
	 */
	public String getProductDescription()
	{
		return productDescription;
	}

	/**
	 * @param productDescription the productDescription to set
	 */
	public void setProductDescription(String productDescription)
	{
		this.productDescription = productDescription;
	}

	/**
	 * @return the pSBillingTypeId
	 */
	public Integer getpSBillingTypeId()
	{
		return pSBillingTypeId;
	}

	/**
	 * @param pSBillingTypeId the pSBillingTypeId to set
	 */
	public void setpSBillingTypeId(Integer pSBillingTypeId)
	{
		this.pSBillingTypeId = pSBillingTypeId;
	}

	/**
	 * @return the pSBillingTypeCode
	 */
	public String getpSBillingTypeCode()
	{
		return pSBillingTypeCode;
	}

	/**
	 * @param pSBillingTypeCode the pSBillingTypeCode to set
	 */
	public void setpSBillingTypeCode(String pSBillingTypeCode)
	{
		this.pSBillingTypeCode = pSBillingTypeCode;
	}

	/**
	 * @return the pSBillingTypeLongName
	 */
	public String getpSBillingTypeLongName()
	{
		return pSBillingTypeLongName;
	}

	/**
	 * @param pSBillingTypeLongName the pSBillingTypeLongName to set
	 */
	public void setpSBillingTypeLongName(String pSBillingTypeLongName)
	{
		this.pSBillingTypeLongName = pSBillingTypeLongName;
	}

	/**
	 * @return the entityName
	 */
	public String getEntityName()
	{
		return entityName;
	}

	/**
	 * @param entityName the entityName to set
	 */
	public void setEntityName(String entityName)
	{
		this.entityName = entityName;
	}

	/**
	 * @return the setId
	 */
	public String getSetId() {
		return setId;
	}

	/**
	 * @param setId the setId to set
	 */
	public void setSetId(String setId) {
		this.setId = setId;
	}

	/**
	 * @return the pSClientId
	 */
	public String getpSClientId() {
		return pSClientId;
	}

	/**
	 * @param pSClientId the pSClientId to set
	 */
	public void setpSClientId(String pSClientId) {
		this.pSClientId = pSClientId;
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
	 * @return the companyCode
	 */
	public String getCompanyCode() {
		return companyCode;
	}
	/**
	 * @param companyCode
	 *            the companyCode to set
	 */
	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}
	
	@Override
	public String toString()
	{
		return "AnalyserClientDatabaseDTO [invoiceFrequency=" + invoiceFrequency + ", contactName=" + contactName + ", entityName=" + entityName + ", clientId=" + clientId 
				+ ", paymentTerms=" + paymentTerms + ", adminFee=" + adminFee + ", volumeDiscount=" + volumeDiscount + ", paymentDiscount=" + paymentDiscount
				+ ", otherDiscount=" + otherDiscount + ", backgroundAmount=" + backgroundAmount + ", creditCheckAmount=" + creditCheckAmount + ", drugTestAmount=" + drugTestAmount 
				+ ", verticalDescription=" + verticalDescription + ", productDescription=" + productDescription + ", pSVerticalCode=" + pSVerticalCode + ", pSProductCode=" + pSProductCode 
				+ ", pSBillingTypeLongName=" + pSBillingTypeLongName + ", companyName=" + companyName 
				+ ", updatedBy=" + updatedBy + ", status=" + status + ", pSClientId=" + pSClientId + ", country=" + country + ", setId=" + setId 
				+ ", pSVerticalId=" + pSVerticalId + ", pSProductId=" + pSProductId + ", portfolio=" + portfolio
				+ ", companyCode="+companyCode+"]";
	}
}
