package com.example.alumniassociation.customException;

public class EventFullException extends RuntimeException {
    public EventFullException(String message) {
        super(message);
    }
}
