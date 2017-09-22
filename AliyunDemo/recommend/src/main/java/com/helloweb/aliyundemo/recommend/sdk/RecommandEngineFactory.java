package com.helloweb.aliyundemo.recommend.sdk;

import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.helloweb.aliyundemo.recommend.configuration.RecommandEngineConfiguration;
import com.helloweb.aliyundemo.recommend.infrastructure.AuthorizationHelper;

@Component
public class RecommandEngineFactory {

	@Autowired
	private RecommandEngineConfiguration config;

	public LogResponse logging(LogRequest request) {

		String json = "";
		String error = "";

		try {
			String url = String.format("%s&customerName=%s", config.getUploadLogUrl(), request.getCustomerName());
			JSONArray content = new JSONArray();
			JSONArray originContent = new JSONArray(request.getJson());
			for (Object item : originContent) {
				content.put(item.toString());
			}

			json = AuthorizationHelper.sendPostGZIP(url, content, config.getAccessKey(), config.getAccessSecret());

		} catch (Exception e) {
			e.printStackTrace();
			error = e.toString();
		}

		return new LogResponse(json, error);
	}

	public RecommandResponse recommand(RecommandRequest request) {

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
