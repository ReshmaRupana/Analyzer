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
import com.disys.analyzer.model.dto.JobBoardDTO;
import com.disys.analyzer.repository.custom.JobBoardRepositoryCustom;

@Repository
@Transactional(readOnly = true)
public class JobBoardRepositoryImpl implements JobBoardRepositoryCustom{

	@PersistenceContext
	EntityManager entityManager;

	@Override
	public List<JobBoardDTO> getJobBoardList(String userId, String companyCode) 
	{
		Session session = entityManager.unwrap(Session.class);
		JobBoardRepositoryWork work = new JobBoardRepositoryWork(userId, companyCode);	
		session.doWork(work);
		List<JobBoardDTO> list = work.getList();
		return list;
	}
	
	private static class JobBoardRepositoryWork implements Work {
		public Logger logger = LoggerFactory.getLogger(getClass());
		private List<JobBoardDTO> list;
		private JobBoardDTO jobBoardDTO;
		String userId;
		String companyCode;
		String recordCode=Constants.STRING_CONSTANT_ALL;
		CallableStatement cstmt = null;
		ResultSet rs = null;

		/**
		 * @param userId
		 */
		public JobBoardRepositoryWork(String userId, String companyCode) {
			super();
			this.userId = userId;
			this.companyCode=companyCode;
		}

		@Override
		public void execute(Connection connection) throws SQLException {

			list = new ArrayList<JobBoardDTO>();
			jobBoardDTO = new JobBoardDTO();

				String query = "{call " + FacesUtils.SCHEMA_DBO
						+ ".spGetJobBoardList('" + userId + "','" + companyCode + "','"+recordCode+"')}";

				System.out.println("Query in JobBoardRepositoryWork " + query);

				logger.debug("Query in JobBoardRepositoryWork " + query);

				cstmt = connection.prepareCall(query);
				rs = cstmt.executeQuery();

			try {
					if (rs != null) 
					{
						while (rs.next()) 
						{
							jobBoardDTO.setJobBoardCode(rs.getString("JobBoardCode"));
							jobBoardDTO.setJobBoardDescription(rs.getString("Description"));														
							list.add(jobBoardDTO);
							jobBoardDTO = new JobBoardDTO();
						}
					} 
					else 
					{
						list = new ArrayList<JobBoardDTO>();
					}
					System.out.println("List size in JobBoardRepositoryWork " + list.size());
					logger.debug("List size in JobBoardRepositoryWork " + list.size());
			} 
			catch (Exception e) 
			{
				System.out.println("Exception in JobBoardRepositoryWork --> execute.");
				logger.debug("Exception in JobBoardRepositoryWork --> execute.");
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
		public List<JobBoardDTO> getList() {
			return list;
		}

	}
	
	@Override
	public List<JobBoardDTO> getJobBoardDescription(String userId, String companyCode, String recordCode) 
	{
		Session session = entityManager.unwrap(Session.class);
		JobBoardDescriptionRepositoryWork work = new JobBoardDescriptionRepositoryWork(userId, companyCode, recordCode);	
		session.doWork(work);
		List<JobBoardDTO> list = work.getList();
		return list;
	}
	
	private static class JobBoardDescriptionRepositoryWork implements Work {
		public Logger logger = LoggerFactory.getLogger(getClass());
		private List<JobBoardDTO> list;
		private JobBoardDTO jobBoardDTO;
		String userId;
		String companyCode;
		String recordCode;
		CallableStatement cstmt = null;
		ResultSet rs = null;

		/**
		 * @param userId
		 */
		public JobBoardDescriptionRepositoryWork(String userId, String companyCode, String recordCode) {
			super();
			this.userId = userId;
			this.companyCode=companyCode;
			this.recordCode=recordCode;
		}

		@Override
		public void execute(Connection connection) throws SQLException {

			list = new ArrayList<JobBoardDTO>();
			jobBoardDTO = new JobBoardDTO();

				String query = "{call " + FacesUtils.SCHEMA_DBO
						+ ".spGetJobBoardDescription('" + userId + "','" + companyCode + "','"+recordCode+"')}";

				System.out.println("Query in JobBoardDescriptionRepositoryWork " + query);

				logger.debug("Query in JobBoardDescriptionRepositoryWork " + query);

				cstmt = connection.prepareCall(query);
				rs = cstmt.executeQuery();

			try {
					if (rs != null) 
					{
						while (rs.next()) 
						{
							jobBoardDTO.setJobBoardDescription(rs.getString("Description").isEmpty()?rs.getString("JobBoardCode"):rs.getString("Description"));														
							list.add(jobBoardDTO);
							jobBoardDTO = new JobBoardDTO();
						}
					} 
					else 
					{
						list = new ArrayList<JobBoardDTO>();
					}
					System.out.println("List size in JobBoardDescriptionRepositoryWork " + list.size());
					logger.debug("List size in JobBoardDescriptionRepositoryWork " + list.size());
			} 
			catch (Exception e) 
			{
				System.out.println("Exception in JobBoardDescriptionRepositoryWork --> execute.");
				logger.debug("Exception in JobBoardDescriptionRepositoryWork --> execute.");
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
		public List<JobBoardDTO> getList() {
			return list;
		}

	
	}
}
