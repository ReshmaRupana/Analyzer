package com.disys.analyzer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.disys.analyzer.model.dto.EntityDTO;
import com.disys.analyzer.repository.custom.EntityRepositoryCustom;

public interface EntityRepository extends JpaRepository<EntityDTO, Long>, EntityRepositoryCustom {

}
