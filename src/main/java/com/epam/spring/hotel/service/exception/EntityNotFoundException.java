package com.epam.spring.hotel.service.exception;

public class EntityNotFoundException extends RuntimeException {
    public static final String DEFAULT_MESSAGE = "Entity not found";

    public EntityNotFoundException(String message) {
        super(message);
    }
}
