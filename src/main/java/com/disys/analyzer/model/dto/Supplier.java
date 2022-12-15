package com.disys.analyzer.model.dto;

/****
 * @author Sajid
 * 
 */

import java.io.Serializable;

public class Supplier implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String emptyString = "";
	private String supplierId = emptyString; //ContractorId
	private String orgId = emptyString;
	private String legalName = emptyString;
	private String dbaName = emptyString;

	private String fin = emptyString;
	private String pocLastName = emptyString;
	private String pocFirstName = emptyString;
	private String paymentTerm = emptyString;
	private String vendorCode = emptyString;
	private String invoiceEmail = emptyString;

	private String headQuartersAddress = emptyString;
	private String headCity = emptyString;
	private String headState = emptyString;
	private String headZip = emptyString;
	private String website = emptyString;
	private String headPhone = emptyString;
	private String headFax = emptyString;
	private String federalTaxId = emptyString;
	private String dunsNumber = emptyString;
	private String remitAddress = emptyString;
	private String remitCity = emptyString;
	private String remitState = emptyString;
	private String remitZip = emptyString;
	private String accountingContact = emptyString;
	private String accountingEmail = emptyString;
	private String remitPhone = emptyString;
	private String remitFax = emptyString;
	private String primaryName = emptyString;
	private String primaryTitle = emptyString;
	private String primaryAddress = emptyString;
	private String primaryCity = emptyString;
	private String primaryState = emptyString;
	private String primaryZip = emptyString;
	private String primaryPhone = emptyString;
	private String primaryFax = emptyString;
	private String primaryEmail = emptyString;
	private String recruitmentName = emptyString;
	private String recruitmentTitle = emptyString;
	private String recruitmentAddress = emptyString;
	private String recruitmentCity = emptyString;
	private String recruitmentState = emptyString;
	private String recruitmentZip = emptyString;
	private String recruitmentPhone = emptyString;
	private String recruitmentFax = emptyString;
	private String recruitmentEmail = emptyString;
	private String officerName1 = emptyString;
	private String officerTitle1 = emptyString;
	private String officerName2 = emptyString;
	private String officerTitle2 = emptyString;
	private String officerName3 = emptyString;
	private String officerTitle3 = emptyString;
	private String officerName4 = emptyString;
	private String officerTitle4 = emptyString;
	private boolean corporation;
	private boolean proprietorship;
	private boolean partnership;
	private boolean other;
	private String otherDescription = emptyString;
	private String employeeCount = emptyString;
	private String dateEstablished = emptyString;
	private String inState = emptyString;
	private String incorporationCertificate = emptyString;
	private String statesAuthorized = emptyString;
	private boolean typeSubcontractor;
	private boolean type1099;
	private boolean smallBusiness;
	private boolean largeBusiness;
	private boolean smallDisadvantaged;
	private boolean womenOwned;
	private boolean veteranOwned;
	private boolean disabledOwned;
	private boolean minorityOwned;
	private boolean asiaPacific;
	private boolean africanAmerican;
	private boolean hispanicAmerican;
	private boolean nativeAmerican;
	private String naicsPrimaryCode = emptyString;
	private String naicsSecondaryCode = emptyString;
	private String generalLiability = emptyString;
	private String generalExpiration = emptyString;
	private String generalLimit = emptyString;
	private String workersLiability = emptyString;
	private String workersExpiration = emptyString;
	private String workersLimit = emptyString;
	private String employersLiability = emptyString;
	private String employersExpiration = emptyString;
	private String employersLimit = emptyString;
	private String automobileLiability = emptyString;
	private String automobileExpiration = emptyString;
	private String automobileLimit = emptyString;
	private String umbrellaLiability = emptyString;
	private String umbrellaExpiration = emptyString;
	private String umbrellaLimit = emptyString;
	private boolean disysAdditionalInsured;
	private boolean subcontractorAgreement;
	private boolean formW9;
	private boolean insuranceCertificate;
	private boolean workOrder;
	
	private String country = emptyString;

	private String updatedBy = emptyString;
	private String updatedDate = emptyString;
	private String discount = emptyString;
	private double discountAmount;
	private String amountType = emptyString;

	private String generalType = emptyString;
	private String workersType = emptyString;
	private String employersType = emptyString;
	private String automobileType = emptyString;
	private String umbrellaType = emptyString;
	
	private String subcontractorComments = emptyString;
	private String company_ = emptyString;
	private String subContractorInfoVerify = emptyString;
	
	private String status = emptyString;
	private String approvalStatus = emptyString; // subcontractorapprove

	private boolean submitStatus;
	private String recordStatus = emptyString;
	private String entryDate = emptyString;
	private String approvalDate = emptyString;
	private String approvedBy = emptyString;
	private String comments = emptyString;

	private boolean llc;
	private String pSVendorId;
	
	private String companyName;
	private String address1;
	private String address2;
	private String city;
	private String state;
	private String zipCode;
	private String companyCode;
	
	public Supplier()
	{
	}

	public String getSubcontractorComments()
	{
		return subcontractorComments;
	}

	public void setSubcontractorComments(String subcontractorComments)
	{
		if (subcontractorComments == null || subcontractorComments.equals("null"))
		{
			subcontractorComments = emptyString;
		}

		this.subcontractorComments = subcontractorComments;
	}

	public String getCompany()
	{
		return company_;
	}

	public void setCompany(String company)
	{
		if (company == null || company.equals("null"))
		{
			company = emptyString;
		}

		company_ = company;
	}

	public String getSubContractorInfoVerify()
	{
		return this.subContractorInfoVerify;
	}

	public void setSubContractorInfoVerify(String subContractorInfoVerify)
	{
		if (subContractorInfoVerify == null || subContractorInfoVerify.equals("null"))
		{
			subContractorInfoVerify = emptyString;
		}

		this.subContractorInfoVerify = subContractorInfoVerify;
	}

	public void setApprovalStatus(String approvalStatus)
	{
		if (approvalStatus == null || approvalStatus.equals("null"))
		{
			approvalStatus = emptyString;
		}

		this.approvalStatus = approvalStatus;
	}

	public String getApprovalStatus()
	{
		return this.approvalStatus;
	}

	/**
	 * Returns the value of supplierId.
	 */
	public String getSupplierId()
	{
		return supplierId;
	}

	/**
	 * Sets the value of supplierId.
	 * 
	 * @param supplierId
	 *            The value to assign supplierId.
	 */
	public void setSupplierId(String supplierId)
	{
		if (supplierId == null || supplierId.equals("null"))
		{
			supplierId = emptyString;
		}
		this.supplierId = supplierId;
	}

	/**
	 * Returns the value of orgId.
	 */
	public String getOrgId()
	{
		return orgId;
	}

	/**
	 * Sets the value of orgId.
	 * 
	 * @param orgId
	 *            The value to assign orgId.
	 */
	public void setOrgId(String orgId)
	{
		if (orgId == null || orgId.equals("null"))
		{
			orgId = emptyString;
		}

		this.orgId = orgId;
	}

	/**
	 * Returns the value of legalName.
	 */
	public String getLegalName()
	{
		return legalName;
	}

	/**
	 * Sets the value of legalName.
	 * 
	 * @param legalName
	 *            The value to assign legalName.
	 */
	public void setLegalName(String legalName)
	{
		if (legalName == null || legalName.equals("null"))
		{
			legalName = emptyString;
		}

		this.legalName = legalName;
	}

	/**
	 * Returns the value of dbaName.
	 */
	public String getDbaName()
	{
		return dbaName;
	}

	/**
	 * Sets the value of dbaName.
	 * 
	 * @param dbaName
	 *            The value to assign dbaName.
	 */
	public void setDbaName(String dbaName)
	{
		if (dbaName == null || dbaName.equals("null"))
		{
			dbaName = emptyString;
		}

		this.dbaName = dbaName;
	}

	/**
	 * Returns the value of fin.
	 */
	public String getFin()
	{
		return fin;
	}

	/**
	 * Sets the value of fin.
	 * 
	 * @param fin
	 *            The value to assign fin.
	 */
	public void setFin(String fin)
	{
		if (fin == null || fin.equals("null"))
		{
			fin = emptyString;
		}

		this.fin = fin;
	}

	/**
	 * Returns the value of pocLastName.
	 */
	public String getPocLastName()
	{
		return pocLastName;
	}

	/**
	 * Sets the value of pocLastName.
	 * 
	 * @param pocLastName
	 *            The value to assign pocLastName.
	 */
	public void setPocLastName(String pocLastName)
	{
		if (pocLastName == null || pocLastName.equals("null"))
		{
			pocLastName = emptyString;
		}

		this.pocLastName = pocLastName;
	}

	/**
	 * Returns the value of pocFirstName.
	 */
	public String getPocFirstName()
	{
		return pocFirstName;
	}

	/**
	 * Sets the value of pocFirstName.
	 * 
	 * @param pocFirstName
	 *            The value to assign pocFirstName.
	 */
	public void setPocFirstName(String pocFirstName)
	{
		if (pocFirstName == null || pocFirstName.equals("null"))
		{
			pocFirstName = emptyString;
		}
		this.pocFirstName = pocFirstName;
	}

	/**
	 * Returns the value of paymentTerm.
	 */
	public String getPaymentTerm()
	{
		return paymentTerm;
	}

	/**
	 * Sets the value of paymentTerm.
	 * 
	 * @param paymentTerm
	 *            The value to assign paymentTerm.
	 */
	public void setPaymentTerm(String paymentTerm)
	{
		if (paymentTerm == null || paymentTerm.equals("null"))
		{
			paymentTerm = emptyString;
		}
		this.paymentTerm = paymentTerm;
	}

	/**
	 * Returns the value of vendorCode.
	 */
	public String getVendorCode()
	{
		return vendorCode;
	}

	/**
	 * Sets the value of vendorCode.
	 * 
	 * @param vendorCode
	 *            The value to assign vendorCode.
	 */
	public void setVendorCode(String vendorCode)
	{
		if (vendorCode == null || vendorCode.equals("null"))
		{
			vendorCode = emptyString;
		}
		this.vendorCode = vendorCode;
	}

	/**
	 * Returns the value of invoiceEmail.
	 */
	public String getInvoiceEmail()
	{
		return invoiceEmail;
	}

	/**
	 * Sets the value of invoiceEmail.
	 * 
	 * @param invoiceEmail
	 *            The value to assign invoiceEmail.
	 */
	public void setInvoiceEmail(String invoiceEmail)
	{
		if (invoiceEmail == null || invoiceEmail.equals("null"))
		{
			invoiceEmail = emptyString;
		}
		this.invoiceEmail = invoiceEmail;
	}

	/**
	 * Returns the value of headQuartersAddress.
	 */
	public String getHeadQuartersAddress()
	{
		return headQuartersAddress;
	}

	/**
	 * Sets the value of headQuartersAddress.
	 * 
	 * @param headQuartersAddress
	 *            The value to assign headQuartersAddress.
	 */
	public void setHeadQuartersAddress(String headQuartersAddress)
	{
		if (headQuartersAddress == null || headQuartersAddress.equals("null"))
		{
			headQuartersAddress = emptyString;
		}
		this.headQuartersAddress = headQuartersAddress;
	}

	/**
	 * Returns the value of headCity.
	 */
	public String getHeadCity()
	{
		return headCity;
	}

	/**
	 * Sets the value of headCity.
	 * 
	 * @param headCity
	 *            The value to assign headCity.
	 */
	public void setHeadCity(String headCity)
	{
		if (headCity == null || headCity.equals("null"))
		{
			headCity = emptyString;
		}
		this.headCity = headCity;
	}

	/**
	 * Returns the value of headState.
	 */
	public String getHeadState()
	{
		return headState;
	}

	/**
	 * Sets the value of headState.
	 * 
	 * @param headState
	 *            The value to assign headState.
	 */
	public void setHeadState(String headState)
	{
		if (headState == null || headState.equals("null"))
		{
			headState = emptyString;
		}
		this.headState = headState;
	}

	/**
	 * Returns the value of headZip.
	 */
	public String getHeadZip()
	{
		return headZip;
	}

	/**
	 * Sets the value of headZip.
	 * 
	 * @param headZip
	 *            The value to assign headZip.
	 */
	public void setHeadZip(String headZip)
	{
		if (headZip == null || headZip.equals("null"))
		{
			headZip = emptyString;
		}
		this.headZip = headZip;
	}

	/**
	 * Returns the value of website.
	 */
	public String getWebsite()
	{
		return website;
	}

	/**
	 * Sets the value of website.
	 * 
	 * @param website
	 *            The value to assign website.
	 */
	public void setWebsite(String website)
	{
		if (website == null || website.equals("null"))
		{
			website = emptyString;
		}
		this.website = website;
	}

	/**
	 * Returns the value of headPhone.
	 */
	public String getHeadPhone()
	{
		return headPhone;
	}

	/**
	 * Sets the value of headPhone.
	 * 
	 * @param headPhone
	 *            The value to assign headPhone.
	 */
	public void setHeadPhone(String headPhone)
	{
		if (headPhone == null || headPhone.equals("null"))
		{
			headPhone = emptyString;
		}
		this.headPhone = headPhone;
	}

	/**
	 * Returns the value of headFax.
	 */
	public String getHeadFax()
	{
		return headFax;
	}

	/**
	 * Sets the value of headFax.
	 * 
	 * @param headFax
	 *            The value to assign headFax.
	 */
	public void setHeadFax(String headFax)
	{
		if (headFax == null || headFax.equals("null"))
		{
			headFax = emptyString;
		}
		this.headFax = headFax;
	}

	/**
	 * Returns the value of federalTaxId.
	 */
	public String getFederalTaxId()
	{
		return federalTaxId;
	}

	/**
	 * Sets the value of federalTaxId.
	 * 
	 * @param federalTaxId
	 *            The value to assign federalTaxId.
	 */
	public void setFederalTaxId(String federalTaxId)
	{
		if (federalTaxId == null || federalTaxId.equals("null"))
		{
			federalTaxId = emptyString;
		}
		this.federalTaxId = federalTaxId;
	}

	/**
	 * Returns the value of dunsNumber.
	 */
	public String getDunsNumber()
	{
		return dunsNumber;
	}

	/**
	 * Sets the value of dunsNumber.
	 * 
	 * @param dunsNumber
	 *            The value to assign dunsNumber.
	 */
	public void setDunsNumber(String dunsNumber)
	{
		if (dunsNumber == null || dunsNumber.equals("null"))
		{
			dunsNumber = emptyString;
		}
		this.dunsNumber = dunsNumber;
	}

	/**
	 * Returns the value of remitAddress.
	 */
	public String getRemitAddress()
	{
		return remitAddress;
	}

	/**
	 * Sets the value of remitAddress.
	 * 
	 * @param remitAddress
	 *            The value to assign remitAddress.
	 */
	public void setRemitAddress(String remitAddress)
	{
		if (remitAddress == null || remitAddress.equals("null"))
		{
			remitAddress = emptyString;
		}
		this.remitAddress = remitAddress;
	}

	/**
	 * Returns the value of remitCity.
	 */
	public String getRemitCity()
	{
		return remitCity;
	}

	/**
	 * Sets the value of remitCity.
	 * 
	 * @param remitCity
	 *            The value to assign remitCity.
	 */
	public void setRemitCity(String remitCity)
	{
		if (remitCity == null || remitCity.equals("null"))
		{
			remitCity = emptyString;
		}
		this.remitCity = remitCity;
	}

	/**
	 * Returns the value of remitState.
	 */
	public String getRemitState()
	{
		return remitState;
	}

	/**
	 * Sets the value of remitState.
	 * 
	 * @param remitState
	 *            The value to assign remitState.
	 */
	public void setRemitState(String remitState)
	{
		if (remitState == null || remitState.equals("null"))
		{
			remitState = emptyString;
		}
		this.remitState = remitState;
	}

	/**
	 * Returns the value of remitZip.
	 */
	public String getRemitZip()
	{
		return remitZip;
	}

	/**
	 * Sets the value of remitZip.
	 * 
	 * @param remitZip
	 *            The value to assign remitZip.
	 */
	public void setRemitZip(String remitZip)
	{
		if (remitZip == null || remitZip.equals("null"))
		{
			remitZip = emptyString;
		}
		this.remitZip = remitZip;
	}

	/**
	 * Returns the value of accountingContact.
	 */
	public String getAccountingContact()
	{
		return accountingContact;
	}

	/**
	 * Sets the value of accountingContact.
	 * 
	 * @param accountingContact
	 *            The value to assign accountingContact.
	 */
	public void setAccountingContact(String accountingContact)
	{
		if (accountingContact == null || accountingContact.equals("null"))
		{
			accountingContact = emptyString;
		}
		this.accountingContact = accountingContact;
	}

	/**
	 * Returns the value of accountingEmail.
	 */
	public String getAccountingEmail()
	{
		return accountingEmail;
	}

	/**
	 * Sets the value of accountingEmail.
	 * 
	 * @param accountingEmail
	 *            The value to assign accountingEmail.
	 */
	public void setAccountingEmail(String accountingEmail)
	{
		if (accountingEmail == null || accountingEmail.equals("null"))
		{
			accountingEmail = emptyString;
		}
		this.accountingEmail = accountingEmail;
	}

	/**
	 * Returns the value of remitPhone.
	 */
	public String getRemitPhone()
	{
		return remitPhone;
	}

	/**
	 * Sets the value of remitPhone.
	 * 
	 * @param remitPhone
	 *            The value to assign remitPhone.
	 */
	public void setRemitPhone(String remitPhone)
	{
		if (remitPhone == null || remitPhone.equals("null"))
		{
			remitPhone = emptyString;
		}
		this.remitPhone = remitPhone;
	}

	/**
	 * Returns the value of remitFax.
	 */
	public String getRemitFax()
	{
		return remitFax;
	}

	/**
	 * Sets the value of remitFax.
	 * 
	 * @param remitFax
	 *            The value to assign remitFax.
	 */
	public void setRemitFax(String remitFax)
	{
		if (remitFax == null || remitFax.equals("null"))
		{
			remitFax = emptyString;
		}
		this.remitFax = remitFax;
	}

	/**
	 * Returns the value of primaryName.
	 */
	public String getPrimaryName()
	{
		return primaryName;
	}

	/**
	 * Sets the value of primaryName.
	 * 
	 * @param primaryName
	 *            The value to assign primaryName.
	 */
	public void setPrimaryName(String primaryName)
	{
		if (primaryName == null || primaryName.equals("null"))
		{
			primaryName = emptyString;
		}
		this.primaryName = primaryName;
	}

	/**
	 * Returns the value of primaryTitle.
	 */
	public String getPrimaryTitle()
	{
		return primaryTitle;
	}

	/**
	 * Sets the value of primaryTitle.
	 * 
	 * @param primaryTitle
	 *            The value to assign primaryTitle.
	 */
	public void setPrimaryTitle(String primaryTitle)
	{
		if (primaryTitle == null || primaryTitle.equals("null"))
		{
			primaryTitle = emptyString;
		}
		this.primaryTitle = primaryTitle;
	}

	/**
	 * Returns the value of primaryAddress.
	 */
	public String getPrimaryAddress()
	{
		return primaryAddress;
	}

	/**
	 * Sets the value of primaryAddress.
	 * 
	 * @param primaryAddress
	 *            The value to assign primaryAddress.
	 */
	public void setPrimaryAddress(String primaryAddress)
	{
		if (primaryAddress == null || primaryAddress.equals("null"))
		{
			primaryAddress = emptyString;
		}
		this.primaryAddress = primaryAddress;
	}

	/**
	 * Returns the value of primaryCity.
	 */
	public String getPrimaryCity()
	{
		return primaryCity;
	}

	/**
	 * Sets the value of primaryCity.
	 * 
	 * @param primaryCity
	 *            The value to assign primaryCity.
	 */
	public void setPrimaryCity(String primaryCity)
	{
		if (primaryCity == null || primaryCity.equals("null"))
		{
			primaryCity = emptyString;
		}
		this.primaryCity = primaryCity;
	}

	/**
	 * Returns the value of primaryState.
	 */
	public String getPrimaryState()
	{
		return primaryState;
	}

	/**
	 * Sets the value of primaryState.
	 * 
	 * @param primaryState
	 *            The value to assign primaryState.
	 */
	public void setPrimaryState(String primaryState)
	{
		if (primaryState == null || primaryState.equals("null"))
		{
			primaryState = emptyString;
		}
		this.primaryState = primaryState;
	}

	/**
	 * Returns the value of primaryZip.
	 */
	public String getPrimaryZip()
	{
		return primaryZip;
	}

	/**
	 * Sets the value of primaryZip.
	 * 
	 * @param primaryZip
	 *            The value to assign primaryZip.
	 */
	public void setPrimaryZip(String primaryZip)
	{
		if (primaryZip == null || primaryZip.equals("null"))
		{
			primaryZip = emptyString;
		}
		this.primaryZip = primaryZip;
	}

	/**
	 * Returns the value of primaryPhone.
	 */
	public String getPrimaryPhone()
	{
		return primaryPhone;
	}

	/**
	 * Sets the value of primaryPhone.
	 * 
	 * @param primaryPhone
	 *            The value to assign primaryPhone.
	 */
	public void setPrimaryPhone(String primaryPhone)
	{
		if (primaryPhone == null || primaryPhone.equals("null"))
		{
			primaryPhone = emptyString;
		}
		this.primaryPhone = primaryPhone;
	}

	/**
	 * Returns the value of primaryFax.
	 */
	public String getPrimaryFax()
	{
		return primaryFax;
	}

	/**
	 * Sets the value of primaryFax.
	 * 
	 * @param primaryFax
	 *            The value to assign primaryFax.
	 */
	public void setPrimaryFax(String primaryFax)
	{
		if (primaryFax == null || primaryFax.equals("null"))
		{
			primaryFax = emptyString;
		}
		this.primaryFax = primaryFax;
	}

	/**
	 * Returns the value of primaryEmail.
	 */
	public String getPrimaryEmail()
	{
		return primaryEmail;
	}

	/**
	 * Sets the value of primaryEmail.
	 * 
	 * @param primaryEmail
	 *            The value to assign primaryEmail.
	 */
	public void setPrimaryEmail(String primaryEmail)
	{
		if (primaryEmail == null || primaryEmail.equals("null"))
		{
			primaryEmail = emptyString;
		}
		this.primaryEmail = primaryEmail;
	}

	/**
	 * Returns the value of recruitmentName.
	 */
	public String getRecruitmentName()
	{
		return recruitmentName;
	}

	/**
	 * Sets the value of recruitmentName.
	 * 
	 * @param recruitmentName
	 *            The value to assign recruitmentName.
	 */
	public void setRecruitmentName(String recruitmentName)
	{
		if (recruitmentName == null || recruitmentName.equals("null"))
		{
			recruitmentName = emptyString;
		}
		this.recruitmentName = recruitmentName;
	}

	/**
	 * Returns the value of recruitmentTitle.
	 */
	public String getRecruitmentTitle()
	{
		return recruitmentTitle;
	}

	/**
	 * Sets the value of recruitmentTitle.
	 * 
	 * @param recruitmentTitle
	 *            The value to assign recruitmentTitle.
	 */
	public void setRecruitmentTitle(String recruitmentTitle)
	{
		if (recruitmentTitle == null || recruitmentTitle.equals("null"))
		{
			recruitmentTitle = emptyString;
		}
		this.recruitmentTitle = recruitmentTitle;
	}

	/**
	 * Returns the value of recruitmentAddress.
	 */
	public String getRecruitmentAddress()
	{
		return recruitmentAddress;
	}

	/**
	 * Sets the value of recruitmentAddress.
	 * 
	 * @param recruitmentAddress
	 *            The value to assign recruitmentAddress.
	 */
	public void setRecruitmentAddress(String recruitmentAddress)
	{
		if (recruitmentAddress == null || recruitmentAddress.equals("null"))
		{
			recruitmentAddress = emptyString;
		}
		this.recruitmentAddress = recruitmentAddress;
	}

	/**
	 * Returns the value of recruitmentCity.
	 */
	public String getRecruitmentCity()
	{
		return recruitmentCity;
	}

	/**
	 * Sets the value of recruitmentCity.
	 * 
	 * @param recruitmentCity
	 *            The value to assign recruitmentCity.
	 */
	public void setRecruitmentCity(String recruitmentCity)
	{
		if (recruitmentCity == null || recruitmentCity.equals("null"))
		{
			recruitmentCity = emptyString;
		}
		this.recruitmentCity = recruitmentCity;
	}

	/**
	 * Returns the value of recruitmentState.
	 */
	public String getRecruitmentState()
	{
		return recruitmentState;
	}

	/**
	 * Sets the value of recruitmentState.
	 * 
	 * @param recruitmentState
	 *            The value to assign recruitmentState.
	 */
	public void setRecruitmentState(String recruitmentState)
	{
		if (recruitmentState == null || recruitmentState.equals("null"))
		{
			recruitmentState = emptyString;
		}
		this.recruitmentState = recruitmentState;
	}

	/**
	 * Returns the value of recruitmentZip.
	 */
	public String getRecruitmentZip()
	{
		return recruitmentZip;
	}

	/**
	 * Sets the value of recruitmentZip.
	 * 
	 * @param recruitmentZip
	 *            The value to assign recruitmentZip.
	 */
	public void setRecruitmentZip(String recruitmentZip)
	{
		if (recruitmentZip == null || recruitmentZip.equals("null"))
		{
			recruitmentZip = emptyString;
		}
		this.recruitmentZip = recruitmentZip;
	}

	/**
	 * Returns the value of recruitmentPhone.
	 */
	public String getRecruitmentPhone()
	{
		return recruitmentPhone;
	}

	/**
	 * Sets the value of recruitmentPhone.
	 * 
	 * @param recruitmentPhone
	 *            The value to assign recruitmentPhone.
	 */
	public void setRecruitmentPhone(String recruitmentPhone)
	{
		if (recruitmentPhone == null || recruitmentPhone.equals("null"))
		{
			recruitmentPhone = emptyString;
		}
		this.recruitmentPhone = recruitmentPhone;
	}

	/**
	 * Returns the value of recruitmentFax.
	 */
	public String getRecruitmentFax()
	{
		return recruitmentFax;
	}

	/**
	 * Sets the value of recruitmentFax.
	 * 
	 * @param recruitmentFax
	 *            The value to assign recruitmentFax.
	 */
	public void setRecruitmentFax(String recruitmentFax)
	{
		if (recruitmentFax == null || recruitmentFax.equals("null"))
		{
			recruitmentFax = emptyString;
		}
		this.recruitmentFax = recruitmentFax;
	}

	/**
	 * Returns the value of recruitmentEmail.
	 */
	public String getRecruitmentEmail()
	{
		return recruitmentEmail;
	}

	/**
	 * Sets the value of recruitmentEmail.
	 * 
	 * @param recruitmentEmail
	 *            The value to assign recruitmentEmail.
	 */
	public void setRecruitmentEmail(String recruitmentEmail)
	{
		if (recruitmentEmail == null || recruitmentEmail.equals("null"))
		{
			recruitmentEmail = emptyString;
		}
		this.recruitmentEmail = recruitmentEmail;
	}

	/**
	 * Returns the value of officerName1.
	 */
	public String getOfficerName1()
	{
		return officerName1;
	}

	/**
	 * Sets the value of officerName1.
	 * 
	 * @param officerName1
	 *            The value to assign officerName1.
	 */
	public void setOfficerName1(String officerName1)
	{
		if (officerName1 == null || officerName1.equals("null"))
		{
			officerName1 = emptyString;
		}
		this.officerName1 = officerName1;
	}

	/**
	 * Returns the value of officerTitle1.
	 */
	public String getOfficerTitle1()
	{
		return officerTitle1;
	}

	/**
	 * Sets the value of officerTitle1.
	 * 
	 * @param officerTitle1
	 *            The value to assign officerTitle1.
	 */
	public void setOfficerTitle1(String officerTitle1)
	{
		if (officerTitle1 == null || officerTitle1.equals("null"))
		{
			officerTitle1 = emptyString;
		}
		this.officerTitle1 = officerTitle1;
	}

	/**
	 * Returns the value of officerName2.
	 */
	public String getOfficerName2()
	{
		return officerName2;
	}

	/**
	 * Sets the value of officerName2.
	 * 
	 * @param officerName2
	 *            The value to assign officerName2.
	 */
	public void setOfficerName2(String officerName2)
	{
		if (officerName2 == null || officerName2.equals("null"))
		{
			officerName2 = emptyString;
		}
		this.officerName2 = officerName2;
	}

	/**
	 * Returns the value of officerTitle2.
	 */
	public String getOfficerTitle2()
	{
		return officerTitle2;
	}

	/**
	 * Sets the value of officerTitle2.
	 * 
	 * @param officerTitle2
	 *            The value to assign officerTitle2.
	 */
	public void setOfficerTitle2(String officerTitle2)
	{
		if (officerTitle2 == null || officerTitle2.equals("null"))
		{
			officerTitle2 = emptyString;
		}
		this.officerTitle2 = officerTitle2;
	}

	/**
	 * Returns the value of officerName3.
	 */
	public String getOfficerName3()
	{
		return officerName3;
	}

	/**
	 * Sets the value of officerName3.
	 * 
	 * @param officerName3
	 *            The value to assign officerName3.
	 */
	public void setOfficerName3(String officerName3)
	{
		if (officerName3 == null || officerName3.equals("null"))
		{
			officerName3 = emptyString;
		}
		this.officerName3 = officerName3;
	}

	/**
	 * Returns the value of officerTitle3.
	 */
	public String getOfficerTitle3()
	{
		return officerTitle3;
	}

	/**
	 * Sets the value of officerTitle3.
	 * 
	 * @param officerTitle3
	 *            The value to assign officerTitle3.
	 */
	public void setOfficerTitle3(String officerTitle3)
	{
		if (officerTitle3 == null || officerTitle3.equals("null"))
		{
			officerTitle3 = emptyString;
		}
		this.officerTitle3 = officerTitle3;
	}

	/**
	 * Returns the value of officerName4.
	 */
	public String getOfficerName4()
	{
		return officerName4;
	}

	/**
	 * Sets the value of officerName4.
	 * 
	 * @param officerName4
	 *            The value to assign officerName4.
	 */
	public void setOfficerName4(String officerName4)
	{
		if (officerName4 == null || officerName4.equals("null"))
		{
			officerName4 = emptyString;
		}
		this.officerName4 = officerName4;
	}

	/**
	 * Returns the value of officerTitle4.
	 */
	public String getOfficerTitle4()
	{
		return officerTitle4;
	}

	/**
	 * Sets the value of officerTitle4.
	 * 
	 * @param officerTitle4
	 *            The value to assign officerTitle4.
	 */
	public void setOfficerTitle4(String officerTitle4)
	{
		if (officerTitle4 == null || officerTitle4.equals("null"))
		{
			officerTitle4 = emptyString;
		}
		this.officerTitle4 = officerTitle4;
	}

	/**
	 * Returns the value of corporation.
	 */
	public boolean getCorporation()
	{
		return corporation;
	}

	/**
	 * Sets the value of corporation.
	 * 
	 * @param corporation
	 *            The value to assign corporation.
	 */
	public void setCorporation(boolean corporation)
	{
		this.corporation = corporation;
	}

	/**
	 * Returns the value of proprietorship.
	 */
	public boolean getProprietorship()
	{
		return proprietorship;
	}

	/**
	 * Sets the value of proprietorship.
	 * 
	 * @param proprietorship
	 *            The value to assign proprietorship.
	 */
	public void setProprietorship(boolean proprietorship)
	{
		this.proprietorship = proprietorship;
	}

	/**
	 * Returns the value of partnership.
	 */
	public boolean getPartnership()
	{
		return partnership;
	}

	/**
	 * Sets the value of partnership.
	 * 
	 * @param partnership
	 *            The value to assign partnership.
	 */
	public void setPartnership(boolean partnership)
	{
		this.partnership = partnership;
	}

	/**
	 * Returns the value of other.
	 */
	public boolean getOther()
	{
		return other;
	}

	/**
	 * Sets the value of other.
	 * 
	 * @param other
	 *            The value to assign other.
	 */
	public void setOther(boolean other)
	{
		this.other = other;
	}

	/**
	 * Returns the value of otherDescription.
	 */
	public String getOtherDescription()
	{
		return otherDescription;
	}

	/**
	 * Sets the value of otherDescription.
	 * 
	 * @param otherDescription
	 *            The value to assign otherDescription.
	 */
	public void setOtherDescription(String otherDescription)
	{
		if (otherDescription == null || otherDescription.equals("null"))
		{
			otherDescription = emptyString;
		}
		this.otherDescription = otherDescription;
	}

	/**
	 * Returns the value of employeeCount.
	 */
	public String getEmployeeCount()
	{
		return employeeCount;
	}

	/**
	 * Sets the value of employeeCount.
	 * 
	 * @param employeeCount
	 *            The value to assign employeeCount.
	 */
	public void setEmployeeCount(String employeeCount)
	{
		if (employeeCount == null || employeeCount.equals("null"))
		{
			employeeCount = emptyString;
		}
		this.employeeCount = employeeCount;
	}

	/**
	 * Returns the value of dateEstablished.
	 */
	public String getDateEstablished()
	{
		return dateEstablished;
	}

	/**
	 * Sets the value of dateEstablished.
	 * 
	 * @param dateEstablished
	 *            The value to assign dateEstablished.
	 */
	public void setDateEstablished(String dateEstablished)
	{
		if (dateEstablished == null || dateEstablished.equals("null"))
		{
			dateEstablished = emptyString;
		}
		this.dateEstablished = dateEstablished;
	}

	/**
	 * Returns the value of inState.
	 */
	public String getInState()
	{
		return inState;
	}

	/**
	 * Sets the value of inState.
	 * 
	 * @param inState
	 *            The value to assign inState.
	 */
	public void setInState(String inState)
	{
		if (inState == null || inState.equals("null"))
		{
			inState = emptyString;
		}
		this.inState = inState;
	}

	/**
	 * Returns the value of incorporationCertificate.
	 */
	public String getIncorporationCertificate()
	{
		return incorporationCertificate;
	}

	/**
	 * Sets the value of incorporationCertificate.
	 * 
	 * @param incorporationCertificate
	 *            The value to assign incorporationCertificate.
	 */
	public void setIncorporationCertificate(String incorporationCertificate)
	{
		if (incorporationCertificate == null || incorporationCertificate.equals("null"))
		{
			incorporationCertificate = emptyString;
		}
		this.incorporationCertificate = incorporationCertificate;
	}

	/**
	 * Returns the value of statesAuthorized.
	 */
	public String getStatesAuthorized()
	{
		return statesAuthorized;
	}

	/**
	 * Sets the value of statesAuthorized.
	 * 
	 * @param statesAuthorized
	 *            The value to assign statesAuthorized.
	 */
	public void setStatesAuthorized(String statesAuthorized)
	{
		if (statesAuthorized == null || statesAuthorized.equals("null"))
		{
			statesAuthorized = emptyString;
		}
		this.statesAuthorized = statesAuthorized;
	}

	/**
	 * Returns the value of typeSubcontractor.
	 */
	public boolean getTypeSubcontractor()
	{
		return typeSubcontractor;
	}

	/**
	 * Sets the value of typeSubcontractor.
	 * 
	 * @param typeSubcontractor
	 *            The value to assign typeSubcontractor.
	 */
	public void setTypeSubcontractor(boolean typeSubcontractor)
	{
		this.typeSubcontractor = typeSubcontractor;
	}

	/**
	 * Returns the value of type1099.
	 */
	public boolean getType1099()
	{
		return type1099;
	}

	/**
	 * Sets the value of type1099.
	 * 
	 * @param type1099
	 *            The value to assign type1099.
	 */
	public void setType1099(boolean type1099)
	{
		this.type1099 = type1099;
	}

	/**
	 * Returns the value of smallBusiness.
	 */
	public boolean getSmallBusiness()
	{
		return smallBusiness;
	}

	/**
	 * Sets the value of smallBusiness.
	 * 
	 * @param smallBusiness
	 *            The value to assign smallBusiness.
	 */
	public void setSmallBusiness(boolean smallBusiness)
	{
		this.smallBusiness = smallBusiness;
	}

	/**
	 * Returns the value of largeBusiness.
	 */
	public boolean getLargeBusiness()
	{
		return largeBusiness;
	}

	/**
	 * Sets the value of largeBusiness.
	 * 
	 * @param largeBusiness
	 *            The value to assign largeBusiness.
	 */
	public void setLargeBusiness(boolean largeBusiness)
	{
		this.largeBusiness = largeBusiness;
	}

	/**
	 * Returns the value of smallDisadvantaged.
	 */
	public boolean getSmallDisadvantaged()
	{
		return smallDisadvantaged;
	}

	/**
	 * Sets the value of smallDisadvantaged.
	 * 
	 * @param smallDisadvantaged
	 *            The value to assign smallDisadvantaged.
	 */
	public void setSmallDisadvantaged(boolean smallDisadvantaged)
	{
		this.smallDisadvantaged = smallDisadvantaged;
	}

	/**
	 * Returns the value of womenOwned.
	 */
	public boolean getWomenOwned()
	{
		return womenOwned;
	}

	/**
	 * Sets the value of womenOwned.
	 * 
	 * @param womenOwned
	 *            The value to assign womenOwned.
	 */
	public void setWomenOwned(boolean womenOwned)
	{
		this.womenOwned = womenOwned;
	}

	/**
	 * Returns the value of veteranOwned.
	 */
	public boolean getVeteranOwned()
	{
		return veteranOwned;
	}

	/**
	 * Sets the value of veteranOwned.
	 * 
	 * @param veteranOwned
	 *            The value to assign veteranOwned.
	 */
	public void setVeteranOwned(boolean veteranOwned)
	{
		this.veteranOwned = veteranOwned;
	}

	/**
	 * Returns the value of disabledOwned.
	 */
	public boolean getDisabledOwned()
	{
		return disabledOwned;
	}

	/**
	 * Sets the value of disabledOwned.
	 * 
	 * @param disabledOwned
	 *            The value to assign disabledOwned.
	 */
	public void setDisabledOwned(boolean disabledOwned)
	{
		this.disabledOwned = disabledOwned;
	}

	/**
	 * Returns the value of minorityOwned.
	 */
	public boolean getMinorityOwned()
	{
		return minorityOwned;
	}

	/**
	 * Sets the value of minorityOwned.
	 * 
	 * @param minorityOwned
	 *            The value to assign minorityOwned.
	 */
	public void setMinorityOwned(boolean minorityOwned)
	{
		this.minorityOwned = minorityOwned;
	}

	/**
	 * Returns the value of asiaPacific.
	 */
	public boolean getAsiaPacific()
	{
		return asiaPacific;
	}

	/**
	 * Sets the value of asiaPacific.
	 * 
	 * @param asiaPacific
	 *            The value to assign asiaPacific.
	 */
	public void setAsiaPacific(boolean asiaPacific)
	{
		this.asiaPacific = asiaPacific;
	}

	/**
	 * Returns the value of africanAmerican.
	 */
	public boolean getAfricanAmerican()
	{
		return africanAmerican;
	}

	/**
	 * Sets the value of africanAmerican.
	 * 
	 * @param africanAmerican
	 *            The value to assign africanAmerican.
	 */
	public void setAfricanAmerican(boolean africanAmerican)
	{
		this.africanAmerican = africanAmerican;
	}

	/**
	 * Returns the value of hispanicAmerican.
	 */
	public boolean getHispanicAmerican()
	{
		return hispanicAmerican;
	}

	/**
	 * Sets the value of hispanicAmerican.
	 * 
	 * @param hispanicAmerican
	 *            The value to assign hispanicAmerican.
	 */
	public void setHispanicAmerican(boolean hispanicAmerican)
	{
		this.hispanicAmerican = hispanicAmerican;
	}

	/**
	 * Returns the value of nativeAmerican.
	 */
	public boolean getNativeAmerican()
	{
		return nativeAmerican;
	}

	/**
	 * Sets the value of nativeAmerican.
	 * 
	 * @param nativeAmerican
	 *            The value to assign nativeAmerican.
	 */
	public void setNativeAmerican(boolean nativeAmerican)
	{
		this.nativeAmerican = nativeAmerican;
	}

	/**
	 * Returns the value of naicsPrimaryCode.
	 */
	public String getNaicsPrimaryCode()
	{
		return naicsPrimaryCode;
	}

	/**
	 * Sets the value of naicsPrimaryCode.
	 * 
	 * @param naicsPrimaryCode
	 *            The value to assign naicsPrimaryCode.
	 */
	public void setNaicsPrimaryCode(String naicsPrimaryCode)
	{
		if (naicsPrimaryCode == null || naicsPrimaryCode.equals("null"))
		{
			naicsPrimaryCode = emptyString;
		}
		this.naicsPrimaryCode = naicsPrimaryCode;
	}

	/**
	 * Returns the value of naicsSecondaryCode.
	 */
	public String getNaicsSecondaryCode()
	{
		return naicsSecondaryCode;
	}

	/**
	 * Sets the value of naicsSecondaryCode.
	 * 
	 * @param naicsSecondaryCode
	 *            The value to assign naicsSecondaryCode.
	 */
	public void setNaicsSecondaryCode(String naicsSecondaryCode)
	{
		if (naicsSecondaryCode == null || naicsSecondaryCode.equals("null"))
		{
			naicsSecondaryCode = emptyString;
		}
		this.naicsSecondaryCode = naicsSecondaryCode;
	}

	/**
	 * Returns the value of generalLiability.
	 */
	public String getGeneralLiability()
	{
		return generalLiability;
	}

	/**
	 * Sets the value of generalLiability.
	 * 
	 * @param generalLiability
	 *            The value to assign generalLiability.
	 */
	public void setGeneralLiability(String generalLiability)
	{
		if (generalLiability == null || generalLiability.equals("null"))
		{
			generalLiability = emptyString;
		}
		this.generalLiability = generalLiability;
	}

	/**
	 * Returns the value of generalExpiration.
	 */
	public String getGeneralExpiration()
	{
		return generalExpiration;
	}

	/**
	 * Sets the value of generalExpiration.
	 * 
	 * @param generalExpiration
	 *            The value to assign generalExpiration.
	 */
	public void setGeneralExpiration(String generalExpiration)
	{
		if (generalExpiration == null || generalExpiration.equals("null"))
		{
			generalExpiration = emptyString;
		}
		this.generalExpiration = generalExpiration;
	}

	/**
	 * Returns the value of generalLimit.
	 */
	public String getGeneralLimit()
	{
		return generalLimit;
	}

	/**
	 * Sets the value of generalLimit.
	 * 
	 * @param generalLimit
	 *            The value to assign generalLimit.
	 */
	public void setGeneralLimit(String generalLimit)
	{
		if (generalLimit == null || generalLimit.equals("null"))
		{
			generalLimit = emptyString;
		}
		this.generalLimit = generalLimit;
	}

	/**
	 * Returns the value of workersLiability.
	 */
	public String getWorkersLiability()
	{
		return workersLiability;
	}

	/**
	 * Sets the value of workersLiability.
	 * 
	 * @param workersLiability
	 *            The value to assign workersLiability.
	 */
	public void setWorkersLiability(String workersLiability)
	{
		if (workersLiability == null || workersLiability.equals("null"))
		{
			workersLiability = emptyString;
		}
		this.workersLiability = workersLiability;
	}

	/**
	 * Returns the value of workersExpiration.
	 */
	public String getWorkersExpiration()
	{
		return workersExpiration;
	}

	/**
	 * Sets the value of workersExpiration.
	 * 
	 * @param workersExpiration
	 *            The value to assign workersExpiration.
	 */
	public void setWorkersExpiration(String workersExpiration)
	{
		if (workersExpiration == null || workersExpiration.equals("null"))
		{
			workersExpiration = emptyString;
		}
		this.workersExpiration = workersExpiration;
	}

	/**
	 * Returns the value of workersLimit.
	 */
	public String getWorkersLimit()
	{
		return workersLimit;
	}

	/**
	 * Sets the value of workersLimit.
	 * 
	 * @param workersLimit
	 *            The value to assign workersLimit.
	 */
	public void setWorkersLimit(String workersLimit)
	{
		if (workersLimit == null || workersLimit.equals("null"))
		{
			workersLimit = emptyString;
		}
		this.workersLimit = workersLimit;
	}

	/**
	 * Returns the value of employersLiability.
	 */
	public String getEmployersLiability()
	{
		return employersLiability;
	}

	/**
	 * Sets the value of employersLiability.
	 * 
	 * @param employersLiability
	 *            The value to assign employersLiability.
	 */
	public void setEmployersLiability(String employersLiability)
	{
		if (employersLiability == null || employersLiability.equals("null"))
		{
			employersLiability = emptyString;
		}
		this.employersLiability = employersLiability;
	}

	/**
	 * Returns the value of employersExpiration.
	 */
	public String getEmployersExpiration()
	{
		return employersExpiration;
	}

	/**
	 * Sets the value of employersExpiration.
	 * 
	 * @param employersExpiration
	 *            The value to assign employersExpiration.
	 */
	public void setEmployersExpiration(String employersExpiration)
	{
		if (employersExpiration == null || employersExpiration.equals("null"))
		{
			employersExpiration = emptyString;
		}
		this.employersExpiration = employersExpiration;
	}

	/**
	 * Returns the value of employersLimit.
	 */
	public String getEmployersLimit()
	{
		return employersLimit;
	}

	/**
	 * Sets the value of employersLimit.
	 * 
	 * @param employersLimit
	 *            The value to assign employersLimit.
	 */
	public void setEmployersLimit(String employersLimit)
	{
		if (employersLimit == null || employersLimit.equals("null"))
		{
			employersLimit = emptyString;
		}
		this.employersLimit = employersLimit;
	}

	/**
	 * Returns the value of automobileLiability.
	 */
	public String getAutomobileLiability()
	{
		return automobileLiability;
	}

	/**
	 * Sets the value of automobileLiability.
	 * 
	 * @param automobileLiability
	 *            The value to assign automobileLiability.
	 */
	public void setAutomobileLiability(String automobileLiability)
	{
		if (automobileLiability == null || automobileLiability.equals("null"))
		{
			automobileLiability = emptyString;
		}
		this.automobileLiability = automobileLiability;
	}

	/**
	 * Returns the value of automobileExpiration.
	 */
	public String getAutomobileExpiration()
	{
		return automobileExpiration;
	}

	/**
	 * Sets the value of automobileExpiration.
	 * 
	 * @param automobileExpiration
	 *            The value to assign automobileExpiration.
	 */
	public void setAutomobileExpiration(String automobileExpiration)
	{
		if (automobileExpiration == null || automobileExpiration.equals("null"))
		{
			automobileExpiration = emptyString;
		}
		this.automobileExpiration = automobileExpiration;
	}

	/**
	 * Returns the value of automobileLimit.
	 */
	public String getAutomobileLimit()
	{
		return automobileLimit;
	}

	/**
	 * Sets the value of automobileLimit.
	 * 
	 * @param automobileLimit
	 *            The value to assign automobileLimit.
	 */
	public void setAutomobileLimit(String automobileLimit)
	{
		if (automobileLimit == null || automobileLimit.equals("null"))
		{
			automobileLimit = emptyString;
		}
		this.automobileLimit = automobileLimit;
	}

	/**
	 * Returns the value of umbrellaLiability.
	 */
	public String getUmbrellaLiability()
	{
		return umbrellaLiability;
	}

	/**
	 * Sets the value of umbrellaLiability.
	 * 
	 * @param umbrellaLiability
	 *            The value to assign umbrellaLiability.
	 */
	public void setUmbrellaLiability(String umbrellaLiability)
	{
		if (umbrellaLiability == null || umbrellaLiability.equals("null"))
		{
			umbrellaLiability = emptyString;
		}
		this.umbrellaLiability = umbrellaLiability;
	}

	/**
	 * Returns the value of umbrellaExpiration.
	 */
	public String getUmbrellaExpiration()
	{
		return umbrellaExpiration;
	}

	/**
	 * Sets the value of umbrellaExpiration.
	 * 
	 * @param umbrellaExpiration
	 *            The value to assign umbrellaExpiration.
	 */
	public void setUmbrellaExpiration(String umbrellaExpiration)
	{
		if (umbrellaExpiration == null || umbrellaExpiration.equals("null"))
		{
			umbrellaExpiration = emptyString;
		}
		this.umbrellaExpiration = umbrellaExpiration;
	}

	/**
	 * Returns the value of umbrellaLimit.
	 */
	public String getUmbrellaLimit()
	{
		return umbrellaLimit;
	}

	/**
	 * Sets the value of umbrellaLimit.
	 * 
	 * @param umbrellaLimit
	 *            The value to assign umbrellaLimit.
	 */
	public void setUmbrellaLimit(String umbrellaLimit)
	{
		if (umbrellaLimit == null || umbrellaLimit.equals("null"))
		{
			umbrellaLimit = emptyString;
		}
		this.umbrellaLimit = umbrellaLimit;
	}

	/**
	 * Returns the value of disysAdditionalInsured.
	 */
	public boolean getDisysAdditionalInsured()
	{
		return disysAdditionalInsured;
	}

	/**
	 * Sets the value of disysAdditionalInsured.
	 * 
	 * @param disysAdditionalInsured
	 *            The value to assign disysAdditionalInsured.
	 */
	public void setDisysAdditionalInsured(boolean disysAdditionalInsured)
	{
		this.disysAdditionalInsured = disysAdditionalInsured;
	}

	/**
	 * Returns the value of subcontractorAgreement.
	 */
	public boolean getSubcontractorAgreement()
	{
		return subcontractorAgreement;
	}

	/**
	 * Sets the value of subcontractorAgreement.
	 * 
	 * @param subcontractorAgreement
	 *            The value to assign subcontractorAgreement.
	 */
	public void setSubcontractorAgreement(boolean subcontractorAgreement)
	{
		this.subcontractorAgreement = subcontractorAgreement;
	}

	/**
	 * Returns the value of formW9.
	 */
	public boolean getFormW9()
	{
		return formW9;
	}

	/**
	 * Sets the value of formW9.
	 * 
	 * @param formW9
	 *            The value to assign formW9.
	 */
	public void setFormW9(boolean formW9)
	{
		this.formW9 = formW9;
	}

	/**
	 * Returns the value of insuranceCertificate.
	 */
	public boolean getInsuranceCertificate()
	{
		return insuranceCertificate;
	}

	/**
	 * Sets the value of insuranceCertificate.
	 * 
	 * @param insuranceCertificate
	 *            The value to assign insuranceCertificate.
	 */
	public void setInsuranceCertificate(boolean insuranceCertificate)
	{
		this.insuranceCertificate = insuranceCertificate;
	}

	/**
	 * Returns the value of workOrder.
	 */
	public boolean getWorkOrder()
	{
		return workOrder;
	}

	/**
	 * Sets the value of workOrder.
	 * 
	 * @param workOrder
	 *            The value to assign workOrder.
	 */
	public void setWorkOrder(boolean workOrder)
	{
		this.workOrder = workOrder;
	}

	/**
	 * Returns the value of country.
	 */
	public String getCountry()
	{
		return country;
	}

	/**
	 * Sets the value of country.
	 * 
	 * @param country
	 *            The value to assign country.
	 */
	public void setCountry(String country)
	{
		if (country == null || country.equals("null"))
		{
			country = emptyString;
		}
		this.country = country;
	}

	/**
	 * Returns the value of updatedBy.
	 */
	public String getUpdatedBy()
	{
		return updatedBy;
	}

	/**
	 * Sets the value of updatedBy.
	 * 
	 * @param updatedBy
	 *            The value to assign updatedBy.
	 */
	public void setUpdatedBy(String updatedBy)
	{
		if (updatedBy == null || updatedBy.equals("null"))
		{
			updatedBy = emptyString;
		}
		this.updatedBy = updatedBy;
	}

	/**
	 * Returns the value of updatedDate.
	 */
	public String getUpdatedDate()
	{
		return updatedDate;
	}

	/**
	 * Sets the value of updatedDate.
	 * 
	 * @param updatedDate
	 *            The value to assign updatedDate.
	 */
	public void setUpdatedDate(String updatedDate)
	{
		if (updatedDate == null || updatedDate.equals("null"))
		{
			updatedDate = emptyString;
		}
		this.updatedDate = updatedDate;
	}

	/**
	 * Returns the value of status.
	 */
	// public boolean getStatus()
	// {
	// return status;
	// }

	public String getStatus()
	{
		return this.status;
	}

	/**
	 * Sets the value of status.
	 * 
	 * @param status
	 *            The value to assign status.
	 */
	// public void setStatus(boolean status)
	// {
	// this.status = status;
	// }

	public void setStatus(String status)
	{
		if (status == null || status.equals("null"))
		{
			status = emptyString;
		}
		this.status = status;
	}

	public String getDiscount()
	{
		return discount;
	}

	public void setDiscount(String discount)
	{
		if (discount == null || discount.equals("null"))
		{
			discount = emptyString;
		}
		this.discount = discount;
	}

	public double getDiscountAmount()
	{
		return discountAmount;
	}

	public void setDiscountAmount(double discountAmount)
	{
		this.discountAmount = discountAmount;
	}

	public String getAmountType()
	{
		return amountType;
	}

	public void setAmountType(String amountType)
	{
		if (amountType == null || amountType.equals("null"))
		{
			amountType = emptyString;
		}
		this.amountType = amountType;
	}

	public String getGeneralType()
	{
		return generalType;
	}

	public void setGeneralType(String generalType)
	{
		if (generalType == null || generalType.equals("null"))
		{
			generalType = emptyString;
		}
		this.generalType = generalType;
	}

	public String getWorkersType()
	{
		return workersType;
	}

	public void setWorkersType(String workersType)
	{
		if (workersType == null || workersType.equals("null"))
		{
			workersType = emptyString;
		}
		this.workersType = workersType;
	}

	public String getEmployersType()
	{
		return employersType;
	}

	public void setEmployersType(String employersType)
	{
		if (employersType == null || employersType.equals("null"))
		{
			employersType = emptyString;
		}
		this.employersType = employersType;
	}

	public String getAutomobileType()
	{
		return automobileType;
	}

	public void setAutomobileType(String automobileType)
	{
		if (automobileType == null || automobileType.equals("null"))
		{
			automobileType = emptyString;
		}
		this.automobileType = automobileType;
	}

	public String getUmbrellaType()
	{
		return umbrellaType;
	}

	public void setUmbrellaType(String umbrellaType)
	{
		if (umbrellaType == null || umbrellaType.equals("null"))
		{
			umbrellaType = emptyString;
		}
		this.umbrellaType = umbrellaType;
	}

	public boolean isSubmitStatus()
	{
		return submitStatus;
	}

	public boolean getSubmitStatus()
	{
		return submitStatus;
	}

	public void setSubmitStatus(boolean submitStatus)
	{
		this.submitStatus = submitStatus;
	}

	public void print()
	{
		System.out.println("supplierId = " + supplierId);
		System.out.println("orgId = " + orgId);
		System.out.println("legalName = " + legalName);
		System.out.println("dbaName = " + dbaName);
		System.out.println("fin = " + fin);
		System.out.println("pocLastName = " + pocLastName);
		System.out.println("pocFirstName = " + pocFirstName);
		System.out.println("paymentTerm = " + paymentTerm);
		System.out.println("vendorCode = " + vendorCode);
		System.out.println("invoiceEmail = " + invoiceEmail);
		System.out.println("headQuartersAddress = " + headQuartersAddress);
		System.out.println("headCity = " + headCity);
		System.out.println("headState = " + headState);
		System.out.println("headZip = " + headZip);
		System.out.println("website = " + website);
		System.out.println("headPhone = " + headPhone);
		System.out.println("headFax = " + headFax);
		System.out.println("federalTaxId = " + federalTaxId);
		System.out.println("dunsNumber = " + dunsNumber);
		System.out.println("remitAddress = " + remitAddress);
		System.out.println("remitCity = " + remitCity);
		System.out.println("remitState = " + remitState);
		System.out.println("remitZip = " + remitZip);
		System.out.println("accountingContact = " + accountingContact);
		System.out.println("accountingEmail = " + accountingEmail);
		System.out.println("remitPhone = " + remitPhone);
		System.out.println("remitFax = " + remitFax);
		System.out.println("primaryName = " + primaryName);
		System.out.println("primaryTitle = " + primaryTitle);
		System.out.println("primaryAddress = " + primaryAddress);
		System.out.println("primaryCity = " + primaryCity);
		System.out.println("primaryState = " + primaryState);
		System.out.println("primaryZip = " + primaryZip);
		System.out.println("primaryPhone = " + primaryPhone);
		System.out.println("primaryFax = " + primaryFax);
		System.out.println("primaryEmail = " + primaryEmail);
		System.out.println("recruitmentName = " + recruitmentName);
		System.out.println("recruitmentTitle = " + recruitmentTitle);
		System.out.println("recruitmentAddress = " + recruitmentAddress);
		System.out.println("recruitmentCity = " + recruitmentCity);
		System.out.println("recruitmentState = " + recruitmentState);
		System.out.println("recruitmentZip = " + recruitmentZip);
		System.out.println("recruitmentPhone = " + recruitmentPhone);
		System.out.println("recruitmentFax = " + recruitmentFax);
		System.out.println("recruitmentEmail = " + recruitmentEmail);
		System.out.println("officerName1 = " + officerName1);
		System.out.println("officerTitle1 = " + officerTitle1);
		System.out.println("officerName2 = " + officerName2);
		System.out.println("officerTitle2 = " + officerTitle2);
		System.out.println("officerName3 = " + officerName3);
		System.out.println("officerTitle3 = " + officerTitle3);
		System.out.println("officerName4 = " + officerName4);
		System.out.println("officerTitle4 = " + officerTitle4);
		System.out.println("corporation = " + corporation);
		System.out.println("proprietorship = " + proprietorship);
		System.out.println("partnership = " + partnership);
		System.out.println("other = " + other);
		System.out.println("otherDescription = " + otherDescription);
		System.out.println("employeeCount = " + employeeCount);
		System.out.println("dateEstablished = " + dateEstablished);
		System.out.println("inState = " + inState);
		System.out.println("incorporationCertificate = " + incorporationCertificate);
		System.out.println("statesAuthorized = " + statesAuthorized);
		System.out.println("typeSubcontractor = " + typeSubcontractor);
		System.out.println("type1099 = " + type1099);
		System.out.println("smallBusiness = " + smallBusiness);
		System.out.println("largeBusiness = " + largeBusiness);
		System.out.println("smallDisadvantaged = " + smallDisadvantaged);
		System.out.println("womenOwned = " + womenOwned);
		System.out.println("veteranOwned = " + veteranOwned);
		System.out.println("disabledOwned = " + disabledOwned);
		System.out.println("minorityOwned = " + minorityOwned);
		System.out.println("asiaPacific = " + asiaPacific);
		System.out.println("africanAmerican = " + africanAmerican);
		System.out.println("hispanicAmerican = " + hispanicAmerican);
		System.out.println("nativeAmerican = " + nativeAmerican);
		System.out.println("naicsPrimaryCode = " + naicsPrimaryCode);
		System.out.println("naicsSecondaryCode = " + naicsSecondaryCode);
		System.out.println("generalLiability = " + generalLiability);
		System.out.println("generalExpiration = " + generalExpiration);
		System.out.println("generalLimit = " + generalLimit);
		System.out.println("workersLiability = " + workersLiability);
		System.out.println("workersExpiration = " + workersExpiration);
		System.out.println("workersLimit = " + workersLimit);
		System.out.println("employersLiability = " + employersLiability);
		System.out.println("employersExpiration = " + employersExpiration);
		System.out.println("employersLimit = " + employersLimit);
		System.out.println("automobileLiability = " + automobileLiability);
		System.out.println("automobileExpiration = " + automobileExpiration);
		System.out.println("automobileLimit = " + automobileLimit);
		System.out.println("umbrellaLiability = " + umbrellaLiability);
		System.out.println("umbrellaExpiration = " + umbrellaExpiration);
		System.out.println("umbrellaLimit = " + umbrellaLimit);
		System.out.println("disysAdditionalInsured = " + disysAdditionalInsured);
		System.out.println("subcontractorAgreement = " + subcontractorAgreement);
		System.out.println("formW9 = " + formW9);
		System.out.println("insuranceCertificate = " + insuranceCertificate);
		System.out.println("workOrder = " + workOrder);
		System.out.println("country = " + country);
		System.out.println("updatedBy = " + updatedBy);
		System.out.println("updatedDate = " + updatedDate);
		System.out.println("discount = " + discount);
		System.out.println("discountAmount = " + discountAmount);
		System.out.println("amountType = " + amountType);
		System.out.println("generalType = " + generalType);
		System.out.println("workersType = " + workersType);
		System.out.println("employersType = " + employersType);
		System.out.println("automobileType = " + automobileType);
		System.out.println("umbrellaType = " + umbrellaType);
		System.out.println("orgId = " + orgId);
		System.out.println("legalName = " + legalName);
		System.out.println("dbaName = " + dbaName);
		System.out.println("fin = " + fin);
		System.out.println("pocLastName = " + pocLastName);
		System.out.println("pocFirstName = " + pocFirstName);
		System.out.println("paymentTerm = " + paymentTerm);
		System.out.println("vendorCode = " + vendorCode);
		System.out.println("invoiceEmail = " + invoiceEmail);
		System.out.println("headQuartersAddress = " + headQuartersAddress);
		System.out.println("headCity = " + headCity);
		System.out.println("headState = " + headState);
		System.out.println("headZip = " + headZip);
		System.out.println("website = " + website);
		System.out.println("headPhone = " + headPhone);
		System.out.println("headFax = " + headFax);
		System.out.println("federalTaxId = " + federalTaxId);
		System.out.println("dunsNumber = " + dunsNumber);
		System.out.println("remitAddress = " + remitAddress);
		System.out.println("remitCity = " + remitCity);
		System.out.println("remitState = " + remitState);
		System.out.println("remitZip = " + remitZip);
		System.out.println("accountingContact = " + accountingContact);
		System.out.println("accountingEmail = " + accountingEmail);
		System.out.println("remitPhone = " + remitPhone);
		System.out.println("remitFax = " + remitFax);
		System.out.println("primaryName = " + primaryName);
		System.out.println("primaryTitle = " + primaryTitle);
		System.out.println("primaryAddress = " + primaryAddress);
		System.out.println("primaryCity = " + primaryCity);
		System.out.println("primaryState = " + primaryState);
		System.out.println("primaryZip = " + primaryZip);
		System.out.println("primaryPhone = " + primaryPhone);
		System.out.println("primaryFax = " + primaryFax);
		System.out.println("primaryEmail = " + primaryEmail);
		System.out.println("recruitmentName = " + recruitmentName);
		System.out.println("recruitmentTitle = " + recruitmentTitle);
		System.out.println("recruitmentAddress = " + recruitmentAddress);
		System.out.println("recruitmentCity = " + recruitmentCity);
		System.out.println("recruitmentState = " + recruitmentState);
		System.out.println("recruitmentZip = " + recruitmentZip);
		System.out.println("recruitmentPhone = " + recruitmentPhone);
		System.out.println("recruitmentFax = " + recruitmentFax);
		System.out.println("recruitmentEmail = " + recruitmentEmail);
		System.out.println("officerName1 = " + officerName1);
		System.out.println("officerTitle1 = " + officerTitle1);
		System.out.println("officerName2 = " + officerName2);
		System.out.println("officerTitle2 = " + officerTitle2);
		System.out.println("officerName3 = " + officerName3);
		System.out.println("officerTitle3 = " + officerTitle3);
		System.out.println("officerName4 = " + officerName4);
		System.out.println("officerTitle4 = " + officerTitle4);
		System.out.println("corporation = " + corporation);
		System.out.println("proprietorship = " + proprietorship);
		System.out.println("partnership = " + partnership);
		System.out.println("other = " + other);
		System.out.println("otherDescription = " + otherDescription);
		System.out.println("employeeCount = " + employeeCount);
		System.out.println("dateEstablished = " + dateEstablished);
		System.out.println("inState = " + inState);
		System.out.println("incorporationCertificate = " + incorporationCertificate);
		System.out.println("statesAuthorized = " + statesAuthorized);
		System.out.println("typeSubcontractor = " + typeSubcontractor);
		System.out.println("type1099 = " + type1099);
		System.out.println("smallBusiness = " + smallBusiness);
		System.out.println("largeBusiness = " + largeBusiness);
		System.out.println("smallDisadvantaged = " + smallDisadvantaged);
		System.out.println("womenOwned = " + womenOwned);
		System.out.println("veteranOwned = " + veteranOwned);
		System.out.println("disabledOwned = " + disabledOwned);
		System.out.println("minorityOwned = " + minorityOwned);
		System.out.println("asiaPacific = " + asiaPacific);
		System.out.println("africanAmerican = " + africanAmerican);
		System.out.println("hispanicAmerican = " + hispanicAmerican);
		System.out.println("nativeAmerican = " + nativeAmerican);
		System.out.println("naicsPrimaryCode = " + naicsPrimaryCode);
		System.out.println("naicsSecondaryCode = " + naicsSecondaryCode);
		System.out.println("generalLiability = " + generalLiability);
		System.out.println("generalExpiration = " + generalExpiration);
		System.out.println("generalLimit = " + generalLimit);
		System.out.println("workersLiability = " + workersLiability);
		System.out.println("workersExpiration = " + workersExpiration);
		System.out.println("workersLimit = " + workersLimit);
		System.out.println("employersLiability = " + employersLiability);
		System.out.println("employersExpiration = " + employersExpiration);
		System.out.println("employersLimit = " + employersLimit);
		System.out.println("automobileLiability = " + automobileLiability);
		System.out.println("automobileExpiration = " + automobileExpiration);
		System.out.println("automobileLimit = " + automobileLimit);
		System.out.println("umbrellaLiability = " + umbrellaLiability);
		System.out.println("umbrellaExpiration = " + umbrellaExpiration);
		System.out.println("umbrellaLimit = " + umbrellaLimit);
		System.out.println("disysAdditionalInsured = " + disysAdditionalInsured);
		System.out.println("subcontractorAgreement = " + subcontractorAgreement);
		System.out.println("formW9 = " + formW9);
		System.out.println("insuranceCertificate = " + insuranceCertificate);
		System.out.println("workOrder = " + workOrder);
		System.out.println("country = " + country);
		System.out.println("updatedBy = " + updatedBy);
		System.out.println("updatedDate = " + updatedDate);
		System.out.println("discount = " + discount);
		System.out.println("discountAmount = " + discountAmount);
		System.out.println("amountType = " + amountType);
		System.out.println("generalType = " + generalType);
		System.out.println("workersType = " + workersType);
		System.out.println("employersType = " + employersType);
		System.out.println("automobileType = " + automobileType);
		System.out.println("umbrellaType = " + umbrellaType);
		System.out.println("subcontractorComments = " + subcontractorComments);
		System.out.println("company_ = " + company_);
		System.out.println("subContractorInfoVerify = " + subContractorInfoVerify);

		System.out.println("submitStatus = " + submitStatus);
		System.out.println("status = " + status);
		System.out.println("approvalStatus = subcontractorapprove = " + approvalStatus);
		System.out.println("recordStatus = " + recordStatus);
		System.out.println("entryDate = " + entryDate);
		System.out.println("approvalDate = " + approvalDate);
		System.out.println("approvedBy = " + approvedBy);
		System.out.println("comments = " + comments);
		System.out.println("llc = " + llc);
		System.out.println("pSVendorId = " + pSVendorId);
	}

	public String getRecordStatus()
	{
		return recordStatus;
	}

	public void setRecordStatus(String recordStatus)
	{
		if (recordStatus == null || recordStatus.equals("null"))
		{
			recordStatus = emptyString;
		}
		this.recordStatus = recordStatus;
	}

	public String getEntryDate()
	{
		return entryDate;
	}

	public void setEntryDate(String entryDate)
	{
		if (entryDate == null || entryDate.equals("null"))
		{
			entryDate = emptyString;
		}
		this.entryDate = entryDate;
	}

	public String getApprovalDate()
	{
		return approvalDate;
	}

	public void setApprovalDate(String approvalDate)
	{
		if (approvalDate == null || approvalDate.equals("null"))
		{
			approvalDate = emptyString;
		}
		this.approvalDate = approvalDate;
	}

	public String getApprovedBy()
	{
		return approvedBy;
	}

	public void setApprovedBy(String approvedBy)
	{
		if (approvedBy == null || approvedBy.equals("null"))
		{
			approvedBy = emptyString;
		}
		this.approvedBy = approvedBy;
	}

	public String getComments()
	{
		return comments;
	}

	public void setComments(String comments)
	{
		if (comments == null || comments.equals("null"))
		{
			comments = emptyString;
		}
		this.comments = comments;
	}

	public boolean isLlc()
	{
		return llc;
	}

	public void setLlc(boolean llc)
	{
		this.llc = llc;
	}
	
	public boolean getLlc()
	{
		return llc;
	}

	/**
	 * @return the pSVendorId
	 */
	public String getpSVendorId()
	{
		return pSVendorId;
	}

	/**
	 * @param pSVendorId the pSVendorId to set
	 */
	public void setpSVendorId(String pSVendorId)
	{
		this.pSVendorId = pSVendorId;
	}

	/**
	 * @return the companyName
	 */
	public String getCompanyName()
	{
		return companyName;
	}

	/**
	 * @param companyName the companyName to set
	 */
	public void setCompanyName(String companyName)
	{
		this.companyName = companyName;
	}

	/**
	 * @return the address1
	 */
	public String getAddress1()
	{
		return address1;
	}

	/**
	 * @param address1 the address1 to set
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
	 * @param address2 the address2 to set
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
	 * @param city the city to set
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
	 * @param state the state to set
	 */
	public void setState(String state)
	{
		this.state = state;
	}

	/**
	 * @return the zipCode
	 */
	public String getZipCode()
	{
		return zipCode;
	}

	/**
	 * @param zipCode the zipCode to set
	 */
	public void setZipCode(String zipCode)
	{
		this.zipCode = zipCode;
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
	
}