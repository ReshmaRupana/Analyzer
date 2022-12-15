package com.disys.analyzer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.disys.analyzer.model.dto.WorkFromSourceDTO;
import com.disys.analyzer.repository.custom.WorkFromSourceRepositoryCustom;

public interface WorkFromSourceRepository extends JpaRepository<WorkFromSourceDTO, Long>, WorkFromSourceRepositoryCustom {

}
