package com.epam.spring.hotel.service.model;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class Order {
    private long idOrder;
    private Date dateOfSettlement;
    private Date dateOfDeparture;
    private Date dateOfCreateOrder;
    private String paymentStatus;
    private User user;
}
