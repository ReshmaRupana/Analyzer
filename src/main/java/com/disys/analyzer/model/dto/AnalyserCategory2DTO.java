package com.disys.analyzer.model.dto;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
@Entity
public class AnalyserCategory2DTO implements Serializable
{
	private static final long serialVersionUID = 6128885673928232458L;
	@Id
	private Integer sui;
	private String analyserCategory2Code;
	private String analyserCategory2Description;
	private String companyCode;
	
	public AnalyserCategory2DTO()
	{
		// TODO Auto-generated constructor stub
	}

	
	public AnalyserCategory2DTO(Integer sui, String analyserCategory2Code, String analyserCategory2Description, String companyCode) {
		super();
		this.sui=sui;
		this.analyserCategory2Code = analyserCategory2Code;
		this.analyserCategory2Description = analyserCategory2Description;
		this.companyCode=companyCode;
	
	}

	public AnalyserCategory2DTO( String analyserCategory2Code, String analyserCategory2Description, String companyCode) {
		super();
		this.analyserCategory2Code = analyserCategory2Code;
		this.analyserCategory2Description = analyserCategory2Description;
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
	 * @return the analyserCategory2Code
	 */
	public String getAnalyserCategory2Code() {
		return analyserCategory2Code;
	}
	/**
	 * @param analyserCategory2Code the analyserCategory2Code to set
	 */
	public void setAnalyserCategory2Code(String analyserCategory2Code) {
		this.analyserCategory2Code = analyserCategory2Code;
	}

	/**
	 * @return the analyserCategory2Description
	 */
	public String getAnalyserCategory2Description() {
		return analyserCategory2Description;
	}
	/**
	 * @param analyserCategory2Description the analyserCategory2Description to set
	 */
	public void setAnalyserCategory2Description(String analyserCategory2Description) {
		this.analyserCategory2Description = analyserCategory2Description;
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
		return "AnalyserCategory2DTO [analyserCategory2Code=" + analyserCategory2Code + ", analyserCategory2Description=" + analyserCategory2Description
				+ "]";
	}




	public void print ()
	{
		System.out.println("AnalyserCategory2 --> analyserCategory2Code" + analyserCategory2Code);
		System.out.println("AnalyserCategory2 --> analyserCategory2Description" + analyserCategory2Description);
	}
}
