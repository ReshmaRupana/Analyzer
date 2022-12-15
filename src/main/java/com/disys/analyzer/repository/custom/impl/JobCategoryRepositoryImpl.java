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
import com.disys.analyzer.model.dto.JobCategoryDTO;
import com.disys.analyzer.repository.custom.JobCategoryRepositoryCustom;

@Repository
@Transactional(readOnly = true)
public class JobCategoryRepositoryImpl implements JobCategoryRepositoryCustom{

	@PersistenceContext
	EntityManager entityManager;

	@Override
	public List<JobCategoryDTO> getJobCategoryList(String userId, String companyCode) 
	{
		Session session = entityManager.unwrap(Session.class);
		JobCategoryRepositoryWork work = new JobCategoryRepositoryWork(userId, companyCode);	
		session.doWork(work);
		List<JobCategoryDTO> list = work.getList();
		return list;
	}
	
	private static class JobCategoryRepositoryWork implements Work {
		public Logger logger = LoggerFactory.getLogger(getClass());
		private List<JobCategoryDTO> list;
		private JobCategoryDTO jobCategoryDTO;
		String userId;
		String companyCode;
		String recordCode=Constants.STRING_CONSTANT_ALL;
		CallableStatement cstmt = null;
		ResultSet rs = null;

		/**
		 * @param userId
		 */
		public JobCategoryRepositoryWork(String userId, String companyCode) {
			super();
			this.userId = userId;
			this.companyCode=companyCode;
		}

		@Override
		public void execute(Connection connection) throws SQLException {

			list = new ArrayList<JobCategoryDTO>();
			jobCategoryDTO = new JobCategoryDTO();

				String query = "{call " + FacesUtils.SCHEMA_DBO
						+ ".spGetJobCategoryList('" + userId + "','" + companyCode + "','"+recordCode+"')}";

				System.out.println("Query in JobCategoryRepositoryWork " + query);

				logger.debug("Query in JobCategoryRepositoryWork " + query);

				cstmt = connection.prepareCall(query);
				rs = cstmt.executeQuery();

			try {
					if (rs != null) 
					{
						while (rs.next()) 
						{
							jobCategoryDTO.setJobCategoryCode(rs.getString("JobCategoryCode"));
							jobCategoryDTO.setJobCategoryDescription(rs.getString("Description"));														
							list.add(jobCategoryDTO);
							jobCategoryDTO = new JobCategoryDTO();
						}
					} 
					else 
					{
						list = new ArrayList<JobCategoryDTO>();
					}
					System.out.println("List size in JobCategoryRepositoryWork " + list.size());
					logger.debug("List size in JobCategoryRepositoryWork " + list.size());
			} 
			catch (Exception e) 
			{
				System.out.println("Exception in JobCategoryRepositoryWork --> execute.");
				logger.debug("Exception in JobCategoryRepositoryWork --> execute.");
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
		public List<JobCategoryDTO> getList() {
			return list;
		}

	}
	

	@Override
	public List<JobCategoryDTO> getJobCategoryDescription(String userId, String companyCode, String recordCode) 
	{
		Session session = entityManager.unwrap(Session.class);
		JobCategoryDescriptionRepositoryWork work = new JobCategoryDescriptionRepositoryWork(userId, companyCode, recordCode);	
		session.doWork(work);
		List<JobCategoryDTO> list = work.getList();
		return list;
	}
	
	private static class JobCategoryDescriptionRepositoryWork implements Work {
		public Logger logger = LoggerFactory.getLogger(getClass());
		private List<JobCategoryDTO> list;
		private JobCategoryDTO jobCategoryDTO;
		String userId;
		String companyCode;
		String recordCode;
		CallableStatement cstmt = null;
		ResultSet rs = null;

		/**
		 * @param userId
		 */
		public JobCategoryDescriptionRepositoryWork(String userId, String companyCode, String recordCode) {
			super();
			this.userId = userId;
			this.companyCode=companyCode;
			this.recordCode=recordCode;
		}

		@Override
		public void execute(Connection connection) throws SQLException {

			list = new ArrayList<JobCategoryDTO>();
			jobCategoryDTO = new JobCategoryDTO();

				String query = "{call " + FacesUtils.SCHEMA_DBO
						+ ".spGetJobCategoryDescription('" + userId + "','" + companyCode + "','"+recordCode+"')}";

				System.out.println("Query in JobCategoryDescriptionRepositoryWork " + query);

				logger.debug("Query in JobCategoryDescriptionRepositoryWork " + query);

				cstmt = connection.prepareCall(query);
				rs = cstmt.executeQuery();

			try {
					if (rs != null) 
					{
						while (rs.next()) 
						{
							jobCategoryDTO.setJobCategoryDescription(rs.getString("Description").isEmpty()?rs.getString("JobCategoryCode"):rs.getString("Description"));														
							list.add(jobCategoryDTO);
							jobCategoryDTO = new JobCategoryDTO();
						}
					} 
					else 
					{
						list = new ArrayList<JobCategoryDTO>();
					}
					System.out.println("List size in JobCategoryDescriptionRepositoryWork " + list.size());
					logger.debug("List size in JobCategoryDescriptionRepositoryWork " + list.size());
			} 
			catch (Exception e) 
			{
				System.out.println("Exception in JobCategoryDescriptionRepositoryWork --> execute.");
				logger.debug("Exception in JobCategoryDescriptionRepositoryWork --> execute.");
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
		public List<JobCategoryDTO> getList() {
			return list;
		}	
	}
}
