package com.disys.analyzer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.disys.analyzer.model.dto.PortfolioDTO;
import com.disys.analyzer.repository.custom.PortfolioRepositoryCustom;

public interface PortfolioRepository extends JpaRepository<PortfolioDTO, Long>, PortfolioRepositoryCustom {

}
