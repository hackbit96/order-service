package com.tc.shared.exception;

import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException {
    private final ApiResponseCode code;

    public BusinessException(ApiResponseCode code) {
        super(code.getMessage());
        this.code = code;
    }

}