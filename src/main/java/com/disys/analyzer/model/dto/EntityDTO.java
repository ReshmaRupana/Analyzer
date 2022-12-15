package com.disys.analyzer.model.dto;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
@Entity
public class EntityDTO implements Serializable
{
	private static final long serialVersionUID = 6128885673928232458L;
	@Id
	private Integer sui;
	private String entityCode;
	private String entityDescription;
	private String companyCode;
	public EntityDTO()
	{
		// TODO Auto-generated constructor stub
	}

	
	public EntityDTO(Integer sui, String entityCode, String entityDescription, String companyCode) {
		super();
		this.sui=sui;
		this.entityCode = entityCode;
		this.entityDescription = entityDescription;
		this.companyCode=companyCode;
	
	}

	public EntityDTO( String entityCode, String entityDescription, String companyCode) {
		super();
		this.entityCode = entityCode;
		this.entityDescription = entityDescription;
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

	public String getEntityCode() {
		return entityCode;
	}

	public void setEntityCode(String entityCode) {
		this.entityCode = entityCode;
	}

	public String getEntityDescription() {
		return entityDescription;
	}

	public void setEntityDescription(String entityDescription) {
		this.entityDescription = entityDescription;
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
		return "EntityDTO [entityCode=" + entityCode + ", entityDescription=" + entityDescription
				+ "]";
	}
	

	public void print ()
	{
		System.out.println("Entity --> entityCode" + entityCode);
		System.out.println("Entity --> entityDescription" + entityDescription);
	}
}
