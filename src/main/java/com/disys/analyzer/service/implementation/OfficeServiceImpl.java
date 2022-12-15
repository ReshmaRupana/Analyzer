package com.disys.analyzer.service.implementation;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.disys.analyzer.model.dto.OfficeDTO;
import com.disys.analyzer.repository.OfficeRepository;
import com.disys.analyzer.service.OfficeService;
/**
 * @author Saravanan Dhurairaj
 * 
 */
@Service
@Transactional
public class OfficeServiceImpl implements OfficeService, Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private OfficeRepository officeRepository;
	
	@Autowired
	Map<String, List<OfficeDTO>> officeDropDownBean;
	
	@Override
	public List<OfficeDTO> getOfficeList(String userId, String companyCode) 
	{
		if(officeDropDownBean.containsKey(companyCode)) {
			System.out.println("officeDropDownBean has key : " + companyCode);
			return officeDropDownBean.get(companyCode);
		}
		List<OfficeDTO> list = officeRepository.getOfficeList(userId, companyCode);
		officeDropDownBean.put(companyCode, list);
		return list;
	}
	
	@Override
	public List<OfficeDTO> getOfficeDescription(String userId, String companyCode, String recordCode) 
	{
		return officeRepository.getOfficeDescription(userId, companyCode, recordCode);
	}
	
}
