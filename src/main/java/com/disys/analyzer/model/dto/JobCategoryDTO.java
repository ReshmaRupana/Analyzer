package com.disys.analyzer.model.dto;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
@Entity
public class JobCategoryDTO implements Serializable
{
	private static final long serialVersionUID = 6128885673928232458L;
	@Id
	private Integer sui;
	private String jobCategoryCode;
	private String jobCategoryDescription;
	private String companyCode;
	
	public JobCategoryDTO()
	{
		// TODO Auto-generated constructor stub
	}

	
	public JobCategoryDTO(Integer sui, String jobCategoryCode, String jobCategoryDescription, String companyCode) {
		super();
		this.sui=sui;
		this.jobCategoryCode = jobCategoryCode;
		this.jobCategoryDescription = jobCategoryDescription;
		this.companyCode=companyCode;
	
	}

	public JobCategoryDTO( String jobCategoryCode, String jobCategoryDescription, String companyCode) {
		super();
		this.jobCategoryCode = jobCategoryCode;
		this.jobCategoryDescription = jobCategoryDescription;
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
	 * @return the jobCategoryCode
	 */
	public String getJobCategoryCode() {
		return jobCategoryCode;
	}

	/**
	 * @param jobCategoryCode the jobCategoryCode to set
	 */
	public void setJobCategoryCode(String jobCategoryCode) {
		this.jobCategoryCode = jobCategoryCode;
	}

	/**
	 * @return the jobCategoryDescription
	 */
	public String getJobCategoryDescription() {
		return jobCategoryDescription;
	}

	/**
	 * @param jobCategoryDescription the jobCategoryDescription to set
	 */
	public void setJobCategoryDescription(String jobCategoryDescription) {
		this.jobCategoryDescription = jobCategoryDescription;
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
		return "JobCategoryDTO [jobCategoryCode=" + jobCategoryCode + ", jobCategoryDescription=" + jobCategoryDescription
				+ "]";
	}

	public void print ()
	{
		System.out.println("JobCategory --> jobCategoryCode" + jobCategoryCode);
		System.out.println("JobCategory --> jobCategoryDescription" + jobCategoryDescription);
	}
}
