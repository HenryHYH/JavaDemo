package com.helloweb.aliyundemo.recommend.sdk;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.helloweb.aliyundemo.recommend.configuration.RecommandEngineConfiguration;
import com.helloweb.aliyundemo.recommend.infrastructure.AuthorizationHelper;
import com.helloweb.aliyundemo.recommend.infrastructure.EncryptSignHelper;

@Component
public class RecommandEngineFactory {

	@Autowired
	private RecommandEngineConfiguration config;

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

			// 1.对body做MD5+BASE64加密
			String bodyMd5 = EncryptSignHelper.MD5Base64(body);
			String stringToSign = method + "\n" + accept + "\n" + bodyMd5 + "\n" + content_type + "\n" + date + "\n"
					+ path;
			// 2.计算 HMAC-SHA1
			String signature = EncryptSignHelper.HMACSha1(stringToSign, accessKeySecret);
			// 3.得到 authorization header
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
				if (result.length() > 0) {
					JSONObject rsp_json = new JSONObject(result.toString());
					int success = rsp_json.getInt("success");
					if (success == 1) {
						System.out.println("数据上传成功");
						System.out.println(result.toString());
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

	public static void sendLog(List<String> logs) throws Exception {
		String url = "https://dtplus-cn-shanghai.data.aliyuncs.com/dt_ng_1352702786563330/re/uploadlog?businessName=recommend&customerName=henry_hyh_rec_test&token=alidata73315607a7cca280187441263";
		JSONArray content = new JSONArray();
		for (String log : logs)
			content.put(log);
		String result = sendPostGZIP(url, content, "LTAIlehNSqGVZVMh", "K7MkEq51BX989AEqzRBWL4pyPokpPp");
		System.out.println("response body:" + result);
	}

	public RecommandResponse recommand(RecommandRequest request) throws Exception {

		String json = "";
		String error = "";

		try {
			String url = String.format("%s?%s", config.getDoRecUrl(), request.toUrlPath());
			json = AuthorizationHelper.sendGet(url, config.getAccessKey(), config.getAccessSecret());
		} catch (Exception e) {
			e.printStackTrace();
			error = e.toString();
		}

		return new RecommandResponse(json, error);
	}
}
