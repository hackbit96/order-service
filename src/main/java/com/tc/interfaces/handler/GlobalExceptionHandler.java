package com.tc.interfaces.handler;

import com.tc.shared.dto.ApiResponse;
import com.tc.shared.exception.ApiResponseCode;
import com.tc.shared.exception.BusinessException;
import com.tc.shared.exception.IntegrationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ApiResponse> handleBusiness(BusinessException ex) {
        return ResponseEntity.badRequest().body(
                new ApiResponse(ex.getCode())
        );
    }

    @ExceptionHandler(IntegrationException.class)
    public ResponseEntity<ApiResponse> handleIntegration(IntegrationException ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                new ApiResponse(ex.getCode())
        );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse> handleGeneric(Exception ex) {
        if (ex instanceof ResponseStatusException) {
            ResponseStatusException statusEx = (ResponseStatusException) ex;
            return handleStatusException(statusEx);
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ApiResponse(ApiResponseCode.INTERNAL_ERROR));
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ApiResponse> handleStatusException(ResponseStatusException ex) {
        return ResponseEntity
                .status(ex.getStatusCode())
                .body(new ApiResponse("HTTP_" + ex.getStatusCode().value(), ex.getReason()));
    }

}

