package com.disys.analyzer.repository.custom.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
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
import com.disys.analyzer.config.util.Constants;
import com.disys.analyzer.config.util.FacesUtils;
import com.disys.analyzer.model.dto.BullhornBatchDataProcessedDTO;
import com.disys.analyzer.repository.custom.BullhornBatchDataProcessedRepositoryCustom;

/**
 * @author Sajid
 * 
 */

@Repository
@Transactional(readOnly = true)
public class BullhornBatchDataProcessedRepositoryImpl implements BullhornBatchDataProcessedRepositoryCustom{

	@PersistenceContext
	EntityManager entityManager;


	
	/* (non-Javadoc)
	 * @see com.disys.analyzer.repository.custom.DealTypeRepositoryCustom#getDealType()
	 */
	@Override
	public List<BullhornBatchDataProcessedDTO> getBullhornBatchDataProcessedList(String userId,
			Integer bullhornBatchInfoId) 
	{
		Session session = entityManager.unwrap(Session.class);
		BullhornBatchDataProcessedRepositoryWork work = new BullhornBatchDataProcessedRepositoryWork(userId, bullhornBatchInfoId);	
		session.doWork(work);
		List<BullhornBatchDataProcessedDTO> list = work.getList();
		return list;
	}
	
	private static class BullhornBatchDataProcessedRepositoryWork implements Work {
		public Logger logger = LoggerFactory.getLogger(getClass());
		private List<BullhornBatchDataProcessedDTO> list;
		private BullhornBatchDataProcessedDTO bullhornBatchDataProcessedDTO;
		private String userId;
		private Integer bullhornBatchInfoId;
		private String processCode;
		private String batchCode; 
		private String searchString;
		CallableStatement cstmt = null;
		ResultSet rs = null;

		/**
		 * @param userId
		 */
		public BullhornBatchDataProcessedRepositoryWork(String userId, Integer bullhornBatchInfoId) {
			super();
			this.userId = userId;
			this.bullhornBatchInfoId = bullhornBatchInfoId;
		}

		@Override
		public void execute(Connection connection) throws SQLException {

			list = new ArrayList<BullhornBatchDataProcessedDTO>();
			bullhornBatchDataProcessedDTO = new BullhornBatchDataProcessedDTO();

				String query = "{call " + FacesUtils.SCHEMA_DBO
						+ ".spIntegrationGetProcessedDataDetails('" + userId + "','"+ bullhornBatchInfoId +"')}";

				System.out.println("Query in BullhornBatchDataProcessedRepositoryWork " + query);

				logger.debug("Query in BullhornBatchDataProcessedRepositoryWork " + query);

				cstmt = connection.prepareCall(query);
				rs = cstmt.executeQuery();

			try {
					if (rs != null) 
					{
						while (rs.next()) 
						{
							bullhornBatchDataProcessedDTO.setBullhornBatchDataProcessedId(rs.getInt("BullhornBatchDataProcessedId"));
							bullhornBatchDataProcessedDTO.setLastName(rs.getString("LName"));
							bullhornBatchDataProcessedDTO.setFirstName(rs.getString("FName"));
							bullhornBatchDataProcessedDTO.setState(rs.getString("State"));
							bullhornBatchDataProcessedDTO.setEmail(rs.getString("Email"));
							bullhornBatchDataProcessedDTO.setSsn(rs.getString("SSN"));
							bullhornBatchDataProcessedDTO.setDob(rs.getString("DOB"));
							bullhornBatchDataProcessedDTO.setJobTitle(rs.getString("Job_Title"));
							bullhornBatchDataProcessedDTO.setEmployeeType(rs.getString("EmpType"));
							bullhornBatchDataProcessedDTO.setThw(rs.getInt("THW"));
							bullhornBatchDataProcessedDTO.setBillRate(rs.getDouble("BillRate"));
							bullhornBatchDataProcessedDTO.setPayRate(rs.getDouble("PayRate"));
							bullhornBatchDataProcessedDTO.setClientId(rs.getInt("ClientId"));
							bullhornBatchDataProcessedDTO.setPsClientId(rs.getString("PSClientId"));
							bullhornBatchDataProcessedDTO.setStartDate(rs.getString("StartDate"));
							bullhornBatchDataProcessedDTO.setEndDate(rs.getString("EndDate"));
							bullhornBatchDataProcessedDTO.setEffectiveDate(rs.getString("EffectiveDate"));
							bullhornBatchDataProcessedDTO.setPto(rs.getInt("PTO"));
							bullhornBatchDataProcessedDTO.setOffice(rs.getString("Office"));
							bullhornBatchDataProcessedDTO.setHrbpEmplId(rs.getString("HRBPEmplId"));
							bullhornBatchDataProcessedDTO.setLiaisonName(rs.getString("LiaisonName"));
							bullhornBatchDataProcessedDTO.setJobCategory(rs.getString("JobCategory"));
							bullhornBatchDataProcessedDTO.setEmployeeCategory(rs.getString("EmployeeCategory"));
							bullhornBatchDataProcessedDTO.setUnEmployedForTwoMonths(rs.getBoolean("UnEmployedForTwoMonths"));
							bullhornBatchDataProcessedDTO.setConsultantJobBoard(rs.getString("ConsultantJobBoard"));
							bullhornBatchDataProcessedDTO.setGender(rs.getString("Gender"));
							bullhornBatchDataProcessedDTO.setFlsaStatus(rs.getString("FLSAStatus"));
							bullhornBatchDataProcessedDTO.setVertical(rs.getString("VERTICAL"));
							bullhornBatchDataProcessedDTO.setProduct(rs.getString("Product"));
							bullhornBatchDataProcessedDTO.setTravelRequired(rs.getString("TravelRequired"));
							bullhornBatchDataProcessedDTO.setRecruitingClassification(rs.getString("RecruitingClassification"));
							bullhornBatchDataProcessedDTO.setManagingDirectorEmplId(rs.getString("ManagingDirectorEmplId"));
							bullhornBatchDataProcessedDTO.setManagingDirector(rs.getString("MANAGINGDIRECTOR"));
							bullhornBatchDataProcessedDTO.setDealType(rs.getString("DealType"));
							bullhornBatchDataProcessedDTO.setCommEffDate(rs.getString("CommEffDate"));
							bullhornBatchDataProcessedDTO.setAe1EmplId(rs.getString("AE1EmplId"));
							bullhornBatchDataProcessedDTO.setCommisionName1AE1(rs.getString("Commision_Name1_AE1"));
							bullhornBatchDataProcessedDTO.setCommissionModel1AE1(rs.getString("CommissionModel1_AE1"));
							bullhornBatchDataProcessedDTO.setCommisionPercent1AE1(rs.getDouble("Commision_Percent1_AE1"));
							bullhornBatchDataProcessedDTO.setCommissionPersonGrade1AE1(rs.getString("CommissionPersonGrade1_AE1"));
							bullhornBatchDataProcessedDTO.setExecOrphanAE1(rs.getInt("ExecOrphan_AE1"));
							bullhornBatchDataProcessedDTO.setSplitCommissionPercentage1AE1(rs.getInt("SPLITCOMMISSIONPERCENTAGE1_AE1"));
							bullhornBatchDataProcessedDTO.setRec1EmplId(rs.getString("Rec1EmplId"));
							bullhornBatchDataProcessedDTO.setCommisionName2REC1(rs.getString("Commision_Name2_REC1"));
							bullhornBatchDataProcessedDTO.setCommissionModel2REC1(rs.getString("CommissionModel2_REC1"));
							bullhornBatchDataProcessedDTO.setCommisionPercent2REC1(rs.getDouble("Commision_Percent2_REC1"));
							bullhornBatchDataProcessedDTO.setRecruiterOrphanREC1(rs.getInt("RecruiterOrphan_REC1"));
							bullhornBatchDataProcessedDTO.setSplitCommissionPercentage2REC1(rs.getInt("SPLITCOMMISSIONPERCENTAGE2_REC1"));
							bullhornBatchDataProcessedDTO.setInitial(rs.getString("Initial"));
							bullhornBatchDataProcessedDTO.setAddress1(rs.getString("Address1"));
							bullhornBatchDataProcessedDTO.setAddress2(rs.getString("Address2"));
							bullhornBatchDataProcessedDTO.setCity(rs.getString("City"));
							bullhornBatchDataProcessedDTO.setZipcode(rs.getString("Zipcode"));
							bullhornBatchDataProcessedDTO.setTelephone(rs.getString("Telephone"));
							bullhornBatchDataProcessedDTO.setWorkPhone(rs.getString("WorkPhone"));
							bullhornBatchDataProcessedDTO.setMobilePager(rs.getString("Mobile_Pager"));
							bullhornBatchDataProcessedDTO.setWorkEmail(rs.getString("WorkEmail"));
							bullhornBatchDataProcessedDTO.setAe2EmplId(rs.getString("AE2EmplId"));
							bullhornBatchDataProcessedDTO.setCommisionName3AE2(rs.getString("Commision_Name3_AE2"));
							bullhornBatchDataProcessedDTO.setCommissionModel3AE2(rs.getString("CommissionModel3_AE2"));
							bullhornBatchDataProcessedDTO.setCommisionPercent3AE2(rs.getDouble("Commision_Percent3_AE2"));
							bullhornBatchDataProcessedDTO.setCommissionPersonGrade3AE2(rs.getString("CommissionPersonGrade3_AE2"));
							bullhornBatchDataProcessedDTO.setOther1OrphanAE2(rs.getInt("Other1Orphan_AE2"));
							bullhornBatchDataProcessedDTO.setSplitCommissionPercentage3AE2(rs.getInt("SPLITCOMMISSIONPERCENTAGE3_AE2"));
							bullhornBatchDataProcessedDTO.setRec2EmplId(rs.getString("Rec2EmplId"));
							bullhornBatchDataProcessedDTO.setCommisionName4REC2(rs.getString("Commision_Name4_REC2"));
							bullhornBatchDataProcessedDTO.setCommissionModel4REC2(rs.getString("CommissionModel4_REC2"));
							bullhornBatchDataProcessedDTO.setCommisionPercent4REC2(rs.getDouble("Commision_Percent4_REC2"));
							bullhornBatchDataProcessedDTO.setOther2OrphanREC2(rs.getInt("Other2Orphan_REC2"));
							bullhornBatchDataProcessedDTO.setSplitCommissionPercentage4REC2(rs.getInt("SPLITCOMMISSIONPERCENTAGE4_REC2"));
							bullhornBatchDataProcessedDTO.setBillablePTO(rs.getInt("BillablePTO"));
							bullhornBatchDataProcessedDTO.setNonBillablePTO(rs.getInt("NonBillablePTO"));
							bullhornBatchDataProcessedDTO.setTotalHolidays(rs.getDouble("TotalHolidays"));
							bullhornBatchDataProcessedDTO.setBillableHolidays(rs.getInt("BillableHolidays"));
							bullhornBatchDataProcessedDTO.setNonBillableHolidays(rs.getInt("NonBillableHolidays"));
							bullhornBatchDataProcessedDTO.setSickLeaveType(rs.getString("SickLeaveType"));
							bullhornBatchDataProcessedDTO.setOneTimeBill(rs.getDouble("OneTime_Bill"));
							bullhornBatchDataProcessedDTO.setAnnualPay(rs.getDouble("AnnualPay"));
							bullhornBatchDataProcessedDTO.setDoubleTimeBill(rs.getDouble("DoubleTimeBill"));
							bullhornBatchDataProcessedDTO.setSubContractorId(rs.getInt("SubContractorId"));
							bullhornBatchDataProcessedDTO.setPsVendorId(rs.getString("PSVendorId"));
							bullhornBatchDataProcessedDTO.setFlsaReferance(rs.getString("FLSAReferance"));
							bullhornBatchDataProcessedDTO.setBonusEligible(rs.getBoolean("IsBonusEligible"));
							bullhornBatchDataProcessedDTO.setBonusPercentage(rs.getInt("BonusPercentage"));
							bullhornBatchDataProcessedDTO.setEmployeeClass(rs.getString("EMPLOYEECLASS"));
							bullhornBatchDataProcessedDTO.setEquipmentCost(rs.getDouble("EquipmentCost"));
							bullhornBatchDataProcessedDTO.setOneTimePay(rs.getDouble("OneTime_Pay"));
							bullhornBatchDataProcessedDTO.setDoubleTime(rs.getDouble("DoubleTime"));
							bullhornBatchDataProcessedDTO.setComments(rs.getString("Comments"));
							bullhornBatchDataProcessedDTO.setGeoOffice(rs.getString("GeoOffice"));
							bullhornBatchDataProcessedDTO.setEmployeeVeteran(rs.getString("EmployeeVeteran"));
							bullhornBatchDataProcessedDTO.setCheckedReferences(rs.getInt("CheckedReferences"));
							bullhornBatchDataProcessedDTO.setFax(rs.getString("Fax"));
							bullhornBatchDataProcessedDTO.setOtherDollar(rs.getDouble("Other_Dollar"));
							bullhornBatchDataProcessedDTO.setOtherAmountHour(rs.getDouble("OtherAmount_Hour"));
							bullhornBatchDataProcessedDTO.setPerDiem(rs.getDouble("PerDiem"));
							bullhornBatchDataProcessedDTO.setH1(rs.getInt("H1"));
							bullhornBatchDataProcessedDTO.setDentalInsurance(rs.getString("dentalInsurance"));
							bullhornBatchDataProcessedDTO.setChkReferralFee(rs.getInt("chkReferralFee"));
							bullhornBatchDataProcessedDTO.setReferralFeeAmount(rs.getDouble("referralFeeAmount"));
							bullhornBatchDataProcessedDTO.setStdBenefit(rs.getString("StdBenefit"));
							bullhornBatchDataProcessedDTO.setLtdBenefit(rs.getString("LtdBenefit"));
							bullhornBatchDataProcessedDTO.setAnalyzerCategory2Classification(rs.getString("AnalyzerCategory2Classification"));
							bullhornBatchDataProcessedDTO.setSkillCategory(rs.getString("SKILLCATEGORY"));
							bullhornBatchDataProcessedDTO.setImmigrationCost(rs.getDouble("ImmigrationCost"));
							bullhornBatchDataProcessedDTO.setBillableCost(rs.getDouble("BillableCost"));
							bullhornBatchDataProcessedDTO.setProjectCost(rs.getDouble("PROJECTCOST"));
							bullhornBatchDataProcessedDTO.setPortfolio(rs.getString("Portfolio"));
							bullhornBatchDataProcessedDTO.setMspEmplId(rs.getString("MSPEmplId"));
							bullhornBatchDataProcessedDTO.setCommissionPerson5MSP(rs.getString("COMMISSIONPERSON5_MSP"));
							bullhornBatchDataProcessedDTO.setCommissionPercentage5MSP(rs.getDouble("COMMISSIONPERCENTAGE5_MSP"));
							bullhornBatchDataProcessedDTO.setCommission5MSP(rs.getDouble("COMMISSION5_MSP"));
							bullhornBatchDataProcessedDTO.setSiteId(rs.getInt("SiteId"));
							bullhornBatchDataProcessedDTO.setWorksiteAddress1(rs.getString("WorksiteAddress1"));
							bullhornBatchDataProcessedDTO.setWorksiteAddress2(rs.getString("WorksiteAddress2"));
							bullhornBatchDataProcessedDTO.setWorksiteCity(rs.getString("WorksiteCity"));
							bullhornBatchDataProcessedDTO.setWorksiteState(rs.getString("WorksiteState"));
							bullhornBatchDataProcessedDTO.setWorksiteZipCode(rs.getString("WorksiteZipCode"));
							bullhornBatchDataProcessedDTO.setWorksiteManagerFirstName(rs.getString("WorksiteManagerFirstName"));
							bullhornBatchDataProcessedDTO.setWorksiteManagerEmail(rs.getString("WorksiteManagerEmail"));
							bullhornBatchDataProcessedDTO.setWorksiteManagerPhone(rs.getString("WorksiteManagerPhone"));
							bullhornBatchDataProcessedDTO.setWorksiteManagerLastName(rs.getString("WorksiteManagerLastName"));
							bullhornBatchDataProcessedDTO.setClientAddressId(rs.getInt("ClientAddressId"));
							bullhornBatchDataProcessedDTO.setPsCustomerAddressId(rs.getInt("PSCustomerAddressId"));
							bullhornBatchDataProcessedDTO.setAnalyzerCategory1(rs.getString("AnalyzerCategory1"));
							bullhornBatchDataProcessedDTO.setPortfolioDescription(rs.getString("PortfolioDescription"));
							bullhornBatchDataProcessedDTO.setBullhornBatchCode(rs.getString("BullhornBatchCode"));
							bullhornBatchDataProcessedDTO.setTransactionType(rs.getString("TransactionType"));
							bullhornBatchDataProcessedDTO.setBullhornRecordId(rs.getInt("BullhornRecordId"));
//							bullhornBatchDataProcessedDTO.setPlacementId(rs.getInt("PlacementId"));
							bullhornBatchDataProcessedDTO.setEntryDateTime(rs.getTimestamp("EntryDateTime"));
//							bullhornBatchDataProcessedDTO.setRecordProcessingStatus(rs.getString("RecordProcessingStatus"));
							bullhornBatchDataProcessedDTO.setCompanyCode(rs.getString("CompanyCode"));
							bullhornBatchDataProcessedDTO.setProcessed(rs.getBoolean("IsProcessed"));
							bullhornBatchDataProcessedDTO.setImportComments(rs.getString("ImportComments"));
							bullhornBatchDataProcessedDTO.setDealPortfolio1AE1(rs.getString("DealPortfolio1_AE1"));
							bullhornBatchDataProcessedDTO.setDealPortfolio2REC1(rs.getString("DealPortfolio2_REC1"));
							bullhornBatchDataProcessedDTO.setDealPortfolio3AE2(rs.getString("DealPortfolio3_AE2"));
							bullhornBatchDataProcessedDTO.setDealPortfolio4REC2(rs.getString("DealPortfolio4_REC2"));
							bullhornBatchDataProcessedDTO.setRecordAccepted(rs.getBoolean("IsRecordAccepted"));
							bullhornBatchDataProcessedDTO.setAssignedAnalyzerId(rs.getInt("AssignedAnalyzerId"));
							bullhornBatchDataProcessedDTO.setAssignedParentId(rs.getInt("AssignedParentId"));
							bullhornBatchDataProcessedDTO.setAssignedSSN(rs.getString("AssignedSSN"));
							bullhornBatchDataProcessedDTO.setSsnGenerated(rs.getBoolean("IsSSNGenerated"));
							bullhornBatchDataProcessedDTO.setUpdateDateTime(rs.getTimestamp("UpdateDateTime"));
							bullhornBatchDataProcessedDTO.setProcessingDateTime(rs.getTimestamp("ProcessingDateTime"));
							bullhornBatchDataProcessedDTO.setRecordHasErrors(rs.getString("IsRecordHasErrors"));
							bullhornBatchDataProcessedDTO.setBullhornBatchDataRawId(rs.getInt("BullhornBatchDataRawId"));
							
							bullhornBatchDataProcessedDTO.setDataSource(rs.getString("DataSource"));
							bullhornBatchDataProcessedDTO.setCoSellStatus(rs.getString("CoSellStatus"));
							bullhornBatchDataProcessedDTO.setPlacementType(rs.getString("PlacementType"));
							bullhornBatchDataProcessedDTO.setSubmittedByEmplId(rs.getString("SubmittedByEmplId"));
							bullhornBatchDataProcessedDTO.setSubmittedByEmail(rs.getString("SubmittedByEmail"));
							bullhornBatchDataProcessedDTO.setBullhornPlacementId(rs.getInt("BullhornPlacementId"));
							bullhornBatchDataProcessedDTO.setBullhornBatchInfoId(rs.getInt("BullhornBatchInfoId"));
							bullhornBatchDataProcessedDTO.setIsTransferredToAnalyzerStaging(rs.getString("IsTransferredToAnalyzerStaging"));
							bullhornBatchDataProcessedDTO.setTransferToAnalyzerStagingDate(rs.getTimestamp("TransferToAnalyzerStagingDate"));
							bullhornBatchDataProcessedDTO.setIsTransferredToAnalyzer(rs.getString("IsTransferredToAnalyzer"));
							bullhornBatchDataProcessedDTO.setTransferToAnalyzerDate(rs.getTimestamp("TransferToAnalyzerDate"));	
							bullhornBatchDataProcessedDTO.setProcessingComments(rs.getString("ProcessingComments"));
							
							list.add(bullhornBatchDataProcessedDTO);
							bullhornBatchDataProcessedDTO = new BullhornBatchDataProcessedDTO();
						}
					} 
					else 
					{
						list = new ArrayList<BullhornBatchDataProcessedDTO>();
					}
					System.out.println("List size in BullhornBatchDataProcessedRepositoryWork " + list.size());
					logger.debug("List size in BullhornBatchDataProcessedRepositoryWork " + list.size());
			} 
			catch (Exception e) 
			{
				System.out.println("Exception in BullhornBatchDataProcessedRepositoryWork --> execute.");
				logger.debug("Exception in BullhornBatchDataProcessedRepositoryWork --> execute.");
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
		public List<BullhornBatchDataProcessedDTO> getList() {
			return list;
		}
	
	}
}
