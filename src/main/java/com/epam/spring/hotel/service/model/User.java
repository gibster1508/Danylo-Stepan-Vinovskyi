package com.epam.spring.hotel.service.model;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class User {
    private long idUser;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Date dob;
    private String mobileNo;
    private String role;
}
