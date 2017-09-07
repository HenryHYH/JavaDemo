package com.helloweb.aliyundemo.opensearch.sdk;

import com.aliyun.opensearch.sdk.dependencies.org.apache.commons.lang.StringUtils;

public class SuggestRequest extends BaseSearchRequest {

	private String suggestName;

	public String getSuggestName() {
		return suggestName;
	}

	public void setSuggestName(String suggestName) {
		this.suggestName = suggestName;
	}

	@Override
	public String validate() {
		String base = super.validate();
		if (StringUtils.isNotEmpty(base))
			return base;
		else if (StringUtils.isEmpty(suggestName))
			return "suggestName不能为空";

		return "";
	}

}
