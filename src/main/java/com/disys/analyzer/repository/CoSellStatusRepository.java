package com.disys.analyzer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.disys.analyzer.model.dto.CoSellStatusDTO;
import com.disys.analyzer.repository.custom.CoSellStatusRepositoryCustom;

public interface CoSellStatusRepository extends JpaRepository<CoSellStatusDTO, Long>, CoSellStatusRepositoryCustom {

}
