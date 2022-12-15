package com.disys.analyzer.model.dto;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
@Entity
public class OfficeDTO implements Serializable
{
	private static final long serialVersionUID = 6128885673928232458L;
	@Id
	private Integer sui;
	private Integer status;
	private String officeCode;
	private String officeDescription;
	private String companyCode;
	private String location;
	private String manager;
	private String address1;
	private String address2;
	private String city;
	private String state;
	private String zipCode;
	private String country;
	private String longitude;
	private String latitude;
	private String psOfficeCode;
	private Integer international;
	
	public OfficeDTO()
	{
		// TODO Auto-generated constructor stub
	}

	
	public OfficeDTO(Integer sui, Integer status, String officeCode, String officeDescription, String companyCode, String location, String manager, 
			String address1, String address2, String city, String state, String zipCode, String country, String longitude, String latitude, 
			String psOfficeCode, Integer international) {
		super();
		this.sui=sui;
		this.status=status;
		this.officeCode = officeCode;
		this.officeDescription = officeDescription;
		this.companyCode=companyCode;
		this.location = location;
		this.manager = manager;
		this.address1=address1;
		this.address2 = address2;
		this.city = city;
		this.state=state;
		this.zipCode = zipCode;
		this.country = country;
		this.longitude = longitude;
		this.latitude=latitude;
		this.psOfficeCode = psOfficeCode;
		this.international = international;
	
	}

	public OfficeDTO(Integer status, String officeCode, String officeDescription, String companyCode, String location, String manager, 
			String address1, String address2, String city, String state, String zipCode, String country, String longitude, String latitude, 
			String psOfficeCode, Integer international) {
		super();
		this.status=status;
		this.officeCode = officeCode;
		this.officeDescription = officeDescription;
		this.companyCode=companyCode;
		this.location = location;
		this.manager = manager;
		this.address1=address1;
		this.address2 = address2;
		this.city = city;
		this.state=state;
		this.zipCode = zipCode;
		this.country = country;
		this.longitude = longitude;
		this.latitude=latitude;
		this.psOfficeCode = psOfficeCode;
		this.international = international;
		
	}
	
	/**
	 * @return the sui
	 */

	public Integer getSui() {
		return sui;
	}

	/**
	 * @param sui the sui to set
	 */
	public void setSui(Integer sui) {
		this.sui = sui;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getOfficeCode() {
		return officeCode;
	}


	public void setOfficeCode(String officeCode) {
		this.officeCode = officeCode;
	}


	public String getOfficeDescription() {
		return officeDescription;
	}


	public void setOfficeDescription(String officeDescription) {
		this.officeDescription = officeDescription;
	}


	public String getCompanyCode() {
		return companyCode;
	}


	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}


	public String getLocation() {
		return location;
	}


	public void setLocation(String location) {
		this.location = location;
	}


	public String getManager() {
		return manager;
	}


	public void setManager(String manager) {
		this.manager = manager;
	}


	public String getAddress1() {
		return address1;
	}


	public void setAddress1(String address1) {
		this.address1 = address1;
	}


	public String getAddress2() {
		return address2;
	}


	public void setAddress2(String address2) {
		this.address2 = address2;
	}


	public String getCity() {
		return city;
	}


	public void setCity(String city) {
		this.city = city;
	}


	public String getState() {
		return state;
	}


	public void setState(String state) {
		this.state = state;
	}


	public String getZipCode() {
		return zipCode;
	}


	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}


	public String getCountry() {
		return country;
	}


	public void setCountry(String country) {
		this.country = country;
	}


	public String getLongitude() {
		return longitude;
	}


	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}


	public String getLatitude() {
		return latitude;
	}


	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}


	public String getPsOfficeCode() {
		return psOfficeCode;
	}


	public void setPsOfficeCode(String psOfficeCode) {
		this.psOfficeCode = psOfficeCode;
	}


	public Integer getInternational() {
		return international;
	}


	public void setInternational(Integer international) {
		this.international = international;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */

	@Override
	public String toString() {
		return "OfficeDTO [status=" + status + ", officeCode=" + officeCode+ ", officeDescription=" + officeDescription + ", "
				+ "companyCode=" + companyCode+ ", location=" + location + ", manager=" + manager+ ", "
						+ "address1=" + address1 + ", address2=" + address2+ ", city=" + city + ", "
								+ "state=" + state+ ", zipCode=" + zipCode + ", country=" + country+ ", "
										+ "longitude=" + longitude + ", latitude=" + latitude+ ", psOfficeCode=" + psOfficeCode + ","
												+ " international=" + international+ " ]";
	}
	

	public void print ()
	{
		System.out.println("Office --> officeCode" + officeCode);
		System.out.println("Office --> officeDescription" + officeDescription);
		System.out.println("Office --> companyCode" + companyCode);
		System.out.println("Office --> location" + location);
		System.out.println("Office --> manager" + manager);
		System.out.println("Office --> address1" + address1);
		System.out.println("Office --> address2" + address2);
		System.out.println("Office --> city" + city);
		System.out.println("Office --> state" + state);
		System.out.println("Office --> zipCode" + zipCode);
		System.out.println("Office --> country" + country);
		System.out.println("Office --> longitude" + longitude);
		System.out.println("Office --> latitude" + latitude);
		System.out.println("Office --> psOfficeCode" + psOfficeCode);
		System.out.println("Office --> international" + international);
	}
}
