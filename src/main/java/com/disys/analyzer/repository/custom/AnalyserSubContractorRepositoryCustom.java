/**
 * 
 */
package com.disys.analyzer.repository.custom;

import java.util.List;

import org.springframework.data.jpa.repository.query.Procedure;

import com.disys.analyzer.model.AnalyserSubContractor;
import com.disys.analyzer.model.dto.Supplier;

/**
 * @author Sajid
 * 
 */
public interface AnalyserSubContractorRepositoryCustom {

	@Procedure
	public List<AnalyserSubContractor> getAllSubContractors(String userId,
			String companyName, String orderBy, String searchString,
			String recordStatus, String companyCode);
	
	public List<AnalyserSubContractor> getAllSubContractors(String userId,
			String companyName, String approvalStatus, String companyCode);

	public Supplier getSupplierInfo(String supplierId);
	
	
	public String changeSubcontractorStatus(String userId, Integer contractorId, Integer status);
	
	public Boolean updateSupplierInfo(String userId, Integer contractorId, String subContractorName,
			String address1, String address2, String city, String state, String zipCode, String companyCode);
	
}
