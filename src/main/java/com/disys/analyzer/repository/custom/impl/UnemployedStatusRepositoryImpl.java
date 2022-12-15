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
import com.disys.analyzer.model.dto.UnemployedStatusDTO;
import com.disys.analyzer.repository.custom.UnemployedStatusRepositoryCustom;

@Repository
@Transactional(readOnly = true)
public class UnemployedStatusRepositoryImpl implements UnemployedStatusRepositoryCustom{

	@PersistenceContext
	EntityManager entityManager;

	@Override
	public List<UnemployedStatusDTO> getUnemployedStatusList(String userId, String companyCode) 
	{
		Session session = entityManager.unwrap(Session.class);
		UnemployedStatusRepositoryWork work = new UnemployedStatusRepositoryWork(userId, companyCode);	
		session.doWork(work);
		List<UnemployedStatusDTO> list = work.getList();
		return list;
	}
	
	private static class UnemployedStatusRepositoryWork implements Work {
		public Logger logger = LoggerFactory.getLogger(getClass());
		private List<UnemployedStatusDTO> list;
		private UnemployedStatusDTO unemployedStatusDTO;
		String userId;
		String companyCode;
		String recordCode=Constants.STRING_CONSTANT_ALL;
		CallableStatement cstmt = null;
		ResultSet rs = null;

		/**
		 * @param userId
		 */
		public UnemployedStatusRepositoryWork(String userId, String companyCode) {
			super();
			this.userId = userId;
			this.companyCode=companyCode;
		}

		@Override
		public void execute(Connection connection) throws SQLException {

			list = new ArrayList<UnemployedStatusDTO>();
			unemployedStatusDTO = new UnemployedStatusDTO();

				String query = "{call " + FacesUtils.SCHEMA_DBO
						+ ".spGetUnemployedStatusList('" + userId + "','" + companyCode + "','"+recordCode+"')}";

				System.out.println("Query in UnemployedStatusRepositoryWork " + query);

				logger.debug("Query in UnemployedStatusRepositoryWork " + query);

				cstmt = connection.prepareCall(query);
				rs = cstmt.executeQuery();

			try {
					if (rs != null) 
					{
						while (rs.next()) 
						{
							unemployedStatusDTO.setUnemployedStatusCode(rs.getString("UnemployedStatusCode"));
							unemployedStatusDTO.setUnemployedStatusDescription(rs.getString("Description"));														
							list.add(unemployedStatusDTO);
							unemployedStatusDTO = new UnemployedStatusDTO();
						}
					} 
					else 
					{
						list = new ArrayList<UnemployedStatusDTO>();
					}
					System.out.println("List size in UnemployedStatusRepositoryWork " + list.size());
					logger.debug("List size in UnemployedStatusRepositoryWork " + list.size());
			} 
			catch (Exception e) 
			{
				System.out.println("Exception in UnemployedStatusRepositoryWork --> execute.");
				logger.debug("Exception in UnemployedStatusRepositoryWork --> execute.");
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
		public List<UnemployedStatusDTO> getList() {
			return list;
		}

	}
	

	@Override
	public List<UnemployedStatusDTO> getUnemployedStatusDescription(String userId, String companyCode, String recordCode) 
	{
		Session session = entityManager.unwrap(Session.class);
		UnemployedStatusDescriptionRepositoryWork work = new UnemployedStatusDescriptionRepositoryWork(userId, companyCode, recordCode);	
		session.doWork(work);
		List<UnemployedStatusDTO> list = work.getList();
		return list;
	}
	
	private static class UnemployedStatusDescriptionRepositoryWork implements Work {
		public Logger logger = LoggerFactory.getLogger(getClass());
		private List<UnemployedStatusDTO> list;
		private UnemployedStatusDTO unemployedStatusDTO;
		String userId;
		String companyCode;
		String recordCode;
		CallableStatement cstmt = null;
		ResultSet rs = null;

		/**
		 * @param userId
		 */
		public UnemployedStatusDescriptionRepositoryWork(String userId, String companyCode, String recordCode) {
			super();
			this.userId = userId;
			this.companyCode=companyCode;
			this.recordCode=recordCode;
		}

		@Override
		public void execute(Connection connection) throws SQLException {

			list = new ArrayList<UnemployedStatusDTO>();
			unemployedStatusDTO = new UnemployedStatusDTO();

				String query = "{call " + FacesUtils.SCHEMA_DBO
						+ ".spGetUnemployedStatusDescription('" + userId + "','" + companyCode + "','"+recordCode+"')}";

				System.out.println("Query in UnemployedStatusDescriptionRepositoryWork " + query);

				logger.debug("Query in UnemployedStatusDescriptionRepositoryWork " + query);

				cstmt = connection.prepareCall(query);
				rs = cstmt.executeQuery();

			try {
					if (rs != null) 
					{
						while (rs.next()) 
						{
							unemployedStatusDTO.setUnemployedStatusDescription(rs.getString("Description").isEmpty()?rs.getString("UnemployedStatusCode"):rs.getString("Description"));														
							list.add(unemployedStatusDTO);
							unemployedStatusDTO = new UnemployedStatusDTO();
						}
					} 
					else 
					{
						list = new ArrayList<UnemployedStatusDTO>();
					}
					System.out.println("List size in UnemployedStatusDescriptionRepositoryWork " + list.size());
					logger.debug("List size in UnemployedStatusDescriptionRepositoryWork " + list.size());
			} 
			catch (Exception e) 
			{
				System.out.println("Exception in UnemployedStatusDescriptionRepositoryWork --> execute.");
				logger.debug("Exception in UnemployedStatusDescriptionRepositoryWork --> execute.");
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
		public List<UnemployedStatusDTO> getList() {
			return list;
		}

	
	}
}
