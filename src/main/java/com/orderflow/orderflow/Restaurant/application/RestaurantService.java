package com.orderflow.orderflow.Restaurant.application;

import java.util.List;

public interface RestaurantService {

    RestaurantSaveResponse saveRestaurant(RestaurantSaveRequest request);

    void updateInfo(RestaurantUpdateRequest request, Long id);

    void deleteRestaurant(Long id);

    List<RestaurantInfoResponse> findAllRestaurants();
}
