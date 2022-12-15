package com.disys.analyzer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.disys.analyzer.model.dto.PtoDTO;
import com.disys.analyzer.repository.custom.PtoRepositoryCustom;

public interface PtoRepository extends JpaRepository<PtoDTO, Long>, PtoRepositoryCustom {

}
