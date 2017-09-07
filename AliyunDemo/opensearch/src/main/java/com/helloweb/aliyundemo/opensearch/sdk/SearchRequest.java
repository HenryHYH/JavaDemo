package com.helloweb.aliyundemo.opensearch.sdk;

import java.util.List;

import com.aliyun.opensearch.sdk.dependencies.org.apache.commons.lang.StringUtils;

public class SearchRequest extends BaseSearchRequest {

	private List<String> fetchFields;
	private String kvparis;

	public String getKvparis() {
		return kvparis;
	}

	public void setKvparis(String kvparis) {
		this.kvparis = kvparis;
	}

	public List<String> getFetchFields() {
		return fetchFields;
	}

	public void setFetchFields(List<String> fetchFields) {
		this.fetchFields = fetchFields;
	}

	@Override
	public String validate() {
		String base = super.validate();
		if (StringUtils.isNotEmpty(base))
			return base;

		return "";
	}
}
