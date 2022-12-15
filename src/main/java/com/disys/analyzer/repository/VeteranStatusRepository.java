package com.disys.analyzer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.disys.analyzer.model.dto.VeteranStatusDTO;
import com.disys.analyzer.repository.custom.VeteranStatusRepositoryCustom;

public interface VeteranStatusRepository extends JpaRepository<VeteranStatusDTO, Long>, VeteranStatusRepositoryCustom {

}
