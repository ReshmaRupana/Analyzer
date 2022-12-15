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
import com.disys.analyzer.model.dto.AnalyserCategory2DTO;
import com.disys.analyzer.repository.custom.AnalyserCategory2RepositoryCustom;

@Repository
@Transactional(readOnly = true)
public class AnalyserCategory2RepositoryImpl implements AnalyserCategory2RepositoryCustom{

	@PersistenceContext
	EntityManager entityManager;

	@Override
	public List<AnalyserCategory2DTO> getAnalyserCategory2List(String userId, String companyCode) 
	{
		Session session = entityManager.unwrap(Session.class);
		AnalyserCategory2RepositoryWork work = new AnalyserCategory2RepositoryWork(userId, companyCode);	
		session.doWork(work);
		List<AnalyserCategory2DTO> list = work.getList();
		return list;
	}
	
	private static class AnalyserCategory2RepositoryWork implements Work {
		public Logger logger = LoggerFactory.getLogger(getClass());
		private List<AnalyserCategory2DTO> list;
		private AnalyserCategory2DTO analyserCategory2DTO;
		String userId;
		String companyCode;
		String recordCode=Constants.STRING_CONSTANT_ALL;
		CallableStatement cstmt = null;
		ResultSet rs = null;

		/**
		 * @param userId
		 */
		public AnalyserCategory2RepositoryWork(String userId, String companyCode) {
			super();
			this.userId = userId;
			this.companyCode=companyCode;
		}

		@Override
		public void execute(Connection connection) throws SQLException {

			list = new ArrayList<AnalyserCategory2DTO>();
			analyserCategory2DTO = new AnalyserCategory2DTO();

				String query = "{call " + FacesUtils.SCHEMA_DBO
						+ ".spGetAnalyzerCategory2List('" + userId + "','" + companyCode + "','"+recordCode+"')}";

				System.out.println("Query in AnalyserCategory2RepositoryWork " + query);

				logger.debug("Query in AnalyserCategory2RepositoryWork " + query);

				cstmt = connection.prepareCall(query);
				rs = cstmt.executeQuery();

			try {
					if (rs != null) 
					{
						while (rs.next()) 
						{
							analyserCategory2DTO.setAnalyserCategory2Code(rs.getString("AnalyzerCategory2Code"));
							analyserCategory2DTO.setAnalyserCategory2Description(rs.getString("Description"));														
							list.add(analyserCategory2DTO);
							analyserCategory2DTO = new AnalyserCategory2DTO();
						}
					} 
					else 
					{
						list = new ArrayList<AnalyserCategory2DTO>();
					}
					System.out.println("List size in AnalyserCategory2RepositoryWork " + list.size());
					logger.debug("List size in AnalyserCategory2RepositoryWork " + list.size());
			} 
			catch (Exception e) 
			{
				System.out.println("Exception in AnalyserCategory2RepositoryWork --> execute.");
				logger.debug("Exception in AnalyserCategory2RepositoryWork --> execute.");
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
		public List<AnalyserCategory2DTO> getList() {
			return list;
		}

	}
	

	@Override
	public List<AnalyserCategory2DTO> getAnalyserCategory2Description(String userId, String companyCode, String recordCode) 
	{
		Session session = entityManager.unwrap(Session.class);
		AnalyserCategory2DescriptionRepositoryWork work = new AnalyserCategory2DescriptionRepositoryWork(userId, companyCode, recordCode);	
		session.doWork(work);
		List<AnalyserCategory2DTO> list = work.getList();
		return list;
	}
	
	private static class AnalyserCategory2DescriptionRepositoryWork implements Work {
		public Logger logger = LoggerFactory.getLogger(getClass());
		private List<AnalyserCategory2DTO> list;
		private AnalyserCategory2DTO analyserCategory2DTO;
		String userId;
		String companyCode;
		String recordCode;
		CallableStatement cstmt = null;
		ResultSet rs = null;

		/**
		 * @param userId
		 */
		public AnalyserCategory2DescriptionRepositoryWork(String userId, String companyCode, String recordCode) {
			super();
			this.userId = userId;
			this.companyCode=companyCode;
			this.recordCode=recordCode;
		}

		@Override
		public void execute(Connection connection) throws SQLException {

			list = new ArrayList<AnalyserCategory2DTO>();
			analyserCategory2DTO = new AnalyserCategory2DTO();

				String query = "{call " + FacesUtils.SCHEMA_DBO
						+ ".spGetAnalyzerCategory2Description('" + userId + "','" + companyCode + "','"+recordCode+"')}";

				System.out.println("Query in AnalyserCategory2DescriptionRepositoryWork " + query);

				logger.debug("Query in AnalyserCategory2DescriptionRepositoryWork " + query);

				cstmt = connection.prepareCall(query);
				rs = cstmt.executeQuery();

			try {
					if (rs != null) 
					{
						while (rs.next()) 
						{
							analyserCategory2DTO.setAnalyserCategory2Description(rs.getString("Description").isEmpty()?rs.getString("AnalyzerCategory2Code"):rs.getString("Description"));														
							list.add(analyserCategory2DTO);
							analyserCategory2DTO = new AnalyserCategory2DTO();
						}
					} 
					else 
					{
						list = new ArrayList<AnalyserCategory2DTO>();
					}
					System.out.println("List size in AnalyserCategory2DescriptionRepositoryWork " + list.size());
					logger.debug("List size in AnalyserCategory2DescriptionRepositoryWork " + list.size());
			} 
			catch (Exception e) 
			{
				System.out.println("Exception in AnalyserCategory2DescriptionRepositoryWork --> execute.");
				logger.debug("Exception in AnalyserCategory2DescriptionRepositoryWork --> execute.");
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
		public List<AnalyserCategory2DTO> getList() {
			return list;
		}

	
	}
}
