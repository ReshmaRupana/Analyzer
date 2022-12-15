package com.disys.analyzer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.disys.analyzer.model.dto.BonusPercentageDTO;
import com.disys.analyzer.repository.custom.BonusPercentageRepositoryCustom;

public interface BonusPercentageRepository extends JpaRepository<BonusPercentageDTO, Long>, BonusPercentageRepositoryCustom {

}
