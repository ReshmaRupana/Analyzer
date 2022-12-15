package com.disys.analyzer.repository.custom.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Session;
import org.hibernate.jdbc.Work;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.disys.analyzer.config.util.FacesUtils;
import com.disys.analyzer.model.dto.BullhornBatchInfoDTO;
import com.disys.analyzer.repository.custom.BullhornBatchInfoRepositoryCustom;

/**
 * @author Sajid
 * 
 */

@Repository
@Transactional(readOnly = true)
public class BullhornBatchInfoRepositoryImpl implements BullhornBatchInfoRepositoryCustom{

	@PersistenceContext
	EntityManager entityManager;


	
	/* (non-Javadoc)
	 * @see com.disys.analyzer.repository.custom.DealTypeRepositoryCustom#getDealType()
	 */
	@Override
	public List<BullhornBatchInfoDTO> getBullhornBatchInfoList(String userId, String processCode, String batchCode, String searchString) 
	{
		Session session = entityManager.unwrap(Session.class);
		BullhornBatchInfoRepositoryWork work = new BullhornBatchInfoRepositoryWork(userId, processCode, batchCode, searchString);	
		session.doWork(work);
		List<BullhornBatchInfoDTO> list = work.getList();
		return list;
	}
	
	private static class BullhornBatchInfoRepositoryWork implements Work {
		public Logger logger = LoggerFactory.getLogger(getClass());
		private List<BullhornBatchInfoDTO> list;
		private BullhornBatchInfoDTO bullhornBatchInfo;
		String userId;
		String processCode;
		String batchCode;
		String searchString;
		CallableStatement cstmt = null;
		ResultSet rs = null;

		/**
		 * @param userId
		 */
		public BullhornBatchInfoRepositoryWork(String userId, String startDate, String endDate, String searchString) {
			super();
			this.userId = userId;
			this.processCode = startDate;
			this.batchCode = endDate;
			this.searchString = searchString;
		}

		@Override
		public void execute(Connection connection) throws SQLException {

			list = new ArrayList<BullhornBatchInfoDTO>();
			bullhornBatchInfo = new BullhornBatchInfoDTO();

				String query = "{call " + FacesUtils.SCHEMA_DBO
						+ ".spIntegrationGetBatchDetails('" + userId + "','"+ processCode +"','"+ batchCode +"','"+ searchString +"')}";

				System.out.println("Query in BullhornBatchInfoRepositoryWork " + query);

				logger.debug("Query in BullhornBatchInfoRepositoryWork " + query);

				cstmt = connection.prepareCall(query);
				rs = cstmt.executeQuery();

			try {
					if (rs != null) 
					{
						while (rs.next()) 
						{
							bullhornBatchInfo.setBullhornBatchInfoId(rs.getInt("BullhornBatchInfoId"));
							bullhornBatchInfo.setBullhornBatchCode(rs.getString("BullhornBatchCode"));
							bullhornBatchInfo.setBatchEntryDateTime(rs.getTimestamp("BatchEntryDateTime"));
							bullhornBatchInfo.setBatchStatus(rs.getString("BatchStatus"));
							bullhornBatchInfo.setBatchType(rs.getString("BatchType"));
							bullhornBatchInfo.setProcessingDateTime(rs.getTimestamp("ProcessingDateTime"));								
							bullhornBatchInfo.setTotalBatchRecords(rs.getInt("TotalBatchRecords"));
							bullhornBatchInfo.setProcessedRecords(rs.getInt("ProcessedRecords"));
							bullhornBatchInfo.setAcceptedRecords(rs.getInt("AcceptedRecords"));
							bullhornBatchInfo.setRejectedRecords(rs.getInt("RejectedRecords"));							
							bullhornBatchInfo.setCompanyCode(rs.getString("CompanyCode"));							
							bullhornBatchInfo.setCompletionPercentage(rs.getInt("CompletionPercentage"));
							bullhornBatchInfo.setExecutedBy(rs.getString("ExecutedBy"));
							bullhornBatchInfo.setExecutionCompletion(rs.getTimestamp("ExecutionCompletion"));
							bullhornBatchInfo.setExecutionStart(rs.getTimestamp("ExecutionStart"));
							bullhornBatchInfo.setImportComments(rs.getString("ImportComments"));
							bullhornBatchInfo.setIsRecordHasErrors(rs.getString("IsRecordHasErrors"));							
							bullhornBatchInfo.setIsTransferredForProcessing(rs.getString("IsTransferredForProcessing"));
							bullhornBatchInfo.setProcessingStatus(rs.getString("ProcessingStatus"));
							bullhornBatchInfo.setRecordsTransferredToAnalyzer(rs.getInt("RecordsTransferredToAnalyzer"));
							bullhornBatchInfo.setRecordsTransferredToStaging(rs.getInt("RecordsTransferredToStaging"));
							bullhornBatchInfo.setTotalRecordsTransferred(rs.getInt("TotalRecordsTransferred"));
							list.add(bullhornBatchInfo);
							bullhornBatchInfo = new BullhornBatchInfoDTO();
						}
					} 
					else 
					{
						list = new ArrayList<BullhornBatchInfoDTO>();
					}
					System.out.println("List size in BullhornBatchInfoRepositoryWork " + list.size());
					logger.debug("List size in BullhornBatchInfoRepositoryWork " + list.size());
			} 
			catch (Exception e) 
			{
				System.out.println("Exception in BullhornBatchInfoRepositoryWork --> execute.");
				logger.debug("Exception in BullhornBatchInfoRepositoryWork --> execute.");
				e.printStackTrace();
			} 
			finally 
			{
				if (rs != null)
					rs.close();
				if (cstmt != null)
					cstmt.close();
			}
		}

		/**
		 * @return the list
		 */
		public List<BullhornBatchInfoDTO> getList() {
			return list;
		}
	
	}

	@Override
	public BullhornBatchInfoDTO getBullhornBatchInfoById(String userId, Integer batchInfoId) {
		Session session = entityManager.unwrap(Session.class);
		BullhornBatchInfoRepositoryGetByIdWork work = new BullhornBatchInfoRepositoryGetByIdWork(userId, batchInfoId);	
		session.doWork(work);
		BullhornBatchInfoDTO bullhornBatchInfoDTO = work.getBullhornBatchInfo();
		return bullhornBatchInfoDTO;
	}
	
	private static class BullhornBatchInfoRepositoryGetByIdWork implements Work {
		public Logger logger = LoggerFactory.getLogger(getClass());
		private List<BullhornBatchInfoDTO> list;
		private BullhornBatchInfoDTO bullhornBatchInfo;
		String userId;
		Integer batchInfoId;
		CallableStatement cstmt = null;
		ResultSet rs = null;

		/**
		 * @param userId
		 */
		public BullhornBatchInfoRepositoryGetByIdWork(String userId, Integer batchInfoId) {
			super();
			this.userId = userId;
			this.batchInfoId = batchInfoId;
		}

		@Override
		public void execute(Connection connection) throws SQLException {

			list = new ArrayList<BullhornBatchInfoDTO>();
			bullhornBatchInfo = new BullhornBatchInfoDTO();

				String query = "{call " + FacesUtils.SCHEMA_DBO
						+ ".spIntegrationGetBatchRecordDetails('" + userId + "','"+ batchInfoId +"')}";

				System.out.println("Query in BullhornBatchInfoRepositoryGetByIdWork " + query);

				logger.debug("Query in BullhornBatchInfoRepositoryGetByIdWork " + query);

				cstmt = connection.prepareCall(query);
				rs = cstmt.executeQuery();

			try {
					if (rs != null) 
					{
						while (rs.next()) 
						{
							bullhornBatchInfo.setBullhornBatchInfoId(rs.getInt("BullhornBatchInfoId"));
							bullhornBatchInfo.setBullhornBatchCode(rs.getString("BullhornBatchCode"));
							bullhornBatchInfo.setBatchEntryDateTime(rs.getTimestamp("BatchEntryDateTime"));
							bullhornBatchInfo.setBatchStatus(rs.getString("BatchStatus"));
							bullhornBatchInfo.setBatchType(rs.getString("BatchType"));
							bullhornBatchInfo.setProcessingDateTime(rs.getTimestamp("ProcessingDateTime"));								
							bullhornBatchInfo.setTotalBatchRecords(rs.getInt("TotalBatchRecords"));
							bullhornBatchInfo.setProcessedRecords(rs.getInt("ProcessedRecords"));
							bullhornBatchInfo.setAcceptedRecords(rs.getInt("AcceptedRecords"));
							bullhornBatchInfo.setRejectedRecords(rs.getInt("RejectedRecords"));							
							bullhornBatchInfo.setCompanyCode(rs.getString("CompanyCode"));							
							bullhornBatchInfo.setCompletionPercentage(rs.getInt("CompletionPercentage"));
							bullhornBatchInfo.setExecutedBy(rs.getString("ExecutedBy"));
							bullhornBatchInfo.setExecutionCompletion(rs.getTimestamp("ExecutionCompletion"));
							bullhornBatchInfo.setExecutionStart(rs.getTimestamp("ExecutionStart"));
							bullhornBatchInfo.setImportComments(rs.getString("ImportComments"));
							bullhornBatchInfo.setIsRecordHasErrors(rs.getString("IsRecordHasErrors"));							
							bullhornBatchInfo.setIsTransferredForProcessing(rs.getString("IsTransferredForProcessing"));
							bullhornBatchInfo.setProcessingStatus(rs.getString("ProcessingStatus"));
							bullhornBatchInfo.setRecordsTransferredToAnalyzer(rs.getInt("RecordsTransferredToAnalyzer"));
							bullhornBatchInfo.setRecordsTransferredToStaging(rs.getInt("RecordsTransferredToStaging"));
							bullhornBatchInfo.setTotalRecordsTransferred(rs.getInt("TotalRecordsTransferred"));
							list.add(bullhornBatchInfo);
							bullhornBatchInfo = new BullhornBatchInfoDTO();
						}
					} 
					else 
					{
						list = new ArrayList<BullhornBatchInfoDTO>();
					}
					System.out.println("List size in BullhornBatchInfoRepositoryWork " + list.size());
					logger.debug("List size in BullhornBatchInfoRepositoryWork " + list.size());
			} 
			catch (Exception e) 
			{
				System.out.println("Exception in BullhornBatchInfoRepositoryWork --> execute.");
				logger.debug("Exception in BullhornBatchInfoRepositoryWork --> execute.");
				e.printStackTrace();
			} 
			finally 
			{
				if (rs != null)
					rs.close();
				if (cstmt != null)
					cstmt.close();
			}
		}

		/**
		 * @return the list
		 */
		public List<BullhornBatchInfoDTO> getList() {
			return list;
		}

		public BullhornBatchInfoDTO getBullhornBatchInfo() {
			return bullhornBatchInfo;
		}

		public void setBullhornBatchInfo(BullhornBatchInfoDTO bullhornBatchInfo) {
			this.bullhornBatchInfo = bullhornBatchInfo;
		}

		public String getUserId() {
			return userId;
		}

		public void setUserId(String userId) {
			this.userId = userId;
		}

		public Integer getBatchInfoId() {
			return batchInfoId;
		}

		public void setBatchInfoId(Integer batchInfoId) {
			this.batchInfoId = batchInfoId;
		}

		public CallableStatement getCstmt() {
			return cstmt;
		}

		public void setCstmt(CallableStatement cstmt) {
			this.cstmt = cstmt;
		}

		public ResultSet getRs() {
			return rs;
		}

		public void setRs(ResultSet rs) {
			this.rs = rs;
		}
	
	}

	@Override
	public void executeProcess(String userId,String batchInfoId) {
		Session session = entityManager.unwrap(Session.class);
		BullhornBatchInfoRepositoryExecuteIdWork work = new BullhornBatchInfoRepositoryExecuteIdWork(userId, batchInfoId);	
		session.doWork(work);
//		String infoId = work.getBatchInfoId();
//		BullhornBatchInfoDTO bullhornBatchInfoDTO = work.getBullhornBatchInfo();
//		return bullhornBatchInfoDTO;
	}	
	
	private static class BullhornBatchInfoRepositoryExecuteIdWork implements Work {
		public Logger logger = LoggerFactory.getLogger(getClass());
		String userId;
		String batchInfoId;
		CallableStatement cstmt = null;
		ResultSet rs = null;

		/**
		 * @param userId
		 */
		public BullhornBatchInfoRepositoryExecuteIdWork(String userId, String batchInfoId) {
			super();
			this.userId = userId;
			this.batchInfoId = batchInfoId;
		}

		@Override
		public void execute(Connection connection) throws SQLException {

				String query = "{call " + FacesUtils.SCHEMA_DBO
						+ ".spIntegrationProcessBullhornBatch('" + userId + "','"+ batchInfoId +"')}";

				System.out.println("Query in spIntegrationProcessBullhornBatch " + query);

				logger.debug("Query in spIntegrationProcessBullhornBatch " + query);

				cstmt = connection.prepareCall(query);
//				rs = cstmt.executeQuery();
				cstmt.executeUpdate();
			
		}

		public Logger getLogger() {
			return logger;
		}

		public void setLogger(Logger logger) {
			this.logger = logger;
		}

		public String getUserId() {
			return userId;
		}

		public void setUserId(String userId) {
			this.userId = userId;
		}

		public String getBatchInfoId() {
			return batchInfoId;
		}

		public void setBatchInfoId(String batchInfoId) {
			this.batchInfoId = batchInfoId;
		}

		public CallableStatement getCstmt() {
			return cstmt;
		}

		public void setCstmt(CallableStatement cstmt) {
			this.cstmt = cstmt;
		}

		public ResultSet getRs() {
			return rs;
		}

		public void setRs(ResultSet rs) {
			this.rs = rs;
		}
	
	}	

	
}
