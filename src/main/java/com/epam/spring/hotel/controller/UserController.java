package com.epam.spring.hotel.controller;

import com.epam.spring.hotel.api.UserApi;
import com.epam.spring.hotel.dto.OrderDto;
import com.epam.spring.hotel.dto.UserDto;
import com.epam.spring.hotel.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController implements UserApi {
    private final UserService userService;

    public List<UserDto> getAllUsers() {
        return userService.listUsers();
    }

    public UserDto getUser(String email) {
        return userService.getUser(email);
    }

    public UserDto createUser(UserDto userDto) {
        return userService.createUser(userDto);
    }

    public UserDto updateUser(String email, UserDto userDto) {
        return userService.updateUser(email, userDto);
    }

    public ResponseEntity<Void> deleteUser(Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    public Page<OrderDto> getAllUserOrders(long id, int pageSize, int pageNumber, String sortType) {
        return userService.getAllUserOrders(id, PageRequest.of(pageSize, pageNumber, Sort.by(Sort.DEFAULT_DIRECTION, sortType)));
    }
}
