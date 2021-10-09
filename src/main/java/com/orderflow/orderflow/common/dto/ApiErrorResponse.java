package com.orderflow.orderflow.common.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ApiErrorResponse {
    private boolean success;
    private String message;
}
