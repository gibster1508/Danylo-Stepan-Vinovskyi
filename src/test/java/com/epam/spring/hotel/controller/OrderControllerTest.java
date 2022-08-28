package com.epam.spring.hotel.controller;

import com.epam.spring.hotel.dto.OrderDto;
import com.epam.spring.hotel.service.OrderService;
import com.epam.spring.hotel.test.config.TestConfig;
import com.epam.spring.hotel.test.util.TestOrderDataUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static com.epam.spring.hotel.test.util.TestOrderDataUtil.ID;
import static com.epam.spring.hotel.test.util.TestOrderDataUtil.createOrderDto;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(OrderController.class)
@Import(TestConfig.class)
public class OrderControllerTest {
    public static final String URL = "/hotel/order/";

    @MockBean
    private OrderService orderService;


    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;


    @Test
    void getAllUserOrdersTest() throws Exception {
        OrderDto orderDto = TestOrderDataUtil.createOrderDto();

        when(orderService.listOrders()).thenReturn(Collections.singletonList(orderDto));

        mockMvc.perform(get(URL))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].idOrder").value(ID));
    }


    @Test
    void createOrderTest() throws Exception {
        OrderDto orderDto = createOrderDto();
        when(orderService.createOrder(any(OrderDto.class))).thenReturn(orderDto);

        mockMvc.perform(post(URL)
                        .content(objectMapper.writeValueAsString(orderDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.idOrder").value(ID));

    }

    @Test
    void updateUserTest() throws Exception {
        OrderDto orderDto = createOrderDto();

        when(orderService.updateOrder(eq(ID), any(OrderDto.class))).thenReturn(orderDto);

        mockMvc.perform(put(URL + ID)
                        .content(objectMapper.writeValueAsString(orderDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.idOrder").value(ID));
    }

    @Test
    void getAllUserOrders() throws Exception {
        OrderDto orderDto = createOrderDto();

        when(orderService.getAllUserOrders(eq(ID), eq(PageRequest.of(1, 1, Sort.by(Sort.DEFAULT_DIRECTION, "idOrder"))))).thenReturn(Collections.singletonList(orderDto));
        mockMvc.perform(get(URL + "user/" + ID + "?pageSize=1&pageNumber=1&sortType=idOrder"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].idOrder").value(ID));
    }

    @Test
    void getInternalServerErrorAllUserOrders() throws Exception {
        when(orderService.getAllUserOrders(eq(ID), eq(PageRequest.of(1, 1, Sort.by(Sort.DEFAULT_DIRECTION, "idOrder"))))).thenThrow(new NullPointerException());
        mockMvc.perform(get(URL + "user/" + ID + "?pageSize=1&pageNumber=1&sortType=idOrder"))
                .andDo(print())
                .andExpect(status().isInternalServerError());

    }
}