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
import com.disys.analyzer.model.dto.VerticalDTO;
import com.disys.analyzer.repository.custom.VerticalRepositoryCustom;

@Repository
@Transactional(readOnly = true)
public class VerticalRepositoryImpl implements VerticalRepositoryCustom{

	@PersistenceContext
	EntityManager entityManager;

	@Override
	public List<VerticalDTO> getVerticalList(String userId, String companyCode) 
	{
		Session session = entityManager.unwrap(Session.class);
		VerticalRepositoryWork work = new VerticalRepositoryWork(userId, companyCode);	
		session.doWork(work);
		List<VerticalDTO> list = work.getList();
		return list;
	}
	
	private static class VerticalRepositoryWork implements Work {
		public Logger logger = LoggerFactory.getLogger(getClass());
		private List<VerticalDTO> list;
		private VerticalDTO verticalDTO;
		String userId;
		String companyCode;
		String recordCode=Constants.STRING_CONSTANT_ALL;
		CallableStatement cstmt = null;
		ResultSet rs = null;

		/**
		 * @param userId
		 */
		public VerticalRepositoryWork(String userId, String companyCode) {
			super();
			this.userId = userId;
			this.companyCode=companyCode;
		}

		@Override
		public void execute(Connection connection) throws SQLException {

			list = new ArrayList<VerticalDTO>();
			verticalDTO = new VerticalDTO();

				String query = "{call " + FacesUtils.SCHEMA_DBO
						+ ".spGetVerticallListNew('" + userId + "','" + companyCode + "','"+recordCode+"')}";

				System.out.println("Query in VerticalRepositoryWork " + query);

				logger.debug("Query in VerticalRepositoryWork " + query);

				cstmt = connection.prepareCall(query);
				rs = cstmt.executeQuery();

			try {
					if (rs != null) 
					{
						while (rs.next()) 
						{
							verticalDTO.setVerticalCode(rs.getString("PSVerticalCode"));
							verticalDTO.setVerticalDescription(rs.getString("VerticalDescription"));														
							list.add(verticalDTO);
							verticalDTO = new VerticalDTO();
						}
					} 
					else 
					{
						list = new ArrayList<VerticalDTO>();
					}
					System.out.println("List size in VerticalRepositoryWork " + list.size());
					logger.debug("List size in VerticalRepositoryWork " + list.size());
			} 
			catch (Exception e) 
			{
				System.out.println("Exception in VerticalRepositoryWork --> execute.");
				logger.debug("Exception in VerticalRepositoryWork --> execute.");
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
		public List<VerticalDTO> getList() {
			return list;
		}

	}
	

	@Override
	public List<VerticalDTO> getVerticalDescription(String userId, String companyCode, String recordCode) 
	{
		Session session = entityManager.unwrap(Session.class);
		VerticalDescriptionRepositoryWork work = new VerticalDescriptionRepositoryWork(userId, companyCode, recordCode);	
		session.doWork(work);
		List<VerticalDTO> list = work.getList();
		return list;
	}
	
	private static class VerticalDescriptionRepositoryWork implements Work {
		public Logger logger = LoggerFactory.getLogger(getClass());
		private List<VerticalDTO> list;
		private VerticalDTO verticalDTO;
		String userId;
		String companyCode;
		String recordCode;
		CallableStatement cstmt = null;
		ResultSet rs = null;

		/**
		 * @param userId
		 */
		public VerticalDescriptionRepositoryWork(String userId, String companyCode, String recordCode) {
			super();
			this.userId = userId;
			this.companyCode=companyCode;
			this.recordCode=recordCode;
		}

		@Override
		public void execute(Connection connection) throws SQLException {

			list = new ArrayList<VerticalDTO>();
			verticalDTO = new VerticalDTO();

				String query = "{call " + FacesUtils.SCHEMA_DBO
						+ ".spGetVerticalDescription('" + userId + "','" + companyCode + "','"+recordCode+"')}";

				System.out.println("Query in VerticalDescriptionRepositoryWork " + query);

				logger.debug("Query in VerticalDescriptionRepositoryWork " + query);

				cstmt = connection.prepareCall(query);
				rs = cstmt.executeQuery();

			try {
					if (rs != null) 
					{
						while (rs.next()) 
						{
							verticalDTO.setVerticalDescription(rs.getString("VerticalDescription").isEmpty()?rs.getString("VerticalDescription"):rs.getString("VerticalDescription"));														
							list.add(verticalDTO);
							verticalDTO = new VerticalDTO();
						}
					} 
					else 
					{
						list = new ArrayList<VerticalDTO>();
					}
					System.out.println("List size in VerticalDescriptionRepositoryWork " + list.size());
					logger.debug("List size in VerticalDescriptionRepositoryWork " + list.size());
			} 
			catch (Exception e) 
			{
				System.out.println("Exception in VerticalDescriptionRepositoryWork --> execute.");
				logger.debug("Exception in VerticalDescriptionRepositoryWork --> execute.");
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
		public List<VerticalDTO> getList() {
			return list;
		}

	
	}
}
