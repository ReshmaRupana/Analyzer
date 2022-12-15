package com.disys.analyzer.service.implementation;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.disys.analyzer.model.dto.CommissionModelDTO;
import com.disys.analyzer.repository.CommissionModelRepository;
import com.disys.analyzer.service.CommissionModelService;
/**
 * @author Saravanan Dhurairaj
 * 
 */
@Service
@Transactional
public class CommissionModelServiceImpl implements CommissionModelService, Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private CommissionModelRepository commissionModelRepository;
	
	@Autowired
	Map<String, List<CommissionModelDTO>> commissionModelDropDownBean;
	
	/* (non-Javadoc)
	 * @see com.disys.analyzer.service.CommissionModelService#getCommissionModels(java.lang.String)
	 */
	@Override
	public List<CommissionModelDTO> getCommissionModelList(String userId, String companyCode) 
	{
		if(commissionModelDropDownBean.containsKey(companyCode)) {
			System.out.println("commissionModelDropDownBean has key : " + companyCode);
			return commissionModelDropDownBean.get(companyCode);
		}
		List<CommissionModelDTO> list = commissionModelRepository.getCommissionModelList(userId, companyCode);
		commissionModelDropDownBean.put(companyCode, list);
		return list; 
	}
	
	@Override
	public List<CommissionModelDTO> getCommissionModelDescription(String userId, String companyCode, String recordCode) 
	{
		return commissionModelRepository.getCommissionModelDescription(userId, companyCode, recordCode);
	}
	
}
