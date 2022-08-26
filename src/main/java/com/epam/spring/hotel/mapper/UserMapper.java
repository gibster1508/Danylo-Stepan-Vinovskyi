package com.epam.spring.hotel.mapper;


import com.epam.spring.hotel.dto.UserDto;
import com.epam.spring.hotel.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDto mapUserToUserDto(User user);

    User mapUserDtoToUser(UserDto userDto);

    List<UserDto> mapToUserListDto(List<User> users);

    @Mapping(target = "idUser", ignore = true)
    User updateUser(@MappingTarget User oldUser, User updateUser);
}
