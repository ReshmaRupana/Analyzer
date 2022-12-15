/**
 * 
 */
package com.disys.analyzer.security.service.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.disys.analyzer.config.util.Constants;

/**
 * @author Sajid
 * 
 */
public class SmartyStreetService {
	
	protected Configure config;
	
	private String city;
	private String state;
	private String zipCode;
	private String address1;
	private String address2;

	public SmartyStreetService(){
		config = Configure.getConfigObject();
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SmartyStreetService obj = new SmartyStreetService();
		
		obj.setSecondAddress();
		
		//Map<Integer, String> res = validateAddress(obj.getAddress1(), obj.getAddress2(), obj.getCity(), obj.getState(), obj.getZipCode());
	}
	
	
	private void setFirstAddress(){
		this.city = "Mclean";
		this.state = "VA";
		this.zipCode = "22102";
		this.address1 = "8270 Greensboro Drive";
		this.address2 = "Falls Church";
	}
	
	private void setSecondAddress(){
		this.city = "Redmond";
		this.state = "WA";
		this.zipCode = "98052";
		this.address1 = "8271 154th Ave NE";
		this.address2 = "";
	}

	
	public static String encode(String url) {
		try {
			String encodeURL = URLEncoder.encode(url, "UTF-8");
			return encodeURL;
		} catch (UnsupportedEncodingException e) {
			return "Issue while encoding" + e.getMessage();
		}
	}

	public Map<Integer, String> validateAddress(String address1, String address2,
			String city, String state, String zipCode) {
		URL obj = null;
		HttpURLConnection con = null;
		String responseMessage = "";

		Map<Integer,String> result = new HashMap<Integer, String>();
		try {

			String url = config.getSmartyURL();
			String query = "";
			if(config.getEnvironment().equalsIgnoreCase(Constants.ENVIRONMENT_DEVELOPMENT)){
				query = "auth-id=" + config.getSmartyDevelopmentAuthID() + "&auth-token=" + config.getSmartyDevelopmentAuthToken();
			} else if(config.getEnvironment().equalsIgnoreCase(Constants.ENVIRONMENT_PRODUCTION)){
				query = "auth-id=" + config.getSmartyProductionAuthID() + "&auth-token=" + config.getSmartyProductionAuthToken();
			}
			query = query + "&street=" + encode(address1) + "&street2="
					+ encode(address2);
			query = query + "&city=" + encode(city) + "&state=" + state
					+ "&zipcode=" + zipCode;
			url = url + query;

			obj = new URL(url);
			con = (HttpURLConnection) obj.openConnection();

			// optional default is GET
			con.setRequestMethod("GET");

			con.setRequestProperty("Content-Type", "application/json");
			con.setRequestProperty("Accept", "application/json");

			int responseCode = con.getResponseCode();
			System.out.println("\nSending 'GET' request to URL : " + url);
			System.out.println("Response Code : " + responseCode);

			if (responseCode == 401) {
				responseMessage = "Unauthorized: The credentials were provided incorrectly or did not match any existing, active credentials.";
				System.err
						.println(responseMessage);
				result.put(responseCode, responseMessage);
				return result;
			} else if (responseCode == 402) {
				responseMessage = "Payment Required: There is no active subscription for the account associated with the credentials submitted with the request.";
				System.err
						.println(responseMessage);
				result.put(responseCode, responseMessage);
				return result;
			} else if (responseCode == 413) {
				responseMessage = "Request Entity Too Large: The maximum size for a request body to this API is 16K (16,384 bytes).";
				System.err
						.println(responseMessage);
				result.put(responseCode, responseMessage);
				return result;
			} else if (responseCode == 400) {
				responseMessage = "Bad Request (Malformed Payload): The request body of a POST request contained no lookups or contained malformed JSON.";
				System.err
						.println(responseMessage);
				result.put(responseCode, responseMessage);
				return result;
			} else if (responseCode == 429) {
				responseMessage = "Too Many Requests: When using public website key authentication, we restrict the number of requests coming from a given source over too short of a time. If you use website key authentication, you can avoid this error by adding your IP address as an authorized host for the website key in question.";
				System.err
						.println(responseMessage);
				result.put(responseCode, responseMessage);
				return result;
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
				System.out.println(response.toString());

				JSONArray jsonArray = new JSONArray(response.toString());
				JSONObject myResponse = jsonArray.getJSONObject(0);

				System.out.println("result after Reading JSON Response");
				System.out.println(myResponse.toString());
				
				responseMessage = myResponse.toString();
				System.out
				.println(responseMessage);
				result.put(responseCode, responseMessage);
				return result;
			}

		} catch (JSONException e) {
			responseMessage = "Invalid address " + e.getLocalizedMessage();
			System.err
			.println(responseMessage);
			result.put(-1, responseMessage);
			return result;
		} catch (Exception ex) {
			responseMessage = "Invalid address " + ex.getLocalizedMessage();
			System.err
			.println(responseMessage);
			result.put(-1, responseMessage);
			return result;
		} finally {
			if (con != null) {
				con.disconnect();
			}
		}
		responseMessage = "Invalid request";
		result.put(-1, responseMessage);
		return result;
	}

	/**
	 * @return the city
	 */
	public String getCity()
	{
		return city;
	}

	/**
	 * @param city the city to set
	 */
	public void setCity(String city)
	{
		this.city = city;
	}

	/**
	 * @return the state
	 */
	public String getState()
	{
		return state;
	}

	/**
	 * @param state the state to set
	 */
	public void setState(String state)
	{
		this.state = state;
	}

	/**
	 * @return the zipCode
	 */
	public String getZipCode()
	{
		return zipCode;
	}

	/**
	 * @param zipCode the zipCode to set
	 */
	public void setZipCode(String zipCode)
	{
		this.zipCode = zipCode;
	}

	/**
	 * @return the address1
	 */
	public String getAddress1()
	{
		return address1;
	}

	/**
	 * @param address1 the address1 to set
	 */
	public void setAddress1(String address1)
	{
		this.address1 = address1;
	}

	/**
	 * @return the address2
	 */
	public String getAddress2()
	{
		return address2;
	}

	/**
	 * @param address2 the address2 to set
	 */
	public void setAddress2(String address2)
	{
		this.address2 = address2;
	}
}