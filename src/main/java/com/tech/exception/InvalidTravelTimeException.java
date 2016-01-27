package com.tech.exception;

public class InvalidTravelTimeException extends RuntimeException {

    public InvalidTravelTimeException(String message) {
        super(message);
    }

    public InvalidTravelTimeException(Throwable throwable) {
        super(throwable);
    }

    public InvalidTravelTimeException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
