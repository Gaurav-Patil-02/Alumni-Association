package com.example.alumniassociation.customException;




public class EventNotFoundException extends RuntimeException {
 
 
    public EventNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
