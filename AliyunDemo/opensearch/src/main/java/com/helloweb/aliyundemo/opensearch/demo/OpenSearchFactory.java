package com.helloweb.aliyundemo.opensearch.demo;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.aliyun.opensearch.DocumentClient;
import com.aliyun.opensearch.OpenSearchClient;
import com.aliyun.opensearch.SearcherClient;
import com.aliyun.opensearch.sdk.dependencies.com.google.common.collect.Lists;
import com.aliyun.opensearch.sdk.dependencies.org.json.JSONObject;
import com.aliyun.opensearch.sdk.generated.OpenSearch;
import com.aliyun.opensearch.sdk.generated.commons.OpenSearchClientException;
import com.aliyun.opensearch.sdk.generated.commons.OpenSearchException;
import com.aliyun.opensearch.sdk.generated.commons.OpenSearchResult;
import com.aliyun.opensearch.sdk.generated.search.Config;
import com.aliyun.opensearch.sdk.generated.search.DeepPaging;
import com.aliyun.opensearch.sdk.generated.search.SearchFormat;
import com.aliyun.opensearch.sdk.generated.search.SearchParams;
import com.aliyun.opensearch.sdk.generated.search.Suggest;
import com.aliyun.opensearch.sdk.generated.search.general.SearchResult;
import com.aliyun.opensearch.search.SearchParamsBuilder;
import com.aliyun.opensearch.search.SearchResultDebug;

@Component
public class OpenSearchFactory {

	private String accessKey = "LTAIAEMVSMtDaRcn";
	private String accessSecret = "u6CgiFiWd6ahL8ux4fRd7tWmiHmDhH";
	private String host = "http://opensearch-cn-hangzhou.aliyuncs.com";
	private String appName = "helloworld";

	private OpenSearchClient getClient() {
		OpenSearch option = new OpenSearch(accessKey, accessSecret, host);

		return new OpenSearchClient(option);
	}

	private SearcherClient getSearcherClient() {
		return new SearcherClient(getClient());
	}

	private DocumentClient getDocumentClient() {
		return new DocumentClient(getClient());
	}

	private void printDebug(SearcherClient client, SearchParams params)
			throws OpenSearchException, OpenSearchClientException {

		SearchResultDebug debug = client.executeDebug(params);
		System.out.println("Status = " + debug.getStatusCode());
		System.out.println("Reason = " + debug.getReasonPhrase());
		System.out.println("Url = " + debug.getRequestUrl());
	}

	public void scroll() throws OpenSearchException, OpenSearchClientException, InterruptedException {

		SearcherClient search = getSearcherClient();

		Config config = new Config(Lists.newArrayList(appName));
		config.setHits(1);
		config.setSearchFormat(SearchFormat.FULLJSON);

		SearchParams searchParams = new SearchParams(config);
		searchParams.setQuery("default:'搜索'");

		DeepPaging deep = new DeepPaging();
		deep.setScrollExpire("3m"); // 3 mins
		searchParams.setDeepPaging(deep);

		SearchParamsBuilder paramsBuilder = SearchParamsBuilder.create(searchParams);

		SearchResult result = search.execute(paramsBuilder);
		String strResult = result.getResult();
		JSONObject obj = new JSONObject(strResult);

		System.out.println("第0次执行:" + obj.toString());

		for (int i = 1; i <= 6; i++) {
			String scrollId = new JSONObject(obj.get("result").toString()).get("scroll_id").toString();
			deep.setScrollId(scrollId);
			deep.setScrollExpire("3m");

			result = search.execute(paramsBuilder);
			strResult = result.getResult();
			obj = new JSONObject(strResult);

			System.out.println("第" + i + "次执行:" + obj.toString());
			Thread.sleep(1000);
		}
	}

	public String search(String query) {

		String result = "NULL";

		SearcherClient search = getSearcherClient();

		Config config = new Config(Lists.newArrayList(appName));
		config.setStart(0);
		config.setHits(5);
		config.setSearchFormat(SearchFormat.FULLJSON);
		// config.setFetchFields(Lists.newArrayList("id", "title", "description",
		// "name"));

		SearchParams searchParams = new SearchParams(config);
		searchParams.setQuery(query);

		SearchParamsBuilder paramsBuilder = SearchParamsBuilder.create(searchParams);

		try {

			SearchResult searchResult = search.execute(paramsBuilder);
			String strResult = searchResult.getResult();
			JSONObject obj = new JSONObject(strResult);
			result = obj.toString();

			printDebug(search, searchParams);

		} catch (OpenSearchException | OpenSearchClientException e) {
			e.printStackTrace();
		}

		return result;
	}

	public String suggest(String query) throws OpenSearchException, OpenSearchClientException {

		String result = "NULL";

		SearcherClient client = getSearcherClient();

		Config config = new Config(Lists.newArrayList(appName));
		config.setStart(0);
		config.setHits(5);
		config.setSearchFormat(SearchFormat.FULLJSON);

		SearchParams searchParams = new SearchParams(config);

		Suggest sug = new Suggest("hello_dropdown");
		searchParams.setSuggest(sug);
		searchParams.setQuery(query);

		SearchParamsBuilder paramsBuilder = SearchParamsBuilder.create(searchParams);

		SearchResult searchResult = client.execute(paramsBuilder);
		String json = searchResult.getResult();
		JSONObject obj = new JSONObject(json);
		if (!obj.has("errors")) {
			result = obj.toString();
		}

		return result;
	}

	public String addDocument(String tableName, Map<String, Object> fields)
			throws OpenSearchException, OpenSearchClientException {

		DocumentClient client = getDocumentClient();

		client.add(fields);
		OpenSearchResult response = client.commit(appName, tableName);
		System.out.println(response.getTraceInfo());

		return response.getResult();
	}

	public String updateDocument(String tableName, Map<String, Object> fields)
			throws OpenSearchException, OpenSearchClientException {

		DocumentClient client = getDocumentClient();

		client.update(fields);
		OpenSearchResult response = client.commit(appName, tableName);
		System.out.println(response.getTraceInfo());

		return response.getResult();
	}

}
