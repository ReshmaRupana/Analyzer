package com.disys.analyzer.jsf.bean;

import java.io.Serializable;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.primefaces.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import org.springframework.web.jsf.FacesContextUtils;

import com.disys.analyzer.config.util.FacesUtils;
import com.disys.analyzer.service.AnalyserService;

@ManagedBean
@ViewScoped
public class AnalyzerRejectBean extends SpringBeanAutowiringSupport implements Serializable
{
	private static final long serialVersionUID = -7678459508988861444L;
	private Logger logger = LoggerFactory.getLogger(getClass());
	private String rejectReason;
	private String rejectedAnalyserId;
	private String rejectedParentId;
	
	@Autowired private AnalyserService service;
	
	public AnalyzerRejectBean()
	{
		FacesContext fc = FacesUtils.getFacesContext();
		ExternalContext externalContext = fc.getExternalContext();
		Map<String, String> requestMap = externalContext.getRequestParameterMap();
		rejectedAnalyserId = requestMap.get("analyserId");
		rejectedParentId = requestMap.get("parentId");
	}
	
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
	
	public void rejectAnalyser()
	{
		try
		{
			String result = service.rejectAnalyser(FacesUtils.getCurrentUserId(), Integer.parseInt(rejectedAnalyserId),
			        Integer.parseInt(rejectedParentId), rejectReason);
			String title = "Success";
			String detail = "";
			FacesMessage message = null;
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
			rejectAnalyserFromDialog(rejectedAnalyserId);
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
	}
	
	public void rejectAnalyserFromDialog(String analyserId)
	{
		RequestContext.getCurrentInstance().closeDialog(analyserId);
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
