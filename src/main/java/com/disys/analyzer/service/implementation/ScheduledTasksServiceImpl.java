/**
 * 
 */
package com.disys.analyzer.service.implementation;

import static org.springframework.ldap.query.LdapQueryBuilder.query;

import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.directory.api.util.DateUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.core.AttributesMapper;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.filter.AndFilter;
import org.springframework.ldap.filter.EqualsFilter;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.disys.analyzer.config.UserAttributesMapper;
import com.disys.analyzer.config.util.ExcelWriter;
//import com.disys.analyzer.config.util.ExcelWriter;
import com.disys.analyzer.config.util.FacesUtils;
import com.disys.analyzer.model.User;
import com.disys.analyzer.model.dto.AnalyserCommisionPersonDTO;
import com.disys.analyzer.repository.AnalyserRepository;
import com.disys.analyzer.repository.ApplicationUserRepository;
import com.disys.analyzer.service.ScheduledTasksService;

/**
 * @author Sajid Qureshi
 * @since Oct 03, 2020
 */
@Service
@Transactional(readOnly = true)
public class ScheduledTasksServiceImpl implements ScheduledTasksService, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final Logger logger = LoggerFactory.getLogger(ScheduledTasksServiceImpl.class);

	//String realPath = FacesUtils.getTempFilePath();
	String realPath = FacesUtils.getTemporaryFilePath();

	String FILE_EXTENSION = ".xlsx";

	@Autowired
	private AnalyserRepository analyserRepository;
	
	@Autowired
	private ApplicationUserRepository applicationUserRepository;
	
	@Autowired
	private LdapTemplate ldapTemplate;
	
	@Autowired
	Map<String, List<AnalyserCommisionPersonDTO>> mdCommissionPersonDropDownBean;
	
	@Autowired
	Map<String, List<AnalyserCommisionPersonDTO>>	mspClientPartnerDropDownBean;
	
	@Autowired
	Map<String, List<AnalyserCommisionPersonDTO>> commissionPersonDropDownBean;
	
	private void clearDropDownBeans() {
		logger.info("Started resetting md, msp, commission person dropdowns....");
		mdCommissionPersonDropDownBean.clear();
		mspClientPartnerDropDownBean.clear();
		commissionPersonDropDownBean.clear();
		logger.info("resetting md, msp, commission person dropdowns completed....");
	}

//	@Scheduled(cron = "0 34 19 5 * *")
	@Scheduled(cron = "#{'${environment}'=='development' ? '${development.scheduling.datadumpfile.cron}' : '${production.scheduling.datadumpfile.cron}'}")
	@Override
	public void analyserDataDumpFileServiceMethod() {
		logger.debug("analyserDataDumpFileServiceMethod --> method started at :: " + new Date());
		System.out.println("analyserDataDumpFileServiceMethod --> method started at :: " + new Date());
		logger.info("Starting to execute Analyzer Dump File Job ....");
		System.out.println("Starting to execute Analyzer Dump File Job ....");
		List<Object> analyserList = analyserRepository.getAnalyzerDataDumpFile(FacesUtils.SCHEDULED_TASK_USER);
		if (analyserList != null)
		{
			logger.info("analyserList list size :" + analyserList.size());
			System.out.println("analyserList list size :" + analyserList.size());
		}
		else
		{
			logger.info("analyserList list is NULL.");
			System.out.println("analyserList list is NULL.");			
		}
		String fileName = FacesUtils.getMonthlyAnalyserDataDumpFileName();
		logger.info("Analyzer Dump File Name :" + fileName);
		System.out.println("Analyzer Dump File Name :" + fileName);
		String[] columnNames = {
				"CandidateName",
				"JobTitle",
				"ClientName",
				"LastName",
				"FirstName",
				"SubcontractorName",
				"ManagerName",
				"WorksiteCity",
				"WorksiteState",
				"EmpType",
				"BillRate",
				"PayRate",
				"StartDate",
				"EndDate",
				"EffectiveDate",
				"Office",
				"Hrbp",
				"Vertical",
				"Product",
				"RecruitingClassification",
				"ManagingDirector",
				"Ae",
				"Ae2",
				"Rec",
				"GrossProfit",
				"Worksiteaddress1",
				"Worksiteaddress2",
				"Worksitezipcode",
				"WorksitePhone",
				"WorksiteFax",
				"WorksiteEmail",
				"Address1",
				"Address2",
				"Commision1",
				"Commision2",
				"Commision3",
				"Commision4",
				"Dob",
				"Email",
				"Thw",
				"Clientid",
				"ClientAddressId",
				"SiteId",
				"PTO",
				"EmployeeCategory",
				"UnemployedforTwoMonths",
				"ConsultantJobBoard",
				"Gender",
				"FlsaStatus",
				"TravelRequired",
				"CommissionModel1",
				"CommisionPercent1",
				"JobCategory",
				"CommissionPersongrade1",
				"ExecOrphan",
				"SplitCommissionPercentage1",
				"Commissionmodel2",
				"Commision_percent2",
				"State",
				"Recruiterorphan",
				"SplitCommissionPercentage2",
				"OneTimeBill",
				"AnnualPay",
				"DoubleTimeBill",
				"SubcontractorId",
				"EmployeeClass",
				"OneTimePay",
				"FlsaReferance",
				"DoubleTime",
				"CommEffDate",
				"Custom1",
				"Custom2",
				"Taxes",
				"Initial",
				"Commissionableprofit",
				"Commission",
				"City",
				"Zipcode",
				"Telephone",
				"Workphone",
				"MobilePager",
				"WorkEmail",
				"CommissionPersongrade2",
				"CommissionModel3",
				"CommisionPercent3",
				"Dealtype",
				"CommissionPersonGrade3",
				"Other1Orphan",
				"SplitCommissionPercentage3",
				"Rec2",
				"CommissionModel4",
				"CommisionPercent4",
				"CommissionPersongrade4",
				"Other2Orphan",
				"SplitCommissionPercentage4",
				"SickLeavePerHourRate",
				"SickLeaveCap",
				"PTOLimittoOverrideSick",
				"Discount",
				"OtherDollar",
				"DiscountRate",
				"AnalyserId",
				"ParentId",
				"Leave",
				"PsclientId",
				"GrossProfitPercentage",
				"TerminateDate",
				"SubmissionDate",
				"UserId",
				"Profit",
				"Ga",
				"Reason",
				"Industry",
				"Sickleavecost",
				"OtherAmountHour",
				"RejectionStatus",
				"Rejectedby",
				"RejectionDate",
				"Gregion",
				"Vs",
				"Prac",
				"Deliv",
				"Bde",
				"Prop",
				"VsP",
				"PracP",
				"DelivP",
				"BdeP",
				"PropP",
				"VsA",
				"PracA",
				"DelivA",
				"BdeA",
				"PropA",
				"AnalyzerCategory2",
				"Aisbonueligible",
				"Bonusamount",
				"BonusPercentage",
				"SkillCategory",
				"EmployeeVeteran",
				"Perdiem",
				"Projectcost",
				"Equipmentcost",
				"BillableCost",
				"ImmigrationCost",
				"ReferralFeeAmount",
				"BillablePTO",
				"NonbillablePTO",
				"BillablePTOCost",
				"NonbillablePTOCost",
				"TotalHolidays",
				"BillableHolidays",
				"NonbillableHolidays",
				"BillableHolidaysCost",
				"NonbillableHolidaysCost",
				"FalseTermination",
				"NewParentId",
				"DistanceFromWorksite",
				"IsaddressUspsValidated",
				"UspsaddressValidationDate",
				"AppBkup",
				"ApprovalDate",
				"ApprovedbyManager",
				"Isrehired",
				"RehireDate",
				"RecordStatus",
				"CompanyCode"
				};

		try 
		{
			ExcelWriter.writeExcelFile(analyserList, columnNames, realPath, fileName, FILE_EXTENSION, "Analysers");
			logger.debug("analyserDataDumpFileServiceMethod method completed at :: " + new Date());
			System.out.println("analyserDataDumpFileServiceMethod method completed at :: " + new Date());
		} 
		catch (InvalidFormatException e) {
			logger.error(e.getMessage());
			System.out.println(e.getMessage());
			e.printStackTrace();
		} 
		catch (IOException e) {
			logger.error(e.getMessage());
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		catch (Exception e) {
			logger.error(e.getMessage());
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
	
/*	private class AnalyserAttributesMapper implements AttributesMapper<Analyser> {
		public Analyser mapFromAttributes(Attributes attrs) throws NamingException {
			Analyser person = new Analyser();
			person.setFullName((String) attrs.get("cn").get());
			person.setLastName((String) attrs.get("sn").get());
			person.setDescription((String) attrs.get("description").get());
			return person;
		}
	}*/

	@Override
	@Scheduled(cron = "#{'${environment}'=='development' ? '${development.scheduling.addnewanalyseruser.cron}' : '${production.scheduling.addnewanalyseruser.cron}'}")
	//@Scheduled(cron = "${scheduling.addnewanalyser.cron}")
//	@Scheduled(cron = "0 9/30 * ? * *")
	public void addNewAnalyzerScheduleJob() 
	{
		try
		{
			logger.info("Starting to execute add new analyzers job  at ...." + new Date());
			System.out.println("Starting to execute add new analyzers job at ...." + new Date());
			AndFilter filter = new AndFilter();
	//		filter.and(new EqualsFilter("objectclass", "person"));
	//		filter.and(new EqualsFilter("objectclass", "organizationalPerson"));
	//		filter.and(new EqualsFilter("objectclass", "user"));
			//filter.and(new EqualsFilter("memberOf", "CN=Analyzer_AD_Auth,OU=Systems Security Groups,OU=IT,DC=DISYS,DC=LOCAL"));
			filter.and(new EqualsFilter("memberOf", "CN=Analyzer_AD_Auth,OU=Access Groups,OU=Groups,OU=North America,DC=DISYS,DC=LOCAL"));
			@SuppressWarnings("unchecked")
			List<User> usersList = ldapTemplate.search(query().filter(filter), new UserAttributesMapper());
			logger.info("users list size :" + usersList.size());
			System.out.println("users list size :" + usersList.size());
			
	//		List<String> datesCreated = ldapTemplate.search(query().where("objectclass").is("person"), (AttributesMapper<String>) attributes -> attributes.get("whenCreated").get().toString());
	//		logger.info("call "+ FacesUtils.SCHEMA_WIRELESS +".spAddUserBatchJobAudit {'" + "'}");
	//		System.out.println("call "+ FacesUtils.SCHEMA_WIRELESS +".spAddUserBatchJobAudit {'" + "'}");
	//		applicationUserRepository.spAddUserBatchJobAudit(loggedUserID, userLogId);
			for(User user : usersList) {
				if (user != null) {
					System.out.println("Sn : " + user.getUserId());
					logger.info("call " + FacesUtils.SCHEMA_WIRELESS + ".spAddADAnalyzerUserEntry {'" + user.getUserId()
							+ ", " + user.getFirstName() + ", " + user.getLastName() + ", " + user.getStatus().toString()
							+ "'}");
					System.out.println("call " + FacesUtils.SCHEMA_WIRELESS + ".spAddADAnalyzerUserEntry {'"
							+ user.getUserId() + ", " + user.getFirstName() + ", " + user.getLastName() + ", "
							+ user.getStatus().toString() + "'}");
					applicationUserRepository.spAddADAnalyzerUserEntry("Scheduler_User", user.getUserId(),
							user.getFirstName(), user.getLastName(), user.getStatus().toString());
				}		
			}
			logger.debug("add new analyzers job  completed at :: " + new Date());
			System.out.println("add new analyzers job completed at :: " + new Date());
			
			logger.debug("Clear dropdown beans on addNewAnalyzerScheduleJob");
			System.out.println("Clear dropdown beans on addNewAnalyzerScheduleJob");
			clearDropDownBeans();
			
			//logger.info("In addNewAnalyzerScheduleJob --> call " + FacesUtils.SCHEMA_WIRELESS + ".spProcessADSyncData");
			//System.out.println("In addNewAnalyzerScheduleJob --> call " + FacesUtils.SCHEMA_WIRELESS + ".spProcessADSyncData");
			//applicationUserRepository.spProcessADSyncData("Scheduler_User");
		}
		catch (Exception e)
		{
			logger.info("In addNewAnalyzerScheduleJob --> Exception");
			System.out.println("In addNewAnalyzerScheduleJob --> Exception");
			logger.info("In addNewAnalyzerScheduleJob --> Exception DEtails = ");
			System.out.println("In addNewAnalyzerScheduleJob --> Exception");
			e.printStackTrace();
		}
	}

	@Override
	/**
	 * @TODO Need this to be configured based upon new properties file settings
	 * Seprately for production and development environments
	 */
//	@Scheduled(cron = "0 * * ? * *")
	public void disableAnalyzerScheduleJob() {
		logger.info("Starting to execute disable analyzers job ....");
		System.out.println("Starting to execute disable analyzers job ....");
		
		logger.info("call "+ FacesUtils.SCHEMA_WIRELESS +".spAddADAnalyzerUserEntry {'" + "'}");
		System.out.println("call "+ FacesUtils.SCHEMA_WIRELESS +".spAddADAnalyzerUserEntry {'" + "'}");		
//		applicationUserRepository.spAddADAnalyzerUserEntry(loggedUserID, userLogId);	
		AndFilter filter = new AndFilter();
		filter.and(new EqualsFilter("objectclass", "person"));
		List<String> usersSnList = ldapTemplate.search(query().where("objectclass").is("person"), (AttributesMapper<String>) attributes -> attributes.get("cn").get().toString());
		
		List<String> datesModified = ldapTemplate.search(query().where("objectclass").is("person"), (AttributesMapper<String>) attributes -> attributes.get("whenChanged").get().toString());
		logger.info("call "+ FacesUtils.SCHEMA_WIRELESS +".spAddUserBatchJobAudit {'" + "'}");
		System.out.println("call "+ FacesUtils.SCHEMA_WIRELESS +".spAddUserBatchJobAudit {'" + "'}");
//		applicationUserRepository.spAddUserBatchJobAudit(loggedUserID, userLogId);
		for(String sn : usersSnList) {
			System.out.println("Sn : " + sn);
			logger.info("call "+ FacesUtils.SCHEMA_WIRELESS +".spAddActivateADUserToAnalyzer {'" + "'}");
			System.out.println("call "+ FacesUtils.SCHEMA_WIRELESS +".spAddActivateADUserToAnalyzer {'" + "'}");
//			applicationUserRepository.spAddActivateADUserToAnalyzer(loggedUserID, userLogId);
		}
//		
		for(String dateCreated : datesModified) {
			System.out.println("Modified Date : " + DateUtils.getDate(dateCreated));
		}	
		
		logger.debug("Clear dropdown beans on disableAnalyzerScheduleJob");
		System.out.println("Clear dropdown beans on disableAnalyzerScheduleJob");
		clearDropDownBeans();
		
	}
	
	/* private Collection<User> search(String query) {
	        AndFilter filter = new AndFilter();
	        filter.and(new EqualsFilter("objectclass", "person"));
	        filter.and(new WhitespaceWildcardsFilter("cn", query));

	        LdapQuery ldapQuery = LdapQueryBuilder
	                .query()
	                .base(baseDn)
	                .countLimit(20)
	                .timeLimit(5000)
	                .searchScope(SearchScope.SUBTREE)
	                .attributes(identifierAttribute, "givenname", "sn", "mail")
	                .filter(filter);


	        return ldapTemplate.search(ldapQuery, new UserAttributesMapper());
	    }*/
}