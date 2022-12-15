/**
 * 
 */
package com.disys.analyzer.config.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author Sajid
 * @since Nov 18, 2017
 */
public class TitleUtil {

	private final String fileName = "titles.properties";
	private Properties properties = new Properties();

	public static String getTitle(String pageName) {
		try {
			TitleUtil util = new TitleUtil();
			String pageTitle = util.doSomeOperation(pageName);
			return pageTitle;
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	private String doSomeOperation(String pageName) throws IOException {
		// Get the inputStream
		InputStream inputStream = this.getClass().getClassLoader()
				.getResourceAsStream(fileName);
		System.out.println("InputStream is: " + inputStream);
		// load the inputStream using the Properties
		properties.load(inputStream);
		// get the value of the property
		String propValue = properties.getProperty(pageName);
		if(propValue == null){
			return pageName;
		}
		System.out.println(pageName + " Property value is: " + propValue);
		return propValue;
	}
	
	public static void main(String[] args) {
		TitleUtil.getTitle("login.xhtml");
	}
}
