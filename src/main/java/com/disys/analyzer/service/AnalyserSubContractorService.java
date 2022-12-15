/**
 * 
 */
package com.disys.analyzer.service;

import java.util.List;

import com.disys.analyzer.model.AnalyserSubContractor;
import com.disys.analyzer.model.dto.Supplier;

/**
 * @author Sajid
 * 
 */
public interface AnalyserSubContractorService {

	/*
	 * 
	 * @varLoggedOnUserID VARCHAR(50),
	 * 
	 * @varCompanyName VARCHAR(200) = 'A',
	 * 
	 * @varOrderBy VARCHAR(25) = 'Company',
	 * 
	 * @varSearchString VARCHAR(50) = 'ALL',
	 * 
	 * @varRecordStatus VARCHAR(2) = '99' --parameter not used
	 */

	// ANALYSERCONTRACTOR_LIST_REPORT.SQL={call
	// dbo.spGetAnalyserAllContractor('[0]','[1]','[2]','[3]','[4]')}
	// SQL_ORDERLIST=LegalName|Poc_Name|FIN|PaymentTerm|Address|UpdatedBy|Contractor_ID|PrimaryPhone|PrimaryEmail
	public List<AnalyserSubContractor> getAnalyserAllContractors(String userId,
			String companyName, String orderBy, String searchString,
			String recordStatus, String companyCode);
	
	public List<AnalyserSubContractor> getAnalyserAllContractors(String userId,
			String companyName, String approvalStatus, String companyCode);
	
	public Supplier getSupplierInfo(String supplierId);
	
	public String changeSubcontractorStatus(String userId, Integer contractorId, Integer status);
	public Boolean updateSupplierInfo(String userId, Integer contractorId, String subContractorName,
			String address1, String address2, String city, String state, String zipCode,  String companyCode);
}
