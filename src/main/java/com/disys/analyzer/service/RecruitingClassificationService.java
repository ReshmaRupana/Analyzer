/**
 * 
 */
package com.disys.analyzer.service;

import java.util.List;

import com.disys.analyzer.model.dto.RecruitingClassificationDTO;

/**
 * @author Sajid
 *	@since March 26, 2019
 */
public interface RecruitingClassificationService
{
	public List<RecruitingClassificationDTO> getRecruitingClassificationsList(String userId, Boolean fetchWithAll);
	public List<RecruitingClassificationDTO> getRecruitingClassificationsList(String userId, String companyCode);
	public List<RecruitingClassificationDTO> getRecruitingClassificationsDescription(String userId, String companyCode, String recordCode);
	
}
