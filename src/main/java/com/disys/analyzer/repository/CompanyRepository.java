package com.disys.analyzer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.disys.analyzer.model.dto.CompanyDTO;
import com.disys.analyzer.repository.custom.CompanyRepositoryCustom;

public interface CompanyRepository extends JpaRepository<CompanyDTO, Long>, CompanyRepositoryCustom {

}
