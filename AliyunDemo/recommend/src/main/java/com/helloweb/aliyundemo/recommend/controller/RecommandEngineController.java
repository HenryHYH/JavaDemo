package com.helloweb.aliyundemo.recommend.controller;

import java.lang.reflect.InvocationTargetException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.helloweb.aliyundemo.recommend.sdk.BaseRequest;
import com.helloweb.aliyundemo.recommend.sdk.BaseResponse;
import com.helloweb.aliyundemo.recommend.sdk.RecommandEngineFactory;
import com.helloweb.aliyundemo.recommend.sdk.RecommandRequest;
import com.helloweb.aliyundemo.recommend.sdk.RecommandResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

@Api(tags = "推荐引擎", description = "推荐引擎接口封装")
@RestController
@RequestMapping(path = "/receng", produces = "application/json")
public class RecommandEngineController {

	@Autowired
	private RecommandEngineFactory factory;

	private <TRequest extends BaseRequest, TResponse extends BaseResponse> TResponse validate(TRequest request,
			Class<TResponse> type) {
		String validateMessage = "";
		if (null == request)
			validateMessage = "request不能为空";
		else
			validateMessage = request.validate();

		TResponse response = null;
		try {
			if (!StringUtils.isEmpty(validateMessage))
				response = type.getDeclaredConstructor(String.class).newInstance(validateMessage);
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}

		return response;
	}

	@ApiOperation(value = "推荐API", produces = "application/json", httpMethod = "POST")
	@ApiImplicitParam(name = "request", value = "请求实体", required = true, dataType = "RecommandRequest")
	@RequestMapping(path = "/recommand", produces = "application/json", method = RequestMethod.POST)
	public RecommandResponse recommand(@RequestBody RecommandRequest request) throws Exception {

		RecommandResponse response = validate(request, RecommandResponse.class);
		if (null == response) {
			return factory.recommand(request);
		} else
			return response;
	}

}
