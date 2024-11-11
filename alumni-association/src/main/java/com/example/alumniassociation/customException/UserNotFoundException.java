package com.example.alumniassociation.customException;


public class UserNotFoundException extends RuntimeException {


    public UserNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}

