package com.disys.analyzer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.disys.analyzer.model.dto.EmployeeTypeDTO;
import com.disys.analyzer.repository.custom.EmployeeTypeRepositoryCustom;

public interface EmployeeTypeRepository extends JpaRepository<EmployeeTypeDTO, Long>, EmployeeTypeRepositoryCustom {

}
