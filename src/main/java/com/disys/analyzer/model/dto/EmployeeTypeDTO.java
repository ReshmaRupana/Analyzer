package com.disys.analyzer.model.dto;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
@Entity
public class EmployeeTypeDTO implements Serializable
{
	private static final long serialVersionUID = 6128885673928232458L;
	@Id
	private Integer sui;
	private String employeeTypeCode;
	private String employeeTypeDescription;
	private String companyCode;
	
	public EmployeeTypeDTO()
	{
		// TODO Auto-generated constructor stub
	}

	
	public EmployeeTypeDTO(Integer sui, String employeeTypeCode, String employeeTypeDescription, String companyCode) {
		super();
		this.sui=sui;
		this.employeeTypeCode = employeeTypeCode;
		this.employeeTypeDescription = employeeTypeDescription;
		this.companyCode=companyCode;
	
	}

	public EmployeeTypeDTO( String employeeTypeCode, String employeeTypeDescription, String companyCode) {
		super();
		this.employeeTypeCode = employeeTypeCode;
		this.employeeTypeDescription = employeeTypeDescription;
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
	 * @return the employeeTypeCode
	 */
	public String getEmployeeTypeCode() {
		return employeeTypeCode;
	}


	/**
	 * @param employeeTypeCode the employeeTypeCode to set
	 */
	
	public void setEmployeeTypeCode(String employeeTypeCode) {
		this.employeeTypeCode = employeeTypeCode;
	}


	/**
	 * @return the employeeTypeDescription
	 */

	public String getEmployeeTypeDescription() {
		return employeeTypeDescription;
	}


	/**
	 * @param employeeTypeDescription the employeeTypeDescription to set
	 */
	
	public void setEmployeeTypeDescription(String employeeTypeDescription) {
		this.employeeTypeDescription = employeeTypeDescription;
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
		return "EmployeeTypeDTO [employeeTypeCode=" + employeeTypeCode + ", employeeTypeDescription=" + employeeTypeDescription
				+ "]";
	}
	

	public void print ()
	{
		System.out.println("EmployeeType --> employeeTypeCode" + employeeTypeCode);
		System.out.println("EmployeeType --> employeeTypeDescription" + employeeTypeDescription);
	}
}
