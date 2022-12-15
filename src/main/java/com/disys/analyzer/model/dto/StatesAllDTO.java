package com.disys.analyzer.model.dto;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
@Entity
public class StatesAllDTO implements Serializable
{
	private static final long serialVersionUID = 6128885673928232458L;
	
	@Id
	private Integer sui;
	private String stateName;
	private String stateCode;
	
	public StatesAllDTO()
	{
		// TODO Auto-generated constructor stub
	}

	
	public StatesAllDTO( Integer sui, String stateCode, String stateName) {
		super();
		this.sui=sui;
		this.stateCode = stateCode;
		this.stateName = stateName;
	
	}

	public StatesAllDTO( String stateCode, String stateName) {
		super();
		this.stateCode = stateCode;
		this.stateName = stateName;
		
	}
	/**
	 * @return the stateName
	 */
	public String getStateName()
	{
		return stateName;
	}
	/**
	 * @param stateName the stateName to set
	 */
	public void setStateName(String stateName)
	{
		this.stateName = stateName;
	}
	/**
	 * @return the stateCode
	 */
	public String getStateCode()
	{
		return stateCode;
	}
	/**
	 * @param stateCode the stateCode to set
	 */
	public void setStateCode(String stateCode)
	{
		this.stateCode = stateCode;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "StatesAllDTO [stateCode=" + stateCode + ", stateName=" + stateName
				+ "]";
	}

	public void print ()
	{
		System.out.println("State --> stateCode" + stateCode);
		System.out.println("State --> stateName" + stateName);
	}
}
