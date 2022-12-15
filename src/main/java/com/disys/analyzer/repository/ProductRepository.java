/**
 * 
 */
package com.disys.analyzer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.disys.analyzer.model.dto.ProductDTO;
import com.disys.analyzer.repository.custom.ProductRepositoryCustom;

/**
 * @author muhammad.ghauri
 *
 */
public interface ProductRepository extends  JpaRepository<ProductDTO, Long>,ProductRepositoryCustom 
{

}
