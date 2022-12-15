package com.disys.analyzer.config.util;

/**
 * @author Sajid
 * @since Oct 11, 2020
 */

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

import javax.faces.application.FacesMessage;
import javax.faces.application.NavigationHandler;
import javax.faces.component.UIComponent;
import javax.faces.component.UIViewRoot;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.DatatypeConverter;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.http.client.HttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.disys.analyzer.config.AnalyzerPrintStream;
import com.disys.analyzer.model.dto.PortfolioDownstreamRequestDTO;
import com.disys.analyzer.model.dto.PortfolioDownstreamResponseDTO;
import com.disys.analyzer.security.service.gmap.DistancePojo;
import com.google.gson.Gson;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import java.util.Date;

import org.json.JSONArray;
import org.json.JSONObject;

public final class FacesUtils
{
	
	public static final String SCHEMA_DBO = "dbo";
	public static final String SCHEMA_WIRELESS = "wireless";
	public static final String DATE_FORMAT = "MM/dd/yyyy";
	public static final String DATE_FORMAT_2 = "yyyy-MM-dd";
	
	private static Configuration config;
	private static Configuration miscConfig;
	
	private static Logger logger = LoggerFactory.getLogger(FacesUtils.class);
	private static final String PROPERTIES_FILE_NAME = "application.properties";
	
	public static final String SCHEDULED_TASK_USER = "auto@disys.com";
	public static final String MONTHLY_EXCEL_FILE = "Historical_Data";
	public static final int THREAD_POOL_SIZE = 10;
	
	private FacesUtils()
	{
		System.setOut(new AnalyzerPrintStream(System.out));
	}
	
	public static String getTempFilePath()
	{
		if(miscConfig == null){
			try{
				System.out.println("Inside FACESUTILS --> getTempFilePath.");
				logger.debug("Inside FACESUTILS --> getTempFilePath.");
				HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
				//System.out.println("req.getSession().getAttribute(\"downloadFilePath\") = "+req.getSession().getAttribute("downloadFilePath"));
				//logger.debug("req.getSession().getAttribute(\"downloadFilePath\") = "+req.getSession().getAttribute("downloadFilePath"));
				if (req != null && req.getSession() != null && req.getSession().getAttribute("downloadFilePath") != null)
				{
					System.out.println("req.getSession().getAttribute(\"downloadFilePath\") IS NOT NULL. Returning the getTempFilePath FROM Session = "+req.getSession().getAttribute("downloadFilePath"));
					logger.debug("req.getSession().getAttribute(\"downloadFilePath\") IS NOT NULL. Returning the getTempFilePath FROM Session = "+req.getSession().getAttribute("downloadFilePath"));
					System.out.println("req.getSession().getAttribute(\"downloadFilePath\") = "+req.getSession().getAttribute("downloadFilePath"));
					logger.debug("req.getSession().getAttribute(\"downloadFilePath\") = "+req.getSession().getAttribute("downloadFilePath"));

					return (String) req.getSession().getAttribute("downloadFilePath");
				}
				else
				{
					System.out.println("req.getSession().getAttribute(\"downloadFilePath\") IS NULL.");
					logger.debug("req.getSession().getAttribute(\"downloadFilePath\") IS NULL.");

					/*	//12/11/2019	Tayyab
					miscConfig = new PropertiesConfiguration(PROPERTIES_FILE_NAME);
					String path = "";
					if(miscConfig.getString("environment").equals("production")){
						path = miscConfig.getString("production.download.path");
						req.getSession().setAttribute("downloadFilePath", path);
					} else {
						path = miscConfig.getString("development.download.path");
						req.getSession().setAttribute("downloadFilePath", path);
					}
					return path;
					*/
					
					//12/11/2019 Tayyab
					if(miscConfig == null)
					{
						miscConfig = new PropertiesConfiguration(PROPERTIES_FILE_NAME);
					}
					ServletContext ctx = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
					String realPath = ctx.getRealPath("/");
					System.out.println("CONEXT - realPath = "+realPath);
					logger.debug("CONEXT - realPathh = "+realPath);
					String temporaryPath = "";
					
					if(miscConfig.getString("environment").equals("production"))
					{
						temporaryPath = miscConfig.getString("production.download.path");
						System.out.println("For PRODUCTION - download.path = "+temporaryPath);
						logger.debug("For PRODUCTION - download.path = "+temporaryPath);
					} 
					else 
					{
						temporaryPath = miscConfig.getString("development.download.path");
						System.out.println("For Develpopment - download.path = "+temporaryPath);
						logger.debug("For Development - download.path = "+temporaryPath);
					}
					
					realPath = realPath + temporaryPath + "\\";
					System.out.println("Actual FINAL - realPath = "+realPath);
					logger.debug("Actual FINAL - realPathh = "+realPath);
					if (req != null && req.getSession() != null)
					{
						System.out.println("Adding the Path to Session & Returning Path = "+realPath);
						logger.debug("Adding the Path to Session & Returning Path = "+realPath);
						req.getSession().setAttribute("downloadFilePath", realPath);
					}
					else 
					{
						System.out.println("REQ or SESSION are NULL so CAN'T SET ATTRIBUTE downloadFilePath.");
						logger.debug("REQ or SESSION are NULL so CAN'T SET ATTRIBUTE downloadFilePath.");
					}
					
					return realPath;
				}
			} 
			catch(Exception ex)
			{
				System.out.println("Inside FACESUTILS --> getTempFilePath. EXCEPTION");
				logger.debug("Inside FACESUTILS --> getTempFilePath. EXCEPTION");
				miscConfig = null;
				logger.debug(ex.getMessage());
				System.out.println(ex.getMessage());
				ex.printStackTrace();
				return null;
			}
		} else {
			HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
			if (req.getSession().getAttribute("downloadFilePath") != null)
			{
				return (String) req.getSession().getAttribute("downloadFilePath");
			}
			else
			{
				// in this case return only default path
				ServletContext ctx = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
				String realPath = ctx.getRealPath("/");
				return realPath;
			}
		}
	}
	
	private static String getMapsAPIKey()
	{
		if (config == null)
		{
			try
			{
				config = new PropertiesConfiguration(PROPERTIES_FILE_NAME);
				if (config.getString("environment").equalsIgnoreCase(Constants.ENVIRONMENT_DEVELOPMENT))
				{
					return config.getString("google.maps.development.api.key");
				}
				else
				{
					return config.getString("google.maps.production.api.key");
				}
				
			}
			catch (Exception ex)
			{
				config = null;
				logger.debug(ex.getMessage());
				System.out.println(ex.getMessage());
				return null;
			}
		}
		else
		{
			if (config.getString("environment").equalsIgnoreCase(Constants.ENVIRONMENT_DEVELOPMENT))
			{
				return config.getString("google.maps.development.api.key");
			}
			else
			{
				return config.getString("google.maps.production.api.key");
			}
		}
	}
	
	/*
	 * Static Functionalities
	 */
	
	public static FacesContext getFacesContext()
	{
		return FacesContext.getCurrentInstance();
	}
	
	public static HttpServletRequest getRequestObject()
	{
		HttpServletRequest request = (HttpServletRequest) getFacesContext().getExternalContext().getRequest();
		return request;
	}
	
	public static HttpServletResponse getResponseObject()
	{
		HttpServletResponse response = (HttpServletResponse) getFacesContext().getExternalContext().getResponse();
		return response;
	}
	
	public static void addGlobalMessage(FacesMessageSeverity severity, String title, String detail)
	{
		getFacesContext().addMessage(null, new FacesMessage(severity.getSeverity(), title, detail));
	}
	
	public static void addGlobalMessage(FacesMessageSeverity severity, String message)
	{
		addGlobalMessage(severity, message, null);
	}
	
	public static void redirect(String path, FacesMessage message)
	{
		if (path.contains("?"))
		{
			path += "&faces-redirect=true";
		}
		else
		{
			path += "?faces-redirect=true";
		}
		
		FacesContext fc = getFacesContext();
		ExternalContext ec = fc.getExternalContext();
		Flash flashContext = ec.getFlash();
		if (!flashContext.isKeepMessages())
		{
			flashContext.setKeepMessages(true);
		}
		
		fc.addMessage(null, message);
		
		NavigationHandler nh = fc.getApplication().getNavigationHandler();
		nh.handleNavigation(fc, null, path);
	}
	
	public static void addMessage(FacesMessage message)
	{
		FacesContext fc = getFacesContext();
		ExternalContext ec = fc.getExternalContext();
		Flash flashContext = ec.getFlash();
		if (!flashContext.isKeepMessages())
		{
			flashContext.setKeepMessages(true);
		}
		fc.addMessage(null, message);
	}
	
	public static String getCurrentUserId()
	{
		FacesContext fc = getFacesContext();
		ExternalContext ec = fc.getExternalContext();
		return (String) ec.getSessionMap().get("userId");
	}
	
	public static Integer getCurrentUserRole()
	{
		HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		if (req.getSession().getAttribute("roleId") != null)
		{
			Integer roleId = (Integer) req.getSession().getAttribute("roleId");
			return roleId;
		}
		else
		{
			return -1;
		}
		
	}
	
	public static Boolean getAdministrativeActions()
	{
		Integer roleId = getCurrentUserRole();
		if (roleId == 3 || roleId == 443)
		{
			return false;
		}
		else
		{
			return true;
		}
	}
	
	public static Dimension getScreenDimensions()
	{
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		return screenSize;
	}
	
	public static UIComponent getElementFromUI(String elementId)
	{
		UIViewRoot view = FacesContext.getCurrentInstance().getViewRoot();
		UIComponent comp = view.findComponent("layoutId");
		return comp;
	}
	
	public static Map<String, Object> createDialogOptions()
	{
		Map<String, Object> options = new HashMap<String, Object>();
		
		// get the height and width of screen and take
		// 85% of it.
		
		Dimension screenSize = FacesUtils.getScreenDimensions();
		int screenHeight = screenSize.height;
		screenHeight = (int) (screenHeight * 0.65);
		String screenHeightPx = screenHeight + "px";
		
		int screenWidth = screenSize.width;
		screenWidth = (int) (screenWidth * 0.85);
		String screenWidthPx = screenWidth + "px";
		
		System.out.println("Screen Height : " + screenHeight);
		System.out.println("Screen Width : " + screenWidth);
		
		logger.debug("Screen Height : " + screenHeight);
		logger.debug("Screen Width : " + screenWidth);
		
		options.put("resizable", true);
		options.put("draggable", true);
		options.put("modal", true);
		// options.put("header", "Dialog Box");
		options.put("maximizable", true);
		options.put("closeOnEscape", true); // close dialog on Escape button
											// pressed
		options.put("headerElement", null); // don't use <title> as dialog
											// header
		// options.put("appendToBody", true);
		
		options.put("minWidth", 350);
		options.put("minHeight", 150);
		
		options.put("responsive", true);
		
		options.put("width", screenWidthPx);
		options.put("contentWidth", "100%");
		options.put("height", screenHeightPx);
		options.put("contentHeight", "100%");
		
		return options;
	}
	
	public static Map<String, Object> createUSPSDialogOptions()
	{
		Map<String, Object> options = new HashMap<String, Object>();
		
		// get the height and width of screen and take
		// 85% of it.
		Dimension screenSize = FacesUtils.getScreenDimensions();
		int screenHeight = screenSize.height;
		screenHeight = (int) (screenHeight * 0.30);
		String screenHeightPx = screenHeight + "px";
		
		int screenWidth = screenSize.width;
		screenWidth = (int) (screenWidth * 0.75);
		String screenWidthPx = screenWidth + "px";
		
		logger.debug("Screen Height : " + screenHeightPx);
		logger.debug("Screen Width : " + screenWidthPx);
		
		options.put("resizable", true);
		options.put("draggable", true);
		options.put("modal", true);
		options.put("maximizable", true);
		options.put("closeOnEscape", true); // close dialog on Escape button
											// pressed
		options.put("headerElement", null); // don't use <title> as dialog
											// header
		
		options.put("minWidth", 350);
		options.put("minHeight", 150);
		
		options.put("responsive", true);
		
		options.put("width", screenWidthPx);
		options.put("contentWidth", "100%");
		options.put("height", screenHeightPx);
		options.put("contentHeight", "100%");
		
		return options;
	}
	
	/**
	 * Calculates the distance in km between two lat/long points using the
	 * haversine formula
	 */
	public static double calculateDistance(double lat1, double lng1, double lat2, double lng2)
	{
		int r = 6371; // average radius of the earth in km
		double dLat = Math.toRadians(lat2 - lat1);
		double dLon = Math.toRadians(lng2 - lng1);
		double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) * Math.sin(dLon / 2) * Math.sin(dLon / 2);
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
		double d = r * c;
		double inMiles = d * 0.621371;
		return inMiles;
	}
	
	public static double calculateRouteDistance(String branchAddress, String workSiteAddress)
	{
		try
		{
			StringBuilder urlAddress = new StringBuilder();
			
			branchAddress = encodeString(branchAddress);
			workSiteAddress = encodeString(workSiteAddress);
			
			Double val = 0.0;
			
			urlAddress.append("https://maps.googleapis.com/maps/api/distancematrix/json?units=imperial&origins=");
			urlAddress.append(branchAddress + "&destinations=");
			urlAddress.append(workSiteAddress + "&key=" + getMapsAPIKey());
			System.out.println("URL to fetch distance is : " + urlAddress.toString());
			logger.debug("URL to fetch distance is : " + urlAddress.toString());
			URL url = new URL(urlAddress.toString());
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			String line, outputString = "";
			BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			while ((line = reader.readLine()) != null)
			{
				outputString += line;
			}
			DistancePojo capRes = new Gson().fromJson(outputString, DistancePojo.class);
			System.out.println("The result is : " + capRes);
			
			if (capRes != null)
			{
				if (capRes.getRows() != null && capRes.getRows().length > 0)
				{
					if (capRes.getRows()[0].getElements() != null && capRes.getRows()[0].getElements().length > 0)
					{
						if (capRes.getRows()[0].getElements()[0].getDistance() != null)
						{
							String text = capRes.getRows()[0].getElements()[0].getDistance().getText();
							if (text != null && text.length() > 0)
							{
								val = getDistance(text);
								return val;
							}
							else
							{
								return val;
							}
						}
						else
						{
							return val;
						}
					}
					else
					{
						return val;
					}
					
				}
				else
				{
					return val;
				}
				
			}
			else
			{
				return val;
			}
			
		}
		catch (Exception ex)
		{
			System.err.println("Error calculating distance :" + ex.getMessage());
			System.out.println("Error calculating distance in FacesUtil --> calculateRouteDistance :" + ex.getMessage());
			logger.debug("Error calculating distance in FacesUtil --> calculateRouteDistance :" + ex.getMessage());
			ex.printStackTrace();
			return 0.0;
		}
		
	}
	
	private static String encodeString(String encodedString)
	{
		{
			try
			{
				encodedString = URLEncoder.encode(encodedString, "UTF-8");
			}
			catch (UnsupportedEncodingException e)
			{
				e.printStackTrace();
			}
		}
		return encodedString;
	}
	
	private static Double getDistance(String text)
	{
		String res = text.substring(0, text.indexOf(' '));
		if (res.contains(","))
		{
			res = res.replace(",", "");
		}
		Double val = 0.0;
		if (res.length() > 0)
		{
			val = Double.valueOf(res);
			return val;
		}
		else
		{
			return val;
		}
	}
	
	public static Boolean checkIfValueIsDouble(String text)
	{
		if (text != null && !text.isEmpty())
		{
			try
			{
				//Integer value = Integer.parseInt(text);
				Integer.parseInt(text);
				//System.out.println("Value is : " + value);
				// if this value is correctly parsed as Integer value return
				// false,
				// so that the String representation of this value is saved in
				// the Excel file.
				return false;
			}
			catch (Exception e)
			{
				//System.err.println("Couldn't parse integer value, will check for double value check");
				try
				{
//					Double value = Double.parseDouble(text);
					Double.parseDouble(text);
//					System.out.println("Text value " + text + " : Value " + value);
					return true;
				}
				catch (NumberFormatException nfe)
				{
//					System.err.println("Error converting value from double");
					return false;
				}
			}
			
		}
		return false;
	}
	
	public static Boolean isValidDate(String inDate) {
		SimpleDateFormat dateFormat = null;
		if(inDate != null && inDate.length() > 0){
			if(inDate.contains("-")){
				dateFormat = new SimpleDateFormat(DATE_FORMAT_2);
			} else if(inDate.contains("/")) {
				dateFormat = new SimpleDateFormat(DATE_FORMAT);
			}
		}
		// its not a date string
		if(dateFormat == null){
			return false;
		}
        dateFormat.setLenient(false);
        try {
            dateFormat.parse(inDate.trim());
        } catch (ParseException pe) {
            return false;
        }
        return true;
    }

	public static String getTemplateFilePath(){		
			try{
				System.out.println("Inside FACESUTILS --> getTemplateFilePath.");
				logger.debug("Inside FACESUTILS --> getTemplateFilePath.");

				if(miscConfig == null){
					miscConfig = new PropertiesConfiguration(PROPERTIES_FILE_NAME);
				}

				ServletContext ctx = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
				String realPath = ctx.getRealPath("/");
				System.out.println("realPath = "+realPath);
				logger.debug("realPathh = "+realPath);
				
				String templatePath = "";
				if(miscConfig.getString("environment").equals("production")){
					templatePath = miscConfig.getString("production.template.path");
				} else {
					templatePath = miscConfig.getString("development.template.path");
				}
				System.out.println("Returning Template Path = "+templatePath);
				logger.debug("Returning Template Path = "+templatePath);
				
				String actualPath = "";
				actualPath = realPath + templatePath  + "\\";
				
				System.out.println("Returning actualPath = "+actualPath);
				logger.debug("Returning actualPath = "+actualPath);
				return actualPath;
			} catch(Exception ex){
				System.out.println("Inside FACESUTILS --> getTemplateFilePath. EXCEPTION");
				logger.debug("Inside FACESUTILS --> getTemplateFilePath. EXCEPTION");
				miscConfig = null;
				logger.debug(ex.getMessage());
				System.out.println(ex.getMessage());
				
				ServletContext ctx = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
				String realPath = ctx.getRealPath("/");
				return realPath;
				}
	}

	public static String getTemporaryFilePath(){		
		try{
			System.out.println("Inside FACESUTILS --> getTemporaryFilePath.");
			logger.debug("Inside FACESUTILS --> getTemporaryFilePath.");

			if(miscConfig == null){
				miscConfig = new PropertiesConfiguration(PROPERTIES_FILE_NAME);
			}

			ServletContext ctx = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
			String realPath = ctx.getRealPath("/");
			System.out.println("realPath = "+realPath);
			logger.debug("realPath = "+realPath);
			
			String temporaryPath = "";
			if(miscConfig.getString("environment").equals("production")){
				temporaryPath = miscConfig.getString("production.temporary.path");
			} else {
				temporaryPath = miscConfig.getString("development.temporary.path");
			}
			System.out.println("Returning Temporary Path = "+temporaryPath);
			logger.debug("Returning Temporary Path = "+temporaryPath);
			
			String actualPath = "";
			actualPath = realPath + temporaryPath + "\\";
			
			System.out.println("Returning actualPath = "+actualPath);
			logger.debug("Returning actualPath = "+actualPath);
			return actualPath;
		} catch(Exception ex){
			System.out.println("Inside FACESUTILS --> getTemporaryFilePath. EXCEPTION");
			logger.debug("Inside FACESUTILS --> getTemporaryFilePath. EXCEPTION");
			miscConfig = null;
			logger.debug(ex.getMessage());
			System.out.println(ex.getMessage());
			
			ServletContext ctx = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
			String realPath = ctx.getRealPath("/");
			return realPath;
			}
	}
	
	public static String getClientIp(HttpServletRequest request) 
	{
        String remoteAddr = "";

        if (request != null) {
            remoteAddr = request.getHeader("X-FORWARDED-FOR");
            if (remoteAddr == null || "".equals(remoteAddr)) {
                remoteAddr = request.getRemoteAddr();
            }
        }

		System.out.println("Inside FACESUTILS --> getClientIp.");
		logger.debug("Inside FACESUTILS --> getClientIp.");
		System.out.println("remoteAddr = "+remoteAddr);
		logger.debug("remoteAddr = "+remoteAddr);
        return remoteAddr;
    }
	
	private static final String[] IP_HEADER_CANDIDATES = { 
		    "X-Forwarded-For",
		    "Proxy-Client-IP",
		    "WL-Proxy-Client-IP",
		    "HTTP_X_FORWARDED_FOR",
		    "HTTP_X_FORWARDED",
		    "HTTP_X_CLUSTER_CLIENT_IP",
		    "HTTP_CLIENT_IP",
		    "HTTP_FORWARDED_FOR",
		    "HTTP_FORWARDED",
		    "HTTP_VIA",
		    "REMOTE_ADDR" 
	};

	public static String getClientIpFromHeaders(HttpServletRequest request) 
	{
		    for (String header : IP_HEADER_CANDIDATES) {
		        String ip = request.getHeader(header);
		        if (ip != null && ip.length() != 0 && !"unknown".equalsIgnoreCase(ip)) {
		            return ip;
		        }
		    }
		    return request.getRemoteAddr();
	}
	
	public static boolean getLdapAuthenticationStatus ()
	{
		boolean ldapAuthentication = false;
		try{
			System.out.println("Inside FACESUTILS --> isLdapAuthenticationEnabled.");
			logger.debug("Inside FACESUTILS --> isLdapAuthenticationEnabled.");

			if(miscConfig == null)
			{
				miscConfig = new PropertiesConfiguration(PROPERTIES_FILE_NAME);
			}
			
			String ldapAuthenticationEnabled = "";
			if(miscConfig.getString("environment").equals("production")){
				ldapAuthenticationEnabled = miscConfig.getString("production.ldap.enabled");
			} else {
				ldapAuthenticationEnabled = miscConfig.getString("development.ldap.enabled");
			}
			System.out.println("LDAP Enabled Status = ldapAuthenticationEnabled  = "+ldapAuthenticationEnabled);
			logger.debug("LDAP Enabled Status = ldapAuthenticationEnabled  = "+ldapAuthenticationEnabled);
			
			if (ldapAuthenticationEnabled != null && ldapAuthenticationEnabled.equalsIgnoreCase("true"))
			{
				ldapAuthentication = true;
			}
			System.out.println("Returning ldapAuthentication = "+ldapAuthentication);
			logger.debug("Returning ldapAuthentication = "+ldapAuthentication);
			return ldapAuthentication;
		} 
		catch(Exception ex)
		{
			System.out.println("Inside FACESUTILS --> getLdapAuthenticationStatus. EXCEPTION - Returning FALSE");
			logger.debug("Inside FACESUTILS --> getLdapAuthenticationStatus. EXCEPTION - Returning FALSE");
			miscConfig = null;
			logger.debug(ex.getMessage());
			System.out.println(ex.getMessage());
			ex.printStackTrace();
			return false;
		}
	}
	
	public static SecretKeySpec getSecretKey ()
	{
		SecretKeySpec  secretKey = null;
		byte [] key;
        MessageDigest sha = null;
        
        try 
        {
        	key = getRandomString().getBytes("UTF-8");
            sha = MessageDigest.getInstance("SHA-1");
            key = sha.digest(key);
            key = Arrays.copyOf(key, 16); 
            secretKey = new SecretKeySpec(key, "AES");
        } 
        catch (NoSuchAlgorithmException e) 
        {
			System.out.println("Inside FACESUTILS --> getEncryptedData. EXCEPTION. - NoSuchAlgorithmException");
			logger.debug("Inside FACESUTILS --> getEncryptedData. EXCEPTION. - NoSuchAlgorithmException");
            e.printStackTrace();
			logger.debug(e.getMessage());
			System.out.println(e.getMessage());
        } 
        catch (UnsupportedEncodingException e) 
        {
			System.out.println("Inside FACESUTILS --> getEncryptedData. EXCEPTION. - UnsupportedEncodingException");
			logger.debug("Inside FACESUTILS --> getEncryptedData. EXCEPTION. - UnsupportedEncodingException");
            e.printStackTrace();
			logger.debug(e.getMessage());
			System.out.println(e.getMessage());
        }
		catch(Exception ex)
		{
			System.out.println("Inside FACESUTILS --> getEncryptedData. EXCEPTION. - Other SecretKey Exception");
			logger.debug("Inside FACESUTILS --> getEncryptedData. EXCEPTION. - Other SecretKey Exception");
			miscConfig = null;
			logger.debug(ex.getMessage());
			System.out.println(ex.getMessage());
			ex.printStackTrace();
		}
	        
		return secretKey;
	}
	
	public static String getString (String str, boolean mode)
	{
		String result = "";
		
        try 
        {
        	SecretKeySpec  secretKey = FacesUtils.getSecretKey();
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			
        	if (secretKey != null)
        	{
        		if (mode == true)
        		{
        			cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        			result = Base64.getEncoder().encodeToString(cipher.doFinal(str.getBytes("UTF-8")));
        		}
        		else
        		{
                    cipher.init(Cipher.DECRYPT_MODE, secretKey);
                    result = new String(cipher.doFinal(Base64.getDecoder().decode(str)));
        		}
        	}
        } 
		catch(Exception ex)
		{
			System.out.println("Inside FACESUTILS --> getEncryptedData. EXCEPTION. - Other Exception in getString");
			logger.debug("Inside FACESUTILS --> getEncryptedData. EXCEPTION. - Other Exception in getString");
			miscConfig = null;
			logger.debug(ex.getMessage());
			System.out.println(ex.getMessage());
			ex.printStackTrace();
		}
	        
		return result;
	}
	
	private static String getRandomString ()
	{
		int length = 32;
		String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
		             + "abcdefghijklmnopqrstuvwxyz"
		             +"~=+%^*/()[]{}/!@#$?|"
		             + "0123456789"
		             +Constants.DISYS_KEY;
		String str = new Random().ints(length, 0, chars.length())
		                         .mapToObj(i -> "" + chars.charAt(i))
		                         .collect(Collectors.joining());
		return str;
	}
	
	public static boolean isNumeric(String strNum) {
		try {
			double d = Double.parseDouble(strNum);
		} catch (NumberFormatException | NullPointerException nfe) {
			return false;
		}
		return true;
	}
	
	public static String getMonthlyAnalyserDataDumpFileName() {
		SimpleDateFormat sdfr = new SimpleDateFormat("MM_dd_yyyy");
		return FacesUtils.MONTHLY_EXCEL_FILE;
	}
	
	public static String getPortfolioAuthKey()
	{
		if (config == null)
		{
			try
			{
				config = new PropertiesConfiguration(PROPERTIES_FILE_NAME);
				if (config.getString("environment").equalsIgnoreCase(Constants.ENVIRONMENT_DEVELOPMENT))
				{
					return config.getString("development.ps.portfolio.api.key");
				}
				else
				{
					return config.getString("production.ps.portfolio.api.key");
				}
			}
			catch (Exception ex)
			{
				config = null;
				logger.debug(ex.getMessage());
				System.out.println(ex.getMessage());
				return null;
			}
		}
		else
		{
			if (config.getString("environment").equalsIgnoreCase(Constants.ENVIRONMENT_DEVELOPMENT))
			{
				return config.getString("development.ps.portfolio.api.key");
			}
			else
			{
				return config.getString("production.ps.portfolio.api.key");
			}
		}
	}
	
	public static String getPortfolioServiceURL()
	{
		if (config == null)
		{
			try
			{
				config = new PropertiesConfiguration(PROPERTIES_FILE_NAME);
				if (config.getString("environment").equalsIgnoreCase(Constants.ENVIRONMENT_DEVELOPMENT))
				{
					return config.getString("development.ps.portfolio.api.url");
				}
				else
				{
					return config.getString("production.ps.portfolio.api.url");
				}
			}
			catch (Exception ex)
			{
				config = null;
				logger.debug(ex.getMessage());
				System.out.println(ex.getMessage());
				return null;
			}
		}
		else
		{
			if (config.getString("environment").equalsIgnoreCase(Constants.ENVIRONMENT_DEVELOPMENT))
			{
				return config.getString("development.ps.portfolio.api.url");
			}
			else
			{
				return config.getString("production.ps.portfolio.api.url");
			}
		}
	}
	
	public static PortfolioDownstreamResponseDTO getPortfolioFromService (PortfolioDownstreamRequestDTO requestDTO)
	{
		URL obj = null;
		HttpURLConnection con = null;
		String url;
		String requetsedTime;
		String responseReceivedTime;
		Long timeTaken;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
		PortfolioDownstreamResponseDTO responseDTO = new PortfolioDownstreamResponseDTO();
		try 
		{
			
			url = FacesUtils.getPortfolioServiceURL() + "CustID="+requestDTO.getCustomerId()+"&OU="+requestDTO.getVerticalCode()+"&MD="+requestDTO.getMdEmplId()
			      +"&Location="+requestDTO.getGeoLocationCode()+"&Product="+requestDTO.getProductCode()+"&Team="+requestDTO.getRecruitingTeam()
				  +"&DealType="+requestDTO.getDealType()+"&AE1EmplId="+requestDTO.getAe1EmplId()+"&REC1EmplId="+requestDTO.getRec1EmplId();
			
			requestDTO.setUrl(url);
			requestDTO.setAuthToken(FacesUtils.getPortfolioAuthKey());
			
			System.out.println("Calling portfolio Downstream service with the input details--> "+requestDTO);
			logger.debug("Calling portfolio Downstream service with the input details--> "+requestDTO);
			
			requetsedTime = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
			
			/*System.out.println("Service Call Connection START : " + requetsedTime);
			logger.debug("Service Call Connection START : " + requetsedTime);*/
			
			obj = new URL(url);
			con = (HttpURLConnection) obj.openConnection();					
			con.setRequestMethod("GET");
			con.setRequestProperty("Content-Type", "application/json");
			con.setRequestProperty("Accept","application/json");
			con.setRequestProperty("User-Agent", "Mozilla/5.0");
			con.setRequestProperty("Authorization",requestDTO.getAuthToken());
			
			responseDTO.setStatusCode(con.getResponseCode());
			
			if (null != responseDTO.getStatusCode() && responseDTO.getStatusCode() == 200) 
			{
				System.out.println("SUCCESS! Portfolio Service Response OK.");
				logger.debug("success! Portfolio Service Response OK.");
				
				BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
				String inputLine;
				StringBuffer response = new StringBuffer();

				while ((inputLine = in.readLine()) != null) 
				{
					System.out.println("inputLine = "+inputLine);
					response.append(inputLine);
				}
				in.close();
				responseReceivedTime =  new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
				timeTaken = sdf.parse(responseReceivedTime).getTime() - sdf.parse(requetsedTime).getTime();
				response = new StringBuffer("["+response+"]");

				responseDTO.setRawResponse(response+"");
				responseDTO.setCallRequestedTime(requetsedTime);
				responseDTO.setResponseReceivedTime(responseReceivedTime);
				responseDTO.setTimeTakenInMilliSec(timeTaken.toString());
				
				System.out.println("Portfolio Service Response DTO= "+ responseDTO);
				logger.debug("Portfolio Service Response DTO= " +responseDTO);

				/*System.out.println("Service Call Connection RETURN : " + new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()));
				logger.debug("Service Call Connection RETURN : " + new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()));*/
			}
			else 
			{
				System.out.println("ERRROR - Invalid Portfolio Service Response"+ responseDTO);
				logger.debug("ERRROR - Invalid Portfolio Service Response"+ responseDTO);
			}
		} 
		catch (Exception ex) 
		{
			ex.printStackTrace();
			responseDTO.setComments( ex.getMessage());
			System.out.println("EXCEPTION in getPortfolioFromService " + ex.getMessage());
			logger.debug("EXCEPTION in getPortfolioFromService " + ex.getMessage());
		} 
		finally 
		{
			if (con != null) 
			{
				con.disconnect();
			}
		}
		return responseDTO;
	}
	
	public static void main(String[] args)
	{
		/*
		for (int i=0; i< 10; i++)
		{
			System.out.println("Random String "+getRandomString());
		}
		*/
		/*String portfolio = FacesUtils.getPortfolioFromService("1000000945", "021", "100", "NW21", "100010124", "Centralized", "A");
		System.out.println("portfolio call 1 =" + portfolio);
		portfolio = FacesUtils.getPortfolioFromService("1000000945", "031", "100", "NW21", "100010124", "Centralized", "A");
		System.out.println("portfolio call 2 = " + portfolio);
		portfolio = FacesUtils.getPortfolioFromService("1000000945", "021", "100", "NW21", "100010124", "Centralized", "A");
		System.out.println("portfolio call 3 = " + portfolio);*/
	}
		
	public static String getDefaultPortfolio()
	{
		Configuration config = getConfig();	
		if (config.getString("environment").equalsIgnoreCase(Constants.ENVIRONMENT_DEVELOPMENT))
		{
			return config.getString("development.ps.portfolio");
		}
		else
		{
			return config.getString("production.ps.portfolio");
		}
			
	}
	
	public static String getPortfolioDefaultErrorMsg()
	{
		Configuration config = getConfig();	
		if (config.getString("environment").equalsIgnoreCase(Constants.ENVIRONMENT_DEVELOPMENT))
		{
			return config.getString("development.ps.portfolio.error");
		}
		else
		{
			return config.getString("production.ps.portfolio.error");
		}
			
	}
	
	public static Configuration  getConfig() {
		try {
			if (config == null) {
				config = new PropertiesConfiguration(PROPERTIES_FILE_NAME);
			}
		} catch (Exception ex) {
			config = null;
			logger.debug(ex.getMessage());
			System.out.println(ex.getMessage());
			return null;
		}
		return config;
		
	}
	
}