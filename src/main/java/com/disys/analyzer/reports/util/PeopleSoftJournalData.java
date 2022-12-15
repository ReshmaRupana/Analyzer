/**
 * 
 */
package com.disys.analyzer.reports.util;

import java.io.Serializable;

/**
 * @author Sajid
 * 
 */
public class PeopleSoftJournalData implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int peopleSoftJournalDataId; // PK
	private int accountingPeriod;
	private int fiscalYear;
	private String journalId;
	private String journalDate;
	private int journalLine;
	private String account;
	private String chartField1;
	private String projectId;
	private String activityId;
	private String resourceType;
	private String resourceCategory;
	private String analysisType;
	private double monetaryAmount;
	private String lineDescription;
	private String source;
	private String journalLineRef_VoucherId;
	private String transactionRefNumber;
	private String departmentId;
	private String operatingUnit;
	private String businessUnit;
	private String journalLineSource;
	private int journalTotalLines;
	private String postingDate;
	private String journalDescription;
	private String systemSource;
	private String description254;
	private int commissionProcessId;

	public PeopleSoftJournalData() {
	}

	public int getPeopleSoftJournalDataId() {
		return peopleSoftJournalDataId;
	}

	public void setPeopleSoftJournalDataId(int peopleSoftJournalDataId) {
		this.peopleSoftJournalDataId = peopleSoftJournalDataId;
	}

	public int getAccountingPeriod() {
		return accountingPeriod;
	}

	public void setAccountingPeriod(int accountingPeriod) {
		this.accountingPeriod = accountingPeriod;
	}

	public int getFiscalYear() {
		return fiscalYear;
	}

	public void setFiscalYear(int fiscalYear) {
		this.fiscalYear = fiscalYear;
	}

	public String getJournalId() {
		return journalId;
	}

	public void setJournalId(String journalId) {
		this.journalId = journalId;
	}

	public String getJournalDate() {
		return journalDate;
	}

	public void setJournalDate(String journalDate) {
		this.journalDate = journalDate;
	}

	public int getJournalLine() {
		return journalLine;
	}

	public void setJournalLine(int journalLine) {
		this.journalLine = journalLine;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getChartField1() {
		return chartField1;
	}

	public void setChartField1(String chartField1) {
		this.chartField1 = chartField1;
	}

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public String getActivityId() {
		return activityId;
	}

	public void setActivityId(String activityId) {
		this.activityId = activityId;
	}

	public String getResourceType() {
		return resourceType;
	}

	public void setResourceType(String resourceType) {
		this.resourceType = resourceType;
	}

	public String getResourceCategory() {
		return resourceCategory;
	}

	public void setResourceCategory(String resourceCategory) {
		this.resourceCategory = resourceCategory;
	}

	public String getAnalysisType() {
		return analysisType;
	}

	public void setAnalysisType(String analysisType) {
		this.analysisType = analysisType;
	}

	public double getMonetaryAmount() {
		return monetaryAmount;
	}

	public void setMonetaryAmount(double monetaryAmount) {
		this.monetaryAmount = monetaryAmount;
	}

	public String getLineDescription() {
		return lineDescription;
	}

	public void setLineDescription(String lineDescription) {
		this.lineDescription = lineDescription;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getJournalLineRef_VoucherId() {
		return journalLineRef_VoucherId;
	}

	public void setJournalLineRef_VoucherId(String journalLineRef_VoucherId) {
		this.journalLineRef_VoucherId = journalLineRef_VoucherId;
	}

	public String getTransactionRefNumber() {
		return transactionRefNumber;
	}

	public void setTransactionRefNumber(String transactionRefNumber) {
		this.transactionRefNumber = transactionRefNumber;
	}

	public String getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}

	public String getOperatingUnit() {
		return operatingUnit;
	}

	public void setOperatingUnit(String operatingUnit) {
		this.operatingUnit = operatingUnit;
	}

	public String getBusinessUnit() {
		return businessUnit;
	}

	public void setBusinessUnit(String businessUnit) {
		this.businessUnit = businessUnit;
	}

	public String getJournalLineSource() {
		return journalLineSource;
	}

	public void setJournalLineSource(String journalLineSource) {
		this.journalLineSource = journalLineSource;
	}

	public int getJournalTotalLines() {
		return journalTotalLines;
	}

	public void setJournalTotalLines(int journalTotalLines) {
		this.journalTotalLines = journalTotalLines;
	}

	public String getPostingDate() {
		return postingDate;
	}

	public void setPostingDate(String postingDate) {
		this.postingDate = postingDate;
	}

	public String getJournalDescription() {
		return journalDescription;
	}

	public void setJournalDescription(String journalDescription) {
		this.journalDescription = journalDescription;
	}

	public String getSystemSource() {
		return systemSource;
	}

	public void setSystemSource(String systemSource) {
		this.systemSource = systemSource;
	}

	public String getDescription254() {
		return description254;
	}

	public void setDescription254(String description254) {
		this.description254 = description254;
	}

	public int getCommissionProcessId() {
		return commissionProcessId;
	}

	public void setCommissionProcessId(int commissionProcessId) {
		this.commissionProcessId = commissionProcessId;
	}

	public void printOnConsole1() {
		System.out.println("peopleSoftJournalDataId = "
				+ peopleSoftJournalDataId);
		System.out.println("accountingPeriod = " + accountingPeriod);
		System.out.println("fiscalYear = " + fiscalYear);
		System.out.println("journalId = " + journalId);
		System.out.println("journalDate = " + journalDate);
		System.out.println("journalLine = " + journalLine);
		System.out.println("account = " + account);
		System.out.println("chartField1 = " + chartField1);
		System.out.println("projectId = " + projectId);
		System.out.println("activityId = " + activityId);
		System.out.println("resourceType = " + resourceType);
		System.out.println("resourceCategory = " + resourceCategory);
		System.out.println("analysisType = " + analysisType);
		System.out.println("monetaryAmount = " + monetaryAmount);
		System.out.println("lineDescription = " + lineDescription);
		System.out.println("source = " + source);
		System.out.println("journalLineRef_VoucherId = "
				+ journalLineRef_VoucherId);
		System.out.println("transactionRefNumber = " + transactionRefNumber);
		System.out.println("departmentId = " + departmentId);
		System.out.println("operatingUnit = " + operatingUnit);
		System.out.println("businessUnit = " + businessUnit);
		System.out.println("journalLineSource = " + journalLineSource);
		System.out.println("postingDate = " + postingDate);
		System.out.println("journalDescription = " + journalDescription);
		System.out.println("systemSource = " + systemSource);
		System.out.println("description254 = " + description254);
		System.out.println("commissionProcessId = " + commissionProcessId);
	}

}