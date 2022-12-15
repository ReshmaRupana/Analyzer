/**
 * 
 */
package com.disys.analyzer.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Sajid
 * @
 */
@Entity
//@Table(schema="Analyser.dbo")
@Table(name="SickLeaveCost")//,schema="Analyser.dbo")
public class SickLeaveCost implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3286164223994095623L;

	@Id
	@Column(name = "ZipCode")
	private String zipCode;

	@Column(name = "WorksiteStateCode")
	private String worksiteStateCode;
	@Column(name = "worksiteStateDescription")
	private String worksiteStateDescription;
	@Column(name = "CityName")
	private String cityName;
	@Column(name = "SickHoursCost")
	private Double sickHoursCost;
	
	@Column(name = "PlaceName")
	private String placeName;
	
	@Column(name = "StateAbbreviation")
	private String stateAbbreviation;
	
	@Column(name = "HourlySalaryRate")
	private String hourlySalaryRate;
	
	@Column(name = "AnnualCap")
	private Integer annualCap;
	
	@Column(name = "PTO")
	private String pto;
	
	@Column(name = "IsPTOEligible")
	private String isPtoEligible;
	
	@Column(name = "PTOLimitToOverrideSick")
	private Double ptoLimitToOverrideSick;
	
	@Column(name = "SickLeaveSource")
	private String sickLeaveSource;
	
	
	public SickLeaveCost()
	{
		// TODO Auto-generated constructor stub
	}
	
	
	public SickLeaveCost(SickLeaveCost sickLeaveCost)
	{
		this.zipCode = sickLeaveCost.getZipCode();
		this.worksiteStateCode = sickLeaveCost.getWorksiteStateCode();
		this.worksiteStateDescription = sickLeaveCost.getWorksiteStateDescription();
		this.cityName = sickLeaveCost.getCityName();
		this.sickHoursCost = sickLeaveCost.getSickHoursCost();
		this.placeName = sickLeaveCost.getPlaceName();
		this.stateAbbreviation = sickLeaveCost.getStateAbbreviation();
		this.hourlySalaryRate = sickLeaveCost.getHourlySalaryRate();
		this.annualCap = sickLeaveCost.getAnnualCap();
		this.pto = sickLeaveCost.getPto();
		this.isPtoEligible = sickLeaveCost.getIsPtoEligible();
		this.ptoLimitToOverrideSick = sickLeaveCost.getPtoLimitToOverrideSick();
		this.sickLeaveSource = sickLeaveCost.getSickLeaveSource();
	}


	/**
	 * @return the zipCode
	 */
	public String getZipCode() {
		return zipCode;
	}
	/**
	 * @param zipCode the zipCode to set
	 */
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	/**
	 * @return the worksiteStateCode
	 */
	public String getWorksiteStateCode() {
		return worksiteStateCode;
	}
	/**
	 * @param worksiteStateCode the worksiteStateCode to set
	 */
	public void setWorksiteStateCode(String worksiteStateCode) {
		this.worksiteStateCode = worksiteStateCode;
	}
	/**
	 * @return the worksiteStateDescription
	 */
	public String getWorksiteStateDescription() {
		return worksiteStateDescription;
	}
	/**
	 * @param worksiteStateDescription the worksiteStateDescription to set
	 */
	public void setWorksiteStateDescription(String worksiteStateDescription) {
		this.worksiteStateDescription = worksiteStateDescription;
	}
	/**
	 * @return the cityName
	 */
	public String getCityName() {
		return cityName;
	}
	/**
	 * @param cityName the cityName to set
	 */
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	/**
	 * @return the sickHoursCost
	 */
	public Double getSickHoursCost() {
		return sickHoursCost;
	}
	/**
	 * @param sickHoursCost the sickHoursCost to set
	 */
	public void setSickHoursCost(Double sickHoursCost) {
		this.sickHoursCost = sickHoursCost;
	}
	public String getPlaceName()
	{
		return placeName;
	}
	public void setPlaceName(String placeName)
	{
		this.placeName = placeName;
	}
	public String getStateAbbreviation()
	{
		return stateAbbreviation;
	}
	public void setStateAbbreviation(String stateAbbreviation)
	{
		this.stateAbbreviation = stateAbbreviation;
	}
	public String getHourlySalaryRate()
	{
		return hourlySalaryRate;
	}
	public void setHourlySalaryRate(String hourlySalaryRate)
	{
		this.hourlySalaryRate = hourlySalaryRate;
	}
	public Integer getAnnualCap()
	{
		return annualCap;
	}
	public void setAnnualCap(Integer annualCap)
	{
		this.annualCap = annualCap;
	}
	public String getPto()
	{
		return pto;
	}
	public void setPto(String pto)
	{
		this.pto = pto;
	}
	public String getIsPtoEligible()
	{
		return isPtoEligible;
	}
	public void setIsPtoEligible(String isPtoEligible)
	{
		this.isPtoEligible = isPtoEligible;
	}
	public Double getPtoLimitToOverrideSick()
	{
		return ptoLimitToOverrideSick;
	}
	public void setPtoLimitToOverrideSick(Double ptoLimitToOverrideSick)
	{
		this.ptoLimitToOverrideSick = ptoLimitToOverrideSick;
	}
	public String getSickLeaveSource() {
		return sickLeaveSource;
	}
	public void setSickLeaveSource(String sickLeaveSource) {
		this.sickLeaveSource = sickLeaveSource;
	}
	
	
}
