package com.disys.analyzer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.disys.analyzer.model.dto.CommissionModelDTO;
import com.disys.analyzer.repository.custom.CommissionModelRepositoryCustom;

public interface CommissionModelRepository extends JpaRepository<CommissionModelDTO, Long>, CommissionModelRepositoryCustom {

}
