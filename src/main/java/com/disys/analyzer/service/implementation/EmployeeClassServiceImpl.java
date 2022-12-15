package com.disys.analyzer.service.implementation;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.disys.analyzer.model.dto.EmployeeClassDTO;
import com.disys.analyzer.repository.EmployeeClassRepository;
import com.disys.analyzer.service.EmployeeClassService;

/**
 * @author Saravanan Dhurairaj
 * 
 */
@Service
@Transactional
public class EmployeeClassServiceImpl implements EmployeeClassService, Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private EmployeeClassRepository employeeClassRepository;
	
	@Autowired
	Map<String, List<EmployeeClassDTO>> employeeClassDownBean;

	@Override
	public List<EmployeeClassDTO> getEmployeeClassList(String userId, String companyCode) 
	{
		if(employeeClassDownBean.containsKey(companyCode)) {
			System.out.println("employeeClassDownBean has key : " + companyCode);
			return employeeClassDownBean.get(companyCode);
		}
		List<EmployeeClassDTO> list = employeeClassRepository.getEmployeeClassList(userId, companyCode);
		employeeClassDownBean.put(companyCode, list);
		return list;
	}
	
	@Override
	public List<EmployeeClassDTO> getEmployeeClassDescription(String userId, String companyCode, String recordCode) 
	{
		return employeeClassRepository.getEmployeeClassDescription(userId, companyCode, recordCode);
	}
	
}
