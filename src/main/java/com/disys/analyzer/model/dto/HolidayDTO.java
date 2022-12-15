package com.disys.analyzer.model.dto;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
@Entity
public class HolidayDTO implements Serializable
{
	private static final long serialVersionUID = 6128885673928232458L;
	@Id
	private Integer sui;
	private String holidayCode;
	private String holidayDescription;
	private String companyCode;
	
	public HolidayDTO()
	{
		// TODO Auto-generated constructor stub
	}

	
	public HolidayDTO(Integer sui, String holidayCode, String holidayDescription, String companyCode) {
		super();
		this.sui=sui;
		this.holidayCode = holidayCode;
		this.holidayDescription = holidayDescription;
		this.companyCode=companyCode;
	
	}

	public HolidayDTO( String holidayCode, String holidayDescription, String companyCode) {
		super();
		this.holidayCode = holidayCode;
		this.holidayDescription = holidayDescription;
		this.companyCode=companyCode;
		
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

	public String getHolidayCode() {
		return holidayCode;
	}


	public void setHolidayCode(String holidayCode) {
		this.holidayCode = holidayCode;
	}


	public String getHolidayDescription() {
		return holidayDescription;
	}


	public void setHolidayDescription(String holidayDescription) {
		this.holidayDescription = holidayDescription;
	}

	public String getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "HolidayDTO [holidayCode=" + holidayCode + ", holidayDescription=" + holidayDescription
				+ "]";
	}
	

	public void print ()
	{
		System.out.println("Holiday --> holidayCode" + holidayCode);
		System.out.println("Holiday --> holidayDescription" + holidayDescription);
	}
}
