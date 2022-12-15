package com.disys.analyzer.model.dto;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
@Entity
public class CoSellStatusDTO implements Serializable
{
	private static final long serialVersionUID = 6128885673928232458L;
	@Id
	private Integer sui;
	private String coSellStatusCode;
	private String coSellStatusDescription;
	private String companyCode;
	public CoSellStatusDTO()
	{
		// TODO Auto-generated constructor stub
	}

	
	public CoSellStatusDTO(Integer sui, String coSellStatusCode, String coSellStatusDescription, String companyCode) {
		super();
		this.sui=sui;
		this.coSellStatusCode = coSellStatusCode;
		this.coSellStatusDescription = coSellStatusDescription;
		this.companyCode=companyCode;
	
	}

	public CoSellStatusDTO( String coSellStatusCode, String coSellStatusDescription, String companyCode) {
		super();
		this.coSellStatusCode = coSellStatusCode;
		this.coSellStatusDescription = coSellStatusDescription;
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
	
	public String getCoSellStatusCode() {
		return coSellStatusCode;
	}

	public void setCoSellStatusCode(String coSellStatusCode) {
		this.coSellStatusCode = coSellStatusCode;
	}

	public String getCoSellStatusDescription() {
		return coSellStatusDescription;
	}

	public void setCoSellStatusDescription(String coSellStatusDescription) {
		this.coSellStatusDescription = coSellStatusDescription;
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
		return "CoSellStatusDTO [coSellStatusCode=" + coSellStatusCode + ", coSellStatusDescription=" + coSellStatusDescription
				+ "]";
	}
	

	public void print ()
	{
		System.out.println("CoSellStatus --> coSellStatusCode" + coSellStatusCode);
		System.out.println("CoSellStatus --> coSellStatusDescription" + coSellStatusDescription);
	}
}
