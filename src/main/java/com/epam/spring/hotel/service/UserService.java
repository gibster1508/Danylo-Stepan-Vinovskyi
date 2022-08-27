package com.epam.spring.hotel.service;

import com.epam.spring.hotel.dto.OrderDto;
import com.epam.spring.hotel.dto.UserDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {
    List<UserDto> listUsers();

    UserDto getUser(String email);

    UserDto createUser(UserDto userDto);

    UserDto updateUser(String email, UserDto userDto);

    void deleteUser(Long id);
}
