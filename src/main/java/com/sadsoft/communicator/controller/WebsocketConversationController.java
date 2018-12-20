package com.sadsoft.communicator.controller;

import com.sadsoft.communicator.model.Conversation;
import com.sadsoft.communicator.model.Message;
import com.sadsoft.communicator.model.User;
import com.sadsoft.communicator.model.dto.MessageDto;
import com.sadsoft.communicator.service.AuthService;
import com.sadsoft.communicator.service.ConversationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.*;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;

@Controller
public class WebsocketConversationController {

    @Autowired
    private AuthService authService;

    @Autowired
    private ConversationService conversationService;

    @MessageMapping("/{conversationsName}")
    @SendTo("/queue/{conversationsName}")
    public MessageDto processMessageFromClient(@Payload String message,
                                           @DestinationVariable String conversationsName) {

        Conversation conversation = conversationService.getConversationByConversationsName(conversationsName);
        Message lastMessage = conversationService.getLastMessage(conversation);
        MessageDto messageDto = new MessageDto(lastMessage);

        System.out.println(messageDto.getMessageBody());

        return messageDto;
    }

    @MessageExceptionHandler
    public void handleException(RuntimeException exception) {
        throw new RuntimeException(exception.getMessage());
    }
}
