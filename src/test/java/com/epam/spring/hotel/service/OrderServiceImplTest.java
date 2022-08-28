package com.epam.spring.hotel.service;

import com.epam.spring.hotel.dto.OrderDto;
import com.epam.spring.hotel.exception.EntityNotFoundException;
import com.epam.spring.hotel.mapper.OrderMapper;
import com.epam.spring.hotel.model.Order;
import com.epam.spring.hotel.model.PaymentStatus;
import com.epam.spring.hotel.model.User;
import com.epam.spring.hotel.model.enums.TypesOfRooms;
import com.epam.spring.hotel.repository.OrderRepository;
import com.epam.spring.hotel.repository.PaymentStatusRepository;
import com.epam.spring.hotel.service.impl.OrderServiceImpl;
import com.epam.spring.hotel.test.util.TestOrderDataUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static com.epam.spring.hotel.test.util.TestOrderDataUtil.ID;
import static java.util.Arrays.asList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith({MockitoExtension.class})
public class OrderServiceImplTest {

    @InjectMocks
    private OrderServiceImpl orderService;

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private PaymentStatusRepository paymentStatusRepository;


    @Test
    void createOrderTest() {
        Order testOrder = TestOrderDataUtil.createOrder();
        OrderDto testOrderDto = TestOrderDataUtil.createOrderDto();

        when(orderRepository.save(any())).thenReturn(testOrder);


        PaymentStatus paymentStatus = new PaymentStatus();
        when(paymentStatusRepository.findByStatusId(1L)).thenReturn(Optional.of(paymentStatus));
        OrderDto orderDto = orderService.createOrder(testOrderDto);

        assertThat(orderDto, allOf(
                hasProperty("user", equalTo(testOrderDto.getUser())),
                hasProperty("typeOfRoom", equalTo(testOrderDto.getTypeOfRoom()))
        ));
    }

    @Test
    void updateUserTest() {
        Order testOrder = TestOrderDataUtil.createOrder();
        OrderDto testOrderDto = TestOrderDataUtil.createOrderDto();


        when(orderRepository.findById(testOrderDto.getIdOrder())).thenReturn(Optional.of(testOrder));
        when(orderRepository.save(any())).thenReturn(testOrder);

        OrderDto orderDto = orderService.updateOrder(testOrder.getIdOrder(), testOrderDto);

        assertThat(orderDto, allOf(
                hasProperty("user", equalTo(testOrderDto.getUser())),
                hasProperty("paymentStatus", equalTo(testOrderDto.getPaymentStatus())),
                hasProperty("typeOfRoom", equalTo(testOrderDto.getTypeOfRoom()))
        ));
    }

    @Test
    void getAllOrdersTest() {
        Order order = Order.builder()
                .idOrder(3L)
                .dateOfCreateOrder(new Date())
                .dateOfDeparture(new Date())
                .dateOfSettlement(new Date())
                .paymentStatus(new PaymentStatus())
                .typeOfRoom(TypesOfRooms.COMFORT)
                .user(new User())
                .build();

        Order order2 = Order.builder()
                .idOrder(5L)
                .dateOfCreateOrder(new Date())
                .dateOfDeparture(new Date())
                .dateOfSettlement(new Date())
                .paymentStatus(new PaymentStatus())
                .typeOfRoom(TypesOfRooms.ECONOMY)
                .user(new User())
                .build();

        Order order3 = Order.builder()
                .idOrder(10L)
                .dateOfCreateOrder(new Date())
                .dateOfDeparture(new Date())
                .dateOfSettlement(new Date())
                .paymentStatus(new PaymentStatus())
                .typeOfRoom(TypesOfRooms.LUX)
                .user(new User())
                .build();


        given(orderRepository.findAll()).willReturn(asList(order, order2, order3));
        List<OrderDto> allOrders = orderService.listOrders();
        assertThat(allOrders, containsInAnyOrder(OrderMapper.INSTANCE.mapOrderToOrderDto(order3),
                OrderMapper.INSTANCE.mapOrderToOrderDto(order2), OrderMapper.INSTANCE.mapOrderToOrderDto(order)));
    }

    @Test
    void updateOrderOrderNotFoundTest() {
        OrderDto testOrderDto = TestOrderDataUtil.createOrderDto();
        when(orderRepository.findById(testOrderDto.getIdOrder())).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class,
                () -> orderService.updateOrder(testOrderDto.getIdOrder(), testOrderDto));

    }

    @Test
    void getAllUserOrdersTest() {
        List<Order> orders = List.of(TestOrderDataUtil.createOrder(),
                TestOrderDataUtil.createOrder(), TestOrderDataUtil.createOrder(), TestOrderDataUtil.createOrder());
        when(orderRepository.findOrderByIdUser(any(), any())).thenReturn(orders);
        List<OrderDto> allUserOrders = orderService.getAllUserOrders(ID, PageRequest.of(0, 1, Sort.by(Sort.DEFAULT_DIRECTION, "IdUser")));

        verify(orderRepository).findOrderByIdUser(any(), any());
        assertThat(allUserOrders, hasSize(orders.size()));
    }
}
