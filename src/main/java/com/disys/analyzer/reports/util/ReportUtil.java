/**
 * 
 */
package com.disys.analyzer.reports.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.disys.analyzer.config.database.DbWork;
import com.disys.analyzer.config.util.FacesUtils;
import com.disys.analyzer.service.ApplicationUserService;

/**
 * @author Sajid
 * 
 */
public class ReportUtil {
	private static Logger logger = LoggerFactory.getLogger(ReportUtil.class);
	
	private static boolean userRoleAllowedDownloadAccess;
	public ReportUtil() {
		
	}

	public List<Map<String, Object>> getBranches(String UserID,
			String BranchType) throws Exception {
		// BranchType 0 - ALL, 1 US, 2 International

		ReportMetaData reportMetaData = null;
		reportMetaData = new ReportMetaData();
		DbWork db = new DbWork();
		List<Map<String, Object>> statusArray = null;
		statusArray = new ArrayList<Map<String, Object>>();

		String[] param = new String[2];
		param[0] = UserID;
		param[1] = BranchType;

		String queryString = (String) reportMetaData.getData("BRANCHLIST.SQL");
		statusArray = db.getResult(queryString, param, "SP");

		return statusArray;
	}

	public List<Map<String, Object>> getAEs(String UserID, String ActiveOnly)
			throws Exception {
		// ActiveOnly 0 = ALL , 1 = Only Active, 2 = Liaison
		ReportMetaData reportMetaData = null;
		reportMetaData = new ReportMetaData();
		DbWork db = new DbWork();
		List<Map<String, Object>> statusArray = null;
		statusArray = new ArrayList<Map<String, Object>>();

		String[] param = new String[2];
		param[0] = UserID;
		param[1] = ActiveOnly;

		String queryString = (String) reportMetaData.getData("AELIST.SQL");
		statusArray = db.getResult(queryString, param, "SP");

		return statusArray;
	}

	public List<Map<String, Object>> getBrancheOffices(String userId, Integer branchId)
			throws Exception {
		ReportMetaData reportMetaData = null;
		reportMetaData = new ReportMetaData();
		DbWork db = new DbWork();
		List<Map<String, Object>> statusArray = null;
		statusArray = new ArrayList<Map<String, Object>>();

		String[] param = new String[2];
		param[0] = userId;
		param[1] = branchId.toString();

		String queryString = (String) reportMetaData.getData("REGIONLIST.SQL");
		statusArray = db.getResult(queryString, param, "SP");

		return statusArray;
	}

	public List<Map<String, Object>> getClientSite(String userId, Integer clientSiteId)
			throws Exception {
		ReportMetaData reportMetaData = null;
		reportMetaData = new ReportMetaData();
		DbWork db = new DbWork();
		List<Map<String, Object>> statusArray = null;
		statusArray = new ArrayList<Map<String, Object>>();

		String[] param = new String[2];
		param[0] = clientSiteId.toString();
		param[1] = userId;

		String queryString = (String) reportMetaData.getData("CLIENTSITE.SQL");
		statusArray = db.getResult(queryString, param, "SP");

		return statusArray;
	}
	
	public List<Map<String, Object>> getEntityNames(String userId) throws Exception {
		ReportMetaData reportMetaData = null;
		reportMetaData = new ReportMetaData();
		DbWork db = new DbWork();
		List<Map<String, Object>> statusArray = null;
		statusArray = new ArrayList<Map<String, Object>>();
		String[] param = new String[1];
		param[0] = userId;
		String queryString = (String) reportMetaData.getData("ENTITYNAME.SQL");
		statusArray = db.getResult(queryString, param, "SP");
		return statusArray;
	}
	
	public List<Map<String, Object>> getSubContractorNames(String userId) throws Exception {
		ReportMetaData reportMetaData = null;
		reportMetaData = new ReportMetaData();
		DbWork db = new DbWork();
		List<Map<String, Object>> statusArray = null;
		statusArray = new ArrayList<Map<String, Object>>();
		String[] param = new String[1];
		param[0] = userId;
		//param[1] = userId;
		String queryString = (String) reportMetaData.getData("SUBCONTRACTORNAME.SQL");
		statusArray = db.getResult(queryString, param, "SP");
		return statusArray;
	}
	
	public static boolean isUserRoleAllowedDownloadAccess() {
		try {
			System.out.println("Inside REPORTUTILS --> IsUserRoleAllowedDownloadAccess.");
			logger.debug("Inside REPORTUTILS --> IsUserRoleAllowedDownloadAccess.");
			Integer roleId = FacesUtils.getCurrentUserRole();
			
			System.out.println("User Role is = "+roleId.toString());
			logger.debug("User Role is = "+roleId.toString());
			if (roleId == 2 || roleId == 3 || roleId == 4 || roleId == 448)
			{
				userRoleAllowedDownloadAccess = true;
			} else {
				userRoleAllowedDownloadAccess = false;
			}
			return userRoleAllowedDownloadAccess;
		}
		catch (Exception ex) {
			System.out.println("Inside REPORTUTILS --> IsUserRoleAllowedDownloadAccess. EXCEPTION - Returning FALSE");
			logger.debug("Inside REPORTUTILS --> IsUserRoleAllowedDownloadAccess. EXCEPTION - Returning FALSE");
			logger.debug(ex.getMessage());
			System.out.println(ex.getMessage());
			ex.printStackTrace();
			return false;
		}
	}
		
}
