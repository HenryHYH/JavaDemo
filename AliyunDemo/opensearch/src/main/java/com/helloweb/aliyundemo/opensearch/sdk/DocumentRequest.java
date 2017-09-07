package com.helloweb.aliyundemo.opensearch.sdk;

import java.util.Map;

import com.aliyun.opensearch.sdk.dependencies.org.apache.commons.lang.StringUtils;

public class DocumentRequest extends BaseRequest {

	private String tableName;
	private Map<String, Object> fields;

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public Map<String, Object> getFields() {
		return fields;
	}

	public void setFields(Map<String, Object> fields) {
		this.fields = fields;
	}

	@Override
	public String validate() {
		String base = super.validate();
		if (StringUtils.isNotEmpty(base))
			return base;
		else if (StringUtils.isEmpty(tableName))
			return "tableName不能为空";
		else if (null == fields || fields.isEmpty())
			return "fields不能为空";

		return "";
	}

}
