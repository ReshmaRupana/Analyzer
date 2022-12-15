/**
 * 
 */
package com.disys.analyzer.service.implementation;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.disys.analyzer.model.dto.RecruitingClassificationDTO;
import com.disys.analyzer.repository.RecruitingClassificationRepository;
import com.disys.analyzer.service.RecruitingClassificationService;

/**
 * @author Sajid
 * 
 *
 */
@Service
@Transactional
public class RecruitingClassificationServiceImpl implements RecruitingClassificationService, Serializable
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	RecruitingClassificationRepository repository;
	
	@Autowired
	Map<String, List<RecruitingClassificationDTO>> recruitingClassificationDropDownBean;

	public RecruitingClassificationServiceImpl(){
		
	}
	
	/* (non-Javadoc)
	 * @see com.disys.analyzer.service.RecrutingClassificationService#getRecruitingClassificationsList(java.lang.String, java.lang.Boolean)
	 */
	@Override
	public List<RecruitingClassificationDTO> getRecruitingClassificationsList(String userId, Boolean fetchWithAll)
	{
		List<RecruitingClassificationDTO> list = repository.getRecruitingClassificationsList(userId, fetchWithAll);
		/*if(fetchWithAll){
			return list;
		}else {
			if(list != null && list.size()>0){
				list.remove(0);	
			}
			return list;
		}*/
		return list;
	}	
	
	@Override
	public List<RecruitingClassificationDTO> getRecruitingClassificationsList(String userId, String companyCode) 
	{
		if(recruitingClassificationDropDownBean.containsKey(companyCode)) {
			System.out.println("recruitingClassificationDropDownBean has key : " + companyCode);
			return recruitingClassificationDropDownBean.get(companyCode);
		}
		List<RecruitingClassificationDTO> list = repository.getRecruitingClassificationsList(userId, companyCode);
		recruitingClassificationDropDownBean.put(companyCode, list);
		return list; 
	}
	
	@Override
	public List<RecruitingClassificationDTO> getRecruitingClassificationsDescription(String userId, String companyCode, String recordCode) 
	{
		return repository.getRecruitingClassificationsDescription(userId, companyCode, recordCode);
	}
}