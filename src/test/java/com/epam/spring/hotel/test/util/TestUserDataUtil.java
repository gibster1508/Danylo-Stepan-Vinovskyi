package com.epam.spring.hotel.test.util;

import com.epam.spring.hotel.dto.UserDto;
import com.epam.spring.hotel.model.User;
import com.epam.spring.hotel.model.UserRole;

import java.util.Date;


public class TestUserDataUtil {
    public static final String FIRST_NAME = "FirstName";
    public static final String LAST_NAME = "LastName";
    public static final String TEST_EMAIL = "email@email.com";
    public static final String PASSWORD = "DanylO!@15082002";
    public static final String MOBILE = "0955596551";
    public static final Date DATE = new Date();
    public static final UserRole userRole = new UserRole();

    public static User createUser() {
        return User.builder()
                .firstName(FIRST_NAME)
                .lastName(LAST_NAME)
                .email(TEST_EMAIL)
                .password(PASSWORD)
                .dob(DATE)
                .role(userRole)
                .mobile(MOBILE)
                .build();
    }

    public static UserDto createUserDto() {
        return UserDto.builder()
                .firstName(FIRST_NAME)
                .lastName(LAST_NAME)
                .email(TEST_EMAIL)
                .password(PASSWORD)
                .dob(DATE)
                .role(userRole)
                .mobile(MOBILE)
                .build();
    }
}
