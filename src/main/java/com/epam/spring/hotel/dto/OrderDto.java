package com.epam.spring.hotel.dto;

import com.epam.spring.hotel.model.PaymentStatus;
import com.epam.spring.hotel.model.User;
import com.epam.spring.hotel.model.enums.TypesOfRooms;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {

    private Long idOrder;

    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date dateOfSettlement;

    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date dateOfDeparture;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    @NotNull
    private Date dateOfCreateOrder;
    private PaymentStatus paymentStatus;

    @NotNull
    private TypesOfRooms typeOfRoom;

    private User user;
}
