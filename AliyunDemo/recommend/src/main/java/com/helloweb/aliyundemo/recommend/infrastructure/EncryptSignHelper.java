package com.helloweb.aliyundemo.recommend.infrastructure;

import java.io.ByteArrayOutputStream;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.Locale;
import java.util.zip.GZIPOutputStream;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.json.JSONArray;

public final class EncryptSignHelper {

	/**
	 * Base64
	 * 
	 * @param content
	 * @return
	 */
	private static String base64(byte[] content) {
		return Base64.getEncoder().encodeToString(content);
	}

	/**
	 * MD5+Base64
	 * 
	 * @param utfBytes
	 * @return
	 */
	public static String MD5Base64(byte[] utfBytes) {
		if (utfBytes == null)
			return null;
		String encodeStr = "";
		MessageDigest mdTemp;
		try {
			mdTemp = MessageDigest.getInstance("MD5");
			mdTemp.update(utfBytes);
			byte[] md5Bytes = mdTemp.digest();
			encodeStr = base64(md5Bytes);
		} catch (Exception e) {
			throw new Error("Failed to generate MD5 : " + e.getMessage());
		}
		return encodeStr;
	}

	/**
	 * MD5+BASE64
	 * 
	 * @param s
	 * @return
	 */
	public static String MD5Base64(String s) {
		if (null == s)
			return null;

		return MD5Base64(s.getBytes());
	}

	/**
	 * HMAC-SHA1
	 * 
	 * @param data
	 * @param key
	 * @return
	 */
	public static String HMACSha1(String data, String key) {
		String result;
		try {
			SecretKeySpec signingKey = new SecretKeySpec(key.getBytes(), "HmacSHA1");
			Mac mac = Mac.getInstance("HmacSHA1");
			mac.init(signingKey);
			byte[] rawHmac = mac.doFinal(data.getBytes());
			result = base64(rawHmac);
		} catch (Exception e) {
			throw new Error("Failed to generate HMAC : " + e.getMessage());
		}
		return result;
	}

	/**
	 * 等同于javaScript中的 new Date().toUTCString();
	 * 
	 * @param date
	 * @return
	 */
	public static String toGMTString(Date date) {
		SimpleDateFormat df = new SimpleDateFormat("E, dd MMM yyyy HH:mm:ss z", Locale.UK);
		df.setTimeZone(new java.util.SimpleTimeZone(0, "GMT"));
		return df.format(date);
	}

	/**
	 * 压缩
	 * 
	 * @param content
	 * @return
	 */
	public static byte[] compressToByte(JSONArray content) {
		try {
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			GZIPOutputStream gzip = new GZIPOutputStream(out);

			gzip.write(content.toString().getBytes("utf-8"));
			gzip.finish();
			gzip.flush();
			gzip.close();

			return out.toByteArray();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
