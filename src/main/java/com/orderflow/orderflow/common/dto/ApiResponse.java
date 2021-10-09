package com.orderflow.orderflow.common.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ApiResponse <T> {

    private boolean success;

    private T entity;
}
