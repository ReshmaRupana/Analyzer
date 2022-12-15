/**
 * @author Sajid
 * @since Feb 11, 2020
 */

package com.disys.analyzer.config.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import com.disys.analyzer.model.dto.AnalyserDTO;

import java.text.SimpleDateFormat;
import javax.naming.directory.Attributes;
import org.apache.directory.api.util.DateUtils;
import org.springframework.ldap.NamingException;
import org.springframework.ldap.core.AttributesMapper;
import com.disys.analyzer.model.Analyser;

/**
 * @author Sajid
 * @since Aug 31, 2019
 */
public class Util {
	
	private final static String AUTH_ID = "647dcc0d-c8d0-0d91-43b1-23b586de71ac";
	private final static String AUTH_TOKEN = "PNcAIrsxMMZR1w7HssLS";
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	public static String removeSingleQuote(String dirtyString) {
		if (dirtyString == null) {
			return "";
		}
		char[] dirtyArray = new char[dirtyString.length()];
		StringBuffer cleanBuffer = new StringBuffer("");
		dirtyString.getChars(0, dirtyString.length(), dirtyArray, 0);
		String singleQuote = "'";
		for (int i = 0; i < dirtyArray.length; i++) {
			char singleChar = dirtyArray[i];
			char[] sc = new char[1];
			sc[0] = singleChar;
			if (!singleQuote.equals(new String(sc))) {
				cleanBuffer.append(singleChar);
			}
		}
		return cleanBuffer.toString();
	}

	public static String replaceSingleQuote(String dirtyString) {
		if (dirtyString == null) {
			return "";
		}
		char[] dirtyArray = new char[dirtyString.length()];
		StringBuffer cleanBuffer = new StringBuffer("");
		dirtyString.getChars(0, dirtyString.length(), dirtyArray, 0);
		String singleQuote = "'";
		for (int i = 0; i < dirtyArray.length; i++) {
			char singleChar = dirtyArray[i];
			cleanBuffer.append(singleChar);
			char[] sc = new char[1];
			sc[0] = singleChar;
			if (singleQuote.equals(new String(sc))) {
				cleanBuffer.append("'");
			}
		}
		return cleanBuffer.toString();
	}

	public static Date asDate(LocalDate localDate) {
		return Date.from(localDate.atStartOfDay()
				.atZone(ZoneId.systemDefault()).toInstant());
	}

	public static String getResourceFilePath(ServletContext servletContext,
			String resourceName) {
		String pathAndName = null;
		URL url = null;
		if (resourceName != null && resourceName.length() != 0) {
			try {
				// url = session.getServletContext().getResource(resourceName);
				// url = application.getResource(resourceName);
				url = servletContext.getResource(resourceName);
			} catch (MalformedURLException malUrlException) {
				malUrlException.printStackTrace();
				return null;
			}

			if (url != null) {
				pathAndName = url.getPath();
			}
		}
		return pathAndName;
	}
	
	
	/*public void iterateUsingIteratorAndEntry(Map<Integer, String> map) {

		clientNames = new ArrayList<SelectItem>();

		Iterator<Map.Entry<Integer, String>> iterator = map.entrySet()
				.iterator();
		while (iterator.hasNext()) {
			Map.Entry<Integer, String> entry = iterator.next();
			clientNames.add(new SelectItem(entry.getKey(), entry.getValue()
					.toString()));
			System.out.println(entry.getKey() + ":" + entry.getValue());
		}
	}*/
	
	/*public static Boolean verifyUsZipCode(String state,String city, String zipCode){
		StaticCredentials credentials = new StaticCredentials(AUTH_ID,
				AUTH_TOKEN);
		Client client = new ClientBuilder(credentials)
				.buildUsZipCodeApiClient();

		Lookup lookup = new Lookup();
		lookup.setState(state);
		lookup.setCity(city);
		lookup.setZipCode(zipCode);
		
		lookup.setCity("Mountain View");
		lookup.setState("California");
		lookup.setZipCode("98035");

		try {
			client.send(lookup);
		} catch (SmartyException ex) {
			System.out.println(ex.getMessage());
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		}

		Result result = lookup.getResult();
		if (result.getStatus()!=null && result.getStatus().equalsIgnoreCase("conflict")) {
			System.err.println("Error !!!" + result.getReason());
			return false;
		} else {
			ZipCode[] zipCodes = result.getZipCodes();
			City[] cities = result.getCities();

			for (City objCity : cities) {
				System.out.println("\nCity: " + objCity.getCity());
				System.out.println("State: " + objCity.getState());
				System.out.println("Mailable City: " + objCity.getMailableCity());
			}

			for (ZipCode zip : zipCodes) {
				System.out.println("\nZIP Code: " + zip.getZipCode());
				System.out.println("Latitude: " + zip.getLatitude());
				System.out.println("Longitude: " + zip.getLongitude());
			}
			return true;
		}
	}*/
	public static Boolean verifyUsZipCode(String state,String city, String zipCode){
		URL obj = null;
		HttpURLConnection con = null;
		try {
			String url = "https://us-zipcode.api.smartystreets.com/lookup?auth-id="
					+ AUTH_ID + "&auth-token=" + AUTH_TOKEN;
			url = url + "&city="+city+"&state="+state+"&zipcode="+zipCode;
			
			//https://smartystreets.com/products/apis/us-zipcode-api?auth-id=647dcc0d-c8d0-0d91-43b1-23b586de71ac&auth-token=PNcAIrsxMMZR1w7HssLS&city=Mountain%20View&state=CA&zipcode=94035&method=GET
			obj = new URL(url);
			con = (HttpURLConnection) obj.openConnection();

			con.setRequestMethod("GET");

			con.setRequestProperty("Content-Type", "application/json");
			con.setRequestProperty("Accept","application/json");

			int responseCode = con.getResponseCode();
			System.out.println("\nSending 'GET' request to URL : " + url);
			System.out.println("Response Code : " + responseCode);

			if (responseCode == 401) {
				System.err
						.println("Unauthorized: The credentials were provided incorrectly or did not match any existing, active credentials.");
				return false;
			} else if (responseCode == 402) {
				System.err
						.println("Payment Required: There is no active subscription for the account associated with the credentials submitted with the request.");
				return false;
			} else if (responseCode == 413) {
				System.err
						.println("Request Entity Too Large: The maximum size for a request body to this API is 16K (16,384 bytes).");
				return false;
			} else if (responseCode == 400) {
				System.err
						.println("Bad Request (Malformed Payload): The request body of a POST request contained no lookups or contained malformed JSON.");
				return false;
			} else if (responseCode == 429) {
				System.err
						.println("Too Many Requests: When using public website key authentication, we restrict the number of requests coming from a given source over too short of a time. If you use website key authentication, you can avoid this error by adding your IP address as an authorized host for the website key in question.");
				return false;
			} else if (responseCode == 200) {
				System.out
						.println("OK (success!): The response body will be a JSON array containing zero or more matches for the input provided with the request. The structure of the response is the same for both GET and POST requests.");

				BufferedReader in = new BufferedReader(new InputStreamReader(
						con.getInputStream()));
				String inputLine;
				StringBuffer response = new StringBuffer();

				while ((inputLine = in.readLine()) != null) {
					response.append(inputLine);
				}
				in.close();
				
				/*JSONArray jsonArray = new JSONArray(response.toString());
				JSONObject myResponse = jsonArray.getJSONObject(0);*/
				return true;
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		} finally {
			if (con != null) {
				con.disconnect();
			}
		}
		return false;
	}
	
	public static String extractState(String address){
		String sub = "";
		if(address.length() > 0){
			Integer position = address.lastIndexOf(',');
			sub = address.substring(0,position);
			sub = sub.substring(sub.length()-2,sub.length());
		}
		return sub;
	}
	
	public static void main(String[] args) {
		String address = "1901 Buena Vista Rd.,,Columbus,GA,31999-";
		String state = Util.extractState(address);
		System.out.println("I am runnig..."+state);
	}
	
	
	public Map<String, String> saveReportDataToExcelFile(Map<String, String> columnNameMappings,String fileName, String sheetName, List<Map<String, Object>> reportDataCopy) throws Exception
	{
		logger.debug("About to create "+fileName);
		Map<String, String> filePaths = new HashMap<String, String>();
		try
		{
			
			XSSFWorkbook workbook = new XSSFWorkbook();
			XSSFSheet sheet = workbook.createSheet(sheetName);
			
			CellStyle cellStyle = workbook.createCellStyle();
			Font font = workbook.createFont();
			font.setBoldweight(Font.BOLDWEIGHT_BOLD);
			cellStyle.setFont(font);
			
			int rowNum = 0;
			int colNum = 0;
			System.out.println("Creating excel");
			
			Row row = null;
			
			List<Integer> skipColumnsFromPrint = new ArrayList<Integer>();
			/*
			 * 
			 * To print column information from the result set
			 */
			int count = 0;
			Integer columnNumber = 0;
			for (Map<String, Object> map : reportDataCopy)
			{
				row = sheet.createRow(rowNum++);
				colNum = 0;
				Cell cell = null;
				Iterator<Map.Entry<String, Object>> iterator = map.entrySet().iterator();
				count++;
				if (count > 1)
				{
					break;
				}
				while (iterator.hasNext())
				{
					columnNumber++;
					Map.Entry<String, Object> entry = iterator.next();
					try
					{
						String val = "";
						if (entry != null)
						{
							val = entry.toString();
							val = val.substring(0, val.indexOf('='));
						}
						if (!val.isEmpty() && columnNameMappings.containsKey(val))
						{
							cell = row.createCell(colNum++);
							cell.setCellStyle(cellStyle);
							cell.setCellValue(columnNameMappings.get(val));
						}
						else
						{
							skipColumnsFromPrint.add(columnNumber);
						}
					}
					catch (Exception ex)
					{
						logger.debug("Exception in "+logger.getName()+" while writing to the file for : "+entry.toString() +" by user id : "+FacesUtils.getCurrentUserId() + ex.getLocalizedMessage());
						continue;
					}
				}
			}
			//header row is printed now, start from the second row
			rowNum = 1;
			
			for (Map<String, Object> map : reportDataCopy)
			{
				row = sheet.createRow(rowNum++);
				colNum = 0;
				Cell cell = null;
				Integer innerColumnNumber = 0;
				Iterator<Map.Entry<String, Object>> iterator = map.entrySet().iterator();
				while (iterator.hasNext())
				{
					Map.Entry<String, Object> entry = iterator.next();
					try
					{
						innerColumnNumber++;
						// check if we want to show this column on excel file
						if (!skipColumnsFromPrint.contains(innerColumnNumber))
						{
							cell = row.createCell(colNum++);
							// check if the value is a numeric or double
							if (FacesUtils.checkIfValueIsDouble(entry.getValue().toString()))
							{
								cell.setCellValue(Double.parseDouble(entry.getValue().toString()));
							}
							else
							{
								cell.setCellValue(entry.getValue().toString());
							}
						}
					}
					catch (Exception ex)
					{
						logger.debug("Exception in "+logger.getName()+" while writing to the file for : "+entry.toString() +" by user id : "+FacesUtils.getCurrentUserId() + ex.getLocalizedMessage());
						continue;
					}
				}
			}
			
			String realPath = FacesUtils.getTempFilePath();
			
			Long dateTime = System.currentTimeMillis();
			
			String FILE_EXTENSION = ".xlsx";
			
			String completeFilePath = realPath + fileName + "-" + dateTime + FILE_EXTENSION;
			filePaths.put("completeFilePath", completeFilePath);
			String newFileName = fileName + "-" + dateTime + FILE_EXTENSION;
			filePaths.put("newFileName", newFileName);
			logger.debug("File for downloading is : " + completeFilePath);
			System.out.println("File for downloading is : " + completeFilePath);
			
			WriteToFile objWriteToFile = new WriteToFile();
			File file = objWriteToFile.createFile(completeFilePath);
			
			FileOutputStream outputStream = new FileOutputStream(file);
			
			workbook.write(outputStream);
			workbook.close();
			
			outputStream.flush();
			outputStream.close();
			logger.debug("Finished completing file download "+fileName);
			return filePaths;
			
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		return filePaths;
	}
	
	public static String getBooleanValue(Boolean bool) {
		if (bool != null) {
			return bool.booleanValue() ? "Yes" : "Off";
		}
		return "Off";
	}
	
	/*
	public static String getCurrentFormattedDate() {
       SimpleDateFormat formDate = new SimpleDateFormat(Constants.DATE_FORMAT_FILE);
       String strDate = formDate.format(new Date()); 
       return strDate;
	} 
  
	public static String getPdfFileName(AnalyserDTO analyser) {
	  return Constants.ANALYSER_FILE + "-" + analyser.getAnalyserId() + "-" 
				+ Util.getCurrentFormattedDate() + Constants.PDF_FILE_EXTENSION;
	}
	*/
	
	  public static String getCurrentFormattedDate() {
	       SimpleDateFormat formDate = new SimpleDateFormat(Constants.DATE_FORMAT_FILE);
	       String strDate = formDate.format(new Date()); 
	       return strDate;
	  } 
	  
	  public static String getPdfFileName(AnalyserDTO analyser) {
		  return Constants.ANALYSER_FILE + "-" + analyser.getAnalyserId() + "-" 
					+ Util.getCurrentFormattedDate() + Constants.PDF_FILE_EXTENSION;
	  }
	  
	  /**
	 * @param ldapDate
	 * @param javaDate
	 * @return true if ldap date greater than java date
	 */
	public static boolean dateComparator(String ldapDate, Date javaDate) 
	{
		  return DateUtils.getDate(ldapDate).after(javaDate);
	}
	
	@SuppressWarnings("rawtypes")
	public class AnalyserAttributesMapper implements AttributesMapper 
	{
	      public Object mapFromAttributes(Attributes attrs) throws NamingException, javax.naming.NamingException {
	         Analyser analyser = new Analyser();
	         analyser.setUserId((String)attrs.get("sAMAccountName").get() + Constants.DISYS_DOMAIN);
	         //analyser.set((String)attrs.get("cn").get());
	         analyser.setLastName((String)attrs.get("sn").get());
	         return analyser;
	      }
	}
}