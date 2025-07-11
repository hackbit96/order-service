package com.tc.shared.exception;

import lombok.Getter;

@Getter
public class IntegrationException extends RuntimeException {
    private final ApiResponseCode code;

    public IntegrationException(ApiResponseCode code, Throwable cause) {
        super(code.getMessage(), cause);
        this.code = code;
    }

}
