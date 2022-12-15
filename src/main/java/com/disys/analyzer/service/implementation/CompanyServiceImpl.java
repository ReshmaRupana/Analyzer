package com.disys.analyzer.service.implementation;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.disys.analyzer.model.dto.CompanyDTO;
import com.disys.analyzer.repository.CompanyRepository;
import com.disys.analyzer.service.CompanyService;
/**
 * @author Saravanan Dhurairaj
 * 
 */
@Service
@Transactional
public class CompanyServiceImpl implements CompanyService, Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private CompanyRepository companyRepository;
	
	@Override
	public List<CompanyDTO> getCompanyList(String userId) {
		// TODO Auto-generated method stub
		return companyRepository.getCompanyList(userId);
	}
	public List<CompanyDTO> getCompanyDescription(String userId, String recordCode){
		// TODO Auto-generated method stub
		return companyRepository.getCompanyDescription(userId, recordCode);
	}
}
