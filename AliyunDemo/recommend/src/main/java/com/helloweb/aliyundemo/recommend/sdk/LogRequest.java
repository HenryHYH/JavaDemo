package com.helloweb.aliyundemo.recommend.sdk;

import org.springframework.util.StringUtils;

public class LogRequest extends BaseRequest {

	private String customerName;
	private String json;

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getJson() {
		return json;
	}

	public void setJson(String json) {
		this.json = json;
	}

	@Override
	public String validate() {
		String base = super.validate();
		if (!StringUtils.isEmpty(base))
			return base;
		else if (StringUtils.isEmpty(customerName))
			return "customerName不能为空";
		else if (StringUtils.isEmpty(json))
			return "json不能为空";

		return "";
	}
}
