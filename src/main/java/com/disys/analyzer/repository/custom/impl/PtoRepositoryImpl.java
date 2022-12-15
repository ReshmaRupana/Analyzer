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
import com.disys.analyzer.model.dto.PtoDTO;
import com.disys.analyzer.repository.custom.PtoRepositoryCustom;

@Repository
@Transactional(readOnly = true)
public class PtoRepositoryImpl implements PtoRepositoryCustom{

	@PersistenceContext
	EntityManager entityManager;

	@Override
	public List<PtoDTO> getPtoList(String userId, String companyCode) 
	{
		Session session = entityManager.unwrap(Session.class);
		PtoRepositoryWork work = new PtoRepositoryWork(userId, companyCode);	
		session.doWork(work);
		List<PtoDTO> list = work.getList();
		return list;
	}
	
	private static class PtoRepositoryWork implements Work {
		public Logger logger = LoggerFactory.getLogger(getClass());
		private List<PtoDTO> list;
		private PtoDTO ptoDTO;
		String userId;
		String companyCode;
		String recordCode=Constants.STRING_CONSTANT_ALL;
		CallableStatement cstmt = null;
		ResultSet rs = null;

		/**
		 * @param userId
		 */
		public PtoRepositoryWork(String userId, String companyCode) {
			super();
			this.userId = userId;
			this.companyCode=companyCode;
		}

		@Override
		public void execute(Connection connection) throws SQLException {

			list = new ArrayList<PtoDTO>();
			ptoDTO = new PtoDTO();

				String query = "{call " + FacesUtils.SCHEMA_DBO
						+ ".spGetPTOList('" + userId + "','" + companyCode + "','"+recordCode+"')}";

				System.out.println("Query in PtoRepositoryWork " + query);

				logger.debug("Query in PtoRepositoryWork " + query);

				cstmt = connection.prepareCall(query);
				rs = cstmt.executeQuery();

			try {
					if (rs != null) 
					{
						while (rs.next()) 
						{
							ptoDTO.setPtoCode(rs.getString("PTOCode"));
							ptoDTO.setPtoDescription(rs.getString("Description"));														
							list.add(ptoDTO);
							ptoDTO = new PtoDTO();
						}
					} 
					else 
					{
						list = new ArrayList<PtoDTO>();
					}
					System.out.println("List size in PtoRepositoryWork " + list.size());
					logger.debug("List size in PtoRepositoryWork " + list.size());
			} 
			catch (Exception e) 
			{
				System.out.println("Exception in PtoRepositoryWork --> execute.");
				logger.debug("Exception in PtoRepositoryWork --> execute.");
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
		public List<PtoDTO> getList() {
			return list;
		}

	}
	

	@Override
	public List<PtoDTO> getPtoDescription(String userId, String companyCode, String recordCode) 
	{
		Session session = entityManager.unwrap(Session.class);
		PtoDescriptionRepositoryWork work = new PtoDescriptionRepositoryWork(userId, companyCode, recordCode);	
		session.doWork(work);
		List<PtoDTO> list = work.getList();
		return list;
	}
	
	private static class PtoDescriptionRepositoryWork implements Work {
		public Logger logger = LoggerFactory.getLogger(getClass());
		private List<PtoDTO> list;
		private PtoDTO ptoDTO;
		String userId;
		String companyCode;
		String recordCode;
		CallableStatement cstmt = null;
		ResultSet rs = null;

		/**
		 * @param userId
		 */
		public PtoDescriptionRepositoryWork(String userId, String companyCode, String recordCode) {
			super();
			this.userId = userId;
			this.companyCode=companyCode;
			this.recordCode=recordCode;
		}

		@Override
		public void execute(Connection connection) throws SQLException {

			list = new ArrayList<PtoDTO>();
			ptoDTO = new PtoDTO();

				String query = "{call " + FacesUtils.SCHEMA_DBO
						+ ".spGetPTODescription('" + userId + "','" + companyCode + "','"+recordCode+"')}";

				System.out.println("Query in PtoDescriptionRepositoryWork " + query);

				logger.debug("Query in PtoDescriptionRepositoryWork " + query);

				cstmt = connection.prepareCall(query);
				rs = cstmt.executeQuery();

			try {
					if (rs != null) 
					{
						while (rs.next()) 
						{
							ptoDTO.setPtoDescription(rs.getString("Description").isEmpty()?rs.getString("PTOCode"):rs.getString("Description"));														
							list.add(ptoDTO);
							ptoDTO = new PtoDTO();
						}
					} 
					else 
					{
						list = new ArrayList<PtoDTO>();
					}
					System.out.println("List size in PtoDescriptionRepositoryWork " + list.size());
					logger.debug("List size in PtoDescriptionRepositoryWork " + list.size());
			} 
			catch (Exception e) 
			{
				System.out.println("Exception in PtoDescriptionRepositoryWork --> execute.");
				logger.debug("Exception in PtoDescriptionRepositoryWork --> execute.");
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
		public List<PtoDTO> getList() {
			return list;
		}

	
	}
}
