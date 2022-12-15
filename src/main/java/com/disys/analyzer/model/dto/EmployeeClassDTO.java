package com.disys.analyzer.model.dto;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
@Entity
public class EmployeeClassDTO implements Serializable
{
	private static final long serialVersionUID = 6128885673928232458L;
	@Id
	private Integer sui;
	private String employeeClassCode;
	private String employeeClassDescription;
	private String companyCode;
	public EmployeeClassDTO()
	{
		// TODO Auto-generated constructor stub
	}

	
	public EmployeeClassDTO(Integer sui, String employeeClassCode, String employeeClassDescription, String companyCode) {
		super();
		this.sui=sui;
		this.employeeClassCode = employeeClassCode;
		this.employeeClassDescription = employeeClassDescription;
		this.companyCode=companyCode;
	
	}

	public EmployeeClassDTO( String employeeClassCode, String employeeClassDescription, String companyCode) {
		super();
		this.employeeClassCode = employeeClassCode;
		this.employeeClassDescription = employeeClassDescription;
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
	 * @return the employeeClassCode
	 */
	public String getEmployeeClassCode() {
		return employeeClassCode;
	}

	/**
	 * @param sui the employeeClassCode to employeeClassCode
	 */
	public void setEmployeeClassCode(String employeeClassCode) {
		this.employeeClassCode = employeeClassCode;
	}

	/**
	 * @return the employeeClassDescription
	 */
	public String getEmployeeClassDescription() {
		return employeeClassDescription;
	}

	/**
	 * @param employeeClassDescription the employeeClassDescription to set
	 */
	public void setEmployeeClassDescription(String employeeClassDescription) {
		this.employeeClassDescription = employeeClassDescription;
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
		return "EmployeeClassDTO [employeeClassCode=" + employeeClassCode + ", employeeClassDescription=" + employeeClassDescription
				+ "]";
	}

	public void print ()
	{
		System.out.println("EmployeeClass --> employeeClassCode" + employeeClassCode);
		System.out.println("EmployeeClass --> employeeClassDescription" + employeeClassDescription);
	}
}
