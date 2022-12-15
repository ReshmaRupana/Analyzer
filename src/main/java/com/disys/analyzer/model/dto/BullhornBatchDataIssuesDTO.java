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
public class BullhornBatchDataIssuesDTO implements Serializable
{

	private static final long serialVersionUID = 6128885673928232458L;
	@Id
	private Integer bullhornBatchDataIssuesId;
	private String companyCode;
	private String sourceFieldName;
	private String sourceFieldValue;
	private Integer bullhornBatchDataProcessedId;
	private String messageText;
	private String sourceTableName;
	private String sourceIdColumnName;
	private Integer sourceRecordId;
	private String bullhornBatchCode;
	private String recordType;
	private Timestamp entryDateTime;
	private String databaseName;
	
	public BullhornBatchDataIssuesDTO() {
		super();
	}
	
	public Integer getBullhornBatchDataIssuesId() {
		return bullhornBatchDataIssuesId;
	}
	public void setBullhornBatchDataIssuesId(Integer bullhornBatchDataIssuesId) {
		this.bullhornBatchDataIssuesId = bullhornBatchDataIssuesId;
	}
	public String getCompanyCode() {
		return companyCode;
	}
	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}
	public String getSourceFieldName() {
		return sourceFieldName;
	}
	public void setSourceFieldName(String sourceFieldName) {
		this.sourceFieldName = sourceFieldName;
	}
	public String getSourceFieldValue() {
		return sourceFieldValue;
	}
	public void setSourceFieldValue(String sourceFieldValue) {
		this.sourceFieldValue = sourceFieldValue;
	}
	public Integer getBullhornBatchDataProcessedId() {
		return bullhornBatchDataProcessedId;
	}
	public void setBullhornBatchDataProcessedId(Integer bullhornBatchDataProcessedId) {
		this.bullhornBatchDataProcessedId = bullhornBatchDataProcessedId;
	}
	public String getMessageText() {
		return messageText;
	}
	public void setMessageText(String messageText) {
		this.messageText = messageText;
	}

	@Override
	public String toString() {
		return "BullhornBatchDataIssuesDTO [bullhornBatchDataIssuesId=" + bullhornBatchDataIssuesId + ", companyCode="
				+ companyCode + ", sourceFieldName=" + sourceFieldName + ", sourceFieldValue=" + sourceFieldValue
				+ ", bullhornBatchDataProcessedId=" + bullhornBatchDataProcessedId + ", messageText=" + messageText
				+ "]";
	}

	public String getSourceTableName() {
		return sourceTableName;
	}

	public void setSourceTableName(String sourceTableName) {
		this.sourceTableName = sourceTableName;
	}

	public String getSourceIdColumnName() {
		return sourceIdColumnName;
	}

	public void setSourceIdColumnName(String sourceIdColumnName) {
		this.sourceIdColumnName = sourceIdColumnName;
	}

	public Integer getSourceRecordId() {
		return sourceRecordId;
	}

	public void setSourceRecordId(Integer sourceRecordId) {
		this.sourceRecordId = sourceRecordId;
	}

	public String getBullhornBatchCode() {
		return bullhornBatchCode;
	}

	public void setBullhornBatchCode(String bullhornBatchCode) {
		this.bullhornBatchCode = bullhornBatchCode;
	}

	public String getRecordType() {
		return recordType;
	}

	public void setRecordType(String recordType) {
		this.recordType = recordType;
	}

	public Timestamp getEntryDateTime() {
		return entryDateTime;
	}

	public void setEntryDateTime(Timestamp entryDateTime) {
		this.entryDateTime = entryDateTime;
	}

	public String getDatabaseName() {
		return databaseName;
	}

	public void setDatabaseName(String databaseName) {
		this.databaseName = databaseName;
	}
}
