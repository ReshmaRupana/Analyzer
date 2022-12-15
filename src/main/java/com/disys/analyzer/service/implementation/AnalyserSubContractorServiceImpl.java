/**
 * 
 */
package com.disys.analyzer.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.disys.analyzer.model.AnalyserSubContractor;
import com.disys.analyzer.model.dto.Supplier;
import com.disys.analyzer.repository.AnalyserSubContractorRepository;
import com.disys.analyzer.service.AnalyserSubContractorService;

/**
 * @author Sajid
 * 
 */
@Service
@Transactional
public class AnalyserSubContractorServiceImpl implements
		AnalyserSubContractorService {

	@Autowired
	private AnalyserSubContractorRepository repository;

	@Override
	public List<AnalyserSubContractor> getAnalyserAllContractors(String userId,
			String companyName, String orderBy, String searchString,
			String recordStatus, String companyCode) {

		List<AnalyserSubContractor> results = repository.getAllSubContractors(
				userId, companyName, orderBy, searchString, recordStatus, companyCode);
		return results;

		/*Role role = repository.inOnlyTest("Gregory.Armstrong@DISYS.COM");
		System.out.println("DEsc "+ role.getRoleDesc());*/
	}

	@Override
	public Supplier getSupplierInfo(String supplierId) {
		return repository.getSupplierInfo(supplierId);
	}

	@Override
	public String changeSubcontractorStatus(String userId, Integer contractorId, Integer status)
	{
		return repository.changeSubcontractorStatus(userId, contractorId, status);
	}

	@Override
	public List<AnalyserSubContractor> getAnalyserAllContractors(String userId, String companyName, String approvalStatus, String companyCode)
	{
		return repository.getAllSubContractors(
				userId, companyName, approvalStatus, companyCode);
	}

	@Override
	public Boolean updateSupplierInfo(String userId, Integer contractorId, String subContractorName,
			String address1, String address2, String city, String state, String zipCode,  String companyCode)
	{
		return repository.updateSupplierInfo(userId, contractorId, subContractorName,
				address1, address2, city, state, zipCode, companyCode);
	}

}
