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
import com.disys.analyzer.model.dto.SkillCategoryDTO;
import com.disys.analyzer.repository.custom.SkillCategoryRepositoryCustom;

@Repository
@Transactional(readOnly = true)
public class SkillCategoryRepositoryImpl implements SkillCategoryRepositoryCustom{

	@PersistenceContext
	EntityManager entityManager;

	@Override
	public List<SkillCategoryDTO> getSkillCategoryList(String userId, String companyCode) 
	{
		Session session = entityManager.unwrap(Session.class);
		SkillCategoryRepositoryWork work = new SkillCategoryRepositoryWork(userId, companyCode);	
		session.doWork(work);
		List<SkillCategoryDTO> list = work.getList();
		return list;
	}
	
	private static class SkillCategoryRepositoryWork implements Work {
		public Logger logger = LoggerFactory.getLogger(getClass());
		private List<SkillCategoryDTO> list;
		private SkillCategoryDTO skillCategoryDTO;
		String userId;
		String companyCode;
		String recordCode=Constants.STRING_CONSTANT_ALL;
		CallableStatement cstmt = null;
		ResultSet rs = null;

		/**
		 * @param userId
		 */
		public SkillCategoryRepositoryWork(String userId, String companyCode) {
			super();
			this.userId = userId;
			this.companyCode=companyCode;
		}

		@Override
		public void execute(Connection connection) throws SQLException {

			list = new ArrayList<SkillCategoryDTO>();
			skillCategoryDTO = new SkillCategoryDTO();

				String query = "{call " + FacesUtils.SCHEMA_DBO
						+ ".spGetSkillCategoryList('" + userId + "','" + companyCode + "','"+recordCode+"')}";

				System.out.println("Query in SkillCategoryRepositoryWork " + query);

				logger.debug("Query in SkillCategoryRepositoryWork " + query);

				cstmt = connection.prepareCall(query);
				rs = cstmt.executeQuery();

			try {
					if (rs != null) 
					{
						while (rs.next()) 
						{
							skillCategoryDTO.setSkillCategoryCode(rs.getString("SkillCategoryCode"));
							skillCategoryDTO.setSkillCategoryDescription(rs.getString("Description"));														
							list.add(skillCategoryDTO);
							skillCategoryDTO = new SkillCategoryDTO();
						}
					} 
					else 
					{
						list = new ArrayList<SkillCategoryDTO>();
					}
					System.out.println("List size in SkillCategoryRepositoryWork " + list.size());
					logger.debug("List size in SkillCategoryRepositoryWork " + list.size());
			} 
			catch (Exception e) 
			{
				System.out.println("Exception in SkillCategoryRepositoryWork --> execute.");
				logger.debug("Exception in SkillCategoryRepositoryWork --> execute.");
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
		public List<SkillCategoryDTO> getList() {
			return list;
		}

	}
	

	@Override
	public List<SkillCategoryDTO> getSkillCategoryDescription(String userId, String companyCode, String recordCode) 
	{
		Session session = entityManager.unwrap(Session.class);
		SkillCategoryDescriptionRepositoryWork work = new SkillCategoryDescriptionRepositoryWork(userId, companyCode, recordCode);	
		session.doWork(work);
		List<SkillCategoryDTO> list = work.getList();
		return list;
	}
	
	private static class SkillCategoryDescriptionRepositoryWork implements Work {
		public Logger logger = LoggerFactory.getLogger(getClass());
		private List<SkillCategoryDTO> list;
		private SkillCategoryDTO skillCategoryDTO;
		String userId;
		String companyCode;
		String recordCode;
		CallableStatement cstmt = null;
		ResultSet rs = null;

		/**
		 * @param userId
		 */
		public SkillCategoryDescriptionRepositoryWork(String userId, String companyCode, String recordCode) {
			super();
			this.userId = userId;
			this.companyCode=companyCode;
			this.recordCode=recordCode;
		}

		@Override
		public void execute(Connection connection) throws SQLException {

			list = new ArrayList<SkillCategoryDTO>();
			skillCategoryDTO = new SkillCategoryDTO();

				String query = "{call " + FacesUtils.SCHEMA_DBO
						+ ".spGetSkillCategoryDescription('" + userId + "','" + companyCode + "','"+recordCode+"')}";

				System.out.println("Query in SkillCategoryDescriptionRepositoryWork " + query);

				logger.debug("Query in SkillCategoryDescriptionRepositoryWork " + query);

				cstmt = connection.prepareCall(query);
				rs = cstmt.executeQuery();

			try {
					if (rs != null) 
					{
						while (rs.next()) 
						{
							skillCategoryDTO.setSkillCategoryDescription(rs.getString("Description").isEmpty()?rs.getString("SkillCategoryCode"):rs.getString("Description"));														
							list.add(skillCategoryDTO);
							skillCategoryDTO = new SkillCategoryDTO();
						}
					} 
					else 
					{
						list = new ArrayList<SkillCategoryDTO>();
					}
					System.out.println("List size in SkillCategoryDescriptionRepositoryWork " + list.size());
					logger.debug("List size in SkillCategoryDescriptionRepositoryWork " + list.size());
			} 
			catch (Exception e) 
			{
				System.out.println("Exception in SkillCategoryDescriptionRepositoryWork --> execute.");
				logger.debug("Exception in SkillCategoryDescriptionRepositoryWork --> execute.");
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
		public List<SkillCategoryDTO> getList() {
			return list;
		}

	
	}
}
