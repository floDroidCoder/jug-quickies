package com.julien_roux.jug.quickies.utils;

import java.security.MessageDigest;

import org.apache.commons.lang.StringUtils;

public class MD5Encoder {
	private MD5Encoder() {
	}

	public static String toMD5(String toEncode) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(toEncode.getBytes());

			byte byteData[] = md.digest();

			// convert the byte to hex format method 1
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < byteData.length; i++) {
				sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
			}

			return sb.toString();
		} catch (Exception e) {
			return StringUtils.EMPTY;
		}
	}
}
