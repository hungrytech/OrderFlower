package com.orderflow.orderflow.Restaurant.application;

import com.orderflow.orderflow.Restaurant.domain.Address;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RestaurantInfoResponse {

    private Long id;

    private String name;
        
    private String phoneNumber;

    private Address address;
}