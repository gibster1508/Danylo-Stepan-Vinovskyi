package com.epam.spring.hotel.controller;

import com.epam.spring.hotel.dto.UserDto;
import com.epam.spring.hotel.exception.EntityNotFoundException;
import com.epam.spring.hotel.service.UserService;
import com.epam.spring.hotel.test.config.TestConfig;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static com.epam.spring.hotel.test.util.TestUserDataUtil.TEST_EMAIL;
import static com.epam.spring.hotel.test.util.TestUserDataUtil.createUserDto;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(UserController.class)
@Import(TestConfig.class)
public class UserControllerTest {
    public static final String URL = "/hotel/user/";

    @MockBean
    private UserService userService;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getUserTest() throws Exception {
        UserDto userDto = createUserDto();

        when(userService.getUser(TEST_EMAIL)).thenReturn(userDto);

        mockMvc.perform(get(URL + TEST_EMAIL))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.email").value(TEST_EMAIL));
    }

    @Test
    void createUserTest() throws Exception {
        UserDto userDto = createUserDto();
        when(userService.createUser(userDto)).thenReturn(userDto);

        mockMvc.perform(post(URL)
                        .content(objectMapper.writeValueAsString(userDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.email").value(TEST_EMAIL));
    }

    @Test
    void updateUserTest() throws Exception {
        UserDto userDto = createUserDto();

        when(userService.updateUser(TEST_EMAIL, userDto)).thenReturn(userDto);

        mockMvc.perform(put(URL + TEST_EMAIL)
                        .content(objectMapper.writeValueAsString(userDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.email").value(TEST_EMAIL));
    }

    @Test
    void getAllUsersTest() throws Exception {
        UserDto userDto = createUserDto();

        when(userService.listUsers()).thenReturn(Collections.singletonList(userDto));

        mockMvc.perform(get(URL))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].email").value(TEST_EMAIL));
    }

    @Test
    void deleteUserTest() throws Exception {
        mockMvc.perform(delete(URL + TEST_EMAIL))
                .andExpect(status().isNoContent());

        verify(userService).deleteUser(TEST_EMAIL);
    }

    @Test
    void updateNotValidUserTest() throws Exception {
        UserDto userDto = createUserDto();
        userDto.setFirstName("");
        userDto.setLastName("");
        userDto.setEmail("a");
        userDto.setPassword("");

        when(userService.updateUser(eq(userDto.getEmail()), eq(userDto))).thenReturn(userDto);

        mockMvc.perform(put(URL + userDto.getEmail())
                        .content(objectMapper.writeValueAsString(userDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    void updateNotExistingUserTest() throws Exception {
        UserDto userDto = createUserDto();

        when(userService.updateUser(TEST_EMAIL, userDto)).thenThrow(new EntityNotFoundException("User is not found"));

        mockMvc.perform(put(URL + TEST_EMAIL)
                        .content(objectMapper.writeValueAsString(userDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound());
    }
}
