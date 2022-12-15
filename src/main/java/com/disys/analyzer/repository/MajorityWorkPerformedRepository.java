package com.disys.analyzer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.disys.analyzer.model.dto.MajorityWorkPerformedDTO;
import com.disys.analyzer.repository.custom.MajorityWorkPerformedRepositoryCustom;

public interface MajorityWorkPerformedRepository extends JpaRepository<MajorityWorkPerformedDTO, Long>, MajorityWorkPerformedRepositoryCustom {

}
