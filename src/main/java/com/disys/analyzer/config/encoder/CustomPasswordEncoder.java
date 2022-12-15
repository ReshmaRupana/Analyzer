/**
 * 
 */
package com.disys.analyzer.config.encoder;

import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.disys.analyzer.security.service.util.PasswordBasedEncryption;

/**
 * @author Sajid
 * @since Jun 08, 2020
 */
public class CustomPasswordEncoder implements PasswordEncoder
{
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	private static final byte[] SALT = { 
		(byte) 0xff, (byte) 0x92, (byte) 0x25, (byte) 0x3c,
		(byte) 0x7e, (byte) 0xb1, (byte) 0xe4, (byte) 0xa9 
	};
	
	private static final int ITERATION = 2765;
	
	@Override
	public String encode(CharSequence rawPassword)
	{
		PBEKeySpec 			pbeKeySpec 					= null;
		PBEParameterSpec 	pbeParamSpec 				= null;
		SecretKeyFactory 	keyFac 						= null;
		String 				encryptedPasswordString 	= null;
		String 				s 							= rawPassword.toString();
		char[] 				password 					= s.toCharArray();
		
		try
		{
			// Create PBE parameter set
			pbeParamSpec 	= new PBEParameterSpec(SALT, ITERATION);
			pbeKeySpec 		= new PBEKeySpec(password);
			keyFac 			= SecretKeyFactory.getInstance("PBEWithMD5AndDES");
			
			SecretKey pbeKey = keyFac.generateSecret(pbeKeySpec);
			
			// Create PBE Cipher
			Cipher pbeCipher = Cipher.getInstance("PBEWithMD5AndDES");
			
			// Initialize PBE Cipher with key and parameters
			pbeCipher.init(Cipher.ENCRYPT_MODE, pbeKey, pbeParamSpec);
			
			// Our cleartext
			byte[] cleartext = createByteArray(password);
			
			// Encrypt the cleartext
			byte[] ciphertext = pbeCipher.doFinal(cleartext);
			
			encryptedPasswordString = new String(ciphertext);
			
			clearCharArray(password);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return encryptedPasswordString;
	}
	
	private byte[] createByteArray(char[] c)
	{
		byte[] b = new byte[c.length];
		
		for (int i = 0; i < c.length; i++)
		{
			b[i] = (byte) c[i];
		}
		
		return b;
	}
	
	private char[] clearCharArray(char[] c)
	{
		Arrays.fill(c, ' ');
		
		return c;
	}
	
	@Override
	public boolean matches(CharSequence userSupplierPasswordString, String encodedPasswordFromDB)
	{
		if (encodedPasswordFromDB == null || encodedPasswordFromDB.length() == 0)
		{
			logger.warn("Empty encoded password");
			return false;
		}
		
		CustomPasswordEncoder encryption = new CustomPasswordEncoder();
		
		logger.debug("encodedPasswordFromDB --- " + encodedPasswordFromDB);
		System.out.println("encodedPasswordFromDB --- " + encodedPasswordFromDB);
		
		
		String encodedUserSuppliedPassword = encryption.encode(userSupplierPasswordString);
		
		logger.debug("encodedUserSuppliedPassword --- " + encodedUserSuppliedPassword);
		System.out.println("encodedSuppliedPassword --- " + encodedUserSuppliedPassword);
		Boolean match = comparePasswordCharacters(encodedPasswordFromDB,encodedUserSuppliedPassword);
		if (match)
		{
			logger.debug("Password match");
		}else{
			logger.debug("Password doesn't match");
		}
		return match;
		
	}
	
	
	private Boolean comparePasswordCharacters(String userPasswordFromDb, String userPasswordEncrypted)
	{
		char[] pass = userPasswordFromDb.toCharArray();
		char[] pass2 = userPasswordEncrypted.toCharArray();
		Boolean flag = true;
		if (pass.length != pass2.length)
		{
			System.err.println("Password dont match");
			flag = false;
		}
		else
		{
			int val1, val2;
			for (int j = 0; j < pass.length; j++)
			{
				val1 = (int)pass[j];
				val2 = (int)pass2[j];
				//65533 is the maximum value of unsigned short, in this case the hash generated created a character which was not interpreted correctly and
				//so is being replace by the fall back value. and in db is replace by ? mark character, whose value is 63
				if(val2 == 65533){
					val2 = 63;
				}
				if (val1 == val2)
				{
					//System.out.println("Character from pass " + pass[j] + " matches pass2 " + pass2[j]);
					continue;
				}
				else
				{
					//System.err.println("Character from pass " + pass[j] + " doesn't pass2 " + pass2[j]);
					flag = false;
				}
			}
		}
		return flag;
	}
	
	public static void main(String[] args)
	{
		CustomPasswordEncoder encryption = new CustomPasswordEncoder();
		for(int i=0; i<5;i++){
			String password = PasswordBasedEncryption.getInstance().generateRandomPassword();
			//System.out.println("Password generated is : "+password);
			//String encodedSuppliedPassword = encryption.encode("delta");
			String passwordEncrypted = PasswordBasedEncryption.getInstance().encrypt(password);
			//System.out.println(passwordEncrypted);
			System.out.println("-----------------");
//			char [] pass = passwordEncrypted.toCharArray();
//			for(int j=0; j<pass.length;j++){
//				System.out.println(pass[j]);
//			}
			if(!encryption.matches(password, passwordEncrypted)){
				System.err.println("Error matching passwords");
				System.exit(0);
			}
//			if (passwordEncrypted.equals(encryption.encode(password)))
//			{
//				System.out.println("Password Matched ");
//			}
//			else
//			{
//				System.err.println("Password dont match");
//				System.exit(0);
//			}
		}
		
	}
	
}
