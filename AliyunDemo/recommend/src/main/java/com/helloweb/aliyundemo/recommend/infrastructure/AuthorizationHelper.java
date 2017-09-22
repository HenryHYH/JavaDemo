package com.helloweb.aliyundemo.recommend.infrastructure;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;

import javax.net.ssl.HttpsURLConnection;

import org.json.JSONArray;

public final class AuthorizationHelper {

	/**
	 * 发送POST请求
	 */
	public static String sendPost(String url, String body, String assessKeyId, String accessKeySecret)
			throws Exception {
		PrintWriter out = null;
		BufferedReader in = null;
		String result = "";
		int statusCode = 200;
		try {
			URL realUrl = new URL(url);
			/*
			 * http header 参数
			 */
			String method = "POST";
			String accept = "application/json";
			String content_type = "application/json";
			String path = realUrl.getFile();
			String date = EncryptSignHelper.toGMTString(new Date());
			// 1.对body做MD5+BASE64加密
			String bodyMd5 = EncryptSignHelper.MD5Base64(body);
			String stringToSign = method + "\n" + accept + "\n" + bodyMd5 + "\n" + content_type + "\n" + date + "\n"
					+ path;
			// 2.计算 HMAC-SHA1
			String signature = EncryptSignHelper.HMACSha1(stringToSign, accessKeySecret);
			// 3.得到 authorization header
			String authHeader = "Dataplus " + assessKeyId + ":" + signature;
			// 打开和URL之间的连接
			URLConnection conn = realUrl.openConnection();
			// 设置通用的请求属性
			conn.setRequestProperty("accept", accept);
			conn.setRequestProperty("content-type", content_type);
			conn.setRequestProperty("date", date);
			conn.setRequestProperty("Authorization", authHeader);
			// 发送POST请求必须设置如下两行
			conn.setDoOutput(true);
			conn.setDoInput(true);
			// 获取URLConnection对象对应的输出流
			out = new PrintWriter(conn.getOutputStream());
			// 发送请求参数
			out.print(body);
			// flush输出流的缓冲
			out.flush();
			// 定义BufferedReader输入流来读取URL的响应
			statusCode = ((HttpURLConnection) conn).getResponseCode();
			if (statusCode != 200) {
				in = new BufferedReader(new InputStreamReader(((HttpURLConnection) conn).getErrorStream()));
			} else {
				in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			}
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (out != null) {
					out.close();
				}
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		if (statusCode != 200) {
			throw new IOException("\nHttp StatusCode: " + statusCode + "\nErrorMessage: " + result);
		}
		return result;
	}

	/**
	 * GET请求
	 */
	public static String sendGet(String url, String accessKeyId, String accessKeySecret) throws Exception {
		String result = "";
		BufferedReader in = null;
		int statusCode = 200;
		try {
			URL realUrl = new URL(url);
			/*
			 * http header 参数
			 */
			String method = "GET";
			String accept = "application/json";
			String content_type = "application/json";
			String path = realUrl.getFile();
			String date = EncryptSignHelper.toGMTString(new Date());
			// 1.对body做MD5+BASE64加密
			// String bodyMd5 = MD5Base64(body);
			String stringToSign = method + "\n" + accept + "\n" + "" + "\n" + content_type + "\n" + date + "\n" + path;
			// 2.计算 HMAC-SHA1
			String signature = EncryptSignHelper.HMACSha1(stringToSign, accessKeySecret);
			// 3.得到 authorization header
			String authHeader = "Dataplus " + accessKeyId + ":" + signature;
			// 打开和URL之间的连接
			URLConnection connection = realUrl.openConnection();
			// 设置通用的请求属性
			connection.setRequestProperty("accept", accept);
			connection.setRequestProperty("content-type", content_type);
			connection.setRequestProperty("date", date);
			connection.setRequestProperty("Authorization", authHeader);
			connection.setRequestProperty("Connection", "keep-alive");
			// 建立实际的连接
			connection.connect();
			// 定义 BufferedReader输入流来读取URL的响应
			statusCode = ((HttpURLConnection) connection).getResponseCode();
			if (statusCode != 200) {
				in = new BufferedReader(new InputStreamReader(((HttpURLConnection) connection).getErrorStream()));
			} else {
				in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			}
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (statusCode != 200) {
			throw new IOException("\nHttp StatusCode: " + statusCode + "\nErrorMessage: " + result);
		}
		return result;
	}

	/**
	 * Post GZIP
	 * 
	 * @param url
	 * @param content
	 * @param assessKeyId
	 * @param accessKeySecret
	 * @return
	 * @throws Exception
	 */
	public static String sendPostGZIP(String url, JSONArray content, String assessKeyId, String accessKeySecret)
			throws Exception {
		BufferedReader in = null;
		HttpsURLConnection conn = null;
		StringBuilder result = new StringBuilder();
		try {
			URL realUrl = new URL(url);
			/*
			 * http header 参数
			 */
			String method = "POST";
			String accept = "application/json";
			String content_type = "application/json";
			String path = realUrl.getFile();
			String date = EncryptSignHelper.toGMTString(new Date());
			String content_encoding = "gzip";

			// 1. 对日志JSONArray进行FGZIP压缩
			byte[] body = EncryptSignHelper.compressToByte(content);

			// 2.对body做MD5+BASE64加密
			String bodyMd5 = EncryptSignHelper.MD5Base64(body);
			String stringToSign = method + "\n" + accept + "\n" + bodyMd5 + "\n" + content_type + "\n" + date + "\n"
					+ path;
			// 3.计算 HMAC-SHA1
			String signature = EncryptSignHelper.HMACSha1(stringToSign, accessKeySecret);
			// 4.得到 authorization header
			String authHeader = "Dataplus " + assessKeyId + ":" + signature;

			// 发起连接
			conn = (HttpsURLConnection) realUrl.openConnection();
			// 设置超时, 建议1分钟, 可以更大一点
			conn.setConnectTimeout(60000);
			conn.setReadTimeout(60000);
			// 设置请求方法
			conn.setRequestMethod("POST");
			// 设置通用的请求属性
			conn.setRequestProperty("accept", accept);
			conn.setRequestProperty("content-type", content_type);
			conn.setRequestProperty("date", date);
			conn.setRequestProperty("Authorization", authHeader);
			// 必须要设置为GZIP，否则服务器会不接受
			conn.setRequestProperty("Content-Encoding", content_encoding);
			// 发送POST请求必须设置如下两行
			conn.setDoOutput(true);
			conn.setDoInput(true);
			// 设置是非缓存
			conn.setUseCaches(false);

			// 传输body流 必须GZIP加密后字节数组
			DataOutputStream data_stream = new DataOutputStream(conn.getOutputStream());
			data_stream.write(body);
			data_stream.flush();
			data_stream.close();
			if (conn.getResponseCode() != 200) {
				System.err.println("日志API连接失败!");
			} else {
				// 返回jsonobject
				in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
				String line = null;
				while ((line = in.readLine()) != null) {
					result.append(line);
				}
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				conn.disconnect();
			}
		}

		return result.toString();
	}

}
