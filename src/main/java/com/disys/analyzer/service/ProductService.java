/**
 * 
 */
package com.disys.analyzer.service;

import java.util.List;

import com.disys.analyzer.model.dto.ProductDTO;

/**
 * @author muhammad.ghauri
 *
 */
public interface ProductService 
{
	public List<ProductDTO> getProductList (String userId);
	public List<ProductDTO> getProductList(String userId, String companyCode);
	public List<ProductDTO> getProductDescription(String userId, String companyCode, String recordCode);
}
