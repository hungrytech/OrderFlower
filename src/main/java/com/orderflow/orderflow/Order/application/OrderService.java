package com.orderflow.orderflow.Order.application;

import java.util.List;

public interface OrderService {

    OrderSaveResponse saveOrder(OrderRequest request, Long id);

    List<OrderInfoResponse> findOrders(Long id);

    void deleteOrder(Long id);
}
