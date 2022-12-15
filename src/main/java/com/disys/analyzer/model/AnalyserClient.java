package com.disys.analyzer.model;

import java.io.Serializable;

import javax.persistence.*;

import com.disys.analyzer.config.util.FacesUtils;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * The persistent class for the AnalyserClient database table.
 * 
 */
@Entity(name="Analyser_Client")
//@Table(schema="Analyser.dbo")
@NamedQueries(value = {
		@NamedQuery(name = "AnalyserClient.findAll", query = "SELECT a FROM Analyser_Client a"),
		@NamedQuery(name = "AnalyserClient.getClientList", query = "SELECT a.clientId, a.company FROM Analyser_Client a "
				+ "where  a.orgId = (select u.orgId from User u where u.userId = :userId ) "
				+ " ORDER BY a.company asc") })


/*@NamedStoredProcedureQueries({
	@NamedStoredProcedureQuery(
            name = "AnalyserClient.getAnalyserAllClients"
            , procedureName = ""+ FacesUtils.SCHEMA_WIRELESS +".spGetAnalyserAllClient"
            , parameters = {
                    @StoredProcedureParameter(mode = ParameterMode.IN, name = "varLoggedOnUserID", type = String.class),
                    @StoredProcedureParameter(mode = ParameterMode.IN, name = "varUserList", type = String.class),
                    @StoredProcedureParameter(mode = ParameterMode.IN, name = "varOrderBy", type = String.class),
                    @StoredProcedureParameter(mode = ParameterMode.IN, name = "varSearchString", type = String.class),
                    @StoredProcedureParameter(mode = ParameterMode.IN, name = "varVertical", type = String.class),
                    @StoredProcedureParameter(mode = ParameterMode.IN, name = "varProduct", type = String.class)
                    }
        )
})*/

/*@SqlResultSetMapping(
    name="AnalyserClientResults",
    entities={
        @EntityResult(
            entityClass=AnalyserSubContractor.class,
            fields={
                @FieldResult(name="contractorId", column="Contractor_ID"),
                @FieldResult(name="accountingContact", column="AccountingContact"),
                @FieldResult(name="accountingEmail", column="AccountingEmail"),
                @FieldResult(name="address1", column="Address1"),
                @FieldResult(name="address2", column="Address2"),
                @FieldResult(name="dbaName", column="DbaName"),
                @FieldResult(name="completeAddress", column="Address")
            }
        )
    }
)*/
public class AnalyserClient implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "Client_ID")
	private Integer clientId;

	@Column(name = "AdminFee")
	private String adminFee;

	@Column(name = "AgreementNumber")
	private String agreementNumber;

	@Column(name = "AgreementType")
	private String agreementType;

	private String amount1;

	private BigDecimal backgroundAmount;

	@Column(name = "BackgroundCheck")
	private String backgroundCheck;

	@Column(name = "BackgroundCheckInfo")
	private String backgroundCheckInfo;

	@Column(name = "BackgroundCompany")
	private String backgroundCompany;

	private String chkBackgroundAmount;

	private String chkCreditCheckAmount;

	private String chkDrugTestAmount;

	private String chkOtherDiscount;

	private String chkPaymentDiscount;

	private String chkVolumeDiscount;

	@Column(name = "ClientIndustry")
	private String clientIndustry;

	@Column(name = "Company")
	private String company;

	@Column(name = "ContactAddress")
	private String contactAddress;

	@Column(name = "ContactCity")
	private String contactCity;

	@Column(name = "ContactEmail")
	private String contactEmail;

	@Column(name = "ContactFax")
	private String contactFax;

	@Column(name = "ContactName")
	private String contactName;

	@Column(name = "ContactPhone")
	private String contactPhone;

	@Column(name = "ContactState")
	private String contactState;

	@Column(name = "ContactTitle")
	private String contactTitle;

	@Column(name = "ContactZip")
	private String contactZip;

	@Column(name = "ContractLengthFrom")
	private String contractLengthFrom;

	@Column(name = "ContractLengthTo")
	private String contractLengthTo;

	@Column(name = "Country")
	private String country;

	@Column(name = "CreditCheck")
	private String creditCheck;

	private BigDecimal creditCheckAmount;

	@Column(name = "CreditCheckInfo")
	private String creditCheckInfo;

	@Column(name = "CreditCompany")
	private String creditCompany;

	@Column(name = "DiscountRemarks")
	private String discountRemarks;

	@Column(name = "DisputeTimeframe")
	private String disputeTimeframe;

	private BigDecimal drugTestAmount;

	@Column(name = "DrugTesting")
	private String drugTesting;

	@Column(name = "DrugTestingCompany")
	private String drugTestingCompany;

	@Column(name = "DrugTestingInfo")
	private String drugTestingInfo;

	@Column(name = "EftSetup")
	private String eftSetup;

	@Column(name = "EndClient")
	private String endClient;

	@Column(name = "ENTEREDBY")
	private String enteredby;

	@Column(name = "ENTRYDATE")
	private Timestamp entrydate;

	@Column(name = "InsAuto")
	private String insAuto;

	@Column(name = "InsBondAmount")
	private String insBondAmount;

	@Column(name = "InsCancelDays")
	private String insCancelDays;

	@Column(name = "InsCgl")
	private String insCgl;

	@Column(name = "InsEmployers")
	private String insEmployers;

	@Column(name = "InsPolicyDescription")
	private String insPolicyDescription;

	@Column(name = "InsProfessionalErrors")
	private String insProfessionalErrors;

	@Column(name = "InsThirdPartyCrimeBond")
	private String insThirdPartyCrimeBond;

	@Column(name = "InsUmbrella")
	private String insUmbrella;

	@Column(name = "InsuranceCertificate")
	private String insuranceCertificate;

	@Column(name = "InsWaiver")
	private String insWaiver;

	@Column(name = "InsWorkmans")
	private String insWorkmans;

	@Column(name = "InvoiceFrequency")
	private String invoiceFrequency;

	@Column(name = "InvoicingAddressInfo")
	private String invoicingAddressInfo;

	@Column(name = "MutualConfidentiality")
	private String mutualConfidentiality;

	@Column(name = "NonComplete")
	private String nonComplete;

	@Column(name = "NonCompleteInformation")
	private String nonCompleteInformation;

	@Column(name = "NonSolicitation")
	private String nonSolicitation;

	@Column(name = "NonSolicitationTimeframe")
	private String nonSolicitationTimeframe;

	@Column(name = "NoticeAddress")
	private String noticeAddress;

	@Column(name = "NoticeCity")
	private String noticeCity;

	@Column(name = "NoticeState")
	private String noticeState;

	@Column(name = "NoticeZip")
	private String noticeZip;

	@Column(name = "ORG_ID")
	private Integer orgId;

	@Column(name = "OtherAgreement")
	private String otherAgreement;

	private BigDecimal otherDiscount;

	@Column(name = "Overtime")
	private String overtime;

	private BigDecimal paymentDiscount;

	@Column(name = "PaymentTerms")
	private String paymentTerms;

	@Column(name = "PersonalAssignedForms")
	private String personalAssignedForms;

	@Column(name = "PersonalForms")
	private String personalForms;

	@Column(name = "PricingInfo")
	private String pricingInfo;

	@Column(name = "PrimeSub")
	private String primeSub;

	@Column(name = "ProductDescription")
	private String productDescription;

	@Column(name = "PromptPay")
	private String promptPay;

	@Column(name = "PSBillingTypeCode")
	private String pSBillingTypeCode;

	@Column(name = "PSBillingTypeLongName")
	private String pSBillingTypeLongName;

	@Column(name = "PSCLIENTID")
	private String pSClientId;

	@Column(name = "PSProductCode")
	private String pSProductCode;

	@Column(name = "PSSalesforceClientCode")
	private String pSSalesforceClientCode;

	@Column(name = "PSSalesforceClientName")
	private String pSSalesforceClientName;

	@Column(name = "PSServiceAreaCode")
	private String pSServiceAreaCode;
	
	@Column(name = "PSServiceAreaLongName")
	private String pSServiceAreaLongName;
	
	@Column(name = "PSVerticalCode")
	private String pSVerticalCode;
	

	@Column(name = "Renewals")
	private String renewals;

	@Column(name = "ReplacementGuarentee")
	private String replacementGuarentee;

	@Column(name = "RightToHire")
	private String rightToHire;

	@Column(name = "RightToHireFees")
	private String rightToHireFees;

	@Column(name = "RightToHireMonths")
	private String rightToHireMonths;

	private Integer status;

	@Column(name = "SubContractingAllowed")
	private String subContractingAllowed;

	@Column(name = "TenureDiscountAmount1")
	private BigDecimal tenureDiscountAmount1;

	@Column(name = "TenureDiscountAmount2")
	private BigDecimal tenureDiscountAmount2;

	@Column(name = "TenureDiscountAmount3")
	private BigDecimal tenureDiscountAmount3;

	@Column(name = "TenureDiscountAmount4")
	private BigDecimal tenureDiscountAmount4;

	@Column(name = "TenureDiscountAmount5")
	private BigDecimal tenureDiscountAmount5;

	@Column(name = "TenureDiscountHighLimit1")
	private String tenureDiscountHighLimit1;

	@Column(name = "TenureDiscountHighLimit2")
	private String tenureDiscountHighLimit2;

	@Column(name = "TenureDiscountHighLimit3")
	private String tenureDiscountHighLimit3;

	@Column(name = "TenureDiscountHighLimit4")
	private String tenureDiscountHighLimit4;

	@Column(name = "TenureDiscountHighLimit5")
	private String tenureDiscountHighLimit5;

	@Column(name = "TenureDiscountLowLimit1")
	private String tenureDiscountLowLimit1;

	@Column(name = "TenureDiscountLowLimit2")
	private String tenureDiscountLowLimit2;

	@Column(name = "TenureDiscountLowLimit3")
	private String tenureDiscountLowLimit3;

	@Column(name = "TenureDiscountLowLimit4")
	private String tenureDiscountLowLimit4;

	@Column(name = "TenureDiscountLowLimit5")
	private String tenureDiscountLowLimit5;

	@Column(name = "TermEnd")
	private String termEnd;

	@Column(name = "Termination")
	private String termination;

	@Column(name = "TermStart")
	private String termStart;

	private String timesheetFrequency;

	private String tmContract;

	@Column(name = "TravelFees")
	private String travelFees;

	@Column(name = "Updated_By")
	private String updatedBy;

	@Column(name = "Updated_date")
	private Timestamp updatedDate;

	@Column(name = "VerticalDescription")
	private String verticalDescription;

	@Column(name = "VMS")
	private String vms;

	@Column(name = "VMSName")
	private String vMSName;

	private BigDecimal volumeDiscount;

	@Column(name = "VolumeDiscountAmount1")
	private BigDecimal volumeDiscountAmount1;

	@Column(name = "VolumeDiscountAmount2")
	private BigDecimal volumeDiscountAmount2;

	@Column(name = "VolumeDiscountAmount3")
	private BigDecimal volumeDiscountAmount3;

	@Column(name = "VolumeDiscountAmount4")
	private BigDecimal volumeDiscountAmount4;

	@Column(name = "VolumeDiscountAmount5")
	private BigDecimal volumeDiscountAmount5;

	@Column(name = "VolumeDiscountAmount6")
	private BigDecimal volumeDiscountAmount6;

	@Column(name = "VolumeDiscountAmount7")
	private BigDecimal volumeDiscountAmount7;

	@Column(name = "VolumeDiscountHighLimit1")
	private String volumeDiscountHighLimit1;

	@Column(name = "VolumeDiscountHighLimit2")
	private String volumeDiscountHighLimit2;

	@Column(name = "VolumeDiscountHighLimit3")
	private String volumeDiscountHighLimit3;

	@Column(name = "VolumeDiscountHighLimit4")
	private String volumeDiscountHighLimit4;

	@Column(name = "VolumeDiscountHighLimit5")
	private String volumeDiscountHighLimit5;

	@Column(name = "VolumeDiscountHighLimit6")
	private String volumeDiscountHighLimit6;

	@Column(name = "VolumeDiscountHighLimit7")
	private String volumeDiscountHighLimit7;

	@Column(name = "VolumeDiscountLowLimit1")
	private String volumeDiscountLowLimit1;

	@Column(name = "VolumeDiscountLowLimit2")
	private String volumeDiscountLowLimit2;

	@Column(name = "VolumeDiscountLowLimit3")
	private String volumeDiscountLowLimit3;

	@Column(name = "VolumeDiscountLowLimit4")
	private String volumeDiscountLowLimit4;

	@Column(name = "VolumeDiscountLowLimit5")
	private String volumeDiscountLowLimit5;

	@Column(name = "VolumeDiscountLowLimit6")
	private String volumeDiscountLowLimit6;

	@Column(name = "VolumeDiscountLowLimit7")
	private String volumeDiscountLowLimit7;

	@Column(name = "VolumeDiscountType1")
	private String volumeDiscountType1;

	@Column(name = "VolumeDiscountType2")
	private String volumeDiscountType2;

	@Column(name = "VolumeDiscountType3")
	private String volumeDiscountType3;

	@Column(name = "VolumeDiscountType4")
	private String volumeDiscountType4;

	@Column(name = "VolumeDiscountType5")
	private String volumeDiscountType5;

	@Column(name = "VolumeDiscountType6")
	private String volumeDiscountType6;

	@Column(name = "VolumeDiscountType7")
	private String volumeDiscountType7;

	@Column(name = "Warrenty")
	private String warrenty;

	// bi-directional many-to-one association to PSBillingType
	@ManyToOne
	@JoinColumn(name = "PSBillingTypeId")
	private PSBillingType psBillingType;

	// bi-directional many-to-one association to PSOperatingUnitOrVertical
	@ManyToOne
	@JoinColumn(name = "PSVerticalId")
	private PSOperatingUnitOrVertical psOperatingUnitOrVertical;

	// bi-directional many-to-one association to PSProduct
	@ManyToOne
	@JoinColumn(name = "PSProductId")
	private PSProduct pSProduct;

	// bi-directional many-to-one association to PSSalesforceClient
	@ManyToOne
	@JoinColumn(name = "PSSalesforceClientId")
	private PSSalesforceClient pSSalesforceClient;

	// bi-directional many-to-one association to PSServiceArea
	@ManyToOne
	@JoinColumn(name = "PSServiceAreaId")
	private PSServiceArea pSServiceArea;

	public AnalyserClient() {
	}

	/**
	 * @return the clientId
	 */
	public Integer getClientId() {
		return clientId;
	}

	/**
	 * @param clientId
	 *            the clientId to set
	 */
	public void setClientId(Integer clientId) {
		this.clientId = clientId;
	}

	/**
	 * @return the adminFee
	 */
	public String getAdminFee() {
		return adminFee;
	}

	/**
	 * @param adminFee
	 *            the adminFee to set
	 */
	public void setAdminFee(String adminFee) {
		this.adminFee = adminFee;
	}

	/**
	 * @return the agreementNumber
	 */
	public String getAgreementNumber() {
		return agreementNumber;
	}

	/**
	 * @param agreementNumber
	 *            the agreementNumber to set
	 */
	public void setAgreementNumber(String agreementNumber) {
		this.agreementNumber = agreementNumber;
	}

	/**
	 * @return the agreementType
	 */
	public String getAgreementType() {
		return agreementType;
	}

	/**
	 * @param agreementType
	 *            the agreementType to set
	 */
	public void setAgreementType(String agreementType) {
		this.agreementType = agreementType;
	}

	/**
	 * @return the amount1
	 */
	public String getAmount1() {
		return amount1;
	}

	/**
	 * @param amount1
	 *            the amount1 to set
	 */
	public void setAmount1(String amount1) {
		this.amount1 = amount1;
	}

	/**
	 * @return the backgroundAmount
	 */
	public BigDecimal getBackgroundAmount() {
		return backgroundAmount;
	}

	/**
	 * @param backgroundAmount
	 *            the backgroundAmount to set
	 */
	public void setBackgroundAmount(BigDecimal backgroundAmount) {
		this.backgroundAmount = backgroundAmount;
	}

	/**
	 * @return the backgroundCheck
	 */
	public String getBackgroundCheck() {
		return backgroundCheck;
	}

	/**
	 * @param backgroundCheck
	 *            the backgroundCheck to set
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
	 * @param backgroundCheckInfo
	 *            the backgroundCheckInfo to set
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
	 * @param backgroundCompany
	 *            the backgroundCompany to set
	 */
	public void setBackgroundCompany(String backgroundCompany) {
		this.backgroundCompany = backgroundCompany;
	}

	/**
	 * @return the chkBackgroundAmount
	 */
	public String getChkBackgroundAmount() {
		return chkBackgroundAmount;
	}

	/**
	 * @param chkBackgroundAmount
	 *            the chkBackgroundAmount to set
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
	 * @param chkCreditCheckAmount
	 *            the chkCreditCheckAmount to set
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
	 * @param chkDrugTestAmount
	 *            the chkDrugTestAmount to set
	 */
	public void setChkDrugTestAmount(String chkDrugTestAmount) {
		this.chkDrugTestAmount = chkDrugTestAmount;
	}

	/**
	 * @return the chkOtherDiscount
	 */
	public String getChkOtherDiscount() {
		return chkOtherDiscount;
	}

	/**
	 * @param chkOtherDiscount
	 *            the chkOtherDiscount to set
	 */
	public void setChkOtherDiscount(String chkOtherDiscount) {
		this.chkOtherDiscount = chkOtherDiscount;
	}

	/**
	 * @return the chkPaymentDiscount
	 */
	public String getChkPaymentDiscount() {
		return chkPaymentDiscount;
	}

	/**
	 * @param chkPaymentDiscount
	 *            the chkPaymentDiscount to set
	 */
	public void setChkPaymentDiscount(String chkPaymentDiscount) {
		this.chkPaymentDiscount = chkPaymentDiscount;
	}

	/**
	 * @return the chkVolumeDiscount
	 */
	public String getChkVolumeDiscount() {
		return chkVolumeDiscount;
	}

	/**
	 * @param chkVolumeDiscount
	 *            the chkVolumeDiscount to set
	 */
	public void setChkVolumeDiscount(String chkVolumeDiscount) {
		this.chkVolumeDiscount = chkVolumeDiscount;
	}

	/**
	 * @return the clientIndustry
	 */
	public String getClientIndustry() {
		return clientIndustry;
	}

	/**
	 * @param clientIndustry
	 *            the clientIndustry to set
	 */
	public void setClientIndustry(String clientIndustry) {
		this.clientIndustry = clientIndustry;
	}

	/**
	 * @return the company
	 */
	public String getCompany() {
		return company;
	}

	/**
	 * @param company
	 *            the company to set
	 */
	public void setCompany(String company) {
		this.company = company;
	}

	/**
	 * @return the contactAddress
	 */
	public String getContactAddress() {
		return contactAddress;
	}

	/**
	 * @param contactAddress
	 *            the contactAddress to set
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
	 * @param contactCity
	 *            the contactCity to set
	 */
	public void setContactCity(String contactCity) {
		this.contactCity = contactCity;
	}

	/**
	 * @return the contactEmail
	 */
	public String getContactEmail() {
		return contactEmail;
	}

	/**
	 * @param contactEmail
	 *            the contactEmail to set
	 */
	public void setContactEmail(String contactEmail) {
		this.contactEmail = contactEmail;
	}

	/**
	 * @return the contactFax
	 */
	public String getContactFax() {
		return contactFax;
	}

	/**
	 * @param contactFax
	 *            the contactFax to set
	 */
	public void setContactFax(String contactFax) {
		this.contactFax = contactFax;
	}

	/**
	 * @return the contactName
	 */
	public String getContactName() {
		return contactName;
	}

	/**
	 * @param contactName
	 *            the contactName to set
	 */
	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	/**
	 * @return the contactPhone
	 */
	public String getContactPhone() {
		return contactPhone;
	}

	/**
	 * @param contactPhone
	 *            the contactPhone to set
	 */
	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}

	/**
	 * @return the contactState
	 */
	public String getContactState() {
		return contactState;
	}

	/**
	 * @param contactState
	 *            the contactState to set
	 */
	public void setContactState(String contactState) {
		this.contactState = contactState;
	}

	/**
	 * @return the contactTitle
	 */
	public String getContactTitle() {
		return contactTitle;
	}

	/**
	 * @param contactTitle
	 *            the contactTitle to set
	 */
	public void setContactTitle(String contactTitle) {
		this.contactTitle = contactTitle;
	}

	/**
	 * @return the contactZip
	 */
	public String getContactZip() {
		return contactZip;
	}

	/**
	 * @param contactZip
	 *            the contactZip to set
	 */
	public void setContactZip(String contactZip) {
		this.contactZip = contactZip;
	}

	/**
	 * @return the contractLengthFrom
	 */
	public String getContractLengthFrom() {
		return contractLengthFrom;
	}

	/**
	 * @param contractLengthFrom
	 *            the contractLengthFrom to set
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
	 * @param contractLengthTo
	 *            the contractLengthTo to set
	 */
	public void setContractLengthTo(String contractLengthTo) {
		this.contractLengthTo = contractLengthTo;
	}

	/**
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * @param country
	 *            the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	/**
	 * @return the creditCheck
	 */
	public String getCreditCheck() {
		return creditCheck;
	}

	/**
	 * @param creditCheck
	 *            the creditCheck to set
	 */
	public void setCreditCheck(String creditCheck) {
		this.creditCheck = creditCheck;
	}

	/**
	 * @return the creditCheckAmount
	 */
	public BigDecimal getCreditCheckAmount() {
		return creditCheckAmount;
	}

	/**
	 * @param creditCheckAmount
	 *            the creditCheckAmount to set
	 */
	public void setCreditCheckAmount(BigDecimal creditCheckAmount) {
		this.creditCheckAmount = creditCheckAmount;
	}

	/**
	 * @return the creditCheckInfo
	 */
	public String getCreditCheckInfo() {
		return creditCheckInfo;
	}

	/**
	 * @param creditCheckInfo
	 *            the creditCheckInfo to set
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
	 * @param creditCompany
	 *            the creditCompany to set
	 */
	public void setCreditCompany(String creditCompany) {
		this.creditCompany = creditCompany;
	}

	/**
	 * @return the discountRemarks
	 */
	public String getDiscountRemarks() {
		return discountRemarks;
	}

	/**
	 * @param discountRemarks
	 *            the discountRemarks to set
	 */
	public void setDiscountRemarks(String discountRemarks) {
		this.discountRemarks = discountRemarks;
	}

	/**
	 * @return the disputeTimeframe
	 */
	public String getDisputeTimeframe() {
		return disputeTimeframe;
	}

	/**
	 * @param disputeTimeframe
	 *            the disputeTimeframe to set
	 */
	public void setDisputeTimeframe(String disputeTimeframe) {
		this.disputeTimeframe = disputeTimeframe;
	}

	/**
	 * @return the drugTestAmount
	 */
	public BigDecimal getDrugTestAmount() {
		return drugTestAmount;
	}

	/**
	 * @param drugTestAmount
	 *            the drugTestAmount to set
	 */
	public void setDrugTestAmount(BigDecimal drugTestAmount) {
		this.drugTestAmount = drugTestAmount;
	}

	/**
	 * @return the drugTesting
	 */
	public String getDrugTesting() {
		return drugTesting;
	}

	/**
	 * @param drugTesting
	 *            the drugTesting to set
	 */
	public void setDrugTesting(String drugTesting) {
		this.drugTesting = drugTesting;
	}

	/**
	 * @return the drugTestingCompany
	 */
	public String getDrugTestingCompany() {
		return drugTestingCompany;
	}

	/**
	 * @param drugTestingCompany
	 *            the drugTestingCompany to set
	 */
	public void setDrugTestingCompany(String drugTestingCompany) {
		this.drugTestingCompany = drugTestingCompany;
	}

	/**
	 * @return the drugTestingInfo
	 */
	public String getDrugTestingInfo() {
		return drugTestingInfo;
	}

	/**
	 * @param drugTestingInfo
	 *            the drugTestingInfo to set
	 */
	public void setDrugTestingInfo(String drugTestingInfo) {
		this.drugTestingInfo = drugTestingInfo;
	}

	/**
	 * @return the eftSetup
	 */
	public String getEftSetup() {
		return eftSetup;
	}

	/**
	 * @param eftSetup
	 *            the eftSetup to set
	 */
	public void setEftSetup(String eftSetup) {
		this.eftSetup = eftSetup;
	}

	/**
	 * @return the endClient
	 */
	public String getEndClient() {
		return endClient;
	}

	/**
	 * @param endClient
	 *            the endClient to set
	 */
	public void setEndClient(String endClient) {
		this.endClient = endClient;
	}

	/**
	 * @return the enteredby
	 */
	public String getEnteredby() {
		return enteredby;
	}

	/**
	 * @param enteredby
	 *            the enteredby to set
	 */
	public void setEnteredby(String enteredby) {
		this.enteredby = enteredby;
	}

	/**
	 * @return the entrydate
	 */
	public Timestamp getEntrydate() {
		return entrydate;
	}

	/**
	 * @param entrydate
	 *            the entrydate to set
	 */
	public void setEntrydate(Timestamp entrydate) {
		this.entrydate = entrydate;
	}

	/**
	 * @return the insAuto
	 */
	public String getInsAuto() {
		return insAuto;
	}

	/**
	 * @param insAuto
	 *            the insAuto to set
	 */
	public void setInsAuto(String insAuto) {
		this.insAuto = insAuto;
	}

	/**
	 * @return the insBondAmount
	 */
	public String getInsBondAmount() {
		return insBondAmount;
	}

	/**
	 * @param insBondAmount
	 *            the insBondAmount to set
	 */
	public void setInsBondAmount(String insBondAmount) {
		this.insBondAmount = insBondAmount;
	}

	/**
	 * @return the insCancelDays
	 */
	public String getInsCancelDays() {
		return insCancelDays;
	}

	/**
	 * @param insCancelDays
	 *            the insCancelDays to set
	 */
	public void setInsCancelDays(String insCancelDays) {
		this.insCancelDays = insCancelDays;
	}

	/**
	 * @return the insCgl
	 */
	public String getInsCgl() {
		return insCgl;
	}

	/**
	 * @param insCgl
	 *            the insCgl to set
	 */
	public void setInsCgl(String insCgl) {
		this.insCgl = insCgl;
	}

	/**
	 * @return the insEmployers
	 */
	public String getInsEmployers() {
		return insEmployers;
	}

	/**
	 * @param insEmployers
	 *            the insEmployers to set
	 */
	public void setInsEmployers(String insEmployers) {
		this.insEmployers = insEmployers;
	}

	/**
	 * @return the insPolicyDescription
	 */
	public String getInsPolicyDescription() {
		return insPolicyDescription;
	}

	/**
	 * @param insPolicyDescription
	 *            the insPolicyDescription to set
	 */
	public void setInsPolicyDescription(String insPolicyDescription) {
		this.insPolicyDescription = insPolicyDescription;
	}

	/**
	 * @return the insProfessionalErrors
	 */
	public String getInsProfessionalErrors() {
		return insProfessionalErrors;
	}

	/**
	 * @param insProfessionalErrors
	 *            the insProfessionalErrors to set
	 */
	public void setInsProfessionalErrors(String insProfessionalErrors) {
		this.insProfessionalErrors = insProfessionalErrors;
	}

	/**
	 * @return the insThirdPartyCrimeBond
	 */
	public String getInsThirdPartyCrimeBond() {
		return insThirdPartyCrimeBond;
	}

	/**
	 * @param insThirdPartyCrimeBond
	 *            the insThirdPartyCrimeBond to set
	 */
	public void setInsThirdPartyCrimeBond(String insThirdPartyCrimeBond) {
		this.insThirdPartyCrimeBond = insThirdPartyCrimeBond;
	}

	/**
	 * @return the insUmbrella
	 */
	public String getInsUmbrella() {
		return insUmbrella;
	}

	/**
	 * @param insUmbrella
	 *            the insUmbrella to set
	 */
	public void setInsUmbrella(String insUmbrella) {
		this.insUmbrella = insUmbrella;
	}

	/**
	 * @return the insuranceCertificate
	 */
	public String getInsuranceCertificate() {
		return insuranceCertificate;
	}

	/**
	 * @param insuranceCertificate
	 *            the insuranceCertificate to set
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
	 * @param insWaiver
	 *            the insWaiver to set
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
	 * @param insWorkmans
	 *            the insWorkmans to set
	 */
	public void setInsWorkmans(String insWorkmans) {
		this.insWorkmans = insWorkmans;
	}

	/**
	 * @return the invoiceFrequency
	 */
	public String getInvoiceFrequency() {
		return invoiceFrequency;
	}

	/**
	 * @param invoiceFrequency
	 *            the invoiceFrequency to set
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
	 * @param invoicingAddressInfo
	 *            the invoicingAddressInfo to set
	 */
	public void setInvoicingAddressInfo(String invoicingAddressInfo) {
		this.invoicingAddressInfo = invoicingAddressInfo;
	}

	/**
	 * @return the mutualConfidentiality
	 */
	public String getMutualConfidentiality() {
		return mutualConfidentiality;
	}

	/**
	 * @param mutualConfidentiality
	 *            the mutualConfidentiality to set
	 */
	public void setMutualConfidentiality(String mutualConfidentiality) {
		this.mutualConfidentiality = mutualConfidentiality;
	}

	/**
	 * @return the nonComplete
	 */
	public String getNonComplete() {
		return nonComplete;
	}

	/**
	 * @param nonComplete
	 *            the nonComplete to set
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
	 * @param nonCompleteInformation
	 *            the nonCompleteInformation to set
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
	 * @param nonSolicitation
	 *            the nonSolicitation to set
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
	 * @param nonSolicitationTimeframe
	 *            the nonSolicitationTimeframe to set
	 */
	public void setNonSolicitationTimeframe(String nonSolicitationTimeframe) {
		this.nonSolicitationTimeframe = nonSolicitationTimeframe;
	}

	/**
	 * @return the noticeAddress
	 */
	public String getNoticeAddress() {
		return noticeAddress;
	}

	/**
	 * @param noticeAddress
	 *            the noticeAddress to set
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
	 * @param noticeCity
	 *            the noticeCity to set
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
	 * @param noticeState
	 *            the noticeState to set
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
	 * @param noticeZip
	 *            the noticeZip to set
	 */
	public void setNoticeZip(String noticeZip) {
		this.noticeZip = noticeZip;
	}

	/**
	 * @return the orgId
	 */
	public Integer getOrgId() {
		return orgId;
	}

	/**
	 * @param orgId
	 *            the orgId to set
	 */
	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}

	/**
	 * @return the otherAgreement
	 */
	public String getOtherAgreement() {
		return otherAgreement;
	}

	/**
	 * @param otherAgreement
	 *            the otherAgreement to set
	 */
	public void setOtherAgreement(String otherAgreement) {
		this.otherAgreement = otherAgreement;
	}

	/**
	 * @return the otherDiscount
	 */
	public BigDecimal getOtherDiscount() {
		return otherDiscount;
	}

	/**
	 * @param otherDiscount
	 *            the otherDiscount to set
	 */
	public void setOtherDiscount(BigDecimal otherDiscount) {
		this.otherDiscount = otherDiscount;
	}

	/**
	 * @return the overtime
	 */
	public String getOvertime() {
		return overtime;
	}

	/**
	 * @param overtime
	 *            the overtime to set
	 */
	public void setOvertime(String overtime) {
		this.overtime = overtime;
	}

	/**
	 * @return the paymentDiscount
	 */
	public BigDecimal getPaymentDiscount() {
		return paymentDiscount;
	}

	/**
	 * @param paymentDiscount
	 *            the paymentDiscount to set
	 */
	public void setPaymentDiscount(BigDecimal paymentDiscount) {
		this.paymentDiscount = paymentDiscount;
	}

	/**
	 * @return the paymentTerms
	 */
	public String getPaymentTerms() {
		return paymentTerms;
	}

	/**
	 * @param paymentTerms
	 *            the paymentTerms to set
	 */
	public void setPaymentTerms(String paymentTerms) {
		this.paymentTerms = paymentTerms;
	}

	/**
	 * @return the personalAssignedForms
	 */
	public String getPersonalAssignedForms() {
		return personalAssignedForms;
	}

	/**
	 * @param personalAssignedForms
	 *            the personalAssignedForms to set
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
	 * @param personalForms
	 *            the personalForms to set
	 */
	public void setPersonalForms(String personalForms) {
		this.personalForms = personalForms;
	}

	/**
	 * @return the pricingInfo
	 */
	public String getPricingInfo() {
		return pricingInfo;
	}

	/**
	 * @param pricingInfo
	 *            the pricingInfo to set
	 */
	public void setPricingInfo(String pricingInfo) {
		this.pricingInfo = pricingInfo;
	}

	/**
	 * @return the productDescription
	 */
	public String getProductDescription() {
		return productDescription;
	}

	/**
	 * @param productDescription
	 *            the productDescription to set
	 */
	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	/**
	 * @return the promptPay
	 */
	public String getPromptPay() {
		return promptPay;
	}

	/**
	 * @param promptPay
	 *            the promptPay to set
	 */
	public void setPromptPay(String promptPay) {
		this.promptPay = promptPay;
	}

	/**
	 * @return the renewals
	 */
	public String getRenewals() {
		return renewals;
	}

	/**
	 * @param renewals
	 *            the renewals to set
	 */
	public void setRenewals(String renewals) {
		this.renewals = renewals;
	}

	/**
	 * @return the replacementGuarentee
	 */
	public String getReplacementGuarentee() {
		return replacementGuarentee;
	}

	/**
	 * @param replacementGuarentee
	 *            the replacementGuarentee to set
	 */
	public void setReplacementGuarentee(String replacementGuarentee) {
		this.replacementGuarentee = replacementGuarentee;
	}

	/**
	 * @return the rightToHire
	 */
	public String getRightToHire() {
		return rightToHire;
	}

	/**
	 * @param rightToHire
	 *            the rightToHire to set
	 */
	public void setRightToHire(String rightToHire) {
		this.rightToHire = rightToHire;
	}

	/**
	 * @return the rightToHireFees
	 */
	public String getRightToHireFees() {
		return rightToHireFees;
	}

	/**
	 * @param rightToHireFees
	 *            the rightToHireFees to set
	 */
	public void setRightToHireFees(String rightToHireFees) {
		this.rightToHireFees = rightToHireFees;
	}

	/**
	 * @return the rightToHireMonths
	 */
	public String getRightToHireMonths() {
		return rightToHireMonths;
	}

	/**
	 * @param rightToHireMonths
	 *            the rightToHireMonths to set
	 */
	public void setRightToHireMonths(String rightToHireMonths) {
		this.rightToHireMonths = rightToHireMonths;
	}

	/**
	 * @return the status
	 */
	public Integer getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/**
	 * @return the subContractingAllowed
	 */
	public String getSubContractingAllowed() {
		return subContractingAllowed;
	}

	/**
	 * @param subContractingAllowed
	 *            the subContractingAllowed to set
	 */
	public void setSubContractingAllowed(String subContractingAllowed) {
		this.subContractingAllowed = subContractingAllowed;
	}

	/**
	 * @return the tenureDiscountAmount1
	 */
	public BigDecimal getTenureDiscountAmount1() {
		return tenureDiscountAmount1;
	}

	/**
	 * @param tenureDiscountAmount1
	 *            the tenureDiscountAmount1 to set
	 */
	public void setTenureDiscountAmount1(BigDecimal tenureDiscountAmount1) {
		this.tenureDiscountAmount1 = tenureDiscountAmount1;
	}

	/**
	 * @return the tenureDiscountAmount2
	 */
	public BigDecimal getTenureDiscountAmount2() {
		return tenureDiscountAmount2;
	}

	/**
	 * @param tenureDiscountAmount2
	 *            the tenureDiscountAmount2 to set
	 */
	public void setTenureDiscountAmount2(BigDecimal tenureDiscountAmount2) {
		this.tenureDiscountAmount2 = tenureDiscountAmount2;
	}

	/**
	 * @return the tenureDiscountAmount3
	 */
	public BigDecimal getTenureDiscountAmount3() {
		return tenureDiscountAmount3;
	}

	/**
	 * @param tenureDiscountAmount3
	 *            the tenureDiscountAmount3 to set
	 */
	public void setTenureDiscountAmount3(BigDecimal tenureDiscountAmount3) {
		this.tenureDiscountAmount3 = tenureDiscountAmount3;
	}

	/**
	 * @return the tenureDiscountAmount4
	 */
	public BigDecimal getTenureDiscountAmount4() {
		return tenureDiscountAmount4;
	}

	/**
	 * @param tenureDiscountAmount4
	 *            the tenureDiscountAmount4 to set
	 */
	public void setTenureDiscountAmount4(BigDecimal tenureDiscountAmount4) {
		this.tenureDiscountAmount4 = tenureDiscountAmount4;
	}

	/**
	 * @return the tenureDiscountAmount5
	 */
	public BigDecimal getTenureDiscountAmount5() {
		return tenureDiscountAmount5;
	}

	/**
	 * @param tenureDiscountAmount5
	 *            the tenureDiscountAmount5 to set
	 */
	public void setTenureDiscountAmount5(BigDecimal tenureDiscountAmount5) {
		this.tenureDiscountAmount5 = tenureDiscountAmount5;
	}

	/**
	 * @return the tenureDiscountHighLimit1
	 */
	public String getTenureDiscountHighLimit1() {
		return tenureDiscountHighLimit1;
	}

	/**
	 * @param tenureDiscountHighLimit1
	 *            the tenureDiscountHighLimit1 to set
	 */
	public void setTenureDiscountHighLimit1(String tenureDiscountHighLimit1) {
		this.tenureDiscountHighLimit1 = tenureDiscountHighLimit1;
	}

	/**
	 * @return the tenureDiscountHighLimit2
	 */
	public String getTenureDiscountHighLimit2() {
		return tenureDiscountHighLimit2;
	}

	/**
	 * @param tenureDiscountHighLimit2
	 *            the tenureDiscountHighLimit2 to set
	 */
	public void setTenureDiscountHighLimit2(String tenureDiscountHighLimit2) {
		this.tenureDiscountHighLimit2 = tenureDiscountHighLimit2;
	}

	/**
	 * @return the tenureDiscountHighLimit3
	 */
	public String getTenureDiscountHighLimit3() {
		return tenureDiscountHighLimit3;
	}

	/**
	 * @param tenureDiscountHighLimit3
	 *            the tenureDiscountHighLimit3 to set
	 */
	public void setTenureDiscountHighLimit3(String tenureDiscountHighLimit3) {
		this.tenureDiscountHighLimit3 = tenureDiscountHighLimit3;
	}

	/**
	 * @return the tenureDiscountHighLimit4
	 */
	public String getTenureDiscountHighLimit4() {
		return tenureDiscountHighLimit4;
	}

	/**
	 * @param tenureDiscountHighLimit4
	 *            the tenureDiscountHighLimit4 to set
	 */
	public void setTenureDiscountHighLimit4(String tenureDiscountHighLimit4) {
		this.tenureDiscountHighLimit4 = tenureDiscountHighLimit4;
	}

	/**
	 * @return the tenureDiscountHighLimit5
	 */
	public String getTenureDiscountHighLimit5() {
		return tenureDiscountHighLimit5;
	}

	/**
	 * @param tenureDiscountHighLimit5
	 *            the tenureDiscountHighLimit5 to set
	 */
	public void setTenureDiscountHighLimit5(String tenureDiscountHighLimit5) {
		this.tenureDiscountHighLimit5 = tenureDiscountHighLimit5;
	}

	/**
	 * @return the tenureDiscountLowLimit1
	 */
	public String getTenureDiscountLowLimit1() {
		return tenureDiscountLowLimit1;
	}

	/**
	 * @param tenureDiscountLowLimit1
	 *            the tenureDiscountLowLimit1 to set
	 */
	public void setTenureDiscountLowLimit1(String tenureDiscountLowLimit1) {
		this.tenureDiscountLowLimit1 = tenureDiscountLowLimit1;
	}

	/**
	 * @return the tenureDiscountLowLimit2
	 */
	public String getTenureDiscountLowLimit2() {
		return tenureDiscountLowLimit2;
	}

	/**
	 * @param tenureDiscountLowLimit2
	 *            the tenureDiscountLowLimit2 to set
	 */
	public void setTenureDiscountLowLimit2(String tenureDiscountLowLimit2) {
		this.tenureDiscountLowLimit2 = tenureDiscountLowLimit2;
	}

	/**
	 * @return the tenureDiscountLowLimit3
	 */
	public String getTenureDiscountLowLimit3() {
		return tenureDiscountLowLimit3;
	}

	/**
	 * @param tenureDiscountLowLimit3
	 *            the tenureDiscountLowLimit3 to set
	 */
	public void setTenureDiscountLowLimit3(String tenureDiscountLowLimit3) {
		this.tenureDiscountLowLimit3 = tenureDiscountLowLimit3;
	}

	/**
	 * @return the tenureDiscountLowLimit4
	 */
	public String getTenureDiscountLowLimit4() {
		return tenureDiscountLowLimit4;
	}

	/**
	 * @param tenureDiscountLowLimit4
	 *            the tenureDiscountLowLimit4 to set
	 */
	public void setTenureDiscountLowLimit4(String tenureDiscountLowLimit4) {
		this.tenureDiscountLowLimit4 = tenureDiscountLowLimit4;
	}

	/**
	 * @return the tenureDiscountLowLimit5
	 */
	public String getTenureDiscountLowLimit5() {
		return tenureDiscountLowLimit5;
	}

	/**
	 * @param tenureDiscountLowLimit5
	 *            the tenureDiscountLowLimit5 to set
	 */
	public void setTenureDiscountLowLimit5(String tenureDiscountLowLimit5) {
		this.tenureDiscountLowLimit5 = tenureDiscountLowLimit5;
	}

	/**
	 * @return the termEnd
	 */
	public String getTermEnd() {
		return termEnd;
	}

	/**
	 * @param termEnd
	 *            the termEnd to set
	 */
	public void setTermEnd(String termEnd) {
		this.termEnd = termEnd;
	}

	/**
	 * @return the termination
	 */
	public String getTermination() {
		return termination;
	}

	/**
	 * @param termination
	 *            the termination to set
	 */
	public void setTermination(String termination) {
		this.termination = termination;
	}

	/**
	 * @return the termStart
	 */
	public String getTermStart() {
		return termStart;
	}

	/**
	 * @param termStart
	 *            the termStart to set
	 */
	public void setTermStart(String termStart) {
		this.termStart = termStart;
	}

	/**
	 * @return the timesheetFrequency
	 */
	public String getTimesheetFrequency() {
		return timesheetFrequency;
	}

	/**
	 * @param timesheetFrequency
	 *            the timesheetFrequency to set
	 */
	public void setTimesheetFrequency(String timesheetFrequency) {
		this.timesheetFrequency = timesheetFrequency;
	}

	/**
	 * @return the tmContract
	 */
	public String getTmContract() {
		return tmContract;
	}

	/**
	 * @param tmContract
	 *            the tmContract to set
	 */
	public void setTmContract(String tmContract) {
		this.tmContract = tmContract;
	}

	/**
	 * @return the travelFees
	 */
	public String getTravelFees() {
		return travelFees;
	}

	/**
	 * @param travelFees
	 *            the travelFees to set
	 */
	public void setTravelFees(String travelFees) {
		this.travelFees = travelFees;
	}

	/**
	 * @return the verticalDescription
	 */
	public String getVerticalDescription() {
		return verticalDescription;
	}

	/**
	 * @param verticalDescription
	 *            the verticalDescription to set
	 */
	public void setVerticalDescription(String verticalDescription) {
		this.verticalDescription = verticalDescription;
	}

	/**
	 * @return the vms
	 */
	public String getVms() {
		return vms;
	}

	/**
	 * @param vms
	 *            the vms to set
	 */
	public void setVms(String vms) {
		this.vms = vms;
	}

	/**
	 * @return the volumeDiscount
	 */
	public BigDecimal getVolumeDiscount() {
		return volumeDiscount;
	}

	/**
	 * @param volumeDiscount
	 *            the volumeDiscount to set
	 */
	public void setVolumeDiscount(BigDecimal volumeDiscount) {
		this.volumeDiscount = volumeDiscount;
	}

	/**
	 * @return the volumeDiscountAmount1
	 */
	public BigDecimal getVolumeDiscountAmount1() {
		return volumeDiscountAmount1;
	}

	/**
	 * @param volumeDiscountAmount1
	 *            the volumeDiscountAmount1 to set
	 */
	public void setVolumeDiscountAmount1(BigDecimal volumeDiscountAmount1) {
		this.volumeDiscountAmount1 = volumeDiscountAmount1;
	}

	/**
	 * @return the volumeDiscountAmount2
	 */
	public BigDecimal getVolumeDiscountAmount2() {
		return volumeDiscountAmount2;
	}

	/**
	 * @param volumeDiscountAmount2
	 *            the volumeDiscountAmount2 to set
	 */
	public void setVolumeDiscountAmount2(BigDecimal volumeDiscountAmount2) {
		this.volumeDiscountAmount2 = volumeDiscountAmount2;
	}

	/**
	 * @return the volumeDiscountAmount3
	 */
	public BigDecimal getVolumeDiscountAmount3() {
		return volumeDiscountAmount3;
	}

	/**
	 * @param volumeDiscountAmount3
	 *            the volumeDiscountAmount3 to set
	 */
	public void setVolumeDiscountAmount3(BigDecimal volumeDiscountAmount3) {
		this.volumeDiscountAmount3 = volumeDiscountAmount3;
	}

	/**
	 * @return the volumeDiscountAmount4
	 */
	public BigDecimal getVolumeDiscountAmount4() {
		return volumeDiscountAmount4;
	}

	/**
	 * @param volumeDiscountAmount4
	 *            the volumeDiscountAmount4 to set
	 */
	public void setVolumeDiscountAmount4(BigDecimal volumeDiscountAmount4) {
		this.volumeDiscountAmount4 = volumeDiscountAmount4;
	}

	/**
	 * @return the volumeDiscountAmount5
	 */
	public BigDecimal getVolumeDiscountAmount5() {
		return volumeDiscountAmount5;
	}

	/**
	 * @param volumeDiscountAmount5
	 *            the volumeDiscountAmount5 to set
	 */
	public void setVolumeDiscountAmount5(BigDecimal volumeDiscountAmount5) {
		this.volumeDiscountAmount5 = volumeDiscountAmount5;
	}

	/**
	 * @return the volumeDiscountAmount6
	 */
	public BigDecimal getVolumeDiscountAmount6() {
		return volumeDiscountAmount6;
	}

	/**
	 * @param volumeDiscountAmount6
	 *            the volumeDiscountAmount6 to set
	 */
	public void setVolumeDiscountAmount6(BigDecimal volumeDiscountAmount6) {
		this.volumeDiscountAmount6 = volumeDiscountAmount6;
	}

	/**
	 * @return the volumeDiscountAmount7
	 */
	public BigDecimal getVolumeDiscountAmount7() {
		return volumeDiscountAmount7;
	}

	/**
	 * @param volumeDiscountAmount7
	 *            the volumeDiscountAmount7 to set
	 */
	public void setVolumeDiscountAmount7(BigDecimal volumeDiscountAmount7) {
		this.volumeDiscountAmount7 = volumeDiscountAmount7;
	}

	/**
	 * @return the volumeDiscountHighLimit1
	 */
	public String getVolumeDiscountHighLimit1() {
		return volumeDiscountHighLimit1;
	}

	/**
	 * @param volumeDiscountHighLimit1
	 *            the volumeDiscountHighLimit1 to set
	 */
	public void setVolumeDiscountHighLimit1(String volumeDiscountHighLimit1) {
		this.volumeDiscountHighLimit1 = volumeDiscountHighLimit1;
	}

	/**
	 * @return the volumeDiscountHighLimit2
	 */
	public String getVolumeDiscountHighLimit2() {
		return volumeDiscountHighLimit2;
	}

	/**
	 * @param volumeDiscountHighLimit2
	 *            the volumeDiscountHighLimit2 to set
	 */
	public void setVolumeDiscountHighLimit2(String volumeDiscountHighLimit2) {
		this.volumeDiscountHighLimit2 = volumeDiscountHighLimit2;
	}

	/**
	 * @return the volumeDiscountHighLimit3
	 */
	public String getVolumeDiscountHighLimit3() {
		return volumeDiscountHighLimit3;
	}

	/**
	 * @param volumeDiscountHighLimit3
	 *            the volumeDiscountHighLimit3 to set
	 */
	public void setVolumeDiscountHighLimit3(String volumeDiscountHighLimit3) {
		this.volumeDiscountHighLimit3 = volumeDiscountHighLimit3;
	}

	/**
	 * @return the volumeDiscountHighLimit4
	 */
	public String getVolumeDiscountHighLimit4() {
		return volumeDiscountHighLimit4;
	}

	/**
	 * @param volumeDiscountHighLimit4
	 *            the volumeDiscountHighLimit4 to set
	 */
	public void setVolumeDiscountHighLimit4(String volumeDiscountHighLimit4) {
		this.volumeDiscountHighLimit4 = volumeDiscountHighLimit4;
	}

	/**
	 * @return the volumeDiscountHighLimit5
	 */
	public String getVolumeDiscountHighLimit5() {
		return volumeDiscountHighLimit5;
	}

	/**
	 * @param volumeDiscountHighLimit5
	 *            the volumeDiscountHighLimit5 to set
	 */
	public void setVolumeDiscountHighLimit5(String volumeDiscountHighLimit5) {
		this.volumeDiscountHighLimit5 = volumeDiscountHighLimit5;
	}

	/**
	 * @return the volumeDiscountHighLimit6
	 */
	public String getVolumeDiscountHighLimit6() {
		return volumeDiscountHighLimit6;
	}

	/**
	 * @param volumeDiscountHighLimit6
	 *            the volumeDiscountHighLimit6 to set
	 */
	public void setVolumeDiscountHighLimit6(String volumeDiscountHighLimit6) {
		this.volumeDiscountHighLimit6 = volumeDiscountHighLimit6;
	}

	/**
	 * @return the volumeDiscountHighLimit7
	 */
	public String getVolumeDiscountHighLimit7() {
		return volumeDiscountHighLimit7;
	}

	/**
	 * @param volumeDiscountHighLimit7
	 *            the volumeDiscountHighLimit7 to set
	 */
	public void setVolumeDiscountHighLimit7(String volumeDiscountHighLimit7) {
		this.volumeDiscountHighLimit7 = volumeDiscountHighLimit7;
	}

	/**
	 * @return the volumeDiscountLowLimit1
	 */
	public String getVolumeDiscountLowLimit1() {
		return volumeDiscountLowLimit1;
	}

	/**
	 * @param volumeDiscountLowLimit1
	 *            the volumeDiscountLowLimit1 to set
	 */
	public void setVolumeDiscountLowLimit1(String volumeDiscountLowLimit1) {
		this.volumeDiscountLowLimit1 = volumeDiscountLowLimit1;
	}

	/**
	 * @return the volumeDiscountLowLimit2
	 */
	public String getVolumeDiscountLowLimit2() {
		return volumeDiscountLowLimit2;
	}

	/**
	 * @param volumeDiscountLowLimit2
	 *            the volumeDiscountLowLimit2 to set
	 */
	public void setVolumeDiscountLowLimit2(String volumeDiscountLowLimit2) {
		this.volumeDiscountLowLimit2 = volumeDiscountLowLimit2;
	}

	/**
	 * @return the volumeDiscountLowLimit3
	 */
	public String getVolumeDiscountLowLimit3() {
		return volumeDiscountLowLimit3;
	}

	/**
	 * @param volumeDiscountLowLimit3
	 *            the volumeDiscountLowLimit3 to set
	 */
	public void setVolumeDiscountLowLimit3(String volumeDiscountLowLimit3) {
		this.volumeDiscountLowLimit3 = volumeDiscountLowLimit3;
	}

	/**
	 * @return the volumeDiscountLowLimit4
	 */
	public String getVolumeDiscountLowLimit4() {
		return volumeDiscountLowLimit4;
	}

	/**
	 * @param volumeDiscountLowLimit4
	 *            the volumeDiscountLowLimit4 to set
	 */
	public void setVolumeDiscountLowLimit4(String volumeDiscountLowLimit4) {
		this.volumeDiscountLowLimit4 = volumeDiscountLowLimit4;
	}

	/**
	 * @return the volumeDiscountLowLimit5
	 */
	public String getVolumeDiscountLowLimit5() {
		return volumeDiscountLowLimit5;
	}

	/**
	 * @param volumeDiscountLowLimit5
	 *            the volumeDiscountLowLimit5 to set
	 */
	public void setVolumeDiscountLowLimit5(String volumeDiscountLowLimit5) {
		this.volumeDiscountLowLimit5 = volumeDiscountLowLimit5;
	}

	/**
	 * @return the volumeDiscountLowLimit6
	 */
	public String getVolumeDiscountLowLimit6() {
		return volumeDiscountLowLimit6;
	}

	/**
	 * @param volumeDiscountLowLimit6
	 *            the volumeDiscountLowLimit6 to set
	 */
	public void setVolumeDiscountLowLimit6(String volumeDiscountLowLimit6) {
		this.volumeDiscountLowLimit6 = volumeDiscountLowLimit6;
	}

	/**
	 * @return the volumeDiscountLowLimit7
	 */
	public String getVolumeDiscountLowLimit7() {
		return volumeDiscountLowLimit7;
	}

	/**
	 * @param volumeDiscountLowLimit7
	 *            the volumeDiscountLowLimit7 to set
	 */
	public void setVolumeDiscountLowLimit7(String volumeDiscountLowLimit7) {
		this.volumeDiscountLowLimit7 = volumeDiscountLowLimit7;
	}

	/**
	 * @return the volumeDiscountType1
	 */
	public String getVolumeDiscountType1() {
		return volumeDiscountType1;
	}

	/**
	 * @param volumeDiscountType1
	 *            the volumeDiscountType1 to set
	 */
	public void setVolumeDiscountType1(String volumeDiscountType1) {
		this.volumeDiscountType1 = volumeDiscountType1;
	}

	/**
	 * @return the volumeDiscountType2
	 */
	public String getVolumeDiscountType2() {
		return volumeDiscountType2;
	}

	/**
	 * @param volumeDiscountType2
	 *            the volumeDiscountType2 to set
	 */
	public void setVolumeDiscountType2(String volumeDiscountType2) {
		this.volumeDiscountType2 = volumeDiscountType2;
	}

	/**
	 * @return the volumeDiscountType3
	 */
	public String getVolumeDiscountType3() {
		return volumeDiscountType3;
	}

	/**
	 * @param volumeDiscountType3
	 *            the volumeDiscountType3 to set
	 */
	public void setVolumeDiscountType3(String volumeDiscountType3) {
		this.volumeDiscountType3 = volumeDiscountType3;
	}

	/**
	 * @return the volumeDiscountType4
	 */
	public String getVolumeDiscountType4() {
		return volumeDiscountType4;
	}

	/**
	 * @param volumeDiscountType4
	 *            the volumeDiscountType4 to set
	 */
	public void setVolumeDiscountType4(String volumeDiscountType4) {
		this.volumeDiscountType4 = volumeDiscountType4;
	}

	/**
	 * @return the volumeDiscountType5
	 */
	public String getVolumeDiscountType5() {
		return volumeDiscountType5;
	}

	/**
	 * @param volumeDiscountType5
	 *            the volumeDiscountType5 to set
	 */
	public void setVolumeDiscountType5(String volumeDiscountType5) {
		this.volumeDiscountType5 = volumeDiscountType5;
	}

	/**
	 * @return the volumeDiscountType6
	 */
	public String getVolumeDiscountType6() {
		return volumeDiscountType6;
	}

	/**
	 * @param volumeDiscountType6
	 *            the volumeDiscountType6 to set
	 */
	public void setVolumeDiscountType6(String volumeDiscountType6) {
		this.volumeDiscountType6 = volumeDiscountType6;
	}

	/**
	 * @return the volumeDiscountType7
	 */
	public String getVolumeDiscountType7() {
		return volumeDiscountType7;
	}

	/**
	 * @param volumeDiscountType7
	 *            the volumeDiscountType7 to set
	 */
	public void setVolumeDiscountType7(String volumeDiscountType7) {
		this.volumeDiscountType7 = volumeDiscountType7;
	}

	/**
	 * @return the warrenty
	 */
	public String getWarrenty() {
		return warrenty;
	}

	/**
	 * @param warrenty
	 *            the warrenty to set
	 */
	public void setWarrenty(String warrenty) {
		this.warrenty = warrenty;
	}

	/**
	 * @return the psBillingType
	 */
	public PSBillingType getPsBillingType() {
		return psBillingType;
	}

	/**
	 * @param psBillingType
	 *            the psBillingType to set
	 */
	public void setPsBillingType(PSBillingType psBillingType) {
		this.psBillingType = psBillingType;
	}

	/**
	 * @return the psOperatingUnitOrVertical
	 */
	public PSOperatingUnitOrVertical getPsOperatingUnitOrVertical() {
		return psOperatingUnitOrVertical;
	}

	/**
	 * @param psOperatingUnitOrVertical
	 *            the psOperatingUnitOrVertical to set
	 */
	public void setPsOperatingUnitOrVertical(
			PSOperatingUnitOrVertical psOperatingUnitOrVertical) {
		this.psOperatingUnitOrVertical = psOperatingUnitOrVertical;
	}

	/**
	 * @return the primeSub
	 */
	public String getPrimeSub() {
		return primeSub;
	}

	/**
	 * @param primeSub
	 *            the primeSub to set
	 */
	public void setPrimeSub(String primeSub) {
		this.primeSub = primeSub;
	}

	/**
	 * @return the pSBillingTypeCode
	 */
	public String getpSBillingTypeCode() {
		return pSBillingTypeCode;
	}

	/**
	 * @param pSBillingTypeCode
	 *            the pSBillingTypeCode to set
	 */
	public void setpSBillingTypeCode(String pSBillingTypeCode) {
		this.pSBillingTypeCode = pSBillingTypeCode;
	}

	/**
	 * @return the pSBillingTypeLongName
	 */
	public String getpSBillingTypeLongName() {
		return pSBillingTypeLongName;
	}

	/**
	 * @param pSBillingTypeLongName
	 *            the pSBillingTypeLongName to set
	 */
	public void setpSBillingTypeLongName(String pSBillingTypeLongName) {
		this.pSBillingTypeLongName = pSBillingTypeLongName;
	}

	/**
	 * @return the pSClientId
	 */
	public String getpSClientId() {
		return pSClientId;
	}

	/**
	 * @param pSClientId
	 *            the pSClientId to set
	 */
	public void setpSClientId(String pSClientId) {
		this.pSClientId = pSClientId;
	}

	/**
	 * @return the pSProductCode
	 */
	public String getpSProductCode() {
		return pSProductCode;
	}

	/**
	 * @param pSProductCode
	 *            the pSProductCode to set
	 */
	public void setpSProductCode(String pSProductCode) {
		this.pSProductCode = pSProductCode;
	}

	/**
	 * @return the pSSalesforceClientCode
	 */
	public String getpSSalesforceClientCode() {
		return pSSalesforceClientCode;
	}

	/**
	 * @param pSSalesforceClientCode
	 *            the pSSalesforceClientCode to set
	 */
	public void setpSSalesforceClientCode(String pSSalesforceClientCode) {
		this.pSSalesforceClientCode = pSSalesforceClientCode;
	}

	/**
	 * @return the pSSalesforceClientName
	 */
	public String getpSSalesforceClientName() {
		return pSSalesforceClientName;
	}

	/**
	 * @param pSSalesforceClientName
	 *            the pSSalesforceClientName to set
	 */
	public void setpSSalesforceClientName(String pSSalesforceClientName) {
		this.pSSalesforceClientName = pSSalesforceClientName;
	}

	/**
	 * @return the pSServiceAreaCode
	 */
	public String getpSServiceAreaCode() {
		return pSServiceAreaCode;
	}

	/**
	 * @param pSServiceAreaCode
	 *            the pSServiceAreaCode to set
	 */
	public void setpSServiceAreaCode(String pSServiceAreaCode) {
		this.pSServiceAreaCode = pSServiceAreaCode;
	}

	/**
	 * @return the pSServiceAreaLongName
	 */
	public String getpSServiceAreaLongName() {
		return pSServiceAreaLongName;
	}

	/**
	 * @param pSServiceAreaLongName
	 *            the pSServiceAreaLongName to set
	 */
	public void setpSServiceAreaLongName(String pSServiceAreaLongName) {
		this.pSServiceAreaLongName = pSServiceAreaLongName;
	}

	/**
	 * @return the pSVerticalCode
	 */
	public String getpSVerticalCode() {
		return pSVerticalCode;
	}

	/**
	 * @param pSVerticalCode
	 *            the pSVerticalCode to set
	 */
	public void setpSVerticalCode(String pSVerticalCode) {
		this.pSVerticalCode = pSVerticalCode;
	}

	/**
	 * @return the updatedBy
	 */
	public String getUpdatedBy() {
		return updatedBy;
	}

	/**
	 * @param updatedBy
	 *            the updatedBy to set
	 */
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	/**
	 * @return the updatedDate
	 */
	public Timestamp getUpdatedDate() {
		return updatedDate;
	}

	/**
	 * @param updatedDate
	 *            the updatedDate to set
	 */
	public void setUpdatedDate(Timestamp updatedDate) {
		this.updatedDate = updatedDate;
	}

	/**
	 * @return the vMSName
	 */
	public String getvMSName() {
		return vMSName;
	}

	/**
	 * @param vMSName
	 *            the vMSName to set
	 */
	public void setvMSName(String vMSName) {
		this.vMSName = vMSName;
	}

	/**
	 * @return the pSProduct
	 */
	public PSProduct getpSProduct() {
		return pSProduct;
	}

	/**
	 * @param pSProduct
	 *            the pSProduct to set
	 */
	public void setpSProduct(PSProduct pSProduct) {
		this.pSProduct = pSProduct;
	}

	/**
	 * @return the pSSalesforceClient
	 */
	public PSSalesforceClient getpSSalesforceClient() {
		return pSSalesforceClient;
	}

	/**
	 * @param pSSalesforceClient
	 *            the pSSalesforceClient to set
	 */
	public void setpSSalesforceClient(PSSalesforceClient pSSalesforceClient) {
		this.pSSalesforceClient = pSSalesforceClient;
	}

	/**
	 * @return the pSServiceArea
	 */
	public PSServiceArea getpSServiceArea() {
		return pSServiceArea;
	}

	/**
	 * @param pSServiceArea
	 *            the pSServiceArea to set
	 */
	public void setpSServiceArea(PSServiceArea pSServiceArea) {
		this.pSServiceArea = pSServiceArea;
	}
}