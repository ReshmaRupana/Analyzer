package com.disys.analyzer.model.dto;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
@Entity
public class FLSAStatusDTO implements Serializable
{
	private static final long serialVersionUID = 6128885673928232458L;
	@Id
	private Integer sui;
	private String flsaStatusCode;
	private String flsaStatusDescription;
	private String companyCode;
	
	public FLSAStatusDTO()
	{
		// TODO Auto-generated constructor stub
	}

	
	public FLSAStatusDTO(Integer sui, String flsaStatusCode, String flsaStatusDescription, String companyCode) {
		super();
		this.sui=sui;
		this.flsaStatusCode = flsaStatusCode;
		this.flsaStatusDescription = flsaStatusDescription;
		this.companyCode=companyCode;
	
	}

	public FLSAStatusDTO( String flsaStatusCode, String flsaStatusDescription, String companyCode) {
		super();
		this.flsaStatusCode = flsaStatusCode;
		this.flsaStatusDescription = flsaStatusDescription;
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
	 * @return the flsaStatusCode
	 */

	public String getFlsaStatusCode() {
		return flsaStatusCode;
	}
	/**
	 * @param flsaStatusCode the flsaStatusCode to set
	 */

	public void setFlsaStatusCode(String flsaStatusCode) {
		this.flsaStatusCode = flsaStatusCode;
	}

	/**
	 * @return the flsaStatusDescription
	 */
	public String getFlsaStatusDescription() {
		return flsaStatusDescription;
	}

	/**
	 * @param sui the flsaStatusDescription to flsaStatusDescription
	 */
	public void setFlsaStatusDescription(String flsaStatusDescription) {
		this.flsaStatusDescription = flsaStatusDescription;
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
		return "FLSAStatusDTO [flsaStatusCode=" + flsaStatusCode + ", flsaStatusDescription=" + flsaStatusDescription
				+ "]";
	}

	public void print ()
	{
		System.out.println("FLSAStatus --> flsaStatusCode" + flsaStatusCode);
		System.out.println("FLSAStatus --> flsaStatusDescription" + flsaStatusDescription);
	}
}
