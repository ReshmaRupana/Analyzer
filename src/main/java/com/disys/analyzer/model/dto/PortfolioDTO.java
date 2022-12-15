package com.disys.analyzer.model.dto;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
@Entity
public class PortfolioDTO implements Serializable
{
	private static final long serialVersionUID = 6128885673928232458L;
	@Id
	private Integer portfolioId;
	private String portfolioCode;
	private String portfolioDescription;
	
	public PortfolioDTO()
	{
		// TODO Auto-generated constructor stub
	}

	
	public PortfolioDTO(Integer portfolioId, String portfolioCode, String portfolioDescription) {
		super();
		this.portfolioId=portfolioId;
		this.portfolioCode = portfolioCode;
		this.portfolioDescription = portfolioDescription;
	
	}

	public PortfolioDTO( String portfolioCode, String portfolioDescription) {
		super();
		this.portfolioCode = portfolioCode;
		this.portfolioDescription = portfolioDescription;
		
	}
	/**
	 * @return the portfolioId
	 */
	
	public Integer getPortfolioId() {
		return portfolioId;
	}

	/**
	 * @param portfolioId the portfolioId to set
	 */
	public void setPortfolioId(Integer portfolioId) {
		this.portfolioId = portfolioId;
	}

	/**
	 * @return the portfolioCode
	 */
	public String getPortfolioCode() {
		return portfolioCode;
	}

	/**
	 * @param portfolioCode the portfolioCode to set
	 */
	public void setPortfolioCode(String portfolioCode) {
		this.portfolioCode = portfolioCode;
	}

	/**
	 * @return the portfolioDescription
	 */
	public String getPortfolioDescription() {
		return portfolioDescription;
	}

	/**
	 * @param portfolioDescription the portfolioDescription to set
	 */
	public void setPortfolioDescription(String portfolioDescription) {
		this.portfolioDescription = portfolioDescription;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "PortfolioDTO [portfolioCode=" + portfolioCode + ", portfolioDescription=" + portfolioDescription
				+ "]";
	}

	public void print ()
	{
		System.out.println("Portfolio --> portfolioCode" + portfolioCode);
		System.out.println("Portfolio --> portfolioDescription" + portfolioDescription);
	}
}
