/**
 * 
 */
package com.disys.analyzer.repository.custom.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.disys.analyzer.config.database.DBConnection;
import com.disys.analyzer.config.util.DateUtils;
import com.disys.analyzer.config.util.FacesUtils;
import com.disys.analyzer.model.AnalyserSubContractor;
import com.disys.analyzer.model.dto.Supplier;
import com.disys.analyzer.repository.custom.AnalyserSubContractorRepositoryCustom;

/**
 * @author Sajid
 * 
 */
@Repository
@Transactional(readOnly = true)
public class AnalyserSubContractorRepositoryImpl implements AnalyserSubContractorRepositoryCustom
{
	
	@PersistenceContext EntityManager entityManager;
	
	Logger logger = LoggerFactory.getLogger(AnalyserSubContractorRepositoryImpl.class);
	
	@Override
	public List<AnalyserSubContractor> getAllSubContractors(String userId, String companyName, String orderBy, String searchString,
	        String recordStatus, String companyCode)
	{		
		
		StoredProcedureQuery findByYearProcedure = entityManager
		        .createStoredProcedureQuery("" + FacesUtils.SCHEMA_WIRELESS + ".spGetAnalyserAllContractor")
		        .registerStoredProcedureParameter(1, String.class, ParameterMode.IN)
		        .registerStoredProcedureParameter(2, String.class, ParameterMode.IN)
		        .registerStoredProcedureParameter(3, String.class, ParameterMode.IN)
		        .registerStoredProcedureParameter(4, String.class, ParameterMode.IN)
		        .registerStoredProcedureParameter(5, String.class, ParameterMode.IN)
		        .registerStoredProcedureParameter(6, String.class, ParameterMode.IN);
		
		StoredProcedureQuery storedProcedure = findByYearProcedure.setParameter(1, userId).setParameter(2, companyName).setParameter(3, orderBy)
		        .setParameter(4, searchString).setParameter(5, recordStatus).setParameter(6, companyCode);
		
		List<Object[]> results = storedProcedure.getResultList();
		List<AnalyserSubContractor> list = new ArrayList<AnalyserSubContractor>();
		if (results != null && results.size() > 0)
		{
			System.out.println("The size is : " + results.size());
			AnalyserSubContractor obj = new AnalyserSubContractor();
			for (Object a[] : results)
			{
				// System.out.println(a.toString());
				obj = new AnalyserSubContractor();
				obj.setRecordStatus((String) a[0]);
				obj.setSubcontractorApprove((Integer) a[1]);
				obj.setStatus((Integer) a[2]);
				obj.setCompany((String) a[3]);
				obj.setPocName((String) a[4]);
				obj.setDbaName((String) a[5]);
				obj.setFederalTaxId((String) a[6]);
				obj.setPaymentTerm((String) a[7]);
				obj.setCompleteAddress((String) a[8]);
				obj.setUpdatedBy((String) a[9]);
				obj.setContractorId((Integer) a[10]);
				obj.setPrimaryPhone((String) a[11]);
				obj.setPrimaryEmail((String) a[12]);
				obj.setPSVendorId((String)a[13]);				
				obj.setSetId((String)a[14]);
				obj.setCompanyCode((String)a[15]);
				list.add(obj);
			}
		}
		else
		{
			System.out.println("No record found ...");
		}
		return list;
	}
	
	@Override
	public List<AnalyserSubContractor> getAllSubContractors(String userId, String companyName, String approvalStatus, String companyCode)
	{
		
		StoredProcedureQuery findByYearProcedure = entityManager
		        .createStoredProcedureQuery("" + FacesUtils.SCHEMA_WIRELESS + ".spGetAnalyserAllContractorNew")
		        .registerStoredProcedureParameter(1, String.class, ParameterMode.IN)
		        .registerStoredProcedureParameter(2, String.class, ParameterMode.IN)
		        .registerStoredProcedureParameter(3, String.class, ParameterMode.IN)
		        .registerStoredProcedureParameter(4, String.class, ParameterMode.IN);
		
		StoredProcedureQuery storedProcedure = findByYearProcedure.setParameter(1, userId).setParameter(2, companyName).setParameter(3, approvalStatus).setParameter(4, companyCode);
		
		List<Object[]> results = storedProcedure.getResultList();
		List<AnalyserSubContractor> list = new ArrayList<AnalyserSubContractor>();
		if (results != null && results.size() > 0)
		{
			System.out.println("The size is : " + results.size());
			AnalyserSubContractor obj = new AnalyserSubContractor();
			for (Object a[] : results)
			{
				// System.out.println(a.toString());
				obj = new AnalyserSubContractor();
				obj.setRecordStatus((String) a[0]);
				obj.setSubcontractorApprove((Integer) a[1]);
				obj.setStatus((Integer) a[2]);
				obj.setCompany((String) a[3]);
				obj.setPocName((String) a[4]);
				obj.setDbaName((String) a[5]);
				obj.setFederalTaxId((String) a[6]);
				obj.setPaymentTerm((String) a[7]);
				obj.setCompleteAddress((String) a[8]);
				obj.setUpdatedBy((String) a[9]);
				obj.setContractorId((Integer) a[10]);
				obj.setPrimaryPhone((String) a[11]);
				obj.setPrimaryEmail((String) a[12]);
				obj.setPSVendorId((String)a[13]);
				obj.setUpdatedDate((Timestamp)a[18]);
				obj.setCompanyCode((String)a[19]);
				obj.setSetId((String)a[20]);
				/*if(a[18]!=null){
					obj.setUpdatedDate(DateUtils.getTimestampFromStringDate((String)a[18]));
				} else {
					obj.setUpdatedDate(null);
				}*/
				list.add(obj);
			}
		}
		else
		{
			System.out.println("No record found ...");
		}
		return list;
	}
	
	@Override
	public Supplier getSupplierInfo(String supplierId)
	{
		String query = "";
		query = "{call " + FacesUtils.SCHEMA_WIRELESS + ".spGetSupplierInfoById(" + supplierId + ")}";
		logger.debug(query);
		Supplier supplier = this.getSupplier(query);
		return supplier;
	}
	
	/*
	 * Supplier can be looked-up either through email or supplierid query will
	 * be made sent through for processing
	 */
	public Supplier getSupplier(String query)
	{
		Connection con = null;
		CallableStatement callStmt = null;
		ResultSet rs = null;
		Supplier supplier = null;
		
		try
		{
			con = DBConnection.getConnection();
			
			if (con != null)
			{
				System.out.println("query = " + query);
				callStmt = con.prepareCall(query);
				rs = callStmt.executeQuery();
				if (rs != null)
				{
					if (rs.next())
					{
						
						supplier = new Supplier();
						
						supplier.setSupplierId(rs.getString("Contractor_ID"));
						supplier.setOrgId(rs.getString("Org_ID"));
						supplier.setLegalName(rs.getString("Company"));
						supplier.setDbaName(rs.getString("DbaName"));
						
						supplier.setFin(rs.getString("FIN"));
						supplier.setPocLastName(rs.getString("LastName"));
						supplier.setPocFirstName(rs.getString("Poc_Name"));
						supplier.setPaymentTerm(rs.getString("Payment_Term"));
						supplier.setVendorCode(rs.getString("Vendor_Code"));
						supplier.setInvoiceEmail(rs.getString("InvoiceEmail"));
						
						supplier.setHeadQuartersAddress(rs.getString("Address1"));
						supplier.setHeadCity(rs.getString("City"));
						supplier.setHeadState(rs.getString("State"));
						supplier.setHeadZip(rs.getString("Zipcode"));
						supplier.setWebsite(rs.getString("Website"));
						supplier.setHeadPhone(rs.getString("Telephone"));
						supplier.setHeadFax(rs.getString("Fax"));
						supplier.setFederalTaxId(rs.getString("FederalTaxId"));
						supplier.setDunsNumber(rs.getString("DunsNumber"));
						supplier.setRemitAddress(rs.getString("RemitAddress"));
						supplier.setRemitCity(rs.getString("RemitCity"));
						supplier.setRemitState(rs.getString("RemitState"));
						supplier.setRemitZip(rs.getString("RemitZip"));
						supplier.setAccountingContact(rs.getString("AccountingContact"));
						supplier.setAccountingEmail(rs.getString("AccountingEmail"));
						supplier.setRemitPhone(rs.getString("RemitPhone"));
						supplier.setRemitFax(rs.getString("RemitFax"));
						supplier.setPrimaryName(rs.getString("PrimaryName"));
						supplier.setPrimaryTitle(rs.getString("PrimaryTitle"));
						supplier.setPrimaryAddress(rs.getString("PrimaryAddress"));
						supplier.setPrimaryCity(rs.getString("PrimaryCity"));
						supplier.setPrimaryState(rs.getString("PrimaryState"));
						supplier.setPrimaryZip(rs.getString("PrimaryZip"));
						supplier.setPrimaryPhone(rs.getString("PrimaryPhone"));
						supplier.setPrimaryFax(rs.getString("PrimaryFax"));
						supplier.setPrimaryEmail(rs.getString("PrimaryEmail"));
						supplier.setRecruitmentName(rs.getString("RecruitmentName"));
						supplier.setRecruitmentTitle(rs.getString("RecruitmentTitle"));
						supplier.setRecruitmentAddress(rs.getString("RecruitmentAddress"));
						supplier.setRecruitmentCity(rs.getString("RecruitmentCity"));
						supplier.setRecruitmentState(rs.getString("RecruitmentState"));
						supplier.setRecruitmentZip(rs.getString("RecruitmentZip"));
						supplier.setRecruitmentPhone(rs.getString("RecruitmentPhone"));
						supplier.setRecruitmentFax(rs.getString("RecruitmentFax"));
						supplier.setRecruitmentEmail(rs.getString("RecruitmentEmail"));
						supplier.setOfficerName1(rs.getString("OfficerName1"));
						supplier.setOfficerTitle1(rs.getString("OfficerTitle1"));
						supplier.setOfficerName2(rs.getString("OfficerName2"));
						supplier.setOfficerTitle2(rs.getString("OfficerTitle2"));
						supplier.setOfficerName3(rs.getString("OfficerName3"));
						supplier.setOfficerTitle3(rs.getString("OfficerTitle3"));
						supplier.setOfficerName4(rs.getString("OfficerName4"));
						supplier.setOfficerTitle4(rs.getString("OfficerTitle4"));
						
						supplier.setCorporation((rs.getInt("Corporation") == 1) ? true : false);
						supplier.setProprietorship((rs.getInt("Proprietorship") == 1) ? true : false);
						supplier.setPartnership((rs.getInt("Partnership") == 1) ? true : false);
						supplier.setOther((rs.getInt("Other") == 1) ? true : false);
						
						supplier.setOtherDescription(rs.getString("OtherDescription"));
						supplier.setEmployeeCount(rs.getString("EmployeeCount"));
						supplier.setDateEstablished(rs.getString("DateEstablishedFormatted"));
						supplier.setInState(rs.getString("InState"));
						supplier.setIncorporationCertificate(rs.getString("IncorporationCertificate"));
						supplier.setStatesAuthorized(rs.getString("StatesAuthorized"));
						
						supplier.setTypeSubcontractor((rs.getInt("TypeSubcontractor") == 1) ? true : false);
						supplier.setType1099((rs.getInt("Type1099") == 1) ? true : false);
						supplier.setSmallBusiness((rs.getInt("SmallBusiness") == 1) ? true : false);
						supplier.setLargeBusiness((rs.getInt("LargeBusiness") == 1) ? true : false);
						supplier.setSmallDisadvantaged((rs.getInt("SmallDisadvantaged") == 1) ? true : false);
						supplier.setWomenOwned((rs.getInt("WomenOwned") == 1) ? true : false);
						supplier.setVeteranOwned((rs.getInt("VeteranOwned") == 1) ? true : false);
						supplier.setDisabledOwned((rs.getInt("DisabledOwned") == 1) ? true : false);
						supplier.setMinorityOwned((rs.getInt("MinorityOwned") == 1) ? true : false);
						supplier.setAsiaPacific((rs.getInt("AsiaPacific") == 1) ? true : false);
						supplier.setAfricanAmerican((rs.getInt("AfricanAmerican") == 1) ? true : false);
						supplier.setHispanicAmerican((rs.getInt("HispanicAmerican") == 1) ? true : false);
						supplier.setNativeAmerican((rs.getInt("NativeAmerican") == 1) ? true : false);
						
						supplier.setNaicsPrimaryCode(rs.getString("NaicsPrimaryCode"));
						supplier.setNaicsSecondaryCode(rs.getString("NaicsSecondaryCode"));
						supplier.setGeneralLiability(rs.getString("GeneralLiability"));
						supplier.setGeneralExpiration(rs.getString("GeneralExpirationFormatted"));
						supplier.setGeneralLimit(rs.getString("GeneralLimit"));
						supplier.setWorkersLiability(rs.getString("WorkersLiability"));
						supplier.setWorkersExpiration(rs.getString("WorkersExpirationFormatted"));
						supplier.setWorkersLimit(rs.getString("WorkersLimit"));
						supplier.setEmployersLiability(rs.getString("EmployersLiability"));
						supplier.setEmployersExpiration(rs.getString("EmployersExpirationFormatted"));
						supplier.setEmployersLimit(rs.getString("EmployersLimit"));
						supplier.setAutomobileLiability(rs.getString("AutomobileLiability"));
						supplier.setAutomobileExpiration(rs.getString("AutomobileExpirationFormatted"));
						supplier.setAutomobileLimit(rs.getString("AutomobileLimit"));
						supplier.setUmbrellaLiability(rs.getString("UmbrellaLiability"));
						supplier.setUmbrellaExpiration(rs.getString("UmbrellaExpirationFormatted"));
						supplier.setUmbrellaLimit(rs.getString("UmbrellaLimit"));
						
						supplier.setDisysAdditionalInsured((rs.getInt("DisysAdditionalInsured") == 1) ? true : false);
						supplier.setSubcontractorAgreement((rs.getInt("SubcontractorAgreement") == 1) ? true : false);
						supplier.setFormW9((rs.getInt("W9") == 1) ? true : false);
						supplier.setInsuranceCertificate((rs.getInt("InsuranceCertificate") == 1) ? true : false);
						supplier.setWorkOrder((rs.getInt("WorkOrder") == 1) ? true : false);
						
						supplier.setCountry(rs.getString("Country"));
						supplier.setDiscount(rs.getString("discount"));
						
						supplier.setSubcontractorComments(rs.getString("SubcontractorComments"));
						String val = rs.getString("discountAmount");
						System.out.println("Discount Amount = " + val);
						if (val == null || val.trim().equalsIgnoreCase("null") || val.equals(""))
						{
							supplier.setDiscountAmount(0.0);
						}
						else
						{
							try
							{
								supplier.setDiscountAmount(Double.parseDouble(val));
							}
							catch (Exception e)
							{
								System.out.println(
								        "Error in Method getSupplierInfo, error converting discountAmount to a double value, discount amount = "
								                + val);
								e.printStackTrace();
								supplier.setDiscountAmount(0.0);
							}
						}
						
						supplier.setAmountType(rs.getString("amountType"));
						
						supplier.setGeneralType(rs.getString("GeneralType"));
						supplier.setWorkersType(rs.getString("WorkersType"));
						supplier.setEmployersType(rs.getString("EmployersType"));
						supplier.setAutomobileType(rs.getString("AutomobileType"));
						supplier.setUmbrellaType(rs.getString("UmbrellaType"));
						
						if (this.setSubmitStatus(supplier.getSupplierId()).equals("1"))
						{
							supplier.setSubmitStatus(true);
						}
						else
						{
							supplier.setSubmitStatus(false);
						}
						supplier.setApprovalStatus(rs.getString("SubcontractorApprove"));
						supplier.setStatus(rs.getString("Status"));
						supplier.setRecordStatus(rs.getString("RecordStatus"));
						supplier.setEntryDate(rs.getString("EntryDate"));
						supplier.setApprovalDate(rs.getString("ApprovalDate"));
						supplier.setApprovedBy(rs.getString("ApprovedBy"));
						supplier.setComments(rs.getString("Comments"));
						supplier.setLlc((rs.getInt("Llc") == 1) ? true : false);
						supplier.setpSVendorId(rs.getString("PSVendorId"));
						
						supplier.setCompanyName(rs.getString("Company"));
						supplier.setAddress1(rs.getString("Address1"));
						supplier.setAddress2(rs.getString("Address2"));
						supplier.setCity(rs.getString("City"));
						supplier.setState(rs.getString("State"));
						supplier.setZipCode(rs.getString("Zipcode"));
						supplier.setCompanyCode(rs.getString("CompanyCode"));
					}
					else
					{
						supplier = null;
					}
				}
				else
				{
					supplier = null;
				}
			}
			else
			{
				throw new Exception("Connection is null - getSupplierInfo");
			}
		}
		catch (Exception ex)
		{
			System.out.println("Exception in getSupplierInfo method of CustomerSessionEJBBean");
			ex.printStackTrace();
		}
		finally
		{
			DBConnection.closeConnection(con, callStmt, rs);
		}
		return supplier;
		
	}
	
	private String setSubmitStatus(String contractorId)
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
				/*
				 * return 0 for non-ok status and 1 for ok status and -1 for
				 * error
				 */
				query = "{call " + FacesUtils.SCHEMA_WIRELESS + ".spGetSupplierSubmitStatus(" + contractorId + ")}";
				System.out.println("Executing Query :  " + query);
				
				logger.debug("Executing Query :  " + query);
				callStmt = con.prepareCall(query);
				rs = callStmt.executeQuery();
				if (rs != null)
				{
					if (rs.next())
					{
						result = rs.getString("Result");
					}
					System.out.println("setSubmitStatus - CustomerSessionEJBBean - result = " + result);
				}
				else
				{
					throw new Exception("rs is NULL in setSubmitStatus - CustomerSessionEJBBean");
				}
			}
			else
			{
				throw new Exception("Connection is null in setSubmitStatus - CustomerSessionEJBBean");
			}
		}
		catch (Exception ex)
		{
			result = "0";
			System.out.println("Exception in setSubmitStatus - CustomerSessionEJBBean");
			ex.printStackTrace();
		}
		finally
		{
			DBConnection.closeConnection(con, callStmt, null);
		}
		return result;
	}
	
	@Override
	public String changeSubcontractorStatus(String userId, Integer contractorId, Integer status)
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
				
				query = "{call " + FacesUtils.SCHEMA_WIRELESS + ".spSetContractorStatus('" + userId + "', " + contractorId + ", " + status + ")}";
				
				System.out.println("Executing Query in setContractorStatusInDatabase -- :  " + query);
				System.out.println("In AnalyserSubContractorRepositoryImpl Contractor id = " + contractorId);
				System.out.println("In AnalyserSubContractorRepositoryImpl Status = " + status);
				callStmt = con.prepareCall(query);
				res = callStmt.executeUpdate();
				
				if (res != 0) // return 0 if success or "" otherwise
				    result = "";
				
				con.close();
				callStmt.close();
				if (rs != null)
				{
					rs.close();
				}
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

	@Override
	public Boolean updateSupplierInfo(String userId, Integer contractorId, String subContractorName,
			String address1, String address2, String city, String state, String zipCode, String companyCode)
	{
		Connection con = null;
		CallableStatement callStmt = null;
		ResultSet rs = null;
		Boolean result = false;
		try
		{	
			con = DBConnection.getConnection();
			if (con != null)
			{
				String query = null;
				
				query = "{call " + FacesUtils.SCHEMA_WIRELESS + ".updateSupplierInfo('" + userId + "', " + contractorId + ","
						+ " '" + subContractorName + "','"
						+address1+"','"+address2+"','"+city+"','"+state+"','"+zipCode+"', '"+companyCode+"')}";
				
				System.out.println("Executing Query in setContractorStatusInDatabase -- :  " + query);
				System.out.println("In updateSupplierInfo userId = " + userId);
				System.out.println("In updateSupplierInfo Contractor id = " + contractorId);
				System.out.println("In updateSupplierInfo subContractorName = " + subContractorName);
				System.out.println("In updateSupplierInfo address1 = " + address1);
				System.out.println("In updateSupplierInfo address2 = " + address2);
				System.out.println("In updateSupplierInfo city = " + city);
				System.out.println("In updateSupplierInfo state = " + state);
				System.out.println("In updateSupplierInfo zipCode = " + zipCode);
				System.out.println("In updateSupplierInfo companyCode = " + companyCode);
				
				logger.debug("Executing Query in setContractorStatusInDatabase -- :  " + query);
				logger.debug("In updateSupplierInfo userId = " + userId);
				logger.debug("In updateSupplierInfo Contractor id = " + contractorId);
				logger.debug("In updateSupplierInfo subContractorName = " + subContractorName);
				logger.debug("In updateSupplierInfo address1 = " + address1);
				logger.debug("In updateSupplierInfo address2 = " + address2);
				logger.debug("In updateSupplierInfo city = " + city);
				logger.debug("In updateSupplierInfo state = " + state);
				logger.debug("In updateSupplierInfo zipCode = " + zipCode);
				logger.debug("In updateSupplierInfo companyCode = " + companyCode);
				
				callStmt = con.prepareCall(query);
				int res = callStmt.executeUpdate();
				
				if (res != 0){
					result = true;
				} else {
					result = false;
				}
				
				con.close();
				callStmt.close();
				if (rs != null)
				{
					rs.close();
				}
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
			System.out.println("Exception in updateSupplierInfo method of AnalyserSubContractorRepositoryImpl");
			ex.printStackTrace();
			return false;
		}
		finally
		{
			DBConnection.closeConnection(con, callStmt, rs);
		}
		return result;
	}
	
}
