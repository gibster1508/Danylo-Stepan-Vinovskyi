package com.epam.spring.hotel.test.util;

import com.epam.spring.hotel.dto.OrderDto;
import com.epam.spring.hotel.model.Order;
import com.epam.spring.hotel.model.enums.TypesOfRooms;

import java.util.Date;

public class TestOrderDataUtil {

    public static final Long ID = 1000L;

    public static Order createOrder() {
        return Order.builder()
                .idOrder(ID)
                .dateOfCreateOrder(new Date())
                .dateOfDeparture(new Date())
                .dateOfSettlement(new Date())
                .typeOfRoom(TypesOfRooms.ECONOMY)
                .build();
    }

    public static OrderDto createOrderDto() {
        return OrderDto.builder()
                .idOrder(ID)
                .dateOfCreateOrder(new Date())
                .dateOfDeparture(new Date())
                .dateOfSettlement(new Date())
                .typeOfRoom(TypesOfRooms.ECONOMY)
                .build();
    }
}
