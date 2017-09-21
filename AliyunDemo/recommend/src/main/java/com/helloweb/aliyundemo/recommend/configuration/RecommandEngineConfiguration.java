package com.helloweb.aliyundemo.recommend.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "aliyun.recommandengine")
public class RecommandEngineConfiguration {

	private String accessKey;
	private String accessSecret;
	private String doRecUrl;
	private String uploadLogUrl;

	public String getAccessKey() {
		return accessKey;
	}

	public void setAccessKey(String accessKey) {
		this.accessKey = accessKey;
	}

	public String getAccessSecret() {
		return accessSecret;
	}

	public void setAccessSecret(String accessSecret) {
		this.accessSecret = accessSecret;
	}

	public String getDoRecUrl() {
		return doRecUrl;
	}

	public void setDoRecUrl(String doRecUrl) {
		this.doRecUrl = doRecUrl;
	}

	public String getUploadLogUrl() {
		return uploadLogUrl;
	}

	public void setUploadLogUrl(String uploadLogUrl) {
		this.uploadLogUrl = uploadLogUrl;
	}

}
