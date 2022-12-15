package com.disys.analyzer.model;

import java.io.Serializable;

import javax.persistence.*;

import java.sql.Timestamp;

/**
 * The persistent class for the AnalyserLiaison database table.
 * 
 */
@Entity(name = "Analyser_Liaison")
//@Table(schema="Analyser.dbo")
@NamedQueries(value = {
		@NamedQuery(name = "AnalyserLiaison.findAll", query = "SELECT a FROM Analyser_Liaison a"),
		@NamedQuery(name = "AnalyserLiaison.getLiaisonList", query = "Select a.liaisonName from Analyser_Liaison a "
				+ "where  a.orgId = (select u.orgId from User u where u.userId = :userId )  and a.status = 1"
				+ " ORDER BY a.liaisonName asc") })
public class AnalyserLiaison implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "LiaisonID")
	private String liaisonID;

	@Column(name = "LiaisonName")
	private String liaisonName;

	@Column(name = "ORG_ID")
	private Integer orgId;

	@Column(name = "Updated_By")
	private String updatedBy;

	@Column(name = "Updated_Date")
	private Timestamp updatedDate;
	
	@Column(name = "Status")
	private Integer status;
	
	@Column(name = "CompanyCode")
	private String companyCode;

	public AnalyserLiaison() {
	}

	/**
	 * @param orgId
	 */
	public AnalyserLiaison(Integer orgId) {
		super();
		this.orgId = orgId;
	}

	public String getLiaisonID() {
		return this.liaisonID;
	}

	public void setLiaisonID(String liaisonID) {
		this.liaisonID = liaisonID;
	}

	public String getLiaisonName() {
		return this.liaisonName;
	}

	public void setLiaisonName(String liaisonName) {
		this.liaisonName = liaisonName;
	}

	/**
	 * @return the orgId
	 */
	public Integer getOrgId() {
		return orgId;
	}

	/**
	 * @param orgId
	 *            the orgId to set
	 */
	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
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