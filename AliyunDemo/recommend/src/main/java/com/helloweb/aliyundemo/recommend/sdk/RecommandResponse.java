package com.helloweb.aliyundemo.recommend.sdk;

public class RecommandResponse extends BaseResponse {

	public RecommandResponse(String error) {
		super(error);
	}

	public RecommandResponse(String json, String error) {
		super(json, error);
	}

}
