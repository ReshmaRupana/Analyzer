/**
 * 
 */
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
import com.disys.analyzer.model.dto.RecruitingClassificationDTO;
import com.disys.analyzer.repository.custom.RecruitingClassificationRepositoryCustom;


/**
 * @author Sajid
 * 
 *
 */
@Repository
@Transactional(readOnly = true)
public class RecruitingClassificationRepositoryImpl implements RecruitingClassificationRepositoryCustom
{
	
	public Logger logger = LoggerFactory.getLogger(getClass());
	
	@PersistenceContext
	EntityManager entityManager;
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.disys.analyzer.repository.custom.RecrutingClassificationRepositoryCustom
	 * #getRecruitingClassificationsList(java.lang.String, java.lang.Boolean)
	 */
	@Override
	public List<RecruitingClassificationDTO> getRecruitingClassificationsList(String userId, Boolean fetchWithAll)
	{
		Session session = entityManager.unwrap(Session.class);
		RecruitingClassificationWork work = new RecruitingClassificationWork(userId, fetchWithAll);	
		session.doWork(work);
		List<RecruitingClassificationDTO> list = work.getList();
		return list;
	}
	
	private static class RecruitingClassificationWork implements Work {
		public Logger logger = LoggerFactory.getLogger(getClass());
		private List<RecruitingClassificationDTO> list;
		RecruitingClassificationDTO recruitingClassification;
		String userId;
		Boolean fetchWithAll;
		
		CallableStatement cstmt = null;
		ResultSet rs = null;
		
		/**
		 * @param userId
		 */
		public RecruitingClassificationWork(String userId, Boolean fetchWithAll) {
			super();
			this.userId = userId;
			this.fetchWithAll = fetchWithAll;
		}

		@Override
		public void execute(Connection connection) throws SQLException {

			list = new ArrayList<RecruitingClassificationDTO>();
			recruitingClassification = new RecruitingClassificationDTO();

				String value = "ALL";
				if(fetchWithAll){
					value = "ALL";
				}else {
					value = "NONE";
				}
				String query = "{call " + FacesUtils.SCHEMA_DBO
						+ ".getRecruitingClassificationList('" + userId + "'" + ",'" + value + "')}";
				
				System.out.println("Query in RecruitingClassificationWork " + query);

				logger.debug("Query in RecruitingClassificationWork " + query);

				cstmt = connection.prepareCall(query);
				rs = cstmt.executeQuery();

			try {
					if (rs != null) 
					{
						while (rs.next()) 
						{
							recruitingClassification.setRecruitingClassificationId(rs.getInt("RECRUITINGCLASSIFICATIONID"));
							recruitingClassification.setRecruitingClassificationName(rs.getString("RECRUITINGCLASSIFICATIONNAME"));
							recruitingClassification.setRecruitingClassificationValue(rs.getString("RECRUITINGCLASSIFICATIONVALUE"));
							recruitingClassification.setStatus(rs.getInt("STATUS"));
							
							list.add(recruitingClassification);
							recruitingClassification = new RecruitingClassificationDTO();
						}
					} 
					else 
					{
						list = new ArrayList<RecruitingClassificationDTO>();
					}
					System.out.println("List size in RecruitingClassificationWork " + list.size());
					logger.debug("List size in RecruitingClassificationWork " + list.size());
			} 
			catch (Exception e) 
			{
				System.out.println("Exception in RecruitingClassificationWork --> execute.");
				logger.debug("Exception in RecruitingClassificationWork --> execute.");
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
		public List<RecruitingClassificationDTO> getList() {
			return list;
		}

	}
	

	@Override
	public List<RecruitingClassificationDTO> getRecruitingClassificationsList(String userId, String companyCode) 
	{
		Session session = entityManager.unwrap(Session.class);
		RecruitingClassificationRepositoryWork work = new RecruitingClassificationRepositoryWork(userId, companyCode);	
		session.doWork(work);
		List<RecruitingClassificationDTO> list = work.getList();
		return list;
	}
	
	private static class RecruitingClassificationRepositoryWork implements Work {
		public Logger logger = LoggerFactory.getLogger(getClass());
		private List<RecruitingClassificationDTO> list;
		private RecruitingClassificationDTO recruitingClassificationDTO;
		String userId;
		String companyCode;
		String recordCode=Constants.STRING_CONSTANT_ALL;
		CallableStatement cstmt = null;
		ResultSet rs = null;

		/**
		 * @param userId
		 */
		public RecruitingClassificationRepositoryWork(String userId, String companyCode) {
			super();
			this.userId = userId;
			this.companyCode=companyCode;
		}

		@Override
		public void execute(Connection connection) throws SQLException {

			list = new ArrayList<RecruitingClassificationDTO>();
			recruitingClassificationDTO = new RecruitingClassificationDTO();

				String query = "{call " + FacesUtils.SCHEMA_DBO
						+ ".spGetRecruitingClassificationList('" + userId + "','" + companyCode + "','"+recordCode+"')}";

				System.out.println("Query in RecruitingClassificationRepositoryWork " + query);

				logger.debug("Query in RecruitingClassificationRepositoryWork " + query);

				cstmt = connection.prepareCall(query);
				rs = cstmt.executeQuery();

			try {
					if (rs != null) 
					{
						while (rs.next()) 
						{
							recruitingClassificationDTO.setRecruitingClassificationValue(rs.getString("RecruitingClassificationValue"));
							recruitingClassificationDTO.setRecruitingClassificationName(rs.getString("RecruitingClassificationName"));														
							list.add(recruitingClassificationDTO);
							recruitingClassificationDTO = new RecruitingClassificationDTO();
						}
					} 
					else 
					{
						list = new ArrayList<RecruitingClassificationDTO>();
					}
					System.out.println("List size in RecruitingClassificationRepositoryWork " + list.size());
					logger.debug("List size in RecruitingClassificationRepositoryWork " + list.size());
			} 
			catch (Exception e) 
			{
				System.out.println("Exception in RecruitingClassificationRepositoryWork --> execute.");
				logger.debug("Exception in RecruitingClassificationRepositoryWork --> execute.");
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
		public List<RecruitingClassificationDTO> getList() {
			return list;
		}

	}
	

	@Override
	public List<RecruitingClassificationDTO> getRecruitingClassificationsDescription(String userId, String companyCode, String recordCode) 
	{
		Session session = entityManager.unwrap(Session.class);
		RecruitingClassificationDescriptionRepositoryWork work = new RecruitingClassificationDescriptionRepositoryWork(userId, companyCode, recordCode);	
		session.doWork(work);
		List<RecruitingClassificationDTO> list = work.getList();
		return list;
	}
	
	private static class RecruitingClassificationDescriptionRepositoryWork implements Work {
		public Logger logger = LoggerFactory.getLogger(getClass());
		private List<RecruitingClassificationDTO> list;
		private RecruitingClassificationDTO recruitingClassificationDTO;
		String userId;
		String companyCode;
		String recordCode;
		CallableStatement cstmt = null;
		ResultSet rs = null;

		/**
		 * @param userId
		 */
		public RecruitingClassificationDescriptionRepositoryWork(String userId, String companyCode, String recordCode) {
			super();
			this.userId = userId;
			this.companyCode=companyCode;
			this.recordCode=recordCode;
		}

		@Override
		public void execute(Connection connection) throws SQLException {

			list = new ArrayList<RecruitingClassificationDTO>();
			recruitingClassificationDTO = new RecruitingClassificationDTO();

				String query = "{call " + FacesUtils.SCHEMA_DBO
						+ ".spGetRecruitingClassificationDescription('" + userId + "','" + companyCode + "','"+recordCode+"')}";

				System.out.println("Query in RecruitingClassificationDescriptionRepositoryWork " + query);

				logger.debug("Query in RecruitingClassificationDescriptionRepositoryWork " + query);

				cstmt = connection.prepareCall(query);
				rs = cstmt.executeQuery();

			try {
					if (rs != null) 
					{
						while (rs.next()) 
						{
							recruitingClassificationDTO.setRecruitingClassificationName(rs.getString("RecruitingClassificationName").isEmpty()?rs.getString("RecruitingClassificationValue"):rs.getString("RecruitingClassificationName"));														
							list.add(recruitingClassificationDTO);
							recruitingClassificationDTO = new RecruitingClassificationDTO();
						}
					} 
					else 
					{
						list = new ArrayList<RecruitingClassificationDTO>();
					}
					System.out.println("List size in RecruitingClassificationDescriptionRepositoryWork " + list.size());
					logger.debug("List size in RecruitingClassificationDescriptionRepositoryWork " + list.size());
			} 
			catch (Exception e) 
			{
				System.out.println("Exception in RecruitingClassificationDescriptionRepositoryWork --> execute.");
				logger.debug("Exception in RecruitingClassificationDescriptionRepositoryWork --> execute.");
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
		public List<RecruitingClassificationDTO> getList() {
			return list;
		}

	
	}
}
