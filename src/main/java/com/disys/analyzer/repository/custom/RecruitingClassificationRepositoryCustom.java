/**
 * 
 */
package com.disys.analyzer.repository.custom;

import java.util.List;

import com.disys.analyzer.model.dto.RecruitingClassificationDTO;

/**
 * @author Sajid
 * 
 *
 */
public interface RecruitingClassificationRepositoryCustom
{
	public List<RecruitingClassificationDTO> getRecruitingClassificationsList(String userId, Boolean fetchWithAll);
	public List<RecruitingClassificationDTO> getRecruitingClassificationsList(String userId, String companyCode);
	public List<RecruitingClassificationDTO> getRecruitingClassificationsDescription(String userId, String companyCode, String recordCode);
}
