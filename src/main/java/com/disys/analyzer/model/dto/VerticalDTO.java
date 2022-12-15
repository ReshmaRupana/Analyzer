package com.disys.analyzer.model.dto;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
@Entity
public class VerticalDTO implements Serializable
{
	private static final long serialVersionUID = 6128885673928232458L;
	@Id
	private Integer sui;
	private String verticalCode;
	private String verticalDescription;
	private String companyCode;
	
	public VerticalDTO()
	{
		// TODO Auto-generated constructor stub
	}

	
	public VerticalDTO(Integer sui, String verticalCode, String verticalDescription, String companyCode) {
		super();
		this.sui=sui;
		this.verticalCode = verticalCode;
		this.verticalDescription = verticalDescription;
		this.companyCode=companyCode;
	
	}

	public VerticalDTO( String verticalCode, String verticalDescription, String companyCode) {
		super();
		this.verticalCode = verticalCode;
		this.verticalDescription = verticalDescription;
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

	public String getVerticalCode() {
		return verticalCode;
	}


	public void setVerticalCode(String verticalCode) {
		this.verticalCode = verticalCode;
	}


	public String getVerticalDescription() {
		return verticalDescription;
	}


	public void setVerticalDescription(String verticalDescription) {
		this.verticalDescription = verticalDescription;
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
		return "VerticalDTO [verticalCode=" + verticalCode + ", verticalDescription=" + verticalDescription
				+ "]";
	}
	

	public void print ()
	{
		System.out.println("Vertical --> verticalCode" + verticalCode);
		System.out.println("Vertical --> verticalDescription" + verticalDescription);
	}
}
