package com.epam.spring.hotel.mapper;

import com.epam.spring.hotel.dto.OrderDto;
import com.epam.spring.hotel.model.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface OrderMapper {
    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    OrderDto mapOrderToOrderDto(Order Order);

    Order mapOrderDtoToOrder(OrderDto orderDto);

    List<OrderDto> mapToOrderListDto(List<Order> orders);

    @Mapping(target = "idOrder", ignore = true)
    Order updateOrder(@MappingTarget Order oldUser, Order updateUser);
}



