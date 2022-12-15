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
import com.disys.analyzer.model.dto.SplitPercentageDTO;
import com.disys.analyzer.repository.custom.SplitPercentageRepositoryCustom;

@Repository
@Transactional(readOnly = true)
public class SplitPercentageRepositoryImpl implements SplitPercentageRepositoryCustom{

	@PersistenceContext
	EntityManager entityManager;

	@Override
	public List<SplitPercentageDTO> getSplitPercentageList(String userId, String companyCode) 
	{
		Session session = entityManager.unwrap(Session.class);
		SplitPercentageRepositoryWork work = new SplitPercentageRepositoryWork(userId, companyCode);	
		session.doWork(work);
		List<SplitPercentageDTO> list = work.getList();
		return list;
	}
	
	private static class SplitPercentageRepositoryWork implements Work {
		public Logger logger = LoggerFactory.getLogger(getClass());
		private List<SplitPercentageDTO> list;
		private SplitPercentageDTO splitPercentageDTO;
		String userId;
		String companyCode;
		String recordCode=Constants.STRING_CONSTANT_ALL;
		CallableStatement cstmt = null;
		ResultSet rs = null;

		/**
		 * @param userId
		 */
		public SplitPercentageRepositoryWork(String userId, String companyCode) {
			super();
			this.userId = userId;
			this.companyCode=companyCode;
		}

		@Override
		public void execute(Connection connection) throws SQLException {

			list = new ArrayList<SplitPercentageDTO>();
			splitPercentageDTO = new SplitPercentageDTO();

				String query = "{call " + FacesUtils.SCHEMA_DBO
						+ ".spGetSplitPercentageList('" + userId + "','" + companyCode + "','"+recordCode+"')}";

				System.out.println("Query in SplitPercentageRepositoryWork " + query);

				logger.debug("Query in SplitPercentageRepositoryWork " + query);

				cstmt = connection.prepareCall(query);
				rs = cstmt.executeQuery();

			try {
					if (rs != null) 
					{
						while (rs.next()) 
						{
							splitPercentageDTO.setSplitPercentageCode(rs.getString("SplitPercentageCode"));
							splitPercentageDTO.setSplitPercentageDescription(rs.getString("Description"));														
							list.add(splitPercentageDTO);
							splitPercentageDTO = new SplitPercentageDTO();
						}
					} 
					else 
					{
						list = new ArrayList<SplitPercentageDTO>();
					}
					System.out.println("List size in SplitPercentageRepositoryWork " + list.size());
					logger.debug("List size in SplitPercentageRepositoryWork " + list.size());
			} 
			catch (Exception e) 
			{
				System.out.println("Exception in SplitPercentageRepositoryWork --> execute.");
				logger.debug("Exception in SplitPercentageRepositoryWork --> execute.");
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
		public List<SplitPercentageDTO> getList() {
			return list;
		}

	}
	

	@Override
	public List<SplitPercentageDTO> getSplitPercentageDescription(String userId, String companyCode, String recordCode) 
	{
		Session session = entityManager.unwrap(Session.class);
		SplitPercentageDescriptionRepositoryWork work = new SplitPercentageDescriptionRepositoryWork(userId, companyCode, recordCode);	
		session.doWork(work);
		List<SplitPercentageDTO> list = work.getList();
		return list;
	}
	
	private static class SplitPercentageDescriptionRepositoryWork implements Work {
		public Logger logger = LoggerFactory.getLogger(getClass());
		private List<SplitPercentageDTO> list;
		private SplitPercentageDTO splitPercentageDTO;
		String userId;
		String companyCode;
		String recordCode;
		CallableStatement cstmt = null;
		ResultSet rs = null;

		/**
		 * @param userId
		 */
		public SplitPercentageDescriptionRepositoryWork(String userId, String companyCode, String recordCode) {
			super();
			this.userId = userId;
			this.companyCode=companyCode;
			this.recordCode=recordCode;
		}

		@Override
		public void execute(Connection connection) throws SQLException {

			list = new ArrayList<SplitPercentageDTO>();
			splitPercentageDTO = new SplitPercentageDTO();

				String query = "{call " + FacesUtils.SCHEMA_DBO
						+ ".spGetSplitPercentageDescription('" + userId + "','" + companyCode + "','"+recordCode+"')}";

				System.out.println("Query in SplitPercentageDescriptionRepositoryWork " + query);

				logger.debug("Query in SplitPercentageDescriptionRepositoryWork " + query);

				cstmt = connection.prepareCall(query);
				rs = cstmt.executeQuery();

			try {
					if (rs != null) 
					{
						while (rs.next()) 
						{
							splitPercentageDTO.setSplitPercentageDescription(rs.getString("Description").isEmpty()?rs.getString("SplitPercentageCode"):rs.getString("Description"));														
							list.add(splitPercentageDTO);
							splitPercentageDTO = new SplitPercentageDTO();
						}
					} 
					else 
					{
						list = new ArrayList<SplitPercentageDTO>();
					}
					System.out.println("List size in SplitPercentageDescriptionRepositoryWork " + list.size());
					logger.debug("List size in SplitPercentageDescriptionRepositoryWork " + list.size());
			} 
			catch (Exception e) 
			{
				System.out.println("Exception in SplitPercentageDescriptionRepositoryWork --> execute.");
				logger.debug("Exception in SplitPercentageDescriptionRepositoryWork --> execute.");
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
		public List<SplitPercentageDTO> getList() {
			return list;
		}

	
	}
}
