package com.epam.spring.hotel.service.impl;

import com.epam.spring.hotel.controller.dto.UserDto;
import com.epam.spring.hotel.service.UserService;
import com.epam.spring.hotel.service.mapper.UserMapper;
import com.epam.spring.hotel.service.model.User;
import com.epam.spring.hotel.service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;


@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public List<UserDto> listUsers() {
        log.info("get all users");
        List<User> users = userRepository.listUsers();
        return UserMapper.INSTANCE.mapToUserListDto(users);
    }

    @Override
    public UserDto getUser(String email) {
        log.info("get user by email: {}", email);
        User user = userRepository.getUser(email);
        return UserMapper.INSTANCE.mapUserToUserDto(user);
    }

    @Override
    public UserDto createUser(UserDto user) {
        log.info("create user: {}", user);
        User createdUser = userRepository.createUser(UserMapper.INSTANCE.mapUserDtoToUser(user));
        return UserMapper.INSTANCE.mapUserToUserDto(createdUser);
    }

    @Override
    public UserDto updateUser(String oldLogin, UserDto user) {
        log.info("update user by email: {}", oldLogin);
        User oldUser = userRepository.updateUser(oldLogin, UserMapper.INSTANCE.mapUserDtoToUser(user));
        return UserMapper.INSTANCE.mapUserToUserDto(oldUser);
    }

    @Override
    public void deleteUser(String email) {
        log.info("deleteUser with email {}", email);
        userRepository.deleteUser(email);
    }


}
