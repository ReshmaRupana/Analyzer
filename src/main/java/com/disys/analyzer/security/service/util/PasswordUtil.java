/**
 * 
 */
package com.disys.analyzer.security.service.util;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import com.disys.analyzer.config.database.DBConnection;

/**
 * @author Sajid
 * 
 */
public class PasswordUtil
{
	
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		// String password =
		// PasswordBasedEncryption.getInstance().generateRandomPassword();
		String password = "tIf7IpNn";
		String userPasswordEncrypted = PasswordBasedEncryption.getInstance().encrypt(password);
		System.out.println("passwordEncrypted = " + userPasswordEncrypted);
		
		StringBuffer dirtyBuffer = new StringBuffer(password);
		
		int position = password.indexOf("'");
		
		if (position > -1)
		{
			StringBuffer cleanBuffer = dirtyBuffer.insert(position, "'");
			
			password = cleanBuffer.toString();
		}
		
		System.out.println("Password is  :" + password);
		
		String userPasswordFromDb = comparePasswordWithDbValue();
		
		
		byte[] bytes = null;
		String ans = "";
		bytes = userPasswordFromDb.getBytes(StandardCharsets.US_ASCII);
		for(int k=0; k<bytes.length;k++){
			System.out.println(bytes[k]);
		}
		ans = new String(bytes, StandardCharsets.UTF_8);
		
        //String decryptedPasswordString = PasswordBasedEncryption.getInstance().decrypt(userPasswordFromDb);
		
		
		comparePasswordCharacters(userPasswordFromDb,userPasswordEncrypted);
//		comparePasswordCharacters(userPasswordFromDb, ans);
		// String userId = "mohammad.hassan@disys.com";
		// getUserWithPassword(userId, userPasswordEncrypted);
	}
	
	public static void getUserWithPassword(String userId, String password)
	{
		Connection connection = DBConnection.getConnection();
		Statement sta;
		try
		{
			sta = connection.createStatement();
			String Sql = "select * from Users where USER_ID = '" + userId + "' and Password = '" + password + "'";
			ResultSet rs = sta.executeQuery(Sql);
			
			if (rs != null)
			{
				while (rs.next())
				{
					System.err.println("User found with firstname : " + rs.getString("First_Name") + " and last name : " + rs.getString("Last_Name"));
				}
			}
			System.out.println("Password from db is : " + password);
			// printCharacters(password);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static String comparePasswordWithDbValue()
	{
		Connection connection = DBConnection.getConnection();
		String password = "";
		Statement sta;
		try
		{
			sta = connection.createStatement();
			String Sql = "select * from Users where USER_ID = 'mohammad.hassan@disys.com'";
			ResultSet rs = sta.executeQuery(Sql);
			
			if (rs != null)
			{
				while (rs.next())
				{
					password = rs.getString("Password");
				}
			}
			System.out.println("Password from db is : " + password);
			// printCharacters(password);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return password;
	}
	
	public static void printCharacters(String passwordEncrypted)
	{
		char[] pass = passwordEncrypted.toCharArray();
		for (int j = 0; j < pass.length; j++)
		{
			System.out.println(pass[j]);
		}
	}
	
	public static void comparePasswordCharacters(String userPasswordFromDb, String userPasswordEncrypted)
	{
		char[] pass = userPasswordFromDb.toCharArray();
		char[] pass2 = userPasswordEncrypted.toCharArray();
		if (pass.length != pass2.length)
		{
			System.err.println("Password dont match");
		}
		else
		{
			int val1, val2;
			for (int j = 0; j < pass.length; j++)
			{
				val1 = (int)pass[j];
				val2 = (int)pass2[j];
				if(val2 == 65533){
					val2 = 63;
				}
				if (val1 == val2)
				{
					System.out.println("Character from pass " + pass[j] + " matches pass2 " + pass2[j]);
					continue;
				}
				else
				{
					System.err.println("Character from pass " + pass[j] + " doesn't pass2 " + pass2[j]);
					break;
				}
			}
			//System.out.println("Password matched");
		}
	}
	
}
