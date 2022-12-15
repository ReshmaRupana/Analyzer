package com.disys.analyzer.model.dto;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
@Entity
public class AnalyzerCategory1DTO implements Serializable
{
	private static final long serialVersionUID = 6128885673928232458L;
	@Id
	private Integer sui;
	private String analyzerCategory1Code;
	private String analyzerCategory1Description;
	private String companyCode;
	
	public AnalyzerCategory1DTO()
	{
		// TODO Auto-generated constructor stub
	}

	
	public AnalyzerCategory1DTO(Integer sui, String analyzerCategory1Code, String analyzerCategory1Description, String companyCode) {
		super();
		this.sui=sui;
		this.analyzerCategory1Code = analyzerCategory1Code;
		this.analyzerCategory1Description = analyzerCategory1Description;
		this.companyCode=companyCode;
	
	}

	public AnalyzerCategory1DTO( String analyzerCategory1Code, String analyzerCategory1Description, String companyCode) {
		super();
		this.analyzerCategory1Code = analyzerCategory1Code;
		this.analyzerCategory1Description = analyzerCategory1Description;
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
	
	public String getAnalyzerCategory1Code() {
		return analyzerCategory1Code;
	}


	public void setAnalyzerCategory1Code(String analyzerCategory1Code) {
		this.analyzerCategory1Code = analyzerCategory1Code;
	}


	public String getAnalyzerCategory1Description() {
		return analyzerCategory1Description;
	}


	public void setAnalyzerCategory1Description(String analyzerCategory1Description) {
		this.analyzerCategory1Description = analyzerCategory1Description;
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
		return "AnalyzerCategory1DTO [analyzerCategory1Code=" + analyzerCategory1Code + ", analyzerCategory1Description=" + analyzerCategory1Description
				+ "]";
	}
	

	public void print ()
	{
		System.out.println("AnalyzerCategory1 --> analyzerCategory1Code" + analyzerCategory1Code);
		System.out.println("AnalyzerCategory1 --> analyzerCategory1Description" + analyzerCategory1Description);
	}
}
