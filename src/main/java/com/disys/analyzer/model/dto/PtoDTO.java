package com.disys.analyzer.model.dto;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
@Entity
public class PtoDTO implements Serializable
{
	private static final long serialVersionUID = 6128885673928232458L;
	@Id
	private Integer sui;
	private String ptoCode;
	private String ptoDescription;
	
	public PtoDTO()
	{
		// TODO Auto-generated constructor stub
	}

	
	public PtoDTO(Integer sui, String ptoCode, String ptoDescription) {
		super();
		this.sui=sui;
		this.ptoCode = ptoCode;
		this.ptoDescription = ptoDescription;
	
	}

	public PtoDTO( String ptoCode, String ptoDescription) {
		super();
		this.ptoCode = ptoCode;
		this.ptoDescription = ptoDescription;
		
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


	public String getPtoCode() {
		return ptoCode;
	}


	public void setPtoCode(String ptoCode) {
		this.ptoCode = ptoCode;
	}


	public String getPtoDescription() {
		return ptoDescription;
	}


	public void setPtoDescription(String ptoDescription) {
		this.ptoDescription = ptoDescription;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "PtoDTO [ptoCode=" + ptoCode + ", ptoDescription=" + ptoDescription
				+ "]";
	}
	

	public void print ()
	{
		System.out.println("PTO --> ptoCode" + ptoCode);
		System.out.println("PTO --> ptoDescription" + ptoDescription);
	}
}
