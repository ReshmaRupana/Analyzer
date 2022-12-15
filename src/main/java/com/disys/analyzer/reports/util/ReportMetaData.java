/**
 * 
 */
package com.disys.analyzer.reports.util;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Properties;

import com.disys.analyzer.config.util.Constants;

/**
 * @author Sajid
 * 
 */
public class ReportMetaData {
	private static Properties prop = null;

	public ReportMetaData() throws Exception {
		if (prop == null) {
			loadreportMetaData();
		}
	}

	public void loadreportMetaData() throws Exception {
		prop = new Properties();
		try {
			InputStream inputStream = this.getClass().getClassLoader()
					.getResourceAsStream(Constants.REPORT_FILE_LOCATION);
			System.out.println("InputStream is: " + inputStream);
			prop.load(inputStream);
		} catch (FileNotFoundException e) {
			throw new Exception(
					"An error occured while retrieving report meta data.  Error type: "
							+ e.getMessage());
		}
	}

	public String getData(String name) throws Exception {
		if (prop == null)
			throw new Exception("Report metadata has not been loaded.");
		Object obj = prop.get(name);
		String value = (String) obj;
		return value;
	}
}
