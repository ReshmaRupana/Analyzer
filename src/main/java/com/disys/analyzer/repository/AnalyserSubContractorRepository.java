/**
 * 
 */
package com.disys.analyzer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.disys.analyzer.model.AnalyserSubContractor;
import com.disys.analyzer.repository.custom.AnalyserSubContractorRepositoryCustom;

/**
 * @author Sajid
 * 
 */
public interface AnalyserSubContractorRepository extends
		JpaRepository<AnalyserSubContractor, Integer>
	,AnalyserSubContractorRepositoryCustom {

	//@Procedure(name="inOnlyTest")
	//Role inOnlyTest(@Param("varLoggedOnUserID") String message);
	
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

	// Explicitly mapped to named stored procedure {@code
	// AnalyserSubContractor.spGetAnalyserAllContractor} in the
	// {@link EntityManager}.
	// By default, we would've try to find a procedure declaration named
	// AnalyserSubContractor.spGetAnalyserAllContractorBackedByOtherNamedStoredProcedure

	/*
	 * @Procedure(name = "AnalyserSubContractor.spGetAnalyserAllContractor")
	 * List<AnalyserSubContractor>
	 * spGetAnalyserAllContractorBackedByOtherNamedStoredProcedure(
	 * 
	 * @Param("varLoggedOnUserID") String userId,
	 * 
	 * @Param("varCompanyName") String companyName,
	 * 
	 * @Param("varOrderBy") String orderBy,
	 * 
	 * @Param("varSearchString") String searchString,
	 * 
	 * @Param("varRecordStatus") String recordStatus);
	 */

	// @Procedure(name = "AnalyserSubContractor.spGetAnalyserAllContractor")

	/*@Procedure("dbo.spSajid")
	List<AnalyserSubContractor> getAnalyserAllContractor(
			@Param("varLoggedOnUserID") String userId,
			@Param("varCompanyName") String companyName,
			@Param("varOrderBy") String orderBy,
			@Param("varSearchString") String searchString,
			@Param("varRecordStatus") String recordStatus);*/
	
	/*@Procedure
	List<AnalyserSubContractor> spSajid(
			String userId,
			String companyName,
			String orderBy,
			String searchString,
			String recordStatus);*/

	/*@Procedure("dbo.spSajid")
	Object[] getAnalyserAllContractor(
			@Param("varLoggedOnUserID") String userId,
			@Param("varCompanyName") String companyName,
			@Param("varOrderBy") String orderBy,
			@Param("varSearchString") String searchString,
			@Param("varRecordStatus") String recordStatus);*/
	
	/*List<AnalyserSubContractor> spGetAnalyserAllContractor(String userId,
			String companyName, String orderBy, String searchString,
			String recordStatus);*/

}
