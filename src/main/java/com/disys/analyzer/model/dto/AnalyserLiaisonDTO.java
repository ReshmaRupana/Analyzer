package com.disys.analyzer.model.dto;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
@Entity
public class AnalyserLiaisonDTO implements Serializable
{
	private static final long serialVersionUID = 6128885673928232458L;
	@Id
	private Integer sui;
	private String analyserLiaisonIdCode;
	private String analyserLiaisonNameDescription;
	private String companyCode;
	
	public AnalyserLiaisonDTO()
	{
		// TODO Auto-generated constructor stub
	}
	
	public AnalyserLiaisonDTO(Integer sui, String analyserLiaisonIdCode, String analyserLiaisonNameDescription, String companyCode) {
		super();
		this.sui=sui;
		this.analyserLiaisonIdCode = analyserLiaisonIdCode;
		this.analyserLiaisonNameDescription = analyserLiaisonNameDescription;
		this.companyCode=companyCode;
	
	}

	public AnalyserLiaisonDTO( String analyserLiaisonIdCode, String analyserLiaisonNameDescription, String companyCode) {
		super();
		this.analyserLiaisonIdCode = analyserLiaisonIdCode;
		this.analyserLiaisonNameDescription = analyserLiaisonNameDescription;
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

	public String getAnalyserLiaisonIdCode() {
		return analyserLiaisonIdCode;
	}

	public void setAnalyserLiaisonIdCode(String analyserLiaisonIdCode) {
		this.analyserLiaisonIdCode = analyserLiaisonIdCode;
	}

	public String getAnalyserLiaisonNameDescription() {
		return analyserLiaisonNameDescription;
	}

	public void setAnalyserLiaisonNameDescription(String analyserLiaisonNameDescription) {
		this.analyserLiaisonNameDescription = analyserLiaisonNameDescription;
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
		return "AnalyserLiaisonDTO [analyserLiaisonIdCode=" + analyserLiaisonIdCode + ", analyserLiaisonNameDescription=" + analyserLiaisonNameDescription
				+ "]";
	}
	

	public void print ()
	{
		System.out.println("AnalyserLiaisonDTO --> analyserLiaisonIdCode" + analyserLiaisonIdCode);
		System.out.println("AnalyserLiaisonDTO --> analyserLiaisonNameDescription" + analyserLiaisonNameDescription);
	}
}
