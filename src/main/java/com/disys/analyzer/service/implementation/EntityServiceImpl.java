package com.disys.analyzer.service.implementation;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.disys.analyzer.model.dto.EntityDTO;
import com.disys.analyzer.repository.EntityRepository;
import com.disys.analyzer.service.EntityService;
/**
 * @author Saravanan Dhurairaj
 * 
 */
@Service
@Transactional
public class EntityServiceImpl implements EntityService, Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private EntityRepository entityRepository;
	
	@Autowired
	Map<String, List<EntityDTO>> entityDownBean;
	
	@Override
	public List<EntityDTO> getEntityList(String userId, String companyCode) 
	{
		if(entityDownBean.containsKey(companyCode)) {
			System.out.println("entityDownBean has key : " + companyCode);
			return entityDownBean.get(companyCode);
		}
		List<EntityDTO> list = entityRepository.getEntityList(userId, companyCode);
		entityDownBean.put(companyCode, list);
		return list; 
	}
	
	@Override
	public List<EntityDTO> getEntityDescription(String userId, String companyCode, String recordCode) 
	{
		return entityRepository.getEntityDescription(userId, companyCode, recordCode);
	}
	
}
