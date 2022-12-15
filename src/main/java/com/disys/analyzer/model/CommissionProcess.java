/**
 * 
 */
package com.disys.analyzer.model;


import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
/**
 * @author Sajid
 * @since May 26, 2018
 *
 */
@Entity
@Table(schema="Commission.dbo")
public class CommissionProcess implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8036758856971691112L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "CommissionProcessId")
	private Integer commissionProcessId;

//	@Column(name = "AccountingPeriod")
	private Integer accountingPeriod;
	
//	@Column(name = "FiscalYear")
	private Integer fiscalYear;
	
//	@Column(name = "CommissionMode")
	private String commissionMode;

//	@Column(name = "ExecutedBy")
	private String executedBy;
	
//	@Column(name = "ExecutionDate")
	private Timestamp executionDate;
	
	private Timestamp completionDate;
	
	private Double completionPercentage;

	/**
	 * @return the accountingPeriod
	 */
	public Integer getAccountingPeriod() {
		return accountingPeriod;
	}

	/**
	 * @param accountingPeriod the accountingPeriod to set
	 */
	public void setAccountingPeriod(Integer accountingPeriod) {
		this.accountingPeriod = accountingPeriod;
	}

	/**
	 * @return the fiscalYear
	 */
	public Integer getFiscalYear() {
		return fiscalYear;
	}

	/**
	 * @param fiscalYear the fiscalYear to set
	 */
	public void setFiscalYear(Integer fiscalYear) {
		this.fiscalYear = fiscalYear;
	}

	/**
	 * @return the commissionMode
	 */
	public String getCommissionMode() {
		return commissionMode;
	}

	/**
	 * @param commissionMode the commissionMode to set
	 */
	public void setCommissionMode(String commissionMode) {
		this.commissionMode = commissionMode;
	}

	/**
	 * @return the executedBy
	 */
	public String getExecutedBy() {
		return executedBy;
	}

	/**
	 * @param executedBy the executedBy to set
	 */
	public void setExecutedBy(String executedBy) {
		this.executedBy = executedBy;
	}

	/**
	 * @return the executionDate
	 */
	public Timestamp getExecutionDate() {
		return executionDate;
	}

	/**
	 * @param executionDate the executionDate to set
	 */
	public void setExecutionDate(Timestamp executionDate) {
		this.executionDate = executionDate;
	}

	/**
	 * @return the commissionProcessId
	 */
	public Integer getCommissionProcessId() {
		return commissionProcessId;
	}

	/**
	 * @param commissionProcessId the commissionProcessId to set
	 */
	public void setCommissionProcessId(Integer commissionProcessId) {
		this.commissionProcessId = commissionProcessId;
	}

	/**
	 * @return the completionDate
	 */
	public Timestamp getCompletionDate() {
		return completionDate;
	}

	/**
	 * @param completionDate the completionDate to set
	 */
	public void setCompletionDate(Timestamp completionDate) {
		this.completionDate = completionDate;
	}

	/**
	 * @return the completionPercentage
	 */
	public Double getCompletionPercentage() {
		return completionPercentage;
	}

	/**
	 * @param completionPercentage the completionPercentage to set
	 */
	public void setCompletionPercentage(Double completionPercentage) {
		this.completionPercentage = completionPercentage;
	}
	
}