package com.MBAssignment.utility;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author Mayur
 *
 */
public class MD5Hash {

	/**
	 * Calculate the HashCode for given string.
	 * 
	 * @param password
	 * @return
	 */
	public static String getHash(String password) {

		String hashpassword = "";

		try {
			// Create MessageDigest instance for MD5
			MessageDigest md = MessageDigest.getInstance("MD5");
			// Add password bytes to digest
			md.update(password.getBytes());
			// Get the hash's bytes
			byte[] bytes = md.digest();
			// This bytes[] has bytes in decimal format;
			// Convert it to hexadecimal format
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < bytes.length; i++) {
				sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
			}
			// Get complete hashed password in hex format
			hashpassword = sb.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		return hashpassword;
	}

}
