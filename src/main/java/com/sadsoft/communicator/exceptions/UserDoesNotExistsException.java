package com.sadsoft.communicator.exceptions;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserDoesNotExistsException extends RuntimeException {

    public UserDoesNotExistsException(String s) {
        super(s);
    }
}
