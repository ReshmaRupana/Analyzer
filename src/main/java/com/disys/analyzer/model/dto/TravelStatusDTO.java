package com.disys.analyzer.model.dto;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
@Entity
public class TravelStatusDTO implements Serializable
{
	private static final long serialVersionUID = 6128885673928232458L;
	@Id
	private Integer sui;
	private String travelStatusCode;
	private String travelStatusDescription;
	private String companyCode;
	
	public TravelStatusDTO()
	{
		// TODO Auto-generated constructor stub
	}

	
	public TravelStatusDTO(Integer sui, String travelStatusCode, String travelStatusDescription, String companyCode) {
		super();
		this.sui=sui;
		this.travelStatusCode = travelStatusCode;
		this.travelStatusDescription = travelStatusDescription;
		this.companyCode=companyCode;
	
	}

	public TravelStatusDTO( String travelStatusCode, String travelStatusDescription, String companyCode) {
		super();
		this.travelStatusCode = travelStatusCode;
		this.travelStatusDescription = travelStatusDescription;
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
	
	public String getTravelStatusCode() {
		return travelStatusCode;
	}
	
	public void setTravelStatusCode(String travelStatusCode) {
		this.travelStatusCode = travelStatusCode;
	}

	public String getTravelStatusDescription() {
		return travelStatusDescription;
	}

	public void setTravelStatusDescription(String travelStatusDescription) {
		this.travelStatusDescription = travelStatusDescription;
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
		return "TravelStatusDTO [travelStatusCode=" + travelStatusCode + ", travelStatusDescription=" + travelStatusDescription
				+ "]";
	}
	

	public void print ()
	{
		System.out.println("TravelStatus --> travelStatusCode" + travelStatusCode);
		System.out.println("TravelStatus --> travelStatusDescription" + travelStatusDescription);
	}
}
