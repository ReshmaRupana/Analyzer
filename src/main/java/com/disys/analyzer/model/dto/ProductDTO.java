/**
 * 
 */
package com.disys.analyzer.model.dto;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author muhammad.ghauri
 *
 */
@Entity 
//@Table(name = "PSPRODUCT")
public class ProductDTO implements Serializable
{
	private static final long serialVersionUID = 6128885673928232458L;
	
	@Id
	private Integer psProductId;
	
	private String pSProductCode;
	private String productDescription;
	private String status;
	private String productLabel;
	private String companyCode;
	/**
	 * 
	 */
	public ProductDTO() {
		// TODO Auto-generated constructor stub
	}

	public ProductDTO(Integer psProductId, String pSProductCode, String productDescription, String status, String companyCode) {
		super();
		this.psProductId = psProductId;
		this.pSProductCode = pSProductCode;
		this.productDescription = productDescription;
		this.status = status;
		this.companyCode=companyCode;
	}

	public ProductDTO(Integer psProductId, String pSProductCode, String productDescription, String companyCode) {
		super();
		this.psProductId = psProductId;
		this.pSProductCode = pSProductCode;
		this.productDescription = productDescription;
		this.status = "1";
		this.companyCode=companyCode;
	}
	
	/**
	 * @return the psProductId
	 */
	public Integer getPsProductId() {
		return psProductId;
	}

	/**
	 * @param psProductId the psProductId to set
	 */
	public void setPsProductId(Integer psProductId) {
		this.psProductId = psProductId;
	}

	/**
	 * @return the pSProductCode
	 */
	public String getpSProductCode() {
		return pSProductCode;
	}

	/**
	 * @param pSProductCode the pSProductCode to set
	 */
	public void setpSProductCode(String pSProductCode) {
		this.pSProductCode = pSProductCode;
	}

	/**
	 * @return the productDescription
	 */
	public String getProductDescription() {
		return productDescription;
	}

	/**
	 * @param productDescription the productDescription to set
	 */
	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	
	/**
	 * @return the companyCode
	 */
	public String getCompanyCode() {
		return companyCode;
	}
	/**
	 * @param companyCode the companyCode to set
	 */
	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ProductDTO [psProductId=" + psProductId + ", pSProductCode=" + pSProductCode
				+ ", productDescription=" + productDescription
				+ ", productLabel=" + productLabel
				+ ", status=" + status + "]";
	}

	public void print ()
	{
		System.out.println("Product --> psProductId" + psProductId);
		System.out.println("Product --> pSProductCode" + pSProductCode);
		System.out.println("Product --> productDescription" + productDescription);
		System.out.println("Product --> psProductId" + psProductId);
		System.out.println("Product --> productLabel" + productLabel);
	}

	/**
	 * @return the productLabel
	 */
	public String getProductLabel() {
		return productLabel;
	}

	/**
	 * @param productLabel the productLabel to set
	 */
	public void setProductLabel(String productLabel) {
		this.productLabel = productLabel;
	}
}
