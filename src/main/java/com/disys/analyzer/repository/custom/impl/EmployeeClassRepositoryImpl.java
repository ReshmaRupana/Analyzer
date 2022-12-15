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
import com.disys.analyzer.model.dto.EmployeeClassDTO;
import com.disys.analyzer.repository.custom.EmployeeClassRepositoryCustom;

@Repository
@Transactional(readOnly = true)
public class EmployeeClassRepositoryImpl implements EmployeeClassRepositoryCustom{

	@PersistenceContext
	EntityManager entityManager;

	@Override
	public List<EmployeeClassDTO> getEmployeeClassList(String userId, String companyCode) 
	{
		Session session = entityManager.unwrap(Session.class);
		EmployeeClassRepositoryWork work = new EmployeeClassRepositoryWork(userId, companyCode);	
		session.doWork(work);
		List<EmployeeClassDTO> list = work.getList();
		return list;
	}
	
	private static class EmployeeClassRepositoryWork implements Work {
		public Logger logger = LoggerFactory.getLogger(getClass());
		private List<EmployeeClassDTO> list;
		private EmployeeClassDTO employeeClassDTO;
		String userId;
		String companyCode;
		String recordCode=Constants.STRING_CONSTANT_ALL;
		CallableStatement cstmt = null;
		ResultSet rs = null;

		/**
		 * @param userId
		 */
		public EmployeeClassRepositoryWork(String userId, String companyCode) {
			super();
			this.userId = userId;
			this.companyCode=companyCode;
		}

		@Override
		public void execute(Connection connection) throws SQLException {

			list = new ArrayList<EmployeeClassDTO>();
			employeeClassDTO = new EmployeeClassDTO();

				String query = "{call " + FacesUtils.SCHEMA_DBO
						+ ".spGetEmployeeClassList('" + userId + "','" + companyCode + "','"+recordCode+"')}";

				System.out.println("Query in EmployeeClassRepositoryWork " + query);

				logger.debug("Query in EmployeeClassRepositoryWork " + query);

				cstmt = connection.prepareCall(query);
				rs = cstmt.executeQuery();

			try {
					if (rs != null) 
					{
						while (rs.next()) 
						{
							employeeClassDTO.setEmployeeClassCode(rs.getString("EmployeeClassCode"));
							employeeClassDTO.setEmployeeClassDescription(rs.getString("Description"));														
							list.add(employeeClassDTO);
							employeeClassDTO = new EmployeeClassDTO();
						}
					} 
					else 
					{
						list = new ArrayList<EmployeeClassDTO>();
					}
					System.out.println("List size in EmployeeClassRepositoryWork " + list.size());
					logger.debug("List size in EmployeeClassRepositoryWork " + list.size());
			} 
			catch (Exception e) 
			{
				System.out.println("Exception in EmployeeClassRepositoryWork --> execute.");
				logger.debug("Exception in EmployeeClassRepositoryWork --> execute.");
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
		public List<EmployeeClassDTO> getList() {
			return list;
		}

	}
	

	@Override
	public List<EmployeeClassDTO> getEmployeeClassDescription(String userId, String companyCode, String recordCode) 
	{
		Session session = entityManager.unwrap(Session.class);
		EmployeeClassDescriptionRepositoryWork work = new EmployeeClassDescriptionRepositoryWork(userId, companyCode, recordCode);	
		session.doWork(work);
		List<EmployeeClassDTO> list = work.getList();
		return list;
	}
	
	private static class EmployeeClassDescriptionRepositoryWork implements Work {
		public Logger logger = LoggerFactory.getLogger(getClass());
		private List<EmployeeClassDTO> list;
		private EmployeeClassDTO employeeClassDTO;
		String userId;
		String companyCode;
		String recordCode;
		CallableStatement cstmt = null;
		ResultSet rs = null;

		/**
		 * @param userId
		 */
		public EmployeeClassDescriptionRepositoryWork(String userId, String companyCode, String recordCode) {
			super();
			this.userId = userId;
			this.companyCode=companyCode;
			this.recordCode=recordCode;
		}

		@Override
		public void execute(Connection connection) throws SQLException {

			list = new ArrayList<EmployeeClassDTO>();
			employeeClassDTO = new EmployeeClassDTO();

				String query = "{call " + FacesUtils.SCHEMA_DBO
						+ ".spGetEmployeeClassDescription('" + userId + "','" + companyCode + "','"+recordCode+"')}";

				System.out.println("Query in EmployeeClassDescriptionRepositoryWork " + query);

				logger.debug("Query in EmployeeClassDescriptionRepositoryWork " + query);

				cstmt = connection.prepareCall(query);
				rs = cstmt.executeQuery();

			try {
					if (rs != null) 
					{
						while (rs.next()) 
						{
							employeeClassDTO.setEmployeeClassDescription(rs.getString("Description").isEmpty()?rs.getString("EmployeeClassCode"):rs.getString("Description"));														
							list.add(employeeClassDTO);
							employeeClassDTO = new EmployeeClassDTO();
						}
					} 
					else 
					{
						list = new ArrayList<EmployeeClassDTO>();
					}
					System.out.println("List size in EmployeeClassDescriptionRepositoryWork " + list.size());
					logger.debug("List size in EmployeeClassDescriptionRepositoryWork " + list.size());
			} 
			catch (Exception e) 
			{
				System.out.println("Exception in EmployeeClassDescriptionRepositoryWork --> execute.");
				logger.debug("Exception in EmployeeClassDescriptionRepositoryWork --> execute.");
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
		public List<EmployeeClassDTO> getList() {
			return list;
		}

	
	}
}
