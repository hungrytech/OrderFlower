package com.orderflow.orderflow.Order.application;

import com.orderflow.orderflow.Order.domain.Order;
import com.orderflow.orderflow.Order.domain.OrderRepository;
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
public class OrderServiceImpl implements OrderService{
    private final OrderRepository orderRepository;
    private final RestaurantRepository restaurantRepository;
    private final ModelMapper modelMapper;

    @Transactional
    @Override
    public OrderSaveResponse saveOrder(OrderRequest request, Long id) {
        Optional<Restaurant> optionalRestaurant = restaurantRepository.findById(id);
        Order order = optionalRestaurant.map(restaurant -> Order.createOrder(request, restaurant)).orElseThrow();
        return new OrderSaveResponse(orderRepository.save(order).getId());
    }

    @Transactional(readOnly = true)
    @Override
    public List<OrderInfoResponse>  findOrders(Long id) {
        List<Order> orders = orderRepository.findOrdersByRestaurantId(id);
        return orders.stream()
                .map(order -> new OrderInfoResponse(
                        order.getId(),
                        order.getOrderTime(), new RestaurantInfo(order.getRestaurant())))
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public void deleteOrder(Long id) {
        Optional<Order> optionalOrder = orderRepository.findById(id);

        Order order = optionalOrder.orElseThrow();
        orderRepository.delete(order);
    }
}
