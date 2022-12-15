package com.disys.analyzer.security.service.gmap;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class GmapDistance
{
	public static void main(String[] args) throws IOException
	{
		
		// Reference -
		// https://developers.google.com/maps/documentation/distancematrix/
		String address1 = "8271 154th Ave NE, Redmond, WA 98052";
		address1 = encodeString(address1);
		String address2 = "1800 Harrison St 22nd Floor, Kaiser Engineering Building, Oakland, CA 94612";
		address2 = encodeString(address2);
		String API_KEY = "AIzaSyBOysS7GHcQZjswRVFH3w7tU6kehCoWWDg";
		
		StringBuilder urlAddress = new StringBuilder();
		
		urlAddress.append("https://maps.googleapis.com/maps/api/distancematrix/json?units=imperial&origins=");
		urlAddress.append(address1 + "&destinations=");
		urlAddress.append(address2 + "&key=" + API_KEY);
		System.out.println("URL to fetch distance is : " + urlAddress.toString());
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
		
		String response = "[status=OK, destination_addresses=[Kaiser Engineering Building, 1800 Harrison St 22nd Floor, Oakland, CA 94612, USA], origin_addresses=[8271 154th Ave NE, Redmond, WA 98052, USA], rows=[Rows [elements=[Elements [duration=Duration [duration=null, distance=null, status=null], distance=Distance [text=811 mi, value=1305941], status=OK]]]]]";
		
		parseJsonResponse(response);
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
	
	private static String parseJsonResponse(String response)
	{
		JsonElement jelement = new JsonParser().parse(response);
		JsonObject jobject = jelement.getAsJsonObject();
		jobject = jobject.getAsJsonObject("data");
		JsonArray jarray = jobject.getAsJsonArray("translations");
		jobject = jarray.get(0).getAsJsonObject();
		String result = jobject.get("translatedText").getAsString();
		return result;
	}
}
