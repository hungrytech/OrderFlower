package com.orderflow.orderflow.Restaurant.domain;

import com.orderflow.orderflow.Order.application.OrderRequest;
import com.orderflow.orderflow.Order.application.RestaurantInfo;
import com.orderflow.orderflow.Restaurant.application.RestaurantSaveRequest;
import com.orderflow.orderflow.Restaurant.application.RestaurantUpdateRequest;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static org.springframework.util.StringUtils.hasText;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "RESTAURENT_ID")
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String phoneNumber;

    @Embedded
    private Address address;

    private Restaurant(String name, String phoneNumber, Address address) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    public static Restaurant createRestaurant(RestaurantSaveRequest request) {
        validateInfo(request.getName(), request.getPhoneNumber(), request.getAddress());

        return new Restaurant(request.getName(), request.getPhoneNumber(), request.getAddress());
    }

    public void updateInfo(RestaurantUpdateRequest request) {
        validateInfo(request.getName(), request.getPhoneNumber(), request.getAddress());

        name = request.getName();
        phoneNumber = request.getPhoneNumber();
        address = request.getAddress();
    }

    public boolean isSame(OrderRequest request) {
        RestaurantInfo restaurantInfo = request.getRestaurantInfo();
        return (name.equals(restaurantInfo.getName())) &&
                (phoneNumber.equals(restaurantInfo.getPhoneNumber())) &&
                (address.isSame(restaurantInfo.getAddress()));
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Address getAddress() {
        return address;
    }

    // 추후 다른 객체로 분리 해야함
    private static void validateInfo(String name, String phoneNumber, Address address) {
        if(!(hasText(name) && hasText(phoneNumber) && address.checkInfo())) {
            throw new IllegalArgumentException("식당 정보가 잘못되었습니다.");
        }
    }


}
