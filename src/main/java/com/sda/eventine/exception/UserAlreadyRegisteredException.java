package com.sda.eventine.exception;

public class UserAlreadyRegisteredException extends RuntimeException{
    public UserAlreadyRegisteredException(String message) {
        super(message);
    }
}
