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
import com.disys.analyzer.model.dto.HolidayDTO;
import com.disys.analyzer.repository.custom.HolidayRepositoryCustom;

@Repository
@Transactional(readOnly = true)
public class HolidayRepositoryImpl implements HolidayRepositoryCustom{

	@PersistenceContext
	EntityManager entityManager;

	@Override
	public List<HolidayDTO> getHolidayList(String userId, String companyCode) 
	{
		Session session = entityManager.unwrap(Session.class);
		HolidayRepositoryWork work = new HolidayRepositoryWork(userId, companyCode);	
		session.doWork(work);
		List<HolidayDTO> list = work.getList();
		return list;
	}
	
	private static class HolidayRepositoryWork implements Work {
		public Logger logger = LoggerFactory.getLogger(getClass());
		private List<HolidayDTO> list;
		private HolidayDTO holidayDTO;
		String userId;
		String companyCode;
		String recordCode=Constants.STRING_CONSTANT_ALL;
		CallableStatement cstmt = null;
		ResultSet rs = null;

		/**
		 * @param userId
		 */
		public HolidayRepositoryWork(String userId, String companyCode) {
			super();
			this.userId = userId;
			this.companyCode=companyCode;
		}

		@Override
		public void execute(Connection connection) throws SQLException {

			list = new ArrayList<HolidayDTO>();
			holidayDTO = new HolidayDTO();

				String query = "{call " + FacesUtils.SCHEMA_DBO
						+ ".spGetHolidayList('" + userId + "','" + companyCode + "','"+recordCode+"')}";

				System.out.println("Query in HolidayRepositoryWork " + query);

				logger.debug("Query in HolidayRepositoryWork " + query);

				cstmt = connection.prepareCall(query);
				rs = cstmt.executeQuery();

			try {
					if (rs != null) 
					{
						while (rs.next()) 
						{
							holidayDTO.setHolidayCode(rs.getString("HolidayCode"));
							holidayDTO.setHolidayDescription(rs.getString("Description"));														
							list.add(holidayDTO);
							holidayDTO = new HolidayDTO();
						}
					} 
					else 
					{
						list = new ArrayList<HolidayDTO>();
					}
					System.out.println("List size in HolidayRepositoryWork " + list.size());
					logger.debug("List size in HolidayRepositoryWork " + list.size());
			} 
			catch (Exception e) 
			{
				System.out.println("Exception in HolidayRepositoryWork --> execute.");
				logger.debug("Exception in HolidayRepositoryWork --> execute.");
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
		public List<HolidayDTO> getList() {
			return list;
		}

	}
	

	@Override
	public List<HolidayDTO> getHolidayDescription(String userId, String companyCode, String recordCode) 
	{
		Session session = entityManager.unwrap(Session.class);
		HolidayDescriptionRepositoryWork work = new HolidayDescriptionRepositoryWork(userId, companyCode, recordCode);	
		session.doWork(work);
		List<HolidayDTO> list = work.getList();
		return list;
	}
	
	private static class HolidayDescriptionRepositoryWork implements Work {
		public Logger logger = LoggerFactory.getLogger(getClass());
		private List<HolidayDTO> list;
		private HolidayDTO holidayDTO;
		String userId;
		String companyCode;
		String recordCode;
		CallableStatement cstmt = null;
		ResultSet rs = null;

		/**
		 * @param userId
		 */
		public HolidayDescriptionRepositoryWork(String userId, String companyCode, String recordCode) {
			super();
			this.userId = userId;
			this.companyCode=companyCode;
			this.recordCode=recordCode;
		}

		@Override
		public void execute(Connection connection) throws SQLException {

			list = new ArrayList<HolidayDTO>();
			holidayDTO = new HolidayDTO();

				String query = "{call " + FacesUtils.SCHEMA_DBO
						+ ".spGetHolidayDescription('" + userId + "','" + companyCode + "','"+recordCode+"')}";

				System.out.println("Query in HolidayDescriptionRepositoryWork " + query);

				logger.debug("Query in HolidayDescriptionRepositoryWork " + query);

				cstmt = connection.prepareCall(query);
				rs = cstmt.executeQuery();

			try {
					if (rs != null) 
					{
						while (rs.next()) 
						{
							holidayDTO.setHolidayDescription(rs.getString("Description").isEmpty()?rs.getString("HolidayCode"):rs.getString("Description"));														
							list.add(holidayDTO);
							holidayDTO = new HolidayDTO();
						}
					} 
					else 
					{
						list = new ArrayList<HolidayDTO>();
					}
					System.out.println("List size in HolidayDescriptionRepositoryWork " + list.size());
					logger.debug("List size in HolidayDescriptionRepositoryWork " + list.size());
			} 
			catch (Exception e) 
			{
				System.out.println("Exception in HolidayDescriptionRepositoryWork --> execute.");
				logger.debug("Exception in HolidayDescriptionRepositoryWork --> execute.");
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
		public List<HolidayDTO> getList() {
			return list;
		}

	
	}
}
