package com.orderflow.orderflow.Restaurant.application;

import com.orderflow.orderflow.Restaurant.domain.Restaurant;
import com.orderflow.orderflow.Restaurant.domain.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RestaurantServiceImpl implements RestaurantService{

    private final RestaurantRepository restaurantRepository;
    private final ModelMapper modelMapper;

    @Transactional
    @Override
    public RestaurantSaveResponse saveRestaurant(RestaurantSaveRequest request) {
        Restaurant restaurant = Restaurant.createRestaurant(request);
        return new RestaurantSaveResponse(restaurantRepository.save(restaurant).getId());
    }

    @Transactional
    @Override
    public void updateInfo(RestaurantUpdateRequest request, Long id) {
        Optional<Restaurant> optionalRestaurant = restaurantRepository.findById(id);

        optionalRestaurant.ifPresent(restaurant -> restaurant.updateInfo(request));
    }

    @Transactional
    @Override
    public void deleteRestaurant(Long id) {
        Optional<Restaurant> optionalRestaurant = restaurantRepository.findById(id);
        optionalRestaurant.ifPresent(restaurantRepository::delete);
    }

    @Override
    public List<RestaurantInfoResponse> findAllRestaurants() {
        return restaurantRepository.findAll().stream()
                .map(restaurant -> modelMapper.map(restaurant, RestaurantInfoResponse.class))
                .collect(Collectors.toList());

    }
}
