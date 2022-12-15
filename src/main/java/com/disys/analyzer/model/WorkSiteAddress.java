package com.disys.analyzer.model;

import java.io.Serializable;

import javax.persistence.*;

/**
 * The persistent class for the WorkSiteAddress database table.
 * 
 */
@Entity
@NamedQuery(name = "WorkSiteAddress.findAll", query = "SELECT w FROM WorkSiteAddress w")
//@Table(schema="Analyser.dbo")
public class WorkSiteAddress implements Serializable {
	private static final long serialVersionUID = 1L;

	/*
	 * @Id
	 * 
	 * @GeneratedValue(strategy=GenerationType.AUTO)
	 * 
	 * @Column(name="WorkSiteAddressId") private int workSiteAddressId;
	 */

	@EmbeddedId
	private WorkSiteAddressPK id;

	private String address;

	private String city;

	private String state;

	private String zip;

	/**
	 * @return the id
	 */
	public WorkSiteAddressPK getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(WorkSiteAddressPK id) {
		this.id = id;
	}

	public WorkSiteAddress() {
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZip() {
		return this.zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

}