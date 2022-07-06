package com.epam.spring.hotel.service.mapper;

import com.epam.spring.hotel.controller.dto.OrderDto;
import com.epam.spring.hotel.service.model.Order;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface OrderMapper {
    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    OrderDto mapOrderToOrderDto(Order Order);

    Order mapOrderDtoToOrder(OrderDto orderDto);

    List<OrderDto> mapToOrderListDto(List<Order> orders);
}



