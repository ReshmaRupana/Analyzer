/**
 * 
 */
package com.disys.analyzer.config.database;

import java.io.IOException;
import java.io.InputStream;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.disys.analyzer.config.util.Constants;
import com.disys.analyzer.config.util.FacesUtils;
import com.disys.analyzer.model.dto.AnalyserDTO;

/**
 * @author Sajid
 * @since Jan 24, 2020
 */
public class DBConnection
{
	private static String driverClassName;
	private static String url;
	private static String userName;
	private static String password;
	
	private static String driverClassNameCommission;
	private static String urlCommission;
	private static String userNameCommission;
	private static String passwordCommission;
	
	private static Integer initPoolSize;
	private static Integer maxPoolSize;
	
	private static ConnectionPool connectionPool;
	private static ConnectionPool connectionPoolCommission;
	
	
	private static final String PROPERTIES_FILE_NAME = "application.properties";
	
	static {
		try
		{
			readFromPropertiesFile();
			connectionPool = AnalyserConnectionPool.create(url, userName, password,initPoolSize, maxPoolSize);
			
			readFromPropertiesFileForCommission();
			connectionPoolCommission = AnalyserConnectionPool.create(urlCommission, userNameCommission, passwordCommission,initPoolSize, maxPoolSize);
			
			System.out.println("Inside DBConnection --> STATIC TRY BLOCK FOR THE FIRST TIME.");
			System.out.println("url = "+url);
			System.out.println("urlCommission = "+urlCommission);
			System.out.println("initPoolSize = "+initPoolSize);
			System.out.println("maxPoolSize = "+maxPoolSize);		
		}
		catch (SQLException e)
		{
			System.out.println("Error getting database connection."+e.getMessage());
		}
		
    }
	
	public DBConnection()
	{
		
	}
	
	
	
	public static void saveAnalyserLatest(Connection connection)
	{
		
		CallableStatement callStmt = null;
		ResultSet rs = null;
		try
		{
			String insertStoreProc = "{call "
					+ FacesUtils.SCHEMA_WIRELESS
					+ ".spAddUpdateAnalyserNew(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
			
			callStmt = connection.prepareCall(insertStoreProc);
			
			Integer analyserId = 248637;
			AnalyserDTO analyser = getAnalyserInfo(analyserId);
			
			Integer references;
			Integer h1;
			Integer execOrphan;
			Integer recruiterOrphan;
			Integer other1Orphan;
			Integer other2Orphan;
			Integer placement;
			Integer com;
			Integer execOrphanInter;
			Integer other1OrphanInter;
			Integer other2OrphanInter;
			Integer recruiterOrphanInter;
			
			analyser.setCustom1("--");
			analyser.setCustom2("--");
			analyser.setMrmrs("--");
			analyser.setJrsr("--");
			analyser.setPonumber("--");
			analyser.setPermGAAmount(0.00);
			analyser.setVerticalTimesheetType("--");
			analyser.setPlacement(true);
			
			if (analyser.getPlacement())
			{
				placement = 1;
			}
			else
			{
				placement = 0;
			}
			
			if (analyser.getReferences())
			{
				references = 1;
			}
			else
			{
				references = 0;
			}
			
			if (analyser.getH1())
			{
				h1 = 1;
			}
			else
			{
				h1 = 0;
			}
			
			if (analyser.getExecOrphan())
			{
				execOrphan = 1;
			}
			else
			{
				execOrphan = 0;
			}
			
			if (analyser.getRecruiterOrphan())
			{
				recruiterOrphan = 1;
			}
			else
			{
				recruiterOrphan = 0;
			}
			
			if (analyser.getOther1Orphan())
			{
				other1Orphan = 1;
			}
			else
			{
				other1Orphan = 0;
			}
			
			if (analyser.getOther2Orphan())
			{
				other2Orphan = 1;
			}
			else
			{
				other2Orphan = 0;
			}
			if (analyser.getCom())
			{
				com = 1;
			}
			else
			{
				com = 0;
			}
			
			if (analyser.getExecOrphanInter() != null && analyser.getExecOrphanInter())
			{
				execOrphanInter = 1;
			}
			else
			{
				execOrphanInter = 0;
			}
			
			if (analyser.getOther1OrphanInter() != null && analyser.getOther1OrphanInter())
			{
				other1OrphanInter = 1;
			}
			else
			{
				other1OrphanInter = 0;
			}
			if (analyser.getOther2OrphanInter() != null && analyser.getOther2OrphanInter())
			{
				other2OrphanInter = 1;
			}
			else
			{
				other2OrphanInter = 0;
			}
			if (analyser.getRecruiterOrphanInter() != null && analyser.getRecruiterOrphanInter())
			{
				recruiterOrphanInter = 1;
			}
			else
			{
				recruiterOrphanInter = 0;
			}
			
			if (analyser.getBenefitsAmount() == null || analyser.getBenefitsAmount().trim().equals("null"))
			{
				analyser.setBenefitsAmount("0");
			}
			if (analyser.getPercent401k() == null || analyser.getPercent401k().trim().equals("null"))
			{
				analyser.setPercent401k("0");
			}
			if (analyser.getK401() == null || analyser.getK401().trim().equals("null"))
			{
				analyser.setK401("0");
			}
			if (analyser.getTax() == null || analyser.getTax().trim().equals("null"))
			{
				analyser.setTax("0");
			}
			if (analyser.getRdoPTO() == null || analyser.getRdoPTO().trim().equals("null"))
			{
				analyser.setRdoPTO("0");
			}
			
			if (analyser.getSickLeaveCap() == null)
			{
				analyser.setSickLeaveCap("0");
			}
			
			if (analyser.getpTOLimitToOverrideSick() == null)
			{
				analyser.setpTOLimitToOverrideSick(0.00);
			}
			
			String val = "";
			int intVal = 0;
			
			StringBuilder queryToAddAnalyser = new StringBuilder();
			queryToAddAnalyser.append("USE [Analyser] \nGO \nDECLARE	@return_value int \nEXEC	@return_value = [wireless].[spAddUpdateAnalyserNew]\n");
			// @intActionType
			Integer actionType = 2;
			String userId = "mohammad.hassan@disys.com";
			callStmt.setInt(1, actionType);
			queryToAddAnalyser.append("\n@intActionType = " + actionType + ",");
			// @varLoggedUserID
			callStmt.setString(2, userId);
			queryToAddAnalyser.append("\n@varLoggedUserID = N'" + userId + "',");
			// @intAnalyserID
			intVal = analyserId == null ? 0 : Integer.valueOf(analyserId);
			callStmt.setInt(3, intVal);
			queryToAddAnalyser.append("\n@intAnalyserID = " + intVal + ",");
			// @varLName
			callStmt.setString(4, analyser.getlName());
			queryToAddAnalyser.append("\n@varLName = N'" + analyser.getlName() + "',");
			// @varFName
			callStmt.setString(5, analyser.getfName());
			queryToAddAnalyser.append("\n@varFName = N'" + analyser.getfName() + "',");
			// @varEmpType
			callStmt.setString(6, analyser.getEmpType());
			queryToAddAnalyser.append("\n@varEmpType = N'" + analyser.getEmpType() + "',");
			// @varAddress1
			callStmt.setString(7, analyser.getAddress1());
			queryToAddAnalyser.append("\n@varAddress1 = N'" + analyser.getAddress1() + "',");
			// @varAddress2
			callStmt.setString(8, analyser.getAddress2());
			queryToAddAnalyser.append("\n@varAddress2 = N'" + analyser.getAddress2() + "',");
			// @varCity
			callStmt.setString(9, analyser.getCity());
			queryToAddAnalyser.append("\n@varCity = N'" + analyser.getCity() + "',");
			// @varState
			callStmt.setString(10, analyser.getState());
			queryToAddAnalyser.append("\n@varState = N'" + analyser.getState() + "',");
			// @varZip
			callStmt.setString(11, analyser.getZip());
			queryToAddAnalyser.append("\n@varZip = N'" + analyser.getZip() + "',");
			// @varTel
			callStmt.setString(12, analyser.getTelephone());
			queryToAddAnalyser.append("\n@varTel = N'" + analyser.getTelephone() + "',");
			// @varWokphone
			callStmt.setString(13, analyser.getWorkPhone());
			queryToAddAnalyser.append("\n@varWokphone = N'" + analyser.getWorkPhone() + "',");
			// @varFax
			callStmt.setString(14, analyser.getFax());
			queryToAddAnalyser.append("\n@varFax = N'" + analyser.getFax() + "',");
			// @varEmail
			String email = (analyser.getEmail() == null || analyser.getEmail().trim().length() == 0) ? "empty@empty.com" : analyser.getEmail();
			analyser.setEmail(email.trim());
			callStmt.setString(15, analyser.getEmail());
			queryToAddAnalyser.append("\n@varEmail = N'" + analyser.getEmail() + "',");
			// @varSsn
			callStmt.setString(16, analyser.getSsn());
			queryToAddAnalyser.append("\n@varSsn = N'" + analyser.getSsn() + "',");
			// @varDob
			callStmt.setString(17, analyser.getDob());
			queryToAddAnalyser.append("\n@varDob = N'" + analyser.getDob() + "',");
			// @varJobTitle
			callStmt.setString(18, analyser.getJobTitle());
			queryToAddAnalyser.append("\n@varJobTitle = N'" + analyser.getJobTitle() + "',");
			// @varStartDate
			callStmt.setString(19, analyser.getStartDate());
			queryToAddAnalyser.append("\n@varStartDate = N'" + analyser.getStartDate() + "',");
			// @varEndDate
			callStmt.setString(20, analyser.getEndDate());
			queryToAddAnalyser.append("\n@varEndDate = N'" + analyser.getEndDate() + "',");
			// @intClientId
			intVal = analyser.getClientId() == null ? null : Integer.valueOf(analyser.getClientId());
			callStmt.setInt(21, intVal);
			queryToAddAnalyser.append("\n@intClientId = " + intVal + ",");
			// @intClientAddressId
			intVal = analyser.getClientAddressId() == null ? null : Integer.valueOf(analyser.getClientAddressId());
			callStmt.setInt(22, intVal);
			queryToAddAnalyser.append("\n@intClientAddressId = " + intVal + ",");
			// @intClientSiteId
			callStmt.setInt(23, analyser.getClientSiteId());
			queryToAddAnalyser.append("\n@intClientSiteId = " + analyser.getClientSiteId() + ",");
			// @varContractorId
			callStmt.setString(24, analyser.getContractorId());
			queryToAddAnalyser.append("\n@varContractorId = N'" + analyser.getContractorId() + "',");
			// @varCommName1
			callStmt.setString(25, analyser.getCommName1());
			queryToAddAnalyser.append("\n@varCommName1 = N'" + analyser.getCommName1() + "',");
			// @varPer1
			val = analyser.getCommPer1() == null ? "0.00" : analyser.getCommPer1().toString();
			callStmt.setString(26, val);
			queryToAddAnalyser.append("\n@varPer1 = N'" + val + "',");
			// @varCommName2
			callStmt.setString(27, analyser.getCommName2());
			queryToAddAnalyser.append("\n@varCommName2 = N'" + analyser.getCommName2() + "',");
			// @varPer2
			val = analyser.getCommPer2() == null ? "0.00" : analyser.getCommPer2().toString();
			callStmt.setString(28, val);
			queryToAddAnalyser.append("\n@varPer2 = N'" + val + "',");
			// @varCommName3
			callStmt.setString(29, analyser.getCommName3());
			queryToAddAnalyser.append("\n@varCommName3 = N'" + analyser.getCommName3() + "',");
			// @varPer3
			val = analyser.getCommPer3() == null ? "0.00" : analyser.getCommPer3().toString();
			callStmt.setString(30, val);
			queryToAddAnalyser.append("\n@varPer3 = N'" + val + "',");
			// @varCommName4
			callStmt.setString(31, analyser.getCommName4());
			queryToAddAnalyser.append("\n@varCommName4 = N'" + analyser.getCommName4() + "',");
			// @varPer4
			val = analyser.getCommPer4() == null ? "0.00" : analyser.getCommPer4().toString();
			callStmt.setString(32, val);
			queryToAddAnalyser.append("\n@varPer4 = N'" + val + "',");
			// @varCommName5
			callStmt.setString(33, analyser.getCommissionPerson5());
			queryToAddAnalyser.append("\n@varCommName5 = N'" + analyser.getCommissionPerson5() + "',");
			// @varPer5
			val = analyser.getCommission5() == null ? "0.00" : analyser.getCommission5().toString();
			callStmt.setString(34, val);
			queryToAddAnalyser.append("\n@varPer5 = N'" + val + "',");
			// @varTHW
			val = analyser.getTotHoursWorked() == null ? "0.00" : analyser.getTotHoursWorked().toString();
			callStmt.setString(35, val);
			queryToAddAnalyser.append("\n@varTHW = N'" + val + "',");
			// @varBenefitsAmount
			callStmt.setString(36, analyser.getBenefitsAmount());
			queryToAddAnalyser.append("\n@varBenefitsAmount = N'" + analyser.getBenefitsAmount() + "',");
			// @varPercent401k
			callStmt.setString(37, analyser.getPercent401k());
			queryToAddAnalyser.append("\n@varPercent401k = N'" + analyser.getPercent401k() + "',");
			// @varEducation
			callStmt.setString(38, analyser.getEducation());
			queryToAddAnalyser.append("\n@varEducation = N'" + analyser.getEducation() + "',");
			// @varLeave
			val = analyser.getLeave() == null ? "0.00" : analyser.getLeave().toString();
			callStmt.setString(39, val);
			queryToAddAnalyser.append("\n@varLeave = N'" + val + "',");
			// @varOtherDollar
			callStmt.setString(40, analyser.getOd());
			queryToAddAnalyser.append("\n@varOtherDollar = N'" + analyser.getOd() + "',");
			// @varOtherAmounts
			callStmt.setString(41, analyser.getOtherAmounts());
			queryToAddAnalyser.append("\n@varOtherAmounts = N'" + analyser.getOtherAmounts() + "',");
			// @varDiscount
			callStmt.setString(42, analyser.getDiscount());
			queryToAddAnalyser.append("\n@varDiscount = N'" + analyser.getDiscount() + "',");
			// @varBillRate
			val = analyser.getBillRate() == null ? "0.00" : analyser.getBillRate().toString();
			callStmt.setString(43, val);
			queryToAddAnalyser.append("\n@varBillRate = N'" + val + "',");
			// @varHealth
			callStmt.setString(44, analyser.getHealth());
			queryToAddAnalyser.append("\n@varHealth = N'" + analyser.getHealth() + "',");
			// @vark401
			callStmt.setString(45, analyser.getK401());
			queryToAddAnalyser.append("\n@vark401 = N'" + analyser.getK401() + "',");
			// @varTax
			callStmt.setString(46, analyser.getTax());
			queryToAddAnalyser.append("\n@varTax = N'" + analyser.getTax() + "',");
			// @varPayRate
			val = analyser.getPayRate() == null ? "0.00" : analyser.getPayRate().toString();
			callStmt.setString(47, val);
			queryToAddAnalyser.append("\n@varPayRate = N'" + val + "',");
			// @varGA
			val = analyser.getGa() == null ? "0.00" : analyser.getGa().toString();
			callStmt.setString(48, val);
			queryToAddAnalyser.append("\n@varGA = N'" + val + "',");
			// @varCommission
			val = analyser.getCommission() == null ? "0.00" : analyser.getCommission().toString();
			callStmt.setString(49, val);
			queryToAddAnalyser.append("\n@varCommission = N'" + val + "',");
			// @varSpreadWeek
			callStmt.setString(50, analyser.getSpreadWeek());
			queryToAddAnalyser.append("\n@varSpreadWeek = N'" + analyser.getSpreadWeek() + "',");
			// @varOtb
			callStmt.setString(51, analyser.getOtb());
			queryToAddAnalyser.append("\n@varOtb = N'" + analyser.getOtb() + "',");
			// @varOneTimeAmount
			callStmt.setString(52, analyser.getOneTimeAmount());
			queryToAddAnalyser.append("\n@varOneTimeAmount = N'" + analyser.getOneTimeAmount() + "',");
			// @varComments
			callStmt.setString(53, analyser.getComments());
			queryToAddAnalyser.append("\n@varComments = N'" + analyser.getComments() + "',");
			// @varDiscountRate
			callStmt.setString(54, analyser.getDiscountRate());
			queryToAddAnalyser.append("\n@varDiscountRate = N'" + analyser.getDiscountRate() + "',");
			// @varEffectiveDate
			callStmt.setString(55, analyser.getEffectiveDate());
			queryToAddAnalyser.append("\n@varEffectiveDate = N'" + analyser.getEffectiveDate() + "',");
			// @varProfit
			val = analyser.getProfit() == null ? "0.00" : analyser.getProfit().toString();
			callStmt.setString(56, val);
			queryToAddAnalyser.append("\n@varProfit = N'" + val + "',");
			// @varTermDate
			callStmt.setString(57, analyser.getTermDate());
			queryToAddAnalyser.append("\n@varTermDate = N'" + analyser.getTermDate() + "',");
			// @varReason
			callStmt.setString(58, analyser.getReason());
			queryToAddAnalyser.append("\n@varReason = N'" + analyser.getReason() + "',");
			// @varBranch
			callStmt.setString(59, analyser.getBranch());
			queryToAddAnalyser.append("\n@varBranch = N'" + analyser.getBranch() + "',");
			// @varPto
			callStmt.setString(60, analyser.getRdoPTO());
			queryToAddAnalyser.append("\n@varPto = N'" + analyser.getRdoPTO() + "',");
			// @varCurrency
			callStmt.setString(61, analyser.getCurrency());
			queryToAddAnalyser.append("\n@varCurrency = N'" + analyser.getCurrency() + "',");
			// @varMob_Pager
			callStmt.setString(62, analyser.getMobilePager());
			queryToAddAnalyser.append("\n@varMob_Pager = N'" + analyser.getMobilePager() + "',");
			// @varInitial
			callStmt.setString(63, analyser.getInitial());
			queryToAddAnalyser.append("\n@varInitial = N'" + analyser.getInitial() + "',");
			// @varMrmrs
			callStmt.setString(64, analyser.getMrmrs());
			queryToAddAnalyser.append("\n@varMrmrs = N'" + analyser.getMrmrs() + "',");
			// @varJrsr
			callStmt.setString(65, analyser.getJrsr());
			queryToAddAnalyser.append("\n@varJrsr = N'" + analyser.getJrsr() + "',");
			// @varCommision1
			callStmt.setString(66, analyser.getCommision1());
			queryToAddAnalyser.append("\n@varCommision1 = N'" + analyser.getCommision1() + "',");
			// @varCommision2
			callStmt.setString(67, analyser.getCommision2());
			queryToAddAnalyser.append("\n@varCommision2 = N'" + analyser.getCommision2() + "',");
			// @varCommision3
			callStmt.setString(68, analyser.getCommision3());
			queryToAddAnalyser.append("\n@varCommision3 = N'" + analyser.getCommision3() + "',");
			// @varCommision4
			callStmt.setString(69, analyser.getCommision4());
			queryToAddAnalyser.append("\n@varCommision4 = N'" + analyser.getCommision4() + "',");
			// @varCommEffDate
			callStmt.setString(70, analyser.getCommEffDate());
			queryToAddAnalyser.append("\n@varCommEffDate = N'" + analyser.getCommEffDate() + "',");
			// @varTemps
			callStmt.setString(71, analyser.getTemps());
			queryToAddAnalyser.append("\n@varTemps = N'" + analyser.getTemps() + "',");
			// @varAnnualPay
			callStmt.setString(72, analyser.getAnnualPay());
			queryToAddAnalyser.append("\n@varAnnualPay = N'" + analyser.getAnnualPay() + "',");
			// @varContractVehicle
			callStmt.setString(73, analyser.getContractVehicle());
			queryToAddAnalyser.append("\n@varContractVehicle = N'" + analyser.getContractVehicle() + "',");
			if (analyser.getRecordStatus() == null || analyser.getRecordStatus().trim().equals(""))
			{
				analyser.setRecordStatus("1");
			}
			// @intRecordStatus
			callStmt.setInt(74, Integer.valueOf(analyser.getRecordStatus()));
			queryToAddAnalyser.append("\n@intRecordStatus = " + Integer.valueOf(analyser.getRecordStatus()) + ",");
			// @varLiaisonName
			callStmt.setString(75, analyser.getLiaisonName());
			queryToAddAnalyser.append("\n@varLiaisonName = N'" + analyser.getLiaisonName() + "',");
			// @intReferences
			callStmt.setInt(76, references);
			queryToAddAnalyser.append("\n@intReferences = " + references + ",");
			// @intH1
			callStmt.setInt(77, h1);
			queryToAddAnalyser.append("\n@intH1 = " + h1 + ",");
			// @varJobCategory
			callStmt.setString(78, analyser.getJobCategory());
			queryToAddAnalyser.append("\n@varJobCategory = N'" + analyser.getJobCategory() + "',");
			// @execOrphan
			callStmt.setString(79, execOrphan.toString());
			queryToAddAnalyser.append("\n@execOrphan = N'" + execOrphan.toString() + "',");
			// @recruiterOrphan
			callStmt.setString(80, recruiterOrphan.toString());
			queryToAddAnalyser.append("\n@recruiterOrphan = N'" + recruiterOrphan.toString() + "',");
			// @other1Orphan
			callStmt.setString(81, other1Orphan.toString());
			queryToAddAnalyser.append("\n@other1Orphan = N'" + other1Orphan.toString() + "',");
			// @other2Orphan
			callStmt.setString(82, other2Orphan.toString());
			queryToAddAnalyser.append("\n@other2Orphan = N'" + other2Orphan.toString() + "',");
			// @varJobPayRate
			callStmt.setString(83, analyser.getJobPayRate());
			queryToAddAnalyser.append("\n@varJobPayRate = N'" + analyser.getJobPayRate() + "',");
			// @varJobBillRate
			callStmt.setString(84, analyser.getJobBillRate());
			queryToAddAnalyser.append("\n@varJobBillRate = N'" + analyser.getJobBillRate() + "',");
			// @varPoNumber
			callStmt.setString(85, analyser.getPonumber());
			queryToAddAnalyser.append("\n@varPoNumber = N'" + analyser.getPonumber() + "',");
			// @varDoubleTime
			callStmt.setString(86, analyser.getDoubleTime());
			queryToAddAnalyser.append("\n@varDoubleTime = N'" + analyser.getDoubleTime() + "',");
			// @varDoubleTimeBill
			callStmt.setString(87, analyser.getDoubleTimeBill());
			queryToAddAnalyser.append("\n@varDoubleTimeBill = N'" + analyser.getDoubleTimeBill() + "',");
			// @varPerdiem
			callStmt.setString(88, analyser.getPerdiem());
			queryToAddAnalyser.append("\n@varPerdiem = N'" + analyser.getPerdiem() + "',");
			// @varWorkEmail
			callStmt.setString(89, analyser.getWorkEmail());
			queryToAddAnalyser.append("\n@varWorkEmail = N'" + analyser.getWorkEmail() + "',");
			// @varChkReferralFee
			callStmt.setString(90, analyser.getChkReferralFee());
			queryToAddAnalyser.append("\n@varChkReferralFee = N'" + analyser.getChkReferralFee() + "',");
			// @varReferralFeeAmount
			callStmt.setString(91, analyser.getReferralFeeAmount());
			queryToAddAnalyser.append("\n@varReferralFeeAmount = N'" + analyser.getReferralFeeAmount() + "',");
			// @varDentalInsurance
			callStmt.setString(92, analyser.getDentalInsurance());
			queryToAddAnalyser.append("\n@varDentalInsurance = N'" + analyser.getDentalInsurance() + "',");
			// @varStdBenefit
			callStmt.setString(93, analyser.getStdBenefit());
			queryToAddAnalyser.append("\n@varStdBenefit = N'" + analyser.getStdBenefit() + "',");
			// @varLtdBenefit
			callStmt.setString(94, analyser.getLtdBenefit());
			queryToAddAnalyser.append("\n@varLtdBenefit = N'" + analyser.getLtdBenefit() + "',");
			// @varConsultantJobBoard
			callStmt.setString(95, analyser.getConsultantJobBoard());
			queryToAddAnalyser.append("\n@varConsultantJobBoard = N'" + analyser.getConsultantJobBoard() + "',");
			// @varDentalInsuranceAmount
			val = analyser.getDentalInsuranceAmount() == null ? "0.00" : analyser.getDentalInsuranceAmount().toString();
			callStmt.setString(96, val);
			queryToAddAnalyser.append("\n@varDentalInsuranceAmount = N'" + val + "',");
			// @varUnEmployedForTwoMonths
			callStmt.setString(97, analyser.getUnEmployedForTwoMonths());
			queryToAddAnalyser.append("\n@varUnEmployedForTwoMonths = N'" + analyser.getUnEmployedForTwoMonths() + "',");
			// @varEmployeeVeteran
			callStmt.setString(98, analyser.getEmployeeVeteran());
			queryToAddAnalyser.append("\n@varEmployeeVeteran = N'" + analyser.getEmployeeVeteran() + "',");
			// @gender
			callStmt.setString(99, analyser.getGender());
			queryToAddAnalyser.append("\n@gender = N'" + analyser.getGender() + "',");
			// @WorkSiteState
			callStmt.setString(100, analyser.getWorkSiteState());
			queryToAddAnalyser.append("\n@WorkSiteState = N'" + analyser.getWorkSiteState() + "',");
			// @AnalyzerCategory1
			callStmt.setString(101, analyser.getAnalyzerCategory1());
			queryToAddAnalyser.append("\n@AnalyzerCategory1 = N'" + analyser.getAnalyzerCategory1() + "',");
			// @AnalyzerCategory2
			callStmt.setString(102, analyser.getAnalyzerCategory2());
			queryToAddAnalyser.append("\n@AnalyzerCategory2 = N'" + analyser.getAnalyzerCategory2() + "',");
			// @FLSAStatus
			callStmt.setString(103, analyser.getFlsaStatus());
			queryToAddAnalyser.append("\n@FLSAStatus = N'" + analyser.getFlsaStatus() + "',");
			// @FLSAReferance
			callStmt.setString(104, analyser.getFlsaReference());
			queryToAddAnalyser.append("\n@FLSAReferance = N'" + analyser.getFlsaReference() + "',");
			// @Custom1
			callStmt.setString(105, analyser.getCustom1());
			queryToAddAnalyser.append("\n@Custom1 = N'" + analyser.getCustom1() + "',");
			// @Com
			callStmt.setString(106, com.toString());
			queryToAddAnalyser.append("\n@Com = N'" + com.toString() + "',");
			// @Custom2
			callStmt.setString(107, analyser.getCustom2());
			queryToAddAnalyser.append("\n@Custom2 = N'" + analyser.getCustom2() + "',");
			// @Comm
			callStmt.setString(108, analyser.getComm());
			queryToAddAnalyser.append("\n@Comm = N'" + analyser.getComm() + "',");
			// @DealType
			callStmt.setString(109, analyser.getDealType());
			queryToAddAnalyser.append("\n@DealType = N'" + analyser.getDealType() + "',");
			// @IsBonusEligible
			callStmt.setString(110, analyser.getIsBonusEligible());
			queryToAddAnalyser.append("\n@IsBonusEligible = N'" + analyser.getIsBonusEligible() + "',");
			// @BonusAmount
			callStmt.setDouble(111, analyser.getBonusAmount());
			queryToAddAnalyser.append("\n@BonusAmount = " + analyser.getBonusAmount() + ",");
			// @BonusPercentage
			callStmt.setString(112, analyser.getBonusPercentage());
			queryToAddAnalyser.append("\n@BonusPercentage = N'" + analyser.getBonusPercentage() + "',");
			// @PermGAAmount
			callStmt.setDouble(113, analyser.getPermGAAmount());
			queryToAddAnalyser.append("\n@PermGAAmount = " + analyser.getPermGAAmount() + ",");
			// @splitCommissionPercentage1
			callStmt.setString(114, analyser.getSplitCommissionPercentage1());
			queryToAddAnalyser.append("\n@splitCommissionPercentage1 = N'" + analyser.getSplitCommissionPercentage1() + "',");
			// @splitCommissionPercentage2
			callStmt.setString(115, analyser.getSplitCommissionPercentage2());
			queryToAddAnalyser.append("\n@splitCommissionPercentage2 = N'" + analyser.getSplitCommissionPercentage2() + "',");
			// @splitCommissionPercentage3
			callStmt.setString(116, analyser.getSplitCommissionPercentage3());
			queryToAddAnalyser.append("\n@splitCommissionPercentage3 = N'" + analyser.getSplitCommissionPercentage3() + "',");
			// @splitCommissionPercentage4
			callStmt.setString(117, analyser.getSplitCommissionPercentage4());
			queryToAddAnalyser.append("\n@splitCommissionPercentage4 = N'" + analyser.getSplitCommissionPercentage4() + "',");
			// @skillCategory
			callStmt.setString(118, analyser.getSkillCategory());
			queryToAddAnalyser.append("\n@skillCategory = N'" + analyser.getSkillCategory() + "',");
			// @vertical
			callStmt.setString(119, analyser.getVertical());
			queryToAddAnalyser.append("\n@vertical = N'" + analyser.getVertical() + "',");
			// @employeeClass
			callStmt.setString(120, analyser.getEmployeeClass());
			queryToAddAnalyser.append("\n@employeeClass = N'" + analyser.getEmployeeClass() + "',");
			// @verticalTimesheetType
			callStmt.setString(121, analyser.getVerticalTimesheetType());
			queryToAddAnalyser.append("\n@verticalTimesheetType = N'" + analyser.getVerticalTimesheetType() + "',");
			// @ImmigrationCost
			callStmt.setDouble(122, analyser.getImmigrationCost());
			queryToAddAnalyser.append("\n@ImmigrationCost = " + analyser.getImmigrationCost() + ",");
			// @EquipmentCost
			callStmt.setDouble(123, analyser.getEquipmentCost());
			queryToAddAnalyser.append("\n@EquipmentCost = " + analyser.getEquipmentCost() + ",");
			// @Product
			callStmt.setString(124, analyser.getProduct());
			queryToAddAnalyser.append("\n@Product = N'" + analyser.getProduct() + "',");
			// @NonBillableCost
			callStmt.setDouble(125, analyser.getNonBillableCost());
			queryToAddAnalyser.append("\n@NonBillableCost = " + analyser.getNonBillableCost() + ",");
			// @TravelRequired
			callStmt.setString(126, analyser.getTravelRequired());
			queryToAddAnalyser.append("\n@TravelRequired = N'" + analyser.getTravelRequired() + "',");
			// @CommissionModel1
			callStmt.setString(127, analyser.getCommissionModel1());
			queryToAddAnalyser.append("\n@CommissionModel1 = N'" + analyser.getCommissionModel1() + "',");
			// @CommissionModel2
			callStmt.setString(128, analyser.getCommissionModel2());
			queryToAddAnalyser.append("\n@CommissionModel2 = N'" + analyser.getCommissionModel2() + "',");
			// @CommissionModel3
			callStmt.setString(129, analyser.getCommissionModel3());
			queryToAddAnalyser.append("\n@CommissionModel3 = N'" + analyser.getCommissionModel3() + "',");
			// @CommissionModel4
			callStmt.setString(130, analyser.getCommissionModel4());
			queryToAddAnalyser.append("\n@CommissionModel4 = N'" + analyser.getCommissionModel4() + "',");
			// @RecruitingClassification
			callStmt.setString(131, analyser.getRecruitingClassification());
			queryToAddAnalyser.append("\n@RecruitingClassification = N'" + analyser.getRecruitingClassification() + "',");
			// @SickLeaveCost
			callStmt.setDouble(132, analyser.getSickLeaveCost());
			queryToAddAnalyser.append("\n@SickLeaveCost = " + analyser.getSickLeaveCost() + ",");
			// @FalseTermination
			callStmt.setString(133, analyser.getFalseTermination());
			queryToAddAnalyser.append("\n@FalseTermination = N'" + analyser.getFalseTermination() + "',");
			// @CommissionPerson5
			callStmt.setString(134, analyser.getCommissionPerson5());
			queryToAddAnalyser.append("\n@CommissionPerson5 = N'" + analyser.getCommissionPerson5() + "',");
			// @CommissionPerson6
			callStmt.setString(135, analyser.getCommissionPerson6());
			queryToAddAnalyser.append("\n@CommissionPerson6 = N'" + analyser.getCommissionPerson6() + "',");
			// @CommissionPerson7
			callStmt.setString(136, analyser.getCommissionPerson7());
			queryToAddAnalyser.append("\n@CommissionPerson7 = N'" + analyser.getCommissionPerson7() + "',");
			// @CommissionPerson8
			callStmt.setString(137, analyser.getCommissionPerson8());
			queryToAddAnalyser.append("\n@CommissionPerson8 = N'" + analyser.getCommissionPerson8() + "',");
			// @CommissionPerson9
			callStmt.setString(138, analyser.getCommissionPerson9());
			queryToAddAnalyser.append("\n@CommissionPerson9 = N'" + analyser.getCommissionPerson9() + "',");
			// @CommissionPercentage5
			callStmt.setDouble(139, analyser.getCommissionPercentage5());
			queryToAddAnalyser.append("\n@CommissionPercentage5 = " + analyser.getCommissionPercentage5() + ",");
			// @CommissionPercentage6
			callStmt.setDouble(140, analyser.getCommissionPercentage6());
			queryToAddAnalyser.append("\n@CommissionPercentage6 = " + analyser.getCommissionPercentage6() + ",");
			// @CommissionPercentage7
			callStmt.setDouble(141, analyser.getCommissionPercentage7());
			queryToAddAnalyser.append("\n@CommissionPercentage7 = " + analyser.getCommissionPercentage7() + ",");
			// @CommissionPercentage8
			callStmt.setDouble(142, analyser.getCommissionPercentage8());
			queryToAddAnalyser.append("\n@CommissionPercentage8 = " + analyser.getCommissionPercentage8() + ",");
			// @CommissionPercentage9
			callStmt.setDouble(143, analyser.getCommissionPercentage9());
			queryToAddAnalyser.append("\n@CommissionPercentage9 = " + analyser.getCommissionPercentage9() + ",");
			// @Commission5
			callStmt.setDouble(144, analyser.getCommission5());
			queryToAddAnalyser.append("\n@Commission5 = " + analyser.getCommission5() + ",");
			// @Commission6
			callStmt.setDouble(145, analyser.getCommission6());
			queryToAddAnalyser.append("\n@Commission6 = " + analyser.getCommission6() + ",");
			// @Commission7
			callStmt.setDouble(146, analyser.getCommission7());
			queryToAddAnalyser.append("\n@Commission7 = " + analyser.getCommission7() + ",");
			// @Commission8
			callStmt.setDouble(147, analyser.getCommission8());
			queryToAddAnalyser.append("\n@Commission8 = " + analyser.getCommission8() + ",");
			// @Commission9
			callStmt.setDouble(148, analyser.getCommission9());
			queryToAddAnalyser.append("\n@Commission9 = " + analyser.getCommission9() + ",");
			// @ProjectCost
			callStmt.setDouble(149, analyser.getProjectCost());
			queryToAddAnalyser.append("\n@ProjectCost = " + analyser.getProjectCost() + ",");
			// @SickLeavePerHourRate
			callStmt.setDouble(150, analyser.getSickLeavePerHourRate());
			queryToAddAnalyser.append("\n@SickLeavePerHourRate = " + analyser.getSickLeavePerHourRate() + ",");
			// @SickLeaveCap
			callStmt.setString(151, analyser.getSickLeaveCap());
			queryToAddAnalyser.append("\n@SickLeaveCap = N'" + analyser.getSickLeaveCap() + "',");
			// @ManagingDirector
			callStmt.setString(152, analyser.getManagingDirector());
			queryToAddAnalyser.append("\n@ManagingDirector = N'" + analyser.getManagingDirector() + "',");
			// @CommissionPersonGrade1
			callStmt.setString(153, analyser.getCommissionPersonGrade1());
			queryToAddAnalyser.append("\n@CommissionPersonGrade1 = N'" + analyser.getCommissionPersonGrade1() + "',");
			// @CommissionPersonGrade2
			callStmt.setString(154, analyser.getCommissionPersonGrade2());
			queryToAddAnalyser.append("\n@CommissionPersonGrade2 = N'" + analyser.getCommissionPersonGrade2() + "',");
			// @CommissionPersonGrade3
			callStmt.setString(155, analyser.getCommissionPersonGrade3());
			queryToAddAnalyser.append("\n@CommissionPersonGrade3 = N'" + analyser.getCommissionPersonGrade3() + "',");
			// @CommissionPersonGrade4
			callStmt.setString(156, analyser.getCommissionPersonGrade4());
			queryToAddAnalyser.append("\n@CommissionPersonGrade4 = N'" + analyser.getCommissionPersonGrade4() + "',");
			// @IsAddressUSPSValidated
			callStmt.setString(157, analyser.getIsAddressUSPSValidated());
			queryToAddAnalyser.append("\n@IsAddressUSPSValidated = N'" + analyser.getIsAddressUSPSValidated() + "',");
			// @UsPSAddressValidationDate
			val = analyser.getuSPSAddressValidationDate() == null ? null : analyser.getuSPSAddressValidationDate().toString();
			callStmt.setString(158, val);
			queryToAddAnalyser.append("\n@UsPSAddressValidationDate = N'" + val + "',");
			// @billablePTO
			callStmt.setDouble(159, analyser.getBillablePTO());
			queryToAddAnalyser.append("\n@billablePTO = " + analyser.getBillablePTO() + ",");
			// @nonBillablePTO
			callStmt.setDouble(160, analyser.getNonBillablePTO());
			queryToAddAnalyser.append("\n@nonBillablePTO = " + analyser.getNonBillablePTO() + ",");
			// @billablePTOCost
			callStmt.setDouble(161, analyser.getBillablePTOCost());
			queryToAddAnalyser.append("\n@billablePTOCost = " + analyser.getBillablePTOCost() + ",");
			// @nonBillablePTOCost
			callStmt.setDouble(162, analyser.getNonBillablePTOCost());
			queryToAddAnalyser.append("\n@nonBillablePTOCost = " + analyser.getNonBillablePTOCost() + ",");
			// @totalHolidays
			callStmt.setDouble(163, analyser.getTotalHolidays());
			queryToAddAnalyser.append("\n@totalHolidays = " + analyser.getTotalHolidays() + ",");
			// @billableHolidays
			callStmt.setDouble(164, analyser.getBillableHolidays());
			queryToAddAnalyser.append("\n@billableHolidays = " + analyser.getBillableHolidays() + ",");
			// @nonBillableHolidays
			callStmt.setDouble(165, analyser.getNonBillableHolidays());
			queryToAddAnalyser.append("\n@nonBillableHolidays = " + analyser.getNonBillableHolidays() + ",");
			// @billableHolidaysCost
			callStmt.setDouble(166, analyser.getBillableHolidaysCost());
			queryToAddAnalyser.append("\n@billableHolidaysCost = " + analyser.getBillableHolidaysCost() + ",");
			// @nonBillableHolidaysCost
			callStmt.setDouble(167, analyser.getNonBillableHolidaysCost());
			queryToAddAnalyser.append("\n@nonBillableHolidaysCost = " + analyser.getNonBillableHolidaysCost() + ",");
			// @intPermanentPlacement
			callStmt.setString(168, placement.toString());
			queryToAddAnalyser.append("\n@intPermanentPlacement = N'" + placement.toString() + "',");
			// @varPlacementAmount
			callStmt.setString(169, analyser.getPlacementAmount());
			queryToAddAnalyser.append("\n@varPlacementAmount = N'" + analyser.getPlacementAmount() + "',");
			// @varSalaryAmount
			callStmt.setString(170, analyser.getSalaryAmount());
			queryToAddAnalyser.append("\n@varSalaryAmount = N'" + analyser.getSalaryAmount() + "',");
			// @varPlacementPercentage
			callStmt.setString(171, analyser.getPlacementPercentage());
			queryToAddAnalyser.append("\n@varPlacementPercentage = N'" + analyser.getPlacementPercentage() + "',");
			// @varPlacementDate
			callStmt.setString(172, analyser.getPlacementDate());
			queryToAddAnalyser.append("\n@varPlacementDate = N'" + analyser.getPlacementDate() + "',");
			// @intDummy
			callStmt.setString(173, null);
			queryToAddAnalyser.append("\n@intDummy = N'" + null + "',");
			// @intDummy1
			callStmt.setString(174, null);
			queryToAddAnalyser.append("\n@intDummy1 = N'" + null + "',");
			// @execOrphanInter
			callStmt.setString(175, execOrphanInter.toString());
			queryToAddAnalyser.append("\n@execOrphanInter = N'" + execOrphanInter.toString() + "',");
			// @varBranchInter
			callStmt.setString(176, analyser.getBranchInter());
			queryToAddAnalyser.append("\n@varBranchInter = N'" + analyser.getBranchInter() + "',");
			// @varCommentsInter
			callStmt.setString(177, analyser.getCommentsInter());
			queryToAddAnalyser.append("\n@varCommentsInter = N'" + analyser.getCommentsInter() + "',");
			// @varCommName1Inter
			callStmt.setString(178, analyser.getCommName1Inter());
			queryToAddAnalyser.append("\n@varCommName1Inter = N'" + analyser.getCommName1Inter() + "',");
			// @varPer1Inter
			callStmt.setString(179, analyser.getCommPer1Inter());
			queryToAddAnalyser.append("\n@varPer1Inter = N'" + analyser.getCommPer1Inter() + "',");
			// @varCommName2Inter
			callStmt.setString(180, analyser.getCommName2Inter());
			queryToAddAnalyser.append("\n@varCommName2Inter = N'" + analyser.getCommName2Inter() + "',");
			// @varPer2Inter
			callStmt.setString(181, analyser.getCommPer2Inter());
			queryToAddAnalyser.append("\n@varPer2Inter = N'" + analyser.getCommPer2Inter() + "',");
			// @varCommName3Inter
			callStmt.setString(182, analyser.getCommName3Inter());
			queryToAddAnalyser.append("\n@varCommName3Inter = N'" + analyser.getCommName3Inter() + "',");
			// @varPer3Inter
			callStmt.setString(183, analyser.getCommPer3Inter());
			queryToAddAnalyser.append("\n@varPer3Inter = N'" + analyser.getCommPer3Inter() + "',");
			// @varCommName4Inter
			callStmt.setString(184, analyser.getCommName4Inter());
			queryToAddAnalyser.append("\n@varCommName4Inter = N'" + analyser.getCommName4Inter() + "',");
			// @varPer4Inter
			callStmt.setString(185, analyser.getCommPer4Inter());
			queryToAddAnalyser.append("\n@varPer4Inter = N'" + analyser.getCommPer4Inter() + "',");
			// @varCommision1Inter
			callStmt.setString(186, analyser.getCommision1Inter());
			queryToAddAnalyser.append("\n@varCommision1Inter = N'" + analyser.getCommision1Inter() + "',");
			// @varCommision2Inter
			callStmt.setString(187, analyser.getCommision2Inter());
			queryToAddAnalyser.append("\n@varCommision2Inter = N'" + analyser.getCommision2Inter() + "',");
			// @varCommision3Inter
			callStmt.setString(188, analyser.getCommision3Inter());
			queryToAddAnalyser.append("\n@varCommision3Inter = N'" + analyser.getCommision3Inter() + "',");
			// @varCommision4Inter
			callStmt.setString(189, analyser.getCommision4Inter());
			queryToAddAnalyser.append("\n@varCommision4Inter = N'" + analyser.getCommision4Inter() + "',");
			if (analyser.getSplit() == null || analyser.getSplit().trim().equals(""))
			{
				analyser.setSplit("0");
			}
			// @intSplit
			callStmt.setInt(190, Integer.valueOf(analyser.getSplit()));
			queryToAddAnalyser.append("\n@intSplit = " + Integer.valueOf(analyser.getSplit()) + ",");
			// @varCountry
			callStmt.setString(191, analyser.getCountry());
			queryToAddAnalyser.append("\n@varCountry = N'" + analyser.getCountry() + "',");
			// @bitInternational
			callStmt.setString(192, null);
			queryToAddAnalyser.append("\n@bitInternational = N'" + null + "',");
			// @other1OrphanInter
			callStmt.setString(193, other1OrphanInter.toString());
			queryToAddAnalyser.append("\n@other1OrphanInter = N'" + other1OrphanInter.toString() + "',");
			// @other2OrphanInter
			callStmt.setString(194, other2OrphanInter.toString());
			queryToAddAnalyser.append("\n@other2OrphanInter = N'" + other2OrphanInter.toString() + "',");
			// @recruiterOrphanInter
			callStmt.setString(195, recruiterOrphanInter.toString());
			queryToAddAnalyser.append("\n@recruiterOrphanInter = N'" + recruiterOrphanInter.toString() + "',");
			
			// @grossProfit1
			callStmt.setDouble(196, analyser.getGrossProfit());
			queryToAddAnalyser.append("\n@grossProfit = " + analyser.getGrossProfit() + ",");
			
			// @grossProfitPercentage1
			callStmt.setDouble(197, analyser.getGrossProfitPercentage());
			queryToAddAnalyser.append("\n@grossProfitPercentage = " + analyser.getGrossProfitPercentage() + ",");
			
			// @commissionableProfit1
			callStmt.setDouble(198, analyser.getCommissionableProfit());
			queryToAddAnalyser.append("\n@commissionableProfit = " + analyser.getCommissionableProfit() + ",");
			
			// @geoOffice1
			callStmt.setString(199, analyser.getGeoOffice());
			queryToAddAnalyser.append("\n@geoOffice = N'" + analyser.getGeoOffice() + "',");
			
			callStmt.setString(200, analyser.getLongitude());
			queryToAddAnalyser.append("\n@longitude = N'" + analyser.getLongitude() + "',");
			
			callStmt.setString(201, analyser.getLatitude());
			queryToAddAnalyser.append("\n@latitude = N'" + analyser.getLatitude() + "',");
			
			// @pTOLimitToOverrideSick
			callStmt.setDouble(202, analyser.getpTOLimitToOverrideSick());
			queryToAddAnalyser.append("\n@pTOLimitToOverrideSick = " + analyser.getpTOLimitToOverrideSick() + ",");
			
			// @DistanceFromWorksite
			callStmt.setString(203, analyser.getDistanceFromWorksite());
			queryToAddAnalyser.append("\n@DistanceFromWorksite = '" + analyser.getDistanceFromWorksite() + "' ");
			
			queryToAddAnalyser.append("\nSELECT	'Return Value' = @return_value \nGO ");
			
			System.out.println(queryToAddAnalyser.toString());
			// logger.debug("Query to add update analyser is : " +
			// queryToAddAnalyser.toString());
			
			// execute insertDBUSER store procedure
			rs = callStmt.executeQuery();
			String status = "";
			if (rs != null)
			{
				while (rs.next())
				{
					status = "" + rs.getInt(1);
					System.out.println("Status : "+status);
				}
				// con.close();
				callStmt.close();
				rs.close();
				// con = null;
				callStmt = null;
				rs = null;
			}
		}
		catch (Exception ex)
		{
			
			System.err.println("Exception : " + ex.getMessage());
			System.out.println("Exception in addUpdateAnalyser method of AnalyserRepositoryImpl");
			ex.printStackTrace();
		}
		finally
		{
			if (rs != null) try
			{
				rs.close();
			}
			catch (SQLException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (callStmt != null) try
			{
				callStmt.close();
			}
			catch (SQLException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	public static AnalyserDTO getAnalyserInfo(Integer analyserId)
	{
		try{
			CallableStatement callStmt = null;
			ResultSet rs = null;
			Connection connection = DBConnection.getStaticConnection();
			String query = "{call " + FacesUtils.SCHEMA_WIRELESS + ".spGetAnalyserInfo(" + analyserId + ")}";
			System.out.println("query " + query);
			callStmt = connection.prepareCall(query);
			rs = callStmt.executeQuery();
			List<AnalyserDTO> list = new ArrayList<AnalyserDTO>();
			
			boolean references = false;
			boolean h1 = false;
			boolean execOrphan = false;
			boolean recruiterOrphan = false;
			boolean other1Orphan = false;
			boolean other2Orphan = false;
			
			boolean execOrphanInter = false;
			boolean recruiterOrphanInter = false;
			boolean other1OrphanInter = false;
			boolean other2OrphanInter = false;
			
			boolean placement = false;
			boolean com = false;
			
			AnalyserDTO analyser;
			if (rs != null)
			{
				
				if (rs.next())
				{
					
					analyser = new AnalyserDTO();
					
					analyser.setAnalyserId(rs.getString("AnalyserId"));
					analyser.setOrgId(rs.getString("Org_Id"));
					analyser.setfName(rs.getString("FName"));
					analyser.setlName(rs.getString("LName"));
					
					analyser.setAddress1(rs.getString("Address1"));
					analyser.setAddress2(rs.getString("Address2"));
					analyser.setCity(rs.getString("City"));
					analyser.setState(rs.getString("State"));
					analyser.setTelephone(rs.getString("Telephone"));
					analyser.setWorkPhone(rs.getString("Workphone"));
					analyser.setZip(rs.getString("Zipcode"));
					analyser.setEmail(rs.getString("Email"));
					analyser.setFax(rs.getString("Fax"));
					analyser.setEmpType(rs.getString("EmpType"));
					
					analyser.setTotHoursWorked(rs.getDouble("THW"));
					analyser.setBenefitsAmount(rs.getString("HealthBenefitRates"));
					analyser.setPercent401k(rs.getString("Percentage_401k"));
					analyser.setEducation(rs.getString("Education"));
					
					analyser.setLeave(rs.getDouble("Leave"));
					if (analyser.getLeave() == null)
					{
						analyser.setLeave(0.00);
					}
					
					analyser.setOd(rs.getString("Other_Dollar"));
					analyser.setOtherAmounts(rs.getString("OtherAmount_Hour"));
					analyser.setDiscount(rs.getString("Discount"));
					
					analyser.setBillRate(rs.getDouble("BillRate"));
					
					analyser.setHealth(rs.getString("Health"));
					if (analyser.getHealth() == null || analyser.getHealth().trim().equals("") || analyser.getHealth().trim().equals("null"))
					{
						analyser.setHealth("0");
					}
					
					analyser.setK401(rs.getString("k401"));
					analyser.setTax(rs.getString("Taxes"));
					analyser.setPayRate(rs.getDouble("PayRate"));
					analyser.setGa(rs.getDouble("GA"));
					analyser.setCommission(rs.getDouble("Commission"));
					analyser.setSpreadWeek(rs.getString("Spread_w"));
					
					analyser.setOtb(rs.getString("OneTime_Bill"));
					analyser.setOneTimeAmount(rs.getString("OneTime_Pay"));
					
					analyser.setSsn(rs.getString("SSN"));
					analyser.setDob(rs.getString("DOB"));
					analyser.setJobTitle(rs.getString("Job_Title"));
					analyser.setStartDate(rs.getString("StartDate"));
					analyser.setEndDate(rs.getString("EndDate"));
					
					analyser.setClientId(rs.getString("ClientId"));
					analyser.setClientAddressId(Integer.valueOf(rs.getString("ClientAddressId")));
					analyser.setClientSiteId(Integer.valueOf(rs.getString("SiteId")));
					analyser.setContractorId(rs.getString("SubContractorId"));
					
					analyser.setClientCompany(rs.getString("ClientCompany"));
					analyser.setInvoiceAddress(rs.getString("ClientAddress"));
					analyser.setClientManagerName(rs.getString("ClientManagerName"));
					analyser.setManagerPhone(rs.getString("ClientManagerPhone"));
					analyser.setSiteLocation(rs.getString("ClientSiteLocation"));
					
					analyser.setContCompanyName(rs.getString("ContractorCompany"));
					analyser.setContFin(rs.getString("ContractorFin"));
					analyser.setContPocName(rs.getString("ContractorPOC"));
					analyser.setContPayTerm(rs.getString("ContractorPayTerm"));
					analyser.setContEmail(rs.getString("ContractorEmail"));
					analyser.setContPhone(rs.getString("ContractorPhone"));
					analyser.setContAddress(rs.getString("ContractorAddress"));
					
					analyser.setComments(rs.getString("Comments"));
					
					analyser.setRecordStatus(rs.getString("RecordStatus"));
					
					analyser.setCommName1(rs.getString("Commision_Name1"));
					analyser.setCommPer1(rs.getDouble("Commision_Percent1"));
					
					analyser.setCommName2(rs.getString("Commision_Name2"));
					
					// TODO check with Mr Tayyab about the 3.5 value
					// in commission 2
					/*
					 * USE [Analyser] GO DECLARE @return_value int EXEC
					 * @return_value = [wireless].[spGetCommissionHistory]
					 * 
					 * @VARUSERID = N'Gregory.Armstrong@DISYS.COM',
					 * 
					 * @VARNAME = N'ALL',
					 * 
					 * @VARPARENT = N'170675' SELECT 'Return Value' = @return_value
					 * GO
					 */
					String res = rs.getString("Commision_Percent2");
					if (res != null && !res.equals(""))
					{
						if (res.contains("."))
						{
							res = res.substring(0, res.indexOf('.'));
						}
						Double val = Double.parseDouble(res);
						analyser.setCommPer2(val);
					}
					
					analyser.setCommName3(rs.getString("Commision_Name3"));
					analyser.setCommPer3(rs.getDouble("Commision_Percent3"));
					
					analyser.setCommName4(rs.getString("Commision_Name4"));
					analyser.setCommPer4(rs.getDouble("Commision_Percent4"));
					
					analyser.setProfit(rs.getDouble("Profit"));
					analyser.setDiscountRate(rs.getString("DiscountRate"));
					analyser.setEffectiveDate(rs.getString("EffectiveDate"));
					
					analyser.setTermDate(rs.getString("TerminateDate"));
					analyser.setReason(rs.getString("Reason"));
					
					analyser.setBranch(rs.getString("Branch"));
					analyser.setCurrency(rs.getString("Currency"));
					analyser.setRdoPTO(rs.getString("PTO"));
					
					analyser.setMobilePager(rs.getString("Mobile_Pager"));
					analyser.setInitial(rs.getString("Initial"));
					analyser.setMrmrs(rs.getString("Mrmrs"));
					analyser.setJrsr(rs.getString("Jrsr"));
					
					analyser.setCommision1(rs.getString("Commision1"));
					analyser.setCommision2(rs.getString("Commision2"));
					analyser.setCommision3(rs.getString("Commision3"));
					analyser.setCommision4(rs.getString("Commision4"));
					
					analyser.setInternalAccounting(rs.getString("Internal_Accounting"));
					
					analyser.setCommEffDate(rs.getString("CommEffDate"));
					
					analyser.setTemps(rs.getString("Temps"));
					analyser.setAnnualPay(rs.getString("AnnualPay"));
					analyser.setContractVehicle(rs.getString("ContractVehicle"));
					
					analyser.setJobPayRate(rs.getString("JobPayRate"));
					analyser.setJobBillRate(rs.getString("JobBillRate"));
					
					analyser.setPonumber(rs.getString("PoNumber"));
					
					analyser.setLiaisonName(rs.getString("LiaisonName"));
					
					analyser.setJobCategory(rs.getString("JobCategory"));
					analyser.setCountry(rs.getString("Country"));
					
					String val = rs.getString("CheckedReferences");
					if (val != null)
					{
						if (val.equals("1"))
						{
							references = true;
						}
						else
						{
							references = false;
						}
					}
					
					val = rs.getString("H1");
					if (val != null)
					{
						if (val.equals("1"))
						{
							h1 = true;
						}
						else
						{
							h1 = false;
						}
					}
					
					analyser.setReferences(references);
					analyser.setH1(h1);
					
					analyser.setBranchInter(rs.getString("BranchInter"));
					analyser.setCommentsInter(rs.getString("CommentsInter"));
					
					analyser.setCommName1Inter(rs.getString("Commision_Name1Inter"));
					analyser.setCommPer1Inter(rs.getString("Commision_Percent1Inter"));
					
					analyser.setCommName2Inter(rs.getString("Commision_Name2Inter"));
					analyser.setCommPer2Inter(rs.getString("Commision_Percent2Inter"));
					
					analyser.setCommName3Inter(rs.getString("Commision_Name3Inter"));
					analyser.setCommPer3Inter(rs.getString("Commision_Percent3Inter"));
					
					analyser.setCommName4Inter(rs.getString("Commision_Name4Inter"));
					analyser.setCommPer4Inter(rs.getString("Commision_Percent4Inter"));
					
					analyser.setCommision1Inter(rs.getString("Commision1Inter"));
					analyser.setCommision2Inter(rs.getString("Commision2Inter"));
					analyser.setCommision3Inter(rs.getString("Commision3Inter"));
					analyser.setCommision4Inter(rs.getString("Commision4Inter"));
					
					analyser.setSplit(rs.getString("Split"));
					
					val = rs.getString("ExecOrphan");
					if (val != null)
					{
						if (val.equals("1"))
						{
							execOrphan = true;
						}
						else
						{
							execOrphan = false;
						}
					}
					analyser.setExecOrphan(execOrphan);
					
					val = rs.getString("RecruiterOrphan");
					if (val != null)
					{
						if (val.equals("1"))
						{
							recruiterOrphan = true;
						}
						else
						{
							recruiterOrphan = false;
						}
					}
					analyser.setRecruiterOrphan(recruiterOrphan);
					
					val = rs.getString("Other1Orphan");
					if (val != null)
					{
						if (val.equals("1"))
						{
							other1Orphan = true;
						}
						else
						{
							other1Orphan = false;
						}
					}
					analyser.setOther1Orphan(other1Orphan);
					
					val = rs.getString("Other2Orphan");
					if (val != null)
					{
						if (val.equals("1"))
						{
							other2Orphan = true;
						}
						else
						{
							other2Orphan = false;
						}
					}
					analyser.setOther2Orphan(other2Orphan);
					
					val = rs.getString("ExecOrphanInter");
					if (val != null)
					{
						if (val.equals("1"))
						{
							execOrphanInter = true;
						}
						else
						{
							execOrphanInter = false;
						}
					}
					analyser.setExecOrphanInter(execOrphanInter);
					
					val = rs.getString("RecruiterOrphanInter");
					if (val != null)
					{
						if (val.equals("1"))
						{
							recruiterOrphanInter = true;
						}
						else
						{
							recruiterOrphanInter = false;
						}
					}
					analyser.setRecruiterOrphanInter(recruiterOrphanInter);
					
					val = rs.getString("Other1OrphanInter");
					if (val != null)
					{
						if (val.equals("1"))
						{
							other1OrphanInter = true;
						}
						else
						{
							other1OrphanInter = false;
						}
					}
					analyser.setOther1OrphanInter(other1OrphanInter);
					
					val = rs.getString("Other2OrphanInter");
					if (val != null)
					{
						if (val.equals("1"))
						{
							other2OrphanInter = true;
						}
						else
						{
							other2OrphanInter = false;
						}
					}
					analyser.setOther2OrphanInter(other2OrphanInter);
					
					val = rs.getString("PermanentPlacement");
					if (val != null)
					{
						if (val.equals("1"))
						{
							placement = true;
						}
						else
						{
							placement = false;
						}
					}
					analyser.setPlacement(placement);
					
					val = rs.getString("Com");
					if (val != null)
					{
						if (val.equals("1"))
						{
							com = true;
						}
						else
						{
							com = false;
						}
					}
					analyser.setCom(com);
					
					analyser.setDoubleTime(rs.getString("DoubleTime"));
					analyser.setDoubleTimeBill(rs.getString("DoubleTimeBill"));
					analyser.setPerdiem(rs.getString("PerDiem"));
					analyser.setPlacementAmount(rs.getString("PlacementAmount"));
					analyser.setPlacementDate(rs.getString("PlacementDate"));
					analyser.setSalaryAmount(rs.getString("PlacementSalaryAmount"));
					analyser.setPlacementPercentage(rs.getString("PlacementPercentage"));
					
					analyser.setWorkEmail(rs.getString("workEmail"));
					analyser.setChkReferralFee(rs.getString("chkReferralFee"));
					analyser.setReferralFeeAmount(rs.getString("referralFeeAmount"));
					
					analyser.setVolumeDiscount(rs.getDouble("volumeDiscount"));
					analyser.setPaymentDiscount(rs.getDouble("paymentDiscount"));
					analyser.setOtherDiscount(rs.getDouble("otherDiscount"));
					analyser.setBackgroundAmount(rs.getDouble("backgroundAmount"));
					analyser.setCreditCheckAmount(rs.getDouble("creditCheckAmount"));
					analyser.setDrugTestAmount(rs.getDouble("drugTestAmount"));
					analyser.setBackgroundCheckAmount(0.00);
					analyser.setClientEmail(rs.getString("clientEmail"));
					
					analyser.setManagerEmail(rs.getString("managerEmail"));
					
					analyser.setUnEmployedForTwoMonths(rs.getString("unEmployedForTwoMonths"));
					
					analyser.setDentalInsurance(rs.getString("dentalInsurance"));
					analyser.setStdBenefit(rs.getString("stdBenefit"));
					analyser.setLtdBenefit(rs.getString("ltdBenefit"));
					analyser.setConsultantJobBoard(rs.getString("consultantJobBoard"));
					
					analyser.setPaymentTerms(rs.getString("paymentTerms"));
					analyser.setAttention(rs.getString("attention"));
					analyser.setDistributionMethod(rs.getString("distributionMethod"));
					analyser.setSpecialNotes(rs.getString("SpecialNotes"));
					analyser.setInvoiceFrequency(rs.getString("invoiceFrequency"));
					
					analyser.setEmployeeVeteran(rs.getString("EmployeeVeteran"));
					analyser.setGender(rs.getString("Gender")); // add
																// gender
					analyser.setWorkSiteState(rs.getString("WorkSiteState")); // add
																				// WorkSiteState
					analyser.setAnalyzerCategory1(rs.getString("AnalyzerCategory1"));
					analyser.setAnalyzerCategory2(rs.getString("AnalyzerCategory2"));
					analyser.setFlsaStatus(rs.getString("FlsaStatus"));
					analyser.setFlsaReference(rs.getString("FlsaReferance"));
					analyser.setCustom1(rs.getString("Custom1")); // EXTRA
																	// COLUMN
					analyser.setBillingType(rs.getString("BillingType"));
					analyser.setDeliveryType(rs.getString("DeliveryType"));
					analyser.setPracticeArea(rs.getString("PracticeArea"));
					analyser.setParentId(rs.getString("Parentid"));
					analyser.setDealType(rs.getString("DealType")); // EXTRA
																	// COLUMN
					analyser.setComm(rs.getString("Comm")); // EXTRA
															// COLUMN
					analyser.setCustom2(rs.getString("Custom2")); // EXTRA
																	// COLUMN
					analyser.setIsBonusEligible(rs.getString("IsBonusEligible"));
					analyser.setBonusAmount(rs.getDouble("BonusAmount"));
					analyser.setBonusPercentage(rs.getString("BonusPercentage"));
					analyser.setPermGAAmount(rs.getDouble("PermGAAmount"));
					// ADDED EW FEILDS FOR COMMISSION
					analyser.setSplitCommissionPercentage1(rs.getString("SplitCommissionPercentage1"));
					analyser.setSplitCommissionPercentage2(rs.getString("SplitCommissionPercentage2"));
					analyser.setSplitCommissionPercentage3(rs.getString("SplitCommissionPercentage3"));
					analyser.setSplitCommissionPercentage4(rs.getString("SplitCommissionPercentage4"));
					analyser.setSkillCategory(rs.getString("SkillCategory"));
					analyser.setVertical(rs.getString("Vertical"));
					analyser.setEmployeeClass(rs.getString("EmployeeClass"));
					analyser.setVerticalTimesheetType(rs.getString("VerticalTimesheetType"));
					analyser.setImmigrationCost(rs.getDouble("ImmigrationCost")); // 20160627
					analyser.setEquipmentCost(rs.getDouble("EquipmentCost"));
					analyser.setProduct(rs.getString("Product"));
					analyser.setNonBillableCost(rs.getDouble("NonBillableCost"));
					analyser.setTravelRequired(rs.getString("TravelRequired"));
					analyser.setCommissionModel1(rs.getString("CommissionModel1"));
					analyser.setCommissionModel2(rs.getString("CommissionModel2"));
					analyser.setCommissionModel3(rs.getString("CommissionModel3"));
					analyser.setCommissionModel4(rs.getString("CommissionModel4"));
					analyser.setRecruitingClassification(rs.getString("RecruitingClassification")); // 20170330
					
					analyser.setSickLeaveCost(rs.getDouble("SickLeaveCost")); // 20171206
					analyser.setFalseTermination(rs.getString("FalseTermination"));
					analyser.setCommissionPerson5(rs.getString("CommissionPerson5"));
					analyser.setCommissionPerson6(rs.getString("CommissionPerson6"));
					analyser.setCommissionPerson7(rs.getString("CommissionPerson7"));
					analyser.setCommissionPerson8(rs.getString("CommissionPerson8"));
					analyser.setCommissionPerson9(rs.getString("CommissionPerson9"));
					analyser.setCommissionPercentage5(rs.getDouble("CommissionPercentage5"));
					analyser.setCommissionPercentage6(rs.getDouble("CommissionPercentage6"));
					analyser.setCommissionPercentage7(rs.getDouble("CommissionPercentage7"));
					analyser.setCommissionPercentage8(rs.getDouble("CommissionPercentage8"));
					analyser.setCommissionPercentage9(rs.getDouble("CommissionPercentage9"));
					analyser.setCommission5(rs.getDouble("Commission5"));
					analyser.setCommission6(rs.getDouble("Commission6"));
					analyser.setCommission7(rs.getDouble("Commission7"));
					analyser.setCommission8(rs.getDouble("Commission8"));
					analyser.setCommission9(rs.getDouble("Commission9"));
					analyser.setProjectCost(rs.getDouble("ProjectCost"));
					analyser.setSickLeavePerHourRate(rs.getDouble("SickLeavePerHourRate"));
					analyser.setSickLeaveCap(rs.getString("SickLeaveCap"));
					analyser.setManagingDirector(rs.getString("ManagingDirector"));
					
					// ---------- 16-08-2018 -------------------//
					analyser.setBillablePTO(rs.getDouble("BillablePTO"));
					analyser.setNonBillablePTO(rs.getDouble("NonBillablePTO"));
					analyser.setBillablePTOCost(rs.getDouble("BillablePTOCost"));
					analyser.setNonBillablePTOCost(rs.getDouble("NonBillablePTOCost"));
					analyser.setTotalHolidays(rs.getDouble("TotalHolidays"));
					analyser.setBillableHolidays(rs.getDouble("BillableHolidays"));
					analyser.setNonBillableHolidays(rs.getDouble("NonBillableHolidays"));
					analyser.setBillableHolidaysCost(rs.getDouble("BillableHolidaysCost"));
					analyser.setNonBillableHolidaysCost(rs.getDouble("NonBillableHolidaysCost"));
					
					analyser.setGrossProfitPercentage(rs.getDouble("GrossProfitPercentage"));
					analyser.setGrossProfit(rs.getDouble("GrossProfit"));
					analyser.setCommissionableProfit(rs.getDouble("CommissionableProfit"));
					analyser.setRejectionStatus(rs.getInt("RejectionStatus"));
					analyser.setRejectionReason(rs.getString("RejectionReason"));
					analyser.setRejectedBy(rs.getString("RejectedBy"));
					analyser.setRejectionDate(rs.getTimestamp("RejectionDate"));
					analyser.setGeoOffice(rs.getString("GeoOffice"));
					analyser.setpTOLimitToOverrideSick(rs.getDouble("PTOLimitToOverrideSick"));
					
					list.add(analyser);
				}
				else
				{
					analyser = null;
				}
			}
			return list.get(0);	
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return null;
		
	}
	
	public static void readFromPropertiesFileForCommission()
	{
		Properties prop = new Properties();
		InputStream input = null;
		try
		{
			
			String filename = PROPERTIES_FILE_NAME;
			input = DBConnection.class.getClassLoader().getResourceAsStream(filename);
			if (input == null)
			{
				System.out.println("Sorry, unable to find " + filename);
				return;
			}
			// load a properties file from class path, inside static method
			prop.load(input);
			// get the property value and print it out
			if (prop.getProperty("environment").equals(Constants.ENVIRONMENT_DEVELOPMENT))
			{
				driverClassNameCommission = prop.getProperty("development.driverClassName");
				urlCommission = prop.getProperty("development.url2");
				userNameCommission = prop.getProperty("development.username");
				passwordCommission = prop.getProperty("development.password");
			}
			else
			{	
				driverClassNameCommission = prop.getProperty("production.driverClassName");
				urlCommission = prop.getProperty("production.url2");
				userNameCommission = prop.getProperty("production.username");
				passwordCommission = prop.getProperty("production.password");
			}
		}
		catch (IOException ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			if (input != null)
			{
				try
				{
					input.close();
				}
				catch (IOException e)
				{
					e.printStackTrace();
				}
			}
		}
	}
	
	public static void readFromPropertiesFile()
	{
		Properties prop = new Properties();
		InputStream input = null;
		try
		{
			
			String filename = PROPERTIES_FILE_NAME;
			input = DBConnection.class.getClassLoader().getResourceAsStream(filename);
			if (input == null)
			{
				System.out.println("Sorry, unable to find " + filename);
				return;
			}
			// load a properties file from class path, inside static method
			prop.load(input);
			// get the property value and print it out
			if (prop.getProperty("environment").equals(Constants.ENVIRONMENT_DEVELOPMENT))
			{
				driverClassName = prop.getProperty("development.driverClassName");
				url = prop.getProperty("development.url");
				userName = prop.getProperty("development.username");
				password = prop.getProperty("development.password");
			}
			else
			{
				driverClassName = prop.getProperty("production.driverClassName");
				url = prop.getProperty("production.url");
				userName = prop.getProperty("production.username");
				password = prop.getProperty("production.password");
			}
			
			initPoolSize = Integer.parseInt(prop.getProperty("INITIAL_POOL_SIZE"));
			maxPoolSize = Integer.parseInt(prop.getProperty("MAX_POOL_SIZE"));
		}
		catch (IOException ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			if (input != null)
			{
				try
				{
					input.close();
				}
				catch (IOException e)
				{
					e.printStackTrace();
				}
			}
		}
	}
	
	public static Connection getStaticConnection()
	{
		Connection con = null;
		try
		{
			// LOCAL TEST
			System.out.println("LOCAL DATABASE SETTINGS CALLED");
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			String connectionString = "jdbc:sqlserver://localhost:1433;databaseName=Analyser;user=sa;password=Analyser@123";
			con = DriverManager.getConnection(connectionString);
			
		}
		catch (Exception e)
		{
			System.out.println("Error in getConnection under DBConnection" + e.getMessage());
			e.printStackTrace();
		}
		return con;
	}
	
	
	
	public static Connection getConnection()
	{
		Connection con = null;
		try
		{
			System.out.println("Inside DBConnection --> getConnection");
			System.out.println("ConnectionPool Size = "+connectionPool.getSize());
			System.out.println("connectionPoolCommission Size = "+connectionPoolCommission.getSize());
			// LOCAL TEST
			//System.out.println("--LOCAL DATABASE SETTINGS CALLED FOR TEMPDB");
//			DBConnection db = new DBConnection();
//			db.readFromPropertiesFile();
//			// Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//			Class.forName(db.getDriverClassName());
//			String connectionUrl = db.getUrl() + ";user=" + db.getUserName() + ";password=" + db.getPassword();
//			con = DriverManager.getConnection(connectionUrl);
			int retryNumber = 0;
			do{
				con = connectionPool.getConnection();
				System.out.println("Try number "+(++retryNumber)+" to get connection");
			}while (con == null);
			// PRODUCTION
			//System.out.println("--PRODUCTION DATABASE SETTINGS CALLED");
			
		}
		catch (Exception e)
		{
			System.out.println("Error in getConnection under DBConnection" + e.getMessage());
			e.printStackTrace();
		}
		return con;
	}
	
	public static Connection getConnectionFromCommission()
	{
		Connection con = null;
		try
		{
			System.out.println("Inside DBConnection --> getConnectionFromCommission");
			System.out.println("ConnectionPool Size = "+connectionPool.getSize());
			System.out.println("connectionPoolCommission Size = "+connectionPoolCommission.getSize());
			// LOCAL TEST
			//System.out.println("LOCAL DATABASE SETTINGS CALLED FOR Commission DB");
//			DBConnection db = new DBConnection();
//			db.readFromPropertiesFileForCommission();
//			Class.forName(db.getDriverClassName());
//			String connectionUrl = db.getUrl() + ";user=" + db.getUserName() + ";password=" + db.getPassword();
//			con = DriverManager.getConnection(connectionUrl);
			int retryNumber = 0;
			do{
				con = connectionPoolCommission.getConnection();
				System.out.println("Try number "+(++retryNumber)+" to get commission connection");
			}while (con == null);
			//con = connectionPoolCommission.getConnection();	//30/2019 commented
			
			//System.out.println("COMMISSION DATABASE SETTINGS CALLED");
			
		}
		catch (Exception e)
		{
			System.out.println("Error in getConnection under DBConnection" + e.getMessage());
			e.printStackTrace();
		}
		return con;
	}
	
	public static void closeConnection(Connection con)
	{
		System.out.println("Inside DBConnection --> closeConnection(Connection con)");
		connectionPool.releaseConnection(con);
	}
	
	public static void closeConnection(Connection con, Statement st, ResultSet rs)
	{
		System.out.println("Inside DBConnection --> closeConnection(Connection con, Statement st, ResultSet rs)");
		try
		{
			if (st != null)
			{
				st.close();
				st = null;
			}
			if (rs != null)
			{
				rs.close();
				rs = null;
			}
		}
		catch (Exception e)
		{
			System.err.println("ERROR Closing REssult Set and Statement Objects.)");
			e.printStackTrace();
		}

		connectionPool.releaseConnection(con);
	}
	
	public static void closeConnectionCommission(Connection con)
	{
		System.out.println("Inside DBConnection --> closeConnectionCommission(Connection con)");
		connectionPoolCommission.releaseConnection(con);
	}
	
	public static void closeConnectionCommission(Connection con, Statement st, ResultSet rs)
	{
		System.out.println("Inside DBConnection --> closeConnectionCommission(Connection con, Statement st, ResultSet rs)");
		connectionPoolCommission.releaseConnection(con);
	}
	
	public static void main(String[] args)
	{
		try
		{
			Connection connection = DBConnection.getConnection();
			
			// readFromUsers(connection);
			
			readUsersRolesAndTheirPrivileges(connection);
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
//		try
//		{
//			Connection connection = DBConnection.getStaticConnection();
//			
//			DBConnection.saveAnalyserLatest(connection);
//			
//		}
//		catch (Exception e)
//		{
//			e.printStackTrace();
//		}
	}
	
	private static List<Integer> getResourceIds(Connection connection)
	{
		// select RES_ID from Resources where RES_Desc like '%Report' order by
		// RES_ID;
		String Sql = "select *  from Resources where RES_Desc like '%Report%' and status = 1 and Parent_Id !=-1; ";
		Statement sta = null;
		ResultSet rs;
		List<Integer> ids = new ArrayList<Integer>();
		try
		{
			sta = connection.createStatement();
			rs = sta.executeQuery(Sql);
			
			if (rs != null)
			{
				while (rs.next())
				{
					ids.add(rs.getInt("RES_ID"));
				}
			}
		}
		catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("--List size is : "+ids.size());
		return ids;
	}
	
	private static Boolean isPrivilegeAlreadyAdded(Connection connection, Integer roleId, Integer resourceId){
		String Sql = "select count(*) as exist from Role_Privileges where ROL_ID = "+roleId+" and RES_ID = "+resourceId;
		Statement sta = null;
		ResultSet rs;
		Integer count = 0;
		try
		{
			sta = connection.createStatement();
			rs = sta.executeQuery(Sql);
			if (rs != null)
			{
				while (rs.next())
				{
					count = rs.getInt("exist");
				}
			}
			if(count == 0){
				return false;
			}else{
				return true;
			}
		}
		catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	private static void readUsersRolesAndTheirPrivileges(Connection connection)
	{
		
		List<Integer> ids = getResourceIds(connection);
		
		Statement sta;
		Statement newStatement;
		try
		{
			sta = connection.createStatement();
			String Sql = "select * from Roles";
			ResultSet rs = sta.executeQuery(Sql);
			Integer roleId = 0;
			while (rs.next())
			{
				roleId = rs.getInt("ROL_ID");
				System.out.println("--Role id is : " + roleId + " and role Name : " + rs.getString("Role_Desc"));
				String queryToGetRolePrivileges = "select * from Role_Privileges where ROL_ID = " + roleId;
				newStatement = connection.createStatement();
				ResultSet rsNew = newStatement.executeQuery(queryToGetRolePrivileges);
				System.out.println("------------------------------------");
				for(Integer id:ids){
					if(!isPrivilegeAlreadyAdded(connection,roleId,id)){
						System.out.println("INSERT INTO ROLE_PRIVILEGES VALUES("+roleId+","+id+");");
					}
				}
				//insert analyser subcontractor
//				if(!isPrivilegeAlreadyAdded(connection,roleId,469)){
//					System.out.println("INSERT INTO ROLE_PRIVILEGES VALUES("+roleId+",469);");
//				}
				
				//insert analyser clients
//				if(!isPrivilegeAlreadyAdded(connection,roleId,467)){
//					System.out.println("INSERT INTO ROLE_PRIVILEGES VALUES("+roleId+",467);");
//				}
				
				// insert Add Analyser
//				if(!isPrivilegeAlreadyAdded(connection,roleId,461)){
//					System.out.println("INSERT INTO ROLE_PRIVILEGES VALUES("+roleId+",461);");
//				}
				
				// insert Analyser List
//				if(!isPrivilegeAlreadyAdded(connection,roleId,462)){
//					System.out.println("INSERT INTO ROLE_PRIVILEGES VALUES("+roleId+",462);");
//				}

				//insert change password
//				if(!isPrivilegeAlreadyAdded(connection,roleId,485)){
//					System.out.println("INSERT INTO ROLE_PRIVILEGES VALUES("+roleId+",485);");
//				}
				
//				while (rsNew.next())
//				{
//					System.out.println("Role id is : " + roleId + " and resource id : " + rsNew.getInt("RES_ID"));
//				}
				System.out.println("------------------------------------");
			}
			System.out.println("--Done with the connection---");
			DBConnection.closeConnection(connection, sta, rs);
		}
		catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private static void readFromUsers(Connection connection)
	{
		
		Statement sta;
		try
		{
			sta = connection.createStatement();
			String Sql = "select * from Users";
			ResultSet rs = sta.executeQuery(Sql);
			while (rs.next())
			{
				System.out.println(rs.getString("First_Name"));
			}
			System.out.println("Done with the connection---");
			DBConnection.closeConnection(connection, sta, rs);
		}
		catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public String getDriverClassName()
	{
		return driverClassName;
	}
	
	public String getUrl()
	{
		return url;
	}
	
	public String getUserName()
	{
		return userName;
	}
	
	public String getPassword()
	{
		return password;
	}
	
	/**
	 * @return the urlCommission
	 */
	public static String getUrlCommission()
	{
		return urlCommission;
	}

	/**
	 * @return the userNameCommission
	 */
	public static String getUserNameCommission()
	{
		return userNameCommission;
	}

	/**
	 * @return the passwordCommission
	 */
	public static String getPasswordCommission()
	{
		return passwordCommission;
	}

	/**
	 * @return the driverClassNameCommission
	 */
	public static String getDriverClassNameCommission()
	{
		return driverClassNameCommission;
	}
	
}
