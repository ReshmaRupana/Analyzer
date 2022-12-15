/**
 * 
 */
package com.disys.analyzer.service.implementation;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.disys.analyzer.model.dto.ProductDTO;
import com.disys.analyzer.repository.ProductRepository;
import com.disys.analyzer.service.ProductService;

/**
 * @author muhammad.ghauri
 *
 */
@Service
@Transactional
public class ProductServiceImpl implements ProductService, Serializable 
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5394904261284296308L;

	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	Map<String, List<ProductDTO>> productDownBean;
	
	/**
	 * 
	 */
	public ProductServiceImpl() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see com.disys.analyzer.service.ProductService#getProductList(java.lang.String)
	 */
	@Override
	public List<ProductDTO> getProductList(String userId) 
	{
		return productRepository.getProductList(userId);
	}
	
	@Override
	public List<ProductDTO> getProductList(String userId, String companyCode) 
	{
		if(productDownBean.containsKey(companyCode)) {
			System.out.println("productDownBean has key : " + companyCode);
			return productDownBean.get(companyCode);
		}
		List<ProductDTO> list = productRepository.getProductList(userId, companyCode);
		productDownBean.put(companyCode, list);
		return list;
	}
	
	@Override
	public List<ProductDTO> getProductDescription(String userId, String companyCode, String recordCode) 
	{
		return productRepository.getProductDescription(userId, companyCode, recordCode);
	}
}
