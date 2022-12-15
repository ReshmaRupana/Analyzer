package com.disys.analyzer.model;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;

/**
 * The persistent class for the PSSalesforceClient database table.
 * 
 */
@Entity
@NamedQuery(name = "PSSalesforceClient.findAll", query = "SELECT p FROM PSSalesforceClient p")
//@Table(schema="Analyser.dbo")
public class PSSalesforceClient implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer PSSalesforceClientId;

	@Column(name = "PSSalesforceClientCode")
	private String PSSalesforceClientCode;

	@Column(name = "PSSalesforceClientName")
	private String PSSalesforceClientName;

	// bi-directional many-to-one association to AnalyserClient
	@OneToMany(mappedBy = "pSSalesforceClient")
	private List<AnalyserClient> analyserClients;

	public PSSalesforceClient() {
	}

	public AnalyserClient addAnalyserClient(AnalyserClient analyserClient) {
		getAnalyserClients().add(analyserClient);
		analyserClient.setpSSalesforceClient(this);

		return analyserClient;
	}

	public AnalyserClient removeAnalyserClient(AnalyserClient analyserClient) {
		getAnalyserClients().remove(analyserClient);
		analyserClient.setpSSalesforceClient(null);

		return analyserClient;
	}

	/**
	 * @return the analyserClients
	 */
	public List<AnalyserClient> getAnalyserClients() {
		return analyserClients;
	}

	/**
	 * @param analyserClients
	 *            the analyserClients to set
	 */
	public void setAnalyserClients(List<AnalyserClient> analyserClients) {
		this.analyserClients = analyserClients;
	}

	/**
	 * @return the pSSalesforceClientId
	 */
	public Integer getPSSalesforceClientId() {
		return PSSalesforceClientId;
	}

	/**
	 * @param pSSalesforceClientId
	 *            the pSSalesforceClientId to set
	 */
	public void setPSSalesforceClientId(Integer pSSalesforceClientId) {
		PSSalesforceClientId = pSSalesforceClientId;
	}

	/**
	 * @return the pSSalesforceClientCode
	 */
	public String getPSSalesforceClientCode() {
		return PSSalesforceClientCode;
	}

	/**
	 * @param pSSalesforceClientCode
	 *            the pSSalesforceClientCode to set
	 */
	public void setPSSalesforceClientCode(String pSSalesforceClientCode) {
		PSSalesforceClientCode = pSSalesforceClientCode;
	}

	/**
	 * @return the pSSalesforceClientName
	 */
	public String getPSSalesforceClientName() {
		return PSSalesforceClientName;
	}

	/**
	 * @param pSSalesforceClientName
	 *            the pSSalesforceClientName to set
	 */
	public void setPSSalesforceClientName(String pSSalesforceClientName) {
		PSSalesforceClientName = pSSalesforceClientName;
	}

}