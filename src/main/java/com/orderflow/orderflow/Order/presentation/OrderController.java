package com.orderflow.orderflow.Order.presentation;

import com.orderflow.orderflow.Order.application.OrderInfoResponse;
import com.orderflow.orderflow.Order.application.OrderRequest;
import com.orderflow.orderflow.Order.application.OrderSaveResponse;
import com.orderflow.orderflow.Order.application.OrderService;
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
public class OrderController {
    private final OrderService orderService;

    // 해당 식당의 모든 주문 조회
    @GetMapping("/orders/{restaurantId}")
    public ResponseEntity<ApiResponse<List<OrderInfoResponse>>> findAllOrdersByRestaurant(@PathVariable("restaurantId") Long id) {
        List<OrderInfoResponse> orders = orderService.findOrders(id);

        return ResponseEntity.ok(ApiUtils.convertResponse(orders));
    }

    @PostMapping("/orders/{restaurantId}/new")
    public ResponseEntity<ApiResponse<OrderSaveResponse>> saveOrder(@RequestBody OrderRequest request,
                                                                    @PathVariable("restaurantId") Long id) {
        return new ResponseEntity<>(ApiUtils.convertResponse(orderService.saveOrder(request, id)), HttpStatus.CREATED);
    }

    @DeleteMapping("/orders/{orderId}")
    public ResponseEntity<Void> deleteOrder(@PathVariable("orderId") Long id) {
        orderService.deleteOrder(id);

        return ResponseEntity.ok().build();
    }

}
