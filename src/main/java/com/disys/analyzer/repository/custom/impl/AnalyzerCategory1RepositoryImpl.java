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
import com.disys.analyzer.model.dto.AnalyzerCategory1DTO;
import com.disys.analyzer.repository.custom.AnalyzerCategory1RepositoryCustom;

@Repository
@Transactional(readOnly = true)
public class AnalyzerCategory1RepositoryImpl implements AnalyzerCategory1RepositoryCustom{

	@PersistenceContext
	EntityManager entityManager;

	@Override
	public List<AnalyzerCategory1DTO> getAnalyzerCategory1List(String userId, String companyCode) 
	{
		Session session = entityManager.unwrap(Session.class);
		AnalyzerCategory1RepositoryWork work = new AnalyzerCategory1RepositoryWork(userId, companyCode);	
		session.doWork(work);
		List<AnalyzerCategory1DTO> list = work.getList();
		return list;
	}
	
	private static class AnalyzerCategory1RepositoryWork implements Work {
		public Logger logger = LoggerFactory.getLogger(getClass());
		private List<AnalyzerCategory1DTO> list;
		private AnalyzerCategory1DTO analyzerCategory1DTO;
		String userId;
		String companyCode;
		String recordCode=Constants.STRING_CONSTANT_ALL;
		CallableStatement cstmt = null;
		ResultSet rs = null;

		/**
		 * @param userId
		 */
		public AnalyzerCategory1RepositoryWork(String userId, String companyCode) {
			super();
			this.userId = userId;
			this.companyCode=companyCode;
		}

		@Override
		public void execute(Connection connection) throws SQLException {

			list = new ArrayList<AnalyzerCategory1DTO>();
			analyzerCategory1DTO = new AnalyzerCategory1DTO();

				String query = "{call " + FacesUtils.SCHEMA_DBO
						+ ".spGetAnalyzerCategory1List('" + userId + "','" + companyCode + "','"+recordCode+"')}";

				System.out.println("Query in AnalyzerCategory1RepositoryWork " + query);

				logger.debug("Query in AnalyzerCategory1RepositoryWork " + query);

				cstmt = connection.prepareCall(query);
				rs = cstmt.executeQuery();

			try {
					if (rs != null) 
					{
						while (rs.next()) 
						{
							analyzerCategory1DTO.setAnalyzerCategory1Code(rs.getString("AnalyzerCategory1Code"));
							analyzerCategory1DTO.setAnalyzerCategory1Description(rs.getString("Description"));														
							list.add(analyzerCategory1DTO);
							analyzerCategory1DTO = new AnalyzerCategory1DTO();
						}
					} 
					else 
					{
						list = new ArrayList<AnalyzerCategory1DTO>();
					}
					System.out.println("List size in AnalyzerCategory1RepositoryWork " + list.size());
					logger.debug("List size in AnalyzerCategory1RepositoryWork " + list.size());
			} 
			catch (Exception e) 
			{
				System.out.println("Exception in AnalyzerCategory1RepositoryWork --> execute.");
				logger.debug("Exception in AnalyzerCategory1RepositoryWork --> execute.");
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
		public List<AnalyzerCategory1DTO> getList() {
			return list;
		}

	}
	

	@Override
	public List<AnalyzerCategory1DTO> getAnalyzerCategory1Description(String userId, String companyCode, String recordCode) 
	{
		Session session = entityManager.unwrap(Session.class);
		AnalyzerCategory1DescriptionRepositoryWork work = new AnalyzerCategory1DescriptionRepositoryWork(userId, companyCode, recordCode);	
		session.doWork(work);
		List<AnalyzerCategory1DTO> list = work.getList();
		return list;
	}
	
	private static class AnalyzerCategory1DescriptionRepositoryWork implements Work {
		public Logger logger = LoggerFactory.getLogger(getClass());
		private List<AnalyzerCategory1DTO> list;
		private AnalyzerCategory1DTO analyzerCategory1DTO;
		String userId;
		String companyCode;
		String recordCode;
		CallableStatement cstmt = null;
		ResultSet rs = null;

		/**
		 * @param userId
		 */
		public AnalyzerCategory1DescriptionRepositoryWork(String userId, String companyCode, String recordCode) {
			super();
			this.userId = userId;
			this.companyCode=companyCode;
			this.recordCode=recordCode;
		}

		@Override
		public void execute(Connection connection) throws SQLException {

			list = new ArrayList<AnalyzerCategory1DTO>();
			analyzerCategory1DTO = new AnalyzerCategory1DTO();

				String query = "{call " + FacesUtils.SCHEMA_DBO
						+ ".spGetAnalyzerCategory1Description('" + userId + "','" + companyCode + "','"+recordCode+"')}";

				System.out.println("Query in AnalyzerCategory1DescriptionRepositoryWork " + query);

				logger.debug("Query in AnalyzerCategory1DescriptionRepositoryWork " + query);

				cstmt = connection.prepareCall(query);
				rs = cstmt.executeQuery();

			try {
					if (rs != null) 
					{
						while (rs.next()) 
						{
							analyzerCategory1DTO.setAnalyzerCategory1Description(rs.getString("Description").isEmpty()?rs.getString("AnalyzerCategory1Code"):rs.getString("Description"));														
							list.add(analyzerCategory1DTO);
							analyzerCategory1DTO = new AnalyzerCategory1DTO();
						}
					} 
					else 
					{
						list = new ArrayList<AnalyzerCategory1DTO>();
					}
					System.out.println("List size in AnalyzerCategory1DescriptionRepositoryWork " + list.size());
					logger.debug("List size in AnalyzerCategory1DescriptionRepositoryWork " + list.size());
			} 
			catch (Exception e) 
			{
				System.out.println("Exception in AnalyzerCategory1DescriptionRepositoryWork --> execute.");
				logger.debug("Exception in AnalyzerCategory1DescriptionRepositoryWork --> execute.");
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
		public List<AnalyzerCategory1DTO> getList() {
			return list;
		}

	
	}
}
