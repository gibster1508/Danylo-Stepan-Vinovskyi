package com.epam.spring.hotel.service.impl;

import com.epam.spring.hotel.controller.dto.OrderDto;
import com.epam.spring.hotel.service.OrderService;
import com.epam.spring.hotel.service.mapper.OrderMapper;
import com.epam.spring.hotel.service.model.Order;
import com.epam.spring.hotel.service.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;

    @Override
    public OrderDto createOrder(OrderDto orderDto) {
        log.info("createOrder with idOrder {}", orderDto.getIdOrder());
        Order newOrder = orderRepository.createOrder(OrderMapper.INSTANCE.mapOrderDtoToOrder(orderDto));
        return OrderMapper.INSTANCE.mapOrderToOrderDto(newOrder);
    }

    @Override
    public List<OrderDto> listOrders() {
        log.info("get all orders");
        List<Order> orders = orderRepository.listOrders();
        return OrderMapper.INSTANCE.mapToOrderListDto(orders);
    }

    @Override
    public OrderDto updateOrder(long idOrder, OrderDto orderDto) {
        log.info("updateOrder with orderId {}", orderDto.getIdOrder());
        Order order = OrderMapper.INSTANCE.mapOrderDtoToOrder(orderDto);
        Order updatedOrder = orderRepository.updateOrder(order.getIdOrder(), order);
        return OrderMapper.INSTANCE.mapOrderToOrderDto(updatedOrder);
    }
}
