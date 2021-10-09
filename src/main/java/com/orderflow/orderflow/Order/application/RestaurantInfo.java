package com.orderflow.orderflow.Order.application;

import com.orderflow.orderflow.Restaurant.domain.Address;
import com.orderflow.orderflow.Restaurant.domain.Restaurant;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RestaurantInfo {

    private String name;

    private String phoneNumber;

    private Address address;

    public RestaurantInfo(Restaurant restaurant) {
        name = restaurant.getName();
        phoneNumber = restaurant.getPhoneNumber();
        address = restaurant.getAddress();

    }

}
