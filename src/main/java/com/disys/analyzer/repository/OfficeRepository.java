package com.disys.analyzer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.disys.analyzer.model.dto.OfficeDTO;
import com.disys.analyzer.repository.custom.OfficeRepositoryCustom;

public interface OfficeRepository extends JpaRepository<OfficeDTO, Long>, OfficeRepositoryCustom {

}
