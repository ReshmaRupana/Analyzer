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
import com.disys.analyzer.model.dto.BullhornBatchProcessDTO;
import com.disys.analyzer.repository.custom.BullhornBatchProcessRepositoryCustom;

/**
 * @author Sajid
 * 
 */

@Repository
@Transactional(readOnly = true)
public class BullhornBatchProcessRepositoryImpl implements BullhornBatchProcessRepositoryCustom{

	@PersistenceContext
	EntityManager entityManager;


	
	/* (non-Javadoc)
	 * @see com.disys.analyzer.repository.custom.DealTypeRepositoryCustom#getDealType()
	 */
	@Override
	public List<BullhornBatchProcessDTO> getBullhornBatchProcessList(String userId, String processCode, String batchCode, String searchString) 
	{
		Session session = entityManager.unwrap(Session.class);
		BullhornBatchProcessRepositoryWork work = new BullhornBatchProcessRepositoryWork(userId, processCode, batchCode, searchString);	
		session.doWork(work);
		List<BullhornBatchProcessDTO> list = work.getList();
		return list;
	}
	
	private static class BullhornBatchProcessRepositoryWork implements Work {
		public Logger logger = LoggerFactory.getLogger(getClass());
		private List<BullhornBatchProcessDTO> list;
		private BullhornBatchProcessDTO bullhornBatchProcess;
		String userId;
		String processCode=Constants.STRING_CONSTANT_ALL;
		String batchCode;
		String searchString;
		CallableStatement cstmt = null;
		ResultSet rs = null;

		/**
		 * @param userId
		 */
		public BullhornBatchProcessRepositoryWork(String userId, String processCode, String batchCode, String searchString) {
			super();
			this.userId = userId;
			this.processCode=processCode;
			this.batchCode = batchCode;
			this.searchString = searchString;
		}

		@Override
		public void execute(Connection connection) throws SQLException {

			list = new ArrayList<BullhornBatchProcessDTO>();
			bullhornBatchProcess = new BullhornBatchProcessDTO();

				String query = "{call " + FacesUtils.SCHEMA_DBO
						+ ".spGetBatchProcessDetails('" + userId + "','"+processCode+"','"+batchCode+"','"+searchString+"')}";

				System.out.println("Query in BullhornBatchProcessRepositoryWork " + query);

				logger.debug("Query in BullhornBatchProcessRepositoryWork " + query);

				cstmt = connection.prepareCall(query);
				rs = cstmt.executeQuery();

			try {
					if (rs != null) 
					{
						while (rs.next()) 
						{
							bullhornBatchProcess.setBullhornBatchProcessId(rs.getString("bullhornBatchProcessId"));
							bullhornBatchProcess.setCompletionPercentage(rs.getDouble("completionPercentage"));
							bullhornBatchProcess.setExecutedBy(rs.getString("executedBy"));
							bullhornBatchProcess.setExecutionCompletionDateTime(rs.getTimestamp("executionCompletionDateTime"));
							bullhornBatchProcess.setExecutionStartDateTime(rs.getTimestamp("executionStartDateTime"));
							bullhornBatchProcess.setProcessStatus(rs.getString("processStatus"));
							list.add(bullhornBatchProcess);
							bullhornBatchProcess = new BullhornBatchProcessDTO();
						}
					} 
					else 
					{
						list = new ArrayList<BullhornBatchProcessDTO>();
					}
					System.out.println("List size in BullhornBatchProcessRepositoryWork " + list.size());
					logger.debug("List size in BullhornBatchProcessRepositoryWork " + list.size());
			} 
			catch (Exception e) 
			{
				System.out.println("Exception in BullhornBatchProcessRepositoryWork --> execute.");
				logger.debug("Exception in BullhornBatchProcessRepositoryWork --> execute.");
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
		public List<BullhornBatchProcessDTO> getList() {
			return list;
		}

	
	}
	/* (non-Javadoc)
	 * @see com.disys.analyzer.repository.custom.BullhornBatchProcessDescRepositoryWork#getBullhornBatchProcessDesc()
	 */
	@Override
	public List<BullhornBatchProcessDTO> getBullhornBatchProcessDescription(String userId, String recordCode) 
	{
		Session session = entityManager.unwrap(Session.class);
		BullhornBatchProcessDescriptioncRepositoryWork work = new BullhornBatchProcessDescriptioncRepositoryWork(userId, recordCode);	
		session.doWork(work);
		List<BullhornBatchProcessDTO> list = work.getList();
		return list;
	}
	
	private static class BullhornBatchProcessDescriptioncRepositoryWork implements Work {
		public Logger logger = LoggerFactory.getLogger(getClass());
		private List<BullhornBatchProcessDTO> list;
		private BullhornBatchProcessDTO bullhornBatchProcessDescription;
		String userId;
		String recordCode;
		CallableStatement cstmt = null;
		ResultSet rs = null;

		/**
		 * @param userId
		 */
		public BullhornBatchProcessDescriptioncRepositoryWork(String userId, String recordCode) {
			super();
			this.userId = userId;
			this.recordCode=recordCode;
		}

		@Override
		public void execute(Connection connection) throws SQLException {

			list = new ArrayList<BullhornBatchProcessDTO>();
			bullhornBatchProcessDescription = new BullhornBatchProcessDTO();

				String query = "{call " + FacesUtils.SCHEMA_DBO
						+ ".spGetBullhornBatchProcessDescription('" + userId + "','"+recordCode+"')}";

				System.out.println("Query in BullhornBatchProcessDescriptioncRepositoryWork " + query);

				logger.debug("Query in BullhornBatchProcessDescriptioncRepositoryWork " + query);

				cstmt = connection.prepareCall(query);
				rs = cstmt.executeQuery();

			try {
					if (rs != null) 
					{
						while (rs.next()) 
						{
							list.add(bullhornBatchProcessDescription);
							bullhornBatchProcessDescription = new BullhornBatchProcessDTO();
						}
					} 
					else 
					{
						list = new ArrayList<BullhornBatchProcessDTO>();
					}
					System.out.println("List size in BullhornBatchProcessDescriptioncRepositoryWork " + list.size());
					logger.debug("List size in BullhornBatchProcessDescriptionRepositoryWork " + list.size());
			} 
			catch (Exception e) 
			{
				System.out.println("Exception in BullhornBatchProcessDescriptioncRepositoryWork --> execute.");
				logger.debug("Exception in BullhornBatchProcessDescriptioncRepositoryWork --> execute.");
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
		public List<BullhornBatchProcessDTO> getList() {
			return list;
		}

	
	}
}
