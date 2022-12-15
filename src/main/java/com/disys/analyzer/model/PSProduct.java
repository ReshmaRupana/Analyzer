package com.disys.analyzer.model;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;

/**
 * The persistent class for the PSProduct database table.
 * 
 */
@Entity
@NamedQuery(name = "PSProduct.findAll", query = "SELECT p FROM PSProduct p")
//@Table(schema="Analyser.dbo")
public class PSProduct implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer PSProductId;

	@Column(name = "ProductDescription")
	private String productDescription;

	private String PSProductCode;

	// bi-directional many-to-one association to AnalyserClient
	@OneToMany(mappedBy = "pSProduct")
	private List<AnalyserClient> analyserClients;

	public PSProduct() {
	}

	public Integer getPSProductId() {
		return this.PSProductId;
	}

	public void setPSProductId(Integer PSProductId) {
		this.PSProductId = PSProductId;
	}

	public String getProductDescription() {
		return this.productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	public String getPSProductCode() {
		return this.PSProductCode;
	}

	public void setPSProductCode(String PSProductCode) {
		this.PSProductCode = PSProductCode;
	}

	public List<AnalyserClient> getAnalyserClients() {
		return this.analyserClients;
	}

	public void setAnalyserClients(List<AnalyserClient> analyserClients) {
		this.analyserClients = analyserClients;
	}

	public AnalyserClient addAnalyserClient(AnalyserClient analyserClient) {
		getAnalyserClients().add(analyserClient);
		analyserClient.setpSProduct(this);

		return analyserClient;
	}

	public AnalyserClient removeAnalyserClient(AnalyserClient analyserClient) {
		getAnalyserClients().remove(analyserClient);
		analyserClient.setpSProduct(null);

		return analyserClient;
	}

}