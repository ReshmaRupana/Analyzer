package com.disys.analyzer.model.dto;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
@Entity
public class JobBoardDTO implements Serializable
{
	private static final long serialVersionUID = 6128885673928232458L;
	@Id
	private Integer sui;
	private String jobBoardCode;
	private String jobBoardDescription;
	private String companyCode;
	
	public JobBoardDTO()
	{
		// TODO Auto-generated constructor stub
	}

	
	public JobBoardDTO(Integer sui, String jobBoardCode, String jobBoardDescription, String companyCode) {
		super();
		this.sui=sui;
		this.jobBoardCode = jobBoardCode;
		this.jobBoardDescription = jobBoardDescription;
		this.companyCode=companyCode;
	
	}

	public JobBoardDTO( String jobBoardCode, String jobBoardDescription, String companyCode) {
		super();
		this.jobBoardCode = jobBoardCode;
		this.jobBoardDescription = jobBoardDescription;
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
	
	public String getJobBoardCode() {
		return jobBoardCode;
	}

	public void setJobBoardCode(String jobBoardCode) {
		this.jobBoardCode = jobBoardCode;
	}


	public String getJobBoardDescription() {
		return jobBoardDescription;
	}

	public void setJobBoardDescription(String jobBoardDescription) {
		this.jobBoardDescription = jobBoardDescription;
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
		return "JobBoardDTO [jobBoardCode=" + jobBoardCode + ", jobBoardDescription=" + jobBoardDescription
				+ "]";
	}
	

	public void print ()
	{
		System.out.println("JobBoard --> jobBoardCode" + jobBoardCode);
		System.out.println("JobBoard --> jobBoardDescription" + jobBoardDescription);
	}
}
