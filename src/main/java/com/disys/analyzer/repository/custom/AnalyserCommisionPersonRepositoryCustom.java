/**
 * 
 */
package com.disys.analyzer.repository.custom;

import java.util.List;
import java.util.Map;

import com.disys.analyzer.model.AnalyserCommisionPerson;
import com.disys.analyzer.model.dto.AnalyserCommisionPersonDTO;
import com.disys.analyzer.model.dto.CommissionPersonGradeDTO;
import com.disys.analyzer.model.dto.UserDTO;

/**
 * @author Sajid
 * 
 */
public interface AnalyserCommisionPersonRepositoryCustom
{
	public List<AnalyserCommisionPerson> getCommissionUsersList(String userId, String userList, String orderBy, String searchString, String companyCode);
	
	public String addUpdateAnalyserComm(String commPerson, String commPersonOld, String userId, int actionType);
	
	public Map<String, String> getCommPersonList(String userId, String type);
	
	public String changeCommissionPersonStatus(String userId, String updatedBy, String personName, Integer status);
	
	public List<UserDTO> spGetActiveUsers(String userId, String companyCode);
	
	public List<UserDTO> spGetActiveCommissionPersons(String userId, String companyCode);
	
	public List<CommissionPersonGradeDTO> spGetCommissionPersonGrade(String userId);
	
	public String spAddUpdateCommissionPersonGrade(String userId, CommissionPersonGradeDTO personDto, Integer actionType);
	
	public AnalyserCommisionPerson getAnalyserCommissionPerson(String userId, String commissionPersonId);
	
	public Boolean updateAnalyserCommissionPerson(String userId,String personName,String commissionPersonUserId,
			String commissionPersonRole,String commissionPersonClassification, String companyCode);
	
	public List<AnalyserCommisionPersonDTO> getCommisionPersonList(String userId, String companyCode, String roleCode, String classificationCode );
	
	public List<AnalyserCommisionPersonDTO> getMDCommissionPersonList(String userId, String companyCode);
	
	public List<AnalyserCommisionPersonDTO> getAECommissionPersonList(String userId, String companyCode);
	
	public List<AnalyserCommisionPersonDTO> getRecruiterCommissionPersonList(String userId, String companyCode);
	
	public List<AnalyserCommisionPersonDTO> getAllCommissionPersonList(String userId, String companyCode);
	
	public List<AnalyserCommisionPersonDTO> getMSPCommissionPersonList(String userId, String companyCode);
	
}