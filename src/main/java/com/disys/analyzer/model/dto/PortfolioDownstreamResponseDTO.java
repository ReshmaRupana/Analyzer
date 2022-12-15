/**
 * 
 */
package com.disys.analyzer.model.dto;

import java.io.Serializable;

/**
 * @author Sajid
 * 
 */
public class PortfolioDownstreamResponseDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1029331799366053787L;
	
	private String rawResponse;
	private Integer statusCode;
	private String callRequestedTime;
	private String responseReceivedTime;
	private String timeTakenInMilliSec;
	private String comments;
	
	public String getRawResponse() {
		return rawResponse;
	}



	public void setRawResponse(String rawResponse) {
		this.rawResponse = rawResponse;
	}



	public Integer getStatusCode() {
		return statusCode;
	}



	public void setStatusCode(Integer statusCode) {
		this.statusCode = statusCode;
	}



	public String getCallRequestedTime() {
		return callRequestedTime;
	}



	public void setCallRequestedTime(String callRequestedTime) {
		this.callRequestedTime = callRequestedTime;
	}



	public String getResponseReceivedTime() {
		return responseReceivedTime;
	}

	public void setResponseReceivedTime(String responseReceivedTime) {
		this.responseReceivedTime = responseReceivedTime;
	}

	public String getTimeTakenInMilliSec() {
		return timeTakenInMilliSec;
	}

	public void setTimeTakenInMilliSec(String timeTakenInMilliSec) {
		this.timeTakenInMilliSec = timeTakenInMilliSec;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}



	@Override
	public String toString()
	{
		return "PortfolioDownstreamResponseDTO [rawResponse=" + rawResponse + ", statusCode=" + statusCode + ", callRequestedTime=" + callRequestedTime 
				+ ", responseReceivedTime=" + responseReceivedTime + ", timeTakenInMilliSec=" + timeTakenInMilliSec +", comments=" + comments +"]";
	}
}
