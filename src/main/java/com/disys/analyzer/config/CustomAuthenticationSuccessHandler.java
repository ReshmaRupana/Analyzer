/**
 * 
 */
package com.disys.analyzer.config;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import com.disys.analyzer.config.util.FacesUtils;
import com.disys.analyzer.service.ApplicationUserService;

import org.springframework.security.ldap.userdetails.LdapUserDetailsImpl;
import com.disys.analyzer.config.util.Constants;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * @author Sajid
 * @since Feb 17, 2020
 */
@Component
public class CustomAuthenticationSuccessHandler implements
		AuthenticationSuccessHandler {

	private Logger logger = LoggerFactory
			.getLogger(CustomAuthenticationSuccessHandler.class);
	
	@Autowired private ApplicationUserService applicationUserService;

	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

	private String defaultSuccessURL = "/Analyzer/protected/analyser-list.xhtml?compCode=DISYS";

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		
		response.setStatus(HttpServletResponse.SC_OK);
        
        boolean admin = false;
        
        logger.info("In onAuthenticationSuccess(...) function!");  
        
        /*for (GrantedAuthority auth : authentication.getAuthorities()) {
            if ("ROLE_ADMIN".equals(auth.getAuthority())){
            	admin = true;
            }
        }
        
        if(admin){
        	response.sendRedirect("/admin");
        }else{
        	response.sendRedirect("/welcome");
        }*/
		
		/*handle(request, response, authentication);
		clearAuthenticationAttributes(request);*/
        
        
        HttpSession session = request.getSession();
        //User authUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();	//Tayyab for LOCAL
        //session.setAttribute("userId", authUser.getUsername());	//Tayyab for LOCAL
        
        //For hybrid authentication
        UserDetails authUser;
        if(FacesUtils.getLdapAuthenticationStatus()) 	//LDAP
        {
        	authUser = (LdapUserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        	//session.setAttribute("userId", (authUser.getUsername() + Constants.DISYS_DOMAIN));
            System.out.println("Authentication from LDAP ENABLED");
            logger.info("Authentication from LDAP ENABLED");
            session.setAttribute("userId", (authUser.getUsername() + Constants.DISYS_DOMAIN));
        }
        else 
        {
	        authUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();	//Tayyab for LOCAL
	        //session.setAttribute("userId", authUser.getUsername());	//Tayyab for LOCAL
            System.out.println("Authentication from LDAP DISABLED");
            logger.info("Authentication from LDAP DISABLED");
            session.setAttribute("userId", (authUser.getUsername()));
        }
 
        //LdapUserDetailsImpl authUser = (LdapUserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        //session.setAttribute("userId", (authUser.getUsername() + Constants.DISYS_DOMAIN));
        session.setAttribute("authorities", authentication.getAuthorities());
        
        System.out.println("Input value1 : " + (String)(request.getParameter("username")));
        //System.out.println("Input value2 : " + FacesUtils.getString((String)(request.getParameter("password")), true));
        logger.info("Input value1 : " + (String)(request.getParameter("username")));
        //logger.info("Input value2 : " + FacesUtils.getString((String)(request.getParameter("password")), true));	
        System.out.println("spSetUserLoginAttempt call for SUCCESSFUL login in onAuthenticationSuccess \n : " + authUser.getUsername() + "\nIP address : " + FacesUtils.getClientIp(request));
        logger.info("spSetUserLoginAttempt call for SUCCESSFUL login in onAuthenticationSuccess \n : " + authUser.getUsername() + "\nIP address : " + FacesUtils.getClientIp(request));
        
        int userLogId = applicationUserService.spSetUserLoginAttempt(authUser.getUsername(), FacesUtils.getClientIp(request), "S");
        
        System.out.println("Loggedin user id:  " + userLogId);
        
        request.getSession().setAttribute("userLogId", userLogId);
        
        response.sendRedirect(defaultSuccessURL);

	}

	protected void handle(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication)
			throws IOException {

		String targetUrl = determineTargetUrl(authentication);

		if (response.isCommitted()) {
			logger.debug("Response has already been committed. Unable to redirect to "
					+ targetUrl);
			return;
		}

		redirectStrategy.sendRedirect(request, response, targetUrl);
	}

	protected String determineTargetUrl(Authentication authentication) {
		boolean isUser = false;
		boolean isAdmin = false;
		Collection<? extends GrantedAuthority> authorities = authentication
				.getAuthorities();
		for (GrantedAuthority grantedAuthority : authorities) {
			if (grantedAuthority.getAuthority().equals("ROLE_USER")) {
				isUser = true;
				break;
			} else if (grantedAuthority.getAuthority().equals("ROLE_ADMIN")) {
				isAdmin = true;
				break;
			}
		}

		if (isUser) {
			return defaultSuccessURL;
		} else if (isAdmin) {
			return defaultSuccessURL;
		} else {
			throw new IllegalStateException();
		}
	}

	protected void clearAuthenticationAttributes(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if (session == null) {
			return;
		}
		session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
	}

	public void setRedirectStrategy(RedirectStrategy redirectStrategy) {
		this.redirectStrategy = redirectStrategy;
	}

	protected RedirectStrategy getRedirectStrategy() {
		return redirectStrategy;
	}

}