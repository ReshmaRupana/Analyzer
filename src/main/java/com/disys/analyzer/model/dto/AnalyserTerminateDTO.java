/**
 * 
 */
package com.disys.analyzer.model.dto;

import java.io.Serializable;

/**
 * @author Sajid
 * @since 4th July, 2019
 */
public class AnalyserTerminateDTO implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String analyserId;
	private String terminateDate;
	private boolean eligibleForRehire;
	private boolean dentalInsurance;
	private boolean healthInsurance;
	private boolean k401Savings;
	private String reason;
	private boolean falseTermination;
	/**
	 * @return the analyserId
	 */
	public String getAnalyserId()
	{
		return analyserId;
	}
	/**
	 * @param analyserId the analyserId to set
	 */
	public void setAnalyserId(String analyserId)
	{
		this.analyserId = analyserId;
	}
	/**
	 * @return the terminateDate
	 */
	public String getTerminateDate()
	{
		return terminateDate;
	}
	/**
	 * @param terminateDate the terminateDate to set
	 */
	public void setTerminateDate(String terminateDate)
	{
		this.terminateDate = terminateDate;
	}
	/**
	 * @return the eligibleForRehire
	 */
	public boolean isEligibleForRehire()
	{
		return eligibleForRehire;
	}
	/**
	 * @param eligibleForRehire the eligibleForRehire to set
	 */
	public void setEligibleForRehire(boolean eligibleForRehire)
	{
		this.eligibleForRehire = eligibleForRehire;
	}
	/**
	 * @return the dentalInsurance
	 */
	public boolean isDentalInsurance()
	{
		return dentalInsurance;
	}
	/**
	 * @param dentalInsurance the dentalInsurance to set
	 */
	public void setDentalInsurance(boolean dentalInsurance)
	{
		this.dentalInsurance = dentalInsurance;
	}
	/**
	 * @return the healthInsurance
	 */
	public boolean isHealthInsurance()
	{
		return healthInsurance;
	}
	/**
	 * @param healthInsurance the healthInsurance to set
	 */
	public void setHealthInsurance(boolean healthInsurance)
	{
		this.healthInsurance = healthInsurance;
	}
	/**
	 * @return the k401Savings
	 */
	public boolean isK401Savings()
	{
		return k401Savings;
	}
	/**
	 * @param k401Savings the k401Savings to set
	 */
	public void setK401Savings(boolean k401Savings)
	{
		this.k401Savings = k401Savings;
	}
	/**
	 * @return the reason
	 */
	public String getReason()
	{
		return reason;
	}
	/**
	 * @param reason the reason to set
	 */
	public void setReason(String reason)
	{
		this.reason = reason;
	}
	/**
	 * @return the falseTermination
	 */
	public boolean isFalseTermination()
	{
		return falseTermination;
	}
	/**
	 * @param falseTermination the falseTermination to set
	 */
	public void setFalseTermination(boolean falseTermination)
	{
		this.falseTermination = falseTermination;
	}
}
