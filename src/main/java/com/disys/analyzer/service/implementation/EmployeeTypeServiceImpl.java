package com.disys.analyzer.service.implementation;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.disys.analyzer.model.dto.EmployeeTypeDTO;
import com.disys.analyzer.repository.EmployeeTypeRepository;
import com.disys.analyzer.service.EmployeeTypeService;

/**
 * @author Saravanan Dhurairaj
 * 
 */
@Service
@Transactional
public class EmployeeTypeServiceImpl implements EmployeeTypeService, Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private EmployeeTypeRepository employeeTypeRepository;
	
	@Autowired
	Map<String, List<EmployeeTypeDTO>> employeeTypeDropDownBean;

	@Override
	public List<EmployeeTypeDTO> getEmployeeTypeList(String userId, String companyCode) 
	{
		if(employeeTypeDropDownBean.containsKey(companyCode)) {
			System.out.println("employeeTypeDropDownBean has key : " + companyCode);
			return employeeTypeDropDownBean.get(companyCode);
		}
		List<EmployeeTypeDTO> list = employeeTypeRepository.getEmployeeTypeList(userId, companyCode);
		employeeTypeDropDownBean.put(companyCode, list);
		return list;
	}
	
	@Override
	public List<EmployeeTypeDTO> getEmployeeTypeDescription(String userId, String companyCode, String recordCode) 
	{
		return employeeTypeRepository.getEmployeeTypeDescription(userId, companyCode, recordCode);
	}
	
}
