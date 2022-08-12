package com.epam.spring.hotel.service.repository;

import com.epam.spring.hotel.service.model.Order;

import java.util.List;

public interface OrderRepository {
    Order createOrder(Order order);

    List<Order> listOrders();

    Order updateOrder(long idOrder, Order order);
}
