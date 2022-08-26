package com.epam.spring.hotel.controller;

import com.epam.spring.hotel.api.OrderApi;
import com.epam.spring.hotel.dto.OrderDto;
import com.epam.spring.hotel.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class OrderController implements OrderApi {
    private final OrderService orderService;

    @Override
    public OrderDto createOrder(OrderDto orderDto) {
        return orderService.createOrder(orderDto);
    }

    @Override
    public OrderDto updateOrder(int idOrder, OrderDto orderDto) {
        return orderService.updateOrder(idOrder, orderDto);
    }

    @Override
    public List<OrderDto> getAllOrders() {
        return orderService.listOrders();
    }
}
