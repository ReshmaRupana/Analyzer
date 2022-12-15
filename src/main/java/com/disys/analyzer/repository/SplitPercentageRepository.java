package com.disys.analyzer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.disys.analyzer.model.dto.SplitPercentageDTO;
import com.disys.analyzer.repository.custom.SplitPercentageRepositoryCustom;

public interface SplitPercentageRepository extends JpaRepository<SplitPercentageDTO, Long>, SplitPercentageRepositoryCustom {

}
