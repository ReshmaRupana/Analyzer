package com.disys.analyzer.model.dto;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
@Entity
public class CompanyDTO implements Serializable
{
	private static final long serialVersionUID = 6128885673928232458L;
	@Id
	private Integer sui;
	private String companyCode;
	private String companyDescription;
	
	public CompanyDTO()
	{
		// TODO Auto-generated constructor stub
	}

	
	public CompanyDTO(Integer sui, String companyCode, String companyDescription) {
		super();
		this.sui=sui;
		this.companyCode = companyCode;
		this.companyDescription = companyDescription;
	
	}

	public CompanyDTO( String companyCode, String companyDescription) {
		super();
		this.companyCode = companyCode;
		this.companyDescription = companyDescription;
		
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

	/**
	 * @return the companyDescription
	 */

	public String getCompanyDescription() {
		return companyDescription;
	}

	/**
	 * @param companyDescription the companyDescription to set
	 */
	public void setCompanyDescription(String companyDescription) {
		this.companyDescription = companyDescription;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "CompanyDTO [companyCode=" + companyCode + ", companyDescription=" + companyDescription
				+ "]";
	}

	public void print ()
	{
		System.out.println("Company --> companyCode" + companyCode);
		System.out.println("Company --> companyDescription" + companyDescription);
	}
}
