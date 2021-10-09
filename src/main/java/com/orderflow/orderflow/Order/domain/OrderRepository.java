package com.orderflow.orderflow.Order.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query("select o from Order o join fetch o.restaurant r where r.id =:restaurantId")
    List<Order> findOrdersByRestaurantId(@Param("restaurantId") Long restaurantId);
}
