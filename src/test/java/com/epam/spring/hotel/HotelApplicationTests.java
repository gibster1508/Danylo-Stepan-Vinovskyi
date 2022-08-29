package com.epam.spring.hotel;

import com.epam.spring.hotel.dto.UserDto;
import com.epam.spring.hotel.exception.EntityNotFoundException;
import com.epam.spring.hotel.model.User;
import com.epam.spring.hotel.test.util.TestUserDataUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Profile("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class HotelApplicationTests {

    @Value("http://localhost:${local.server.port}/hotel/user/")
    private String baseUrl;

    @Value("${app.user.email}")
    private String email;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    void createUserTest() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        UserDto userDto = TestUserDataUtil.createUserDto();
        HttpEntity<UserDto> httpEntity = new HttpEntity<>(userDto, headers);

        User user = testRestTemplate.postForObject(baseUrl, httpEntity, User.class);
        assertEquals(user.getEmail(), email);
    }

    @Test
    void getUserTest() {
        User user = testRestTemplate.getForObject(baseUrl + email, User.class);
        assertEquals(user.getEmail(), email);
    }

    @Test
    void deleteUserTest() {
        testRestTemplate.delete(baseUrl + email);
        testRestTemplate.getForObject(baseUrl + email, EntityNotFoundException.class);
    }


}
