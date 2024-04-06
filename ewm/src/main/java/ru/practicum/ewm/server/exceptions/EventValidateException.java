package ru.practicum.ewm.server.exceptions;

public class EventValidateException extends RuntimeException {
    public EventValidateException(String message) {
        super(message);
    }
}

