package com.disys.analyzer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.disys.analyzer.model.dto.SickLeaveTypeDTO;
import com.disys.analyzer.repository.custom.SickLeaveTypeRepositoryCustom;

public interface SickLeaveTypeRepository extends JpaRepository<SickLeaveTypeDTO, Long>, SickLeaveTypeRepositoryCustom {

}
