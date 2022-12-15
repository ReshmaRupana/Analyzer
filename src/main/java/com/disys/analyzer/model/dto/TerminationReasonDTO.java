package com.disys.analyzer.model.dto;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
@Entity
public class TerminationReasonDTO implements Serializable
{
	private static final long serialVersionUID = 6128885673928232458L;
	@Id
	private Integer sui;
	private String terminationReasonCode;
	private String terminationReasonDescription;
	private String companyCode;
	
	public TerminationReasonDTO()
	{
		// TODO Auto-generated constructor stub
	}

	
	public TerminationReasonDTO(Integer sui, String terminationReasonCode, String terminationReasonDescription, String companyCode) {
		super();
		this.sui=sui;
		this.terminationReasonCode = terminationReasonCode;
		this.terminationReasonDescription = terminationReasonDescription;
		this.companyCode=companyCode;
	
	}

	public TerminationReasonDTO( String terminationReasonCode, String terminationReasonDescription, String companyCode) {
		super();
		this.terminationReasonCode = terminationReasonCode;
		this.terminationReasonDescription = terminationReasonDescription;
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
	 * @return the terminationReasonCode
	 */
	public String getTerminationReasonCode() {
		return terminationReasonCode;
	}

	/**
	 * @param terminationReasonCode the terminationReasonCode to set
	 */
	public void setTerminationReasonCode(String terminationReasonCode) {
		this.terminationReasonCode = terminationReasonCode;
	}

	/**
	 * @return the terminationReasonDescription
	 */
	public String getTerminationReasonDescription() {
		return terminationReasonDescription;
	}

	/**
	 * @param terminationReasonDescription the terminationReasonDescription to set
	 */
	public void setTerminationReasonDescription(String terminationReasonDescription) {
		this.terminationReasonDescription = terminationReasonDescription;
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
		return "TerminationReasonDTO [terminationReasonCode=" + terminationReasonCode + ", terminationReasonDescription=" + terminationReasonDescription
				+ "]";
	}
	

	public void print ()
	{
		System.out.println("TerminationReason --> terminationReasonCode" + terminationReasonCode);
		System.out.println("TerminationReason --> terminationReasonDescription" + terminationReasonDescription);
	}
}
