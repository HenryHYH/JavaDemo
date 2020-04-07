package com.henry.framework.demo.dto.result;

import lombok.Getter;

public class ResultData<T> {

    @Getter
    private int code;

    @Getter
    private String message;

    @Getter
    private T data;

    public ResultData(T data) {
        this(ResultCode.SUCCESS, data);
    }

    public ResultData(ResultCode resultCode, T data) {
        this.code = resultCode.getCode();
        this.message = resultCode.getMessage();
        this.data = data;
    }

}