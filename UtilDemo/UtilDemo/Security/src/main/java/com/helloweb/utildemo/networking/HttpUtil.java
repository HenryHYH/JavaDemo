package com.helloweb.utildemo.networking;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class HttpUtil {

	public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

	public static String get(String url) throws IOException {

		OkHttpClient client = new OkHttpClient();
		Request request = new Request.Builder().url(url).build();
		try (Response response = client.newCall(request).execute()) {
			return response.body().string();
		}
	}

	public static String post(String url, String json) throws IOException {

		OkHttpClient client = new OkHttpClient();
		RequestBody body = RequestBody.create(JSON, json);
		Request request = new Request.Builder().url(url).post(body).build();
		try (Response response = client.newCall(request).execute()) {
			return response.body().string();
		}
	}
}
