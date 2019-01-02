package com.sadsoft.communicator.controller;

import com.sadsoft.communicator.model.dto.MessageDto;
import com.sadsoft.communicator.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.*;
import org.springframework.stereotype.Controller;

@Controller
public class WebsocketConversationController {

    @Autowired
    private AuthService authService;

    @MessageMapping("/{conversationsName}")
    @SendTo("/queue/{conversationsName}")
    public MessageDto processMessageFromClient(@Payload MessageDto message,
                                               @DestinationVariable String conversationsName) {

        System.out.println(message);

        return message;
    }

    @MessageExceptionHandler
    public void handleException(RuntimeException exception) {
        throw new RuntimeException(exception.getMessage());
    }
}
