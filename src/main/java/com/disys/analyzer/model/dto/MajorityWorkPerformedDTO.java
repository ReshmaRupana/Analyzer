package com.disys.analyzer.model.dto;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
@Entity
public class MajorityWorkPerformedDTO implements Serializable
{
	private static final long serialVersionUID = 1L;
	@Id
	private Integer sui;
	private String majorityWorkPerformedCode;
	private String majorityWorkPerformedDescription;
	private String companyCode;
	
	public MajorityWorkPerformedDTO()
	{
		// TODO Auto-generated constructor stub
	}

	
	public MajorityWorkPerformedDTO(Integer sui, String majorityWorkPerformedCode, String majorityWorkPerformedDescription, String companyCode) {
		super();
		this.sui=sui;
		this.majorityWorkPerformedCode = majorityWorkPerformedCode;
		this.majorityWorkPerformedDescription = majorityWorkPerformedDescription;
		this.companyCode=companyCode;
	
	}

	public MajorityWorkPerformedDTO( String majorityWorkPerformedCode, String majorityWorkPerformedDescription, String companyCode) {
		super();
		this.majorityWorkPerformedCode = majorityWorkPerformedCode;
		this.majorityWorkPerformedDescription = majorityWorkPerformedDescription;
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
	 * @return the majorityWorkPerformedCode
	 */

	public String getMajorityWorkPerformedCode() {
		return majorityWorkPerformedCode;
	}
	/**
	 * @param majorityWorkPerformedCode the majorityWorkPerformedCode to set
	 */

	public void setMajorityWorkPerformedCode(String majorityWorkPerformedCode) {
		this.majorityWorkPerformedCode = majorityWorkPerformedCode;
	}

	/**
	 * @return the majorityWorkPerformedStatusDescription
	 */
	public String getMajorityWorkPerformedDescription() {
		return majorityWorkPerformedDescription;
	}

	/**
	 * @param sui the majorityWorkPerformedDescription to majorityWorkPerformedDescription
	 */
	public void setMajorityWorkPerformedDescription(String majorityWorkPerformedDescription) {
		this.majorityWorkPerformedDescription = majorityWorkPerformedDescription;
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
		return "majorityWorkPerformedDTO [majorityWorkPerformedCode=" + majorityWorkPerformedCode + ", "
				+ "majorityWorkPerformedDescription=" + majorityWorkPerformedDescription+ "]";
	}

	public void print ()
	{
		System.out.println("majorityWorkPerformed --> majorityWorkPerformedDescription" + majorityWorkPerformedCode);
		System.out.println("majorityWorkPerformed --> majorityWorkPerformedDescription" + majorityWorkPerformedDescription);
	}
}
