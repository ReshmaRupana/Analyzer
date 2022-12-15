/**
 * 
 */
package com.disys.analyzer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.disys.analyzer.model.dto.RecruitingClassificationDTO;
import com.disys.analyzer.repository.custom.RecruitingClassificationRepositoryCustom;

/**
 * @author Sajid
 *
 */
public interface RecruitingClassificationRepository extends  JpaRepository<RecruitingClassificationDTO, Integer>, RecruitingClassificationRepositoryCustom 
{
	
}
