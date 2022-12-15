package com.disys.analyzer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.disys.analyzer.model.dto.TravelStatusDTO;
import com.disys.analyzer.repository.custom.TravelStatusRepositoryCustom;

public interface TravelStatusRepository extends JpaRepository<TravelStatusDTO, Long>, TravelStatusRepositoryCustom {

}
