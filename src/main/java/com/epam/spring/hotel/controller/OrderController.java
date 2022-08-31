package com.epam.spring.hotel.controller;

import com.epam.spring.hotel.api.OrderApi;
import com.epam.spring.hotel.dto.OrderDto;
import com.epam.spring.hotel.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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

    @Override
    public List<OrderDto> getAllUserOrders(long id, int pageSize, int pageNumber, String sortType) {
        return orderService.getAllUserOrders(id, PageRequest.of(pageSize, pageNumber, Sort.by(Sort.DEFAULT_DIRECTION, sortType)));
    }
}

