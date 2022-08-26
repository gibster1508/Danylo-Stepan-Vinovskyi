package com.epam.spring.hotel.service;

import com.epam.spring.hotel.dto.OrderDto;

import java.util.List;

public interface OrderService {
    OrderDto createOrder(OrderDto orderDto);

    List<OrderDto> listOrders();

    OrderDto updateOrder(long idOrder, OrderDto orderDto);
}
