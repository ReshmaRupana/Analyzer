package com.disys.analyzer.model;

import java.io.Serializable;

import javax.persistence.*;

/**
 * The persistent class for the CommissionAdjustments database table.
 * 
 */
@Entity
@Table(name = "CommissionAdjustments")//,schema="Analyser.dbo")
@NamedQuery(name = "CommissionAdjustment.findAll", query = "SELECT c FROM CommissionAdjustment c")
public class CommissionAdjustment implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private CommissionAdjustmentPK id;

	@Column(name = "AdjustmentType")
	private String adjustmentType;

	@Column(name = "Branch")
	private String branch;

	@Column(name = "NewProfit")
	private String newProfit;

	@Column(name = "OldProfit")
	private String oldProfit;

	/*@Column(name = "ParentId")
	private String parentId;*/

	public CommissionAdjustment() {
	}

	/**
	 * @return the id
	 */
	public CommissionAdjustmentPK getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(CommissionAdjustmentPK id) {
		this.id = id;
	}

	public String getAdjustmentType() {
		return this.adjustmentType;
	}

	public void setAdjustmentType(String adjustmentType) {
		this.adjustmentType = adjustmentType;
	}

	public String getBranch() {
		return this.branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public String getNewProfit() {
		return this.newProfit;
	}

	public void setNewProfit(String newProfit) {
		this.newProfit = newProfit;
	}

	public String getOldProfit() {
		return this.oldProfit;
	}

	public void setOldProfit(String oldProfit) {
		this.oldProfit = oldProfit;
	}
/*
	public String getParentId() {
		return this.parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}*/

}