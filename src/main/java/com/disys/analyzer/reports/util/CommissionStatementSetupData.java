/**
 * 
 */
package com.disys.analyzer.reports.util;

import java.io.Serializable;

/**
 * @author Sajid
 * 
 */
public class CommissionStatementSetupData implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6753751307234907013L;
	private String companyName;
	private String reportHeader;
	private String commissionPersonEmplId;
	private String commissionPersonEmplIdLabel;
	private String commissionPersonName;
	private String commissionPersonNameLabel;
	private String commissionPeriod;
	private String commissionPeriodLabel;
	private double currentCommission;
	private String currentCommissionLabel;
	private double ytdCommission;
	private String ytdCommissionLabel;
	private String subHeader1; // COMMISSION TYPE
	private String subHeader2; // YTD
	private String subHeader3;// BALANCE
	private String subHeader4;// CURRENT
	private String headerTableColumn1Label;
	private String headerTableColumn2Label;
	private String headerTableColumn3Label;
	private String headerTableColumn4Label;
	private String legendLabel;
	private String detailTableColumnLabel1; // Consultant Name
	private String detailTableColumnLabel2; // Customer Name
	private String detailTableColumnLabel3; // Hours
	private String detailTableColumnLabel4; // Revenue
	private String detailTableColumnLabel5; // Cost
	private String detailTableColumnLabel6; // G&A
	private String detailTableColumnLabel7; // GP
	private String detailTableColumnLabel8; // GP%
	private String detailTableColumnLabel9; // PRF
	private String detailTableColumnLabel10; // Comm%
	private String detailTableColumnLabel11; // AF
	private String detailTableColumnLabel12; // Comm
	private String imageName;
	private double staffingAndSolutionsCommissionYtd; //
	private double staffingAndSolutionsCommissionBalance; //
	private double staffingAndSolutionsCommissionCurrent; //
	private String staffingAndSolutionsCommissionLabel; //
	private double servicesCommissionYtd; //
	private double servicesCommissionBalance; //
	private double servicesCommissionCurrent; //
	private String servicesCommissionLabel; //
	private double totalCommissionYtd; //
	private double totalCommissionBalance; //
	private double totalCommissionCurrent; //
	private String totalCommissionLabel; //
	// -- New added Col. 20170509
	private String detailTableColumnLabel4B; // Revenue(Commissionable Rev)
	private String detailTableColumnLabel13; // CP

	public CommissionStatementSetupData() {
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getReportHeader() {
		return reportHeader;
	}

	public void setReportHeader(String reportHeader) {
		this.reportHeader = reportHeader;
	}

	public String getCommissionPersonEmplId() {
		return commissionPersonEmplId;
	}

	public void setCommissionPersonEmplId(String commissionPersonEmplId) {
		this.commissionPersonEmplId = commissionPersonEmplId;
	}

	public String getCommissionPersonEmplIdLabel() {
		return commissionPersonEmplIdLabel;
	}

	public void setCommissionPersonEmplIdLabel(
			String commissionPersonEmplIdLabel) {
		this.commissionPersonEmplIdLabel = commissionPersonEmplIdLabel;
	}

	public String getCommissionPersonName() {
		return commissionPersonName;
	}

	public void setCommissionPersonName(String commissionPersonName) {
		this.commissionPersonName = commissionPersonName;
	}

	public String getCommissionPersonNameLabel() {
		return commissionPersonNameLabel;
	}

	public void setCommissionPersonNameLabel(String commissionPersonNameLabel) {
		this.commissionPersonNameLabel = commissionPersonNameLabel;
	}

	public String getCommissionPeriod() {
		return commissionPeriod;
	}

	public void setCommissionPeriod(String commissionPeriod) {
		this.commissionPeriod = commissionPeriod;
	}

	public String getCommissionPeriodLabel() {
		return commissionPeriodLabel;
	}

	public void setCommissionPeriodLabel(String commissionPeriodLabel) {
		this.commissionPeriodLabel = commissionPeriodLabel;
	}

	public double getCurrentCommission() {
		return currentCommission;
	}

	public void setCurrentCommission(double currentCommission) {
		this.currentCommission = currentCommission;
	}

	public String getCurrentCommissionLabel() {
		return currentCommissionLabel;
	}

	public void setCurrentCommissionLabel(String currentCommissionLabel) {
		this.currentCommissionLabel = currentCommissionLabel;
	}

	public double getYtdCommission() {
		return ytdCommission;
	}

	public void setYtdCommission(double ytdCommission) {
		this.ytdCommission = ytdCommission;
	}

	public String getYtdCommissionLabel() {
		return ytdCommissionLabel;
	}

	public void setYtdCommissionLabel(String ytdCommissionLabel) {
		this.ytdCommissionLabel = ytdCommissionLabel;
	}

	public String getSubHeader1() {
		return subHeader1;
	}

	public void setSubHeader1(String subHeader1) {
		this.subHeader1 = subHeader1;
	}

	public String getSubHeader2() {
		return subHeader2;
	}

	public void setSubHeader2(String subHeader2) {
		this.subHeader2 = subHeader2;
	}

	public String getSubHeader3() {
		return subHeader3;
	}

	public void setSubHeader3(String subHeader3) {
		this.subHeader3 = subHeader3;
	}

	public String getSubHeader4() {
		return subHeader4;
	}

	public void setSubHeader4(String subHeader4) {
		this.subHeader4 = subHeader4;
	}

	public String getHeaderTableColumn1Label() {
		return headerTableColumn1Label;
	}

	public void setHeaderTableColumn1Label(String headerTableColumn1Label) {
		this.headerTableColumn1Label = headerTableColumn1Label;
	}

	public String getHeaderTableColumn2Label() {
		return headerTableColumn2Label;
	}

	public void setHeaderTableColumn2Label(String headerTableColumn2Label) {
		this.headerTableColumn2Label = headerTableColumn2Label;
	}

	public String getHeaderTableColumn3Label() {
		return headerTableColumn3Label;
	}

	public void setHeaderTableColumn3Label(String headerTableColumn3Label) {
		this.headerTableColumn3Label = headerTableColumn3Label;
	}

	public String getHeaderTableColumn4Label() {
		return headerTableColumn4Label;
	}

	public void setHeaderTableColumn4Label(String headerTableColumn4Label) {
		this.headerTableColumn4Label = headerTableColumn4Label;
	}

	public String getLegendLabel() {
		return legendLabel;
	}

	public void setLegendLabel(String legendLabel) {
		this.legendLabel = legendLabel;
	}

	public String getDetailTableColumnLabel1() {
		return detailTableColumnLabel1;
	}

	public void setDetailTableColumnLabel1(String detailTableColumnLabel1) {
		this.detailTableColumnLabel1 = detailTableColumnLabel1;
	}

	public String getDetailTableColumnLabel2() {
		return detailTableColumnLabel2;
	}

	public void setDetailTableColumnLabel2(String detailTableColumnLabel2) {
		this.detailTableColumnLabel2 = detailTableColumnLabel2;
	}

	public String getDetailTableColumnLabel3() {
		return detailTableColumnLabel3;
	}

	public void setDetailTableColumnLabel3(String detailTableColumnLabel3) {
		this.detailTableColumnLabel3 = detailTableColumnLabel3;
	}

	public String getDetailTableColumnLabel4() {
		return detailTableColumnLabel4;
	}

	public void setDetailTableColumnLabel4(String detailTableColumnLabel4) {
		this.detailTableColumnLabel4 = detailTableColumnLabel4;
	}

	public String getDetailTableColumnLabel5() {
		return detailTableColumnLabel5;
	}

	public void setDetailTableColumnLabel5(String detailTableColumnLabel5) {
		this.detailTableColumnLabel5 = detailTableColumnLabel5;
	}

	public String getDetailTableColumnLabel6() {
		return detailTableColumnLabel6;
	}

	public void setDetailTableColumnLabel6(String detailTableColumnLabel6) {
		this.detailTableColumnLabel6 = detailTableColumnLabel6;
	}

	public String getDetailTableColumnLabel7() {
		return detailTableColumnLabel7;
	}

	public void setDetailTableColumnLabel7(String detailTableColumnLabel7) {
		this.detailTableColumnLabel7 = detailTableColumnLabel7;
	}

	public String getDetailTableColumnLabel8() {
		return detailTableColumnLabel8;
	}

	public void setDetailTableColumnLabel8(String detailTableColumnLabel8) {
		this.detailTableColumnLabel8 = detailTableColumnLabel8;
	}

	public String getDetailTableColumnLabel9() {
		return detailTableColumnLabel9;
	}

	public void setDetailTableColumnLabel9(String detailTableColumnLabel9) {
		this.detailTableColumnLabel9 = detailTableColumnLabel9;
	}

	public String getDetailTableColumnLabel10() {
		return detailTableColumnLabel10;
	}

	public void setDetailTableColumnLabel10(String detailTableColumnLabel10) {
		this.detailTableColumnLabel10 = detailTableColumnLabel10;
	}

	public String getDetailTableColumnLabel11() {
		return detailTableColumnLabel11;
	}

	public void setDetailTableColumnLabel11(String detailTableColumnLabel11) {
		this.detailTableColumnLabel11 = detailTableColumnLabel11;
	}

	public String getDetailTableColumnLabel12() {
		return detailTableColumnLabel12;
	}

	public void setDetailTableColumnLabel12(String detailTableColumnLabel12) {
		this.detailTableColumnLabel12 = detailTableColumnLabel12;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public double getStaffingAndSolutionsCommissionYtd() {
		return staffingAndSolutionsCommissionYtd;
	}

	public void setStaffingAndSolutionsCommissionYtd(
			double staffingAndSolutionsCommissionYtd) {
		this.staffingAndSolutionsCommissionYtd = staffingAndSolutionsCommissionYtd;
	}

	public double getStaffingAndSolutionsCommissionBalance() {
		return staffingAndSolutionsCommissionBalance;
	}

	public void setStaffingAndSolutionsCommissionBalance(
			double staffingAndSolutionsCommissionBalance) {
		this.staffingAndSolutionsCommissionBalance = staffingAndSolutionsCommissionBalance;
	}

	public double getStaffingAndSolutionsCommissionCurrent() {
		return staffingAndSolutionsCommissionCurrent;
	}

	public void setStaffingAndSolutionsCommissionCurrent(
			double staffingAndSolutionsCommissionCurrent) {
		this.staffingAndSolutionsCommissionCurrent = staffingAndSolutionsCommissionCurrent;
	}

	public String getStaffingAndSolutionsCommissionLabel() {
		return staffingAndSolutionsCommissionLabel;
	}

	public void setStaffingAndSolutionsCommissionLabel(
			String staffingAndSolutionsCommissionLabel) {
		this.staffingAndSolutionsCommissionLabel = staffingAndSolutionsCommissionLabel;
	}

	public double getServicesCommissionYtd() {
		return servicesCommissionYtd;
	}

	public void setServicesCommissionYtd(double servicesCommissionYtd) {
		this.servicesCommissionYtd = servicesCommissionYtd;
	}

	public double getServicesCommissionBalance() {
		return servicesCommissionBalance;
	}

	public void setServicesCommissionBalance(double servicesCommissionBalance) {
		this.servicesCommissionBalance = servicesCommissionBalance;
	}

	public double getServicesCommissionCurrent() {
		return servicesCommissionCurrent;
	}

	public void setServicesCommissionCurrent(double servicesCommissionCurrent) {
		this.servicesCommissionCurrent = servicesCommissionCurrent;
	}

	public String getServicesCommissionLabel() {
		return servicesCommissionLabel;
	}

	public void setServicesCommissionLabel(String servicesCommissionLabel) {
		this.servicesCommissionLabel = servicesCommissionLabel;
	}

	public double getTotalCommissionYtd() {
		return totalCommissionYtd;
	}

	public void setTotalCommissionYtd(double totalCommissionYtd) {
		this.totalCommissionYtd = totalCommissionYtd;
	}

	public double getTotalCommissionBalance() {
		return totalCommissionBalance;
	}

	public void setTotalCommissionBalance(double totalCommissionBalance) {
		this.totalCommissionBalance = totalCommissionBalance;
	}

	public double getTotalCommissionCurrent() {
		return totalCommissionCurrent;
	}

	public void setTotalCommissionCurrent(double totalCommissionCurrent) {
		this.totalCommissionCurrent = totalCommissionCurrent;
	}

	public String getTotalCommissionLabel() {
		return totalCommissionLabel;
	}

	public void setTotalCommissionLabel(String totalCommissionLabel) {
		this.totalCommissionLabel = totalCommissionLabel;
	}

	// -- New added Col. 20170509

	public String getDetailTableColumnLabel4B() {
		return detailTableColumnLabel4B;
	}

	public void setDetailTableColumnLabel4B(String detailTableColumnLabel4B) {
		this.detailTableColumnLabel4B = detailTableColumnLabel4B;
	}

	// -- New added Col. 20170509

	public String getDetailTableColumnLabel13() {
		return detailTableColumnLabel13;
	}

	public void setDetailTableColumnLabel13(String detailTableColumnLabel13) {
		this.detailTableColumnLabel13 = detailTableColumnLabel13;
	}

	public void printOnConsole() {
		System.out.println("companyName = " + companyName);
		System.out.println("reportHeader = " + reportHeader);
		System.out
				.println("commissionPersonEmplId = " + commissionPersonEmplId);
		System.out.println("commissionPersonEmplIdLabel = "
				+ commissionPersonEmplIdLabel);
		System.out.println("commissionPersonName = " + commissionPersonName);
		System.out.println("commissionPersonNameLabel = "
				+ commissionPersonNameLabel);
		System.out.println("commissionPeriod = " + commissionPeriod);
		System.out.println("commissionPeriodLabel = " + commissionPeriodLabel);
		System.out.println("currentCommission = " + currentCommission);
		System.out
				.println("currentCommissionLabel = " + currentCommissionLabel);
		System.out.println("ytdCommission = " + ytdCommission);
		System.out.println("ytdCommissionLabel = " + ytdCommissionLabel);
		System.out.println("subHeader1 = " + subHeader1);
		System.out.println("subHeader2 = " + subHeader2);
		System.out.println("subHeader3 = " + subHeader3);
		System.out.println("subHeader4 = " + subHeader4);
		System.out.println("headerTableColumn1Label = "
				+ headerTableColumn1Label);
		System.out.println("headerTableColumn2Label = "
				+ headerTableColumn2Label);
		System.out.println("headerTableColumn3Label = "
				+ headerTableColumn3Label);
		System.out.println("headerTableColumn4Label = "
				+ headerTableColumn4Label);
		System.out.println("legendLabel = " + legendLabel);
		System.out.println("detailTableColumnLabel1 = "
				+ detailTableColumnLabel1);
		System.out.println("detailTableColumnLabel2 = "
				+ detailTableColumnLabel2);
		System.out.println("detailTableColumnLabel3 = "
				+ detailTableColumnLabel3);
		System.out.println("detailTableColumnLabel4 = "
				+ detailTableColumnLabel4);
		System.out.println("detailTableColumnLabel5 = "
				+ detailTableColumnLabel5);
		System.out.println("detailTableColumnLabel6 = "
				+ detailTableColumnLabel6);
		System.out.println("detailTableColumnLabel7 = "
				+ detailTableColumnLabel7);
		System.out.println("detailTableColumnLabel8 = "
				+ detailTableColumnLabel8);
		System.out.println("detailTableColumnLabel9 = "
				+ detailTableColumnLabel9);
		System.out.println("detailTableColumnLabel10 = "
				+ detailTableColumnLabel10);
		System.out.println("detailTableColumnLabel11 = "
				+ detailTableColumnLabel11);
		System.out.println("detailTableColumnLabel12 = "
				+ detailTableColumnLabel12);
		System.out.println("imageName = " + imageName);
		System.out.println("staffingAndSolutionsCommissionYtd = "
				+ staffingAndSolutionsCommissionYtd);
		System.out.println("staffingAndSolutionsCommissionBalance = "
				+ staffingAndSolutionsCommissionBalance);
		System.out.println("staffingAndSolutionsCommissionCurrent = "
				+ staffingAndSolutionsCommissionCurrent);
		System.out.println("staffingAndSolutionsCommissionLabel = "
				+ staffingAndSolutionsCommissionLabel);
		System.out.println("servicesCommissionYtd = " + servicesCommissionYtd);
		System.out.println("servicesCommissionBalance = "
				+ servicesCommissionBalance);
		System.out.println("servicesCommissionCurrent = "
				+ servicesCommissionCurrent);
		System.out.println("servicesCommissionLabel = "
				+ servicesCommissionLabel);
		System.out.println("totalCommissionYtd = " + totalCommissionYtd);
		System.out
				.println("totalCommissionBalance = " + totalCommissionBalance);
		System.out
				.println("totalCommissionCurrent = " + totalCommissionCurrent);
		System.out.println("totalCommissionLabel = " + totalCommissionLabel);
		// -- New added Col. 20170509
		System.out.println("detailTableColumnLabel4B = "
				+ detailTableColumnLabel4B);
		System.out.println("detailTableColumnLabel13 = "
				+ detailTableColumnLabel13);

	}
}