/**
 * 
 */
package com.disys.analyzer.config.util;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

/**
 * @author Sajid
 * @since Sep 12, 2017
 *
 */
public class DateUtils {
	public static Date asDate(LocalDate localDate) {
		return Date.from(localDate.atStartOfDay()
				.atZone(ZoneId.systemDefault()).toInstant());
	}

	public static String getMonthName(Integer monthNumber) {
		switch (monthNumber) {
		case 1:
			return "January";
		case 2:
			return "Februray";
		case 3:
			return "March";
		case 4:
			return "April";
		case 5:
			return "May";
		case 6:
			return "June";
		case 7:
			return "July";
		case 8:
			return "August";
		case 9:
			return "September";
		case 10:
			return "October";
		case 11:
			return "November";
		case 12:
			return "December";
		default:
			break;
		}
		return "";
	}
	
	public static Timestamp getTimestampFromStringDate(String date)
	{
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
		Date d = null;
		Timestamp ts = null;
		try
		{
			d = dateFormat.parse(date);
			ts =new Timestamp(d.getTime());
		}
		catch (ParseException e)
		{
			//e.printStackTrace();
			System.out.println("Error converting string date :"+date+" to timestamp");
			return null;
		}
		return ts;
	}
}
