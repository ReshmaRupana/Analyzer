package com.disys.analyzer.model.dto;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
@Entity
public class BonusPercentageDTO implements Serializable
{
	private static final long serialVersionUID = 6128885673928232458L;
	@Id
	private Integer sui;
	private String bonusPercentageCode;
	private String bonusPercentageDescription;
	private String companyCode;
	
	public BonusPercentageDTO()
	{
		// TODO Auto-generated constructor stub
	}
	
	public BonusPercentageDTO(Integer sui, String bonusPercentageCode, String bonusPercentageDescription, String companyCode) {
		super();
		this.sui=sui;
		this.bonusPercentageCode = bonusPercentageCode;
		this.bonusPercentageDescription = bonusPercentageDescription;
		this.companyCode=companyCode;
	
	}

	public BonusPercentageDTO( String bonusPercentageCode, String bonusPercentageDescription, String companyCode) {
		super();
		this.bonusPercentageCode = bonusPercentageCode;
		this.bonusPercentageDescription = bonusPercentageDescription;
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
	
	public String getBonusPercentageCode() {
		return bonusPercentageCode;
	}

	public void setBonusPercentageCode(String bonusPercentageCode) {
		this.bonusPercentageCode = bonusPercentageCode;
	}

	public String getBonusPercentageDescription() {
		return bonusPercentageDescription;
	}

	public void setBonusPercentageDescription(String bonusPercentageDescription) {
		this.bonusPercentageDescription = bonusPercentageDescription;
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
		return "BonusPercentageDTO [bonusPercentageCode=" + bonusPercentageCode + ", bonusPercentageDescription=" + bonusPercentageDescription
				+ "]";
	}
	

	public void print ()
	{
		System.out.println("BonusPercentage --> bonusPercentageCode" + bonusPercentageCode);
		System.out.println("BonusPercentage --> bonusPercentageDescription" + bonusPercentageDescription);
	}
}
