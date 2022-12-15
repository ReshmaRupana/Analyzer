/**
 * 
 */
package com.disys.analyzer.model.dto;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author Sajid
 * 
 *
 */
@Entity
public class RecruitingClassificationDTO implements Serializable
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	private Integer recruitingClassificationId;
	private Integer status;
	private String recruitingClassificationName;
	private String recruitingClassificationValue;
	
	/**
	 * @return the recruitingClassificationId
	 */
	public Integer getRecruitingClassificationId()
	{
		return recruitingClassificationId;
	}
	
	/**
	 * @param recruitingClassificationId
	 *            the recruitingClassificationId to set
	 */
	public void setRecruitingClassificationId(Integer recruitingClassificationId)
	{
		this.recruitingClassificationId = recruitingClassificationId;
	}
	
	/**
	 * @return the status
	 */
	public Integer getStatus()
	{
		return status;
	}
	
	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(Integer status)
	{
		this.status = status;
	}
	
	/**
	 * @return the recruitingClassificationName
	 */
	public String getRecruitingClassificationName()
	{
		return recruitingClassificationName;
	}
	
	/**
	 * @param recruitingClassificationName
	 *            the recruitingClassificationName to set
	 */
	public void setRecruitingClassificationName(String recruitingClassificationName)
	{
		this.recruitingClassificationName = recruitingClassificationName;
	}
	
	/**
	 * @return the recruitingClassificationValue
	 */
	public String getRecruitingClassificationValue()
	{
		return recruitingClassificationValue;
	}
	
	/**
	 * @param recruitingClassificationValue
	 *            the recruitingClassificationValue to set
	 */
	public void setRecruitingClassificationValue(String recruitingClassificationValue)
	{
		this.recruitingClassificationValue = recruitingClassificationValue;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "RecruitingClassificationDTO [recruitingClassificationId=" + recruitingClassificationId + ", status=" + status + ", recruitingClassificationName=" + recruitingClassificationName
				+ ", recruitingClassificationValue=" + recruitingClassificationValue + "]";
	}
}