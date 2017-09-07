package com.helloweb.aliyundemo.opensearch.sdk;

public class SearchResponse extends BaseResponse {

	public SearchResponse(String error) {
		super(error);
	}

	public SearchResponse(String json, String error) {
		super(json, error);
	}

}
