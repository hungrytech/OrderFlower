package com.orderflow.orderflow.common.error;

import com.orderflow.orderflow.common.dto.ApiErrorResponse;
import com.orderflow.orderflow.common.dto.ApiUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;

@RestControllerAdvice
public class CommonControllerAdviser {


    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiErrorResponse> errorArgumentResponse(IllegalArgumentException e) {
        return ResponseEntity.badRequest()
                .body(ApiUtils.convertErrorResponse(e));
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ApiErrorResponse> errorNoSuchResponse(NoSuchElementException e) {
        return ResponseEntity.badRequest()
                .body(ApiUtils.convertErrorResponse(e));
    }

}
