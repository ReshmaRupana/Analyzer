/**
 * 
 */
package com.disys.analyzer.repository.custom;

import java.util.List;

import com.disys.analyzer.model.AnalyserLiaison;
import com.disys.analyzer.model.dto.AnalyserLiaisonDTO;
import com.disys.analyzer.model.dto.UserDTO;

/**
 * @author Sajid
 * 
 *
 */
public interface AnalyserLiaisonRepositoryCustom {
	
	public List<AnalyserLiaison> getAllAnalyserLiaison(String userId, String userList, String orderBy, String searchString, String action);
	
	public List<AnalyserLiaisonDTO> getAnalyserLiaisonList(String userId, String companyCode);
	
	public List<AnalyserLiaisonDTO> getAnalyserLiaisonDescription(String userId, String companyCode, String recordCode);
	
	public List<AnalyserLiaison> getAnalyserAllLiaison(String userId, String userList, String sortOrder,String searchString, String companyCode);
	
	public List<UserDTO> spGetActiveUsersLiaison(String userId, String companyCode);
	
}
