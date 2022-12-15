package com.disys.analyzer.service.implementation;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.disys.analyzer.model.dto.EmployeeCategoryDTO;
import com.disys.analyzer.repository.EmployeeCategoryRepository;
import com.disys.analyzer.service.EmployeeCategoryService;

/**
 * @author Saravanan Dhurairaj
 * 
 */
@Service
@Transactional
public class EmployeeCategoryServiceImpl implements EmployeeCategoryService, Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private EmployeeCategoryRepository employeeCategoryRepository;
	
	@Autowired
	Map<String, List<EmployeeCategoryDTO>> employeeCategoryDropDownBean;

	@Override
	public List<EmployeeCategoryDTO> getEmployeeCategoryList(String userId, String companyCode) 
	{
		if(employeeCategoryDropDownBean.containsKey(companyCode)) {
			System.out.println("employeeCategoryDropDownBean has key : " + companyCode);
			return employeeCategoryDropDownBean.get(companyCode);
		}
		List<EmployeeCategoryDTO> list = employeeCategoryRepository.getEmployeeCategoryList(userId, companyCode);
		employeeCategoryDropDownBean.put(companyCode, list);
		return list; 
	}
	
	@Override
	public List<EmployeeCategoryDTO> getEmployeeCategoryDescription(String userId, String companyCode, String recordCode) 
	{
		return employeeCategoryRepository.getEmployeeCategoryDescription(userId, companyCode, recordCode);
	}
	
}
