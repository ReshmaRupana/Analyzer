/**
 * 
 */
package com.disys.analyzer.model.dto;

/**
 * @author Sajid
 * @since 10th May, 2018
 *
 */
public class AnalyserUspsAddress
{
	
	private String address1;
	private String address2;
	private String city;
	private String zipCode;
	private String state;
	private String longitude;
	private String latitude;
	private Integer validated;
	
	public String getAddress1()
	{
		return address1;
	}
	
	public void setAddress1(String address1)
	{
		this.address1 = address1;
	}
	
	public String getAddress2()
	{
		return address2;
	}
	
	public void setAddress2(String address2)
	{
		this.address2 = address2;
	}
	
	public String getCity()
	{
		return city;
	}
	
	public void setCity(String city)
	{
		this.city = city;
	}
	
	public String getZipCode()
	{
		return zipCode;
	}
	
	public void setZipCode(String zipCode)
	{
		this.zipCode = zipCode;
	}
	
	public String getState()
	{
		return state;
	}
	
	public void setState(String state)
	{
		this.state = state;
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
	 * @return the validated
	 */
	public Integer getValidated()
	{
		return validated;
	}

	/**
	 * @param validated the validated to set
	 */
	public void setValidated(Integer validated)
	{
		this.validated = validated;
	}
	
}
