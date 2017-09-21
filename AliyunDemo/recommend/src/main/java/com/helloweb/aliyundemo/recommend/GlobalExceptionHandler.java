package com.helloweb.aliyundemo.recommend;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.helloweb.aliyundemo.recommend.sdk.BaseResponse;

public class GlobalExceptionHandler {

	@ExceptionHandler(value = Exception.class)
	@ResponseBody
	public BaseResponse jsonErrorHandler(HttpServletRequest req, Exception e) throws Exception {
		return new BaseResponse(e.toString());
	}
}
