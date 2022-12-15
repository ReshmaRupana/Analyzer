package com.disys.analyzer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.disys.analyzer.model.dto.EmployeeCategoryDTO;
import com.disys.analyzer.repository.custom.EmployeeCategoryRepositoryCustom;

public interface EmployeeCategoryRepository extends JpaRepository<EmployeeCategoryDTO, Long>, EmployeeCategoryRepositoryCustom {

}
