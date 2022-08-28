package com.epam.spring.hotel;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class WebHotelApplication {
    public static void main(String[] args) {
        SpringApplication.run(WebHotelApplication.class, args);
    }
}
