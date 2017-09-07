package com.helloweb.aliyundemo.opensearch;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.helloweb.aliyundemo.opensearch.sdk.BaseResponse;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(value = Exception.class)
	@ResponseBody
	public BaseResponse jsonErrorHandler(HttpServletRequest req, Exception e) throws Exception {
		return new BaseResponse(e.toString());
	}

}
