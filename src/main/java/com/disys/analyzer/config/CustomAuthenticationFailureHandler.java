/**
 * 
 */
package com.disys.analyzer.config;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import com.disys.analyzer.config.util.FacesUtils;
import com.disys.analyzer.service.ApplicationUserService;

/**
 * @author Sajid
 * @since Nov 16, 2019
 */
@Component
public class CustomAuthenticationFailureHandler implements
		AuthenticationFailureHandler {

	private Logger logger = LoggerFactory
			.getLogger(getClass());
	
	
	private String defaultFailureURL = "public/login.xhtml?error=true";
	
	@Autowired private ApplicationUserService applicationUserService;
	
	@Override
	public void onAuthenticationFailure(HttpServletRequest request,
			HttpServletResponse response, AuthenticationException exception)
			throws IOException, ServletException {

		logger.info("In onAuthenticationFailure(...) function!");
		
		
		String url = request.getRequestURL().toString();
		url = url.substring(0, url.length() - request.getRequestURI().length()) + request.getContextPath();
		
		logger.debug("Redirecting to "+url+"/"+defaultFailureURL);
		System.out.println("Redirecting to "+url+"/"+defaultFailureURL);
		
//		Enumeration<String> params = request.getParameterNames(); 
//		while(params.hasMoreElements()){
//		 String paramName = params.nextElement();
//		 System.out.println("Parameter Name - "+paramName+", Value - "+request.getParameter(paramName));
//		}
		
		/*FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
				"Authentication Error!",
				"Username or Password incorret, try again.");

		FacesUtils.redirect(defaultFailureURL, message);*/
		
        System.out.println("Input value1 : " + (String)(request.getParameter("username")));
        //System.out.println("Input value2 : " + FacesUtils.getString((String)(request.getParameter("password")), true));
        logger.info("Input value1 : " + (String)(request.getParameter("username")));
        //logger.info("Input value2 : " + FacesUtils.getString((String)(request.getParameter("password")), true));		
        System.out.println("spSetUserLoginAttempt call for FAIL login in onAuthenticationFailure : " + (String)(request.getParameter("username")));
        logger.info("spSetUserLoginAttempt call for FAIL login in onAuthenticationFailure : " + (String)(request.getParameter("username")));
		
		int userLogId = applicationUserService.spSetUserLoginAttempt((String)(request.getParameter("username")), FacesUtils.getClientIp(request), "F");
		
		System.out.println("Fail attempt by  : " + userLogId);
		
		response.sendRedirect(url+"/"+defaultFailureURL);
		
	}

}
