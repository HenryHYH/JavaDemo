package com.helloweb.aliyundemo.opensearch.sdk;

public class SearchRequestDistinct {

	private String key;
	private int distCount;
	private int distTimes;
	private boolean reserved;
	private boolean updateTotalHit;
	private String distFilter;
	private String grade;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public int getDistCount() {
		return distCount;
	}

	public void setDistCount(int distCount) {
		this.distCount = distCount;
	}

	public int getDistTimes() {
		return distTimes;
	}

	public void setDistTimes(int distTimes) {
		this.distTimes = distTimes;
	}

	public boolean isReserved() {
		return reserved;
	}

	public void setReserved(boolean reserved) {
		this.reserved = reserved;
	}

	public boolean isUpdateTotalHit() {
		return updateTotalHit;
	}

	public void setUpdateTotalHit(boolean updateTotalHit) {
		this.updateTotalHit = updateTotalHit;
	}

	public String getDistFilter() {
		return distFilter;
	}

	public void setDistFilter(String distFilter) {
		this.distFilter = distFilter;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

}
