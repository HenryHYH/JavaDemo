package com.helloweb.aliyundemo.opensearch.controller;

import java.lang.reflect.InvocationTargetException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.aliyun.opensearch.sdk.dependencies.org.apache.commons.lang.StringUtils;
import com.helloweb.aliyundemo.opensearch.sdk.BaseRequest;
import com.helloweb.aliyundemo.opensearch.sdk.BaseResponse;
import com.helloweb.aliyundemo.opensearch.sdk.DocumentRequest;
import com.helloweb.aliyundemo.opensearch.sdk.DocumentResponse;
import com.helloweb.aliyundemo.opensearch.sdk.OpenSearchFactory;
import com.helloweb.aliyundemo.opensearch.sdk.SearchRequest;
import com.helloweb.aliyundemo.opensearch.sdk.SearchResponse;
import com.helloweb.aliyundemo.opensearch.sdk.SuggestRequest;
import com.helloweb.aliyundemo.opensearch.sdk.SuggestResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

@Api(tags = "开放搜索", description = "开放搜索接口封装")
@RestController
@RequestMapping(path = "/opensearch", produces = "application/json")
public class OpenSearchController {

	@Autowired
	private OpenSearchFactory factory;

	private <TRequest extends BaseRequest, TResponse extends BaseResponse> TResponse validate(TRequest request,
			Class<TResponse> type) {
		String validateMessage = "";
		if (null == request)
			validateMessage = "request不能为空";
		else
			validateMessage = request.validate();

		TResponse response = null;
		try {
			if (StringUtils.isNotEmpty(validateMessage))
				response = type.getDeclaredConstructor(String.class).newInstance(validateMessage);
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}

		return response;
	}

	@ApiOperation(value = "执行查询操作", produces = "application/json", httpMethod = "POST")
	@ApiImplicitParam(name = "request", value = "请求实体", required = true, dataType = "SearchRequest")
	@RequestMapping(path = "/search", produces = "application/json", method = RequestMethod.POST)
	public SearchResponse search(@RequestBody SearchRequest request) {

		SearchResponse response = validate(request, SearchResponse.class);
		if (null == response)
			return factory.search(request);
		else
			return response;
	}

	@ApiOperation(value = "执行下拉查询操作", produces = "application/json", httpMethod = "POST")
	@ApiImplicitParam(name = "request", value = "请求实体", required = true, dataType = "SuggestRequest")
	@RequestMapping(path = "/suggest", produces = "application/json", method = RequestMethod.POST)
	public SuggestResponse suggest(@RequestBody SuggestRequest request) {

		SuggestResponse response = validate(request, SuggestResponse.class);
		if (null == response)
			return factory.suggest(request);
		else
			return response;
	}

	@ApiOperation(value = "执行添加文档操作", produces = "application/json", httpMethod = "POST")
	@ApiImplicitParam(name = "request", value = "请求实体", required = true, dataType = "DocumentRequest")
	@RequestMapping(path = "/adddoc", produces = "application/json", method = RequestMethod.POST)
	public DocumentResponse addDocument(@RequestBody DocumentRequest request) {

		DocumentResponse response = validate(request, DocumentResponse.class);
		if (null == response)
			return factory.addDocument(request);
		else
			return response;
	}

	@ApiOperation(value = "执行更新文档操作", produces = "application/json", httpMethod = "POST")
	@ApiImplicitParam(name = "request", value = "请求实体", required = true, dataType = "DocumentRequest")
	@RequestMapping(path = "/updatedoc", produces = "application/json", method = RequestMethod.POST)
	public DocumentResponse updateDocument(@RequestBody DocumentRequest request) {

		DocumentResponse response = validate(request, DocumentResponse.class);
		if (null == response)
			return factory.updateDocument(request);
		else
			return response;
	}

	@ApiOperation(value = "执行删除文档操作", produces = "application/json", httpMethod = "POST")
	@ApiImplicitParam(name = "request", value = "请求实体", required = true, dataType = "DocumentRequest")
	@RequestMapping(path = "/deletedoc", produces = "application/json", method = RequestMethod.POST)
	public DocumentResponse deleteDocument(@RequestBody DocumentRequest request) {

		DocumentResponse response = validate(request, DocumentResponse.class);
		if (null == response)
			return factory.deleteDocument(request);
		else
			return response;
	}

}
