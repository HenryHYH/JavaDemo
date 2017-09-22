package com.helloweb.aliyundemo.recommend.sdk;

public class LogResponse extends BaseResponse {

	public LogResponse(String error) {
		super(error);
	}

	public LogResponse(String json, String error) {
		super(json, error);
	}

}
