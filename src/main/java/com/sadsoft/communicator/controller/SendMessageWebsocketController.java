package com.sadsoft.communicator.controller;

import com.sadsoft.communicator.model.Message;
import com.sadsoft.communicator.model.User;
import com.sadsoft.communicator.model.dto.MessageResponseDto;
import com.sadsoft.communicator.service.AuthService;
import com.sadsoft.communicator.service.ConversationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.*;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;

@Controller
public class SendMessageWebsocketController {

    @Autowired
    private AuthService authService;

    @Autowired
    private ConversationService conversationService;

    @Autowired
    private SimpMessagingTemplate template;

    @MessageMapping("/{receiversName}")
    public void processMessageFromClient(@Payload String messageBody,
                                           @DestinationVariable String receiversName) {

        template.convertAndSend("/queue/"+receiversName, messageBody);
    }

    @MessageExceptionHandler
    public void handleException(RuntimeException exception) {
        throw new RuntimeException(exception.getMessage());
    }
}
