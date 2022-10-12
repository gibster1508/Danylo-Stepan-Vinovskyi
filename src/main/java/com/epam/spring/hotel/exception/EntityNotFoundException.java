package com.epam.spring.hotel.exception;

public class EntityNotFoundException extends RuntimeException {
    public static final String DEFAULT_MESSAGE = "Entity not found";

    public EntityNotFoundException(String message) {
        super(message);
    }

    public EntityNotFoundException() {
        super(DEFAULT_MESSAGE);
    }
}
