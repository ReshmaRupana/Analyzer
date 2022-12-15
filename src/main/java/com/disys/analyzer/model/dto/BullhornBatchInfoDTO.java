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
public class BullhornBatchInfoDTO implements Serializable
{
	private static final long serialVersionUID = 6128885673928232458L;
	@Id
	private Integer bullhornBatchInfoId;
	private String bullhornBatchCode;
//	private Integer bullhornBatchProcessId;
	private Timestamp batchEntryDateTime;
	private String batchStatus;
	private String batchType;
	private Timestamp processingDateTime;
	private Integer totalBatchRecords;
	private Integer processedRecords;
	private Integer acceptedRecords;
	private Integer rejectedRecords;
	
	private String companyCode;	
	private Integer completionPercentage;
	private String executedBy;
	private Timestamp executionCompletion;
	private Timestamp executionStart;
	private String importComments;
	private String isRecordHasErrors;
	private String isTransferredForProcessing;
	private String processingStatus;
	private Integer recordsTransferredToAnalyzer;
	private Integer recordsTransferredToStaging;
	private Integer totalRecordsTransferred;



	
	public BullhornBatchInfoDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Integer getBullhornBatchInfoId() {
		return bullhornBatchInfoId;
	}
	public void setBullhornBatchInfoId(Integer bullhornBatchInfoId) {
		this.bullhornBatchInfoId = bullhornBatchInfoId;
	}
	public String getBullhornBatchCode() {
		return bullhornBatchCode;
	}
	public void setBullhornBatchCode(String bullhornBatchCode) {
		this.bullhornBatchCode = bullhornBatchCode;
	}

	public Timestamp getBatchEntryDateTime() {
		return batchEntryDateTime;
	}
	public void setBatchEntryDateTime(Timestamp batchEntryDateTime) {
		this.batchEntryDateTime = batchEntryDateTime;
	}
	public String getBatchStatus() {
		return batchStatus;
	}
	public void setBatchStatus(String batchStatus) {
		this.batchStatus = batchStatus;
	}
	public String getBatchType() {
		return batchType;
	}
	public void setBatchType(String batchType) {
		this.batchType = batchType;
	}
	public Timestamp getProcessingDateTime() {
		return processingDateTime;
	}
	public void setProcessingDateTime(Timestamp processingDateTime) {
		this.processingDateTime = processingDateTime;
	}
	public Integer getTotalBatchRecords() {
		return totalBatchRecords;
	}
	public void setTotalBatchRecords(Integer totalBatchRecords) {
		this.totalBatchRecords = totalBatchRecords;
	}
	public Integer getProcessedRecords() {
		return processedRecords;
	}
	public void setProcessedRecords(Integer processedRecords) {
		this.processedRecords = processedRecords;
	}
	public Integer getAcceptedRecords() {
		return acceptedRecords;
	}
	public void setAcceptedRecords(Integer acceptedRecords) {
		this.acceptedRecords = acceptedRecords;
	}
	public Integer getRejectedRecords() {
		return rejectedRecords;
	}
	public void setRejectedRecords(Integer rejectedRecords) {
		this.rejectedRecords = rejectedRecords;
	}

	@Override
	public String toString() {
		return "BullhornBatchInfoDTO [bullhornBatchInfoId=" + bullhornBatchInfoId + ", bullhornBatchCode="
				+ bullhornBatchCode + ", bullhornBatchProcessId=" + ", batchEntryDateTime="
				+ batchEntryDateTime + ", batchStatus=" + batchStatus + ", batchType=" + batchType
				+ ", processingDateTime=" + processingDateTime + ", totalBatchRecords=" + totalBatchRecords
				+ ", processedRecords=" + processedRecords + ", acceptedRecords=" + acceptedRecords
				+ ", rejectedRecords=" + rejectedRecords + "]";
	}

	public String getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	public Integer getCompletionPercentage() {
		return completionPercentage;
	}

	public void setCompletionPercentage(Integer completionPercentage) {
		this.completionPercentage = completionPercentage;
	}

	public String getExecutedBy() {
		return executedBy;
	}

	public void setExecutedBy(String executedBy) {
		this.executedBy = executedBy;
	}

	public Timestamp getExecutionCompletion() {
		return executionCompletion;
	}

	public void setExecutionCompletion(Timestamp executionCompletion) {
		this.executionCompletion = executionCompletion;
	}

	public Timestamp getExecutionStart() {
		return executionStart;
	}

	public void setExecutionStart(Timestamp executionStart) {
		this.executionStart = executionStart;
	}

	public String getImportComments() {
		return importComments;
	}

	public void setImportComments(String importComments) {
		this.importComments = importComments;
	}

	public String getIsRecordHasErrors() {
		return isRecordHasErrors;
	}

	public void setIsRecordHasErrors(String isRecordHasErrors) {
		this.isRecordHasErrors = isRecordHasErrors;
	}

	public String getIsTransferredForProcessing() {
		return isTransferredForProcessing;
	}

	public void setIsTransferredForProcessing(String isTransferredForProcessing) {
		this.isTransferredForProcessing = isTransferredForProcessing;
	}

	public String getProcessingStatus() {
		return processingStatus;
	}

	public void setProcessingStatus(String processingStatus) {
		this.processingStatus = processingStatus;
	}

	public Integer getRecordsTransferredToAnalyzer() {
		return recordsTransferredToAnalyzer;
	}

	public void setRecordsTransferredToAnalyzer(Integer recordsTransferredToAnalyzer) {
		this.recordsTransferredToAnalyzer = recordsTransferredToAnalyzer;
	}

	public Integer getRecordsTransferredToStaging() {
		return recordsTransferredToStaging;
	}

	public void setRecordsTransferredToStaging(Integer recordsTransferredToStaging) {
		this.recordsTransferredToStaging = recordsTransferredToStaging;
	}

	public Integer getTotalRecordsTransferred() {
		return totalRecordsTransferred;
	}

	public void setTotalRecordsTransferred(Integer totalRecordsTransferred) {
		this.totalRecordsTransferred = totalRecordsTransferred;
	}
	

}
