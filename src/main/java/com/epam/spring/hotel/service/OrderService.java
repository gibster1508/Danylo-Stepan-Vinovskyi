package com.epam.spring.hotel.service;

import com.epam.spring.hotel.dto.OrderDto;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface OrderService {
    OrderDto createOrder(OrderDto orderDto);

    List<OrderDto> listOrders();

    OrderDto updateOrder(long idOrder, OrderDto orderDto);

    List<OrderDto> getAllUserOrders(long id, Pageable pageable);
}
