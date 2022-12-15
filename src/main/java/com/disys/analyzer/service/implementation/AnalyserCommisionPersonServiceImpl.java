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

import com.disys.analyzer.model.AnalyserCommisionPerson;
import com.disys.analyzer.model.dto.AnalyserCommisionPersonDTO;
import com.disys.analyzer.model.dto.CommissionPersonGradeDTO;
import com.disys.analyzer.model.dto.UserDTO;
import com.disys.analyzer.repository.AnalyserCommisionPersonRepository;
import com.disys.analyzer.service.AnalyserCommisionPersonService;

/**
 * @author Sajid
 * 
 */
// @Service(value = "commisionPersonService")
@Service
@Transactional
public class AnalyserCommisionPersonServiceImpl implements AnalyserCommisionPersonService, Serializable
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Autowired private AnalyserCommisionPersonRepository repository;
	
	@Autowired
	Map<String, List<AnalyserCommisionPersonDTO>> commissionPersonDropDownBean;
	
	@Autowired
	Map<String, List<AnalyserCommisionPersonDTO>> mdCommissionPersonDropDownBean;
	
	@Autowired
	Map<String, List<AnalyserCommisionPersonDTO>> allCommissionPersonDropDownBean;
	
	@Autowired
	Map<String, List<AnalyserCommisionPersonDTO>> mspClientPartnerDropDownBean;
	
	@Override
	public long count()
	{
		return repository.count();
	}
	
	@Override
	public List<AnalyserCommisionPerson> getCommissionUsersList(String userId, String userList, String orderBy, String searchString, String companyCode)
	{
		return repository.getCommissionUsersList(userId, userList, orderBy, searchString, companyCode);
	}
	
	@Override
	public String addUpdateAnalyserComm(String commPerson, String commPersonOld, String userId, int actionType)
	{
		return repository.addUpdateAnalyserComm(commPerson, commPersonOld, userId, actionType);
	}
	
	@Override
	public Map<String, String> getCommPersonList(String userId, String type)
	{
		// TODO Auto-generated method stub
		return repository.getCommPersonList(userId, type);
	}
	
	@Override
	public String changeCommissionPersonStatus(String userId, String updatedBy, String personName, Integer status)
	{
		return repository.changeCommissionPersonStatus(userId, updatedBy, personName, status);
	}
	
	@Override
	public List<String> getCommissionUsersList(String userId)
	{
		return null;
	}
	
	@Override
	public List<UserDTO> spGetActiveUsers(String userId, String companyCode)
	{
		return repository.spGetActiveUsers(userId, companyCode);
	}
	
	@Override
	public List<UserDTO> spGetActiveCommissionPersons(String userId, String companyCode)
	{
		return repository.spGetActiveCommissionPersons(userId, companyCode);
	}
	
	@Override
	public List<CommissionPersonGradeDTO> spGetCommissionPersonGrade(String userId)
	{
		return repository.spGetCommissionPersonGrade(userId);
	}
	
	public String spAddUpdateCommissionPersonGrade(String userId, CommissionPersonGradeDTO personDto, Integer actionType)
	{
		return repository.spAddUpdateCommissionPersonGrade(userId, personDto, actionType);
	}

	@Override
	public AnalyserCommisionPerson getAnalyserCommissionPerson(String userId, String commissionPersonId)
	{
		return repository.getAnalyserCommissionPerson(userId, commissionPersonId);
	}
	
	@Override
	public Boolean updateAnalyserCommissionPerson(String userId,String personName,String commissionPersonUserId,
			String commissionPersonRole,String commissionPersonClassification, String companyCode){
		return repository.updateAnalyserCommissionPerson(userId,personName,commissionPersonUserId,
				commissionPersonRole,commissionPersonClassification, companyCode);
	}
	
	@Override
	public List<AnalyserCommisionPersonDTO> getCommisionPersonList(String userId, String companyCode, String roleCode, String classificationCode) 
	{
		return repository.getCommisionPersonList(userId, companyCode, roleCode, classificationCode);
	}
	@Override
	public List<AnalyserCommisionPersonDTO> getMDCommissionPersonList(String userId, String companyCode) 
	{
		if(mdCommissionPersonDropDownBean.containsKey(companyCode)) {
			System.out.println("getMDCommissionPersonList has key : " + companyCode);
			return mdCommissionPersonDropDownBean.get(companyCode);
		}
		List<AnalyserCommisionPersonDTO> list = repository.getMDCommissionPersonList(userId, companyCode);
		mdCommissionPersonDropDownBean.put(companyCode, list);
		return list;
	}
	@Override
	public List<AnalyserCommisionPersonDTO> getAECommissionPersonList(String userId, String companyCode) 
	{
		if(commissionPersonDropDownBean.containsKey(companyCode)) {
			System.out.println("getAECommissionPersonList has key : " + companyCode);
			return commissionPersonDropDownBean.get(companyCode);
		}
		List<AnalyserCommisionPersonDTO> list = repository.getAECommissionPersonList(userId, companyCode);
		commissionPersonDropDownBean.put(companyCode, list);
		return list;
	}
	@Override
	public List<AnalyserCommisionPersonDTO> getRecruiterCommissionPersonList(String userId, String companyCode) 
	{
		if(commissionPersonDropDownBean.containsKey(companyCode)) {
			System.out.println("getRecruiterCommissionPersonList has key : " + companyCode);
			return commissionPersonDropDownBean.get(companyCode);
		}
		List<AnalyserCommisionPersonDTO> list = repository.getRecruiterCommissionPersonList(userId, companyCode);
		commissionPersonDropDownBean.put(companyCode, list);
		return list;
	}
	@Override
	public List<AnalyserCommisionPersonDTO> getAllCommissionPersonList(String userId, String companyCode)
	{
		if(allCommissionPersonDropDownBean.containsKey(companyCode)) {
			System.out.println("getAllCommissionPersonList has key : " + companyCode);
			return allCommissionPersonDropDownBean.get(companyCode);
		}
		List<AnalyserCommisionPersonDTO> list = repository.getAllCommissionPersonList(userId, companyCode);
		allCommissionPersonDropDownBean.put(companyCode, list);
		return list;
	}
	@Override
	public List<AnalyserCommisionPersonDTO> getMSPCommissionPersonList(String userId, String companyCode) 
	{
		if(mspClientPartnerDropDownBean.containsKey(companyCode)) {
			System.out.println("getMSPCommissionPersonList has key : " + companyCode);
			return mspClientPartnerDropDownBean.get(companyCode);
		}
		List<AnalyserCommisionPersonDTO> list = repository.getMSPCommissionPersonList(userId, companyCode);
		mspClientPartnerDropDownBean.put(companyCode, list);
		return list;
	}
	
}
