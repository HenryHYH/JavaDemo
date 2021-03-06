package com.henry.framework.core.dto.result;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import lombok.Getter;
import lombok.ToString;

@ToString
public class ResultData<T> {

    @Getter
    private int code;

    @Getter
    private String message;

    @Getter
    private String responseAt;

    @Getter
    private T data;

    public ResultData() {
    }

    public ResultData(T data) {
        this(ResultCode.SUCCESS, data);
    }

    public ResultData(ResultCode resultCode, T data) {
        this.code = resultCode.getCode();
        this.message = resultCode.getMessage();
        this.data = data;
        this.responseAt = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS"));
    }

}