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
import com.disys.analyzer.model.dto.CountryDTO;
import com.disys.analyzer.repository.custom.CountryRepositoryCustom;

@Repository
@Transactional(readOnly = true)
public class CountryRepositoryImpl implements CountryRepositoryCustom{

	@PersistenceContext
	EntityManager entityManager;

	/* (non-Javadoc)
	 * @see com.disys.analyzer.repository.custom.CountryRepositoryCustom#getAllCountries()
	 */
	@Override
	public List<CountryDTO> getAllCountries(String userId) 
	{
		Session session = entityManager.unwrap(Session.class);
		CountryRepositoryWork work = new CountryRepositoryWork(userId);	
		session.doWork(work);
		List<CountryDTO> list = work.getList();
		return list;
	}
	
	private static class CountryRepositoryWork implements Work {
		public Logger logger = LoggerFactory.getLogger(getClass());
		private List<CountryDTO> list;
		private CountryDTO countries;
		String userId;
		
		CallableStatement cstmt = null;
		ResultSet rs = null;

		/**
		 * @param userId
		 */
		public CountryRepositoryWork(String userId) {
			super();
			this.userId = userId;
		}

		@Override
		public void execute(Connection connection) throws SQLException {

			list = new ArrayList<CountryDTO>();
			countries = new CountryDTO();

				String query = "{call " + FacesUtils.SCHEMA_DBO
						+ ".getAllCountries('" + userId + "')}";

				System.out.println("Query in CountryRepositoryWork " + query);

				logger.debug("Query in CountryRepositoryWork " + query);

				cstmt = connection.prepareCall(query);
				rs = cstmt.executeQuery();

			try {
					if (rs != null) 
					{
						while (rs.next()) 
						{
							countries.setCountryCode(rs.getString("CountryCode"));
							countries.setCountryName(rs.getString("CountryName"));
							
							
							list.add(countries);
							countries = new CountryDTO();
						}
					} 
					else 
					{
						list = new ArrayList<CountryDTO>();
					}
					System.out.println("List size in CountryRepositoryWork " + list.size());
					logger.debug("List size in CountryRepositoryWork " + list.size());
			} 
			catch (Exception e) 
			{
				System.out.println("Exception in CountryRepositoryWork --> execute.");
				logger.debug("Exception in CountryRepositoryWork --> execute.");
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
		public List<CountryDTO> getList() {
			return list;
		}

	
	}
}
