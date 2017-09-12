package com.helloweb.aliyundemo.recommend.sdk;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.zip.GZIPOutputStream;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.net.ssl.HttpsURLConnection;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

@Component
public class RecommendFactory {

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
	 * 计算MD5+BASE64
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
	 * 计算 HMAC-SHA1
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

	public static String sendPostGZIP(String url, JSONArray content, String assessKeyId, String accessKeySecret)
			throws Exception {
		HttpsURLConnection conn = null;
		try {
			BufferedReader in = null;
			URL realUrl = new URL(url);
			StringBuilder result = new StringBuilder();
			/*
			 * http header 参数 必须设置
			 */
			String method = "POST";
			String accept = "application/json";
			String content_type = "application/json";
			String path = realUrl.getFile();
			String date = toGMTString(new Date());
			String content_encoding = "gzip";
			// 1. 对日志JSONArray进行FGZIP压缩
			byte[] body = compressToByte(content);
			// 2. 对body做MD5+BASE64加密
			String body_md5 = MD5Base64(body);
			String string_to_sign = method + "\n" + accept + "\n" + body_md5 + "\n" + content_type + "\n" + date + "\n"
					+ path;
			// 3.计算 HMAC-SHA1
			String signature = HMACSha1(string_to_sign, accessKeySecret);
			// 4.得到 authorization header
			String auth_header = "Dataplus " + assessKeyId + ":" + signature;
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
			conn.setRequestProperty("Authorization", auth_header);
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
				if (result.length() > 0) {
					JSONObject rsp_json = new JSONObject(result.toString());
					int success = rsp_json.getInt("success");
					if (success == 1) {
						System.out.println("数据上传成功");
					} else {
						System.out.println("数据上传失败, 出错信息: " + rsp_json.getString("errMsg"));
					}
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

		return "OK";
	}

	private static byte[] compressToByte(JSONArray content) {
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
			String date = toGMTString(new Date());
			// 1.对body做MD5+BASE64加密
			String bodyMd5 = MD5Base64(body);
			String stringToSign = method + "\n" + accept + "\n" + bodyMd5 + "\n" + content_type + "\n" + date + "\n"
					+ path;
			// 2.计算 HMAC-SHA1
			String signature = HMACSha1(stringToSign, accessKeySecret);
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
			String date = toGMTString(new Date());
			// 1.对body做MD5+BASE64加密
			// String bodyMd5 = MD5Base64(body);
			String stringToSign = method + "\n" + accept + "\n" + "" + "\n" + content_type + "\n" + date + "\n" + path;
			// 2.计算 HMAC-SHA1
			String signature = HMACSha1(stringToSign, accessKeySecret);
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

	public static void sendLog(List<String> logs) throws Exception {
		String url = "https://dtplus-cn-shanghai.data.aliyuncs.com/dt_ng_1352702786563330/re/uploadlog?businessName=recommend&customerName=rec_movie&token=alidata73315607a7cca280187441263";
		JSONArray content = new JSONArray();
		for (String log : logs)
			content.put(log);
		String result = sendPostGZIP(url, content, "LTAIAEMVSMtDaRcn", "u6CgiFiWd6ahL8ux4fRd7tWmiHmDhH");
		System.out.println("response body:" + result);
	}

	public static void rec() throws Exception {
		// 发送POST请求示例
		// String ak_id1 = "NMV.............5jv"; // 用户ak
		// String ak_secret1 = "Fgs...............3zu"; // 用户ak_secret
		// String url =
		// "https://dtplus-cn-shanghai.data.aliyuncs.com/henry-hyh//re/doRec";
		// String body = "{\"param1\": \"xxx\", \"param2\":\"xxx\"}";
		// System.out.println("response body:" + sendPost(url, body, ak_id1,
		// ak_secret1));

		// 发送GET请求
		String ak_id2 = "LTAIAEMVSMtDaRcn"; // 用户ak
		String ak_secret2 = "u6CgiFiWd6ahL8ux4fRd7tWmiHmDhH"; // 用户ak_secret
		String url1 = "https://dtplus-cn-shanghai.data.aliyuncs.com/dt_ng_1352702786563330/re/doRec?bizCode=rec_movie&scnCode=index&userId=1&recnum=100";
		System.out.println("response body:" + sendGet(url1, ak_id2, ak_secret2));
	}
}
