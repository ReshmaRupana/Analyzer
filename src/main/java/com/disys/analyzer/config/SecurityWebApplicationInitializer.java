/**
 * 
 */
package com.disys.analyzer.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

/**
 * @author Sajid
 * @since Oct 3, 2017
 */
@Configuration
@Order(2)
public class SecurityWebApplicationInitializer extends
AbstractSecurityWebApplicationInitializer {

}
