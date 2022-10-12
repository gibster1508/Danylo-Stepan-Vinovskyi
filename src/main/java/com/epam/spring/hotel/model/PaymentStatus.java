package com.epam.spring.hotel.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PaymentStatus {

    @JsonIgnore
    @OneToMany(mappedBy = "paymentStatus")
    @ToString.Exclude
    List<Order> orders;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long statusId;

    private String name;
}
