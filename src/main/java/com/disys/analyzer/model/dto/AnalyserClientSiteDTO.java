/**
 * 
 */
package com.disys.analyzer.model.dto;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author Sajid
 * 
 */
public class AnalyserClientSiteDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9276705583737980L;
	private Integer clientId;
	private String address1;
	private String address2;
	private String city;
	private String country;
	private String email;
	private String enteredBy;
	private Timestamp entryDate;
	private String fax;
	private String isProcessed;
	private String isUpdateProcessed;
	private String lastName;
	private String managerName;
	private Integer orgId;
	private Timestamp processDate;
	private Integer siteId;
	private String state;
	private Integer status;
	private String telephone;
	private String updatedBy;
	private Timestamp updatedDate;
	private String zipCode;
	private String completeAddress;
	private String company;
	
	private Double sickLeavePerHourRate;
	private Integer sickLeaveCap;
	
	private String isAddressUSPSValidated;
	private Timestamp uSPSAddressValidationDate;
	private String validatedBy;
	
	private String longitude;
	private String latitude;
	
	private String pTOLimitToOverrideSick;

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
	 * @return the enteredBy
	 */
	public String getEnteredBy() {
		return enteredBy;
	}

	/**
	 * @param enteredBy
	 *            the enteredBy to set
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
	 * @return the isProcessed
	 */
	public String getIsProcessed() {
		return isProcessed;
	}

	/**
	 * @param isProcessed
	 *            the isProcessed to set
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
	 * @param isUpdateProcessed
	 *            the isUpdateProcessed to set
	 */
	public void setIsUpdateProcessed(String isUpdateProcessed) {
		this.isUpdateProcessed = isUpdateProcessed;
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
	 * @return the managerName
	 */
	public String getManagerName() {
		return managerName;
	}

	/**
	 * @param managerName
	 *            the managerName to set
	 */
	public void setManagerName(String managerName) {
		this.managerName = managerName;
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
	 * @return the processDate
	 */
	public Timestamp getProcessDate() {
		return processDate;
	}

	/**
	 * @param processDate
	 *            the processDate to set
	 */
	public void setProcessDate(Timestamp processDate) {
		this.processDate = processDate;
	}

	/**
	 * @return the siteId
	 */
	public Integer getSiteId() {
		return siteId;
	}

	/**
	 * @param siteId
	 *            the siteId to set
	 */
	public void setSiteId(Integer siteId) {
		this.siteId = siteId;
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
	 * @param completeAddress
	 *            the completeAddress to set
	 */
	public void setCompleteAddress(String completeAddress) {
		this.completeAddress = completeAddress;
	}

	/**
	 * @return the company
	 */
	public String getCompany() {
		return company;
	}

	/**
	 * @param company the company to set
	 */
	public void setCompany(String company) {
		this.company = company;
	}

	/**
	 * @return the sickLeavePerHourRate
	 */
	public Double getSickLeavePerHourRate() {
		return sickLeavePerHourRate;
	}

	/**
	 * @param sickLeavePerHourRate the sickLeavePerHourRate to set
	 */
	public void setSickLeavePerHourRate(Double sickLeavePerHourRate) {
		this.sickLeavePerHourRate = sickLeavePerHourRate;
	}

	/**
	 * @return the sickLeaveCap
	 */
	public Integer getSickLeaveCap() {
		return sickLeaveCap;
	}

	/**
	 * @param sickLeaveCap the sickLeaveCap to set
	 */
	public void setSickLeaveCap(Integer sickLeaveCap) {
		this.sickLeaveCap = sickLeaveCap;
	}

	/**
	 * @return the longitude
	 */
	public String getLongitude() {
		return longitude;
	}

	/**
	 * @param longitude the longitude to set
	 */
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	/**
	 * @return the latitude
	 */
	public String getLatitude() {
		return latitude;
	}

	/**
	 * @param latitude the latitude to set
	 */
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	/**
	 * @return the isAddressUSPSValidated
	 */
	public String getIsAddressUSPSValidated() {
		return isAddressUSPSValidated;
	}

	/**
	 * @param isAddressUSPSValidated the isAddressUSPSValidated to set
	 */
	public void setIsAddressUSPSValidated(String isAddressUSPSValidated) {
		this.isAddressUSPSValidated = isAddressUSPSValidated;
	}

	/**
	 * @return the uSPSAddressValidationDate
	 */
	public Timestamp getuSPSAddressValidationDate() {
		return uSPSAddressValidationDate;
	}

	/**
	 * @param uSPSAddressValidationDate the uSPSAddressValidationDate to set
	 */
	public void setuSPSAddressValidationDate(Timestamp uSPSAddressValidationDate) {
		this.uSPSAddressValidationDate = uSPSAddressValidationDate;
	}

	/**
	 * @return the validatedBy
	 */
	public String getValidatedBy() {
		return validatedBy;
	}

	/**
	 * @param validatedBy the validatedBy to set
	 */
	public void setValidatedBy(String validatedBy) {
		this.validatedBy = validatedBy;
	}

	/**
	 * @return the pTOLimitToOverrideSick
	 */
	public String getPTOLimitToOverrideSick()
	{
		return pTOLimitToOverrideSick;
	}

	/**
	 * @param pTOLimitToOverrideSick the pTOLimitToOverrideSick to set
	 */
	public void setPTOLimitToOverrideSick(String pTOLimitToOverrideSick)
	{
		this.pTOLimitToOverrideSick = pTOLimitToOverrideSick;
	}

	public String getpTOLimitToOverrideSick()
	{
		return pTOLimitToOverrideSick;
	}

	public void setpTOLimitToOverrideSick(String pTOLimitToOverrideSick)
	{
		this.pTOLimitToOverrideSick = pTOLimitToOverrideSick;
	}

}
