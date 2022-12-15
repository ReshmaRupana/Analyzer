/**
 * 
 */
package com.disys.analyzer.jsf.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.apache.commons.lang3.builder.Diff;
import org.apache.commons.lang3.builder.DiffResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import org.springframework.web.jsf.FacesContextUtils;

import com.disys.analyzer.config.util.FacesUtils;
import com.disys.analyzer.model.dto.AnalyserDTO;
import com.disys.analyzer.service.AnalyserService;

/**
 * @author Sajid
 * @since Nov 23, 2018
 */
@ManagedBean
@ViewScoped
public class AnalyserCommissionHistoryBean extends SpringBeanAutowiringSupport implements Serializable
{
	private static final long serialVersionUID = -6620555259347297363L;
	private Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired private AnalyserService service;
	
	private List<AnalyserDTO> list;
	private List<AnalyserDTO> filteredList;
	private List<AnalyserDTO> filteredList1;
	private List<AnalyserDTO> filteredList2;
	private List<AnalyserDTO> filteredList3;
	private List<AnalyserDTO> filteredList4;
	private List<AnalyserDTO> filteredList5;
	private String analyserId;
	List<Map<String, Object>> responseList;
	
	public static Integer SIZE = 300;
	
	@PostConstruct
	public void init()
	{
		try
		{
			
			FacesContextUtils.getRequiredWebApplicationContext(FacesContext.getCurrentInstance()).getAutowireCapableBeanFactory().autowireBean(this);
			
		}
		catch (Exception ex)
		{
			logger.debug(ex.getMessage());
		}
	}
	
	public AnalyserCommissionHistoryBean()
	{
		Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		analyserId = (String) params.get("analyserId");
//		this.filteredList = new ArrayList<AnalyserDTO>(0);
//		this.filteredList1 = new ArrayList<AnalyserDTO>(0);
//		this.filteredList2 = new ArrayList<AnalyserDTO>(0);
//		this.filteredList3 = new ArrayList<AnalyserDTO>(0);
	}
	
	/**
	 * @return the list
	 */
	public List<AnalyserDTO> getList()
	{
		if (list == null)
		{
			list = new ArrayList<AnalyserDTO>();
			try
			{
				list = service.getAnalyserCommissionHistory(FacesUtils.getCurrentUserId(), analyserId, "ALL", "Company");
				logger.debug("{call " + FacesUtils.SCHEMA_WIRELESS + ".spGetCommissionHistory('[0]','[1]', '[2]')}");
				logger.debug("{call " + FacesUtils.SCHEMA_WIRELESS + ".spGetCommissionHistory('" + FacesUtils.getCurrentUserId() + "'," + analyserId
				        + ", ALL,Company)}");
				logger.debug("List size is : " + list.size());
			}
			catch (Exception ex)
			{
				logger.debug("Error fetching list from the spGetCommissionHistory stored procedure ");
			}
		}
		return list;
	}
	
	/**
	 * @param list
	 *            the list to set
	 */
	public void setList(List<AnalyserDTO> list)
	{
		this.list = list;
	}
	
	/**
	 * @return the analyserId
	 */
	public String getAnalyserId()
	{
		return analyserId;
	}
	
	/**
	 * @param analyserId
	 *            the analyserId to set
	 */
	public void setAnalyserId(String analyserId)
	{
		this.analyserId = analyserId;
	}
	
	/**
	 * @return the filteredList
	 */
	public List<AnalyserDTO> getFilteredList()
	{
		return filteredList;
	}
	
	/**
	 * @param filteredList
	 *            the filteredList to set
	 */
	public void setFilteredList(List<AnalyserDTO> filteredList)
	{
		this.filteredList = filteredList;
	}

	public List<AnalyserDTO> getFilteredList1()
	{
		return filteredList1;
	}

	public void setFilteredList1(List<AnalyserDTO> filteredList1)
	{
		this.filteredList1 = filteredList1;
	}

	public List<AnalyserDTO> getFilteredList2()
	{
		return filteredList2;
	}

	public void setFilteredList2(List<AnalyserDTO> filteredList2)
	{
		this.filteredList2 = filteredList2;
	}

	/**
	 * @return the responseList
	 */
	public List<Map<String, Object>> getResponseList() {

		if (responseList == null || responseList.isEmpty())
		{
			list = new ArrayList<AnalyserDTO>();
			try
			{
				list = service.getAnalyserCommissionHistory(FacesUtils.getCurrentUserId(), analyserId, "ALL", "Company");
				String query = "USE [Analyser]\n"
						+ "GO\n"
						+"DECLARE	@return_value int\n"
						+ "EXEC	@return_value = [wireless].[spGetCommissionHistory]\n"
						+ "@VARUSERID = N'"+FacesUtils.getCurrentUserId()+"',\n"
						+ "@VARNAME = N'ALL',\n"
						+ "@VARPARENT = N'"+analyserId+"'\n"
						+ "SELECT	'Return Value' = @return_value\n"
						+ "GO";
				System.out.println(query);
				logger.debug(query);
				responseList = new ArrayList<Map<String,Object>>(list.size());
				AnalyserDTO prevAnalyser = null, nextAnalyser = null;
				Map<String, Object> prevMap = null, nextMap = null;
				if(list.size() == 1) {
					prevAnalyser = list.get(0);
					prevMap = new HashMap<String, Object>(SIZE);
					
					prevMap.put("Employee Category", prevAnalyser.getTemps());
					prevMap.put("Is Rehired", prevAnalyser.getIsRehired());
					prevMap.put("Approval Date", prevAnalyser.getApprovalDate());
					prevMap.put("Approved By Manager", prevAnalyser.getApprovedByManager());
					prevMap.put("One Time Amount", prevAnalyser.getOneTimeAmount());
					prevMap.put("Client ID", prevAnalyser.getClientId());
					prevMap.put("Parent ID", prevAnalyser.getParentId());
					prevMap.put("New Parent ID", prevAnalyser.getNewParentId());
					prevMap.put("Record Status", prevAnalyser.getRecordStatus());
					prevMap.put("Employee Type", prevAnalyser.getEmpType());
					prevMap.put("Gross Profit Percentage", prevAnalyser.getGrossProfitPercentage());
					prevMap.put("AE-1 Name", prevAnalyser.getCommName1());
					prevMap.put("AE-1 Percent", prevAnalyser.getCommPer1());
					prevMap.put("AE-1 Amount", prevAnalyser.getCommision1());
					prevMap.put("AE-1 SPLIT%", prevAnalyser.getSplitCommissionPercentage1());
					prevMap.put("AE-1 Orphan", prevAnalyser.getExecOrphan());
					prevMap.put("RECRUITER-1 NAME", prevAnalyser.getCommName2());
					prevMap.put("RECRUITER-1 Percent", prevAnalyser.getCommPer2());
					prevMap.put("RECRUITER-1 Amount", prevAnalyser.getCommision2());
					prevMap.put("RECRUITER-1 SPLIT%", prevAnalyser.getSplitCommissionPercentage2());
					prevMap.put("RECRUITER-1 Orphan", prevAnalyser.getRecruiterOrphan());
					prevMap.put("AE-2 Name", prevAnalyser.getCommName3());
					prevMap.put("AE-2 Percent", prevAnalyser.getCommPer3());
					prevMap.put("AE-2 Amount", prevAnalyser.getCommision3());
					prevMap.put("AE-2 SPLIT%", prevAnalyser.getSplitCommissionPercentage3());
					prevMap.put("AE-2 Orphan", prevAnalyser.getOther1Orphan());
					prevMap.put("RECRUITER-2 Name", prevAnalyser.getCommName4());
					prevMap.put("RECRUITER-2 Percent", prevAnalyser.getCommPer4());
					prevMap.put("RECRUITER-2 Amount", prevAnalyser.getCommision4());
					prevMap.put("RECRUITER-2 Split%", prevAnalyser.getSplitCommissionPercentage4());
					prevMap.put("RECRUITER-2 Orphan", prevAnalyser.getOther2Orphan());
					prevMap.put("Analyzer ID", prevAnalyser.getAnalyserId());
					prevMap.put("Last Name", prevAnalyser.getlName());
			        prevMap.put("First Name", prevAnalyser.getfName());
			        prevMap.put("Initials", prevAnalyser.getInitial());
			        prevMap.put("Branch", prevAnalyser.getBranch());
			        prevMap.put("Client Name", prevAnalyser.getClientCompany());
			        prevMap.put("Invoice Address", prevAnalyser.getInvoiceAddress());
			        prevMap.put("Client Site", prevAnalyser.getSiteLocation());
			        prevMap.put("SubContractor Name", prevAnalyser.getContCompanyName());
			        prevMap.put("Payment Terms", prevAnalyser.getPaymentTerms());
			        prevMap.put("Profit", prevAnalyser.getProfit());
			        prevMap.put("Start Date", prevAnalyser.getStartDate());
			        prevMap.put("End Date", prevAnalyser.getEndDate());
			        prevMap.put("Submission Date", prevAnalyser.getSubmissionDate());
			        prevMap.put("Comm Eff Date", prevAnalyser.getCommEffDate());
			        prevMap.put("Parent Id", prevAnalyser.getParentId());
			        prevMap.put("Commission Model-1", prevAnalyser.getCommissionModel1());
			        prevMap.put("COMMISSION MODEL-2", prevAnalyser.getCommissionModel2());
			        prevMap.put("TRAVEL REQUIRED", prevAnalyser.getTravelRequired());
			        prevMap.put("Deal Type", prevAnalyser.getDealType());
			        prevMap.put("IMMIGRATION COST", prevAnalyser.getImmigrationCost());
			        prevMap.put("EQUIPMENT COST", prevAnalyser.getEquipmentCost());
			        prevMap.put("Address 1", prevAnalyser.getAddress1());
			        prevMap.put("Address 2", prevAnalyser.getAddress2());
			        prevMap.put("City", prevAnalyser.getCity());
			        prevMap.put("Referral Fee Amount", prevAnalyser.getReferralFeeAmount());
			        prevMap.put("Work Email", prevAnalyser.getWorkEmail());
			        prevMap.put("Personal Email", prevAnalyser.getEmail());
			        prevMap.put("Work Phone", prevAnalyser.getWorkPhone());
			        prevMap.put("Personal Phone", prevAnalyser.getTelephone());
			        prevMap.put("FLSA Status", prevAnalyser.getFlsaStatus());
			        prevMap.put("FLSA Reference", prevAnalyser.getFlsaReference());
			        prevMap.put("Comments", prevAnalyser.getComments());
			        prevMap.put("Client Complete Address", prevAnalyser.getClientCompleteAddress());
			        prevMap.put("Bill Rate", prevAnalyser.getBillRate());
			        prevMap.put("One time bill", prevAnalyser.getOtb());
			        prevMap.put("Pay Rate", prevAnalyser.getPayRate());
			        prevMap.put("Terminate Date", prevAnalyser.getTermDate());
			        prevMap.put("Reason", prevAnalyser.getReason());
			        prevMap.put("Dental Insurance Amount", prevAnalyser.getDentalInsuranceAmount());
			        prevMap.put("Billable Cost", prevAnalyser.getNonBillableCost());
			        prevMap.put("Employee Class", prevAnalyser.getEmployeeClass());
			        prevMap.put("Vertical", prevAnalyser.getVertical());
			        prevMap.put("Annual Salary", prevAnalyser.getAnnualPay());
			        prevMap.put("Liaison Name", prevAnalyser.getLiaisonName());
			        prevMap.put("Discount Rate", prevAnalyser.getDiscountRate());
			        prevMap.put("Total Commission Amount", prevAnalyser.getCommission());
			        prevMap.put("Fringe", prevAnalyser.getTax());
			        prevMap.put("GA Amount", prevAnalyser.getGa());
			        prevMap.put("DOUBLE TIME PAY", prevAnalyser.getDoubleTime());
			        prevMap.put("DOUBLE TIME BILL", prevAnalyser.getDoubleTimeBill());
			        prevMap.put("EFFECTIVE DATE", prevAnalyser.getEffectiveDate());
			        prevMap.put("PAYMENT TERM", prevAnalyser.getPaymentTerms());
			        prevMap.put("WORK PHONE", prevAnalyser.getWorkPhone());
			        prevMap.put("TELEPHONE", prevAnalyser.getTelephone());
			        prevMap.put("CURRENCY", prevAnalyser.getCurrency());
			        prevMap.put("SPLIT", prevAnalyser.getSplit());
			        prevMap.put("DATE OF BIRTH", prevAnalyser.getDob());
			        prevMap.put("SSN", prevAnalyser.getSsn());
			        prevMap.put("EMAIL", prevAnalyser.getEmail());
			        prevMap.put("TITLE", prevAnalyser.getJobTitle());
			        prevMap.put("TOTAL HOURS ESTIMATED", prevAnalyser.getTotHoursWorked());
			        prevMap.put("Product Staff", prevAnalyser.getProduct());
			        prevMap.put("Managing Director", prevAnalyser.getManagingDirector());
			        prevMap.put("ModifiedBy", prevAnalyser.getModifiedBy());
			        
			        prevMap.put("Sick Leave Cost", prevAnalyser.getSickLeaveCost());
			        prevMap.put("False Termination", prevAnalyser.getFalseTermination());
			        prevMap.put("Vertical Sales", prevAnalyser.getCommissionPerson5());
			        prevMap.put("IT-GS-Practice", prevAnalyser.getCommissionPerson6());
			        prevMap.put("IT-GS-Delivery", prevAnalyser.getCommissionPerson7());
			        prevMap.put("IT-GS-BDE", prevAnalyser.getCommissionPerson8());
			        prevMap.put("IT-GS-Proposal", prevAnalyser.getCommissionPerson9());
			        prevMap.put("Commission Percentage 5", prevAnalyser.getCommissionPercentage5());
			        prevMap.put("Commission Percentage 6", prevAnalyser.getCommissionPercentage6());
			        prevMap.put("Commission Percentage 7", prevAnalyser.getCommissionPercentage7());
			        prevMap.put("Commission Percentage 8", prevAnalyser.getCommissionPercentage8());
			        prevMap.put("Commission Percentage 9", prevAnalyser.getCommissionPercentage9());
			        
			        prevMap.put("Commission 5 Amount", prevAnalyser.getCommission5());
			        prevMap.put("Commission 6 Amount", prevAnalyser.getCommission6());
			        prevMap.put("Commission 7 Amount", prevAnalyser.getCommission7());
			        prevMap.put("Commission 8 Amount", prevAnalyser.getCommission8());
			        prevMap.put("Commission 9 Amount", prevAnalyser.getCommission9());
			        
					prevMap.put("Project Cost", prevAnalyser.getProjectCost());
					prevMap.put("Sick Leave Per Hour Rate", prevAnalyser.getSickLeavePerHourRate());
					prevMap.put("Sick Leave Cap", prevAnalyser.getSickLeaveCap());
					prevMap.put("Reason", prevAnalyser.getReason());
					prevMap.put("Sick Leave Type", prevAnalyser.getSickLeaveType());
					prevMap.put("PTO", prevAnalyser.getRdoPTO());
					prevMap.put("Leave", prevAnalyser.getLeave());
					prevMap.put("Total Holidays", prevAnalyser.getTotalHolidays());
					
					prevMap.put("Gross Profit", prevAnalyser.getGrossProfit());
					prevMap.put("Spread Week", prevAnalyser.getSpreadWeek());
					prevMap.put("Discount Rate", prevAnalyser.getDiscountRate());
					prevMap.put("Discount", prevAnalyser.getDiscount());
					prevMap.put("FLSA Status", prevAnalyser.getFlsaStatus());
					prevMap.put("PTO Limit To Override Sick", prevAnalyser.getpTOLimitToOverrideSick());
					prevMap.put("Recruiting Classification", prevAnalyser.getRecruitingClassification());
					
					prevMap.put("BILLABLEPTO", prevAnalyser.getBillablePTO());
					prevMap.put("NONBILLABLEPTO", prevAnalyser.getNonBillablePTO());
					prevMap.put("BILLABLEPTOCOST", prevAnalyser.getNonBillablePTOCost());
					prevMap.put("NONBILLABLEPTOCOST", prevAnalyser.getNonBillablePTOCost());
					prevMap.put("TOTALHOLIDAYS", prevAnalyser.getTotalHolidays());
					prevMap.put("BILLABLEHOLIDAYS", prevAnalyser.getBillableHolidays());
					prevMap.put("NONBILLABLEHOLIDAYS", prevAnalyser.getNonBillableHolidays());
					prevMap.put("BILLABLEHOLIDAYSCOST", prevAnalyser.getBillableHolidaysCost());
					prevMap.put("NONBILLABLEHOLIDAYSCOST", prevAnalyser.getNonBillableHolidaysCost());
					prevMap.put("GROSSPROFITPERCENTAGE", prevAnalyser.getGrossProfitPercentage());
					prevMap.put("COMMISSIONABLEPROFIT", prevAnalyser.getCommissionableProfit());
					prevMap.put("REJECTIONSTATUS", prevAnalyser.getRejectionStatus());
					prevMap.put("REJECTIONREASON", prevAnalyser.getRejectionReason());
					prevMap.put("REJECTEDBY", prevAnalyser.getRejectedBy());
					prevMap.put("REJECTIONDATE", prevAnalyser.getRejectionDate());
					prevMap.put("GEOOFFICE", prevAnalyser.getGeoOffice());
					
					prevMap.put("SubContractor Id", prevAnalyser.getContractorId());
			        prevMap.put("Site Id",prevAnalyser.getClientSiteId());
			        prevMap.put("Client Address Id",prevAnalyser.getClientAddressId());
			        
			        prevMap.put("Portfolio Code", prevAnalyser.getPortfolio());
			        prevMap.put("Portfolio Description", prevAnalyser.getPortfolioDescription());
			        prevMap.put("CompanyCode", prevAnalyser.getCompanyCode());
			        prevMap.put("BullhornPlacementId", prevAnalyser.getBullhornPlacementId());
			        prevMap.put("DealActivityAmount", prevAnalyser.getDealActivityAmount());			        
			        prevMap.put("DealPortfolio1AE1", prevAnalyser.getDealPortfolio1AE1());
			        prevMap.put("DealPortfolio2REC1", prevAnalyser.getDealPortfolio2REC1());
			        prevMap.put("DealPortfolio3AE2", prevAnalyser.getDealPortfolio3AE2());
			        prevMap.put("DealPortfolio4REC2", prevAnalyser.getDealPortfolio4REC2());
			        
					responseList.add(prevMap);
				}
				else 
				{
					int index = 0;
					responseList = new ArrayList<Map<String,Object>>(list.size());
					Map<String, Object> map;
					while((index + 1) < list.size())
					{
						prevAnalyser = list.get(index);
						nextAnalyser = list.get(index + 1);
						prevMap = new HashMap<String, Object>(SIZE);
						nextMap = new HashMap<String, Object>(SIZE);
						map = new HashMap<String, Object>(SIZE);
						
						prevMap.put("BILLABLEPTO", prevAnalyser.getBillablePTO());
						prevMap.put("NONBILLABLEPTO", prevAnalyser.getNonBillablePTO());
						prevMap.put("BILLABLEPTOCOST", prevAnalyser.getNonBillablePTOCost());
						prevMap.put("NONBILLABLEPTOCOST", prevAnalyser.getNonBillablePTOCost());
						prevMap.put("TOTALHOLIDAYS", prevAnalyser.getTotalHolidays());
						prevMap.put("BILLABLEHOLIDAYS", prevAnalyser.getBillableHolidays());
						prevMap.put("NONBILLABLEHOLIDAYS", prevAnalyser.getNonBillableHolidays());
						prevMap.put("BILLABLEHOLIDAYSCOST", prevAnalyser.getBillableHolidaysCost());
						prevMap.put("NONBILLABLEHOLIDAYSCOST", prevAnalyser.getNonBillableHolidaysCost());
						prevMap.put("GROSSPROFITPERCENTAGE", prevAnalyser.getGrossProfitPercentage());
						prevMap.put("COMMISSIONABLEPROFIT", prevAnalyser.getCommissionableProfit());
						prevMap.put("REJECTIONSTATUS", prevAnalyser.getRejectionStatus());
						prevMap.put("REJECTIONREASON", prevAnalyser.getRejectionReason());
						prevMap.put("REJECTEDBY", prevAnalyser.getRejectedBy());
						prevMap.put("REJECTIONDATE", prevAnalyser.getRejectionDate());
						prevMap.put("GEOOFFICE", prevAnalyser.getGeoOffice());
						
						prevMap.put("Employee Category", prevAnalyser.getTemps());
						prevMap.put("Is Rehired", prevAnalyser.getIsRehired());
						prevMap.put("Approval Date", prevAnalyser.getApprovalDate());
						prevMap.put("Approved By Manager", prevAnalyser.getApprovedByManager());
						prevMap.put("One Time Amount", prevAnalyser.getOneTimeAmount());
						prevMap.put("Client ID", prevAnalyser.getClientId());
						prevMap.put("Parent ID", prevAnalyser.getParentId());
						prevMap.put("New Parent ID", prevAnalyser.getNewParentId());
						prevMap.put("Record Status", prevAnalyser.getRecordStatus());
						prevMap.put("Employee Type", prevAnalyser.getEmpType());
						prevMap.put("Gross Profit Percentage", prevAnalyser.getGrossProfitPercentage());
						prevMap.put("AE-1 Name", prevAnalyser.getCommName1());
						prevMap.put("AE-1 Percent", prevAnalyser.getCommPer1());
						prevMap.put("AE-1 Amount", prevAnalyser.getCommision1());
						prevMap.put("AE-1 SPLIT%", prevAnalyser.getSplitCommissionPercentage1());
						prevMap.put("AE-1 Orphan", prevAnalyser.getExecOrphan());
						prevMap.put("RECRUITER-1 NAME", prevAnalyser.getCommName2());
						prevMap.put("RECRUITER-1 Percent", prevAnalyser.getCommPer2());
						prevMap.put("RECRUITER-1 Amount", prevAnalyser.getCommision2());
						prevMap.put("RECRUITER-1 SPLIT%", prevAnalyser.getSplitCommissionPercentage2());
						prevMap.put("RECRUITER-1 Orphan", prevAnalyser.getRecruiterOrphan());
						prevMap.put("AE-2 Name", prevAnalyser.getCommName3());
						prevMap.put("AE-2 Percent", prevAnalyser.getCommPer3());
						prevMap.put("AE-2 Amount", prevAnalyser.getCommision3());
						prevMap.put("AE-2 SPLIT%", prevAnalyser.getSplitCommissionPercentage3());
						prevMap.put("AE-2 Orphan", prevAnalyser.getOther1Orphan());
						prevMap.put("RECRUITER-2 Name", prevAnalyser.getCommName4());
						prevMap.put("RECRUITER-2 Percent", prevAnalyser.getCommPer4());
						prevMap.put("RECRUITER-2 Amount", prevAnalyser.getCommision4());
						prevMap.put("RECRUITER-2 Split%", prevAnalyser.getSplitCommissionPercentage4());
						prevMap.put("RECRUITER-2 Orphan", prevAnalyser.getOther2Orphan());
						prevMap.put("Analyzer ID", prevAnalyser.getAnalyserId());
						prevMap.put("Gross Profit", prevAnalyser.getGrossProfit());
				        prevMap.put("Last Name", prevAnalyser.getlName());
				        prevMap.put("First Name", prevAnalyser.getfName());
				        prevMap.put("Initials", prevAnalyser.getInitial());
				        prevMap.put("Branch", prevAnalyser.getBranch());
				        prevMap.put("Client Name", prevAnalyser.getClientCompany());
				        prevMap.put("Invoice Address", prevAnalyser.getInvoiceAddress());
				        prevMap.put("Client Site", prevAnalyser.getSiteLocation());
				        prevMap.put("SubContractor Name", prevAnalyser.getContCompanyName());
				        prevMap.put("Payment Terms", prevAnalyser.getPaymentTerms());
				        prevMap.put("Profit", prevAnalyser.getProfit());
				        prevMap.put("Start Date", prevAnalyser.getStartDate());
				        prevMap.put("End Date", prevAnalyser.getEndDate());
				        prevMap.put("Submission Date", prevAnalyser.getSubmissionDate());
				        prevMap.put("Comm Eff Date", prevAnalyser.getCommEffDate());
				        prevMap.put("Parent Id", prevAnalyser.getParentId());
				        prevMap.put("Commission Model-1", prevAnalyser.getCommissionModel1());
				        prevMap.put("COMMISSION MODEL-2", prevAnalyser.getCommissionModel2());
				        prevMap.put("TRAVEL REQUIRED", prevAnalyser.getTravelRequired());
				        prevMap.put("Deal Type", prevAnalyser.getDealType());
				        prevMap.put("IMMIGRATION COST", prevAnalyser.getImmigrationCost());
				        prevMap.put("EQUIPMENT COST", prevAnalyser.getEquipmentCost());
				        prevMap.put("Address 1", prevAnalyser.getAddress1());
				        prevMap.put("Address 2", prevAnalyser.getAddress2());
				        prevMap.put("City", prevAnalyser.getCity());
				        prevMap.put("Referral Fee Amount", prevAnalyser.getReferralFeeAmount());
				        prevMap.put("Work Email", prevAnalyser.getWorkEmail());
				        prevMap.put("Personal Email", prevAnalyser.getEmail());
				        prevMap.put("Work Phone", prevAnalyser.getWorkPhone());
				        prevMap.put("Personal Phone", prevAnalyser.getTelephone());
				        prevMap.put("FLSA Status", prevAnalyser.getFlsaStatus());
				        prevMap.put("FLSA Reference", prevAnalyser.getFlsaReference());
				        prevMap.put("Comments", prevAnalyser.getComments());
				        prevMap.put("Client Complete Address", prevAnalyser.getClientCompleteAddress());
				        prevMap.put("Bill Rate", prevAnalyser.getBillRate());
				        prevMap.put("One time bill", prevAnalyser.getOtb());
				        prevMap.put("Pay Rate", prevAnalyser.getPayRate());
				        prevMap.put("Terminate Date", prevAnalyser.getTermDate());
				        prevMap.put("Reason", prevAnalyser.getReason());
				        prevMap.put("Dental Insurance Amount", prevAnalyser.getDentalInsuranceAmount());
				        prevMap.put("Billable Cost", prevAnalyser.getNonBillableCost());
				        prevMap.put("Employee Class", prevAnalyser.getEmployeeClass());
				        prevMap.put("Vertical", prevAnalyser.getVertical());
				        prevMap.put("Annual Salary", prevAnalyser.getAnnualPay());
				        prevMap.put("Liaison Name", prevAnalyser.getLiaisonName());
				        prevMap.put("Discount Rate", prevAnalyser.getDiscountRate());
				        prevMap.put("Total Commission Amount", prevAnalyser.getCommission());
				        prevMap.put("Fringe", prevAnalyser.getTax());
				        prevMap.put("GA Amount", prevAnalyser.getGa());
				        prevMap.put("DOUBLE TIME PAY", prevAnalyser.getDoubleTime());
				        prevMap.put("DOUBLE TIME BILL", prevAnalyser.getDoubleTimeBill());
				        prevMap.put("EFFECTIVE DATE", prevAnalyser.getEffectiveDate());
				        prevMap.put("PAYMENT TERM", prevAnalyser.getPaymentTerms());
				        prevMap.put("WORK PHONE", prevAnalyser.getWorkPhone());
				        prevMap.put("TELEPHONE", prevAnalyser.getTelephone());
				        prevMap.put("CURRENCY", prevAnalyser.getCurrency());
				        prevMap.put("SPLIT", prevAnalyser.getSplit());
				        prevMap.put("DATE OF BIRTH", prevAnalyser.getDob());
				        prevMap.put("SSN", prevAnalyser.getSsn());
				        prevMap.put("EMAIL", prevAnalyser.getEmail());
				        prevMap.put("TITLE", prevAnalyser.getJobTitle());
				        prevMap.put("TOTAL HOURS ESTIMATED", prevAnalyser.getTotHoursWorked());
				        prevMap.put("Product Staff", prevAnalyser.getProduct());
				        prevMap.put("Managing Director", prevAnalyser.getManagingDirector());
				        prevMap.put("ModifiedBy", prevAnalyser.getModifiedBy());
				        
				        prevMap.put("Sick Leave Cost", prevAnalyser.getSickLeaveCost());
				        prevMap.put("False Termination", prevAnalyser.getFalseTermination());
				        
				        prevMap.put("Vertical Sales", prevAnalyser.getCommissionPerson5());
				        prevMap.put("IT-GS-Practice", prevAnalyser.getCommissionPerson6());
				        prevMap.put("IT-GS-Delivery", prevAnalyser.getCommissionPerson7());
				        prevMap.put("IT-GS-BDE", prevAnalyser.getCommissionPerson8());
				        prevMap.put("IT-GS-Proposal", prevAnalyser.getCommissionPerson9());
				        
				        prevMap.put("Commission Percentage 5", prevAnalyser.getCommissionPercentage5());
				        prevMap.put("Commission Percentage 6", prevAnalyser.getCommissionPercentage6());
				        prevMap.put("Commission Percentage 7", prevAnalyser.getCommissionPercentage7());
				        prevMap.put("Commission Percentage 8", prevAnalyser.getCommissionPercentage8());
				        prevMap.put("Commission Percentage 9", prevAnalyser.getCommissionPercentage9());

				        prevMap.put("Commission 5 Amount", prevAnalyser.getCommission5());
				        prevMap.put("Commission 6 Amount", prevAnalyser.getCommission6());
				        prevMap.put("Commission 7 Amount", prevAnalyser.getCommission7());
				        prevMap.put("Commission 8 Amount", prevAnalyser.getCommission8());
				        prevMap.put("Commission 9 Amount", prevAnalyser.getCommission9());

				        prevMap.put("Project Cost", prevAnalyser.getProjectCost());
				        prevMap.put("Sick Leave Per Hour Rate", prevAnalyser.getSickLeavePerHourRate());
				        prevMap.put("Sick Leave Cap", prevAnalyser.getSickLeaveCap());
				        prevMap.put("Reason", prevAnalyser.getReason());
				        prevMap.put("Sick Leave Type", prevAnalyser.getSickLeaveType());
				        prevMap.put("PTO", prevAnalyser.getRdoPTO());
				        prevMap.put("Leave", prevAnalyser.getLeave());
				        prevMap.put("Total Holidays", prevAnalyser.getTotalHolidays());

				        prevMap.put("Gross Profit", prevAnalyser.getGrossProfit());
				        prevMap.put("Spread Week", prevAnalyser.getSpreadWeek());
				        prevMap.put("Discount Rate", prevAnalyser.getDiscountRate());
				        prevMap.put("Discount", prevAnalyser.getDiscount());
				        prevMap.put("FLSA Status", prevAnalyser.getFlsaStatus());
				        prevMap.put("PTO Limit To Override Sick", prevAnalyser.getpTOLimitToOverrideSick());
				        prevMap.put("Recruiting Classification", prevAnalyser.getRecruitingClassification());
				        
				        prevMap.put("SubContractor Id", prevAnalyser.getContractorId());
				        prevMap.put("Site Id",prevAnalyser.getClientSiteId());
				        prevMap.put("Client Address Id",prevAnalyser.getClientAddressId());
				        
				        // next map
				        nextMap.put("BILLABLEPTO", nextAnalyser.getBillablePTO());
						nextMap.put("NONBILLABLEPTO", nextAnalyser.getNonBillablePTO());
						nextMap.put("BILLABLEPTOCOST", nextAnalyser.getNonBillablePTOCost());
						nextMap.put("NONBILLABLEPTOCOST", nextAnalyser.getNonBillablePTOCost());
						nextMap.put("TOTALHOLIDAYS", nextAnalyser.getTotalHolidays());
						nextMap.put("BILLABLEHOLIDAYS", nextAnalyser.getBillableHolidays());
						nextMap.put("NONBILLABLEHOLIDAYS", nextAnalyser.getNonBillableHolidays());
						nextMap.put("BILLABLEHOLIDAYSCOST", nextAnalyser.getBillableHolidaysCost());
						nextMap.put("NONBILLABLEHOLIDAYSCOST", nextAnalyser.getNonBillableHolidaysCost());
						nextMap.put("GROSSPROFITPERCENTAGE", nextAnalyser.getGrossProfitPercentage());
						nextMap.put("COMMISSIONABLEPROFIT", nextAnalyser.getCommissionableProfit());
						nextMap.put("REJECTIONSTATUS", nextAnalyser.getRejectionStatus());
						nextMap.put("REJECTIONREASON", nextAnalyser.getRejectionReason());
						nextMap.put("REJECTEDBY", nextAnalyser.getRejectedBy());
						nextMap.put("REJECTIONDATE", nextAnalyser.getRejectionDate());
						nextMap.put("GEOOFFICE", nextAnalyser.getGeoOffice());
				        nextMap.put("Employee Category", nextAnalyser.getTemps());
						nextMap.put("Is Rehired", nextAnalyser.getIsRehired());
						nextMap.put("Approval Date", nextAnalyser.getApprovalDate());
						nextMap.put("Approved By Manager", nextAnalyser.getApprovedByManager());
						nextMap.put("One Time Amount", nextAnalyser.getOneTimeAmount());
						nextMap.put("Client ID", nextAnalyser.getClientId());
						nextMap.put("Parent ID", nextAnalyser.getParentId());
						nextMap.put("New Parent ID", nextAnalyser.getNewParentId());
						nextMap.put("Record Status", nextAnalyser.getRecordStatus());
						nextMap.put("Employee Type", nextAnalyser.getEmpType());
						nextMap.put("Gross Profit Percentage", nextAnalyser.getGrossProfitPercentage());
						nextMap.put("AE-1 Name", nextAnalyser.getCommName1());
						nextMap.put("AE-1 Percent", nextAnalyser.getCommPer1());
						nextMap.put("AE-1 Amount", nextAnalyser.getCommision1());
						nextMap.put("AE-1 SPLIT%", nextAnalyser.getSplitCommissionPercentage1());
						nextMap.put("AE-1 Orphan", nextAnalyser.getExecOrphan());
						nextMap.put("RECRUITER-1 NAME", nextAnalyser.getCommName2());
						nextMap.put("RECRUITER-1 Percent", nextAnalyser.getCommPer2());
						nextMap.put("RECRUITER-1 Amount", nextAnalyser.getCommision2());
						nextMap.put("RECRUITER-1 SPLIT%", nextAnalyser.getSplitCommissionPercentage2());
						nextMap.put("RECRUITER-1 Orphan", nextAnalyser.getRecruiterOrphan());
						nextMap.put("AE-2 Name", nextAnalyser.getCommName3());
						nextMap.put("AE-2 Percent", nextAnalyser.getCommPer3());
						nextMap.put("AE-2 Amount", nextAnalyser.getCommision3());
						nextMap.put("AE-2 SPLIT%", nextAnalyser.getSplitCommissionPercentage3());
						nextMap.put("AE-2 Orphan", nextAnalyser.getOther1Orphan());
						nextMap.put("RECRUITER-2 Name", nextAnalyser.getCommName4());
						nextMap.put("RECRUITER-2 Percent", nextAnalyser.getCommPer4());
						nextMap.put("RECRUITER-2 Amount", nextAnalyser.getCommision4());
						nextMap.put("RECRUITER-2 Split%", nextAnalyser.getSplitCommissionPercentage4());
						nextMap.put("RECRUITER-2 Orphan", nextAnalyser.getOther2Orphan());
						nextMap.put("Analyzer ID", nextAnalyser.getAnalyserId());
						nextMap.put("Gross Profit", nextAnalyser.getGrossProfit());
				        nextMap.put("Last Name", nextAnalyser.getlName());
				        nextMap.put("First Name", nextAnalyser.getfName());
				        nextMap.put("Initials", nextAnalyser.getInitial());
				        nextMap.put("Branch", nextAnalyser.getBranch());
				        nextMap.put("Client Name", nextAnalyser.getClientCompany());
				        nextMap.put("Invoice Address", nextAnalyser.getInvoiceAddress());
				        nextMap.put("Client Site", nextAnalyser.getSiteLocation());
				        nextMap.put("SubContractor Name", nextAnalyser.getContCompanyName());
				        nextMap.put("Payment Terms", nextAnalyser.getPaymentTerms());
				        nextMap.put("Profit", nextAnalyser.getProfit());
				        nextMap.put("Start Date", nextAnalyser.getStartDate());
				        nextMap.put("End Date", nextAnalyser.getEndDate());
				        nextMap.put("Submission Date", nextAnalyser.getSubmissionDate());
				        nextMap.put("Comm Eff Date", nextAnalyser.getCommEffDate());
				        nextMap.put("Parent Id", nextAnalyser.getParentId());
				        nextMap.put("Commission Model-1", nextAnalyser.getCommissionModel1());
				        nextMap.put("COMMISSION MODEL-2", nextAnalyser.getCommissionModel2());
				        nextMap.put("TRAVEL REQUIRED", nextAnalyser.getTravelRequired());
				        nextMap.put("Deal Type", nextAnalyser.getDealType());
				        nextMap.put("IMMIGRATION COST", nextAnalyser.getImmigrationCost());
				        nextMap.put("EQUIPMENT COST", nextAnalyser.getEquipmentCost());
				        nextMap.put("Address 1", nextAnalyser.getAddress1());
				        nextMap.put("Address 2", nextAnalyser.getAddress2());
				        nextMap.put("City", nextAnalyser.getCity());
				        nextMap.put("Referral Fee Amount", nextAnalyser.getReferralFeeAmount());
				        nextMap.put("Work Email", nextAnalyser.getWorkEmail());
				        nextMap.put("Personal Email", nextAnalyser.getEmail());
				        nextMap.put("Work Phone", nextAnalyser.getWorkPhone());
				        nextMap.put("Personal Phone", nextAnalyser.getTelephone());
				        nextMap.put("FLSA Status", nextAnalyser.getFlsaStatus());
				        nextMap.put("FLSA Reference", nextAnalyser.getFlsaReference());
				        nextMap.put("Comments", nextAnalyser.getComments());
				        nextMap.put("Client Complete Address", nextAnalyser.getClientCompleteAddress());
				        nextMap.put("Bill Rate", nextAnalyser.getBillRate());
				        nextMap.put("One time bill", nextAnalyser.getOtb());
				        nextMap.put("Pay Rate", nextAnalyser.getPayRate());
				        nextMap.put("Terminate Date", nextAnalyser.getTermDate());
				        nextMap.put("Reason", nextAnalyser.getReason());
				        nextMap.put("Dental Insurance Amount", nextAnalyser.getDentalInsuranceAmount());
				        nextMap.put("Billable Cost", nextAnalyser.getNonBillableCost());
				        nextMap.put("Employee Class", nextAnalyser.getEmployeeClass());
				        nextMap.put("Vertical", nextAnalyser.getVertical());
				        nextMap.put("Annual Salary", nextAnalyser.getAnnualPay());
				        nextMap.put("Liaison Name", nextAnalyser.getLiaisonName());
				        nextMap.put("Discount Rate", nextAnalyser.getDiscountRate());
				        nextMap.put("Total Commission Amount", nextAnalyser.getCommission());
				        nextMap.put("Fringe", nextAnalyser.getTax());
				        nextMap.put("GA Amount", nextAnalyser.getGa());
				        nextMap.put("DOUBLE TIME PAY", nextAnalyser.getDoubleTime());
				        nextMap.put("DOUBLE TIME BILL", nextAnalyser.getDoubleTimeBill());
				        nextMap.put("EFFECTIVE DATE", nextAnalyser.getEffectiveDate());
				        nextMap.put("PAYMENT TERM", nextAnalyser.getPaymentTerms());
				        nextMap.put("WORK PHONE", nextAnalyser.getWorkPhone());
				        nextMap.put("TELEPHONE", nextAnalyser.getTelephone());
				        nextMap.put("CURRENCY", nextAnalyser.getCurrency());
				        nextMap.put("SPLIT", nextAnalyser.getSplit());
				        nextMap.put("DATE OF BIRTH", nextAnalyser.getDob());
				        nextMap.put("SSN", nextAnalyser.getSsn());
				        nextMap.put("EMAIL", nextAnalyser.getEmail());
				        nextMap.put("TITLE", nextAnalyser.getJobTitle());
				        nextMap.put("TOTAL HOURS ESTIMATED", nextAnalyser.getTotHoursWorked());
				        nextMap.put("Product Staff", nextAnalyser.getProduct());
				        nextMap.put("Managing Director", nextAnalyser.getManagingDirector());
				        nextMap.put("ModifiedBy", nextAnalyser.getModifiedBy());
				        
				        nextMap.put("Sick Leave Cost", nextAnalyser.getSickLeaveCost());
				        nextMap.put("False Termination", nextAnalyser.getFalseTermination());

				        nextMap.put("Vertical Sales", nextAnalyser.getCommissionPerson5());
				        nextMap.put("IT-GS-Practice", nextAnalyser.getCommissionPerson6());
				        nextMap.put("IT-GS-Delivery", nextAnalyser.getCommissionPerson7());
				        nextMap.put("IT-GS-BDE", nextAnalyser.getCommissionPerson8());
				        nextMap.put("IT-GS-Proposal", nextAnalyser.getCommissionPerson9());

				        nextMap.put("Commission Percentage 5", nextAnalyser.getCommissionPercentage5());
				        nextMap.put("Commission Percentage 6", nextAnalyser.getCommissionPercentage6());
				        nextMap.put("Commission Percentage 7", nextAnalyser.getCommissionPercentage7());
				        nextMap.put("Commission Percentage 8", nextAnalyser.getCommissionPercentage8());
				        nextMap.put("Commission Percentage 9", nextAnalyser.getCommissionPercentage9());

				        nextMap.put("Commission 5 Amount", nextAnalyser.getCommission5());
				        nextMap.put("Commission 6 Amount", nextAnalyser.getCommission6());
				        nextMap.put("Commission 7 Amount", nextAnalyser.getCommission7());
				        nextMap.put("Commission 8 Amount", nextAnalyser.getCommission8());
				        nextMap.put("Commission 9 Amount", nextAnalyser.getCommission9());

				        nextMap.put("Project Cost", nextAnalyser.getProjectCost());
				        nextMap.put("Sick Leave Per Hour Rate", nextAnalyser.getSickLeavePerHourRate());
				        nextMap.put("Sick Leave Cap", nextAnalyser.getSickLeaveCap());
				        nextMap.put("Reason", nextAnalyser.getReason());
				        nextMap.put("Sick Leave Type", nextAnalyser.getSickLeaveType());
				        nextMap.put("PTO", nextAnalyser.getRdoPTO());
				        nextMap.put("Leave", nextAnalyser.getLeave());
				        nextMap.put("Total Holidays", nextAnalyser.getTotalHolidays());

				        nextMap.put("Gross Profit", nextAnalyser.getGrossProfit());
				        nextMap.put("Spread Week", nextAnalyser.getSpreadWeek());
				        nextMap.put("Discount Rate", nextAnalyser.getDiscountRate());
				        nextMap.put("Discount", nextAnalyser.getDiscount());
				        nextMap.put("FLSA Status", nextAnalyser.getFlsaStatus());
				        nextMap.put("PTO Limit To Override Sick", nextAnalyser.getpTOLimitToOverrideSick());
				        nextMap.put("Recruiting Classification", nextAnalyser.getRecruitingClassification());
				        
				        nextMap.put("SubContractor Id", nextAnalyser.getContractorId());
				        nextMap.put("Site Id",nextAnalyser.getClientSiteId());
				        nextMap.put("Client Address Id",nextAnalyser.getClientAddressId());
				        
				        nextMap.put("Portfolio Code", nextAnalyser.getPortfolio());
				        nextMap.put("Portfolio Description", nextAnalyser.getPortfolioDescription());
				        nextMap.put("CompanyCode", nextAnalyser.getCompanyCode());
				        nextMap.put("DealActivityAmount", nextAnalyser.getDealActivityAmount());
				        nextMap.put("BullhornPlacementId", nextAnalyser.getBullhornPlacementId());
				        nextMap.put("DealPortfolio1AE1", nextAnalyser.getDealPortfolio1AE1());
				        nextMap.put("DealPortfolio2REC1", nextAnalyser.getDealPortfolio2REC1());
				        nextMap.put("DealPortfolio3AE2", nextAnalyser.getDealPortfolio3AE2());
				        nextMap.put("DealPortfolio4REC2", nextAnalyser.getDealPortfolio4REC2());
						
						DiffResult diff = prevAnalyser.diff(nextAnalyser);
						for (Diff<?> d : diff.getDiffs()) {
	
							prevMap.put(d.getFieldName() + "-color", "color : red");
							nextMap.put(d.getFieldName() + "-color", "color : red");
							map.put(d.getFieldName() + "-color", "color : red");
							System.out.println(""+d.getFieldName()+"-color");
							System.out.println(d.getFieldName() + "= FROM["+ d.getLeft() + "] TO [" + d.getRight() + "]");
							logger.debug(d.getFieldName() + "= FROM["+ d.getLeft() + "] TO [" + d.getRight() + "]");
						}
						if(responseList.size() == 0)
						{
							responseList.add(prevMap);
						}
						else
						{
							if(map != null && map.size() > 0)
							{
								for(Map.Entry<String, Object> entry : map.entrySet())
								{
									responseList.get(responseList.size()- 1).put(entry.getKey(), entry.getValue());
								}
							}
						}
						responseList.add(nextMap);
						index ++;
					}
				}
				
//				for (Map<String, Object> mapObject : responseList) {
//					for(Map.Entry<String, Object> obj : mapObject.entrySet())
//					{
//						System.out.println("Key = " + obj.getKey());
//						System.out.println("Value = " + obj.getValue());
//					}
//					System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
//				}
				
				logger.debug("{call " + FacesUtils.SCHEMA_WIRELESS + ".spGetCommissionHistory('[0]','[1]', '[2]')}");
				logger.debug("{call " + FacesUtils.SCHEMA_WIRELESS + ".spGetCommissionHistory('" + FacesUtils.getCurrentUserId() + "'," + analyserId
				        + ", ALL,Company)}");
				logger.debug("List size is : " + list.size());
			}
			catch (Exception ex)
			{
				logger.debug("Error fetching list from the spGetCommissionHistory stored procedure ");
			}
		}
		return responseList;
	
	}

	/**
	 * @param responseList the responseList to set
	 */
	public void setResponseList(List<Map<String, Object>> responseList) {
		this.responseList = responseList;
	}

	public List<AnalyserDTO> getFilteredList3()
	{
		return filteredList3;
	}

	public void setFilteredList3(List<AnalyserDTO> filteredList3)
	{
		this.filteredList3 = filteredList3;
	}

	/**
	 * @return the filteredList4
	 */
	public List<AnalyserDTO> getFilteredList4()
	{
		return filteredList4;
	}

	/**
	 * @param filteredList4 the filteredList4 to set
	 */
	public void setFilteredList4(List<AnalyserDTO> filteredList4)
	{
		this.filteredList4 = filteredList4;
	}

	/**
	 * @return the filteredList5
	 */
	public List<AnalyserDTO> getFilteredList5()
	{
		return filteredList5;
	}

	/**
	 * @param filteredList5 the filteredList5 to set
	 */
	public void setFilteredList5(List<AnalyserDTO> filteredList5)
	{
		this.filteredList5 = filteredList5;
	}
}
