package com.epam.spring.hotel.controller;

import com.epam.spring.hotel.controller.dto.OrderDto;
import com.epam.spring.hotel.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/order")
    public OrderDto createOrder(@RequestBody OrderDto orderDto) {
        return orderService.createOrder(orderDto);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/order")
    public List<OrderDto> getAllOrders() {
        return orderService.listOrders();
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping(value = "/order/{idOrder}")
    public OrderDto updateOrder(@PathVariable long idOrder, @RequestBody OrderDto orderDto) {
        return orderService.updateOrder(idOrder, orderDto);
    }
}
