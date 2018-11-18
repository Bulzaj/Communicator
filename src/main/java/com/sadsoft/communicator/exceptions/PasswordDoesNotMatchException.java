package com.sadsoft.communicator.exceptions;

public class PasswordDoesNotMatchException extends RuntimeException {

    public PasswordDoesNotMatchException(String s) {
        super(s);
    }
}
