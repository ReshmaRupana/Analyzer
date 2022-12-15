/**
 * 
 */
package com.disys.analyzer.model.dto;

import java.io.Serializable;

/**
 * @author Sajid
 * 
 */
public class AnalyserClientDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8548727100869932038L;
	private String invoiceFrequency;
	private String contactName;
	private String distributionMethod;
	private String specialNotes;
	private String paymentTerms;
	private String adminFee;
	private String email;
	private Double volumeDiscount;
	private Double paymentDiscount;
	private Double otherDiscount;
	private String backgroundAmount;
	private String creditCheckAmount;
	private String drugTestAmount;
	private String company;
	private Integer clientId;
	private Integer addressId;
	private String address;
	private String dysBillType;
	private String dysDeliveryType;
	private String dysPracticeArea;
	private String verticalDescription;
	private String productDescription;
	private String pSVerticalCode;
	private String pSProductCode;
	private String pSBillingTypeLongName;
	private String pSServiceAreaLongName;
	private String pSSalesForceClientName;
	
	private String updatedBy;
	private Integer status;
	
	private String pSClientId;
	private Double totalDiscount;
	private String country;
	private String setId;
	
	private Integer psVerticalId;
	private Integer psProductId;
	private String portfolio;
	private String entityName;
	private String companyCode;
	
	/**
	 * @return the totalDiscount
	 */
	public Double getTotalDiscount() {
		return totalDiscount;
	}

	/**
	 * @param totalDiscount the totalDiscount to set
	 */
	public void setTotalDiscount(Double totalDiscount) {
		this.totalDiscount = totalDiscount;
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
	 * @return the distributionMethod
	 */
	public String getDistributionMethod() {
		return distributionMethod;
	}

	/**
	 * @param distributionMethod
	 *            the distributionMethod to set
	 */
	public void setDistributionMethod(String distributionMethod) {
		this.distributionMethod = distributionMethod;
	}

	/**
	 * @return the specialNotes
	 */
	public String getSpecialNotes() {
		return specialNotes;
	}

	/**
	 * @param specialNotes
	 *            the specialNotes to set
	 */
	public void setSpecialNotes(String specialNotes) {
		this.specialNotes = specialNotes;
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
	 * @return the backgroundAmount
	 */
	public String getBackgroundAmount() {
		return backgroundAmount;
	}

	/**
	 * @param backgroundAmount
	 *            the backgroundAmount to set
	 */
	public void setBackgroundAmount(String backgroundAmount) {
		this.backgroundAmount = backgroundAmount;
	}

	/**
	 * @return the creditCheckAmount
	 */
	public String getCreditCheckAmount() {
		return creditCheckAmount;
	}

	/**
	 * @param creditCheckAmount
	 *            the creditCheckAmount to set
	 */
	public void setCreditCheckAmount(String creditCheckAmount) {
		this.creditCheckAmount = creditCheckAmount;
	}

	/**
	 * @return the drugTestAmount
	 */
	public String getDrugTestAmount() {
		return drugTestAmount;
	}

	/**
	 * @param drugTestAmount
	 *            the drugTestAmount to set
	 */
	public void setDrugTestAmount(String drugTestAmount) {
		this.drugTestAmount = drugTestAmount;
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
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address
	 *            the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the dysBillType
	 */
	public String getDysBillType() {
		return dysBillType;
	}

	/**
	 * @param dysBillType
	 *            the dysBillType to set
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
	 * @param dysDeliveryType
	 *            the dysDeliveryType to set
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
	 * @param dysPracticeArea
	 *            the dysPracticeArea to set
	 */
	public void setDysPracticeArea(String dysPracticeArea) {
		this.dysPracticeArea = dysPracticeArea;
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
	 * @return the pSSalesForceClientName
	 */
	public String getpSSalesForceClientName() {
		return pSSalesForceClientName;
	}

	/**
	 * @param pSSalesForceClientName
	 *            the pSSalesForceClientName to set
	 */
	public void setpSSalesForceClientName(String pSSalesForceClientName) {
		this.pSSalesForceClientName = pSSalesForceClientName;
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
	 * @return the volumeDiscount
	 */
	public Double getVolumeDiscount() {
		return volumeDiscount;
	}

	/**
	 * @param volumeDiscount
	 *            the volumeDiscount to set
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
	 * @param paymentDiscount
	 *            the paymentDiscount to set
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
	 * @param otherDiscount
	 *            the otherDiscount to set
	 */
	public void setOtherDiscount(Double otherDiscount) {
		this.otherDiscount = otherDiscount;
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
	 * @return the addressId
	 */
	public Integer getAddressId() {
		return addressId;
	}

	/**
	 * @param addressId
	 *            the addressId to set
	 */
	public void setAddressId(Integer addressId) {
		this.addressId = addressId;
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
	 * @return the pSClientId
	 */
	public String getpSClientId()
	{
		return pSClientId;
	}

	/**
	 * @param pSClientId the pSClientId to set
	 */
	public void setpSClientId(String pSClientId)
	{
		this.pSClientId = pSClientId;
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
	 * @return the psVerticalId
	 */
	public Integer getPsVerticalId() {
		return psVerticalId;
	}

	/**
	 * @param psVerticalId the psVerticalId to set
	 */
	public void setPsVerticalId(Integer psVerticalId) {
		this.psVerticalId = psVerticalId;
	}

	/**
	 * @return the psProductId
	 */
	public Integer getPsProductId() {
		return psProductId;
	}

	/**
	 * @param psProductId the psProductId to set
	 */
	public void setPsProductId(Integer psProductId) {
		this.psProductId = psProductId;
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
	
	public String getEntityName() {
		return entityName;
	}

	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}

	public String getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	@Override
	public String toString()
	{
		return "AnalyserClientDTO [invoiceFrequency=" + invoiceFrequency + ", contactName=" + contactName + ", distributionMethod=" + distributionMethod + ", specialNotes=" + specialNotes 
				+ ", paymentTerms=" + paymentTerms + ", adminFee=" + adminFee + ", email=" + email + ", volumeDiscount=" + volumeDiscount + ", paymentDiscount=" + paymentDiscount
				+ ", otherDiscount=" + otherDiscount + ", backgroundAmount=" + backgroundAmount + ", creditCheckAmount=" + creditCheckAmount + ", drugTestAmount=" + drugTestAmount + ", company=" + company 
				+ ", clientId=" + clientId + ", addressId=" + addressId + ", address=" + address + ", dysBillType=" + dysBillType + ", dysDeliveryType=" + dysDeliveryType + ", dysPracticeArea=" + dysPracticeArea
				+ ", verticalDescription=" + verticalDescription + ", productDescription=" + productDescription + ", pSVerticalCode=" + pSVerticalCode + ", pSProductCode=" + pSProductCode 
				+ ", pSBillingTypeLongName=" + pSBillingTypeLongName + ", pSServiceAreaLongName=" + pSServiceAreaLongName + ", pSSalesForceClientName=" + pSSalesForceClientName 
				+ ", updatedBy=" + updatedBy + ", status=" + status + ", pSClientId=" + pSClientId + ", totalDiscount=" + totalDiscount + ", country=" + country + ", setId=" + setId 
				+ ", psVerticalId=" + psVerticalId + ", psProductId=" + psProductId + ", portfolio=" + portfolio
				+ "]";
	}
}