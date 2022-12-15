package com.disys.analyzer.service.implementation;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.disys.analyzer.model.dto.PtoDTO;
import com.disys.analyzer.repository.PtoRepository;
import com.disys.analyzer.service.PtoService;
/**
 * @author Saravanan Dhurairaj
 * 
 */
@Service
@Transactional
public class PtoServiceImpl implements PtoService, Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private PtoRepository ptoRepository;
	
	@Autowired
	Map<String, List<PtoDTO>> ptoDownBean;
	
	@Override
	public List<PtoDTO> getPtoList(String userId, String companyCode) 
	{
		if(ptoDownBean.containsKey(companyCode)) {
			System.out.println("ptoDropDownBean has key : " + companyCode);
			return ptoDownBean.get(companyCode);
		}
		List<PtoDTO> list = ptoRepository.getPtoList(userId, companyCode);
		ptoDownBean.put(companyCode, list);
		return list; 
	}
	
	@Override
	public List<PtoDTO> getPtoDescription(String userId, String companyCode, String recordCode) 
	{
		return ptoRepository.getPtoDescription(userId, companyCode, recordCode);
	}
	
}
