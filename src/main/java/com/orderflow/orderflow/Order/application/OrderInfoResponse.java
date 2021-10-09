package com.orderflow.orderflow.Order.application;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class OrderInfoResponse {
    private Long id;

    private LocalDateTime orderTime;

    private RestaurantInfo restaurant;
}
