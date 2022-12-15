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
import com.disys.analyzer.model.dto.FLSAStatusDTO;
import com.disys.analyzer.repository.custom.FLSAStatusRepositoryCustom;

@Repository
@Transactional(readOnly = true)
public class FLSAStatusRepositoryImpl implements FLSAStatusRepositoryCustom{

	@PersistenceContext
	EntityManager entityManager;

	@Override
	public List<FLSAStatusDTO> getFLSAStatusList(String userId, String companyCode) 
	{
		Session session = entityManager.unwrap(Session.class);
		FLSAStatusRepositoryWork work = new FLSAStatusRepositoryWork(userId, companyCode);	
		session.doWork(work);
		List<FLSAStatusDTO> list = work.getList();
		return list;
	}
	
	private static class FLSAStatusRepositoryWork implements Work {
		public Logger logger = LoggerFactory.getLogger(getClass());
		private List<FLSAStatusDTO> list;
		private FLSAStatusDTO flsaStatusDTO;
		String userId;
		String companyCode;
		String recordCode=Constants.STRING_CONSTANT_ALL;
		CallableStatement cstmt = null;
		ResultSet rs = null;

		/**
		 * @param userId
		 */
		public FLSAStatusRepositoryWork(String userId, String companyCode) {
			super();
			this.userId = userId;
			this.companyCode=companyCode;
		}

		@Override
		public void execute(Connection connection) throws SQLException {

			list = new ArrayList<FLSAStatusDTO>();
			flsaStatusDTO = new FLSAStatusDTO();

				String query = "{call " + FacesUtils.SCHEMA_DBO
						+ ".spGetFLSAStatusList('" + userId + "','" + companyCode + "','"+recordCode+"')}";

				System.out.println("Query in FLSAStatusRepositoryWork " + query);

				logger.debug("Query in FLSAStatusRepositoryWork " + query);

				cstmt = connection.prepareCall(query);
				rs = cstmt.executeQuery();

			try {
					if (rs != null) 
					{
						while (rs.next()) 
						{
							flsaStatusDTO.setFlsaStatusCode(rs.getString("FLSAStatusCode"));
							flsaStatusDTO.setFlsaStatusDescription(rs.getString("Description"));														
							list.add(flsaStatusDTO);
							flsaStatusDTO = new FLSAStatusDTO();
						}
					} 
					else 
					{
						list = new ArrayList<FLSAStatusDTO>();
					}
					System.out.println("List size in FLSAStatusRepositoryWork " + list.size());
					logger.debug("List size in FLSAStatusRepositoryWork " + list.size());
			} 
			catch (Exception e) 
			{
				System.out.println("Exception in FLSAStatusRepositoryWork --> execute.");
				logger.debug("Exception in FLSAStatusRepositoryWork --> execute.");
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
		public List<FLSAStatusDTO> getList() {
			return list;
		}

	}
	

	@Override
	public List<FLSAStatusDTO> getFLSAStatusDescription(String userId, String companyCode, String recordCode) 
	{
		Session session = entityManager.unwrap(Session.class);
		FLSAStatusDescriptionRepositoryWork work = new FLSAStatusDescriptionRepositoryWork(userId, companyCode, recordCode);	
		session.doWork(work);
		List<FLSAStatusDTO> list = work.getList();
		return list;
	}
	
	private static class FLSAStatusDescriptionRepositoryWork implements Work {
		public Logger logger = LoggerFactory.getLogger(getClass());
		private List<FLSAStatusDTO> list;
		private FLSAStatusDTO flsaStatusDTO;
		String userId;
		String companyCode;
		String recordCode;
		CallableStatement cstmt = null;
		ResultSet rs = null;

		/**
		 * @param userId
		 */
		public FLSAStatusDescriptionRepositoryWork(String userId, String companyCode, String recordCode) {
			super();
			this.userId = userId;
			this.companyCode=companyCode;
			this.recordCode=recordCode;
		}

		@Override
		public void execute(Connection connection) throws SQLException {

			list = new ArrayList<FLSAStatusDTO>();
			flsaStatusDTO = new FLSAStatusDTO();

				String query = "{call " + FacesUtils.SCHEMA_DBO
						+ ".spGetFLSAStatusDescription('" + userId + "','" + companyCode + "','"+recordCode+"')}";

				System.out.println("Query in FLSAStatusDescriptionRepositoryWork " + query);

				logger.debug("Query in FLSAStatusDescriptionRepositoryWork " + query);

				cstmt = connection.prepareCall(query);
				rs = cstmt.executeQuery();

			try {
					if (rs != null) 
					{
						while (rs.next()) 
						{
							flsaStatusDTO.setFlsaStatusDescription(rs.getString("Description").isEmpty()?rs.getString("FLSAStatusCode"):rs.getString("Description"));														
							list.add(flsaStatusDTO);
							flsaStatusDTO = new FLSAStatusDTO();
						}
					} 
					else 
					{
						list = new ArrayList<FLSAStatusDTO>();
					}
					System.out.println("List size in FLSAStatusDescriptionRepositoryWork " + list.size());
					logger.debug("List size in FLSAStatusDescriptionRepositoryWork " + list.size());
			} 
			catch (Exception e) 
			{
				System.out.println("Exception in FLSAStatusDescriptionRepositoryWork --> execute.");
				logger.debug("Exception in FLSAStatusDescriptionRepositoryWork --> execute.");
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
		public List<FLSAStatusDTO> getList() {
			return list;
		}

	
	}
}
