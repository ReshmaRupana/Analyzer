/**
 * 
 */
package com.disys.analyzer.security.service.util;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.PropertiesConfiguration;

/**
 * @author Sajid
 * @since 24th December, 2018
 *
 */
public class Configure
{
	private static Configure ref;
	private Configuration config;
	private static final String PROPERTIES_FILE_NAME = "application.properties";
	
	private Configure(){
		try {
			config = new PropertiesConfiguration(PROPERTIES_FILE_NAME);
		}catch (Exception ex){
			config = null;
			System.out.println(ex.getMessage());
		}
	}
	
	public static Configure getConfigObject(){
		if(ref == null){
			ref = new Configure();
		}
		return ref;
	}
	
	public String getSmartyURL(){
		if(config != null){
			return config.getString("smarty.url");
		} else {
			return null;
		}
	}
	
	public String getEnvironment(){
		if(config != null){
			return config.getString("environment");
		} else {
			return null;
		}
	}
	
	public String getSmartyProductionAuthID(){
		if(config != null){
			return config.getString("smarty.production.auth.id");
		} else {
			return null;
		}
	}
	
	public String getSmartyProductionAuthToken(){
		if(config != null){
			return config.getString("smarty.production.auth.token");
		} else {
			return null;
		}
	}
	
	public String getSmartyDevelopmentAuthID(){
		if(config != null){
			return config.getString("smarty.development.auth.id");
		} else {
			return null;
		}
	}
	
	public String getSmartyDevelopmentAuthToken(){
		if(config != null){
			return config.getString("smarty.development.auth.token");
		} else {
			return null;
		}
	}
}
