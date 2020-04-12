package com.henry.framework.demo.infrastructure.exception;

import com.henry.framework.core.dto.result.ResultCode;
import com.henry.framework.core.dto.result.ResultData;
import com.henry.framework.core.dto.exception.BusinessException;

import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionAdvice {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResultData<String> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        ObjectError error = e.getBindingResult().getAllErrors().get(0);

        return new ResultData<>(ResultCode.VALIDATE_FAILED, error.getDefaultMessage());
    }

    @ExceptionHandler(BusinessException.class)
    public ResultData<String> businessExceptionHandler(BusinessException e) {
        return new ResultData<>(ResultCode.ERROR, e.getMessage());
    }

}