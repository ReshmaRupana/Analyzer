/**
 * 
 */
package com.disys.analyzer.model.dto;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Sajid
 * 
 *
 */
@Entity
@Table(name="ChartData")
public class ChartDataMapping implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	private Integer fiscalYear;
	private Integer accountingPeriod;
	private String payableCommissionType;
	private Integer commissionPersonEmplIdOrOprId;
	private Double total;
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
	 * @return the payableCommissionType
	 */
	public String getPayableCommissionType() {
		return payableCommissionType;
	}
	/**
	 * @param payableCommissionType the payableCommissionType to set
	 */
	public void setPayableCommissionType(String payableCommissionType) {
		this.payableCommissionType = payableCommissionType;
	}
	/**
	 * @return the commissionPersonEmplIdOrOprId
	 */
	public Integer getCommissionPersonEmplIdOrOprId() {
		return commissionPersonEmplIdOrOprId;
	}
	/**
	 * @param commissionPersonEmplIdOrOprId the commissionPersonEmplIdOrOprId to set
	 */
	public void setCommissionPersonEmplIdOrOprId(
			Integer commissionPersonEmplIdOrOprId) {
		this.commissionPersonEmplIdOrOprId = commissionPersonEmplIdOrOprId;
	}
	/**
	 * @return the total
	 */
	public Double getTotal() {
		return total;
	}
	/**
	 * @param total the total to set
	 */
	public void setTotal(Double total) {
		this.total = total;
	}
}