/**
 * 
 */
package com.disys.analyzer.repository.custom;

import java.util.List;

import com.disys.analyzer.model.dto.ProductDTO;

/**
 * @author muhammad.ghauri
 *
 */
public interface ProductRepositoryCustom 
{
	public List<ProductDTO> getProductList (String userId);
	public List<ProductDTO> getProductList(String userId, String companyCode);
	public List<ProductDTO> getProductDescription(String userId, String companyCode, String recordCode);

}
