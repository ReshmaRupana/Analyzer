package com.disys.analyzer.model.dto;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
@Entity
public class SickLeaveTypeDTO implements Serializable
{
	private static final long serialVersionUID = 6128885673928232458L;
	@Id
	private Integer sui;
	private String sickLeaveTypeCode;
	private String sickLeaveTypeDescription;
	private String companyCode;
	
	public SickLeaveTypeDTO()
	{
		// TODO Auto-generated constructor stub
	}

	
	public SickLeaveTypeDTO(Integer sui, String sickLeaveTypeCode, String sickLeaveTypeDescription, String companyCode) {
		super();
		this.sui=sui;
		this.sickLeaveTypeCode = sickLeaveTypeCode;
		this.sickLeaveTypeDescription = sickLeaveTypeDescription;
		this.companyCode=companyCode;
	
	}

	public SickLeaveTypeDTO( String sickLeaveTypeCode, String sickLeaveTypeDescription, String companyCode) {
		super();
		this.sickLeaveTypeCode = sickLeaveTypeCode;
		this.sickLeaveTypeDescription = sickLeaveTypeDescription;
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

	public String getSickLeaveTypeCode() {
		return sickLeaveTypeCode;
	}

	public void setSickLeaveTypeCode(String sickLeaveTypeCode) {
		this.sickLeaveTypeCode = sickLeaveTypeCode;
	}

	public String getSickLeaveTypeDescription() {
		return sickLeaveTypeDescription;
	}

	public void setSickLeaveTypeDescription(String sickLeaveTypeDescription) {
		this.sickLeaveTypeDescription = sickLeaveTypeDescription;
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
		return "SickLeaveTypeDTO [sickLeaveTypeCode=" + sickLeaveTypeCode + ", sickLeaveTypeDescription=" + sickLeaveTypeDescription
				+ "]";
	}
	
	public void print ()
	{
		System.out.println("SickLeaveType --> sickLeaveTypeCode" + sickLeaveTypeCode);
		System.out.println("SickLeaveType --> sickLeaveTypeDescription" + sickLeaveTypeDescription);
	}
}
