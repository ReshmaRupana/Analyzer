package com.disys.analyzer.model;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;

/**
 * The persistent class for the PSBillingType database table.
 * 
 */
@Entity
@NamedQuery(name = "PSBillingType.findAll", query = "SELECT p FROM PSBillingType p")
//@Table(schema="Analyser.dbo")
public class PSBillingType implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer PSBillingTypeId;

	private String PSBillingTypeCode;

	private String PSBillingTypeLongName;

	// bi-directional many-to-one association to AnalyserClient
	@OneToMany(mappedBy = "psBillingType")
	private List<AnalyserClient> analyserClients;

	public PSBillingType() {
	}

	public Integer getPSBillingTypeId() {
		return this.PSBillingTypeId;
	}

	public void setPSBillingTypeId(Integer PSBillingTypeId) {
		this.PSBillingTypeId = PSBillingTypeId;
	}

	public String getPSBillingTypeCode() {
		return this.PSBillingTypeCode;
	}

	public void setPSBillingTypeCode(String PSBillingTypeCode) {
		this.PSBillingTypeCode = PSBillingTypeCode;
	}

	public String getPSBillingTypeLongName() {
		return this.PSBillingTypeLongName;
	}

	public void setPSBillingTypeLongName(String PSBillingTypeLongName) {
		this.PSBillingTypeLongName = PSBillingTypeLongName;
	}

	public List<AnalyserClient> getAnalyserClients() {
		return this.analyserClients;
	}

	public void setAnalyserClients(List<AnalyserClient> analyserClients) {
		this.analyserClients = analyserClients;
	}

	public AnalyserClient addAnalyserClient(AnalyserClient analyserClient) {
		getAnalyserClients().add(analyserClient);
		analyserClient.setPsBillingType(this);

		return analyserClient;
	}

	public AnalyserClient removeAnalyserClient(AnalyserClient analyserClient) {
		getAnalyserClients().remove(analyserClient);
		analyserClient.setPsBillingType(null);

		return analyserClient;
	}

}