package com.disys.analyzer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.disys.analyzer.model.dto.EmployeeClassDTO;
import com.disys.analyzer.repository.custom.EmployeeClassRepositoryCustom;

public interface EmployeeClassRepository extends JpaRepository<EmployeeClassDTO, Long>, EmployeeClassRepositoryCustom {

}
