package com.helloweb.aliyundemo.opensearch.sdk;

public class DocumentResponse extends BaseResponse {

	public DocumentResponse(String error) {
		super(error);
	}

	public DocumentResponse(String json, String error) {
		super(json, error);
	}

}
