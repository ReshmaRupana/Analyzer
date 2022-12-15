/**
 * 
 */
package com.disys.analyzer.jsf.bean;

import static java.util.Map.Entry.comparingByKey;
import static java.util.stream.Collectors.toMap;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.builder.Diff;
import org.apache.commons.lang3.builder.DiffResult;
import org.primefaces.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import org.springframework.web.jsf.FacesContextUtils;

import com.disys.analyzer.config.util.FacesUtils;
import com.disys.analyzer.model.dto.AnalyserDTO;
import com.disys.analyzer.service.AnalyserService;
import com.disys.analyzer.service.ApplicationUserService;

/**
 * @author Sajid
 * @since Nov 19, 2018
 */
@ManagedBean
@ViewScoped
public class AnalyserHistoryBean extends SpringBeanAutowiringSupport implements Serializable
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -454734875513483238L;
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired private AnalyserService service;
	
	@Autowired private ApplicationUserService applicationUserService;
	
	private AnalyserDTO selectedAnalyser;
	
	private Map<String, List<String>> difference;
	
	private List<AnalyserDTO> list;
	
	private boolean showApprovalButton;
	private String analyserId;
	private String parentId;
	private String rejectReason;
	
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
	
	public AnalyserHistoryBean()
	{
		Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		analyserId = (String) params.get("analyserId");
		parentId = (String) params.get("parentId");
		this.rejectReason = "";
		selectedAnalyser = new AnalyserDTO();
		list = service.getAnalyserHistory(FacesUtils.getCurrentUserId(), analyserId, "null", "ALL");
		difference = new LinkedHashMap<String, List<String>>();
		AnalyserDTO currentAnalyser;
		AnalyserDTO oldAnalyser;
		if (list.size() > 1)
		{
			oldAnalyser = list.get(0);
			currentAnalyser = list.get(1);
			
			//01/31/2019 Tayyab 
			//Make sure if AnalyzerId for oldAnalyser is greater than AnalyzerId for currentAnalyser
			//Then swap objects to show the latest as current
			Integer oldAnalyzerId = Integer.parseInt(oldAnalyser.getAnalyserId());
			Integer currentAnalyzerId = Integer.parseInt(currentAnalyser.getAnalyserId());
			System.out.println("Analyzer Id for object oldAnalyser = "+oldAnalyzerId.toString());
			logger.debug("Analyzer Id for object oldAnalyser = "+oldAnalyzerId.toString());
			System.out.println("Analyzer Id for object currentAnalyser = "+currentAnalyzerId.toString());
			logger.debug("Analyzer Id for object currentAnalyser = "+currentAnalyzerId.toString());
			if (oldAnalyzerId > currentAnalyzerId)
			{
				oldAnalyser = list.get(1);
				currentAnalyser = list.get(0);
			}
			
			List<String> valueDifference = null;
			
			selectedAnalyser = currentAnalyser;
			
			DiffResult diff = oldAnalyser.diff(currentAnalyser);
			for (Diff<?> d : diff.getDiffs())
			{
				valueDifference = new ArrayList<String>();
				try
				{
					valueDifference.add(d.getLeft().toString());
				}
				catch (NullPointerException ne)
				{
					valueDifference.add("");
				}
				try
				{
					valueDifference.add(d.getRight().toString());
				}
				catch (NullPointerException ne)
				{
					valueDifference.add("");
				}
				difference.put(d.getFieldName(), valueDifference);
				System.out.println(d.getFieldName() + "= FROM[" + d.getLeft() + "] TO [" + d.getRight() + "]");
				logger.debug(d.getFieldName() + "= FROM[" + d.getLeft() + "] TO [" + d.getRight() + "]");
			}
			
			// Sajid - 16012019 to make sure that 'Gross Profit Percentage' and 'Client Name' are always part of the approve view history, even if 
			// there is no change in their values
			if(!difference.containsKey("Gross Profit Percentage")){
				valueDifference = new ArrayList<String>();
				valueDifference.add(oldAnalyser.getGrossProfitPercentage().toString());
				valueDifference.add(currentAnalyser.getGrossProfitPercentage().toString());
				
				difference.put("Gross Profit Percentage", valueDifference);
			}
			
			if(!difference.containsKey("Client Name")){
				valueDifference = new ArrayList<String>();
				valueDifference.add(oldAnalyser.getClientCompany());
				valueDifference.add(currentAnalyser.getClientCompany());
				
				difference.put("Client Name", valueDifference);
			}
		}
		else
		{
			
			oldAnalyser = list.get(0);
			currentAnalyser = new AnalyserDTO();
			
			selectedAnalyser = oldAnalyser;
			
			List<String> valueDifference = null;
			
			DiffResult diff = oldAnalyser.diff(currentAnalyser);
			for (Diff<?> d : diff.getDiffs())
			{
				valueDifference = new ArrayList<String>();
				try
				{
					valueDifference.add(d.getLeft().toString());
				}
				catch (NullPointerException ne)
				{
					valueDifference.add("");
				}
				// valueDifference.add(d.getRight().toString());
				valueDifference.add("");
				difference.put(d.getFieldName(), valueDifference);
				System.out.println(d.getFieldName() + "= FROM[" + d.getLeft() + "] TO [" + d.getRight() + "]");
				logger.debug(d.getFieldName() + "= FROM[" + d.getLeft() + "] TO [" + d.getRight() + "]");
			}
		}
		
		// let's sort this map by values first
		difference = difference
		        .entrySet()
		        .stream()
		        .sorted(comparingByKey())
		        .collect(
		            toMap(e -> e.getKey(), e -> e.getValue(), (e1, e2) -> e2,
		            		LinkedHashMap::new));

		
		System.out.println("{call " + FacesUtils.SCHEMA_WIRELESS + ".spGetAnalyserHistory('" + FacesUtils.getCurrentUserId() + "','" + analyserId
		        + "','null','ALL')}");
		
		logger.debug("History found : " + selectedAnalyser.getAnalyserId());
		logger.debug("{call " + FacesUtils.SCHEMA_WIRELESS + ".spGetAnalyserHistory('" + FacesUtils.getCurrentUserId() + "','" + analyserId
		        + "','null','ALL')}");
		
		Integer roleId = applicationUserService.getRoleId(FacesUtils.getCurrentUserId());
		
		if (!selectedAnalyser.getRecordStatus().equals("3") && (roleId == 2 || roleId == 3 || roleId == 4))
		{
			showApprovalButton = true;
		}
		else
		{
			showApprovalButton = false;
		}
		// <% if (!rStatus.equalsIgnoreCase("Approved") && (rolid.equals("2") ||
		// rolid.equals("3") || rolid.equals("4")) )
	}
	
	public void approveAnalyser()
	{
		logger.debug("Approving analyser...");
		String result = service.approveAnalyser(Integer.valueOf(selectedAnalyser.getAnalyserId()), FacesUtils.getCurrentUserId());
		String title = "Success";
		String detail = "";
		// Severity severity = FacesMessage.SEVERITY_INFO;
		FacesMessage message = null;
		if (result.equals("0"))
		{
			detail = "Approval was successful";
			// severity = FacesMessage.SEVERITY_INFO;
			message = new FacesMessage(FacesMessage.SEVERITY_INFO, title, detail);
		}
		else if (result.equals("1"))
		{
			// Already modified by someone else when the user was viewing the
			// analyser
//			detail = "Analyser already modified by someone else during your operation";
			detail = "Operation performed successfully.";
			// severity = FacesMessage.SEVERITY_INFO;
			message = new FacesMessage(FacesMessage.SEVERITY_INFO, title, detail);
		}
		else if (result.equals("2"))
		{
			// The user does not have the permission
			detail = "You do not have the required permission";
			// severity = FacesMessage.SEVERITY_WARN;
			message = new FacesMessage(FacesMessage.SEVERITY_WARN, title, detail);
		}
		else if (result.equals("3"))
		{
			// Already Approved
			detail = "Analyser was already approved";
			// severity = FacesMessage.SEVERITY_ERROR;
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, title, detail);
		}
		
		HttpServletRequest request = FacesUtils.getRequestObject();
		HttpSession session = request.getSession();
		session.setAttribute("message", message);
		
		approverAnalyserFromDialog(selectedAnalyser);
		
		// FacesUtils.redirect("/protected/analyser-list.xhtml", message);
	}
	
	public void approverAnalyserFromDialog(AnalyserDTO analyser)
	{
		RequestContext.getCurrentInstance().closeDialog(analyser);
	}
	
	public void rejectAnalyser()
	{
		try
		{
			FacesMessage message = null;
			if(this.rejectReason == null || this.rejectReason.trim().length() == 0 || this.rejectReason.trim().equalsIgnoreCase(""))
			{
				message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error", "Please enter comments to reject analyzer");
				FacesContext.getCurrentInstance().addMessage(null, message);
				return;
			}
			String result = service.rejectAnalyser(FacesUtils.getCurrentUserId(), Integer.parseInt(analyserId),
			        Integer.parseInt(parentId), rejectReason);
			String title = "Success";
			String detail = "";
			if (result.equals("1"))
			{
				detail = "Successfully rejected analyzer";
				message = new FacesMessage(FacesMessage.SEVERITY_INFO, title, detail);
			}
			else
			{
				detail = "Faild to reject analyzer";
				message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Failure", detail);
			}
			HttpServletRequest request = FacesUtils.getRequestObject();
			HttpSession session = request.getSession();
			session.setAttribute("message", message);
			approverAnalyserFromDialog(selectedAnalyser);
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
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
	
	/**
	 * @return the difference
	 */
	public Map<String, List<String>> getDifference()
	{
		return difference;
	}
	
	/**
	 * @param difference
	 *            the difference to set
	 */
	public void setDifference(Map<String, List<String>> difference)
	{
		this.difference = difference;
	}
	
	/**
	 * @return the list
	 */
	public List<AnalyserDTO> getList()
	{
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
	 * @return the showApprovalButton
	 */
	public boolean isShowApprovalButton()
	{
		return showApprovalButton;
	}
	
	/**
	 * @param showApprovalButton
	 *            the showApprovalButton to set
	 */
	public void setShowApprovalButton(boolean showApprovalButton)
	{
		this.showApprovalButton = showApprovalButton;
	}

	public String getRejectReason()
	{
		return rejectReason;
	}

	public void setRejectReason(String rejectReason)
	{
		this.rejectReason = rejectReason;
	}
}
