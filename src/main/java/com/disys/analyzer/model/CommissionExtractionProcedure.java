/**
 * 
 */
package com.disys.analyzer.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Sajid
 * @since May 28, 2018
 *
 */
@Entity
@Table(schema = "Commission.dbo")
public class CommissionExtractionProcedure implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8036758856971691112L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "CommissionExtractionProcedureId")
	private Integer commissionExtractionProcedureId;

	@Column(name = "QueryName")
	private String queryName;

	@Column(name = "ExcelSheetName")
	private String excelSheetName;

	@Column(name = "QuerySequenceNumber")
	private Integer querySequenceNumber;

	@Column(name = "ProcedureName")
	private String procedureName;

	/**
	 * @return the commissionExtractionProcedureId
	 */
	public Integer getCommissionExtractionProcedureId() {
		return commissionExtractionProcedureId;
	}

	/**
	 * @param commissionExtractionProcedureId
	 *            the commissionExtractionProcedureId to set
	 */
	public void setCommissionExtractionProcedureId(
			Integer commissionExtractionProcedureId) {
		this.commissionExtractionProcedureId = commissionExtractionProcedureId;
	}

	/**
	 * @return the queryName
	 */
	public String getQueryName() {
		return queryName;
	}

	/**
	 * @param queryName
	 *            the queryName to set
	 */
	public void setQueryName(String queryName) {
		this.queryName = queryName;
	}

	/**
	 * @return the excelSheetName
	 */
	public String getExcelSheetName() {
		return excelSheetName;
	}

	/**
	 * @param excelSheetName
	 *            the excelSheetName to set
	 */
	public void setExcelSheetName(String excelSheetName) {
		this.excelSheetName = excelSheetName;
	}

	/**
	 * @return the querySequenceNumber
	 */
	public Integer getQuerySequenceNumber() {
		return querySequenceNumber;
	}

	/**
	 * @param querySequenceNumber
	 *            the querySequenceNumber to set
	 */
	public void setQuerySequenceNumber(Integer querySequenceNumber) {
		this.querySequenceNumber = querySequenceNumber;
	}

	/**
	 * @return the procedureName
	 */
	public String getProcedureName() {
		return procedureName;
	}

	/**
	 * @param procedureName
	 *            the procedureName to set
	 */
	public void setProcedureName(String procedureName) {
		this.procedureName = procedureName;
	}

}