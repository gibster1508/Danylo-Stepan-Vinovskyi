package com.epam.spring.hotel.service;

import com.epam.spring.hotel.dto.UserDto;
import com.epam.spring.hotel.exception.EntityNotFoundException;
import com.epam.spring.hotel.mapper.UserMapper;
import com.epam.spring.hotel.model.User;
import com.epam.spring.hotel.model.UserRole;
import com.epam.spring.hotel.repository.UserRepository;
import com.epam.spring.hotel.repository.UserRoleRepository;
import com.epam.spring.hotel.service.impl.UserServiceImpl;
import com.epam.spring.hotel.test.util.TestUserDataUtil;
import com.github.javafaker.Faker;
import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

import static com.epam.spring.hotel.test.util.TestUserDataUtil.*;
import static java.util.Arrays.asList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith({MockitoExtension.class})
public class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserRoleRepository userRoleRepository;

    @Test
    void getAllUsersTest() {
        FakeValuesService fakeValuesService = new FakeValuesService(
                new Locale("en-US"), new RandomService());
        Faker faker = new Faker();
        User user = User.builder()
                .firstName(faker.address().firstName())
                .lastName(faker.address().lastName())
                .email(fakeValuesService.bothify("????##@gmail.com"))
                .password(PASSWORD)
                .dob(DATE)
                .role(userRole)
                .build();

        User user2 = User.builder()
                .firstName(faker.address().firstName())
                .lastName(faker.address().lastName())
                .email(fakeValuesService.bothify("????##@gmail.com"))
                .password(PASSWORD)
                .dob(DATE)
                .role(userRole)
                .build();

        User user3 = User.builder()
                .firstName(faker.address().firstName())
                .lastName(faker.address().lastName())
                .email(fakeValuesService.bothify("????##@gmail.com"))
                .password(PASSWORD)
                .dob(DATE)
                .role(userRole)
                .build();


        given(userRepository.findAll()).willReturn(asList(user, user2, user3));
        List<UserDto> allUsers = userService.listUsers();
        assertThat(allUsers, containsInAnyOrder(UserMapper.INSTANCE.mapUserToUserDto(user3),
                UserMapper.INSTANCE.mapUserToUserDto(user2), UserMapper.INSTANCE.mapUserToUserDto(user)));

    }

    @Test
    void getUserTest() {
        User user = TestUserDataUtil.createUser();
        when(userRepository.findByEmail(TEST_EMAIL)).thenReturn(Optional.of(user));

        UserDto userDto = userService.getUser(TEST_EMAIL);

        assertThat(userDto, allOf(
                hasProperty("email", equalTo(user.getEmail())),
                hasProperty("firstName", equalTo(user.getFirstName()))
        ));
    }


    @Test
    void getUserUserNotFoundTest() {
        when(userRepository.findByEmail(TEST_EMAIL)).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> userService.getUser(TEST_EMAIL));
    }

    @Test
    void createUserTest() {
        User testUser = TestUserDataUtil.createUser();
        UserDto testUserDto = TestUserDataUtil.createUserDto();
        when(userRepository.save(any())).thenReturn(testUser);

        UserRole UserRole = new UserRole();
        when(userRoleRepository.findByRoleName("user")).thenReturn(Optional.of(UserRole));
        UserDto userDto = userService.createUser(testUserDto);

        assertThat(userDto, allOf(
                hasProperty("firstName", equalTo(testUserDto.getFirstName())),
                hasProperty("lastName", equalTo(testUserDto.getLastName())),
                hasProperty("email", equalTo(testUserDto.getEmail())),
                hasProperty("password", equalTo(testUserDto.getPassword()))
        ));
    }

    @Test
    void createUserUserAlreadyExistTest() {
        UserDto testUserDto = TestUserDataUtil.createUserDto();
        when(userRepository.existsByEmail(TEST_EMAIL)).thenReturn(true);

        assertThrows(EntityNotFoundException.class, () -> userService.createUser(testUserDto));
    }

    @Test
    void updateUserTest() {
        UserDto testUserDto = TestUserDataUtil.createUserDto();
        User testUser = TestUserDataUtil.createUser();

        when(userRepository.findByEmail(testUserDto.getEmail())).thenReturn(Optional.of(testUser));
        when(userRepository.save(any())).thenReturn(testUser);

        UserDto userDto = userService.updateUser(testUser.getEmail(), testUserDto);

        assertThat(userDto, allOf(
                hasProperty("firstName", equalTo(testUserDto.getFirstName())),
                hasProperty("lastName", equalTo(testUserDto.getLastName())),
                hasProperty("email", equalTo(testUserDto.getEmail())),
                hasProperty("password", equalTo(testUserDto.getPassword()))
        ));
    }

    @Test
    void deleteUserTest() {
        User testUser = TestUserDataUtil.createUser();
        when(userRepository.findByEmail(TEST_EMAIL)).thenReturn(Optional.of(testUser));

        userService.deleteUser(testUser.getEmail());
        verify(userRepository, times(1)).delete(testUser);
    }

    @Test
    void updateUserUserNotFoundTest() {
        UserDto testUserDto = TestUserDataUtil.createUserDto();
        when(userRepository.findByEmail(TEST_EMAIL)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class,
                () -> userService.updateUser(testUserDto.getEmail(), testUserDto));

    }


    @Test
    void deleteUserUserNotFoundTest() {
        when(userRepository.findByEmail(TEST_EMAIL)).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> userService.deleteUser(TEST_EMAIL));
    }
}
