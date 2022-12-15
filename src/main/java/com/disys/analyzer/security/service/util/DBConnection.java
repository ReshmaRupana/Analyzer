/**
 * 
 */
package com.disys.analyzer.security.service.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.Diff;
import org.apache.commons.lang3.builder.DiffResult;

import com.disys.analyzer.config.util.FacesUtils;
import com.disys.analyzer.model.dto.AnalyserDTO;

/**
 * @author Sajid
 * 
 */
public class DBConnection
{
	
	public static void main(String[] args)
	{
		// example class
		// callRehireFunction();
		
		// saveUpdateAnalyser();
		
		String flsa = "111-11-1111";
		String ssn = "111-12-1212";
		// getDuplicateFLSA(flsa);
		/*
		 * Integer count = getDupliacateSSN(ssn); if(count == 0){
		 * System.out.println("Not Duplicate"); }else {
		 * System.out.println("Duplicate"); }
		 */
		
		// String respones = addUpdateAnalyser();
		// System.out.println("Respones is : " + respones);
		readFromCodeFile();
	}
	
	public static Integer getDupliacateSSN(String ssn)
	{
		Connection con = null;
		CallableStatement callStmt = null;
		ResultSet rs = null;
		Integer count = 0;
		try
		{
			con = DBConnection.getConnection();
			if (con != null)
			{
				System.out.println("Connection established successfully...");
				String query = null;
				
				query = "{call " + FacesUtils.SCHEMA_WIRELESS + ".spGetDuplicateSSN('" + ssn + "')}";
				callStmt = con.prepareCall(query);
				
				rs = callStmt.executeQuery();
				
				if (rs != null)
				{
					if (rs.next())
					{
						count = rs.getInt("duplicate");
						System.out.println("Count is : " + count);
					}
				}
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			if (con != null) try
			{
				con.close();
				System.out.println("Connection closed successfully...");
			}
			catch (Exception e)
			{
			}
		}
		return count;
	}
	
	public static void callGenericDescription()
	{
		Connection con = null;
		try
		{
			con = DBConnection.getConnection();
			if (con != null)
			{
				System.out.println("Connection established successfully...");
				
				String type = "WORKSITESTATE";
				String param1 = "20655";
				String param2 = "";
				
				String respones = getGenericDescription(type, param1, param2);
				System.out.println("Respones is  : " + respones);
				
				/*
				 * String respones = addUpdateAnalyser();
				 * System.out.println("Respones is  : " + respones);
				 */
				
				AnalyserDTO an = getAnalyserInfo("218723");
				System.out.println("----- Analyser Info -----");
				
				System.out.println("Analyser Address 1 : " + an.getAddress1());
				System.out.println("Analyser Address 2 : " + an.getAddress2());
				System.out.println("Analyser Attention : " + an.getAttention());
				
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			if (con != null) try
			{
				con.close();
				System.out.println("Connection closed successfully...");
			}
			catch (Exception e)
			{
			}
		}
	}
	
	public static void callDifference()
	{
		try
		{
			List<AnalyserDTO> list = getAnalyserHistory("Gregory.Armstrong@DISYS.COM", "218663", "null", "ALL");
			AnalyserDTO currentAnlayser;
			AnalyserDTO oldAnalyser;
			
			if (list.size() > 1)
			{
				oldAnalyser = list.get(0);
				currentAnlayser = list.get(1);
				
				DiffResult diff = oldAnalyser.diff(currentAnlayser);
				for (Diff<?> d : diff.getDiffs())
				{
					System.out.println(d.getFieldName() + "= FROM[" + d.getLeft() + "] TO [" + d.getRight() + "]");
				}
			}
			else
			{
				
				oldAnalyser = list.get(0);
				currentAnlayser = new AnalyserDTO();
				
				DiffResult diff = oldAnalyser.diff(currentAnlayser);
				for (Diff<?> d : diff.getDiffs())
				{
					System.out.println(d.getFieldName() + "= FROM[" + d.getLeft() + "] TO [" + d.getRight() + "]");
				}
				
				System.out.println("History is just 1 record old, nothing to compare");
			}
			
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
	}
	
	public static void saveUpdateAnalyser()
	{
		String respones = addUpdateAnalyser();
		System.out.println("Respones is  : " + respones);
	}
	
	public static void callRehireFunction()
	{
		// DBConnection obj = new DBConnection();
		boolean res = isItemPendingForUser(218736, "REHIRE");
		System.out.println("Result is  : " + res);
	}
	
	public static Connection getConnection()
	{
		Connection con = null;
		try
		{
			// LOCAL TEST
			System.out.println("LOCAL DATABASE SETTINGS CALLED");
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			String connectionString = "jdbc:sqlserver://localhost:1433;databaseName=Analyser;user=sa;password=Analyser@123";
			// String connectionString =
			// "jdbc:sqlserver://DISYS-DEVO1;databaseName=Analyser;user=analyzer;password=Disys@99";
			con = DriverManager.getConnection(connectionString);
			
		}
		catch (Exception e)
		{
			System.out.println("Error in getConnection under DBConnection" + e.getMessage());
			e.printStackTrace();
		}
		return con;
	}
	
	public String approveAnalyser(String analyserId, String userId)
	{
		Connection con = null;
		CallableStatement callStmt = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		String result = "0";
		String query = null;
		
		try
		{
			con = DBConnection.getConnection();
			
			if (con != null)
			{
				query = "{call " + FacesUtils.SCHEMA_WIRELESS + ".spApproveAnalyser(" + analyserId + ",'" + userId + "')}";
				callStmt = con.prepareCall(query);
				System.out.println("Executing Query in approveAnalyser of " + "CustomerSessionEJBBean :  " + query);
				rs = callStmt.executeQuery();
				
				if (rs != null)
				{
					if (rs.next())
					{
						result = rs.getString(1);
					}
					
				}
				else
				{
					result = "1";
				}
				con.close();
				// pstmt.close();
				callStmt.close();
				if (rs != null)
				{
					rs.close();
				}
				con = null;
				pstmt = null;
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
			System.out.println("Exception in approveAnalyser method of CustomerSessionEJBBean");
			ex.printStackTrace();
			result = "1";
		}
		finally
		{
			DBConnection.closeConnection(con, pstmt, rs);
		}
		return result;
	}
	
	public static void closeConnection(Connection con, Statement st, ResultSet rs)
	{
		try
		{
			if (con != null)
			{
				con.close();
			}
			if (st != null)
			{
				st.close();
			}
			if (rs != null)
			{
				rs.close();
			}
		}
		catch (Exception conEx)
		{
		}
		finally
		{
			con = null;
			st = null;
			rs = null;
		}
	}
	
	public static String getGenericDescription(String type, String param1, String param2)
	{
		Connection con = null;
		CallableStatement callStmt = null;
		ResultSet rs = null;
		String result = "";
		
		try
		{
			con = DBConnection.getConnection();
			if (con != null)
			{
				String query = null;
				
				System.out.println("DBHELPER --> getGenericDescription Started");
				query = "{call " + FacesUtils.SCHEMA_WIRELESS + ".getGenericDescription('" + type + "','" + param1 + "','" + param2 + "')}";
				
				System.out.println("Executing Query in getGenericDescription :  " + query);
				callStmt = con.prepareCall(query);
				rs = callStmt.executeQuery();
				
				if (rs != null)
				{
					if (rs.next())
					{
						result = rs.getString("Result");
					}
				}
			}
			else
			{
				throw new Exception("Connection is null in getGenericDescription");
			}
		}
		catch (Exception ex)
		{
			System.out.println("Exception in getGenericDescription method of DBHelper");
			ex.printStackTrace();
		}
		finally
		{
			DBConnection.closeConnection(con, callStmt, rs);
		}
		System.out.println("Return result = " + result);
		System.out.println("DBHELPER --> getGenericDescription Ended.");
		return result;
	}
	
	public static String addUpdateAnalyser()
	{
		Connection con = null;
		CallableStatement callStmt = null;
		ResultSet rs = null;
		String result = "0";
		try
		{
			con = DBConnection.getConnection();
			
			StringBuilder query = new StringBuilder();
			query.append("{call " + FacesUtils.SCHEMA_WIRELESS + ".spAddUpdateAnalyser ( ");
			
			Integer intActionType = 1;
			query.append("" + intActionType + ",");
			
			String varLoggedUserID = "Gregory.Armstrong@DISYS.COM";
			query.append("'" + varLoggedUserID + "',");
			
			Integer intAnalyserID = 0;
			query.append("" + intAnalyserID + ",");
			
			String varLName = "Sajid";
			query.append("'" + varLName + "',");
			
			String varFName = "Sajid";
			query.append("'" + varFName + "',");
			
			String varEmpType = "w2";
			query.append("'" + varEmpType + "',");
			
			String varAddress1 = "Test";
			query.append("'" + varAddress1 + "',");
			
			String varAddress2 = "Address";
			query.append("'" + varAddress2 + "',");
			
			String varCity = "Test City";
			query.append("'" + varCity + "',");
			
			String varState = "AL";
			query.append("'" + varState + "',");
			
			String varZip = "12312-3123";
			query.append("'" + varZip + "',");
			
			String varTel = "(123) 123-1231";
			query.append("'" + varTel + "',");
			
			String varWokphone = "(234) 234-2342";
			query.append("'" + varWokphone + "',");
			
			String varFax = "(123) 123-2131";
			query.append("'" + varFax + "',");
			
			String varEmail = "test@testing.com";
			query.append("'" + varEmail + "',");
			
			String varSsn = "324-23-4234";
			query.append("'" + varSsn + "',");
			
			String varDob = "11/23/1988";
			query.append("'" + varDob + "',");
			
			String varJobTitle = "SS";
			query.append("'" + varJobTitle + "',");
			
			String varStartDate = "11/13/2017";
			query.append("'" + varStartDate + "',");
			
			String varEndDate = "12/31/2020";
			query.append("'" + varEndDate + "',");
			
			Integer intClientId = 0;
			query.append("" + intClientId + ",");
			
			Integer ClientAddressId = 9985;
			query.append("" + ClientAddressId + ",");
			
			Integer ClientSiteId = 20655;
			query.append("" + ClientSiteId + ",");
			
			String varContractorId = "7880";
			query.append("'" + varContractorId + "',");
			
			String varCommName1 = "Aaron Bessett";
			query.append("'" + varCommName1 + "',");
			
			String varPer1 = "0";
			query.append("'" + varPer1 + "',");
			
			String varCommName2 = "Aaron Rontal";
			query.append("'" + varCommName2 + "',");
			
			String varPer2 = "Aaron Silcox";
			query.append("'" + varPer2 + "',");
			
			String varCommName3 = "0";
			query.append("'" + varCommName3 + "',");
			
			String varPer3 = "0";
			query.append("'" + varPer3 + "',");
			
			String varCommName4 = "";
			query.append("'" + varCommName4 + "',");
			
			String varPer4 = "0";
			query.append("'" + varPer4 + "',");
			
			String varCommName5 = "null";
			query.append("'" + varCommName5 + "',");
			
			String varPer5 = "0";
			query.append("'" + varPer5 + "',");
			
			String varTHW = "2080.0";
			query.append("'" + varTHW + "',");
			
			String varBenefitsAmount = "0";
			query.append("'" + varBenefitsAmount + "',");
			
			String varPercent401k = "0";
			query.append("'" + varPercent401k + "',");
			
			String varEducation = "null";
			query.append("'" + varEducation + "',");
			
			String varLeave = "0";
			query.append("'" + varLeave + "',");
			
			String varOtherDollar = "0";
			query.append("'" + varOtherDollar + "',");
			
			String varOtherAmounts = "0";
			query.append("'" + varOtherAmounts + "',");
			
			String varDiscount = "0";
			query.append("'" + varDiscount + "',");
			
			String varBillRate = "12.0";
			query.append("'" + varBillRate + "',");
			
			String varHealth = "0";
			query.append("'" + varHealth + "',");
			
			String vark401 = "0";
			query.append("'" + vark401 + "',");
			
			String varTax = "0";
			query.append("'" + varTax + "',");
			
			String varPayRate = "25.0";
			query.append("'" + varPayRate + "',");
			
			String varGA = "0";
			query.append("'" + varGA + "',");
			
			String varCommission = "0.0";
			query.append("'" + varCommission + "',");
			
			String varSpreadWeek = "-3885.05";
			query.append("'" + varSpreadWeek + "',");
			
			String varOtb = "0";
			query.append("'" + varOtb + "',");
			
			String varOneTimeAmount = "0";
			query.append("'" + varOneTimeAmount + "',");
			
			String varComments = "Testing commments.";
			query.append("'" + varComments + "',");
			
			String varDiscountRate = "0";
			query.append("'" + varDiscountRate + "',");
			
			String varEffectiveDate = "01/02/2018";
			query.append("'" + varEffectiveDate + "',");
			
			String varProfit = "-97.13";
			query.append("'" + varProfit + "',");
			
			String varTermDate = "";
			query.append("'" + varTermDate + "',");
			
			String varReason = "";
			query.append("'" + varReason + "',");
			
			String varBranch = "FNA";
			query.append("'" + varBranch + "',");
			
			String varPto = "";
			query.append("'" + varPto + "',");
			
			String varCurrency = "USD";
			query.append("'" + varCurrency + "',");
			
			String varMob_Pager = "(234) 234-5234";
			query.append("'" + varMob_Pager + "',");
			
			String varInitial = "U";
			query.append("'" + varInitial + "',");
			
			String varMrmrs = "--";
			query.append("'" + varMrmrs + "',");
			
			String varJrsr = "--";
			query.append("'" + varJrsr + "',");
			
			String varCommision1 = "0";
			query.append("'" + varCommision1 + "',");
			
			String varCommision2 = "0";
			query.append("'" + varCommision2 + "',");
			
			String varCommision3 = "0";
			query.append("'" + varCommision3 + "',");
			
			String varCommision4 = "0";
			query.append("'" + varCommision4 + "',");
			
			String varCommEffDate = "01/01/2018";
			query.append("'" + varCommEffDate + "',");
			
			String varTemps = "FA";
			query.append("'" + varTemps + "',");
			
			String varAnnualPay = "200000";
			query.append("'" + varAnnualPay + "',");
			
			String varContractVehicle = "";
			query.append("'" + varContractVehicle + "',");
			
			Integer intRecordStatus = 1;
			query.append("" + intRecordStatus + ",");
			
			String varLiaisonName = "Ariel Dobbins";
			query.append("'" + varLiaisonName + "',");
			
			Integer intReferences = 1;
			query.append("" + intReferences + ",");
			
			Integer intH1 = 0;
			query.append("" + intH1 + ",");
			
			String varJobCategory = "Administration";
			query.append("'" + varJobCategory + "',");
			
			String execOrphan = "0";
			query.append("'" + execOrphan + "',");
			
			String recruiterOrphan = "0";
			query.append("'" + recruiterOrphan + "',");
			
			String other1Orphan = "0";
			query.append("'" + other1Orphan + "',");
			
			String other2Orphan = "0";
			query.append("'" + other2Orphan + "',");
			
			String varJobPayRate = "0";
			query.append("'" + varJobPayRate + "',");
			
			String varJobBillRate = "0";
			query.append("'" + varJobBillRate + "',");
			
			String varPoNumber = "--";
			query.append("'" + varPoNumber + "',");
			
			String varDoubleTime = "0";
			query.append("'" + varDoubleTime + "',");
			
			String varDoubleTimeBill = "0";
			query.append("'" + varDoubleTimeBill + "',");
			
			String varPerdiem = "0";
			query.append("'" + varPerdiem + "',");
			
			String varWorkEmail = "test@testmail.com";
			query.append("'" + varWorkEmail + "',");
			
			String varChkReferralFee = "false";
			query.append("'" + varChkReferralFee + "',");
			
			String varReferralFeeAmount = "0";
			query.append("'" + varReferralFeeAmount + "',");
			
			String varDentalInsurance = "N";
			query.append("'" + varDentalInsurance + "',");
			
			String varStdBenefit = "Y";
			query.append("'" + varStdBenefit + "',");
			
			String varLtdBenefit = "N";
			query.append("'" + varLtdBenefit + "',");
			
			String varConsultantJobBoard = "Career Builder";
			query.append("'" + varConsultantJobBoard + "',");
			
			String varDentalInsuranceAmount = "0.0";
			query.append("'" + varDentalInsuranceAmount + "',");
			
			String varUnEmployedForTwoMonths = "N";
			query.append("'" + varUnEmployedForTwoMonths + "',");
			
			String varEmployeeVeteran = "Y";
			query.append("'" + varEmployeeVeteran + "',");
			
			String gender = "M";
			query.append("'" + gender + "',");
			
			String WorkSiteState = "null";
			query.append("'" + WorkSiteState + "',");
			
			String AnalyzerCategory1 = "NDT";
			query.append("'" + AnalyzerCategory1 + "',");
			
			String AnalyzerCategory2 = "GSOC";
			query.append("'" + AnalyzerCategory2 + "',");
			
			String FLSAStatus = "ES";
			query.append("'" + FLSAStatus + "',");
			
			String FLSAReferance = "ST";
			query.append("'" + FLSAReferance + "',");
			
			String Custom1 = "--";
			query.append("'" + Custom1 + "',");
			
			String Com = "0";
			query.append("'" + Com + "',");
			
			String Custom2 = "--";
			query.append("'" + Custom2 + "',");
			
			String Comm = "null";
			query.append("'" + Comm + "',");
			
			String DealType = "ITA";
			query.append("'" + DealType + "',");
			
			String IsBonusEligible = "N";
			query.append("'" + IsBonusEligible + "',");
			
			double BonusAmount = 0.0;
			query.append("" + BonusAmount + ",");
			
			String BonusPercentage = "";
			query.append("'" + BonusPercentage + "',");
			
			double PermGAAmount = 0.0;
			query.append("" + PermGAAmount + ",");
			
			String splitCommissionPercentage1 = "75";
			query.append("'" + splitCommissionPercentage1 + "',");
			
			String splitCommissionPercentage2 = "0";
			query.append("'" + splitCommissionPercentage2 + "',");
			
			String splitCommissionPercentage3 = "25";
			query.append("'" + splitCommissionPercentage3 + "',");
			
			String splitCommissionPercentage4 = "0";
			query.append("'" + splitCommissionPercentage4 + "',");
			
			String skillCategory = "AppsQA";
			query.append("'" + skillCategory + "',");
			
			String vertical = "Finance and Accounting";
			query.append("'" + vertical + "',");
			
			String employeeClass = "FUB";
			query.append("'" + employeeClass + "',");
			
			String verticalTimesheetType = "--";
			query.append("'" + verticalTimesheetType + "',");
			
			double ImmigrationCost = 0.0;
			query.append("" + ImmigrationCost + ",");
			
			double EquipmentCost = 0.0;
			query.append("" + EquipmentCost + ",");
			
			String Product = "PERM";
			query.append("'" + Product + "',");
			
			double NonBillableCost = 0.0;
			query.append("" + NonBillableCost + ",");
			
			String TravelRequired = "Yes";
			query.append("'" + TravelRequired + "',");
			
			String CommissionModel1 = "F&A";
			query.append("'" + CommissionModel1 + "',");
			
			String CommissionModel2 = "IRC";
			query.append("'" + CommissionModel2 + "',");
			
			String CommissionModel3 = "F&A";
			query.append("'" + CommissionModel3 + "',");
			
			String CommissionModel4 = "";
			query.append("'" + CommissionModel4 + "',");
			
			String RecruitingClassification = "Vertical";
			query.append("'" + RecruitingClassification + "',");
			
			Integer intPermanentPlacement = 1;
			query.append("" + intPermanentPlacement + ",");
			
			Integer varPlacementAmount = 0;
			query.append("" + varPlacementAmount + ",");
			
			Integer varSalaryAmount = 0;
			query.append("" + varSalaryAmount + ",");
			
			Integer varPlacementPercentage = 0;
			query.append("" + varPlacementPercentage + ",");
			
			String varPlacementDate = "";
			query.append("'" + varPlacementDate + "')}");
			
			// -- below are not part of the parameters
			
			/*
			 * String intDummy = null; query.append("'"+intDummy+"',");
			 * 
			 * String intDummy1 =null; query.append("'"+intDummy1+"',");
			 * 
			 * String execOrphanInter = null;
			 * query.append("'"+execOrphanInter+"',");
			 * 
			 * String varBranchInter = null;
			 * query.append("'"+varBranchInter+"',");
			 * 
			 * String varCommentsInter = null;
			 * query.append("'"+varCommentsInter+"',");
			 * 
			 * String varCommName1Inter = null;
			 * query.append("'"+varCommName1Inter+"',");
			 * 
			 * String varPer1Inter = null; query.append("'"+varPer1Inter+"',");
			 * 
			 * String varCommName2Inter = null;
			 * query.append("'"+varCommName2Inter+"',");
			 * 
			 * String varPer2Inter = null; query.append("'"+varPer2Inter+"',");
			 * 
			 * String varCommName3Inter = null;
			 * query.append("'"+varCommName3Inter+"',");
			 * 
			 * 
			 * String varPer3Inter = null; query.append("'"+varPer3Inter+"',");
			 * 
			 * String varCommName4Inter = null;
			 * query.append("'"+varCommName4Inter+"',");
			 * 
			 * String varPer4Inter = null; query.append("'"+varPer4Inter+"',");
			 * 
			 * String varCommision1Inter = null;
			 * query.append("'"+varCommision1Inter+"',");
			 * 
			 * String varCommision2Inter = null;
			 * query.append("'"+varCommision2Inter+"',");
			 * 
			 * String varCommision3Inter = null;
			 * query.append("'"+varCommision3Inter+"',");
			 * 
			 * String varCommision4Inter = null;
			 * query.append("'"+varCommision4Inter+"',");
			 * 
			 * 
			 * int intSplit = 0; query.append(""+intSplit+",");
			 * 
			 * String varCountry = "USA"; query.append("'"+varCountry+"',");
			 * 
			 * String bitInternational = "1";
			 * query.append("'"+bitInternational+"',");
			 * 
			 * String other1OrphanInter = null;
			 * query.append("'"+other1OrphanInter+"',");
			 * 
			 * String other2OrphanInter = null;
			 * query.append("'"+other2OrphanInter+"',");
			 * 
			 * String recruiterOrphanInter = null;
			 * query.append("'"+recruiterOrphanInter+"')}");
			 */
			
			if (con != null)
			{
				
				String queryFromServer = "{call " + FacesUtils.SCHEMA_WIRELESS + ".spAddUpdateAnalyser "
				        + "(1,'Gregory.Armstrong@DISYS.COM',0,'Jason','Madden','1099','Laudefort','Port Vienna','Louisvalle','AK','abb12-3123', "
				        + "'(123)-334-5534','(123)-444-5678','','jason.m@yahoo.com','123-12-3123','10/01/1991','Software Developer','11/07/2017', "
				        + "'11/01/2018','502717','9101','13664','7880','Aaron Bessett','0','Aaron Rontal','0','','0','','0','null','','1000','0', "
				        + "'0','null','0','0','0','0','0','0','0','0','0','0','0','0','','','','0','11/07/2017','0','','','FNA','0','USD','','J', "
				        + "'null','null','0','0','0','0','11/17/2017','FA','0','',1,'Ariel Dobbins',1,1,'Development','0','0','0','0','25','10', "
				        + "'null','20','25','0','jason.madden@tee.com','null','0','Y','Y','null','Career Builder','0.0','N','No','M','null','NDT', "
				        + "'GSOC','','','null','1','null','null','ITA','null','0.0','','0.0','100','100','','0','Functional', "
				        + "'Finance and Accounting','FUB','null','0.0','0.0','STAFF AUG','0.0','Yes','F&A','IRC','','','Cadre','null','0.0','0', "
				        + "null,null,null,'')}";
				
				String querySystem = "";
				
				querySystem = "{call " + FacesUtils.SCHEMA_WIRELESS + ".spAddUpdateAnalyser(1,'Gregory.Armstrong@DISYS.COM',0,'Sajid','Sajid','w2',"
				        + "'Test','Address','Test City','AL','12312-3123','(141) 341-4514','(151) 451-2351',"
				        + "'(123) 415-1451','test@testing.com','123-12-3512','06/21/1994','SS','11/30/2017',"
				        + "'03/30/2018','null','9985','20655','null','Aaron Bessett','0','Aaron Rontal','0',"
				        + "'Aaron Silcox','0','','0','null','null','1500.0','0','0','null','null','0','0','0',"
				        + "'12.0','null','0','0','48.0','null','0.0','-1731.92','0','0','ETst','0','01/03/2018',"
				        + "'-43.3','null','','FNA','10','USD','(145) 151-2352','U','--','--','null','null','null',"
				        + "'null','01/01/2018','FA','0','',1,'Ariel Dobbins',0,0,'Bidding','0','0','0','0','0','0',"
				        + "'--','0','0','0','test@testmail.com','false','120','true','false','true','Career Builder',"
				        + "'null','Y','Y','Male','null','NDT','GSOC','EH','SST','--','0','--','null','ITB','false',"
				        + "'null','','0.0','75','0','25','0','Functional','Finance and Accounting','FUB','--','0.0',"
				        + "'0.0','PERM','0.0','Yes','F&A','IRC','F&A','','Vertical',1,null,null,null,'')}";
				
				String queryFailed = "";
				
				queryFailed = "{call " + FacesUtils.SCHEMA_WIRELESS + ".spAddUpdateAnalyser(1,'Gregory.Armstrong@DISYS.COM',0,'Sajid','Sajid','w2',"
				        + "'Dubai','','Dubai','AK','55556-6666',"
				        + "'','','','Sajid@data2.com','551-66-6555','07/25/1978','Assistant','11/25/2017','02/28/2018'," + "502286,8963,17841,'',"
				        + "'Abby Frane','0','Aaron Silcox','0','','0','','0','null','0'," + "'1900.0','0','0','null','0.0','0','0','0','65.0',"
				        + "'0','0','0','28.0','0.0','0.0','1076.5','0','0'," + "'This is my first comment.','0','11/25/2017','26.91','','',"
				        + "'Arizona','','USD','','Y','--','--','0','0','0','0','11/30/2017'," + "'IT','0','',1,'Candace Childs',1,1,'Administration',"
				        + "'0','0','0','0','0','0','--','0','0','0','Sajid@beta.com'," + "'Y','1000','Y','Y','Y','MH Database',"
				        + "'0.0','Y','Y','M','','DSS','GSOC','EH','Asset','--'," + "'1','--','','ITA','Y',0,'',0,'100','25','0',"
				        + "'0','Infrastructure','Banking','FUB','--',1225,150,'STAFF AUG'," + "150,'Yes','ITC','CDR','','',"
				        + "'Vertical',1,'null','null','null','','',''," + "1,2,3,4,5,6,7,8,9,0,1,12,13,"
				        + "'Michael Rutkowski','A','B','C','D','E')}";
				
				// queryFailed = "{call
				// wireless.spAddUpdateAnalyser(1,'Gregory.Armstrong@DISYS.COM',0,'Haq','Tehseen','w2','855
				// Amherst Way','','Mountain
				// View','CA','94035-','','','','tehseen@alpha.com','151-23-5325','06/06/1986','Accountant','03/25/2018','04/07/2018',503214,10064,18782,'','Aaron
				// Rontal','3','','0','','0','','0','Kristin
				// Trombulak','0.0','2000.0','0','0','null','0.0','0','0','','120.0','0','0','0','42.0','0.0','0.0','0.0','0','0','testing
				// comments.','0.0','03/25/2018','0.0','','','Florida','10','USD','(135)
				// 132-5132','H','--','--','0','0','0','0','03/31/2018','IT','0','',1,'Chelsea
				// Brown',0,0,'Administration','0','0','0','0','0.0','0.0','--','0','0','0.0','','N','0.0','Y','N','N','Free
				// Boards','0.0','N','N','M','','IDT','GSOC','EH','test123123','--','0','--','null','ITB','N',0.0,'',0.0,'100','0','0','0','Infrastructure','Energy','FUB','--',500.0,0.0,'SERVICES',0.0,'Yes','ITA','','','','Vertical',0.0,'false','Kristin
				// Trombulak','','','','',0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,'0','Geoffrey
				// Goodson','null','null','null','null','Y','2018-03-25
				// 23:38:39.92','1','null','null','null','')}";
				queryFailed = "{call wireless.spAddUpdateAnalyser(1,'Gregory.Armstrong@DISYS.COM',0,'Singh','Yuvraj','w2',"
				        + "'Dehli 6','','Indore','CA','44624-6243',"
				        + "'','','','yuvi@yahoo.com','541-66-6559','08/11/2000','Batsman','04/06/2018','05/03/2018'," + "503214,10064,18782,'',"
				        + "'Aaron Rontal','0','Abdullah Lawal','0','','0','10.10','0','Michele McGee','0.0',"
				        + "'2080.0','0','0','null','0.0','0','0','','40.0'," + "'0','0','0','96.15','0.0','0.0','0.0','0','0',"
				        + "'test','0.0','04/06/2018','0.0','',''," + "'Florida','10','USD','','Y','--','--','0','0','0','0','04/24/2018',"
				        + "'IT','200000','',1,'Charity Corkins',0,0,'Administration',"
				        + "'0','0','0','0','0.0','0.0','--','0','0','0.0','yuvi@alpha.com'," + "'N','0.0','N','N','Y','MH Database',"
				        + "'0.0','N','N','M','','DSS','GSOC','ES','666666','--'," + "'1','--','null','ITA','Y','0.0','10','0.0','100','100','',"
				        + "'0','AppsQA','Energy','PUB','--',0.0,0.0,'SERVICES'," + "0.0,'No','ITA','ITA','','',"
				        + "'IRC','0.0','false','Michele McGee','Shabbir Alibhai','','',''," + "0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0334,40,"
				        + "'Alan Wilhite','A','B','C','D','E'," + "'04/06/2018','1','null','null','null','04/06/2018')}";
				
				// queryFailed = "{call
				// wireless.spAddUpdateAnalyser(1,'Gregory.Armstrong@DISYS.COM',0,'Singh','Yuvraj','w2','Dehli
				// 6','','Indore','CA','46246-4236','(432) 643-2624','(462)
				// 462-4362','','yuvi@yahoo.com','551-66-6559','04/30/2018','Accountant','04/06/2018','05/03/2018','503363','10388','19998','','Aaron
				// Bessett','0','','0','','0','','0','David
				// Marler','0.0','2080.0','0','0','null','0.0','0','50','','40.0','0','0','0','96.15','0.0','0.0','0.0','0','0','I
				// am testing it for the last
				// time.','0.0','04/06/2018','0.0','','','Arizona','15','USD','(111)
				// 111-1111','Y','--','--','0','0','0','0','05/04/2018','IT','200000','',1,'Ariel
				// Dobbins',1,1,'Administration','0','0','0','0','0.0','0.0','--','0','0','0.0','yuvi@alpha.com','Y','0.0','Y','N','Y','Linkedin','0.0','Y','N','M','','DSS','GSOC','ES','666666','--','1','--','null','ITB','Y','0.0','7','0.0','100','0','','0','Infrastructure','Banking','PUB','--',500.0,400.0,'SERVICES',0.0,'Yes','ITA','','','','Vertical',0.0,'false','David
				// Marler','David Sufrinko','Chantz Duncan','Jack
				// Gorsuch','Cydney
				// Peyton',0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0334,40,'Ash
				// Srivastava','','null','null','null','N','null','1','null','','null','','null','','')}";
				
				// queryFailed = "{call
				// wireless.spAddUpdateAnalyser(1,'Gregory.Armstrong@DISYS.COM',0,'Singh','Yuvi','w2','agf','afdadf','adfadf','AK','15134-3211','(135)
				// 321-4132','','','shakeel@alpha.com','501-66-6558','04/25/2018','Accountant','04/06/2018','04/25/2018','503201','10051','18777','','Aaron
				// Rontal','0','','0','','0','','0','David
				// Marler','0.0','2080.0','0','0','null','0.0','0','0','','20.0','0','0',70.70,'96.15',30.30,40.40,50.50,'0','0','another
				// comment.','0.0','04/06/2018',21.21,'','','Florida','10','USD','(234)
				// 623-4623','Y','--','--',13.13,13.13,13.13,13.13,'05/05/2018','IT','200000','',1,'Chelsea
				// Brown',1,1,'Administration','0','1','1','1','0.0','0.0','--','0','0','0.0','yuvi@alpha.com','N','0.0','N','Y','Y','Career
				// Builder',0.0,'Y','Y','M','','NDT','GSOC','ES','666666','--','1','--','null','ITA','Y','0.0','7','0.0','100','0','','0','AppsQA','Banking','PUB','--',0.0,0.0,'SERVICES',0.0,'Yes','ITA','','','','Cadre',0.0,'false','David
				// Marler','Jody Smith','Aries Webb-Williams','Niraj
				// Gupta','Andrea
				// Bell',0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0334,40,'Alan
				// Wilhite',NULL,NULL,NULL,NULL,'N','null','1','111.15','100.10','null','N')}";
				
				// System.out.println("Query is : "+query.toString());
				
				/*
				 * Query to rehire analyser 03-08-2018
				 * 
				 */
				String userId = "Gregory.Armstrong@DISYS.COM";
				Integer intAnalyserId = 226210;
				queryFailed = "{call " + FacesUtils.SCHEMA_WIRELESS + ".spAddUpdateAnalyser(" + 5 + ",'" + userId + "'," + intAnalyserId
				        + ",'','','null','','','','null','null','null','null',"
				        + "'null','null','null','null','','null','null','0','0','0','null','null','null','null','null',"
				        + "'null','null','null','null','null','null','null','0','0','null','null','null','null','null','null'"
				        + ",'null','0','0','null','null','null','null','null','null','','null','null','null','null','','null',"
				        + "'0','USD','null','null','null','null','null','null','null','null','null','null','null','null',1,'null',"
				        + "0,0,'null','0','0','0','0','null','null','null','null','null','null','','null','null','null','null','null',"
				        + "'null','0.0','null','null','null','null','null','null','null','null','null','0','null','null','null','null',"
				        + "'0.0','null','0.0','null','null','null','null','null','null','null','null','0.0','0.0','null','0.0','null',"
				        + "'null','null','null','null','null','0.0','null','null','null','null','null','null','0.0','0.0','0.0','0.0',"
				        + "'0.0','0.0','0.0','0.0','0.0','0.0','0.0','0.0','null','null','null','null','null','null','null','null','0',"
				        + "null,null,null,'null')}";
				System.out.println("Query String is : " + queryFailed);
				// System.out.println("Query String is : "+queryLocal);
				
				// callStmt = con.prepareCall(querySystem);
				callStmt = con.prepareCall(queryFailed);
				rs = callStmt.executeQuery();
				String message = "";
				if (rs != null)
				{
					while (rs.next())
					{
						result = "" + rs.getInt(1);
						// message = rs.getString("mes");
					}
					con.close();
					callStmt.close();
					rs.close();
					con = null;
					callStmt = null;
					rs = null;
				}
				else
				{
					throw new Exception("Analyser could not be inserted");
				}
				System.out.println("Message from stored procedure is : " + message);
			}
			else
			{
				throw new Exception("Connection is null");
			}
		}
		catch (Exception ex)
		{
			result = "-1";
			System.out.println("Exception in addUpdateAnalyser method of");
			ex.printStackTrace();
		}
		finally
		{
			DBConnection.closeConnection(con, callStmt, null);
		}
		return result;
	}
	
	public static AnalyserDTO getAnalyserInfo(String analyserId)
	{
		Connection con = null;
		CallableStatement callStmt = null;
		ResultSet rs = null;
		AnalyserDTO analyser = null;
		
		boolean references;
		boolean h1;
		boolean execOrphan;
		boolean recruiterOrphan;
		boolean other1Orphan;
		boolean other2Orphan;
		
		boolean execOrphanInter;
		boolean recruiterOrphanInter;
		boolean other1OrphanInter;
		boolean other2OrphanInter;
		
		boolean placement;
		boolean com;
		
		String result = "0";
		try
		{
			con = DBConnection.getConnection();
			
			if (con != null)
			{
				
				String query = "{call " + FacesUtils.SCHEMA_WIRELESS + ".spGetAnalyserInfo(" + analyserId + ")}";
				System.out.println("query " + query);
				callStmt = con.prepareCall(query);
				rs = callStmt.executeQuery();
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
							analyser.setLeave(0.0);
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
						analyser.setCommPer2(rs.getDouble("Commision_Percent2"));
						
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
						
						if (rs.getInt("CheckedReferences") == 1)
						{
							references = true;
						}
						else
						{
							references = false;
						}
						
						if (rs.getInt("H1") == 1)
						{
							h1 = true;
						}
						else
						{
							h1 = false;
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
						
						if (rs.getInt("ExecOrphan") == 1)
						{
							execOrphan = true;
						}
						else
						{
							execOrphan = false;
						}
						
						analyser.setExecOrphan(execOrphan);
						
						if (rs.getInt("RecruiterOrphan") == 1)
						{
							recruiterOrphan = true;
						}
						else
						{
							recruiterOrphan = false;
						}
						
						analyser.setRecruiterOrphan(recruiterOrphan);
						
						if (rs.getInt("Other1Orphan") == 1)
						{
							other1Orphan = true;
						}
						else
						{
							other1Orphan = false;
						}
						
						analyser.setOther1Orphan(other1Orphan);
						
						if (rs.getInt("Other2Orphan") == 1)
						{
							other2Orphan = true;
						}
						else
						{
							other2Orphan = false;
						}
						
						analyser.setOther2Orphan(other2Orphan);
						
						if (rs.getInt("ExecOrphanInter") == 1)
						{
							execOrphanInter = true;
						}
						else
						{
							execOrphanInter = false;
						}
						
						analyser.setExecOrphanInter(execOrphanInter);
						
						if (rs.getInt("RecruiterOrphanInter") == 1)
						{
							recruiterOrphanInter = true;
						}
						else
						{
							recruiterOrphanInter = false;
						}
						
						analyser.setRecruiterOrphanInter(recruiterOrphanInter);
						
						if (rs.getInt("Other1OrphanInter") == 1)
						{
							other1OrphanInter = true;
						}
						else
						{
							other1OrphanInter = false;
						}
						
						analyser.setOther1OrphanInter(other1OrphanInter);
						
						if (rs.getInt("Other2OrphanInter") == 1)
						{
							other2OrphanInter = true;
						}
						else
						{
							other2OrphanInter = false;
						}
						
						analyser.setOther2OrphanInter(other2OrphanInter);
						
						if (rs.getInt("PermanentPlacement") == 1)
						{
							placement = true;
						}
						else
						{
							placement = false;
						}
						
						analyser.setPlacement(placement);
						
						if (rs.getInt("Com") == 1)
						{
							com = true;
						}
						else
						{
							com = false;
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
						analyser.setBackgroundCheckAmount(0.0);
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
						analyser.setComm(rs.getString("Comm")); // EXTRA COLUMN
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
					}
					else
					{
						analyser = null;
					}
					
					con.close();
					callStmt.close();
					rs.close();
					con = null;
					callStmt = null;
					rs = null;
				}
				else
				{
					analyser = null;
				}
			}
			else
			{
				throw new Exception("Connection is null");
			}
		}
		catch (Exception ex)
		{
			System.out.println("Exception in getAnalyserInfo method of CustomerSessionEJBBean");
			ex.printStackTrace();
		}
		finally
		{
			DBConnection.closeConnection(con, callStmt, rs);
		}
		return analyser;
	}
	
	public static List<AnalyserDTO> getAnalyserHistory(String userId, String analyserId, String orderBy, String searchString)
	{
		Connection con = null;
		CallableStatement callStmt = null;
		ResultSet rs = null;
		AnalyserDTO analyser = null;
		
		List<AnalyserDTO> list = new ArrayList<AnalyserDTO>();
		
		try
		{
			con = DBConnection.getConnection();
			
			if (con != null)
			{
				
				String query = "{call " + FacesUtils.SCHEMA_WIRELESS + ".spGetAnalyserHistory('" + userId + "'" + "," + analyserId + ",'" + orderBy
				        + "','" + searchString + "')}";
				System.out.println("query " + query);
				callStmt = con.prepareCall(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
				rs = callStmt.executeQuery();
				int rowCount = 0;
				if (rs != null)
				{
					
					if (rs.last())
					{
						rowCount = rs.getRow();
						rs.beforeFirst();
					}
					System.out.println("Row Count : " + rowCount);
					
					while (rs.next())
					{
						
						analyser = new AnalyserDTO();
						analyser.setAnalyserId(rs.getString("AnalyserId"));
						
						analyser.setParentId(rs.getString("ParentId"));
						
						analyser.setGrossProfit(rs.getDouble("gp"));
						
						analyser.setlName(rs.getString("LastName"));
						analyser.setInitial(rs.getString("Initial"));
						analyser.setfName(rs.getString("FirstName"));
						
						analyser.setBranch(rs.getString("Branch"));
						analyser.setComments(rs.getString("Comments"));
						analyser.setCommentsInter(rs.getString("CommentsInter"));
						
						analyser.setClientCompany(rs.getString("Client"));
						analyser.setClientCompleteAddress(rs.getString("ClientAddress"));
						analyser.setSiteLocation(rs.getString("SiteAddress"));
						analyser.setContCompanyName(rs.getString("Contractor"));
						
						analyser.setPaymentTerms(rs.getString("PaymentTerm"));
						analyser.setBillRate(rs.getDouble("BillRate"));
						analyser.setOtb(rs.getString("OneTime_Bill"));
						analyser.setPayRate(rs.getDouble("PayRate"));
						analyser.setOneTimeAmount(rs.getString("OneTime_Pay"));
						analyser.setProfit(rs.getDouble("Profit"));
						analyser.setStartDate(rs.getString("StartDate"));
						analyser.setEndDate(rs.getString("EndDate"));
						
						analyser.setCommName1(rs.getString("Commision_Name1"));
						analyser.setCommPer1(rs.getDouble("Commision_Percent1"));
						analyser.setCommision1(rs.getString("Commision1"));
						analyser.setSplitCommissionPercentage1(rs.getString("SPLITCOMMISSIONPERCENTAGE1"));
						analyser.setCommissionModel1(rs.getString("COMMISSIONMODEL1"));
						
						analyser.setCommName2(rs.getString("Commision_Name2"));
						analyser.setCommPer2(rs.getDouble("Commision_Percent2"));
						analyser.setCommision2(rs.getString("Commision2"));
						analyser.setSplitCommissionPercentage2(rs.getString("SPLITCOMMISSIONPERCENTAGE2"));
						analyser.setCommissionModel2(rs.getString("COMMISSIONMODEL2"));
						
						analyser.setCommName3(rs.getString("Commision_Name3"));
						analyser.setCommPer3(rs.getDouble("Commision_Percent3"));
						analyser.setCommision3(rs.getString("Commision3"));
						analyser.setSplitCommissionPercentage3(rs.getString("SPLITCOMMISSIONPERCENTAGE3"));
						analyser.setCommissionModel3(rs.getString("COMMISSIONMODEL3"));
						
						analyser.setCommName4(rs.getString("Commision_Name4"));
						analyser.setCommPer4(rs.getDouble("Commision_Percent4"));
						analyser.setCommision4(rs.getString("Commision4"));
						analyser.setSplitCommissionPercentage4(rs.getString("SPLITCOMMISSIONPERCENTAGE4"));
						analyser.setCommissionModel4(rs.getString("COMMISSIONMODEL4"));
						
						analyser.setTravelRequired(rs.getString("TRAVELREQUIRED"));
						analyser.setDealType(rs.getString("DealType"));
						
						analyser.setCommName1Inter(rs.getString("Commision_Name1Inter"));
						analyser.setCommPer1Inter(rs.getString("Commision_Percent1Inter"));
						analyser.setCommision1Inter(rs.getString("Commision1Inter"));
						
						analyser.setCommName2Inter(rs.getString("Commision_Name2Inter"));
						analyser.setCommPer2Inter(rs.getString("Commision_Percent2Inter"));
						analyser.setCommision2Inter(rs.getString("Commision2Inter"));
						
						analyser.setCommName3Inter(rs.getString("Commision_Name3Inter"));
						analyser.setCommPer3Inter(rs.getString("Commision_Percent3Inter"));
						analyser.setCommision3Inter(rs.getString("Commision3Inter"));
						
						analyser.setCommName4Inter(rs.getString("Commision_Name4Inter"));
						analyser.setCommPer4Inter(rs.getString("Commision_Percent4Inter"));
						analyser.setCommision4Inter(rs.getString("Commision4Inter"));
						
						analyser.setImmigrationCost(rs.getDouble("IMMIGRATIONCOST"));
						analyser.setEquipmentCost(rs.getDouble("EQUIPMENTCOST"));
						analyser.setNonBillableCost(rs.getDouble("NONBILLABLECOST"));
						analyser.setProduct(rs.getString("PRODUCT"));
						analyser.setEmployeeClass(rs.getString("EMPLOYEECLASS"));
						analyser.setVertical(rs.getString("VERTICAL"));
						
						analyser.setFlsaStatus(rs.getString("FLSAStatus"));
						analyser.setFlsaReference(rs.getString("FLSAReferance"));
						analyser.setBonusAmount(rs.getDouble("BonusAmount"));
						analyser.setAnnualPay(rs.getString("annualpay"));
						analyser.setLiaisonName(rs.getString("LiaisonName"));
						
						analyser.setHealth(rs.getString("Health"));
						analyser.setK401(rs.getString("k401"));
						analyser.setOd(rs.getString("Other_Dollar"));
						analyser.setOtherAmounts(rs.getString("OtherAmount_Hour"));
						analyser.setDiscount(rs.getString("Discount"));
						analyser.setDiscountRate(rs.getString("DiscountRate"));
						analyser.setCommission(rs.getDouble("Commission"));
						analyser.setLeave(rs.getDouble("Leave"));
						analyser.setTax(rs.getString("Taxes"));
						analyser.setGa(rs.getDouble("GA"));
						
						analyser.setPerdiem(rs.getString("PerDiem"));
						analyser.setDoubleTime(rs.getString("doubletime"));
						analyser.setDoubleTimeBill(rs.getString("doubletimebill"));
						analyser.setEffectiveDate(rs.getString("EffectiveDate"));
						analyser.setPaymentTerms(rs.getString("PaymentTerm"));
						analyser.setWorkPhone(rs.getString("WorkPhone"));
						analyser.setTermDate(rs.getString("TerminateDate"));
						analyser.setReason(rs.getString("Reason"));
						analyser.setTelephone(rs.getString("Telephone"));
						analyser.setMobilePager(rs.getString("Mobile_Pager"));
						
						analyser.setBranchInter(rs.getString("branchInter"));
						
						analyser.setCurrency(rs.getString("currency"));
						analyser.setSplit(rs.getString("split"));
						
						analyser.setDob(rs.getString("DOB"));
						
						analyser.setSsn(rs.getString("SSN"));
						
						analyser.setEmail(rs.getString("Email"));
						
						analyser.setJobTitle(rs.getString("Job_Title"));
						analyser.setTotHoursWorked(rs.getDouble("THW"));
						
						list.add(analyser);
					}
				}
				else
				{
					analyser = null;
				}
			}
			else
			{
				throw new Exception("Connection is null");
			}
		}
		catch (Exception ex)
		{
			System.out.println("Exception in getAnalyserInfo method of CustomerSessionEJBBean");
			ex.printStackTrace();
		}
		finally
		{
			DBConnection.closeConnection(con, callStmt, rs);
		}
		return list;
	}
	
	public static boolean isItemPendingForUser(Integer analyserId, String itemName)
	{
		Connection con = null;
		CallableStatement callStmt = null;
		ResultSet rs = null;
		
		boolean result = false;
		try
		{
			con = DBConnection.getConnection();
			
			if (con != null)
			{
				String query = null;
				
				query = "{call wireless.spGetIssuesCountForUser('" + analyserId + "', '" + itemName + "')}";
				callStmt = con.prepareCall(query);
				
				// System.out.println("Executing Query for
				// spGetIssuesCountForUser : "
				// + query);
				rs = callStmt.executeQuery();
				
				if (rs != null)
				{
					if (rs.next())
					{
						String count = rs.getString("Records");
						if (!count.equals("0")) result = true;
					}
					callStmt.close();
					rs.close();
					
					con.close();
					con = null;
					callStmt = null;
					rs = null;
				}
			}
			else
			{
				throw new Exception("Connection is null in spGetIssuesCountForUser");
			}
		}
		catch (Exception ex)
		{
			System.out.println("Exception in spGetIssuesCountForUser method of DBHelper");
			ex.printStackTrace();
		}
		finally
		{
			DBConnection.closeConnection(con, callStmt, rs);
		}
		// System.out.println("Result is = " + result);
		return result;
	}
	
	public static void readFile()
	{
		try
		{
			
			File f = new File("E:/Temp/procedure.txt");
			
			BufferedReader b = new BufferedReader(new FileReader(f));
			
			String readLine = "";
			String line = "";
			int count = 0;
			/*
			 * while ((readLine = b.readLine()) != null) { count++; if
			 * (readLine.contains("N'")) { line = "callStmt.setString(" + count
			 * + ",\"" + readLine.substring(readLine.indexOf("'") + 1,
			 * readLine.lastIndexOf("'")).trim() + "\")";
			 * System.out.println("// " + readLine.substring(0,
			 * readLine.indexOf("=")).trim()); System.out.println(line + ";"); }
			 * else if (readLine.contains(".")) { line = "callStmt.setDouble(" +
			 * count + "," + readLine.substring(readLine.indexOf("=") + 1,
			 * readLine.lastIndexOf(",")).trim() + ")"; System.out.println("// "
			 * + readLine.substring(0, readLine.indexOf("=")).trim());
			 * System.out.println(line + ";"); } else { line =
			 * "callStmt.setInt(" + count + "," +
			 * readLine.substring(readLine.indexOf("=") + 1,
			 * readLine.lastIndexOf(",")).trim() + ")"; System.out.println("// "
			 * + readLine.substring(0, readLine.indexOf("=")).trim());
			 * System.out.println(line + ";"); }
			 * 
			 * // System.out.println(readLine); }
			 */
			
			/*
			 * while ((readLine = b.readLine()) != null) { count++; if
			 * (readLine.contains("N'")) { line = "callStmt.setString(" + count
			 * + ",\"" + readLine.substring(readLine.indexOf("'") + 1,
			 * readLine.lastIndexOf("'")).trim() + "\")";
			 * System.out.println(line+";"); line =
			 * "queryToAddAnalyser.append(\""+readLine.substring(0,
			 * readLine.indexOf("=")).trim()+ "= N'\"+analyser.get+\"',\");";
			 * //queryToAddAnalyser.append("@varLName = N'"+analyser.getlName()+
			 * "',"); System.out.println(line); } else if
			 * (readLine.contains(".")) { line = "callStmt.setDouble(" + count +
			 * "," + readLine.substring(readLine.indexOf("=") + 1,
			 * readLine.lastIndexOf(",")).trim() + ")"; System.out.println(line
			 * + ";"); line =
			 * "queryToAddAnalyser.append(\""+readLine.substring(0,
			 * readLine.indexOf("=")).trim()+ "= \"+analyser.get+\",\");";
			 * System.out.println(line); } else { line = "callStmt.setInt(" +
			 * count + "," + readLine.substring(readLine.indexOf("=") + 1,
			 * readLine.lastIndexOf(",")).trim() + ")"; System.out.println(line
			 * + ";"); line =
			 * "queryToAddAnalyser.append(\""+readLine.substring(0,
			 * readLine.indexOf("=")).trim()+ "= \"+analyser.get+\",\");";
			 * System.out.println(line); }
			 */
			
			while ((readLine = b.readLine()) != null)
			{
				count++;
				if (readLine.contains("N'"))
				{
					line = "queryToAddAnalyser.append(\"" + readLine.substring(0, readLine.indexOf("=")).trim() + "= N'\"+analyser.get+\"',\");";
					// queryToAddAnalyser.append("@varLName =
					// N'"+analyser.getlName()+"',");
					System.out.println(line);
				}
				else
				{
					
					line = "queryToAddAnalyser.append(\"" + readLine.substring(0, readLine.indexOf("=")).trim() + "= \"+analyser.get+\",\");";
					System.out.println(line);
				}
				
				// System.out.println(readLine);
			}
			
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	public static void readFromCodeFile()
	{
		try
		{
			
			File f = new File("E:/Temp/AnalyserSave-Code call.txt");
			
			BufferedReader b = new BufferedReader(new FileReader(f));
			
			String readLine = "";
			String line = "";
			int count = 0;
			String parameterName = "", sub = "";
			String startTag = "queryToAddAnalyser.append(\"";
			boolean ifClosed = false;
			while ((readLine = b.readLine()) != null)
			{
				count++;
				
				if(readLine.contains("if (")){
					ifClosed = true;
					System.out.println(readLine);
				}else if(ifClosed){
					System.out.println(readLine);
					if(readLine.contains("}")){
						ifClosed = false;
					}
				}
				/*if(readLine.contains("//")){
					System.out.println(readLine);
					parameterName = readLine.substring(2).trim();
					//System.out.print(startTag+ parameterName + " = ");
				}
				if(readLine.contains("callStmt.setInt") || readLine.contains("callStmt.setDouble")){
					System.out.println(readLine);
					sub = readLine.substring(readLine.indexOf(',')+1, readLine.lastIndexOf(")"));
					System.out.println(startTag + parameterName + " = \"+" + sub + "+\",\");");
				}
				if(readLine.contains("callStmt.setString")){
					System.out.println(readLine);
					sub = readLine.substring(readLine.indexOf(',')+1, readLine.lastIndexOf(")"));
					System.out.println(startTag + parameterName + " = N'\"+" + sub + "+\"',\");");
				}*/
				
			}
			
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}