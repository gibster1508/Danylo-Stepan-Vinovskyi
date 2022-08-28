package com.epam.spring.hotel;

import com.epam.spring.hotel.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.Profile;

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
    void getUserTest() {
        User user = testRestTemplate.getForObject(baseUrl + email, User.class);
        assertEquals(user.getEmail(), email);
    }

}
