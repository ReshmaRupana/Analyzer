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
import com.disys.analyzer.model.dto.CompanyDTO;
import com.disys.analyzer.repository.custom.CompanyRepositoryCustom;

@Repository
@Transactional(readOnly = true)
public class CompanyRepositoryImpl implements CompanyRepositoryCustom{

	@PersistenceContext
	EntityManager entityManager;


	
	/* (non-Javadoc)
	 * @see com.disys.analyzer.repository.custom.DealTypeRepositoryCustom#getDealType()
	 */
	@Override
	public List<CompanyDTO> getCompanyList(String userId) 
	{
		Session session = entityManager.unwrap(Session.class);
		CompanyRepositoryWork work = new CompanyRepositoryWork(userId);	
		session.doWork(work);
		List<CompanyDTO> list = work.getList();
		return list;
	}
	
	private static class CompanyRepositoryWork implements Work {
		public Logger logger = LoggerFactory.getLogger(getClass());
		private List<CompanyDTO> list;
		private CompanyDTO company;
		String userId;
		String recordCode=Constants.STRING_CONSTANT_ALL;
		CallableStatement cstmt = null;
		ResultSet rs = null;

		/**
		 * @param userId
		 */
		public CompanyRepositoryWork(String userId) {
			super();
			this.userId = userId;
		}

		@Override
		public void execute(Connection connection) throws SQLException {

			list = new ArrayList<CompanyDTO>();
			company = new CompanyDTO();

				String query = "{call " + FacesUtils.SCHEMA_DBO
						+ ".spGetCompanyList('" + userId + "','"+recordCode+"')}";

				System.out.println("Query in CompanyRepositoryWork " + query);

				logger.debug("Query in CompanyRepositoryWork " + query);

				cstmt = connection.prepareCall(query);
				rs = cstmt.executeQuery();

			try {
					if (rs != null) 
					{
						while (rs.next()) 
						{
							company.setCompanyCode(rs.getString("CompanyCode"));
							company.setCompanyDescription(rs.getString("Description"));														
							list.add(company);
							company = new CompanyDTO();
						}
					} 
					else 
					{
						list = new ArrayList<CompanyDTO>();
					}
					System.out.println("List size in CompanyRepositoryWork " + list.size());
					logger.debug("List size in CompanyRepositoryWork " + list.size());
			} 
			catch (Exception e) 
			{
				System.out.println("Exception in CompanyRepositoryWork --> execute.");
				logger.debug("Exception in CompanyRepositoryWork --> execute.");
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
		public List<CompanyDTO> getList() {
			return list;
		}

	
	}
	/* (non-Javadoc)
	 * @see com.disys.analyzer.repository.custom.CompanyDescRepositoryWork#getCompanyDesc()
	 */
	@Override
	public List<CompanyDTO> getCompanyDescription(String userId, String recordCode) 
	{
		Session session = entityManager.unwrap(Session.class);
		CompanyDescriptioncRepositoryWork work = new CompanyDescriptioncRepositoryWork(userId, recordCode);	
		session.doWork(work);
		List<CompanyDTO> list = work.getList();
		return list;
	}
	
	private static class CompanyDescriptioncRepositoryWork implements Work {
		public Logger logger = LoggerFactory.getLogger(getClass());
		private List<CompanyDTO> list;
		private CompanyDTO companyDescription;
		String userId;
		String recordCode;
		CallableStatement cstmt = null;
		ResultSet rs = null;

		/**
		 * @param userId
		 */
		public CompanyDescriptioncRepositoryWork(String userId, String recordCode) {
			super();
			this.userId = userId;
			this.recordCode=recordCode;
		}

		@Override
		public void execute(Connection connection) throws SQLException {

			list = new ArrayList<CompanyDTO>();
			companyDescription = new CompanyDTO();

				String query = "{call " + FacesUtils.SCHEMA_DBO
						+ ".spGetCompanyDescription('" + userId + "','"+recordCode+"')}";

				System.out.println("Query in CompanyDescriptioncRepositoryWork " + query);

				logger.debug("Query in CompanyDescriptioncRepositoryWork " + query);

				cstmt = connection.prepareCall(query);
				rs = cstmt.executeQuery();

			try {
					if (rs != null) 
					{
						while (rs.next()) 
						{
							companyDescription.setCompanyDescription(rs.getString("Description").isEmpty()?rs.getString("CompanyCode"):rs.getString("Description"));														
							list.add(companyDescription);
							companyDescription = new CompanyDTO();
						}
					} 
					else 
					{
						list = new ArrayList<CompanyDTO>();
					}
					System.out.println("List size in CompanyDescriptioncRepositoryWork " + list.size());
					logger.debug("List size in CompanyDescriptionRepositoryWork " + list.size());
			} 
			catch (Exception e) 
			{
				System.out.println("Exception in CompanyDescriptioncRepositoryWork --> execute.");
				logger.debug("Exception in CompanyDescriptioncRepositoryWork --> execute.");
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
		public List<CompanyDTO> getList() {
			return list;
		}

	
	}
}
