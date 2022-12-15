package com.disys.analyzer.model.dto;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
@Entity
public class GenderDTO implements Serializable
{
	private static final long serialVersionUID = 6128885673928232458L;
	@Id
	private Integer sui;
	private String genderCode;
	private String genderDescription;
	private String companyCode;
	
	public GenderDTO()
	{
		// TODO Auto-generated constructor stub
	}

	
	public GenderDTO(Integer sui, String genderCode, String genderDescription, String companyCode) {
		super();
		this.sui=sui;
		this.genderCode = genderCode;
		this.genderDescription = genderDescription;
		this.companyCode=companyCode;
	
	}

	public GenderDTO( String genderCode, String genderDescription, String companyCode) {
		super();
		this.genderCode = genderCode;
		this.genderDescription = genderDescription;
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

	/**
	 * @return the genderCode
	 */
	public String getGenderCode() {
		return genderCode;
	}

	/**
	 * @param genderCode the genderCode to set
	 */
	public void setGenderCode(String genderCode) {
		this.genderCode = genderCode;
	}
	/**
	 * @return the genderDescription
	 */
	public String getGenderDescription() {
		return genderDescription;
	}
	/**
	 * @param genderDescription the genderDescription to set
	 */
	public void setGenderDescription(String genderDescription) {
		this.genderDescription = genderDescription;
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
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "GenderDTO [genderCode=" + genderCode + ", genderDescription=" + genderDescription
				+ "]";
	}
	

	public void print ()
	{
		System.out.println("Gender --> genderCode" + genderCode);
		System.out.println("Gender --> genderDescription" + genderDescription);
	}
}
