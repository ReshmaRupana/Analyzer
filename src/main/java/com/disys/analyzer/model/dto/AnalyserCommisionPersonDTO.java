/**
 * 
 */
package com.disys.analyzer.model.dto;

import java.io.Serializable;
import java.sql.Timestamp;

import com.disys.analyzer.model.AnalyserCommisionPerson;

/**
 * @author Sajid
 * 
 *
 */
public class AnalyserCommisionPersonDTO  implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer orgId;
	private String userId;
	private String commissionPersonRole;
	private String personName;
	private String updatedBy;
	private Timestamp updatedDate;
	private String commissionPersonClassification;
	private Integer status;
	private String ADPCode;
	
	public AnalyserCommisionPersonDTO(){
		
	}
	
	public AnalyserCommisionPersonDTO(AnalyserCommisionPerson person, String ADPCode){
		this.orgId = person.getId().getOrgId();
		this.userId = person.getId().getUserId();
		this.commissionPersonClassification = person.getCommissionPersonClassification();
		this.personName = person.getPersonName();
		this.updatedBy = person.getUpdatedBy();
		this.updatedDate = person.getUpdatedDate();
		this.commissionPersonRole = person.getCommissionPersonRole();
		this.status = person.getStatus();
		this.ADPCode = ADPCode;
	}
	
	
	/**
	 * @return the orgId
	 */
	public Integer getOrgId()
	{
		return orgId;
	}
	/**
	 * @param orgId the orgId to set
	 */
	public void setOrgId(Integer orgId)
	{
		this.orgId = orgId;
	}
	/**
	 * @return the userId
	 */
	public String getUserId()
	{
		return userId;
	}
	/**
	 * @param userId the userId to set
	 */
	public void setUserId(String userId)
	{
		this.userId = userId;
	}
	/**
	 * @return the commissionPersonRole
	 */
	public String getCommissionPersonRole()
	{
		return commissionPersonRole;
	}
	/**
	 * @param commissionPersonRole the commissionPersonRole to set
	 */
	public void setCommissionPersonRole(String commissionPersonRole)
	{
		this.commissionPersonRole = commissionPersonRole;
	}
	/**
	 * @return the personName
	 */
	public String getPersonName()
	{
		return personName;
	}
	/**
	 * @param personName the personName to set
	 */
	public void setPersonName(String personName)
	{
		this.personName = personName;
	}
	/**
	 * @return the updatedBy
	 */
	public String getUpdatedBy()
	{
		return updatedBy;
	}
	/**
	 * @param updatedBy the updatedBy to set
	 */
	public void setUpdatedBy(String updatedBy)
	{
		this.updatedBy = updatedBy;
	}
	/**
	 * @return the updatedDate
	 */
	public Timestamp getUpdatedDate()
	{
		return updatedDate;
	}
	/**
	 * @param updatedDate the updatedDate to set
	 */
	public void setUpdatedDate(Timestamp updatedDate)
	{
		this.updatedDate = updatedDate;
	}
	/**
	 * @return the commissionPersonClassification
	 */
	public String getCommissionPersonClassification()
	{
		return commissionPersonClassification;
	}
	/**
	 * @param commissionPersonClassification the commissionPersonClassification to set
	 */
	public void setCommissionPersonClassification(String commissionPersonClassification)
	{
		this.commissionPersonClassification = commissionPersonClassification;
	}
	/**
	 * @return the status
	 */
	public Integer getStatus()
	{
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(Integer status)
	{
		this.status = status;
	}
	/**
	 * @return the aDPCode
	 */
	public String getADPCode()
	{
		return ADPCode;
	}
	/**
	 * @param aDPCode the aDPCode to set
	 */
	public void setADPCode(String aDPCode)
	{
		ADPCode = aDPCode;
	}
}
