package com.disys.analyzer.model.dto;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
@Entity
public class DealTypeDTO implements Serializable
{
	private static final long serialVersionUID = 6128885673928232458L;
	@Id
	private Integer sui;
	private String dealTypeCode;
	private String dealTypeDescription;
	private String companyCode;
	public DealTypeDTO()
	{
		// TODO Auto-generated constructor stub
	}
	
	public DealTypeDTO(Integer sui, String dealTypeCode, String dealTypeDescription, String companyCode) {
		super();
		this.sui=sui;
		this.dealTypeCode = dealTypeCode;
		this.dealTypeDescription = dealTypeDescription;
		this.companyCode=companyCode;
	
	}

	public DealTypeDTO( String dealTypeCode, String dealTypeDescription, String companyCode) {
		super();
		this.dealTypeCode = dealTypeCode;
		this.dealTypeDescription = dealTypeDescription;
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
	 * @return the dealTypeCode
	 */	
	public String getDealTypeCode() {
		return dealTypeCode;
	}
	/**
	 * @param dealTypeCode the dealTypeCode to set
	 */
	public void setDealTypeCode(String dealTypeCode) {
		this.dealTypeCode = dealTypeCode;
	}
	/**
	 * @return the dealTypeDescription
	 */
	public String getDealTypeDescription() {
		return dealTypeDescription;
	}
	/**
	 * @param dealTypeDescription the dealTypeDescription to set
	 */

	public void setDealTypeDescription(String dealTypeDescription) {
		this.dealTypeDescription = dealTypeDescription;
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
		return "DealTypeDTO [dealTypeCode=" + dealTypeCode + ", dealTypeDescription=" + dealTypeDescription
				+ "]";
	}

	

	public void print ()
	{
		System.out.println("DealType --> dealTypeCode" + dealTypeCode);
		System.out.println("DealType --> dealTypeDescription" + dealTypeDescription);
	}
}
