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
import com.disys.analyzer.model.dto.GenderDTO;
import com.disys.analyzer.repository.custom.GenderRepositoryCustom;

@Repository
@Transactional(readOnly = true)
public class GenderRepositoryImpl implements GenderRepositoryCustom{

	@PersistenceContext
	EntityManager entityManager;

	@Override
	public List<GenderDTO> getGenderList(String userId, String companyCode) 
	{
		Session session = entityManager.unwrap(Session.class);
		GenderRepositoryWork work = new GenderRepositoryWork(userId, companyCode);	
		session.doWork(work);
		List<GenderDTO> list = work.getList();
		return list;
	}
	
	private static class GenderRepositoryWork implements Work {
		public Logger logger = LoggerFactory.getLogger(getClass());
		private List<GenderDTO> list;
		private GenderDTO genderDTO;
		String userId;
		String companyCode;
		String recordCode=Constants.STRING_CONSTANT_ALL;
		CallableStatement cstmt = null;
		ResultSet rs = null;

		/**
		 * @param userId
		 */
		public GenderRepositoryWork(String userId, String companyCode) {
			super();
			this.userId = userId;
			this.companyCode=companyCode;
		}

		@Override
		public void execute(Connection connection) throws SQLException {

			list = new ArrayList<GenderDTO>();
			genderDTO = new GenderDTO();

				String query = "{call " + FacesUtils.SCHEMA_DBO
						+ ".spGetGenderList('" + userId + "','" + companyCode + "','"+recordCode+"')}";

				System.out.println("Query in GenderRepositoryWork " + query);

				logger.debug("Query in GenderRepositoryWork " + query);

				cstmt = connection.prepareCall(query);
				rs = cstmt.executeQuery();

			try {
					if (rs != null) 
					{
						while (rs.next()) 
						{
							genderDTO.setGenderCode(rs.getString("GenderCode"));
							genderDTO.setGenderDescription(rs.getString("Description"));														
							list.add(genderDTO);
							genderDTO = new GenderDTO();
						}
					} 
					else 
					{
						list = new ArrayList<GenderDTO>();
					}
					System.out.println("List size in GenderRepositoryWork " + list.size());
					logger.debug("List size in GenderRepositoryWork " + list.size());
			} 
			catch (Exception e) 
			{
				System.out.println("Exception in GenderRepositoryWork --> execute.");
				logger.debug("Exception in GenderRepositoryWork --> execute.");
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
		public List<GenderDTO> getList() {
			return list;
		}

	}
	

	@Override
	public List<GenderDTO> getGenderDescription(String userId, String companyCode, String recordCode) 
	{
		Session session = entityManager.unwrap(Session.class);
		GenderDescriptionRepositoryWork work = new GenderDescriptionRepositoryWork(userId, companyCode, recordCode);	
		session.doWork(work);
		List<GenderDTO> list = work.getList();
		return list;
	}
	
	private static class GenderDescriptionRepositoryWork implements Work {
		public Logger logger = LoggerFactory.getLogger(getClass());
		private List<GenderDTO> list;
		private GenderDTO genderDTO;
		String userId;
		String companyCode;
		String recordCode;
		CallableStatement cstmt = null;
		ResultSet rs = null;

		/**
		 * @param userId
		 */
		public GenderDescriptionRepositoryWork(String userId, String companyCode, String recordCode) {
			super();
			this.userId = userId;
			this.companyCode=companyCode;
			this.recordCode=recordCode;
		}

		@Override
		public void execute(Connection connection) throws SQLException {

			list = new ArrayList<GenderDTO>();
			genderDTO = new GenderDTO();

				String query = "{call " + FacesUtils.SCHEMA_DBO
						+ ".spGetGenderDescription('" + userId + "','" + companyCode + "','"+recordCode+"')}";

				System.out.println("Query in GenderDescriptionRepositoryWork " + query);

				logger.debug("Query in GenderDescriptionRepositoryWork " + query);

				cstmt = connection.prepareCall(query);
				rs = cstmt.executeQuery();

			try {
					if (rs != null) 
					{
						while (rs.next()) 
						{
							genderDTO.setGenderDescription(rs.getString("Description").isEmpty()?rs.getString("GenderCode"):rs.getString("Description"));														
							list.add(genderDTO);
							genderDTO = new GenderDTO();
						}
					} 
					else 
					{
						list = new ArrayList<GenderDTO>();
					}
					System.out.println("List size in GenderDescriptionRepositoryWork " + list.size());
					logger.debug("List size in GenderDescriptionRepositoryWork " + list.size());
			} 
			catch (Exception e) 
			{
				System.out.println("Exception in GenderDescriptionRepositoryWork --> execute.");
				logger.debug("Exception in GenderDescriptionRepositoryWork --> execute.");
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
		public List<GenderDTO> getList() {
			return list;
		}

	
	}
}
