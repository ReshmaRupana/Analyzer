/**
 * 
 */
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
import com.disys.analyzer.model.dto.ProductDTO;
import com.disys.analyzer.repository.custom.ProductRepositoryCustom;

/**
 * @author muhammad.ghauri
 *
 */
@Repository
@Transactional(readOnly = true)
public class ProductRepositoryImpl implements ProductRepositoryCustom 
{
	public Logger logger = LoggerFactory.getLogger(getClass());
	
	@PersistenceContext
	EntityManager entityManager;
	
	/**
	 * 
	 */
	public ProductRepositoryImpl() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see com.disys.analyzer.repository.custom.ProductRepositoryCustom#getProductList()
	 */
	@Override
	public List<ProductDTO> getProductList(String userId) 
	{
		Session session = entityManager.unwrap(Session.class);
		ProductRepositoryWork work = new ProductRepositoryWork(userId);	
		session.doWork(work);
		List<ProductDTO> list = work.getList();
		return list;
	}
	
	private static class ProductRepositoryWork implements Work {
		public Logger logger = LoggerFactory.getLogger(getClass());
		private List<ProductDTO> list;
		private ProductDTO product;
		String userId;
		
		CallableStatement cstmt = null;
		ResultSet rs = null;
		
		String result;

		/**
		 * @param userId
		 */
		public ProductRepositoryWork(String userId) {
			super();
			this.userId = userId;
		}

		@Override
		public void execute(Connection connection) throws SQLException {

			list = new ArrayList<ProductDTO>();
			product = new ProductDTO();

				String query = "{call " + FacesUtils.SCHEMA_WIRELESS
						+ ".spGetProductsList('" + userId + "')}";

				System.out.println("Query in ProductRepositoryWork " + query);

				logger.debug("Query in ProductRepositoryWork " + query);

				cstmt = connection.prepareCall(query);
				rs = cstmt.executeQuery();

			try {
					if (rs != null) 
					{
						while (rs.next()) 
						{
							product.setPsProductId(rs.getInt("PSProductId".toUpperCase()));
							product.setpSProductCode(rs.getString("PSProductCode".toUpperCase()));
							product.setProductDescription(rs.getString("ProductDescription".toUpperCase()));
							product.setStatus(rs.getString("Status".toUpperCase()));
							
							product.setProductLabel(rs.getString("ProductLabel".toUpperCase()));
							
							list.add(product);
							product = new ProductDTO();
						}
					} 
					else 
					{
						list = new ArrayList<ProductDTO>();
					}
					System.out.println("List size in ProductRepositoryWork " + list.size());
					logger.debug("List size in ProductRepositoryWork " + list.size());
			} 
			catch (Exception e) 
			{
				System.out.println("Exception in ProductRepositoryWork --> execute.");
				logger.debug("Exception in ProductRepositoryWork --> execute.");
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
		public List<ProductDTO> getList() {
			return list;
		}

		/**
		 * @return the result
		 */
		public String getResult() {
			return result;
		}
	}
	
	@Override
	public List<ProductDTO> getProductList(String userId, String companyCode) 
	{
		Session session = entityManager.unwrap(Session.class);
		ProductRepositorysWork work = new ProductRepositorysWork(userId, companyCode);	
		session.doWork(work);
		List<ProductDTO> list = work.getList();
		return list;
	}
	
	private static class ProductRepositorysWork implements Work {
		public Logger logger = LoggerFactory.getLogger(getClass());
		private List<ProductDTO> list;
		private ProductDTO productDTO;
		String userId;
		String companyCode;
		String recordCode=Constants.STRING_CONSTANT_ALL;
		CallableStatement cstmt = null;
		ResultSet rs = null;

		/**
		 * @param userId
		 */
		public ProductRepositorysWork(String userId, String companyCode) {
			super();
			this.userId = userId;
			this.companyCode=companyCode;
		}

		@Override
		public void execute(Connection connection) throws SQLException {

			list = new ArrayList<ProductDTO>();
			productDTO = new ProductDTO();

				String query = "{call " + FacesUtils.SCHEMA_DBO
						+ ".spGetProductListNew('" + userId + "','" + companyCode + "','"+recordCode+"')}";

				System.out.println("Query in ProductRepositoryWork " + query);

				logger.debug("Query in ProductRepositoryWork " + query);

				cstmt = connection.prepareCall(query);
				rs = cstmt.executeQuery();

			try {
					if (rs != null) 
					{
						while (rs.next()) 
						{
							productDTO.setProductDescription(rs.getString("ProductDescription".toUpperCase()));
							productDTO.setProductLabel(rs.getString("ProductLabel".toUpperCase()));														
							list.add(productDTO);
							productDTO = new ProductDTO();
						}
					} 
					else 
					{
						list = new ArrayList<ProductDTO>();
					}
					System.out.println("List size in ProductRepositoryWork " + list.size());
					logger.debug("List size in ProductRepositoryWork " + list.size());
			} 
			catch (Exception e) 
			{
				System.out.println("Exception in ProductRepositoryWork --> execute.");
				logger.debug("Exception in ProductRepositoryWork --> execute.");
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
		public List<ProductDTO> getList() {
			return list;
		}

	}
	

	@Override
	public List<ProductDTO> getProductDescription(String userId, String companyCode, String recordCode) 
	{
		Session session = entityManager.unwrap(Session.class);
		ProductDescriptionRepositoryWork work = new ProductDescriptionRepositoryWork(userId, companyCode, recordCode);	
		session.doWork(work);
		List<ProductDTO> list = work.getList();
		return list;
	}
	
	private static class ProductDescriptionRepositoryWork implements Work {
		public Logger logger = LoggerFactory.getLogger(getClass());
		private List<ProductDTO> list;
		private ProductDTO productDTO;
		String userId;
		String companyCode;
		String recordCode;
		CallableStatement cstmt = null;
		ResultSet rs = null;

		/**
		 * @param userId
		 */
		public ProductDescriptionRepositoryWork(String userId, String companyCode, String recordCode) {
			super();
			this.userId = userId;
			this.companyCode=companyCode;
			this.recordCode=recordCode;
		}

		@Override
		public void execute(Connection connection) throws SQLException {

			list = new ArrayList<ProductDTO>();
			productDTO = new ProductDTO();

				String query = "{call " + FacesUtils.SCHEMA_DBO
						+ ".spGetProductDescription('" + userId + "','" + companyCode + "','"+recordCode+"')}";

				System.out.println("Query in ProductDescriptionRepositoryWork " + query);

				logger.debug("Query in ProductDescriptionRepositoryWork " + query);

				cstmt = connection.prepareCall(query);
				rs = cstmt.executeQuery();

			try {
					if (rs != null) 
					{
						while (rs.next())
						{
							productDTO.setProductLabel(rs.getString("ProductLabel".toUpperCase()).isEmpty()?rs.getString("ProductDescription".toUpperCase()):rs.getString("ProductLabel".toUpperCase()));														
							list.add(productDTO);
							productDTO = new ProductDTO();
						}
					} 
					else 
					{
						list = new ArrayList<ProductDTO>();
					}
					System.out.println("List size in ProductDescriptionRepositoryWork " + list.size());
					logger.debug("List size in ProductDescriptionRepositoryWork " + list.size());
			} 
			catch (Exception e) 
			{
				System.out.println("Exception in ProductDescriptionRepositoryWork --> execute.");
				logger.debug("Exception in ProductDescriptionRepositoryWork --> execute.");
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
		public List<ProductDTO> getList() {
			return list;
		}

	
	}
}
