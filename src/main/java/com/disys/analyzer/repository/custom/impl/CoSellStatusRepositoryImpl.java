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
import com.disys.analyzer.model.dto.CoSellStatusDTO;
import com.disys.analyzer.repository.custom.CoSellStatusRepositoryCustom;

@Repository
@Transactional(readOnly = true)
public class CoSellStatusRepositoryImpl implements CoSellStatusRepositoryCustom{

	@PersistenceContext
	EntityManager entityManager;

	@Override
	public List<CoSellStatusDTO> getCoSellStatusList(String userId, String companyCode) 
	{
		Session session = entityManager.unwrap(Session.class);
		CoSellStatusRepositoryWork work = new CoSellStatusRepositoryWork(userId, companyCode);	
		session.doWork(work);
		List<CoSellStatusDTO> list = work.getList();
		return list;
	}
	
	private static class CoSellStatusRepositoryWork implements Work {
		public Logger logger = LoggerFactory.getLogger(getClass());
		private List<CoSellStatusDTO> list;
		private CoSellStatusDTO coSellStatusDTO;
		String userId;
		String companyCode;
		String recordCode=Constants.STRING_CONSTANT_ALL;
		CallableStatement cstmt = null;
		ResultSet rs = null;

		/**
		 * @param userId
		 */
		public CoSellStatusRepositoryWork(String userId, String companyCode) {
			super();
			this.userId = userId;
			this.companyCode=companyCode;
		}

		@Override
		public void execute(Connection connection) throws SQLException {

			list = new ArrayList<CoSellStatusDTO>();
			coSellStatusDTO = new CoSellStatusDTO();

				String query = "{call " + FacesUtils.SCHEMA_DBO
						+ ".spGetCoSellStatusList('" + userId + "','" + companyCode + "','"+recordCode+"')}";

				System.out.println("Query in CoSellStatusRepositoryWork " + query);

				logger.debug("Query in CoSellStatusRepositoryWork " + query);

				cstmt = connection.prepareCall(query);
				rs = cstmt.executeQuery();

			try {
					if (rs != null) 
					{
						while (rs.next()) 
						{
							coSellStatusDTO.setCoSellStatusCode(rs.getString("CoSellStatusCode"));
							coSellStatusDTO.setCoSellStatusDescription(rs.getString("Description"));														
							list.add(coSellStatusDTO);
							coSellStatusDTO = new CoSellStatusDTO();
						}
					} 
					else 
					{
						list = new ArrayList<CoSellStatusDTO>();
					}
					System.out.println("List size in CoSellStatusRepositoryWork " + list.size());
					logger.debug("List size in CoSellStatusRepositoryWork " + list.size());
			} 
			catch (Exception e) 
			{
				System.out.println("Exception in CoSellStatusRepositoryWork --> execute.");
				logger.debug("Exception in CoSellStatusRepositoryWork --> execute.");
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
		public List<CoSellStatusDTO> getList() {
			return list;
		}

	}
	

	@Override
	public List<CoSellStatusDTO> getCoSellStatusDescription(String userId, String companyCode, String recordCode) 
	{
		Session session = entityManager.unwrap(Session.class);
		CoSellStatusDescriptionRepositoryWork work = new CoSellStatusDescriptionRepositoryWork(userId, companyCode, recordCode);	
		session.doWork(work);
		List<CoSellStatusDTO> list = work.getList();
		return list;
	}
	
	private static class CoSellStatusDescriptionRepositoryWork implements Work {
		public Logger logger = LoggerFactory.getLogger(getClass());
		private List<CoSellStatusDTO> list;
		private CoSellStatusDTO coSellStatusDTO;
		String userId;
		String companyCode;
		String recordCode;
		CallableStatement cstmt = null;
		ResultSet rs = null;

		/**
		 * @param userId
		 */
		public CoSellStatusDescriptionRepositoryWork(String userId, String companyCode, String recordCode) {
			super();
			this.userId = userId;
			this.companyCode=companyCode;
			this.recordCode=recordCode;
		}

		@Override
		public void execute(Connection connection) throws SQLException {

			list = new ArrayList<CoSellStatusDTO>();
			coSellStatusDTO = new CoSellStatusDTO();

				String query = "{call " + FacesUtils.SCHEMA_DBO
						+ ".spGetCoSellStatusDescription('" + userId + "','" + companyCode + "','"+recordCode+"')}";

				System.out.println("Query in CoSellStatusDescriptionRepositoryWork " + query);

				logger.debug("Query in CoSellStatusDescriptionRepositoryWork " + query);

				cstmt = connection.prepareCall(query);
				rs = cstmt.executeQuery();

			try {
					if (rs != null) 
					{
						while (rs.next()) 
						{
							coSellStatusDTO.setCoSellStatusDescription(rs.getString("Description").isEmpty()?rs.getString("CoSellStatusCode"):rs.getString("Description"));														
							list.add(coSellStatusDTO);
							coSellStatusDTO = new CoSellStatusDTO();
						}
					} 
					else 
					{
						list = new ArrayList<CoSellStatusDTO>();
					}
					System.out.println("List size in CoSellStatusDescriptionRepositoryWork " + list.size());
					logger.debug("List size in CoSellStatusDescriptionRepositoryWork " + list.size());
			} 
			catch (Exception e) 
			{
				System.out.println("Exception in CoSellStatusDescriptionRepositoryWork --> execute.");
				logger.debug("Exception in CoSellStatusDescriptionRepositoryWork --> execute.");
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
		public List<CoSellStatusDTO> getList() {
			return list;
		}

	
	}
}
