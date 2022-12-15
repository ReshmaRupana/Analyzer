package com.disys.analyzer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.disys.analyzer.model.dto.FLSAStatusDTO;
import com.disys.analyzer.repository.custom.FLSAStatusRepositoryCustom;

public interface FLSAStatusRepository extends JpaRepository<FLSAStatusDTO, Long>, FLSAStatusRepositoryCustom {

}
