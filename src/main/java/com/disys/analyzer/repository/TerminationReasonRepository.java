package com.disys.analyzer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.disys.analyzer.model.dto.TerminationReasonDTO;
import com.disys.analyzer.repository.custom.TerminationReasonRepositoryCustom;

public interface TerminationReasonRepository extends JpaRepository<TerminationReasonDTO, Long>, TerminationReasonRepositoryCustom {

}
