package com.disys.analyzer.model.dto;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
@Entity
public class WorkFromSourceDTO implements Serializable
{
	private static final long serialVersionUID = 6128885673928232458L;
	@Id
	private Integer sui;
	private String workFromSourceCode;
	private String workFromSourceDescription;
	private String companyCode;
	
	public WorkFromSourceDTO()
	{
		// TODO Auto-generated constructor stub
	}

	
	public WorkFromSourceDTO(Integer sui, String workFromSourceCode, String workFromSourceDescription, String companyCode) {
		super();
		this.sui=sui;
		this.workFromSourceCode = workFromSourceCode;
		this.workFromSourceDescription = workFromSourceDescription;
		this.companyCode=companyCode;
	
	}

	public WorkFromSourceDTO( String workFromSourceCode, String workFromSourceDescription, String companyCode) {
		super();
		this.workFromSourceCode = workFromSourceCode;
		this.workFromSourceDescription = workFromSourceDescription;
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
	 * @return the WorkFromSourceCode
	 */

	public String getWorkFromSourceCode() {
		return workFromSourceCode;
	}
	/**
	 * @param WorkFromSourceCode the WorkFromSourceCode to set
	 */

	public void setWorkFromSourceCode(String workFromSourceCode) {
		this.workFromSourceCode = workFromSourceCode;
	}

	/**
	 * @return the WorkFromSourceDescription
	 */
	public String getWorkFromSourceDescription() {
		return workFromSourceDescription;
	}

	/**
	 * @param sui the WorkFromSourceDescription to WorkFromSourceDescription
	 */
	public void setWorkFromSourceDescription(String workFromSourceDescription) {
		this.workFromSourceDescription = workFromSourceDescription;
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
		return "WorkFromSourceDTO [workFromSourceCode=" + workFromSourceCode + ", workFromSourceDescription=" + workFromSourceDescription
				+ "]";
	}

	public void print ()
	{
		System.out.println("WorkFromSource --> workFromSourceCode" + workFromSourceCode);
		System.out.println("WorkFromSource --> workFromSourceDescription" + workFromSourceDescription);
	}
}
