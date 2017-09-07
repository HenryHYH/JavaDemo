package com.helloweb.aliyundemo.opensearch.sdk;

public class SuggestResponse extends BaseResponse {

	public SuggestResponse(String error) {
		super(error);
	}

	public SuggestResponse(String json, String error) {
		super(json, error);
	}

}
