package com.disys.analyzer.model;

import java.io.Serializable;

import javax.persistence.*;

import java.math.BigDecimal;
import java.util.List;

/**
 * The persistent class for the Organization database table.
 * 
 */
@Entity
@NamedQuery(name = "Organization.findAll", query = "SELECT o FROM Organization o")
//@Table(schema="Analyser.dbo")
public class Organization implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "Org_ID")
	private Integer orgId;

	@Column(name = "Address_1")
	private String address1;

	@Column(name = "Address_2")
	private String address2;

	@Column(name = "City")
	private String city;

	@Column(name = "Default_Inventory_Email")
	private String defaultInventoryEmail;

	@Column(name = "Download_Task_Days")
	private Integer downloadTaskDays;

	@Column(name = "Download_Task_Type")
	private Integer downloadTaskType;

	@Column(name = "No_Of_Devices")
	private BigDecimal noOfDevices;

	@Column(name = "Org_lat")
	private String orgLat;

	@Column(name = "Org_long")
	private String orgLong;

	@Column(name = "Org_Name")
	private String orgName;

	@Column(name = "Passcode")
	private String passcode;

	@Column(name = "Primary_Email")
	private String primaryEmail;

	@Column(name = "State")
	private String state;

	@Column(name = "TimeZone")
	private Integer timeZone;

	@Column(name = "Zip")
	private String zip;

	
	/*
	 * //bi-directional many-to-one association to Role
	 * 
	 * @OneToMany(mappedBy="organization") private List<Role> roles;
	 */

	public Organization() {
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
	 * @return the address1
	 */
	public String getAddress1() {
		return address1;
	}

	/**
	 * @param address1
	 *            the address1 to set
	 */
	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	/**
	 * @return the address2
	 */
	public String getAddress2() {
		return address2;
	}

	/**
	 * @param address2
	 *            the address2 to set
	 */
	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city
	 *            the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @return the defaultInventoryEmail
	 */
	public String getDefaultInventoryEmail() {
		return defaultInventoryEmail;
	}

	/**
	 * @param defaultInventoryEmail
	 *            the defaultInventoryEmail to set
	 */
	public void setDefaultInventoryEmail(String defaultInventoryEmail) {
		this.defaultInventoryEmail = defaultInventoryEmail;
	}

	/**
	 * @return the downloadTaskDays
	 */
	public Integer getDownloadTaskDays() {
		return downloadTaskDays;
	}

	/**
	 * @param downloadTaskDays
	 *            the downloadTaskDays to set
	 */
	public void setDownloadTaskDays(Integer downloadTaskDays) {
		this.downloadTaskDays = downloadTaskDays;
	}

	/**
	 * @return the downloadTaskType
	 */
	public Integer getDownloadTaskType() {
		return downloadTaskType;
	}

	/**
	 * @param downloadTaskType
	 *            the downloadTaskType to set
	 */
	public void setDownloadTaskType(Integer downloadTaskType) {
		this.downloadTaskType = downloadTaskType;
	}

	/**
	 * @return the noOfDevices
	 */
	public BigDecimal getNoOfDevices() {
		return noOfDevices;
	}

	/**
	 * @param noOfDevices
	 *            the noOfDevices to set
	 */
	public void setNoOfDevices(BigDecimal noOfDevices) {
		this.noOfDevices = noOfDevices;
	}

	/**
	 * @return the orgLat
	 */
	public String getOrgLat() {
		return orgLat;
	}

	/**
	 * @param orgLat
	 *            the orgLat to set
	 */
	public void setOrgLat(String orgLat) {
		this.orgLat = orgLat;
	}

	/**
	 * @return the orgLong
	 */
	public String getOrgLong() {
		return orgLong;
	}

	/**
	 * @param orgLong
	 *            the orgLong to set
	 */
	public void setOrgLong(String orgLong) {
		this.orgLong = orgLong;
	}

	/**
	 * @return the orgName
	 */
	public String getOrgName() {
		return orgName;
	}

	/**
	 * @param orgName
	 *            the orgName to set
	 */
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	/**
	 * @return the passcode
	 */
	public String getPasscode() {
		return passcode;
	}

	/**
	 * @param passcode
	 *            the passcode to set
	 */
	public void setPasscode(String passcode) {
		this.passcode = passcode;
	}

	/**
	 * @return the primaryEmail
	 */
	public String getPrimaryEmail() {
		return primaryEmail;
	}

	/**
	 * @param primaryEmail
	 *            the primaryEmail to set
	 */
	public void setPrimaryEmail(String primaryEmail) {
		this.primaryEmail = primaryEmail;
	}

	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}

	/**
	 * @param state
	 *            the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * @return the timeZone
	 */
	public Integer getTimeZone() {
		return timeZone;
	}

	/**
	 * @param timeZone
	 *            the timeZone to set
	 */
	public void setTimeZone(Integer timeZone) {
		this.timeZone = timeZone;
	}

	/**
	 * @return the zip
	 */
	public String getZip() {
		return zip;
	}

	/**
	 * @param zip
	 *            the zip to set
	 */
	public void setZip(String zip) {
		this.zip = zip;
	}
/*

	public User addUser(User user) {
		getUsers().add(user);
		user.setOrganization(this);

		return user;
	}

	public User removeUser(User user) {
		getUsers().remove(user);
		user.setOrganization(null);

		return user;
	}*/

	/*public List<Role> getRoles() {
		return this.roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}*/

	/*
	 * public Role addRole(Role role) { getRoles().add(role);
	 * role.setOrganization(this);
	 * 
	 * return role; }
	 * 
	 * public Role removeRole(Role role) { getRoles().remove(role);
	 * role.setOrganization(null);
	 * 
	 * return role; }
	 */

}