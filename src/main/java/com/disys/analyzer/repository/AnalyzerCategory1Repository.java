package com.disys.analyzer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.disys.analyzer.model.dto.AnalyzerCategory1DTO;
import com.disys.analyzer.repository.custom.AnalyzerCategory1RepositoryCustom;

public interface AnalyzerCategory1Repository extends JpaRepository<AnalyzerCategory1DTO, Long>, AnalyzerCategory1RepositoryCustom {

}
