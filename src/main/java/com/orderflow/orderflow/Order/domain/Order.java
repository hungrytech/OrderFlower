package com.orderflow.orderflow.Order.domain;

import com.orderflow.orderflow.Order.application.OrderRequest;
import com.orderflow.orderflow.Restaurant.domain.Restaurant;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "Orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ORDER_ID")
    private Long id;

    @CreationTimestamp
    private LocalDateTime orderTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant")
    private Restaurant restaurant;

    private Order(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public static Order createOrder(OrderRequest request, Restaurant restaurant) {
        if(restaurant.isSame(request)) {
            return new Order(restaurant);
        }
        throw new IllegalArgumentException("가게 정보가 알맞지 않습니다.");
    }

    public Long getId() {
        return id;
    }
}
