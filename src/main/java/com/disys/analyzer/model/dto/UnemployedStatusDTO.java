package com.disys.analyzer.model.dto;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
@Entity
public class UnemployedStatusDTO implements Serializable
{
	private static final long serialVersionUID = 6128885673928232458L;
	@Id
	private Integer sui;
	private String unemployedStatusCode;
	private String unemployedStatusDescription;
	private String companyCode;
	public UnemployedStatusDTO()
	{
		// TODO Auto-generated constructor stub
	}

	
	public UnemployedStatusDTO(Integer sui, String unemployedStatusCode, String unemployedStatusDescription, String companyCode) {
		super();
		this.sui=sui;
		this.unemployedStatusCode = unemployedStatusCode;
		this.unemployedStatusDescription = unemployedStatusDescription;
		this.companyCode=companyCode;
	
	}

	public UnemployedStatusDTO( String unemployedStatusCode, String unemployedStatusDescription, String companyCode) {
		super();
		this.unemployedStatusCode = unemployedStatusCode;
		this.unemployedStatusDescription = unemployedStatusDescription;
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

	public String getUnemployedStatusCode() {
		return unemployedStatusCode;
	}

	public void setUnemployedStatusCode(String unemployedStatusCode) {
		this.unemployedStatusCode = unemployedStatusCode;
	}

	public String getUnemployedStatusDescription() {
		return unemployedStatusDescription;
	}

	public void setUnemployedStatusDescription(String unemployedStatusDescription) {
		this.unemployedStatusDescription = unemployedStatusDescription;
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
		return "UnemployedStatusDTO [unemployedStatusCode=" + unemployedStatusCode + ", unemployedStatusDescription=" + unemployedStatusDescription
				+ "]";
	}
	

	public void print ()
	{
		System.out.println("UnemployedStatus --> unemployedStatusCode" + unemployedStatusCode);
		System.out.println("UnemployedStatus --> unemployedStatusDescription" + unemployedStatusDescription);
	}
}
