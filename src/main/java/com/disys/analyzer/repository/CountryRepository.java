package com.disys.analyzer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.disys.analyzer.model.dto.CountryDTO;
import com.disys.analyzer.repository.custom.CountryRepositoryCustom;

public interface CountryRepository extends JpaRepository<CountryDTO, Long>, CountryRepositoryCustom {

}
