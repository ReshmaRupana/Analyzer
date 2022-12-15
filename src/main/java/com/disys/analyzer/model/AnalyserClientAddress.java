package com.disys.analyzer.model;

import java.io.Serializable;

import javax.persistence.*;

import java.sql.Timestamp;


/**
 * The persistent class for the AnalyserClientAddress database table.
 * 
 */
@Entity(name="Analyser_ClientAddress")
//@Table(schema="Analyser.dbo")
@NamedQuery(name="AnalyserClientAddress.findAll", query="SELECT a FROM Analyser_ClientAddress a")
public class AnalyserClientAddress implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="Address_Id")
	private Integer addressId;

	@Column(name="Address1")
	private String address1;

	@Column(name="Address2")
	private String address2;

	@Column(name="City")
	private String city;

	@Column(name="Client_Id")
	private Integer clientId;

	@Column(name="Contact_Name")
	private String contactName;

	@Column(name="Country")
	private String country;

	@Column(name="DistributionMethod")
	private String distributionMethod;

	@Column(name="DysBillType")
	private String dysBillType;

	@Column(name="DysDeliveryType")
	private String dysDeliveryType;

	@Column(name="DysPracticeArea")
	private String dysPracticeArea;

	@Column(name="Email")
	private String email;

	@Column(name="EnteredBy")
	private String enteredBy;

	@Column(name="EntryDate")
	private Timestamp entryDate;

	@Column(name="Fax")
	private String fax;

	@Column(name="IsProcessed")
	private String isProcessed;

	@Column(name="IsUpdateProcessed")
	private String isUpdateProcessed;

	@Column(name="ORG_ID")
	private Integer orgId;

	@Column(name="ProcessDate")
	private Timestamp processDate;

	@Column(name="SpecialNotes")
	private String specialNotes;

	@Column(name="State")
	private String state;

	private Integer status;

	@Column(name="Telephone")
	private String telephone;

	@Column(name="Updated_By")
	private String updatedBy;

	@Column(name="Updated_date")
	private Timestamp updatedDate;

	@Column(name="Zipcode")
	private String zipCode;

	public AnalyserClientAddress() {
	}

	/**
	 * @return the addressId
	 */
	public Integer getAddressId() {
		return addressId;
	}

	/**
	 * @param addressId the addressId to set
	 */
	public void setAddressId(Integer addressId) {
		this.addressId = addressId;
	}

	/**
	 * @return the address1
	 */
	public String getAddress1() {
		return address1;
	}

	/**
	 * @param address1 the address1 to set
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
	 * @param address2 the address2 to set
	 */
	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @return the clientId
	 */
	public Integer getClientId() {
		return clientId;
	}

	/**
	 * @param clientId the clientId to set
	 */
	public void setClientId(Integer clientId) {
		this.clientId = clientId;
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
	 * @return the distributionMethod
	 */
	public String getDistributionMethod() {
		return distributionMethod;
	}

	/**
	 * @param distributionMethod the distributionMethod to set
	 */
	public void setDistributionMethod(String distributionMethod) {
		this.distributionMethod = distributionMethod;
	}

	/**
	 * @return the dysBillType
	 */
	public String getDysBillType() {
		return dysBillType;
	}

	/**
	 * @param dysBillType the dysBillType to set
	 */
	public void setDysBillType(String dysBillType) {
		this.dysBillType = dysBillType;
	}

	/**
	 * @return the dysDeliveryType
	 */
	public String getDysDeliveryType() {
		return dysDeliveryType;
	}

	/**
	 * @param dysDeliveryType the dysDeliveryType to set
	 */
	public void setDysDeliveryType(String dysDeliveryType) {
		this.dysDeliveryType = dysDeliveryType;
	}

	/**
	 * @return the dysPracticeArea
	 */
	public String getDysPracticeArea() {
		return dysPracticeArea;
	}

	/**
	 * @param dysPracticeArea the dysPracticeArea to set
	 */
	public void setDysPracticeArea(String dysPracticeArea) {
		this.dysPracticeArea = dysPracticeArea;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the enteredBy
	 */
	public String getEnteredBy() {
		return enteredBy;
	}

	/**
	 * @param enteredBy the enteredBy to set
	 */
	public void setEnteredBy(String enteredBy) {
		this.enteredBy = enteredBy;
	}

	/**
	 * @return the entryDate
	 */
	public Timestamp getEntryDate() {
		return entryDate;
	}

	/**
	 * @param entryDate the entryDate to set
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
	 * @param fax the fax to set
	 */
	public void setFax(String fax) {
		this.fax = fax;
	}

	/**
	 * @return the isProcessed
	 */
	public String getIsProcessed() {
		return isProcessed;
	}

	/**
	 * @param isProcessed the isProcessed to set
	 */
	public void setIsProcessed(String isProcessed) {
		this.isProcessed = isProcessed;
	}

	/**
	 * @return the isUpdateProcessed
	 */
	public String getIsUpdateProcessed() {
		return isUpdateProcessed;
	}

	/**
	 * @param isUpdateProcessed the isUpdateProcessed to set
	 */
	public void setIsUpdateProcessed(String isUpdateProcessed) {
		this.isUpdateProcessed = isUpdateProcessed;
	}

	/**
	 * @return the orgId
	 */
	public Integer getOrgId() {
		return orgId;
	}

	/**
	 * @param orgId the orgId to set
	 */
	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}

	/**
	 * @return the processDate
	 */
	public Timestamp getProcessDate() {
		return processDate;
	}

	/**
	 * @param processDate the processDate to set
	 */
	public void setProcessDate(Timestamp processDate) {
		this.processDate = processDate;
	}

	/**
	 * @return the specialNotes
	 */
	public String getSpecialNotes() {
		return specialNotes;
	}

	/**
	 * @param specialNotes the specialNotes to set
	 */
	public void setSpecialNotes(String specialNotes) {
		this.specialNotes = specialNotes;
	}

	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}

	/**
	 * @param state the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * @return the status
	 */
	public Integer getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/**
	 * @return the telephone
	 */
	public String getTelephone() {
		return telephone;
	}

	/**
	 * @param telephone the telephone to set
	 */
	public void setTelephone(String telephone) {
		this.telephone = telephone;
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
	public Timestamp getUpdatedDate() {
		return updatedDate;
	}

	/**
	 * @param updatedDate the updatedDate to set
	 */
	public void setUpdatedDate(Timestamp updatedDate) {
		this.updatedDate = updatedDate;
	}

	/**
	 * @return the zipCode
	 */
	public String getZipCode() {
		return zipCode;
	}

	/**
	 * @param zipCode the zipCode to set
	 */
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	
}