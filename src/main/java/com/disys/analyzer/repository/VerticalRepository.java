package com.disys.analyzer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.disys.analyzer.model.dto.VerticalDTO;
import com.disys.analyzer.repository.custom.VerticalRepositoryCustom;

public interface VerticalRepository extends JpaRepository<VerticalDTO, Long>, VerticalRepositoryCustom {

}
