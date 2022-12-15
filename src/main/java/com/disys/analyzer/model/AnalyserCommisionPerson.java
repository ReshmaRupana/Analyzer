package com.disys.analyzer.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * The persistent class for the AnalyserCommisionPersons database table.
 * 
 */
@Entity
@Table(name = "Analyser_Commision_Persons")//,schema="Analyser.dbo")
@NamedQuery(name = "AnalyserCommisionPerson.findAll", query = "SELECT a FROM AnalyserCommisionPerson a")
public class AnalyserCommisionPerson implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private AnalyserCommisionPersonPK id;
	
	/*
	 * @Id
	 * 
	 * @GeneratedValue(strategy=GenerationType.AUTO)
	 * 
	 * @Column(name="AnalyserCommisionPersonId") private int
	 * analyserCommisionPersonId;
	 */
	@Column(name = "CommissionPersonRole")
	private String commissionPersonRole;

	@Column(name = "PersonName")
	private String personName;

	@Column(name = "Updated_By")
	private String updatedBy;

	@Column(name = "Updated_Date")
	private Timestamp updatedDate;

	@Column(name = "CommissionPersonClassification")
	private String commissionPersonClassification;

	@Column(name = "Status")
	private Integer status;
	
	@Column(name = "CompanyCode")
	private String companyCode;
	
	@Transient
	private String adpCode;
	
	public AnalyserCommisionPerson() {
	}

	public String getCommissionPersonRole() {
		return this.commissionPersonRole;
	}

	public void setCommissionPersonRole(String commissionPersonRole) {
		this.commissionPersonRole = commissionPersonRole;
	}

	/**
	 * @return the personName
	 */
	public String getPersonName() {
		return personName;
	}

	/**
	 * @param personName
	 *            the personName to set
	 */
	public void setPersonName(String personName) {
		this.personName = personName;
	}

	/**
	 * @return the updatedBy
	 */
	public String getUpdatedBy() {
		return updatedBy;
	}

	/**
	 * @param updatedBy
	 *            the updatedBy to set
	 */
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	/**
	 * @return the updatedDate
	 */
	public Timestamp getUpdatedDate() {
		return updatedDate;
	}

	/**
	 * @param updatedDate
	 *            the updatedDate to set
	 */
	public void setUpdatedDate(Timestamp updatedDate) {
		this.updatedDate = updatedDate;
	}

	/**
	 * @return the commissionPersonClassification
	 */
	public String getCommissionPersonClassification() {
		return commissionPersonClassification;
	}

	/**
	 * @param commissionPersonClassification
	 *            the commissionPersonClassification to set
	 */
	public void setCommissionPersonClassification(
			String commissionPersonClassification) {
		this.commissionPersonClassification = commissionPersonClassification;
	}

	public Integer getStatus()
	{
		return status;
	}

	public void setStatus(Integer status)
	{
		this.status = status;
	}

	public String getAdpCode()
	{
		return adpCode;
	}

	public void setAdpCode(String adpCode)
	{
		this.adpCode = adpCode;
	}

	/**
	 * @return the id
	 */
	public AnalyserCommisionPersonPK getId()
	{
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(AnalyserCommisionPersonPK id)
	{
		this.id = id;
	}
	/**
	 * @return the companyCode
	 */
	public String getCompanyCode() {
		return companyCode;
	}
	/**
	 * @param companyCode the companyCode to set
	 */
	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	
}