package com.disys.analyzer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.disys.analyzer.model.dto.JobCategoryDTO;
import com.disys.analyzer.repository.custom.JobCategoryRepositoryCustom;

public interface JobCategoryRepository extends JpaRepository<JobCategoryDTO, Long>, JobCategoryRepositoryCustom {

}
