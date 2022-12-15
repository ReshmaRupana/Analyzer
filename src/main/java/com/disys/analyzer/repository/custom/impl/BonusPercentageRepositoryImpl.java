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
import com.disys.analyzer.model.dto.BonusPercentageDTO;
import com.disys.analyzer.repository.custom.BonusPercentageRepositoryCustom;


@Repository
@Transactional(readOnly = true)
public class BonusPercentageRepositoryImpl implements BonusPercentageRepositoryCustom{

	@PersistenceContext
	EntityManager entityManager;

	@Override
	public List<BonusPercentageDTO> getBonusPercentageList(String userId, String companyCode) 
	{
		Session session = entityManager.unwrap(Session.class);
		BonusPercentageRepositoryWork work = new BonusPercentageRepositoryWork(userId, companyCode);	
		session.doWork(work);
		List<BonusPercentageDTO> list = work.getList();
		return list;
	}
	
	private static class BonusPercentageRepositoryWork implements Work {
		public Logger logger = LoggerFactory.getLogger(getClass());
		private List<BonusPercentageDTO> list;
		private BonusPercentageDTO bonusPercentageDTO;
		String userId;
		String companyCode;
		String recordCode=Constants.STRING_CONSTANT_ALL;
		CallableStatement cstmt = null;
		ResultSet rs = null;

		/**
		 * @param userId
		 */
		public BonusPercentageRepositoryWork(String userId, String companyCode) {
			super();
			this.userId = userId;
			this.companyCode=companyCode;
		}

		@Override
		public void execute(Connection connection) throws SQLException {

			list = new ArrayList<BonusPercentageDTO>();
			bonusPercentageDTO = new BonusPercentageDTO();

				String query = "{call " + FacesUtils.SCHEMA_DBO
						+ ".spGetBonusPercentageList('" + userId + "','" + companyCode + "','"+recordCode+"')}";

				System.out.println("Query in BonusPercentageRepositoryWork " + query);

				logger.debug("Query in BonusPercentageRepositoryWork " + query);

				cstmt = connection.prepareCall(query);
				rs = cstmt.executeQuery();

			try {
					if (rs != null) 
					{
						while (rs.next()) 
						{
							bonusPercentageDTO.setBonusPercentageCode(rs.getString("BonusPercentageCode"));
							bonusPercentageDTO.setBonusPercentageDescription(rs.getString("Description"));														
							list.add(bonusPercentageDTO);
							bonusPercentageDTO = new BonusPercentageDTO();
						}
					} 
					else 
					{
						list = new ArrayList<BonusPercentageDTO>();
					}
					System.out.println("List size in BonusPercentageRepositoryWork " + list.size());
					logger.debug("List size in BonusPercentageRepositoryWork " + list.size());
			} 
			catch (Exception e) 
			{
				System.out.println("Exception in BonusPercentageRepositoryWork --> execute.");
				logger.debug("Exception in BonusPercentageRepositoryWork --> execute.");
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
		public List<BonusPercentageDTO> getList() {
			return list;
		}

	}
	

	@Override
	public List<BonusPercentageDTO> getBonusPercentageDescription(String userId, String companyCode, String recordCode) 
	{
		Session session = entityManager.unwrap(Session.class);
		BonusPercentageDescriptionRepositoryWork work = new BonusPercentageDescriptionRepositoryWork(userId, companyCode, recordCode);	
		session.doWork(work);
		List<BonusPercentageDTO> list = work.getList();
		return list;
	}
	
	private static class BonusPercentageDescriptionRepositoryWork implements Work {
		public Logger logger = LoggerFactory.getLogger(getClass());
		private List<BonusPercentageDTO> list;
		private BonusPercentageDTO bonusPercentageDTO;
		String userId;
		String companyCode;
		String recordCode;
		CallableStatement cstmt = null;
		ResultSet rs = null;

		/**
		 * @param userId
		 */
		public BonusPercentageDescriptionRepositoryWork(String userId, String companyCode, String recordCode) {
			super();
			this.userId = userId;
			this.companyCode=companyCode;
			this.recordCode=recordCode;
		}

		@Override
		public void execute(Connection connection) throws SQLException {

			list = new ArrayList<BonusPercentageDTO>();
			bonusPercentageDTO = new BonusPercentageDTO();

				String query = "{call " + FacesUtils.SCHEMA_DBO
						+ ".spGetBonusPercentageDescription('" + userId + "','" + companyCode + "','"+recordCode+"')}";

				System.out.println("Query in BonusPercentageDescriptionRepositoryWork " + query);

				logger.debug("Query in BonusPercentageDescriptionRepositoryWork " + query);

				cstmt = connection.prepareCall(query);
				rs = cstmt.executeQuery();

			try {
					if (rs != null) 
					{
						while (rs.next()) 
						{
							bonusPercentageDTO.setBonusPercentageDescription(rs.getString("Description").isEmpty()?rs.getString("BonusPercentageCode"):rs.getString("Description"));														
							list.add(bonusPercentageDTO);
							bonusPercentageDTO = new BonusPercentageDTO();
						}
					} 
					else 
					{
						list = new ArrayList<BonusPercentageDTO>();
					}
					System.out.println("List size in BonusPercentageDescriptionRepositoryWork " + list.size());
					logger.debug("List size in BonusPercentageDescriptionRepositoryWork " + list.size());
			} 
			catch (Exception e) 
			{
				System.out.println("Exception in BonusPercentageDescriptionRepositoryWork --> execute.");
				logger.debug("Exception in BonusPercentageDescriptionRepositoryWork --> execute.");
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
		public List<BonusPercentageDTO> getList() {
			return list;
		}

	
	}
}
