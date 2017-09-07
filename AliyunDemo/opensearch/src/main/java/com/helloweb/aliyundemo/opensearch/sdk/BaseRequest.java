package com.helloweb.aliyundemo.opensearch.sdk;

import com.aliyun.opensearch.sdk.dependencies.org.apache.commons.lang.StringUtils;

public abstract class BaseRequest {

	private String appName;

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public String validate() {
		if (StringUtils.isEmpty(appName))
			return "appName不能为空";

		return "";
	}

}
