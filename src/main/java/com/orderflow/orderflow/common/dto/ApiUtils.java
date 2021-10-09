package com.orderflow.orderflow.common.dto;

public class ApiUtils {

    public static <T> ApiResponse<T> convertResponse(T entity) {
        return ApiResponse.<T>builder()
                .success(true)
                .entity(entity)
                .build();
    }

    public static ApiErrorResponse convertErrorResponse(Exception e) {
        return ApiErrorResponse.builder()
                .success(false)
                .message(e.getMessage())
                .build();
    }
}
