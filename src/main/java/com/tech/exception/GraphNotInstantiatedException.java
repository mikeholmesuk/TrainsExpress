package com.tech.exception;

public class GraphNotInstantiatedException extends RuntimeException {

    public GraphNotInstantiatedException(String message) {
        super(message);
    }

    public GraphNotInstantiatedException(Throwable throwable) {
        super(throwable);
    }

    public GraphNotInstantiatedException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
