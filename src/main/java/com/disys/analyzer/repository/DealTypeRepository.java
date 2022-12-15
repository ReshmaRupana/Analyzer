package com.disys.analyzer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.disys.analyzer.model.dto.DealTypeDTO;
import com.disys.analyzer.repository.custom.DealTypeRepositoryCustom;

public interface DealTypeRepository extends JpaRepository<DealTypeDTO, Long>, DealTypeRepositoryCustom {

}
