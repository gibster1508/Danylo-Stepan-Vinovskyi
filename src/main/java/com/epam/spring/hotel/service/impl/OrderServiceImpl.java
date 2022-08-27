package com.epam.spring.hotel.service.impl;

import com.epam.spring.hotel.dto.OrderDto;
import com.epam.spring.hotel.mapper.OrderMapper;
import com.epam.spring.hotel.model.Order;
import com.epam.spring.hotel.repository.OrderRepository;
import com.epam.spring.hotel.repository.PaymentStatusRepository;
import com.epam.spring.hotel.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final PaymentStatusRepository paymentStatusRepository;
    private final OrderRepository orderRepository;

    @Override
    public OrderDto createOrder(OrderDto orderDto) {
        log.info("createOrder with idOrder {}", orderDto.getIdOrder());
        orderDto.setPaymentStatus(paymentStatusRepository.findByStatusId(1L).orElseThrow(EntityNotFoundException::new));
        Order newOrder = orderRepository.save(OrderMapper.INSTANCE.mapOrderDtoToOrder(orderDto));
        log.info("Order with idOrder {} successfully created", orderDto.getIdOrder());
        return OrderMapper.INSTANCE.mapOrderToOrderDto(newOrder);
    }

    @Override
    public List<OrderDto> listOrders() {
        log.info("get all orders");
        List<Order> orders = orderRepository.findAll();
        return OrderMapper.INSTANCE.mapToOrderListDto(orders);
    }

    @Override
    public OrderDto updateOrder(long idOrder, OrderDto orderDto) {
        log.info("updateOrder with orderId {}", orderDto.getIdOrder());
        Order order = OrderMapper.INSTANCE.mapOrderDtoToOrder(orderDto);
        Order updatedOrder = orderRepository.save(order);
        return OrderMapper.INSTANCE.mapOrderToOrderDto(updatedOrder);
    }
    public List<OrderDto> getAllUserOrders(long id, Pageable pageable) {
        return orderRepository.findOrderByIdUser(id, pageable).stream().map(OrderMapper.INSTANCE::mapOrderToOrderDto).collect(Collectors.toList());
    }
}
