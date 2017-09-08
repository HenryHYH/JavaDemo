package com.helloweb.aliyundemo.opensearch.sdk;

public class SearchRequestSummary {

	private String field;
	private int length;
	private String element;
	private String ellipsis;
	private int snippet;

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public String getElement() {
		return element;
	}

	public void setElement(String element) {
		this.element = element;
	}

	public String getEllipsis() {
		return ellipsis;
	}

	public void setEllipsis(String ellipsis) {
		this.ellipsis = ellipsis;
	}

	public int getSnippet() {
		return snippet;
	}

	public void setSnippet(int snippet) {
		this.snippet = snippet;
	}

}
