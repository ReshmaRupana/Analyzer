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
import com.disys.analyzer.model.dto.MajorityWorkPerformedDTO;
import com.disys.analyzer.repository.custom.MajorityWorkPerformedRepositoryCustom;

@Repository
@Transactional(readOnly = true)
public class MajorityWorkPerformedRepositoryImpl implements MajorityWorkPerformedRepositoryCustom{

	@PersistenceContext
	EntityManager entityManager;

	@Override
	public List<MajorityWorkPerformedDTO> getMajorityWorkPerformedList(String userId, String companyCode) 
	{
		Session session = entityManager.unwrap(Session.class);
		MajorityWorkPerformedRepositoryWork work = new MajorityWorkPerformedRepositoryWork(userId, companyCode);	
		session.doWork(work);
		List<MajorityWorkPerformedDTO> list = work.getList();
		return list;
	}
	
	private static class MajorityWorkPerformedRepositoryWork implements Work {
		public Logger logger = LoggerFactory.getLogger(getClass());
		private List<MajorityWorkPerformedDTO> list;
		private MajorityWorkPerformedDTO majorityWorkPerformedDTO;
		String userId;
		String companyCode;
		String recordCode=Constants.STRING_CONSTANT_ALL;
		CallableStatement cstmt = null;
		ResultSet rs = null;

		/**
		 * @param userId
		 */
		public MajorityWorkPerformedRepositoryWork(String userId, String companyCode) {
			super();
			this.userId = userId;
			this.companyCode=companyCode;
		}

		@Override
		public void execute(Connection connection) throws SQLException {

			list = new ArrayList<MajorityWorkPerformedDTO>();
			majorityWorkPerformedDTO = new MajorityWorkPerformedDTO();

				String query = "{call " + FacesUtils.SCHEMA_DBO
						+ ".spGetMajorityWorkPerformedList('" + userId + "','" + companyCode + "','"+recordCode+"')}";

				System.out.println("Query in MajorityWorkPerformedRepositoryWork " + query);

				logger.debug("Query in MajorityWorkPerformedRepositoryWork " + query);

				cstmt = connection.prepareCall(query);
				rs = cstmt.executeQuery();

			try {
					if (rs != null) 
					{
						while (rs.next()) 
						{
							majorityWorkPerformedDTO.setMajorityWorkPerformedCode(rs.getString("MajorityWorkPerformedCode"));
							majorityWorkPerformedDTO.setMajorityWorkPerformedDescription(rs.getString("Description"));														
							list.add(majorityWorkPerformedDTO);
							majorityWorkPerformedDTO = new MajorityWorkPerformedDTO();
						}
					} 
					else 
					{
						list = new ArrayList<MajorityWorkPerformedDTO>();
					}
					System.out.println("List size in MajorityWorkPerformedRepositoryWork " + list.size());
					logger.debug("List size in MajorityWorkPerformedRepositoryWork " + list.size());
			} 
			catch (Exception e) 
			{
				System.out.println("Exception in MajorityWorkPerformedRepositoryWork --> execute.");
				logger.debug("Exception in MajorityWorkPerformedRepositoryWork --> execute.");
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
		public List<MajorityWorkPerformedDTO> getList() {
			return list;
		}

	}
	

	@Override
	public List<MajorityWorkPerformedDTO> getMajorityWorkPerformedDescription(String userId, String companyCode, String recordCode) 
	{
		Session session = entityManager.unwrap(Session.class);
		MajorityWorkPerformedDescriptionRepositoryWork work = new MajorityWorkPerformedDescriptionRepositoryWork(userId, companyCode, recordCode);	
		session.doWork(work);
		List<MajorityWorkPerformedDTO> list = work.getList();
		return list;
	}
	
	private static class MajorityWorkPerformedDescriptionRepositoryWork implements Work {
		public Logger logger = LoggerFactory.getLogger(getClass());
		private List<MajorityWorkPerformedDTO> list;
		private MajorityWorkPerformedDTO majorityWorkPerformedDTO;
		String userId;
		String companyCode;
		String recordCode;
		CallableStatement cstmt = null;
		ResultSet rs = null;

		/**
		 * @param userId
		 */
		public MajorityWorkPerformedDescriptionRepositoryWork(String userId, String companyCode, String recordCode) {
			super();
			this.userId = userId;
			this.companyCode=companyCode;
			this.recordCode=recordCode;
		}

		@Override
		public void execute(Connection connection) throws SQLException {

			list = new ArrayList<MajorityWorkPerformedDTO>();
			majorityWorkPerformedDTO = new MajorityWorkPerformedDTO();

				String query = "{call " + FacesUtils.SCHEMA_DBO
						+ ".spGetMajorityWorkPerformedDescription('" + userId + "','" + companyCode + "','"+recordCode+"')}";

				System.out.println("Query in MajorityWorkPerformedDescriptionRepositoryWork " + query);

				logger.debug("Query in MajorityWorkPerformedDescriptionRepositoryWork " + query);

				cstmt = connection.prepareCall(query);
				rs = cstmt.executeQuery();

			try {
					if (rs != null) 
					{
						while (rs.next()) 
						{
							majorityWorkPerformedDTO.setMajorityWorkPerformedDescription(rs.getString("Description").isEmpty()?rs.getString("MajorityWorkPerformedCode"):rs.getString("Description"));														
							list.add(majorityWorkPerformedDTO);
							majorityWorkPerformedDTO = new MajorityWorkPerformedDTO();
						}
					} 
					else 
					{
						list = new ArrayList<MajorityWorkPerformedDTO>();
					}
					System.out.println("List size in MajorityWorkPerformedDescriptionRepositoryWork " + list.size());
					logger.debug("List size in MajorityWorkPerformedDescriptionRepositoryWork " + list.size());
			} 
			catch (Exception e) 
			{
				System.out.println("Exception in MajorityWorkPerformedDescriptionRepositoryWork --> execute.");
				logger.debug("Exception in MajorityWorkPerformedDescriptionRepositoryWork --> execute.");
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
		public List<MajorityWorkPerformedDTO> getList() {
			return list;
		}

	
	}
}
