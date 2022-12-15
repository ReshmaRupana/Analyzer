package com.disys.analyzer.model;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;

/**
 * The persistent class for the PSServiceArea database table.
 * 
 */
@Entity
@NamedQuery(name = "PSServiceArea.findAll", query = "SELECT p FROM PSServiceArea p")
//@Table(schema="Analyser.dbo")
public class PSServiceArea implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer PSServiceAreaId;

	private String PSServiceAreaCode;

	private String PSServiceAreaLongName;

	// bi-directional many-to-one association to AnalyserClient
	@OneToMany(mappedBy = "pSServiceArea")
	private List<AnalyserClient> analyserClients;

	public PSServiceArea() {
	}

	public Integer getPSServiceAreaId() {
		return this.PSServiceAreaId;
	}

	public void setPSServiceAreaId(Integer PSServiceAreaId) {
		this.PSServiceAreaId = PSServiceAreaId;
	}

	public String getPSServiceAreaCode() {
		return this.PSServiceAreaCode;
	}

	public void setPSServiceAreaCode(String PSServiceAreaCode) {
		this.PSServiceAreaCode = PSServiceAreaCode;
	}

	public String getPSServiceAreaLongName() {
		return this.PSServiceAreaLongName;
	}

	public void setPSServiceAreaLongName(String PSServiceAreaLongName) {
		this.PSServiceAreaLongName = PSServiceAreaLongName;
	}

	public List<AnalyserClient> getAnalyserClients() {
		return this.analyserClients;
	}

	public void setAnalyserClients(List<AnalyserClient> analyserClients) {
		this.analyserClients = analyserClients;
	}

	public AnalyserClient addAnalyserClient(AnalyserClient analyserClient) {
		getAnalyserClients().add(analyserClient);
		analyserClient.setpSServiceArea(this);

		return analyserClient;
	}

	public AnalyserClient removeAnalyserClient(AnalyserClient analyserClient) {
		getAnalyserClients().remove(analyserClient);
		analyserClient.setpSServiceArea(null);

		return analyserClient;
	}

}