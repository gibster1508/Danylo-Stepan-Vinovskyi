package com.epam.spring.hotel.api;

import com.epam.spring.hotel.dto.UserDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Api(tags = "User management API")
@RequestMapping("/hotel/user")
public interface UserApi {

    @ApiOperation("Get all users")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/")
    List<UserDto> getAllUsers();

    @ApiImplicitParam(name = "email", paramType = "path", required = true, value = "User email")
    @ApiOperation("Get User")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/{email}")
    UserDto getUser(@PathVariable String email);

    @ApiOperation("Create user")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/")
    UserDto createUser(@RequestBody @Validated UserDto userDto);

    @ApiOperation("Update user")
    @ApiImplicitParam(name = "email", paramType = "path", required = true, value = "User email")
    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{email}")
    UserDto updateUser(@PathVariable String email, @RequestBody @Validated UserDto userDto);

    @ApiOperation("Delete user")
    @ApiImplicitParam(name = "email", paramType = "path", required = true, value = "User id")
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{email}")
    ResponseEntity<Void> deleteUser(@PathVariable String email);

}
