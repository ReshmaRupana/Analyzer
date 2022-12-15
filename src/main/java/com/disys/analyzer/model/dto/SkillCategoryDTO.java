package com.disys.analyzer.model.dto;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
@Entity
public class SkillCategoryDTO implements Serializable
{
	private static final long serialVersionUID = 6128885673928232458L;
	@Id
	private Integer sui;
	private String skillCategoryCode;
	private String skillCategoryDescription;
	private String companyCode;
	
	public SkillCategoryDTO()
	{
		// TODO Auto-generated constructor stub
	}

	
	public SkillCategoryDTO(Integer sui, String skillCategoryCode, String skillCategoryDescription, String companyCode) {
		super();
		this.sui=sui;
		this.skillCategoryCode = skillCategoryCode;
		this.skillCategoryDescription = skillCategoryDescription;
		this.companyCode=companyCode;
	
	}

	public SkillCategoryDTO( String skillCategoryCode, String skillCategoryDescription, String companyCode) {
		super();
		this.skillCategoryCode = skillCategoryCode;
		this.skillCategoryDescription = skillCategoryDescription;
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

	public String getSkillCategoryCode() {
		return skillCategoryCode;
	}


	public void setSkillCategoryCode(String skillCategoryCode) {
		this.skillCategoryCode = skillCategoryCode;
	}


	public String getSkillCategoryDescription() {
		return skillCategoryDescription;
	}


	public void setSkillCategoryDescription(String skillCategoryDescription) {
		this.skillCategoryDescription = skillCategoryDescription;
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
		return "SkillCategoryDTO [skillCategoryCode=" + skillCategoryCode + ", skillCategoryDescription=" + skillCategoryDescription
				+ "]";
	}
	

	public void print ()
	{
		System.out.println("SkillCategory --> skillCategoryCode" + skillCategoryCode);
		System.out.println("SkillCategory --> skillCategoryDescription" + skillCategoryDescription);
	}
}
