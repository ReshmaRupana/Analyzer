/**
 * 
 */
package com.disys.analyzer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.disys.analyzer.model.SickLeaveCost;
import com.disys.analyzer.repository.custom.SickLeaveCostRepositoryCustom;

/**
 * @author Sajid
 * 
 */
public interface SickLeaveCostRepository extends JpaRepository<SickLeaveCost, String>, SickLeaveCostRepositoryCustom
{
	
	public SickLeaveCost findByWorksiteStateCode(String workSiteId);
	
	public SickLeaveCost findByZipCode(String zipCode);
	
}
