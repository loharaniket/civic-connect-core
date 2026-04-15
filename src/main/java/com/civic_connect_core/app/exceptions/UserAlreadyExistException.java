package com.civic_connect_core.app.exceptions;

public class UserAlreadyExistException extends RuntimeException {
    public UserAlreadyExistException(String msg) {
        super(msg);
    }
}
