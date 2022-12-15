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
import com.disys.analyzer.model.dto.EmployeeCategoryDTO;
import com.disys.analyzer.repository.custom.EmployeeCategoryRepositoryCustom;

@Repository
@Transactional(readOnly = true)
public class EmployeeCategoryRepositoryImpl implements EmployeeCategoryRepositoryCustom{

	@PersistenceContext
	EntityManager entityManager;

	@Override
	public List<EmployeeCategoryDTO> getEmployeeCategoryList(String userId, String companyCode) 
	{
		Session session = entityManager.unwrap(Session.class);
		EmployeeCategoryRepositoryWork work = new EmployeeCategoryRepositoryWork(userId, companyCode);	
		session.doWork(work);
		List<EmployeeCategoryDTO> list = work.getList();
		return list;
	}
	
	private static class EmployeeCategoryRepositoryWork implements Work {
		public Logger logger = LoggerFactory.getLogger(getClass());
		private List<EmployeeCategoryDTO> list;
		private EmployeeCategoryDTO employeeCategoryDTO;
		String userId;
		String companyCode;
		String recordCode=Constants.STRING_CONSTANT_ALL;
		CallableStatement cstmt = null;
		ResultSet rs = null;

		/**
		 * @param userId
		 */
		public EmployeeCategoryRepositoryWork(String userId, String companyCode) {
			super();
			this.userId = userId;
			this.companyCode=companyCode;
		}

		@Override
		public void execute(Connection connection) throws SQLException {

			list = new ArrayList<EmployeeCategoryDTO>();
			employeeCategoryDTO = new EmployeeCategoryDTO();

				String query = "{call " + FacesUtils.SCHEMA_DBO
						+ ".spGetEmployeeCategoryList('" + userId + "','" + companyCode + "','"+recordCode+"')}";

				System.out.println("Query in EmployeeCategoryRepositoryWork " + query);

				logger.debug("Query in EmployeeCategoryRepositoryWork " + query);

				cstmt = connection.prepareCall(query);
				rs = cstmt.executeQuery();

			try {
					if (rs != null) 
					{
						while (rs.next()) 
						{
							employeeCategoryDTO.setEmployeeCategoryCode(rs.getString("EmployeeCategoryCode"));
							employeeCategoryDTO.setEmployeeCategoryDescription(rs.getString("Description"));														
							list.add(employeeCategoryDTO);
							employeeCategoryDTO = new EmployeeCategoryDTO();
						}
					} 
					else 
					{
						list = new ArrayList<EmployeeCategoryDTO>();
					}
					System.out.println("List size in EmployeeCategoryRepositoryWork " + list.size());
					logger.debug("List size in EmployeeCategoryRepositoryWork " + list.size());
			} 
			catch (Exception e) 
			{
				System.out.println("Exception in EmployeeCategoryRepositoryWork --> execute.");
				logger.debug("Exception in EmployeeCategoryRepositoryWork --> execute.");
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
		public List<EmployeeCategoryDTO> getList() {
			return list;
		}

	}
	

	@Override
	public List<EmployeeCategoryDTO> getEmployeeCategoryDescription(String userId, String companyCode, String recordCode) 
	{
		Session session = entityManager.unwrap(Session.class);
		EmployeeCategoryDescriptionRepositoryWork work = new EmployeeCategoryDescriptionRepositoryWork(userId, companyCode, recordCode);	
		session.doWork(work);
		List<EmployeeCategoryDTO> list = work.getList();
		return list;
	}
	
	private static class EmployeeCategoryDescriptionRepositoryWork implements Work {
		public Logger logger = LoggerFactory.getLogger(getClass());
		private List<EmployeeCategoryDTO> list;
		private EmployeeCategoryDTO employeeCategoryDTO;
		String userId;
		String companyCode;
		String recordCode;
		CallableStatement cstmt = null;
		ResultSet rs = null;

		/**
		 * @param userId
		 */
		public EmployeeCategoryDescriptionRepositoryWork(String userId, String companyCode, String recordCode) {
			super();
			this.userId = userId;
			this.companyCode=companyCode;
			this.recordCode=recordCode;
		}

		@Override
		public void execute(Connection connection) throws SQLException {

			list = new ArrayList<EmployeeCategoryDTO>();
			employeeCategoryDTO = new EmployeeCategoryDTO();

				String query = "{call " + FacesUtils.SCHEMA_DBO
						+ ".spGetEmployeeCategoryDescription('" + userId + "','" + companyCode + "','"+recordCode+"')}";

				System.out.println("Query in EmployeeCategoryDescriptionRepositoryWork " + query);

				logger.debug("Query in EmployeeCategoryDescriptionRepositoryWork " + query);

				cstmt = connection.prepareCall(query);
				rs = cstmt.executeQuery();

			try {
					if (rs != null) 
					{
						while (rs.next()) 
						{
							employeeCategoryDTO.setEmployeeCategoryDescription(rs.getString("Description").isEmpty()?rs.getString("EmployeeCategoryCode"):rs.getString("Description"));														
							list.add(employeeCategoryDTO);
							employeeCategoryDTO = new EmployeeCategoryDTO();
						}
					} 
					else 
					{
						list = new ArrayList<EmployeeCategoryDTO>();
					}
					System.out.println("List size in EmployeeCategoryDescriptionRepositoryWork " + list.size());
					logger.debug("List size in EmployeeCategoryDescriptionRepositoryWork " + list.size());
			} 
			catch (Exception e) 
			{
				System.out.println("Exception in EmployeeCategoryDescriptionRepositoryWork --> execute.");
				logger.debug("Exception in EmployeeCategoryDescriptionRepositoryWork --> execute.");
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
		public List<EmployeeCategoryDTO> getList() {
			return list;
		}

	
	}
}
