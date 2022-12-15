/**
 * 
 */
package com.disys.analyzer.model.dto;

import java.io.Serializable;

/**
 * @author Sajid
 * 
 */
public class PortfolioDownstreamRequestDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1029331799366053787L;
	
	private String customerId;
	private String verticalCode;
	private String productCode;
	private String geoLocationCode;
	private String mdEmplId;
	private String recruitingTeam;
	private String dealType;
	private String url;
	private String authToken;
	private String ae1EmplId;
	private String rec1EmplId;

	/*private String companyCode;
	private String userId;*/

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getVerticalCode() {
		return verticalCode;
	}

	public void setVerticalCode(String verticalCode) {
		this.verticalCode = verticalCode;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getGeoLocationCode() {
		return geoLocationCode;
	}

	public void setGeoLocationCode(String geoLocationCode) {
		this.geoLocationCode = geoLocationCode;
	}

	public String getMdEmplId() {
		return mdEmplId;
	}

	public void setMdEmplId(String mdEmplId) {
		this.mdEmplId = mdEmplId;
	}

	public String getRecruitingTeam() {
		return recruitingTeam;
	}

	public void setRecruitingTeam(String recruitingTeam) {
		this.recruitingTeam = recruitingTeam;
	}

	public String getDealType() {
		return dealType;
	}

	public void setDealType(String dealType) {
		this.dealType = dealType;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getAuthToken() {
		return authToken;
	}

	public void setAuthToken(String authToken) {
		this.authToken = authToken;
	}
	
	public String getAe1EmplId() {
		return ae1EmplId;
	}

	public void setAe1EmplId(String ae1EmplId) {
		this.ae1EmplId = ae1EmplId;
	}

	public String getRec1EmplId() {
		return rec1EmplId;
	}

	public void setRec1EmplId(String rec1EmplId) {
		this.rec1EmplId = rec1EmplId;
	}

	@Override
	public String toString()
	{
		return "PortfolioDownstreamRequestDTO [customerId=" + customerId + ", verticalCode="+ verticalCode 
				+ ", productCode=" + productCode + ", geoLocationCode=" + geoLocationCode + ", mdEmplId=" + mdEmplId 
				+ ", recruitingTeam=" + recruitingTeam +", dealType=" + dealType + ", url=" + url + ", authToken=" + authToken 
				+", ae1EmplId=" + ae1EmplId +", rec1EmplId=" + rec1EmplId 
				+  "]";
	}
}
