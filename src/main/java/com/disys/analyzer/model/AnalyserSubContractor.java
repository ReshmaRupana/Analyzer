package com.disys.analyzer.model;

import java.io.Serializable;

import javax.persistence.*;

import com.disys.analyzer.config.util.FacesUtils;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * The persistent class for the AnalyserSubContractor database table.
 * 
 */
@Entity(name = "Analyser_SubContractor")
//@Table(schema="Analyser.dbo")
@NamedQuery(name = "AnalyserSubContractor.findAll", query = "SELECT a FROM Analyser_SubContractor a")
@NamedStoredProcedureQueries(
		{
			@NamedStoredProcedureQuery(//
		            name = "AnalyserSubContractor.getAllSubContractors"
		            , procedureName = ""+FacesUtils.SCHEMA_WIRELESS +".spGetAnalyserAllContractor"
		            , parameters = {
		                    @StoredProcedureParameter(mode = ParameterMode.IN, name = "varLoggedOnUserID", type = String.class),
		                    @StoredProcedureParameter(mode = ParameterMode.IN, name = "varCompanyName", type = String.class),
		                    @StoredProcedureParameter(mode = ParameterMode.IN, name = "varOrderBy", type = String.class),
		                    @StoredProcedureParameter(mode = ParameterMode.IN, name = "varSearchString", type = String.class),
		                    @StoredProcedureParameter(mode = ParameterMode.IN, name = "varRecordStatus", type = String.class)
		                    }
		            // Can not have both the resultClasses and resultSetMappings
		            //, resultClasses = AnalyserSubContractor.class
		            ,resultSetMappings = { "AnalyserSubContractorResults" }
		        )
		}
	)

@SqlResultSetMapping(
    name="AnalyserSubContractorResults",
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
)

public class AnalyserSubContractor implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "Contractor_ID")
	private Integer contractorId;

	@Column(name = "AccountingContact")
	private String accountingContact;

	@Column(name = "AccountingEmail")
	private String accountingEmail;

	@Column(name = "Address1")
	private String address1;

	@Column(name = "Address2")
	private String address2;

	@Column(name = "AfricanAmerican")
	private Integer africanAmerican;

	private String amountType;

	@Column(name = "ApprovalDate")
	private Timestamp approvalDate;

	@Column(name = "ApprovedBy")
	private String approvedBy;

	@Column(name = "AsiaPacific")
	private Integer asiaPacific;

	@Column(name = "AutomobileExpiration")
	private Timestamp automobileExpiration;

	@Column(name = "AutomobileLiability")
	private String automobileLiability;

	@Column(name = "AutomobileLimit")
	private String automobileLimit;

	@Column(name = "AutomobileType")
	private String automobileType;

	@Column(name = "City")
	private String city;

	@Column(name = "Comments")
	private String comments;

	@Column(name = "Company")
	private String company;

	@Column(name = "Corporation")
	private Integer corporation;

	@Column(name = "Country")
	private String country;

	@Column(name = "CreatedBy")
	private String createdBy;

	@Column(name = "CreationDate")
	private Timestamp creationDate;

	@Column(name = "DateEstablished")
	private Timestamp dateEstablished;

	@Column(name = "DbaName",columnDefinition = "nvarchar (200)")
	private String dbaName;

	@Column(name = "DisabledOwned")
	private Integer disabledOwned;

	private String discount;

	private BigDecimal discountAmount;

	@Column(name = "DisysAdditionalInsured")
	private Integer disysAdditionalInsured;

	@Column(name = "DunsNumber")
	private String dunsNumber;

	@Column(name = "Email")
	private String email;

	@Column(name = "EmployeeCount")
	private Integer employeeCount;

	@Column(name = "EmployersExpiration")
	private Timestamp employersExpiration;

	@Column(name = "EmployersLiability")
	private String employersLiability;

	@Column(name = "EmployersLimit")
	private String employersLimit;

	@Column(name = "EmployersType")
	private String employersType;

	@Column(name = "EntryDate")
	private Timestamp entryDate;

	@Column(name = "Fax")
	private String fax;

	@Column(name = "FederalTaxId")
	private String federalTaxId;

	@Column(name = "FIN")
	private String fin;

	@Column(name = "GeneralExpiration")
	private Timestamp generalExpiration;

	@Column(name = "GeneralLiability")
	private String generalLiability;

	@Column(name = "GeneralLimit")
	private String generalLimit;

	@Column(name = "GeneralType")
	private String generalType;

	@Column(name = "HispanicAmerican")
	private Integer hispanicAmerican;

	@Column(name = "IncorporationCertificate", columnDefinition = "nvarchar (100)")
	private String incorporationCertificate;

	@Column(name = "InState", columnDefinition = "nvarchar (50)")
	private String inState;

	@Column(name = "InsuranceCertificate")
	private Integer insuranceCertificate;

	@Column(name = "LargeBusiness")
	private Integer largeBusiness;

	@Column(name = "LastName")
	private String lastName;

	@Column(name = "Llc")
	private Integer llc;

	@Column(name = "MinorityOwned")
	private Integer minorityOwned;

	@Column(name = "NaicsPrimaryCode", columnDefinition = "nvarchar (50)")
	private String naicsPrimaryCode;

	@Column(name = "NaicsSecondaryCode", columnDefinition = "nvarchar (150)")
	private String naicsSecondaryCode;

	@Column(name = "NativeAmerican")
	private Integer nativeAmerican;

	@Column(name = "OfficerName1")
	private String officerName1;

	@Column(name = "OfficerName2")
	private String officerName2;

	@Column(name = "OfficerName3")
	private String officerName3;

	@Column(name = "OfficerName4")
	private String officerName4;

	@Column(name = "OfficerTitle1")
	private String officerTitle1;

	@Column(name = "OfficerTitle2")
	private String officerTitle2;

	@Column(name = "OfficerTitle3")
	private String officerTitle3;

	@Column(name = "OfficerTitle4")
	private String officerTitle4;

	@Column(name = "ORG_ID")
	private Integer orgId;

	@Column(name = "Other")
	private Integer other;

	@Column(name = "OtherDescription")
	private String otherDescription;

	@Column(name = "Partnership")
	private Integer partnership;

	@Column(name = "Payment_Term")
	private String paymentTerm;

	@Column(name = "Poc_Name")
	private String pocName;

	@Column(name = "PrimaryAddress")
	private String primaryAddress;

	@Column(name = "PrimaryCity")
	private String primaryCity;

	@Column(name = "PrimaryEmail")
	private String primaryEmail;

	@Column(name = "PrimaryFax")
	private String primaryFax;

	@Column(name = "PrimaryName")
	private String primaryName;

	@Column(name = "PrimaryPhone")
	private String primaryPhone;

	@Column(name = "PrimaryState")
	private String primaryState;

	@Column(name = "PrimaryTitle")
	private String primaryTitle;

	@Column(name = "PrimaryZip")
	private String primaryZip;

	@Column(name = "Proprietorship")
	private Integer proprietorship;

	private String PSVendorId;

	@Column(name = "RecordStatus")
	private String recordStatus;

	@Column(name = "RecruitmentAddress")
	private String recruitmentAddress;

	@Column(name = "RecruitmentCity")
	private String recruitmentCity;

	@Column(name = "RecruitmentEmail")
	private String recruitmentEmail;

	@Column(name = "RecruitmentFax")
	private String recruitmentFax;

	@Column(name = "RecruitmentName")
	private String recruitmentName;

	@Column(name = "RecruitmentPhone")
	private String recruitmentPhone;

	@Column(name = "RecruitmentState")
	private String recruitmentState;

	@Column(name = "RecruitmentTitle")
	private String recruitmentTitle;

	@Column(name = "RecruitmentZip")
	private String recruitmentZip;

	@Column(name = "RemitAddress")
	private String remitAddress;

	@Column(name = "RemitCity")
	private String remitCity;

	@Column(name = "RemitFax")
	private String remitFax;

	@Column(name = "RemitPhone")
	private String remitPhone;

	@Column(name = "RemitState")
	private String remitState;

	@Column(name = "RemitZip")
	private String remitZip;

	@Column(name = "SmallBusiness")
	private Integer smallBusiness;

	@Column(name = "SmallDisadvantaged")
	private Integer smallDisadvantaged;

	@Column(name = "State")
	private String state;

	@Column(name = "StatesAuthorized")
	private String statesAuthorized;

	private Integer status;

	@Column(name = "SubcontractorAgreement")
	private Integer subcontractorAgreement;

	@Column(name = "SubcontractorApprove")
	private Integer subcontractorApprove;

	@Column(name = "SubcontractorComments")
	private String subcontractorComments;

	@Column(name = "Telephone")
	private String telephone;

	@Column(name = "Type1099")
	private Integer type1099;

	@Column(name = "TypeSubcontractor")
	private Integer typeSubcontractor;

	@Column(name = "UmbrellaExpiration")
	private Timestamp umbrellaExpiration;

	@Column(name = "UmbrellaLiability")
	private String umbrellaLiability;

	@Column(name = "UmbrellaLimit")
	private String umbrellaLimit;

	@Column(name = "UmbrellaType")
	private String umbrellaType;

	@Column(name = "Updated_By")
	private String updatedBy;

	@Column(name = "Updated_date")
	private Timestamp updatedDate;

	@Column(name = "Vendor_Code")
	private String vendorCode;

	@Column(name = "VeteranOwned")
	private Integer veteranOwned;

	@Column(name = "W9")
	private Integer w9;

	@Column(name = "Website")
	private String website;

	@Column(name = "WomenOwned")
	private Integer womenOwned;

	@Column(name = "WorkersExpiration")
	private Timestamp workersExpiration;

	@Column(name = "WorkersLiability")
	private String workersLiability;

	@Column(name = "WorkersLimit")
	private String workersLimit;

	@Column(name = "WorkersType")
	private String workersType;

	@Column(name = "WorkOrder")
	private Integer workOrder;

	@Column(name = "Zipcode")
	private String zipCode;
	
	@Column(name = "CompanyCode")
	private String companyCode;
	
	@Column(name = "SetId")
	private String setId;
	
	@Transient
	private String completeAddress;

	// bi-directional many-to-one association to User
	/*@ManyToOne
	@JoinColumn(name = "InvoiceEmail")
	private User user;*/

	public AnalyserSubContractor() {
	}

	/**
	 * @return the contractorId
	 */
	public Integer getContractorId() {
		return contractorId;
	}

	/**
	 * @param contractorId
	 *            the contractorId to set
	 */
	public void setContractorId(Integer contractorId) {
		this.contractorId = contractorId;
	}

	/**
	 * @return the address1
	 */
	public String getAddress1() {
		return address1;
	}

	/**
	 * @param address1
	 *            the address1 to set
	 */
	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	/**
	 * @return the address2
	 */
	public String getAddress2() {
		return address2;
	}

	/**
	 * @param address2
	 *            the address2 to set
	 */
	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	/**
	 * @return the africanAmerican
	 */
	public Integer getAfricanAmerican() {
		return africanAmerican;
	}

	/**
	 * @param africanAmerican
	 *            the africanAmerican to set
	 */
	public void setAfricanAmerican(Integer africanAmerican) {
		this.africanAmerican = africanAmerican;
	}

	/**
	 * @return the amountType
	 */
	public String getAmountType() {
		return amountType;
	}

	/**
	 * @param amountType
	 *            the amountType to set
	 */
	public void setAmountType(String amountType) {
		this.amountType = amountType;
	}

	/**
	 * @return the approvalDate
	 */
	public Timestamp getApprovalDate() {
		return approvalDate;
	}

	/**
	 * @param approvalDate
	 *            the approvalDate to set
	 */
	public void setApprovalDate(Timestamp approvalDate) {
		this.approvalDate = approvalDate;
	}

	/**
	 * @return the approvedBy
	 */
	public String getApprovedBy() {
		return approvedBy;
	}

	/**
	 * @param approvedBy
	 *            the approvedBy to set
	 */
	public void setApprovedBy(String approvedBy) {
		this.approvedBy = approvedBy;
	}

	/**
	 * @return the asiaPacific
	 */
	public Integer getAsiaPacific() {
		return asiaPacific;
	}

	/**
	 * @param asiaPacific
	 *            the asiaPacific to set
	 */
	public void setAsiaPacific(Integer asiaPacific) {
		this.asiaPacific = asiaPacific;
	}

	/**
	 * @return the automobileExpiration
	 */
	public Timestamp getAutomobileExpiration() {
		return automobileExpiration;
	}

	/**
	 * @param automobileExpiration
	 *            the automobileExpiration to set
	 */
	public void setAutomobileExpiration(Timestamp automobileExpiration) {
		this.automobileExpiration = automobileExpiration;
	}

	/**
	 * @return the automobileLiability
	 */
	public String getAutomobileLiability() {
		return automobileLiability;
	}

	/**
	 * @param automobileLiability
	 *            the automobileLiability to set
	 */
	public void setAutomobileLiability(String automobileLiability) {
		this.automobileLiability = automobileLiability;
	}

	/**
	 * @return the automobileLimit
	 */
	public String getAutomobileLimit() {
		return automobileLimit;
	}

	/**
	 * @param automobileLimit
	 *            the automobileLimit to set
	 */
	public void setAutomobileLimit(String automobileLimit) {
		this.automobileLimit = automobileLimit;
	}

	/**
	 * @return the automobileType
	 */
	public String getAutomobileType() {
		return automobileType;
	}

	/**
	 * @param automobileType
	 *            the automobileType to set
	 */
	public void setAutomobileType(String automobileType) {
		this.automobileType = automobileType;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city
	 *            the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @return the comments
	 */
	public String getComments() {
		return comments;
	}

	/**
	 * @param comments
	 *            the comments to set
	 */
	public void setComments(String comments) {
		this.comments = comments;
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
	 * @return the corporation
	 */
	public Integer getCorporation() {
		return corporation;
	}

	/**
	 * @param corporation
	 *            the corporation to set
	 */
	public void setCorporation(Integer corporation) {
		this.corporation = corporation;
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
	 * @return the createdBy
	 */
	public String getCreatedBy() {
		return createdBy;
	}

	/**
	 * @param createdBy
	 *            the createdBy to set
	 */
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	/**
	 * @return the creationDate
	 */
	public Timestamp getCreationDate() {
		return creationDate;
	}

	/**
	 * @param creationDate
	 *            the creationDate to set
	 */
	public void setCreationDate(Timestamp creationDate) {
		this.creationDate = creationDate;
	}

	/**
	 * @return the dateEstablished
	 */
	public Timestamp getDateEstablished() {
		return dateEstablished;
	}

	/**
	 * @param dateEstablished
	 *            the dateEstablished to set
	 */
	public void setDateEstablished(Timestamp dateEstablished) {
		this.dateEstablished = dateEstablished;
	}

	/**
	 * @return the dbaName
	 */
	public String getDbaName() {
		return dbaName;
	}

	/**
	 * @param dbaName
	 *            the dbaName to set
	 */
	public void setDbaName(String dbaName) {
		this.dbaName = dbaName;
	}

	/**
	 * @return the disabledOwned
	 */
	public Integer getDisabledOwned() {
		return disabledOwned;
	}

	/**
	 * @param disabledOwned
	 *            the disabledOwned to set
	 */
	public void setDisabledOwned(Integer disabledOwned) {
		this.disabledOwned = disabledOwned;
	}

	/**
	 * @return the discount
	 */
	public String getDiscount() {
		return discount;
	}

	/**
	 * @param discount
	 *            the discount to set
	 */
	public void setDiscount(String discount) {
		this.discount = discount;
	}

	/**
	 * @return the discountAmount
	 */
	public BigDecimal getDiscountAmount() {
		return discountAmount;
	}

	/**
	 * @param discountAmount
	 *            the discountAmount to set
	 */
	public void setDiscountAmount(BigDecimal discountAmount) {
		this.discountAmount = discountAmount;
	}

	/**
	 * @return the disysAdditionalInsured
	 */
	public Integer getDisysAdditionalInsured() {
		return disysAdditionalInsured;
	}

	/**
	 * @param disysAdditionalInsured
	 *            the disysAdditionalInsured to set
	 */
	public void setDisysAdditionalInsured(Integer disysAdditionalInsured) {
		this.disysAdditionalInsured = disysAdditionalInsured;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the employeeCount
	 */
	public Integer getEmployeeCount() {
		return employeeCount;
	}

	/**
	 * @param employeeCount
	 *            the employeeCount to set
	 */
	public void setEmployeeCount(Integer employeeCount) {
		this.employeeCount = employeeCount;
	}

	/**
	 * @return the employersExpiration
	 */
	public Timestamp getEmployersExpiration() {
		return employersExpiration;
	}

	/**
	 * @param employersExpiration
	 *            the employersExpiration to set
	 */
	public void setEmployersExpiration(Timestamp employersExpiration) {
		this.employersExpiration = employersExpiration;
	}

	/**
	 * @return the employersLiability
	 */
	public String getEmployersLiability() {
		return employersLiability;
	}

	/**
	 * @param employersLiability
	 *            the employersLiability to set
	 */
	public void setEmployersLiability(String employersLiability) {
		this.employersLiability = employersLiability;
	}

	/**
	 * @return the employersLimit
	 */
	public String getEmployersLimit() {
		return employersLimit;
	}

	/**
	 * @param employersLimit
	 *            the employersLimit to set
	 */
	public void setEmployersLimit(String employersLimit) {
		this.employersLimit = employersLimit;
	}

	/**
	 * @return the employersType
	 */
	public String getEmployersType() {
		return employersType;
	}

	/**
	 * @param employersType
	 *            the employersType to set
	 */
	public void setEmployersType(String employersType) {
		this.employersType = employersType;
	}

	/**
	 * @return the entryDate
	 */
	public Timestamp getEntryDate() {
		return entryDate;
	}

	/**
	 * @param entryDate
	 *            the entryDate to set
	 */
	public void setEntryDate(Timestamp entryDate) {
		this.entryDate = entryDate;
	}

	/**
	 * @return the fax
	 */
	public String getFax() {
		return fax;
	}

	/**
	 * @param fax
	 *            the fax to set
	 */
	public void setFax(String fax) {
		this.fax = fax;
	}

	/**
	 * @return the federalTaxId
	 */
	public String getFederalTaxId() {
		return federalTaxId;
	}

	/**
	 * @param federalTaxId
	 *            the federalTaxId to set
	 */
	public void setFederalTaxId(String federalTaxId) {
		this.federalTaxId = federalTaxId;
	}

	/**
	 * @return the fin
	 */
	public String getFin() {
		return fin;
	}

	/**
	 * @param fin
	 *            the fin to set
	 */
	public void setFin(String fin) {
		this.fin = fin;
	}

	/**
	 * @return the generalExpiration
	 */
	public Timestamp getGeneralExpiration() {
		return generalExpiration;
	}

	/**
	 * @param generalExpiration
	 *            the generalExpiration to set
	 */
	public void setGeneralExpiration(Timestamp generalExpiration) {
		this.generalExpiration = generalExpiration;
	}

	/**
	 * @return the generalLiability
	 */
	public String getGeneralLiability() {
		return generalLiability;
	}

	/**
	 * @param generalLiability
	 *            the generalLiability to set
	 */
	public void setGeneralLiability(String generalLiability) {
		this.generalLiability = generalLiability;
	}

	/**
	 * @return the generalLimit
	 */
	public String getGeneralLimit() {
		return generalLimit;
	}

	/**
	 * @param generalLimit
	 *            the generalLimit to set
	 */
	public void setGeneralLimit(String generalLimit) {
		this.generalLimit = generalLimit;
	}

	/**
	 * @return the generalType
	 */
	public String getGeneralType() {
		return generalType;
	}

	/**
	 * @param generalType
	 *            the generalType to set
	 */
	public void setGeneralType(String generalType) {
		this.generalType = generalType;
	}

	/**
	 * @return the hispanicAmerican
	 */
	public Integer getHispanicAmerican() {
		return hispanicAmerican;
	}

	/**
	 * @param hispanicAmerican
	 *            the hispanicAmerican to set
	 */
	public void setHispanicAmerican(Integer hispanicAmerican) {
		this.hispanicAmerican = hispanicAmerican;
	}

	/**
	 * @return the incorporationCertificate
	 */
	public String getIncorporationCertificate() {
		return incorporationCertificate;
	}

	/**
	 * @param incorporationCertificate
	 *            the incorporationCertificate to set
	 */
	public void setIncorporationCertificate(String incorporationCertificate) {
		this.incorporationCertificate = incorporationCertificate;
	}

	/**
	 * @return the inState
	 */
	public String getInState() {
		return inState;
	}

	/**
	 * @param inState
	 *            the inState to set
	 */
	public void setInState(String inState) {
		this.inState = inState;
	}

	/**
	 * @return the insuranceCertificate
	 */
	public Integer getInsuranceCertificate() {
		return insuranceCertificate;
	}

	/**
	 * @param insuranceCertificate
	 *            the insuranceCertificate to set
	 */
	public void setInsuranceCertificate(Integer insuranceCertificate) {
		this.insuranceCertificate = insuranceCertificate;
	}

	/**
	 * @return the largeBusiness
	 */
	public Integer getLargeBusiness() {
		return largeBusiness;
	}

	/**
	 * @param largeBusiness
	 *            the largeBusiness to set
	 */
	public void setLargeBusiness(Integer largeBusiness) {
		this.largeBusiness = largeBusiness;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName
	 *            the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the llc
	 */
	public Integer getLlc() {
		return llc;
	}

	/**
	 * @param llc
	 *            the llc to set
	 */
	public void setLlc(Integer llc) {
		this.llc = llc;
	}

	/**
	 * @return the minorityOwned
	 */
	public Integer getMinorityOwned() {
		return minorityOwned;
	}

	/**
	 * @param minorityOwned
	 *            the minorityOwned to set
	 */
	public void setMinorityOwned(Integer minorityOwned) {
		this.minorityOwned = minorityOwned;
	}

	/**
	 * @return the naicsPrimaryCode
	 */
	public String getNaicsPrimaryCode() {
		return naicsPrimaryCode;
	}

	/**
	 * @param naicsPrimaryCode
	 *            the naicsPrimaryCode to set
	 */
	public void setNaicsPrimaryCode(String naicsPrimaryCode) {
		this.naicsPrimaryCode = naicsPrimaryCode;
	}

	/**
	 * @return the naicsSecondaryCode
	 */
	public String getNaicsSecondaryCode() {
		return naicsSecondaryCode;
	}

	/**
	 * @param naicsSecondaryCode
	 *            the naicsSecondaryCode to set
	 */
	public void setNaicsSecondaryCode(String naicsSecondaryCode) {
		this.naicsSecondaryCode = naicsSecondaryCode;
	}

	/**
	 * @return the nativeAmerican
	 */
	public Integer getNativeAmerican() {
		return nativeAmerican;
	}

	/**
	 * @param nativeAmerican
	 *            the nativeAmerican to set
	 */
	public void setNativeAmerican(Integer nativeAmerican) {
		this.nativeAmerican = nativeAmerican;
	}

	/**
	 * @return the officerName1
	 */
	public String getOfficerName1() {
		return officerName1;
	}

	/**
	 * @param officerName1
	 *            the officerName1 to set
	 */
	public void setOfficerName1(String officerName1) {
		this.officerName1 = officerName1;
	}

	/**
	 * @return the officerName2
	 */
	public String getOfficerName2() {
		return officerName2;
	}

	/**
	 * @param officerName2
	 *            the officerName2 to set
	 */
	public void setOfficerName2(String officerName2) {
		this.officerName2 = officerName2;
	}

	/**
	 * @return the officerName3
	 */
	public String getOfficerName3() {
		return officerName3;
	}

	/**
	 * @param officerName3
	 *            the officerName3 to set
	 */
	public void setOfficerName3(String officerName3) {
		this.officerName3 = officerName3;
	}

	/**
	 * @return the officerName4
	 */
	public String getOfficerName4() {
		return officerName4;
	}

	/**
	 * @param officerName4
	 *            the officerName4 to set
	 */
	public void setOfficerName4(String officerName4) {
		this.officerName4 = officerName4;
	}

	/**
	 * @return the officerTitle1
	 */
	public String getOfficerTitle1() {
		return officerTitle1;
	}

	/**
	 * @param officerTitle1
	 *            the officerTitle1 to set
	 */
	public void setOfficerTitle1(String officerTitle1) {
		this.officerTitle1 = officerTitle1;
	}

	/**
	 * @return the officerTitle2
	 */
	public String getOfficerTitle2() {
		return officerTitle2;
	}

	/**
	 * @param officerTitle2
	 *            the officerTitle2 to set
	 */
	public void setOfficerTitle2(String officerTitle2) {
		this.officerTitle2 = officerTitle2;
	}

	/**
	 * @return the officerTitle3
	 */
	public String getOfficerTitle3() {
		return officerTitle3;
	}

	/**
	 * @param officerTitle3
	 *            the officerTitle3 to set
	 */
	public void setOfficerTitle3(String officerTitle3) {
		this.officerTitle3 = officerTitle3;
	}

	/**
	 * @return the officerTitle4
	 */
	public String getOfficerTitle4() {
		return officerTitle4;
	}

	/**
	 * @param officerTitle4
	 *            the officerTitle4 to set
	 */
	public void setOfficerTitle4(String officerTitle4) {
		this.officerTitle4 = officerTitle4;
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
	 * @return the other
	 */
	public Integer getOther() {
		return other;
	}

	/**
	 * @param other
	 *            the other to set
	 */
	public void setOther(Integer other) {
		this.other = other;
	}

	/**
	 * @return the otherDescription
	 */
	public String getOtherDescription() {
		return otherDescription;
	}

	/**
	 * @param otherDescription
	 *            the otherDescription to set
	 */
	public void setOtherDescription(String otherDescription) {
		this.otherDescription = otherDescription;
	}

	/**
	 * @return the partnership
	 */
	public Integer getPartnership() {
		return partnership;
	}

	/**
	 * @param partnership
	 *            the partnership to set
	 */
	public void setPartnership(Integer partnership) {
		this.partnership = partnership;
	}

	/**
	 * @return the paymentTerm
	 */
	public String getPaymentTerm() {
		return paymentTerm;
	}

	/**
	 * @param paymentTerm
	 *            the paymentTerm to set
	 */
	public void setPaymentTerm(String paymentTerm) {
		this.paymentTerm = paymentTerm;
	}

	/**
	 * @return the pocName
	 */
	public String getPocName() {
		return pocName;
	}

	/**
	 * @param pocName
	 *            the pocName to set
	 */
	public void setPocName(String pocName) {
		this.pocName = pocName;
	}

	/**
	 * @return the primaryAddress
	 */
	public String getPrimaryAddress() {
		return primaryAddress;
	}

	/**
	 * @param primaryAddress
	 *            the primaryAddress to set
	 */
	public void setPrimaryAddress(String primaryAddress) {
		this.primaryAddress = primaryAddress;
	}

	/**
	 * @return the primaryCity
	 */
	public String getPrimaryCity() {
		return primaryCity;
	}

	/**
	 * @param primaryCity
	 *            the primaryCity to set
	 */
	public void setPrimaryCity(String primaryCity) {
		this.primaryCity = primaryCity;
	}

	/**
	 * @return the primaryEmail
	 */
	public String getPrimaryEmail() {
		return primaryEmail;
	}

	/**
	 * @param primaryEmail
	 *            the primaryEmail to set
	 */
	public void setPrimaryEmail(String primaryEmail) {
		this.primaryEmail = primaryEmail;
	}

	/**
	 * @return the primaryFax
	 */
	public String getPrimaryFax() {
		return primaryFax;
	}

	/**
	 * @param primaryFax
	 *            the primaryFax to set
	 */
	public void setPrimaryFax(String primaryFax) {
		this.primaryFax = primaryFax;
	}

	/**
	 * @return the primaryName
	 */
	public String getPrimaryName() {
		return primaryName;
	}

	/**
	 * @param primaryName
	 *            the primaryName to set
	 */
	public void setPrimaryName(String primaryName) {
		this.primaryName = primaryName;
	}

	/**
	 * @return the primaryPhone
	 */
	public String getPrimaryPhone() {
		return primaryPhone;
	}

	/**
	 * @param primaryPhone
	 *            the primaryPhone to set
	 */
	public void setPrimaryPhone(String primaryPhone) {
		this.primaryPhone = primaryPhone;
	}

	/**
	 * @return the primaryState
	 */
	public String getPrimaryState() {
		return primaryState;
	}

	/**
	 * @param primaryState
	 *            the primaryState to set
	 */
	public void setPrimaryState(String primaryState) {
		this.primaryState = primaryState;
	}

	/**
	 * @return the primaryTitle
	 */
	public String getPrimaryTitle() {
		return primaryTitle;
	}

	/**
	 * @param primaryTitle
	 *            the primaryTitle to set
	 */
	public void setPrimaryTitle(String primaryTitle) {
		this.primaryTitle = primaryTitle;
	}

	/**
	 * @return the primaryZip
	 */
	public String getPrimaryZip() {
		return primaryZip;
	}

	/**
	 * @param primaryZip
	 *            the primaryZip to set
	 */
	public void setPrimaryZip(String primaryZip) {
		this.primaryZip = primaryZip;
	}

	/**
	 * @return the proprietorship
	 */
	public Integer getProprietorship() {
		return proprietorship;
	}

	/**
	 * @param proprietorship
	 *            the proprietorship to set
	 */
	public void setProprietorship(Integer proprietorship) {
		this.proprietorship = proprietorship;
	}

	/**
	 * @return the pSVendorId
	 */
	public String getPSVendorId() {
		return PSVendorId;
	}

	/**
	 * @param pSVendorId
	 *            the pSVendorId to set
	 */
	public void setPSVendorId(String pSVendorId) {
		PSVendorId = pSVendorId;
	}

	/**
	 * @return the recordStatus
	 */
	public String getRecordStatus() {
		return recordStatus;
	}

	/**
	 * @param recordStatus
	 *            the recordStatus to set
	 */
	public void setRecordStatus(String recordStatus) {
		this.recordStatus = recordStatus;
	}

	/**
	 * @return the recruitmentAddress
	 */
	public String getRecruitmentAddress() {
		return recruitmentAddress;
	}

	/**
	 * @param recruitmentAddress
	 *            the recruitmentAddress to set
	 */
	public void setRecruitmentAddress(String recruitmentAddress) {
		this.recruitmentAddress = recruitmentAddress;
	}

	/**
	 * @return the recruitmentCity
	 */
	public String getRecruitmentCity() {
		return recruitmentCity;
	}

	/**
	 * @param recruitmentCity
	 *            the recruitmentCity to set
	 */
	public void setRecruitmentCity(String recruitmentCity) {
		this.recruitmentCity = recruitmentCity;
	}

	/**
	 * @return the recruitmentEmail
	 */
	public String getRecruitmentEmail() {
		return recruitmentEmail;
	}

	/**
	 * @param recruitmentEmail
	 *            the recruitmentEmail to set
	 */
	public void setRecruitmentEmail(String recruitmentEmail) {
		this.recruitmentEmail = recruitmentEmail;
	}

	/**
	 * @return the recruitmentFax
	 */
	public String getRecruitmentFax() {
		return recruitmentFax;
	}

	/**
	 * @param recruitmentFax
	 *            the recruitmentFax to set
	 */
	public void setRecruitmentFax(String recruitmentFax) {
		this.recruitmentFax = recruitmentFax;
	}

	/**
	 * @return the recruitmentName
	 */
	public String getRecruitmentName() {
		return recruitmentName;
	}

	/**
	 * @param recruitmentName
	 *            the recruitmentName to set
	 */
	public void setRecruitmentName(String recruitmentName) {
		this.recruitmentName = recruitmentName;
	}

	/**
	 * @return the recruitmentPhone
	 */
	public String getRecruitmentPhone() {
		return recruitmentPhone;
	}

	/**
	 * @param recruitmentPhone
	 *            the recruitmentPhone to set
	 */
	public void setRecruitmentPhone(String recruitmentPhone) {
		this.recruitmentPhone = recruitmentPhone;
	}

	/**
	 * @return the recruitmentState
	 */
	public String getRecruitmentState() {
		return recruitmentState;
	}

	/**
	 * @param recruitmentState
	 *            the recruitmentState to set
	 */
	public void setRecruitmentState(String recruitmentState) {
		this.recruitmentState = recruitmentState;
	}

	/**
	 * @return the recruitmentTitle
	 */
	public String getRecruitmentTitle() {
		return recruitmentTitle;
	}

	/**
	 * @param recruitmentTitle
	 *            the recruitmentTitle to set
	 */
	public void setRecruitmentTitle(String recruitmentTitle) {
		this.recruitmentTitle = recruitmentTitle;
	}

	/**
	 * @return the recruitmentZip
	 */
	public String getRecruitmentZip() {
		return recruitmentZip;
	}

	/**
	 * @param recruitmentZip
	 *            the recruitmentZip to set
	 */
	public void setRecruitmentZip(String recruitmentZip) {
		this.recruitmentZip = recruitmentZip;
	}

	/**
	 * @return the remitAddress
	 */
	public String getRemitAddress() {
		return remitAddress;
	}

	/**
	 * @param remitAddress
	 *            the remitAddress to set
	 */
	public void setRemitAddress(String remitAddress) {
		this.remitAddress = remitAddress;
	}

	/**
	 * @return the remitCity
	 */
	public String getRemitCity() {
		return remitCity;
	}

	/**
	 * @param remitCity
	 *            the remitCity to set
	 */
	public void setRemitCity(String remitCity) {
		this.remitCity = remitCity;
	}

	/**
	 * @return the remitFax
	 */
	public String getRemitFax() {
		return remitFax;
	}

	/**
	 * @param remitFax
	 *            the remitFax to set
	 */
	public void setRemitFax(String remitFax) {
		this.remitFax = remitFax;
	}

	/**
	 * @return the remitPhone
	 */
	public String getRemitPhone() {
		return remitPhone;
	}

	/**
	 * @param remitPhone
	 *            the remitPhone to set
	 */
	public void setRemitPhone(String remitPhone) {
		this.remitPhone = remitPhone;
	}

	/**
	 * @return the remitState
	 */
	public String getRemitState() {
		return remitState;
	}

	/**
	 * @param remitState
	 *            the remitState to set
	 */
	public void setRemitState(String remitState) {
		this.remitState = remitState;
	}

	/**
	 * @return the remitZip
	 */
	public String getRemitZip() {
		return remitZip;
	}

	/**
	 * @param remitZip
	 *            the remitZip to set
	 */
	public void setRemitZip(String remitZip) {
		this.remitZip = remitZip;
	}

	/**
	 * @return the smallBusiness
	 */
	public Integer getSmallBusiness() {
		return smallBusiness;
	}

	/**
	 * @param smallBusiness
	 *            the smallBusiness to set
	 */
	public void setSmallBusiness(Integer smallBusiness) {
		this.smallBusiness = smallBusiness;
	}

	/**
	 * @return the smallDisadvantaged
	 */
	public Integer getSmallDisadvantaged() {
		return smallDisadvantaged;
	}

	/**
	 * @param smallDisadvantaged
	 *            the smallDisadvantaged to set
	 */
	public void setSmallDisadvantaged(Integer smallDisadvantaged) {
		this.smallDisadvantaged = smallDisadvantaged;
	}

	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}

	/**
	 * @param state
	 *            the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * @return the statesAuthorized
	 */
	public String getStatesAuthorized() {
		return statesAuthorized;
	}

	/**
	 * @param statesAuthorized
	 *            the statesAuthorized to set
	 */
	public void setStatesAuthorized(String statesAuthorized) {
		this.statesAuthorized = statesAuthorized;
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
	 * @return the subcontractorAgreement
	 */
	public Integer getSubcontractorAgreement() {
		return subcontractorAgreement;
	}

	/**
	 * @param subcontractorAgreement
	 *            the subcontractorAgreement to set
	 */
	public void setSubcontractorAgreement(Integer subcontractorAgreement) {
		this.subcontractorAgreement = subcontractorAgreement;
	}

	/**
	 * @return the subcontractorApprove
	 */
	public Integer getSubcontractorApprove() {
		return subcontractorApprove;
	}

	/**
	 * @param subcontractorApprove
	 *            the subcontractorApprove to set
	 */
	public void setSubcontractorApprove(Integer subcontractorApprove) {
		this.subcontractorApprove = subcontractorApprove;
	}

	/**
	 * @return the subcontractorComments
	 */
	public String getSubcontractorComments() {
		return subcontractorComments;
	}

	/**
	 * @param subcontractorComments
	 *            the subcontractorComments to set
	 */
	public void setSubcontractorComments(String subcontractorComments) {
		this.subcontractorComments = subcontractorComments;
	}

	/**
	 * @return the telephone
	 */
	public String getTelephone() {
		return telephone;
	}

	/**
	 * @param telephone
	 *            the telephone to set
	 */
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	/**
	 * @return the type1099
	 */
	public Integer getType1099() {
		return type1099;
	}

	/**
	 * @param type1099
	 *            the type1099 to set
	 */
	public void setType1099(Integer type1099) {
		this.type1099 = type1099;
	}

	/**
	 * @return the typeSubcontractor
	 */
	public Integer getTypeSubcontractor() {
		return typeSubcontractor;
	}

	/**
	 * @param typeSubcontractor
	 *            the typeSubcontractor to set
	 */
	public void setTypeSubcontractor(Integer typeSubcontractor) {
		this.typeSubcontractor = typeSubcontractor;
	}

	/**
	 * @return the umbrellaExpiration
	 */
	public Timestamp getUmbrellaExpiration() {
		return umbrellaExpiration;
	}

	/**
	 * @param umbrellaExpiration
	 *            the umbrellaExpiration to set
	 */
	public void setUmbrellaExpiration(Timestamp umbrellaExpiration) {
		this.umbrellaExpiration = umbrellaExpiration;
	}

	/**
	 * @return the umbrellaLiability
	 */
	public String getUmbrellaLiability() {
		return umbrellaLiability;
	}

	/**
	 * @param umbrellaLiability
	 *            the umbrellaLiability to set
	 */
	public void setUmbrellaLiability(String umbrellaLiability) {
		this.umbrellaLiability = umbrellaLiability;
	}

	/**
	 * @return the umbrellaLimit
	 */
	public String getUmbrellaLimit() {
		return umbrellaLimit;
	}

	/**
	 * @param umbrellaLimit
	 *            the umbrellaLimit to set
	 */
	public void setUmbrellaLimit(String umbrellaLimit) {
		this.umbrellaLimit = umbrellaLimit;
	}

	/**
	 * @return the umbrellaType
	 */
	public String getUmbrellaType() {
		return umbrellaType;
	}

	/**
	 * @param umbrellaType
	 *            the umbrellaType to set
	 */
	public void setUmbrellaType(String umbrellaType) {
		this.umbrellaType = umbrellaType;
	}

	/**
	 * @return the veteranOwned
	 */
	public Integer getVeteranOwned() {
		return veteranOwned;
	}

	/**
	 * @param veteranOwned
	 *            the veteranOwned to set
	 */
	public void setVeteranOwned(Integer veteranOwned) {
		this.veteranOwned = veteranOwned;
	}

	/**
	 * @return the w9
	 */
	public Integer getW9() {
		return w9;
	}

	/**
	 * @param w9
	 *            the w9 to set
	 */
	public void setW9(Integer w9) {
		this.w9 = w9;
	}

	/**
	 * @return the website
	 */
	public String getWebsite() {
		return website;
	}

	/**
	 * @param website
	 *            the website to set
	 */
	public void setWebsite(String website) {
		this.website = website;
	}

	/**
	 * @return the womenOwned
	 */
	public Integer getWomenOwned() {
		return womenOwned;
	}

	/**
	 * @param womenOwned
	 *            the womenOwned to set
	 */
	public void setWomenOwned(Integer womenOwned) {
		this.womenOwned = womenOwned;
	}

	/**
	 * @return the workersExpiration
	 */
	public Timestamp getWorkersExpiration() {
		return workersExpiration;
	}

	/**
	 * @param workersExpiration
	 *            the workersExpiration to set
	 */
	public void setWorkersExpiration(Timestamp workersExpiration) {
		this.workersExpiration = workersExpiration;
	}

	/**
	 * @return the workersLiability
	 */
	public String getWorkersLiability() {
		return workersLiability;
	}

	/**
	 * @param workersLiability
	 *            the workersLiability to set
	 */
	public void setWorkersLiability(String workersLiability) {
		this.workersLiability = workersLiability;
	}

	/**
	 * @return the workersLimit
	 */
	public String getWorkersLimit() {
		return workersLimit;
	}

	/**
	 * @param workersLimit
	 *            the workersLimit to set
	 */
	public void setWorkersLimit(String workersLimit) {
		this.workersLimit = workersLimit;
	}

	/**
	 * @return the workersType
	 */
	public String getWorkersType() {
		return workersType;
	}

	/**
	 * @param workersType
	 *            the workersType to set
	 */
	public void setWorkersType(String workersType) {
		this.workersType = workersType;
	}

	/**
	 * @return the workOrder
	 */
	public Integer getWorkOrder() {
		return workOrder;
	}

	/**
	 * @param workOrder
	 *            the workOrder to set
	 */
	public void setWorkOrder(Integer workOrder) {
		this.workOrder = workOrder;
	}

	/**
	 * @return the accountingContact
	 */
	public String getAccountingContact() {
		return accountingContact;
	}

	/**
	 * @param accountingContact
	 *            the accountingContact to set
	 */
	public void setAccountingContact(String accountingContact) {
		this.accountingContact = accountingContact;
	}

	/**
	 * @return the accountingEmail
	 */
	public String getAccountingEmail() {
		return accountingEmail;
	}

	/**
	 * @param accountingEmail
	 *            the accountingEmail to set
	 */
	public void setAccountingEmail(String accountingEmail) {
		this.accountingEmail = accountingEmail;
	}

	/**
	 * @return the dunsNumber
	 */
	public String getDunsNumber() {
		return dunsNumber;
	}

	/**
	 * @param dunsNumber
	 *            the dunsNumber to set
	 */
	public void setDunsNumber(String dunsNumber) {
		this.dunsNumber = dunsNumber;
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
	 * @return the vendorCode
	 */
	public String getVendorCode() {
		return vendorCode;
	}

	/**
	 * @param vendorCode
	 *            the vendorCode to set
	 */
	public void setVendorCode(String vendorCode) {
		this.vendorCode = vendorCode;
	}

	/**
	 * @return the zipCode
	 */
	public String getZipCode() {
		return zipCode;
	}

	/**
	 * @param zipCode
	 *            the zipCode to set
	 */
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	/**
	 * @return the completeAddress
	 */
	public String getCompleteAddress() {
		return completeAddress;
	}

	/**
	 * @param completeAddress the completeAddress to set
	 */
	public void setCompleteAddress(String completeAddress) {
		this.completeAddress = completeAddress;
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

}