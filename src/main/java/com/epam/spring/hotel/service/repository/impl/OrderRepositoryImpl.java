package com.epam.spring.hotel.service.repository.impl;

import com.epam.spring.hotel.service.model.Order;
import com.epam.spring.hotel.service.repository.OrderRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrderRepositoryImpl implements OrderRepository {
    private final List<Order> list = new ArrayList<>();


    @Override
    public Order createOrder(Order order) {
        list.add(order);
        return order;
    }

    @Override
    public List<Order> listOrders() {
        return new ArrayList<>(list);
    }

    @Override
    public Order updateOrder(long idOrder, Order order) {
        boolean isDeleted = list.removeIf(u -> u.getIdOrder() == idOrder);
        if (isDeleted) {
            list.add(order);
        } else {
            throw new RuntimeException("Order is not found!");
        }
        return order;
    }
}
