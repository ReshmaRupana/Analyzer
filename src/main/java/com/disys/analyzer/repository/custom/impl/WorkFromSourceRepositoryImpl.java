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
import com.disys.analyzer.model.dto.WorkFromSourceDTO;
import com.disys.analyzer.repository.custom.WorkFromSourceRepositoryCustom;

@Repository
@Transactional(readOnly = true)
public class WorkFromSourceRepositoryImpl implements WorkFromSourceRepositoryCustom{

	@PersistenceContext
	EntityManager entityManager;

	@Override
	public List<WorkFromSourceDTO> getWorkFromSourceList(String userId, String companyCode) 
	{
		Session session = entityManager.unwrap(Session.class);
		WorkFromSourceRepositoryWork work = new WorkFromSourceRepositoryWork(userId, companyCode);	
		session.doWork(work);
		List<WorkFromSourceDTO> list = work.getList();
		return list;
	}
	
	private static class WorkFromSourceRepositoryWork implements Work {
		public Logger logger = LoggerFactory.getLogger(getClass());
		private List<WorkFromSourceDTO> list;
		private WorkFromSourceDTO workFromSourceDTO;
		String userId;
		String companyCode;
		String recordCode=Constants.STRING_CONSTANT_ALL;
		CallableStatement cstmt = null;
		ResultSet rs = null;

		/**
		 * @param userId
		 */
		public WorkFromSourceRepositoryWork(String userId, String companyCode) {
			super();
			this.userId = userId;
			this.companyCode=companyCode;
		}

		@Override
		public void execute(Connection connection) throws SQLException {

			list = new ArrayList<WorkFromSourceDTO>();
			workFromSourceDTO = new WorkFromSourceDTO();

				String query = "{call " + FacesUtils.SCHEMA_DBO
						+ ".spGetWorkFromSourceList('" + userId + "','" + companyCode + "','"+recordCode+"')}";

				System.out.println("Query in WorkFromSourceRepositoryWork " + query);

				logger.debug("Query in WorkFromSourceRepositoryWork " + query);

				cstmt = connection.prepareCall(query);
				rs = cstmt.executeQuery();

			try {
					if (rs != null) 
					{
						while (rs.next()) 
						{
							workFromSourceDTO.setWorkFromSourceCode(rs.getString("WorkFromSourceCode"));
							workFromSourceDTO.setWorkFromSourceDescription(rs.getString("Description"));														
							list.add(workFromSourceDTO);
							workFromSourceDTO = new WorkFromSourceDTO();
						}
					} 
					else 
					{
						list = new ArrayList<WorkFromSourceDTO>();
					}
					System.out.println("List size in WorkFromSourceRepositoryWork " + list.size());
					logger.debug("List size in WorkFromSourceRepositoryWork " + list.size());
			} 
			catch (Exception e) 
			{
				System.out.println("Exception in WorkFromSourceRepositoryWork --> execute.");
				logger.debug("Exception in WorkFromSourceRepositoryWork --> execute.");
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
		public List<WorkFromSourceDTO> getList() {
			return list;
		}

	}
	

	@Override
	public List<WorkFromSourceDTO> getWorkFromSourceDescription(String userId, String companyCode, String recordCode) 
	{
		Session session = entityManager.unwrap(Session.class);
		WorkFromSourceDescriptionRepositoryWork work = new WorkFromSourceDescriptionRepositoryWork(userId, companyCode, recordCode);	
		session.doWork(work);
		List<WorkFromSourceDTO> list = work.getList();
		return list;
	}
	
	private static class WorkFromSourceDescriptionRepositoryWork implements Work {
		public Logger logger = LoggerFactory.getLogger(getClass());
		private List<WorkFromSourceDTO> list;
		private WorkFromSourceDTO workFromSourceDTO;
		String userId;
		String companyCode;
		String recordCode;
		CallableStatement cstmt = null;
		ResultSet rs = null;

		/**
		 * @param userId
		 */
		public WorkFromSourceDescriptionRepositoryWork(String userId, String companyCode, String recordCode) {
			super();
			this.userId = userId;
			this.companyCode=companyCode;
			this.recordCode=recordCode;
		}

		@Override
		public void execute(Connection connection) throws SQLException {

			list = new ArrayList<WorkFromSourceDTO>();
			workFromSourceDTO = new WorkFromSourceDTO();

				String query = "{call " + FacesUtils.SCHEMA_DBO
						+ ".spGetWorkFromSourceDescription('" + userId + "','" + companyCode + "','"+recordCode+"')}";

				System.out.println("Query in WorkFromSourceDescriptionRepositoryWork " + query);

				logger.debug("Query in WorkFromSourceDescriptionRepositoryWork " + query);

				cstmt = connection.prepareCall(query);
				rs = cstmt.executeQuery();

			try {
					if (rs != null) 
					{
						while (rs.next()) 
						{
							workFromSourceDTO.setWorkFromSourceDescription(rs.getString("Description").isEmpty()?rs.getString("WorkFromSourceCode"):rs.getString("Description"));														
							list.add(workFromSourceDTO);
							workFromSourceDTO = new WorkFromSourceDTO();
						}
					} 
					else 
					{
						list = new ArrayList<WorkFromSourceDTO>();
					}
					System.out.println("List size in WorkFromSourceDescriptionRepositoryWork " + list.size());
					logger.debug("List size in WorkFromSourceDescriptionRepositoryWork " + list.size());
			} 
			catch (Exception e) 
			{
				System.out.println("Exception in WorkFromSourceDescriptionRepositoryWork --> execute.");
				logger.debug("Exception in WorkFromSourceDescriptionRepositoryWork --> execute.");
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
		public List<WorkFromSourceDTO> getList() {
			return list;
		}

	
	}
}
