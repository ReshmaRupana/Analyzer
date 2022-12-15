package com.disys.analyzer.repository.custom;

import java.util.List;
import com.disys.analyzer.model.dto.SkillCategoryDTO;

public interface SkillCategoryRepositoryCustom {
	
	public List<SkillCategoryDTO> getSkillCategoryList(String userId, String companyCode);
	public List<SkillCategoryDTO> getSkillCategoryDescription(String userId, String companyCode, String recordCode);
}
