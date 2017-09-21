package com.helloweb.aliyundemo.recommend.sdk;

import java.lang.reflect.Field;
import java.util.LinkedList;
import java.util.List;

import org.springframework.util.StringUtils;

public class RecommandRequest extends BaseRequest {

	private String bizCode;
	private String scnCode;
	private String userId;
	private String itemId;
	private String category;
	private String className;

	public String getBizCode() {
		return bizCode;
	}

	public void setBizCode(String bizCode) {
		this.bizCode = bizCode;
	}

	public String getScnCode() {
		return scnCode;
	}

	public void setScnCode(String scnCode) {
		this.scnCode = scnCode;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String toUrlPath() throws IllegalArgumentException, IllegalAccessException {
		List<String> list = new LinkedList<>();

		Field[] fields = this.getClass().getDeclaredFields();
		for (Field field : fields) {
			String name = field.getName();
			if ("className" == name)
				name = "class";

			Object value = field.get(this);
			if (null == value)
				continue;

			list.add(String.format("%s=%s", name, value));
		}

		return String.join("&", list);
	}

	@Override
	public String validate() {
		String base = super.validate();
		if (!StringUtils.isEmpty(base))
			return base;
		else if (StringUtils.isEmpty(bizCode))
			return "bizCode不能为空";
		else if (StringUtils.isEmpty(scnCode))
			return "scnCode不能为空";

		return "";
	}
}
