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
import com.disys.analyzer.model.dto.OfficeDTO;
import com.disys.analyzer.repository.custom.OfficeRepositoryCustom;

@Repository
@Transactional(readOnly = true)
public class OfficeRepositoryImpl implements OfficeRepositoryCustom{

	@PersistenceContext
	EntityManager entityManager;

	@Override
	public List<OfficeDTO> getOfficeList(String userId, String companyCode) 
	{
		Session session = entityManager.unwrap(Session.class);
		OfficeRepositoryWork work = new OfficeRepositoryWork(userId, companyCode);	
		session.doWork(work);
		List<OfficeDTO> list = work.getList();
		return list;
	}
	
	private static class OfficeRepositoryWork implements Work {
		public Logger logger = LoggerFactory.getLogger(getClass());
		private List<OfficeDTO> list;
		private OfficeDTO officeDTO;
		String userId;
		String companyCode;
		String recordCode=Constants.STRING_CONSTANT_ALL;
		CallableStatement cstmt = null;
		ResultSet rs = null;

		/**
		 * @param userId
		 */
		public OfficeRepositoryWork(String userId, String companyCode) {
			super();
			this.userId = userId;
			this.companyCode=companyCode;
		}

		@Override
		public void execute(Connection connection) throws SQLException {

			list = new ArrayList<OfficeDTO>();
			officeDTO = new OfficeDTO();

				String query = "{call " + FacesUtils.SCHEMA_DBO
						+ ".spGetOfficeList('" + userId + "','" + companyCode + "','"+recordCode+"')}";

				System.out.println("Query in OfficeRepositoryWork " + query);

				logger.debug("Query in OfficeRepositoryWork " + query);

				cstmt = connection.prepareCall(query);
				rs = cstmt.executeQuery();

			try {
					if (rs != null) 
					{
						while (rs.next()) 
						{
							officeDTO.setOfficeCode(rs.getString("PSOfficeCode"));
							officeDTO.setOfficeDescription(rs.getString("BranchName"));														
							list.add(officeDTO);
							officeDTO = new OfficeDTO();
						}
					} 
					else 
					{
						list = new ArrayList<OfficeDTO>();
					}
					System.out.println("List size in OfficeRepositoryWork " + list.size());
					logger.debug("List size in OfficeRepositoryWork " + list.size());
			} 
			catch (Exception e) 
			{
				System.out.println("Exception in OfficeRepositoryWork --> execute.");
				logger.debug("Exception in OfficeRepositoryWork --> execute.");
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
		public List<OfficeDTO> getList() {
			return list;
		}

	}
	

	@Override
	public List<OfficeDTO> getOfficeDescription(String userId, String companyCode, String recordCode) 
	{
		Session session = entityManager.unwrap(Session.class);
		OfficeDescriptionRepositoryWork work = new OfficeDescriptionRepositoryWork(userId, companyCode, recordCode);	
		session.doWork(work);
		List<OfficeDTO> list = work.getList();
		return list;
	}
	
	private static class OfficeDescriptionRepositoryWork implements Work {
		public Logger logger = LoggerFactory.getLogger(getClass());
		private List<OfficeDTO> list;
		private OfficeDTO officeDTO;
		String userId;
		String companyCode;
		String recordCode;
		CallableStatement cstmt = null;
		ResultSet rs = null;

		/**
		 * @param userId
		 */
		public OfficeDescriptionRepositoryWork(String userId, String companyCode, String recordCode) {
			super();
			this.userId = userId;
			this.companyCode=companyCode;
			this.recordCode=recordCode;
		}

		@Override
		public void execute(Connection connection) throws SQLException {

			list = new ArrayList<OfficeDTO>();
			officeDTO = new OfficeDTO();

				String query = "{call " + FacesUtils.SCHEMA_DBO
						+ ".spGetOfficeDescription('" + userId + "','" + companyCode + "','"+recordCode+"')}";

				System.out.println("Query in OfficeDescriptionRepositoryWork " + query);

				logger.debug("Query in OfficeDescriptionRepositoryWork " + query);

				cstmt = connection.prepareCall(query);
				rs = cstmt.executeQuery();

			try {
					if (rs != null) 
					{
						while (rs.next()) 
						{
							officeDTO.setOfficeCode(rs.getString("BranchName").isEmpty()?rs.getString("BranchName"):rs.getString("BranchName"));														
							list.add(officeDTO);
							officeDTO = new OfficeDTO();
						}
					} 
					else 
					{
						list = new ArrayList<OfficeDTO>();
					}
					System.out.println("List size in OfficeDescriptionRepositoryWork " + list.size());
					logger.debug("List size in OfficeDescriptionRepositoryWork " + list.size());
			} 
			catch (Exception e) 
			{
				System.out.println("Exception in OfficeDescriptionRepositoryWork --> execute.");
				logger.debug("Exception in OfficeDescriptionRepositoryWork --> execute.");
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
		public List<OfficeDTO> getList() {
			return list;
		}

	
	}
}
