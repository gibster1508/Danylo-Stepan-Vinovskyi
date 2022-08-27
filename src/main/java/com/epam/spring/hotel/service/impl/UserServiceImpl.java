package com.epam.spring.hotel.service.impl;

import com.epam.spring.hotel.dto.OrderDto;
import com.epam.spring.hotel.dto.UserDto;
import com.epam.spring.hotel.exception.EntityNotFoundException;
import com.epam.spring.hotel.mapper.OrderMapper;
import com.epam.spring.hotel.mapper.UserMapper;
import com.epam.spring.hotel.model.User;
import com.epam.spring.hotel.repository.UserRepository;
import com.epam.spring.hotel.repository.UserRoleRepository;
import com.epam.spring.hotel.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;

    @Override
    public List<UserDto> listUsers() {
        log.info("get all users");
        List<User> users = userRepository.findAll();
        return UserMapper.INSTANCE.mapToUserListDto(users);
    }

    @Override
    public UserDto getUser(String email) {
        log.info("get user by email: {}", email);
        User user = userRepository.findByEmail(email).orElseThrow(() -> new EntityNotFoundException(
                "User not found with email " + email));
        return UserMapper.INSTANCE.mapUserToUserDto(user);
    }

    @Override
    public UserDto createUser(UserDto user) {
        log.info("create user: {}", user);
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new EntityNotFoundException("user with this email already exist");
        }
        user.setRole(userRoleRepository.findByRoleName("user").orElseThrow(javax.persistence.EntityNotFoundException::new));
        User createdUser = userRepository.save(UserMapper.INSTANCE.mapUserDtoToUser(user));
        log.info("user was succesfully created with email={} and id={}", user.getEmail(), user.getIdUser());
        return UserMapper.INSTANCE.mapUserToUserDto(createdUser);
    }

    @Override
    public UserDto updateUser(String oldLogin, UserDto user) {
        log.info("update user by email: {}", oldLogin);
        User persistedUser = userRepository.findByEmail(oldLogin).orElseThrow(() -> new EntityNotFoundException(
                "User not found with email " + oldLogin));
        User updatedUser = UserMapper.INSTANCE.updateUser(persistedUser, UserMapper.INSTANCE.mapUserDtoToUser(user));
        User oldUser = userRepository.save(updatedUser);
        log.info("User with email {} was successfully updated ", oldUser.getEmail());
        return UserMapper.INSTANCE.mapUserToUserDto(oldUser);
    }

    @Override
    public void deleteUser(Long id) {
        log.info("deleteUser by id {}", id);
        userRepository.deleteById(id);
    }


}
