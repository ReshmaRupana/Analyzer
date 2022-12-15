package com.disys.analyzer.model;

import java.io.Serializable;

import javax.persistence.*;

/**
 * The persistent class for the AnalyzerAdjustments database table.
 * 
 */
@Entity
@Table(name = "AnalyzerAdjustments")//,schema="Analyser.dbo")
@NamedQuery(name = "AnalyzerAdjustment.findAll", query = "SELECT a FROM AnalyzerAdjustment a")
public class AnalyzerAdjustment implements Serializable {
	private static final long serialVersionUID = 1L;

	/*
	 * @Id
	 * 
	 * @GeneratedValue(strategy=GenerationType.AUTO)
	 * 
	 * @Column(name="AnalyserAdjustmentsId") private int analyserAdjustmentsId;
	 */

	@EmbeddedId
	private AnalyzerAdjustmentPK id;

	@Column(name = "AdjustmentType")
	private String adjustmentType;

	@Column(name = "Branch")
	private String branch;

	@Column(name = "NewProfit")
	private String newProfit;

	@Column(name = "OldProfit")
	private String oldProfit;

	public AnalyzerAdjustment() {
	}

	/**
	 * @return the id
	 */
	public AnalyzerAdjustmentPK getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(AnalyzerAdjustmentPK id) {
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

}