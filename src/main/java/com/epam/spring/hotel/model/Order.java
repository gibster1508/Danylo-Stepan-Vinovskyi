package com.epam.spring.hotel.model;

import com.epam.spring.hotel.model.enums.TypesOfRooms;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    @ManyToOne()
    @JoinColumn(name = "id_user")
    User user;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idOrder;

    private Date dateOfSettlement;
    private Date dateOfDeparture;
    private Date dateOfCreateOrder;

    @Enumerated(EnumType.STRING)
    private TypesOfRooms typeOfRoom;

    @ManyToOne()
    @JoinColumn(name = "status_id")
    @JsonIgnore
    private PaymentStatus paymentStatus;
}
