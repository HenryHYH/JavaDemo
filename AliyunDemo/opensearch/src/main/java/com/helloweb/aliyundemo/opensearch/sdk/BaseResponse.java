package com.helloweb.aliyundemo.opensearch.sdk;

public class BaseResponse {

	private boolean success;
	private String error;
	private String json;

	public BaseResponse() {
	}

	public BaseResponse(String error) {
		this();
		this.success = false;
		this.error = error;
		this.json = "";
	}

	public BaseResponse(String json, String error) {
		this();
		this.success = null == error || error.isEmpty();
		this.error = error;
		this.json = json;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getJson() {
		return json;
	}

	public void setJson(String json) {
		this.json = json;
	}
}
