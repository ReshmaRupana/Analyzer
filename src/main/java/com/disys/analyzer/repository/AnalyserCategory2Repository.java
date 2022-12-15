package com.disys.analyzer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.disys.analyzer.model.dto.AnalyserCategory2DTO;
import com.disys.analyzer.repository.custom.AnalyserCategory2RepositoryCustom;

public interface AnalyserCategory2Repository extends JpaRepository<AnalyserCategory2DTO, Long>, AnalyserCategory2RepositoryCustom {

}
