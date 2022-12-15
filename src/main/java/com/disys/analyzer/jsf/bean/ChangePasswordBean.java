/**
 * 
 */
package com.disys.analyzer.jsf.bean;

import java.io.IOException;
import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import org.springframework.web.jsf.FacesContextUtils;

import com.disys.analyzer.config.util.FacesMessageSeverity;
import com.disys.analyzer.config.util.FacesUtils;
import com.disys.analyzer.security.service.util.PasswordBasedEncryption;
import com.disys.analyzer.service.ApplicationUserService;

/**
 * @author Sajid
 * @since Dec 25, 2019
 */
@ManagedBean
@ViewScoped
public class ChangePasswordBean extends SpringBeanAutowiringSupport implements
		Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private ApplicationUserService service;

	private String userId;

	private String password;
	private String confirmPassword;
	
	private String referralPageName;

	@PostConstruct
	public void init() {
		FacesContextUtils
				.getRequiredWebApplicationContext(
						FacesContext.getCurrentInstance())
				.getAutowireCapableBeanFactory().autowireBean(this);
	}

	public ChangePasswordBean() {
		logger.debug("Insider ChangePasswordBean");
		referralPageName = FacesContext.getCurrentInstance().getExternalContext().getRequestHeaderMap().get("referer");
		System.out.println("Referal page link is : "+referralPageName);
//		referralPageName = referralPageName.substring(referralPageName.lastIndexOf("/")+1,referralPageName.length());
//		if(referralPageName.contains(".xhtml")){
//			referralPageName = referralPageName.substring(0,referralPageName.indexOf(".xhtml"));
//		}
//		System.out.println("Referal page name is : "+referralPageName);
		userId = FacesUtils.getCurrentUserId();
	}
	
	public void cancelPasswordChange(){
		HttpServletResponse res = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
		try
		{
			res.sendRedirect(referralPageName+"?faces-redirect=true");
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void changePassword() {
		logger.debug("About to reset password for the user id : " + userId);

		if (!password.equals(confirmPassword)) {
			String infoMessage = "Passwords dont match, check confirm password";
			FacesUtils.addGlobalMessage(FacesMessageSeverity.ERROR, "Error!",
					infoMessage);
			return;
		}
		String result = "";
		String passwordEncrypted = PasswordBasedEncryption.getInstance()
				.encrypt(password);

		result = service.modifyUserPassword(userId, passwordEncrypted);

		if (result.equals("0")) {
			FacesMessage message = new FacesMessage(
					FacesMessage.SEVERITY_INFO, "Password changed successfully",
					"Password was successfully Modified!");

			FacesUtils.redirect("/protected/application-users.xhtml",
					message);
			
			
		} else if (result.equals("1")) {
			System.out.println("");
			String infoMessage = "There was an error changing password for user "
					+ userId + ", contact Disys Administrator";
			FacesUtils.addGlobalMessage(FacesMessageSeverity.INFO, "Error!",
					infoMessage);
		}

	}

	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * @param userId
	 *            the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the confirmPassword
	 */
	public String getConfirmPassword() {
		return confirmPassword;
	}

	/**
	 * @param confirmPassword
	 *            the confirmPassword to set
	 */
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	/**
	 * @return the referralPageName
	 */
	public String getReferralPageName()
	{
		return referralPageName;
	}

	/**
	 * @param referralPageName the referralPageName to set
	 */
	public void setReferralPageName(String referralPageName)
	{
		this.referralPageName = referralPageName;
	}

}
