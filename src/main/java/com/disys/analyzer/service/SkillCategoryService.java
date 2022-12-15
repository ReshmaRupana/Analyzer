package com.disys.analyzer.service;

import java.util.List;
import com.disys.analyzer.model.dto.SkillCategoryDTO;

public interface SkillCategoryService {
	
	public List<SkillCategoryDTO> getSkillCategoryList(String userId, String companyCode);
	public List<SkillCategoryDTO> getSkillCategoryDescription(String userId, String companyCode, String recordCode);
	
}
