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
import com.disys.analyzer.model.dto.CommissionModelDTO;
import com.disys.analyzer.repository.custom.CommissionModelRepositoryCustom;

@Repository
@Transactional(readOnly = true)
public class CommissionModelRepositoryImpl implements CommissionModelRepositoryCustom{

	@PersistenceContext
	EntityManager entityManager;

	@Override
	public List<CommissionModelDTO> getCommissionModelList(String userId, String companyCode) 
	{
		Session session = entityManager.unwrap(Session.class);
		CommissionModelRepositoryWork work = new CommissionModelRepositoryWork(userId, companyCode);	
		session.doWork(work);
		List<CommissionModelDTO> list = work.getList();
		return list;
	}
	
	private static class CommissionModelRepositoryWork implements Work {
		public Logger logger = LoggerFactory.getLogger(getClass());
		private List<CommissionModelDTO> list;
		private CommissionModelDTO commissionModelDTO;
		String userId;
		String companyCode;
		String recordCode=Constants.STRING_CONSTANT_ALL;
		CallableStatement cstmt = null;
		ResultSet rs = null;

		/**
		 * @param userId
		 */
		public CommissionModelRepositoryWork(String userId, String companyCode) {
			super();
			this.userId = userId;
			this.companyCode=companyCode;
		}

		@Override
		public void execute(Connection connection) throws SQLException {

			list = new ArrayList<CommissionModelDTO>();
			commissionModelDTO = new CommissionModelDTO();

				String query = "{call " + FacesUtils.SCHEMA_DBO
						+ ".spGetCommissionModelList('" + userId + "','" + companyCode + "','"+recordCode+"')}";

				System.out.println("Query in CommissionModelRepositoryWork " + query);

				logger.debug("Query in CommissionModelRepositoryWork " + query);

				cstmt = connection.prepareCall(query);
				rs = cstmt.executeQuery();

			try {
					if (rs != null) 
					{
						while (rs.next()) 
						{
							commissionModelDTO.setCommissionModelCode(rs.getString("CommissionModelCode"));
							commissionModelDTO.setCommissionModelDescription(rs.getString("Description"));														
							list.add(commissionModelDTO);
							commissionModelDTO = new CommissionModelDTO();
						}
					} 
					else 
					{
						list = new ArrayList<CommissionModelDTO>();
					}
					System.out.println("List size in CommissionModelRepositoryWork " + list.size());
					logger.debug("List size in CommissionModelRepositoryWork " + list.size());
			} 
			catch (Exception e) 
			{
				System.out.println("Exception in CommissionModelRepositoryWork --> execute.");
				logger.debug("Exception in CommissionModelRepositoryWork --> execute.");
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
		public List<CommissionModelDTO> getList() {
			return list;
		}

	}
	
	/* (non-Javadoc)
	 * @see com.disys.analyzer.repository.custom.CommissionModelDescriptionRepositoryWork#getCommissionModelDescription()
	 */
	@Override
	public List<CommissionModelDTO> getCommissionModelDescription(String userId, String companyCode, String recordCode) 
	{
		Session session = entityManager.unwrap(Session.class);
		CommissionModelDescriptionRepositoryWork work = new CommissionModelDescriptionRepositoryWork(userId, companyCode, recordCode);	
		session.doWork(work);
		List<CommissionModelDTO> list = work.getList();
		return list;
	}
	
	private static class CommissionModelDescriptionRepositoryWork implements Work {
		public Logger logger = LoggerFactory.getLogger(getClass());
		private List<CommissionModelDTO> list;
		private CommissionModelDTO commissionModelDTO;
		String userId;
		String companyCode;
		String recordCode;
		CallableStatement cstmt = null;
		ResultSet rs = null;

		/**
		 * @param userId
		 */
		public CommissionModelDescriptionRepositoryWork(String userId, String companyCode, String recordCode) {
			super();
			this.userId = userId;
			this.companyCode=companyCode;
			this.recordCode=recordCode;
		}

		@Override
		public void execute(Connection connection) throws SQLException {

			list = new ArrayList<CommissionModelDTO>();
			commissionModelDTO = new CommissionModelDTO();

				String query = "{call " + FacesUtils.SCHEMA_DBO
						+ ".spGetCommissionModelDescription('" + userId + "','" + companyCode + "','"+recordCode+"')}";

				System.out.println("Query in CommissionModelDescriptionRepositoryWork " + query);

				logger.debug("Query in CommissionModelDescriptionRepositoryWork " + query);

				cstmt = connection.prepareCall(query);
				rs = cstmt.executeQuery();

			try {
					if (rs != null) 
					{
						while (rs.next()) 
						{
							commissionModelDTO.setCommissionModelDescription(rs.getString("Description").isEmpty()?rs.getString("CommissionModelCode"):rs.getString("Description"));														
							list.add(commissionModelDTO);
							commissionModelDTO = new CommissionModelDTO();
						}
					} 
					else 
					{
						list = new ArrayList<CommissionModelDTO>();
					}
					System.out.println("List size in CommissionModelDescriptionRepositoryWork " + list.size());
					logger.debug("List size in CommissionModelDescriptionRepositoryWork " + list.size());
			} 
			catch (Exception e) 
			{
				System.out.println("Exception in CommissionModelDescriptionRepositoryWork --> execute.");
				logger.debug("Exception in CommissionModelDescriptionRepositoryWork --> execute.");
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
		public List<CommissionModelDTO> getList() {
			return list;
		}

	
	}
}
