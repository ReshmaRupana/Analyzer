package com.disys.analyzer.service.implementation;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.disys.analyzer.model.dto.SkillCategoryDTO;
import com.disys.analyzer.repository.SkillCategoryRepository;
import com.disys.analyzer.service.SkillCategoryService;
/**
 * @author Saravanan Dhurairaj
 * 
 */
@Service
@Transactional
public class SkillCategoryServiceImpl implements SkillCategoryService, Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private SkillCategoryRepository skillCategoryRepository;
	
	@Autowired
	Map<String, List<SkillCategoryDTO>> skillCategoryDownBean;
	
	@Override
	public List<SkillCategoryDTO> getSkillCategoryList(String userId, String companyCode) 
	{
		if(skillCategoryDownBean.containsKey(companyCode)) {
			System.out.println("skillCategoryDownBean has key : " + companyCode);
			return skillCategoryDownBean.get(companyCode);
		}
		List<SkillCategoryDTO> list = skillCategoryRepository.getSkillCategoryList(userId, companyCode);
		skillCategoryDownBean.put(companyCode, list);
		return list; 
	}
	
	@Override
	public List<SkillCategoryDTO> getSkillCategoryDescription(String userId, String companyCode, String recordCode) 
	{
		return skillCategoryRepository.getSkillCategoryDescription(userId, companyCode, recordCode);
	}
	
}
