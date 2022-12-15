package com.disys.analyzer.service;

import org.json.JSONArray;
import org.json.JSONObject;

public class JsonTest {

	public static void main(String[] args) {
				
				String portfolio ="";
		
		JSONObject myResponse = new JSONObject("{ \"Portfolio\":\"CACA01\",\"Rec1Portfolio\":\"CACA03\" }");
		portfolio = (String) myResponse.get("Portfolio");
		
		System.out.println("portfolio = "+portfolio);
		System.out.println("AE1Portfolio = "+ (String) myResponse.get("AE1Portfolio"));

	}

}
