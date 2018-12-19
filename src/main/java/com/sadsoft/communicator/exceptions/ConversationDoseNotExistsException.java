package com.sadsoft.communicator.exceptions;

public class ConversationDoseNotExistsException extends RuntimeException {
    public ConversationDoseNotExistsException(String conversationsName) {
        super("Conversation: " + conversationsName + " does not exists");
    }
}
