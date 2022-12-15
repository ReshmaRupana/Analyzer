package com.disys.analyzer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.disys.analyzer.model.dto.UnemployedStatusDTO;
import com.disys.analyzer.repository.custom.UnemployedStatusRepositoryCustom;

public interface UnemployedStatusRepository extends JpaRepository<UnemployedStatusDTO, Long>, UnemployedStatusRepositoryCustom {

}
