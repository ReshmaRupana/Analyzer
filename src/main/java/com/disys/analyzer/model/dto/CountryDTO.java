package com.disys.analyzer.model.dto;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
@Entity
public class CountryDTO implements Serializable
{
	private static final long serialVersionUID = 6128885673928232458L;
	@Id
	private Integer sui;
	private String countryName;
	private String countryCode;
	
	public CountryDTO()
	{
		// TODO Auto-generated constructor stub
	}

	
	public CountryDTO(Integer sui, String countryCode, String countryName) {
		super();
this.sui=sui;
		this.countryCode = countryCode;
		this.countryName = countryName;
	
	}

	public CountryDTO( String countryCode, String countryName) {
		super();
		this.countryCode = countryCode;
		this.countryName = countryName;
		
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

	/**
	 * @return the countryName
	 */
	public String getCountryName() {
		return countryName;
	}

	/**
	 * @param countryName the countryName to set
	 */
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	/**
	 * @return the countryCode
	 */
	public String getCountryCode() {
		return countryCode;
	}

	/**
	 * @param countryCode the countryCode to set
	 */
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "CountryDTO [countryCode=" + countryCode + ", countryName=" + countryName
				+ "]";
	}

	public void print ()
	{
		System.out.println("Country --> countryCode" + countryCode);
		System.out.println("Country --> countryName" + countryName);
	}
}
