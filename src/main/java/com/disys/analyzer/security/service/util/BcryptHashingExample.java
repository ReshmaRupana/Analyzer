/**
 * 
 */
package com.disys.analyzer.security.service.util;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import org.springframework.security.crypto.bcrypt.BCrypt;

/**
 * @author Sajid
 * 
 */
public class BcryptHashingExample {
	
	private static final String SALT = "analyzer";

	/**
	 * @param args
	 */
	public static void main(String[] args) throws NoSuchAlgorithmException {
		String originalPassword = "disys";
		int log_rounds = 12;
		
		
		
		/*String generatedSecuredPasswordHash = BCrypt.hashpw(originalPassword,
				BCrypt.gensalt(12));*/
		
		String generatedSecuredPasswordHash = BCrypt.hashpw(originalPassword,
				BCrypt.gensalt(log_rounds, new SecureRandom(SALT.getBytes())));
		
		System.out.println(generatedSecuredPasswordHash);

		//generatedSecuredPasswordHash = "$2y$10$3DKh5syvLyYhowm8ov9PxebxPJyv7kZj0r4UV0U4uY9puCbRGL3oq";
		
		boolean matched = BCrypt.checkpw(originalPassword,
				generatedSecuredPasswordHash);
		System.out.println(matched);
	}

}
