/**
 * 
 */
package com.disys.analyzer.repository.custom.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Session;
import org.hibernate.jdbc.Work;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.disys.analyzer.config.database.DBConnection;
import com.disys.analyzer.config.util.Constants;
import com.disys.analyzer.config.util.FacesUtils;
import com.disys.analyzer.config.util.Util;
import com.disys.analyzer.model.PSBillingType;
import com.disys.analyzer.model.PSOperatingUnitOrVertical;
import com.disys.analyzer.model.PSProduct;
import com.disys.analyzer.model.dto.AnalyserClientDTO;
import com.disys.analyzer.model.dto.AnalyserClientDatabaseDTO;
import com.disys.analyzer.repository.custom.AnalyserClientRepositoryCustom;


/**
 * @author Sajid
 * 
 */
@Repository
@Transactional(readOnly = true)
public class AnalyserClientRepositoryImpl implements AnalyserClientRepositoryCustom
{
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@PersistenceContext EntityManager entityManager;
	
	public static final String CLIENT_LIST = "list";
	public static final String CLIENT_DISTINCT = "distinct";
	public static final String CLIENT_INFO = "info";
	public static final String CLIENT_ADD_UPDATE = "addUpdate";
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.disys.analyzer.repository.AnalyserClientRepositoryCustom#
	 * getAnalyserAllClients(java.lang.String, java.lang.String,
	 * java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public List<AnalyserClientDTO> getAnalyserAllClients(String userId, String userList, String orderBy, String searchString, String vertical,
	        String product, String branch, String companyCode)
	{
		System.out.println("In AnalyserClientRepositoryCustomImpl --> getAnalyserAllClients");
		logger.debug("In AnalyserClientRepositoryCustomImpl --> getAnalyserAllClients");
		System.out.println("In AnalyserClientRepositoryCustomImpl --> branch = "+branch);
		System.out.println("In AnalyserClientRepositoryCustomImpl --> companyCode = "+companyCode);
		logger.debug("In AnalyserClientRepositoryCustomImpl --> branch = "+branch);
		List<AnalyserClientDTO> clientsList = new ArrayList<AnalyserClientDTO>();
		
		Session session = entityManager.unwrap(Session.class);
		String action = CLIENT_LIST;
		AnalyserClientDTOWork work = new AnalyserClientDTOWork(userId, userList, orderBy, searchString, vertical, product, action, branch, companyCode);
		session.doWork(work);	//call the execute method of passed object/class
		clientsList = work.getClientsList();
		System.out.println("Size is : " + clientsList);
		return clientsList;
		
	}
	
	@Override
	public List<AnalyserClientDTO> spGetAnalyserAllDistinctClient(String userId, String userList, String orderBy, String searchString, String companyCode)
	{
		List<AnalyserClientDTO> clientsList = new ArrayList<AnalyserClientDTO>(0);
		Session session = entityManager.unwrap(Session.class);
		String action = CLIENT_DISTINCT;
		AnalyserClientDTOWork work = new AnalyserClientDTOWork(userId, userList, orderBy, searchString, action, companyCode);
		session.doWork(work);
		clientsList = work.getClientsList();
		return clientsList;
	}
	
	private static class AnalyserClientDTOWork implements Work
	{
		
		List<AnalyserClientDTO> clientsList;
		AnalyserClientDTO client;
		String userId;
		String userList;
		String orderBy;
		String searchString;
		String vertical;
		String product;
		String action;
		String result;
		String branch;
		String companyCode;
		
		public AnalyserClientDTOWork(String userId, String userList, String orderBy, String searchString, String vertical, String product,
		        String action, String branch, String companyCode)
		{
			this.userId = userId;
			this.userList = userList;
			this.orderBy = orderBy;
			this.searchString = searchString;
			this.vertical = vertical;
			this.product = product;
			this.action = action;
			this.branch = branch;
			this.companyCode = companyCode;
		}
		
		public AnalyserClientDTOWork(String userId, String userList, String orderBy, String searchString, String vertical, String product,
		        String action)
		{
			this.userId = userId;
			this.userList = userList;
			this.orderBy = orderBy;
			this.searchString = searchString;
			this.vertical = vertical;
			this.product = product;
			this.action = action;
		}
		
		public AnalyserClientDTOWork(String userId, String userList, String orderBy, String searchString, String action, String companyCode)
		{
			this.userId = userId;
			this.userList = userList;
			this.orderBy = orderBy;
			this.searchString = searchString;
			this.action = action;
			this.companyCode = companyCode;
		}
		
		@Override
		public void execute(Connection connection) throws SQLException
		{
			clientsList = new ArrayList<AnalyserClientDTO>(0);
			client = new AnalyserClientDTO();
			
			ResultSet rs = null;
			CallableStatement cstmt = null;
			
			if (action.equals(CLIENT_LIST))
			{
				System.out.println("In AnalyserClientRepositoryCustomImpl --> execute CLIENT_LIST");
				System.out.println("In AnalyserClientRepositoryCustomImpl --> action CLIENT_LIST = "+action);
				System.out.println("{call "+ FacesUtils.SCHEMA_WIRELESS +".spGetAnalyserAllClient(?, ?, ?, ?, ?, ?, ?, ?)}");
				System.out.println("{call "+ FacesUtils.SCHEMA_WIRELESS +".spGetAnalyserAllClient(\"" + userId
						+ "\", \"" + userList + "\", \"" + orderBy + "\", \"" + searchString
						+ "\", \"" + vertical + "\", \"" + product + "\", \"" + branch + "\", \"" + companyCode + "\")}");
				
				cstmt = connection.prepareCall("{call " + FacesUtils.SCHEMA_WIRELESS + ".spGetAnalyserAllClient(?, ?, ?, ?, ?, ?, ?, ?)}");
				cstmt.setString("varLoggedOnUserID", userId);
				cstmt.setString("varUserList", userList);
				cstmt.setString("varOrderBy", orderBy);
				cstmt.setString("varSearchString", searchString);
				cstmt.setString("varVertical", vertical);
				cstmt.setString("varProduct", product);
				cstmt.setString("varBranch", branch);
				cstmt.setString("varCompanyCode", companyCode);
				rs = cstmt.executeQuery();
			}
			else if (action.equals(CLIENT_DISTINCT))
			{
				// ('Gregory.Armstrong@DISYS.COM','0','Company','ALL')
				cstmt = connection.prepareCall("{call " + FacesUtils.SCHEMA_WIRELESS + ".spGetAnalyserAllDistinctClient(?, ?, ?, ?, ?)}");
				cstmt.setString("varLoggedOnUserID", userId);
				cstmt.setString("varUserList", userList);
				cstmt.setString("varOrderBy", orderBy);
				cstmt.setString("varSearchString", searchString);
				cstmt.setString("varCompanyCode", companyCode);
				rs = cstmt.executeQuery();
			}
			
			try
			{
				clientsList = getRSRowInfo(rs);
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
			finally
			{
				if (rs != null) rs.close();
				if (cstmt != null) cstmt.close();
			}
			
		}
		
		private List<AnalyserClientDTO> getRSRowInfo(ResultSet rs) throws Exception
		{
			ResultSetMetaData rsMeta;
			try
			{
				rsMeta = rs.getMetaData();
				int iRowCount = rsMeta.getColumnCount();
				while (rs.next())
				{
					client = new AnalyserClientDTO();
					for (int i = 0; i < iRowCount; i++)
					{
						String columnName = rsMeta.getColumnLabel(i + 1);
						// put values into the array list
						try
						{
							if (columnName.equalsIgnoreCase("InvoiceFrequency"))
							{
								client.setInvoiceFrequency((String) getRSColumnValue(rs, rsMeta, i + 1));
							}
							else if (columnName.equalsIgnoreCase("Contact_Name"))
							{
								client.setContactName((String) getRSColumnValue(rs, rsMeta, i + 1));
							}
							else if (columnName.equalsIgnoreCase("DistributionMethod"))
							{
								client.setDistributionMethod((String) getRSColumnValue(rs, rsMeta, i + 1));
							}
							else if (columnName.equalsIgnoreCase("SpecialNotes"))
							{
								client.setSpecialNotes((String) getRSColumnValue(rs, rsMeta, i + 1));
							}
							else if (columnName.equalsIgnoreCase("PaymentTerms"))
							{
								client.setPaymentTerms((String) getRSColumnValue(rs, rsMeta, i + 1));
							}
							else if (columnName.equalsIgnoreCase("AdminFee"))
							{
								client.setAdminFee((String) getRSColumnValue(rs, rsMeta, i + 1));
							}
							else if (columnName.equalsIgnoreCase("Email"))
							{
								client.setEmail((String) getRSColumnValue(rs, rsMeta, i + 1));
							}
							else if (columnName.equalsIgnoreCase("VolumeDiscount"))
							{
								client.setVolumeDiscount((Double) getRSColumnValue(rs, rsMeta, i + 1));
							}
							else if (columnName.equalsIgnoreCase("PaymentDiscount"))
							{
								client.setPaymentDiscount((Double) getRSColumnValue(rs, rsMeta, i + 1));
							}
							else if (columnName.equalsIgnoreCase("OtherDiscount"))
							{
								client.setOtherDiscount((Double) getRSColumnValue(rs, rsMeta, i + 1));
							}
							else if (columnName.equalsIgnoreCase("BackgroundAmount"))
							{
								client.setBackgroundAmount((String) getRSColumnValue(rs, rsMeta, i + 1));
							}
							else if (columnName.equalsIgnoreCase("CreditCheckAmount"))
							{
								client.setCreditCheckAmount((String) getRSColumnValue(rs, rsMeta, i + 1));
							}
							else if (columnName.equalsIgnoreCase("DrugTestAmount"))
							{
								client.setDrugTestAmount((String) getRSColumnValue(rs, rsMeta, i + 1));
							}
							else if (columnName.equalsIgnoreCase("Company"))
							{
								//company= (String) getRSColumnValue(rs, rsMeta, i + 1);
								client.setCompany((String) getRSColumnValue(rs, rsMeta, i + 1));
							}
							else if (columnName.equalsIgnoreCase("Client_ID"))
							{
								client.setClientId((Integer) getRSColumnValue(rs, rsMeta, i + 1));
							}
							else if (columnName.equalsIgnoreCase("Address_ID"))
							{
								client.setAddressId((Integer) getRSColumnValue(rs, rsMeta, i + 1));
							}
							else if (columnName.equalsIgnoreCase("Address"))
							{
								client.setAddress((String) getRSColumnValue(rs, rsMeta, i + 1));
							}
							else if (columnName.equalsIgnoreCase("DysBillType"))
							{
								client.setDysBillType((String) getRSColumnValue(rs, rsMeta, i + 1));
							}
							else if (columnName.equalsIgnoreCase("DysDeliveryType"))
							{
								client.setDysDeliveryType((String) getRSColumnValue(rs, rsMeta, i + 1));
							}
							else if (columnName.equalsIgnoreCase("PSSALESFORCECLIENTNAME"))
							{
								client.setpSSalesForceClientName((String) getRSColumnValue(rs, rsMeta, i + 1));
							}
							else if (columnName.equalsIgnoreCase("PSSERVICEAREALONGNAME"))
							{
								client.setpSServiceAreaLongName((String) getRSColumnValue(rs, rsMeta, i + 1));
							}
							else if (columnName.equalsIgnoreCase("PSBillingTypeLongName"))
							{
								client.setpSBillingTypeLongName((String) getRSColumnValue(rs, rsMeta, i + 1));
							}
							else if (columnName.equalsIgnoreCase("PRODUCTDESCRIPTION"))
							{
								client.setProductDescription((String) getRSColumnValue(rs, rsMeta, i + 1));
							}
							else if (columnName.equalsIgnoreCase("VERTICALDESCRIPTION"))
							{
								client.setVerticalDescription((String) getRSColumnValue(rs, rsMeta, i + 1));
							}
							else if (columnName.equalsIgnoreCase("PSVERTICALCODE"))
							{
								client.setpSVerticalCode((String) getRSColumnValue(rs, rsMeta, i + 1));
							}
							else if (columnName.equalsIgnoreCase("PSPRODUCTCODE"))
							{
								client.setpSProductCode((String) getRSColumnValue(rs, rsMeta, i + 1));
							}
							else if (columnName.equalsIgnoreCase("DysPracticeArea"))
							{
								client.setDysPracticeArea((String) getRSColumnValue(rs, rsMeta, i + 1));
							}
							else if (columnName.equalsIgnoreCase("Updated_By"))
							{
								client.setUpdatedBy((String) getRSColumnValue(rs, rsMeta, i + 1));
								/*
								 * client.setDysPracticeArea((String)
								 * getRSColumnValue( rs, rsMeta, i + 1));
								 */
							}
							else if (columnName.equalsIgnoreCase("Status"))
							{
								try
								{
									// System.out.println("Status is :
									// "+(Integer) getRSColumnValue(rs, rsMeta,
									// i +
									// 1));
									client.setStatus((Integer) getRSColumnValue(rs, rsMeta, i + 1));
								}
								catch (Exception e)
								{
									System.err.println("Error reading status value");
								}
								
							}
							else if (columnName.equalsIgnoreCase("PSCLIENTID"))
							{
								client.setpSClientId((String) getRSColumnValue(rs, rsMeta, i + 1));
							}
							else if (columnName.equalsIgnoreCase("TotalDiscount"))
							{
								client.setTotalDiscount((Double) getRSColumnValue(rs, rsMeta, i + 1));
							}
							else if (columnName.equalsIgnoreCase("Country"))
							{
								client.setCountry((String) getRSColumnValue(rs, rsMeta, i + 1));
							}
							else if (columnName.equalsIgnoreCase("SetId"))
							{
								client.setSetId((String) getRSColumnValue(rs, rsMeta, i + 1));
							}
							
							else if (columnName.equalsIgnoreCase("PSVerticalId"))
							{
								client.setPsVerticalId((Integer) getRSColumnValue(rs, rsMeta, i + 1));
							}
							else if (columnName.equalsIgnoreCase("PSProductId"))
							{
								client.setPsProductId((Integer) getRSColumnValue(rs, rsMeta, i + 1));
							}
							else if (columnName.equalsIgnoreCase("CompanyCode"))
							{
							    client.setCompanyCode((String) getRSColumnValue(rs, rsMeta, i + 1));
							}
							
						}
						catch (Exception ex)
						{
							continue;
						}
					}					
					clientsList.add(client);
					/*
					if (client != null)
					{
						System.out.println("client.getSetId() = " + client.getSetId());
						System.out.println("client.getCountry() = " + client.getCountry());
					}
					*/
				}
				return clientsList;
			}
			catch (SQLException e)
			{
				String sz = "An error occured while attempting to parse resultset column information from the database.  Error type :  "
				        + e.getSQLState() + ".  Error Code : " + e.getErrorCode();
				Exception err = new Exception(sz);
				throw err;
			}
		}
		
		private Object getRSColumnValue(ResultSet rs, ResultSetMetaData rsMeta, int pos) throws Exception
		{
			try
			{
				int iType = (int) rsMeta.getColumnType(pos);
				switch (iType)
				{
					case Types.BIGINT:
						return new Long(rs.getLong(pos));
					case Types.NUMERIC:
					{
						java.math.BigDecimal bigDecimal = rs.getBigDecimal(pos);
						if (bigDecimal == null) return null;
						else if (bigDecimal.scale() == 0) return new Long(bigDecimal.longValue());
						else return new Double(bigDecimal.doubleValue());
					}
					
					case Types.CHAR:
					{
						String s = rs.getString(pos);
						// if (rs.wasNull()) return "";
						return s;
					}
					case Types.TIMESTAMP:
					{
						java.sql.Timestamp d = rs.getTimestamp(pos);
						return d;
					}
					case Types.DATE:
					{
						java.sql.Date d = rs.getDate(pos);
						return d;
					}
					case Types.DOUBLE:
						return new Double(rs.getDouble(pos));
					case Types.FLOAT:
						return new Float(rs.getFloat(pos));
					case Types.INTEGER:
						return new Integer(rs.getInt(pos));
					case Types.LONGVARCHAR:
						return rs.getString(pos);
					case Types.VARBINARY:
						return rs.getBytes(pos);
					case Types.VARCHAR:
					{
						String s = rs.getString(pos);
						return s;
					}
					default:
						return rs.getObject(pos);
				}
			}
			catch (SQLException e)
			{
				String sz = "An error occured while attempting to parse resultset column information from the database.  Error type :  "
				        + e.getSQLState() + ".  Error Code : " + e.getErrorCode();
				Exception err = new Exception(sz);
				throw err;
			}
			
		}
		
		/**
		 * @return the clientsList
		 */
		public List<AnalyserClientDTO> getClientsList()
		{
			return clientsList;
		}
		
		public String getResult()
		{
			return null;
		}
	}
	
	@Override
	public AnalyserClientDatabaseDTO getClientDatabaseInfo(String clientId)
	{
		Session session = entityManager.unwrap(Session.class);
		AnalyserClientInfoWork work = new AnalyserClientInfoWork(clientId);
		session.doWork(work);
		AnalyserClientDatabaseDTO client = work.getClient();
		return client;
	}
	
	private static class AnalyserClientInfoWork implements Work
	{
		public Logger logger = LoggerFactory.getLogger(getClass());
		private AnalyserClientDatabaseDTO client;
		String clientId;
		String userId;
		
		ResultSet rs = null;
		CallableStatement callStmt = null;
		
		/**
		 * @param analyserAddressId
		 */
		public AnalyserClientInfoWork(String clientId)
		{
			super();
			this.clientId = clientId;
			this.userId = FacesUtils.getCurrentUserId();
		}
		
		@Override
		public void execute(Connection connection) throws SQLException
		{
			CallableStatement cstmt = null;
			client = new AnalyserClientDatabaseDTO();
			String query;
			
			//String query = "select * from Analyser_Client" + " where Client_Id = " + clientId;
			
			try
			{
				System.out.println("In AnalyserClientRepositoryCustomImpl --> static class AnalyserClientInfoWork --> execute and load AnalyserClientDatabaseDTO");
				logger.debug("In AnalyserClientRepositoryCustomImpl --> static class AnalyserClientInfoWork --> execute and load AnalyserClientDatabaseDTO");
				
				query = "{call " + FacesUtils.SCHEMA_WIRELESS + ".spGetAnalyserClientInfo('" + userId + "'" + ", " + clientId + " )}";
				System.out.println("query " + query);
				logger.debug("query " + query);

				//cstmt = connection.prepareCall("{call " + FacesUtils.SCHEMA_WIRELESS + ".[spGetAnalyserClientInfo(?, ?)}");
				//cstmt.setString(1, userId);
				//cstmt.setString(2, clientId);
				
				callStmt = connection.prepareCall(query);
				rs = callStmt.executeQuery();
				
				if (rs != null)
				{
					if (rs.next())
					{
						client.setClientId(rs.getString("Client_Id"));
						// client.setOrgId(rs.getString("Org_Id"));
						client.setPrimeSub(rs.getString("PrimeSub"));
						client.setCompanyName(rs.getString("Company"));
						client.setAgreementType(rs.getString("AgreementType"));
						client.setOtherAgreement(rs.getString("OtherAgreement"));
						client.setEndClient(rs.getString("EndClient"));
						client.setAgreementNumber(rs.getString("AgreementNumber"));
						client.setContactName(rs.getString("ContactName"));
						client.setContactTitle(rs.getString("ContactTitle"));
						client.setContactAddress(rs.getString("ContactAddress"));
						client.setContactCity(rs.getString("ContactCity"));
						client.setContactState(rs.getString("ContactState"));
						client.setContactZip(rs.getString("ContactZip"));
						client.setContactPhone(rs.getString("ContactPhone"));
						client.setContactFax(rs.getString("ContactFax"));
						client.setContactEmail(rs.getString("ContactEmail"));
						client.setTermStart(rs.getString("TermStart"));
						client.setTermEnd(rs.getString("TermEnd"));
						client.setRenewals(rs.getString("Renewals"));
						client.setNoticeAddress(rs.getString("NoticeAddress"));
						client.setNoticeCity(rs.getString("NoticeCity"));
						client.setNoticeState(rs.getString("NoticeState"));
						client.setNoticeZip(rs.getString("NoticeZip"));
						client.setPaymentTerms(rs.getString("PaymentTerms"));
						client.setPromptPay(rs.getString("PromptPay"));
						client.setEftSetup(rs.getString("EftSetup"));
						client.setInvoiceFrequency(rs.getString("InvoiceFrequency"));
						client.setInvoicingAddressInfo(rs.getString("InvoicingAddressInfo"));
						client.setAdminFee(rs.getString("AdminFee"));
						client.setTravelFees(rs.getString("TravelFees"));
						client.setOvertime(rs.getString("Overtime"));
						client.setContractLengthFrom(rs.getString("ContractLengthFrom"));
						client.setContractLengthTo(rs.getString("ContractLengthTo"));
						client.setTermination(rs.getString("Termination"));
						client.setDisputeTimeframe(rs.getString("DisputeTimeframe"));
						client.setPricingInfo(rs.getString("PricingInfo"));
						client.setInsuranceCertificate(rs.getString("InsuranceCertificate"));
						client.setInsWaiver(rs.getString("InsWaiver"));
						client.setInsWorkmans(rs.getString("InsWorkmans"));
						client.setInsEmployers(rs.getString("InsEmployers"));
						client.setInsCgl(rs.getString("InsCgl"));
						client.setInsAuto(rs.getString("InsAuto"));
						client.setInsUmbrella(rs.getString("InsUmbrella"));
						client.setInsProfessionalErrors(rs.getString("InsProfessionalErrors"));
						client.setInsCancelDays(rs.getString("InsCancelDays"));
						client.setInsPolicyDescription(rs.getString("InsPolicyDescription"));
						client.setInsThirdPartyCrimeBond(rs.getString("InsThirdPartyCrimeBond"));
						client.setInsBondAmount(rs.getString("InsBondAmount"));
						client.setWarrenty(rs.getString("Warrenty"));
						client.setReplacementGuarentee(rs.getString("ReplacementGuarentee"));
						
						client.setMutualConfidentiality(rs.getString("MutualConfidentiality"));
						client.setRightToHire(rs.getString("RightToHire"));
						client.setRightToHireMonths(rs.getString("RightToHireMonths"));
						client.setRightToHireFees(rs.getString("RightToHireFees"));
						client.setNonComplete(rs.getString("NonComplete"));
						client.setNonCompleteInformation(rs.getString("NonCompleteInformation"));
						client.setNonSolicitation(rs.getString("NonSolicitation"));
						client.setNonSolicitationTimeframe(rs.getString("NonSolicitationTimeframe"));
						client.setSubContractingAllowed(rs.getString("SubContractingAllowed"));
						client.setPersonalAssignedForms(rs.getString("PersonalAssignedForms"));
						client.setPersonalForms(rs.getString("PersonalForms"));
						client.setBackgroundCheck(rs.getString("BackgroundCheck"));
						client.setBackgroundCheckInfo(rs.getString("BackgroundCheckInfo"));
						client.setBackgroundCompany(rs.getString("BackgroundCompany"));
						client.setDrugTesting(rs.getString("DrugTesting"));
						client.setDrugTestingInfo(rs.getString("DrugTestingInfo"));
						client.setDrugTestingCompany(rs.getString("DrugTestingCompany"));
						client.setCreditCheck(rs.getString("CreditCheck"));
						client.setCreditCheckInfo(rs.getString("CreditCheckInfo"));
						client.setCreditCompany(rs.getString("CreditCompany"));
						client.setVms(rs.getString("VMS"));
						client.setVmsname(rs.getString("VMSName"));
						client.setCountry(rs.getString("Country"));
						
						client.setUpdatedBy(rs.getString("Updated_By"));
						client.setUpdatedDate(rs.getString("Updated_Date"));
						
						client.setTmContract(rs.getString("tmContract"));
						client.setAmount(rs.getString("amount1"));
						
						client.setChkVolumeDiscount(rs.getString("chkVolumeDiscount"));
						client.setVolumeDiscount(rs.getDouble("volumeDiscount"));
						client.setChkPaymentDiscount(rs.getString("chkPaymentDiscount"));
						client.setPaymentDiscount(rs.getDouble("paymentDiscount"));
						client.setChkOtherDiscount(rs.getString("chkOtherDiscount"));
						client.setOtherDiscount(rs.getDouble("otherDiscount"));
						client.setChkBackgroundAmount(rs.getString("chkBackgroundAmount"));
						client.setBackgroundAmount(rs.getDouble("backgroundAmount"));
						client.setChkCreditCheckAmount(rs.getString("chkCreditCheckAmount"));
						client.setCreditCheckAmount(rs.getDouble("creditCheckAmount"));
						client.setChkDrugTestAmount(rs.getString("chkDrugTestAmount"));
						client.setDrugTestAmount(rs.getDouble("drugTestAmount"));
						
						client.setStatus(rs.getString("Status"));
						client.setTimesheetFrequency(rs.getString("timesheetFrequency"));
						
						client.setVolumeDiscountType1(rs.getString("VolumeDiscountType1"));
						client.setVolumeDiscountType2(rs.getString("VolumeDiscountType2"));
						client.setVolumeDiscountType3(rs.getString("VolumeDiscountType3"));
						client.setVolumeDiscountType4(rs.getString("VolumeDiscountType4"));
						client.setVolumeDiscountType5(rs.getString("VolumeDiscountType5"));
						client.setVolumeDiscountType6(rs.getString("VolumeDiscountType6"));
						client.setVolumeDiscountType7(rs.getString("VolumeDiscountType7"));
						
						client.setVolumeDiscountLowLimit1(rs.getString("VolumeDiscountLowLimit1"));
						client.setVolumeDiscountLowLimit2(rs.getString("VolumeDiscountLowLimit2"));
						client.setVolumeDiscountLowLimit3(rs.getString("VolumeDiscountLowLimit3"));
						client.setVolumeDiscountLowLimit4(rs.getString("VolumeDiscountLowLimit4"));
						client.setVolumeDiscountLowLimit5(rs.getString("VolumeDiscountLowLimit5"));
						client.setVolumeDiscountLowLimit6(rs.getString("VolumeDiscountLowLimit6"));
						client.setVolumeDiscountLowLimit7(rs.getString("VolumeDiscountLowLimit7"));
						
						client.setVolumeDiscountHighLimit1(rs.getString("VolumeDiscountHighLimit1"));
						client.setVolumeDiscountHighLimit2(rs.getString("VolumeDiscountHighLimit2"));
						client.setVolumeDiscountHighLimit3(rs.getString("VolumeDiscountHighLimit3"));
						client.setVolumeDiscountHighLimit4(rs.getString("VolumeDiscountHighLimit4"));
						client.setVolumeDiscountHighLimit5(rs.getString("VolumeDiscountHighLimit5"));
						client.setVolumeDiscountHighLimit6(rs.getString("VolumeDiscountHighLimit6"));
						client.setVolumeDiscountHighLimit7(rs.getString("VolumeDiscountHighLimit7"));
						
						client.setVolumeDiscountAmount1(rs.getDouble("VolumeDiscountAmount1"));
						client.setVolumeDiscountAmount2(rs.getDouble("VolumeDiscountAmount2"));
						client.setVolumeDiscountAmount3(rs.getDouble("VolumeDiscountAmount3"));
						client.setVolumeDiscountAmount4(rs.getDouble("VolumeDiscountAmount4"));
						client.setVolumeDiscountAmount5(rs.getDouble("VolumeDiscountAmount5"));
						client.setVolumeDiscountAmount6(rs.getDouble("VolumeDiscountAmount6"));
						client.setVolumeDiscountAmount7(rs.getDouble("VolumeDiscountAmount7"));
						
						client.setDiscountRemarks(rs.getString("discountRemarks"));
						
						client.setTenureDiscountLowLimit1(rs.getString("TenureDiscountLowLimit1"));
						client.setTenureDiscountLowLimit2(rs.getString("TenureDiscountLowLimit2"));
						client.setTenureDiscountLowLimit3(rs.getString("TenureDiscountLowLimit3"));
						client.setTenureDiscountLowLimit4(rs.getString("TenureDiscountLowLimit4"));
						client.setTenureDiscountLowLimit5(rs.getString("TenureDiscountLowLimit5"));
						
						client.setTenureDiscountHighLimit1(rs.getString("TenureDiscountHighLimit1"));
						client.setTenureDiscountHighLimit2(rs.getString("TenureDiscountHighLimit2"));
						client.setTenureDiscountHighLimit3(rs.getString("TenureDiscountHighLimit3"));
						client.setTenureDiscountHighLimit4(rs.getString("TenureDiscountHighLimit4"));
						client.setTenureDiscountHighLimit5(rs.getString("TenureDiscountHighLimit5"));
						
						client.setTenureDiscountAmount1(rs.getDouble("TenureDiscountAmount1"));
						client.setTenureDiscountAmount2(rs.getDouble("TenureDiscountAmount2"));
						client.setTenureDiscountAmount3(rs.getDouble("TenureDiscountAmount3"));
						client.setTenureDiscountAmount4(rs.getDouble("TenureDiscountAmount4"));
						client.setTenureDiscountAmount5(rs.getDouble("TenureDiscountAmount5"));
						
						client.setClientIndustry(rs.getString("ClientIndustry"));
						
						client.setTotalPTODays(rs.getInt("TotalPTODays"));						
						try{
							client.setBillablePTO(Double.parseDouble(rs.getString("BillablePTO")));
						} catch(Exception ex){
							client.setBillablePTO(0.0);
						}
						
						try{
							client.setNonBillablePTO(Double.parseDouble(rs.getString("NonBillablePTO")));
						} catch(Exception ex){
							client.setNonBillablePTO(0.0);
						}
						
						client.setTotalHolidays(rs.getInt("TotalHolidays"));
						try{
							client.setBillableHolidays(Double.parseDouble(rs.getString("BillableHolidays")));
						} catch(Exception ex){
							client.setBillableHolidays(0.0);
						}
						
						try{
							client.setNonBillableHolidays(Double.parseDouble(rs.getString("NonBillableHolidays")));
						} catch(Exception ex){
							client.setNonBillableHolidays(0.0);
						}
						
						client.setpSVerticalId(rs.getInt("PSVerticalId"));
						client.setpSVerticalCode(rs.getString("PSVerticalCode"));
						client.setVerticalDescription(rs.getString("VerticalDescription"));
						
						client.setpSProductId(rs.getInt("PSProductId"));
						client.setpSProductCode(rs.getString("PSProductCode"));
						client.setProductDescription(rs.getString("ProductDescription"));
						
						client.setpSBillingTypeId(rs.getInt("PSBillingTypeId"));
						client.setpSBillingTypeCode(rs.getString("PSBillingTypeCode"));
						client.setpSBillingTypeLongName(rs.getString("PSBillingTypeLongName"));
						
						client.setSickLeaveType(rs.getString("SickLeaveType"));
						client.setEntityName(rs.getString("EntityName"));
						client.setSetId(rs.getString("SetId"));
						
						client.setpSClientId(rs.getString("PSClientId"));
						client.setPortfolio(rs.getString("Portfolio"));
						client.setCompanyCode(rs.getString("CompanyCode"));
						
						System.out.println("client.getClientIndustry() = " + client.getClientIndustry());
						System.out.println("client.getSetId() = " + client.getSetId());
						System.out.println("client.getCountry() = " + client.getCountry());
						System.out.println("client.getpSClientId() = " + client.getpSClientId());
						System.out.println("client.getCompanyCode() = " + client.getCompanyCode());
						logger.debug("client.getClientIndustry() = " + client.getClientIndustry());
						logger.debug("client.getSetId() = " + client.getSetId());
						logger.debug("client.getCountry() = " + client.getCountry());
						logger.debug("client.getpSClientId() = " + client.getpSClientId());
						logger.debug("client.getClientIndustry() = " + client.getClientIndustry());
					}
				}
				else
				{
					client = null;
				}
			}
			catch (Exception e)
			{
				e.printStackTrace();
				System.out.println("Exception In AnalyserClientRepositoryCustomImpl --> static class AnalyserClientInfoWork --> execut " + e.toString());
				logger.debug("Exception In AnalyserClientRepositoryCustomImpl --> static class AnalyserClientInfoWork --> execut " + e.toString());
			}
			finally
			{
				if (rs != null) rs.close();
				if (callStmt != null) callStmt.close();
			}
		}
		
		/**
		 * @return the client
		 */
		public AnalyserClientDatabaseDTO getClient()
		{
			return client;
		}
		
	}
	
	@Override
	public String addUpdateAnalyserClientDatabase(AnalyserClientDatabaseDTO client, String userId, int actionType)
	{
		String response = "";
		Session session = entityManager.unwrap(Session.class);
		// String action = CLIENT_ADD_UPDATE;
		
		AnalyserClientDatabaseWork work = new AnalyserClientDatabaseWork(client, userId, actionType);
		session.doWork(work);
		response = work.getResult();
		return response;
	}
	
	private static class AnalyserClientDatabaseWork implements Work
	{
		
		private String result;
		private Logger loggerAnalyserClientWork = LoggerFactory.getLogger(AnalyserClientDatabaseWork.class);
		AnalyserClientDatabaseDTO client;
		int actionType;
		String userId;
		
		public AnalyserClientDatabaseWork(AnalyserClientDatabaseDTO client, String userId, int actionType)
		{
			this.client = client;
			this.userId = userId;
			this.actionType = actionType;
		}
		
		public String getResult()
		{
			return result;
		}
		
		@Override
		public void execute(Connection connection) throws SQLException
		{
			
			try
			{
				ResultSet rs = null;
				CallableStatement callStmt = null;
				
				String query = null;
				int intClientDatabaseId = 0;
				String analyserClientDatabaseId = client.getClientId();
				intClientDatabaseId = Integer.parseInt(analyserClientDatabaseId);
				
				query = "{call " + FacesUtils.SCHEMA_WIRELESS + ".spAddUpdateAnalyserClientDatabaseNew ( " + actionType + ",'" + userId 
						+ "'," + intClientDatabaseId
						+ ",'" + client.getCompanyName() + "','" + client.getTimesheetFrequency() + "','" + client.getAdminFee() 
				        + "','" + client.getPromptPay() + "'," + client.getPaymentDiscount() + "," + client.getVolumeDiscount()
				        + ", '" + Util.removeSingleQuote(client.getDiscountRemarks()) + "'," + client.getOtherDiscount() 
				        + ", " + client.getTotalPTODays() + ", " + client.getBillablePTO() + ", "+ client.getNonBillablePTO()
				        + ", " + client.getTotalHolidays() + ", " + client.getBillableHolidays() + ", "+ client.getNonBillableHolidays()
				        
				        + ", " + client.getpSVerticalId()+ ", '" + client.getpSVerticalCode() + "', '"+ client.getVerticalDescription()
				        + "', " + client.getpSProductId() + ", '" + client.getpSProductCode() + "', '"+ client.getProductDescription()
				        + "', " + client.getpSBillingTypeId() + ", '" + client.getpSBillingTypeCode() + "', '"+ client.getpSBillingTypeLongName()
						
				        + "', '" + client.getSickLeaveType() + "', '" + client.getEntityName() + "', '" + client.getCompanyCode() + "' )}";
						
				System.out.println("Executing Query in spAddUpdateAnalyserClientDatabaseNew of :  " + query);
				loggerAnalyserClientWork.debug("Executing Query in spAddUpdateAnalyserClientDatabaseNew of :  " + query);
				
				callStmt = connection.prepareCall(query);
				rs = callStmt.executeQuery();
				if (rs != null)
				{
					while (rs.next())
					{
						result = "" + rs.getInt(1);
					}
					callStmt.close();
					rs.close();
					callStmt = null;
					rs = null;
				}
				else
				{
					throw new Exception("ClientDatabase could not be updated");
				}
			}
			catch (Exception ex)
			{
				ex.printStackTrace();
			}
		}
		
	}
	
	@Override
	public String changeClientStatus(String userId, Integer clientId, Integer status)
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
				
				query = "{call " + FacesUtils.SCHEMA_WIRELESS + ".spSetClientStatus('" + userId + "', " + clientId + ", " + status + ")}";
				
				System.out.println("Executing Query in changeClientStatus -- :  " + query);
				logger.debug("Executing Query in changeClientStatus -- :  " + query);
				System.out.println("In DBHelper Client id = " + clientId);
				System.out.println("In DBHelper Status = " + status);
				callStmt = con.prepareCall(query);
				res = callStmt.executeUpdate();
				
				if (res != 0) // return 0 if success or "" otherwise
				    result = "";
				
				/*
				con.close();
				callStmt.close();
				if (rs != null)
				{
					rs.close();
				}
				con = null;
				callStmt = null;
				rs = null;
				*/
			}
			else
			{
				throw new Exception("Connection is null");
			}
		}
		catch (Exception ex)
		{
			System.out.println("Exception in changeClientStatus method of AnalyserClientRepositoryImpl");
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
	public String changeClientSiteStatus(String userId, Integer clientId, Integer siteId, Integer status)
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
				
				query = "{call " + FacesUtils.SCHEMA_WIRELESS + ".spSetClientSiteStatus('" + userId + "', " + clientId + ", " + siteId + ", " + status
				        + ")}";
				
				System.out.println("Executing Query in spSetClientAddressStatus -- :  " + query);
				logger.debug("Executing Query in spSetClientAddressStatus -- :  " + query);
				System.out.println("In DBHelper Client id = " + clientId);
				System.out.println("In DBHelper siteId id = " + siteId);
				System.out.println("In DBHelper Status = " + status);
				callStmt = con.prepareCall(query);
				res = callStmt.executeUpdate();
				
				if (res != 0) // return 0 if success or "" otherwise
				    result = "";
				
				/*
				con.close();
				callStmt.close();
				if (rs != null)
				{
					rs.close();
				}
				con = null;
				callStmt = null;
				rs = null;
				*/
			}
			else
			{
				throw new Exception("Connection is null");
			}
		}
		catch (Exception ex)
		{
			System.out.println("Exception in changeClientSiteStatus method of AnalyserClientRepositoryImpl");
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
	public String changeClientAddressStatus(String userId, Integer clientId, Integer addressId, Integer status)
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
				
				query = "{call " + FacesUtils.SCHEMA_WIRELESS + ".spSetClientAddressStatus('" + userId + "', " + clientId + ", " + addressId + ", "
				        + status + ")}";
				
				System.out.println("Executing Query in spSetClientAddressStatus -- :  " + query);
				logger.debug("Executing Query in spSetClientAddressStatus -- :  " + query);
				System.out.println("In DBHelper Client id = " + clientId);
				System.out.println("In DBHelper addressId id = " + addressId);
				System.out.println("In DBHelper Status = " + status);
				callStmt = con.prepareCall(query);
				res = callStmt.executeUpdate();
				
				if (res != 0) // return 0 if success or "" otherwise
				    result = "";
				
				/*
				con.close();
				callStmt.close();
				if (rs != null)
				{
					rs.close();
				}
				con = null;
				callStmt = null;
				rs = null;
				*/
			}
			else
			{
				throw new Exception("Connection is null");
			}
		}
		catch (Exception ex)
		{
			System.out.println("Exception in changeClientAddressStatus method of AnalyserClientRepositoryImpl");
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
	public String getTotalDiscount(String userId, Integer clientId)
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
				
				query = "{call " + FacesUtils.SCHEMA_WIRELESS + ".spGetAnalyserClientTotalDiscount('" + userId + "', " + clientId + ")}";
				
				System.out.println("Executing Query to getTotalDiscount -- :  " + query);
				logger.debug("Executing Query to getTotalDiscount -- :  " + query);
				System.out.println("In DBHelper Client id = " + clientId);
				System.out.println("In DBHelper userId  = " + userId);
				callStmt = con.prepareCall(query);
				rs = callStmt.executeQuery();
				if(rs != null){
					while(rs.next()){
						result = rs.getString("TotalDiscount");
					}
				}
				
				/*
				con.close();
				callStmt.close();
				if (rs != null)
				{
					rs.close();
				}
				con = null;
				callStmt = null;
				rs = null;
				*/
			}
			else
			{
				throw new Exception("Connection is null");
			}
		}
		catch (Exception ex)
		{
			System.out.println("Exception in getTotalDiscount method of AnalyserClientRepositoryImpl");
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
	public List<PSOperatingUnitOrVertical> getPSVerticals(String userId, Integer pSVerticalId, Integer fetchAll) {
		Connection con = null;
		CallableStatement callStmt = null;
		ResultSet rs = null;
		List<PSOperatingUnitOrVertical> verticals = new ArrayList<PSOperatingUnitOrVertical>();
		try
		{	
			con = DBConnection.getConnection();
			if (con != null)
			{
				String query = null;
				
				query = "{call " + FacesUtils.SCHEMA_DBO + ".getAllVerticalList('" + userId + "', " + pSVerticalId + ","+ fetchAll+")}";
				
				System.out.println("Executing Query to getPSVerticals -- :  " + query);
				logger.debug("Executing Query to getPSVerticals -- :  " + query);
				System.out.println("In DBHelper pSVerticalId = " + pSVerticalId);
				System.out.println("In DBHelper userId  = " + userId);
				callStmt = con.prepareCall(query);
				rs = callStmt.executeQuery();
				PSOperatingUnitOrVertical obj = new PSOperatingUnitOrVertical();
				if(rs != null){
					while(rs.next()){
						obj = new PSOperatingUnitOrVertical();
						obj.setPSVerticalCode(rs.getString("PSVerticalCode"));
						obj.setPSVerticalId(rs.getInt("PSVerticalId"));
						obj.setVerticalDescription(rs.getString("VerticalDescription"));
						verticals.add(obj);
					}
				}
				/*
				con.close();
				callStmt.close();
				if (rs != null)
				{
					rs.close();
				}
				con = null;
				callStmt = null;
				rs = null;
				*/
			}
			else
			{
				throw new Exception("Connection is null");
			}
		}
		catch (Exception ex)
		{
			System.out.println("Exception in getPSVerticals method of AnalyserClientRepositoryImpl");
			ex.printStackTrace();
			return verticals;
		}
		finally
		{
			DBConnection.closeConnection(con, callStmt, rs);
		}
		return verticals;
	}
	
	@Override
	public List<PSProduct> getPSProducts(String userId, Integer pSProductId, Integer fetchAll){
		Connection con = null;
		CallableStatement callStmt = null;
		ResultSet rs = null;
		List<PSProduct> products = new ArrayList<PSProduct>();
		try
		{	
			con = DBConnection.getConnection();
			if (con != null)
			{
				String query = null;
				
				query = "{call " + FacesUtils.SCHEMA_DBO + ".getAllPSProducts('" + userId + "', " + pSProductId + ","+ fetchAll+")}";
				
				System.out.println("Executing Query to getPSProduct -- :  " + query);
				logger.debug("Executing Query to getPSProduct -- :  " + query);
				System.out.println("In DBHelper pSProductId = " + pSProductId);
				System.out.println("In DBHelper userId  = " + userId);
				callStmt = con.prepareCall(query);
				rs = callStmt.executeQuery();
				PSProduct obj = new PSProduct();
				if(rs != null){
					while(rs.next()){
						obj = new PSProduct();
						obj.setPSProductId(rs.getInt("PSProductId"));
						obj.setPSProductCode(rs.getString("PSProductCode"));
						obj.setProductDescription(rs.getString("ProductDescription"));
						products.add(obj);
					}
				}
				/*
				con.close();
				callStmt.close();
				if (rs != null)
				{
					rs.close();
				}
				con = null;
				callStmt = null;
				rs = null;
				*/
			}
			else
			{
				throw new Exception("Connection is null");
			}
		}
		catch (Exception ex)
		{
			System.out.println("Exception in getPSProducts method of AnalyserClientRepositoryImpl");
			ex.printStackTrace();
			return products;
		}
		finally
		{
			DBConnection.closeConnection(con, callStmt, rs);
		}
		return products;
	}
	
	@Override
	public List<PSBillingType> getPSBillingTypes(String userId, Integer pSBillingTypeId, Integer fetchAll){
		Connection con = null;
		CallableStatement callStmt = null;
		ResultSet rs = null;
		List<PSBillingType> billingTypes = new ArrayList<PSBillingType>();
		try
		{	
			con = DBConnection.getConnection();
			if (con != null)
			{
				String query = null;
				
				query = "{call " + FacesUtils.SCHEMA_DBO + ".getAllPSBillingTypes('" + userId + "', " + pSBillingTypeId + ","+ fetchAll+")}";
				
				System.out.println("Executing Query to getAllPSBillingTypes -- :  " + query);
				logger.debug("Executing Query to getAllPSBillingTypes -- :  " + query);
				System.out.println("In DBHelper pSBillingTypeId = " + pSBillingTypeId);
				System.out.println("In DBHelper userId  = " + userId);
				callStmt = con.prepareCall(query);
				rs = callStmt.executeQuery();
				PSBillingType obj = new PSBillingType();
				if(rs != null){
					while(rs.next()){
						obj = new PSBillingType();
						obj.setPSBillingTypeCode(rs.getString("PSBillingTypeCode"));
						obj.setPSBillingTypeId(rs.getInt("PSBillingTypeId"));
						obj.setPSBillingTypeLongName(rs.getString("PSBillingTypeLongName"));
						billingTypes.add(obj);
					}
				}
				/*
				con.close();
				callStmt.close();
				if (rs != null)
				{
					rs.close();
				}
				con = null;
				callStmt = null;
				rs = null;
				*/
			}
			else
			{
				throw new Exception("Connection is null");
			}
		}
		catch (Exception ex)
		{
			System.out.println("Exception in getPSBillingTypes method of AnalyserClientRepositoryImpl");
			ex.printStackTrace();
			return billingTypes;
		}
		finally
		{
			DBConnection.closeConnection(con, callStmt, rs);
		}
		return billingTypes;
	}
	
	
	@Override
	public List<AnalyserClientDTO> getAnalyserClientList(String userId, String companyCode) 
	{
		Session session = entityManager.unwrap(Session.class);
		AnalyserClientRepositoryWork work = new AnalyserClientRepositoryWork(userId, companyCode);	
		session.doWork(work);
		List<AnalyserClientDTO> list = work.getList();
		return list;
	}
	
	private static class AnalyserClientRepositoryWork implements Work {
		public Logger logger = LoggerFactory.getLogger(getClass());
		private List<AnalyserClientDTO> list;
		private AnalyserClientDTO analyserClientDTO;
		String userId;
		String companyCode;
		String recordCode=Constants.STRING_CONSTANT_ALL;
		CallableStatement cstmt = null;
		ResultSet rs = null;

		/**
		 * @param userId
		 */
		public AnalyserClientRepositoryWork(String userId, String companyCode) {
			super();
			this.userId = userId;
			this.companyCode=companyCode;
		}

		@Override
		public void execute(Connection connection) throws SQLException {

			list = new ArrayList<AnalyserClientDTO>();
			analyserClientDTO = new AnalyserClientDTO();

				String query = "{call " + FacesUtils.SCHEMA_DBO
						+ ".spGetClientListNew('" + userId + "','" + companyCode + "','"+recordCode+"')}";

				System.out.println("Query in AnalyserClientRepositoryWork " + query);

				logger.debug("Query in AnalyserClientRepositoryWork " + query);

				cstmt = connection.prepareCall(query);
				rs = cstmt.executeQuery();

			try {
					if (rs != null) 
					{
						while (rs.next()) 
						{	
							analyserClientDTO.setClientId(rs.getInt("CLIENT_ID"));
							analyserClientDTO.setCompany(rs.getString("COMPANY"));
							analyserClientDTO.setCompanyCode(rs.getString("CompanyCode"));
							list.add(analyserClientDTO);
							analyserClientDTO = new AnalyserClientDTO();
						}
					} 
					else 
					{
						list = new ArrayList<AnalyserClientDTO>();
					}
					System.out.println("List size in AnalyserClientRepositoryWork " + list.size());
					logger.debug("List size in AnalyserClientRepositoryWork " + list.size());
			} 
			catch (Exception e) 
			{
				System.out.println("Exception in AnalyserClientRepositoryWork --> execute.");
				logger.debug("Exception in AnalyserClientRepositoryWork --> execute.");
				e.printStackTrace();
			} 
			finally 
			{
				if (rs != null)
					rs.close();
				if (cstmt != null)
					cstmt.close();
			}
		}

		/**
		 * @return the list
		 */
		public List<AnalyserClientDTO> getList() {
			return list;
		}

	}
	

	@Override
	public List<AnalyserClientDTO> getAnalyserClientDescription(String userId, String companyCode, String recordCode) 
	{
		Session session = entityManager.unwrap(Session.class);
		AnalyserClientDescriptionRepositoryWork work = new AnalyserClientDescriptionRepositoryWork(userId, companyCode, recordCode);	
		session.doWork(work);
		List<AnalyserClientDTO> list = work.getList();
		return list;
	}
	
	private static class AnalyserClientDescriptionRepositoryWork implements Work {
		public Logger logger = LoggerFactory.getLogger(getClass());
		private List<AnalyserClientDTO> list;
		private AnalyserClientDTO analyserClientDTO;
		String userId;
		String companyCode;
		String recordCode;
		CallableStatement cstmt = null;
		ResultSet rs = null;

		/**
		 * @param userId
		 */
		public AnalyserClientDescriptionRepositoryWork(String userId, String companyCode, String recordCode) {
			super();
			this.userId = userId;
			this.companyCode=companyCode;
			this.recordCode=recordCode;
		}

		@Override
		public void execute(Connection connection) throws SQLException {

			list = new ArrayList<AnalyserClientDTO>();
			analyserClientDTO = new AnalyserClientDTO();

				String query = "{call " + FacesUtils.SCHEMA_DBO
						+ ".spGetClientDescription('" + userId + "','" + companyCode + "','"+recordCode+"')}";

				System.out.println("Query in AnalyserClientDescriptionRepositoryWork " + query);

				logger.debug("Query in AnalyserClientDescriptionRepositoryWork " + query);

				cstmt = connection.prepareCall(query);
				rs = cstmt.executeQuery();

			try {
					if (rs != null) 
					{
						while (rs.next()) 
						{
							analyserClientDTO.setCompany(rs.getString("COMPANY").isEmpty()?rs.getString("COMPANY"):rs.getString("COMPANY"));														
							list.add(analyserClientDTO);
							analyserClientDTO = new AnalyserClientDTO();
						}
					} 
					else 
					{
						list = new ArrayList<AnalyserClientDTO>();
					}
					System.out.println("List size in AnalyserClientDescriptionRepositoryWork " + list.size());
					logger.debug("List size in AnalyserClientDescriptionRepositoryWork " + list.size());
			} 
			catch (Exception e) 
			{
				System.out.println("Exception in AnalyserClientDescriptionRepositoryWork --> execute.");
				logger.debug("Exception in AnalyserClientDescriptionRepositoryWork --> execute.");
				e.printStackTrace();
			} 
			finally 
			{
				if (rs != null)
					rs.close();
				if (cstmt != null)
					cstmt.close();
			}
		}

		/**
		 * @return the list
		 */
		public List<AnalyserClientDTO> getList() {
			return list;
		}

	
	}
}