package com.helloweb.aliyundemo.opensearch.sdk;

public class SearchRequestAggregate {

	private String groupKey;
	private String aggFun;
	private String aggFilter;
	private String range;
	private String aggSamplerThresHold;
	private String aggSamplerStep;
	private String maxGroup;

	public String getGroupKey() {
		return groupKey;
	}

	public void setGroupKey(String groupKey) {
		this.groupKey = groupKey;
	}

	public String getAggFun() {
		return aggFun;
	}

	public void setAggFun(String aggFun) {
		this.aggFun = aggFun;
	}

	public String getAggFilter() {
		return aggFilter;
	}

	public void setAggFilter(String aggFilter) {
		this.aggFilter = aggFilter;
	}

	public String getRange() {
		return range;
	}

	public void setRange(String range) {
		this.range = range;
	}

	public String getAggSamplerThresHold() {
		return aggSamplerThresHold;
	}

	public void setAggSamplerThresHold(String aggSamplerThresHold) {
		this.aggSamplerThresHold = aggSamplerThresHold;
	}

	public String getAggSamplerStep() {
		return aggSamplerStep;
	}

	public void setAggSamplerStep(String aggSamplerStep) {
		this.aggSamplerStep = aggSamplerStep;
	}

	public String getMaxGroup() {
		return maxGroup;
	}

	public void setMaxGroup(String maxGroup) {
		this.maxGroup = maxGroup;
	}

}
