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

import com.disys.analyzer.config.util.FacesUtils;
import com.disys.analyzer.model.dto.StatesAllDTO;
import com.disys.analyzer.repository.custom.StatesRepositoryCustom;

@Repository
@Transactional(readOnly = true)
public class StatesRepositoryImpl implements StatesRepositoryCustom{

	@PersistenceContext
	EntityManager entityManager;

	/* (non-Javadoc)
	 * @see com.disys.analyzer.repository.custom.StatesRepositoryCustom#getAllStatess()
	 */
	@Override
	public List<StatesAllDTO> getAllStatess(String userId) 
	{
		Session session = entityManager.unwrap(Session.class);
		StatesRepositoryWork work = new StatesRepositoryWork(userId);	
		session.doWork(work);
		List<StatesAllDTO> list = work.getList();
		return list;
	}
	
	private static class StatesRepositoryWork implements Work {
		public Logger logger = LoggerFactory.getLogger(getClass());
		private List<StatesAllDTO> list;
		private StatesAllDTO state;
		String userId;
		
		CallableStatement cstmt = null;
		ResultSet rs = null;

		/**
		 * @param userId
		 */
		public StatesRepositoryWork(String userId) {
			super();
			this.userId = userId;
		}

		@Override
		public void execute(Connection connection) throws SQLException {

			list = new ArrayList<StatesAllDTO>();
			state = new StatesAllDTO();

				String query = "{call " + FacesUtils.SCHEMA_DBO
						+ ".getAllStates('" + userId + "')}";

				System.out.println("Query in StatesRepositoryWork " + query);

				logger.debug("Query in StatesRepositoryWork " + query);

				cstmt = connection.prepareCall(query);
				rs = cstmt.executeQuery();

			try {
					if (rs != null) 
					{
						while (rs.next()) 
						{
							state.setStateCode(rs.getString("StateCode"));
							state.setStateName(rs.getString("StateName"));
							
							
							list.add(state);
							state = new StatesAllDTO();
						}
					} 
					else 
					{
						list = new ArrayList<StatesAllDTO>();
					}
					System.out.println("List size in StatesRepositoryWork " + list.size());
					logger.debug("List size in StatesRepositoryWork " + list.size());
			} 
			catch (Exception e) 
			{
				System.out.println("Exception in StatesRepositoryWork --> execute.");
				logger.debug("Exception in StatesRepositoryWork --> execute.");
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
		public List<StatesAllDTO> getList() {
			return list;
		}

	
	}
}
