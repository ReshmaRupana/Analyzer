/**
 * 
 */
package com.disys.analyzer.jsf.bean;

import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import org.springframework.web.jsf.FacesContextUtils;

import com.disys.analyzer.config.util.FacesUtils;
import com.disys.analyzer.model.Analyser;
import com.disys.analyzer.model.Resource;
import com.disys.analyzer.model.Title;
import com.disys.analyzer.model.User;
import com.disys.analyzer.service.AnalyserService;
import com.disys.analyzer.service.ApplicationUserService;
import com.disys.analyzer.service.ResourceService;
import com.disys.analyzer.service.TitleService;

/**
 * @author Tayyab
 * 
 */
@ManagedBean
@RequestScoped
public class TitleBean extends SpringBeanAutowiringSupport implements Serializable
{
	
	/**
	 * 
	 */
	private Logger logger = LoggerFactory.getLogger(TitleBean.class);
	private static final long serialVersionUID = -1766779855535933830L;
	
	@Autowired private TitleService titleService;
	@Autowired private ResourceService resourceService;
	@Autowired private ApplicationUserService applicationUserService;
	
	@Autowired private AnalyserService service;
	
	private String pageTitle;
	
	private Integer roleId;
	
	/*
	 * 
	 * Main headline links
	 */
	private boolean userManagementMenu;
	private boolean analyserMenu;
	private boolean reportsMenu;
	private boolean commissionMenu;
	private boolean setupMenu;
	private boolean analyserCommonMenu;
	private boolean signatureAnalyserMenu;
	/*
	 * User Management links
	 */
	private boolean usersTreeMenu;
	private boolean groupsTreeMenu;
	private boolean rolesTreeMenu;
	private boolean resourcesTreeMenu;
	
	/*
	 * Report links
	 */
	private boolean revenueTreeMenu;
	private boolean consultantsTreeMenu;
	private boolean commissionTreeMenu;
	private boolean othersTreeMenu;
	private boolean activityTreeMenu;
	
	private Integer menuNumber;
	
	private Boolean displayAnalyserMenu;
	private Boolean displayReportsMenu;
	private Boolean displayCommissionManagementMenu;
	private Boolean displayUserManagementMenu;
	private Boolean displaySetupMenu;
	private Boolean displayAnalyserCommonMenu;
	private Boolean displaySignatureAnalyserMenu;
	
	private Boolean isUserLoggedIn;
	private Integer rejectedCount;
	
	private List<Title> analyserMenuList;
	private List<Title> reportsMenuList;
	private List<Title> commissionMenuList;
	private List<Title> userManageMenuList;
	private List<Title> setupMenuList;
	
	private List<Title> reportActivityMenuList;
	private List<Title> reportRevenueMenuList;
	private List<Title> reportConsultantMenuList;
	private List<Title> reportCommissionMenuList;
	private List<Title> reportOthersMenuList;
	private List<Title> usersMenuList;
	private List<Title> groupsMenuList;
	private List<Title> roleManageMenuList;
	private List<Title> analyserCommonMenuList;
	private List<Title> signatureAnalyserMenuList;
	@PostConstruct
	public void init()
	{
		try
		{
			FacesContextUtils.getRequiredWebApplicationContext(FacesContext.getCurrentInstance()).getAutowireCapableBeanFactory().autowireBean(this);
		}
		catch (BeansException e)
		{
			e.printStackTrace();
		}
		catch (IllegalStateException e)
		{
			e.printStackTrace();
		}
	}
	
	public TitleBean()
	{
		roleId = -1;
		menuNumber = 1;
		displayAnalyserMenu = true;
		displayReportsMenu = false;
		displayCommissionManagementMenu = false;
		displayUserManagementMenu = false;
		displaySetupMenu = false;
		displayAnalyserCommonMenu = true;
		displaySignatureAnalyserMenu = false;
		rejectedCount = 0;
		analyserMenuList = new ArrayList<Title>(0);
		reportsMenuList = new ArrayList<Title>(0);
		commissionMenuList = new ArrayList<Title>(0);
		userManageMenuList = new ArrayList<Title>(0);
		setupMenuList = new ArrayList<Title>(0);
		
		reportActivityMenuList = new ArrayList<Title>(0);
		reportRevenueMenuList = new ArrayList<Title>(0);
		reportConsultantMenuList = new ArrayList<Title>(0);
		reportCommissionMenuList = new ArrayList<Title>(0);
		reportOthersMenuList = new ArrayList<Title>(0);
		usersMenuList = new ArrayList<Title>(0);
		groupsMenuList = new ArrayList<Title>(0);
		roleManageMenuList = new ArrayList<Title>(0);
		analyserCommonMenuList = new ArrayList<Title>(0);
		signatureAnalyserMenuList = new ArrayList<Title>(0);
		LocalDateTime currentTime = LocalDateTime.now();
		
		HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		
		/*
		 * webRootPath =
		 * FacesContext.getCurrentInstance().getExternalContext().getContextName
		 * (); System.out.println("Web root path is  : "+webRootPath);
		 */
		
		String url = req.getRequestURL().toString();
		// pageTitle = url.substring(0, url.length() -
		// req.getRequestURI().length()) + req.getContextPath() + "/";
		pageTitle = url.substring(url.lastIndexOf('/') + 1);
		System.out.println(currentTime + " Requested URL is : " + pageTitle);
		// getting page title from the properties file.
		Title title = titleService.findByResourceName(pageTitle);
		if (title != null)
		{
			pageTitle = title.getTitle();
		}
		else
		{
			pageTitle = "Not Defined";
		}
		
		Boolean canAccessThisPage = false;
		
		if(url.contains("/public/forgot-password.xhtml")){
			canAccessThisPage = true;
			pageTitle = "Forgot Password";
		}
		
		isUserLoggedIn = !url.contains("/public/login.xhtml");
		
		
		if (isUserLoggedIn)
		{
			User user = applicationUserService.findByUsername(FacesUtils.getCurrentUserId());
			if (user != null && user.getUserId() != null && user.getRoleId() != null)
			{
				/*@@
				 * Check why this is not being displayed in the menu link through the Side-Navigation xhtml file
				 * For now just hard-coding the value to zero
				 * Also need to figure out a mechanism to pass the company code to this method. 
				 */
				/*
				List<Analyser> analyserList = service.getRejectedAnalyserList(FacesUtils.getCurrentUserId(), "null", "ALL");
				if (analyserList != null)
				{
					rejectedCount = analyserList.size();
				}
				*/
				rejectedCount = 0;
				
				roleId = user.getRoleId();
				System.out.println("Role Id is : " + roleId);
				HttpSession session = req.getSession();
				if (session.getAttribute("roleId") == null)
				{
					System.out.println(" and is set in the session");
					session.setAttribute("roleId", roleId);
				}
				
				List<Resource> userResources = resourceService.spGetLoginUserRolePrivileges(user.getUserId(), user.getRoleId());
				if (userResources != null && !userResources.isEmpty())
				{
					for (Resource resource : userResources)
					{
//						logger.debug("Resource ID :: " + resource.getResId() + " :: Desc :: " + resource.getResDesc() + " :: First Parent :: "
//						        + resource.getFirstParent() + " :: Second Parent :: " + resource.getSecondParent());
						if (resource.getResDesc().trim().equalsIgnoreCase("View Analyser")
						        || resource.getFirstParent().trim().equalsIgnoreCase("View Analyser")
						        || resource.getSecondParent().trim().equalsIgnoreCase("View Analyser"))
						{
							if (resource.getPageName() != null && resource.getPageName().trim().length() > 0 && resource.getMenuItem() == 1)
							{	
								analyserMenuList.add(new Title(resource.getResDesc(), resource.getPageName().trim()));
								//System.out.println("analyserMenuList Line 234 TitleBean=========================================== " +resource.getPageName().trim()+" "+resource.getResDesc());								
							}													
						}
						if (resource.getResDesc().trim().equalsIgnoreCase("Signature Analyzer")
						        || resource.getFirstParent().trim().equalsIgnoreCase("Signature Analyzer")
						        || resource.getSecondParent().trim().equalsIgnoreCase("Signature Analyzer"))
						{
							if (resource.getPageName() != null && resource.getPageName().trim().length() > 0 && resource.getMenuItem() == 1)
							{	
								displaySignatureAnalyserMenu=true;
								signatureAnalyserMenuList.add(new Title(resource.getResDesc(), resource.getPageName().trim()));
								//System.out.println("signaturAanalyserMenuList line 245 TitleBean=========================================== " +resource.getPageName().trim()+" "+resource.getResDesc());								
							}														
						}
						if (resource.getResDesc().trim().equalsIgnoreCase("Analyser Common")
						        || resource.getFirstParent().trim().equalsIgnoreCase("Analyzer Common")
						        || resource.getSecondParent().trim().equalsIgnoreCase("Analyzer Common"))
						{
							if (resource.getPageName() != null && resource.getPageName().trim().length() > 0 && resource.getMenuItem() == 1)
							{		
									analyserCommonMenuList.add(new Title(resource.getResDesc(), resource.getPageName().trim()));
									//System.out.println("analyserCommonMenuList line 257 TitleBean=========================================== " +resource.getPageName().trim()+" "+resource.getResDesc());								
							}
						}
						if (resource.getResDesc().trim().equalsIgnoreCase("View User")
						        || resource.getFirstParent().trim().equalsIgnoreCase("View User")
						        || resource.getSecondParent().trim().equalsIgnoreCase("View User"))
						{
							displayUserManagementMenu = true;
							if (resource.getPageName() != null && resource.getPageName().trim().length() > 0 && resource.getMenuItem() == 1)
							{
								if(resource.getFirstParent().equalsIgnoreCase("View User"))
								{
									userManageMenuList.add(new Title(resource.getResDesc(), resource.getPageName().trim()));
								}
								else if (resource.getFirstParent().equalsIgnoreCase("Users")) 
								{
									if(usersMenuList == null || usersMenuList.isEmpty())
									{
										userManageMenuList.add(new Title(resource.getFirstParent(), resource.getPageName().trim()));
									}
									usersMenuList.add(new Title(resource.getResDesc(), resource.getPageName().trim()));
								}
								else if (resource.getFirstParent().equalsIgnoreCase("Groups")) 
								{
									if(groupsMenuList == null || groupsMenuList.isEmpty())
									{
										userManageMenuList.add(new Title(resource.getFirstParent(), resource.getPageName().trim()));
									}
									groupsMenuList.add(new Title(resource.getResDesc(), resource.getPageName().trim()));
								}
								else if (resource.getFirstParent().equalsIgnoreCase("Roles Management")) 
								{
									if(roleManageMenuList == null || roleManageMenuList.isEmpty())
									{
										userManageMenuList.add(new Title(resource.getFirstParent(), resource.getPageName().trim()));
									}
									roleManageMenuList.add(new Title(resource.getResDesc(), resource.getPageName().trim()));
								}
							}
						}
						if (resource.getResDesc().trim().equalsIgnoreCase("View Reports")
						        || resource.getFirstParent().trim().equalsIgnoreCase("View Reports")
						        || resource.getSecondParent().trim().equalsIgnoreCase("View Reports"))
						{
							displayReportsMenu = true;
							if (resource.getPageName() != null && resource.getPageName().trim().length() > 0 && resource.getMenuItem() == 1)
							{
								if(resource.getFirstParent().equalsIgnoreCase("View Reports"))
								{
									reportsMenuList.add(new Title(resource.getResDesc(), resource.getPageName().trim()));
								}
								else if(resource.getFirstParent().equalsIgnoreCase("Activity"))
								{
									if(reportActivityMenuList == null || reportActivityMenuList.isEmpty())
									{
										reportsMenuList.add(new Title(resource.getFirstParent(), resource.getPageName().trim()));
									}
									reportActivityMenuList.add(new Title(resource.getResDesc(), "reports/"+resource.getPageName().trim()));
								}
								else if(resource.getFirstParent().equalsIgnoreCase("Revenue"))
								{
									if(reportRevenueMenuList == null || reportRevenueMenuList.isEmpty())
									{
										reportsMenuList.add(new Title(resource.getFirstParent(), resource.getPageName().trim()));
									}
									reportRevenueMenuList.add(new Title(resource.getResDesc(), "reports/"+resource.getPageName().trim()));
								}
								else if(resource.getFirstParent().equalsIgnoreCase("Consultants"))
								{
									if(reportConsultantMenuList == null || reportConsultantMenuList.isEmpty())
									{
										reportsMenuList.add(new Title(resource.getFirstParent(), resource.getPageName().trim()));
									}
									reportConsultantMenuList.add(new Title(resource.getResDesc(), "reports/"+resource.getPageName().trim()));
								}
								else if(resource.getFirstParent().equalsIgnoreCase("Commission"))
								{
									if(reportCommissionMenuList == null || reportCommissionMenuList.isEmpty())
									{
										reportsMenuList.add(new Title(resource.getFirstParent(), resource.getPageName().trim()));
									}
									reportCommissionMenuList.add(new Title(resource.getResDesc(), "reports/"+resource.getPageName().trim()));
								}
								else if(resource.getFirstParent().equalsIgnoreCase("Others"))
								{
									if(reportOthersMenuList == null || reportOthersMenuList.isEmpty())
									{
										reportsMenuList.add(new Title(resource.getFirstParent(), resource.getPageName().trim()));
									}
									reportOthersMenuList.add(new Title(resource.getResDesc(), "reports/"+resource.getPageName().trim()));
								}
							}
						}
						if (resource.getResDesc().trim().equalsIgnoreCase("View Analyser Setup")
						        || resource.getFirstParent().trim().equalsIgnoreCase("View Analyser Setup")
						        || resource.getSecondParent().trim().equalsIgnoreCase("View Analyser Setup"))
						{
							displaySetupMenu = true;
							if (resource.getPageName() != null && resource.getPageName().trim().length() > 0 && resource.getMenuItem() == 1)
							{
								setupMenuList.add(new Title(resource.getResDesc(), resource.getPageName().trim()));
							}
						}
						if (resource.getResDesc().trim().equalsIgnoreCase("View Commission")
						        || resource.getFirstParent().trim().equalsIgnoreCase("View Commission")
						        || resource.getSecondParent().trim().equalsIgnoreCase("View Commission"))
						{
							displayCommissionManagementMenu = false;
							if (FacesUtils.getCurrentUserId().trim().equalsIgnoreCase("Mahfuz.Ahmed@DISYS.COM")
							        //|| FacesUtils.getCurrentUserId().trim().equalsIgnoreCase("Firas.Alhindi@DISYS.COM")
							        //|| FacesUtils.getCurrentUserId().trim().equalsIgnoreCase("Maruf@DISYS.COM")
							        //|| FacesUtils.getCurrentUserId().trim().equalsIgnoreCase("Mahfuz@DISYS.COM")
							        //|| FacesUtils.getCurrentUserId().trim().equalsIgnoreCase("Santhosh.Srinivasan@DISYS.COM")
							        //|| FacesUtils.getCurrentUserId().trim().equalsIgnoreCase("Syed.Ali@DISYS.COM")
							        || FacesUtils.getCurrentUserId().trim().equalsIgnoreCase("Muhammad.Ghauri@DISYS.COM")
							        || FacesUtils.getCurrentUserId().trim().equalsIgnoreCase("Andrea.Bell@disys.com")
							        || FacesUtils.getCurrentUserId().trim().equalsIgnoreCase("Amy.Domagala-Parks@DISYS.COM")
							        //|| FacesUtils.getCurrentUserId().trim().equalsIgnoreCase("Tara.Winn@DISYS.COM")
							        || FacesUtils.getCurrentUserId().trim().equalsIgnoreCase("Tara.Holt@DISYS.COM")
							        || FacesUtils.getCurrentUserId().trim().equalsIgnoreCase("Maruf.Ahmed@DISYS.COM")
							        || FacesUtils.getCurrentUserId().trim().equalsIgnoreCase("Tara.Winn@DISYS.COM"))
							{
								displayCommissionManagementMenu = true;
								if (resource.getPageName() != null && resource.getPageName().trim().length() > 0  && resource.getMenuItem() == 1)
								{
									commissionMenuList.add(new Title(resource.getResDesc(), resource.getPageName().trim()));
								}
								
							}
						}
						
						if (title != null && title.getResourceId() != null && (resource.getResId().intValue() == title.getResourceId().intValue()
						        || resource.getParentId().intValue() == title.getResourceId().intValue()
						        || resource.getSecondParentId().intValue() == title.getResourceId().intValue()))
						{
							canAccessThisPage = true;
						}
					}
					// Tayyab - 16012019 - Sort report menu by title
					if(reportsMenuList != null && reportsMenuList.size() > 0){
						Collections.sort(reportsMenuList, Title.COMPARE_BY_TITLE);
					}
					if(reportActivityMenuList != null && reportActivityMenuList.size() > 0){
						Collections.sort(reportActivityMenuList, Title.COMPARE_BY_TITLE);
					}
					
					if(reportCommissionMenuList != null && reportCommissionMenuList.size() > 0){
						Collections.sort(reportCommissionMenuList, Title.COMPARE_BY_TITLE);
					}
					
					if(reportConsultantMenuList != null && reportConsultantMenuList.size() > 0){
						Collections.sort(reportConsultantMenuList, Title.COMPARE_BY_TITLE);
					}
					
					if(reportOthersMenuList != null && reportOthersMenuList.size() > 0){
						Collections.sort(reportOthersMenuList, Title.COMPARE_BY_TITLE);
					}
					
					if(reportRevenueMenuList != null && reportRevenueMenuList.size() > 0){
						Collections.sort(reportRevenueMenuList, Title.COMPARE_BY_TITLE);
					}
				}
			}
			
			// TODO we will do it afterwards
			// canAccessThisPage = true;
			if (!canAccessThisPage)
			{
				HttpServletResponse res = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
				String defaultFailureURL = "protected/403.xhtml";
				String newUrl = req.getRequestURL().toString();
				newUrl = newUrl.substring(0, newUrl.length() - req.getRequestURI().length()) + req.getContextPath();
				
				logger.debug("Redirecting to " + newUrl + "/" + defaultFailureURL);
				System.out.println("Redirecting to " + newUrl + "/" + defaultFailureURL);
				try
				{ // This was the resultant URL, fix it //
				  // http://localhost:8081/Analyzer/protected/analyser-list.xhtml/protected/403.xhtml
					res.sendRedirect(newUrl + "/" + defaultFailureURL);
				}
				catch (IOException e)
				{ // TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}
		
		// pageTitle = TitleUtil.getTitle(pageTitle);
		
		logger.debug(currentTime + "  Requested URL is : " + pageTitle);
		System.out.println(currentTime + "  Corresponding page title is : " + pageTitle);
		if(title != null){
			if (title.getResourceId() != null)
			{
				// 405 View User
				// 406 View Reports
				// 407 View Commission
				// 408 View Analyser
				// 470 View Analyser Setup
				if (title.getResourceId() == 405)
				{
					menuNumber = 1;
					// Menu displayed
					userManagementMenu = true;
					analyserMenu = false;
					reportsMenu = false;
					commissionMenu = false;
					setupMenu = false;
					analyserCommonMenu = false;
					signatureAnalyserMenu = false;
					System.out.println("User Management menu will be active ");
					// now select which sub menu is opened by default
					if (pageTitle.contains("Users") || pageTitle.contains("User"))
					{
						System.out.println("Sub menu will be : " + pageTitle);
						usersTreeMenu = true;
						groupsTreeMenu = false;
						rolesTreeMenu = false;
						resourcesTreeMenu = false;
					}
					else if (pageTitle.contains("Groups") || pageTitle.contains("Group"))
					{
						System.out.println("Sub menu will be : " + pageTitle);
						usersTreeMenu = false;
						groupsTreeMenu = true;
						rolesTreeMenu = false;
						resourcesTreeMenu = false;
					}
					else if (pageTitle.contains("Roles") || pageTitle.contains("Role"))
					{
						System.out.println("Sub menu will be : " + pageTitle);
						usersTreeMenu = false;
						groupsTreeMenu = false;
						rolesTreeMenu = true;
						resourcesTreeMenu = false;
					}
					else if (pageTitle.contains("Resources") || pageTitle.contains("Resource"))
					{
						usersTreeMenu = false;
						groupsTreeMenu = false;
						rolesTreeMenu = false;
						resourcesTreeMenu = true;
					}
				}
				
				else if (title.getResourceId() == 408)
				{
					menuNumber = 2;
					// Menu displayed
					userManagementMenu = false;
					analyserMenu = true;					
					reportsMenu = false;
					commissionMenu = false;
					setupMenu = false;
					analyserCommonMenu = true;
					signatureAnalyserMenu = false;
					System.out.println("Sub menu will be : " + pageTitle);
					
					System.out.println("Analyser tree menu will be active ");
				}
				else if (title.getResourceId() == 493)	//Signature Analyzer
				{
					menuNumber = 6;
					// Menu displayed
					userManagementMenu = false;
					analyserMenu = true;					
					reportsMenu = false;
					commissionMenu = false;
					setupMenu = false;
					analyserCommonMenu = true;
					signatureAnalyserMenu = false;
					System.out.println("493 - Signature Analyzer - Sub menu will be : " + pageTitle);
					
					System.out.println("Analyser tree menu will be active ");
				}
				else if (title.getResourceId() == 492)	//Analyzer Common
				{
					menuNumber = 7;
					// Menu displayed
					userManagementMenu = false;
					analyserMenu = true;					
					reportsMenu = false;
					commissionMenu = false;
					setupMenu = false;
					analyserCommonMenu = true;
					signatureAnalyserMenu = false;
					System.out.println("493 - Analyzer Common - Sub menu will be : " + pageTitle);
					
					System.out.println("Analyser tree menu will be active ");
				}
				else if (title.getResourceId() == 406)
				{
					menuNumber = 3;
					// Menu displayed
					userManagementMenu = false;
					analyserMenu = false;
					reportsMenu = true;
					commissionMenu = false;
					setupMenu = false;
					analyserCommonMenu = false;
					signatureAnalyserMenu = false;
					if (pageTitle.contains("Revenue"))
					{
						System.out.println("Sub menu will be : " + pageTitle);
						revenueTreeMenu = true;
						consultantsTreeMenu = false;
						commissionTreeMenu = false;
						othersTreeMenu = false;
						activityTreeMenu = false;
					}
					else if (pageTitle.contains("Consultants") || pageTitle.contains("Consultant"))
					{
						System.out.println("Sub menu will be : " + pageTitle);
						revenueTreeMenu = false;
						consultantsTreeMenu = true;
						commissionTreeMenu = false;
						othersTreeMenu = false;
						activityTreeMenu = false;
					}
					else if (pageTitle.contains("Commissions") || pageTitle.contains("Commission") || pageTitle.contains("Quota"))
					{
						System.out.println("Sub menu will be : " + pageTitle);
						revenueTreeMenu = false;
						consultantsTreeMenu = false;
						commissionTreeMenu = true;
						othersTreeMenu = false;
						activityTreeMenu = false;
					}
					else if (pageTitle.contains("Activity"))
					{
						System.out.println("Sub menu will be : " + pageTitle);
						revenueTreeMenu = false;
						consultantsTreeMenu = false;
						commissionTreeMenu = false;
						othersTreeMenu = false;
						activityTreeMenu = true;
					}
					else
					{
						revenueTreeMenu = false;
						consultantsTreeMenu = false;
						commissionTreeMenu = false;
						othersTreeMenu = true;
						activityTreeMenu = false;
					}
					System.out.println("Sub menu will be : " + pageTitle);
					System.out.println("Reports tree menu will be active ");
				}
				else if (title.getResourceId() == 407)
				{
					menuNumber = 4;
					commissionMenu = true;
					userManagementMenu = false;
					analyserMenu = false;
					reportsMenu = false;
					setupMenu = false;
					analyserCommonMenu = false;
					signatureAnalyserMenu = false;
				}
				else
				{
					menuNumber = 5;
					setupMenu = true;
					commissionMenu = false;
					userManagementMenu = false;
					analyserMenu = false;
					reportsMenu = false;
					analyserCommonMenu = false;
					signatureAnalyserMenu = false;
				}
			}
		}
	}
	
	/**
	 * @return the pageTitle
	 */
	public String getPageTitle()
	{
		return pageTitle;
	}
	
	/**
	 * @param pageTitle
	 *            the pageTitle to set
	 */
	public void setPageTitle(String pageTitle)
	{
		this.pageTitle = pageTitle;
	}
	
	/**
	 * @return the userManagementMenu
	 */
	public boolean isUserManagementMenu()
	{
		return userManagementMenu;
	}
	
	/**
	 * @param userManagementMenu
	 *            the userManagementMenu to set
	 */
	public void setUserManagementMenu(boolean userManagementMenu)
	{
		this.userManagementMenu = userManagementMenu;
	}
	
	/**
	 * @return the analyserMenu
	 */
	public boolean isAnalyserMenu()
	{
		return analyserMenu;
	}
	
	/**
	 * @param analyserMenu
	 *            the analyserMenu to set
	 */
	public void setAnalyserMenu(boolean analyserMenu)
	{
		this.analyserMenu = analyserMenu;
	}
	
	/**
	 * @return the groupsTreeMenu
	 */
	public boolean isGroupsTreeMenu()
	{
		return groupsTreeMenu;
	}
	
	/**
	 * @param groupsTreeMenu
	 *            the groupsTreeMenu to set
	 */
	public void setGroupsTreeMenu(boolean groupsTreeMenu)
	{
		this.groupsTreeMenu = groupsTreeMenu;
	}
	
	/**
	 * @return the reportsMenu
	 */
	public boolean isReportsMenu()
	{
		return reportsMenu;
	}
	
	/**
	 * @param reportsMenu
	 *            the reportsMenu to set
	 */
	public void setReportsMenu(boolean reportsMenu)
	{
		this.reportsMenu = reportsMenu;
	}
	
	/**
	 * @return the usersTreeMenu
	 */
	public boolean isUsersTreeMenu()
	{
		return usersTreeMenu;
	}
	
	/**
	 * @param usersTreeMenu
	 *            the usersTreeMenu to set
	 */
	public void setUsersTreeMenu(boolean usersTreeMenu)
	{
		this.usersTreeMenu = usersTreeMenu;
	}
	
	/**
	 * @return the rolesTreeMenu
	 */
	public boolean isRolesTreeMenu()
	{
		return rolesTreeMenu;
	}
	
	/**
	 * @param rolesTreeMenu
	 *            the rolesTreeMenu to set
	 */
	public void setRolesTreeMenu(boolean rolesTreeMenu)
	{
		this.rolesTreeMenu = rolesTreeMenu;
	}
	
	/**
	 * @return the resourcesTreeMenu
	 */
	public boolean isResourcesTreeMenu()
	{
		return resourcesTreeMenu;
	}
	
	/**
	 * @param resourcesTreeMenu
	 *            the resourcesTreeMenu to set
	 */
	public void setResourcesTreeMenu(boolean resourcesTreeMenu)
	{
		this.resourcesTreeMenu = resourcesTreeMenu;
	}
	
	/**
	 * @return the revenueTreeMenu
	 */
	public boolean isRevenueTreeMenu()
	{
		return revenueTreeMenu;
	}
	
	/**
	 * @param revenueTreeMenu
	 *            the revenueTreeMenu to set
	 */
	public void setRevenueTreeMenu(boolean revenueTreeMenu)
	{
		this.revenueTreeMenu = revenueTreeMenu;
	}
	
	/**
	 * @return the consultantsTreeMenu
	 */
	public boolean isConsultantsTreeMenu()
	{
		return consultantsTreeMenu;
	}
	
	/**
	 * @param consultantsTreeMenu
	 *            the consultantsTreeMenu to set
	 */
	public void setConsultantsTreeMenu(boolean consultantsTreeMenu)
	{
		this.consultantsTreeMenu = consultantsTreeMenu;
	}
	
	/**
	 * @return the menuNumber
	 */
	public Integer getMenuNumber()
	{
		return menuNumber;
	}
	
	/**
	 * @param menuNumber
	 *            the menuNumber to set
	 */
	public void setMenuNumber(Integer menuNumber)
	{
		this.menuNumber = menuNumber;
	}
	
	/**
	 * @return the commissionTreeMenu
	 */
	public boolean isCommissionTreeMenu()
	{
		return commissionTreeMenu;
	}
	
	/**
	 * @param commissionTreeMenu
	 *            the commissionTreeMenu to set
	 */
	public void setCommissionTreeMenu(boolean commissionTreeMenu)
	{
		this.commissionTreeMenu = commissionTreeMenu;
	}
	
	/**
	 * @return the othersTreeMenu
	 */
	public boolean isOthersTreeMenu()
	{
		return othersTreeMenu;
	}
	
	/**
	 * @param othersTreeMenu
	 *            the othersTreeMenu to set
	 */
	public void setOthersTreeMenu(boolean othersTreeMenu)
	{
		this.othersTreeMenu = othersTreeMenu;
	}
	
	/**
	 * @return the commissionMenu
	 */
	public boolean isCommissionMenu()
	{
		return commissionMenu;
	}
	
	/**
	 * @param commissionMenu
	 *            the commissionMenu to set
	 */
	public void setCommissionMenu(boolean commissionMenu)
	{
		this.commissionMenu = commissionMenu;
	}
	
	public Boolean getDisplayAnalyserMenu()
	{
		return displayAnalyserMenu;
	}
	
	public void setDisplayAnalyserMenu(Boolean displayAnalyserMenu)
	{
		this.displayAnalyserMenu = displayAnalyserMenu;
	}
	
	public Boolean getDisplayReportsMenu()
	{
		return displayReportsMenu;
	}
	
	public void setDisplayReportsMenu(Boolean displayReportsMenu)
	{
		this.displayReportsMenu = displayReportsMenu;
	}
	
	public Boolean getDisplayCommissionManagementMenu()
	{
		return displayCommissionManagementMenu;
	}
	
	public void setDisplayCommissionManagementMenu(Boolean displayCommissionManagementMenu)
	{
		this.displayCommissionManagementMenu = displayCommissionManagementMenu;
	}
	
	public Boolean getDisplayUserManagementMenu()
	{
		return displayUserManagementMenu;
	}
	
	public void setDisplayUserManagementMenu(Boolean displayUserManagementMenu)
	{
		this.displayUserManagementMenu = displayUserManagementMenu;
	}
	
	public Boolean getIsUserLoggedIn()
	{
		return isUserLoggedIn;
	}
	
	public void setIsUserLoggedIn(Boolean isUserLoggedIn)
	{
		this.isUserLoggedIn = isUserLoggedIn;
	}
	
	/**
	 * @return the activityTreeMenu
	 */
	public boolean isActivityTreeMenu()
	{
		return activityTreeMenu;
	}
	
	/**
	 * @param activityTreeMenu
	 *            the activityTreeMenu to set
	 */
	public void setActivityTreeMenu(boolean activityTreeMenu)
	{
		this.activityTreeMenu = activityTreeMenu;
	}
	/**
	 * @return the analyserCommonMenu
	 */
	
	public boolean isAnalyserCommonMenu() {
		return analyserCommonMenu;
	}
	/**
	 * @param analyserCommonMenu
	 *            the analyserCommonMenu to set
	 */
	public void setAnalyserCommonMenu(boolean analyserCommonMenu) {
		this.analyserCommonMenu = analyserCommonMenu;
	}
	/**
	 * @return the signatureAnalyserMenu
	 */
	public boolean isSignatureAnalyserMenu() {
		return signatureAnalyserMenu;
	}
	/**
	 * @param signatureAnalyserMenu
	 *            the signatureAnalyserMenu to set
	 */
	public void setSignatureAnalyserMenu(boolean signatureAnalyserMenu) {
		this.signatureAnalyserMenu = signatureAnalyserMenu;
	}

	/**
	 * @return the roleId
	 */
	public Integer getRoleId()
	{
		return roleId;
	}
	
	/**
	 * @param roleId
	 *            the roleId to set
	 */
	public void setRoleId(Integer roleId)
	{
		this.roleId = roleId;
	}
	
	public Integer getRejectedCount()
	{
		return rejectedCount;
	}
	
	public void setRejectedCount(Integer rejectedCount)
	{
		this.rejectedCount = rejectedCount;
	}
	
	public Boolean getDisplaySetupMenu()
	{
		return displaySetupMenu;
	}
	
	public void setDisplaySetupMenu(Boolean displaySetupMenu)
	{
		this.displaySetupMenu = displaySetupMenu;
	}
	
	public boolean isSetupMenu()
	{
		return setupMenu;
	}
	
	public void setSetupMenu(boolean setupMenu)
	{
		this.setupMenu = setupMenu;
	}
	public Boolean getDisplayAnalyserCommonMenu() {
		return displayAnalyserCommonMenu;
	}

	public void setDisplayAnalyserCommonMenu(Boolean displayAnalyserCommonMenu) {
		this.displayAnalyserCommonMenu = displayAnalyserCommonMenu;
	}

	public Boolean getDisplaySignatureAnalyserMenu() {
		return displaySignatureAnalyserMenu;
	}

	public void setDisplaySignatureAnalyserMenu(Boolean displaySignatureAnalyserMenu) {
		this.displaySignatureAnalyserMenu = displaySignatureAnalyserMenu;
	}

	public List<Title> getAnalyserMenuList()
	{
		return analyserMenuList;
	}
	
	public void setAnalyserMenuList(List<Title> analyserMenuList)
	{
		this.analyserMenuList = analyserMenuList;
	}
	
	public List<Title> getReportsMenuList()
	{
		return reportsMenuList;
	}
	
	public void setReportsMenuList(List<Title> reportsMenuList)
	{
		this.reportsMenuList = reportsMenuList;
	}
	
	public List<Title> getCommissionMenuList()
	{
		return commissionMenuList;
	}
	
	public void setCommissionMenuList(List<Title> commissionMenuList)
	{
		this.commissionMenuList = commissionMenuList;
	}
	
	public List<Title> getSetupMenuList()
	{
		return setupMenuList;
	}
	
	public void setSetupMenuList(List<Title> setupMenuList)
	{
		this.setupMenuList = setupMenuList;
	}

	public List<Title> getUserManageMenuList()
	{
		return userManageMenuList;
	}

	public void setUserManageMenuList(List<Title> userManageMenuList)
	{
		this.userManageMenuList = userManageMenuList;
	}

	public List<Title> getReportActivityMenuList()
	{
		return reportActivityMenuList;
	}

	public void setReportActivityMenuList(List<Title> reportActivityMenuList)
	{
		this.reportActivityMenuList = reportActivityMenuList;
	}

	public List<Title> getReportRevenueMenuList()
	{
		return reportRevenueMenuList;
	}

	public void setReportRevenueMenuList(List<Title> reportRevenueMenuList)
	{
		this.reportRevenueMenuList = reportRevenueMenuList;
	}

	public List<Title> getReportConsultantMenuList()
	{
		return reportConsultantMenuList;
	}

	public void setReportConsultantMenuList(List<Title> reportConsultantMenuList)
	{
		this.reportConsultantMenuList = reportConsultantMenuList;
	}

	public List<Title> getReportCommissionMenuList()
	{
		return reportCommissionMenuList;
	}

	public void setReportCommissionMenuList(List<Title> reportCommissionMenuList)
	{
		this.reportCommissionMenuList = reportCommissionMenuList;
	}

	public List<Title> getReportOthersMenuList()
	{
		return reportOthersMenuList;
	}

	public void setReportOthersMenuList(List<Title> reportOthersMenuList)
	{
		this.reportOthersMenuList = reportOthersMenuList;
	}

	public List<Title> getUsersMenuList()
	{
		return usersMenuList;
	}

	public void setUsersMenuList(List<Title> usersMenuList)
	{
		this.usersMenuList = usersMenuList;
	}

	public List<Title> getGroupsMenuList()
	{
		return groupsMenuList;
	}

	public void setGroupsMenuList(List<Title> groupsMenuList)
	{
		this.groupsMenuList = groupsMenuList;
	}

	public List<Title> getRoleManageMenuList()
	{
		return roleManageMenuList;
	}

	public void setRoleManageMenuList(List<Title> roleManageMenuList)
	{
		this.roleManageMenuList = roleManageMenuList;
	}

	public List<Title> getAnalyserCommonMenuList() {
		return analyserCommonMenuList;
	}

	public void setAnalyserCommonMenuList(List<Title> analyserCommonMenuList) {
		this.analyserCommonMenuList = analyserCommonMenuList;
	}

	public List<Title> getSignatureAnalyserMenuList() {
		return signatureAnalyserMenuList;
	}

	public void setSignatureAnalyserMenuList(List<Title> signatureAnalyserMenuList) {
		this.signatureAnalyserMenuList = signatureAnalyserMenuList;
	}
	
}