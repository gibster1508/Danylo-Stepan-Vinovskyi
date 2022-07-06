package com.epam.spring.hotel.service.mapper;


import com.epam.spring.hotel.controller.dto.UserDto;
import com.epam.spring.hotel.service.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDto mapUserToUserDto(User user);

    User mapUserDtoToUser(UserDto userDto);

    List<UserDto> mapToUserListDto(List<User> users);
}