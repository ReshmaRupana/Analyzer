package com.disys.analyzer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.disys.analyzer.model.dto.HolidayDTO;
import com.disys.analyzer.repository.custom.HolidayRepositoryCustom;

public interface HolidayRepository extends JpaRepository<HolidayDTO, Long>, HolidayRepositoryCustom {

}
