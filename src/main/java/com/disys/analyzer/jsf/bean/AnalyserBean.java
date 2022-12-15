/**
 * 
 */
package com.disys.analyzer.jsf.bean;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIInput;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import org.springframework.web.jsf.FacesContextUtils;

import com.disys.analyzer.config.util.Constants;
import com.disys.analyzer.config.util.FacesMessageSeverity;
import com.disys.analyzer.config.util.FacesUtils;
import com.disys.analyzer.config.util.Util;
import com.disys.analyzer.model.AnalyserClientSite;
import com.disys.analyzer.model.AnalyserLiaison;
import com.disys.analyzer.model.AnalyserSubContractor;
import com.disys.analyzer.model.ManagerGroup;
import com.disys.analyzer.model.Role;
import com.disys.analyzer.model.SickLeaveCost;
import com.disys.analyzer.model.dto.AnalyserClientDTO;
import com.disys.analyzer.model.dto.AnalyserClientDatabaseDTO;
import com.disys.analyzer.model.dto.AnalyserClientSiteDTO;
import com.disys.analyzer.model.dto.AnalyserDTO;
import com.disys.analyzer.model.dto.AnalyserUspsAddress;
import com.disys.analyzer.model.dto.PortfolioDownstreamRequestDTO;
import com.disys.analyzer.model.dto.PortfolioDownstreamResponseDTO;
import com.disys.analyzer.reports.util.ReportUtil;
import com.disys.analyzer.security.service.util.SmartyStreetService;
import com.disys.analyzer.service.AnalyserClientService;
import com.disys.analyzer.service.AnalyserClientSiteService;
import com.disys.analyzer.service.AnalyserCommisionPersonService;
import com.disys.analyzer.service.AnalyserLiaisonService;
import com.disys.analyzer.service.AnalyserService;
import com.disys.analyzer.service.ApplicationUserService;
import com.disys.analyzer.service.ManagerGroupService;
import com.disys.analyzer.service.RoleService;
import com.disys.analyzer.service.SickLeaveCostService;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;

/**
 * @author Sajid
 * @since Dec 31, 2019
 */
@ManagedBean
@ViewScoped
public class AnalyserBean extends SpringBeanAutowiringSupport implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4213592558797672968L;

	private static Logger logger = LoggerFactory.getLogger(AnalyserBean.class);

	private AnalyserDTO analyser;
	private AnalyserDTO selectedAnalyser;

	private List<SelectItem> branches;
	private List<SelectItem> uSAStates;
	private List<SelectItem> employeeTypes;
	// temps is mapped with employee categories
	private List<SelectItem> employeeCategories;
	private List<SelectItem> commissionPersonsList1;
	private List<SelectItem> commissionPersonsList2;
	private List<SelectItem> commissionPersonsList3;
	private List<SelectItem> commissionPersonsList4;
	private List<SelectItem> commissionPersonsList5;
	private List<SelectItem> commissionPersonsList6;
	private List<SelectItem> commissionPersonsList7;
	private List<SelectItem> commissionPersonsList8;
	private List<SelectItem> commissionPersonsList9;

	private List<SelectItem> jobCategories;
	private List<SelectItem> jobBoards;
	private List<SelectItem> verticals;
	private List<SelectItem> products;
	private List<SelectItem> commissionModels;
	private List<SelectItem> splitPercent1;
	private List<SelectItem> splitPercent2;
	private List<SelectItem> splitPercent3;
	private List<SelectItem> splitPercent4;
	private List<SelectItem> dealTypes;
	private List<SelectItem> analyzerCategory1;
	private List<SelectItem> analyzerCategory2;
	private List<SelectItem> recruitingTeams;
	private List<SelectItem> genders;
	private List<SelectItem> flsaStatuses;
	private List<SelectItem> liaisonList;

	private List<SelectItem> holidays;

	@Autowired private AnalyserCommisionPersonService service;

	@Autowired private ManagerGroupService managerService;

	@Autowired private AnalyserLiaisonService liaisonService;

	@Autowired private AnalyserService analyserService;

	@Autowired private ApplicationUserService applicationUserService;

	@Autowired private SickLeaveCostService sickLeaveCostService;

	@Autowired private AnalyserClientSiteService analyserClientSiteService;

	@Autowired
	private AnalyserClientService analyserClientService;

	/*
	 * Used for searching sub company name in the form. It is enabled only when
	 * employee category is 1099
	 */
	private boolean searchSubcontractorFlag = true;

	/*
	 * Used for searching clients in the form. It is enabled only when Product
	 * and Vertical are selected.
	 */
	private boolean searchClientFlag = true;

	private boolean commPer1Display = false;

	private String grossProfitStyle;

	private boolean k401Check = false;
	private boolean healthCheck = false;

	private boolean flsaReferenceFlag = false;

	private boolean clientCompanyDialog = true;

	private String analyserId;
	private String action;

	private boolean showSaveButton;

	private String siteState;
	private String siteZipCode;
	private String isRehired;
	private String isFalseTerminated;

	private String oldStartDate;
	private String commStartDate;
	private String effectiveDate;
	private Double profitValue;

	private boolean showSsn;
	private boolean showPtoFields;

	private boolean validFlsaRefernce;
	private boolean validSsn;
	private boolean validZipCode;

	@Autowired private RoleService roleService;
	private Role role;

	private boolean hideAdditionalCommissionPersonsDiv;

	private String commissionPerson;

	private List<String> allCommissionPersonsList;
	private List<SelectItem> managingDirectorList;

	private boolean addressValidated;

	private String clientAddressId;
	private String siteId;

	private String oldCommissionPersonGrade1;
	private String oldCommissionPersonGrade3;
	private String commName1;
	private String commName3;

	private String ptoEligibility;
	private String siteZipCodeValue;

	private Integer ircCommissionPersonStatusForAe1;
	private Integer ircCommissionPersonStatusForAe2;
	private Integer ircCommissionPersonStatusForRec1;
	private Integer ircCommissionPersonStatusForRec2;

	private String ptoStatus;
	private String state;
	private Double oldGp;
	private Boolean showRejectionDiv;
	private List<Map<String, Object>> branchData;
	private String distance;
	private String latitude = "0.00", longitude = "0.00";
	private String workSiteLatitude = "0.00", workSiteLongitude = "0.00";
	private String msg;
	private static final double old_gp_default_value = -6.73;
	private static final String branch_distance_error_msg = "Branch address is not validate";
	private StringBuilder branchAddress = new StringBuilder();
	private String prevSsn;
	private String prevFlsaRef;

	private SmartyStreetService objSmarty = null;
	private Integer roleId;

	private String completeStatementFilePath;
	private boolean reportCreated = true;
	private String newPdfFileName;
	private StreamedContent generatedPdfFile;
	private String companyCodeLocal;
	private boolean enableCompanySpecificFields;
	// 03/11/2021 Added as part of Co-Sell Project
	private static final String ALL = "ALL";
	
	private static final String NULL = "NULL";
	@PostConstruct
	public void init()
	{
		try
		{
			FacesContextUtils.getRequiredWebApplicationContext(FacesContext.getCurrentInstance()).getAutowireCapableBeanFactory().autowireBean(this);
			// TODO check if both the services are autowired or not
		}
		catch (Exception ex)
		{
			logger.debug(ex.getMessage());
		}
	}

	public AnalyserBean()
	{
		System.err.println("I am called in Analyser Bean.....");
		logger.debug("In Analyser Bean constructor");
		msg = "";
		prevFlsaRef = "";
		prevSsn = "";
		objSmarty = new SmartyStreetService();

		String userId = FacesUtils.getCurrentUserId();
		//createCommissionPersonsList(userId);
		//createIRCCommissionPersonsList();

		FacesContext fc = FacesUtils.getFacesContext();
		ExternalContext externalContext = fc.getExternalContext();
		Map<String, String> requestMap = externalContext.getRequestParameterMap();

		requestMap.entrySet().forEach(entry ->
		{
			System.out.println("Key : " + entry.getKey() + " Value : " + entry.getValue());
			if (entry.getKey().equals("analyserId"))
			{
				analyserId = entry.getValue();
			}
			else if (entry.getKey().equals("action"))
			{
				action = entry.getValue();
			}
			else if (entry.getKey().equals("compCode"))
			{

				if(entry.getValue() == null || entry.getValue().isEmpty())
				{
					companyCodeLocal = Constants.DEFAULT_COMPANY_CODE;
					//analyser.setCompanyCode(companyCodeLocal);
				}
				else
				{
					companyCodeLocal = entry.getValue();
					//analyser.setCompanyCode(companyCodeLocal);
				}

			}});

		if(companyCodeLocal == null || companyCodeLocal.trim().equals("") ){
			System.out.println("Company Code Null Set DISYS");
			logger.debug("Company Code Null Set DISYS");
			companyCodeLocal = Constants.DEFAULT_COMPANY_CODE;
		}

		if(companyCodeLocal.equalsIgnoreCase("DISYS")){
			System.out.println("Company Code DISYS Portfolio AE1, REC1, AE2, REC2, Benefits Group field DISABLED");
			logger.debug("Company Code DISYS Portfolio AE1, REC1, AE2, REC2 field DISABLED");
			setEnableCompanySpecificFields(false);
		}else if(companyCodeLocal.equalsIgnoreCase("Signature")){
			System.out.println("Company Code Signature CoSellStatus field DISABLED");
			logger.debug("Company Code Signature CoSellStatus field DISABLED");
			setEnableCompanySpecificFields(true);
		}
		validFlsaRefernce = false;
		validSsn = false;
		showRejectionDiv = false;
		showPtoFields = false;

		roleId = applicationUserService.getRoleId(FacesUtils.getCurrentUserId());

		if(roleId == 3){
			showPtoFields = true;
		}
		/*
		 * TODO - For creating a clone of the Analyser record, for testing
		 * purpose.
		 */

		if (analyserId != null && action.equalsIgnoreCase("Add"))
		{
			analyser = analyserService.getAnalyserInfo(analyserId);
			System.out.println("Analyser found with following values");
			logger.debug("Analyser found with following values");
			logger.debug(analyser.toString());
			analyser.setExecOrphanHdnVal(false);
			analyser.setRecruiterOrphanHdnVal(false);
			analyser.setOther1OrphanHdnVal(false);
			analyser.setOther2OrphanHdnVal(false);

			if (analyser.getFlsaReference() != null)
			{
				prevFlsaRef = analyser.getFlsaReference();
				validFlsaRefernce = false;
			}
			if (analyser.getSsn() != null)
			{
				prevSsn = analyser.getSsn();
				validSsn = false;
			}

			/*
			 * Added as part of sick Leave project
			 */
			getSickLeaveDetails(analyser);

			if (analyser.getCommEffDate() != null && analyser.getCommEffDate().trim().length() > 0)
			{
				String[] splitDateAry = analyser.getCommEffDate().split("/");
				if (splitDateAry != null && splitDateAry.length == 3)
				{
					if (splitDateAry[0].length() < 2)
					{
						splitDateAry[0] = "0" + splitDateAry[0];
					}
					if (splitDateAry[1].length() < 2)
					{
						splitDateAry[1] = "0" + splitDateAry[1];
					}
					analyser.setCommEffDate(splitDateAry[0] + "/" + splitDateAry[1] + "/" + splitDateAry[2]);
				}
			}

			if (analyser.getExecOrphan() != null)
			{
				analyser.setExecOrphanHdnVal(analyser.getExecOrphan());
			}
			if (analyser.getRecruiterOrphan() != null)
			{
				analyser.setRecruiterOrphanHdnVal(analyser.getRecruiterOrphan());
			}
			if (analyser.getOther1Orphan() != null)
			{
				analyser.setOther1OrphanHdnVal(analyser.getOther1Orphan());
			}
			if (analyser.getOther2Orphan() != null)
			{
				analyser.setOther2OrphanHdnVal(analyser.getOther2Orphan());
			}

			if (analyser.getRejectionStatus() == 1)
			{
				showRejectionDiv = true;
			}
			selectedAnalyser = analyserService.getAnalyserInfo(analyserId);
			String message = "Analyser Loaded with Analyzer Id = "+selectedAnalyser.getAnalyserId()+" , UserId = "+FacesUtils.getCurrentUserId()+" and following values";
			System.out.println(message);
			logger.debug(message);
			selectedAnalyser.setExecOrphanHdnVal(false);
			selectedAnalyser.setRecruiterOrphanHdnVal(false);
			selectedAnalyser.setOther1OrphanHdnVal(false);
			selectedAnalyser.setOther2OrphanHdnVal(false);
			if (selectedAnalyser.getExecOrphan() != null)
			{
				selectedAnalyser.setExecOrphanHdnVal(selectedAnalyser.getExecOrphan());
			}
			if (selectedAnalyser.getRecruiterOrphan() != null)
			{
				selectedAnalyser.setRecruiterOrphanHdnVal(selectedAnalyser.getRecruiterOrphan());
			}
			if (selectedAnalyser.getOther1Orphan() != null)
			{
				selectedAnalyser.setOther1OrphanHdnVal(selectedAnalyser.getOther1Orphan());
			}
			if (selectedAnalyser.getOther2Orphan() != null)
			{
				selectedAnalyser.setOther2OrphanHdnVal(selectedAnalyser.getOther2Orphan());
			}

			logger.debug("Successfully loaded analyser with id : " + analyser.getAnalyserId());

			// To make sure that it is considered as a new record.
			selectedAnalyser.setAnalyserId(null);

			if (analyser.getBranch() != null && analyser.getBranch().trim().length() > 0)
			{
				// analyser.setBranch(analyser.getBranch().trim());
				if (analyser.getGeoOffice() == null || analyser.getGeoOffice().trim().length() == 0)
				{
					ReportUtil report = new ReportUtil();
					try
					{
						branchData = report.getBranches(FacesUtils.getCurrentUserId(), "0");
						if (branchData != null && !branchData.isEmpty())
						{
							for (int i = 0; i < branchData.size(); i++)
							{
								Map<String, Object> map = branchData.get(i);
								Integer id = (Integer) map.get("BRANCHID");
								String name = (String) map.get("BRANCHNAME");
								if (name.trim().equalsIgnoreCase(analyser.getBranch().trim()))
								{
									List<Map<String, Object>> branchOffices = report.getBrancheOffices(FacesUtils.getCurrentUserId(), id);
									if (branchOffices != null && branchOffices.size() > 0)
									{
										Map<String, Object> officeMap = branchOffices.get(0);
										name = (String) officeMap.get("GEOOFFICENAME");
										analyser.setGeoOffice(name);
									}
									break;
								}
							}
						}
					}
					catch (Exception e)
					{
					}
				}
			}

			showSaveButton = false;

			if (analyser.getGrossProfitPercentage() != null)
			{
				oldGp = analyser.getGrossProfitPercentage();
			}
			else
			{
				// For earlier records gp percentage was not saved, in that
				// assign the calculated gp percentage to this variable
				// this value is provided so that it can be checked at the front
				// end and if its still this value, replace it with the
				// calculated value of gp%
				oldGp = old_gp_default_value;
			}

			if (analyser.getHealth() != null)
			{
				if (analyser.getHealth().equals("0"))
				{
					healthCheck = false;
				}
				else
				{
					healthCheck = true;
				}
			}
			else
			{
				healthCheck = true;
			}

			if (analyser.getK401() != null)
			{
				if (analyser.getK401().equals("0"))
				{
					k401Check = false;
				}
				else
				{
					k401Check = true;
				}
			}
			else
			{
				k401Check = true;
			}

			if (analyser.getIsBonusEligible() != null)
			{
				if (analyser.getIsBonusEligible().equals("Y"))
				{
					analyser.setIsBonusEligible("true");
				}
				else
				{
					analyser.setIsBonusEligible("false");
				}
			}
			else
			{
				analyser.setIsBonusEligible("false");
			}

			if (analyser.getStdBenefit() != null)
			{
				if (analyser.getStdBenefit().equals("Y"))
				{
					analyser.setStdBenefit("true");
				}
				else
				{
					analyser.setStdBenefit("false");
				}
			}
			else
			{
				analyser.setStdBenefit("false");
			}

			if (analyser.getLtdBenefit() != null)
			{
				if (analyser.getLtdBenefit().equals("Y"))
				{
					analyser.setLtdBenefit("true");
				}
				else
				{
					analyser.setLtdBenefit("false");
				}
			}
			else
			{
				analyser.setLtdBenefit("false");
			}

			if (analyser.getDentalInsurance() != null)
			{
				if (analyser.getDentalInsurance().equals("Y"))
				{
					analyser.setDentalInsurance("true");
				}
				else
				{
					analyser.setDentalInsurance("false");
				}
			}
			else
			{
				analyser.setDentalInsurance("false");
			}

			if (analyser.getChkReferralFee() != null)
			{
				if (analyser.getChkReferralFee().equals("Y"))
				{
					analyser.setChkReferralFee("true");
				}
				else
				{
					analyser.setChkReferralFee("false");
				}
			}
			else
			{
				analyser.setChkReferralFee("false");
			}

			siteId = analyser.getClientSiteId().toString();
			System.out.println("Analyzer JSP SiteID = " + siteId);

			siteState = analyserService.getGenericDescription("WORKSITESTATE", siteId, "");

			siteZipCode = analyserService.getGenericDescription("WORKSITEZIPCODE", siteId, "");

			boolean rehiredFlag = analyserService.isItemPendingForUser(Integer.valueOf(analyser.getAnalyserId()), "REHIRE");

			logger.debug("Rehired flag : " + rehiredFlag);

			if (rehiredFlag || analyser.getAnalyserId() == null)
			{
				isRehired = "1";
			}
			else
			{
				isRehired = "0";
			}
			logger.debug("Is rehired : " + isRehired);

			isFalseTerminated = analyserService.getGenericDescription("FALSETERMINATION", analyser.getParentId(), analyser.getAnalyserId());

			logger.debug("falseTerminatedFlag : " + isFalseTerminated);

			oldStartDate = analyser.getStartDate();
			commStartDate = analyser.getCommEffDate();
			effectiveDate = analyser.getEffectiveDate();
			profitValue = analyser.getProfit();

			if (roleId == 3 || roleId == 447 || roleId == 448 || roleId == 453)
			{
				showSsn = true;
			}
			else
			{
				showSsn = false;
			}

			// for new records or terminated records needing approval
			employeeCategories = new ArrayList<SelectItem>();
			/*
			 * if (analyser.getRecordStatus().equalsIgnoreCase("1") ||
			 * (!analyser.getRecordStatus().equalsIgnoreCase("1") &&
			 * analyser.getEmpType().equalsIgnoreCase("PT"))) {
			 * employeeCategories = new ArrayList<SelectItem>();
			 * employeeCategories.add(new SelectItem("PT", "Part Time"));
			 * employeeCategories.add(new SelectItem("RFT / Hourly",
			 * "RFT / Hourly")); employeeCategories.add(new
			 * SelectItem("Projects", "Projects Techs"));
			 * employeeCategories.add(new SelectItem("IBT - Field Techs",
			 * "IBT - Field Techs")); } if
			 * (analyser.getRecordStatus().equalsIgnoreCase("1") ||
			 * (!analyser.getRecordStatus().equalsIgnoreCase("1") &&
			 * !analyser.getEmpType().equalsIgnoreCase("PT"))) {
			 * employeeCategories.add(new SelectItem("IT", "IT"));
			 * employeeCategories.add(new SelectItem("FA", "F&A")); }
			 */
			//employeeCategories.add(new SelectItem("IT", "IT"));
			//employeeCategories.add(new SelectItem("FA", "F&A"));
			//employeeCategories.add(new SelectItem("FA", "Non-IT"));	//12/23/2019 Tayyab
			employeeCategories.add(new SelectItem("IT", "Full Time"));
			employeeCategories.add(new SelectItem("PT", "Part Time"));
			analyser.setSickLeaveType("NB"); //Default for Sick Leave type
		}	//Only to handle Clone Test function
		else	
		{	//To handle both New & Modify

			if (analyserId == null)	//For New Add
			{
				analyser = new AnalyserDTO();
				action = "Add";
				showSaveButton = true;
				distance = "0.00";
				healthCheck = false;
				k401Check = false;
				siteState = "";
				siteZipCode = "";
				isRehired = "1";
				isFalseTerminated = "1";
				oldStartDate = "";
				commStartDate = "";
				effectiveDate = "";
				profitValue = 0.00;
				oldGp = old_gp_default_value;
				showSsn = true;
				siteId = "";
				analyser.setSickLeaveType("NB");
				analyser.setCompanyCode(companyCodeLocal);
			}
			else if(action.equalsIgnoreCase("Modify"))
			{	//Modify Analyzer

				distance = "0.00";
				analyser = analyserService.getAnalyserInfo(analyserId);
				String message = "Analyser Loaded with Analyzer Id = "+analyser.getAnalyserId()+" , UserId = "+FacesUtils.getCurrentUserId()+" and following values";
				logger.debug(message);
				logger.debug(analyser.toString());
				analyser.setExecOrphanHdnVal(false);
				analyser.setRecruiterOrphanHdnVal(false);
				analyser.setOther1OrphanHdnVal(false);
				analyser.setOther2OrphanHdnVal(false);
				if (analyser.getExecOrphan() != null)
				{
					analyser.setExecOrphanHdnVal(analyser.getExecOrphan());
				}
				if (analyser.getRecruiterOrphan() != null)
				{
					analyser.setRecruiterOrphanHdnVal(analyser.getRecruiterOrphan());
				}
				if (analyser.getOther1Orphan() != null)
				{
					analyser.setOther1OrphanHdnVal(analyser.getOther1Orphan());
				}
				if (analyser.getOther2Orphan() != null)
				{
					analyser.setOther2OrphanHdnVal(analyser.getOther2Orphan());
				}
				if (analyser.getRejectionStatus() == 1)
				{
					showRejectionDiv = true;
				}

				/*
				 * Added as part of sick Leave project
				 */
				getSickLeaveDetails(analyser);

				selectedAnalyser = analyserService.getAnalyserInfo(analyserId);
				System.out.println("Successfully loaded analyser with id : " + analyser.getAnalyserId());
				logger.debug("Successfully loaded analyser with id : " + analyser.getAnalyserId());
				showSaveButton = false;
				selectedAnalyser.setExecOrphanHdnVal(false);
				selectedAnalyser.setRecruiterOrphanHdnVal(false);
				selectedAnalyser.setOther1OrphanHdnVal(false);
				selectedAnalyser.setOther2OrphanHdnVal(false);
				if (selectedAnalyser.getExecOrphan() != null)
				{
					selectedAnalyser.setExecOrphanHdnVal(selectedAnalyser.getExecOrphan());
				}
				if (selectedAnalyser.getRecruiterOrphan() != null)
				{
					selectedAnalyser.setRecruiterOrphanHdnVal(selectedAnalyser.getRecruiterOrphan());
				}
				if (selectedAnalyser.getOther1Orphan() != null)
				{
					selectedAnalyser.setOther1OrphanHdnVal(selectedAnalyser.getOther1Orphan());
				}
				if (selectedAnalyser.getOther2Orphan() != null)
				{
					selectedAnalyser.setOther2OrphanHdnVal(selectedAnalyser.getOther2Orphan());
				}

				if (analyser.getFlsaReference() != null)
				{
					prevFlsaRef = analyser.getFlsaReference();
					validFlsaRefernce = true;
				}
				if (analyser.getSsn() != null)
				{
					prevSsn = analyser.getSsn();
					/*
					 * Show only last four numbers of SSN, and mask all others for everyone except Admins
					 */
					if (roleId != 3 && roleId != 447 && roleId != 448 && roleId != 453)
					{
						String maskedSsn = analyser.getSsn();
						// 7 is the length uptil second - in the ssn string
						maskedSsn = "***-**-"+maskedSsn.substring(7,maskedSsn.length());
						System.out.println("Masked SSN is : "+maskedSsn);
						analyser.setSsn(maskedSsn);
						showSsn = false;
					} else {
						showSsn = true;
					}
					validSsn = true;
				}
				if (analyser.getCommEffDate() != null && analyser.getCommEffDate().trim().length() > 0)
				{
					String[] splitDateAry = analyser.getCommEffDate().split("/");
					if (splitDateAry != null && splitDateAry.length == 3)
					{
						if (splitDateAry[0].length() < 2)
						{
							splitDateAry[0] = "0" + splitDateAry[0];
						}
						if (splitDateAry[1].length() < 2)
						{
							splitDateAry[1] = "0" + splitDateAry[1];
						}
						analyser.setCommEffDate(splitDateAry[0] + "/" + splitDateAry[1] + "/" + splitDateAry[2]);
					}
				}

				if (analyser.getBranch() != null && analyser.getBranch().trim().length() > 0)
				{
					ReportUtil report = new ReportUtil();
					try
					{
						branchData = report.getBranches(FacesUtils.getCurrentUserId(), "0");
						if (branchData != null && !branchData.isEmpty())
						{
							for (int i = 0; i < branchData.size(); i++)
							{
								Map<String, Object> map = branchData.get(i);
								String name = (String) map.get("BRANCHNAME");
								if (name.trim().equalsIgnoreCase(analyser.getBranch().trim()))
								{
									latitude = (String) map.get("LATITUDE");
									longitude = (String) map.get("LONGITUDE");
									branchAddress.append(map.get("ADDRESS1"));
									branchAddress.append(" " + (map.get("ADDRESS2") == null ? "" : map.get("ADDRESS2")));
									branchAddress.append("," + map.get("CITY"));
									branchAddress.append("," + map.get("STATE"));
									branchAddress.append(" " + map.get("ZIPCODE"));

									if (latitude == null || latitude.trim().equals("") || longitude == null || longitude.trim().equals(""))
									{
										distance = branch_distance_error_msg;
									}
									break;
								}
							}
						}
						if (analyser.getClientSiteId() != null)
						{
							DecimalFormat df = new DecimalFormat("0.00");
							try
							{
								List<Map<String, Object>> clientSiteData = report.getClientSite(FacesUtils.getCurrentUserId(),
										analyser.getClientSiteId());
								if (clientSiteData != null && !clientSiteData.isEmpty())
								{
									Map<String, Object> map = clientSiteData.get(0);

									AnalyserClientSite analyserClientSite = createAnalyserClientSiteObject(map);

									String isUspsValidated = analyserClientSite.getIsAddressUSPSValidated();
									if (isUspsValidated != null && isUspsValidated.trim().equalsIgnoreCase("1"))
									{
										workSiteLatitude = analyserClientSite.getLatitude();
										workSiteLongitude = analyserClientSite.getLongitude();

										StringBuilder workSiteAddress = new StringBuilder();
										workSiteAddress.append(analyserClientSite.getAddress1());
										workSiteAddress
										.append(" " + (analyserClientSite.getAddress2() == null ? "" : analyserClientSite.getAddress2()));
										workSiteAddress.append("," + analyserClientSite.getCity());
										workSiteAddress.append("," + analyserClientSite.getState());
										workSiteAddress.append(" " + analyserClientSite.getZipCode());

										if ((workSiteLatitude != null && workSiteLatitude.length() > 0)
												&& (workSiteLongitude != null && workSiteLongitude.length() > 0))
										{
											if (!(latitude == null || latitude.trim().equals("") || longitude == null || longitude.trim().equals("")))
											{	
												Double calulateDistance = FacesUtils.calculateRouteDistance(branchAddress.toString(),
														workSiteAddress.toString());
												distance = df.format(calulateDistance);
											}
										}
									}
									else
									{	
										Map<Integer, String> status = objSmarty.validateAddress(analyserClientSite.getAddress1(),
												analyserClientSite.getAddress2(), analyserClientSite.getCity(), analyserClientSite.getState(),
												analyserClientSite.getZipCode());

										Integer code = 0;
										String response = "";
										for (Entry<Integer, String> entry : status.entrySet())
										{
											code = entry.getKey();
											response = entry.getValue();
										}
										if (code == 200)
										{
											if (!response.startsWith("["))
											{
												response = "[" + response + "]";
											}

											JSONArray jsonArray = new JSONArray(response.toString());
											JSONObject myResponse = jsonArray.getJSONObject(0);
											JSONObject longLat = myResponse.getJSONObject("metadata");

											try
											{

												workSiteLatitude = String.valueOf(longLat.getDouble("latitude"));
												workSiteLongitude = String.valueOf(longLat.getDouble("longitude"));

												analyserClientSite.setIsAddressUSPSValidated("1");
												analyserClientSite.setLongitude(workSiteLongitude);
												analyserClientSite.setLatitude(workSiteLatitude);
												analyserClientSite.setUpdatedBy(FacesUtils.getCurrentUserId());
												analyserClientSite.setValidatedBy(FacesUtils.getCurrentUserId());
												Instant now = Instant.now();
												Timestamp current = Timestamp.from(now);
												// set nanos to 0 otherwise
												// occured an error on saving
												// date
												current.setNanos(0);
												analyserClientSite.setuSPSAddressValidationDate(current);
												analyserClientSite.setUpdatedDate(current);

												int actionType = 2;
												String result = analyserClientSiteService.addUpdateAnalyserSite(analyserClientSite,
														FacesUtils.getCurrentUserId(), actionType);

												if (result.equals("0"))
												{
													logger.debug("Client work site couldn't be updated for longitude ("
															+ analyserClientSite.getLongitude() + ") and latitude ("
															+ analyserClientSite.getLatitude() + ") : " + analyserClientSite.getSiteId());
													System.err.println("Client work site couldn't be updated for longitude ("
															+ analyserClientSite.getLongitude() + ") and latitude ("
															+ analyserClientSite.getLatitude() + ") : " + analyserClientSite.getSiteId());

													StringBuilder workSiteAddress = new StringBuilder();
													workSiteAddress.append(analyserClientSite.getAddress1());
													workSiteAddress.append(
															" " + (analyserClientSite.getAddress2() == null ? "" : analyserClientSite.getAddress2()));
													workSiteAddress.append("," + analyserClientSite.getCity());
													workSiteAddress.append("," + analyserClientSite.getState());
													workSiteAddress.append(" " + analyserClientSite.getZipCode());

													if ((workSiteLatitude != null && workSiteLatitude.length() > 0)
															&& (workSiteLongitude != null && workSiteLongitude.length() > 0))
													{
														if (!(latitude == null || latitude.trim().equals("") || longitude == null
																|| longitude.trim().equals("")))
														{
															Double calulateDistance = FacesUtils.calculateRouteDistance(branchAddress.toString(),
																	workSiteAddress.toString());
															distance = df.format(calulateDistance);
														}
													}
												}
												else if (result.equals("1"))
												{
													logger.debug("Client work site updated successfully");
													System.err.println("Client work site updated successfully");
												}

											}
											catch (org.json.JSONException ex)
											{
												System.err.println("Json exception...");
											}

										}
										else
										{
											distance = "Address not validated.";
										}
									}
								}
							}
							catch (Exception e)
							{
								e.printStackTrace();
							}
						}
					}
					catch (Exception e1)
					{
						e1.printStackTrace();
					}

					System.out.println("Branch Name = " + analyser.getBranch());
					if (analyser.getGeoOffice() == null || analyser.getGeoOffice().trim().length() == 0)
					{
						try
						{
							if (branchData != null && !branchData.isEmpty())
							{
								for (int i = 0; i < branchData.size(); i++)
								{
									Map<String, Object> map = branchData.get(i);
									Integer id = (Integer) map.get("BRANCHID");
									String name = (String) map.get("BRANCHNAME");
									System.out.println("Branch Name = " + name);
									if (name.trim().equalsIgnoreCase(analyser.getBranch().trim()))
									{
										List<Map<String, Object>> branchOffices = report.getBrancheOffices(FacesUtils.getCurrentUserId(), id);
										if (branchOffices != null && branchOffices.size() > 0)
										{
											Map<String, Object> officeMap = branchOffices.get(0);
											name = (String) officeMap.get("GEOOFFICENAME");
											analyser.setGeoOffice(name);
										}
										break;
									}
								}
							}
						}
						catch (Exception e)
						{
						}
					}
				}

				if (analyser.getGrossProfitPercentage() != null)
				{
					oldGp = analyser.getGrossProfitPercentage();
				}
				else
				{
					// For earlier records gp percentage was not saved, in that
					// assign the calculated gp percentage to this variable
					// this value is provided so that it can be checked at the
					// front end and if its still this value, replace it with
					// the calculated value of gp%
					oldGp = old_gp_default_value;
				}
				if (analyser.getHealth() != null)
				{
					if (analyser.getHealth().equals("0"))
					{
						healthCheck = false;
					}
					else
					{
						healthCheck = true;
					}
				}
				else
				{
					healthCheck = true;
				}

				if (analyser.getK401() != null)
				{
					if (analyser.getK401().equals("0"))
					{
						k401Check = false;
					}
					else
					{
						k401Check = true;
					}
				}
				else
				{
					k401Check = true;
				}

				if (analyser.getIsBonusEligible() != null)
				{
					if (analyser.getIsBonusEligible().equals("Y"))
					{
						analyser.setIsBonusEligible("true");
					}
					else
					{
						analyser.setIsBonusEligible("false");
					}
				}
				else
				{
					analyser.setIsBonusEligible("false");
				}

				if (analyser.getStdBenefit() != null)
				{
					if (analyser.getStdBenefit().equals("Y"))
					{
						analyser.setStdBenefit("true");
					}
					else
					{
						analyser.setStdBenefit("false");
					}
				}
				else
				{
					analyser.setStdBenefit("false");
				}

				if (analyser.getLtdBenefit() != null)
				{
					if (analyser.getLtdBenefit().equals("Y"))
					{
						analyser.setLtdBenefit("true");
					}
					else
					{
						analyser.setLtdBenefit("false");
					}
				}
				else
				{
					analyser.setLtdBenefit("false");
				}

				if (analyser.getDentalInsurance() != null)
				{
					if (analyser.getDentalInsurance().equals("Y"))
					{
						analyser.setDentalInsurance("true");
					}
					else
					{
						analyser.setDentalInsurance("false");
					}
				}
				else
				{
					analyser.setDentalInsurance("false");
				}

				if (analyser.getChkReferralFee() != null)
				{
					if (analyser.getChkReferralFee().equals("Y"))
					{
						analyser.setChkReferralFee("true");
					}
					else
					{
						analyser.setChkReferralFee("false");
					}
				}
				else
				{
					analyser.setChkReferralFee("false");
				}

				siteId = analyser.getClientSiteId().toString();
				System.out.println("Analyzer JSP SiteID = " + siteId);

				/*//Incorrect
				if(siteId != null){
					setUpdatedDiscountRate(Integer.valueOf(siteId));
				}
				 */
				System.out.println("Analyzer JSP ClientId in Analyzer Bean = " + analyser.getClientId());
				if(analyser.getClientId() != null)
				{
					setUpdatedDiscountRate(Integer.valueOf(analyser.getClientId()));
				}

				siteState = analyserService.getGenericDescription("WORKSITESTATE", siteId, "");

				siteZipCode = analyserService.getGenericDescription("WORKSITEZIPCODE", siteId, "");

				boolean rehiredFlag = analyserService.isItemPendingForUser(Integer.valueOf(analyser.getAnalyserId()), "REHIRE");

				logger.debug("Rehired flag : " + rehiredFlag);

				if (rehiredFlag || analyser.getAnalyserId() == null)
				{
					isRehired = "1";
				}
				else
				{
					isRehired = "0";
				}
				logger.debug("Is rehired : " + isRehired);

				isFalseTerminated = analyserService.getGenericDescription("FALSETERMINATION", analyser.getParentId(),
						analyser.getAnalyserId());

				logger.debug("falseTerminatedFlag : " + isFalseTerminated);

				oldStartDate = analyser.getStartDate();
				commStartDate = analyser.getCommEffDate();
				effectiveDate = analyser.getEffectiveDate();
				profitValue = analyser.getProfit();

				/*
				 * Sajid - 01292019
				 * While modifying a record, Grade will be calculated when
				 * 1 - CommName1 is not empty and Grade1 is empty
				 * 2 - CommName3 is not empty and Grade3 is empty
				 */
				if(analyser.getCommName1() != null && !analyser.getCommName1().isEmpty()) {
					if(analyser.getCommissionPersonGrade1() != null 
							&& 
							!(analyser.getCommissionPersonGrade1().equalsIgnoreCase("A")
									|| analyser.getCommissionPersonGrade1().equalsIgnoreCase("B")
									|| analyser.getCommissionPersonGrade1().equalsIgnoreCase("C")
									|| analyser.getCommissionPersonGrade1().equalsIgnoreCase("D")
									|| analyser.getCommissionPersonGrade1().equalsIgnoreCase("E")
									|| analyser.getCommissionPersonGrade1().equalsIgnoreCase("F"))) {
						logger.debug("Commission Person Grade 1 was missing");
						String status = analyserService.getGenericDescription("SCORECARDGRADING", 
								analyser.getCommName1(), "");
						if(status.equals("0")){
							status = "Z";
						}	
						analyser.setCommissionPersonGrade1(status);
						logger.debug("Setting it to : "+status);
					}
				}
				
				if(analyser.getCommName3() != null && !analyser.getCommName3().isEmpty()) {
					if(analyser.getCommissionPersonGrade3() != null 
							&&
							!(analyser.getCommissionPersonGrade1().equalsIgnoreCase("A")
									|| analyser.getCommissionPersonGrade3().equalsIgnoreCase("B")
									|| analyser.getCommissionPersonGrade3().equalsIgnoreCase("C")
									|| analyser.getCommissionPersonGrade3().equalsIgnoreCase("D")
									|| analyser.getCommissionPersonGrade3().equalsIgnoreCase("E")
									|| analyser.getCommissionPersonGrade3().equalsIgnoreCase("F"))) {
						logger.debug("Commission Person Grade 3 was missing");
						String status = analyserService.getGenericDescription("SCORECARDGRADING", 
								analyser.getCommName3(), "");
						if(status.equals("0")){
							status = "Z";
						}	
						analyser.setCommissionPersonGrade3(status);
						logger.debug("Setting it to : "+status);
					}

				}

				// for new records or terminated records needing approval
				employeeCategories = new ArrayList<SelectItem>();
				//employeeCategories.add(new SelectItem("IT", "IT"));
				//employeeCategories.add(new SelectItem("FA", "F&A"));
				//employeeCategories.add(new SelectItem("FA", "Non-IT"));	//12/23/2019 Tayyab
				employeeCategories.add(new SelectItem("IT", "Full Time"));	//01/30/2020 Sajid
				employeeCategories.add(new SelectItem("PT", "Part Time"));
				if (analyser.getSickLeaveType() == null || analyser.getSickLeaveType().equals(""))
				{
					analyser.setSickLeaveType("NB");
				}
			} else if(action.equalsIgnoreCase("View")){
				//View Analyzer

				distance = "0.00";
				analyser = analyserService.getAnalyserInfo(analyserId);
				logger.debug("Analyser found with following values");

				if (analyser.getRejectionStatus() == 1)
				{
					showRejectionDiv = true;
				}
				distance = analyser.getDistanceFromWorksite();
				System.out.println("Successfully loaded analyser with id : " + analyser.getAnalyserId());
				logger.debug("Successfully loaded analyser with id : " + analyser.getAnalyserId());
				showSaveButton = false;

				if (analyser.getFlsaReference() != null)
				{
					prevFlsaRef = analyser.getFlsaReference();
					validFlsaRefernce = true;
				}
				if (analyser.getSsn() != null)
				{
					prevSsn = analyser.getSsn();
					/*
					 * Show only last four numbers of SSN, and mask all others for everyone except Admins
					 */
					if (roleId != 3 && roleId != 447 && roleId != 448 && roleId != 453)
					{
						String maskedSsn = analyser.getSsn();
						// 7 is the length uptil second - in the ssn string
						maskedSsn = "***-**-"+maskedSsn.substring(7,maskedSsn.length());
						System.out.println("Masked SSN is : "+maskedSsn);
						analyser.setSsn(maskedSsn);
						showSsn = false;
					} else {
						showSsn = true;
					}
					validSsn = true;
				}

				if (analyser.getGrossProfitPercentage() != null)
				{
					oldGp = analyser.getGrossProfitPercentage();
				}
				if (analyser.getHealth() != null)
				{
					if (analyser.getHealth().equals("0"))
					{
						healthCheck = false;
					}
					else
					{
						healthCheck = true;
					}
				}

				if (analyser.getK401() != null)
				{
					if (analyser.getK401().equals("0"))
					{
						k401Check = false;
					}
					else
					{
						k401Check = true;
					}
				}

				if (analyser.getIsBonusEligible() != null)
				{
					if (analyser.getIsBonusEligible().equals("Y"))
					{
						analyser.setIsBonusEligible("true");
					}
					else
					{
						analyser.setIsBonusEligible("false");
					}
				}

				if (analyser.getStdBenefit() != null)
				{
					if (analyser.getStdBenefit().equals("Y"))
					{
						analyser.setStdBenefit("true");
					}
					else
					{
						analyser.setStdBenefit("false");
					}
				}

				if (analyser.getLtdBenefit() != null)
				{
					if (analyser.getLtdBenefit().equals("Y"))
					{
						analyser.setLtdBenefit("true");
					}
					else
					{
						analyser.setLtdBenefit("false");
					}
				}

				if (analyser.getDentalInsurance() != null)
				{
					if (analyser.getDentalInsurance().equals("Y"))
					{
						analyser.setDentalInsurance("true");
					}
					else
					{
						analyser.setDentalInsurance("false");
					}
				}

				if (analyser.getChkReferralFee() != null)
				{
					if (analyser.getChkReferralFee().equals("Y"))
					{
						analyser.setChkReferralFee("true");
					}
					else
					{
						analyser.setChkReferralFee("false");
					}
				}

				siteId = analyser.getClientSiteId().toString();
				System.out.println("Analyzer SiteID = " + siteId);

				siteState = analyserService.getGenericDescription("WORKSITESTATE", siteId, "");

				siteZipCode = analyserService.getGenericDescription("WORKSITEZIPCODE", siteId, "");

				boolean rehiredFlag = analyserService.isItemPendingForUser(Integer.valueOf(analyser.getAnalyserId()), "REHIRE");

				logger.debug("Rehired flag : " + rehiredFlag);

				if (rehiredFlag || analyser.getAnalyserId() == null)
				{
					isRehired = "1";
				}
				else
				{
					isRehired = "0";
				}
				logger.debug("Is rehired : " + isRehired);

				isFalseTerminated = analyserService.getGenericDescription("FALSETERMINATION", analyser.getParentId(),
						analyser.getAnalyserId());

				logger.debug("falseTerminatedFlag : " + isFalseTerminated);

				oldStartDate = analyser.getStartDate();
				commStartDate = analyser.getCommEffDate();
				effectiveDate = analyser.getEffectiveDate();
				profitValue = analyser.getProfit();

				// for new records or terminated records needing approval
				employeeCategories = new ArrayList<SelectItem>();

				//employeeCategories.add(new SelectItem("IT", "IT"));
				//employeeCategories.add(new SelectItem("FA", "F&A"));
				//employeeCategories.add(new SelectItem("FA", "Non-IT"));	//12/23/2019 Tayyab
				employeeCategories.add(new SelectItem("IT", "Full Time"));	//01/30/2020 Sajid
				employeeCategories.add(new SelectItem("PT", "Part Time"));
				if (analyser.getSickLeaveType() == null || analyser.getSickLeaveType().equals(""))
				{
					analyser.setSickLeaveType("NB");
				}
			}
		}

		role = roleService.getUserRole(FacesUtils.getCurrentUserId());
		if (role != null)
		{
			if (role.getRoleDesc().equalsIgnoreCase(Constants.SERVICES_DEALS))
			{
				System.out.println("User role is : " + role.getRoleDesc());
				hideAdditionalCommissionPersonsDiv = true;
			}
			else
			{
				System.out.println("User role is : " + role.getRoleDesc());
				hideAdditionalCommissionPersonsDiv = false;
			}
		}
	}

	public void setAnalyserValues(Map<String, String> params)
	{

		if(params != null && params.size() > 0){
			Iterator<Map.Entry<String, String>> iterator = params.entrySet().iterator();
			while (iterator.hasNext())
			{
				Map.Entry<String, String> obj = iterator.next();
				System.out.println("=== ( "+obj.getKey() + " = "+obj.getValue().toString()+" )");
				logger.debug("=== ( "+obj.getKey() + " = "+obj.getValue().toString()+" )");
			}
		}

		String analyserId1 = (String) params.get("analyserForm:vertical");
		String analyserId = (String) params.get("vertical");
		System.out.println(analyserId);
		System.out.println(analyserId1);

		analyser.setCommPer1(params.get("analyserForm:commPer1") == null ? 0.0 : Double.valueOf(params.get("analyserForm:commPer1")));
		analyser.setCommissionPersonGrade1(params.get("analyserForm:commissionPersonGrade1"));
		analyser.setCommision1((String) params.get("analyserForm:commision1"));
		analyser.setCommPer2(params.get("analyserForm:commPer2") == null ? 0.0 : Double.valueOf(params.get("analyserForm:commPer2")));
		analyser.setCommision2((String) params.get("analyserForm:commision2"));
		analyser.setCommPer3(params.get("analyserForm:commPer3") == null ? 0.0 : Double.valueOf(params.get("analyserForm:commPer3")));
		analyser.setCommissionPersonGrade3(params.get("analyserForm:commissionPersonGrade3"));
		analyser.setCommision3((String) params.get("analyserForm:commision3"));
		analyser.setCommPer4(params.get("analyserForm:commPer4") == null ? 0.0 : Double.valueOf(params.get("analyserForm:commPer4")));
		analyser.setCommision4((String) params.get("analyserForm:commision4"));
		analyser.setCommissionPercentage5(params.get("analyserForm:commPer5") == null ? 0.0 : Double.valueOf(params.get("analyserForm:commPer5")));
		analyser.setCommission5(params.get("analyserForm:commision5") == null ? 0.0 : Double.valueOf(params.get("analyserForm:commision5")));
		analyser.setCommissionPercentage6(params.get("analyserForm:commPer6") == null ? 0.0 : Double.valueOf(params.get("analyserForm:commPer5")));
		analyser.setCommission6(params.get("analyserForm:commision6") == null ? 0.0 : Double.valueOf(params.get("analyserForm:commision6")));
		analyser.setCommissionPercentage7(params.get("analyserForm:commPer7") == null ? 0.0 : Double.valueOf(params.get("analyserForm:commPer5")));
		analyser.setCommission7(params.get("analyserForm:commision7") == null ? 0.0 : Double.valueOf(params.get("analyserForm:commision7")));
		analyser.setCommissionPercentage8(params.get("analyserForm:commPer8") == null ? 0.0 : Double.valueOf(params.get("analyserForm:commPer5")));
		analyser.setCommission8(params.get("analyserForm:commision8") == null ? 0.0 : Double.valueOf(params.get("analyserForm:commision8")));
		analyser.setCommissionPercentage9(params.get("analyserForm:commPer9") == null ? 0.0 : Double.valueOf(params.get("analyserForm:commPer5")));
		analyser.setCommission9(params.get("analyserForm:commision9") == null ? 0.0 : Double.valueOf(params.get("analyserForm:commision9")));

		analyser.setIsBonusEligible(params.get("analyserForm:isBonusEligible") == null ? "false" : params.get("analyserForm:isBonusEligible"));
		analyser.setBonusAmount(params.get("analyserForm:bonusAmount") == null ? 0.0 : Double.valueOf(params.get("analyserForm:bonusAmount")));
		analyser.setContCompanyName(params.get("analyserForm:contCompanyName"));
		analyser.setAnnualPay(params.get("analyserForm:annualPay"));
		analyser.setHealth(params.get("analyserForm:health"));
		analyser.setDiscountRate(params.get("analyserForm:discountRate"));
		analyser.setDiscount(params.get("analyserForm:discount"));

		analyser.setCommission(params.get("analyserForm:commission") == null ? 0.0 : Double.valueOf(params.get("analyserForm:commission")));
		analyser.setGrossProfit(params.get("analyserForm:gp") == null ? 0.0 : Double.valueOf(params.get("analyserForm:gp")));
		analyser.setLeave(params.get("analyserForm:leave") == null ? 0.0 : Double.valueOf(params.get("analyserForm:leave")));
		analyser.setSickLeaveCost(params.get("analyserForm:sickLeaveCost") == null ? 0.0 : Double.valueOf(params.get("analyserForm:sickLeaveCost")));
		analyser.setTax(params.get("analyserForm:tax"));
		analyser.setGa(params.get("analyserForm:ga") == null ? 0.0 : Double.valueOf(params.get("analyserForm:ga")));
		analyser.setSpreadWeek(params.get("analyserForm:spreadWeek"));
		analyser.setProfit(params.get("analyserForm:profit") == null ? 0.0 : Double.valueOf(params.get("analyserForm:profit")));
		analyser.setK401(params.get("analyserForm:k401"));
		analyser.setReferralFeeAmount(params.get("analyserForm:referralFeeAmount"));

		analyser.setClientCompany(params.get("analyserForm:clientCompanyName"));
		analyser.setAttention(params.get("analyserForm:attention"));
		analyser.setPaymentTerms(params.get("analyserForm:paymentTerms"));
		analyser.setDistributionMethod(params.get("analyserForm:distributionMethod"));
		analyser.setClientEmail(params.get("analyserForm:invoiceEmail"));
		analyser.setInvoiceFrequency(params.get("analyserForm:invoiceFrequency"));
		analyser.setBillingType(params.get("analyserForm:billingType"));
		analyser.setDeliveryType(params.get("analyserForm:deliveryType"));
		analyser.setPracticeArea(params.get("analyserForm:practiceArea"));

		analyser.setSiteLocation(params.get("analyserForm:siteLocation"));
		analyser.setManagerEmail(params.get("analyserForm:managerEmail"));
		analyser.setTermDate(params.get("analyserForm:termDate"));
		analyser.setReason(params.get("analyserForm:reason"));
		analyser.setBackgroundCheckAmount(
				params.get("analyserForm:backgroundCheckAmount") == null ? 0.0 : Double.valueOf(params.get("analyserForm:backgroundCheckAmount")));
		analyser.setParentId(params.get("analyserForm:parentId"));
		analyser.setAnalyserId(params.get("analyserForm:analyzerId"));
		analyser.setClientAddressId(
				params.get("analyserForm:clientAddressId") == null ? 0 : Integer.valueOf(params.get("analyserForm:clientAddressId")));
		analyser.setSickLeavePerHourRate(
				params.get("analyserForm:sickLeavePerHourRate") == null ? 0 : Double.valueOf(params.get("analyserForm:sickLeavePerHourRate")));
		analyser.setSickLeaveCap(params.get("analyserForm:sickLeaveCap"));
		analyser.setGrossProfit(params.get("analyserForm:gProfit") == null ? 0.0 : Double.valueOf(params.get("analyserForm:gProfit")));
		analyser.setGrossProfitPercentage(params.get("analyserForm:gp") == null ? 0.0 : Double.valueOf(params.get("analyserForm:gp")));
		analyser.setCommissionableProfit(params.get("analyserForm:cmProfit") == null ? 0.0 : Double.valueOf(params.get("analyserForm:cmProfit")));
		analyser.setRejectionStatus(0);
		analyser.setGeoOffice(params.get("analyserForm:region"));
		analyser.setpTOLimitToOverrideSick(
				params.get("analyserForm:pTOLimitToOverrideSick") == null ? 0.0 : Double.valueOf(params.get("analyserForm:pTOLimitToOverrideSick")));

		analyser.setBillablePTOCost(
				params.get("analyserForm:billablePTOCost") == null ? 0.0 : Double.valueOf(params.get("analyserForm:billablePTOCost")));
		analyser.setNonBillablePTOCost(
				params.get("analyserForm:nonBillablePTOCost") == null ? 0.0 : Double.valueOf(params.get("analyserForm:nonBillablePTOCost")));
		analyser.setBillableHolidaysCost(
				params.get("analyserForm:billableHolidayCost") == null ? 0.0 : Double.valueOf(params.get("analyserForm:billableHolidayCost")));
		analyser.setNonBillableHolidaysCost(params.get("analyserForm:nonBillableHolidayCost") == null ? 0.0
				: Double.valueOf(params.get("analyserForm:nonBillableHolidayCost")));

		analyser.setBillablePTO(
				params.get("analyserForm:billablePTO") == null ? 0.0 : Double.valueOf(params.get("analyserForm:billablePTO")));
		analyser.setNonBillablePTO(
				params.get("analyserForm:nonBillablePTO") == null ? 0.0 : Double.valueOf(params.get("analyserForm:nonBillablePTO")));
		analyser.setBillableHolidays(
				params.get("analyserForm:billableHolidays") == null ? 0.0 : Double.valueOf(params.get("analyserForm:billableHolidays")));
		analyser.setNonBillableHolidays(
				params.get("analyserForm:nonBillableHolidays") == null ? 0.0 : Double.valueOf(params.get("analyserForm:nonBillableHolidays")));
		analyser.setTotalHolidays(
				params.get("analyserForm:totalHolidays") == null ? 0.0 : Double.valueOf(params.get("analyserForm:totalHolidays")));

		analyser.setSickLeaveType(params.get("analyserForm:sickLeaveType"));
		analyser.setCompanyCode(params.get("analyserForm:companyCode"));
		analyser.setDealPortfolio1AE1(params.get("analyserForm:dealPortfolio1AE1"));
		analyser.setDealPortfolio2REC1(params.get("analyserForm:dealPortfolio2REC1"));
		analyser.setDealPortfolio3AE2(params.get("analyserForm:dealPortfolio3AE2"));
		analyser.setDealPortfolio4REC2(params.get("analyserForm:dealPortfolio4REC2"));
		analyser.setBullhornBatchDataProcessedId(
				params.get("analyserForm:bullhornBatchDataProcessedId") == null ? 0L : Long.valueOf(params.get("analyserForm:bullhornBatchDataProcessedId")));
		analyser.setCoSellStatus(params.get("analyserForm:coSellStatus"));
		analyser.setDataSource(params.get("analyserForm:dataSource"));
		analyser.setBullhornBatchCode(params.get("analyserForm:bullhornBatchCode"));		
		analyser.setBullhornBatchAnalyzerStagingId(
				params.get("analyserForm:bullhornBatchAnalyzerStagingId") == null ? 0L : Long.valueOf(params.get("analyserForm:bullhornBatchAnalyzerStagingId")));
		analyser.setPlacementType(params.get("analyserForm:placementType"));
		/*
		 * Added as part of Tax and Sick Leave Project
		 */
		analyser.setBullhornPlacementId(
				params.get("analyserForm:bullhornPlacementId") == null || StringUtils.isEmpty(params.get("analyserForm:bullhornPlacementId")) ? 0 : Integer.valueOf(params.get("analyserForm:bullhornPlacementId")));
		analyser.setBullhornBatchInfoId(
				params.get("analyserForm:bullhornBatchInfoId") == null ? 0 : Integer.valueOf(params.get("analyserForm:bullhornBatchInfoId")));
		analyser.setIsModificationRequired(params.get("analyserForm:isModificationRequired"));
		analyser.setBullhornTerminationDataProcessedId(
				params.get("analyserForm:bullhornTerminationDataProcessedId") == null ? 0L : Long.valueOf(params.get("analyserForm:bullhornTerminationDataProcessedId")));
		analyser.setBullhornTerminationDataStagingId(
				params.get("analyserForm:bullhornTerminationDataStagingId") == null ? 0L : Long.valueOf(params.get("analyserForm:bullhornTerminationDataStagingId")));
		analyser.setTerminationBullhornBatchInfoId(
				params.get("analyserForm:terminationBullhornBatchInfoId") == null ? 0 : Integer.valueOf(params.get("analyserForm:terminationBullhornBatchInfoId")));
		analyser.setOverrideTermination(params.get("analyserForm:overrideTermination"));
		analyser.setWorkFromSource(params.get("analyserForm:workFromSource"));
		analyser.setMajorityWorkPerformed(params.get("analyserForm:majorityWorkPerformed"));
		analyser.setSickLeaveSource(params.get("analyserForm:sickLeaveSource"));


	}

	public void setAnalyserDefaultValues(){
		// set null values for Stored procedure insertion
		if (analyser.getCommPer1() == null)
		{
			analyser.setCommPer1(0.00);
		}
		if (analyser.getCommPer2() == null)
		{
			analyser.setCommPer2(0.00);
		}
		if (analyser.getCommPer3() == null)
		{
			analyser.setCommPer3(0.00);
		}
		if (analyser.getCommPer4() == null)
		{
			analyser.setCommPer4(0.00);
		}

		if(analyser.getCommission5() != null) {
			if(analyser.getCommission5() == Double.NaN) {
				analyser.setCommission5(0.0);
			}
		}

		if(analyser.getCommission6() != null) {
			if(analyser.getCommission6() == Double.NaN) {
				analyser.setCommission6(0.0);
			}
		}
		if(analyser.getCommission7() != null) {
			if(analyser.getCommission7() == Double.NaN) {
				analyser.setCommission7(0.0);
			}
		}
		if(analyser.getCommission8() != null) {
			if(analyser.getCommission8() == Double.NaN) {
				analyser.setCommission8(0.0);
			}
		}
		if(analyser.getCommission9() != null) {
			if(analyser.getCommission9() == Double.NaN) {
				analyser.setCommission9(0.0);
			}
		}

		if(analyser.getCommission() != null) {
			if(analyser.getCommission() == Double.NaN) {
				analyser.setCommission(0.0);
			}
		}

		if (analyser.getLeave() == null)
		{
			analyser.setLeave(0.00);
		}
		if (analyser.getHealth() == null)
		{
			analyser.setHealth("0");
		}
		if (analyser.getK401() == null)
		{
			analyser.setK401("0");
		}
		if (analyser.getPayRate() == null)
		{
			analyser.setPayRate(0.00);
		}
		if (analyser.getGa() == null)
		{
			analyser.setGa(0.00);
		}
		if (analyser.getTermDate() == null)
		{
			analyser.setTermDate("");
		}
		if (analyser.getCommision1() == null || analyser.getCommision1().trim().equals(""))
		{
			analyser.setCommision1("0.00");
		}
		if (analyser.getCommision2() == null || analyser.getCommision2().trim().equals(""))
		{
			analyser.setCommision2("0.00");
		}
		if (analyser.getCommision3() == null || analyser.getCommision3().trim().equals(""))
		{
			analyser.setCommision3("0.00");
		}
		if (analyser.getCommision4() == null || analyser.getCommision4().trim().equals(""))
		{
			analyser.setCommision4("0.00");
		}
		if (analyser.getDentalInsuranceAmount() == null)
		{
			analyser.setDentalInsuranceAmount(0.00);
		}
		if (analyser.getBonusAmount() == null)
		{
			analyser.setBonusAmount(0.00);
		}
		if (analyser.getDentalInsurance().equals("true") || analyser.getDentalInsurance().equalsIgnoreCase("on"))
		{
			analyser.setDentalInsurance("Y");
		}
		else
		{
			analyser.setDentalInsurance("N");
		}
		if (analyser.getStdBenefit().equals("true") || analyser.getStdBenefit().equalsIgnoreCase("on"))
		{
			analyser.setStdBenefit("Y");
		}
		else
		{
			analyser.setStdBenefit("N");
		}
		if (analyser.getLtdBenefit().equals("true") || analyser.getLtdBenefit().equalsIgnoreCase("on"))
		{
			analyser.setLtdBenefit("Y");
		}
		else
		{
			analyser.setLtdBenefit("N");
		}
		if (analyser.getIsBonusEligible().equals("true") || analyser.getIsBonusEligible().equalsIgnoreCase("on"))
		{
			analyser.setIsBonusEligible("Y");
		}
		else
		{
			analyser.setIsBonusEligible("N");
		}

		if (analyser.getChkReferralFee().equals("true") || analyser.getChkReferralFee().equalsIgnoreCase("on"))
		{
			analyser.setChkReferralFee("Y");
		}
		else
		{
			analyser.setChkReferralFee("N");
		}


		if (analyser.getDiscount() == null || analyser.getDiscount().trim().equals(""))
		{
			analyser.setDiscount("0.00");
		}

		if (analyser.getTax() == null || analyser.getTax().trim().equals(""))
		{
			analyser.setTax("0.00");
		}

		if (analyser.getCommission() == null)
		{
			analyser.setCommission(0.00);
		}

		if (analyser.getSpreadWeek() == null || analyser.getSpreadWeek().trim().equals(""))
		{
			analyser.setSpreadWeek("0.00");
		}

		if (analyser.getProfit() == null)
		{
			analyser.setProfit(0.00);
		}

		if (analyser.getPlacementAmount() == null || analyser.getPlacementAmount().trim().equals(""))
		{
			analyser.setPlacementAmount("0.00");
		}

		if (analyser.getPermGAAmount() == null)
		{
			analyser.setPermGAAmount(0.00);
		}

		if (analyser.getImmigrationCost() == null)
		{
			analyser.setImmigrationCost(0.00);
		}

		if (analyser.getEquipmentCost() == null)
		{
			analyser.setEquipmentCost(0.00);
		}

		if (analyser.getNonBillableCost() == null)
		{
			analyser.setNonBillableCost(0.00);
		}

		if (analyser.getCommissionPercentage5() == null)
		{
			analyser.setCommissionPercentage5(0.00);
		}

		if (analyser.getCommissionPercentage6() == null)
		{
			analyser.setCommissionPercentage6(0.00);
		}

		if (analyser.getCommissionPercentage7() == null)
		{
			analyser.setCommissionPercentage7(0.00);
		}

		if (analyser.getCommissionPercentage8() == null)
		{
			analyser.setCommissionPercentage8(0.00);
		}

		if (analyser.getCommissionPercentage9() == null)
		{
			analyser.setCommissionPercentage9(0.00);
		}

		if (analyser.getCommission5() == null)
		{
			analyser.setCommission5(0.00);
		}

		if (analyser.getCommission6() == null)
		{
			analyser.setCommission6(0.00);
		}

		if (analyser.getCommission7() == null)
		{
			analyser.setCommission7(0.00);
		}

		if (analyser.getCommission8() == null)
		{
			analyser.setCommission8(0.00);
		}

		if (analyser.getCommission9() == null)
		{
			analyser.setCommission9(0.00);
		}

		if (analyser.getProjectCost() == null)
		{
			analyser.setProjectCost(0.00);
		}

		if (analyser.getSickLeavePerHourRate() == null)
		{
			analyser.setSickLeavePerHourRate(0.00);
		}

		if (analyser.getSickLeaveCost() == null)
		{
			analyser.setSickLeaveCost(0.00);
		}

		if (analyser.getCommissionPersonGrade1() == null || analyser.getCommissionPersonGrade1().trim().equals(""))
		{
			analyser.setCommissionPersonGrade1("0.00");
		}
		if (analyser.getGrossProfit() == null)
		{
			analyser.setGrossProfit(0.00);
		}
		if (analyser.getGrossProfitPercentage() == null)
		{
			analyser.setGrossProfitPercentage(0.00);
		}else {
			if(analyser.getGrossProfitPercentage() == Double.POSITIVE_INFINITY 
					|| analyser.getGrossProfitPercentage() == Double.NaN) {
				analyser.setGrossProfitPercentage(0.0);
			}
		}

		if (analyser.getCommissionableProfit() == null)
		{
			analyser.setCommissionableProfit(0.00);
		}
		if (analyser.getRejectionStatus() == null)
		{
			analyser.setRejectionStatus(0);
		}
		if (analyser.getpTOLimitToOverrideSick() == null)
		{
			analyser.setpTOLimitToOverrideSick(0.00);
		}

		if(analyser.getTotalHolidays() == null) {
			analyser.setTotalHolidays(0.00);
		}

		if (analyser.getPortfolio() == null)
		{
			analyser.setPortfolio("");
		}

		/*
		 * Added as part of Tax and Sick Leave Project
		 */

		if (analyser.getBullhornPlacementId() == null)
		{
			analyser.setBullhornPlacementId(0);
		}

		if (analyser.getBullhornBatchInfoId() == null)
		{
			analyser.setBullhornBatchInfoId(0);
		}

		if (analyser.getBullhornTerminationDataProcessedId() == null)
		{
			analyser.setBullhornTerminationDataProcessedId(0L);
		}

		if (analyser.getBullhornTerminationDataStagingId() == null)
		{
			analyser.setBullhornTerminationDataStagingId(0L);
		}

		if (analyser.getTerminationBullhornBatchInfoId() == null)
		{
			analyser.setTerminationBullhornBatchInfoId(0);
		}

		if (analyser.getIsModificationRequired() == null)
		{
			analyser.setIsModificationRequired("");
		}

		if (analyser.getOverrideTermination() == null)
		{
			analyser.setOverrideTermination("");
		}
		if (analyser.getSickLeaveSource() == null)
		{
			analyser.setSickLeaveSource("");
		}
		if (analyser.getWorkFromSource() == null)
		{
			analyser.setWorkFromSource("");
		}
		if (analyser.getMajorityWorkPerformed() == null)
		{
			analyser.setMajorityWorkPerformed("");
		}


	}

	public void saveAnalyser()
	{

		// while saving check if the required attributes value is -1, set it to
		// blank

		// Check if Client Site and Work site are related
		if(analyser.getClientId() != null && analyser.getClientSiteId() != null){
			Boolean res = sickLeaveCostService.validateClientSite(FacesUtils.getCurrentUserId(), analyser.getClientSiteId(), Integer.valueOf(analyser.getClientId()));
			if(!res) {
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Client Site Issue", "Client company and Worksite doesn't match.");
				FacesUtils.getFacesContext().addMessage(null, message);
				return;
			}
		}

		String msg = "About to save Analyzer with UserId = "+FacesUtils.getCurrentUserId()+" and following values:";
		logger.debug(msg);
		System.out.println(msg);

		int actionType = 1; // -- insert

		Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		setAnalyserValues(params);
		setAnalyserDefaultValues();


		if (analyser.getCommEffDate() != null && analyser.getCommEffDate().trim().length() > 0)
		{
			String[] splitDateAry = analyser.getCommEffDate().split("/");
			if (splitDateAry != null && splitDateAry.length == 3)
			{
				if (splitDateAry[0].length() < 2)
				{
					splitDateAry[0] = "0" + splitDateAry[0];
				}
				if (splitDateAry[1].length() < 2)
				{
					splitDateAry[1] = "0" + splitDateAry[1];
				}
				analyser.setCommEffDate(splitDateAry[0] + "/" + splitDateAry[1] + "/" + splitDateAry[2]);
			}
		}

		if (isAddressValidated())
		{
			analyser.setIsAddressUSPSValidated("Y");

			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy");
			LocalDate localDate = LocalDate.now();

			analyser.setuSPSAddressValidationDate(dtf.format(localDate));
			analyser.setLongitude(longitude);
			analyser.setLatitude(latitude);
		}
		else
		{
			analyser.setIsAddressUSPSValidated("N");
		}

		if(analyser.getLatitude() == null){
			analyser.setLatitude("0.0");
		}

		if(analyser.getLongitude() == null){
			analyser.setLongitude("0.0");
		}
		analyser.setCurrency("USD");

		analyser.setExecOrphan(analyser.getExecOrphanHdnVal());
		analyser.setRecruiterOrphan(analyser.getRecruiterOrphanHdnVal());
		analyser.setOther1Orphan(analyser.getOther1OrphanHdnVal());
		analyser.setOther2Orphan(analyser.getOther2OrphanHdnVal());
		analyser.setDistanceFromWorksite(distance);

		//set portfolio information
		updatePortfolio();

		String status = analyserService.addUpdateAnalyser(analyser, FacesUtils.getCurrentUserId(), actionType);

		System.out.println("Saved Status : " + status);
		if (status.equals("1"))
		{
			/*Fix for STRY0017698 */
			String exception = "The email id" + analyser.getEmail() + " is already used by some subcontractor.Insert/update to this email id is not allowed";

			System.out.println(exception);

			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", exception);
			FacesUtils.getFacesContext().addMessage(null, message);
			return;
			// return "analyser-list.xhtml?faces-redirect=true";
		}

		if (status.equals("10"))
		{
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Duplicate SSN", "An employee with same SSN already exists in the system, duplicate SSN entry is not allowed.");
			FacesUtils.getFacesContext().addMessage(null, message);
			return;
		}

		/*
		 * FacesContext facesContext = FacesContext.getCurrentInstance(); Flash
		 * flash = facesContext.getExternalContext().getFlash();
		 * flash.setKeepMessages(true); flash.setRedirect(true);
		 */
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Analyzer Save", "Analyser operation is successfully completed!");
		System.out.println("Analyzer Save CompanyCodeLocal " +companyCodeLocal);
		logger.info("Analyzer Save CompanyCodeLocal " +companyCodeLocal);
		System.out.println("Analyzer Save analyser.getCompanyCode() " +analyser.getCompanyCode());
		logger.info("Analyzer Save analyser.getCompanyCode() " +analyser.getCompanyCode());
		if (analyser.getCompanyCode().equalsIgnoreCase(Constants.DEFAULT_COMPANY_CODE))
		{
			FacesUtils.redirect("/protected/analyser-list.xhtml?compCode="+analyser.getCompanyCode()+"", message);
		}
		else
		{
			FacesUtils.redirect("/protected/analyser-list-sig.xhtml?compCode="+analyser.getCompanyCode()+"", message);
		}
	}

	public void setValuesForUpdate()
	{
		analyser.setVertical(selectedAnalyser.getVertical());
		analyser.setProduct(selectedAnalyser.getProduct());
		analyser.setTemps(selectedAnalyser.getTemps());
		// analyser.setBranch(selectedAnalyser.getBranch());
		analyser.setEmpType(selectedAnalyser.getEmpType());
		analyser.setReason(selectedAnalyser.getReason());
	}

	public void setValuesForRehire(Map<String, String> params)
	{
		try{
			analyser.setVertical(params.get("analyserForm:vertical"));
			analyser.setProduct(params.get("analyserForm:product"));
			analyser.setTemps(params.get("analyserForm:employeeCategory"));
			analyser.setEmpType(params.get("analyserForm:empType"));
			analyser.setReason(params.get("analyserForm:reason"));
		}catch(Exception ex){
			logger.debug("Error setting default values for rehire",ex.getMessage());
		}
	}

	public void updateAnalyser()
	{

		// Check if Client Site and Work site are related
		if(analyser.getClientId() != null && analyser.getClientSiteId() != null){
			Boolean res = sickLeaveCostService.validateClientSite(FacesUtils.getCurrentUserId(), analyser.getClientSiteId(), Integer.valueOf(analyser.getClientId()));
			if(!res) {
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Client Site Issue", "Client company and Worksite doesn't match.");
				FacesUtils.getFacesContext().addMessage(null, message);
				return;
			}
		}

		logger.debug("About to update analyser form...");
		int actionType = 2; // -- Modify
		Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		setAnalyserValues(params);

		if(params.get("analyserForm:isRehired")!=null && Integer.parseInt(params.get("analyserForm:isRehired")) != 1){
			setValuesForUpdate();
		}else{
			setValuesForRehire(params);
		}

		setAnalyserDefaultValues();

		/*
		 * In case of Role Id other than 3, i.e. Admin, update SSn from prevSsn
		 */
		if(roleId != 3){
			analyser.setSsn(params.get("analyserForm:prevSsn"));
		}

		if (isAddressValidated())
		{
			analyser.setIsAddressUSPSValidated("Y");

			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy");
			LocalDate localDate = LocalDate.now();

			analyser.setuSPSAddressValidationDate(dtf.format(localDate));
			analyser.setLongitude(longitude);
			analyser.setLatitude(latitude);
		}
		else
		{
			analyser.setIsAddressUSPSValidated("N");
		}


		analyser.setExecOrphan(analyser.getExecOrphanHdnVal());
		analyser.setRecruiterOrphan(analyser.getRecruiterOrphanHdnVal());
		analyser.setOther1Orphan(analyser.getOther1OrphanHdnVal());
		analyser.setOther2Orphan(analyser.getOther2OrphanHdnVal());
		analyser.setDistanceFromWorksite(distance);
		if(analyser.getLatitude() == null){
			analyser.setLatitude("0.0");
		}

		if(analyser.getLongitude() == null){
			analyser.setLongitude("0.0");
		}
		analyser.setCurrency("USD");

		//set portfolio information
		updatePortfolio();

		String status = analyserService.addUpdateAnalyser(analyser, FacesUtils.getCurrentUserId(), actionType);

		System.out.println("Update Status : " + status);

		if (status.equals("1"))
		{
			/*Fix for STRY0017698 */
			String exception = "The email id" + analyser.getEmail() + " is already used by some subcontractor.Insert/update to this email id is not allowed";

			System.out.println(exception);

			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", exception);
			FacesUtils.getFacesContext().addMessage(null, message);
			return;
			// return "analyser-list.xhtml?faces-redirect=true";
		}

		if (status.equals("10"))
		{
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Duplicate SSN", "An employee with same SSN already exists in the system, duplicate SSN entry is not allowed.");
			FacesUtils.getFacesContext().addMessage(null, message);
			return;
		}

		/*
		 * FacesContext facesContext = FacesContext.getCurrentInstance(); Flash
		 * flash = facesContext.getExternalContext().getFlash();
		 * flash.setKeepMessages(true); flash.setRedirect(true);
		 */
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Analyzer Updated", "Analyser operation is successfully completed!");
		System.out.println("Analyzer Bean --> updateAnalyser() --> CompanyCodeLocal = " +companyCodeLocal);
		logger.info("Analyzer Bean --> updateAnalyser() --> CompanyCodeLocal = " +companyCodeLocal);

		System.out.println("Analyzer updateAnalyser analyser.getCompanyCode() " +analyser.getCompanyCode());
		logger.info("Analyzer updateAnalyser analyser.getCompanyCode() " +analyser.getCompanyCode());
		if (analyser.getCompanyCode().equalsIgnoreCase(Constants.DEFAULT_COMPANY_CODE))
		{
			FacesUtils.redirect("/protected/analyser-list.xhtml?compCode="+analyser.getCompanyCode()+"", message);
		}
		else
		{
			FacesUtils.redirect("/protected/analyser-list-sig.xhtml?compCode="+analyser.getCompanyCode()+"", message);
		}
	}

	public void discardChanges()
	{
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Analyzer Changes Discarded", "Changes discarded!");
		System.out.println("Analyzer Bean --> discardChanges() --> CompanyCodeLocal = " +companyCodeLocal);
		logger.info("Analyzer Bean --> discardChanges() --> CompanyCodeLocal = " +companyCodeLocal);
		System.out.println("Analyzer discardChanges analyser.getCompanyCode() " +analyser.getCompanyCode());
		logger.info("Analyzer discardChanges analyser.getCompanyCode() " +analyser.getCompanyCode());
		if (analyser.getCompanyCode() .equalsIgnoreCase(Constants.DEFAULT_COMPANY_CODE))
		{
			FacesUtils.redirect("/protected/analyser-list.xhtml?compCode="+analyser.getCompanyCode()+"", message);
		}
		else
		{
			FacesUtils.redirect("/protected/analyser-list-sig.xhtml?compCode="+analyser.getCompanyCode()+"", message);
		}
	}

	public void enableSubcontractorSearch()
	{
		/*
		 * String summary = searchSubcontractorFlag ? "Start Searching" :
		 * "Searching Disabled";
		 * FacesContext.getCurrentInstance().addMessage(null, new
		 * FacesMessage(summary));
		 */
		if (analyser.getEmpType().equalsIgnoreCase("1099"))
		{
			searchSubcontractorFlag = false;
		}
		else
		{
			searchSubcontractorFlag = true;
		}
	}

	public void onChangeOffice()
	{
		try
		{
			System.out.println("Selected analyser region :: " + analyser.getBranch());
			logger.debug("Selected analyser region :: " + analyser.getBranch());

			if (branchData != null && !branchData.isEmpty())
			{
				Map<String, Object> map = null;
				ReportUtil report = new ReportUtil();
				for (int i = 0; i < branchData.size(); i++)
				{
					map = branchData.get(i);
					Integer id = (Integer) map.get("BRANCHID");
					String name = (String) map.get("BRANCHNAME");
					if (name.equalsIgnoreCase(analyser.getBranch()))
					{
						latitude = (String) map.get("LATITUDE");
						longitude = (String) map.get("LONGITUDE");

						branchAddress.append(map.get("ADDRESS1"));
						branchAddress.append(" " + (map.get("ADDRESS2") == null ? "" : map.get("ADDRESS2")));
						branchAddress.append("," + map.get("CITY"));
						branchAddress.append("," + map.get("STATE"));
						branchAddress.append(" " + map.get("ZIPCODE"));

						if (latitude == null || latitude.trim().equals("") || longitude == null || longitude.trim().equals(""))
						{
							distance = branch_distance_error_msg;
						}
						List<Map<String, Object>> branchOffices = report.getBrancheOffices(FacesUtils.getCurrentUserId(), id);
						if (branchOffices != null && branchOffices.size() > 0)
						{
							Map<String, Object> officeMap = branchOffices.get(0);
							name = (String) officeMap.get("GEOOFFICENAME");
							analyser.setGeoOffice(name);
						}
						else
						{
							analyser.setGeoOffice(null);
						}
						break;
					}
				}

				if (analyser.getClientSiteId() != null)
				{
					DecimalFormat df = new DecimalFormat("0.00");
					try
					{
						List<Map<String, Object>> clientSiteData = report.getClientSite(FacesUtils.getCurrentUserId(), analyser.getClientSiteId());
						if (clientSiteData != null && !clientSiteData.isEmpty())
						{
							map = clientSiteData.get(0);
							AnalyserClientSite analyserClientSite = createAnalyserClientSiteObject(map);
							String isUspsValidated = analyserClientSite.getIsAddressUSPSValidated();
							if (isUspsValidated != null && isUspsValidated.trim().equalsIgnoreCase("1"))
							{
								workSiteLatitude = analyserClientSite.getLatitude();
								workSiteLongitude = analyserClientSite.getLongitude();
								StringBuilder workSiteAddress = new StringBuilder();
								workSiteAddress.append(analyserClientSite.getAddress1());
								workSiteAddress.append(" " + (analyserClientSite.getAddress2() == null ? "" : analyserClientSite.getAddress2()));
								workSiteAddress.append("," + analyserClientSite.getCity());
								workSiteAddress.append("," + analyserClientSite.getState());
								workSiteAddress.append(" " + analyserClientSite.getZipCode());

								if ((workSiteLatitude != null && workSiteLatitude.length() > 0)
										&& (workSiteLongitude != null && workSiteLongitude.length() > 0))
								{
									if (!(latitude == null || latitude.trim().equals("") || longitude == null || longitude.trim().equals("")))
									{
										Double calulateDistance = FacesUtils.calculateRouteDistance(branchAddress.toString(),
												workSiteAddress.toString());
										distance = df.format(calulateDistance);

										System.out.println("Calculated Distance :: " + calulateDistance);
									}
								}
							}
							else
							{
								Map<Integer, String> status = objSmarty.validateAddress(analyserClientSite.getAddress1(),
										analyserClientSite.getAddress2(), analyserClientSite.getCity(), analyserClientSite.getState(),
										analyserClientSite.getZipCode());
								Integer code = 0;
								String response = "";
								for (Entry<Integer, String> entry : status.entrySet())
								{
									code = entry.getKey();
									response = entry.getValue();
								}
								if (code == 200)
								{
									if (!response.startsWith("["))
									{
										response = "[" + response + "]";
									}

									JSONArray jsonArray = new JSONArray(response.toString());
									JSONObject myResponse = jsonArray.getJSONObject(0);
									JSONObject longLat = myResponse.getJSONObject("metadata");
									try
									{
										workSiteLatitude = String.valueOf(longLat.getDouble("latitude"));
										workSiteLongitude = String.valueOf(longLat.getDouble("longitude"));
										analyserClientSite.setIsAddressUSPSValidated("1");
										analyserClientSite.setLongitude(workSiteLongitude);
										analyserClientSite.setLatitude(workSiteLatitude);
										analyserClientSite.setUpdatedBy(FacesUtils.getCurrentUserId());
										analyserClientSite.setValidatedBy(FacesUtils.getCurrentUserId());
										Instant now = Instant.now();
										Timestamp current = Timestamp.from(now);
										// set nanos to 0 otherwise occured an
										// error on saving date
										current.setNanos(0);
										analyserClientSite.setuSPSAddressValidationDate(current);
										analyserClientSite.setUpdatedDate(current);

										int actionType = 2;
										String result = analyserClientSiteService.addUpdateAnalyserSite(analyserClientSite,
												FacesUtils.getCurrentUserId(), actionType);

										if (result.equals("0"))
										{
											logger.debug("Client work site couldn't be updated for longitude (" + analyserClientSite.getLongitude()
											+ ") and latitude (" + analyserClientSite.getLatitude() + ") : "
											+ analyserClientSite.getSiteId());
											System.err.println("Client work site couldn't be updated for longitude ("
													+ analyserClientSite.getLongitude() + ") and latitude (" + analyserClientSite.getLatitude()
													+ ") : " + analyserClientSite.getSiteId());
											System.out.println("Client work site couldn't be updated for longitude ("
													+ analyserClientSite.getLongitude() + ") and latitude (" + analyserClientSite.getLatitude()
													+ ") : " + analyserClientSite.getSiteId());

											StringBuilder workSiteAddress = new StringBuilder();
											workSiteAddress.append(analyserClientSite.getAddress1());
											workSiteAddress
											.append(" " + (analyserClientSite.getAddress2() == null ? "" : analyserClientSite.getAddress2()));
											workSiteAddress.append("," + analyserClientSite.getCity());
											workSiteAddress.append("," + analyserClientSite.getState());
											workSiteAddress.append(" " + analyserClientSite.getZipCode());

											if ((workSiteLatitude != null && workSiteLatitude.length() > 0)
													&& (workSiteLongitude != null && workSiteLongitude.length() > 0))
											{
												if (!(latitude == null || latitude.trim().equals("") || longitude == null
														|| longitude.trim().equals("")))
												{
													Double calulateDistance = FacesUtils.calculateRouteDistance(branchAddress.toString(),
															workSiteAddress.toString());
													distance = df.format(calulateDistance);
													System.out.println("Calculated Distance :: " + calulateDistance);
												}
											}
										}
										else if (result.equals("1"))
										{
											logger.debug("Client work site updated successfully");
											System.err.println("Client work site updated successfully");
										}

									}
									catch (org.json.JSONException ex)
									{
										System.err.println("Json exception...");
									}

								}
								else
								{
									distance = "Address not validated.";
								}
							}
						}
					}
					catch (Exception e)
					{
						e.printStackTrace();
					}
				}
			}
		}
		catch (Exception e)
		{
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error Loading Region", e.getMessage());
			FacesUtils.getFacesContext().addMessage(null, message);
		}
	}

	private String fixSsn(String ssn){
		if(ssn.contains("-")){
			ssn = ssn.replaceAll("-", "");
		}
		final Pattern ssnFormat = Pattern.compile("^(\\d{3})(\\d{2})(\\d{4})$");

		Matcher m = ssnFormat.matcher(ssn);  // make ssn a string!
		if (m.find()) {
			System.out.printf("%s-%s-%s%n", m.group(1), m.group(2), m.group(3));
			return m.group(1)+"-"+m.group(2)+"-"+m.group(3); 
		} else {
			System.err.println("Enter a valid social security number!");
			return "-1";
		}
	}

	public void checkSsn()
	{
		try
		{
			// TODO add a hidden field and check if ssn is valid, if valid,
			// change the color of ssn on its basis
			System.out.println("SSN is : " + analyser.getSsn());
			if (analyser.getSsn() == null || analyser.getSsn().equals(""))
			{
				String summary = "SSN needs to be Filled in or is invalid";
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, summary, ""));
				validSsn = false;
				return;
			}

			String ssn = fixSsn(analyser.getSsn());
			if(ssn.equals("-1")){
				validSsn = false;
				return;
			}
			String regex = "^(?!000|666)[0-8][0-9]{2}-(?!00)[0-9]{2}-(?!0000)[0-9]{4}$";
			Pattern pattern = Pattern.compile(regex);
			Matcher matcher = pattern.matcher(ssn);
			if (!matcher.matches())
			{
				String summary = "SSN cannot starts with 000 | 666 | 999 - 00 - 0000";
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, summary, ""));
				validSsn = false;
				return;
			}

			Integer count;
			if (analyser.getParentId() == null || analyser.getParentId().equals(""))
			{
				// this is an add request.
				count = analyserService.findDuplicateSsn(ssn, null, 'A');
			}
			else
			{
				// this is an update request.
				count = analyserService.findDuplicateSsn(ssn, Integer.parseInt(analyser.getParentId()), 'U');
			}

			if (count != null)
			{
				if (count > 0)
				{
					String summary = "An employee with same SSN already exists in the system, duplicate SSN entry is not allowed.";
					logger.debug("Duplicate SSN :" + analyser.getSsn());
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, summary, ""));
					validSsn = false;
					return;
				}
				else
				{
					//update ssn value in the analyser object as well
					analyser.setSsn(ssn);
					validSsn = true;
					prevSsn = ssn;
				}
			}

		}
		catch (Exception ex)
		{
			ex.printStackTrace();
			String summary = "Error validating SSN";
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, summary, ""));
			validSsn = false;
			return;
		}
	}

	public void checkFlsaReference()
	{
		try
		{
			// change the color of ssn on its basis
			System.out.println("Flsa Reference is : " + analyser.getFlsaReference());
			if (analyser.getFlsaReference() == null || analyser.getFlsaReference().equals(""))
			{
				validFlsaRefernce = false;
				return;
			}

			Integer flsaReferenceList;
			if (analyser.getParentId() == null || analyser.getParentId().equals(""))
			{
				// this is an add request.
				flsaReferenceList = analyserService.findDuplicateFlsaReference(analyser.getFlsaReference(), null, 'A');
			}
			else
			{
				// this is an update request.
				flsaReferenceList = analyserService.findDuplicateFlsaReference(analyser.getFlsaReference(), Integer.parseInt(analyser.getParentId()),
						'U');
			}

			if (flsaReferenceList != null)
			{
				if (flsaReferenceList > 0)
				{
					String summary = "Duplicate FLSA Reference";
					logger.debug("Duplicate FLSA Reference :" + analyser.getFlsaReference());
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(summary));
					validFlsaRefernce = false;
					return;
				}
				else
				{
					validFlsaRefernce = true;
					prevFlsaRef = analyser.getFlsaReference();
				}
			}

		}
		catch (Exception ex)
		{
			ex.printStackTrace();
			String summary = "Error validating FLSA reference";
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(summary));
			validFlsaRefernce = false;
			return;
		}
	}

	public void checkZipCode()
	{
		try
		{
			System.out.println("Zip Code is : " + analyser.getZip());
			if (analyser.getZip() == null || analyser.getZip().equals(""))
			{
				FacesUtils.addGlobalMessage(FacesMessageSeverity.ERROR, "Zip Code is required", "Enter zip code and then try again!");
				validZipCode = false;
				return;
			}
			else if (analyser.getAddress1() == null || analyser.getAddress1().trim().equals(""))
			{
				validZipCode = false;
				FacesUtils.addGlobalMessage(FacesMessageSeverity.ERROR, "Address not entered", "Enter Address 1 and then try again!");
				return;
			}
			// temp remove by asim
			// else if (analyser.getState() == null ||
			// analyser.getState().trim().equals("")) {
			// validZipCode = false;
			// FacesUtils.addGlobalMessage(FacesMessageSeverity.ERROR, "State
			// not selected",
			// "Select State and then try again!");
			// return;
			// }

			// temp remove by asim
			Map<Integer, String> status = objSmarty.validateAddress(analyser.getAddress1(), analyser.getAddress2(), analyser.getCity(),
					analyser.getState(), analyser.getZip());

			// Iterator<Map.Entry<Integer,String>> itr=
			// status.entrySet().iterator();

			// Integer code = 200;
			// String response = "{\"last_line\":\"McLean VA
			// 22102-3800\",\"components\":{\"zipcode\":\"22102\",\"default_city_name\":\"Mc
			// Lean\",\"city_name\":\"McLean\",\"primary_number\":\"8270\",\"delivery_point_check_digit\":\"4\",\"delivery_point\":\"99\",\"state_abbreviation\":\"VA\",\"plus4_code\":\"3800\",\"street_name\":\"Greensboro\",\"street_suffix\":\"Dr\"},\"metadata\":{\"congressional_district\":\"11\",\"utc_offset\":-5,\"elot_sort\":\"A\",\"dst\":true,\"latitude\":38.92361,\"precision\":\"Zip9\",\"county_name\":\"Fairfax\",\"time_zone\":\"Eastern\",\"building_default_indicator\":\"Y\",\"record_type\":\"H\",\"carrier_route\":\"C025\",\"county_fips\":\"51059\",\"zip_type\":\"Standard\",\"rdi\":\"Commercial\",\"elot_sequence\":\"0022\",\"longitude\":-77.2306},\"candidate_index\":0,\"delivery_point_barcode\":\"221023800994\",\"delivery_line_1\":\"8270
			// Greensboro
			// Dr\",\"analysis\":{\"dpv_match_code\":\"D\",\"dpv_vacant\":\"N\",\"active\":\"N\",\"dpv_cmra\":\"N\",\"footnotes\":\"H#L#\",\"dpv_footnotes\":\"AAN1\"},\"input_index\":0}";

			// temp remove by asim
			Integer code = 0;
			String response = "";
			for (Entry<Integer, String> entry : status.entrySet())
			{
				code = entry.getKey();
				response = entry.getValue();
				System.out.println("Key : " + entry.getKey() + " Value : " + entry.getValue());
			}

			/*
			 * while(itr.hasNext()) { code = itr.next().getKey(); response =
			 * itr.next().getValue();
			 * System.out.println("key of : "+itr.next().getKey()
			 * +" value of      Map"+itr.next().getValue()); }
			 */

			if (code == 200)
			{
				if (!response.startsWith("["))
				{
					response = "[" + response + "]";
				}

				JSONArray jsonArray = new JSONArray(response.toString());
				JSONObject myResponse = jsonArray.getJSONObject(0);
				JSONObject longLat = myResponse.getJSONObject("metadata");
				String latitude = "0.00";
				String longitude = "0.00";

				String newAddress1 = "", newAddress2 = "", lastLine = "";
				String city = "", zipCode = "";
				state = "";
				try
				{
					latitude = String.valueOf(longLat.getDouble("latitude"));
					longitude = String.valueOf(longLat.getDouble("longitude"));
					newAddress1 = myResponse.getString("delivery_line_1");
					lastLine = myResponse.getString("last_line");
					if (lastLine != "" || lastLine != null && lastLine.length() > 0)
					{
						zipCode = lastLine.substring(lastLine.lastIndexOf(' '), lastLine.length());
						lastLine = lastLine.substring(0, lastLine.lastIndexOf(' '));
						state = lastLine.substring(lastLine.lastIndexOf(' '), lastLine.length());
						lastLine = lastLine.substring(0, lastLine.lastIndexOf(' '));
						city = lastLine;
						if (zipCode != null)
						{
							zipCode = zipCode.trim();
						}
						if (state != null)
						{
							state = state.trim();
						}
						if (city != null)
						{
							city = city.trim();
						}
						System.out.println("Zip Code :: " + zipCode);
						System.out.println("State    :: " + state);
						System.out.println("City     :: " + city);
					}
				}
				catch (org.json.JSONException ex)
				{
					System.err.println("Json exception...");
				}

				AnalyserUspsAddress oldAddress = new AnalyserUspsAddress();
				oldAddress.setAddress1(analyser.getAddress1());
				oldAddress.setAddress2(analyser.getAddress2());
				oldAddress.setCity(analyser.getCity());
				oldAddress.setState(analyser.getState());
				oldAddress.setZipCode(analyser.getZip());
				oldAddress.setLongitude(analyser.getLongitude());
				oldAddress.setLatitude(analyser.getLatitude());

				AnalyserUspsAddress validatedAddress = new AnalyserUspsAddress();
				validatedAddress.setAddress1(newAddress1);
				validatedAddress.setAddress2(newAddress2);
				validatedAddress.setCity(city);
				validatedAddress.setState(state);
				validatedAddress.setZipCode(zipCode);
				validatedAddress.setLongitude(longitude);
				validatedAddress.setLatitude(latitude);

				List<AnalyserUspsAddress> analyserUspsAddressList = new ArrayList<AnalyserUspsAddress>();
				analyserUspsAddressList.add(0, oldAddress);
				analyserUspsAddressList.add(1, validatedAddress);

				// Mountain View CA 94043-1783

				HttpSession session = FacesUtils.getRequestObject().getSession(false);// getCurrentRequestFromFacesContext().getSession(false);
				session.setAttribute("uspsList", analyserUspsAddressList);

				Map<String, Object> options = FacesUtils.createUSPSDialogOptions();
				RequestContext.getCurrentInstance().openDialog("dialog/select_analyser_usps_address.xhtml", options, null);

				/*
				 * String summary = "Valid Address";
				 * logger.debug("Valid address :" + analyser.getZip());
				 * FacesContext.getCurrentInstance().addMessage(null, new
				 * FacesMessage(summary));
				 */
				/*
				 * FacesUtils.addGlobalMessage(FacesMessageSeverity.INFO,
				 * "Success", "Address validated Successfully!");
				 */
				validZipCode = true;
				addressValidated = true;

				// return;
			}
			else
			{
				String summary = "Invalid Address";
				logger.debug("Invalid address :" + analyser.getAddress1(), analyser.getAddress2(), analyser.getCity(), analyser.getState(),
						analyser.getZip());
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(summary));
				validZipCode = false;
				addressValidated = false;
				return;
			}

		}
		catch (Exception ex)
		{
			ex.printStackTrace();
			String summary = "Error validating Zip Code";
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(summary));
			validZipCode = false;
			addressValidated = true;
			return;
		}
	}

	public void chooseAnalyserSubcontractor()
	{
		Map<String, Object> options = FacesUtils.createDialogOptions();

		Map<String, List<String>> parameters = new HashMap<String, List<String>>();

		List<String> companyList = new ArrayList<String>();
		companyList.add("-");

		List<String> orderByList = new ArrayList<String>();
		orderByList.add("LegalName");

		List<String> searchStringList = new ArrayList<String>();
		searchStringList.add("FORACOMPANYACTIVE");

		List<String> recordStatusList = new ArrayList<String>();
		recordStatusList.add("1");

		List<String> companyCodeList = new ArrayList<String>();
		companyCodeList.add(analyser.getCompanyCode());		
		parameters.put("companyName", companyList);
		parameters.put("orderBy", orderByList);
		parameters.put("searchString", searchStringList);
		parameters.put("recordStatus", recordStatusList);
		parameters.put("companyCode", companyCodeList);
		// call
		// wireless.spGetAnalyserAllContractor('Gregory.Armstrong@DISYS.COM','-','LegalName','FORACOMPANYACTIVE','1')

		RequestContext.getCurrentInstance().openDialog("dialog/select_analyser_sub_contractors.xhtml", options, parameters);
	}

	public void onSubcontractorChosen(SelectEvent event)
	{
		try
		{
			AnalyserSubContractor analyserSubContractor = (AnalyserSubContractor) event.getObject();
			analyser.setContPocName(analyserSubContractor.getPocName());
			analyser.setContEmail(analyserSubContractor.getPrimaryEmail());
			analyser.setContAddress(analyserSubContractor.getCompleteAddress());
			analyser.setContPayTerm(analyserSubContractor.getPaymentTerm());
			analyser.setContFin(analyserSubContractor.getFederalTaxId());
			analyser.setContPhone(analyserSubContractor.getPrimaryPhone());
			analyser.setContCompanyName(analyserSubContractor.getCompany());
			analyser.setContractorId(analyserSubContractor.getContractorId().toString());
			analyser.setVendorCompanyCode(analyserSubContractor.getCompanyCode());
		}
		catch (Exception ex)
		{
			logger.debug("Exception while handling subcontract " + ex.getMessage());
		}

	}

	public void onUspsAddressChosen(SelectEvent event)
	{
		System.out.println("Address selected...");
		AnalyserUspsAddress selectedAddress = (AnalyserUspsAddress) event.getObject();
		analyser.setAddress1(selectedAddress.getAddress1());
		analyser.setAddress2(selectedAddress.getAddress2());
		analyser.setCity(selectedAddress.getCity());
		analyser.setState(selectedAddress.getState());
		analyser.setZip(selectedAddress.getZipCode());
	}

	/**
	 * Related to choosing client company
	 */
	public void enableClientSearch()
	{
		boolean pass = false;
		if (analyser.getVertical() != null || !(analyser.getVertical().equals("")))
		{
			pass = true;
		}
		else
		{
			pass = false;
		}
		// if the vertical is selected, then move forward and check the product
		// selection.
		if (pass)
		{
			if (analyser.getProduct() != null || !(analyser.getProduct().equals("")))
			{
				pass = true;
				searchSubcontractorFlag = false;
			}
			else
			{
				pass = false;
				searchSubcontractorFlag = false;
			}
		}
	}

	public void chooseAnalyserClient()
	{
		Map<String, Object> options = FacesUtils.createDialogOptions();
		Map<String, List<String>> parameters = new HashMap<String, List<String>>();
		List<String> verticalList = new ArrayList<String>();
		List<String> productList = new ArrayList<String>();
		List<String> branchList = new ArrayList<String>();
		List<String> companyCodeList = new ArrayList<String>();

		verticalList.add(analyser.getVertical());
		productList.add(analyser.getProduct());
		branchList.add(analyser.getBranch());
		// 03/11/2021 Added as part of Co-Sell Project
		//companyCodeList.add(analyser.getCompanyCode());
		companyCodeList.add(ALL);

		System.out.println("In AnalyserBean --> chooseAnalyserClient");
		logger.debug("In AnalyserBean --> chooseAnalyserClient");
		System.out.println("In AnalyserBean --> Parameter analyser.getBranch() added = "+analyser.getBranch());
		logger.debug("In AnalyserBean --> Parameter analyser.getBranch() added = "+analyser.getBranch());

		parameters.put("vertical", verticalList);
		parameters.put("product", productList);
		parameters.put("branch", branchList);
		parameters.put("companyCode", companyCodeList);

		List<String> orderByList = new ArrayList<String>();
		orderByList.add("Company");

		List<String> userList = new ArrayList<String>();
		userList.add("dummy");

		List<String> searchStringList = new ArrayList<String>();
		searchStringList.add(ALL);

		parameters.put("orderBy", orderByList);
		parameters.put("searchString", searchStringList);
		parameters.put("userList", userList);

		RequestContext.getCurrentInstance().openDialog("dialog/select_analyser_clients.xhtml", options, parameters);
	}

	public void onClientChosen(SelectEvent event)
	{
		try
		{
			AnalyserClientDTO analyserClient = (AnalyserClientDTO) event.getObject();

			//FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Selected", "Company Name :" + analyserClient.getCompany());
			//FacesContext.getCurrentInstance().addMessage(null, message);

			analyser.setClientCompany(analyserClient.getCompany());
			analyser.setInvoiceAddress(analyserClient.getAddress());
			analyser.setAttention(analyserClient.getContactName());
			analyser.setPaymentTerms(analyserClient.getPaymentTerms());
			analyser.setSpecialNotes(analyserClient.getSpecialNotes());
			analyser.setDistributionMethod(analyserClient.getDistributionMethod());
			analyser.setClientEmail(analyserClient.getEmail());
			analyser.setBillingType(analyserClient.getDysBillType());
			analyser.setDeliveryType(analyserClient.getDysDeliveryType());
			analyser.setPracticeArea(analyserClient.getDysPracticeArea());

			// select client id
			analyser.setClientAddressId(analyserClient.getAddressId());
			analyser.setClientId(analyserClient.getClientId().toString());

			//analyser.setDiscountRate(analyserClient.getAdminFee());
			analyser.setDiscountRate(analyserClient.getTotalDiscount().toString());
			analyser.setClientCompanyCode(analyserClient.getCompanyCode());
		}
		catch (Exception ex)
		{
			System.out.println("Exception In AnalyserBean --> onClientChosen " + ex.getMessage());
			logger.debug("Exception In AnalyserBean --> onClientChosen " + ex.getMessage());
		}
	}

	private void setUpdatedDiscountRate(Integer clientId){
		String newdiscountRate = "0";
		String oldDiscountRate = analyser.getDiscountRate();
		logger.debug("Old discount rate for Analyser "+analyser.getAnalyserId()+" and client Id : "+clientId+" was : "+oldDiscountRate);
		System.out.println("Old discount rate for Analyser "+analyser.getAnalyserId()+" and client Id : "+clientId+" was : "+oldDiscountRate);
		newdiscountRate = analyserClientService.getTotalDiscount(FacesUtils.getCurrentUserId(), clientId);
		logger.debug("New discount rate for Analyser "+analyser.getAnalyserId()+" and client Id : "+clientId+" is : "+newdiscountRate);
		System.out.println("New discount rate for Analyser "+analyser.getAnalyserId()+" and client Id : "+clientId+" is : "+newdiscountRate);
		try
		{
			if (Double.parseDouble(newdiscountRate) != Double.parseDouble(oldDiscountRate))
			{
				logger.debug("SETTING NEW DISCOUNT Rate for "+analyser.getAnalyserId()+" and client Id : "+clientId+" is : "+newdiscountRate);
				System.out.println("SETTING NEW DISCOUNT Rate for "+analyser.getAnalyserId()+" and client Id : "+clientId+" is : "+newdiscountRate);
				analyser.setDiscountRate(newdiscountRate);
			}
		}
		catch (Exception e)
		{
			logger.debug("Exception while parsing NEW & OLD DIscount Rates in setUpdatedDiscountRate method of AnalyzerBean = " + e.getMessage());
			System.out.println("Exception while parsing NEW & OLD DIscount Rates in setUpdatedDiscountRate method of AnalyzerBean = " + e.getMessage());
		}
	}

	public void chooseClientInvoice()
	{
		try
		{
			if (analyser.getClientId() == null || analyser.getClientId().equals(""))
			{
				/*
				 * User have not select client yet, so send an error message;
				 */
				FacesUtils.addGlobalMessage(FacesMessageSeverity.ERROR, "Client Company not selected",
						"Select client company first and then try again!");

				return;
			}

			Map<String, Object> options = FacesUtils.createDialogOptions();
			Map<String, List<String>> parameters = new HashMap<String, List<String>>();
			List<String> verticalList = new ArrayList<String>();
			List<String> productList = new ArrayList<String>();
			List<String> branchList = new ArrayList<String>();

			verticalList.add(analyser.getVertical());
			productList.add(analyser.getProduct());
			branchList.add(analyser.getBranch());

			System.out.println("In AnalyserBean --> chooseClientInvoice");
			logger.debug("In AnalyserBean --> chooseClientInvoice");
			System.out.println("In AnalyserBean --> Parameter analyser.getBranch() added = "+analyser.getBranch());
			logger.debug("In AnalyserBean --> Parameter analyser.getBranch() added = "+analyser.getBranch());

			parameters.put("vertical", verticalList);
			parameters.put("product", productList);
			parameters.put("branch", branchList);

			List<String> orderByList = new ArrayList<String>();
			orderByList.add("Company");

			List<String> userList = new ArrayList<String>();
			userList.add("dummy");

			List<String> searchStringList = new ArrayList<String>();
			searchStringList.add(analyser.getClientId());

			parameters.put("orderBy", orderByList);
			parameters.put("searchString", searchStringList);
			parameters.put("userList", userList);

			RequestContext.getCurrentInstance().openDialog("dialog/select_analyser_client_invoice.xhtml", options, parameters);
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
			logger.debug("Error message : " + ex.getMessage());
			FacesUtils.addGlobalMessage(FacesMessageSeverity.ERROR, "Error", "Error opening invoice address for client!");

			return;
		}

	}

	public void onClientInvoiceChosen(SelectEvent event)
	{
		try
		{
			AnalyserClientDTO analyserClient = (AnalyserClientDTO) event.getObject();

			//			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "analyserClient invoice Selected",
			//			        "Id:" + analyserClient.getAddressId());
			//			FacesContext.getCurrentInstance().addMessage(null, message);
			//			
			analyser.setClientCompany(analyserClient.getCompany());
			analyser.setInvoiceAddress(analyserClient.getAddress());
			analyser.setAttention(analyserClient.getContactName());
			analyser.setPaymentTerms(analyserClient.getPaymentTerms());
			analyser.setSpecialNotes(analyserClient.getSpecialNotes());
			analyser.setDistributionMethod(analyserClient.getDistributionMethod());
			analyser.setClientEmail(analyserClient.getEmail());
			analyser.setBillingType(analyserClient.getDysBillType());
			analyser.setDeliveryType(analyserClient.getDysDeliveryType());
			analyser.setPracticeArea(analyserClient.getDysPracticeArea());

			//analyser.setDiscountRate(analyserClient.getAdminFee());
			analyser.setDiscountRate(analyserClient.getTotalDiscount().toString());

			// select client id
			analyser.setClientAddressId(analyserClient.getAddressId());
			analyser.setClientId(analyserClient.getClientId().toString());

		}
		catch (Exception ex)
		{
			logger.debug("Exception while handling subcontract " + ex.getMessage());
		}

	}

	// ////////////////////////////////////////////////////////////////

	/**
	 * Related to choosing client site location
	 */
	public void chooseAnalyserClientSite()
	{
		try
		{
			if (analyser.getClientId() == null || analyser.getClientId().equals(""))
			{
				/*
				 * User have not select client yet, so send an error message;
				 */
				/*
				 * FacesMessage message = new
				 * FacesMessage(FacesMessage.SEVERITY_ERROR,
				 * "Client Company not selected",
				 * "Select client company first and then try again!");
				 */

				FacesUtils.addGlobalMessage(FacesMessageSeverity.ERROR, "Client Company not selected",
						"Select client company first and then try again!");

				return;
			}
			Map<String, Object> options = FacesUtils.createDialogOptions();
			Map<String, List<String>> parameters = new HashMap<String, List<String>>();

			List<String> userList = new ArrayList<String>();
			userList.add("1");

			List<String> orderByList = new ArrayList<String>();
			orderByList.add("Company");

			List<String> searchStringList = new ArrayList<String>();
			searchStringList.add(analyser.getClientId());

			parameters.put("userList", userList);
			parameters.put("orderBy", orderByList);
			parameters.put("searchString", searchStringList);

			RequestContext.getCurrentInstance().openDialog("dialog/select_analyser_client_site.xhtml", options, parameters);
		}
		catch (Exception ex)
		{
			System.err.println(ex.getLocalizedMessage());
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "There was an error opening client site");
			FacesUtils.addMessage(message);
			return;
		}
	}

	public void chooseAnalyserWorkSiteLocation()
	{
		try
		{
			if (analyser.getClientId() == null || analyser.getClientId().equals(""))
			{
				/*
				 * User have not select client yet, so send an error message;
				 */
				/*
				 * FacesMessage message = new
				 * FacesMessage(FacesMessage.SEVERITY_ERROR,
				 * "Client Company not selected",
				 * "Select client company first and then try again!");
				 */

				FacesUtils.addGlobalMessage(FacesMessageSeverity.ERROR, "Client Company not selected",
						"Select client company first and then try again!");

				return;
			}
			Map<String, Object> options = FacesUtils.createDialogOptions();
			Map<String, List<String>> parameters = new HashMap<String, List<String>>();

			List<String> clientIdsList = new ArrayList<String>();
			clientIdsList.add(analyser.getClientId());

			parameters.put("clientId", clientIdsList);

			RequestContext.getCurrentInstance().openDialog("dialog/analyser-work-site-location.xhtml", options, parameters);
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
			System.err.println(ex.getLocalizedMessage());
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "There was an error opening client site");
			FacesUtils.addMessage(message);
			return;
		}
	}

	public void onClientSiteChosen(SelectEvent event)
	{
		try
		{
			DecimalFormat df = new DecimalFormat("0.00");
			AnalyserClientSiteDTO analyserClientSiteDTO = (AnalyserClientSiteDTO) event.getObject();
			siteState = Util.extractState(analyserClientSiteDTO.getCompleteAddress());
			analyser.setSiteLocation(analyserClientSiteDTO.getCompleteAddress());
			analyser.setManagerPhone(analyserClientSiteDTO.getTelephone());
			analyser.setClientManagerName(analyserClientSiteDTO.getManagerName());
			analyser.setManagerEmail(analyserClientSiteDTO.getEmail());
			analyser.setClientSiteId(analyserClientSiteDTO.getSiteId());
			getSickLeaveDetails(analyser);
			if(analyserClientSiteDTO.getCompleteAddress()!=null){
				analyser.setWorksiteSelectionMessage("In order to ensure tax and paid sick leave compliance, the work site address must be accurate. For full-time remote workers, this should be their home address (or wherever they are actually working from). If the worker is full time on the company site or going into the company site on a regular basis (even if part-time) then you should list the company site address.");
			}

			if (analyserClientSiteDTO.getIsAddressUSPSValidated() != null && analyserClientSiteDTO.getIsAddressUSPSValidated().equals("1"))
			{
				workSiteLatitude = analyserClientSiteDTO.getLatitude();
				workSiteLongitude = analyserClientSiteDTO.getLongitude();
				System.out.println("Work site latitude : " + workSiteLatitude);
				System.out.println("Work site longitude : " + workSiteLongitude);

				StringBuilder workSiteAddress = new StringBuilder();
				workSiteAddress.append(analyser.getAddress1());
				workSiteAddress.append(" " + (analyser.getAddress2() == null ? "" : analyser.getAddress2()));
				workSiteAddress.append("," + analyser.getCity());
				workSiteAddress.append("," + analyser.getState());
				workSiteAddress.append(" " + analyser.getZip());

				if ((workSiteLatitude != null && workSiteLatitude.length() > 0) && (workSiteLongitude != null && workSiteLongitude.length() > 0))
				{
					if (!(latitude == null || latitude.trim().equals("") || longitude == null || longitude.trim().equals("")))
					{
						//						Double calulateDistance = FacesUtils.calculateRouteDistance(branchAddress.toString(), workSiteAddress.toString());
						Double calulateDistance = FacesUtils.calculateRouteDistance(branchAddress.toString(), analyserClientSiteDTO.getCompleteAddress());
						System.out.println("Calculated Distance :: " + calulateDistance);
						distance = df.format(calulateDistance);
					}
				}
				System.out.println("Distance calculate is : " + distance);

			}
			else
			{
				Map<String, Object> map = null;
				ReportUtil report = new ReportUtil();
				try
				{
					List<Map<String, Object>> clientSiteData = report.getClientSite(FacesUtils.getCurrentUserId(), analyser.getClientSiteId());
					if (clientSiteData != null && !clientSiteData.isEmpty())
					{
						map = clientSiteData.get(0);
						AnalyserClientSite analyserClientSite = createAnalyserClientSiteObject(map);
						String isUspsValidated = analyserClientSite.getIsAddressUSPSValidated();
						if (isUspsValidated != null && isUspsValidated.trim().equalsIgnoreCase("1"))
						{
							workSiteLatitude = analyserClientSite.getLatitude();
							workSiteLongitude = analyserClientSite.getLongitude();

							StringBuilder workSiteAddress = new StringBuilder();
							workSiteAddress.append(analyserClientSite.getAddress1());
							workSiteAddress.append(" " + (analyserClientSite.getAddress2() == null ? "" : analyserClientSite.getAddress2()));
							workSiteAddress.append("," + analyserClientSite.getCity());
							workSiteAddress.append("," + analyserClientSite.getState());
							workSiteAddress.append(" " + analyserClientSite.getZipCode());

							if ((workSiteLatitude != null && workSiteLatitude.length() > 0)
									&& (workSiteLongitude != null && workSiteLongitude.length() > 0))
							{
								if (!(latitude == null || latitude.trim().equals("") || longitude == null || longitude.trim().equals("")))
								{
									Double calulateDistance = FacesUtils.calculateRouteDistance(branchAddress.toString(), workSiteAddress.toString());
									distance = df.format(calulateDistance);
									System.out.println("Calculated Distance :: " + calulateDistance);
								}
							}
						}
						else
						{
							Map<Integer, String> status = objSmarty.validateAddress(analyserClientSite.getAddress1(),
									analyserClientSite.getAddress2(), analyserClientSite.getCity(), analyserClientSite.getState(),
									analyserClientSite.getZipCode());
							Integer code = 0;
							String response = "";
							for (Entry<Integer, String> entry : status.entrySet())
							{
								code = entry.getKey();
								response = entry.getValue();
							}
							if (code == 200)
							{
								if (!response.startsWith("["))
								{
									response = "[" + response + "]";
								}

								JSONArray jsonArray = new JSONArray(response.toString());
								JSONObject myResponse = jsonArray.getJSONObject(0);
								JSONObject longLat = myResponse.getJSONObject("metadata");
								try
								{
									workSiteLatitude = String.valueOf(longLat.getDouble("latitude"));
									workSiteLongitude = String.valueOf(longLat.getDouble("longitude"));
									analyserClientSite.setIsAddressUSPSValidated("1");
									analyserClientSite.setLongitude(workSiteLongitude);
									analyserClientSite.setLatitude(workSiteLatitude);
									analyserClientSite.setUpdatedBy(FacesUtils.getCurrentUserId());
									analyserClientSite.setValidatedBy(FacesUtils.getCurrentUserId());
									Instant now = Instant.now();
									Timestamp current = Timestamp.from(now);
									// set nanos to 0 otherwise occured an error
									// on saving date
									current.setNanos(0);
									analyserClientSite.setuSPSAddressValidationDate(current);
									analyserClientSite.setUpdatedDate(current);

									int actionType = 2;
									String result = analyserClientSiteService.addUpdateAnalyserSite(analyserClientSite, FacesUtils.getCurrentUserId(),
											actionType);

									if (result.equals("0"))
									{
										logger.debug("Client work site couldn't be updated for longitude (" + analyserClientSite.getLongitude()
										+ ") and latitude (" + analyserClientSite.getLatitude() + ") : " + analyserClientSite.getSiteId());
										System.err.println("Client work site couldn't be updated for longitude (" + analyserClientSite.getLongitude()
										+ ") and latitude (" + analyserClientSite.getLatitude() + ") : " + analyserClientSite.getSiteId());

										StringBuilder workSiteAddress = new StringBuilder();
										workSiteAddress.append(analyserClientSite.getAddress1());
										workSiteAddress
										.append(" " + (analyserClientSite.getAddress2() == null ? "" : analyserClientSite.getAddress2()));
										workSiteAddress.append("," + analyserClientSite.getCity());
										workSiteAddress.append("," + analyserClientSite.getState());
										workSiteAddress.append(" " + analyserClientSite.getZipCode());

										if ((workSiteLatitude != null && workSiteLatitude.length() > 0)
												&& (workSiteLongitude != null && workSiteLongitude.length() > 0))
										{
											if (!(latitude == null || latitude.trim().equals("") || longitude == null || longitude.trim().equals("")))
											{
												Double calulateDistance = FacesUtils.calculateRouteDistance(branchAddress.toString(),
														workSiteAddress.toString());
												distance = df.format(calulateDistance);
												System.out.println("Calculated Distance :: " + calulateDistance);
											}
										}
									}
									else if (result.equals("1"))
									{
										logger.debug("Client work site updated successfully");
										System.err.println("Client work site updated successfully");
									}

								}
								catch (org.json.JSONException ex)
								{
									System.err.println("Json exception...");
								}

							}
							else
							{
								distance = "Address not validated.";
							}
						}
					}
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}

			/*
			 * SickLeaveCost leaveCost =
			 * sickLeaveCostService.findByZipCode(analyserClientSite.getZipCode(
			 * )); if(leaveCost != null){
			 * analyser.setSickLeaveCost(leaveCost.getSickHoursCost()); }else{
			 * analyser.setSickLeaveCost(0.00); }
			 */

		}
		catch (Exception ex)
		{
			logger.debug("Exception while handling subcontract " + ex.getMessage());
		}

	}

	public void onClientWorkSiteAdded(SelectEvent event)
	{
		try
		{

			AnalyserClientSite analyserClientSite = (AnalyserClientSite) event.getObject();

			//			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Success", "Analyser client site added successfully");
			//			FacesContext.getCurrentInstance().addMessage(null, message);

			// Address 1, address2, city, state, zipcode as complete address

			//added data shouldn't go back to analyzer page by default

			//analyser.setManagerPhone(analyserClientSite.getTelephone());
			//String name = analyserClientSite.getManagerName() + " " + analyserClientSite.getLastName();
			//analyser.setClientManagerName(name);
			//analyser.setManagerEmail(analyserClientSite.getEmail());
			System.out.println("New Worksite Created, Clearing all old worksite Address fields..");
			analyser.setManagerPhone("");
			analyser.setManagerEmail("");
			analyser.setClientManagerName("");
			analyser.setSiteLocation("");
			analyser.setClientSiteId(null);

			/**
			 * In the application, once the new address is added, nothing is
			 * done, user have to select it again from the select window
			 */

			/*
			 * String siteLocation = analyserClientSite.getAddress1() + "," +
			 * analyserClientSite.getAddress2() + "," +
			 * analyserClientSite.getCity() + "," +
			 * analyserClientSite.getState() + "," +
			 * analyserClientSite.getZipCode();
			 * 
			 * String name = analyserClientSite.getManagerName() + " " +
			 * analyserClientSite.getLastName();
			 * 
			 * analyser.setSiteLocation(siteLocation);
			 * analyser.setManagerPhone(analyserClientSite.getTelephone());
			 * analyser.setClientManagerName(name);
			 * analyser.setManagerEmail(analyserClientSite.getEmail());
			 * 
			 * //TODO get the new generated site id from the db
			 * analyser.setClientSiteId(analyserClientSite.getSiteId());
			 * 
			 * 
			 * SickLeaveCost leaveCost =
			 * sickLeaveCostService.findByZipCode(analyserClientSite.getZipCode(
			 * )); if(leaveCost != null){
			 * analyser.setSickLeaveCost(leaveCost.getSickHoursCost()); }else{
			 * analyser.setSickLeaveCost(0.00); }
			 */

		}
		catch (Exception ex)
		{
			logger.debug("Exception while handling subcontract " + ex.getMessage());
		}

	}

	public void openImmigrationCost()
	{
		Map<String, Object> options = FacesUtils.createDialogOptions();

		RequestContext.getCurrentInstance().openDialog("dialog/immigration_cost.xhtml", options, null);
	}

	// ////////////////////////////////////////////////////////////////

	//NOT BEING USED NOW AFTER MULTI COMPANY SETUP
	private void createIRCCommissionPersonsList()
	{

		Map<String, String> commPersonMap = new HashMap<String, String>();
		String userId = FacesUtils.getCurrentUserId();

		if (commissionPersonsList5 == null)
		{
			// {call
			// wireless.spAnalyzerCommissionPersons('Gregory.Armstrong@DISYS.COM','AGM')}
			String type = "AGM";
			commissionPersonsList5 = new ArrayList<SelectItem>();
			commPersonMap = service.getCommPersonList(userId, type);
			Iterator<Map.Entry<String, String>> iterator = commPersonMap.entrySet().iterator();
			while (iterator.hasNext())
			{
				Map.Entry<String, String> obj = iterator.next();
				if (obj.getValue() == null)
				{
					commissionPersonsList5.add(new SelectItem(obj.getKey(), ""));
				}
				else
				{
					commissionPersonsList5.add(new SelectItem(obj.getKey(), obj.getValue().toString()));
				}
			}
		}

		if (commissionPersonsList6 == null)
		{
			// {call
			// wireless.spAnalyzerCommissionPersons('Gregory.Armstrong@DISYS.COM','Practice')}
			String type = "Practice";
			commissionPersonsList6 = new ArrayList<SelectItem>();
			commPersonMap = service.getCommPersonList(userId, type);
			Iterator<Map.Entry<String, String>> iterator = commPersonMap.entrySet().iterator();
			while (iterator.hasNext())
			{
				Map.Entry<String, String> obj = iterator.next();
				if (obj.getValue() == null)
				{
					commissionPersonsList6.add(new SelectItem(obj.getKey(), ""));
				}
				else
				{
					commissionPersonsList6.add(new SelectItem(obj.getKey(), obj.getValue().toString()));
				}

			}
		}

		if (commissionPersonsList7 == null)
		{
			// {call
			// wireless.spAnalyzerCommissionPersons('Gregory.Armstrong@DISYS.COM','Delivery')}
			String type = "Delivery";
			commissionPersonsList7 = new ArrayList<SelectItem>();
			commPersonMap = service.getCommPersonList(userId, type);
			Iterator<Map.Entry<String, String>> iterator = commPersonMap.entrySet().iterator();
			while (iterator.hasNext())
			{
				Map.Entry<String, String> obj = iterator.next();
				if (obj.getValue() == null)
				{
					commissionPersonsList7.add(new SelectItem(obj.getKey(), ""));
				}
				else
				{
					commissionPersonsList7.add(new SelectItem(obj.getKey(), obj.getValue().toString()));
				}
			}
		}

		if (commissionPersonsList8 == null)
		{
			// {call
			// wireless.spAnalyzerCommissionPersons('Gregory.Armstrong@DISYS.COM','BDE')}
			String type = "BDE";
			commissionPersonsList8 = new ArrayList<SelectItem>();
			commPersonMap = service.getCommPersonList(userId, type);
			Iterator<Map.Entry<String, String>> iterator = commPersonMap.entrySet().iterator();
			while (iterator.hasNext())
			{
				Map.Entry<String, String> obj = iterator.next();
				if (obj.getValue() == null)
				{
					commissionPersonsList8.add(new SelectItem(obj.getKey(), ""));
				}
				else
				{
					commissionPersonsList8.add(new SelectItem(obj.getKey(), obj.getValue().toString()));
				}
			}
		}

		if (commissionPersonsList9 == null)
		{
			// {call
			// wireless.spAnalyzerCommissionPersons('Gregory.Armstrong@DISYS.COM','Proposal')}
			String type = "Proposal";
			commissionPersonsList9 = new ArrayList<SelectItem>();
			commPersonMap = service.getCommPersonList(userId, type);
			Iterator<Map.Entry<String, String>> iterator = commPersonMap.entrySet().iterator();
			while (iterator.hasNext())
			{
				Map.Entry<String, String> obj = iterator.next();
				if (obj.getValue() == null)
				{
					commissionPersonsList9.add(new SelectItem(obj.getKey(), ""));
				}
				else
				{
					commissionPersonsList9.add(new SelectItem(obj.getKey(), obj.getValue().toString()));
				}
			}
		}

		if (managingDirectorList == null)
		{
			// {call
			// wireless.spAnalyzerCommissionPersons('Gregory.Armstrong@DISYS.COM','MD')}
			String type = "MD";
			managingDirectorList = new ArrayList<SelectItem>();
			commPersonMap = service.getCommPersonList(userId, type);
			Iterator<Map.Entry<String, String>> iterator = commPersonMap.entrySet().iterator();
			while (iterator.hasNext())
			{
				Map.Entry<String, String> obj = iterator.next();
				if (obj.getValue() == null)
				{
					managingDirectorList.add(new SelectItem(obj.getKey(), ""));
				}
				else
				{
					managingDirectorList.add(new SelectItem(obj.getKey(), obj.getValue().toString()));
				}
			}
		}
	}

	/*
	 * //NOT BEING USED NOW AFTER MULTI COMPANY SETUP
	private void createCommissionPersonsList(String userId)
	{
	 * if (list == null || list.size() == 0) { logger.
	 * debug("Exception in getCommList method of CustomerSessionEJBBean" );
	 * } allCommissionPersonsList = list;

		if (commissionPersonsList1 == null)
		{
			List<String> list = service.findActiveCommissionPersons(userId, "AE-1");
			commissionPersonsList1 = new ArrayList<SelectItem>();
			for (String selectItem : list)
			{
				commissionPersonsList1.add(new SelectItem(selectItem, selectItem));
			}
		}

		if (commissionPersonsList2 == null)
		{
			List<String> list = service.findActiveCommissionPersons(userId, "Rec-1");
			commissionPersonsList2 = new ArrayList<SelectItem>();
			for (String selectItem : list)
			{
				commissionPersonsList2.add(new SelectItem(selectItem, selectItem));
			}
		}

		if (commissionPersonsList3 == null)
		{
			List<String> list = service.findActiveCommissionPersons(userId, "AE-2");
			commissionPersonsList3 = new ArrayList<SelectItem>();
			for (String selectItem : list)
			{
				commissionPersonsList3.add(new SelectItem(selectItem, selectItem));
			}
		}

		if (commissionPersonsList4 == null)
		{
			List<String> list = service.findActiveCommissionPersons(userId, "Rec-2");
			commissionPersonsList4 = new ArrayList<SelectItem>();
			for (String selectItem : list)
			{
				commissionPersonsList4.add(new SelectItem(selectItem, selectItem));
			}
		}
	}
	 */

	/*
	 * 
	 * When selected commission type is IRC then change the person list, only
	 * the IRC commission persons will be shown here, that have prefix IND in
	 * AGP Code.
	 */

	public void ircCommissionPersons(AjaxBehaviorEvent event)
	{
		String type = "IRCCOMMISSIONPERSON";
		String buttonId = "";
		CommandButton source = (CommandButton)event.getSource();
		buttonId = source.getId();

		FacesContext fc = FacesUtils.getFacesContext();
		ExternalContext externalContext = fc.getExternalContext();
		Map<String, String> requestMap = externalContext.getRequestParameterMap();

		Iterator<Map.Entry<String, String>> iterator = requestMap.entrySet().iterator();
		while (iterator.hasNext())
		{
			Map.Entry<String, String> obj = iterator.next();
			// System.out.println("Key : "+obj.getKey());
			// System.out.println("Value : " + obj.getValue().toString());
			if (obj.getKey().equals("analyserForm:commissionPerson"))
			{
				commissionPerson = obj.getValue().toString();
			}
		}

		System.out.println("commissionPerson in ircCommissionPersons "+commissionPerson+ " with Type: " + type);	

		String status = analyserService.getGenericDescription(type, commissionPerson, "");
		

		if(buttonId.equals("ae1Button")){
			try{
				ircCommissionPersonStatusForAe1 = Integer.valueOf(status);
			}catch(NumberFormatException e){
				ircCommissionPersonStatusForAe1 = 0;
			}
		}else if(buttonId.equals("ae2Button")){
			try{
				ircCommissionPersonStatusForAe2 = Integer.valueOf(status);
			}catch(NumberFormatException e){
				ircCommissionPersonStatusForAe2 = 0;
			}
		}else if(buttonId.equals("rec1Button")){
			try{
				ircCommissionPersonStatusForRec1 = Integer.valueOf(status);
			}catch(NumberFormatException e){
				ircCommissionPersonStatusForRec1 = 0;
			}
		}else if(buttonId.equals("rec2Button")){
			try{
				ircCommissionPersonStatusForRec2 = Integer.valueOf(status);
			}catch(NumberFormatException e){
				ircCommissionPersonStatusForRec2 = 0;
			}
		}



	}

	public void aeGrade(AjaxBehaviorEvent event)
	{
		String type = "SCORECARDGRADING";

		String buttonId = "";
		CommandButton source = (CommandButton)event.getSource();
		buttonId = source.getId();

		FacesContext fc = FacesUtils.getFacesContext();
		ExternalContext externalContext = fc.getExternalContext();
		Map<String, String> requestMap = externalContext.getRequestParameterMap();

		Iterator<Map.Entry<String, String>> iterator = requestMap.entrySet().iterator();
		while (iterator.hasNext())
		{
			Map.Entry<String, String> obj = iterator.next();
			// System.out.println("Key : "+obj.getKey());
			// System.out.println("Value : " + obj.getValue().toString());
			if (obj.getKey().equals("analyserForm:commissionPerson"))
			{
				commissionPerson = obj.getValue().toString();
			}
		}

		System.out.println("commissionPerson in aeGrade"+commissionPerson+ " with Type: " + type);

		String status = analyserService.getGenericDescription(type, commissionPerson, "");
		if(status.equals("0")){
			status = "Z";
		}
		if(buttonId.equalsIgnoreCase("ae1Grade1Button")){
			analyser.setCommissionPersonGrade1(status);
			logger.debug("Commission person grade 1 : " + status);
		} else if(buttonId.equalsIgnoreCase("ae1Grade2Button")) {
			analyser.setCommissionPersonGrade3(status);
			logger.debug("Commission person grade 3 : " + status);
		}
	}
	/*
	public String ae1Grade2(AjaxBehaviorEvent event)
	{
		String type = "SCORECARDGRADING";

		FacesContext fc = FacesUtils.getFacesContext();
		ExternalContext externalContext = fc.getExternalContext();
		Map<String, String> requestMap = externalContext.getRequestParameterMap();

		Iterator<Map.Entry<String, String>> iterator = requestMap.entrySet().iterator();
		while (iterator.hasNext())
		{
			Map.Entry<String, String> obj = iterator.next();
			// System.out.println("Key : "+obj.getKey());
			// System.out.println("Value : " + obj.getValue().toString());
			if (obj.getKey().equals("analyserForm:commissionPerson"))
			{
				commissionPerson = obj.getValue().toString();
			}
		}

		System.out.println("commissionPerson : " + commissionPerson);

		String status = analyserService.getGenericDescription(type, commissionPerson, "");
		aeStatus = status;
		return getAeStatus();
	}*/

	public void calculateDistance()
	{
		DecimalFormat df = new DecimalFormat("0.00");
		if (analyser.getBranch() != null && analyser.getBranch().trim().length() > 0)
		{
			ReportUtil report = new ReportUtil();
			try
			{
				branchData = report.getBranches(FacesUtils.getCurrentUserId(), "0");
				if (branchData != null && !branchData.isEmpty())
				{
					for (int i = 0; i < branchData.size(); i++)
					{
						Map<String, Object> map = branchData.get(i);
						String name = (String) map.get("BRANCHNAME");
						if (name.trim().equalsIgnoreCase(analyser.getBranch().trim()))
						{
							latitude = (String) map.get("LATITUDE");
							longitude = (String) map.get("LONGITUDE");
							break;
						}
					}
				}
				if (analyser.getClientSiteId() != null)
				{
					try
					{
						List<Map<String, Object>> clientSiteData = report.getClientSite(FacesUtils.getCurrentUserId(), analyser.getClientSiteId());
						if (clientSiteData != null && !clientSiteData.isEmpty())
						{
							Map<String, Object> map = branchData.get(0);
							AnalyserClientSite analyserClientSite = createAnalyserClientSiteObject(map);
							String isUspsValidated = (String) map.get("ISADDRESSUSPSVALIDATED");
							if (isUspsValidated != null && isUspsValidated.trim().equalsIgnoreCase("1"))
							{
								workSiteLatitude = (String) map.get("LATITUDE");
								workSiteLongitude = (String) map.get("LONGITUDE");

								StringBuilder workSiteAddress = new StringBuilder();
								workSiteAddress.append(analyserClientSite.getAddress1());
								workSiteAddress.append(" " + (analyserClientSite.getAddress2() == null ? "" : analyserClientSite.getAddress2()));
								workSiteAddress.append("," + analyserClientSite.getCity());
								workSiteAddress.append("," + analyserClientSite.getState());
								workSiteAddress.append(" " + analyserClientSite.getZipCode());

								if ((workSiteLatitude != null && workSiteLatitude.length() > 0)
										&& (workSiteLongitude != null && workSiteLongitude.length() > 0))
								{
									Double calulateDistance = FacesUtils.calculateRouteDistance(branchAddress.toString(), workSiteAddress.toString());
									distance = df.format(calulateDistance);
								}
							}
							else
							{
								msg = "Cannot calculate distance worksite usps address is not validated";
								FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error Loading Region", msg);
								FacesUtils.getFacesContext().addMessage(null, message);
							}
						}
					}
					catch (Exception e)
					{
						e.printStackTrace();
					}
				}
			}
			catch (Exception e1)
			{
				e1.printStackTrace();
			}
		}
	}

	public String pTOSickLeaveCost(AjaxBehaviorEvent event)
	{

		try
		{
			String type = "PTOSICKLEAVECOST";

			FacesContext fc = FacesUtils.getFacesContext();
			ExternalContext externalContext = fc.getExternalContext();
			Map<String, String> requestMap = externalContext.getRequestParameterMap();
			String newZipCode = "";
			String newClientSiteId = "";

			Double billablePTO = 0.00;
			Double nonBillablePTO = 0.00;
			Double varPTOValue = 0.00;

			Iterator<Map.Entry<String, String>> iterator = requestMap.entrySet().iterator();
			while (iterator.hasNext())
			{
				Map.Entry<String, String> obj = iterator.next();
				// System.out.println("Key : "+obj.getKey());
				// System.out.println("Value : " + obj.getValue().toString());

				if (obj.getKey().equals("analyserForm:billablePTO"))
				{
					billablePTO = Double.parseDouble(obj.getValue().toString());
				}
				if (obj.getKey().equals("analyserForm:nonBillablePTO"))
				{
					nonBillablePTO = Double.parseDouble(obj.getValue().toString());
				}
				varPTOValue = billablePTO + nonBillablePTO;

				if (obj.getKey().equals("analyserForm:commissionPerson"))
				{
					commissionPerson = obj.getValue().toString();
				}

				if (obj.getKey().equals("analyserForm:siteZipCodeValue"))
				{
					newZipCode = obj.getValue().toString();
				}

				if (obj.getKey().equals("analyserForm:clientSiteId"))
				{
					newClientSiteId = obj.getValue().toString();
				}

			}

			String varZipcode = this.siteZipCode;
			if (!(siteId.equals(newClientSiteId)))
			{
				varZipcode = newZipCode;
			}

			System.out.println("commissionPerson in pTOSickLeaveCost "+commissionPerson+ " with Type: " + type);

			String status = analyserService.getGenericDescription(type, varPTOValue.toString(), varZipcode);
			ptoStatus = status;
			return getPtoStatus();
		}
		catch (NullPointerException e)
		{
			logger.debug("Error message while getting pTOSickLeaveCost " + e.getMessage());
			ptoStatus = "-1";
			return getPtoStatus();
		}

	}

	public void changeCommissionPersonsList()
	{
		Integer listId = 0;

		FacesContext fc = FacesUtils.getFacesContext();
		ExternalContext externalContext = fc.getExternalContext();
		Map<String, String> requestMap = externalContext.getRequestParameterMap();

		Iterator<Map.Entry<String, String>> iterator = requestMap.entrySet().iterator();
		while (iterator.hasNext())
		{
			Map.Entry<String, String> obj = iterator.next();
			if (obj.getKey().equals("listId"))
			{
				listId = Integer.valueOf(obj.getValue().toString());
			}
		}

		System.out.println("List id : " + listId);
	}

	public void changeToAllCommissionPersonsList()
	{
		Integer listId = 0;

		FacesContext fc = FacesUtils.getFacesContext();
		ExternalContext externalContext = fc.getExternalContext();
		Map<String, String> requestMap = externalContext.getRequestParameterMap();

		Iterator<Map.Entry<String, String>> iterator = requestMap.entrySet().iterator();
		while (iterator.hasNext())
		{
			Map.Entry<String, String> obj = iterator.next();
			if (obj.getKey().equals("listId"))
			{
				listId = Integer.valueOf(obj.getValue().toString());
			}
		}
		System.out.println("List id : " + listId);

		System.out.println("Total commission persons are  : " + allCommissionPersonsList.size());
		logger.debug("Total commission persons are  : " + allCommissionPersonsList.size());

		if (listId == 1)
		{
			commissionPersonsList1 = new ArrayList<SelectItem>();
			for (String obj : allCommissionPersonsList)
			{
				commissionPersonsList1.add(new SelectItem(obj, obj));
			}
		}
		else if (listId == 2)
		{
			commissionPersonsList2 = new ArrayList<SelectItem>();
			for (String obj : allCommissionPersonsList)
			{
				commissionPersonsList2.add(new SelectItem(obj, obj));
			}
		}
		else if (listId == 3)
		{
			commissionPersonsList3 = new ArrayList<SelectItem>();
			for (String obj : allCommissionPersonsList)
			{
				commissionPersonsList3.add(new SelectItem(obj, obj));
			}
		}
		else if (listId == 4)
		{
			commissionPersonsList4 = new ArrayList<SelectItem>();
			for (String obj : allCommissionPersonsList)
			{
				commissionPersonsList4.add(new SelectItem(obj, obj));
			}
		}
	}

	// To select Analyser Liasion from the dialog.
	public void chooseAnalyserLiaison()
	{
		Map<String, Object> options = FacesUtils.createDialogOptions();
		RequestContext.getCurrentInstance().openDialog("dialog/select_analyser_liaison.xhtml", options, null);
	}

	public void onAnalyserLiaisonChosen(SelectEvent event)
	{
		AnalyserLiaison analyserLiaison = (AnalyserLiaison) event.getObject();
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Analyser Liaison Selected", "Id:" + analyserLiaison.getLiaisonID());
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	// To select Manager from the dialog.
	public void chooseManager()
	{
		Map<String, Object> options = FacesUtils.createDialogOptions();
		RequestContext.getCurrentInstance().openDialog("dialog/select_manager.xhtml", options, null);
	}

	public void onManagerChosen(SelectEvent event)
	{
		ManagerGroup managerGroup = (ManagerGroup) event.getObject();
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Manager Selected", "Id:" + managerGroup.getId().getUserId());

		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	/**
	 * @return the analyser
	 */
	public AnalyserDTO getAnalyser()
	{
		return analyser;
	}

	/**
	 * @param analyser
	 *            the analyser to set
	 */
	public void setAnalyser(AnalyserDTO analyser)
	{
		this.analyser = analyser;
	}

	/**
	 * @return the branches
	 */
	public List<SelectItem> getBranches()
	{
		if (branches == null)
		{
			branches = new ArrayList<SelectItem>();

			ReportUtil report = new ReportUtil();
			try
			{
				branchData = report.getBranches(FacesUtils.getCurrentUserId(), "0");
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
			for (int i = 0; i < branchData.size(); i++)
			{
				Map<String, Object> map = branchData.get(i);
				String id = (String) map.get("BRANCHNAME");
				String desc = (String) map.get("BRANCHNAME");
				branches.add(new SelectItem(id, desc));
			}

		}
		return branches;
	}

	public void setBranches(List<SelectItem> branches)
	{
		this.branches = branches;
	}

	/**
	 * @return the uSAStates
	 */
	public List<SelectItem> getuSAStates()
	{
		if (uSAStates == null)
		{
			uSAStates = new ArrayList<SelectItem>();
			uSAStates.add(new SelectItem("AL", "Alabama"));
			uSAStates.add(new SelectItem("AK", "Alaska"));
			uSAStates.add(new SelectItem("AZ", "Arizona"));
			uSAStates.add(new SelectItem("AR", "Arkansas"));
			uSAStates.add(new SelectItem("CA", "California"));
			uSAStates.add(new SelectItem("CO", "Colorado"));
			uSAStates.add(new SelectItem("CT", "Connecticut"));
			uSAStates.add(new SelectItem("DE", "Delaware"));
			uSAStates.add(new SelectItem("DC", "District Of Columbia"));
			uSAStates.add(new SelectItem("FL", "Florida"));
			uSAStates.add(new SelectItem("GA", "Georgia"));
			uSAStates.add(new SelectItem("HI", "Hawaii"));
			uSAStates.add(new SelectItem("ID", "Idaho"));
			uSAStates.add(new SelectItem("IL", "Illinois"));
			uSAStates.add(new SelectItem("IN", "Indiana"));
			uSAStates.add(new SelectItem("IA", "Iowa"));
			uSAStates.add(new SelectItem("KS", "Kansas"));
			uSAStates.add(new SelectItem("KY", "Kentucky"));
			uSAStates.add(new SelectItem("LA", "Louisiana"));
			uSAStates.add(new SelectItem("ME", "Maine"));
			uSAStates.add(new SelectItem("MD", "Maryland"));
			uSAStates.add(new SelectItem("MA", "Massachusetts"));
			uSAStates.add(new SelectItem("MI", "Michigan"));
			uSAStates.add(new SelectItem("MN", "Minnesota"));
			uSAStates.add(new SelectItem("MS", "Mississippi"));
			uSAStates.add(new SelectItem("MO", "Missouri"));
			uSAStates.add(new SelectItem("MT", "Montana"));
			uSAStates.add(new SelectItem("NE", "Nebraska"));
			uSAStates.add(new SelectItem("NV", "Nevada"));
			uSAStates.add(new SelectItem("NH", "New Hampshire"));
			uSAStates.add(new SelectItem("NJ", "New Jersey"));
			uSAStates.add(new SelectItem("NM", "New Mexico"));
			uSAStates.add(new SelectItem("NY", "New York"));
			uSAStates.add(new SelectItem("NC", "North Carolina"));
			uSAStates.add(new SelectItem("ND", "North Dakota"));
			uSAStates.add(new SelectItem("OH", "Ohio"));
			uSAStates.add(new SelectItem("OK", "Oklahoma"));
			uSAStates.add(new SelectItem("OR", "Oregon"));
			uSAStates.add(new SelectItem("PA", "Pennsylvania"));
			uSAStates.add(new SelectItem("RI", "Rhode Island"));
			uSAStates.add(new SelectItem("SC", "South Carolina"));
			uSAStates.add(new SelectItem("SD", "South Dakota"));
			uSAStates.add(new SelectItem("TN", "Tennessee"));
			uSAStates.add(new SelectItem("TX", "Texas"));
			uSAStates.add(new SelectItem("UT", "Utah"));
			uSAStates.add(new SelectItem("VT", "Vermont"));
			uSAStates.add(new SelectItem("VA", "Virginia"));
			uSAStates.add(new SelectItem("WA", "Washington"));
			uSAStates.add(new SelectItem("WV", "West Virginia"));
			uSAStates.add(new SelectItem("WI", "Wisconsin"));
			uSAStates.add(new SelectItem("WY", "Wyoming"));

			uSAStates.add(new SelectItem("AB", "Alberta"));
			uSAStates.add(new SelectItem("BC", "British Columbia"));
			uSAStates.add(new SelectItem("MB", "Manitoba"));
			uSAStates.add(new SelectItem("NB", "New Brunswick"));
			uSAStates.add(new SelectItem("NL", "Newfoundland and Labrador"));
			uSAStates.add(new SelectItem("NT", "Northwest Territories"));
			uSAStates.add(new SelectItem("NS", "Nova Scotia"));
			uSAStates.add(new SelectItem("NU", "Nunavut"));
			uSAStates.add(new SelectItem("ON", "Ontario"));
			uSAStates.add(new SelectItem("PE", "Prince Edward Island"));
			uSAStates.add(new SelectItem("QC", "Quebec"));
			uSAStates.add(new SelectItem("SK", "Saskatchewan"));
			uSAStates.add(new SelectItem("YT", "Yukon"));
		}
		return uSAStates;
	}

	/**
	 * @param uSAStates
	 *            the uSAStates to set
	 */
	public void setuSAStates(List<SelectItem> uSAStates)
	{
		this.uSAStates = uSAStates;
	}

	/**
	 * @return the employeeCategories
	 */
	public List<SelectItem> getEmployeeCategories()
	{
		if (employeeCategories == null)
		{
			employeeCategories = new ArrayList<SelectItem>();
			/*
			 * employeeCategories.add(new SelectItem("PT", "Part Time"));
			 * employeeCategories.add(new SelectItem("RFT / Hourly",
			 * "RFT / Hourly")); employeeCategories.add(new
			 * SelectItem("Projects", "Projects Techs"));
			 * employeeCategories.add(new SelectItem("IBT - Field Techs",
			 * "IBT - Field Techs")); employeeCategories.add(new
			 * SelectItem("IT", "IT")); employeeCategories.add(new
			 * SelectItem("FA", "F&A"));
			 */

			//employeeCategories.add(new SelectItem("FA", "F&A"));
			//employeeCategories.add(new SelectItem("IT", "IT"));
			//employeeCategories.add(new SelectItem("FA", "Non-IT"));	//12/23/2019 Tayyab
			employeeCategories.add(new SelectItem("IT", "Full Time"));	//01/30/2020 Sajid
			employeeCategories.add(new SelectItem("PT", "Part Time"));
		}
		return employeeCategories;
	}

	/**
	 * @param employeeCategories
	 *            the employeeCategories to set
	 */
	public void setEmployeeCategories(List<SelectItem> employeeCategories)
	{
		this.employeeCategories = employeeCategories;
	}

	/**
	 * @return the employeeTypes
	 */
	public List<SelectItem> getEmployeeTypes()
	{
		if (employeeTypes == null)
		{
			employeeTypes = new ArrayList<SelectItem>();
			employeeTypes.add(new SelectItem("w2", "W2"));
			employeeTypes.add(new SelectItem("1099", "1099"));
			employeeTypes.add(new SelectItem("perm", "Permanent Placement"));
		}
		return employeeTypes;
	}

	/**
	 * @param employeeTypes
	 *            the employeeTypes to set
	 */
	public void setEmployeeTypes(List<SelectItem> employeeTypes)
	{
		this.employeeTypes = employeeTypes;
	}

	/**
	 * @return the jobCategories
	 */
	public List<SelectItem> getJobCategories()
	{
		if (jobCategories == null)
		{
			jobCategories = new ArrayList<SelectItem>();
			jobCategories.add(new SelectItem("Administration", "Administration"));
			jobCategories.add(new SelectItem("Marketing", "Marketing"));
			jobCategories.add(new SelectItem("Development", "Development"));
			jobCategories.add(new SelectItem("Bidding", "Bidding"));
			jobCategories.add(new SelectItem("Accounts", "Accounts"));
			jobCategories.add(new SelectItem("Temporary", "Temporary"));
		}
		return jobCategories;
	}

	/**
	 * @param jobCategories
	 *            the jobCategories to set
	 */
	public void setJobCategories(List<SelectItem> jobCategories)
	{
		this.jobCategories = jobCategories;
	}

	/**
	 * @return the jobBoards
	 */
	public List<SelectItem> getJobBoards()
	{
		if (jobBoards == null)
		{
			jobBoards = new ArrayList<SelectItem>();
			jobBoards.add(new SelectItem("Career Builder", "Career Builder"));
			jobBoards.add(new SelectItem("Clearance Jobs", "Clearance Jobs"));
			jobBoards.add(new SelectItem("Dice", "Dice"));
			jobBoards.add(new SelectItem("Free Boards", "Free Boards"));
			jobBoards.add(new SelectItem("Linkedin", "Linkedin"));
			jobBoards.add(new SelectItem("BH Database", "BH Database"));
			jobBoards.add(new SelectItem("Monster", "Monster"));
			jobBoards.add(new SelectItem("Other", "Other"));
			jobBoards.add(new SelectItem("Referral", "Referral"));
			jobBoards.add(new SelectItem("Third Parties (Subs)", "Third Parties (Subs)"));
			jobBoards.add(new SelectItem("Indeed", "Indeed"));
		}
		return jobBoards;
	}

	/**
	 * @param jobBoards
	 *            the jobBoards to set
	 */
	public void setJobBoards(List<SelectItem> jobBoards)
	{
		this.jobBoards = jobBoards;
	}

	/**
	 * @return the verticals
	 */
	public List<SelectItem> getVerticals()
	{
		if (verticals == null)
		{
			/*
			 * verticals = new ArrayList<SelectItem>(); verticals.add(new
			 * SelectItem("Banking", "Banking")); verticals.add(new
			 * SelectItem("Diversified", "Diversified")); verticals.add(new
			 * SelectItem("Energy", "Energy")); verticals.add(new
			 * SelectItem("Finance and Accounting", "Finance and Accounting"));
			 * verticals.add(new SelectItem("Health", "Health"));
			 * verticals.add(new SelectItem("Hi Tech", "Hi Tech"));
			 */

			verticals = new ArrayList<SelectItem>();
			List<String> list = analyserService.getVerticalsList();
			for (String ver : list)
			{
				SelectItem item = new SelectItem(ver, ver);
				verticals.add(item);
			}
		}
		return verticals;
	}

	/**
	 * @param verticals
	 *            the verticals to set
	 */
	public void setVerticals(List<SelectItem> verticals)
	{
		this.verticals = verticals;
	}

	/**
	 * @return the products
	 */
	public List<SelectItem> getProducts()
	{
		if (products == null)
		{
			products = new ArrayList<SelectItem>();
			products.add(new SelectItem("STAFF AUG", "STAFF AUG"));
			products.add(new SelectItem("SERVICES", "SERVICES"));
			products.add(new SelectItem("PERM", "PERM"));
			//01/23/2019 Tayyab
			products.add(new SelectItem("R4R", "R4R"));
		}
		return products;
	}

	/**
	 * @param products
	 *            the products to set
	 */
	public void setProducts(List<SelectItem> products)
	{
		this.products = products;
	}

	/**
	 * @return the commissionModels
	 */
	public List<SelectItem> getCommissionModels()
	{
		if (commissionModels == null)
		{
			commissionModels = new ArrayList<SelectItem>();
			//commissionModels.add(new SelectItem("F&A", "F&A Model"));
			commissionModels.add(new SelectItem("F&A", "Non-IT Model"));	//12/20/2019 Tayyab -LATER WITH eMP cATEGORY CHANGE
			//commissionModels.add(new SelectItem("IRC", "IRC Model"));
			commissionModels.add(new SelectItem("IRC", "Centralized"));	//12/20/2019 Tayyab
			commissionModels.add(new SelectItem("ITA", "IT-A Model"));
			commissionModels.add(new SelectItem("ITB", "IT-B Model"));
			commissionModels.add(new SelectItem("ITC", "IT-C Model"));
			commissionModels.add(new SelectItem("PRM", "PERM Model"));
		}
		return commissionModels;
	}

	/**
	 * @param commissionModels
	 *            the commissionModels to set
	 */
	public void setCommissionModels(List<SelectItem> commissionModels)
	{
		this.commissionModels = commissionModels;
	}

	/**
	 * @return the dealTypes
	 */
	public List<SelectItem> getDealTypes()
	{
		if (dealTypes == null)
		{
			dealTypes = new ArrayList<SelectItem>();
			dealTypes.add(new SelectItem("A", "A"));
			dealTypes.add(new SelectItem("B", "B"));
			dealTypes.add(new SelectItem("C", "C"));
		}
		return dealTypes;
	}

	/**
	 * @param dealTypes
	 *            the dealTypes to set
	 */
	public void setDealTypes(List<SelectItem> dealTypes)
	{
		this.dealTypes = dealTypes;
	}

	/**
	 * @return the analyzerCategory1
	 */
	public List<SelectItem> getAnalyzerCategory1()
	{
		if (analyzerCategory1 == null)
		{
			analyzerCategory1 = new ArrayList<SelectItem>();
			analyzerCategory1.add(new SelectItem("NDT", "NDT"));
			analyzerCategory1.add(new SelectItem("IDT", "IDT"));
			analyzerCategory1.add(new SelectItem("DSS", "DSS"));
			analyzerCategory1.add(new SelectItem("RPO", "RPO"));
		}
		return analyzerCategory1;
	}

	/**
	 * @param analyzerCategory1
	 *            the analyzerCategory1 to set
	 */
	public void setAnalyzerCategory1(List<SelectItem> analyzerCategory1)
	{
		this.analyzerCategory1 = analyzerCategory1;
	}

	/**
	 * @return the analyzerCategory2
	 */
	public List<SelectItem> getAnalyzerCategory2()
	{
		if (analyzerCategory2 == null)
		{
			analyzerCategory2 = new ArrayList<SelectItem>();
			analyzerCategory2.add(new SelectItem("Canada", "Canada"));
			analyzerCategory2.add(new SelectItem("D2M", "D2M"));
			analyzerCategory2.add(new SelectItem("Digital CBU", "Digital CBU"));
			analyzerCategory2.add(new SelectItem("ERP CBU", "ERP CBU"));
			//analyzerCategory2.add(new SelectItem("GSOC", "GSOC"));
			analyzerCategory2.add(new SelectItem("Go", "Go"));
			analyzerCategory2.add(new SelectItem("Implementation", "Implementation"));
			analyzerCategory2.add(new SelectItem("India A/B", "India A/B"));
			analyzerCategory2.add(new SelectItem("Java CBU", "Java CBU"));
			analyzerCategory2.add(new SelectItem("PERM CBU", "PERM CBU"));
		}
		return analyzerCategory2;
	}

	/**
	 * @param analyzerCategory2
	 *            the analyzerCategory2 to set
	 */
	public void setAnalyzerCategory2(List<SelectItem> analyzerCategory2)
	{
		this.analyzerCategory2 = analyzerCategory2;
	}

	/**
	 * @return the recruitingTeams
	 */
	public List<SelectItem> getRecruitingTeams()
	{
		if (recruitingTeams == null)
		{
			recruitingTeams = new ArrayList<SelectItem>();
			recruitingTeams.add(new SelectItem("GS", "GS"));
			recruitingTeams.add(new SelectItem("IRC", "IRC"));
			recruitingTeams.add(new SelectItem("IRC-C", "IRC-C"));
			recruitingTeams.add(new SelectItem("IRC-H", "IRC-H"));
			recruitingTeams.add(new SelectItem("IRC-N", "IRC-N"));
			recruitingTeams.add(new SelectItem("Vertical", "Vertical"));
		}
		return recruitingTeams;
	}

	/**
	 * @param recruitingTeams
	 *            the recruitingTeams to set
	 */
	public void setRecruitingTeams(List<SelectItem> recruitingTeams)
	{
		this.recruitingTeams = recruitingTeams;
	}

	/**
	 * @return the genders
	 */
	public List<SelectItem> getGenders()
	{
		if (genders == null)
		{
			genders = new ArrayList<SelectItem>();
			//genders.add(new SelectItem("N", "Not Specified"));
			genders.add(new SelectItem("M", "Male"));
			genders.add(new SelectItem("F", "Female"));
		}
		return genders;
	}

	/**
	 * @param genders
	 *            the genders to set
	 */
	public void setGenders(List<SelectItem> genders)
	{
		this.genders = genders;
	}

	/**
	 * @return the flsaStatuses
	 */
	public List<SelectItem> getFlsaStatuses()
	{
		if (flsaStatuses == null)
		{
			flsaStatuses = new ArrayList<SelectItem>();
			flsaStatuses.add(new SelectItem("EH", "Exempt-Hourly"));
			flsaStatuses.add(new SelectItem("NE", "Non-Exempt"));
			flsaStatuses.add(new SelectItem("ES", "Exempt-Salaried"));
			flsaStatuses.add(new SelectItem("NES", "Non-Exempt Salaried"));
		}
		return flsaStatuses;
	}

	/**
	 * @param flsaStatuses
	 *            the flsaStatuses to set
	 */
	public void setFlsaStatuses(List<SelectItem> flsaStatuses)
	{
		this.flsaStatuses = flsaStatuses;
	}

	/**
	 * @return the searchSubcontractorFlag
	 */
	public boolean isSearchSubcontractorFlag()
	{
		return searchSubcontractorFlag;
	}

	/**
	 * @param searchSubcontractorFlag
	 *            the searchSubcontractorFlag to set
	 */
	public void setSearchSubcontractorFlag(boolean searchSubcontractorFlag)
	{
		this.searchSubcontractorFlag = searchSubcontractorFlag;
	}

	/**
	 * @return the liaisonList
	 */
	public List<SelectItem> getLiaisonList()
	{
		if (liaisonList == null)
		{
			liaisonList = new ArrayList<SelectItem>();
			List<AnalyserLiaison> list = new ArrayList<AnalyserLiaison>();
			list = liaisonService.getLiaisonList(FacesUtils.getCurrentUserId());
			for (AnalyserLiaison l : list)
			{
				if (l.getStatus() == 1)
				{
					liaisonList.add(new SelectItem(l.getLiaisonName(), l.getLiaisonName()));
				}
			}
		}
		return liaisonList;
	}

	private AnalyserClientSite createAnalyserClientSiteObject(Map<String, Object> map)
	{

		AnalyserClientSite analyserClientSite = new AnalyserClientSite();

		analyserClientSite.setSiteId((Integer) map.get("SITE_ID"));
		analyserClientSite.setClientId((Integer) map.get("CLIENT_ID"));
		analyserClientSite.setManagerName((String) map.get("MANAGER_NAME"));
		Short orgId = (Short) map.get("ORG_ID");
		if (orgId != null)
		{
			analyserClientSite.setOrgId(orgId.intValue());
		}
		analyserClientSite.setAddress1((String) map.get("ADDRESS1"));
		analyserClientSite.setAddress2((String) map.get("ADDRESS2"));
		analyserClientSite.setCity((String) map.get("CITY"));
		analyserClientSite.setState((String) map.get("STATE"));
		analyserClientSite.setZipCode((String) map.get("ZIPCODE"));
		analyserClientSite.setTelephone((String) map.get("TELEPHONE"));
		analyserClientSite.setFax((String) map.get("FAX"));
		analyserClientSite.setEmail((String) map.get("EMAIL"));
		analyserClientSite.setStatus((Integer) map.get("STATUS"));
		analyserClientSite.setUpdatedBy((String) map.get("UPDATED_BY"));
		analyserClientSite.setUpdatedDate((Timestamp) map.get("UPDATED_DATE"));
		analyserClientSite.setLastName((String) map.get("LASTNAME"));
		analyserClientSite.setCountry((String) map.get("COUNTRY"));
		analyserClientSite.setEntryDate((Timestamp) map.get("ENTRYDATE"));
		analyserClientSite.setEnteredBy((String) map.get("ENTEREDBY"));
		analyserClientSite.setIsProcessed((String) map.get("ISPROCESSED"));
		analyserClientSite.setProcessDate((Timestamp) map.get("PROCESSDATE"));
		analyserClientSite.setIsUpdateProcessed((String) map.get("ISUPDATEPROCESSED"));
		analyserClientSite.setHawkSiteId((Integer) map.get("HAWKSITEID"));
		analyserClientSite.setIsAddressUSPSValidated((String) map.get("ISADDRESSUSPSVALIDATED"));
		analyserClientSite.setValidatedBy((String) map.get("VALIDATEDBY"));
		analyserClientSite.setLongitude((String) map.get("LONGITUDE"));
		analyserClientSite.setLatitude((String) map.get("LATITUDE"));
		analyserClientSite.setuSPSAddressValidationDate((Timestamp) map.get("USPSADDRESSVALIDATIONDATE"));

		return analyserClientSite;
	}

	/**
	 * @param liaisonList
	 *            the liaisonList to set
	 */
	public void setLiaisonList(List<SelectItem> liaisonList)
	{
		this.liaisonList = liaisonList;
	}

	/**
	 * @return the commPer1Display
	 */
	public boolean isCommPer1Display()
	{
		return commPer1Display;
	}

	/**
	 * @param commPer1Display
	 *            the commPer1Display to set
	 */
	public void setCommPer1Display(boolean commPer1Display)
	{
		this.commPer1Display = commPer1Display;
	}

	/**
	 * @return the searchClientFlag
	 */
	public boolean isSearchClientFlag()
	{
		return searchClientFlag;
	}

	/**
	 * @param searchClientFlag
	 *            the searchClientFlag to set
	 */
	public void setSearchClientFlag(boolean searchClientFlag)
	{
		this.searchClientFlag = searchClientFlag;
	}

	/**
	 * @return the grossProfitStyle
	 */
	public String getGrossProfitStyle()
	{
		return grossProfitStyle;
	}

	/**
	 * @param grossProfitStyle
	 *            the grossProfitStyle to set
	 */
	public void setGrossProfitStyle(String grossProfitStyle)
	{
		this.grossProfitStyle = grossProfitStyle;
	}

	/**
	 * @return the k401Check
	 */
	public boolean isK401Check()
	{
		return k401Check;
	}

	/**
	 * @param k401Check
	 *            the k401Check to set
	 */
	public void setK401Check(boolean k401Check)
	{
		this.k401Check = k401Check;
	}

	/**
	 * @return the healthCheck
	 */
	public boolean isHealthCheck()
	{
		return healthCheck;
	}

	/**
	 * @param healthCheck
	 *            the healthCheck to set
	 */
	public void setHealthCheck(boolean healthCheck)
	{
		this.healthCheck = healthCheck;
	}

	/**
	 * @return the flsaReferenceFlag
	 */
	public boolean isFlsaReferenceFlag()
	{
		return flsaReferenceFlag;
	}

	/**
	 * @param flsaReferenceFlag
	 *            the flsaReferenceFlag to set
	 */
	public void setFlsaReferenceFlag(boolean flsaReferenceFlag)
	{
		this.flsaReferenceFlag = flsaReferenceFlag;
	}

	/**
	 * @return the clientCompanyDialog
	 */
	public boolean isClientCompanyDialog()
	{
		return clientCompanyDialog;
	}

	/**
	 * @param clientCompanyDialog
	 *            the clientCompanyDialog to set
	 */
	public void setClientCompanyDialog(boolean clientCompanyDialog)
	{
		this.clientCompanyDialog = clientCompanyDialog;
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
	 * @return the action
	 */
	public String getAction()
	{
		return action;
	}

	/**
	 * @param action
	 *            the action to set
	 */
	public void setAction(String action)
	{
		this.action = action;
	}

	/**
	 * @return the showSaveButton
	 */
	public boolean isShowSaveButton()
	{
		return showSaveButton;
	}

	/**
	 * @param showSaveButton
	 *            the showSaveButton to set
	 */
	public void setShowSaveButton(boolean showSaveButton)
	{
		this.showSaveButton = showSaveButton;
	}

	/**
	 * @return the siteState
	 */
	public String getSiteState()
	{
		return siteState;
	}

	/**
	 * @param siteState
	 *            the siteState to set
	 */
	public void setSiteState(String siteState)
	{
		this.siteState = siteState;
	}

	/**
	 * @return the oldStartDate
	 */
	public String getOldStartDate()
	{
		return oldStartDate;
	}

	/**
	 * @param oldStartDate
	 *            the oldStartDate to set
	 */
	public void setOldStartDate(String oldStartDate)
	{
		this.oldStartDate = oldStartDate;
	}

	/**
	 * @return the commStartDate
	 */
	public String getCommStartDate()
	{
		return commStartDate;
	}

	/**
	 * @param commStartDate
	 *            the commStartDate to set
	 */
	public void setCommStartDate(String commStartDate)
	{
		this.commStartDate = commStartDate;
	}

	/**
	 * @return the effectiveDate
	 */
	public String getEffectiveDate()
	{
		return effectiveDate;
	}

	/**
	 * @param effectiveDate
	 *            the effectiveDate to set
	 */
	public void setEffectiveDate(String effectiveDate)
	{
		this.effectiveDate = effectiveDate;
	}

	/**
	 * @return the profitValue
	 */
	public Double getProfitValue()
	{
		return profitValue;
	}

	/**
	 * @param profitValue
	 *            the profitValue to set
	 */
	public void setProfitValue(Double profitValue)
	{
		this.profitValue = profitValue;
	}

	/**
	 * @return the isRehired
	 */
	public String getIsRehired()
	{
		return isRehired;
	}

	/**
	 * @param isRehired
	 *            the isRehired to set
	 */
	public void setIsRehired(String isRehired)
	{
		this.isRehired = isRehired;
	}

	public String getIsFalseTerminated()
	{
		return isFalseTerminated;
	}

	public void setIsFalseTerminated(String isFalseTerminated)
	{
		this.isFalseTerminated = isFalseTerminated;
	}

	/**
	 * @return the showSsn
	 */
	public boolean isShowSsn()
	{
		return showSsn;
	}

	/**
	 * @param showSsn
	 *            the showSsn to set
	 */
	public void setShowSsn(boolean showSsn)
	{
		this.showSsn = showSsn;
	}

	/**
	 * @return the validFlsaRefernce
	 */
	public boolean isValidFlsaRefernce()
	{
		return validFlsaRefernce;
	}

	/**
	 * @param validFlsaRefernce
	 *            the validFlsaRefernce to set
	 */
	public void setValidFlsaRefernce(boolean validFlsaRefernce)
	{
		this.validFlsaRefernce = validFlsaRefernce;
	}

	/**
	 * @return the validSsn
	 */
	public boolean isValidSsn()
	{
		return validSsn;
	}

	/**
	 * @param validSsn
	 *            the validSsn to set
	 */
	public void setValidSsn(boolean validSsn)
	{
		this.validSsn = validSsn;
	}

	/**
	 * @return the splitPercent1
	 */
	public List<SelectItem> getSplitPercent1()
	{
		if (splitPercent1 == null)
		{
			splitPercent1 = new ArrayList<SelectItem>();
			splitPercent1.add(new SelectItem("100", "100%"));
			splitPercent1.add(new SelectItem("75", "75%"));
			splitPercent1.add(new SelectItem("50", "50%"));
			splitPercent1.add(new SelectItem("25", "25%"));
			splitPercent1.add(new SelectItem("0", "0%"));
		}
		return splitPercent1;
	}

	/**
	 * @param splitPercent1
	 *            the splitPercent1 to set
	 */
	public void setSplitPercent1(List<SelectItem> splitPercent1)
	{
		this.splitPercent1 = splitPercent1;
	}

	/**
	 * @return the splitPercent2
	 */
	public List<SelectItem> getSplitPercent2()
	{
		if (splitPercent2 == null)
		{
			splitPercent2 = new ArrayList<SelectItem>();
			splitPercent2.add(new SelectItem("100", "100%"));
			splitPercent2.add(new SelectItem("75", "75%"));
			splitPercent2.add(new SelectItem("50", "50%"));
			splitPercent2.add(new SelectItem("25", "25%"));
			splitPercent2.add(new SelectItem("0", "0%"));
		}
		return splitPercent2;
	}

	/**
	 * @param splitPercent2
	 *            the splitPercent2 to set
	 */
	public void setSplitPercent2(List<SelectItem> splitPercent2)
	{
		this.splitPercent2 = splitPercent2;
	}

	/**
	 * @return the splitPercent3
	 */
	public List<SelectItem> getSplitPercent3()
	{
		if (splitPercent3 == null)
		{
			splitPercent3 = new ArrayList<SelectItem>();
			splitPercent3.add(new SelectItem("100", "100%"));
			splitPercent3.add(new SelectItem("75", "75%"));
			splitPercent3.add(new SelectItem("50", "50%"));
			splitPercent3.add(new SelectItem("25", "25%"));
			splitPercent3.add(new SelectItem("0", "0%"));
		}
		return splitPercent3;
	}

	/**
	 * @param splitPercent3
	 *            the splitPercent3 to set
	 */
	public void setSplitPercent3(List<SelectItem> splitPercent3)
	{
		this.splitPercent3 = splitPercent3;
	}

	/**
	 * @return the splitPercent4
	 */
	public List<SelectItem> getSplitPercent4()
	{
		if (splitPercent4 == null)
		{
			splitPercent4 = new ArrayList<SelectItem>();
			splitPercent4.add(new SelectItem("100", "100%"));
			splitPercent4.add(new SelectItem("75", "75%"));
			splitPercent4.add(new SelectItem("50", "50%"));
			splitPercent4.add(new SelectItem("25", "25%"));
			splitPercent4.add(new SelectItem("0", "0%"));
		}
		return splitPercent4;
	}

	/**
	 * @param splitPercent4
	 *            the splitPercent4 to set
	 */
	public void setSplitPercent4(List<SelectItem> splitPercent4)
	{
		this.splitPercent4 = splitPercent4;
	}

	/**
	 * @return the commissionPersonsList1
	 */
	public List<SelectItem> getCommissionPersonsList1()
	{
		return commissionPersonsList1;
	}

	/**
	 * @param commissionPersonsList1
	 *            the commissionPersonsList1 to set
	 */
	public void setCommissionPersonsList1(List<SelectItem> commissionPersonsList1)
	{
		this.commissionPersonsList1 = commissionPersonsList1;
	}

	/**
	 * @return the commissionPersonsList2
	 */
	public List<SelectItem> getCommissionPersonsList2()
	{
		return commissionPersonsList2;
	}

	/**
	 * @param commissionPersonsList2
	 *            the commissionPersonsList2 to set
	 */
	public void setCommissionPersonsList2(List<SelectItem> commissionPersonsList2)
	{
		this.commissionPersonsList2 = commissionPersonsList2;
	}

	/**
	 * @return the commissionPersonsList3
	 */
	public List<SelectItem> getCommissionPersonsList3()
	{
		return commissionPersonsList3;
	}

	/**
	 * @param commissionPersonsList3
	 *            the commissionPersonsList3 to set
	 */
	public void setCommissionPersonsList3(List<SelectItem> commissionPersonsList3)
	{
		this.commissionPersonsList3 = commissionPersonsList3;
	}

	/**
	 * @return the commissionPersonsList4
	 */
	public List<SelectItem> getCommissionPersonsList4()
	{
		return commissionPersonsList4;
	}

	/**
	 * @param commissionPersonsList4
	 *            the commissionPersonsList4 to set
	 */
	public void setCommissionPersonsList4(List<SelectItem> commissionPersonsList4)
	{
		this.commissionPersonsList4 = commissionPersonsList4;
	}

	/**
	 * @return the commissionPersonsList5
	 */
	public List<SelectItem> getCommissionPersonsList5()
	{
		return commissionPersonsList5;
	}

	/**
	 * @param commissionPersonsList5
	 *            the commissionPersonsList5 to set
	 */
	public void setCommissionPersonsList5(List<SelectItem> commissionPersonsList5)
	{
		this.commissionPersonsList5 = commissionPersonsList5;
	}

	/**
	 * @return the commissionPersonsList6
	 */
	public List<SelectItem> getCommissionPersonsList6()
	{
		return commissionPersonsList6;
	}

	/**
	 * @param commissionPersonsList6
	 *            the commissionPersonsList6 to set
	 */
	public void setCommissionPersonsList6(List<SelectItem> commissionPersonsList6)
	{
		this.commissionPersonsList6 = commissionPersonsList6;
	}

	/**
	 * @return the commissionPersonsList7
	 */
	public List<SelectItem> getCommissionPersonsList7()
	{
		return commissionPersonsList7;
	}

	/**
	 * @param commissionPersonsList7
	 *            the commissionPersonsList7 to set
	 */
	public void setCommissionPersonsList7(List<SelectItem> commissionPersonsList7)
	{
		this.commissionPersonsList7 = commissionPersonsList7;
	}

	/**
	 * @return the commissionPersonsList8
	 */
	public List<SelectItem> getCommissionPersonsList8()
	{
		return commissionPersonsList8;
	}

	/**
	 * @param commissionPersonsList8
	 *            the commissionPersonsList8 to set
	 */
	public void setCommissionPersonsList8(List<SelectItem> commissionPersonsList8)
	{
		this.commissionPersonsList8 = commissionPersonsList8;
	}

	/**
	 * @return the commissionPersonsList9
	 */
	public List<SelectItem> getCommissionPersonsList9()
	{
		return commissionPersonsList9;
	}

	/**
	 * @param commissionPersonsList9
	 *            the commissionPersonsList9 to set
	 */
	public void setCommissionPersonsList9(List<SelectItem> commissionPersonsList9)
	{
		this.commissionPersonsList9 = commissionPersonsList9;
	}

	/**
	 * @return the role
	 */
	public Role getRole()
	{
		return role;
	}

	/**
	 * @param role
	 *            the role to set
	 */
	public void setRole(Role role)
	{
		this.role = role;
	}

	/**
	 * @return the hideAdditionalCommissionPersonsDiv
	 */
	public boolean isHideAdditionalCommissionPersonsDiv()
	{
		return hideAdditionalCommissionPersonsDiv;
	}

	/**
	 * @param hideAdditionalCommissionPersonsDiv
	 *            the hideAdditionalCommissionPersonsDiv to set
	 */
	public void setHideAdditionalCommissionPersonsDiv(boolean hideAdditionalCommissionPersonsDiv)
	{
		this.hideAdditionalCommissionPersonsDiv = hideAdditionalCommissionPersonsDiv;
	}

	/**
	 * @return the allCommissionPersonsList
	 */
	public List<String> getAllCommissionPersonsList()
	{
		return allCommissionPersonsList;
	}

	/**
	 * @param allCommissionPersonsList
	 *            the allCommissionPersonsList to set
	 */
	public void setAllCommissionPersonsList(List<String> allCommissionPersonsList)
	{
		this.allCommissionPersonsList = allCommissionPersonsList;
	}

	/**
	 * @return the commissionPerson
	 */
	public String getCommissionPerson()
	{
		return commissionPerson;
	}

	/**
	 * @param commissionPerson
	 *            the commissionPerson to set
	 */
	public void setCommissionPerson(String commissionPerson)
	{
		this.commissionPerson = commissionPerson;
	}

	/**
	 * @return the managingDirectorList
	 */
	public List<SelectItem> getManagingDirectorList()
	{
		return managingDirectorList;
	}

	/**
	 * @param managingDirectorList
	 *            the managingDirectorList to set
	 */
	public void setManagingDirectorList(List<SelectItem> managingDirectorList)
	{
		this.managingDirectorList = managingDirectorList;
	}

	/**
	 * @return the validZipCode
	 */
	public boolean isValidZipCode()
	{
		return validZipCode;
	}

	/**
	 * @param validZipCode
	 *            the validZipCode to set
	 */
	public void setValidZipCode(boolean validZipCode)
	{
		this.validZipCode = validZipCode;
	}

	public boolean isAddressValidated()
	{
		return addressValidated;
	}

	public void setAddressValidated(boolean addressValidated)
	{
		this.addressValidated = addressValidated;
	}

	public String getSiteZipCode()
	{
		return siteZipCode;
	}

	public void setSiteZipCode(String siteZipCode)
	{
		this.siteZipCode = siteZipCode;
	}

	public String getClientAddressId()
	{
		return clientAddressId;
	}

	public void setClientAddressId(String clientAddressId)
	{
		this.clientAddressId = clientAddressId;
	}

	public String getSiteId()
	{
		return siteId;
	}

	public void setSiteId(String siteId)
	{
		this.siteId = siteId;
	}

	public String getOldCommissionPersonGrade1()
	{
		return oldCommissionPersonGrade1;
	}

	public void setOldCommissionPersonGrade1(String oldCommissionPersonGrade1)
	{
		this.oldCommissionPersonGrade1 = oldCommissionPersonGrade1;
	}

	public String getOldCommissionPersonGrade3()
	{
		return oldCommissionPersonGrade3;
	}

	public void setOldCommissionPersonGrade3(String oldCommissionPersonGrade3)
	{
		this.oldCommissionPersonGrade3 = oldCommissionPersonGrade3;
	}

	public String getCommName1()
	{
		return commName1;
	}

	public void setCommName1(String commName1)
	{
		this.commName1 = commName1;
	}

	public String getCommName3()
	{
		return commName3;
	}

	public void setCommName3(String commName3)
	{
		this.commName3 = commName3;
	}

	public String getPtoEligibility()
	{
		return ptoEligibility;
	}

	public void setPtoEligibility(String ptoEligibility)
	{
		this.ptoEligibility = ptoEligibility;
	}

	public String getSiteZipCodeValue()
	{
		return siteZipCodeValue;
	}

	public void setSiteZipCodeValue(String siteZipCodeValue)
	{
		this.siteZipCodeValue = siteZipCodeValue;
	}



	/**
	 * @return the ptoStatus
	 */
	public String getPtoStatus()
	{
		return ptoStatus;
	}

	/**
	 * @param ptoStatus
	 *            the ptoStatus to set
	 */
	public void setPtoStatus(String ptoStatus)
	{
		this.ptoStatus = ptoStatus;
	}

	/**
	 * @return the selectedAnalyser
	 */
	public AnalyserDTO getSelectedAnalyser()
	{
		return selectedAnalyser;
	}

	/**
	 * @param selectedAnalyser
	 *            the selectedAnalyser to set
	 */
	public void setSelectedAnalyser(AnalyserDTO selectedAnalyser)
	{
		this.selectedAnalyser = selectedAnalyser;
	}

	public String getState()
	{
		return state;
	}

	public void setState(String state)
	{
		this.state = state;
	}

	public Boolean getShowRejectionDiv()
	{
		return showRejectionDiv;
	}

	public void setShowRejectionDiv(Boolean showRejectionDiv)
	{
		this.showRejectionDiv = showRejectionDiv;
	}

	public Double getOldGp()
	{
		return oldGp;
	}

	public void setOldGp(Double oldGp)
	{
		this.oldGp = oldGp;
	}

	public List<Map<String, Object>> getBranchData()
	{
		return branchData;
	}

	public void setBranchData(List<Map<String, Object>> branchData)
	{
		this.branchData = branchData;
	}

	public String getDistance()
	{
		return distance;
	}

	public void setDistance(String distance)
	{
		this.distance = distance;
	}

	/**
	 * @return the latitude
	 */
	public String getLatitude()
	{
		return latitude;
	}

	/**
	 * @param latitude
	 *            the latitude to set
	 */
	public void setLatitude(String latitude)
	{
		this.latitude = latitude;
	}

	/**
	 * @return the longitude
	 */
	public String getLongitude()
	{
		return longitude;
	}

	/**
	 * @param longitude
	 *            the longitude to set
	 */
	public void setLongitude(String longitude)
	{
		this.longitude = longitude;
	}

	/**
	 * @return the workSiteLatitude
	 */
	public String getWorkSiteLatitude()
	{
		return workSiteLatitude;
	}

	/**
	 * @param workSiteLatitude
	 *            the workSiteLatitude to set
	 */
	public void setWorkSiteLatitude(String workSiteLatitude)
	{
		this.workSiteLatitude = workSiteLatitude;
	}

	/**
	 * @return the workSiteLongitude
	 */
	public String getWorkSiteLongitude()
	{
		return workSiteLongitude;
	}

	/**
	 * @param workSiteLongitude
	 *            the workSiteLongitude to set
	 */
	public void setWorkSiteLongitude(String workSiteLongitude)
	{
		this.workSiteLongitude = workSiteLongitude;
	}

	/**
	 * @return the msg
	 */
	public String getMsg()
	{
		return msg;
	}

	/**
	 * @param msg
	 *            the msg to set
	 */
	public void setMsg(String msg)
	{
		this.msg = msg;
	}

	public String getPrevSsn()
	{
		return prevSsn;
	}

	public void setPrevSsn(String prevSsn)
	{
		this.prevSsn = prevSsn;
	}

	public String getPrevFlsaRef()
	{
		return prevFlsaRef;
	}

	public void setPrevFlsaRef(String prevFlsaRef)
	{
		this.prevFlsaRef = prevFlsaRef;
	}

	/**
	 * @return the ircCommissionPersonStatusForAe1
	 */
	public Integer getIrcCommissionPersonStatusForAe1()
	{
		return ircCommissionPersonStatusForAe1;
	}

	/**
	 * @param ircCommissionPersonStatusForAe1 the ircCommissionPersonStatusForAe1 to set
	 */
	public void setIrcCommissionPersonStatusForAe1(Integer ircCommissionPersonStatusForAe1)
	{
		this.ircCommissionPersonStatusForAe1 = ircCommissionPersonStatusForAe1;
	}

	/**
	 * @return the ircCommissionPersonStatusForAe2
	 */
	public Integer getIrcCommissionPersonStatusForAe2()
	{
		return ircCommissionPersonStatusForAe2;
	}

	/**
	 * @param ircCommissionPersonStatusForAe2 the ircCommissionPersonStatusForAe2 to set
	 */
	public void setIrcCommissionPersonStatusForAe2(Integer ircCommissionPersonStatusForAe2)
	{
		this.ircCommissionPersonStatusForAe2 = ircCommissionPersonStatusForAe2;
	}

	/**
	 * @return the ircCommissionPersonStatusForRec1
	 */
	public Integer getIrcCommissionPersonStatusForRec1()
	{
		return ircCommissionPersonStatusForRec1;
	}

	/**
	 * @param ircCommissionPersonStatusForRec1 the ircCommissionPersonStatusForRec1 to set
	 */
	public void setIrcCommissionPersonStatusForRec1(Integer ircCommissionPersonStatusForRec1)
	{
		this.ircCommissionPersonStatusForRec1 = ircCommissionPersonStatusForRec1;
	}

	/**
	 * @return the ircCommissionPersonStatusForRec2
	 */
	public Integer getIrcCommissionPersonStatusForRec2()
	{
		return ircCommissionPersonStatusForRec2;
	}

	/**
	 * @param ircCommissionPersonStatusForRec2 the ircCommissionPersonStatusForRec2 to set
	 */
	public void setIrcCommissionPersonStatusForRec2(Integer ircCommissionPersonStatusForRec2)
	{
		this.ircCommissionPersonStatusForRec2 = ircCommissionPersonStatusForRec2;
	}

	/**
	 * @return the showPtoFields
	 */
	public boolean isShowPtoFields()
	{
		return showPtoFields;
	}

	/**
	 * @param showPtoFields the showPtoFields to set
	 */
	public void setShowPtoFields(boolean showPtoFields)
	{
		this.showPtoFields = showPtoFields;
	}

	/**
	 * @return the roleId
	 */
	public Integer getRoleId() {
		return roleId;
	}

	/**
	 * @param roleId the roleId to set
	 */
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	/**
	 * @return the branchAddress
	 */
	public StringBuilder getBranchAddress() {
		return branchAddress;
	}

	/**
	 * @param branchAddress the branchAddress to set
	 */
	public void setBranchAddress(StringBuilder branchAddress) {
		this.branchAddress = branchAddress;
	}

	/**
	 * @return the completeStatementFilePath
	 */
	public String getCompleteStatementFilePath() {
		return completeStatementFilePath;
	}

	/**
	 * @param completeStatementFilePath the completeStatementFilePath to set
	 */
	public void setCompleteStatementFilePath(String completeStatementFilePath) {
		this.completeStatementFilePath = completeStatementFilePath;
	}

	/**
	 * @return the reportCreated
	 */
	public boolean isReportCreated() {
		return reportCreated;
	}

	/**
	 * @param reportCreated the reportCreated to set
	 */
	public void setReportCreated(boolean reportCreated) {
		this.reportCreated = reportCreated;
	}

	/**
	 * @return the newPdfFileName
	 */
	public String getNewPdfFileName() {
		return newPdfFileName;
	}

	/**
	 * @param newPdfFileName the newPdfFileName to set
	 */
	public void setNewPdfFileName(String newPdfFileName) {
		this.newPdfFileName = newPdfFileName;
	}

	/**
	 * @return the generatedPdfFile
	 */
	public StreamedContent getGeneratedPdfFile() {
		return generatedPdfFile;
	}

	/**
	 * @param generatedPdfFile the generatedPdfFile to set
	 */
	public void setGeneratedPdfFile(StreamedContent generatedPdfFile) {
		this.generatedPdfFile = generatedPdfFile;
	}
	/**
	 * @return the generatedPdfFile
	 */
	public String getCompanyCodeLocal() {
		return companyCodeLocal;
	}
	/**
	 * @param generatedPdfFile the generatedPdfFile to set
	 */
	public void setCompanyCodeLocal(String companyCodeLocal) {
		this.companyCodeLocal = companyCodeLocal;
	}		

	/**
	 * @return the enableCompanySpecificFields
	 */
	public boolean isEnableCompanySpecificFields() {
		return enableCompanySpecificFields;
	}
	/**
	 * @param enableCompanySpecificFields the enableCompanySpecificFields to set
	 */
	public void setEnableCompanySpecificFields(boolean enableCompanySpecificFields) {
		this.enableCompanySpecificFields = enableCompanySpecificFields;
	}

	public void createPdfStatement() {
		try {
			System.out.println("Inside ANALYSERBEAN --> createPdfStatement.");
			logger.debug("Inside ANALYSERBEAN --> createPdfStatement.");

			String templatePath = FacesUtils.getTemplateFilePath();

			//String realPath = FacesUtils.getTempFilePath();
			String realPath = FacesUtils.getTemporaryFilePath();
			String newFileName = Util.getPdfFileName(analyser);
			String completeFilePath = realPath + newFileName;
			this.setCompleteStatementFilePath(completeFilePath);
			this.setNewPdfFileName(newFileName);

			// Template PDF
			//String templateFilePath = realPath + Constants.ANALYSER_PDF_TEMPLATE_FILE;
			String templateFilePath = templatePath + Constants.ANALYSER_PDF_TEMPLATE_FILE;
			String generatedFromTemplFilePath = realPath + newFileName;
			PdfReader pdfReader = new PdfReader(templateFilePath);
			PdfStamper pdfStamper = new PdfStamper(pdfReader, new FileOutputStream(generatedFromTemplFilePath));
			pdfStamper.getAcroFields().setField("Office", analyser.getBranch());
			pdfStamper.getAcroFields().setField("Distance", analyser.getDistanceFromWorksite());

			pdfStamper.getAcroFields().setField("Portfolio", analyser.getPortfolio());
			pdfStamper.getAcroFields().setField("PortfolioDescription", analyser.getPortfolioDescription());

			System.out.println("templatePath = "+templatePath);
			logger.debug("templatePath = "+templatePath);
			System.out.println("realPath = "+realPath);
			logger.debug("realPath = "+realPath);
			System.out.println("newFileName = "+newFileName);
			logger.debug("newFileName = "+newFileName);
			System.out.println("completeFilePath = "+completeFilePath);
			logger.debug("completeFilePath = "+completeFilePath);
			System.out.println("templateFilePath = "+templateFilePath);
			logger.debug("templateFilePath = "+templateFilePath);

			logger.debug("File for downloading is : " + realPath);

			// OFFICE COMMISSION INFORMATION
			pdfStamper.getAcroFields().setField("Vertical", analyser.getVertical());
			pdfStamper.getAcroFields().setField("Product", analyser.getProduct());
			pdfStamper.getAcroFields().setField("ManagingDirector", analyser.getManagingDirector());
			pdfStamper.getAcroFields().setField("Comments", analyser.getComments());
			pdfStamper.getAcroFields().setField("AccountExec-1", analyser.getCommName1());
			pdfStamper.getAcroFields().setField("CommissionModel1", analyser.getCommissionModel1());
			pdfStamper.getAcroFields().setField("OfficePercent", analyser.getCommPer1().toString() + "%");
			pdfStamper.getAcroFields().setField("OfficeGrade", analyser.getCommissionPersonGrade1());
			pdfStamper.getAcroFields().setField("ManagingDirector", analyser.getManagingDirector());
			pdfStamper.getAcroFields().setField("OfficeAmount", "$" + analyser.getCommision1());
			pdfStamper.getAcroFields().setField("OfficeAE-1Split", analyser.getSplitCommissionPercentage1() + "%");
			pdfStamper.getAcroFields().setField("Exec1Orphan", Util.getBooleanValue(analyser.getExecOrphan()));
			pdfStamper.getAcroFields().setField("COM", Util.getBooleanValue(analyser.isCom()));

			pdfStamper.getAcroFields().setField("Recruiter-1", analyser.getCommName2());
			pdfStamper.getAcroFields().setField("CommissionModel2", analyser.getCommissionModel2());
			pdfStamper.getAcroFields().setField("Recruiter-1Percent", analyser.getCommPer2().toString() + "%");
			pdfStamper.getAcroFields().setField("Recruiter-1Amount", "$" + analyser.getCommision2());
			pdfStamper.getAcroFields().setField("Recruiter-1 Split", analyser.getSplitCommissionPercentage2() + "%");
			pdfStamper.getAcroFields().setField("Model3Orphan", Util.getBooleanValue(analyser.getRecruiterOrphan()));
			pdfStamper.getAcroFields().setField("Account Exec-2", analyser.getCommName3());

			pdfStamper.getAcroFields().setField("CommissionModel3", analyser.getCommissionModel3());
			pdfStamper.getAcroFields().setField("CommissionModel3Percent", analyser.getCommPer3Inter() + "%");
			pdfStamper.getAcroFields().setField("CommissionModel3Grade", analyser.getCommissionPersonGrade3());
			pdfStamper.getAcroFields().setField("CommissionModel3Amount", "$" + analyser.getCommision3());
			pdfStamper.getAcroFields().setField("AE-2Split", analyser.getSplitCommissionPercentage3() + "%");
			pdfStamper.getAcroFields().setField("Model3Orphan", Util.getBooleanValue(analyser.getOther1Orphan()));
			pdfStamper.getAcroFields().setField("Recruiter-2", analyser.getCommName4());
			pdfStamper.getAcroFields().setField("Recruiter-2CommissionModel4", analyser.getCommissionModel4());
			pdfStamper.getAcroFields().setField("Recruiter-2Percent", analyser.getCommPer4().toString() + "%");
			pdfStamper.getAcroFields().setField("Recruiter-2Amount", "$" + analyser.getCommision4());
			pdfStamper.getAcroFields().setField("Recruiter-2 Split", analyser.getSplitCommissionPercentage4() + "%");
			pdfStamper.getAcroFields().setField("Model4Orphan", Util.getBooleanValue(analyser.getOther2Orphan()));

			pdfStamper.getAcroFields().setField("VerticalSales", analyser.getCommissionPerson5());
			pdfStamper.getAcroFields().setField("VerticalSalesPercent",
					analyser.getCommissionPercentage5().toString() + "%");
			pdfStamper.getAcroFields().setField("VerticalSalesAmount", "$" + analyser.getCommission5().toString());
			pdfStamper.getAcroFields().setField("IT-GS-Practice", analyser.getCommissionPerson6());
			pdfStamper.getAcroFields().setField("IT-GS-PracticePercent",
					analyser.getCommissionPercentage6().toString() + "%");
			pdfStamper.getAcroFields().setField("IT-GS-PracticeAmount", "$" + analyser.getCommission6().toString());
			pdfStamper.getAcroFields().setField("IT-GS-Delivery", analyser.getCommissionPerson7());
			pdfStamper.getAcroFields().setField("IT-GS-DeliveryPercent",
					analyser.getCommissionPercentage7().toString() + "%");
			pdfStamper.getAcroFields().setField("IT-GS-DeliveryAmount", "$" + analyser.getCommission7().toString());
			pdfStamper.getAcroFields().setField("IT-GS-BDE", analyser.getCommissionPerson8());
			pdfStamper.getAcroFields().setField("IT-GS-BDEPercent",
					analyser.getCommissionPercentage8().toString() + "%");
			pdfStamper.getAcroFields().setField("IT-GS-BDEAmount", "$" + analyser.getCommission8().toString());
			pdfStamper.getAcroFields().setField("IT-GS-Proposal", analyser.getCommissionPerson9());
			pdfStamper.getAcroFields().setField("IT-GS-ProposalPercent",
					analyser.getCommissionPercentage9().toString() + "%");
			pdfStamper.getAcroFields().setField("IT-GS-ProposalAmount", "$" + analyser.getCommission9().toString());
			pdfStamper.getAcroFields().setField("EmployeeCategory", analyser.getTemps());
			pdfStamper.getAcroFields().setField("DealType", analyser.getDealType());
			pdfStamper.getAcroFields().setField("H1", Util.getBooleanValue(analyser.getH1()));
			pdfStamper.getAcroFields().setField("Classification", analyser.getAnalyzerCategory2());
			pdfStamper.getAcroFields().setField("chkRef", Util.getBooleanValue(analyser.isReferences()));
			pdfStamper.getAcroFields().setField("CommEffectiveDate", analyser.getCommEffDate());
			pdfStamper.getAcroFields().setField("HRBusinessPartner", analyser.getLiaisonName());
			pdfStamper.getAcroFields().setField("RecruitingTeam", analyser.getRecruitingClassification());

			// EMPLOYEE INFORMATION
			pdfStamper.getAcroFields().setField("LastName", analyser.getlName());
			pdfStamper.getAcroFields().setField("Initial", analyser.getInitial());
			pdfStamper.getAcroFields().setField("FirstName", analyser.getfName());
			pdfStamper.getAcroFields().setField("Gender", analyser.getGender());
			pdfStamper.getAcroFields().setField("EmployeeType", analyser.getEmpType());
			pdfStamper.getAcroFields().setField("Address1", analyser.getAddress1());
			pdfStamper.getAcroFields().setField("Address2", analyser.getAddress2());

			pdfStamper.getAcroFields().setField("EmpCity", analyser.getCity());
			pdfStamper.getAcroFields().setField("EmpState", analyser.getState());
			pdfStamper.getAcroFields().setField("EmpZip", analyser.getZip());
			pdfStamper.getAcroFields().setField("EmpHomePhone", analyser.getTelephone());
			pdfStamper.getAcroFields().setField("EmpWorkPhone", analyser.getWorkPhone());
			pdfStamper.getAcroFields().setField("EmpFax", analyser.getFax());
			pdfStamper.getAcroFields().setField("EmpWorkEmail", analyser.getWorkEmail());
			pdfStamper.getAcroFields().setField("EmpPersonalEmail", analyser.getEmail());
			pdfStamper.getAcroFields().setField("EmpDateOfBirth", analyser.getDob());
			pdfStamper.getAcroFields().setField("EmpJobCat", analyser.getJobCategory());
			pdfStamper.getAcroFields().setField("EmpJobTitle", analyser.getJobTitle());
			pdfStamper.getAcroFields().setField("EmpMobile", analyser.getMobilePager());
			pdfStamper.getAcroFields().setField("EmpSSN", "***-**-" + ( 
					(analyser.getSsn() == null || analyser.getSsn().length() < 4) ? analyser.getSsn() : analyser.getSsn().substring(analyser.getSsn().length() - 4))
					);
			pdfStamper.getAcroFields().setField("EmpFLSAStatus", analyser.getFlsaStatus());
			pdfStamper.getAcroFields().setField("EmpFLSAReference", analyser.getFlsaReference());
			pdfStamper.getAcroFields().setField("EmpBonusEligible", analyser.getIsBonusEligible());
			pdfStamper.getAcroFields().setField("EmpAnnualBonusPer", analyser.getBonusPercentage());
			pdfStamper.getAcroFields().setField("EmpClass", analyser.getEmployeeClass());
			pdfStamper.getAcroFields().setField("EmpSkillCategory", analyser.getSkillCategory());
			pdfStamper.getAcroFields().setField("EmpTravelRequired", analyser.getTravelRequired());
			pdfStamper.getAcroFields().setField("EmpVeteran", analyser.getEmployeeVeteran());
			pdfStamper.getAcroFields().setField("EmpJobBoard", analyser.getConsultantJobBoard());
			pdfStamper.getAcroFields().setField("EmpUnempl60Days", analyser.getUnEmployedForTwoMonths());
			pdfStamper.getAcroFields().setField("SubCompanyName", analyser.getContCompanyName());
			pdfStamper.getAcroFields().setField("SubCompanyFIN", analyser.getContFin());
			pdfStamper.getAcroFields().setField("SubEmail", analyser.getContEmail());
			pdfStamper.getAcroFields().setField("PaymentTerm", analyser.getContPayTerm());
			pdfStamper.getAcroFields().setField("SubPhone", analyser.getContPhone());
			pdfStamper.getAcroFields().setField("SubPOCName", analyser.getContPocName());
			pdfStamper.getAcroFields().setField("SubAddress", analyser.getContAddress());

			// PERMANENT PLACEMENT DETAILS

			pdfStamper.getAcroFields().setField("SalaryAmount", "$" + analyser.getSalaryAmount());
			pdfStamper.getAcroFields().setField("PlacementPer", analyser.getPlacementPercentage() + "%");
			pdfStamper.getAcroFields().setField("PlacementAmount", "$" + analyser.getPlacementAmount());
			pdfStamper.getAcroFields().setField("PlacementDate", analyser.getPlacementDate());

			// ANALYZER DETAILS

			pdfStamper.getAcroFields().setField("TotalHoursWorked", analyser.getTotHoursWorked().toString());
			pdfStamper.getAcroFields().setField("BillRate", "$" + analyser.getBillRate().toString());
			pdfStamper.getAcroFields().setField("AnnualSalary", "$" + analyser.getAnnualPay());
			pdfStamper.getAcroFields().setField("PayRate", "$" + analyser.getPayRate().toString());
			pdfStamper.getAcroFields().setField("GnA", "$" + analyser.getGa().toString());
			pdfStamper.getAcroFields().setField("OtherDollar", "$" + analyser.getOd());
			pdfStamper.getAcroFields().setField("OtherPerHr", "$" + analyser.getOtherAmounts());
			pdfStamper.getAcroFields().setField("DiscountPer", analyser.getDiscountRate() + "%");
			pdfStamper.getAcroFields().setField("Discount", "$" + analyser.getDiscount());
			pdfStamper.getAcroFields().setField("Commission", "$" + analyser.getCommission().toString());
			pdfStamper.getAcroFields().setField("GrossProfitPer", analyser.getGrossProfitPercentage().toString() + "%");
			pdfStamper.getAcroFields().setField("GrossProfit", "$" + analyser.getGrossProfit().toString());
			pdfStamper.getAcroFields().setField("SpreadW", "$" + analyser.getSpreadWeek());
			pdfStamper.getAcroFields().setField("CommAbleProfit", "$" + analyser.getCommissionableProfit().toString());
			pdfStamper.getAcroFields().setField("Profit", "$" + analyser.getProfit().toString());
			pdfStamper.getAcroFields().setField("TaxFrExpenses", "$" + analyser.getTax());
			pdfStamper.getAcroFields().setField("StartDate", analyser.getStartDate());
			pdfStamper.getAcroFields().setField("EndDate", analyser.getEndDate());
			pdfStamper.getAcroFields().setField("EffectiveDate", analyser.getEffectiveDate());
			pdfStamper.getAcroFields().setField("DailyPerDiem", "$" + analyser.getPerdiem());
			pdfStamper.getAcroFields().setField("OTBill", "$" + analyser.getOtb());
			pdfStamper.getAcroFields().setField("OTPay", "$" + analyser.getOneTimeAmount());
			pdfStamper.getAcroFields().setField("DTBill", "$" + analyser.getDoubleTimeBill());
			pdfStamper.getAcroFields().setField("DTPay", "$" + analyser.getDoubleTime());
			pdfStamper.getAcroFields().setField("ProjCostPerHr", "$" + analyser.getProjectCost().toString());
			pdfStamper.getAcroFields().setField("EqptCost", "$" + analyser.getEquipmentCost().toString());
			pdfStamper.getAcroFields().setField("BillCost", "$" + analyser.getNonBillableCost().toString());
			pdfStamper.getAcroFields().setField("VisaCost", "$" + analyser.getImmigrationCost().toString());
			pdfStamper.getAcroFields().setField("RefFeeCheck", analyser.getChkReferralFee());
			pdfStamper.getAcroFields().setField("RefFee", "$" + analyser.getReferralFeeAmount());
			pdfStamper.getAcroFields().setField("STD", analyser.getStdBenefit());
			pdfStamper.getAcroFields().setField("LTD", analyser.getLtdBenefit());
			pdfStamper.getAcroFields().setField("DentalInsurance", analyser.getDentalInsurance());

			// PTO DETAILS

			pdfStamper.getAcroFields().setField("PTODays", analyser.getRdoPTO());
			pdfStamper.getAcroFields().setField("SickLeaveCost", "$" + analyser.getSickLeaveCost().toString());
			pdfStamper.getAcroFields().setField("Leave", "$" + analyser.getLeave().toString());
			pdfStamper.getAcroFields().setField("SickleaveType", analyser.getSickLeaveType());
			pdfStamper.getAcroFields().setField("SickLeaveRate", "$" + analyser.getSickLeavePerHourRate().toString());
			pdfStamper.getAcroFields().setField("SickLeaveCap", analyser.getSickLeaveCap());
			pdfStamper.getAcroFields().setField("PTOLimitSick", analyser.getpTOLimitToOverrideSick().toString());
			pdfStamper.getAcroFields().setField("Non-BillablePTO", analyser.getNonBillablePTO().toString());
			pdfStamper.getAcroFields().setField("Non-BillablePTOCost", "$" + analyser.getNonBillableCost().toString());
			pdfStamper.getAcroFields().setField("BillablePTO", analyser.getBillablePTO().toString());
			pdfStamper.getAcroFields().setField("BillablePTOCost", "$" + analyser.getBillablePTOCost().toString());
			pdfStamper.getAcroFields().setField("Holiday", analyser.getTotalHolidays().toString());
			pdfStamper.getAcroFields().setField("Non-BillableHoliday", analyser.getNonBillableHolidays().toString());
			pdfStamper.getAcroFields().setField("Non-BillableHolidayCost",
					"$" + analyser.getNonBillableHolidaysCost().toString());
			pdfStamper.getAcroFields().setField("BillableHoliday", analyser.getBillableHolidays().toString());
			pdfStamper.getAcroFields().setField("BillableHolidayCost",
					"$" + analyser.getBillableHolidaysCost().toString());

			// CLIENT INFORMATION - INVOICE ADDRESS

			pdfStamper.getAcroFields().setField("ClientCompanyName", analyser.getClientCompany());
			pdfStamper.getAcroFields().setField("InvoiceAddress", analyser.getInvoiceAddress());
			pdfStamper.getAcroFields().setField("PONumber", analyser.getContractVehicle());
			pdfStamper.getAcroFields().setField("Attn", analyser.getAttention());
			pdfStamper.getAcroFields().setField("PaymentTerms", analyser.getPaymentTerms());
			pdfStamper.getAcroFields().setField("DistributionMethod", analyser.getDistributionMethod());
			pdfStamper.getAcroFields().setField("SpecialNotes", analyser.getSpecialNotes());
			pdfStamper.getAcroFields().setField("InvoiceEmail", analyser.getClientEmail());
			pdfStamper.getAcroFields().setField("InvoiceFrequency", analyser.getInvoiceFrequency());
			pdfStamper.getAcroFields().setField("InvoiceBillingType", analyser.getBillingType());
			pdfStamper.getAcroFields().setField("InvoiceDeliveryType", analyser.getDeliveryType());
			pdfStamper.getAcroFields().setField("PracticeArea", analyser.getPracticeArea());

			// CLIENT INFORMATION - MANAGER CONTACT

			pdfStamper.getAcroFields().setField("WorkSite", analyser.getSiteLocation());
			pdfStamper.getAcroFields().setField("ManagerPhone", analyser.getManagerPhone());
			pdfStamper.getAcroFields().setField("ClienManager", analyser.getClientManagerName());
			pdfStamper.getAcroFields().setField("ClientManagerEmail", analyser.getManagerEmail());
			pdfStamper.getAcroFields().setField("Termination", analyser.getTermDate());
			pdfStamper.getAcroFields().setField("FalseTermination", analyser.getFalseTermination());
			pdfStamper.getAcroFields().setField("Reason", analyser.getReason());
			pdfStamper.getAcroFields().setField("BackgroundCheckAmount", "$" +
					analyser.getBackgroundCheckAmount().toString());
			pdfStamper.getAcroFields().setField("ClientParentId", analyser.getParentId());
			pdfStamper.getAcroFields().setField("ClientAnalyzerId", analyser.getAnalyserId());
			pdfStamper.getAcroFields().setField("ClientAddressId", analyser.getClientAddressId().toString());
			pdfStamper.getAcroFields().setField("SiteId", analyser.getClientSiteId().toString());



			// To check PDF checkbox values of checked and un-checked
			// String[] values = pdfStamper.getAcroFields().getAppearanceStates("chkRef");
			// StringBuffer sb = new StringBuffer();
			// for (String value2 : values) {
			// sb.append(value2);
			// sb.append('\n');
			// }
			// System.out.println( sb.toString());

			pdfStamper.close();
			pdfReader.close();
			// End Template PDF

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("Exception in createPdfStatement = " + e.toString());
			e.printStackTrace();
		}
	}

	public void downloadPdfStatement(String filePath) {
		try {
			System.out.println("Inside ANALYSERBEAN --> downloadPdfStatement.");
			logger.debug("Inside ANALYSERBEAN --> downloadPdfStatement.");
			System.out.println("filePath (NOT USED): " + filePath);
			logger.info("filePath (NOT USED): " + filePath);
			System.out.println("filePath: " + FacesUtils.getTemporaryFilePath() + Util.getPdfFileName(analyser));
			logger.info("filePath: " + FacesUtils.getTemporaryFilePath() + Util.getPdfFileName(analyser));

			//InputStream stream = new FileInputStream(FacesUtils.getTempFilePath() + Util.getPdfFileName(analyser));
			InputStream stream = new FileInputStream(FacesUtils.getTemporaryFilePath() + Util.getPdfFileName(analyser));
			generatedPdfFile = new DefaultStreamedContent(stream, "application/pdf", Util.getPdfFileName(analyser));
			System.out.println("Bean Attribute generatedPdfFile : " + generatedPdfFile);
			logger.info("Bean Attribute generatedPdfFile : " + generatedPdfFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args)
	{
		String maskedSsn = "123-45-6789";
		String res = "***-**-"+maskedSsn.substring(7,maskedSsn.length());
		System.out.println("REs is : "+res);
		maskedSsn = "1111-6789";
		if(maskedSsn.contains("-")){
			maskedSsn = maskedSsn.replaceAll("-", "");
			System.out.println("Replaced ssn is : "+maskedSsn);
		}
		final Pattern ssnFormat = Pattern.compile("^(\\d{3})(\\d{2})(\\d{4})$");

		Matcher m = ssnFormat.matcher(maskedSsn);  // make ssn a string!
		if (m.find()) {
			System.out.printf("%s-%s-%s%n", m.group(1), m.group(2), m.group(3));
			//return m.group(1)+"-"+m.group(2)+"-"+m.group(3); 
		} else {
			System.err.println("Enter a valid social security number!");
			//return "-1";
		}

		//System.out.println(obj.fixSsn(maskedSsn));
	}

	/**
	 * @return the holidays
	 */
	public List<SelectItem> getHolidays() {
		if(holidays == null) {
			holidays = new ArrayList<SelectItem>();
			holidays.add(new SelectItem("0.0", "0"));
			holidays.add(new SelectItem("5.0", "5"));
			holidays.add(new SelectItem("6.0", "6"));
			holidays.add(new SelectItem("7.0", "7"));
			holidays.add(new SelectItem("8.0", "8"));
			holidays.add(new SelectItem("9.0", "9"));
			holidays.add(new SelectItem("10.0", "10"));
			holidays.add(new SelectItem("11.0", "11"));
			holidays.add(new SelectItem("12.0", "12"));
			//01/14/2020 Tayyab
			holidays.add(new SelectItem("13.0", "13"));
			holidays.add(new SelectItem("14.0", "14"));
			holidays.add(new SelectItem("15.0", "15"));
			holidays.add(new SelectItem("16.0", "16"));
		}
		return holidays;
	}

	/**
	 * @param holidays the holidays to set
	 */
	public void setHolidays(List<SelectItem> holidays) {
		this.holidays = holidays;
	}

	private void updatePortfolio ()
	{
		if (
				(analyser.getPortfolio() == null || analyser.getPortfolio().equalsIgnoreCase("null") || analyser.getPortfolio().equalsIgnoreCase(""))
				
				/* Added as part of Portfolio Project story no: STRY0017721 */
				/*&&
				(analyser.getCompanyCode().equalsIgnoreCase(Constants.DEFAULT_COMPANY_CODE))	*///Do Not Call for Signature
				)
		{
			System.out.println("In AnalyserBean --> updatePortfolio --> Portfolio value is missiing or invalid");
			logger.debug("In AnalyserBean --> setPortfolioValue --> Portfolio value is missiing or invalid");
			setPortfolioValue();
		}
		else
		{
			System.out.println("In AnalyserBean --> updatePortfolio --> Portfolio value = "+analyser.getPortfolio());
			logger.debug("In AnalyserBean --> setPortfolioValue --> Portfolio value = "+analyser.getPortfolio());
			System.out.println("In AnalyserBean --> updatePortfolio --> Company Code = "+analyser.getCompanyCode());
			logger.debug("In AnalyserBean --> updatePortfolio --> Company Code = "+analyser.getCompanyCode());
			System.out.println("Portfolio Service NOT called.");
			logger.debug("Portfolio Service NOT called.");
		}
	}

	private void setPortfolioValue ()
	{
		AnalyserClientDatabaseDTO analyserClientForPortfolio = new AnalyserClientDatabaseDTO();
		PortfolioDownstreamRequestDTO requestDTO = new PortfolioDownstreamRequestDTO();
		String managingDirectorEmplId = NULL;
		String geoLocationCode = NULL;
		String ae1EmplId = NULL;
		String rec1EmplId = NULL;

		/* Added as part of Portfolio Project story no: STRY0017721 */
		try
		{
			analyserClientForPortfolio = analyserClientService.getClientDatabaseInfo(analyser.getClientId());
	
			System.out.println("Loaded Client Info is --> "+analyserClientForPortfolio);
			logger.debug("Loaded Client Info is --> "+analyserClientForPortfolio);
			
			if (analyserClientForPortfolio != null)
			{
				requestDTO.setProductCode(analyserClientForPortfolio.getpSProductCode());
				requestDTO.setVerticalCode( analyserClientForPortfolio.getpSVerticalCode());
				requestDTO.setCustomerId(analyserClientForPortfolio.getpSClientId());
			}
			else
			{
				System.out.println("analyserClientForPortfolio is NULL");
				logger.debug("analyserClientForPortfolio is NULL");
			}			
			managingDirectorEmplId = analyserService.getGenericDescriptionGeneral("MANAGINGDIRECTOREMPLID", analyser.getManagingDirector());
			requestDTO.setMdEmplId(managingDirectorEmplId);
			ae1EmplId = analyserService.getGenericDescriptionGeneral("MANAGINGDIRECTOREMPLID", analyser.getDealPortfolio1AE1());
			requestDTO.setAe1EmplId(ae1EmplId);
			rec1EmplId = analyserService.getGenericDescriptionGeneral("MANAGINGDIRECTOREMPLID", analyser.getDealPortfolio2REC1());
			requestDTO.setRec1EmplId(rec1EmplId);
			geoLocationCode = analyserService.getGenericDescriptionGeneral("PSREGIONCODE", analyser.getBranch());
			requestDTO.setGeoLocationCode(geoLocationCode);
			requestDTO.setRecruitingTeam( analyser.getRecruitingClassification());
			requestDTO.setDealType(analyser.getDealType());
	

			System.out.println("Calling portfolio Downstream service with the input details--> "+requestDTO);
			logger.debug("Calling portfolio Downstream service with the input details--> "+requestDTO);
	
			PortfolioDownstreamResponseDTO portfolioDownstreamResponseDTO = FacesUtils.getPortfolioFromService(requestDTO);
			analyserService.auditPortofolioWSCall(analyser, requestDTO, portfolioDownstreamResponseDTO);
	        parsePortflioJsonResponse(analyser, portfolioDownstreamResponseDTO);
			}	
			catch (Exception e)
			{
				System.out.println("Exception In AnalyserBean --> setPortfolioValue " + e.getMessage());
				logger.debug("Exception In AnalyserBean --> setPortfolioValue " + e.getMessage());
			}
	}

	public String analyserCommissionModelDescription(String commModel){

		String modelDescription = "";
		if(commModel != null && !commModel.equalsIgnoreCase("")){
			if(commModel.equalsIgnoreCase("F&A")){
				modelDescription = "Non-IT Model";
			}if(commModel.equalsIgnoreCase("IRC")){
				modelDescription = "Centralized";
			}if(commModel.equalsIgnoreCase("ITA")){
				modelDescription = "IT-A Model";
			}if(commModel.equalsIgnoreCase("ITB")){
				modelDescription = "IT-B Model";
			}if(commModel.equalsIgnoreCase("ITC")){
				modelDescription = "IT-C Model";
			}if(commModel.equalsIgnoreCase("PRM")){
				modelDescription = "PERM Model";
			}
		}
		return modelDescription;
	}


	/*
	 * Added as part of Tax and Sick Leave project
	 */
	public void getSickLeaveDetails(AnalyserDTO analyser) {
		if(analyser.getClientSiteId() > 0  &&  StringUtils.isNotEmpty(analyser.getZip())) {
			SickLeaveCost sickLeaveCost = sickLeaveCostService.spGetSickLeaveDetails(FacesUtils.getCurrentUserId(), analyser.getClientSiteId(), analyser.getZip(),
					analyser.getWorkFromSource(), analyser.getMajorityWorkPerformed());

			analyser.setpTOLimitToOverrideSick(sickLeaveCost.getPtoLimitToOverrideSick());
			analyser.setSickLeaveCap(sickLeaveCost.getAnnualCap().toString());
			analyser.setSickLeavePerHourRate(sickLeaveCost.getSickHoursCost());
			analyser.setSickLeaveSource(sickLeaveCost.getSickLeaveSource());
		}

	}

	public void onWorkSourceChange(AjaxBehaviorEvent e) {
		String val = (String)((UIInput) e.getSource()).getValue();
		if(StringUtils.isNotEmpty(val)) {
			getSickLeaveDetails(analyser);
		}
	}


	/* Added as part of Portfolio Project story no: STRY0017721 */
	public void parsePortflioJsonResponse(AnalyserDTO analyser, PortfolioDownstreamResponseDTO portfolioDownstreamResponseDTO) {
		
		JSONArray jsonArray = new JSONArray(portfolioDownstreamResponseDTO.getRawResponse());
		JSONObject myResponse = jsonArray.getJSONObject(0);
		if(!myResponse.has("AE1Portfolio")) {
			myResponse.put("AE1Portfolio", FacesUtils.getDefaultPortfolio());
		}
		if(!myResponse.has("Rec1Portfolio")) {
			myResponse.put("Rec1Portfolio", FacesUtils.getDefaultPortfolio());
		}

		analyser.setPortfolio((String) myResponse.get("Portfolio"));
		analyser.setDealPortfolio1AE1((String) myResponse.get("AE1Portfolio"));
		analyser.setDealPortfolio2REC1((String) myResponse.get("Rec1Portfolio"));
		System.out.println("Portfolio Response = "+portfolioDownstreamResponseDTO.getRawResponse());
		logger.debug("Portfolio Response = "+ portfolioDownstreamResponseDTO.getRawResponse());
		System.out.println("Portfolio Description is set to blank string.");
		logger.debug("Portfolio Description is set to blank string.");
	}
}