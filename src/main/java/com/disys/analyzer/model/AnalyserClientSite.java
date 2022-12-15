package com.disys.analyzer.model;

import java.io.Serializable;

import javax.persistence.*;

import com.disys.analyzer.model.dto.AnalyserClientSiteDTO;

import java.sql.Timestamp;

/**
 * The persistent class for the AnalyserClientSite database table.
 * 
 */
@Entity(name = "Analyser_ClientSite")
// @Table(schema="Analyser.dbo")
@NamedQueries(value = { @NamedQuery(name = "AnalyserClientSite.findAll", query = "SELECT a FROM Analyser_ClientSite a"),

})
public class AnalyserClientSite implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "Client_Id")
	private Integer clientId;
	
	@Column(name = "Address1")
	private String address1;
	
	@Column(name = "Address2")
	private String address2;
	
	@Column(name = "City")
	private String city;
	
	@Column(name = "Country")
	private String country;
	
	@Column(name = "Email")
	private String email;
	
	@Column(name = "EnteredBy")
	private String enteredBy;
	
	@Column(name = "EntryDate")
	private Timestamp entryDate;
	
	@Column(name = "Fax")
	private String fax;
	
	@Column(name = "IsProcessed")
	private String isProcessed;
	
	@Column(name = "IsUpdateProcessed")
	private String isUpdateProcessed;
	
	@Column(name = "LastName")
	private String lastName;
	
	@Column(name = "Manager_Name")
	private String managerName;
	
	@Column(name = "ORG_ID")
	private Integer orgId;
	
	@Column(name = "ProcessDate")
	private Timestamp processDate;
	
	@Column(name = "Site_Id")
	private Integer siteId;
	
	@Column(name = "State")
	private String state;
	
	private Integer status;
	
	@Column(name = "Telephone")
	private String telephone;
	
	@Column(name = "Updated_By")
	private String updatedBy;
	
	@Column(name = "Updated_date")
	private Timestamp updatedDate;
	
	@Column(name = "Zipcode")
	private String zipCode;
	
	@Column(name = "ISAddressUSPSValidated")
	private String isAddressUSPSValidated;
	
	@Column(name = "USPSAddressValidationDate")
	private Timestamp uSPSAddressValidationDate;
	
	@Column(name = "ValidatedBy")
	private String validatedBy;
	
	@Column(name = "Longitude")
	private String longitude;
	
	@Column(name = "Latitude")
	private String latitude;
	
	@Column(name = "HawkSiteId")
	private Integer hawkSiteId;
	
	public AnalyserClientSite(AnalyserClientSiteDTO model)
	{
		super();
		this.clientId = model.getClientId();
		this.address1 = model.getAddress1();
		this.address2 = model.getAddress2();
		this.city = model.getCity();
		this.country = model.getCountry();
		this.email = model.getEmail();
		this.enteredBy = model.getEnteredBy();
		this.entryDate = model.getEntryDate();
		this.fax = model.getFax();
		this.isProcessed = model.getIsProcessed();
		this.isUpdateProcessed = model.getIsUpdateProcessed();
		this.lastName = model.getLastName();
		this.managerName = model.getManagerName();
		this.orgId = model.getOrgId();
		this.processDate = model.getProcessDate();
		this.siteId = model.getSiteId();
		this.state = model.getState();
		this.status = model.getStatus();
		this.telephone = model.getTelephone();
		this.updatedBy = model.getUpdatedBy();
		this.updatedDate = model.getUpdatedDate();
		this.zipCode = model.getZipCode();
		this.isAddressUSPSValidated = model.getIsAddressUSPSValidated();
		this.uSPSAddressValidationDate = model.getuSPSAddressValidationDate();
		this.validatedBy = model.getValidatedBy();
		this.longitude = model.getLongitude();
		this.latitude = model.getLatitude();
	}
	
	public AnalyserClientSite()
	{
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
	
	public String getCity()
	{
		return this.city;
	}
	
	public void setCity(String city)
	{
		this.city = city;
	}
	
	/**
	 * @return the clientId
	 */
	public Integer getClientId()
	{
		return clientId;
	}
	
	/**
	 * @param clientId
	 *            the clientId to set
	 */
	public void setClientId(Integer clientId)
	{
		this.clientId = clientId;
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
	 * @return the enteredBy
	 */
	public String getEnteredBy()
	{
		return enteredBy;
	}
	
	/**
	 * @param enteredBy
	 *            the enteredBy to set
	 */
	public void setEnteredBy(String enteredBy)
	{
		this.enteredBy = enteredBy;
	}
	
	/**
	 * @return the entryDate
	 */
	public Timestamp getEntryDate()
	{
		return entryDate;
	}
	
	/**
	 * @param entryDate
	 *            the entryDate to set
	 */
	public void setEntryDate(Timestamp entryDate)
	{
		this.entryDate = entryDate;
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
	 * @return the isProcessed
	 */
	public String getIsProcessed()
	{
		return isProcessed;
	}
	
	/**
	 * @param isProcessed
	 *            the isProcessed to set
	 */
	public void setIsProcessed(String isProcessed)
	{
		this.isProcessed = isProcessed;
	}
	
	/**
	 * @return the isUpdateProcessed
	 */
	public String getIsUpdateProcessed()
	{
		return isUpdateProcessed;
	}
	
	/**
	 * @param isUpdateProcessed
	 *            the isUpdateProcessed to set
	 */
	public void setIsUpdateProcessed(String isUpdateProcessed)
	{
		this.isUpdateProcessed = isUpdateProcessed;
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
	 * @return the processDate
	 */
	public Timestamp getProcessDate()
	{
		return processDate;
	}
	
	/**
	 * @param processDate
	 *            the processDate to set
	 */
	public void setProcessDate(Timestamp processDate)
	{
		this.processDate = processDate;
	}
	
	/**
	 * @return the siteId
	 */
	public Integer getSiteId()
	{
		return siteId;
	}
	
	/**
	 * @param siteId
	 *            the siteId to set
	 */
	public void setSiteId(Integer siteId)
	{
		this.siteId = siteId;
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
	 * @return the status
	 */
	public Integer getStatus()
	{
		return status;
	}
	
	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(Integer status)
	{
		this.status = status;
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
	 * @return the updatedBy
	 */
	public String getUpdatedBy()
	{
		return updatedBy;
	}
	
	/**
	 * @param updatedBy
	 *            the updatedBy to set
	 */
	public void setUpdatedBy(String updatedBy)
	{
		this.updatedBy = updatedBy;
	}
	
	/**
	 * @return the updatedDate
	 */
	public Timestamp getUpdatedDate()
	{
		return updatedDate;
	}
	
	/**
	 * @param updatedDate
	 *            the updatedDate to set
	 */
	public void setUpdatedDate(Timestamp updatedDate)
	{
		this.updatedDate = updatedDate;
	}
	
	/**
	 * @return the managerName
	 */
	public String getManagerName()
	{
		return managerName;
	}
	
	/**
	 * @param managerName
	 *            the managerName to set
	 */
	public void setManagerName(String managerName)
	{
		this.managerName = managerName;
	}
	
	/**
	 * @return the zipCode
	 */
	public String getZipCode()
	{
		return zipCode;
	}
	
	/**
	 * @param zipCode
	 *            the zipCode to set
	 */
	public void setZipCode(String zipCode)
	{
		this.zipCode = zipCode;
	}
	
	public String getIsAddressUSPSValidated()
	{
		return isAddressUSPSValidated;
	}
	
	public void setIsAddressUSPSValidated(String isAddressUSPSValidated)
	{
		this.isAddressUSPSValidated = isAddressUSPSValidated;
	}
	
	public Timestamp getuSPSAddressValidationDate()
	{
		return uSPSAddressValidationDate;
	}
	
	public void setuSPSAddressValidationDate(Timestamp uSPSAddressValidationDate)
	{
		this.uSPSAddressValidationDate = uSPSAddressValidationDate;
	}
	
	public String getValidatedBy()
	{
		return validatedBy;
	}
	
	public void setValidatedBy(String validatedBy)
	{
		this.validatedBy = validatedBy;
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
	 * @return the hawkSiteId
	 */
	public Integer getHawkSiteId()
	{
		return hawkSiteId;
	}
	
	/**
	 * @param hawkSiteId
	 *            the hawkSiteId to set
	 */
	public void setHawkSiteId(Integer hawkSiteId)
	{
		this.hawkSiteId = hawkSiteId;
	}
	
}