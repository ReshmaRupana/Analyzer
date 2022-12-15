package com.disys.analyzer.model.dto;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
@Entity
public class EmployeeCategoryDTO implements Serializable
{
	private static final long serialVersionUID = 6128885673928232458L;
	@Id
	private Integer sui;
	private String employeeCategoryCode;
	private String employeeCategoryDescription;
	private String companyCode;
	
	public EmployeeCategoryDTO()
	{
		// TODO Auto-generated constructor stub
	}

	
	public EmployeeCategoryDTO(Integer sui, String employeeCategoryCode, String employeeCategoryDescription, String companyCode) {
		super();
		this.sui=sui;
		this.employeeCategoryCode = employeeCategoryCode;
		this.employeeCategoryDescription = employeeCategoryDescription;
		this.companyCode=companyCode;
	
	}

	public EmployeeCategoryDTO( String employeeCategoryCode, String employeeCategoryDescription, String companyCode) {
		super();
		this.employeeCategoryCode = employeeCategoryCode;
		this.employeeCategoryDescription = employeeCategoryDescription;
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
	 * @return the employeeCategoryCode
	 */
	public String getEmployeeCategoryCode() {
		return employeeCategoryCode;
	}

	/**
	 * @param employeeCategoryCode the employeeCategoryCode to set
	 */
	public void setEmployeeCategoryCode(String employeeCategoryCode) {
		this.employeeCategoryCode = employeeCategoryCode;
	}

	/**
	 * @return the employeeCategoryDescription
	 */

	public String getEmployeeCategoryDescription() {
		return employeeCategoryDescription;
	}

	/**
	 * @param employeeCategoryDescription the employeeCategoryDescription to set
	 */
	public void setEmployeeCategoryDescription(String employeeCategoryDescription) {
		this.employeeCategoryDescription = employeeCategoryDescription;
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
		return "EmployeeCategoryDTO [employeeCategoryCode=" + employeeCategoryCode + ", employeeCategoryDescription=" + employeeCategoryDescription
				+ "]";
	}

	public void print ()
	{
		System.out.println("EmployeeCategory --> employeeCategoryCode" + employeeCategoryCode);
		System.out.println("EmployeeCategory --> employeeCategoryDescription" + employeeCategoryDescription);
	}
}
