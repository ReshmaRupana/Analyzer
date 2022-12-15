/**
 * 
 */
package com.disys.analyzer.jsf.bean;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import org.springframework.web.jsf.FacesContextUtils;

import com.disys.analyzer.config.util.FacesUtils;
import com.disys.analyzer.model.User;
import com.disys.analyzer.service.ApplicationUserService;

/**
 * @author Sajid
 * @since Nov 15, 2017
 */
@ManagedBean
@SessionScoped
public class UserBean extends SpringBeanAutowiringSupport implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4481043748924567640L;

	private Logger logger = LoggerFactory.getLogger(UserBean.class);
	
	@Autowired
	private ApplicationUserService applicationUserService;
	
	private User user;
	
	@PostConstruct
	public void init() {
		FacesContextUtils
				.getRequiredWebApplicationContext(
						FacesContext.getCurrentInstance())
				.getAutowireCapableBeanFactory().autowireBean(this);
	}
	
	public UserBean(){
		user = new User();
		user = applicationUserService.findByEmail(FacesUtils.getCurrentUserId());
		
		logger.debug("In User Bean constructor");
		logger.debug("User found " +user.getFirstName() +" "+user.getLastName());
		
		System.err.println("In User Bean constructor");
		System.err.println("User found " +user.getFirstName() +" "+user.getLastName());
	}

	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}

}
