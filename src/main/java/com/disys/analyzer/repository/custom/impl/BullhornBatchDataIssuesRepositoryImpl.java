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

import com.disys.analyzer.config.util.Constants;
import com.disys.analyzer.config.util.FacesUtils;
import com.disys.analyzer.model.dto.BullhornBatchDataIssuesDTO;
import com.disys.analyzer.repository.custom.BullhornBatchDataIssuesRepositoryCustom;

/**
 * @author Sajid
 * 
 */

@Repository
@Transactional(readOnly = true)
public class BullhornBatchDataIssuesRepositoryImpl implements BullhornBatchDataIssuesRepositoryCustom{

	@PersistenceContext
	EntityManager entityManager;


	
	/* (non-Javadoc)
	 * @see com.disys.analyzer.repository.custom.DealTypeRepositoryCustom#getDealType()
	 */
	@Override
	public List<BullhornBatchDataIssuesDTO> getBullhornBatchDataIssuesList(String userId, Integer dataProcessedId) 
	{
		Session session = entityManager.unwrap(Session.class);
		BullhornBatchDataIssuesRepositoryWork work = new BullhornBatchDataIssuesRepositoryWork(userId, dataProcessedId);	
		session.doWork(work);
		List<BullhornBatchDataIssuesDTO> list = work.getList();
		return list;
	}
	
	private static class BullhornBatchDataIssuesRepositoryWork implements Work {
		public Logger logger = LoggerFactory.getLogger(getClass());
		private List<BullhornBatchDataIssuesDTO> list;
		private BullhornBatchDataIssuesDTO bullhornBatchDataIssues;
		String userId;
		Integer dataProcessedId;
		String processCode;
		String batchCode;
		String searchString;
		CallableStatement cstmt = null;
		ResultSet rs = null;

		/**
		 * @param userId
		 */
		public BullhornBatchDataIssuesRepositoryWork(String userId, Integer dataProcessedId) {
			super();
			this.userId = userId;
			this.dataProcessedId = dataProcessedId;
//			this.processCode = processCode;
//			this.batchCode = batchCode;
//			this.searchString = searchString;
		}

		@Override
		public void execute(Connection connection) throws SQLException {

			list = new ArrayList<BullhornBatchDataIssuesDTO>();
			bullhornBatchDataIssues = new BullhornBatchDataIssuesDTO();

				String query = "{call " + FacesUtils.SCHEMA_DBO
						+ ".spIntegrationGetRecordIssues('" + userId + "','"+ dataProcessedId +"')}";

				System.out.println("Query in BullhornBatchDataIssuesRepositoryWork " + query);

				logger.debug("Query in BullhornBatchDataIssuesRepositoryWork " + query);

				cstmt = connection.prepareCall(query);
				rs = cstmt.executeQuery();

			try {
					if (rs != null) 
					{
						while (rs.next()) 
						{
							bullhornBatchDataIssues.setBullhornBatchDataIssuesId(rs.getInt("BullhornBatchDataIssuesId"));
							bullhornBatchDataIssues.setCompanyCode(rs.getString("CompanyCode"));
							bullhornBatchDataIssues.setSourceFieldName(rs.getString("SourceFieldName"));	
							bullhornBatchDataIssues.setSourceFieldValue(rs.getString("SourceFieldValue"));							
							bullhornBatchDataIssues.setMessageText(rs.getString("MessageText"));							
							bullhornBatchDataIssues.setSourceTableName(rs.getString("SourceTableName"));
							bullhornBatchDataIssues.setSourceIdColumnName(rs.getString("SourceIdColumnName"));
							bullhornBatchDataIssues.setBullhornBatchCode(rs.getString("BullhornBatchCode"));
							bullhornBatchDataIssues.setRecordType(rs.getString("RecordType"));
							bullhornBatchDataIssues.setDatabaseName(rs.getString("DatabaseName"));
							bullhornBatchDataIssues.setSourceRecordId(rs.getInt("SourceRecordId"));
							bullhornBatchDataIssues.setEntryDateTime(rs.getTimestamp("EntryDateTime"));						
							
							list.add(bullhornBatchDataIssues);
							bullhornBatchDataIssues = new BullhornBatchDataIssuesDTO();
						}
					} 
					else 
					{
						list = new ArrayList<BullhornBatchDataIssuesDTO>();
					}
					System.out.println("List size in BullhornBatchDataIssuesRepositoryWork " + list.size());
					logger.debug("List size in BullhornBatchDataIssuesRepositoryWork " + list.size());
			} 
			catch (Exception e) 
			{
				System.out.println("Exception in BullhornBatchDataIssuesRepositoryWork --> execute.");
				logger.debug("Exception in BullhornBatchDataIssuesRepositoryWork --> execute.");
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
		public List<BullhornBatchDataIssuesDTO> getList() {
			return list;
		}
	
	}
	
}
