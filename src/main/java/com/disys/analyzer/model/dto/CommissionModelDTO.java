package com.disys.analyzer.model.dto;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
@Entity
public class CommissionModelDTO implements Serializable
{
	private static final long serialVersionUID = 6128885673928232458L;
	@Id
	private Integer sui;
	private String commissionModelCode;
	private String commissionModelDescription;
	private String companyCode;
	
	public CommissionModelDTO()
	{
		// TODO Auto-generated constructor stub
	}

	
	public CommissionModelDTO(Integer sui, String commissionModelCode, String commissionModelDescription, String companyCode) {
		super();
		this.sui=sui;
		this.commissionModelCode = commissionModelCode;
		this.commissionModelDescription = commissionModelDescription;
		this.companyCode=companyCode;
	
	}

	public CommissionModelDTO( String commissionModelCode, String commissionModelDescription, String companyCode) {
		super();
		this.commissionModelCode = commissionModelCode;
		this.commissionModelDescription = commissionModelDescription;
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
	 * @return the commissionModelCode
	 */

	public String getCommissionModelCode() {
		return commissionModelCode;
	}

	/**
	 * @param commissionModelCode the commissionModelCode to set
	 */
	public void setCommissionModelCode(String commissionModelCode) {
		this.commissionModelCode = commissionModelCode;
	}

	/**
	 * @return the commissionModelDescription
	 */
	public String getCommissionModelDescription() {
		return commissionModelDescription;
	}

	/**
	 * @param commissionModelDescription the commissionModelDescription to set
	 */
	public void setCommissionModelDescription(String commissionModelDescription) {
		this.commissionModelDescription = commissionModelDescription;
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
		return "CommissionModelDTO [commissionModelCode=" + commissionModelCode + ", commissionModelDescription=" + commissionModelDescription
				+ "]";
	}

	public void print ()
	{
		System.out.println("CommissionModel --> commissionModelCode" + commissionModelCode);
		System.out.println("CommissionModel --> commissionModelDescription" + commissionModelDescription);
	}
}
