/**
 * 
 */
package com.disys.analyzer.service.implementation;

import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.disys.analyzer.config.database.DBConnection;
import com.disys.analyzer.config.util.FacesUtils;
import com.disys.analyzer.config.util.Util;
import com.disys.analyzer.model.AnalyserLiaison;
import com.disys.analyzer.model.dto.AnalyserClientDTO;
import com.disys.analyzer.model.dto.AnalyserLiaisonDTO;
import com.disys.analyzer.model.dto.GenderDTO;
import com.disys.analyzer.model.dto.HolidayDTO;
import com.disys.analyzer.model.dto.UserDTO;
import com.disys.analyzer.repository.AnalyserLiaisonRepository;
import com.disys.analyzer.service.AnalyserLiaisonService;

/**
 * @author Sajid
 * 
 */
@Service
@Transactional
public class AnalyserLiaisonServiceImpl implements AnalyserLiaisonService, Serializable
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static Logger logger = LoggerFactory.getLogger(AnalyserLiaisonServiceImpl.class);
	
	@Autowired
	private AnalyserLiaisonRepository analyserLiaisonRepository;
	
	@Autowired
	Map<String, List<AnalyserLiaisonDTO>> analyserLiaisonDownBean;
	
	@Override
	public List<AnalyserLiaison> findByOrgId(Integer orgId)
	{
		return analyserLiaisonRepository.findByOrgId(orgId);
	}
	
	@Override
	public void saveAnalyserLiaison(AnalyserLiaison analyserLiaison)
	{
		analyserLiaisonRepository.save(analyserLiaison);
		/*
		 * System.out.println("Analyser Liaison saved successfully.");
		 * logger.info("Analyser Liaison saved successfully.");
		 */
	}
	
	@Override
	public Page<AnalyserLiaison> findAll(Pageable pageable)
	{
		return analyserLiaisonRepository.findAll(pageable);
	}
	
	@Override
	public Page<AnalyserLiaison> findAll(Example<AnalyserLiaison> example, Pageable pageable)
	{
		return analyserLiaisonRepository.findAll(example, pageable);
	}
	
	@Override
	public long count(Example<AnalyserLiaison> example)
	{
		return analyserLiaisonRepository.count();
	}
	
	@Override
	public void delete(String liaisonId)
	{
		analyserLiaisonRepository.delete(liaisonId);
	}
	
	@Override
	public List<AnalyserLiaison> getLiaisonList(String userId)
	{
		// return analyserLiaisonRepository.getLiaisonList(userId);
		logger.debug("about to fetch liaison list");
		return analyserLiaisonRepository.getAllAnalyserLiaison(userId, "0", "LiaisonName", "ALL", "");
	}		
	
	@Override
	public String changeAnalyserLiaisonStatus(String userId, String liaisonID, Integer status)
	{
		Connection con = null;
		CallableStatement callStmt = null;
		ResultSet rs = null;
		String result = "0";
		int res = 0;
		
		try
		{
			
			con = DBConnection.getConnection();
			if (con != null)
			{
				String query = null;
				query = "{call " + FacesUtils.SCHEMA_WIRELESS + ".spSetStatusAnalyserLiaison('" + userId + "', '" + liaisonID + "', " + status + ")}";
				
				System.out.println("Executing Query in ::  " + query);
				System.out.println("In AnalyserLiaisonServiceImpl liaison id = " + liaisonID);
				System.out.println("In AnalyserLiaisonServiceImpl Status = " + status);
				callStmt = con.prepareCall(query);
				res = callStmt.executeUpdate();
				
				if (res != 0) // return 0 if success or "" otherwise
				result = "";
				
				con.close();
				callStmt.close();
				con = null;
				callStmt = null;
				rs = null;
				
			}
			else
			{
				throw new Exception("Connection is null");
			}
		}
		catch (Exception ex)
		{
			System.out.println("Exception in setContractorStatusInDatabase method of AnalyserSubContractorRepositoryImpl");
			ex.printStackTrace();
			return "System Error";
		}
		finally
		{
			DBConnection.closeConnection(con, callStmt, rs);
		}
		
		return result;
	}
	
	public String addUpdateAnalyserLiaison(String liaisonId, String liaisonName, String userId, int actionType)
	{
		Connection con = null;
		CallableStatement callStmt = null;
		ResultSet rs = null;
		String result = "0";
		try
		{	
			con = DBConnection.getConnection();
			if (con != null)
			{
				String query = null;
				
				String userQuery = "SELECT First_Name +' '+ Last_Name as Name from Users WHERE USER_ID = '"+liaisonId+"'";
				System.out.println("Executing userQuery in ::  " + userQuery);
				Statement st = con.createStatement();
				ResultSet rSet = st.executeQuery(userQuery);
				if(rSet != null){
					while(rSet.next()){
						liaisonName = rSet.getString("Name");
					}
				}
				
				query = "{call " + FacesUtils.SCHEMA_WIRELESS + ".spAddUpdateAnalyserLiaison(" + actionType + ",'" + userId + "','" + Util.removeSingleQuote(liaisonId) + "','"
						+ Util.removeSingleQuote(liaisonName) + "')}";
				
				System.out.println("Executing Query in ::  " + query);
				System.out.println("In AnalyserLiaisonServiceImpl liaison id = " + liaisonId);
				callStmt = con.prepareCall(query);
				rs = callStmt.executeQuery();
				if(rs != null){
					while(rs.next()){
						result = rs.getString(1);
					}
				} else {
					result = "0";
				}
				
				con.close();
				callStmt.close();
				con = null;
				callStmt = null;
				
				st.close();
				rSet.close();
			}
			else
			{
				throw new Exception("Connection is null");
			}
		}
		catch (Exception ex)
		{
			System.out.println("Exception in addUpdateAnalyserLiaison method");
			ex.printStackTrace();
			result = "1";
			return result;
		}
		finally
		{
			DBConnection.closeConnection(con, callStmt, rs);
		}
		
		return result;
	}
	

	@Override
	public List<AnalyserLiaisonDTO> getAnalyserLiaisonList(String userId, String companyCode) 
	{
		if(analyserLiaisonDownBean.containsKey(companyCode)) {
			System.out.println("analyserLiaisonDownBean has key : " + companyCode);
			return analyserLiaisonDownBean.get(companyCode);
		}
		List<AnalyserLiaisonDTO> list = analyserLiaisonRepository.getAnalyserLiaisonList(userId, companyCode);
		analyserLiaisonDownBean.put(companyCode, list);
		return list;
	}
	
	@Override
	public List<AnalyserLiaisonDTO> getAnalyserLiaisonDescription(String userId, String companyCode, String recordCode) 
	{
		return analyserLiaisonRepository.getAnalyserLiaisonDescription(userId, companyCode, recordCode);
	}
	
	@Override
	public List<AnalyserLiaison> getAnalyserAllLiaison(String userId, String userList, String sortOrder, String searchString, String companyCode)
	{
		return analyserLiaisonRepository.getAnalyserAllLiaison(userId, userList, sortOrder, searchString, companyCode);
	}
	
	@Override
	public List<UserDTO> spGetActiveUsersLiaison(String userId, String companyCode){
		return analyserLiaisonRepository.spGetActiveUsersLiaison(userId, companyCode);
	}
}
