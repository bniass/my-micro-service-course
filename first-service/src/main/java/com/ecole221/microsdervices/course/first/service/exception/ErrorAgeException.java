package com.ecole221.microsdervices.course.first.service.exception;

public class ErrorAgeException extends RuntimeException {
    public ErrorAgeException(String message) {
        super(message);
    }

    public ErrorAgeException(String message, Throwable cause) {
        super(message, cause);
    }
}
