/**
 * 
 */
package com.disys.analyzer.service;

import java.util.List;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.disys.analyzer.model.AnalyserLiaison;
import com.disys.analyzer.model.dto.AnalyserLiaisonDTO;
import com.disys.analyzer.model.dto.UserDTO;

/**
 * @author Sajid
 * 
 */
public interface AnalyserLiaisonService {
	public List<AnalyserLiaison> findByOrgId(Integer orgId);

	public void saveAnalyserLiaison(AnalyserLiaison analyserLiaison);

	public Page<AnalyserLiaison> findAll(Pageable pageable);

	public Page<AnalyserLiaison> findAll(Example<AnalyserLiaison> example,
			Pageable pageable);

	public long count(Example<AnalyserLiaison> example);
	
	public void delete(String liaisonId);
			
	public List<AnalyserLiaison> getLiaisonList(String userId);
	
	public String changeAnalyserLiaisonStatus(String userId, String liaisonID, Integer status);
	
	public String addUpdateAnalyserLiaison(String liaisonId, String liaisonName, String userId, int actionType);
	
	public List<AnalyserLiaisonDTO> getAnalyserLiaisonList(String userId, String companyCode);
	
	public List<AnalyserLiaisonDTO> getAnalyserLiaisonDescription(String userId, String companyCode, String recordCode);
	
	public List<AnalyserLiaison> getAnalyserAllLiaison(String userId, String userList, String sortOrder,String searchString, String companyCode);
	
	public List<UserDTO> spGetActiveUsersLiaison(String userId, String companyCode);
	
	
}
