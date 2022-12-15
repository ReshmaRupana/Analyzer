/**
 * 
 */
package com.disys.analyzer.reports.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.StringTokenizer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.disys.analyzer.config.database.DbWork;

/**
 * @author Sajid
 * 
 */
public class ReportCriteria {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	private HashMap<String, String> reportInfomationMap = new HashMap<String, String>();
	private ReportMetaData reportMetaData = null;

	private String criteriaIndicator;// OLV( for me)
	private Object[] paramlist;
	private Object[] titleOrderList = null;
	private Object[] paramOrderList = null;
	private Object[] optional = null;

	public List<String> columnList = null;

	public boolean accounting = false;

	public ReportCriteria(String reportName, String userParamList)
			throws Exception {

		this(reportName, userParamList, false);

	}

	public ReportCriteria(String reportName, String userParamList,
			boolean account) throws Exception {
		reportMetaData = new ReportMetaData();
		this.criteriaIndicator = reportName;
		paramlist = parseStringList(userParamList);
		getDetails();
		accounting = account;
	}

	public String[] getOptionalList() {
		String[] retval = new String[optional.length];
		for (int i = 0; i < optional.length; i++) {
			retval[i] = optional[i].toString();
		}

		return retval;
	}

	public String[] getTitleList() {

		String[] retval = new String[titleOrderList.length];
		for (int i = 0; i < titleOrderList.length; i++) {
			retval[i] = titleOrderList[i].toString();
		}

		return retval;
	}

	public String[] getParamList() {

		String[] val = new String[paramOrderList.length];
		for (int i = 0; i < paramOrderList.length; i++) {
			val[i] = paramOrderList[i].toString();
		}

		return val;
	}

	public String[] getParamValue() {

		String[] value = new String[paramlist.length];
		for (int i = 0; i < paramlist.length; i++) {
			value[i] = paramlist[i].toString();
		}

		return value;
	}

	public List<Map<String, Object>> getReport() throws Exception {
		DbWork db = null;

		db = new DbWork();

		String SQL = (String) reportInfomationMap.get(criteriaIndicator
				+ ".SQL");
		String SQLTYPE = (String) reportInfomationMap
				.get((criteriaIndicator + ".SQLTYPE"));
		List<Map<String, Object>> alist = db.getResult(SQL, paramlist,
				SQLTYPE, accounting);
		columnList = db.columnList;
		return alist;
	}
	
	public List<Map<String, Object>> getReportFromCommission() throws Exception {
		DbWork db = null;

		db = new DbWork();

		String SQL = (String) reportInfomationMap.get(criteriaIndicator
				+ ".SQL");
		String SQLTYPE = (String) reportInfomationMap
				.get((criteriaIndicator + ".SQLTYPE"));
		List<Map<String, Object>> alist = db.getResultFromCommission(SQL, paramlist,
				SQLTYPE, accounting);
		columnList = db.columnList;
		return alist;
	}

	private void getDetails() throws Exception {
		String key = criteriaIndicator + ".SQL";
		String value = reportMetaData.getData(key);
		reportInfomationMap.put(key, value);

		key = criteriaIndicator + ".SQLTYPE";
		value = reportMetaData.getData(key);
		reportInfomationMap.put(key, value);

		key = criteriaIndicator + ".SQL_ORDERLIST";
		value = reportMetaData.getData(key);
		// reportInfomationMap.put(key,value);
		titleOrderList = parseStringList(value);

		key = criteriaIndicator + ".PARAMLIST";
		value = reportMetaData.getData(key);
		paramOrderList = parseStringList(value);

		key = criteriaIndicator + ".TOP_BANNER";
		value = reportMetaData.getData(key);
		reportInfomationMap.put(key, value);

		key = criteriaIndicator + ".TITLE";
		value = reportMetaData.getData(key);
		reportInfomationMap.put(key, value);

		key = criteriaIndicator + ".DESCRIPTION";
		value = reportMetaData.getData(key);
		reportInfomationMap.put(key, value);

		key = criteriaIndicator + ".TOPGRAPHICS";
		value = reportMetaData.getData(key);
		reportInfomationMap.put(key, value);

		key = criteriaIndicator + ".LEFTSIDEGRAPHICS";
		value = reportMetaData.getData(key);
		reportInfomationMap.put(key, value);

		key = criteriaIndicator + ".OPTIONAL";
		value = reportMetaData.getData(key);
		reportInfomationMap.put(key, value);
		optional = parseStringList(value);
		
		displayInformationMap(reportInfomationMap);
	}
	
	
	public void displayInformationMap(HashMap<String, String> reportInfomationMap){
		Iterator<Map.Entry<String, String>> iterator = reportInfomationMap.entrySet()
				.iterator();
		while (iterator.hasNext()) {
			Entry<String, String> entry = iterator.next();
			try{
				logger.debug(entry.getKey() + ":" + entry.getValue().toString());
				System.out.println(entry.getKey() + ":" + entry.getValue().toString());	
			}catch(NullPointerException ex){
				logger.debug(ex.getLocalizedMessage());
				continue;
			}
		}
	}

	public String get(String key) {
		Object obj = reportInfomationMap.get(criteriaIndicator + "." + key);
		String value = (String) obj;
		return value;
	}

	public Object[] parseStringList(String userParamList) {
		Object[] localParamlist = null;
		if ((userParamList == null) || userParamList.trim().equals(""))
			return null;

		StringTokenizer stTok;
		ArrayList<String> alist = new ArrayList<String>();
		stTok = new StringTokenizer(userParamList, "|", false);
		while (stTok.hasMoreTokens()) {
			String val = stTok.nextToken();
			alist.add(alist.size(), val);
		}
		localParamlist = alist.toArray();
		return localParamlist;
	}
}
