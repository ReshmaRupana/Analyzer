package com.disys.analyzer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.disys.analyzer.model.dto.StatesAllDTO;
import com.disys.analyzer.repository.custom.StatesRepositoryCustom;

public interface StatesRepository extends JpaRepository<StatesAllDTO, Long>, StatesRepositoryCustom {

}
