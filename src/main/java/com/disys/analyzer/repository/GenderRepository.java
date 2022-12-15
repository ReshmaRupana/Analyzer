package com.disys.analyzer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.disys.analyzer.model.dto.GenderDTO;
import com.disys.analyzer.repository.custom.GenderRepositoryCustom;

public interface GenderRepository extends JpaRepository<GenderDTO, Long>, GenderRepositoryCustom {

}
