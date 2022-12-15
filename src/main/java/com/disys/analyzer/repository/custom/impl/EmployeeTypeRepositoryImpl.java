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
import com.disys.analyzer.model.dto.EmployeeTypeDTO;
import com.disys.analyzer.repository.custom.EmployeeTypeRepositoryCustom;

@Repository
@Transactional(readOnly = true)
public class EmployeeTypeRepositoryImpl implements EmployeeTypeRepositoryCustom{

	@PersistenceContext
	EntityManager entityManager;

	@Override
	public List<EmployeeTypeDTO> getEmployeeTypeList(String userId, String companyCode) 
	{
		Session session = entityManager.unwrap(Session.class);
		EmployeeTypeRepositoryWork work = new EmployeeTypeRepositoryWork(userId, companyCode);	
		session.doWork(work);
		List<EmployeeTypeDTO> list = work.getList();
		return list;
	}
	
	private static class EmployeeTypeRepositoryWork implements Work {
		public Logger logger = LoggerFactory.getLogger(getClass());
		private List<EmployeeTypeDTO> list;
		private EmployeeTypeDTO employeeTypeDTO;
		String userId;
		String companyCode;
		String recordCode=Constants.STRING_CONSTANT_ALL;
		CallableStatement cstmt = null;
		ResultSet rs = null;

		/**
		 * @param userId
		 */
		public EmployeeTypeRepositoryWork(String userId, String companyCode) {
			super();
			this.userId = userId;
			this.companyCode=companyCode;
		}

		@Override
		public void execute(Connection connection) throws SQLException {

			list = new ArrayList<EmployeeTypeDTO>();
			employeeTypeDTO = new EmployeeTypeDTO();

				String query = "{call " + FacesUtils.SCHEMA_DBO
						+ ".spGetEmployeeTypeList('" + userId + "','" + companyCode + "','"+recordCode+"')}";

				System.out.println("Query in EmployeeTypeRepositoryWork " + query);

				logger.debug("Query in EmployeeTypeRepositoryWork " + query);

				cstmt = connection.prepareCall(query);
				rs = cstmt.executeQuery();

			try {
					if (rs != null) 
					{
						while (rs.next()) 
						{
							employeeTypeDTO.setEmployeeTypeCode(rs.getString("EmployeeTypeCode"));
							employeeTypeDTO.setEmployeeTypeDescription(rs.getString("Description"));														
							list.add(employeeTypeDTO);
							employeeTypeDTO = new EmployeeTypeDTO();
						}
					} 
					else 
					{
						list = new ArrayList<EmployeeTypeDTO>();
					}
					System.out.println("List size in EmployeeTypeRepositoryWork " + list.size());
					logger.debug("List size in EmployeeTypeRepositoryWork " + list.size());
			} 
			catch (Exception e) 
			{
				System.out.println("Exception in EmployeeTypeRepositoryWork --> execute.");
				logger.debug("Exception in EmployeeTypeRepositoryWork --> execute.");
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
		public List<EmployeeTypeDTO> getList() {
			return list;
		}

	}
	

	@Override
	public List<EmployeeTypeDTO> getEmployeeTypeDescription(String userId, String companyCode, String recordCode) 
	{
		Session session = entityManager.unwrap(Session.class);
		EmployeeTypeDescriptionRepositoryWork work = new EmployeeTypeDescriptionRepositoryWork(userId, companyCode, recordCode);	
		session.doWork(work);
		List<EmployeeTypeDTO> list = work.getList();
		return list;
	}
	
	private static class EmployeeTypeDescriptionRepositoryWork implements Work {
		public Logger logger = LoggerFactory.getLogger(getClass());
		private List<EmployeeTypeDTO> list;
		private EmployeeTypeDTO employeeTypeDTO;
		String userId;
		String companyCode;
		String recordCode;
		CallableStatement cstmt = null;
		ResultSet rs = null;

		/**
		 * @param userId
		 */
		public EmployeeTypeDescriptionRepositoryWork(String userId, String companyCode, String recordCode) {
			super();
			this.userId = userId;
			this.companyCode=companyCode;
			this.recordCode=recordCode;
		}

		@Override
		public void execute(Connection connection) throws SQLException {

			list = new ArrayList<EmployeeTypeDTO>();
			employeeTypeDTO = new EmployeeTypeDTO();

				String query = "{call " + FacesUtils.SCHEMA_DBO
						+ ".spGetEmployeeTypeDescription('" + userId + "','" + companyCode + "','"+recordCode+"')}";

				System.out.println("Query in EmployeeTypeDescriptionRepositoryWork " + query);

				logger.debug("Query in EmployeeTypeDescriptionRepositoryWork " + query);

				cstmt = connection.prepareCall(query);
				rs = cstmt.executeQuery();

			try {
					if (rs != null) 
					{
						while (rs.next()) 
						{
							employeeTypeDTO.setEmployeeTypeDescription(rs.getString("Description").isEmpty()?rs.getString("EmployeeTypeCode"):rs.getString("Description"));														
							list.add(employeeTypeDTO);
							employeeTypeDTO = new EmployeeTypeDTO();
						}
					} 
					else 
					{
						list = new ArrayList<EmployeeTypeDTO>();
					}
					System.out.println("List size in EmployeeTypeDescriptionRepositoryWork " + list.size());
					logger.debug("List size in EmployeeTypeDescriptionRepositoryWork " + list.size());
			} 
			catch (Exception e) 
			{
				System.out.println("Exception in EmployeeTypeDescriptionRepositoryWork --> execute.");
				logger.debug("Exception in EmployeeTypeDescriptionRepositoryWork --> execute.");
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
		public List<EmployeeTypeDTO> getList() {
			return list;
		}

	
	}
}
