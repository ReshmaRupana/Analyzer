package com.disys.analyzer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.disys.analyzer.model.dto.JobBoardDTO;
import com.disys.analyzer.repository.custom.JobBoardRepositoryCustom;

public interface JobBoardRepository extends JpaRepository<JobBoardDTO, Long>, JobBoardRepositoryCustom {

}
