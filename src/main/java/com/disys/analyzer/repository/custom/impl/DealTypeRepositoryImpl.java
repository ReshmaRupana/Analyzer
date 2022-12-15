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
import com.disys.analyzer.model.dto.DealTypeDTO;
import com.disys.analyzer.repository.custom.DealTypeRepositoryCustom;

@Repository
@Transactional(readOnly = true)
public class DealTypeRepositoryImpl implements DealTypeRepositoryCustom{

	@PersistenceContext
	EntityManager entityManager;

	@Override
	public List<DealTypeDTO> getDealTypeList(String userId, String companyCode) 
	{
		Session session = entityManager.unwrap(Session.class);
		DealTypeRepositoryWork work = new DealTypeRepositoryWork(userId, companyCode);	
		session.doWork(work);
		List<DealTypeDTO> list = work.getList();
		return list;
	}
	
	private static class DealTypeRepositoryWork implements Work {
		public Logger logger = LoggerFactory.getLogger(getClass());
		private List<DealTypeDTO> list;
		private DealTypeDTO dealTypeDTO;
		String userId;
		String companyCode;
		String recordCode=Constants.STRING_CONSTANT_ALL;
		CallableStatement cstmt = null;
		ResultSet rs = null;

		/**
		 * @param userId
		 */
		public DealTypeRepositoryWork(String userId, String companyCode) {
			super();
			this.userId = userId;
			this.companyCode=companyCode;
		}

		@Override
		public void execute(Connection connection) throws SQLException {

			list = new ArrayList<DealTypeDTO>();
			dealTypeDTO = new DealTypeDTO();

				String query = "{call " + FacesUtils.SCHEMA_DBO
						+ ".spGetDealTypeList('" + userId + "','" + companyCode + "','"+recordCode+"')}";

				System.out.println("Query in DealTypeRepositoryWork " + query);

				logger.debug("Query in DealTypeRepositoryWork " + query);

				cstmt = connection.prepareCall(query);
				rs = cstmt.executeQuery();

			try {
					if (rs != null) 
					{
						while (rs.next()) 
						{
							dealTypeDTO.setDealTypeCode(rs.getString("DealTypeCode"));
							dealTypeDTO.setDealTypeDescription(rs.getString("Description"));														
							list.add(dealTypeDTO);
							dealTypeDTO = new DealTypeDTO();
						}
					} 
					else 
					{
						list = new ArrayList<DealTypeDTO>();
					}
					System.out.println("List size in DealTypeRepositoryWork " + list.size());
					logger.debug("List size in DealTypeRepositoryWork " + list.size());
			} 
			catch (Exception e) 
			{
				System.out.println("Exception in DealTypeRepositoryWork --> execute.");
				logger.debug("Exception in DealTypeRepositoryWork --> execute.");
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
		public List<DealTypeDTO> getList() {
			return list;
		}

	}
	

	@Override
	public List<DealTypeDTO> getDealTypeDescription(String userId, String companyCode, String recordCode) 
	{
		Session session = entityManager.unwrap(Session.class);
		DealTypeDescriptionRepositoryWork work = new DealTypeDescriptionRepositoryWork(userId, companyCode, recordCode);	
		session.doWork(work);
		List<DealTypeDTO> list = work.getList();
		return list;
	}
	
	private static class DealTypeDescriptionRepositoryWork implements Work {
		public Logger logger = LoggerFactory.getLogger(getClass());
		private List<DealTypeDTO> list;
		private DealTypeDTO dealTypeDTO;
		String userId;
		String companyCode;
		String recordCode;
		CallableStatement cstmt = null;
		ResultSet rs = null;

		/**
		 * @param userId
		 */
		public DealTypeDescriptionRepositoryWork(String userId, String companyCode, String recordCode) {
			super();
			this.userId = userId;
			this.companyCode=companyCode;
			this.recordCode=recordCode;
		}

		@Override
		public void execute(Connection connection) throws SQLException {

			list = new ArrayList<DealTypeDTO>();
			dealTypeDTO = new DealTypeDTO();

				String query = "{call " + FacesUtils.SCHEMA_DBO
						+ ".spGetDealTypeDescription('" + userId + "','" + companyCode + "','"+recordCode+"')}";

				System.out.println("Query in DealTypeDescriptionRepositoryWork " + query);

				logger.debug("Query in DealTypeDescriptionRepositoryWork " + query);

				cstmt = connection.prepareCall(query);
				rs = cstmt.executeQuery();

			try {
					if (rs != null) 
					{
						while (rs.next()) 
						{
							dealTypeDTO.setDealTypeDescription(rs.getString("Description").isEmpty()?rs.getString("DealTypeCode"):rs.getString("Description"));														
							list.add(dealTypeDTO);
							dealTypeDTO = new DealTypeDTO();
						}
					} 
					else 
					{
						list = new ArrayList<DealTypeDTO>();
					}
					System.out.println("List size in DealTypeDescriptionRepositoryWork " + list.size());
					logger.debug("List size in DealTypeDescriptionRepositoryWork " + list.size());
			} 
			catch (Exception e) 
			{
				System.out.println("Exception in DealTypeDescriptionRepositoryWork --> execute.");
				logger.debug("Exception in DealTypeDescriptionRepositoryWork --> execute.");
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
		public List<DealTypeDTO> getList() {
			return list;
		}

	
	}
}
