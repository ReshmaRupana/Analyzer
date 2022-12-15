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
import com.disys.analyzer.model.dto.SickLeaveTypeDTO;
import com.disys.analyzer.repository.custom.SickLeaveTypeRepositoryCustom;

@Repository
@Transactional(readOnly = true)
public class SickLeaveTypeRepositoryImpl implements SickLeaveTypeRepositoryCustom{

	@PersistenceContext
	EntityManager entityManager;

	@Override
	public List<SickLeaveTypeDTO> getSickLeaveTypeList(String userId, String companyCode) 
	{
		Session session = entityManager.unwrap(Session.class);
		SickLeaveTypeRepositoryWork work = new SickLeaveTypeRepositoryWork(userId, companyCode);	
		session.doWork(work);
		List<SickLeaveTypeDTO> list = work.getList();
		return list;
	}
	
	private static class SickLeaveTypeRepositoryWork implements Work {
		public Logger logger = LoggerFactory.getLogger(getClass());
		private List<SickLeaveTypeDTO> list;
		private SickLeaveTypeDTO sickLeaveTypeDTO;
		String userId;
		String companyCode;
		String recordCode=Constants.STRING_CONSTANT_ALL;
		CallableStatement cstmt = null;
		ResultSet rs = null;

		/**
		 * @param userId
		 */
		public SickLeaveTypeRepositoryWork(String userId, String companyCode) {
			super();
			this.userId = userId;
			this.companyCode=companyCode;
		}

		@Override
		public void execute(Connection connection) throws SQLException {

			list = new ArrayList<SickLeaveTypeDTO>();
			sickLeaveTypeDTO = new SickLeaveTypeDTO();

				String query = "{call " + FacesUtils.SCHEMA_DBO
						+ ".spGetSickLeaveTypeList('" + userId + "','" + companyCode + "','"+recordCode+"')}";

				System.out.println("Query in SickLeaveTypeRepositoryWork " + query);

				logger.debug("Query in SickLeaveTypeRepositoryWork " + query);

				cstmt = connection.prepareCall(query);
				rs = cstmt.executeQuery();

			try {
					if (rs != null) 
					{
						while (rs.next()) 
						{
							sickLeaveTypeDTO.setSickLeaveTypeCode(rs.getString("SickLeaveTypeCode"));
							sickLeaveTypeDTO.setSickLeaveTypeDescription(rs.getString("Description"));														
							list.add(sickLeaveTypeDTO);
							sickLeaveTypeDTO = new SickLeaveTypeDTO();
						}
					} 
					else 
					{
						list = new ArrayList<SickLeaveTypeDTO>();
					}
					System.out.println("List size in SickLeaveTypeRepositoryWork " + list.size());
					logger.debug("List size in SickLeaveTypeRepositoryWork " + list.size());
			} 
			catch (Exception e) 
			{
				System.out.println("Exception in SickLeaveTypeRepositoryWork --> execute.");
				logger.debug("Exception in SickLeaveTypeRepositoryWork --> execute.");
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
		public List<SickLeaveTypeDTO> getList() {
			return list;
		}

	}
	

	@Override
	public List<SickLeaveTypeDTO> getSickLeaveTypeDescription(String userId, String companyCode, String recordCode) 
	{
		Session session = entityManager.unwrap(Session.class);
		SickLeaveTypeDescriptionRepositoryWork work = new SickLeaveTypeDescriptionRepositoryWork(userId, companyCode, recordCode);	
		session.doWork(work);
		List<SickLeaveTypeDTO> list = work.getList();
		return list;
	}
	
	private static class SickLeaveTypeDescriptionRepositoryWork implements Work {
		public Logger logger = LoggerFactory.getLogger(getClass());
		private List<SickLeaveTypeDTO> list;
		private SickLeaveTypeDTO sickLeaveTypeDTO;
		String userId;
		String companyCode;
		String recordCode;
		CallableStatement cstmt = null;
		ResultSet rs = null;

		/**
		 * @param userId
		 */
		public SickLeaveTypeDescriptionRepositoryWork(String userId, String companyCode, String recordCode) {
			super();
			this.userId = userId;
			this.companyCode=companyCode;
			this.recordCode=recordCode;
		}

		@Override
		public void execute(Connection connection) throws SQLException {

			list = new ArrayList<SickLeaveTypeDTO>();
			sickLeaveTypeDTO = new SickLeaveTypeDTO();

				String query = "{call " + FacesUtils.SCHEMA_DBO
						+ ".spGetSickLeaveTypeDescription('" + userId + "','" + companyCode + "','"+recordCode+"')}";

				System.out.println("Query in SickLeaveTypeDescriptionRepositoryWork " + query);

				logger.debug("Query in SickLeaveTypeDescriptionRepositoryWork " + query);

				cstmt = connection.prepareCall(query);
				rs = cstmt.executeQuery();

			try {
					if (rs != null) 
					{
						while (rs.next()) 
						{
							sickLeaveTypeDTO.setSickLeaveTypeDescription(rs.getString("Description").isEmpty()?rs.getString("SickLeaveTypeCode"):rs.getString("Description"));														
							list.add(sickLeaveTypeDTO);
							sickLeaveTypeDTO = new SickLeaveTypeDTO();
						}
					} 
					else 
					{
						list = new ArrayList<SickLeaveTypeDTO>();
					}
					System.out.println("List size in SickLeaveTypeDescriptionRepositoryWork " + list.size());
					logger.debug("List size in SickLeaveTypeDescriptionRepositoryWork " + list.size());
			} 
			catch (Exception e) 
			{
				System.out.println("Exception in SickLeaveTypeDescriptionRepositoryWork --> execute.");
				logger.debug("Exception in SickLeaveTypeDescriptionRepositoryWork --> execute.");
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
		public List<SickLeaveTypeDTO> getList() {
			return list;
		}

	
	}
}
