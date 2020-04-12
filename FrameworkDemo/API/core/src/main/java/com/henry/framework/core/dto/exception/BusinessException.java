package com.henry.framework.core.dto.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class BusinessException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private int code;
    private String message;

    public BusinessException(String message) {
        this(1001, message);
    }

    public BusinessException(int code, String message) {
        this.code = code;
        this.message = message;
    }

}