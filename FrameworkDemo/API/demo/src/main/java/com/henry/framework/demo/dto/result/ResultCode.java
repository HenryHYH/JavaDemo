package com.henry.framework.demo.dto.result;

import lombok.Getter;

public enum ResultCode {

    // @formatter:off

    SUCCESS(0, "操作成功"),
    FAILED(1000, "响应失败"),
    VALIDATE_FAILED(1001, "参数校验失败"),
    ERROR(5000, "错误")
    ;

    // @formatter:on

    @Getter
    private int code;

    @Getter
    private String message;

    ResultCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

}