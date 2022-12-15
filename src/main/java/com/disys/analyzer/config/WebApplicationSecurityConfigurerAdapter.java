package com.disys.analyzer.config;

/**
 * @author Sajid Qureshi
 * @since Jan 23, 2020
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.disys.analyzer.config.encoder.CustomPasswordEncoder;
import com.disys.analyzer.config.util.FacesUtils;
import com.disys.analyzer.security.service.ApplicationUserDetailsService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.PropertySource;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.core.support.LdapContextSource;
import org.springframework.security.ldap.search.FilterBasedLdapUserSearch;
import org.springframework.security.ldap.userdetails.LdapUserDetailsImpl;

@Configuration
@EnableWebSecurity(debug = false)
@EnableGlobalMethodSecurity(prePostEnabled = true)
@PropertySource("classpath:application.properties")
public class WebApplicationSecurityConfigurerAdapter extends
		WebSecurityConfigurerAdapter 
{
	private static final Logger logger = LoggerFactory.getLogger(WebApplicationSecurityConfigurerAdapter.class);

	@Autowired
	private Environment env;

	@Autowired
	private ApplicationUserDetailsService userDetailsService;

	@Autowired
	CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;

	@Autowired
	CustomAuthenticationFailureHandler customAuthenticationFailureHandler;
	
	@Autowired
	CustomLogoutSuccessHandler customLogoutSuccessHandler;

	@Autowired
	CustomLdapAuthoritiesPopulator customLdapAuthoritiesPopulator;
	
	// private static final String SALT = "analyzer"; // Salt should be
	// protected carefully

	/*@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(12, new SecureRandom(SALT.getBytes()));
	}*/

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new CustomPasswordEncoder();

	}

	private static final String[] PUBLIC_MATCHERS = { "/webjars/**", "/css/**",
			"/js/**", "/images/**", "/", "/about/**", "/contact/**",
			"/error/**/*", "/console/**", "/signup" };

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		// Security policy: login page.
		http.formLogin() //
				.loginPage("/public/login.xhtml") //
				.loginProcessingUrl("/public/login.xhtml") //
				.successHandler(customAuthenticationSuccessHandler)
				// .defaultSuccessUrl("/protected/application-users.xhtml") //
				// .failureUrl("/public/login.xhtml?source=loginError") //
				.failureHandler(customAuthenticationFailureHandler).permitAll();

		// Security policy: public available paths
		http.authorizeRequests()
				//
				.antMatchers("/").permitAll()
				//
				.antMatchers("/index.xhtml")
				.permitAll()
				//
				.antMatchers("/public/**", "/resources/**",
						"/javax.faces.resource/**").permitAll();

		http.exceptionHandling().accessDeniedPage("/403.xhtml");

		// Security policy: protecting all remaining paths.
		http.authorizeRequests() //
				.anyRequest().authenticated();

		// Logout handling.
		http.logout() //
				.logoutUrl("/logout") //
				.logoutSuccessUrl("/public/login.xhtml?source=logout") //
				.permitAll()
				.logoutSuccessHandler(customLogoutSuccessHandler).invalidateHttpSession(false);

		/**
		 * To open dialog boxes. By default X-Frame-Options is set to denied, to
		 * prevent clickjacking attacks. To override this, we have to add the
		 * following into spring security configuration
		 */
		http.headers().frameOptions().sameOrigin();
		
		http.headers().xssProtection().disable();

		/*
		 * Security policy: disabling CSRF protection. We must use it, but for
		 * now, I prefer to disable it to prevent more configuration overloads.
		 */
		http.csrf().disable();
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth)
			throws Exception 
	{
		//auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());	//Tayyab for LOCAL
		if(env.getProperty("environment").equalsIgnoreCase("production")) 
		{
	        if(FacesUtils.getLdapAuthenticationStatus()) 	//LDAP
	        {
	            System.out.println("Configuration for Authentication from LDAP ENABLED - production");
	            logger.info("Configuration for Authentication from LDAP ENABLED - production");
				logger.info("In WebApplicationSecurityConfigurerAdapter --> configureGlobal --> Production LDAP url : " + env.getProperty("production.ldap.url"));
				System.out.println("In WebApplicationSecurityConfigurerAdapter --> configureGlobal --> Production LDAP url : " + env.getProperty("production.ldap.url"));
				
				auth.ldapAuthentication().contextSource().url(env.getProperty("production.ldap.url")).managerDn(env.getProperty("production.ldap.managerdn")).managerPassword(env.getProperty("production.ldap.password"))
				 .and()
				 .userSearchFilter("("+ env.getProperty("production.ldap.userearchFilter")+"={0})")
				 .ldapAuthoritiesPopulator(customLdapAuthoritiesPopulator);
	        }
	        else 
	        {
	            System.out.println("Configuration for Authentication from LDAP 	DISABLED - production");
	            logger.info("Configuration for Authentication from LDAP DISABLED - production");
				logger.info("In WebApplicationSecurityConfigurerAdapter --> configureGlobal --> Production - LDAP Disabled. Using database authentication");
				System.out.println("In WebApplicationSecurityConfigurerAdapter --> configureGlobal --> Production - LDAP Disabled. Using database authentication");
				
				auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());	
	        }
		}
		else 
		{
	        if(FacesUtils.getLdapAuthenticationStatus()) 	//LDAP
	        {
	            System.out.println("Configuration for Authentication from LDAP ENABLED - DEVELOPMENT");
	            logger.info("Configuration for Authentication from LDAP ENABLED - DEVELOPMENT");
				logger.info("In WebApplicationSecurityConfigurerAdapter --> configureGlobal --> Development LDAP url : " + env.getProperty("development.ldap.url"));
				System.out.println("In WebApplicationSecurityConfigurerAdapter --> configureGlobal --> Development LDAP url : " + env.getProperty("development.ldap.url"));
				//System.out.println("LDAP Manager DN : " + env.getProperty("development.ldap.managerdn"));
				
				auth.ldapAuthentication().contextSource().url(env.getProperty("development.ldap.url")).managerDn(env.getProperty("development.ldap.managerdn")).managerPassword(env.getProperty("development.ldap.password"))
				 .and()
				 .userSearchFilter("("+ env.getProperty("development.ldap.userearchFilter")+"={0})")
				 .ldapAuthoritiesPopulator(customLdapAuthoritiesPopulator);	
	        }
	        else 
	        {
	            System.out.println("Configuration for Authentication from LDAP 	DISABLED - DEVELOPMENT");
	            logger.info("Configuration for Authentication from LDAP DISABLED - DEVELOPMENT");
				logger.info("In WebApplicationSecurityConfigurerAdapter --> configureGlobal --> Development - LDAP Disabled. Using database authentication");
				System.out.println("In WebApplicationSecurityConfigurerAdapter --> configureGlobal --> Development - LDAP Disabled. Using database authentication");
				
				auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());	
	        }
		}
	}

	@Bean
	public LdapContextSource contextSource() {
		LdapContextSource contextSource = new LdapContextSource();
		if (env.getProperty("environment").equalsIgnoreCase("production")) {
			contextSource.setUrl(env.getProperty("production.ldap.url"));
			contextSource.setUserDn(env.getProperty("production.ldap.managerdn"));
			contextSource.setPassword(env.getProperty("production.ldap.password"));
		} else {
			contextSource.setUrl(env.getProperty("development.ldap.url"));
			contextSource.setUserDn(env.getProperty("development.ldap.managerdn"));
			contextSource.setPassword(env.getProperty("development.ldap.password"));
		}
		return contextSource;
	}
	
	@Bean
	public LdapTemplate ldapTemplate() {
	    //return new LdapTemplate(contextSource());
		LdapTemplate ldapT = new LdapTemplate(contextSource());
		ldapT.setIgnorePartialResultException(true);
	    return ldapT;
	}
	
	@Bean
	public CustomLdapAuthenticationProvider customLdapAuthenticationProvider() {
		CustomBindAuthenticator bindAuthenticator = new CustomBindAuthenticator(contextSource());
		FilterBasedLdapUserSearch filterBasedLdapUserSearch = new FilterBasedLdapUserSearch("", "(sAMAccountName={0})", contextSource());
		bindAuthenticator.setUserSearch(filterBasedLdapUserSearch);
		return new CustomLdapAuthenticationProvider(bindAuthenticator, customLdapAuthoritiesPopulator);
	}
	
}