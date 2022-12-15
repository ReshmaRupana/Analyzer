package com.disys.analyzer.model.dto;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
@Entity
public class VeteranStatusDTO implements Serializable
{
	private static final long serialVersionUID = 6128885673928232458L;
	@Id
	private Integer sui;
	private String veteranStatusCode;
	private String veteranStatusDescription;
	private String companyCode;
	
	public VeteranStatusDTO()
	{
		// TODO Auto-generated constructor stub
	}

	
	public VeteranStatusDTO(Integer sui, String veteranStatusCode, String veteranStatusDescription, String companyCode) {
		super();
		this.sui=sui;
		this.veteranStatusCode = veteranStatusCode;
		this.veteranStatusDescription = veteranStatusDescription;
		this.companyCode=companyCode;
	
	}

	public VeteranStatusDTO( String veteranStatusCode, String veteranStatusDescription, String companyCode) {
		super();
		this.veteranStatusCode = veteranStatusCode;
		this.veteranStatusDescription = veteranStatusDescription;
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

	public String getVeteranStatusCode() {
		return veteranStatusCode;
	}


	public void setVeteranStatusCode(String veteranStatusCode) {
		this.veteranStatusCode = veteranStatusCode;
	}


	public String getVeteranStatusDescription() {
		return veteranStatusDescription;
	}

	public void setVeteranStatusDescription(String veteranStatusDescription) {
		this.veteranStatusDescription = veteranStatusDescription;
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
		return "VeteranStatusDTO [veteranStatusCode=" + veteranStatusCode + ", veteranStatusDescription=" + veteranStatusDescription
				+ "]";
	}
	

	public void print ()
	{
		System.out.println("VeteranStatus --> veteranStatusCode" + veteranStatusCode);
		System.out.println("VeteranStatus --> veteranStatusDescription" + veteranStatusDescription);
	}
}
