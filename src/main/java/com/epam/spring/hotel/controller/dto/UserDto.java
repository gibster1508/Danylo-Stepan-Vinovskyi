package com.epam.spring.hotel.controller.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class UserDto {
    private long idUser;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Date dob;
    private String mobileNo;
    private String role;
}
