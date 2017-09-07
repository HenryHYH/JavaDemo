package com.helloweb.aliyundemo.opensearch.sdk;

import com.aliyun.opensearch.sdk.dependencies.org.apache.commons.lang.StringUtils;

public abstract class BaseSearchRequest extends BaseRequest {

	private String query;
	private int start;
	private int hits;

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getHits() {
		return hits;
	}

	public void setHits(int hits) {
		this.hits = hits;
	}

	@Override
	public String validate() {
		String base = super.validate();
		if (StringUtils.isNotEmpty(base))
			return base;
		else if (StringUtils.isEmpty(query))
			return "query不能为空";
		else if (0 > start)
			return "start必须大于等于0";
		else if (0 >= hits)
			return "hits必须大于0";

		return "";
	}
}
