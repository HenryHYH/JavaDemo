package com.helloweb.aliyundemo.opensearch.sdk;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.helloweb.aliyundemo.opensearch.configuration.OpenSearchConfiguration;

@Component
public class OpenSearchFactory {

	@Autowired
	private OpenSearchConfiguration config;
	private String appName = "helloworld";

	private OpenSearchClient getClient() {
		OpenSearch option = new OpenSearch(config.getAccessKey(), config.getAccessSecret(), config.getHost());

		return new OpenSearchClient(option);
	}

	private SearcherClient getSearcherClient() {
		return new SearcherClient(getClient());
	}

	private DocumentClient getDocumentClient() {
		return new DocumentClient(getClient());
	}

	private Config getConfig(BaseSearchRequest request) {

		Config config = new Config(Lists.newArrayList(request.getAppName()));
		config.setSearchFormat(SearchFormat.FULLJSON);

		// 分页
		config.setStart(request.getStart());
		config.setHits(request.getHits());

		return config;
	}

	@SuppressWarnings("unused")
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

	public SearchResponse search(SearchRequest request) {

		String json = "";
		String error = "";

		Config config = getConfig(request);
		// 设置返回的字段
		if (null != request.getFetchFields() && !request.getFetchFields().isEmpty())
			config.setFetchFields(request.getFetchFields());
		// 设置kvpairs
		if (null != request.getKvparis() && !request.getKvparis().isEmpty())
			config.setKvpairs(request.getKvparis());

		SearchParams searchParams = new SearchParams(config);
		searchParams.setQuery(request.getQuery());

		SearchParamsBuilder builder = SearchParamsBuilder.create(searchParams);
		SearcherClient client = getSearcherClient();
		try {
			SearchResult searchResult = client.execute(builder);
			json = searchResult.getResult();
		} catch (OpenSearchException | OpenSearchClientException e) {
			e.printStackTrace();
			error = e.toString();
		}

		return new SearchResponse(json, error);
	}

	public SuggestResponse suggest(SuggestRequest request) {

		String json = "";
		String error = "";

		Config config = getConfig(request);
		SearchParams searchParams = new SearchParams(config);

		Suggest suggest = new Suggest(request.getSuggestName());
		searchParams.setSuggest(suggest);
		searchParams.setQuery(request.getQuery());

		SearchParamsBuilder builder = SearchParamsBuilder.create(searchParams);
		SearcherClient client = getSearcherClient();
		try {

			SearchResult searchResult = client.execute(builder);
			json = searchResult.getResult();

		} catch (OpenSearchException | OpenSearchClientException e) {
			e.printStackTrace();
			error = e.toString();
		}

		return new SuggestResponse(json, error);
	}

	public DocumentResponse addDocument(DocumentRequest request) {
		return documentAction(request, (client, fields) -> {
			client.add(request.getFields());
		});
	}

	public DocumentResponse updateDocument(DocumentRequest request) {
		return documentAction(request, (client, fields) -> {
			client.update(request.getFields());
		});
	}

	public DocumentResponse deleteDocument(DocumentRequest request) {
		return documentAction(request, (client, fields) -> {
			client.remove(request.getFields());
		});
	}

	private DocumentResponse documentAction(DocumentRequest request, Action action) {

		String result = "";
		String error = "";

		DocumentClient client = getDocumentClient();
		action.execute(client, request);

		try {
			OpenSearchResult searchResult = client.commit(request.getAppName(), request.getTableName());
			result = searchResult.getResult();

		} catch (OpenSearchException | OpenSearchClientException e) {
			e.printStackTrace();
			error = e.toString();
		}

		return new DocumentResponse(result, error);
	}

	private interface Action {
		void execute(DocumentClient client, DocumentRequest request);
	}

}
