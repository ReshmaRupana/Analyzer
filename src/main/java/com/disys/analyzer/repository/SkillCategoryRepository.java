package com.disys.analyzer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.disys.analyzer.model.dto.SkillCategoryDTO;
import com.disys.analyzer.repository.custom.SkillCategoryRepositoryCustom;

public interface SkillCategoryRepository extends JpaRepository<SkillCategoryDTO, Long>, SkillCategoryRepositoryCustom {

}
