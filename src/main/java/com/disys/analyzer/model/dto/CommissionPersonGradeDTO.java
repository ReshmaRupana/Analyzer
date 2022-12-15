package com.disys.analyzer.model.dto;

import java.sql.Timestamp;

public class CommissionPersonGradeDTO
{
	private Integer commissionPersonGradeId;
	private String commissionPersonEmplId; 
	private String commissionPersonName;
	private String commissionPersonEmail;
	private Timestamp effectiveDate;
	private String scoreCardGrade;
	private Timestamp entryDateTime;
	
	public CommissionPersonGradeDTO()
	{
		
	}

	public Integer getCommissionPersonGradeId()
	{
		return commissionPersonGradeId;
	}

	public void setCommissionPersonGradeId(Integer commissionPersonGradeId)
	{
		this.commissionPersonGradeId = commissionPersonGradeId;
	}

	public String getCommissionPersonEmplId()
	{
		return commissionPersonEmplId;
	}

	public void setCommissionPersonEmplId(String commissionPersonEmplId)
	{
		this.commissionPersonEmplId = commissionPersonEmplId;
	}

	public String getCommissionPersonName()
	{
		return commissionPersonName;
	}

	public void setCommissionPersonName(String commissionPersonName)
	{
		this.commissionPersonName = commissionPersonName;
	}

	public String getCommissionPersonEmail()
	{
		return commissionPersonEmail;
	}

	public void setCommissionPersonEmail(String commissionPersonEmail)
	{
		this.commissionPersonEmail = commissionPersonEmail;
	}

	public Timestamp getEffectiveDate()
	{
		return effectiveDate;
	}

	public void setEffectiveDate(Timestamp effectiveDate)
	{
		this.effectiveDate = effectiveDate;
	}

	public String getScoreCardGrade()
	{
		return scoreCardGrade;
	}

	public void setScoreCardGrade(String scoreCardGrade)
	{
		this.scoreCardGrade = scoreCardGrade;
	}

	public Timestamp getEntryDateTime()
	{
		return entryDateTime;
	}

	public void setEntryDateTime(Timestamp entryDateTime)
	{
		this.entryDateTime = entryDateTime;
	}
}
