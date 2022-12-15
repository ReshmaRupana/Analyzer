package com.disys.analyzer.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import com.disys.analyzer.service.ApplicationUserService;
@Component
public class CustomLogoutSuccessHandler implements LogoutSuccessHandler {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired private ApplicationUserService applicationUserService;
	@Override
	public void onLogoutSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		if(authentication != null) {
			System.out.println(authentication.getName());
		}
		
		System.out.println("In Logout Handler");		
		
		HttpSession session = request.getSession();
		String loggedUserID = (String) session.getAttribute("userId");
		int userLogId = (int)session.getAttribute("userLogId");
		
        System.out.println("spLogoutUserActivity call for successful logout");
        System.out.println("loggedUserID : " + loggedUserID + "\nuserLogId : " + userLogId);
        
        logger.info("spLogoutUserActivity call for successful logout");
        logger.info("loggedUserID : " + loggedUserID + "\nuserLogId : " + userLogId);
        
		
		applicationUserService.spLogoutUserActivity(loggedUserID, userLogId);
		
		session.invalidate();
		
		//perform other required operation
		String URL = request.getContextPath() + "/public/login.xhtml?source=logout";
		response.setStatus(HttpStatus.OK.value());
		response.sendRedirect(URL);
	}
} 
