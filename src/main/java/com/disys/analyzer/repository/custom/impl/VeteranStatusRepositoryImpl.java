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
import com.disys.analyzer.model.dto.VeteranStatusDTO;
import com.disys.analyzer.repository.custom.VeteranStatusRepositoryCustom;

@Repository
@Transactional(readOnly = true)
public class VeteranStatusRepositoryImpl implements VeteranStatusRepositoryCustom{

	@PersistenceContext
	EntityManager entityManager;

	@Override
	public List<VeteranStatusDTO> getVeteranStatusList(String userId, String companyCode) 
	{
		Session session = entityManager.unwrap(Session.class);
		VeteranStatusRepositoryWork work = new VeteranStatusRepositoryWork(userId, companyCode);	
		session.doWork(work);
		List<VeteranStatusDTO> list = work.getList();
		return list;
	}
	
	private static class VeteranStatusRepositoryWork implements Work {
		public Logger logger = LoggerFactory.getLogger(getClass());
		private List<VeteranStatusDTO> list;
		private VeteranStatusDTO veteranStatusDTO;
		String userId;
		String companyCode;
		String recordCode=Constants.STRING_CONSTANT_ALL;
		CallableStatement cstmt = null;
		ResultSet rs = null;

		/**
		 * @param userId
		 */
		public VeteranStatusRepositoryWork(String userId, String companyCode) {
			super();
			this.userId = userId;
			this.companyCode=companyCode;
		}

		@Override
		public void execute(Connection connection) throws SQLException {

			list = new ArrayList<VeteranStatusDTO>();
			veteranStatusDTO = new VeteranStatusDTO();

				String query = "{call " + FacesUtils.SCHEMA_DBO
						+ ".spGetVeteranStatusList('" + userId + "','" + companyCode + "','"+recordCode+"')}";

				System.out.println("Query in VeteranStatusRepositoryWork " + query);

				logger.debug("Query in VeteranStatusRepositoryWork " + query);

				cstmt = connection.prepareCall(query);
				rs = cstmt.executeQuery();

			try {
					if (rs != null) 
					{
						while (rs.next()) 
						{
							veteranStatusDTO.setVeteranStatusCode(rs.getString("VeteranStatusCode"));
							veteranStatusDTO.setVeteranStatusDescription(rs.getString("Description"));														
							list.add(veteranStatusDTO);
							veteranStatusDTO = new VeteranStatusDTO();
						}
					} 
					else 
					{
						list = new ArrayList<VeteranStatusDTO>();
					}
					System.out.println("List size in VeteranStatusRepositoryWork " + list.size());
					logger.debug("List size in VeteranStatusRepositoryWork " + list.size());
			} 
			catch (Exception e) 
			{
				System.out.println("Exception in VeteranStatusRepositoryWork --> execute.");
				logger.debug("Exception in VeteranStatusRepositoryWork --> execute.");
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
		public List<VeteranStatusDTO> getList() {
			return list;
		}

	}
	

	@Override
	public List<VeteranStatusDTO> getVeteranStatusDescription(String userId, String companyCode, String recordCode) 
	{
		Session session = entityManager.unwrap(Session.class);
		VeteranStatusDescriptionRepositoryWork work = new VeteranStatusDescriptionRepositoryWork(userId, companyCode, recordCode);	
		session.doWork(work);
		List<VeteranStatusDTO> list = work.getList();
		return list;
	}
	
	private static class VeteranStatusDescriptionRepositoryWork implements Work {
		public Logger logger = LoggerFactory.getLogger(getClass());
		private List<VeteranStatusDTO> list;
		private VeteranStatusDTO veteranStatusDTO;
		String userId;
		String companyCode;
		String recordCode;
		CallableStatement cstmt = null;
		ResultSet rs = null;

		/**
		 * @param userId
		 */
		public VeteranStatusDescriptionRepositoryWork(String userId, String companyCode, String recordCode) {
			super();
			this.userId = userId;
			this.companyCode=companyCode;
			this.recordCode=recordCode;
		}

		@Override
		public void execute(Connection connection) throws SQLException {

			list = new ArrayList<VeteranStatusDTO>();
			veteranStatusDTO = new VeteranStatusDTO();

				String query = "{call " + FacesUtils.SCHEMA_DBO
						+ ".spGetVeteranStatusDescription('" + userId + "','" + companyCode + "','"+recordCode+"')}";

				System.out.println("Query in VeteranStatusDescriptionRepositoryWork " + query);

				logger.debug("Query in VeteranStatusDescriptionRepositoryWork " + query);

				cstmt = connection.prepareCall(query);
				rs = cstmt.executeQuery();

			try {
					if (rs != null) 
					{
						while (rs.next()) 
						{
							veteranStatusDTO.setVeteranStatusDescription(rs.getString("Description").isEmpty()?rs.getString("VeteranStatusCode"):rs.getString("Description"));														
							list.add(veteranStatusDTO);
							veteranStatusDTO = new VeteranStatusDTO();
						}
					} 
					else 
					{
						list = new ArrayList<VeteranStatusDTO>();
					}
					System.out.println("List size in VeteranStatusDescriptionRepositoryWork " + list.size());
					logger.debug("List size in VeteranStatusDescriptionRepositoryWork " + list.size());
			} 
			catch (Exception e) 
			{
				System.out.println("Exception in VeteranStatusDescriptionRepositoryWork --> execute.");
				logger.debug("Exception in VeteranStatusDescriptionRepositoryWork --> execute.");
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
		public List<VeteranStatusDTO> getList() {
			return list;
		}

	
	}
}
