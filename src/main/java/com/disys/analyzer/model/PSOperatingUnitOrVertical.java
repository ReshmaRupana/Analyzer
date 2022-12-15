package com.disys.analyzer.model;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;

/**
 * The persistent class for the PSOperatingUnitOrVertical database table.
 * 
 */
@Entity
@NamedQuery(name = "PSOperatingUnitOrVertical.findAll", query = "SELECT p FROM PSOperatingUnitOrVertical p")
//@Table(schema="Analyser.dbo")
public class PSOperatingUnitOrVertical implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer PSVerticalId;

	private String PSVerticalCode;

	@Column(name = "VerticalDescription")
	private String verticalDescription;

	// bi-directional many-to-one association to AnalyserClient
	@OneToMany(mappedBy = "psOperatingUnitOrVertical")
	private List<AnalyserClient> analyserClients;

	public PSOperatingUnitOrVertical() {
	}

	public Integer getPSVerticalId() {
		return this.PSVerticalId;
	}

	public void setPSVerticalId(Integer PSVerticalId) {
		this.PSVerticalId = PSVerticalId;
	}

	public String getPSVerticalCode() {
		return this.PSVerticalCode;
	}

	public void setPSVerticalCode(String PSVerticalCode) {
		this.PSVerticalCode = PSVerticalCode;
	}

	public String getVerticalDescription() {
		return this.verticalDescription;
	}

	public void setVerticalDescription(String verticalDescription) {
		this.verticalDescription = verticalDescription;
	}

	public List<AnalyserClient> getAnalyserClients() {
		return this.analyserClients;
	}

	public void setAnalyserClients(List<AnalyserClient> analyserClients) {
		this.analyserClients = analyserClients;
	}

	public AnalyserClient addAnalyserClient(AnalyserClient analyserClient) {
		getAnalyserClients().add(analyserClient);
		analyserClient.setPsOperatingUnitOrVertical(this);

		return analyserClient;
	}

	public AnalyserClient removeAnalyserClient(AnalyserClient analyserClient) {
		getAnalyserClients().remove(analyserClient);
		analyserClient.setPsOperatingUnitOrVertical(null);

		return analyserClient;
	}

}