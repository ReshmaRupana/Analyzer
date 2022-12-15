package com.disys.analyzer.model.dto;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
@Entity
public class SplitPercentageDTO implements Serializable
{
	private static final long serialVersionUID = 6128885673928232458L;
	@Id
	private Integer sui;
	private String splitPercentageCode;
	private String splitPercentageDescription;
	private String companyCode;
	
	public SplitPercentageDTO()
	{
		// TODO Auto-generated constructor stub
	}

	
	public SplitPercentageDTO(Integer sui, String splitPercentageCode, String splitPercentageDescription, String companyCode) {
		super();
		this.sui=sui;
		this.splitPercentageCode = splitPercentageCode;
		this.splitPercentageDescription = splitPercentageDescription;
		this.companyCode=companyCode;
	
	}

	public SplitPercentageDTO( String splitPercentageCode, String splitPercentageDescription, String companyCode) {
		super();
		this.splitPercentageCode = splitPercentageCode;
		this.splitPercentageDescription = splitPercentageDescription;
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
	 * @return the splitPercentageCode
	 */
	public String getSplitPercentageCode() {
		return splitPercentageCode;
	}

	/**
	 * @param splitPercentageCode the splitPercentageCode to set
	 */
	public void setSplitPercentageCode(String splitPercentageCode) {
		this.splitPercentageCode = splitPercentageCode;
	}

	/**
	 * @return the splitPercentageDescription
	 */
	public String getSplitPercentageDescription() {
		return splitPercentageDescription;
	}

	/**
	 * @param splitPercentageDescription the splitPercentageDescription to set
	 */
	public void setSplitPercentageDescription(String splitPercentageDescription) {
		this.splitPercentageDescription = splitPercentageDescription;
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
		return "SplitPercentageDTO [splitPercentageCode=" + splitPercentageCode + ", splitPercentageDescription=" + splitPercentageDescription
				+ "]";
	}

	public void print ()
	{
		System.out.println("SplitPercentage --> splitPercentageCode" + splitPercentageCode);
		System.out.println("SplitPercentage --> splitPercentageDescription" + splitPercentageDescription);
	}
}
