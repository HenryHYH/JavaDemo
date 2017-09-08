package com.helloweb.aliyundemo.opensearch.sdk;

import java.util.List;
import java.util.Map;

import com.aliyun.opensearch.sdk.dependencies.org.apache.commons.lang.StringUtils;

public class SearchRequest extends BaseSearchRequest {

	/**
	 * 返回结果中的字段
	 */
	private List<String> fetchFields;

	/**
	 * kvparis子句
	 */
	private String kvparis;

	/**
	 * 查询分析
	 */
	private List<String> qp;

	/**
	 * filter
	 */
	private String filter;

	/**
	 * sort 1为升序 0为降序
	 */
	private Map<String, Integer> sort;

	/**
	 * rank
	 */
	private SearchRequestRank rank;

	/**
	 * summary
	 */
	private List<SearchRequestSummary> summaries;

	/**
	 * aggregate
	 */
	private List<SearchRequestAggregate> aggregates;

	/**
	 * distinct
	 */
	private List<SearchRequestDistinct> distincts;

	public List<SearchRequestSummary> getSummaries() {
		return summaries;
	}

	public void setSummaries(List<SearchRequestSummary> summaries) {
		this.summaries = summaries;
	}

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

	public List<String> getQp() {
		return qp;
	}

	public void setQp(List<String> qp) {
		this.qp = qp;
	}

	public String getFilter() {
		return filter;
	}

	public void setFilter(String filter) {
		this.filter = filter;
	}

	public Map<String, Integer> getSort() {
		return sort;
	}

	public void setSort(Map<String, Integer> sort) {
		this.sort = sort;
	}

	public SearchRequestRank getRank() {
		return rank;
	}

	public void setRank(SearchRequestRank rank) {
		this.rank = rank;
	}

	public List<SearchRequestAggregate> getAggregates() {
		return aggregates;
	}

	public void setAggregates(List<SearchRequestAggregate> aggregates) {
		this.aggregates = aggregates;
	}

	public List<SearchRequestDistinct> getDistincts() {
		return distincts;
	}

	public void setDistincts(List<SearchRequestDistinct> distincts) {
		this.distincts = distincts;
	}

	@Override
	public String validate() {
		String base = super.validate();
		if (StringUtils.isNotEmpty(base))
			return base;

		return "";
	}
}
