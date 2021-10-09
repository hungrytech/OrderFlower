package com.orderflow.orderflow.Restaurant.presentation;

import com.orderflow.orderflow.Restaurant.application.*;
import com.orderflow.orderflow.common.dto.ApiResponse;
import com.orderflow.orderflow.common.dto.ApiUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1")
@RestController
@RequiredArgsConstructor
public class RestaurantController {
    private final RestaurantService restaurantService;

    @PostMapping("/restaurant/new")
    public ResponseEntity<ApiResponse<RestaurantSaveResponse>> saveRestaurant(@RequestBody RestaurantSaveRequest request) {
        return new ResponseEntity<>(ApiUtils.convertResponse(restaurantService.saveRestaurant(request)),
                HttpStatus.CREATED);
    }

    @PutMapping("/restaurant/{restaurantId}")
    public ResponseEntity<?> updateRestaurant(@RequestBody RestaurantUpdateRequest request,
                                              @PathVariable("restaurantId") Long id) {
        restaurantService.updateInfo(request, id);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/restaurant/{restaurantId}")
    public ResponseEntity<?> deleteRestaurant(@PathVariable("restaurantId") Long id) {
        restaurantService.deleteRestaurant(id);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/restaurants")
    public ResponseEntity<ApiResponse<List<RestaurantInfoResponse>>> findAllRestaurants() {
        List<RestaurantInfoResponse> restaurants = restaurantService.findAllRestaurants();

        return new ResponseEntity<>(ApiUtils.convertResponse(restaurants), HttpStatus.OK);
    }

}
