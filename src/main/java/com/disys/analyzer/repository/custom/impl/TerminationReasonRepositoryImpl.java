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
import com.disys.analyzer.model.dto.TerminationReasonDTO;
import com.disys.analyzer.repository.custom.TerminationReasonRepositoryCustom;

@Repository
@Transactional(readOnly = true)
public class TerminationReasonRepositoryImpl implements TerminationReasonRepositoryCustom{

	@PersistenceContext
	EntityManager entityManager;

	@Override
	public List<TerminationReasonDTO> getTerminationReasonList(String userId, String companyCode) 
	{
		Session session = entityManager.unwrap(Session.class);
		TerminationReasonRepositoryWork work = new TerminationReasonRepositoryWork(userId, companyCode);	
		session.doWork(work);
		List<TerminationReasonDTO> list = work.getList();
		return list;
	}
	
	private static class TerminationReasonRepositoryWork implements Work {
		public Logger logger = LoggerFactory.getLogger(getClass());
		private List<TerminationReasonDTO> list;
		private TerminationReasonDTO terminationReasonDTO;
		String userId;
		String companyCode;
		String recordCode=Constants.STRING_CONSTANT_ALL;
		CallableStatement cstmt = null;
		ResultSet rs = null;

		/**
		 * @param userId
		 */
		public TerminationReasonRepositoryWork(String userId, String companyCode) {
			super();
			this.userId = userId;
			this.companyCode=companyCode;
		}

		@Override
		public void execute(Connection connection) throws SQLException {

			list = new ArrayList<TerminationReasonDTO>();
			terminationReasonDTO = new TerminationReasonDTO();

				String query = "{call " + FacesUtils.SCHEMA_DBO
						+ ".spGetTerminationReasonList('" + userId + "','" + companyCode + "','"+recordCode+"')}";

				System.out.println("Query in TerminationReasonRepositoryWork " + query);

				logger.debug("Query in TerminationReasonRepositoryWork " + query);

				cstmt = connection.prepareCall(query);
				rs = cstmt.executeQuery();

			try {
					if (rs != null) 
					{
						while (rs.next()) 
						{
							terminationReasonDTO.setTerminationReasonCode(rs.getString("TerminationReasonCode"));
							terminationReasonDTO.setTerminationReasonDescription(rs.getString("Description"));														
							list.add(terminationReasonDTO);
							terminationReasonDTO = new TerminationReasonDTO();
						}
					} 
					else 
					{
						list = new ArrayList<TerminationReasonDTO>();
					}
					System.out.println("List size in TerminationReasonRepositoryWork " + list.size());
					logger.debug("List size in TerminationReasonRepositoryWork " + list.size());
			} 
			catch (Exception e) 
			{
				System.out.println("Exception in TerminationReasonRepositoryWork --> execute.");
				logger.debug("Exception in TerminationReasonRepositoryWork --> execute.");
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
		public List<TerminationReasonDTO> getList() {
			return list;
		}

	}
	

	@Override
	public List<TerminationReasonDTO> getTerminationReasonDescription(String userId, String companyCode, String recordCode) 
	{
		Session session = entityManager.unwrap(Session.class);
		TerminationReasonDescriptionRepositoryWork work = new TerminationReasonDescriptionRepositoryWork(userId, companyCode, recordCode);	
		session.doWork(work);
		List<TerminationReasonDTO> list = work.getList();
		return list;
	}
	
	private static class TerminationReasonDescriptionRepositoryWork implements Work {
		public Logger logger = LoggerFactory.getLogger(getClass());
		private List<TerminationReasonDTO> list;
		private TerminationReasonDTO terminationReasonDTO;
		String userId;
		String companyCode;
		String recordCode;
		CallableStatement cstmt = null;
		ResultSet rs = null;

		/**
		 * @param userId
		 */
		public TerminationReasonDescriptionRepositoryWork(String userId, String companyCode, String recordCode) {
			super();
			this.userId = userId;
			this.companyCode=companyCode;
			this.recordCode=recordCode;
		}

		@Override
		public void execute(Connection connection) throws SQLException {

			list = new ArrayList<TerminationReasonDTO>();
			terminationReasonDTO = new TerminationReasonDTO();

				String query = "{call " + FacesUtils.SCHEMA_DBO
						+ ".spGetTerminationReasonDescription('" + userId + "','" + companyCode + "','"+recordCode+"')}";

				System.out.println("Query in TerminationReasonDescriptionRepositoryWork " + query);

				logger.debug("Query in TerminationReasonDescriptionRepositoryWork " + query);

				cstmt = connection.prepareCall(query);
				rs = cstmt.executeQuery();

			try {
					if (rs != null) 
					{
						while (rs.next()) 
						{
							terminationReasonDTO.setTerminationReasonDescription(rs.getString("Description").isEmpty()?rs.getString("TerminationReasonCode"):rs.getString("Description"));														
							list.add(terminationReasonDTO);
							terminationReasonDTO = new TerminationReasonDTO();
						}
					} 
					else 
					{
						list = new ArrayList<TerminationReasonDTO>();
					}
					System.out.println("List size in TerminationReasonDescriptionRepositoryWork " + list.size());
					logger.debug("List size in TerminationReasonDescriptionRepositoryWork " + list.size());
			} 
			catch (Exception e) 
			{
				System.out.println("Exception in TerminationReasonDescriptionRepositoryWork --> execute.");
				logger.debug("Exception in TerminationReasonDescriptionRepositoryWork --> execute.");
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
		public List<TerminationReasonDTO> getList() {
			return list;
		}

	
	}
}
