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
import com.disys.analyzer.model.dto.PortfolioDTO;
import com.disys.analyzer.repository.custom.PortfolioRepositoryCustom;

@Repository
@Transactional(readOnly = true)
public class PortfolioRepositoryImpl implements PortfolioRepositoryCustom{

	@PersistenceContext
	EntityManager entityManager;

	/* (non-Javadoc)
	 * @see com.disys.analyzer.repository.custom.PortfolioRepositoryWork#getAllPortfolios()
	 */

	@Override
	public List<PortfolioDTO> getAllPortfolios(String userId, String companyCode) 
	{
		Session session = entityManager.unwrap(Session.class);
		PortfolioRepositoryWorks work = new PortfolioRepositoryWorks(userId, companyCode);	
		session.doWork(work);
		List<PortfolioDTO> list = work.getList();
		return list;
	}
	
	private static class PortfolioRepositoryWorks implements Work {
		public Logger logger = LoggerFactory.getLogger(getClass());
		private List<PortfolioDTO> list;
		private PortfolioDTO portfolio;
		String userId;
		String companyCode;
		CallableStatement cstmt = null;
		ResultSet rs = null;

		/**
		 * @param userId
		 */
		public PortfolioRepositoryWorks(String userId, String companyCode) {
			super();
			this.userId = userId;
			this.companyCode = companyCode;
		}

		@Override
		public void execute(Connection connection) throws SQLException {

			list = new ArrayList<PortfolioDTO>();
			portfolio = new PortfolioDTO();

				String query = "{call " + FacesUtils.SCHEMA_DBO
						+ ".getAllPortfolios('" + userId + "','" + companyCode + "')}";

				System.out.println("Query in PortfolioRepositoryWorks " + query);

				logger.debug("Query in PortfolioRepositoryWorks " + query);

				cstmt = connection.prepareCall(query);
				rs = cstmt.executeQuery();

			try {
					if (rs != null) 
					{
						while (rs.next()) 
						{
							portfolio.setPortfolioCode(rs.getString("PortfolioCode"));
							portfolio.setPortfolioDescription(rs.getString("PortfolioDescription"));
							
							
							list.add(portfolio);
							portfolio = new PortfolioDTO();
						}
					} 
					else 
					{
						list = new ArrayList<PortfolioDTO>();
					}
					System.out.println("List size in PortfolioRepositoryWorks " + list.size());
					logger.debug("List size in PortfolioRepositoryWorks " + list.size());
			} 
			catch (Exception e) 
			{
				System.out.println("Exception in PortfolioRepositoryWorks --> execute.");
				logger.debug("Exception in PortfolioRepositoryWorks --> execute.");
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
		public List<PortfolioDTO> getList() {
			return list;
		}

	
	}
	
}
