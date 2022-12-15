/**
 * 
 */
package com.disys.analyzer.jsf.bean;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import org.springframework.web.jsf.FacesContextUtils;

import com.disys.analyzer.config.util.FacesMessageSeverity;
import com.disys.analyzer.config.util.FacesUtils;
import com.disys.analyzer.model.User;
import com.disys.analyzer.service.ApplicationUserService;

/**
 * @author Sajid
 * @since Dec 25, 2017
 */
@ManagedBean
@ViewScoped
public class ResetPasswordBean extends SpringBeanAutowiringSupport implements
		Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private ApplicationUserService service;

	private String userId;
	
	private String userCode;

	@PostConstruct
	public void init() {
		FacesContextUtils
				.getRequiredWebApplicationContext(
						FacesContext.getCurrentInstance())
				.getAutowireCapableBeanFactory().autowireBean(this);
	}

	public ResetPasswordBean() {
		logger.debug("Insider Reset password bean");
	}

	public void resetPassword() {
		logger.debug("About to reset password for the user id : "+userId);
		
		User user = service.findByUsername(userId);
		if(user == null){
			String infoMessage = "Couldn't find user with id "+userId+" and code "+userCode;
			FacesUtils.addGlobalMessage(FacesMessageSeverity.ERROR,
					"Error!",infoMessage);
			return;
		}
		if(user.getStatus() == 0){
			String infoMessage = "User is inactive, can't reset password";
			FacesUtils.addGlobalMessage(FacesMessageSeverity.ERROR,
					"Error!",infoMessage);
			return;
		}
//		if(!user.getADPCode().equals(userCode)){
//			String infoMessage = "Couldn't find user with id "+userId+" and code "+userCode;
//			FacesUtils.addGlobalMessage(FacesMessageSeverity.ERROR,
//					"Error!",infoMessage);
//			return;
//		}
		String status = service.setRandomUserPassword(userId);
		if(status.equals("0")){
			String infoMessage = "Random password set and emailed successfully "+userId;
			FacesUtils.addGlobalMessage(FacesMessageSeverity.INFO,
					"Success!",infoMessage);
		}else{
			String infoMessage = "There was an error changing password for user "+userId+", contact Disys Administrator";
			FacesUtils.addGlobalMessage(FacesMessageSeverity.INFO,
					"Error!",infoMessage);
		}
	}

	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * @return the userCode
	 */
	public String getUserCode()
	{
		return userCode;
	}

	/**
	 * @param userCode the userCode to set
	 */
	public void setUserCode(String userCode)
	{
		this.userCode = userCode;
	}

}
