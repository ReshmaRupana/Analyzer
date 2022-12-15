package com.disys.analyzer.model.dto;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author Sajid
 * 
 */

@Entity
public class BullhornBatchProcessDTO implements Serializable
{
	private static final long serialVersionUID = 6128885673928232458L;
	@Id
	private String bullhornBatchProcessId;
	private String executedBy;
	private String processStatus;
	private Timestamp executionStartDateTime;
	private Timestamp executionCompletionDateTime;
	private Double completionPercentage;
	
	public BullhornBatchProcessDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public String getBullhornBatchProcessId() {
		return bullhornBatchProcessId;
	}
	public void setBullhornBatchProcessId(String bullhornBatchProcessId) {
		this.bullhornBatchProcessId = bullhornBatchProcessId;
	}
	public String getExecutedBy() {
		return executedBy;
	}
	public void setExecutedBy(String executedBy) {
		this.executedBy = executedBy;
	}
	public String getProcessStatus() {
		return processStatus;
	}
	public void setProcessStatus(String processStatus) {
		this.processStatus = processStatus;
	}
	public Timestamp getExecutionStartDateTime() {
		return executionStartDateTime;
	}
	public void setExecutionStartDateTime(Timestamp executionStartDateTime) {
		this.executionStartDateTime = executionStartDateTime;
	}
	public Timestamp getExecutionCompletionDateTime() {
		return executionCompletionDateTime;
	}
	public void setExecutionCompletionDateTime(Timestamp executionCompletionDateTime) {
		this.executionCompletionDateTime = executionCompletionDateTime;
	}
	public Double getCompletionPercentage() {
		return completionPercentage;
	}
	public void setCompletionPercentage(Double completionPercentage) {
		this.completionPercentage = completionPercentage;
	}
	@Override
	public String toString() {
		return "BullhornBatchProcessDTO [bullhornBatchProcessId=" + bullhornBatchProcessId + ", executedBy="
				+ executedBy + ", processStatus=" + processStatus + ", executionStartDateTime=" + executionStartDateTime
				+ ", executionCompletionDateTime=" + executionCompletionDateTime + ", completionPercentage="
				+ completionPercentage + "]";
	}

}
